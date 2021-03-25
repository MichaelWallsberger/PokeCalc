package View;

import Utilities.Utilities;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuView {

    public void createMenuPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("menu");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            stage.setTitle("GameMenuPage");
            stage.setScene(new Scene(fxmlFile));
            stage.show();
        }
    }
}
