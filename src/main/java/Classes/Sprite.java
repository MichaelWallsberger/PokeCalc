package Classes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Sprite implements Intefaces.Label {
    public int spriteId;
    public String spriteName;
    public String locationOfSprite;

    public Sprite(int spriteId, String spriteName, String locationOfSprite) {
        this.spriteId = spriteId;
        this.spriteName = spriteName;
        this.locationOfSprite = locationOfSprite;
    }

    @Override
    public String toString() {
        return spriteName;
    }

    @Override
    public HBox createLabel() {
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefWidth(9999999);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        // Label
        Label smallImages = new Label();
        Label nameOfPokemon = new Label(spriteName);
        // identifier
        Label identifier = new Label(Integer.toString(spriteId));
        identifier.setMaxWidth(0);
        identifier.setVisible(false);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        setSmallSpriteNextToLabel(smallImages);
        nameOfPokemon.getStyleClass().add("createPokemonLabel");
        // add to HBox
        hbox.getChildren().addAll(smallImages, nameOfPokemon, identifier);

        return hbox;
    }

    public void setSmallSpriteNextToLabel(Label smallImages) {
        smallImages.getStyleClass().add("createSmallSpriteNextToLabel");
        smallImages.setStyle("-fx-background-image: url(" + this.getClass().getResource(locationOfSprite) + ");");
    }

}
