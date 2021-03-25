package Intefaces;

import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public interface InterfacePokemonView {
    API.Database dbAPI = new API.Database();

    default void loadGlobalContent(String gameName, AbstractPokemonController controller) {
        // Species
        ObservableList<PokeStatsList> species = dbAPI.getPokeStatsListFromSpecificGame(gameName);
        controller.SpeciesName.setItems(species);
        // Gender
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
        controller.Gender.setItems(genders);
        controller.Gender.getSelectionModel().selectFirst();
        // Nature
        ObservableList<Nature> natures = dbAPI.getAllNature();
        controller.Nature.setItems(natures);
        // Held Item
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item(-1, -1, "", "", 0, 0, 0));
        itemList.addAll(dbAPI.getAllItemsFromGame(gameName));
        controller.HeldItem.setItems(itemList);
        // Ability
        ObservableList<Ability> abilities = dbAPI.getAllAbilitiesFromGame(gameName);
        controller.Ability.setItems(abilities);
        // Met Location
        ObservableList<Route> metLocations = dbAPI.getALLMetLocationsFromGame(gameName);
        controller.MetLocation.setItems(metLocations);
        // Pokeballs
        ObservableList<Sprite> pokeballSprites = dbAPI.getAllPokeballs();
        controller.PokeBall.setItems(pokeballSprites);
        // Attacks
        ObservableList<Attack> attackList = FXCollections.observableArrayList();
        attackList.add(new Attack(-1, null, "", null, 0,0,null,null,0,null,null,false,false,false,false,false,false));
        attackList.addAll(dbAPI.getAllAttacksFromGame(gameName));
        controller.Attack1Move.setItems(FXCollections.observableArrayList(attackList));
        controller.Attack2Move.setItems(FXCollections.observableArrayList(attackList));
        controller.Attack3Move.setItems(FXCollections.observableArrayList(attackList));
        controller.Attack4Move.setItems(FXCollections.observableArrayList(attackList));

        // search filter
        setSearchFilterOnComboBox(controller);
    }

    default void setSearchFilterOnComboBox(AbstractPokemonController controller) {
        new AutoBoxComplete<>(controller.SpeciesName);
        new AutoBoxComplete<>(controller.Gender);
        new AutoBoxComplete<>(controller.Nature);
        new AutoBoxComplete<>(controller.HeldItem);
        new AutoBoxComplete<>(controller.Ability);
        new AutoBoxComplete<>(controller.MetLocation);
        new AutoBoxComplete<>(controller.PokeBall);
        new AutoBoxComplete<>(controller.Attack1Move);
        new AutoBoxComplete<>(controller.Attack2Move);
        new AutoBoxComplete<>(controller.Attack3Move);
        new AutoBoxComplete<>(controller.Attack4Move);
    }

    // PokeStats
    default void setPokemonStats(PokeStats pokeStats, AbstractPokemonController controller) {
        setMainPokemonStats(pokeStats, controller);
        setStatsPokemonStats(pokeStats, controller);
    }

    default void setMainPokemonStats(PokeStats pokeStats, AbstractPokemonController controller) {
        Utilities.selectSpecies(pokeStats.nameOfPokemon, controller.SpeciesName.getItems(), controller.SpeciesName);
        controller.Nickname.setText(pokeStats.nameOfPokemon);
    }

    default void setStatsPokemonStats(PokeStats pokeStats, AbstractPokemonController controller) {
        controller.BaseHp.setText(String.valueOf(pokeStats.baseHp));
        controller.BaseAttack.setText(String.valueOf(pokeStats.baseAttack));
        controller.BaseDefense.setText(String.valueOf(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(String.valueOf(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(String.valueOf(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(String.valueOf(pokeStats.baseSpeed));
        controller.calculateBaseIvEv();
        setStats(controller);
    }

    default void setStats(AbstractPokemonController controller) {
        int hp = controller.calculateHpStat();
        int attack = controller.calculateAttackStat();
        int defense = controller.calculateDefenseStat();
        int specialAttack = controller.calculateSpecialAttackStat();
        int specialDefense = controller.calculateSpecialDefenseStat();
        int speed = controller.calculateSpeedStat();

        controller.StatsHp.setText(Integer.toString(hp));
        controller.StatsAttack.setText(Integer.toString(attack));
        controller.StatsDefense.setText(Integer.toString(defense));
        controller.StatsSpecialAttack.setText(Integer.toString(specialAttack));
        controller.StatsSpecialDefense.setText(Integer.toString(specialDefense));
        controller.StatsSpeed.setText(Integer.toString(speed));
    }

    default void setBasicValues(AbstractPokemonController controller) {
        controller.Level.setText("5");
        controller.Friendship.setText("255");
        // Gender
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
        controller.Gender.setItems(genders);
        controller.Gender.getSelectionModel().selectFirst();
        // IV
        controller.IvHp.setText("0");
        controller.IvAttack.setText("0");
        controller.IvDefense.setText("0");
        controller.IvSpecialAttack.setText("0");
        controller.IvSpecialDefense.setText("0");
        controller.IvSpeed.setText("0");
        // EV
        controller.EvHp.setText("0");
        controller.EvAttack.setText("0");
        controller.EvDefense.setText("0");
        controller.EvSpecialAttack.setText("0");
        controller.EvSpecialDefense.setText("0");
        controller.EvSpeed.setText("0");
        // PP Ups
        controller.Attack1PPUps.setValue("0");
        controller.Attack2PPUps.setValue("0");
        controller.Attack3PPUps.setValue("0");
        controller.Attack4PPUps.setValue("0");
    }

    // Pokemon
    default void setPokemonStats(PokemonList pokeList, AbstractPokemonController controller) {
        Pokemon pokemon = dataSingleton.tempPokemon;
        PokeStats pokeStats = dataSingleton.pokeStats;

        if (pokeList.pokemonId > 0) pokemon = dbAPI.getPokemonFromPokemonId(pokeList.pokemonId);
        if (pokeList.pokemonStatsId > 0) pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokeList.pokemonStatsId);

        setMainStats(pokemon, pokeStats, controller);
        setMetStats(pokemon, controller);
        setStatsStats(pokemon, pokeStats, controller);
        setMoveStats(pokemon, controller);
    }

    default void setMainStats(Pokemon pokemon, PokeStats pokeStats, AbstractPokemonController controller) {
        // removes EventHandler otherwise it will trigger and tries to calculate stuff
        // while the rest of the data is not set and will result to an error
        EventHandler<ActionEvent> speciesHandler = controller.SpeciesName.getOnAction();
        EventHandler<ActionEvent> natureHandler = controller.Nature.getOnAction();
        controller.SpeciesName.setOnAction(null);
        controller.Nature.setOnAction(null);

        // select stats
        Utilities.selectSpecies(pokeStats.nameOfPokemon, controller.SpeciesName.getItems(), controller.SpeciesName);
        controller.Nickname.setText(pokemon.nickname);
        if (!pokemon.gender.equals("")) controller.Gender.setValue(pokemon.gender); else controller.Gender.getSelectionModel().selectFirst();
        controller.Level.setText(Integer.toString(pokemon.level));
        Utilities.selectNature(pokemon.natureName, controller.Nature.getItems(), controller.Nature);
        Utilities.selectHeldItem(pokemon.itemId, controller.HeldItem.getItems(), controller.HeldItem);
        if (pokemon.abilityId > 0) Utilities.selectAbility(pokemon.abilityId, controller.Ability.getItems(), controller.Ability); else controller.Ability.getSelectionModel().selectFirst();
        controller.Friendship.setText(Integer.toString(pokemon.friendship));

        // add the EventHandler back
        controller.SpeciesName.setOnAction(speciesHandler);
        controller.Nature.setOnAction(natureHandler);
    }

    default void setMetStats(Pokemon pokemon, AbstractPokemonController controller) {
        if (pokemon.metLocation > 0) Utilities.selectMetLocation(pokemon.metLocation, controller.MetLocation.getItems(), controller.MetLocation); else controller.MetLocation.getSelectionModel().selectFirst();
        if (pokemon.pokeball > 0) Utilities.selectPokeball(pokemon.pokeball, controller.PokeBall.getItems(), controller.PokeBall);
        else Utilities.selectPokeball("Poke Ball", controller.PokeBall.getItems(), controller.PokeBall);
    }

    default void setStatsStats(Pokemon pokemon, PokeStats pokeStats, AbstractPokemonController controller) {
        setBaseStats(pokeStats, controller);
        setIvStats(pokemon, controller);
        setEvStats(pokemon, controller);
        controller.calculateBaseIvEv();
        controller.calculateAndSetStats();
    }

    default void setBaseStats(PokeStats pokeStats, AbstractPokemonController controller) {
        controller.BaseHp.setText(Integer.toString(pokeStats.baseHp));
        controller.BaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        controller.BaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    default void setIvStats(Pokemon pokemon, AbstractPokemonController controller) {
        controller.IvHp.setText(Integer.toString(pokemon.ivHp));
        controller.IvAttack.setText(Integer.toString(pokemon.ivAttack));
        controller.IvDefense.setText(Integer.toString(pokemon.ivDefense));
        controller.IvSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        controller.IvSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        controller.IvSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    default void setEvStats(Pokemon pokemon, AbstractPokemonController controller) {
        controller.EvHp.setText(Integer.toString(pokemon.evHp));
        controller.EvAttack.setText(Integer.toString(pokemon.evAttack));
        controller.EvDefense.setText(Integer.toString(pokemon.evDefense));
        controller.EvSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        controller.EvSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        controller.EvSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    default void setMoveStats(Pokemon pokemon, AbstractPokemonController controller) {
        ObservableList<PokemonAttack> pokemonAttackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);

        // set Default No Attack
        ComboBox[] pokemonAttacks = {controller.Attack1Move, controller.Attack2Move, controller.Attack3Move, controller.Attack4Move};
        for (ComboBox attack : pokemonAttacks) { attack.getSelectionModel().selectFirst(); }

        // set Attack
        for (PokemonAttack pokemonAttack : pokemonAttackList) {
            switch(pokemonAttack.attackPosition) {
                case 1 -> controller.Attack1Move.getSelectionModel().select(Utilities.getMove(pokemonAttack.attackId, controller.Attack1Move.getItems()));
                case 2 -> controller.Attack2Move.getSelectionModel().select(Utilities.getMove(pokemonAttack.attackId, controller.Attack2Move.getItems()));
                case 3 -> controller.Attack3Move.getSelectionModel().select(Utilities.getMove(pokemonAttack.attackId, controller.Attack3Move.getItems()));
                case 4 -> controller.Attack4Move.getSelectionModel().select(Utilities.getMove(pokemonAttack.attackId, controller.Attack4Move.getItems()));
            }
        }

    }

}
