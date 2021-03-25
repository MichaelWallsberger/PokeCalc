package Controller;

import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Classes.Animation.BorderShadow;
import Classes.Animation.ShakeTransition;
import Data.dataSingleton;
import Utilities.Utilities;
import View.PokeStatsView;
import View.TrainerView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TrainerController extends AbstractPokemonController {
    private static TrainerController controller = null;
    public static TrainerController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    // Stats
    public VBox TrainerContainer;
    public AnchorPane TrainerAnchor;
    public AnchorPane SpriteAnchor;
    public AnchorPane PokemonAnchor;
    public VBox FilteredTrainerContainer;
    public VBox FilteredTrainerSpriteContainer;
    public VBox FilteredPokemonContainer;
    public TextField TrainerSearchField;
    public TextField SpriteSearchField;
    public TextField PokemonSearchField;
    // Main
    public TextField TrainerName;
    public TextField FightNumber;
    public ComboBox<String> FoughtAlready;
    public ComboBox<Classes.Route> Route;
    // Trainer
    public VBox TrainerSpriteContainer;
    public Label TrainerSpriteBackground;
    // Pokemon Stats
    public VBox PokemonContainer;
    // ---------- Pokemon ----------
    // Box
    public HBox Pokemon1Box;
    public HBox Pokemon3Box;
    public HBox Pokemon5Box;
    public HBox Pokemon2Box;
    public HBox Pokemon4Box;
    public HBox Pokemon6Box;
    public HBox previousSelectedPokemon;
    // Label
    public Label Pokemon1Name;
    public Label Pokemon2Name;
    public Label Pokemon3Name;
    public Label Pokemon4Name;
    public Label Pokemon5Name;
    public Label Pokemon6Name;
    // Image
    public ImageView Pokemon1Image;
    public ImageView Pokemon2Image;
    public ImageView Pokemon3Image;
    public ImageView Pokemon4Image;
    public ImageView Pokemon5Image;
    public ImageView Pokemon6Image;
    // Options
    public Button AddPokemonButton;
    public Button EditPokemonButton;
    public Button DeletePokemonButton;
    // Options Trainer
    public Button CreateTrainerButton;
    public Button EditTrainerButton;
    public Button DeleteTrainerButton;
    // ------- own variables -------
    public int pokemonId;
    public HBox activePokemonHBox;
    public Label activePokemonSprite;
    public Label activePokemonNickname;
    // Trainer
    public HBox activeTrainerBox;
    public int trainerSpriteId;
    public int whichPokemonIsOnTheLine;
    public int trainerId;
    // Labels for Update
    public Label trainerSpriteLabel;
    public Label fightNumberLabel;
    public Label trainerNameLabel;
    // Pokemons
    public Pokemon pokemon1;
    public Pokemon pokemon2;
    public Pokemon pokemon3;
    public Pokemon pokemon4;
    public Pokemon pokemon5;
    public Pokemon pokemon6;
    // Lists
    public ObservableList<Sprite> trainerSpritesList;
    public ObservableList<Trainer> trainerList;
    public ObservableList<PokemonList> pokemonList;


    // ---------------- Back To Menu ----------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    // ---------------- Search ----------------
    public void searchForTrainer(KeyEvent e) {
        SearchFilter<Trainer> filter = new SearchFilter<>();
        ObservableList<Trainer> list = filter.searchFor(e, FilteredTrainerContainer, TrainerSearchField, trainerList);

        if (list != null) {
            setTrainerContainerVisibility(true);
            TrainerAnchor.getChildren().remove(TrainerContainer);
            TrainerView tv = new TrainerView();
            for (Trainer trainer : list) {
                HBox hbox = trainer.createLabel();
                tv.setTrainerEventHandler(hbox, trainer);
                FilteredTrainerContainer.getChildren().add(hbox);
            }
        } else {
            try {
                TrainerAnchor.getChildren().add(TrainerContainer);
            } catch (Exception ignored) {}
            setTrainerContainerVisibility(false);
        }
    }

    public void searchForSprite(KeyEvent e) {
        SearchFilter<Sprite> filter = new SearchFilter<>();
        ObservableList<Sprite> list = filter.searchFor(e, FilteredTrainerSpriteContainer, SpriteSearchField, trainerSpritesList);

        if (list != null) {
            setTrainerSpriteContainerVisibility(true);
            SpriteAnchor.getChildren().remove(TrainerSpriteContainer);
            TrainerView tv = new TrainerView();
            for (Sprite sprite : list) {
                HBox hbox = sprite.createLabel();
                tv.setTrainerSpriteEventHandler(hbox, sprite);
                FilteredTrainerSpriteContainer.getChildren().add(hbox);
            }
        } else {
            try {
                SpriteAnchor.getChildren().add(TrainerSpriteContainer);
            } catch (Exception ignored) {}
            setTrainerSpriteContainerVisibility(false);
        }
    }

    public void searchForPokemon(KeyEvent e) {
        SearchFilter<PokemonList> filter = new SearchFilter<>();
        ObservableList<PokemonList> list = filter.searchFor(e, FilteredPokemonContainer, PokemonSearchField, pokemonList);

        if (list != null) {
            setPokemonContainerVisibility(true);
            PokemonAnchor.getChildren().remove(PokemonContainer);
            TrainerView tv = new TrainerView();
            for (PokemonList pk : list) {
                HBox hbox = pk.createSmallPokemonLabel();
                tv.setPokemonEventHandler(hbox, pk);
                FilteredPokemonContainer.getChildren().add(hbox);
            }
        } else {
            try {
                PokemonAnchor.getChildren().add(PokemonContainer);
            } catch (Exception ignored) {}
            setPokemonContainerVisibility(false);
        }
    }

    private void setTrainerContainerVisibility(boolean b) {
        // entire List
        TrainerContainer.setVisible(!b);
        // search List
        FilteredTrainerContainer.setVisible(b);
    }

    private void setTrainerSpriteContainerVisibility(boolean b) {
        // entire List
        TrainerSpriteContainer.setVisible(!b);
        // search List
        FilteredTrainerSpriteContainer.setVisible(b);
    }

    private void setPokemonContainerVisibility(boolean b) {
        // entire List
        PokemonContainer.setVisible(!b);
        // search List
        FilteredPokemonContainer.setVisible(b);
    }

    // ---------------- Pokemon----------------
    public void loadPokemon1() {
        whichPokemonIsOnTheLine = 1;
        setPreviousSelectedPokemonCss();
        Pokemon1Box.getStyleClass().clear();
        Pokemon1Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon1Box;

        updatePokemon(pokemon1);
    }

    public void loadPokemon2() {
        whichPokemonIsOnTheLine = 2;
        setPreviousSelectedPokemonCss();
        Pokemon2Box.getStyleClass().clear();
        Pokemon2Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon2Box;

        updatePokemon(pokemon2);
    }

    public void loadPokemon3() {
        whichPokemonIsOnTheLine = 3;
        setPreviousSelectedPokemonCss();
        Pokemon3Box.getStyleClass().clear();
        Pokemon3Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon3Box;

        updatePokemon(pokemon3);
    }

    public void loadPokemon4() {
        whichPokemonIsOnTheLine = 4;
        setPreviousSelectedPokemonCss();
        Pokemon4Box.getStyleClass().clear();
        Pokemon4Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon4Box;

        updatePokemon(pokemon4);
    }

    public void loadPokemon5() {
        whichPokemonIsOnTheLine = 5;
        setPreviousSelectedPokemonCss();
        Pokemon5Box.getStyleClass().clear();
        Pokemon5Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon5Box;

        updatePokemon(pokemon5);
    }

    public void loadPokemon6() {
        whichPokemonIsOnTheLine = 6;
        setPreviousSelectedPokemonCss();
        Pokemon6Box.getStyleClass().clear();
        Pokemon6Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon6Box;

        updatePokemon(pokemon6);
    }

    private void setPreviousSelectedPokemonCss() {
        previousSelectedPokemon.getStyleClass().clear();
        previousSelectedPokemon.getStyleClass().add("passivePokemonBox");
    }

    private void updatePokemon(Pokemon pokemon) {
        pokemonId = pokemon.pokemonId;
        updatePokemonStats(pokemon);
        selectPokemonOnTheMenu(pokemon);

        TrainerView tv = new TrainerView();
        tv.setPokemonOptions(pokemon);
    }

    private void updatePokemonStats(Pokemon pokemon) {
        View.TrainerView tv = new View.TrainerView();
        if (pokemon != null) {
            int index = Utilities.findPokemonInPokemonList(pokemon, pokemonList);
            tv.setPokemonStats(pokemonList.get(index), controller);
        } else tv.setPokemonStats(pokemonList.get(0), controller);
    }

    public void selectPokemonOnTheMenu(Pokemon pokemon) {
        if (TrainerView.previousPokemonBox != null) {
            View.TrainerView.previousPokemonBox.getStyleClass().clear();
            View.TrainerView.previousPokemonBox.getStyleClass().add("createPokemonHBox");
        }

        for (Node node : PokemonContainer.getChildren()) {
            HBox hBox = (HBox) node;
            Label pokemonIdLabel = (Label) hBox.getChildren().get(2);
            int pokemonId = Integer.parseInt(pokemonIdLabel.getText());

            if (pokemonId == pokemon.pokemonId) {
                hBox.getStyleClass().clear();
                hBox.getStyleClass().add("createPokemonHBoxActive");
                View.TrainerView.previousPokemonBox = hBox;
                activePokemonHBox = hBox;
                activePokemonSprite = (Label) hBox.getChildren().get(0);
                activePokemonNickname = (Label) hBox.getChildren().get(1);
            }
        }
    }

    // -------------------------- Options-Pokemon --------------------------
    public void addPokemon() {
        if (SpeciesName.getValue().pokemonStatsId > 0) {
            int result = 0;
            try {
                Data.dataSingleton data = Data.dataSingleton.getInstance();
                API.Database dbAPI = new API.Database();

                // get Values
                String gameName = data.getGameName();
                int pokemonStatsId = SpeciesName.getValue().pokemonStatsId;
                String nickName = Nickname.getText();
                String gender = Gender.getValue();
                int level = Integer.parseInt(Level.getText());
                String natureName = Nature.getValue().natureName;
                int itemId = HeldItem.getValue().itemId;
                int abilityId = Ability.getValue().abilityId;
                int friendship = Integer.parseInt(Friendship.getText());
                int pokeball = PokeBall.getValue().spriteId;
                int metLocation = MetLocation.getValue().routeId;
                boolean isShiny = false;
                int ivHp = Integer.parseInt(IvHp.getText());
                int ivAttack = Integer.parseInt(IvAttack.getText());
                int ivDefense = Integer.parseInt(IvDefense.getText());
                int ivSpecialAttack = Integer.parseInt(IvSpecialAttack.getText());
                int ivSpecialDefense = Integer.parseInt(IvSpecialDefense.getText());
                int ivSpeed = Integer.parseInt(IvSpeed.getText());
                int evHp = Integer.parseInt(EvHp.getText());
                int evAttack = Integer.parseInt(EvAttack.getText());
                int evDefense = Integer.parseInt(EvDefense.getText());
                int evSpecialAttack = Integer.parseInt(EvSpecialAttack.getText());
                int evSpecialDefense = Integer.parseInt(EvSpecialDefense.getText());
                int evSpeed = Integer.parseInt(EvSpeed.getText());
                int attack1 = Attack1Move.getValue().attackId;
                int attack2 = Attack2Move.getValue().attackId;
                int attack3 = Attack3Move.getValue().attackId;
                int attack4 = Attack4Move.getValue().attackId;

                result = dbAPI.addPokemon(gameName, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                        gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                        evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (result > 0) {
                BorderShadow light = new BorderShadow(AddPokemonButton);
                light.playFromStart(); // Animation

                pokemonId = result;
                addPokemonToList(result);

                TrainerView tv = new TrainerView();
                tv.setPokemon(new PokemonList(result, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr));

                API.Database dbAPI = new API.Database();
                Pokemon pokemon = dbAPI.getPokemonFromPokemonId(result);
                selectPokemonOnTheMenu(pokemon);
                tv.setPokemonOptions(pokemon);
            } else {
                ShakeTransition anim = new ShakeTransition(AddPokemonButton);
                anim.playFromStart();
            }
        }
    }

    public void editPokemon() {
        if (SpeciesName.getValue().pokemonStatsId > 0) {
            boolean result = false;
            PokemonList tempPokemon = null;

            try {
                API.Database dbAPI = new API.Database();

                // get Values
                int pokemonStatsId = SpeciesName.getValue().pokemonStatsId;
                String nickName = Nickname.getText();
                String gender = Gender.getValue();
                int level = Integer.parseInt(Level.getText());
                String natureName = Nature.getValue().natureName;
                int itemId = HeldItem.getValue().itemId;
                int abilityId = Ability.getValue().abilityId;
                int friendship = Integer.parseInt(Friendship.getText());
                int pokeball = PokeBall.getValue().spriteId;
                int metLocation = MetLocation.getValue().routeId;
                boolean isShiny = false;
                int ivHp = Integer.parseInt(IvHp.getText());
                int ivAttack = Integer.parseInt(IvAttack.getText());
                int ivDefense = Integer.parseInt(IvDefense.getText());
                int ivSpecialAttack = Integer.parseInt(IvSpecialAttack.getText());
                int ivSpecialDefense = Integer.parseInt(IvSpecialDefense.getText());
                int ivSpeed = Integer.parseInt(IvSpeed.getText());
                int evHp = Integer.parseInt(EvHp.getText());
                int evAttack = Integer.parseInt(EvAttack.getText());
                int evDefense = Integer.parseInt(EvDefense.getText());
                int evSpecialAttack = Integer.parseInt(EvSpecialAttack.getText());
                int evSpecialDefense = Integer.parseInt(EvSpecialDefense.getText());
                int evSpeed = Integer.parseInt(EvSpeed.getText());
                int attack1 = Attack1Move.getValue().attackId;
                int attack2 = Attack2Move.getValue().attackId;
                int attack3 = Attack3Move.getValue().attackId;
                int attack4 = Attack4Move.getValue().attackId;

                tempPokemon = new PokemonList(pokemonId,nickName,pokemonStatsId,0);

                result = dbAPI.updatePokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                        gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                        evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (result) {
                BorderShadow light = new BorderShadow(EditPokemonButton);
                light.playFromStart(); // Animation

                updatePokemonInList(tempPokemon);
                updateLabel(tempPokemon);
            } else {
                ShakeTransition anim = new ShakeTransition(EditPokemonButton);
                anim.playFromStart();
            }
        }
    }

    public void deletePokemon() {
        boolean result = false;

        try {
            API.Database dbAPI = new API.Database();
            result = dbAPI.deletePokemon(pokemonId);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(DeletePokemonButton);
            light.playFromStart(); // Animation

            removePokemonFromLists();
            selectNewFirstPokemon();
        } else {
            ShakeTransition anim = new ShakeTransition(DeletePokemonButton);
            anim.playFromStart();
        }
    }

    // Add Pokemon
    private void addPokemonToList(int pokemonId) {
        PokemonList newPokeStat = new PokemonList(pokemonId, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr);

        View.TrainerView pv = new View.TrainerView();
        pv.createPokemonLabel(newPokeStat); // adds To ViewList
        pokemonList.add(newPokeStat);       // adds To PokemonList
    }

    // Edit Pokemon
    private void updatePokemonInList(PokemonList tempPokemon) {
        int index = 0;
        for (PokemonList pokemon : pokemonList) {
            if (pokemon.pokemonId == tempPokemon.pokemonId) {
                break;
            }
            index++;
        }
        pokemonList.set(index, tempPokemon);
    }

    private void updateLabel(PokemonList pokemon) {
        // Sprite
        API.Database dbAPI = new API.Database();
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(SpeciesName.getValue().pokemonStatsId);
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        // Trainer-Pokemon
        TrainerView tv = new TrainerView();
        tv.setPokemon(new PokemonList(pokemonId, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr));
        // PokemonBox
        activePokemonSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
        activePokemonNickname.setText(Nickname.getText());
        // update EventHandler to load in the right stats
        tv.setPokemonEventHandler(activePokemonHBox, pokemon);
    }

    // Delete Pokemon
    private void removePokemonFromLists() {
        deletePokemonFromPokemonList();
        PokemonContainer.getChildren().remove(activePokemonHBox);
    }

    private void selectNewFirstPokemon() {
        TrainerView tv = new TrainerView();
        tv.setPokemon(dataSingleton.pokemonList);
        selectPokemonOnTheMenu(dataSingleton.tempPokemon);
        tv.setPokemonOptions(dataSingleton.tempPokemon);
    }

    private void deletePokemonFromPokemonList() {
        int index = 0;
        for (PokemonList pokemon : pokemonList) {
            if (pokemon.pokemonId == pokemonId) {
                break;
            }
            index++;
        }
        pokemonList.remove(index);
    }

    // -------------------------- Options-Trainer --------------------------
    public void createTrainer() {
        int trainerId = 0;
        int routeId = 0;
        String nameOfTrainer = "";
        int fightNumber = 0;
        boolean foughtAlready = false;

        try {
            nameOfTrainer = TrainerName.getText();

            if (nameOfTrainer.length() > 0) {
                Data.dataSingleton data = Data.dataSingleton.getInstance();
                API.Database dbAPI = new API.Database();

                // Trainer
                routeId = Route.getValue().routeId;
                fightNumber = Integer.parseInt(FightNumber.getText());
                foughtAlready = FoughtAlready.getValue().equals("True");

                trainerId = dbAPI.addTrainer(data.getGameName(), routeId, nameOfTrainer, fightNumber, foughtAlready, trainerSpriteId,
                        pokemon1.pokemonId, pokemon2.pokemonId, pokemon3.pokemonId, pokemon4.pokemonId, pokemon5.pokemonId, pokemon6.pokemonId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (trainerId > 0) {
            BorderShadow light = new BorderShadow(CreateTrainerButton);
            light.playFromStart(); // Animation

            addTrainerToList(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);
        } else {
            ShakeTransition anim = new ShakeTransition(CreateTrainerButton);
            anim.playFromStart();
        }
    }

    public void editTrainer() {
        API.Database dbAPI = new API.Database();
        boolean result = false;
        int fightNumber = 0;
        String nameOfTrainer = "";

        try {
            nameOfTrainer = TrainerName.getText();

            if (nameOfTrainer.length() > 0) {
                // Trainer
                int routeId = Route.getValue().routeId;
                fightNumber = Integer.parseInt(FightNumber.getText());
                boolean foughtAlready = FoughtAlready.getValue().equals("True");

                result = dbAPI.updateTrainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready, trainerSpriteId,
                        pokemon1.pokemonId, pokemon2.pokemonId, pokemon3.pokemonId, pokemon4.pokemonId, pokemon5.pokemonId, pokemon6.pokemonId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result) {
            BorderShadow light = new BorderShadow(EditTrainerButton);
            light.playFromStart(); // Animation
            // Update Label
            String pokeSpritePath = dbAPI.getImagePathFromSpriteID(trainerSpriteId);
            trainerSpriteLabel.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
            fightNumberLabel.setText(Integer.toString(fightNumber));
            trainerNameLabel.setText(nameOfTrainer);
            // update EventHandler
            TrainerView tv = new TrainerView();
            tv.setTrainerEventHandler(activeTrainerBox, dbAPI.getTrainerFromTrainerId(trainerId));
        } else {
            ShakeTransition anim = new ShakeTransition(EditTrainerButton);
            anim.playFromStart();
        }
    }

    public void deleteTrainer() {
        API.Database dbAPI = new API.Database();
        boolean result = false;

        try {
            if(trainerList.size() > 0) {
                result = dbAPI.deleteOnlyTrainer(trainerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result) {
            BorderShadow light = new BorderShadow(DeleteTrainerButton);
            light.playFromStart(); // Animation

            removeTrainerFromLists();
            selectNewFirstPokeStats();
            // try to delete all the Pokemons the Trainer had
            ArrayList<Pokemon> trainerPokemons = dbAPI.getAllPokemonFromTrainer(trainerId);
            for (Pokemon pokemon : trainerPokemons) {
                result = dbAPI.deletePokemon(pokemon.pokemonId);
                if (result) {
                    selectPokemonOnTheMenu(pokemon);
                    pokemonId = pokemon.pokemonId;
                    removePokemonFromLists();
                }
            }
        } else {
            ShakeTransition anim = new ShakeTransition(DeleteTrainerButton);
            anim.playFromStart();
        }
    }

    // Add Trainer
    private void addTrainerToList(int trainerId, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready) {
        View.TrainerView tv = new View.TrainerView();
        Trainer tempTrainer = new Trainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);

        tv.createTrainerLabel(tempTrainer);
        trainerList.add(tempTrainer);
    }

    // Delete Trainer
    private void removeTrainerFromLists() {
        deleteTrainerInTrainerList();
        TrainerContainer.getChildren().remove(activeTrainerBox);
    }

    private void selectNewFirstPokeStats() {
        View.TrainerView pv = new View.TrainerView();
        pv.selectFirstTrainer();
    }

    private void deleteTrainerInTrainerList() {
        int index = 0;
        for (Trainer trainer : trainerList) {
            if (trainer.trainerId == trainerId) {
                break;
            }
            index++;
        }
        trainerList.remove(index);
    }
}
