package Classes.Abstract;

import Classes.PokeStats;
import Classes.Sprite;
import Utilities.Utilities;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AbstractPokeStatsController {
    public TextField Species;
    public TextField DexNr;
    public TextField Height;
    public TextField Weight;
    public ComboBox<String> ExpGrowthRate;
    public ComboBox<String> PokemonType1;
    public ComboBox<String> PokemonType2;
    public TextField BaseHp;
    public TextField BaseAttack;
    public TextField BaseDefense;
    public TextField BaseSpecialAttack;
    public TextField BaseSpecialDefense;
    public TextField BaseSpeed;
    public VBox ImageContainer;
    public Label BackgroundSprite;
    // own Variables
    public HBox previousSpriteBox;
    public Label nameOfPokemon;
    public int spriteId;
    public ObservableList<PokeStats> pokeStatsList;
    public ObservableList<Sprite> pokeSpriteList;

    // --------------- Species Already Exists ---------------
    public boolean doesSpeciesAlreadyExist() {
        String species = Species.getText();
        if (species.length() > 0) {
            species = species.substring(0, 1).toUpperCase() + species.substring(1);
            boolean result;

            if (nameOfPokemon.getText().equals(species)) result = false;
            else result = Utilities.findPokeStatsInList(species, pokeStatsList);

            if (result) Species.setStyle("-fx-background-color: rgba(255, 168, 181, 1);");
            else Species.setStyle("-fx-background-color: rgba(255, 255, 255, 1);");

            return result;
        }
        return true;
    }
}
