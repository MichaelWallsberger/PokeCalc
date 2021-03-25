package Controller;

import Utilities.Utilities;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class StartPageController {
    // static method to create instance of Singleton class
    private static StartPageController controller = null;
    public static StartPageController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    @FXML
    public VBox GameButtonBox;
    @FXML
    public AnchorPane StartImagePane;

    @FXML
    public void addGame() {
        Parent fxmlFile = Utilities.loadFxmlFile("addGame"); // load fxml file
        if (fxmlFile != null) AddGameController.getInstance().opensAddGameStage(fxmlFile);
    }

    @FXML
    public void removeGame() {
        Parent fxmlFile = Utilities.loadFxmlFile("deleteGame"); // load fxml file
        if (fxmlFile != null) DeleteGameController.getInstance().opensDeleteGameStage(fxmlFile);
    }


//    private void opensAddGameStage() {
//        API.Database api = new API.Database();
//
//        Stage newGameStage = new Stage();
//        newGameStage.setTitle("Add Game");
//        newGameStage.setWidth(300);
//        newGameStage.setHeight(250);
//        newGameStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed
//
//        // contains all the elements
//        VBox container = new VBox(10);
//        container.setAlignment(Pos.CENTER);
//
//        // Title Text
//        Label titleText = new Label("Create new Game");
//        titleText.setFont(new Font("Verdana", 25));
//
//        // name of setting
//        HBox nameBox = new HBox(10);
//        nameBox.setAlignment(Pos.CENTER);
//        Label nameText = new Label("Name:");
//        TextField nameField = new TextField();
//        nameField.setStyle("-fx-width: 80px;\n");
//        nameField.setAlignment(Pos.CENTER);
//        // add to Box
//        nameBox.getChildren().addAll(nameText, nameField);
//
//        // Image
//        HBox imageBox = new HBox(10);
//        imageBox.setAlignment(Pos.CENTER);
//        Label imageText = new Label("Image:");
//        ComboBox<String> imagePath = new ComboBox<>();
//        imagePath.setStyle("-fx-max-width: 80px;\n");
//        ObservableMap<String, Integer> backgroundImages = api.getAllSprites();
//        if (backgroundImages != null) imagePath.getItems().setAll(backgroundImages.keySet());
//        imagePath.setValue("None");
//        // add to Box
//        imageBox.getChildren().addAll(imageText, imagePath);
//
//        // chose Template
//        HBox templateBox = new HBox(10);
//        templateBox.setAlignment(Pos.CENTER);
//        Label templateText = new Label("Chose Template:");
//        ComboBox<String> templateCB = new ComboBox<String>();
//        templateCB.setMaxWidth(100);
//        // fills ComboBox with SettingsName that are in the Database
//        ObservableList<String> savedTypeSettings = api.getAllGames();
//        templateCB.setItems(savedTypeSettings);
//        templateCB.setValue("Default");
//        // add To Box
//        templateBox.getChildren().addAll(templateText, templateCB);
//        templateBox.setDisable(true);
//
//        // create Setting
//        Button createButton = new Button("Create");
//        createButton.setOnAction(e -> {
//            boolean result = false;
//            if (nameField.getText().length() >= 1)
//                result = api.addGame(nameField.getText(), backgroundImages.get(imagePath.getValue()), templateCB.getValue());
//            if (result) {
//                StartPage.View v = new StartPage.View();
//                v.constructButton(nameField.getText(), api.getImagePathFromSpriteID(backgroundImages.get(imagePath.getValue())));
//                newGameStage.close();
//            }
//        });
//
//        // add all elements
//        container.getChildren().addAll(titleText, nameBox, imageBox, templateBox, createButton);
//
//        // create Scene and set Scene
//        Scene scene = new Scene(container);
//        newGameStage.setScene(scene);
//        newGameStage.show();
//    }
//
//    private void opensRemoveGameStage() {
//        Stage removeGameStage = new Stage();
//        removeGameStage.setTitle("Add Setting");
//        removeGameStage.setWidth(300);
//        removeGameStage.setHeight(200);
//        removeGameStage.initModality(Modality.APPLICATION_MODAL); // block the other windows for doing something until this window is closed
//
//        // contains all the elements
//        VBox container = new VBox(10);
//        container.setAlignment(Pos.CENTER);
//
//        // Title Text
//        Label titleText = new Label("Delete Game");
//        titleText.setFont(new Font("Verdana", 25));
//
//        // chose Setting
//        HBox templateBox = new HBox(10);
//        templateBox.setAlignment(Pos.CENTER);
//        Label templateText = new Label("Chose Setting:");
//        ComboBox<String> templateCB = new ComboBox<String>();
//        templateCB.setMaxWidth(100);
//        // fills ComboBox with SettingsName that are in the Database
//        API.Database api = new API.Database();
//        ObservableList<String> savedTypeSettings = api.getAllGames();
//        savedTypeSettings.remove("None");
//        templateCB.setValue(savedTypeSettings.get(0));
//        templateCB.setItems(savedTypeSettings);
//        // add To Box
//        templateBox.getChildren().addAll(templateText, templateCB);
//
//        // delete Setting
//        Button deleteButton = new Button("Delete");
//        deleteButton.setOnAction(e -> {
//            boolean result = api.deleteGame(templateCB.getValue());
//            if (result) {
//                // goes over GameButtonBox and finds the value selected in the comboBox to delete it
//                for (Node btn : GameButtonBox.getChildren()) {
//                    Label label = (Label) ((HBox) btn).getChildren().get(0);
//                    String nameOfGame = label.getText();
//
//                    if (nameOfGame.equals(templateCB.getValue())) {
//                        GameButtonBox.getChildren().remove(btn);
//                        break;
//                    }
//                }
//
//                savedTypeSettings.remove(templateCB.getValue());
//            }
//        });
//
//        // add all elements
//        container.getChildren().addAll(titleText, templateBox, deleteButton);
//
//        // create Scene and set Scene
//        Scene scene = new Scene(container);
//        removeGameStage.setScene(scene);
//        removeGameStage.show();
//    }


}
