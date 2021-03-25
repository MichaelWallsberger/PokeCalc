package View;

import Classes.*;
import Intefaces.InterfacePokemonView;
import Controller.UserPokemonController;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserPokemonView implements InterfacePokemonView {
    private static HBox previousPokemonBox;
    private static UserPokemonController controller;
    private static API.Database dbAPI;
    private static Data.dataSingleton data;

    public void createPokemonPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("UserPokemon");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            previousPokemonBox = null;
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Add Pokemon To the User");

            controller = UserPokemonController.getInstance();
            dbAPI = new API.Database();
            data = Data.dataSingleton.getInstance();
            loadUserPokemons();
            loadDynamicContent();
            selectFirstPokemon();

            stage.setScene(scene);
            stage.show();

        }
    }

    public void selectFirstPokemon() {
        // set the first thing
        try {
            controller.pokemonStatsId = controller.pokeStatsList.get(0).pokemonStatsId;
            controller.pokemonId = controller.pokeStatsList.get(0).pokemonId;
            controller.PokemonContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            controller.activePokemon = (HBox) controller.PokemonContainer.getChildren().get(0);
            previousPokemonBox = (HBox) controller.PokemonContainer.getChildren().get(0);

            EventHandler<ActionEvent> speciesHandler = controller.SpeciesName.getOnAction();
            controller.SpeciesName.setOnAction(null);
            controller.SpeciesName.getSelectionModel().selectFirst();
            controller.SpeciesName.setOnAction(speciesHandler);

            setPokemonStats(controller.pokeStatsList.get(0), controller);
            setPokemonOptions(controller.pokeStatsList.get(0));
        } catch (Exception e) {
            System.out.println("You don't have any Pokemons. Error: " + e);
//            e.printStackTrace();
        }
    }

    // Dynamic Content
    private void loadDynamicContent() {
        try {
            // get List with all the PokeStats
            controller.pokeStatsList = FXCollections.observableArrayList(new PokemonList(0,  "No Pokemon", 0, 0));
            controller.pokeStatsList.addAll(dbAPI.getPokemonNicknameWithPokeStatsIdFromSpecificGame(data.getGameName()));

            removePokemonThatTheUserAlreadyHas();

            // Create The Labels on the Side for loading in the data
            for (PokemonList pokeList : controller.pokeStatsList) {
                createPokemonLabel(pokeList);
            }

            loadGlobalContent(data.getGameName(), controller); // Nature / Item / Ability ...
        } catch (Exception e) {
            System.out.println("There are no Pokemons in your Game Error: " + e);
//            e.printStackTrace();
        }
    }

    private void removePokemonThatTheUserAlreadyHas() {
        ArrayList<PokemonList> pokemonToRemove = new ArrayList<PokemonList>();

        for (PokemonList pokeList : controller.pokeStatsList) {
            for (PokemonList userPokeList : controller.userPokemonList) {
                if (pokeList.pokemonId == userPokeList.pokemonId) {
                    pokemonToRemove.add(pokeList);
                }
            }
        }

        for (PokemonList pk : pokemonToRemove) {
            controller.pokeStatsList.remove(pk);
        }
    }

    // Create Pokemon Label
    public void createPokemonLabel(PokemonList pokemon) {
        HBox hbox = pokemon.createPokemonLabel();

        // add EventListener
        setPokemonEventHandler(hbox, pokemon);

        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    public void setPokemonEventHandler(HBox hbox, PokemonList pokemon) {
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokemon, controller);
            // set Pokemon Options
            setPokemonOptions(pokemon);
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.pokemonStatsId = pokemon.pokemonStatsId;
            // set activePokemon
            controller.activePokemon = hbox;
            controller.activePokemonSprite = pokemon.getSpriteLabel(hbox);
            controller.activePokemonDexNr = pokemon.getDexNrLabel(hbox);
            controller.activePokemonNickname = pokemon.getNameLabel(hbox);
        });
    }

    private void setPokemonOptions(PokemonList pokemon) {
        if (pokemon.pokemonId > 0) {
            controller.CreatePokemonButton.setDisable(true);
            controller.AddToUserButton.setDisable(false);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        } else {
            controller.CreatePokemonButton.setDisable(false);
            controller.AddToUserButton.setDisable(true);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);

        }
        controller.RemoveFromUserButton.setDisable(true);
    }

    // load User Pokemons
    private void loadUserPokemons() {
        controller.userPokemonList = FXCollections.observableArrayList(dbAPI.getAllPokemonFromUser(data.getGameName()));

        for (PokemonList pokeList : controller.userPokemonList) {
            createPokemonLabelForUser(pokeList);
        }
    }

    public void createPokemonLabelForUser(PokemonList pokemon) {
        HBox hbox =  pokemon.createPokemonLabel();

        // add EventListener
        setUserPokemonEventHandler(hbox, pokemon);

        // add To Controller
        controller.UserPokemonContainer.getChildren().add(hbox);
    }

    public void setUserPokemonEventHandler(HBox hbox, PokemonList pokemon) {
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokemon, controller);
            // set Pokemon Options
            setTrainerPokemonOptions();
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.pokemonStatsId = pokemon.pokemonStatsId;
            // set activePokemon
            controller.activePokemon = hbox;
            controller.activePokemonSprite = pokemon.getSpriteLabel(hbox);
            controller.activePokemonDexNr = pokemon.getDexNrLabel(hbox);
            controller.activePokemonNickname = pokemon.getNameLabel(hbox);
        });
    }

    private void setTrainerPokemonOptions() {
        controller.CreatePokemonButton.setDisable(true);
        controller.AddToUserButton.setDisable(true);
        controller.EditPokemonButton.setDisable(false);
        controller.RemoveFromUserButton.setDisable(false);
        controller.DeletePokemonButton.setDisable(true);
    }

    // Active Pokemon
    private void setCssOfActiveBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        previousPokemonBox.getStyleClass().clear();
        previousPokemonBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousPokemonBox = hbox;
    }
}
