package Classes.Abstract;

import Classes.Attack;
import Classes.PokeStats;
import Classes.PokeStatsList;
import Classes.Sprite;
import Utilities.Utilities;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractPokemonController {
    // Main
    public ComboBox<PokeStatsList> SpeciesName;
    public TextField Nickname;
    public TextField Level;
    public ComboBox<String> Gender;
    public ComboBox<Classes.Nature> Nature;
    public ImageView HeldItemImage;
    public ComboBox<Classes.Item> HeldItem;
    public ComboBox<Classes.Ability> Ability;
    public TextField Friendship;
    // Met
    public ComboBox<Classes.Route> MetLocation;
    public ImageView PokeballImage;
    public ComboBox<Sprite> PokeBall;
    public TextField IvHp;
    // Stats
    public Label BaseHp;
    public Label BaseAttack;
    public Label BaseDefense;
    public Label BaseSpecialAttack;
    public Label BaseSpecialDefense;
    public Label BaseSpeed;
    public Label BaseTotal;
    public TextField IvAttack;
    public TextField IvDefense;
    public TextField IvSpecialAttack;
    public TextField IvSpecialDefense;
    public TextField IvSpeed;
    public Label IvTotal;
    public TextField EvHp;
    public TextField EvAttack;
    public TextField EvDefense;
    public TextField EvSpecialAttack;
    public TextField EvSpecialDefense;
    public TextField EvSpeed;
    public Label EvTotal;
    public Label StatsHp;
    public Label StatsAttack;
    public Label StatsDefense;
    public Label StatsSpecialAttack;
    public Label StatsSpecialDefense;
    public Label StatsSpeed;
    // Attack
    public ComboBox<Classes.Attack> Attack1Move;
    public ComboBox<Classes.Attack> Attack2Move;
    public ComboBox<Classes.Attack> Attack3Move;
    public ComboBox<Classes.Attack> Attack4Move;
    public Label Attack1PP;
    public Label Attack2PP;
    public Label Attack3PP;
    public Label Attack4PP;
    // Attack PP Ups
    public ComboBox<String> Attack1PPUps;
    public ComboBox<String> Attack2PPUps;
    public ComboBox<String> Attack3PPUps;
    public ComboBox<String> Attack4PPUps;

    private static AbstractPokemonController controller = null;
    public static AbstractPokemonController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    // -------------------------- Main --------------------------
    public void changedSpecies() {
        API.Database dbAPI = new API.Database();

        PokeStats tempPokeStats =  dbAPI.getPokeStatsFromPokemonStatsId(SpeciesName.getValue().pokemonStatsId);
        Nickname.setText(tempPokeStats.nameOfPokemon);
        setBaseStats(tempPokeStats);
        calculateBaseTotal();
        calculateAndSetStats();
    }

    public void changedPokemonLevel() {
        calculateAndSetStats();
    }

    public void changedNature() {
        calculateAndSetStats();
    }

    public void changeHeldItemImage() {
        API.Database dbAPI = new API.Database();
        int spriteId = HeldItem.getValue().spriteId;
        String imagePath = dbAPI.getImagePathFromSpriteID(spriteId);
        if (spriteId > 0) HeldItemImage.setImage(new Image(imagePath));
        else HeldItemImage.setImage(null);
    }

    public void setBaseStats(PokeStats pokeStats) {
        BaseHp.setText(Integer.toString(pokeStats.baseHp));
        BaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        BaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        BaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        BaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        BaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    // -------------------------- Met --------------------------
    public void setPokeballImage() {
        API.Database dbAPI = new API.Database();
        String imagePath = dbAPI.getImagePathFromSpriteID(PokeBall.getValue().spriteId);
        PokeballImage.setImage(new Image(imagePath));
    }

    // -------------------------- Stats --------------------------
    // ---- IV ----
    public void changeIvHp() {
        calculateIvTotal();
        int hp = calculateHpStat();
        StatsHp.setText(Integer.toString(hp));
    }

    public void changeIvAttack() {
        calculateIvTotal();
        int attack = calculateAttackStat();
        StatsAttack.setText(Integer.toString(attack));
    }

    public void changeIvDefense() {
        calculateIvTotal();
        int defense = calculateDefenseStat();
        StatsDefense.setText(Integer.toString(defense));
    }

    public void changeIvSpecialAttack() {
        calculateIvTotal();
        int specialAttack = calculateSpecialAttackStat();
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
    }

    public void changeIvSpecialDefense() {
        calculateIvTotal();
        int specialDefense = calculateSpecialDefenseStat();
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
    }

    public void changeIvSpeed() {
        calculateIvTotal();
        int speed = calculateSpeedStat();
        StatsSpeed.setText(Integer.toString(speed));
    }

    // ---- EV ----
    public void changeEvHp() {
        calculateEvTotal();
        int hp = calculateHpStat();
        StatsHp.setText(Integer.toString(hp));
    }

    public void changeEvAttack() {
        calculateEvTotal();
        int attack = calculateAttackStat();
        StatsAttack.setText(Integer.toString(attack));
    }

    public void changeEvDefense() {
        calculateEvTotal();
        int defense = calculateDefenseStat();
        StatsDefense.setText(Integer.toString(defense));
    }

    public void changeEvSpecialAttack() {
        calculateEvTotal();
        int specialAttack = calculateSpecialAttackStat();
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
    }

    public void changeEvSpecialDefense() {
        calculateEvTotal();
        int specialDefense = calculateSpecialDefenseStat();
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
    }

    public void changeEvSpeed() {
        calculateEvTotal();
        int speed = calculateSpeedStat();
        StatsSpeed.setText(Integer.toString(speed));
    }

    // -------------------------- Attack --------------------------
    public void setPPForMove1() {
        Attack attack = Attack1Move.getValue();
        Attack1PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove2() {
        Attack attack = Attack2Move.getValue();
        Attack2PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove3() {
        Attack attack = Attack3Move.getValue();
        Attack3PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove4() {
        Attack attack = Attack3Move.getValue();
        Attack4PP.setText(Integer.toString(attack.pp));
    }

    // -------------------------- Calculation --------------------------
    public void calculateBaseIvEv() {
        calculateBaseTotal();
        calculateIvTotal();
        calculateEvTotal();
    } // Base | Iv | Ev

    public void calculateAndSetStats() {
        int hp = calculateHpStat();
        int attack = calculateAttackStat();
        int defense = calculateDefenseStat();
        int specialAttack = calculateSpecialAttackStat();
        int specialDefense = calculateSpecialDefenseStat();
        int speed = calculateSpeedStat();

        StatsHp.setText(Integer.toString(hp));
        StatsAttack.setText(Integer.toString(attack));
        StatsDefense.setText(Integer.toString(defense));
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
        StatsSpeed.setText(Integer.toString(speed));
    }

    public void calculateBaseTotal() {
        int baseHP = Integer.parseInt(BaseHp.getText());
        int baseAttack = Integer.parseInt(BaseAttack.getText());
        int baseDefense = Integer.parseInt(BaseDefense.getText());
        int baseSpAttack = Integer.parseInt(BaseSpecialAttack.getText());
        int baseSpDefense = Integer.parseInt(BaseSpecialDefense.getText());
        int baseSpeed = Integer.parseInt(BaseSpeed.getText());
        int baseTotal = baseHP + baseAttack + baseDefense + baseSpAttack + baseSpDefense + baseSpeed;
        BaseTotal.setText(Integer.toString(baseTotal));
    }

    public void calculateIvTotal() {
        try {
            int ivHP = Integer.parseInt(IvHp.getText());
            int ivAttack = Integer.parseInt(IvAttack.getText());
            int ivDefense = Integer.parseInt(IvDefense.getText());
            int ivSpAttack = Integer.parseInt(IvSpecialAttack.getText());
            int ivSpDefense = Integer.parseInt(IvSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(IvSpeed.getText());
            int ivTotal = ivHP + ivAttack + ivDefense + ivSpAttack + ivSpDefense + ivSpeed;
            IvTotal.setText(Integer.toString((ivTotal)));
        } catch (Exception e) {
            System.out.println(e);
            IvTotal.setText("0");
        }
    }

    public void calculateEvTotal() {
        try {
            int evHP = Integer.parseInt(EvHp.getText());
            int evAttack = Integer.parseInt(EvAttack.getText());
            int evDefense = Integer.parseInt(EvDefense.getText());
            int evSpAttack = Integer.parseInt(EvSpecialAttack.getText());
            int evSpDefense = Integer.parseInt(EvSpecialDefense.getText());
            int evSpeed = Integer.parseInt(EvSpeed.getText());
            int evTotal = evHP + evAttack + evDefense + evSpAttack + evSpDefense + evSpeed;
            EvTotal.setText(Integer.toString((evTotal)));
        } catch (Exception e) {
            System.out.println(e);
            EvTotal.setText("0");
        }
    }

    // Stats
    public int calculateHpStat() {
        return Utilities.calculateHpStat(
                Integer.parseInt(BaseHp.getText()),
                Integer.parseInt(EvHp.getText()),
                Integer.parseInt(IvHp.getText()),
                Integer.parseInt(Level.getText())
        );
    }

    public int calculateAttackStat() {
        return Utilities.calculateStatWithBadge(
                Nature.getValue(),
                Integer.parseInt(BaseAttack.getText()),
                Integer.parseInt(EvAttack.getText()),
                Integer.parseInt(IvAttack.getText()),
                Integer.parseInt(Level.getText()),
                "Attack",
                null,
                false
        );
    }

    public int calculateDefenseStat() {
        return Utilities.calculateStatWithBadge(
                Nature.getValue(),
                Integer.parseInt(BaseDefense.getText()),
                Integer.parseInt(EvDefense.getText()),
                Integer.parseInt(IvDefense.getText()),
                Integer.parseInt(Level.getText()),
                "Defense",
                null,
                false
        );
    }

    public int calculateSpecialAttackStat() {
        return Utilities.calculateStatWithBadge(
                Nature.getValue(),
                Integer.parseInt(BaseSpecialAttack.getText()),
                Integer.parseInt(EvSpecialAttack.getText()),
                Integer.parseInt(IvSpecialAttack.getText()),
                Integer.parseInt(Level.getText()),
                "Special Attack",
                null,
                false
        );
    }

    public int calculateSpecialDefenseStat() {
        return Utilities.calculateStatWithBadge(
            Nature.getValue(),
            Integer.parseInt(BaseSpecialDefense.getText()),
            Integer.parseInt(EvSpecialDefense.getText()),
            Integer.parseInt(IvSpecialDefense.getText()),
            Integer.parseInt(Level.getText()),
            "Special Defense",
            null,
            false
        );
    }

    public int calculateSpeedStat() {
        return Utilities.calculateStatWithBadge(
                Nature.getValue(),
                Integer.parseInt(BaseSpeed.getText()),
                Integer.parseInt(EvSpeed.getText()),
                Integer.parseInt(IvSpeed.getText()),
                Integer.parseInt(Level.getText()),
                "Speed",
                null,
                false
        );
    }

}
