package Utilities;

import Classes.*;
import Classes.Abstract.BasicPokemon;
import View.MenuView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Utilities {

    public Utilities() {
    }

    public static Parent loadFxmlFile(String nameOfFile) {
        try {
            return FXMLLoader.load(Utilities.class.getResource("/fxmlFiles/" + nameOfFile +".fxml"));
        } catch (Exception e) {
            System.out.println("There was a problem by loading '" + nameOfFile + "'. Error: " + e);
//            e.printStackTrace();
            return null;
        }
    }

    public static void backToMenu() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        MenuView pv = new MenuView();
        pv.createMenuPage(data.getStage());
    }

    // -------------------------- other --------------------------
    public void setSmallSpriteNextToLabel(Label smallImages, int spriteId) {
        if (spriteId > 0) {
            API.Database dbAPI = new API.Database();
            String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);

            smallImages.getStyleClass().add("createSmallSpriteNextToLabel");
            smallImages.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ");");
        }
    }

    public static boolean findPokeStatsInList(String nameOfPokemon, ObservableList<PokeStats> pokeStatsList) {
        nameOfPokemon = nameOfPokemon.substring(0, 1).toUpperCase() + nameOfPokemon.substring(1);
        for (PokeStats pokeStats : pokeStatsList) {
            if (pokeStats.nameOfPokemon.equals(nameOfPokemon)) {
                return true;
            }
        }
        return false;
    }

    public static int findPokemonInPokemonList(Pokemon pokemon, ObservableList<PokemonList> pokeStatsList) {
        int index = 0;
        for (PokemonList pokeList : pokeStatsList) {
            if(pokeList.pokemonId == pokemon.pokemonId) {
                return index;
            }
            index++;
        }
        return 0;
    }

    public static int findIndexOfSpriteStats(PokeStats pokeStats, ObservableList<Sprite> pokeSpriteList) {
        API.Database dbAPI = new API.Database();
        int pokeStatsSpriteID = dbAPI.getSpriteIdFromSpecificPokemon(pokeStats.pokemonStatsId);

        // find index
        int index = 0;
        for (Sprite sprite : pokeSpriteList) {
            if (sprite.spriteId == pokeStatsSpriteID) {
                return index;
            }
            index++;
        }
        return 0;
    }

    public static int findIndexOfPokeStats(String nameOfPokemon, ObservableList<PokeStats> pokeStatsList) {
        int index = 0;
        for (PokeStats pokeStats : pokeStatsList) {
            if (pokeStats.nameOfPokemon.equals(nameOfPokemon)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public static int getMove(int moveId, ObservableList<Attack> attackList) {
        int index = 0;
        for (Attack item : attackList) {
            if (item.attackId == moveId) {
                return index;
            }
            index++;
        }
        return 0;
    }

    public static int findTrainerSprite(Trainer trainer, VBox trainerSpriteContainer) {
        API.Database dbAPI = new API.Database();
        int trainerSpriteId = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);

        int index = 0;
        for (Node node : trainerSpriteContainer.getChildren()) {
            HBox hbox = (HBox) node;
            Label spriteIdLabel = (Label) hbox.getChildren().get(2);
            int spriteId = Integer.parseInt(spriteIdLabel.getText());

            if (spriteId == trainerSpriteId) {
                return index;
            }
            index++;
        }

        return 0;
    }

    public static ArrayList<Attack> getAllAttackFromPokemon(Pokemon pokemon) {
        API.Database dbAPI = new API.Database();
        ObservableList<PokemonAttack> userTempAttack = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
        ArrayList<Attack> userPokemonAttacks = new ArrayList<Attack>();
        for (PokemonAttack pkA : userTempAttack) {
            userPokemonAttacks.add(dbAPI.getAttackFromAttackId(pkA.attackId));
        }
        return userPokemonAttacks;
    }

    // -------------------------- select or set Nature / Ability / Item / Moves --------------------------
    public static void selectSpecies(String nameOfPokemon, ObservableList<PokeStatsList> list, ComboBox<PokeStatsList> cb) {
        int index = 0;

        for (PokeStatsList item : list) {
            if (item.nameOfPokemon.equals(nameOfPokemon)) {
                cb.getSelectionModel().select(index);
                return;
            }
            index++;
        }
    }

    public static void selectNature(String nature, ObservableList<Nature> natures, ComboBox<Nature> natureBox) {
        int index = 0;
        for (Nature item : natures) {
            if (item.natureName.equals(nature)) {
                break;
            }
            index++;
        }
        natureBox.getSelectionModel().select(index);
    }

    public static void selectHeldItem(int item, ObservableList<Item> itemList, ComboBox<Item> itemBox) {
        if (item > 0) {
            int index = 0;
            for (Item item2 : itemList) {
                if (item2.itemId == item) {
                    break;
                }
                index++;
            }
            itemBox.getSelectionModel().select(index);
        } else {
            itemBox.getSelectionModel().selectFirst();
        }

    }

    public static void selectAbility(int ability, ObservableList<Ability> abilityList, ComboBox<Ability> abilityBox) {
        int index = 0;
        for (Ability item : abilityList) {
            if (item.abilityId == ability) {
                break;
            }
            index++;
        }
        abilityBox.getSelectionModel().select(index);
    }

    public static void selectMove(int moveId, ObservableList<Attack> attackList, ComboBox<Attack> attackBox) {
        int index = 0;
        for (Attack item : attackList) {
            if (item.attackId == moveId) {
                break;
            }
            index++;
        }
        attackBox.getSelectionModel().select(index);
    }

    public static void selectMetLocation(int metLocation, ObservableList<Route> metLocations, ComboBox<Route> metLocationBox) {
        int index = 0;
        for (Route item : metLocations) {
            if (item.routeId == metLocation) {
                break;
            }
            index++;
        }
        metLocationBox.getSelectionModel().select(index);
    }

    public static void selectPokeball(int pokeball, ObservableList<Sprite> pokeballSprites, ComboBox<Sprite> pokeballBox) {
        int index = 0;
        for (Sprite item : pokeballSprites) {
            if (item.spriteId == pokeball) {
                break;
            }
            index++;
        }
        pokeballBox.getSelectionModel().select(index);
    }

    public static void selectPokeball(String pokeball, ObservableList<Sprite> pokeballSprites, ComboBox<Sprite> pokeballBox) {
        int index = 0;

        for (Sprite sprite : pokeballSprites) {
            if (sprite.spriteName.equals(pokeball)) {
                break;
            }
            index++;
        }

        pokeballBox.getSelectionModel().select(index);
    }

    public static void setPokemonSpriteAndNickName(Pokemon pokemon, Label label, ImageView imageview) {
        API.Database dbAPI = new API.Database();
        if (pokemon.pokemonStatsId > 0) {
            int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId);
            String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
            imageview.setImage(new Image(spritePath));
        } else imageview.setImage(new Image("Img/Pokeballs/000.png"));

        label.setText(pokemon.nickname);
    }

    // -------------------------- Calculation --------------------------
    // HP
    public static int calculateHpStat(int baseHp, int ivHp, int evHp, int level) {
        return (int) Math.floor(((baseHp * 2 + ivHp + Math.floor(evHp / 4.0)) * level) / 100) + level + 10;
    }
    // Att - Def- SpA - SpD- Spe
    public static int calculateStatWithBadge(Nature nature, int base, int iv, int ev, int level, String stat, Badge badge, boolean haveBadge) {
        double natureMultiplier = (nature.StatUp.equals(stat) && !nature.StatDown.equals(stat)) ? 1.1 : (nature.StatDown.equals(stat) && !nature.StatUp.equals(stat)) ? 0.9 : 1;
        double tempStat = Math.floor((Math.floor(((base * 2 + iv + Math.floor(ev / 4.0)) * level) / 100) + 5) * natureMultiplier);

        try {
            if (haveBadge && (badge.statBoost.equals(stat) || badge.statBoost.equals(stat.substring(0,7))) ) tempStat *= badge.boostPower;
        } catch (Exception e) {
            System.out.println("String is not long enough or null. Error: " + e);
        }
        return (int) tempStat;
    }

    // Damage
    public static ArrayList<Integer> calculateDamageOfAttack(String activeBattleMode, String activeWeather, boolean critical, PokemonStats attackPokemon, PokemonStats defendPokemon, Attack attack) {
        ArrayList<Integer> damageRolls = new ArrayList<Integer>();

        if (attack.attackName.equals("Low Kick")) attack.power = calculateLowKickBasePower(defendPokemon);

        if (attack == null || attack.category == null || attack.category.isEmpty()|| attack.category.equals("Status") || attack.power <= 0) {
            damageRolls.add(0);
            damageRolls.add(0);
        } else {
            double type = getTypeEffectiveness(attack, defendPokemon);

            double modifierLow = calculateModifier(activeBattleMode, activeWeather, critical, attackPokemon, defendPokemon, attack, type, 0.85);
            double modifierHigh = calculateModifier(activeBattleMode, activeWeather, critical, attackPokemon, defendPokemon, attack, type, 1.0);

            int attackStat = (attack.category.equals("Physical")) ? attackPokemon.attackStat : attackPokemon.specialAttackStat;
            int defenseStat = (attack.category.equals("Physical")) ? defendPokemon.defenseStat : defendPokemon.specialDefenseStat;

            int damage = (int) ((((((2.0 * attackPokemon.level) / 5.0) + 2) * attack.power * ((double) attackStat / defenseStat)) / 50.0) + 2.0);
            int lowestDamage = (modifierLow > 0) ? (int) (damage * modifierLow) : 0;
            int highestDamage = (modifierHigh > 0) ? (int) (damage * modifierHigh) : 0;

            if (type > 0) lowestDamage = Math.max(1, lowestDamage);
            if (type > 0) highestDamage = Math.max(1, highestDamage);

            if (attack.multihits != null) lowestDamage *= attack.multihits;
            if (attack.multihits != null) highestDamage *= attack.multihits;

            damageRolls.add(lowestDamage);
            damageRolls.add(highestDamage);
        }

        return damageRolls;
    }

    private static double calculateModifier(String activeBattleMode, String activeWeather, boolean critical, PokemonStats attackPokemon, PokemonStats defendPokemon, Attack attack, double type, double randomNumber) {
        double target = (activeBattleMode.equals("Single")) ? 1 : (attack.targets.equals("Single")) ? 1 : 0.5;
        double weather = (activeWeather.equals("Sun") && attack.typeName.equals("Fire")) ? 1.5 : (activeWeather.equals("Rain") && attack.typeName.equals("Fire")) ? 0.5 :
                (activeWeather.equals("Rain") && attack.typeName.equals("Water")) ? 1.5 : (activeWeather.equals("Sun") && attack.typeName.equals("Water")) ? 0.5 :
                        1;
        double crit = (critical) ? 2 : 1;
        double stab = getStabOfPokemon(attack, attackPokemon);
        double burn = attack.category.equals("Special") || !attackPokemon.statusCondition.equals("Burned") ? 1 : (attackPokemon.abilityName.equals("Guts")) ? 1 : 0.5;
        double other = calculateOther(attack, attackPokemon);
        return (target * weather * crit * randomNumber * stab * type * burn * other);
    }

    private static double getStabOfPokemon(Attack attack, PokemonStats attackPokemon) {
        String[] attackTypes = {attackPokemon.type1, attackPokemon.type2};

        for (String type : attackTypes) {
            if (type.equals(attack.typeName)) {
                return 1.5;
            }
        }
        return 1;
    }

    private static double getTypeEffectiveness(Attack attack, PokemonStats defendPokemon) {
        API.Database dbAPI = new API.Database();
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        String[] defendTypes = {defendPokemon.type1, defendPokemon.type2};

        double multiplyFactor = 1;
        for (String defendType : defendTypes) {
            if (!defendType.isEmpty()) multiplyFactor *= dbAPI.getMultiplyFactorForTypeAttack(data.getGameName(), attack.typeName, defendType);
        }

        return multiplyFactor;
    }

    private static double calculateOther(Attack attack, PokemonStats attackPokemon) {
        // Item Boost
        if (attackPokemon.itemName != null) {
            if (attackPokemon.itemName.equals("Choice Band")) return 1.5;
            else if (attackPokemon.itemName.equals("Mystic Water") && attack.typeName.equals("Water")) return 1.1;
            else if (attackPokemon.itemName.equals("Black Belt") && attack.typeName.equals("Fighting")) return 1.1;
            else if (attackPokemon.itemName.equals("Black Glasses") && attack.typeName.equals("Dark")) return 1.1;
            else if (attackPokemon.itemName.equals("Charcoal") && attack.typeName.equals("Fire")) return 1.1;
            else if (attackPokemon.itemName.equals("Hard Stone") && attack.typeName.equals("Rock")) return 1.1;
            else if (attackPokemon.itemName.equals("Magnet") && attack.typeName.equals("Electric")) return 1.1;
            else if (attackPokemon.itemName.equals("Metal Coat") && attack.typeName.equals("Steel")) return 1.1;
            else if (attackPokemon.itemName.equals("Miracle Seed") && attack.typeName.equals("Grass")) return 1.1;
            else if (attackPokemon.itemName.equals("Poison Barb") && attack.typeName.equals("Poison")) return 1.1;
            else if (attackPokemon.itemName.equals("Sea Incense") && attack.typeName.equals("Water")) return 1.05;
            else if (attackPokemon.itemName.equals("Silk Scarf") && attack.typeName.equals("Normal")) return 1.1;
            else if (attackPokemon.itemName.equals("Silver Powder") && attack.typeName.equals("Bug")) return 1.1;
            else if (attackPokemon.itemName.equals("Soft Sand") && attack.typeName.equals("Ground")) return 1.1;
            else if (attackPokemon.itemName.equals("Spell Tag") && attack.typeName.equals("Ghost")) return 1.1;
            else if (attackPokemon.itemName.equals("Twisted Spoon") && attack.typeName.equals("Psychic")) return 1.1;
        }
        return 1;
    }

    private static int calculateLowKickBasePower(PokemonStats defendPokemon) {
        API.Database dbAPI = new API.Database();
        PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(defendPokemon.pokemonStatsId);
        double weight = pokeStats.weight;
        if (weight <= 9.9) return 20;
        else if (weight <= 24.9) return 40;
        else if (weight <= 49.9) return 60;
        else if (weight <= 99.9) return 80;
        else if (weight <= 199.9) return 100;
        else return 120;
    }
}


