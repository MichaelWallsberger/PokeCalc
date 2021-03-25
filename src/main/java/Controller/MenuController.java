package Controller;

import View.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    // static method to create instance of Singleton class
    private static MenuController controller = null;
    public static MenuController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    public AnchorPane MenuAnchorPane;

    @FXML
    public void backToStartPage() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        new StartPageView().createStartPage(data.getStage());
    }

    @FXML
    public void goToCalculator() {
        Data.dataSingleton data = Data.dataSingleton.getInstance(); // get object where the stage is stored
        CalculatorView cv = new CalculatorView();
        cv.createCalculatorPage(data.getStage()); // set Calculator scene into the stage
    }

    public void goToPokeStats() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        PokeStatsView pv = new PokeStatsView();
        pv.createPokeStatsPage(data.getStage());
    }

    public void goToPokemon() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        PokemonView pv = new PokemonView();
        pv.createPokemonPage(data.getStage());
    }

    public void goToTrainer() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        TrainerView tv = new TrainerView();
        tv.createPokemonPage(data.getStage());
    }

    public void goToUserPokemons() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        UserPokemonView upv = new UserPokemonView();
        upv.createPokemonPage(data.getStage());
    }

    public void changeBackgroundToCalculator() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/calculator.png');");
    }

    public void changeBackgroundToPokeStats() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/pokemon-list.png');");
    }

    public void changeBackgroundToPokemon() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/Pokemon.png');");
    }

    public void changeBackgroundToTrainer() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/Trainers.png');");
    }

    public void changeBackgroundToUserPokemons() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/User.png');");
    }

    public void changeBackgroundToAbilities() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/abilities.png');");
    }

    public void changeBackgroundToTypes() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/PokemonTypes.png');");
    }

    public void changeBackgroundToAttack() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/moves.png');");
    }

    public void changeBackgroundToMoveset() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/moveset.png');");
    }

    public void changeBackgroundToItem() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/items.png');");
    }

    public void changeBackgroundToBadge() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/badge.png');");
    }

    public void changeBackgroundToRoute() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Route/Littleroot_Town_E.png');");
    }

    public void changeBackgroundToSprite() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/Sprite.png');");
    }

    public void changeBackgroundToTypechart() {
        MenuAnchorPane.setStyle("-fx-background-image: url('/Img/Menu/typechart.png');");
    }


}
