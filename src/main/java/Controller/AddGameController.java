package Controller;

import Classes.AutoBoxComplete;
import Classes.Sprite;
import View.StartPageView;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddGameController {
    private static AddGameController controller = null;
    public static AddGameController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    public AnchorPane newGameIPreviewImage;
    public TextField nameOfNewGame;
    public ComboBox<Sprite> imageOfNewGame;
    public ComboBox<String> templateOfNewGame;

    private Stage newGameStage;


    public void opensAddGameStage(Parent fxmlFile) {
        // create Stage and set Scene
        newGameStage = new Stage();
        newGameStage.setTitle("Add New Game");
        newGameStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed

        // load Content
        API.Database api = new API.Database();
        // Images
        ObservableList<Sprite> backgroundImages = api.getAllSprites();
        if (backgroundImages != null) imageOfNewGame.setItems(backgroundImages);
        imageOfNewGame.getSelectionModel().selectFirst();
        // Templates
        ObservableList<String> savedTypeSettings = api.getAllGames();
        templateOfNewGame.setItems(savedTypeSettings);
        templateOfNewGame.setValue("Default");

        new AutoBoxComplete<>(imageOfNewGame);
        new AutoBoxComplete<>(templateOfNewGame);

        newGameStage.setScene(new Scene(fxmlFile));
        newGameStage.show();
    }

    public void newGameImageSelected() {
        try {
            int imageId = imageOfNewGame.getValue().spriteId;
            if (imageId != -1) {
                API.Database dbApi = new API.Database();
                String imgPath = dbApi.getImagePathFromSpriteID(imageId);
                newGameIPreviewImage.setStyle("-fx-background-image: url(" + imgPath + ");");
            } else {
                newGameIPreviewImage.setStyle("-fx-background-image: null");
            }
        } catch (Exception ignored) {}
    }

    public void createNewGame() {
        API.Database dbApi = new API.Database();
        // create Setting
        boolean result = false;
        // input into database
        if (nameOfNewGame.getText().length() >= 1) result = dbApi.addGame(nameOfNewGame.getText(), imageOfNewGame.getValue().spriteId, templateOfNewGame.getValue());
        // update Scene when successfully added the new Game to database
        if (result) {
            // adds button to View
            StartPageView v = new StartPageView();
            v.constructButton(nameOfNewGame.getText(), dbApi.getImagePathFromSpriteID(imageOfNewGame.getValue().spriteId));

            // creates Trainer 'User' in Database for this specific game
            API.Database dbAPI = new API.Database();
            dbAPI.addTrainerUser(nameOfNewGame.getText());

            newGameStage.close();
        }
    }

}
