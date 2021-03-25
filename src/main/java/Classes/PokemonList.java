package Classes;

import Classes.Abstract.BasicPokemon;
import Utilities.Utilities;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PokemonList extends BasicPokemon {
    public int pokemonId;
    public String nickname;
    public int pokemonStatsId;
    public int dexNr;

    public PokemonList(int pokemonId, String nickname, int pokemonStatsId, int dexNr) {
        this.pokemonId = pokemonId;
        this.nickname = nickname;
        this.pokemonStatsId = pokemonStatsId;
        this.dexNr = dexNr;
    }

    @Override
    public String toString() {
        return nickname;
    }

    // Pokemon Label -> Sprite | DexNr | Name
    public HBox createPokemonLabel() {
        API.Database dbAPI = new API.Database();
        Utilities ut = new Utilities();

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefWidth(250);
        // Set small Pokemon Sprite
        Label smallSpriteLabel = new Label();
        if (pokemonStatsId > 0) ut.setSmallSpriteNextToLabel(smallSpriteLabel, dbAPI.getSpriteIdFromSpecificPokemon(pokemonStatsId));
        // dexNr
        Label dexNrLabel = new Label();
        if (pokemonStatsId > 0) dexNrLabel = new Label(String.format("%03d", dexNr));
        // Name
        Label nameLabel = new Label(nickname);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        dexNrLabel.getStyleClass().add("createPokemonLabel");
        nameLabel.getStyleClass().add("createPokemonLabel");
        // add to HBox
        hbox.getChildren().addAll(smallSpriteLabel, dexNrLabel, nameLabel);

        return hbox;
    }

    public Label getSpriteLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(0);
    }

    public Label getDexNrLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(1);
    }

    public Label getNameLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(2);
    }

    public String nameOfPokemon() {
        API.Database dbAPI = new API.Database();
        String name = "";
        if (pokemonStatsId > 0) name = dbAPI.getPokeStatsFromPokemonStatsId(pokemonStatsId).nameOfPokemon;
        return name;
    }

    public HBox createSmallPokemonLabel() {
        API.Database dbAPI = new API.Database();

        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefWidth(9999999);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (pokemonStatsId > 0) ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokemonStatsId));
        // Name
        Label nameLabel = new Label(nickname);
        // hidden pokemonId for identification
        Label identifier = new Label(Integer.toString(pokemonId));
        identifier.setMaxWidth(0);
        identifier.setVisible(false);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        nameLabel.getStyleClass().add("createPokemonLabel");
        // add to HBox
        hbox.getChildren().addAll(smallSprite, nameLabel, identifier);

        return hbox;
    }
    // for Trainer -> Sprite | Name

    public Label getSmallSpriteLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(0);
    }

    public Label getSmallNameLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(1);
    }

    public Label getSmallPokemonIdLabel(HBox hbox) {
        return (Label) hbox.getChildren().get(2);
    }
}
