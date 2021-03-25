package Controller;

import Classes.Route;
import Classes.SearchFilter;
import Classes.Sprite;
import Classes.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTrainerController {
    // static method to create instance of Singleton class
    private static AddTrainerController controller = null;
    public static AddTrainerController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    // Main
    public TextField TrainerName;
    public TextField FightNumber;
    public ComboBox<String> FoughtAlready;
    public ComboBox<Route> Route;
    // Sprite
    public Label TrainerSpriteBackground;
    public TextField SpriteSearchField;
    public AnchorPane SpriteAnchor;
    public VBox TrainerSpriteContainer;
    public VBox FilteredTrainerSpriteContainer;
    // Options
    public Button CreateTrainerButton;
    // variables
    Stage createTrainerStage;
    int trainerSpriteId;
    private HBox previousTrainerSpriteBox;
    ObservableList<Sprite> trainerSpritesList;


    public void opensAddGameStage(Parent fxmlFile) {
        // create Stage and set Scene
        Scene scene = new Scene(fxmlFile);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));

        createTrainerStage = new Stage();
        createTrainerStage.setTitle("Add New Trainer");
        createTrainerStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed
        createTrainerStage.setScene(scene);
        createTrainerStage.show();

        loadContent();
    }

    // ---------------- Search ----------------
    public void searchForSprite(KeyEvent e) {
        SearchFilter<Sprite> filter = new SearchFilter<>();
        ObservableList<Sprite> list = filter.searchFor(e, FilteredTrainerSpriteContainer, SpriteSearchField, trainerSpritesList);

        if (list != null) {
            setTrainerSpriteContainerVisibility(true);
            SpriteAnchor.getChildren().remove(TrainerSpriteContainer);
            for (Sprite sprite : list) {
                HBox hbox = sprite.createLabel();
                setTrainerSpriteEventHandler(hbox, sprite);
                FilteredTrainerSpriteContainer.getChildren().add(hbox);
            }
        } else {
            try {
                SpriteAnchor.getChildren().add(TrainerSpriteContainer);
            } catch (Exception ignored) {}
            setTrainerSpriteContainerVisibility(false);
        }
    }

    private void setTrainerSpriteContainerVisibility(boolean b) {
        // entire List
        TrainerSpriteContainer.setVisible(!b);
        // search List
        FilteredTrainerSpriteContainer.setVisible(b);
    }


    // --------------- load Content ---------------
    private void loadContent() {
        setFoughtAlready();
        setRoutes();
        loadTrainerSprites();
    }

    private void setFoughtAlready() {
        ObservableList<String> foughtAlready = FXCollections.observableArrayList("True", "False");
        FoughtAlready.setItems(foughtAlready);
        FoughtAlready.getSelectionModel().selectLast();
    }

    private void setRoutes() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        API.Database dbAPI = new API.Database();

        ObservableList<Route> routeList = dbAPI.getALLMetLocationsFromGame(data.getGameName());
        Route.setItems(routeList);
        Route.getSelectionModel().selectFirst();
    }

    // Trainer-Sprites
    private void loadTrainerSprites() {
        API.Database dbAPI = new API.Database();

        trainerSpritesList = dbAPI.getAllTrainerSprites();
        for (Sprite trainerSprite : trainerSpritesList) {
            createTrainerSpriteLabel(trainerSprite);
        }

        setCssOfActiveTrainerSpriteBox((HBox) controller.TrainerSpriteContainer.getChildren().get(0));
        setTrainerSprite(trainerSpritesList.get(0).locationOfSprite);
    }

    private void createTrainerSpriteLabel(Sprite trainerSprite) {
        HBox hbox = trainerSprite.createLabel();

        // add EventListener
        setTrainerSpriteEventHandler(hbox, trainerSprite);

        // add To Controller
        TrainerSpriteContainer.getChildren().add(hbox);
    }

    private void setTrainerSpriteEventHandler(HBox hbox, Sprite trainerSprite) {
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveTrainerSpriteBox(hbox);
            // set Values
            setTrainerSprite(trainerSprite.locationOfSprite);
            // set pokemonStatsId
            trainerSpriteId = trainerSprite.spriteId;
        });
    }

    private void setCssOfActiveTrainerSpriteBox(HBox hbox) {
        if (previousTrainerSpriteBox != null) {
            previousTrainerSpriteBox.getStyleClass().clear();
            previousTrainerSpriteBox.getStyleClass().add("createPokemonHBox");
        }
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousTrainerSpriteBox = hbox;
    }

    public void setTrainerSprite(String spritePath) {
        TrainerSpriteBackground.setStyle("-fx-background-image: url(" + this.getClass().getResource(spritePath) + ");");
    }

    // --------------- create Trainer ---------------
    public void createTrainer() {
        String nameOfTrainer = TrainerName.getText();

        if (nameOfTrainer.length() > 0) {
            Data.dataSingleton data = Data.dataSingleton.getInstance();
            API.Database dbAPI = new API.Database();

            // Trainer
            int routeId = Route.getValue().routeId;
            int fightNumber = Integer.parseInt(FightNumber.getText());
            boolean foughtAlready = FoughtAlready.getValue().equals("True");

            int trainerId = dbAPI.addTrainer(data.getGameName(), routeId, nameOfTrainer, fightNumber, foughtAlready, trainerSpriteId);

            if (trainerId > 0) {
                addTrainerToList(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);
                createTrainerStage.close();
            }
        }
    }

    private void addTrainerToList(int trainerId, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready) {
        Controller.CalculatorController cc = Controller.CalculatorController.getInstance();
        Trainer tempTrainer = new Trainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);

        cc.createTrainerImage(tempTrainer, cc.trainerList.size());
        cc.trainerList.add(tempTrainer);
    }
}
