package Controller;

import Classes.Abstract.AbstractPokeStatsController;
import Classes.Animation.BorderShadow;
import Classes.Animation.ShakeTransition;
import Classes.PokeStats;
import Classes.SearchFilter;
import Classes.Sprite;
import Utilities.Utilities;
import View.PokeStatsView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PokeStatsController extends AbstractPokeStatsController {
    // static method to create instance of Singleton class
    private static PokeStatsController controller = null;
    public static PokeStatsController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    public TextField PokeStatsSearchField;
    public TextField SpriteSearchField;
    public VBox PokemonContainer;
    public VBox FilteredPokeStatsContainer;
    public VBox FilteredSpriteList;
    public AnchorPane PokemonAnchor;
    public AnchorPane SpriteAnchor;
    // Options
    public Button CreatePokeStatsButton;
    public Button EditPokeStatsButton;
    public Button DeletePokeStatsButton;
    // others
    public HBox previousPokeStatsBox;
    public HBox activePokeStatsBox;
    public int pokemonStatsId;
    public Label smallSpriteOfPokemon;
    public Label dexNrOfPokemon;

    public void backToMenu() {
        Utilities.backToMenu();
    }

    // --------------- Search ---------------
    public void searchForPokeStats(KeyEvent e) {
        SearchFilter<PokeStats> filter = new SearchFilter<>();
        ObservableList<PokeStats> list = filter.searchFor(e, FilteredPokeStatsContainer, PokeStatsSearchField, pokeStatsList);

        if (list != null) {
            setPokeStatsContainerVisibility(true);
            PokemonAnchor.getChildren().remove(PokemonContainer);
            PokeStatsView pv = new PokeStatsView();
            for (PokeStats pokeStats : list) {
                HBox hbox = pokeStats.createLabel();
                pv.setPokeStatsEventHandler(hbox, pokeStats);
                FilteredPokeStatsContainer.getChildren().add(hbox);
            }
        } else {
            try {
                PokemonAnchor.getChildren().add(PokemonContainer);
            } catch (Exception ignored) {}
            setPokeStatsContainerVisibility(false);
        }
    }

    public void searchForSprite(KeyEvent e) {
        SearchFilter<Sprite> filter = new SearchFilter<>();
        ObservableList<Sprite> list = filter.searchFor(e, FilteredSpriteList, SpriteSearchField, pokeSpriteList);

        if (list != null) {
            setSpriteContainerVisibility(true);
            SpriteAnchor.getChildren().remove(ImageContainer);
            PokeStatsView pv = new PokeStatsView();
            for (Sprite sprite : list) {
                HBox hbox = sprite.createLabel();
                pv.setSpriteEventHandler(hbox, sprite);
                FilteredSpriteList.getChildren().add(hbox);
            }
        } else {
            try {
                SpriteAnchor.getChildren().add(ImageContainer);
            } catch (Exception ignored) { }
            setSpriteContainerVisibility(false);
        }
    }

    private void setPokeStatsContainerVisibility(boolean b) {
        // entire List
        PokemonContainer.setVisible(!b);
        // search List
        FilteredPokeStatsContainer.setVisible(b);
    }

    private void setSpriteContainerVisibility(boolean b) {
        // entire List
        ImageContainer.setVisible(!b);
        // search List
        FilteredSpriteList.setVisible(b);
    }

    // --------------- Options ---------------
    public void createPokeStats() {
        int pokeStatsId = 0;
        String nameOfPokemon = "";
        int dexNr = 0;
        String expGrowthRate = "";
        double height = 0;
        double weight = 0;
        int baseHp = 0;
        int baseAttack = 0;
        int baseDefense = 0;
        int baseSpecialAttack = 0;
        int baseSpecialDefense = 0;
        int baseSpeed = 0;

        try {
            if (Species.getText().length() > 0) {
                // get Values
                nameOfPokemon = Species.getText();
                dexNr = Integer.parseInt(DexNr.getText());
                expGrowthRate = ExpGrowthRate.getValue();
                height = Double.parseDouble(Height.getText());
                weight = Double.parseDouble(Weight.getText());
                baseHp = Integer.parseInt(BaseHp.getText());
                baseAttack = Integer.parseInt(BaseAttack.getText());
                baseDefense = Integer.parseInt(BaseDefense.getText());
                baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
                baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
                baseSpeed = Integer.parseInt(BaseSpeed.getText());
                String type1 = PokemonType1.getValue();
                String type2 = PokemonType2.getValue();

                API.Database dbAPI = new API.Database();
                Data.dataSingleton data = Data.dataSingleton.getInstance();

                //  check if Species already in PokeStats
                if (!Utilities.findPokeStatsInList(nameOfPokemon, pokeStatsList))
                    pokeStatsId = dbAPI.addPokeStatsToGame(data.getGameName(), nameOfPokemon, dexNr, expGrowthRate, height,
                            weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed, spriteId, type1, type2);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // create Species
        if (pokeStatsId > 0) {
            BorderShadow light = new BorderShadow(CreatePokeStatsButton);
            light.playFromStart(); // Animation

            addPokeStatsToList(pokeStatsId, nameOfPokemon, dexNr, expGrowthRate, height, weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed);
            doesSpeciesAlreadyExist();
        } else {
            ShakeTransition anim = new ShakeTransition(CreatePokeStatsButton);
            anim.playFromStart();
        }
    }

    public void editPokeStats() {
        boolean result = false;

        if (doesSpeciesAlreadyExist()) {
            try {
                API.Database dbAPI = new API.Database();

                String species = Species.getText();
                int dexNr = Integer.parseInt(DexNr.getText());
                double height = Double.parseDouble(Height.getText());
                double weight = Double.parseDouble(Weight.getText());
                String expGrowthRate = ExpGrowthRate.getValue();
                int baseHp = Integer.parseInt(BaseHp.getText());
                int baseAttack = Integer.parseInt(BaseAttack.getText());
                int baseDefense = Integer.parseInt(BaseDefense.getText());
                int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
                int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
                int baseSpeed = Integer.parseInt(BaseSpeed.getText());
                String type1 = PokemonType1.getValue();
                String type2 = PokemonType2.getValue();

                result = dbAPI.updatePokeStats(pokemonStatsId, species, dexNr, expGrowthRate, height, weight,
                        baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed, spriteId, type1, type2);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (result) {
            BorderShadow light = new BorderShadow(EditPokeStatsButton);
            light.playFromStart(); // Animation

            setPokeStatsAndImage();
            doesSpeciesAlreadyExist();
        } else {
            ShakeTransition anim = new ShakeTransition(EditPokeStatsButton);
            anim.playFromStart();
        }
    }

    public void deletePokeStats() {
        if (pokeStatsList.size() > 0) {
            API.Database dbAPI = new API.Database();
            boolean result = dbAPI.deletePokeStats(pokemonStatsId);

            if (result) {
                BorderShadow light = new BorderShadow(DeletePokeStatsButton);
                light.playFromStart(); // Animation

                removePokemonFromLists();
                selectNewFirstPokeStats();
            } else {
                ShakeTransition anim = new ShakeTransition(DeletePokeStatsButton);
                anim.playFromStart();
            }
        }
    }

    // Add PokeStats
    private void addPokeStatsToList(
            int pokeStatsId,
            String nameOfPokemon,
            int dexNr,
            String expGrowthRate,
            double height,
            double weight,
            int baseHp,
            int baseAttack,
            int baseDefense,
            int baseSpecialAttack,
            int baseSpecialDefense,
            int baseSpeed) {
        nameOfPokemon = nameOfPokemon.substring(0, 1).toUpperCase() + nameOfPokemon.substring(1);
        PokeStats newPokeStat = new PokeStats(pokeStatsId, nameOfPokemon, dexNr, expGrowthRate, height, weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed);

        View.PokeStatsView pv = new View.PokeStatsView();
        pv.createPokemonLabel(newPokeStat); // adds To ViewList
        pokeStatsList.add(newPokeStat);     // adds To PokeStatsList
    }

    // Edit PokeStats
    private void setPokeStatsAndImage() {
        setPokeStats();
        setSmallSpriteNextToLabel();
    }

    private void setPokeStats() {
        String species = Species.getText();
        species = species.substring(0, 1).toUpperCase() + species.substring(1);
        int dexNr = Integer.parseInt(DexNr.getText());
        double height = Double.parseDouble(Height.getText());
        double weight = Double.parseDouble(Weight.getText());
        String expGrowthRate = ExpGrowthRate.getValue();
        int baseHp = Integer.parseInt(BaseHp.getText());
        int baseAttack = Integer.parseInt(BaseAttack.getText());
        int baseDefense = Integer.parseInt(BaseDefense.getText());
        int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
        int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
        int baseSpeed = Integer.parseInt(BaseSpeed.getText());

        int index = Utilities.findIndexOfPokeStats(nameOfPokemon.getText(), pokeStatsList);
        if (index >= 0) {
            pokeStatsList.get(index).nameOfPokemon = species;
            pokeStatsList.get(index).dexNr = dexNr;
            pokeStatsList.get(index).height = height;
            pokeStatsList.get(index).weight = weight;
            pokeStatsList.get(index).expGrowthRate = expGrowthRate;
            pokeStatsList.get(index).baseHp = baseHp;
            pokeStatsList.get(index).baseAttack = baseAttack;
            pokeStatsList.get(index).baseDefense = baseDefense;
            pokeStatsList.get(index).baseSpecialAttack = baseSpecialAttack;
            pokeStatsList.get(index).baseSpecialDefense = baseSpecialDefense;
            pokeStatsList.get(index).baseSpeed = baseSpeed;
        }

        nameOfPokemon.setText(species); // works since the Label is a reference to the Object holding the name
        dexNrOfPokemon.setText(String.format("%03d", dexNr));
    }

    private void setSmallSpriteNextToLabel() {
        API.Database dbAPI = new API.Database();
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        smallSpriteOfPokemon.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
    }

    // Delete PokeStats
    private void removePokemonFromLists() {
        deletePokeStatsFromPokeStatsList();
        PokemonContainer.getChildren().remove(activePokeStatsBox);
    }

    private void selectNewFirstPokeStats() {
        View.PokeStatsView pv = new View.PokeStatsView();
        pv.selectFirstPokeStats();
    }

    private void deletePokeStatsFromPokeStatsList() {
        int index = 0;
        for (PokeStats pokeStats : pokeStatsList) {
            if (pokeStats.pokemonStatsId == pokemonStatsId) {
                break;
            }
            index++;
        }
        pokeStatsList.remove(index);
    }
}
