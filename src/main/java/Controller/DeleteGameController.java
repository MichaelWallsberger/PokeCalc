package Controller;

import Classes.AutoBoxComplete;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteGameController {
    private static DeleteGameController controller = null;
    public static DeleteGameController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    public ComboBox<String> chooseGame;
    private ObservableList<String> savedTypeSettings;

    public void opensDeleteGameStage(Parent fxmlFile) {
        // create Stage and set Scene
        Stage newGameStage = new Stage();
        newGameStage.setTitle("Delete Game");
        newGameStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed

        // fills ComboBox with SettingsName that are in the Database
        API.Database api = new API.Database();
        savedTypeSettings = api.getAllGames();
        if (savedTypeSettings != null) {
            savedTypeSettings.remove("None");
            chooseGame.setValue(savedTypeSettings.get(0));
            chooseGame.setItems(savedTypeSettings);
        }

        new AutoBoxComplete<>(chooseGame);

        newGameStage.setScene(new Scene(fxmlFile));
        newGameStage.show();
    }

    @FXML
    private void deleteGame() {
        API.Database api = new API.Database();
        boolean result = api.deleteGame(chooseGame.getValue());
        if (result) {
            // goes over GameButtonBox and finds the value selected in the comboBox to delete it
            StartPageController c = StartPageController.getInstance();
            VBox GameButtonBox = c.GameButtonBox;

            for (Node btn : GameButtonBox.getChildren()) {
                Label label = (Label) ((HBox) btn).getChildren().get(0);
                String nameOfGame = label.getText();

                if (nameOfGame.equals(chooseGame.getValue())) {
                    GameButtonBox.getChildren().remove(btn);
                    break;
                }
            }

            savedTypeSettings.remove(chooseGame.getValue());
        }
    }
}
