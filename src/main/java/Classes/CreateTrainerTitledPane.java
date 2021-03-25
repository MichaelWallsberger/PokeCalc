package Classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CreateTrainerTitledPane {
    public static TitledPane createTitledPane(Trainer trainer) {
        // holds all the items
        TitledPane tp = new TitledPane();
        tp.setExpanded(false);

        // title
        HBox titleBox = createTitle(trainer);
        // set Title
        tp.setGraphic(titleBox);

        return tp;
    }

    public static HBox createTitle(Trainer trainer) {
        API.Database dbAPI = new API.Database();
        int size = 25;
        // Sprite
        int spriteIdTrainer = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
        String spritePathTrainer = dbAPI.getImagePathFromSpriteID(spriteIdTrainer);
        if (spritePathTrainer == null) spritePathTrainer = "/Img/Trainers/Brendan_Small_E.png";
        // Pokemon
        ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);

        // Box
        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(titleBox, new Insets(0, 0, 0, 20));
        // Labels
        ImageView trainerImg = new ImageView(new Image(spritePathTrainer));
        trainerImg.setFitWidth(size); trainerImg.setFitHeight(size);
        Label nameOfTrainer = new Label(trainer.trainerName);
        nameOfTrainer.setTextFill(Color.color(0, 0, 0));
        titleBox.getChildren().addAll(trainerImg, nameOfTrainer);
        // create Pokemon Image
        for (Pokemon pokemon : pokemonList) {
            int pokeSpriteId = dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId);
            String pokeSpritePath = dbAPI.getImagePathFromSpriteID(pokeSpriteId);
            ImageView pokeImg = new ImageView(new Image(pokeSpritePath));
            pokeImg.setFitWidth(size); pokeImg.setFitHeight(size);
            titleBox.getChildren().add(pokeImg);
        }

        return titleBox;
    }
}
