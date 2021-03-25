package View;

import API.Database;
import Intefaces.InterfacePokemonView;
import Classes.*;
import Controller.PokemonController;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PokemonView implements InterfacePokemonView {
    private static HBox previousPokemonBox;
    private static PokemonController controller;
    private static API.Database dbAPI;
    private static Data.dataSingleton data;

    public void createPokemonPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("Pokemon");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            previousPokemonBox = null;
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Edit Pokemon");

            loadDynamicContent();

            stage.setScene(scene);
            stage.show();
        }
    }

    // load all the data
    private void loadDynamicContent() {
        try {
            controller = PokemonController.getInstance();
            dbAPI = new Database();
            data = dataSingleton.getInstance();

            this.setBasicValues(controller); // Iv's / Ev's ...
            this.loadGlobalContent(data.getGameName(), controller); // Nature / Item / Ability ...
            selectFirst();

            createAllPokeStats();
            createAllPokemons();
        } catch (Exception e) {
            System.out.println("There are no Pokemons in your Game Error: " + e);
//            e.printStackTrace();
        }
    }

    private void createAllPokeStats() {
        // get List with all the PokeStats
        controller.pokeStatsList = dbAPI.getPokeStatsFromSpecificGame(data.getGameName());
        // Create The Labels on the Side for loading in the data
        for (PokeStats pokeStats : controller.pokeStatsList) {
            createPokemonLabel(pokeStats);
        }

        setTheFirstThing(controller.pokeStatsList.get(0));
    }

    private void createAllPokemons() {
        // get List with all the Pokemons
        controller.pokemonList = dbAPI.getPokemonNicknameWithPokeStatsIdFromSpecificGame(data.getGameName());
        // Create The Labels on the Side for loading in the data
        for (PokemonList pokeList : controller.pokemonList) {
            createPokemonLabel(pokeList);
        }
    }

    // Select The first thing
    private void selectFirst() {
        controller.Nature.getSelectionModel().selectFirst();
        controller.HeldItem.getSelectionModel().selectFirst();
        controller.Ability.getSelectionModel().selectFirst();
        controller.MetLocation.getSelectionModel().selectFirst();
        Utilities.selectPokeball("Poke Ball", controller.PokeBall.getItems(), controller.PokeBall);
        controller.Attack1Move.getSelectionModel().selectFirst();
        controller.Attack2Move.getSelectionModel().selectFirst();
        controller.Attack3Move.getSelectionModel().selectFirst();
        controller.Attack4Move.getSelectionModel().selectFirst();
    }

    private void setTheFirstThing(PokeStats pokeStats) {
        try {
            this.setPokemonStats(pokeStats, controller);
            controller.PokeStatsContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            previousPokemonBox = (HBox) controller.PokeStatsContainer.getChildren().get(0);
            // lock species
            controller.SpeciesName.setDisable(true);
            // options
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void selectFirstPokemon() {
        // set the first thing
        try {
            controller.pokemonId = controller.pokemonList.get(0).pokemonId;
            controller.PokemonContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            controller.activePokemonHBox = (HBox) controller.PokemonContainer.getChildren().get(0);
            previousPokemonBox = (HBox) controller.PokemonContainer.getChildren().get(0);

            this.setPokemonStats(controller.pokemonList.get(0), controller);
        } catch (Exception e) {
            System.out.println("There aren't any Pokemons to select. Error: " + e);
//            e.printStackTrace();
        }
    }

    // PokeStats
    private void createPokemonLabel(PokeStats pokeStats) {
        HBox hbox = pokeStats.createLabel();

        // add EventListener
        setPokemonLabelEventHandler(hbox, pokeStats);

        // add To Controller
        controller.PokeStatsContainer.getChildren().add(hbox);
    }

    // Pokemon
    public void createPokemonLabel(PokemonList pokemon) {
        HBox hbox = pokemon.createPokemonLabel();

        // add EventListener
        setPokemonLabelEventHandler(hbox, pokemon);

        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    public void setPokemonLabelEventHandler(HBox hbox, PokemonList pokemon) {
        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokemon, controller);
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.activePokemonHBox = hbox;
            controller.activePokemonSprite = pokemon.getSpriteLabel(hbox);
            controller.activePokemonDexNr = pokemon.getDexNrLabel(hbox);
            controller.activePokemonNickname = pokemon.getNameLabel(hbox);
            // activate species
            controller.SpeciesName.setDisable(false);
            // options
            controller.AddPokemonButton.setDisable(true);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        });
    }

    public void setPokemonLabelEventHandler(HBox hbox, PokeStats pokeStats) {
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            this.setPokemonStats(pokeStats, controller);
            // lock species
            controller.SpeciesName.setDisable(true);
            // options
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        });
    }

    // Css of active Box
    private void setCssOfActiveBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        if (previousPokemonBox != null) {
            previousPokemonBox.getStyleClass().clear();
            previousPokemonBox.getStyleClass().add("createPokemonHBox");
        }
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousPokemonBox = hbox;
    }

}
