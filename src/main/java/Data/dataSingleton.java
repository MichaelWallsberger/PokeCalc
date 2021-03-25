package Data;

import Classes.PokeStats;
import Classes.Pokemon;
import Classes.PokemonList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class dataSingleton {
    private static dataSingleton data = null;
    private String gameName;
    private Stage stage;private Scene menu;
    private static int iv = 31;
    public static final Pokemon tempPokemon = new Pokemon(0,0,0,0,"Bashful",0,0,"No Pokemon", "",0,0,"",0,false,iv,iv,iv, iv, iv,iv,0,0,0,0,0,0);
    public static final PokeStats pokeStats = new PokeStats(0, "No Pokemon", 0, "Slow", 0, 0, 10, 10, 10, 10, 10, 10);
    public static final PokemonList pokemonList = new PokemonList(0,  "No Pokemon", 0, 0);

    dataSingleton() {}

    public static dataSingleton getInstance() {
        if (data == null) data = new dataSingleton();
        return data;
    }

    // Setter & Getter
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getMenu() {
        return menu;
    }

    public void setMenu(Scene menu) {
        this.menu = menu;
    }

    public void setIv(int i) {
        iv = i;
    }

    public int getIv() {
        return iv;
    }

    public ArrayList<String> generateButtonColors() {
        ArrayList<String> buttonColor = new ArrayList<>();

        buttonColor.add("-fx-background-color: linear-gradient(to right, #67b26b, #4ca2cb);");               // Mild
        buttonColor.add("-fx-background-color: linear-gradient(to right, #2980b9, #6dd5fa, #ffffff);");      // Cool Sky
        buttonColor.add("-fx-background-color: linear-gradient(to right, #f12711, #f5af19);");               // Flare
        buttonColor.add("-fx-background-color: linear-gradient(to right, #1f4037, #99f2c8);");               // Harvey
        buttonColor.add("-fx-background-color: linear-gradient(to right, #c31432, #240b36);");               // Witching Hour
        buttonColor.add("-fx-background-color: linear-gradient(to right, #8a2387, #e94057, #f27121);");      // Wiretap
        buttonColor.add("-fx-background-color: linear-gradient(to right, #fdc830, #f37335);");               // Citrus Peel
        buttonColor.add("-fx-background-color: linear-gradient(to right, #636363, #a2ab58);");               // Shifty
        buttonColor.add("-fx-background-color: linear-gradient(to right, #ad5389, #3c1053);");               // eXpresso
        buttonColor.add("-fx-background-color: linear-gradient(to right, #355c7d, #6c5b7b, #c06c84);");      // Red Sunset
        buttonColor.add("-fx-background-color: linear-gradient(to right, #355c7d, #6c5b7b, #c06c84);");      // Sand to Blue
        buttonColor.add("-fx-background-color: linear-gradient(to right, #11998e, #38ef7d);");               // Quepal
        buttonColor.add("-fx-background-color: linear-gradient(to right, #fc5c7d, #6a82fb);");               // Sublime Light
        buttonColor.add("-fx-background-color: linear-gradient(to right, #fc466b, #3f5efb);");               // Sublime Vivid
        buttonColor.add("-fx-background-color: linear-gradient(to right, #c94b4b, #4b134f);");               // Bighead
        buttonColor.add("-fx-background-color: linear-gradient(to right, #fffbd5, #b20a2c);");               // Relaxing Red
        buttonColor.add("-fx-background-color: linear-gradient(to right, #00b09b, #96c93d);");               // Ohhappiness
        buttonColor.add("-fx-background-color: linear-gradient(to right, #3c3b3f, #605c3c);");               // Selenium
        buttonColor.add("-fx-background-color: linear-gradient(to right, #00f260, #0575e6);");               // Rainbow Blue
        buttonColor.add("-fx-background-color: linear-gradient(to right, #fc4a1a, #f7b733);");               // Orange Fun
        buttonColor.add("-fx-background-color: linear-gradient(to right, #74ebd5, #acb6e5);");               // Digital Water
        buttonColor.add("-fx-background-color: linear-gradient(to right, #ff9966, #ff5e62);");               // Orange Coral
        buttonColor.add("-fx-background-color: linear-gradient(to right, #0cebeb, #20e3b2, #29ffc6);");      // Subu
        buttonColor.add("-fx-background-color: linear-gradient(to right, #36d1dc, #5b86e5);");               // Scooter
        buttonColor.add("-fx-background-color: linear-gradient(to right, #159957, #155799);");               // Crystal Clear
        buttonColor.add("-fx-background-color: linear-gradient(to right, #007991, #78ffd6);");               // Chitty Chitty Bang Bang
        buttonColor.add("-fx-background-color: linear-gradient(to right, #56ccf2, #2f80ed);");               // Blue Skies
        buttonColor.add("-fx-background-color: linear-gradient(to right, #f2994a, #f2c94c);");               // Sunkist
        buttonColor.add("-fx-background-color: linear-gradient(to right, #4ac29a, #bdfff3);");               // Cinnamint
        buttonColor.add("-fx-background-color: linear-gradient(to right, #b2fefa, #0ed2f7);");               // Maldives
        buttonColor.add("-fx-background-color: linear-gradient(to right, #43c6ac, #f8ffae);");               // Honey Drew
        buttonColor.add("-fx-background-color: linear-gradient(to right, #c0c0aa, #1cefff);");               // Cocoaa Ice
        buttonColor.add("-fx-background-color: linear-gradient(to right, #f3904f, #3b4371);");               // Dawn
        buttonColor.add("-fx-background-color: linear-gradient(to right, #ef32d9, #89fffd);");               // Azure Pop
        buttonColor.add("-fx-background-color: linear-gradient(to right, #3a6186, #89253e);");               // Love Couple
        buttonColor.add("-fx-background-color: linear-gradient(to right, #ff4b1f, #1fddff);");               // Ali
        buttonColor.add("-fx-background-color: linear-gradient(to right, #b3ffab, #12fff7);");               // Neon Life
        buttonColor.add("-fx-background-color: linear-gradient(to right, #12c2e9, #c471ed, #f64f59);" );      // JShine

        return buttonColor;
    }
}
