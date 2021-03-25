package View;

import Utilities.Utilities;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorView {

    public void createCalculatorPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("Calc");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("CalculatorPage");
            stage.setScene(scene);
            stage.show();
        }
    }
}
