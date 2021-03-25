package Controller;

import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Classes.Animation.BorderShadow;
import Classes.Animation.ShakeTransition;
import Utilities.Utilities;
import View.PokemonView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PokemonController extends AbstractPokemonController {
    // static method to create instance of Singleton class
    private static PokemonController controller = null;

    public static PokemonController getInstance() {
        return controller;
    }

    @FXML
    public void initialize() {
        controller = this;
    }

    public TextField PokemonPokeStatsSearchField;
    // Container
    public VBox PokeStatsContainer;
    public VBox PokemonContainer;
    public VBox FilteredPokeStatsContainer;
    public VBox FilteredPokemonContainer;
    public AnchorPane PokeStatsAnchor;
    public AnchorPane PokemonAnchor;
    // Options
    public Button AddPokemonButton;
    public Button EditPokemonButton;
    public Button DeletePokemonButton;
    // filter
    public ObservableList<PokeStats> pokeStatsList;
    public ObservableList<PokemonList> pokemonList;
    // other
    public int pokemonId;
    public HBox activePokemonHBox;
    public Label activePokemonSprite;
    public Label activePokemonDexNr;
    public Label activePokemonNickname;

    // -------------------------- Menu --------------------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    // -------------------------- Search --------------------------
    public void searchForPokemonAndPokeStats(KeyEvent e) {
        ObservableList<PokeStats> filteredPokeStatsList = FXCollections.observableArrayList();
        ObservableList<PokemonList> filteredPokemonList = FXCollections.observableArrayList();
        KeyCode code = e.getCode();

        emptyFilterList();

        // search String
        String filter = PokemonPokeStatsSearchField.getText();

        // empty String when ESC is pressed
        if (code == KeyCode.ESCAPE) {
            filter = "";
            PokemonPokeStatsSearchField.setText("");
        }

        if (filter.length() == 0 || PokemonPokeStatsSearchField.getText().length() == 0) {
            setSearchContainerVisibility(false);
            try {
                PokeStatsAnchor.getChildren().add(PokeStatsContainer);
                PokemonAnchor.getChildren().add(PokemonContainer);
            } catch (Exception ignored) {}
        }
        // create Filtered List
        else {
            setSearchContainerVisibility(true);
            PokeStatsAnchor.getChildren().remove(PokeStatsContainer);
            PokemonAnchor.getChildren().remove(PokemonContainer);

            PokemonView pv = new PokemonView();
            String txtUsr = filter.toLowerCase();

            // PokeStats
            pokeStatsList.stream().filter(el -> el.nameOfPokemon.toLowerCase().contains(txtUsr)).forEach(filteredPokeStatsList::add);
            for (PokeStats pokeStats : filteredPokeStatsList) {
                HBox hbox = pokeStats.createLabel();
                pv.setPokemonLabelEventHandler(hbox, pokeStats);
                FilteredPokeStatsContainer.getChildren().add(hbox);
            }

            // Pokemon
            pokemonList.stream().filter(el -> el.nameOfPokemon().toLowerCase().contains(txtUsr)).forEach(filteredPokemonList::add);
            for (PokemonList pokeList : filteredPokemonList) {
                HBox hbox = pokeList.createPokemonLabel();
                pv.setPokemonLabelEventHandler(hbox, pokeList);
                FilteredPokemonContainer.getChildren().add(hbox);
            }
        }
    }

    private void emptyFilterList() {
        FilteredPokeStatsContainer.getChildren().clear();
        FilteredPokemonContainer.getChildren().clear();
    }

    private void setSearchContainerVisibility(boolean b) {
        // entire List
        PokeStatsContainer.setVisible(!b);
        PokemonContainer.setVisible(!b);
        // search List
        FilteredPokeStatsContainer.setVisible(b);
        FilteredPokemonContainer.setVisible(b);
    }

    // -------------------------- Options --------------------------
    public void addPokemon() {
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

            addPokemonToList(result);
        } else {
            ShakeTransition anim = new ShakeTransition(AddPokemonButton);
            anim.playFromStart();
        }
    }

    public void editPokemon() {
        boolean result = false;

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

            result = dbAPI.updatePokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            BorderShadow light = new BorderShadow(EditPokemonButton);
            light.playFromStart(); // Animation

            updateLabel();
        } else {
            ShakeTransition anim = new ShakeTransition(EditPokemonButton);
            anim.playFromStart();
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
        String nickname = Nickname.getText().substring(0, 1).toUpperCase() + Nickname.getText().substring(1);
        PokemonList newPokeStat = new PokemonList(pokemonId, nickname, SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr);

        View.PokemonView pv = new View.PokemonView();
        pv.createPokemonLabel(newPokeStat); // adds To ViewList
        pokemonList.add(newPokeStat);       // adds To PokemonList
    }

    // Edit Pokemon
    private void updateLabel() {
        // Sprite
        API.Database dbAPI = new API.Database();
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(SpeciesName.getValue().pokemonStatsId);
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        activePokemonSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
        // DexNr
        activePokemonDexNr.setText(String.format("%03d", SpeciesName.getValue().dexNr));
        // Nickname
        activePokemonNickname.setText(Nickname.getText());
    }

    // Delete Pokemon
    private void removePokemonFromLists() {
        deletePokemonFromPokemonList();
        PokemonContainer.getChildren().remove(activePokemonHBox);
    }

    private void selectNewFirstPokemon() {
        View.PokemonView pv = new View.PokemonView();
        pv.selectFirstPokemon();
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


}
