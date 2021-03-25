package Intefaces;

import Classes.Abstract.AbstractPokeStatsController;
import Classes.AutoBoxComplete;
import Classes.PokeStats;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public interface InterfacePokeStatsView {

    default void setExpGrowthRate(AbstractPokeStatsController controller) {
        ObservableList<String> expGrowthRate = FXCollections.observableArrayList("Erratic", "Fast", "Medium Fast", "Medium Slow", "Slow", "Fluctuating");
        controller.ExpGrowthRate.setItems(expGrowthRate);
        new AutoBoxComplete<>(controller.ExpGrowthRate);
    }

    default void loadTypes(AbstractPokeStatsController controller) {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        API.Database dbAPI = new API.Database();
        // load types from db
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("");
        types.addAll(dbAPI.getAllTypeFromSpecificGame(data.getGameName()));
        // set Type
        controller.PokemonType1.setItems(FXCollections.observableArrayList(types));
        controller.PokemonType2.setItems(FXCollections.observableArrayList(types));
        // select the first thing
        controller.PokemonType1.getSelectionModel().selectFirst();
        controller.PokemonType2.getSelectionModel().selectFirst();
        // search filter
        new AutoBoxComplete<>(controller.PokemonType1);
        new AutoBoxComplete<>(controller.PokemonType2);
    }

    // --------------- Images ---------------
    default void setCssOfActiveSpriteBox(HBox hbox, AbstractPokeStatsController controller) {
        // change previousPokemonBox background-color back to original
        if (controller.previousSpriteBox != null) {
            controller.previousSpriteBox.getStyleClass().clear();
            controller.previousSpriteBox.getStyleClass().add("createPokemonHBox");
        }
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        controller.previousSpriteBox = hbox;
    }

    default void setSprite(String spritePath, AbstractPokeStatsController controller) {
        controller.BackgroundSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(spritePath) + ");");
    }

    // --------------- set Stats ---------------
    default void setPokeStats(PokeStats pokeStats, AbstractPokeStatsController controller) {
        setMainStats(pokeStats, controller);
        setBaseStats(pokeStats, controller);
        int index = Utilities.findIndexOfSpriteStats(pokeStats, controller.pokeSpriteList);
        selectSpriteStats(index, controller);
        // set previous Sprite
        setCssOfActiveSpriteBox((HBox) controller.ImageContainer.getChildren().get(index), controller);
    }

    default void setMainStats(PokeStats pokeStats, AbstractPokeStatsController controller) {
        controller.Species.setText(pokeStats.nameOfPokemon);
        controller.DexNr.setText(Integer.toString(pokeStats.dexNr));
        controller.Height.setText(Double.toString(pokeStats.height));
        controller.Weight.setText(Double.toString(pokeStats.weight));
        controller.ExpGrowthRate.setValue(pokeStats.expGrowthRate);

        API.Database dbAPI = new API.Database();
        ArrayList<String> types = dbAPI.getTypesFromPokemonStatsId(pokeStats.pokemonStatsId);
        // Select Type when found
        for (int i = 0; i < types.size(); i++) {
            switch (i) {
                case 0 -> controller.PokemonType1.setValue(types.get(0));
                case 1 -> controller.PokemonType2.setValue(types.get(1));
            }
        }
        // no Type -> select no Type
        for (int i = types.size(); i < 2; i++) {
            switch (i) {
                case 0 -> controller.PokemonType1.getSelectionModel().selectFirst();
                case 1 -> controller.PokemonType2.getSelectionModel().selectFirst();
            }
        }
    }

    default void setBaseStats(PokeStats pokeStats, AbstractPokeStatsController controller) {
        controller.BaseHp.setText(Integer.toString(pokeStats.baseHp));
        controller.BaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        controller.BaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    default void selectSpriteStats(int index, AbstractPokeStatsController controller) {
        try {
            controller.ImageContainer.getChildren().get(index).getStyleClass().add("createPokemonHBoxActive");
            controller.spriteId = controller.pokeSpriteList.get(index).spriteId;
            setSprite(controller.pokeSpriteList.get(index).locationOfSprite, controller);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
