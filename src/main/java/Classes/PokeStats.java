package Classes;

import Classes.Abstract.BasicPokeStats;
import Utilities.Utilities;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PokeStats extends BasicPokeStats implements Intefaces.Label {
    public int pokemonStatsId;
    public String nameOfPokemon;
    public int dexNr;
    public String expGrowthRate;
    public double weight;
    public double height;
    public int baseHp;
    public int baseAttack;
    public int baseDefense;
    public int baseSpecialAttack;
    public int baseSpecialDefense;
    public int baseSpeed;

    public PokeStats(int pokemonStatsId, String nameOfPokemon, int dexNr, String expGrowthRate, double weight, double height, int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack, int baseSpecialDefense, int baseSpeed) {
        this.pokemonStatsId = pokemonStatsId;
        this.nameOfPokemon = nameOfPokemon;
        this.dexNr = dexNr;
        this.expGrowthRate = expGrowthRate;
        this.weight = weight;
        this.height = height;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
        this.baseSpeed = baseSpeed;
    }

    @Override
    public HBox createLabel() {
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
        Label nameLabel = new Label(nameOfPokemon);
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

    @Override
    public String toString() {
        return nameOfPokemon;
    }
}
