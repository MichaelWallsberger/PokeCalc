package Classes;

import Utilities.Utilities;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Trainer implements Intefaces.Label {
    public int trainerId;
    public int routeId;
    public String trainerName;
    public int fightNumber;
    public boolean foughtAlready;

    public Trainer(int trainerId, int routeId, String trainerName, int fightNumber, boolean foughtAlready) {
        this.trainerId = trainerId;
        this.routeId = routeId;
        this.trainerName = trainerName;
        this.fightNumber = fightNumber;
        this.foughtAlready = foughtAlready;
    }

    @Override
    public String toString() {
        return trainerName;
    }

    @Override
    public HBox createLabel() {
        API.Database dbAPI = new API.Database();

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefWidth(300);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (trainerId > 0) ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificTrainer(trainerId));
        // dexNr
        Label fightNumberLabel = new Label();
        if (trainerId > 0) fightNumberLabel = new Label(Integer.toString(fightNumber));
        // Name
        Label trainerNameLabel = new Label(trainerName);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        if (trainerId > 0) smallSprite.getStyleClass().add("createSmallSpriteNextToLabel");
        fightNumberLabel.getStyleClass().add("createPokemonLabel");
        trainerNameLabel.getStyleClass().add("createPokemonLabel");

        // add to HBox
        hbox.getChildren().addAll(smallSprite, fightNumberLabel, trainerNameLabel);

        return hbox;
    }

    public Label getSpriteLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(0);
    }

    public Label getFightNumberLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(1);
    }

    public Label getTrainerNameLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(2);
    }
}
