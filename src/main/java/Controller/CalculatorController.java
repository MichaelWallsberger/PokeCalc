package Controller;

import Classes.*;
import Classes.Animation.BorderShadow;
import Classes.Animation.ShakeTransition;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CalculatorController {
    // static method to create instance of Singleton class
    private static CalculatorController controller = null;
    public static CalculatorController getInstance() {
        return controller;
    }

    private API.Database dbAPI;
    private Data.dataSingleton data;

    // User Move
    public Label userMove1Attack;
    public Label userMove1Damage;
    public Label userMove2Attack;
    public Label userMove2Damage;
    public Label userMove3Attack;
    public Label userMove3Damage;
    public Label userMove4Attack;
    public Label userMove4Damage;
    // Foe Move
    public Label foeMove1Attack;
    public Label foeMove1Damage;
    public Label foeMove2Damage;
    public Label foeMove2Attack;
    public Label foeMove3Damage;
    public Label foeMove3Attack;
    public Label foeMove4Damage;
    public Label foeMove4Attack;
    // --------------------- User Pokemon ---------------------
    public Label userPokemon1;
    public Label userPokemon2;
    public Label userPokemon3;
    public Label userPokemon4;
    public Label userPokemon5;
    public Label userPokemon6;
    public ScrollPane UserBoxBox;
    public ScrollPane UserBoxPokemon;
    public FlowPane UserPokemons;
    // Base
    public TextField UserPokemonNickname;
    public ComboBox<PokeStatsList> UserPokemonSpecies;
    public ComboBox<String> UserPokemonType1;
    public ComboBox<String> UserPokemonType2;
    public ComboBox<String> UserPokemonForm;
    public ComboBox<String> UserPokemonGender;
    public TextField UserPokemonLevel;
    public TextField UserPokemonFriendship;
    // Stats
    public TextField UserPokemonBaseHP;
    public TextField UserPokemonIVHP;
    public TextField UserPokemonEVHP;
    public Label UserPokemonHP;
    public TextField UserPokemonBaseAttack;
    public TextField UserPokemonIVAttack;
    public TextField UserPokemonEVAttack;
    public Label UserPokemonAttack;
    public ComboBox<String> UserPokemonAttackBoost;
    public Label UserPokemonAttackFinal;
    public TextField UserPokemonBaseDefense;
    public TextField UserPokemonIVDefense;
    public TextField UserPokemonEVDefense;
    public Label UserPokemonDefense;
    public ComboBox<String> UserPokemonDefenseBoost;
    public Label UserPokemonDefenseFinal;
    public TextField UserPokemonBaseSpecialAttack;
    public TextField UserPokemonIVSpecialAttack;
    public TextField UserPokemonEVSpecialAttack;
    public Label UserPokemonSpecialAttack;
    public ComboBox<String> UserPokemonSpecialAttackBoost;
    public Label UserPokemonSpecialAttackFinal;
    public TextField UserPokemonBaseSpecialDefense;
    public TextField UserPokemonIVSpecialDefense;
    public TextField UserPokemonEVSpecialDefense;
    public Label UserPokemonSpecialDefense;
    public ComboBox<String> UserPokemonSpecialDefenseBoost;
    public Label UserPokemonSpecialDefenseFinal;
    public TextField UserPokemonBaseSpeed;
    public TextField UserPokemonIVSpeed;
    public TextField UserPokemonEVSpeed;
    public Label UserPokemonSpeed;
    public ComboBox<String> UserPokemonSpeedBoost;
    public Label UserPokemonSpeedFinal;
    // Special
    public ComboBox<Nature> UserPokemonNature;
    public ComboBox<Ability> UserPokemonAbility;
    public ComboBox<Item> UserPokemonItem;
    public ComboBox<String> UserPokemonStatus;
    public TextField UserPokemonCurrentHPFlat;
    public Label UserPokemonMaxHp;
    public TextField UserPokemonCurrentHPPercentage;
    public Button UserPokemonDynamax;
    public ComboBox<Route> UserPokemonMetLocation;
    public ComboBox<Sprite> UserPokemonPokeball;
    // Attacks
    public ComboBox<Attack> UserPokemonAttack1;
    public TextField UserPokemonAttack1Damage;
    public ComboBox<String> UserPokemonAttack1Type;
    public ComboBox<String> UserPokemonAttack1Category;
    public TextField UserPokemonAttack1Hits;
    public ComboBox<Attack> UserPokemonAttack2;
    public TextField UserPokemonAttack2Damage;
    public ComboBox<String> UserPokemonAttack2Type;
    public ComboBox<String> UserPokemonAttack2Category;
    public TextField UserPokemonAttack2Hits;
    public ComboBox<Attack> UserPokemonAttack3;
    public TextField UserPokemonAttack3Damage;
    public ComboBox<String> UserPokemonAttack3Type;
    public ComboBox<String> UserPokemonAttack3Category;
    public TextField UserPokemonAttack3Hits;
    public ComboBox<Attack> UserPokemonAttack4;
    public TextField UserPokemonAttack4Damage;
    public ComboBox<String> UserPokemonAttack4Type;
    public ComboBox<String> UserPokemonAttack4Category;
    public TextField UserPokemonAttack4Hits;
    // Options
    public Button AddUserPokemonButton;
    public Button EditUserPokemonButton;
    public Button DeleteUserPokemonButton;
    // --------------------- Center ---------------------
    public ImageView Badge1;
    public ImageView Badge2;
    public ImageView Badge3;
    public ImageView Badge4;
    public ImageView Badge5;
    public ImageView Badge6;
    public ImageView Badge7;
    public ImageView Badge8;
    // Battle Mode
    public String activeBattleMode;
    public Button Singles;
    public Button Doubles;
    // Terrain
    public Button ElectricTerrain;
    public Button GrassyTerrain;
    public Button MistyTerrain;
    public Button PsychicTerrain;
    // Weather
    public String activeWeather;
    public Button None;
    public Button Sun;
    public Button Rain;
    public Button Sand;
    public Button Hail;
    public Button HarshSunshine;
    public Button HeavyRain;
    public Button StrongWinds;
    // Gravity
    public Button Gravity;
    // Other things
    public Button UserStealthRock;
    public Button FoeStealthRock;
    public Button UserSteelsurge;
    public Button FoeSteelsurge;
    public Button UserVineLash;
    public Button FoeVineLash;
    public Button UserCannonade;
    public Button UserSpike0;
    public Button UserReflect;
    public Button UserLeechSeed;
    public Button UserForesight;
    public Button UserHelpingHand;
    public Button UserTailwind;
    public Button UserFriendGuard;
    public Button UserBattery;
    public Button UserWildfire;
    public Button UserVolcalith;
    public Button UserSpike1;
    public Button UserSpike2;
    public Button UserSpike3;
    public Button UserLightScreen;
    public Button FoeCannonade;
    public Button FoeSpike0;
    public Button FoeReflect;
    public Button FoeLeechSeed;
    public Button FoeForesight;
    public Button FoeHelpingHand;
    public Button FoeTailwind;
    public Button FoeFriendGuard;
    public Button FoeBattery;
    public Button FoeWildfire;
    public Button FoeVolcalith;
    public Button FoeSpike1;
    public Button FoeSpike2;
    public Button FoeSpike3;
    public Button FoeLightScreen;
    // --------------------- Foe ---------------------
    public ImageView FoeTrainerSprite;
    public Label foePokemon1;
    public Label foePokemon2;
    public Label foePokemon3;
    public Label foePokemon4;
    public Label foePokemon5;
    public Label foePokemon6;
    public ScrollPane TrainerBox;
    public VBox Trainers;
    public ScrollPane TrainerBoxPokemon;
    // Base
    public TextField FoePokemonNickname;
    public ComboBox<PokeStatsList> FoePokemonSpecies;
    public ComboBox<String> FoePokemonType1;
    public ComboBox<String> FoePokemonType2;
    public ComboBox<String> FoePokemonForm;
    public ComboBox<String> FoePokemonGender;
    public TextField FoePokemonLevel;
    public TextField FoePokemonFriendship;
    // Stats
    public TextField FoePokemonBaseHP;
    public TextField FoePokemonIVHP;
    public TextField FoePokemonEVHP;
    public Label FoePokemonHP;
    public TextField FoePokemonBaseAttack;
    public TextField FoePokemonIVAttack;
    public TextField FoePokemonEVAttack;
    public Label FoePokemonAttack;
    public ComboBox<String> FoePokemonAttackBoost;
    public Label FoePokemonAttackFinal;
    public TextField FoePokemonBaseDefense;
    public TextField FoePokemonIVDefense;
    public TextField FoePokemonEVDefense;
    public Label FoePokemonDefense;
    public ComboBox<String> FoePokemonDefenseBoost;
    public Label FoePokemonDefenseFinal;
    public TextField FoePokemonBaseSpecialAttack;
    public TextField FoePokemonIVSpecialAttack;
    public TextField FoePokemonEVSpecialAttack;
    public Label FoePokemonSpecialAttack;
    public ComboBox<String> FoePokemonSpecialAttackBoost;
    public Label FoePokemonSpecialAttackFinal;
    public TextField FoePokemonBaseSpecialDefense;
    public TextField FoePokemonIVSpecialDefense;
    public TextField FoePokemonEVSpecialDefense;
    public Label FoePokemonSpecialDefense;
    public ComboBox<String> FoePokemonSpecialDefenseBoost;
    public Label FoePokemonSpecialDefenseFinal;
    public TextField FoePokemonBaseSpeed;
    public TextField FoePokemonIVSpeed;
    public TextField FoePokemonEVSpeed;
    public Label FoePokemonSpeed;
    public ComboBox<String> FoePokemonSpeedBoost;
    public Label FoePokemonSpeedFinal;
    public TextField FoePokemonCurrentHPFlat;
    public Label FoePokemonMaxHp;
    public TextField FoePokemonCurrentHPPercentage;
    public Button FoePokemonDynamax;
    // Special
    public ComboBox<Nature> FoePokemonNature;
    public ComboBox<Ability> FoePokemonAbility;
    public ComboBox<Item> FoePokemonItem;
    public ComboBox<String> FoePokemonStatus;
    public ComboBox<Route> FoePokemonMetLocation;
    public ComboBox<Sprite> FoePokemonPokeball;
    // Attacks
    public ComboBox<Attack> FoePokemonAttack1;
    public TextField FoePokemonAttack1Damage;
    public ComboBox<String> FoePokemonAttack1Type;
    public ComboBox<String> FoePokemonAttack1Category;
    public TextField FoePokemonAttack1Hits;
    public ComboBox<Attack> FoePokemonAttack2;
    public TextField FoePokemonAttack2Damage;
    public ComboBox<String> FoePokemonAttack2Type;
    public ComboBox<String> FoePokemonAttack2Category;
    public TextField FoePokemonAttack2Hits;
    public ComboBox<Attack> FoePokemonAttack3;
    public TextField FoePokemonAttack3Damage;
    public ComboBox<String> FoePokemonAttack3Type;
    public ComboBox<String> FoePokemonAttack3Category;
    public TextField FoePokemonAttack3Hits;
    public ComboBox<Attack> FoePokemonAttack4;
    public TextField FoePokemonAttack4Damage;
    public ComboBox<String> FoePokemonAttack4Type;
    public ComboBox<String> FoePokemonAttack4Category;
    public TextField FoePokemonAttack4Hits;
    // Options
    public Button AddFoePokemonButton;
    public Button EditFoePokemonButton;
    public Button DeleteFoePokemonButton;
    // -------------- Booleans for the middle part --------------
    // Badges
    public boolean badge1;
    public boolean badge2;
    public boolean badge3;
    public boolean badge4;
    public boolean badge5;
    public boolean badge6;
    public boolean badge7;
    public boolean badge8;
    // Mode
    public boolean singleBattle;
    public boolean doubleBattle;
    // Terrain
    public boolean electricTerrain;
    public boolean grassyTerrain;
    public boolean mistyTerain;
    public boolean psychicTerrain;
    // Weather
    public boolean noWeather;
    public boolean sun;
    public boolean rain;
    public boolean sand;
    public boolean hail;
    public boolean harshSunshine;
    public boolean heavyRain;
    public boolean strongWinds;
    // Gravity
    public boolean gravity;
    // other things - User
    public boolean userStealthRock;
    public boolean userSteelsurge;
    public boolean userVineLash, userWildfire;
    public boolean userCannonade, userVolcalith;
    public boolean userSpikes0, userSpikes1, userSpikes2, userSpikes3;
    public boolean userReflect, userLightScreen;
    public boolean userProtect;
    public boolean userLeechSeed;
    public boolean userForesight;
    public boolean userHelpingHand;
    public boolean userTailwind;
    public boolean userFriendGuard;
    public boolean userBattery;
    // other things - Foe
    public boolean foeStealthRock;
    public boolean foeSteelsurge;
    public boolean foeVineLash, foeWildfire;
    public boolean foeCannonade, foeVolcalith;
    public boolean foeSpikes0, foeSpikes1, foeSpikes2, foeSpikes3;
    public boolean foeReflect, foeLightScreen;
    public boolean foeProtect;
    public boolean foeLeechSeed;
    public boolean foeForesight;
    public boolean foeHelpingHand;
    public boolean foeTailwind;
    public boolean foeFriendGuard;
    public boolean foeBattery;
    // --------------------- My Variables ---------------------
    Pokemon tempPokemon = Data.dataSingleton.tempPokemon;
    // User
    public int activeUserPokemonIndex;
    public Label activePokemonImageInSelection;
    public Label activePokemonImageInBox;
    public Label previousUserPokemonBox;
    public Pokemon activeUserPokemon;
    public Pokemon pokemon1;
    public Pokemon pokemon2;
    public Pokemon pokemon3;
    public Pokemon pokemon4;
    public Pokemon pokemon5;
    public Pokemon pokemon6;
    public Label pokemon1ImageBox;
    public Label pokemon2ImageBox;
    public Label pokemon3ImageBox;
    public Label pokemon4ImageBox;
    public Label pokemon5ImageBox;
    public Label pokemon6ImageBox;
    // Trainer
    public ObservableList<Trainer> trainerList;
    public TitledPane activeTrainerTitlePane;
    public int activeTrainerId;
    public int trainerIndex;
    public int activeTrainerPokemonIndex;
    public Label activeTrainerPokemonBox;
    public Label previousTrainerPokemonBox;
    public Pokemon activeTrainerPokemon;
    public Pokemon trainerPokemon1;
    public Pokemon trainerPokemon2;
    public Pokemon trainerPokemon3;
    public Pokemon trainerPokemon4;
    public Pokemon trainerPokemon5;
    public Pokemon trainerPokemon6;
    // flatDamage
    public boolean showFlatDamage;
    // Badges
    public Badge attackBadge;
    public boolean attackBadgeBoolean;
    public Badge defenseBadge;
    public boolean defenseBadgeBoolean;
    public Badge specialBadge;
    public boolean specialBadgeBoolean;
    public Badge speedBadge;
    public boolean speedBadgeBoolean;
    // Lists
    public ArrayList<PokemonList> userPokemonList;
    public ArrayList<Badge> badgeList;

    // setUp Variables
    @FXML
    public void initialize() {
        controller = this;
        showRightWindowsWhenCalculatorIsOpened();
        setupBooleans();
        loadContent();
    }

    private void showRightWindowsWhenCalculatorIsOpened() {
        UserBoxBox.setVisible(true);
        UserBoxPokemon.setVisible(false);
        TrainerBox.setVisible(true);
        TrainerBoxPokemon.setVisible(false);
    }

    // -------------------------------------------- Events --------------------------------------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    public void damagePercentage() {
        showFlatDamage = false;
        calculateDamage();
    }

    public void damageFlat() {
        showFlatDamage = true;
        calculateDamage();
    }

    // -------------------------------------------- User --------------------------------------------
    public void clickedUserPokemon1() {
        activeUserPokemon = pokemon1;
        activePokemonImageInSelection = userPokemon1;
        activePokemonImageInBox = pokemon1ImageBox;
        setActiveUserPokemon(userPokemon1, 1);
        if (pokemon1 != null) setUserPokemonStats(pokemon1.pokemonId);
        calculateDamage();
    }

    public void clickedUserPokemon2() {
        activeUserPokemon = pokemon2;
        activePokemonImageInSelection = userPokemon2;
        activePokemonImageInBox = pokemon2ImageBox;
        setActiveUserPokemon(userPokemon2, 2);
        if (pokemon2 != null) setUserPokemonStats(pokemon2.pokemonId);

        calculateDamage();
    }

    public void clickedUserPokemon3() {
        activeUserPokemon = pokemon3;
        activePokemonImageInSelection = userPokemon3;
        activePokemonImageInBox = pokemon3ImageBox;
        setActiveUserPokemon(userPokemon3, 3);
        if (pokemon3 != null) setUserPokemonStats(pokemon3.pokemonId);
        calculateDamage();
    }

    public void clickedUserPokemon4() {
        activeUserPokemon = pokemon4;
        activePokemonImageInSelection = userPokemon4;
        activePokemonImageInBox = pokemon4ImageBox;
        setActiveUserPokemon(userPokemon4, 4);
        if (pokemon4 != null) setUserPokemonStats(pokemon4.pokemonId);
        calculateDamage();
    }

    public void clickedUserPokemon5() {
        activeUserPokemon = pokemon5;
        activePokemonImageInSelection = userPokemon5;
        activePokemonImageInBox = pokemon5ImageBox;
        setActiveUserPokemon(userPokemon5, 5);
        if (pokemon5 != null) setUserPokemonStats(pokemon5.pokemonId);
        calculateDamage();
    }

    public void clickedUserPokemon6() {
        activeUserPokemon = pokemon6;
        activePokemonImageInSelection = userPokemon6;
        activePokemonImageInBox = pokemon6ImageBox;
        setActiveUserPokemon(userPokemon6, 6);
        if (pokemon6 != null) setUserPokemonStats(pokemon6.pokemonId);
        calculateDamage();
    }

    public void getUserBox() {
        UserBoxBox.setVisible(true);
        UserBoxPokemon.setVisible(false);
    }

    public void getUserPokemonStats() {
        UserBoxBox.setVisible(false);
        UserBoxPokemon.setVisible(true);
    }

    public void clickedNoUserPokemon() {
       setUserPokemonStats(tempPokemon.pokemonId);
       activeUserPokemon = tempPokemon;
       calculateDamage();

       String path = "-fx-background-image: url(/Img/Pokeballs/000.png);";
       switch (activeUserPokemonIndex) {
            case 1 -> {
                userPokemon1.setStyle(path);
                pokemon1 = tempPokemon;
            }
            case 2 -> {
                userPokemon2.setStyle(path);
                pokemon2 = tempPokemon;
            }
            case 3 -> {
                userPokemon3.setStyle(path);
                pokemon3 = tempPokemon;
            }
            case 4 -> {
                userPokemon4.setStyle(path);
                pokemon4 = tempPokemon;
            }
            case 5 -> {
                userPokemon5.setStyle(path);
                pokemon5 = tempPokemon;
            }
            case 6 -> {
                userPokemon6.setStyle(path);
                pokemon6 = tempPokemon;
            }
       }
    }

    public void clickedUserPokemonSpecies() {
        try {
            PokeStatsList pokeStatsList = UserPokemonSpecies.getValue();
            // change Types
            ArrayList<String> pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokeStatsList.pokemonStatsId);
            selecPokemonType(pokemonTypes, UserPokemonType1, UserPokemonType2);
            // load Forms
            UserPokemonForm.setValue(pokeStatsList.nameOfPokemon);
            // load Base Stats
            PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokeStatsList.pokemonStatsId);
            setUserPokemonBaseStats(pokeStats);
            calcAndSetUserPokemonStats();
            // calculate
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void clickedUserPokemonType1() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void clickedUserPokemonType2() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void clickedUserPokemonForm() {
        calculateDamage();
    }

    public void clickedUserPokemonGender() {
        calculateDamage();
    }

    public void changeUserPokemonLevel() {
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void changedUserPokemonBaseHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonIVHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonEVHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonBaseAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changedUserPokemonIVAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changeUserPokemonAttackBoost() {
        calcUserPokemonFinalAttack();
        calculateDamage();
    }

    public void changedUserPokemonBaseDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonIVDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonEVDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonDefenseBoost() {
        calcUserPokemonFinalDefense();
        calculateDamage();
    }

    public void changedUserPokemonBaseSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonIVSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialAttackBoost() {
        calcUserPokemonFinalSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonBaseSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonIVSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonSpecialDefenseBoost() {
        calcUserPokemonFinalSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonBaseSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonIVSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonEVSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonSpeedBoost() {
        calcUserPokemonFinalSpeed();
        calculateDamage();
    }

    public void changeUserPokemonNature() {
        try {
            calcAndSetUserPokemonStats();
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeUserPokemonAbility() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeUserPokemonItem() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeUserPokemonStatus() {
        calculateDamage();
    }

    public void changedUserPokemonCurrentHPFlat() {
        calcUserPokemonPercentageBasedOnCurrentHp();
        calculateDamage();
    }

    public void changedUserPokemonCurrentHPPercentage() {
        calcUserPokemonCurrentHpBasedOnPercentage(Integer.parseInt(UserPokemonHP.getText()));
        calculateDamage();
    }

    public void clickedUserPokemonDynamax() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1() {
        try {
            Attack attack = UserPokemonAttack1.getValue();
            setUserAttackValues(1, attack);
            if (attack.attackId > 0) userMove1Attack.setText(attack.attackName);
            else userMove1Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changedUserPokemonAttack1Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2() {
        try {
            Attack attack = UserPokemonAttack2.getValue();
            setUserAttackValues(2, attack);
            if (attack.attackId > 0) userMove2Attack.setText(attack.attackName);
            else userMove2Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changedUserPokemonAttack2Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3() {
        try {
            Attack attack = UserPokemonAttack3.getValue();
            setUserAttackValues(3, attack);
            if (attack.attackId > 0) userMove3Attack.setText(attack.attackName);
            else userMove3Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changedUserPokemonAttack3Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4() {
        try {
            Attack attack = UserPokemonAttack4.getValue();
            setUserAttackValues(4, attack);
            if (attack.attackId > 0) userMove4Attack.setText(attack.attackName);
            else userMove4Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changedUserPokemonAttack4Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4Category() {
        calculateDamage();
    }

    public void addUserPokemon() {
        int result = 0;
        PokemonList pokemon = null;

        try {
            // get Values
            String gameName = data.getGameName();
            int pokemonStatsId = UserPokemonSpecies.getValue().pokemonStatsId;
            String nickName = UserPokemonNickname.getText();
            String gender = UserPokemonGender.getValue();
            int level = Integer.parseInt(UserPokemonLevel.getText());
            String natureName = UserPokemonNature.getValue().natureName;
            int itemId = UserPokemonItem.getValue().itemId;
            int abilityId = UserPokemonAbility.getValue().abilityId;
            int friendship = Integer.parseInt(UserPokemonFriendship.getText());
            int pokeball = UserPokemonPokeball.getValue().spriteId;
            int metLocation = UserPokemonMetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(UserPokemonIVHP.getText());
            int ivAttack = Integer.parseInt(UserPokemonIVAttack.getText());
            int ivDefense = Integer.parseInt(UserPokemonIVDefense.getText());
            int ivSpecialAttack = Integer.parseInt(UserPokemonIVSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(UserPokemonIVSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(UserPokemonIVSpeed.getText());
            int evHp = Integer.parseInt(UserPokemonEVHP.getText());
            int evAttack = Integer.parseInt(UserPokemonEVAttack.getText());
            int evDefense = Integer.parseInt(UserPokemonEVDefense.getText());
            int evSpecialAttack = Integer.parseInt(UserPokemonEVSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(UserPokemonEVSpecialDefense.getText());
            int evSpeed = Integer.parseInt(UserPokemonEVSpeed.getText());
            int attack1 = UserPokemonAttack1.getValue().attackId;
            int attack2 = UserPokemonAttack2.getValue().attackId;
            int attack3 = UserPokemonAttack3.getValue().attackId;
            int attack4 = UserPokemonAttack4.getValue().attackId;

            result = dbAPI.addPokemon(gameName, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);

            if (result > 0) pokemon = new PokemonList(result,nickName,pokemonStatsId, UserPokemonSpecies.getValue().dexNr);
        } catch (Exception e) {
            System.out.println("Error in adding a Pokemon to the user, " + e);
        }

        if (result > 0) {
            BorderShadow light = new BorderShadow(AddUserPokemonButton);
            light.playFromStart(); // Animation

            int trainerId = dbAPI.getUserIdFromSpecificGame(data.getGameName());
            dbAPI.addPokemonToUser(data.getGameName(), trainerId, result, userPokemonList.size()+1);

            addPokemonToList(pokemon);
            updatePokemonInSelection(pokemon);
        } else {
            ShakeTransition anim = new ShakeTransition(AddUserPokemonButton);
            anim.playFromStart();
        }
    }

    public void editUserPokemon() {
        boolean result = false;

        try {
            // get Values
            int pokemonId = activeUserPokemon.pokemonId;
            int pokemonStatsId = UserPokemonSpecies.getValue().pokemonStatsId;
            String nickName = UserPokemonNickname.getText();
            String gender = UserPokemonGender.getValue();
            int level = Integer.parseInt(UserPokemonLevel.getText());
            String natureName = UserPokemonNature.getValue().natureName;
            int itemId = UserPokemonItem.getValue().itemId;
            int abilityId = UserPokemonAbility.getValue().abilityId;
            int friendship = Integer.parseInt(UserPokemonFriendship.getText());
            int pokeball = UserPokemonPokeball.getValue().spriteId;
            int metLocation = UserPokemonMetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(UserPokemonIVHP.getText());
            int ivAttack = Integer.parseInt(UserPokemonIVAttack.getText());
            int ivDefense = Integer.parseInt(UserPokemonIVDefense.getText());
            int ivSpecialAttack = Integer.parseInt(UserPokemonIVSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(UserPokemonIVSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(UserPokemonIVSpeed.getText());
            int evHp = Integer.parseInt(UserPokemonEVHP.getText());
            int evAttack = Integer.parseInt(UserPokemonEVAttack.getText());
            int evDefense = Integer.parseInt(UserPokemonEVDefense.getText());
            int evSpecialAttack = Integer.parseInt(UserPokemonEVSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(UserPokemonEVSpecialDefense.getText());
            int evSpeed = Integer.parseInt(UserPokemonEVSpeed.getText());
            int attack1 = UserPokemonAttack1.getValue().attackId;
            int attack2 = UserPokemonAttack2.getValue().attackId;
            int attack3 = UserPokemonAttack3.getValue().attackId;
            int attack4 = UserPokemonAttack4.getValue().attackId;

            result = dbAPI.updatePokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
            System.out.println("Error in update user Pokemon, " + e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(EditUserPokemonButton);
            light.playFromStart(); // Animation

            updateLabel();
        } else {
            ShakeTransition anim = new ShakeTransition(EditUserPokemonButton);
            anim.playFromStart();
        }
    }

    public void deleteUserPokemon() {
        boolean result = false;

        try {
            int trainerId = dbAPI.getUserIdFromSpecificGame(data.getGameName());
            result = dbAPI.removePokemonFromUser(trainerId, activeUserPokemon.pokemonId);
        } catch (Exception e) {
            System.out.println("Delete User Pokemon didn't work. Error: " + e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(DeleteUserPokemonButton);
            light.playFromStart(); // Animation

            // deletes Pokemon, only works if no trainer has the pokemon in his party
            dbAPI.deletePokemon(activeUserPokemon.pokemonId);

            removePokemonFromBox();
            selectNoPokemon();
        } else {
            ShakeTransition anim = new ShakeTransition(DeleteUserPokemonButton);
            anim.playFromStart();
        }
    }

    // Add Pokemon
    private void addPokemonToList(PokemonList pokemon) {
        Label newPokemon = createPokemonImage(pokemon);
        activePokemonImageInBox = newPokemon;
        setPokemonToTheUser(pokemon, newPokemon);
    }

    private void updatePokemonInSelection(PokemonList poke) {
        Pokemon pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
        activeUserPokemon = pokemon;
        setUserPokemonOptions(pokemon);
        calculateDamage();
    }

    // Edit Pokemon
    private void updateLabel() {
        // in Box
        updatePokemonSprite(activePokemonImageInBox, UserPokemonSpecies.getValue().pokemonStatsId);
        activePokemonImageInBox.setTooltip(createTooltipForPokemon(dbAPI.getPokemonFromPokemonId(activeUserPokemon.pokemonId)));
        // in Selection
        updatePokemonSprite(activePokemonImageInSelection, UserPokemonSpecies.getValue().pokemonStatsId);
    }

    // Delete Pokemon
    private void removePokemonFromBox() {
        UserPokemons.getChildren().removeIf(node -> node.equals(activePokemonImageInBox));
    }

    private void selectNoPokemon() {
        clickedNoUserPokemon();
    }

    // -------------------------------------------- Center --------------------------------------------
    public void clickedBadge1() {
        badge1 = switchBadgeToActiveOrNot(badge1, Badge1);
        setBadgeBoolean(0, badge1);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge2() {
        badge2 = switchBadgeToActiveOrNot(badge2, Badge2);
        setBadgeBoolean(1, badge2);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge3() {
        badge3 = switchBadgeToActiveOrNot(badge3, Badge3);
        setBadgeBoolean(2, badge3);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge4() {
        badge4 = switchBadgeToActiveOrNot(badge4, Badge4);
        setBadgeBoolean(3, badge4);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge5() {
        badge5 = switchBadgeToActiveOrNot(badge5, Badge5);
        setBadgeBoolean(4, badge5);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge6() {
        badge6 = switchBadgeToActiveOrNot(badge6, Badge6);
        setBadgeBoolean(5, badge6);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge7() {
        badge7 = switchBadgeToActiveOrNot(badge7, Badge7);
        setBadgeBoolean(6, badge7);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge8() {
        badge8 = switchBadgeToActiveOrNot(badge8, Badge8);
        setBadgeBoolean(7, badge8);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedSingles() {
        if (!singleBattle) {
            activeBattleMode = "Single";

            singleBattle = true;
            Singles.getStyleClass().clear();
            Singles.getStyleClass().add("calcButtonInUsage");

            doubleBattle = false;
            Doubles.getStyleClass().clear();
            Doubles.getStyleClass().add("calcButtonsNotUsed");
        }
        calculateDamage();
    }

    public void clickedDoubles() {
        if (!doubleBattle) {
            activeBattleMode = "Double";

            doubleBattle = true;
            Doubles.getStyleClass().clear();
            Doubles.getStyleClass().add("calcButtonInUsage");

            singleBattle = false;
            Singles.getStyleClass().clear();
            Singles.getStyleClass().add("calcButtonsNotUsed");
        }
        calculateDamage();
    }

    public void clickedElectricTerrain() {
        electricTerrain = !electricTerrain;

        if (electricTerrain) {
            grassyTerrain = false;
            mistyTerain = false;
            psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedGrassyTerrain() {
        grassyTerrain = !grassyTerrain;

        if (grassyTerrain) {
            electricTerrain = false;
            mistyTerain = false;
            psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedMistyTerrain() {
        mistyTerain = !mistyTerain;

        if (mistyTerain) {
            electricTerrain = false;
            grassyTerrain = false;
            psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedPsychicTerrain() {
        psychicTerrain = !psychicTerrain;

        if (psychicTerrain) {
            electricTerrain = false;
            grassyTerrain = false;
            mistyTerain = false;
        }

        setTerrainCSS();
    }

    public void clickedNone() {
        if (!noWeather) {
            activeWeather = "None";

            noWeather = true;
            sun = false;
            rain = false;
            sand = false;
            hail = false;
            harshSunshine = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedSun() {
        if (!sun) {
            activeWeather = "Sun";

            sun = true;
            noWeather = false;
            rain = false;
            sand = false;
            hail = false;
            harshSunshine = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedRain() {
        if (!rain) {
            activeWeather = "Rain";

            rain = true;
            noWeather = false;
            sun = false;
            sand = false;
            hail = false;
            harshSunshine = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedSand() {
        if (!sand) {
            activeWeather = "Sand";

            sand = true;
            noWeather = false;
            sun = false;
            rain = false;
            hail = false;
            harshSunshine = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHail() {
        if (!hail) {
            activeWeather = "Hail";

            hail = true;
            noWeather = false;
            sun = false;
            rain = false;
            sand = false;
            harshSunshine = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHarshSunshine() {
        if (!harshSunshine) {
            activeWeather = "Sun";

            harshSunshine = true;
            noWeather = false;
            sun = false;
            rain = false;
            sand = false;
            hail = false;
            heavyRain = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHeavyRain() {
        if (!heavyRain) {
            activeWeather = "Rain";

            heavyRain = true;
            noWeather = false;
            sun = false;
            rain = false;
            sand = false;
            hail = false;
            harshSunshine = false;
            strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedStrongWinds() {
        if (!strongWinds) {
            activeWeather = "None";

            strongWinds = true;
            noWeather = false;
            sun = false;
            rain = false;
            sand = false;
            hail = false;
            harshSunshine = false;
            heavyRain = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedGravity() {
        gravity = !gravity;

        Gravity.getStyleClass().clear();
        if (gravity) Gravity.getStyleClass().add("calcButtonInUsage");
        else Gravity.getStyleClass().add("calcButtonsNotUsed");
    }

    public void clickedUserStealthRock() {
        userStealthRock = switchButtonOnActiveOrNot(userStealthRock, UserStealthRock);
    }

    public void clickedFoeStealthRock() {
        foeStealthRock = switchButtonOnActiveOrNot(foeStealthRock, FoeStealthRock);
    }

    public void clickedUserSteelsurge() {
        userSteelsurge = switchButtonOnActiveOrNot(userSteelsurge, UserSteelsurge);
    }

    public void clickedFoeSteelsurge() {
        foeSteelsurge = switchButtonOnActiveOrNot(foeSteelsurge, FoeSteelsurge);
    }

    public void clickedUserVineLash() {
        userVineLash = switchButtonOnActiveOrNot(userVineLash, UserVineLash);
    }

    public void clickedFoeVineLash() {
        foeVineLash = switchButtonOnActiveOrNot(foeVineLash, FoeVineLash);
    }

    public void clickedUserWildfire() {
        userWildfire = switchButtonOnActiveOrNot(userWildfire, UserWildfire);
    }

    public void clickedFoeWildfire() {
        foeWildfire = switchButtonOnActiveOrNot(foeWildfire, FoeWildfire);
    }

    public void clickedUserCannonade() {
        userCannonade = switchButtonOnActiveOrNot(userCannonade, UserCannonade);
    }

    public void clickedFoeCannonade() {
        foeCannonade = switchButtonOnActiveOrNot(foeCannonade, FoeCannonade);
    }

    public void clickedUserVolcalith() {
        userVolcalith = switchButtonOnActiveOrNot(userVolcalith, UserVolcalith);
    }

    public void clickedFoeVolcalith() {
        foeVolcalith = switchButtonOnActiveOrNot(foeVolcalith, FoeVolcalith);
    }

    public void clickedUserSpike0() {
        if (!userSpikes0) {
            userSpikes0 = true;
            UserSpike0.getStyleClass().clear();
            UserSpike0.getStyleClass().add("calcButtonInUsage");

            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes2 = setSpikeToFalse(UserSpike2);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike1() {
        if (!userSpikes1) {
            userSpikes1 = true;
            UserSpike1.getStyleClass().clear();
            UserSpike1.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes2 = setSpikeToFalse(UserSpike2);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike2() {
        if (!userSpikes2) {
            userSpikes2 = true;
            UserSpike2.getStyleClass().clear();
            UserSpike2.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike3() {
        if (!userSpikes3) {
            userSpikes3 = true;
            UserSpike3.getStyleClass().clear();
            UserSpike3.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes2 = setSpikeToFalse(UserSpike2);
        }
    }

    public void clickedFoeSpike0() {
        if (!foeSpikes0) {
            foeSpikes0 = true;
            FoeSpike0.getStyleClass().clear();
            FoeSpike0.getStyleClass().add("calcButtonInUsage");

            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike1() {
        if (!foeSpikes1) {
            foeSpikes1 = true;
            FoeSpike1.getStyleClass().clear();
            FoeSpike1.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike2() {
        if (!foeSpikes2) {
            foeSpikes2 = true;
            FoeSpike2.getStyleClass().clear();
            FoeSpike2.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike3() {
        if (!foeSpikes3) {
            foeSpikes3 = true;
            FoeSpike3.getStyleClass().clear();
            FoeSpike3.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
        }
    }

    public void clickedUserReflect() {
        userReflect = switchButtonOnActiveOrNot(userReflect, UserReflect);
        calculateDamage();
    }

    public void clickedFoeReflect() {
        foeReflect = switchButtonOnActiveOrNot(foeReflect, FoeReflect);
        calculateDamage();
    }

    public void clickedUserLightScreen() {
        userLightScreen = switchButtonOnActiveOrNot(userLightScreen, UserLightScreen);
        calculateDamage();
    }

    public void clickedFoeLightScreen() {
        foeLightScreen = switchButtonOnActiveOrNot(foeLightScreen, FoeLightScreen);
        calculateDamage();
    }

    public void clickedUserLeechSeed() {
        userLeechSeed = switchButtonOnActiveOrNot(userLeechSeed, UserLeechSeed);
    }

    public void clickedFoeLeechSeed() {
        foeLeechSeed = switchButtonOnActiveOrNot(foeLeechSeed, FoeLeechSeed);
    }

    public void clickedUserForesight() {
        userForesight = switchButtonOnActiveOrNot(userForesight, UserForesight);
    }

    public void clickedFoeForesight() {
        foeForesight = switchButtonOnActiveOrNot(foeForesight, FoeForesight);
    }

    public void clickedUserHelpingHand() {
        userHelpingHand = switchButtonOnActiveOrNot(userHelpingHand, UserHelpingHand);
        calculateDamage();
    }

    public void clickedFoeHelpingHand() {
        foeHelpingHand = switchButtonOnActiveOrNot(foeHelpingHand, FoeHelpingHand);
        calculateDamage();
    }

    public void clickedUserTailwind() {
        userTailwind = switchButtonOnActiveOrNot(userTailwind, UserTailwind);
        calcUserPokemonSpeed();

        if (userTailwind) {
            int speed = Integer.parseInt(UserPokemonSpeed.getText()) * 2;
            UserPokemonSpeed.setText(Integer.toString(speed));
            calcUserPokemonFinalSpeed();
        }

        calculateDamage();
    }

    public void clickedFoeTailwind() {
        foeTailwind = switchButtonOnActiveOrNot(foeTailwind, FoeTailwind);
        calcTrainerPokemonSpeed();

        if (foeTailwind) {
            int speed = Integer.parseInt(FoePokemonSpeed.getText()) * 2;
            FoePokemonSpeed.setText(Integer.toString(speed));
            calcTrainerPokemonFinalSpeed();
        }

        calculateDamage();
    }

    public void clickedUserFriendGuard() {
        userFriendGuard = switchButtonOnActiveOrNot(userFriendGuard, UserFriendGuard);
        calculateDamage();
    }

    public void clickedFoeFriendGuard() {
        foeFriendGuard = switchButtonOnActiveOrNot(foeFriendGuard, FoeFriendGuard);
        calculateDamage();
    }

    public void clickedUserBattery() {
        userBattery = switchButtonOnActiveOrNot(userBattery, UserBattery);
        calculateDamage();
    }

    public void clickedFoeBattery() {
        foeBattery = switchButtonOnActiveOrNot(foeBattery, FoeBattery);
        calculateDamage();
    }

    // -------------------------------------------- Trainer --------------------------------------------
    public void clickedFoePokemon1() {
        activeTrainerPokemon = trainerPokemon1;
        activeTrainerPokemonBox = foePokemon1;
        setActiveTrainerPokemon(foePokemon1, 1);
        if (trainerPokemon1 != null) setTrainerPokemonStats(trainerPokemon1.pokemonId);
        calculateDamage();
    }

    public void clickedFoePokemon2() {
        activeTrainerPokemon = trainerPokemon2;
        activeTrainerPokemonBox = foePokemon2;
        setActiveTrainerPokemon(foePokemon2, 2);
        if (trainerPokemon2 != null) setTrainerPokemonStats(trainerPokemon2.pokemonId);
        calculateDamage();
    }

    public void clickedFoePokemon3() {
        activeTrainerPokemon = trainerPokemon3;
        activeTrainerPokemonBox = foePokemon3;
        setActiveTrainerPokemon(foePokemon3, 3);
        if (trainerPokemon3 != null) setTrainerPokemonStats(trainerPokemon3.pokemonId);
        calculateDamage();
    }

    public void clickedFoePokemon4() {
        activeTrainerPokemon = trainerPokemon4;
        activeTrainerPokemonBox = foePokemon4;
        setActiveTrainerPokemon(foePokemon4, 4);
        if (trainerPokemon4 != null) setTrainerPokemonStats(trainerPokemon4.pokemonId);
        calculateDamage();
    }

    public void clickedFoePokemon5() {
        activeTrainerPokemon = trainerPokemon5;
        activeTrainerPokemonBox = foePokemon5;
        setActiveTrainerPokemon(foePokemon5, 5);
        if (trainerPokemon5 != null) setTrainerPokemonStats(trainerPokemon5.pokemonId);
        calculateDamage();
    }

    public void clickedFoePokemon6() {
        activeTrainerPokemon = trainerPokemon6;
        activeTrainerPokemonBox = foePokemon6;
        setActiveTrainerPokemon(foePokemon6, 6);
        if (trainerPokemon6 != null) setTrainerPokemonStats(trainerPokemon6.pokemonId);
        calculateDamage();
    }

    public void getTrainers() {
        TrainerBox.setVisible(true);
        TrainerBoxPokemon.setVisible(false);
    }

    public void getFoePokemonStats() {
        TrainerBox.setVisible(false);
        TrainerBoxPokemon.setVisible(true);
    }

    public void previousTrainer() {
        if (trainerIndex - 1 >= 0) {
            trainerIndex--;
            selectTrainer(trainerIndex);
        }
    }

    public void nextTrainer() {
        if (trainerIndex + 1 < trainerList.size()) {
            trainerIndex++;
            selectTrainer(trainerIndex);
        }
    }

    public void addTrainer() {
        Parent fxmlFile = Utilities.loadFxmlFile("createTrainer"); // load fxml file
        if (fxmlFile != null) AddTrainerController.getInstance().opensAddGameStage(fxmlFile);
    }

    public void clickedNoFoePokemon() {
        setTrainerPokemonStats(tempPokemon.pokemonId);
        activeTrainerPokemon = tempPokemon;
        calculateDamage();

        String path = "-fx-background-image: url(/Img/Pokeballs/000.png);";
        switch (activeTrainerPokemonIndex) {
            case 1 -> {
                foePokemon1.setStyle(path);
                trainerPokemon1 = tempPokemon;
            }
            case 2 -> {
                foePokemon2.setStyle(path);
                trainerPokemon2 = tempPokemon;
            }
            case 3 -> {
                foePokemon3.setStyle(path);
                trainerPokemon3 = tempPokemon;
            }
            case 4 -> {
                foePokemon4.setStyle(path);
                trainerPokemon4 = tempPokemon;
            }
            case 5 -> {
                foePokemon5.setStyle(path);
                trainerPokemon5 = tempPokemon;
            }
            case 6 -> {
                foePokemon6.setStyle(path);
                trainerPokemon6 = tempPokemon;
            }
        }
    }

    public void clickedFoePokemonSpecies() {
        try {
            PokeStatsList pokeStatsList = FoePokemonSpecies.getValue();
            // change Types
            ArrayList<String> pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokeStatsList.pokemonStatsId);
            selecPokemonType(pokemonTypes, FoePokemonType1, FoePokemonType2);
            // load Forms
            FoePokemonForm.setValue(pokeStatsList.nameOfPokemon);
            // load Base Stats
            PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokeStatsList.pokemonStatsId);
            setFoePokemonBaseStats(pokeStats);
            calcAndSetTrainerPokemonStats();
            // calculate
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonType1() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonType2() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonForm() {
    }

    public void changeFoePokemonGender() {
        calculateDamage();
    }

    public void changeFoePokemonLevel() {
        calcAndSetTrainerPokemonStats();
        calculateDamage();
    }

    public void changeFoePokemonBaseHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonIVHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonEVHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonBaseAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonIVAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonEVAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonAttackBoost() {
        calcTrainerPokemonFinalAttack();
        calculateDamage();
    }

    public void changeFoePokemonBaseDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonIVDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonEVDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonDefenseBoost() {
        calcTrainerPokemonFinalDefense();
        calculateDamage();
    }

    public void changeFoePokemonBaseSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonIVSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonEVSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonSpecialAttackBoost() {
        calcTrainerPokemonFinalSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonBaseSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonIVSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonEVSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonSpecialDefenseBoost() {
        calcTrainerPokemonFinalSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonBaseSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonIVSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonEVSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonSpeedBoost() {
        calcTrainerPokemonFinalSpeed();
        calculateDamage();
    }

    public void changeFoePokemonNature() {
        try {
            calcAndSetTrainerPokemonStats();
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonAbility() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonItem() {
        try {
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonStatus() {
        calculateDamage();
    }

    public void changeFoePokemonCurrentHPFlat() {
        calcTrainerPokemonPercentageBasedOnCurrentHp();
        calculateDamage();
    }

    public void changeFoePokemonCurrentHPPercentage() {
        calcTrainerPokemonCurrentHpBasedOnPercentage(Integer.parseInt(FoePokemonHP.getText()));
        calculateDamage();
    }

    public void clickFoePokemonDynamax() {
    }

    public void changeFoePokemonAttack1() {
        try {
            Attack attack = FoePokemonAttack1.getValue();
            setTrainerAttackValues(1, attack);
            if (attack.attackId > 0) foeMove1Attack.setText(attack.attackName);
            else foeMove1Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonAttack1Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack1Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack1Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2() {
        try {
            Attack attack = FoePokemonAttack2.getValue();
            setTrainerAttackValues(2, attack);
            if (attack.attackId > 0) foeMove2Attack.setText(attack.attackName);
            else foeMove2Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonAttack2Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3() {
        try {
            Attack attack = FoePokemonAttack3.getValue();
            setTrainerAttackValues(3, attack);
            if (attack.attackId > 0) foeMove3Attack.setText(attack.attackName);
            else foeMove3Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonAttack3Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4() {
        try {
            Attack attack = FoePokemonAttack4.getValue();
            setTrainerAttackValues(4, attack);
            if (attack.attackId > 0) foeMove4Attack.setText(attack.attackName);
            else foeMove4Attack.setText("No Move");
            calculateDamage();
        } catch (Exception ignored) {}
    }

    public void changeFoePokemonAttack4Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4Category() {
        calculateDamage();
    }

    public void addTrainerPokemon() {
        int result = 0;
        PokemonList pokemon = null;

        try {
            // get Values
            String gameName = data.getGameName();
            int pokemonStatsId = FoePokemonSpecies.getValue().pokemonStatsId;
            String nickName = FoePokemonNickname.getText();
            String gender = FoePokemonGender.getValue();
            int level = Integer.parseInt(FoePokemonLevel.getText());
            String natureName = FoePokemonNature.getValue().natureName;
            int itemId = FoePokemonItem.getValue().itemId;
            int abilityId = FoePokemonAbility.getValue().abilityId;
            int friendship = Integer.parseInt(FoePokemonFriendship.getText());
            int pokeball = FoePokemonPokeball.getValue().spriteId;
            int metLocation = FoePokemonMetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(FoePokemonIVHP.getText());
            int ivAttack = Integer.parseInt(FoePokemonIVAttack.getText());
            int ivDefense = Integer.parseInt(FoePokemonIVDefense.getText());
            int ivSpecialAttack = Integer.parseInt(FoePokemonIVSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(FoePokemonIVSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(FoePokemonIVSpeed.getText());
            int evHp = Integer.parseInt(FoePokemonEVHP.getText());
            int evAttack = Integer.parseInt(FoePokemonEVAttack.getText());
            int evDefense = Integer.parseInt(FoePokemonEVDefense.getText());
            int evSpecialAttack = Integer.parseInt(FoePokemonEVSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(FoePokemonEVSpecialDefense.getText());
            int evSpeed = Integer.parseInt(FoePokemonEVSpeed.getText());
            int attack1 = FoePokemonAttack1.getValue().attackId;
            int attack2 = FoePokemonAttack2.getValue().attackId;
            int attack3 = FoePokemonAttack3.getValue().attackId;
            int attack4 = FoePokemonAttack4.getValue().attackId;

            result = dbAPI.addPokemon(gameName, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);

            pokemon = new PokemonList(result, nickName, pokemonStatsId, FoePokemonSpecies.getValue().dexNr);
        } catch (Exception e) {
            System.out.println("Error in adding a Pokemon to the Trainer, " + e);
        }

        if (result > 0) {
            BorderShadow light = new BorderShadow(AddFoePokemonButton);
            light.playFromStart(); // Animation

            updateTrainerPokemonInSelection(pokemon);
            // Trainer
            dbAPI.updateTrainerPokemon(activeTrainerId, trainerPokemon1.pokemonId, trainerPokemon2.pokemonId,
                    trainerPokemon3.pokemonId, trainerPokemon4.pokemonId, trainerPokemon5.pokemonId, trainerPokemon6.pokemonId);

            updateTitleImg();
        } else {
            ShakeTransition anim = new ShakeTransition(AddFoePokemonButton);
            anim.playFromStart();
        }
    }

    public void editTrainerPokemon() {
        boolean result = false;
        try {
            // get Values
            int pokemonId = activeTrainerPokemon.pokemonId;
            int pokemonStatsId = FoePokemonSpecies.getValue().pokemonStatsId;
            String nickName = FoePokemonNickname.getText();
            String gender = FoePokemonGender.getValue();
            int level = Integer.parseInt(FoePokemonLevel.getText());
            String natureName = FoePokemonNature.getValue().natureName;
            int itemId = FoePokemonItem.getValue().itemId;
            int abilityId = FoePokemonAbility.getValue().abilityId;
            int friendship = Integer.parseInt(FoePokemonFriendship.getText());
            int pokeball = FoePokemonPokeball.getValue().spriteId;
            int metLocation = FoePokemonMetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(FoePokemonIVHP.getText());
            int ivAttack = Integer.parseInt(FoePokemonIVAttack.getText());
            int ivDefense = Integer.parseInt(FoePokemonIVDefense.getText());
            int ivSpecialAttack = Integer.parseInt(FoePokemonIVSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(FoePokemonIVSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(FoePokemonIVSpeed.getText());
            int evHp = Integer.parseInt(FoePokemonEVHP.getText());
            int evAttack = Integer.parseInt(FoePokemonEVAttack.getText());
            int evDefense = Integer.parseInt(FoePokemonEVDefense.getText());
            int evSpecialAttack = Integer.parseInt(FoePokemonEVSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(FoePokemonEVSpecialDefense.getText());
            int evSpeed = Integer.parseInt(FoePokemonEVSpeed.getText());
            int attack1 = FoePokemonAttack1.getValue().attackId;
            int attack2 = FoePokemonAttack2.getValue().attackId;
            int attack3 = FoePokemonAttack3.getValue().attackId;
            int attack4 = FoePokemonAttack4.getValue().attackId;

            result = dbAPI.updatePokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
            System.out.println("Error in update user Pokemon, " + e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(EditFoePokemonButton);
            light.playFromStart(); // Animation

            updateTrainerPokemonLabel();
//            updateTrainerTooltip();
        } else {
            ShakeTransition anim = new ShakeTransition(EditFoePokemonButton);
            anim.playFromStart();
        }
    }

    public void deleteTrainerPokemon() {
        boolean result = false;

        try {
            result = dbAPI.removePokemonFromUser(activeTrainerId, activeTrainerPokemon.pokemonId);
        } catch (Exception e) {
            System.out.println("Delete Trainer Pokemon didn't work. Error: " + e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(DeleteFoePokemonButton);
            light.playFromStart(); // Animation

            // deletes Pokemon, only works if no trainer has the pokemon in his party
            dbAPI.deletePokemon(activeTrainerPokemon.pokemonId);

            clickedNoFoePokemon();
            updateTitleImg();
        }  else {
            ShakeTransition anim = new ShakeTransition(DeleteFoePokemonButton);
            anim.playFromStart();
        }
    }

    // add Pokemon
    private void updateTrainerPokemonInSelection(PokemonList poke) {
        Pokemon pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
        activeTrainerPokemon = pokemon;
        setTrainerPokemonOptions(pokemon);
        setPokemonToTheTrainer(poke);
        calculateDamage();
    }

    private void setPokemonToTheTrainer(PokemonList poke) {
        switch (activeTrainerPokemonIndex) {
            case 1 -> trainerPokemon1 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            case 2 -> trainerPokemon2 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            case 3 -> trainerPokemon3 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            case 4 -> trainerPokemon4 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            case 5 -> trainerPokemon5 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            case 6 -> trainerPokemon6 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
        }
        updatePokemonSprite(activeTrainerPokemonBox, poke.pokemonStatsId);
    }

    // update Pokemon
    private void updateTrainerPokemonLabel() {
        // in Selection
        updatePokemonSprite(activeTrainerPokemonBox, FoePokemonSpecies.getValue().pokemonStatsId);
    }

    private void updateTitleImg() {
        Trainer trainer = dbAPI.getTrainerFromTrainerId(activeTrainerId);
        activeTrainerTitlePane.setGraphic(CreateTrainerTitledPane.createTitle(trainer));
    }

    // -------------------------------------------- Own Functions --------------------------------------------
    private void setupBooleans() {
        showFlatDamage = false;
        activeBattleMode = "Single";
        activeWeather = "None";
        // Badges
        badge1 = false;
        badge2 = false;
        badge3 = false;
        badge4 = false;
        badge5 = false;
        badge6 = false;
        badge7 = false;
        badge8 = false;
        // Modes
        singleBattle = true;
        doubleBattle = false;
        // Terrain
        electricTerrain = false;
        grassyTerrain = false;
        mistyTerain = false;
        psychicTerrain = false;
        // Weather
        noWeather = true;
        sun = false;
        rain = false;
        sand = false;
        hail = false;
        harshSunshine = false;
        heavyRain = false;
        strongWinds = false;
        // Gravity
        gravity = false;
        // other things - User
        userStealthRock = false;
        userSteelsurge = false;
        userVineLash = false;
        userWildfire = false;
        userCannonade = false;
        userVolcalith = false;
        userSpikes0 = true;
        userSpikes1 = false;
        userSpikes2 = false;
        userSpikes3 = false;
        userReflect = false;
        userLightScreen = false;
        userProtect = false;
        userLeechSeed = false;
        userForesight = false;
        userHelpingHand = false;
        userTailwind = false;
        userFriendGuard = false;
        userBattery = false;
        // other things - Foe
        foeStealthRock = false;
        foeSteelsurge = false;
        foeVineLash = false;
        foeWildfire = false;
        foeCannonade = false;
        foeVolcalith = false;
        foeSpikes0 = true;
        foeSpikes1 = false;
        foeSpikes2 = false;
        foeSpikes3 = false;
        foeReflect = false;
        foeLightScreen = false;
        foeProtect = false;
        foeLeechSeed = false;
        foeForesight = false;
        foeHelpingHand = false;
        foeTailwind = false;
        foeFriendGuard = false;
        foeBattery = false;
    }

    private void setTerrainCSS() {
        ElectricTerrain.getStyleClass().clear();
        GrassyTerrain.getStyleClass().clear();
        MistyTerrain.getStyleClass().clear();
        PsychicTerrain.getStyleClass().clear();

        if (electricTerrain) ElectricTerrain.getStyleClass().add("calcButtonInUsage");
        else ElectricTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (grassyTerrain) GrassyTerrain.getStyleClass().add("calcButtonInUsage");
        else GrassyTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (mistyTerain) MistyTerrain.getStyleClass().add("calcButtonInUsage");
        else MistyTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (psychicTerrain) PsychicTerrain.getStyleClass().add("calcButtonInUsage");
        else PsychicTerrain.getStyleClass().add("calcButtonsNotUsed");
    }

    private void setWeatherCss() {
        None.getStyleClass().clear();
        Sun.getStyleClass().clear();
        Rain.getStyleClass().clear();
        Sand.getStyleClass().clear();
        Hail.getStyleClass().clear();
        HarshSunshine.getStyleClass().clear();
        HeavyRain.getStyleClass().clear();
        StrongWinds.getStyleClass().clear();

        if (noWeather) None.getStyleClass().add("calcButtonInUsage");
        else None.getStyleClass().add("calcButtonsNotUsed");

        if (sun) Sun.getStyleClass().add("calcButtonInUsage");
        else Sun.getStyleClass().add("calcButtonsNotUsed");

        if (rain) Rain.getStyleClass().add("calcButtonInUsage");
        else Rain.getStyleClass().add("calcButtonsNotUsed");

        if (sand) Sand.getStyleClass().add("calcButtonInUsage");
        else Sand.getStyleClass().add("calcButtonsNotUsed");

        if (hail) Hail.getStyleClass().add("calcButtonInUsage");
        else Hail.getStyleClass().add("calcButtonsNotUsed");

        if (harshSunshine) HarshSunshine.getStyleClass().add("calcButtonInUsage");
        else HarshSunshine.getStyleClass().add("calcButtonsNotUsed");

        if (heavyRain) HeavyRain.getStyleClass().add("calcButtonInUsage");
        else HeavyRain.getStyleClass().add("calcButtonsNotUsed");

        if (strongWinds) StrongWinds.getStyleClass().add("calcButtonInUsage");
        else StrongWinds.getStyleClass().add("calcButtonsNotUsed");
    }

    private boolean switchButtonOnActiveOrNot(boolean b, Button btn) {
        b = !b;

        btn.getStyleClass().clear();
        if (b) btn.getStyleClass().add("calcButtonInUsage");
        else btn.getStyleClass().add("calcButtonsNotUsed");
        return b;
    }

    private boolean switchBadgeToActiveOrNot(boolean b, ImageView image) {
        b = !b;

        if (b) image.setOpacity(1);
        else image.setOpacity(0.3);

        return b;
    }

    private boolean setSpikeToFalse(Button btn) {
        btn.getStyleClass().clear();
        btn.getStyleClass().add("calcButtonsNotUsed");
        return false;
    }

    // selects The Badge
    private void setBadgeBoolean(int gymOrder, boolean isSet) {
        Badge badge = badgeList.get(gymOrder);

        if (badge.statBoost != null) {
            switch (badge.statBoost) {
                case "Attack" -> attackBadgeBoolean = isSet;
                case "Defense" -> defenseBadgeBoolean = isSet;
                case "Special" -> specialBadgeBoolean = isSet;
                case "Speed" -> speedBadgeBoolean = isSet;
            }
        }
    }

    // User Selects Pokemon
    private void setActiveUserPokemon(Label label, int i) {
        activeUserPokemonIndex = i;
        previousUserPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        label.getStyleClass().add("pokeballActiveBox");
        previousUserPokemonBox = label;
    }

    // Trainer Selects Pokemon
    private void setActiveTrainerPokemon(Label label, int i) {
        activeTrainerPokemonIndex = i;
        previousTrainerPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        label.getStyleClass().add("pokeballActiveBox");
        previousTrainerPokemonBox = label;
    }

    // sets userPokemonStats
    private void setUserPokemonStats(int pokemonId) {
        Pokemon pokemon = (pokemonId > 0) ? dbAPI.getPokemonFromPokemonId(pokemonId) : tempPokemon;
        // remove EventHandler otherwise they will triggered when you select Pokemon
        // get the handles to add them later again
        EventHandler<ActionEvent> userPokemonHandlerSpecies = UserPokemonSpecies.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerType1 = UserPokemonType1.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerType2 = UserPokemonType2.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerForm = UserPokemonForm.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerGender = UserPokemonGender.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerNature = UserPokemonNature.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAbility = UserPokemonAbility.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerItem = UserPokemonItem.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerStatus = UserPokemonStatus.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack1 = UserPokemonAttack1.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack2 = UserPokemonAttack2.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack3 = UserPokemonAttack3.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack4 = UserPokemonAttack4.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType1 = UserPokemonAttack1Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType2 = UserPokemonAttack2Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType3 = UserPokemonAttack3Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType4 = UserPokemonAttack4Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory1 = UserPokemonAttack1Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory2 = UserPokemonAttack2Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory3 = UserPokemonAttack3Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory4 = UserPokemonAttack4Category.getOnAction();
        // remove onAction
        UserPokemonSpecies.setOnAction(null);
        UserPokemonType1.setOnAction(null);
        UserPokemonType2.setOnAction(null);
        UserPokemonForm.setOnAction(null);
        UserPokemonGender.setOnAction(null);
        UserPokemonNature.setOnAction(null);
        UserPokemonAbility.setOnAction(null);
        UserPokemonItem.setOnAction(null);
        UserPokemonStatus.setOnAction(null);
        UserPokemonAttack1.setOnAction(null);
        UserPokemonAttack2.setOnAction(null);
        UserPokemonAttack3.setOnAction(null);
        UserPokemonAttack4.setOnAction(null);
        UserPokemonAttack1Type.setOnAction(null);
        UserPokemonAttack2Type.setOnAction(null);
        UserPokemonAttack3Type.setOnAction(null);
        UserPokemonAttack4Type.setOnAction(null);
        UserPokemonAttack1Category.setOnAction(null);
        UserPokemonAttack2Category.setOnAction(null);
        UserPokemonAttack3Category.setOnAction(null);
        UserPokemonAttack4Category.setOnAction(null);
        // get all the Data
        // pokeStats
        PokeStats pokeStats = null;
        if (pokemon.pokemonStatsId > 0) pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokemon.pokemonStatsId);
        // types
        ArrayList<String> pokemonTypes = null;
        if (pokemon.pokemonStatsId > 0) pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokemon.pokemonStatsId);
        // item
        Item item = null;
        if (pokemon.itemId > 0) item = dbAPI.getItemFromItemId(pokemon.itemId);
        // attack
        ObservableList<PokemonAttack> attackList = FXCollections.observableArrayList();
        if (pokemon.pokemonId > 0) attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
        // nature
        Nature nature = dbAPI.getNatureFromNatureName(pokemon.natureName);
        // sets Main
        UserPokemonNickname.setText(pokemon.nickname);
        if (pokeStats != null) Utilities.selectSpecies(pokeStats.nameOfPokemon, UserPokemonSpecies.getItems(), UserPokemonSpecies); else UserPokemonSpecies.setValue(null);
        if (pokemonTypes != null) selecPokemonType(pokemonTypes, UserPokemonType1, UserPokemonType2);
        else { UserPokemonType1.getSelectionModel().selectFirst(); UserPokemonType2.getSelectionModel().selectFirst(); }
        if (pokeStats != null) UserPokemonForm.setValue(pokeStats.nameOfPokemon); else UserPokemonForm.setValue("");
        UserPokemonGender.setValue(pokemon.gender);
        UserPokemonLevel.setText(Integer.toString(pokemon.level));
        UserPokemonFriendship.setText(Integer.toString(pokemon.friendship));
        // Stats
        if (pokeStats == null) pokeStats = dataSingleton.pokeStats;
        setUserBaseIvEvStats(pokemon, pokeStats);
        // Nature / Ability / Item / Status
        Utilities.selectNature(pokemon.natureName, UserPokemonNature.getItems(), UserPokemonNature);
        if (pokemon.abilityId > 0) Utilities.selectAbility(pokemon.abilityId, UserPokemonAbility.getItems(), UserPokemonAbility); else UserPokemonAbility.getSelectionModel().selectFirst();
        if (item != null) Utilities.selectHeldItem(pokemon.itemId, UserPokemonItem.getItems(), UserPokemonItem); else UserPokemonItem.getSelectionModel().selectFirst();
        UserPokemonStatus.getSelectionModel().selectFirst();
        // select Boosts
        UserPokemonAttackBoost.getSelectionModel().select(6);
        UserPokemonDefenseBoost.getSelectionModel().select(6);
        UserPokemonSpecialAttackBoost.getSelectionModel().select(6);
        UserPokemonSpecialDefenseBoost.getSelectionModel().select(6);
        UserPokemonSpeedBoost.getSelectionModel().select(6);
        // HP
        int hp = Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level);
        UserPokemonCurrentHPFlat.setText(Integer.toString(hp));
        UserPokemonMaxHp.setText("/" + hp);
        UserPokemonCurrentHPPercentage.setText("100");
        // MetLocations
        if (pokemon.metLocation > 0) Utilities.selectMetLocation(pokemon.metLocation, UserPokemonMetLocation.getItems(), UserPokemonMetLocation); else UserPokemonMetLocation.getSelectionModel().selectFirst();
        // Pokeballs
        if (pokemon.pokeball > 0) Utilities.selectPokeball(pokemon.pokeball, UserPokemonPokeball.getItems(), UserPokemonPokeball); else UserPokemonPokeball.getSelectionModel().selectFirst();
        // Moves
        setUserPokemonAttacks(attackList);
        // Calculate Total Stats / HP / Attack / Defense / SpeAttack / SpeDefense / Speed
        UserPokemonHP.setText(Integer.toString(Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level)));
        UserPokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseAttack, pokemon.ivAttack, pokemon.evAttack, pokemon.level, "Attack", attackBadge, attackBadgeBoolean)));
        UserPokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseDefense, pokemon.ivDefense, pokemon.evDefense, pokemon.level, "Defense", defenseBadge, defenseBadgeBoolean)));
        UserPokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialAttack, pokemon.ivSpecialAttack, pokemon.evSpecialAttack, pokemon.level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
        UserPokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialDefense, pokemon.ivSpecialDefense, pokemon.evSpecialDefense, pokemon.level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
        UserPokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpeed, pokemon.ivSpeed, pokemon.evSpeed, pokemon.level, "Speed", speedBadge, speedBadgeBoolean)));
        // Final Stats
        calcUserPokemonFinalAttack();
        calcUserPokemonFinalDefense();
        calcUserPokemonFinalSpecialAttack();
        calcUserPokemonFinalSpecialDefense();
        calcUserPokemonFinalSpeed();
        // options
        setUserPokemonOptions(pokemon);
        // Re-insert EventHandler
        UserPokemonSpecies.setOnAction(userPokemonHandlerSpecies);
        UserPokemonType1.setOnAction(userPokemonHandlerType1);
        UserPokemonType2.setOnAction(userPokemonHandlerType2);
        UserPokemonForm.setOnAction(userPokemonHandlerForm);
        UserPokemonGender.setOnAction(userPokemonHandlerGender);
        UserPokemonNature.setOnAction(userPokemonHandlerNature);
        UserPokemonAbility.setOnAction(userPokemonHandlerAbility);
        UserPokemonItem.setOnAction(userPokemonHandlerItem);
        UserPokemonStatus.setOnAction(userPokemonHandlerStatus);
        UserPokemonAttack1.setOnAction(userPokemonHandlerAttack1);
        UserPokemonAttack2.setOnAction(userPokemonHandlerAttack2);
        UserPokemonAttack3.setOnAction(userPokemonHandlerAttack3);
        UserPokemonAttack4.setOnAction(userPokemonHandlerAttack4);
        UserPokemonAttack1Type.setOnAction(userPokemonHandlerAttackType1);
        UserPokemonAttack2Type.setOnAction(userPokemonHandlerAttackType2);
        UserPokemonAttack3Type.setOnAction(userPokemonHandlerAttackType3);
        UserPokemonAttack4Type.setOnAction(userPokemonHandlerAttackType4);
        UserPokemonAttack1Category.setOnAction(userPokemonHandlerAttackCategory1);
        UserPokemonAttack2Category.setOnAction(userPokemonHandlerAttackCategory2);
        UserPokemonAttack3Category.setOnAction(userPokemonHandlerAttackCategory3);
        UserPokemonAttack4Category.setOnAction(userPokemonHandlerAttackCategory4);
    }

    private void setUserPokemonOptions(Pokemon pokemon) {
        if (pokemon.pokemonId > 0) {
            AddUserPokemonButton.setDisable(true);
            EditUserPokemonButton.setDisable(false);
            DeleteUserPokemonButton.setDisable(false);
        } else {
            AddUserPokemonButton.setDisable(false);
            EditUserPokemonButton.setDisable(true);
            DeleteUserPokemonButton.setDisable(true);
        }
    }

    private void calcAndSetUserPokemonStats() {
        calcUserPokemonHp();
        calcUserPokemonAttack();
        calcUserPokemonDefense();
        calcUserPokemonSpecialAttack();
        calcUserPokemonSpecialDefense();
        calcUserPokemonSpeed();
    }

    private void calcUserPokemonHp() {
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseHp = Integer.parseInt(UserPokemonBaseHP.getText());
        int ivHp = Integer.parseInt(UserPokemonIVHP.getText());
        int evHp = Integer.parseInt(UserPokemonEVHP.getText());
        int hp = Utilities.calculateHpStat(baseHp, ivHp, evHp, level);
        UserPokemonHP.setText(Integer.toString(hp));
        UserPokemonMaxHp.setText("/" + hp);
        calcUserPokemonCurrentHpBasedOnPercentage(hp);
    }

    private void calcUserPokemonPercentageBasedOnCurrentHp() {
        try {
            double currentHp = Integer.parseInt(UserPokemonCurrentHPFlat.getText()) * 1000.0;
            int maxHp= Integer.parseInt(UserPokemonHP.getText());

            double percentage = Math.floor(currentHp / maxHp) / 10.0;

            UserPokemonCurrentHPPercentage.setText(Double.toString(percentage));
        } catch (Exception ignored) {}
    }

    private void calcUserPokemonCurrentHpBasedOnPercentage(int hp) {
        try {
            double percentage = Double.parseDouble(UserPokemonCurrentHPPercentage.getText());
            int currentHp = (int) (hp * (percentage / 100.0));
            if (percentage > 0) currentHp = Math.max(1, currentHp);

            UserPokemonCurrentHPFlat.setText(Integer.toString(currentHp));
        } catch (Exception ignored) {}
    }

    private void calcUserPokemonAttack() {
        try {
            Nature n = UserPokemonNature.getValue();
            int level = Integer.parseInt(UserPokemonLevel.getText());
            int baseAttack = Integer.parseInt(UserPokemonBaseAttack.getText());
            int ivAttack = Integer.parseInt(UserPokemonIVAttack.getText());
            int evAttack = Integer.parseInt(UserPokemonEVAttack.getText());
            int attack = Utilities.calculateStatWithBadge(n, baseAttack, ivAttack, evAttack, level, "Attack", attackBadge, attackBadgeBoolean);
            UserPokemonAttack.setText(Integer.toString(attack));
            calcUserPokemonFinalAttack();
        } catch (Exception ignored) {}
    }

    private void calcUserPokemonDefense() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseDefense = Integer.parseInt(UserPokemonBaseDefense.getText());
        int ivDefense = Integer.parseInt(UserPokemonIVDefense.getText());
        int evDefense = Integer.parseInt(UserPokemonEVDefense.getText());
        UserPokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseDefense, ivDefense, evDefense, level, "Defense", defenseBadge, defenseBadgeBoolean)));
        calcUserPokemonFinalDefense();
    }

    private void calcUserPokemonSpecialAttack() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpecialAttack = Integer.parseInt(UserPokemonBaseSpecialAttack.getText());
        int ivSpecialAttack = Integer.parseInt(UserPokemonIVSpecialAttack.getText());
        int evSpecialAttack = Integer.parseInt(UserPokemonEVSpecialAttack.getText());
        UserPokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialAttack, ivSpecialAttack, evSpecialAttack, level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
        calcUserPokemonFinalSpecialAttack();
    }

    private void calcUserPokemonSpecialDefense() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpecialDefense = Integer.parseInt(UserPokemonBaseSpecialDefense.getText());
        int ivSpecialDefense = Integer.parseInt(UserPokemonIVSpecialDefense.getText());
        int evSpecialDefense = Integer.parseInt(UserPokemonEVSpecialDefense.getText());
        UserPokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialDefense, ivSpecialDefense, evSpecialDefense, level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
        calcUserPokemonFinalSpecialDefense();
    }

    private void calcUserPokemonSpeed() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpeed = Integer.parseInt(UserPokemonBaseSpeed.getText());
        int ivSpeed = Integer.parseInt(UserPokemonIVSpeed.getText());
        int evSpeed = Integer.parseInt(UserPokemonEVSpeed.getText());
        UserPokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpeed, ivSpeed, evSpeed, level, "Speed", speedBadge, speedBadgeBoolean)));
        calcUserPokemonFinalSpeed();
    }

    private void calcUserPokemonFinalAttack() {
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonAttack.getText()), UserPokemonAttackBoost.getValue());
        UserPokemonAttackFinal.setText(Integer.toString(attackStat));
    }

    private void calcUserPokemonFinalDefense() {
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonDefense.getText()), UserPokemonDefenseBoost.getValue());
        UserPokemonDefenseFinal.setText(Integer.toString(attackStat));
    }

    private void calcUserPokemonFinalSpecialAttack() {
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialAttack.getText()), UserPokemonSpecialAttackBoost.getValue());
        UserPokemonSpecialAttackFinal.setText(Integer.toString(attackStat));
    }

    private void calcUserPokemonFinalSpecialDefense() {
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialDefense.getText()), UserPokemonSpecialDefenseBoost.getValue());
        UserPokemonSpecialDefenseFinal.setText(Integer.toString(attackStat));
    }

    private void calcUserPokemonFinalSpeed() {
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpeed.getText()), UserPokemonSpeedBoost.getValue());
        UserPokemonSpeedFinal.setText(Integer.toString(attackStat));
    }

    private void selecPokemonType(ArrayList<String> pokemonTypes, ComboBox<String> type1, ComboBox<String> type2) {
        for (int i = 0; i < pokemonTypes.size(); i++) {
            if (i == 0) type1.setValue(pokemonTypes.get(0));
            else type2.setValue(pokemonTypes.get(1));
        }

        for (int i = pokemonTypes.size(); i < 2; i++) {
            if (i == 0) type1.getSelectionModel().selectFirst();
            else type2.getSelectionModel().selectFirst();
        }
    }

    private void setUserBaseIvEvStats(Pokemon pokemon, PokeStats pokeStats) {
        setUserPokemonBaseStats(pokeStats);
        setUserPokemonIvStats(pokemon);
        setUserPokemonEvStats(pokemon);
    }

    private void setUserPokemonBaseStats(PokeStats pokeStats) {
        UserPokemonBaseHP.setText(Integer.toString(pokeStats.baseHp));
        UserPokemonBaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        UserPokemonBaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        UserPokemonBaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        UserPokemonBaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        UserPokemonBaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void setUserPokemonIvStats(Pokemon pokemon) {
        UserPokemonIVHP.setText(Integer.toString(pokemon.ivHp));
        UserPokemonIVAttack.setText(Integer.toString(pokemon.ivAttack));
        UserPokemonIVDefense.setText(Integer.toString(pokemon.ivDefense));
        UserPokemonIVSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        UserPokemonIVSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        UserPokemonIVSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    private void setUserPokemonEvStats(Pokemon pokemon) {
        UserPokemonEVHP.setText(Integer.toString(pokemon.evHp));
        UserPokemonEVAttack.setText(Integer.toString(pokemon.evAttack));
        UserPokemonEVDefense.setText(Integer.toString(pokemon.evDefense));
        UserPokemonEVSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        UserPokemonEVSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        UserPokemonEVSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    private void setUserPokemonAttacks(ObservableList<PokemonAttack> at) {
        for (int i = 0; i < at.size(); i++) {
            Attack attack = dbAPI.getAttackFromAttackId(at.get(i).attackId);
            switch (i) {
                case 0 -> {
                    Utilities.selectMove(attack.attackId, UserPokemonAttack1.getItems(), UserPokemonAttack1);
                    userMove1Attack.setText(attack.attackName);
                    setUserAttackValues(1, attack);
                    if (attack.attackId > 0) userMove1Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 1 -> {
                    Utilities.selectMove(attack.attackId, UserPokemonAttack2.getItems(), UserPokemonAttack2);
                    userMove2Attack.setText(attack.attackName);
                    setUserAttackValues(2, attack);
                    if (attack.attackId > 0) userMove2Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 2 -> {
                    Utilities.selectMove(attack.attackId, UserPokemonAttack3.getItems(), UserPokemonAttack3);
                    userMove3Attack.setText(attack.attackName);
                    setUserAttackValues(3, attack);
                    if (attack.attackId > 0) userMove3Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 3 -> {
                    Utilities.selectMove(attack.attackId, UserPokemonAttack4.getItems(), UserPokemonAttack4);
                    userMove4Attack.setText(attack.attackName);
                    setUserAttackValues(4, attack);
                    if (attack.attackId > 0) userMove4Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
            }
        }

        // set the rest of the attack to null
        for (int i = at.size(); i < 4; i++) {
            switch (i) {
                case 0 -> {
                    UserPokemonAttack1.getSelectionModel().selectFirst();
                    userMove1Attack.setText("No Move");
                    setUserAttackValues(1, UserPokemonAttack1.getItems().get(0));
                }
                case 1 -> {
                    UserPokemonAttack2.getSelectionModel().selectFirst();
                    userMove2Attack.setText("No Move");
                    setUserAttackValues(2, UserPokemonAttack2.getItems().get(0));
                }
                case 2 -> {
                    UserPokemonAttack3.getSelectionModel().selectFirst();
                    userMove3Attack.setText("No Move");
                    setUserAttackValues(3, UserPokemonAttack3.getItems().get(0));
                }
                case 3 -> {
                    UserPokemonAttack4.getSelectionModel().selectFirst();
                    userMove4Attack.setText("No Move");
                    setUserAttackValues(4, UserPokemonAttack4.getItems().get(0));
                }
            }
        }
    }

    private void setUserAttackValues(int index, Attack attack) {
        String hits = (attack.attackId > 0) ? "1" : "";

        switch (index) {
            case 1 -> {
                EventHandler<ActionEvent> damage = UserPokemonAttack1Damage.getOnAction();
                EventHandler<ActionEvent> type = UserPokemonAttack1Type.getOnAction();
                EventHandler<ActionEvent> Category = UserPokemonAttack1Category.getOnAction();
                EventHandler<ActionEvent> hit = UserPokemonAttack1Hits.getOnAction();

                UserPokemonAttack1Damage.setOnAction(null);
                UserPokemonAttack1Type.setOnAction(null);
                UserPokemonAttack1Category.setOnAction(null);
                UserPokemonAttack1Hits.setOnAction(null);

                UserPokemonAttack1Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack1Type.setValue(attack.typeName);
                UserPokemonAttack1Category.setValue(attack.category);
                UserPokemonAttack1Hits.setText(hits);

                UserPokemonAttack1Damage.setOnAction(damage);
                UserPokemonAttack1Type.setOnAction(type);
                UserPokemonAttack1Category.setOnAction(Category);
                UserPokemonAttack1Hits.setOnAction(hit);
            }
            case 2 -> {
                EventHandler<ActionEvent> damage = UserPokemonAttack2Damage.getOnAction();
                EventHandler<ActionEvent> type = UserPokemonAttack2Type.getOnAction();
                EventHandler<ActionEvent> Category = UserPokemonAttack2Category.getOnAction();
                EventHandler<ActionEvent> hit = UserPokemonAttack2Hits.getOnAction();

                UserPokemonAttack2Damage.setOnAction(null);
                UserPokemonAttack2Type.setOnAction(null);
                UserPokemonAttack2Category.setOnAction(null);
                UserPokemonAttack2Hits.setOnAction(null);

                UserPokemonAttack2Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack2Type.setValue(attack.typeName);
                UserPokemonAttack2Category.setValue(attack.category);
                UserPokemonAttack2Hits.setText(hits);

                UserPokemonAttack2Damage.setOnAction(damage);
                UserPokemonAttack2Type.setOnAction(type);
                UserPokemonAttack2Category.setOnAction(Category);
                UserPokemonAttack2Hits.setOnAction(hit);
            }
            case 3 -> {
                EventHandler<ActionEvent> damage = UserPokemonAttack3Damage.getOnAction();
                EventHandler<ActionEvent> type = UserPokemonAttack3Type.getOnAction();
                EventHandler<ActionEvent> Category = UserPokemonAttack3Category.getOnAction();
                EventHandler<ActionEvent> hit = UserPokemonAttack3Hits.getOnAction();

                UserPokemonAttack3Damage.setOnAction(null);
                UserPokemonAttack3Type.setOnAction(null);
                UserPokemonAttack3Category.setOnAction(null);
                UserPokemonAttack3Hits.setOnAction(null);

                UserPokemonAttack3Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack3Type.setValue(attack.typeName);
                UserPokemonAttack3Category.setValue(attack.category);
                UserPokemonAttack3Hits.setText(hits);

                UserPokemonAttack3Damage.setOnAction(damage);
                UserPokemonAttack3Type.setOnAction(type);
                UserPokemonAttack3Category.setOnAction(Category);
                UserPokemonAttack3Hits.setOnAction(hit);
            }
            case 4 -> {
                EventHandler<ActionEvent> damage = UserPokemonAttack4Damage.getOnAction();
                EventHandler<ActionEvent> type = UserPokemonAttack4Type.getOnAction();
                EventHandler<ActionEvent> Category = UserPokemonAttack4Category.getOnAction();
                EventHandler<ActionEvent> hit = UserPokemonAttack4Hits.getOnAction();

                UserPokemonAttack4Damage.setOnAction(null);
                UserPokemonAttack4Type.setOnAction(null);
                UserPokemonAttack4Category.setOnAction(null);
                UserPokemonAttack4Hits.setOnAction(null);

                UserPokemonAttack4Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack4Type.setValue(attack.typeName);
                UserPokemonAttack4Category.setValue(attack.category);
                UserPokemonAttack4Hits.setText(hits);

                UserPokemonAttack4Damage.setOnAction(damage);
                UserPokemonAttack4Type.setOnAction(type);
                UserPokemonAttack4Hits.setOnAction(Category);
                UserPokemonAttack4Hits.setOnAction(hit);
            }
        }
    }

    private void setTrainerAttackValues(int index, Attack attack) {
        String hits = (attack.attackId > 0) ? "1" : "";

        switch (index) {
            case 1 -> {
                EventHandler<ActionEvent> damage = FoePokemonAttack1Damage.getOnAction();
                EventHandler<ActionEvent> type = FoePokemonAttack1Type.getOnAction();
                EventHandler<ActionEvent> Category = FoePokemonAttack1Category.getOnAction();
                EventHandler<ActionEvent> hit = FoePokemonAttack1Hits.getOnAction();

                FoePokemonAttack1Damage.setOnAction(null);
                FoePokemonAttack1Type.setOnAction(null);
                FoePokemonAttack1Category.setOnAction(null);
                FoePokemonAttack1Hits.setOnAction(null);

                FoePokemonAttack1Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack1Type.setValue(attack.typeName);
                FoePokemonAttack1Category.setValue(attack.category);
                FoePokemonAttack1Hits.setText(hits);

                FoePokemonAttack1Damage.setOnAction(damage);
                FoePokemonAttack1Type.setOnAction(type);
                FoePokemonAttack1Category.setOnAction(Category);
                FoePokemonAttack1Hits.setOnAction(hit);
            }
            case 2 -> {
                EventHandler<ActionEvent> damage = FoePokemonAttack2Damage.getOnAction();
                EventHandler<ActionEvent> type = FoePokemonAttack2Type.getOnAction();
                EventHandler<ActionEvent> Category = FoePokemonAttack2Category.getOnAction();
                EventHandler<ActionEvent> hit = FoePokemonAttack2Hits.getOnAction();

                FoePokemonAttack2Damage.setOnAction(null);
                FoePokemonAttack2Type.setOnAction(null);
                FoePokemonAttack2Category.setOnAction(null);
                FoePokemonAttack2Hits.setOnAction(null);

                FoePokemonAttack2Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack2Type.setValue(attack.typeName);
                FoePokemonAttack2Category.setValue(attack.category);
                FoePokemonAttack2Hits.setText(hits);

                FoePokemonAttack2Damage.setOnAction(damage);
                FoePokemonAttack2Type.setOnAction(type);
                FoePokemonAttack2Category.setOnAction(Category);
                FoePokemonAttack2Hits.setOnAction(hit);
            }
            case 3 -> {
                EventHandler<ActionEvent> damage = FoePokemonAttack3Damage.getOnAction();
                EventHandler<ActionEvent> type = FoePokemonAttack3Type.getOnAction();
                EventHandler<ActionEvent> Category = FoePokemonAttack3Category.getOnAction();
                EventHandler<ActionEvent> hit = FoePokemonAttack3Hits.getOnAction();

                FoePokemonAttack3Damage.setOnAction(null);
                FoePokemonAttack3Type.setOnAction(null);
                FoePokemonAttack3Category.setOnAction(null);
                FoePokemonAttack3Hits.setOnAction(null);

                FoePokemonAttack3Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack3Type.setValue(attack.typeName);
                FoePokemonAttack3Category.setValue(attack.category);
                FoePokemonAttack3Hits.setText(hits);

                FoePokemonAttack3Damage.setOnAction(damage);
                FoePokemonAttack3Type.setOnAction(type);
                FoePokemonAttack3Category.setOnAction(Category);
                FoePokemonAttack3Hits.setOnAction(hit);
            }
            case 4 -> {
                EventHandler<ActionEvent> damage = FoePokemonAttack4Damage.getOnAction();
                EventHandler<ActionEvent> type = FoePokemonAttack4Type.getOnAction();
                EventHandler<ActionEvent> Category = FoePokemonAttack4Category.getOnAction();
                EventHandler<ActionEvent> hit = FoePokemonAttack4Hits.getOnAction();

                FoePokemonAttack4Damage.setOnAction(null);
                FoePokemonAttack4Type.setOnAction(null);
                FoePokemonAttack4Category.setOnAction(null);
                FoePokemonAttack4Hits.setOnAction(null);

                FoePokemonAttack4Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack4Type.setValue(attack.typeName);
                FoePokemonAttack4Category.setValue(attack.category);
                FoePokemonAttack4Hits.setText(hits);

                FoePokemonAttack4Damage.setOnAction(damage);
                FoePokemonAttack4Type.setOnAction(type);
                FoePokemonAttack4Category.setOnAction(Category);
                FoePokemonAttack4Hits.setOnAction(hit);
            }
        }
    }

    // set trainerPokemonStats
    private void setTrainerPokemonStats(int pokemonId) {
        Pokemon pokemon = (pokemonId > 0) ? dbAPI.getPokemonFromPokemonId(pokemonId): tempPokemon;
        // remove EventHandler otherwise they will triggered when you select Pokemon
        // get the handles to add them later again
        EventHandler<ActionEvent> trainerPokemonHandlerSpecies = FoePokemonSpecies.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerType1 = FoePokemonType1.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerType2 = FoePokemonType2.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerForm = FoePokemonForm.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerGender = FoePokemonGender.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerNature = FoePokemonNature.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAbility = FoePokemonAbility.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerItem = FoePokemonItem.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerStatus = FoePokemonStatus.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack1 = FoePokemonAttack1.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack2 = FoePokemonAttack2.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack3 = FoePokemonAttack3.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack4 = FoePokemonAttack4.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType1 = FoePokemonAttack1Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType2 = FoePokemonAttack2Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType3 = FoePokemonAttack3Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType4 = FoePokemonAttack4Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory1 = FoePokemonAttack1Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory2 = FoePokemonAttack2Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory3 = FoePokemonAttack3Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory4 = FoePokemonAttack4Category.getOnAction();
        // remove onAction
        FoePokemonSpecies.setOnAction(null);
        FoePokemonType1.setOnAction(null);
        FoePokemonType2.setOnAction(null);
        FoePokemonForm.setOnAction(null);
        FoePokemonGender.setOnAction(null);
        FoePokemonNature.setOnAction(null);
        FoePokemonAbility.setOnAction(null);
        FoePokemonItem.setOnAction(null);
        FoePokemonStatus.setOnAction(null);
        FoePokemonAttack1.setOnAction(null);
        FoePokemonAttack2.setOnAction(null);
        FoePokemonAttack3.setOnAction(null);
        FoePokemonAttack4.setOnAction(null);
        FoePokemonAttack1Type.setOnAction(null);
        FoePokemonAttack2Type.setOnAction(null);
        FoePokemonAttack3Type.setOnAction(null);
        FoePokemonAttack4Type.setOnAction(null);
        FoePokemonAttack1Category.setOnAction(null);
        FoePokemonAttack2Category.setOnAction(null);
        FoePokemonAttack3Category.setOnAction(null);
        FoePokemonAttack4Category.setOnAction(null);
        // get all the Data
        // pokeStats
        PokeStats pokeStats = null;
        if (pokemon.pokemonStatsId > 0) pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokemon.pokemonStatsId);
        // types
        ArrayList<String> pokemonTypes = null;
        if (pokemon.pokemonStatsId > 0) pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokemon.pokemonStatsId);
        // item
        Item item = null;
        if (pokemon.itemId > 0) item = dbAPI.getItemFromItemId(pokemon.itemId);
        // attack
        ObservableList<PokemonAttack> attackList = FXCollections.observableArrayList();
        if (pokemon.pokemonId > 0) attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
        // nature-
        Nature nature = dbAPI.getNatureFromNatureName(pokemon.natureName);
        // sets Main
        FoePokemonNickname.setText(pokemon.nickname);
        if (pokeStats != null) Utilities.selectSpecies(pokeStats.nameOfPokemon, FoePokemonSpecies.getItems(), FoePokemonSpecies); else FoePokemonSpecies.setValue(null);
        if (pokemonTypes != null) selecPokemonType(pokemonTypes, FoePokemonType1, FoePokemonType2);
        else { FoePokemonType1.getSelectionModel().selectFirst(); FoePokemonType2.getSelectionModel().selectFirst(); }
        if (pokeStats != null) FoePokemonForm.setValue(pokeStats.nameOfPokemon); else FoePokemonForm.setValue("");
        FoePokemonGender.setValue(pokemon.gender);
        FoePokemonLevel.setText(Integer.toString(pokemon.level));
        FoePokemonFriendship.setText(Integer.toString(pokemon.friendship));
        // Stats
        if (pokeStats == null) pokeStats = dataSingleton.pokeStats;
        setFoeBaseIvEvStats(pokemon, pokeStats);
        // Nature / Ability / Item / Status
        Utilities.selectNature(pokemon.natureName, FoePokemonNature.getItems(), FoePokemonNature);
        if (pokemon.abilityId > 0) Utilities.selectAbility(pokemon.abilityId, FoePokemonAbility.getItems(), FoePokemonAbility);
        if (item != null) Utilities.selectHeldItem(pokemon.itemId, FoePokemonItem.getItems(), FoePokemonItem); else FoePokemonItem.getSelectionModel().selectFirst();
        FoePokemonStatus.getSelectionModel().selectFirst();
        // select Boosts
        FoePokemonAttackBoost.getSelectionModel().select(6);
        FoePokemonDefenseBoost.getSelectionModel().select(6);
        FoePokemonSpecialAttackBoost.getSelectionModel().select(6);
        FoePokemonSpecialDefenseBoost.getSelectionModel().select(6);
        FoePokemonSpeedBoost.getSelectionModel().select(6);
        // HP
        int hp = Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level);
        FoePokemonCurrentHPFlat.setText(Integer.toString(hp));
        FoePokemonMaxHp.setText("/" + hp);
        FoePokemonCurrentHPPercentage.setText("100");
        // MetLocations
        if (pokemon.metLocation > 0) Utilities.selectMetLocation(pokemon.metLocation, FoePokemonMetLocation.getItems(), FoePokemonMetLocation); else FoePokemonMetLocation.getSelectionModel().selectFirst();
        // Pokeballs
        if (pokemon.pokeball > 0) Utilities.selectPokeball(pokemon.pokeball, FoePokemonPokeball.getItems(), FoePokemonPokeball); else FoePokemonPokeball.getSelectionModel().selectFirst();
        // Moves
        setFoePokemonAttacks(attackList);
        // Calculate Total Stats / HP / Attack / Defense / SpeAttack / SpeDefense / Speed
        FoePokemonHP.setText(Integer.toString(Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level)));
        FoePokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseAttack, pokemon.ivAttack, pokemon.evAttack, pokemon.level, "Attack", attackBadge, false)));
        FoePokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseDefense, pokemon.ivDefense, pokemon.evDefense, pokemon.level, "Defense", defenseBadge, false)));
        FoePokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialAttack, pokemon.ivSpecialAttack, pokemon.evSpecialAttack, pokemon.level, "SpecialAttack", specialBadge, false)));
        FoePokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialDefense, pokemon.ivSpecialDefense, pokemon.evSpecialDefense, pokemon.level, "SpecialDefense", specialBadge, false)));
        FoePokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpeed, pokemon.ivSpeed, pokemon.evSpeed, pokemon.level, "Speed", speedBadge, false)));
        // Final Stats
        calcTrainerPokemonFinalAttack();
        calcTrainerPokemonFinalDefense();
        calcTrainerPokemonFinalSpecialAttack();
        calcTrainerPokemonFinalSpecialDefense();
        calcTrainerPokemonFinalSpeed();
        // options
        setTrainerPokemonOptions(pokemon);
        // Re-insert EventHandler
        FoePokemonSpecies.setOnAction(trainerPokemonHandlerSpecies);
        FoePokemonType1.setOnAction(trainerPokemonHandlerType1);
        FoePokemonType2.setOnAction(trainerPokemonHandlerType2);
        FoePokemonForm.setOnAction(trainerPokemonHandlerForm);
        FoePokemonGender.setOnAction(trainerPokemonHandlerGender);
        FoePokemonNature.setOnAction(trainerPokemonHandlerNature);
        FoePokemonAbility.setOnAction(trainerPokemonHandlerAbility);
        FoePokemonItem.setOnAction(trainerPokemonHandlerItem);
        FoePokemonStatus.setOnAction(trainerPokemonHandlerStatus);
        FoePokemonAttack1.setOnAction(trainerPokemonHandlerAttack1);
        FoePokemonAttack2.setOnAction(trainerPokemonHandlerAttack2);
        FoePokemonAttack3.setOnAction(trainerPokemonHandlerAttack3);
        FoePokemonAttack4.setOnAction(trainerPokemonHandlerAttack4);
        FoePokemonAttack1Type.setOnAction(trainerPokemonHandlerAttackType1);
        FoePokemonAttack2Type.setOnAction(trainerPokemonHandlerAttackType2);
        FoePokemonAttack3Type.setOnAction(trainerPokemonHandlerAttackType3);
        FoePokemonAttack4Type.setOnAction(trainerPokemonHandlerAttackType4);
        FoePokemonAttack1Category.setOnAction(trainerPokemonHandlerAttackCategory1);
        FoePokemonAttack2Category.setOnAction(trainerPokemonHandlerAttackCategory2);
        FoePokemonAttack3Category.setOnAction(trainerPokemonHandlerAttackCategory3);
        FoePokemonAttack4Category.setOnAction(trainerPokemonHandlerAttackCategory4);
    }

    private void setTrainerPokemonOptions(Pokemon pokemon) {
        if (pokemon.pokemonId > 0) {
            AddFoePokemonButton.setDisable(true);
            EditFoePokemonButton.setDisable(false);
            DeleteFoePokemonButton.setDisable(false);
        } else {
            AddFoePokemonButton.setDisable(false);
            EditFoePokemonButton.setDisable(true);
            DeleteFoePokemonButton.setDisable(true);
        }
    }

    private void calcAndSetTrainerPokemonStats() {
        calcTrainerPokemonHp();
        calcTrainerPokemonAttack();
        calcTrainerPokemonDefense();
        calcTrainerPokemonSpecialAttack();
        calcTrainerPokemonSpecialDefense();
        calcTrainerPokemonSpeed();
    }

    private void calcTrainerPokemonHp() {
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseHp = Integer.parseInt(FoePokemonBaseHP.getText());
        int ivHp = Integer.parseInt(FoePokemonIVHP.getText());
        int evHp = Integer.parseInt(FoePokemonEVHP.getText());
        int hp = Utilities.calculateHpStat(baseHp, ivHp, evHp, level);
        FoePokemonHP.setText(Integer.toString(hp));
        FoePokemonMaxHp.setText("/" + hp);
        calcTrainerPokemonCurrentHpBasedOnPercentage(hp);
    }

    private void calcTrainerPokemonAttack() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseAttack = Integer.parseInt(FoePokemonBaseAttack.getText());
        int ivAttack = Integer.parseInt(FoePokemonIVAttack.getText());
        int evAttack = Integer.parseInt(FoePokemonEVAttack.getText());
        FoePokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseAttack, ivAttack, evAttack, level, "Attack", attackBadge, attackBadgeBoolean)));
        calcTrainerPokemonFinalAttack();
    }

    private void calcTrainerPokemonDefense() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseDefense = Integer.parseInt(FoePokemonBaseDefense.getText());
        int ivDefense = Integer.parseInt(FoePokemonIVDefense.getText());
        int evDefense = Integer.parseInt(FoePokemonEVDefense.getText());
        FoePokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseDefense, ivDefense, evDefense, level, "Defense", defenseBadge, defenseBadgeBoolean)));
        calcTrainerPokemonFinalDefense();
    }

    private void calcTrainerPokemonSpecialAttack() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpecialAttack = Integer.parseInt(FoePokemonBaseSpecialAttack.getText());
        int ivSpecialAttack = Integer.parseInt(FoePokemonIVSpecialAttack.getText());
        int evSpecialAttack = Integer.parseInt(FoePokemonEVSpecialAttack.getText());
        FoePokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialAttack, ivSpecialAttack, evSpecialAttack, level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
        calcTrainerPokemonFinalSpecialAttack();
    }

    private void calcTrainerPokemonSpecialDefense() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpecialDefense = Integer.parseInt(FoePokemonBaseSpecialDefense.getText());
        int ivSpecialDefense = Integer.parseInt(FoePokemonIVSpecialDefense.getText());
        int evSpecialDefense = Integer.parseInt(FoePokemonEVSpecialDefense.getText());
        FoePokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialDefense, ivSpecialDefense, evSpecialDefense, level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
        calcTrainerPokemonFinalSpecialDefense();
    }

    private void calcTrainerPokemonSpeed() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpeed = Integer.parseInt(FoePokemonBaseSpeed.getText());
        int ivSpeed = Integer.parseInt(FoePokemonIVSpeed.getText());
        int evSpeed = Integer.parseInt(FoePokemonEVSpeed.getText());
        FoePokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpeed, ivSpeed, evSpeed, level, "Speed", speedBadge, speedBadgeBoolean)));
        calcTrainerPokemonFinalSpeed();
    }

    private void calcTrainerPokemonFinalAttack() {
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonAttack.getText()), FoePokemonAttackBoost.getValue());
        FoePokemonAttackFinal.setText(Integer.toString(attackStat));
    }

    private void calcTrainerPokemonFinalDefense() {
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonDefense.getText()), FoePokemonDefenseBoost.getValue());
        FoePokemonDefenseFinal.setText(Integer.toString(attackStat));
    }

    private void calcTrainerPokemonFinalSpecialAttack() {
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialAttack.getText()), FoePokemonSpecialAttackBoost.getValue());
        FoePokemonSpecialAttackFinal.setText(Integer.toString(attackStat));
    }

    private void calcTrainerPokemonFinalSpecialDefense() {
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialDefense.getText()), FoePokemonSpecialDefenseBoost.getValue());
        FoePokemonSpecialDefenseFinal.setText(Integer.toString(attackStat));
    }

    private void calcTrainerPokemonFinalSpeed() {
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpeed.getText()), FoePokemonSpeedBoost.getValue());
        FoePokemonSpeedFinal.setText(Integer.toString(attackStat));
    }

    private void calcTrainerPokemonPercentageBasedOnCurrentHp() {
        try {
            double currentHp = Integer.parseInt(FoePokemonCurrentHPFlat.getText()) * 1000.0;
            int maxHp= Integer.parseInt(FoePokemonHP.getText());

            double percentage = Math.floor(currentHp / maxHp) / 10.0;

            FoePokemonCurrentHPPercentage.setText(Double.toString(percentage));
        } catch (Exception ignored) {}
    }

    private void calcTrainerPokemonCurrentHpBasedOnPercentage(int hp) {
        try {
            double percentage = Double.parseDouble(FoePokemonCurrentHPPercentage.getText());
            int currentHp = (int) (hp * (percentage / 100.0));
            if (percentage > 0) currentHp = Math.max(1, currentHp);

            FoePokemonCurrentHPFlat.setText(Integer.toString(currentHp));
        } catch (Exception ignored) {}
    }

    private void setFoeBaseIvEvStats(Pokemon pokemon, PokeStats pokeStats) {
        setFoePokemonBaseStats(pokeStats);
        setFoePokemonIvStats(pokemon);
        setFoePokemonEvStats(pokemon);
    }

    private void setFoePokemonBaseStats(PokeStats pokeStats) {
        FoePokemonBaseHP.setText(Integer.toString(pokeStats.baseHp));
        FoePokemonBaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        FoePokemonBaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        FoePokemonBaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        FoePokemonBaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        FoePokemonBaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void setFoePokemonIvStats(Pokemon pokemon) {
        FoePokemonIVHP.setText(Integer.toString(pokemon.ivHp));
        FoePokemonIVAttack.setText(Integer.toString(pokemon.ivAttack));
        FoePokemonIVDefense.setText(Integer.toString(pokemon.ivDefense));
        FoePokemonIVSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        FoePokemonIVSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        FoePokemonIVSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    private void setFoePokemonEvStats(Pokemon pokemon) {
        FoePokemonEVHP.setText(Integer.toString(pokemon.evHp));
        FoePokemonEVAttack.setText(Integer.toString(pokemon.evAttack));
        FoePokemonEVDefense.setText(Integer.toString(pokemon.evDefense));
        FoePokemonEVSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        FoePokemonEVSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        FoePokemonEVSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    private void setFoePokemonAttacks(ObservableList<PokemonAttack> at) {
        for (int i = 0; i < at.size(); i++) {
            Attack attack = dbAPI.getAttackFromAttackId(at.get(i).attackId);
            switch (i) {
                case 0 -> {
                    Utilities.selectMove(attack.attackId, FoePokemonAttack1.getItems(), FoePokemonAttack1);
                    foeMove1Attack.setText(attack.attackName);
                    setTrainerAttackValues(1, attack);
                }
                case 1 -> {
                    Utilities.selectMove(attack.attackId, FoePokemonAttack2.getItems(), FoePokemonAttack2);
                    foeMove2Attack.setText(attack.attackName);
                    setTrainerAttackValues(2, attack);
                }
                case 2 -> {
                    Utilities.selectMove(attack.attackId, FoePokemonAttack3.getItems(), FoePokemonAttack3);
                    foeMove3Attack.setText(attack.attackName);
                    setTrainerAttackValues(3, attack);
                }
                case 3 -> {
                    Utilities.selectMove(attack.attackId, FoePokemonAttack4.getItems(), FoePokemonAttack4);
                    foeMove4Attack.setText(attack.attackName);
                    setTrainerAttackValues(4, attack);
                }
            }
        }

        // set the rest of the attack to null
        for (int i = at.size(); i < 4; i++) {
            switch (i) {
                case 0 -> {
                    FoePokemonAttack1.getSelectionModel().selectFirst();
                    foeMove1Attack.setText("No Move");
                    setTrainerAttackValues(1, FoePokemonAttack1.getItems().get(0));
                }
                case 1 -> {
                    FoePokemonAttack2.getSelectionModel().selectFirst();
                    foeMove2Attack.setText("No Move");
                    setTrainerAttackValues(2, FoePokemonAttack2.getItems().get(0));
                }
                case 2 -> {
                    FoePokemonAttack3.getSelectionModel().selectFirst();
                    foeMove3Attack.setText("No Move");
                    setTrainerAttackValues(3, FoePokemonAttack3.getItems().get(0));
                }
                case 3 -> {
                    FoePokemonAttack4.getSelectionModel().selectFirst();
                    foeMove4Attack.setText("No Move");
                    setTrainerAttackValues(4, FoePokemonAttack4.getItems().get(0));
                }
            }
        }
    }

    // load Dynamic Content
    private void loadContent() {
        dbAPI = new API.Database();
        data = Data.dataSingleton.getInstance();
        showThatTheFirstSlotForUserPokemonIsSelected();
        loadGlobalData(); // Species / Types / Gender / Nature / Ability / Item / Status / Attacks
        loadUserPokemons();
        loadTrainer();
        previousTrainerPokemonBox = foePokemon1;
        activeUserPokemon = tempPokemon;
        activeTrainerPokemon = tempPokemon;
        selectTrainer(0);
    }

    private void loadGlobalData() {
        try {
            // Badges
            badgeList = dbAPI.getAllBadgesFromGame(data.getGameName());
            attackBadge = findBadgeViaStatBoost(badgeList, "Attack");
            attackBadgeBoolean = attackBadge.ownBadge;
            defenseBadge = findBadgeViaStatBoost(badgeList, "Defense");
            defenseBadgeBoolean = defenseBadge.ownBadge;
            specialBadge = findBadgeViaStatBoost(badgeList, "Special");
            specialBadgeBoolean = specialBadge.ownBadge;
            speedBadge = findBadgeViaStatBoost(badgeList, "Speed");
            speedBadgeBoolean = speedBadge.ownBadge;
            setBadgeSprites();
            // Species
            ObservableList<PokeStatsList> speciesList = dbAPI.getPokeStatsListFromSpecificGame(data.getGameName());
            UserPokemonSpecies.setItems(FXCollections.observableArrayList(speciesList));
            FoePokemonSpecies.setItems(FXCollections.observableArrayList(speciesList));
            // Types
            ObservableList<String> types = FXCollections.observableArrayList();
            types.add("");
            types.addAll(dbAPI.getAllTypeFromSpecificGame(data.getGameName()));
            UserPokemonType1.setItems(FXCollections.observableArrayList(types));
            UserPokemonType2.setItems(FXCollections.observableArrayList(types));
            FoePokemonType1.setItems(FXCollections.observableArrayList(types));
            FoePokemonType2.setItems(FXCollections.observableArrayList(types));
            // Gender
            ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
            UserPokemonGender.setItems(FXCollections.observableArrayList(genders));
            FoePokemonGender.setItems(FXCollections.observableArrayList(genders));
            // Boosts
            ObservableList<String> boosts = FXCollections.observableArrayList("+6", "+5", "+4", "+3", "+2", "+1", "--", "-1", "-2", "-3", "-4", "-5", "-6");
            UserPokemonAttackBoost.setItems(boosts);
            UserPokemonDefenseBoost.setItems(boosts);
            UserPokemonSpecialAttackBoost.setItems(boosts);
            UserPokemonSpecialDefenseBoost.setItems(boosts);
            UserPokemonSpeedBoost.setItems(boosts);
            FoePokemonAttackBoost.setItems(boosts);
            FoePokemonDefenseBoost.setItems(boosts);
            FoePokemonSpecialAttackBoost.setItems(boosts);
            FoePokemonSpecialDefenseBoost.setItems(boosts);
            FoePokemonSpeedBoost.setItems(boosts);
            // Nature
            ObservableList<Nature> natureList = dbAPI.getAllNature();
            UserPokemonNature.setItems(FXCollections.observableArrayList(natureList));
            FoePokemonNature.setItems(FXCollections.observableArrayList(natureList));
            // Abilities
            ObservableList<Ability> abilityList = dbAPI.getAllAbilitiesFromGame(data.getGameName());
            UserPokemonAbility.setItems(FXCollections.observableArrayList(abilityList));
            FoePokemonAbility.setItems(FXCollections.observableArrayList(abilityList));
            // Item
            ObservableList<Item> itemList = FXCollections.observableArrayList();
            itemList.add(new Item(-1, -1, "", "", 0, 0, 0));
            itemList.addAll(dbAPI.getAllItemsFromGame(data.getGameName()));
            UserPokemonItem.setItems(FXCollections.observableArrayList(itemList));
            FoePokemonItem.setItems(FXCollections.observableArrayList(itemList));
            // Status
            ObservableList<String> status = FXCollections.observableArrayList("Healthy", "Poisoned", "Badly Poisoned", "Burned", "Paralyzed", "Asleep", "Frozen");
            UserPokemonStatus.setItems(FXCollections.observableArrayList(status));
            FoePokemonStatus.setItems(FXCollections.observableArrayList(status));
            // MetLocation
            ObservableList<Route> metLocations = dbAPI.getALLMetLocationsFromGame(data.getGameName());
            UserPokemonMetLocation.setItems(FXCollections.observableArrayList(metLocations));
            FoePokemonMetLocation.setItems(FXCollections.observableArrayList(metLocations));
            // Pokeball
            ObservableList<Sprite> pokeballs = dbAPI.getAllPokeballs();
            UserPokemonPokeball.setItems(FXCollections.observableArrayList(pokeballs));
            FoePokemonPokeball.setItems(FXCollections.observableArrayList(pokeballs));
            // Attacks
            ObservableList<Attack> attackList = FXCollections.observableArrayList();
            attackList.add(new Attack(-1, "", "", "", 0, 0, "", "", 0, "", "", false, false, false, false, false, false));
            attackList.addAll(dbAPI.getAllAttacksFromGame(data.getGameName()));
            setGlobalPokemonAttack(UserPokemonAttack1, UserPokemonAttack1Type, UserPokemonAttack1Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(UserPokemonAttack2, UserPokemonAttack2Type, UserPokemonAttack2Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(UserPokemonAttack3, UserPokemonAttack3Type, UserPokemonAttack3Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(UserPokemonAttack4, UserPokemonAttack4Type, UserPokemonAttack4Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(FoePokemonAttack1, FoePokemonAttack1Type, FoePokemonAttack1Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(FoePokemonAttack2, FoePokemonAttack2Type, FoePokemonAttack2Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(FoePokemonAttack3, FoePokemonAttack3Type, FoePokemonAttack3Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));
            setGlobalPokemonAttack(FoePokemonAttack4, FoePokemonAttack4Type, FoePokemonAttack4Category, FXCollections.observableArrayList(attackList), FXCollections.observableArrayList(types));

            // search filter
            setSearchFilterOnComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSearchFilterOnComboBox() {
        // User
        new AutoBoxComplete<>(UserPokemonSpecies);
        new AutoBoxComplete<>(UserPokemonType1);
        new AutoBoxComplete<>(UserPokemonType2);
        new AutoBoxComplete<>(UserPokemonSpecies);
        new AutoBoxComplete<>(UserPokemonGender);
        new AutoBoxComplete<>(UserPokemonNature);
        new AutoBoxComplete<>(UserPokemonAbility);
        new AutoBoxComplete<>(UserPokemonItem);
        new AutoBoxComplete<>(UserPokemonMetLocation);
        new AutoBoxComplete<>(UserPokemonPokeball);
        new AutoBoxComplete<>(UserPokemonAttack1);
        new AutoBoxComplete<>(UserPokemonAttack1Type);
        new AutoBoxComplete<>(UserPokemonAttack2);
        new AutoBoxComplete<>(UserPokemonAttack2Type);
        new AutoBoxComplete<>(UserPokemonAttack3);
        new AutoBoxComplete<>(UserPokemonAttack3Type);
        new AutoBoxComplete<>(UserPokemonAttack4);
        new AutoBoxComplete<>(UserPokemonAttack4Type);
        // Trainer
        new AutoBoxComplete<>(FoePokemonSpecies);
        new AutoBoxComplete<>(FoePokemonType1);
        new AutoBoxComplete<>(FoePokemonType2);
        new AutoBoxComplete<>(FoePokemonSpecies);
        new AutoBoxComplete<>(FoePokemonGender);
        new AutoBoxComplete<>(FoePokemonNature);
        new AutoBoxComplete<>(FoePokemonAbility);
        new AutoBoxComplete<>(FoePokemonItem);
        new AutoBoxComplete<>(FoePokemonMetLocation);
        new AutoBoxComplete<>(FoePokemonPokeball);
        new AutoBoxComplete<>(FoePokemonAttack1);
        new AutoBoxComplete<>(FoePokemonAttack1Type);
        new AutoBoxComplete<>(FoePokemonAttack2);
        new AutoBoxComplete<>(FoePokemonAttack2Type);
        new AutoBoxComplete<>(FoePokemonAttack3);
        new AutoBoxComplete<>(FoePokemonAttack3Type);
        new AutoBoxComplete<>(FoePokemonAttack4);
        new AutoBoxComplete<>(FoePokemonAttack4Type);
    }

    private Badge findBadgeViaStatBoost(ArrayList<Badge> badgeList, String stat) {
        for (Badge badge : badgeList) {
            if (badge.statBoost != null && badge.statBoost.equals(stat)) {
                return badge;
            }
        }
        return new Badge(0, 0, "", "", 0, false, "", 0, 0);
    }

    private void setBadgeSprites() {
        int size = Math.min(8, badgeList.size());
        for (int i = 0; i < size; i++) {
            switch(i) {
                case 0 -> Badge1.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 1 -> Badge2.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 2 -> Badge3.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 3 -> Badge4.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 4 -> Badge5.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 5 -> Badge6.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 6 -> Badge7.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
                case 7 -> Badge8.setImage(new Image(dbAPI.getImagePathFromSpriteID(badgeList.get(i).spriteId)));
            }
        }
    }

    private void setGlobalPokemonAttack(ComboBox<Attack> userPokemonAttack, ComboBox<String> userPokemonAttackType, ComboBox<String> UserPokemonAttackCategory, ObservableList<Attack> attacks, ObservableList<String> types) {
        userPokemonAttack.setItems(attacks);
        userPokemonAttackType.setItems(types);

        ObservableList<String> category = FXCollections.observableArrayList("Physical", "Special", "Status");
        UserPokemonAttackCategory.setItems(category);
    }

    // loadUserPokemons
    private void showThatTheFirstSlotForUserPokemonIsSelected() {
        activeUserPokemonIndex = 1;
        previousUserPokemonBox = userPokemon1;
        activePokemonImageInSelection = userPokemon1;
        userPokemon1.getStyleClass().add("pokeballActiveBox");
    }

    private void loadUserPokemons() {
        userPokemonList = dbAPI.getAllPokemonFromUser(data.getGameName());
        for (PokemonList poke : userPokemonList) {
            createPokemonImage(poke);
        }

        // Sets Pokemons
        setUserPokemonStats(tempPokemon.pokemonId);
        pokemon1 = tempPokemon;
        pokemon2 = tempPokemon;
        pokemon3 = tempPokemon;
        pokemon4 = tempPokemon;
        pokemon5 = tempPokemon;
        pokemon6 = tempPokemon;
    }

    private Label createPokemonImage(PokemonList poke) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);

        // Pokemon Image
        Label pokemonImage = new Label("");
        pokemonImage.getStyleClass().add("calcPokemonBorder");
        pokemonImage.setStyle("-fx-background-image: url(" + spritePath + ");");
        // Tooltip when hover over Pokemon
        Tooltip tooltip = createTooltipForPokemon(poke);
        pokemonImage.setTooltip(tooltip);
        // add EventListener
        pokemonImage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Pokemon tempPokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            // set Pokemon in the selected User Pokemon
            setPokemonToTheUser(poke, pokemonImage);
            // set Values
            setUserPokemonStats(tempPokemon.pokemonId);
            activeUserPokemon = tempPokemon;
            activePokemonImageInBox = pokemonImage;
            // calculate damage
            calculateDamage();
        });

        // add To Controller
        UserPokemons.getChildren().add(pokemonImage);

        return pokemonImage;
    }

    private Tooltip createTooltipForPokemon(PokemonList poke) {
        try {
            Pokemon pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(poke.pokemonStatsId);
            Ability ability = dbAPI.getAbilityFromAbilityId(pokemon.abilityId);
            // Item
            Item item = dbAPI.getItemFromItemId(pokemon.itemId);
            if (item == null) item = new Item(0, 0, "", "", 0, 0, 0);
            // Attacks
            String attackString = "";
            ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(poke.pokemonId);
            for (PokemonAttack att : attackList) {
                Attack attack = dbAPI.getAttackFromAttackId(att.attackId);
                attackString += "Attack: " + attack.attackName + "\n";
            }

            String tooltip = "Species: " + pokeStats.nameOfPokemon + "\n" +
                    "Nickname: " + poke.nickname + "\n" +
                    "Level: " + pokemon.level + "\n" +
                    "Nature: " + pokemon.natureName + "\n" +
                    "Ability: " + ability.abilityName + "\n" +
                    "Item: " + item.nameOfItem + "\n" +
                    attackString;

            return new Tooltip(tooltip);
        } catch (Exception e) {
            System.out.println("Couldn't make tooltip for pokemon: " + poke.pokemonId + ". Error: " + e);
            return new Tooltip("");
        }
    }

    private Tooltip createTooltipForPokemon(Pokemon poke) {
        try {
            Pokemon pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(poke.pokemonStatsId);
            Ability ability = dbAPI.getAbilityFromAbilityId(pokemon.abilityId);
            // Item
            Item item = dbAPI.getItemFromItemId(pokemon.itemId);
            if (item == null) item = new Item(0, 0, "", "", 0, 0, 0);
            // Attacks
            String attackString = "";
            ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(poke.pokemonId);
            for (PokemonAttack att : attackList) {
                Attack attack = dbAPI.getAttackFromAttackId(att.attackId);
                attackString += "Attack: " + attack.attackName + "\n";
            }

            String tooltip = "Species: " + pokeStats.nameOfPokemon + "\n" +
                    "Nickname: " + poke.nickname + "\n" +
                    "Level: " + pokemon.level + "\n" +
                    "Nature: " + pokemon.natureName + "\n" +
                    "Ability: " + ability.abilityName + "\n" +
                    "Item: " + item.nameOfItem + "\n" +
                    attackString;

            return new Tooltip(tooltip);
        } catch (Exception e) {
            System.out.println("Couldn't make tooltip for pokemon: " + poke.pokemonId + ". Error: " + e);
            return new Tooltip("");
        }
    }

    private void setPokemonToTheUser(PokemonList poke, Label pokemonImage) {
        switch (activeUserPokemonIndex) {
            case 1 -> {
                pokemon1 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon1ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon1, poke.pokemonStatsId);
            }
            case 2 -> {
                pokemon2 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon2ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon2, poke.pokemonStatsId);
            }
            case 3 -> {
                pokemon3 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon3ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon3, poke.pokemonStatsId);
            }
            case 4 -> {
                pokemon4 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon4ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon4, poke.pokemonStatsId);
            }
            case 5 -> {
                pokemon5 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon5ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon5, poke.pokemonStatsId);
            }
            case 6 -> {
                pokemon6 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                pokemon6ImageBox = pokemonImage;
                updatePokemonSprite(userPokemon6, poke.pokemonStatsId);
            }
        }
    }

    private void updatePokemonSprite(Label label, int pokemonStatsId) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        label.setStyle("-fx-background-image: url(" + spritePath + ");");
    }

    // load Trainer
    private void loadTrainer() {
        trainerList = dbAPI.getAllTrainerFromSpecificGame(data.getGameName());
        for (int i = 0; i < trainerList.size(); i++) {
            createTrainerImage(trainerList.get(i), i);
        }
    }

    public void createTrainerImage(Trainer trainer, int index) {
        TitledPane tp = CreateTrainerTitledPane.createTitledPane(trainer);

        // EventHandler when clicked should load the content inside of the box
        tp.expandedProperty().addListener((observable, oldValue, newValue) -> {
            // load content if it is expanded
            if (newValue) loadTrainerContent(tp, trainer, index);
        });

        Trainers.getChildren().add(tp);
    }

    private void loadTrainerContent(TitledPane tp, Trainer trainer, int index) {
        // Trainer Sprite Path
        int spriteIdTrainer = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
        String spritePathTrainer = dbAPI.getImagePathFromSpriteID(spriteIdTrainer);
        if (spritePathTrainer == null) spritePathTrainer = "/Img/Trainers/Brendan_Small_E.png";
        String finalSpritePathTrainer = spritePathTrainer;
        // Trainer Pokemon
        ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);

        // Container
        VBox container = new VBox(10);
        container.setAlignment(Pos.TOP_CENTER);
        container.setStyle("-fx-background-color: linear-gradient(to bottom, derive(-fx-color,-02%), derive(-fx-color,65%) 12%,      derive(-fx-color,23%) 88%, derive(-fx-color,50%) 99%, -fx-box-border);");
        // Trainer Image
        int trainerSize = 40;
        ImageView trainerImg = new ImageView(new Image(finalSpritePathTrainer));
        trainerImg.setFitHeight(trainerSize); trainerImg.setFitWidth(trainerSize);
        // Trainer Pokemons -> Grid
        GridPane pokemonGrid = new GridPane();
        pokemonGrid.setAlignment(Pos.CENTER);
//        gridpane.add(new Button(), 1, 0); // column=1 row=0
        if (pokemonList != null) {
            for (int i = 0; i < pokemonList.size(); i++) {
                Pokemon pokemon = pokemonList.get(i);
                int pokemonImageSize = 25;
                int heldItemSize = 10;
                // width of column
                ColumnConstraints col = new ColumnConstraints(100);
                col.setPrefWidth(100);
                col.setMaxWidth(120);
                col.setHgrow(Priority.SOMETIMES);
                pokemonGrid.getColumnConstraints().add(col);
                // Image
                int pokemonSpriteId = dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId);
                String pokemonSpritePath = dbAPI.getImagePathFromSpriteID(pokemonSpriteId);
                // Held-Item
                Item item;
                if (pokemon.itemId > 0) item = dbAPI.getItemFromItemId(pokemon.itemId);
                else item = new Item(-1, -1, "", "", 0, 0, 0);
                String itemSpritePath = null;
                if (item.spriteId > 0) itemSpritePath = dbAPI.getImagePathFromSpriteID(item.spriteId);

                // make Nodes
                ImageView pokemonImg = new ImageView(new Image(pokemonSpritePath));
                pokemonImg.setFitHeight(pokemonImageSize); pokemonImg.setFitWidth(pokemonImageSize);
                Label pokemonLevel = new Label(Integer.toString(pokemon.level));
                // Item Node
                Label heldItem = new Label(item.nameOfItem);
                 if (itemSpritePath != null) {
                     ImageView heldItemImg = new ImageView(new Image(itemSpritePath));
                     heldItemImg.setFitHeight(heldItemSize); heldItemImg.setFitWidth(heldItemSize);
                     heldItem.setGraphic(heldItemImg);
                 }
                // add Nodes to grid
                pokemonGrid.add(pokemonImg, i ,0);
                pokemonGrid.add(pokemonLevel, i ,1);
                pokemonGrid.add(heldItem, i ,2);
                GridPane.setHalignment(pokemonImg, HPos.CENTER);
                GridPane.setHalignment(pokemonLevel, HPos.CENTER);
                GridPane.setHalignment(heldItem, HPos.CENTER);

                // set font color to black
                pokemonLevel.setTextFill(Color.color(0, 0, 0));
                heldItem.setTextFill(Color.color(0, 0, 0));
                pokemonLevel.setTextFill(Color.color(0, 0, 0));

                // Attacks
                ObservableList<PokemonAttack> attacks = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
                for (int j = 0; j < attacks.size(); j++) {
                    Attack attack = dbAPI.getAttackFromAttackId(attacks.get(j).attackId);
                    Label attackLabel = new Label(attack.attackName);
                    pokemonGrid.add(attackLabel, i ,3+j);
                    GridPane.setHalignment(attackLabel, HPos.CENTER);
                    attackLabel.setTextFill(Color.color(0, 0, 0, 1));
                    attackLabel.setPrefWidth(80);
                    attackLabel.setAlignment(Pos.CENTER);
                    // set background
                    setAttackBackground(attackLabel, attack);
                }
            }
        }

        // load Trainer when Image is clicked
        trainerImg.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            trainerIndex = index; // which trainer is on the line
            activeTrainerId = trainer.trainerId;
            activeTrainerTitlePane = tp;
            selectFirstTrainerPokemon(trainer);
            // set Pokemon in the selected User Pokemon
            FoeTrainerSprite.setImage(new Image(finalSpritePathTrainer)); // set Trainer Sprite
            setPokemonToTrainer(dbAPI.getAllPokemonFromTrainer(trainer.trainerId));
        });

        container.getChildren().addAll(trainerImg, pokemonGrid);
        tp.setContent(container);
    }

    private void setAttackBackground(Label attackLabel, Attack attack) {
        switch (attack.typeName) {
            case "Ghost" -> attackLabel.setStyle("-fx-background-color: rgba(148, 0, 211, 0.5);");
            case "Steel" -> attackLabel.setStyle("-fx-background-color: rgba(140, 146, 172, 0.5);");
            case "Dragon" -> attackLabel.setStyle("-fx-background-color: rgba(75, 0, 130, 0.5);");
            case "Flying" -> attackLabel.setStyle("-fx-background-color: rgba(147, 204, 234, 0.5);");
            case "Water" -> attackLabel.setStyle("-fx-background-color: rgba(0, 127, 255, 0.5);");
            case "Ice" -> attackLabel.setStyle("-fx-background-color: rgba(0, 255, 255, 0.5);");
            case "Grass" -> attackLabel.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
            case "Bug" -> attackLabel.setStyle("-fx-background-color: rgba(128, 128, 0, 0.5);");
            case "Normal" -> attackLabel.setStyle("-fx-background-color: rgba(229, 229, 229, 0.8);");
            case "Electric" -> attackLabel.setStyle("-fx-background-color: rgba(255, 255, 51, 0.5);");
            case "Ground" -> attackLabel.setStyle("-fx-background-color: rgba(236, 213, 64, 0.5);");
            case "Rock" -> attackLabel.setStyle("-fx-background-color: rgba(150, 75, 0, 0.5);");
            case "Fire" -> attackLabel.setStyle("-fx-background-color: rgba(255, 103, 0, 0.5);");
            case "Fighting" -> attackLabel.setStyle("-fx-background-color: rgba(130, 0, 0, 0.5);");
            case "Dark" -> attackLabel.setStyle("-fx-background-color: rgba(20, 20, 20, 0.5);");
            case "Psychic" -> attackLabel.setStyle("-fx-background-color: rgba(255, 105, 180, 0.5);");
            case "Fairy" -> attackLabel.setStyle("-fx-background-color: rgba(249, 132, 229, 0.5);");
            case "Poison" -> attackLabel.setStyle("-fx-background-color: rgba(150, 111, 214, 0.5);");
        }

    }

    private void setPokemonToTrainer(ArrayList<Pokemon> pokemonList) {
        for (int i = 0; i < pokemonList.size(); i++) {
            Pokemon pokemon = pokemonList.get(i);
            switch (i) {
                case 0 -> {
                    trainerPokemon1 = pokemon;
                    updatePokemonSprite(foePokemon1, pokemon);
                }
                case 1 -> {
                    trainerPokemon2 = pokemon;
                    updatePokemonSprite(foePokemon2, pokemon);
                }
                case 2 -> {
                    trainerPokemon3 = pokemon;
                    updatePokemonSprite(foePokemon3, pokemon);
                }
                case 3 -> {
                    trainerPokemon4 = pokemon;
                    updatePokemonSprite(foePokemon4, pokemon);
                }
                case 4 -> {
                    trainerPokemon5 = pokemon;
                    updatePokemonSprite(foePokemon5, pokemon);
                }
                case 5 -> {
                    trainerPokemon6 = pokemon;
                    updatePokemonSprite(foePokemon6, pokemon);
                }
            }
        }

        // fill the rest with Pokeballs
        String path = "-fx-background-image: url(/Img/Pokeballs/000.png);";
        for (int i = pokemonList.size(); i < 6; i++) {
            switch (i) {
                case 0 -> {
                    trainerPokemon1 = tempPokemon;
                    foePokemon1.setStyle(path);
                }
                case 1 -> {
                    trainerPokemon2 = tempPokemon;
                    foePokemon2.setStyle(path);
                }
                case 2 -> {
                    trainerPokemon3 = tempPokemon;
                    foePokemon3.setStyle(path);
                }
                case 3 -> {
                    trainerPokemon4 = tempPokemon;
                    foePokemon4.setStyle(path);
                }
                case 4 -> {
                    trainerPokemon5 = tempPokemon;
                    foePokemon5.setStyle(path);
                }
                case 5 -> {
                    trainerPokemon6 = tempPokemon;
                    foePokemon6.setStyle(path);
                }
            }
        }
    }

    private void updatePokemonSprite(Label label, Pokemon poke) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        label.setStyle("-fx-background-image: url(" + spritePath + ");");
    }

    private void setTrainersFirstPokemon(Pokemon pokemon) {
        showThatTheFirstTrainerPokemonIsSelected();
        setTrainerPokemonStats(pokemon.pokemonId);
    }

    private void showThatTheFirstTrainerPokemonIsSelected() {
        activeTrainerPokemonIndex = 1;
        previousTrainerPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        previousTrainerPokemonBox = foePokemon1;
        foePokemon1.getStyleClass().add("pokeballActiveBox");
    }

    private void selectTrainer(int index) {
        try {
            Trainer trainer = trainerList.get(index);
            activeTrainerId = trainer.trainerId;
            activeTrainerTitlePane = (TitledPane) Trainers.getChildren().get(index);
            ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);
            // Set Trainer Image
            int spriteIdTrainer = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
            String spritePathTrainer = dbAPI.getImagePathFromSpriteID(spriteIdTrainer);
            FoeTrainerSprite.setImage(new Image(spritePathTrainer));

            // set First Pokemon
            activeTrainerPokemonBox = foePokemon1;
            selectFirstTrainerPokemon(trainer);

            setPokemonToTrainer(pokemonList);
        } catch (Exception e) {
            System.out.println("There isn't a Trainer to select. Error: " + e);
//            e.printStackTrace();
        }
    }

    private void setTrainerMovesManually(Pokemon pokemon) {
        ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);

        for (int i = 0; i < attackList.size(); i++) {
            PokemonAttack pkAttack = attackList.get(i);
            Attack attack = dbAPI.getAttackFromAttackId(pkAttack.attackId);
            switch (i) {
                case 0 -> {
                    FoePokemonAttack1Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack1Type.setValue(attack.typeName);
                    FoePokemonAttack1Category.setValue(attack.category);
                }
                case 1 -> {
                    FoePokemonAttack2Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack2Type.setValue(attack.typeName);
                    FoePokemonAttack2Category.setValue(attack.category);
                }
                case 2 -> {
                    FoePokemonAttack3Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack3Type.setValue(attack.typeName);
                    FoePokemonAttack3Category.setValue(attack.category);
                }
                case 3 -> {
                    FoePokemonAttack4Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack4Type.setValue(attack.typeName);
                    FoePokemonAttack4Category.setValue(attack.category);
                }
            }
        }
    }

    private void selectFirstTrainerPokemon(Trainer trainer) {
        ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);
        try {
            Pokemon tempPokemon = pokemonList.get(0);
            setTrainersFirstPokemon(tempPokemon);  // set Values
            activeTrainerPokemon = tempPokemon;  // set this Pokemon as active
            setTrainerMovesManually(tempPokemon); // Event Handler is not active
            calculateDamage();
        } catch (Exception e) {
            setTrainersFirstPokemon(tempPokemon);
            activeTrainerPokemon = tempPokemon;
            setTrainerMovesManually(tempPokemon);
            calculateDamage();
//            System.out.println("Trainer has no Pokemons. Error: " + e);
//                e.printStackTrace();
        }
    }

    // todo -> not all abilities are included or attacks like explosion
    // Damage Calculation
    private void calculateDamage() {
        if (activeUserPokemon.pokemonStatsId > 0 && activeTrainerPokemon.pokemonStatsId > 0) {
            PokemonStats userPokemon = getUserPokemonThroughLabels();
            PokemonStats trainerPokemon = getTrainerPokemonThroughLabels();

            ArrayList<Attack> userPokemonAttacks = getAllUserAttackFromPokemonViaLabel();
            setUserPokemonMoveDamage(userPokemonAttacks, userPokemon, trainerPokemon);

            ArrayList<Attack> trainerPokemonAttacks = getAllTrainerAttackFromPokemonViaLabel();
            setTrainerPokemonMoveDamage(trainerPokemonAttacks, userPokemon, trainerPokemon);
        }
        else {
            setUserPokemonDamage("", 0);
            setUserPokemonDamage("", 1);
            setUserPokemonDamage("", 2);
            setUserPokemonDamage("", 3);
            setTrainerPokemonDamage("", 0);
            setTrainerPokemonDamage("", 1);
            setTrainerPokemonDamage("", 2);
            setTrainerPokemonDamage("", 3);
        }
    }

    private ArrayList<Attack> getAllUserAttackFromPokemonViaLabel() {
        ArrayList<Attack> userPokemonAttacks = new ArrayList<Attack>();

        Attack attack1 = getAttackThoughLabel(UserPokemonAttack1, UserPokemonAttack1Damage, UserPokemonAttack1Type, UserPokemonAttack1Category);
        Attack attack2 = getAttackThoughLabel(UserPokemonAttack2, UserPokemonAttack2Damage, UserPokemonAttack2Type, UserPokemonAttack2Category);
        Attack attack3 = getAttackThoughLabel(UserPokemonAttack3, UserPokemonAttack3Damage, UserPokemonAttack3Type, UserPokemonAttack3Category);
        Attack attack4 = getAttackThoughLabel(UserPokemonAttack4, UserPokemonAttack4Damage, UserPokemonAttack4Type, UserPokemonAttack4Category);

        try { attack1.multihits = Integer.parseInt(UserPokemonAttack1Hits.getText());
        } catch (Exception ignored) {}
        try { attack2.multihits = Integer.parseInt(UserPokemonAttack2Hits.getText());
        } catch (Exception ignored) {}
        try { attack3.multihits = Integer.parseInt(UserPokemonAttack3Hits.getText());
        } catch (Exception ignored) {}
        try { attack4.multihits = Integer.parseInt(UserPokemonAttack4Hits.getText());
        } catch (Exception ignored) {}

        userPokemonAttacks.add(attack1);
        userPokemonAttacks.add(attack2);
        userPokemonAttacks.add(attack3);
        userPokemonAttacks.add(attack4);

        return userPokemonAttacks;
    }

    private ArrayList<Attack> getAllTrainerAttackFromPokemonViaLabel() {
        ArrayList<Attack> trainerPokemonAttacks = new ArrayList<Attack>();

        Attack attack1 = getAttackThoughLabel(FoePokemonAttack1, FoePokemonAttack1Damage, FoePokemonAttack1Type, FoePokemonAttack1Category);
        Attack attack2 = getAttackThoughLabel(FoePokemonAttack2, FoePokemonAttack2Damage, FoePokemonAttack2Type, FoePokemonAttack2Category);
        Attack attack3 = getAttackThoughLabel(FoePokemonAttack3, FoePokemonAttack3Damage, FoePokemonAttack3Type, FoePokemonAttack3Category);
        Attack attack4 = getAttackThoughLabel(FoePokemonAttack4, FoePokemonAttack4Damage, FoePokemonAttack4Type, FoePokemonAttack4Category);

        try { attack1.multihits = Integer.parseInt(FoePokemonAttack1Hits.getText());
        } catch (Exception ignored) {}
        try { attack2.multihits = Integer.parseInt(FoePokemonAttack2Hits.getText());
        } catch (Exception ignored) {}
        try { attack3.multihits = Integer.parseInt(FoePokemonAttack3Hits.getText());
        } catch (Exception ignored) {}
        try { attack4.multihits = Integer.parseInt(FoePokemonAttack4Hits.getText());
        } catch (Exception ignored) {}

        trainerPokemonAttacks.add(attack1);
        trainerPokemonAttacks.add(attack2);
        trainerPokemonAttacks.add(attack3);
        trainerPokemonAttacks.add(attack4);

        return trainerPokemonAttacks;
    }

    private Attack getAttackThoughLabel(ComboBox<Attack> attack, TextField damage, ComboBox<String> attackType, ComboBox<String> category) {
        try {
            Attack ua = attack.getValue();
            return new Attack(ua.attackId, attackType.getValue(), ua.attackName, ua.description, Integer.parseInt(damage.getText()),
                    ua.pp, ua.effect, ua.secondaryEffect, ua.priority, ua.targets, category.getValue(), ua.makesContact, ua.protectAffected,
                    ua.magicCoatAffected, ua.snatchAffected, ua.mirrorMoveAffected, ua.kingsRockAffected);
        } catch (Exception e) {
            e.printStackTrace();
            return new Attack(-1, "", "", "", 0, 0, "", "", 0, "", "", false, false, false, false, false, false);
        }
    }

    private void setUserPokemonMoveDamage(ArrayList<Attack> userPokemonAttacks, PokemonStats userPokemon, PokemonStats trainerPokemon) {
        boolean critical = false;

        if (userPokemon.abilityName.equals("Guts") && !userPokemon.statusCondition.equals("Healthy"))
            userPokemon.attackStat *= 2;
        else if (trainerPokemon.abilityName.equals("Marvel Scale") && !trainerPokemon.statusCondition.equals("Healthy"))
            trainerPokemon.defenseStat *= 1.5;
        else if (userPokemon.abilityName.equals("Huge Power")) userPokemon.attackStat *= 2;
        else if (userPokemon.abilityName.equals("Pure Power")) userPokemon.attackStat *= 2;

        // display Damage
        for (int i = 0; i < userPokemonAttacks.size(); i++) {
            if (userPokemonAttacks.get(i).attackId > 0) {
                Attack attack = userPokemonAttacks.get(i);

                if (userHelpingHand) attack.power *= 1.5;
                if (userBattery && attack.category.equals("Special")) attack.power *= 1.3;

                ArrayList<Integer> damageRange = Utilities.calculateDamageOfAttack(activeBattleMode, activeWeather, critical, userPokemon, trainerPokemon, attack);

                if (foeReflect && attack.category.equals("Physical") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                if (foeLightScreen && attack.category.equals("Special") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                if (foeFriendGuard && damageRange.get(1) > 0) {
                    damageRange.set(0, Math.max(1, (int) (damageRange.get(0) * 0.75)));
                    damageRange.set(1, Math.max(1, (int) (damageRange.get(0) * 0.75)));
                }

                String damageRangeText = (showFlatDamage) ? damageRange.get(0) + " - " + damageRange.get(1) : stringDamageRancePercentage(damageRange);
                setUserPokemonDamage(damageRangeText, i);
            } else {
                String damageRangeText = (showFlatDamage) ? "" : "";
                setUserPokemonDamage(damageRangeText, i);
            }
        }
    }

    private void setTrainerPokemonMoveDamage(ArrayList<Attack> trainerPokemonAttacks, PokemonStats userPokemon, PokemonStats trainerPokemon) {
        boolean critical = false;

        if (trainerPokemon.abilityName.equals("Guts") && !trainerPokemon.statusCondition.equals("Healthy"))
            trainerPokemon.attackStat *= 2;
        else if (userPokemon.abilityName.equals("Marvel Scale") && !userPokemon.statusCondition.equals("Healthy"))
            userPokemon.defenseStat *= 1.5;
        else if (trainerPokemon.abilityName.equals("Huge Power")) trainerPokemon.attackStat *= 2;
        else if (trainerPokemon.abilityName.equals("Pure Power")) trainerPokemon.attackStat *= 2;

        // display Damage
        for (int i = 0; i < trainerPokemonAttacks.size(); i++) {
            if (trainerPokemonAttacks.get(i).attackId > 0) {
                Attack attack = trainerPokemonAttacks.get(i);

                if (foeHelpingHand) attack.power *= 1.5;
                if (foeBattery && attack.category.equals("Special")) attack.power *= 1.3;

                boolean thickFat = trainerPokemon.abilityName.equals("Thick Fat") && (attack.typeName.equals("Fire") || attack.typeName.equals("Ice"));
                if (thickFat) trainerPokemon.attackStat /= 2;
                if (thickFat) trainerPokemon.specialAttackStat /= 2;

                ArrayList<Integer> damageRange = Utilities.calculateDamageOfAttack(activeBattleMode, activeWeather, critical, trainerPokemon, userPokemon, attack);

                if (userReflect && attack.category.equals("Physical") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                if (userLightScreen && attack.category.equals("Special") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                if (userFriendGuard && damageRange.get(1) > 0) {
                    damageRange.set(0, Math.max(1, (int) (damageRange.get(0) * 0.75)));
                    damageRange.set(1, Math.max(1, (int) (damageRange.get(0) * 0.75)));
                }

                String damageRangeText = (showFlatDamage) ? damageRange.get(0) + " - " + damageRange.get(1) : stringDamageRancePercentage(damageRange);
                setTrainerPokemonDamage(damageRangeText, i);
            } else {
                String damageRangeText = (showFlatDamage) ? "" : "";
                setTrainerPokemonDamage(damageRangeText, i);
            }
        }
    }

    private PokemonStats getUserPokemonThroughLabels() {
        int pokemonId = activeUserPokemon.pokemonId;
        int pokemonStatsId = activeUserPokemon.pokemonStatsId;
        String type1 = UserPokemonType1.getValue();
        String type2 = UserPokemonType2.getValue();
        String itemName = UserPokemonItem.getValue().nameOfItem;
        String abilityName = UserPokemonAbility.getValue().abilityName;
        String natureName = UserPokemonNature.getValue().natureName;
        String statusCondition = UserPokemonStatus.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        String gender = UserPokemonGender.getValue();
        int friendship = activeUserPokemon.friendship;
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonAttack.getText()), UserPokemonAttackBoost.getValue());
        int defenseStat = calcStatWithBoost(Integer.parseInt(UserPokemonDefense.getText()), UserPokemonDefenseBoost.getValue());
        int specialAttackStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialAttack.getText()), UserPokemonSpecialAttackBoost.getValue());
        int specialDefenseStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialDefense.getText()), UserPokemonSpecialDefenseBoost.getValue());
        int speedStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpeed.getText()), UserPokemonSpeedBoost.getValue());
        return new PokemonStats(pokemonId, pokemonStatsId, type1, type2, itemName, abilityName, natureName, statusCondition, level,
                gender, friendship, attackStat, defenseStat, specialAttackStat, specialDefenseStat, speedStat);
    }

    private PokemonStats getTrainerPokemonThroughLabels() {
        int pokemonId = activeTrainerPokemon.pokemonId;
        int pokemonStatsId = activeTrainerPokemon.pokemonStatsId;
        String type1 = FoePokemonType1.getValue();
        String type2 = FoePokemonType2.getValue();
        String itemName = FoePokemonItem.getValue().nameOfItem;
        String abilityName = FoePokemonAbility.getValue().abilityName;
        String natureName = FoePokemonNature.getValue().natureName;
        String statusCondition = FoePokemonStatus.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        String gender = FoePokemonGender.getValue();
        int friendship = activeTrainerPokemon.friendship;
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonAttack.getText()), FoePokemonAttackBoost.getValue());
        int defenseStat = calcStatWithBoost(Integer.parseInt(FoePokemonDefense.getText()), FoePokemonDefenseBoost.getValue());
        int specialAttackStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialAttack.getText()), FoePokemonSpecialAttackBoost.getValue());
        int specialDefenseStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialDefense.getText()), FoePokemonSpecialDefenseBoost.getValue());
        int speedStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpeed.getText()), FoePokemonSpeedBoost.getValue());
        return new PokemonStats(pokemonId, pokemonStatsId, type1, type2, itemName, abilityName, natureName, statusCondition, level,
                gender, friendship, attackStat, defenseStat, specialAttackStat, specialDefenseStat, speedStat);
    }

    private int calcStatWithBoost(int stat, String boost) {
        switch (boost) {
            case "+6" -> {
                return stat * 4;
            }
            case "+5" -> {
                return (int) (stat * 3.5);
            }
            case "+4" -> {
                return (int) (stat * 3);
            }
            case "+3" -> {
                return (int) (stat * 2.5);
            }
            case "+2" -> {
                return (int) (stat * 2);
            }
            case "+1" -> {
                return (int) (stat * 1.5);
            }
            case "-1" -> {
                return (int) (stat * 0.66);
            }
            case "-2" -> {
                return (int) (stat * 0.5);
            }
            case "-3" -> {
                return (int) (stat * 0.40);
            }
            case "-4" -> {
                return (int) (stat * 0.33);
            }
            case "-5" -> {
                return (int) (stat * 0.28);
            }
            case "-6" -> {
                return (int) (stat * 0.25);
            }
            default -> {
                return stat;
            }
        }

    }

    private void setUserPokemonDamage(String damageRange, int index) {
        switch (index) {
            case 0 -> userMove1Damage.setText(damageRange);
            case 1 -> userMove2Damage.setText(damageRange);
            case 2 -> userMove3Damage.setText(damageRange);
            case 3 -> userMove4Damage.setText(damageRange);
        }
    }

    private void setTrainerPokemonDamage(String damageRange, int index) {
        switch (index) {
            case 0 -> foeMove1Damage.setText(damageRange);
            case 1 -> foeMove2Damage.setText(damageRange);
            case 2 -> foeMove3Damage.setText(damageRange);
            case 3 -> foeMove4Damage.setText(damageRange);
        }
    }

    private String stringDamageRancePercentage(ArrayList<Integer> damageRange) {
        int trainerPokemonHp = Integer.parseInt(FoePokemonHP.getText());

        double lowPercentage = Math.floor(((damageRange.get(0) * 1.0) / trainerPokemonHp) * 1000) / 10;
        double highPercentage = Math.floor(((damageRange.get(1) * 1.0) / trainerPokemonHp) * 1000) / 10;

        return (lowPercentage + " - " + highPercentage + "%");
    }
}