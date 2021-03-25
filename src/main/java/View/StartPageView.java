package View;

import Controller.StartPageController;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class StartPageView extends Application {
    private static StartPageController c;
    private static String backgroundImagePath;
    private static Label arrows;
    private static API.Database db;
    private static Data.dataSingleton data;
    private static ArrayList<String> buttonColors;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        data = Data.dataSingleton.getInstance();
        data.setStage(primaryStage);
        createStartPage(primaryStage);
    }

    public void createStartPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("startpage");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            Data.dataSingleton data = dataSingleton.getInstance();
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));

            // load Games and the image from the last game opened into fxml and css
            createButtons();

            stage.setTitle("PokeHelper");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void createButtons() {
        // connection to db
        db = new API.Database();
        c = StartPageController.getInstance();

        // Set Background-Image
        backgroundImagePath = db.getStartPageImage();
        c.StartImagePane.setStyle("-fx-background-image: url(" + backgroundImagePath + ");");

        // create >> after btn
        arrows = new Label("»");
        arrows.getStyleClass().add("startArrows");

        // make Buttons depending on the games in db
        LinkedHashMap<String, String> games = db.getGameNameAndSprite();
        // The Color what a button can have
        buttonColors = data.generateButtonColors();

        if (games != null) games.forEach(this::constructButton);
    }

    public void constructButton(String name, String locationOfImage) {
        // create HBox
        HBox hContainer = new HBox();
        hContainer.getStyleClass().add("startContainer");
        hContainer.setAlignment(Pos.CENTER);
        hContainer.setSpacing(10);

        // create button
        Label btn = new Label(name);
        btn.getStyleClass().add("startButton");

        // set Background-Color
        int indexOfColor = new Random().nextInt(buttonColors.size());
        hContainer.setStyle(buttonColors.get(indexOfColor));
        // remove color or generate new List if the is no color to pick anymore
        if (buttonColors.size() > 0) buttonColors.remove(indexOfColor);
        else buttonColors = data.generateButtonColors();

        // set background-image when mouse is over Container
        hContainer.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
            c.StartImagePane.setStyle("-fx-background-image: url(" + locationOfImage + ");");
            hContainer.getChildren().add(arrows);
        });
        // set default-image when mouse leaves the button
        hContainer.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
            c.StartImagePane.setStyle("-fx-background-image: url(" + backgroundImagePath+ ");");
            hContainer.getChildren().remove(arrows);
        });
        // goes to the menu
        hContainer.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set Values -> current game and backgroundImage
            Data.dataSingleton.getInstance().setGameName(name);
            db.setStartPageImage(locationOfImage);
            backgroundImagePath = locationOfImage;

            MenuView mv = new MenuView();
            mv.createMenuPage(data.getStage());
        });

        hContainer.getChildren().add(btn);
        c.GameButtonBox.getChildren().add(hContainer);
    }

}
