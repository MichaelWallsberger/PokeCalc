/*
  In the Database Database   I'm struggling in the Database Wow Wow
  It doesn't even matter if there is no hope As the madness of the system grows
  Database Database   Just living in the Database
 */

package API;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Database {
    private Connection connection;
    private String path;

    public Database() {
        connection = null;
        setPath();
        System.setProperty("derby.language.sequence.preallocator", "1");
    }

    private void connectToDB() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            connection = DriverManager.getConnection("jdbc:derby:" + path);
        } catch (Exception e) {
            System.out.println("No Database Connection\nError: " + e);
//            e.printStackTrace();
        }

    }
    // ---------------------- GET ----------------------
    public int getUserIdFromSpecificGame(String gameName) {
        try {
            connectToDB();

            String query = "SELECT trainerid FROM trainer WHERE gamename = ? and nameoftrainer = 'User'";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int trainerId = rs.getInt("TRAINERID");

             connection.close();
            return trainerId;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public LinkedHashMap<String, String> getGameNameAndSprite() {
        try {
            connectToDB();
            // make query
            String query = "SELECT gamename.gamename, sprite.locationofsprite FROM gamename " +
                    "LEFT JOIN sprite ON gamename.spriteid = sprite.spriteid " +
                    "ORDER BY gamename ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // create HashMap to store data
            LinkedHashMap<String, String> games = new LinkedHashMap<>();
            while (rs.next()) {
                games.put(rs.getString("gamename"), rs.getString("locationofsprite"));
            }

            connection.close();
            return games;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }

        return null;
    }

    public String getStartPageImage() {
        try {
            connectToDB();

            // make query
            String query = "SELECT sprite.locationofsprite FROM StartPageImage " +
                    "JOIN sprite ON startpageimage.backgroundimageid = sprite.spriteid";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get name
            rs.next();
            String name = rs.getString(1);

            connection.close();
            return name;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public String getImagePathFromSpriteID(int spriteId) {
        try {
            connectToDB();

            String query = "SELECT locationofsprite FROM Sprite WHERE spriteid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, spriteId);

            ResultSet rs = stmt.executeQuery();

            rs.next();
            String imagePath = rs.getString("LOCATIONOFSPRITE");

            connection.close();
            return imagePath;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Item getItemFromItemId(int itemId) {
        try {
            connectToDB();

            String query = "SELECT * FROM Item WHERE itemid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, itemId);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            int spriteId = rs.getInt("SPRITEID");
            String nameOfItem = rs.getString("NAMEOFITEM");
            String description = rs.getString("DESCRIPTION");
            double itemValue = rs.getDouble("ITEMVALUE");
            int buyPrice = rs.getInt("BUYPRICE");
            int sellPrice = rs.getInt("SELLPRICE");

            Item item = new Item(itemId, spriteId, nameOfItem, description, itemValue, buyPrice, sellPrice);

            connection.close();
            return item;
        } catch (Exception e) {
            System.out.println("No item was found for the Pokemon. Error: " + e);
            return null;
        }
    }

    public Route getRouteFromRouteId(int routeId) {
        try {
            connectToDB();

            String query = "SELECT * FROM route WHERE routeid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, routeId);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String gameName = rs.getString("GAMENAME");
            String routeName = rs.getString("ROUTENAME");
            boolean pokemonCaught = rs.getBoolean("POKEMONCAUGHT");

            Route route = new Route(routeId, gameName, routeName, pokemonCaught);

            connection.close();
            return route;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Attack getAttackFromAttackId(int attackId) {
        try {
            connectToDB();

            String query = "SELECT * FROM attack WHERE attackid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, attackId);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String typeName = rs.getString("TYPENAME");
            String attackName = rs.getString("ATTACKNAME");
            String description = rs.getString("DESCRIPTION");
            int power = rs.getInt("POWER");
            int pp = rs.getInt("PP");
            String effect = rs.getString("EFFECT");
            String secondaryEffect = rs.getString("SECONDARYEFFECT");
            int priority = rs.getInt("PRIORITY");
            String targets = rs.getString("TARGETS");
            String category = rs.getString("CATEGORY");
            boolean makesContact = rs.getBoolean("MAKESCONTACT");
            boolean protectAffected = rs.getBoolean("PROTECTAFFECTED");
            boolean magicCoatAffected = rs.getBoolean("MAGICCOATAFFECTED");
            boolean snatchAffected = rs.getBoolean("SNATCHAFFECTED");
            boolean mirrorMoveAffected = rs.getBoolean("MIRRORMOVEAFFECTED");
            boolean kingsRockAffected = rs.getBoolean("KINGSROCKAFFECTED");

            Attack attack = new Attack(attackId, typeName, attackName, description, power, pp, effect, secondaryEffect, priority,
                    targets, category, makesContact, protectAffected, magicCoatAffected, snatchAffected, mirrorMoveAffected, kingsRockAffected);

            connection.close();
            return attack;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Nature getNatureFromNatureName(String natureName) {
        try {
            connectToDB();

            String query = "SELECT * FROM nature WHERE naturename = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, natureName);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String statUp = rs.getString("STATUP");
            String statDown = rs.getString("STATDOWN");

            Nature nature = new Nature(natureName, statUp, statDown);

            connection.close();
            return nature;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int getSpriteIdFromSpecificPokemon(int pokemonStatsId) {
        try {
            connectToDB();

            // make query
            String query = "SELECT spriteid FROM pokemonsprite " +
                    "WHERE pokemonstatsid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonStatsId);
            // execute query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int spriteID = rs.getInt("SPRITEID");

            connection.close();
            return spriteID;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }

        return -1;
    }

    public int getSpriteIdFromSpecificTrainer(int trainerId) {
        try {
            connectToDB();

            // make query
            String query = "SELECT spriteid FROM trainersprite " +
                    "WHERE trainerid = ? AND identify = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.setString(2, "Small");
            // execute query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int spriteID = rs.getInt("SPRITEID");

            connection.close();
            return spriteID;
        } catch (Exception e) {
            System.out.println("No Sprite was found for " + trainerId + ". Error: " + e);
//            e.printStackTrace();
        }

        return -1;
    }

    public ObservableList<String> getAllGames() {
        try {
            connectToDB();

            // make query
            String query = "Select gamename From gamename";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<String> games = FXCollections.observableArrayList();
            games.add("None");
            while (rs.next()) {
                games.add(rs.getString("gamename"));
            }

            connection.close();
            return games;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Sprite> getAllSprites() {
        try {
            connectToDB();

            // make query
            String query = "Select * From sprite";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Sprite> spritesList = FXCollections.observableArrayList();

            Sprite sprite = new Sprite(-1, "None", "none");
            spritesList.add(sprite);
            while (rs.next()) {
                int spriteId = rs.getInt("spriteid");
                String spriteName = rs.getString("spritename");
                String locationOfSprite = rs.getString("locationofsprite");

                spritesList.add(new Sprite(spriteId, spriteName, locationOfSprite));
            }

            connection.close();
            return spritesList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Sprite> getAllPokemonSprites() {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM sprite " +
                    "WHERE locationofsprite like '/Img/PokemonSprites/%'";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Sprite> spritesList = FXCollections.observableArrayList();

            while (rs.next()) {
                int spriteId = rs.getInt("spriteid");
                String spriteName = rs.getString("spritename");
                String locationOfSprite = rs.getString("locationofsprite");

                spritesList.add(new Sprite(spriteId, spriteName, locationOfSprite));
            }

            connection.close();
            return spritesList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Nature> getAllNature() {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM nature ORDER BY naturename ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Nature> natureList = FXCollections.observableArrayList();

            while (rs.next()) {
                String natureName = rs.getString("NATURENAME");
                String statUp = rs.getString("STATUP");
                String statDown = rs.getString("STATDOWN");

                natureList.add(new Nature(natureName, statUp, statDown));
            }

            connection.close();
            return natureList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Item> getAllItemsFromGame(String gamename) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM itemspecific " +
                            "JOIN item ON itemspecific.itemid = item.itemid " +
                            "WHERE gamename = ? " +
                            "ORDER BY nameofitem ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Item> itemList = FXCollections.observableArrayList();

            while (rs.next()) {
                int itemId = rs.getInt("ITEMID");
                int spriteId = rs.getInt("SPRITEID");
                String nameOfItem = rs.getString("NAMEOFITEM");
                String description = rs.getString("DESCRIPTION");
                double itemValue = rs.getDouble("ITEMVALUE");
                int buyPrice = rs.getInt("BUYPRICE");
                int sellPrice = rs.getInt("SELLPRICE");

                itemList.add(new Item(itemId, spriteId, nameOfItem, description, itemValue, buyPrice, sellPrice));
            }

            connection.close();
            return itemList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Pokemon> getAllPokemonFromTrainer(int trainerId) {
        try {
            connectToDB();

            // get pokemonId
            String query = "SELECT pokemonid FROM trainerpokemon WHERE trainerid = ? ORDER BY positionofpokemon ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Integer> pokemonIdList = new ArrayList<Integer>();

            while(rs.next()) {
                int pokeminId = rs.getInt("POKEMONID");
                pokemonIdList.add(pokeminId);
            }

            // get Pokemon
            ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
            for (int pokemonId : pokemonIdList) {
                query = "SELECT * FROM pokemon WHERE pokemonid = ?";

                stmt = connection.prepareStatement(query);
                stmt.setInt(1, pokemonId);

                rs = stmt.executeQuery();
                rs.next();

                int pokemonStatsId = rs.getInt("POKEMONSTATSID");
                int itemId = rs.getInt("ITEMID");
                int abilityId = rs.getInt("ABILITYID");
                String natureName = rs.getString("NATURENAME");
                int pokeball = rs.getInt("POKEBALL");
                int metLocation = rs.getInt("METLOCATION");
                String nickname = rs.getString("NICKNAME");
                String statusCondition = rs.getString("STATUSCONDITION");
                int level = rs.getInt("LEVEL");
                int experience = rs.getInt("EXPERIENCE");
                String gender = rs.getString("GENDER");
                int friendship = rs.getInt("FRIENDSHIP");
                boolean isShiny = rs.getBoolean("ISSHINY");
                int ivHp = rs.getInt("IVHP");
                int ivAttack = rs.getInt("IVATTACK");
                int ivDefense = rs.getInt("IVDEFENSE");
                int ivSpecialAttack = rs.getInt("IVSPECIALATTACK");
                int ivSpecialDefense = rs.getInt("IVSPECIALDEFENSE");
                int ivSpeed = rs.getInt("IVSPEED");
                int evHp = rs.getInt("EVHP");
                int evAttack = rs.getInt("EVATTACK");
                int evDefense = rs.getInt("EVDEFENSE");
                int evSpecialAttack = rs.getInt("EVSPECIALATTACK");
                int evSpecialDefense = rs.getInt("EVSPECIALDEFENSE");
                int evSpeed = rs.getInt("EVSPEED");

                Pokemon pokemon = new Pokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickname,
                        statusCondition, level, experience, gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack,
                        ivSpecialDefense, ivSpeed, evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed);

                pokemonList.add(pokemon);
            }

            connection.close();
            return pokemonList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Ability> getAllAbilitiesFromGame(String gamename) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM abilityspecific " +
                    "JOIN ability ON abilityspecific.abilityid = ability.abilityid " +
                    "WHERE gamename = ? " +
                    "ORDER BY abilityname ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Ability> abilityList = FXCollections.observableArrayList();

            while (rs.next()) {
                int abilityId = rs.getInt("ABILITYID");
                String abilityName = rs.getString("ABILITYNAME");
                String abilityDescription = rs.getString("ABILITYDESCRIPTION");

                abilityList.add(new Ability(abilityId, abilityName, abilityDescription));
            }

            connection.close();
            return abilityList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Route> getALLMetLocationsFromGame(String gamename) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM route " +
                            "WHERE gamename = ? " +
                            "ORDER BY routename ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Route> routeList = FXCollections.observableArrayList();

            while (rs.next()) {
                int routeId = rs.getInt("ROUTEID");
                String gameName = rs.getString("GAMENAME");
                String routeName = rs.getString("ROUTENAME");
                boolean pokemonCaught = rs.getBoolean("POKEMONCAUGHT");

                routeList.add(new Route(routeId, gameName, routeName, pokemonCaught));
            }

            connection.close();
            return routeList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Attack> getAllAttacksFromGame(String gamename) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM attackspecific " +
                    "JOIN attack ON attackspecific.attackid = attack.attackid " +
                    "WHERE gamename = ? " +
                    "ORDER BY attackname ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Attack> routeList = FXCollections.observableArrayList();

            while (rs.next()) {
                int attackId = rs.getInt("ATTACKID");
                String typeName = rs.getString("TYPENAME");
                String attackName = rs.getString("ATTACKNAME");
                String description = rs.getString("DESCRIPTION");
                int power = rs.getInt("POWER");
                int pp = rs.getInt("PP");
                String effect = rs.getString("EFFECT");
                String secondaryEffect = rs.getString("SECONDARYEFFECT");
                int priority = rs.getInt("PRIORITY");
                String targets = rs.getString("TARGETS");
                String category = rs.getString("CATEGORY");
                boolean makesContact = rs.getBoolean("MAKESCONTACT");
                boolean protectAffected = rs.getBoolean("PROTECTAFFECTED");
                boolean magicCoatAffected = rs.getBoolean("MAGICCOATAFFECTED");
                boolean snatchAffected = rs.getBoolean("SNATCHAFFECTED");
                boolean mirrorMoveAffected = rs.getBoolean("MIRRORMOVEAFFECTED");
                boolean kingsRockAffected = rs.getBoolean("KINGSROCKAFFECTED");

                routeList.add(new Attack(attackId, typeName, attackName, description, power, pp, effect, secondaryEffect, priority,
                        targets, category, makesContact, protectAffected, magicCoatAffected, snatchAffected, mirrorMoveAffected, kingsRockAffected));
            }

            connection.close();
            return routeList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Sprite> getAllPokeballs() {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM sprite " +
                            "WHERE locationofsprite like '/Img/Pokeballs/%'";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Sprite> pokeballList = FXCollections.observableArrayList();

            while (rs.next()) {
                int spriteID = rs.getInt("SPRITEID");
                String gameName = rs.getString("SPRITENAME");
                String routeName = rs.getString("LOCATIONOFSPRITE");

                pokeballList.add(new Sprite(spriteID, gameName, routeName));
            }

            connection.close();
            return pokeballList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<Sprite> getAllTrainerSprites() {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM sprite " +
                    "WHERE locationofsprite like '/Img/Trainers/%' " +
                    "ORDER BY spritename ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<Sprite> trainerSpriteList = FXCollections.observableArrayList();

            while (rs.next()) {
                int spriteID = rs.getInt("SPRITEID");
                String gameName = rs.getString("SPRITENAME");
                String routeName = rs.getString("LOCATIONOFSPRITE");

                trainerSpriteList.add(new Sprite(spriteID, gameName, routeName));
            }
            connection.close();
            return trainerSpriteList;
        } catch (Exception e) {
//            System.out.println(e);
            e.printStackTrace();
        }

        return FXCollections.observableArrayList();
    }

    public ObservableList<String> getAllTypeFromSpecificGame(String gameName) {
        try {
            connectToDB();

            String query = "SELECT DISTINCT typeattack FROM typechart WHERE gamename = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);

            ResultSet rs = stmt.executeQuery();

            ObservableList<String> typeList = FXCollections.observableArrayList();

            while(rs.next()) {
                String typeName = rs.getString("TYPEATTACK");
                typeList.add(typeName);
            }

            connection.close();
            return typeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<PokeStats> getPokeStatsFromSpecificGame(String gameName) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM pokestatsspecific " +
                            "JOIN pokestats ON pokestatsspecific.pokemonstatsid = pokestats.pokemonstatsid " +
                            "WHERE gamename = ? " +
                            "ORDER BY dexnr ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<PokeStats> pokeStatsList = FXCollections.observableArrayList();

            while (rs.next()) {
                int pokemonstatsid = rs.getInt("POKEMONSTATSID");
                String nameOfPokemon = rs.getString("NAMEOFPOKEMON");
                int dexNr = rs.getInt("DEXNR");
                String expGrowthRate = rs.getString("EXPGROWTHRATE");
                double weight = rs.getDouble("Weight");
                double height = rs.getDouble("HEIGHT");
                int baseHp = rs.getInt("BASEHP");
                int baseAttack = rs.getInt("BASEATTACK");
                int baseDefense = rs.getInt("BASEDEFENSE");
                int baseSpecialAttack = rs.getInt("BASESPECIALATTACK");
                int baseSpecialDefense = rs.getInt("BASESPECIALDEFENSE");
                int baseSpeed = rs.getInt("BASESPEED");

                pokeStatsList.add(new PokeStats(pokemonstatsid, nameOfPokemon, dexNr, expGrowthRate, weight, height,
                        baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed));
            }

            connection.close();
            return pokeStatsList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<PokeStatsList> getPokeStatsListFromSpecificGame(String gameName) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM pokestatsspecific " +
                    "JOIN pokestats ON pokestatsspecific.pokemonstatsid = pokestats.pokemonstatsid " +
                    "WHERE gamename = ? " +
                    "ORDER BY dexnr ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<PokeStatsList> pokeStatsList = FXCollections.observableArrayList();

            while (rs.next()) {
                int pokemonStatsId = rs.getInt("POKEMONSTATSID");
                int dexNr = rs.getInt("DEXNR");
                String nameOfPokemon = rs.getString("NAMEOFPOKEMON");

                pokeStatsList.add(new PokeStatsList(pokemonStatsId, dexNr, nameOfPokemon));
            }

            connection.close();
            return pokeStatsList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ObservableList<PokemonList> getPokemonNicknameWithPokeStatsIdFromSpecificGame(String gamename) {
        try {
            connectToDB();

            // make query
            String query = "SELECT pokemon.pokemonid, pokemon.nickname, pokemon.pokemonstatsid, pokestats.dexnr FROM pokemonspecific " +
                    "INNER JOIN pokemon ON pokemonspecific.pokemonid = pokemon.pokemonid " +
                    "INNER JOIN pokestats ON pokemon.pokemonstatsid = pokestats.pokemonstatsid " +
                    "WHERE gamename = ? " +
                    "ORDER BY dexnr ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // store Pokemons
            ObservableList<PokemonList> pokemonList = FXCollections.observableArrayList();

            while (rs.next()) {
                int pokemonId = rs.getInt("POKEMONID");
                String nickname = rs.getString("NICKNAME");
                int pokemonStatsId = rs.getInt("POKEMONSTATSID");
                int dexNr = rs.getInt("DEXNR");

                pokemonList.add(new PokemonList(pokemonId, nickname, pokemonStatsId, dexNr));
            }

            connection.close();
            return pokemonList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ObservableList<Trainer> getAllTrainerFromSpecificGame(String gameName) {
        try {
            connectToDB();

            String query = "Select * FROM trainer WHERE gamename = ? ORDER BY fightnumber ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);

            ResultSet rs = stmt.executeQuery();

            // store trainers
            ObservableList<Trainer> trainerList = FXCollections.observableArrayList();

            while(rs.next()) {
                int trainerId = rs.getInt("TRAINERID");
                int routeId = rs.getInt("ROUTEID");
                String nameOfTrainer = rs.getString("NAMEOFTRAINER");
                int fightNumber = rs.getInt("FIGHTNUMBER");
                boolean foughtAlready = rs.getBoolean("FOUGHTALREADY");

                if (!nameOfTrainer.equals("User")) trainerList.add(new Trainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready));
            }

            connection.close();
            return trainerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pokemon getPokemonFromPokemonId(int pokemonId) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM Pokemon where pokemonid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonId);
            // execute query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int pokemonStatsId = rs.getInt("POKEMONSTATSID");
            int itemId = rs.getInt("ITEMID");
            int abilityId = rs.getInt("ABILITYID");
            String natureName = rs.getString("NATURENAME");
            int pokeball = rs.getInt("POKEBALL");
            int metLocation = rs.getInt("METLOCATION");
            String nickname = rs.getString("NICKNAME");
            String statusCondition = rs.getString("STATUSCONDITION");
            int level = rs.getInt("LEVEL");
            int experience = rs.getInt("EXPERIENCE");
            String gender = rs.getString("GENDER");
            int friendship = rs.getInt("FRIENDSHIP");
            boolean isShiny = rs.getBoolean("ISSHINY");
            int ivHp = rs.getInt("IVHP");
            int ivAttack = rs.getInt("IVATTACK");
            int ivDefense = rs.getInt("IVDEFENSE");
            int ivSpecialAttack = rs.getInt("IVSPECIALATTACK");
            int ivSpecialDefense = rs.getInt("IVSPECIALDEFENSE");
            int ivSpeed = rs.getInt("IVSPEED");
            int evHp = rs.getInt("EVHP");
            int evAttack = rs.getInt("EVATTACK");
            int evDefense = rs.getInt("EVDEFENSE");
            int evSpecialAttack = rs.getInt("EVSPECIALATTACK");
            int evSpecialDefense = rs.getInt("EVSPECIALDEFENSE");
            int evSpeed = rs.getInt("EVSPEED");

            Pokemon pokemon = new Pokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickname,
                    statusCondition, level, experience, gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack,
                    ivSpecialDefense, ivSpeed, evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed);

            connection.close();
            return pokemon;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Ability getAbilityFromAbilityId(int abilityId) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM ability where abilityid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, abilityId);
            // execute query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            String abilityName = rs.getString("ABILITYNAME");
            String abilityDescription = rs.getString("ABILITYDESCRIPTION");

            Ability ability = new Ability(abilityId, abilityName, abilityDescription);

            connection.close();
            return ability;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public PokeStats getPokeStatsFromPokemonStatsId(int pokemonstatsid) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM pokestats where pokemonstatsid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonstatsid);
            // execute query
            ResultSet rs = stmt.executeQuery();
            rs.next();

            String nameOfPokemon = rs.getString("NAMEOFPOKEMON");
            int dexNr = rs.getInt("DEXNR");
            String expGrowthRate = rs.getString("EXPGROWTHRATE");
            double weight = rs.getDouble("WEIGHT");
            double height = rs.getDouble("HEIGHT");
            int baseHp = rs.getInt("BASEHP");
            int baseAttack = rs.getInt("BASEATTACK");
            int baseDefense = rs.getInt("BASEDEFENSE");
            int baseSpecialAttack = rs.getInt("BASESPECIALATTACK");
            int baseSpecialDefense = rs.getInt("BASESPECIALDEFENSE");
            int baseSpeed = rs.getInt("BASESPEED");


            PokeStats pokestats = new PokeStats(pokemonstatsid, nameOfPokemon, dexNr, expGrowthRate, weight, height,
                    baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed);

            connection.close();
            return pokestats;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ObservableList<PokemonAttack> getAttacksFromASpecificPokemon(int pokemonId) {
        try {
            connectToDB();

            // make query
            String query = "SELECT * FROM pokemonattack where pokemonid = ? ORDER BY attackposition ASC";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonId);
            // execute query
            ResultSet rs = stmt.executeQuery();

            // get Games
            ObservableList<PokemonAttack> attackList = FXCollections.observableArrayList();

            while (rs.next()) {
                int attackId = rs.getInt("ATTACKID");
                int attackPosition = rs.getInt("ATTACKPOSITION");

                attackList.add(new PokemonAttack(pokemonId, attackId, attackPosition));
            }

            connection.close();
            return attackList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<PokemonList> getAllPokemonFromUser(String gamename) {
        try {
            connectToDB();

            String query = "SELECT pokemon.pokemonid, pokemon.nickname, pokestats.pokemonstatsid, pokestats.dexnr " +
                    "FROM trainer " +
                    "INNER JOIN trainerpokemon ON trainer.trainerid = trainerpokemon.trainerid " +
                    "INNER JOIN pokemon ON trainerpokemon.pokemonid = pokemon.pokemonid " +
                    "INNER JOIN pokestats ON pokemon.pokemonstatsid = pokestats.pokemonstatsid " +
                    "WHERE trainer.nameoftrainer = 'User' AND trainer.gamename = ? " +
                    "ORDER BY dexnr ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gamename);
            ResultSet rs = stmt.executeQuery();

            ArrayList<PokemonList> pokeList = new ArrayList<PokemonList>();

            while(rs.next()) {
                int pokemonId = rs.getInt("POKEMONID");
                String nickname = rs.getString("NICKNAME");
                int pokemonStatsId = rs.getInt("POKEMONSTATSID");
                int dexNr = rs.getInt("DEXNR");

                pokeList.add(new PokemonList(pokemonId, nickname, pokemonStatsId, dexNr));
            }


            connection.close();
            return pokeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Badge> getAllBadgesFromGame(String gameName) {
        try {
            connectToDB();

            String query = "SELECT * FROM badge WHERE gamename = ? ORDER BY gymorder ASC";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Badge> badgeList = new ArrayList<Badge>();
            while(rs.next()) {
                int badgeId = rs.getInt("BADGEID");
                int spriteId = rs.getInt("SPRITEID");
                String badgeName = rs.getString("BADGENAME");
                String description = rs.getString("DESCRIPTION");
                int levelCap = rs.getInt("LEVELCAP");
                boolean ownBadge = rs.getBoolean("OWNBADGE");
                String statBoost = rs.getString("STATBOOST");
                double boostPower = rs.getDouble("BOOSTPOWER");
                int gymOrder = rs.getInt("GYMORDER");

                badgeList.add(new Badge(badgeId, spriteId, badgeName, description, levelCap, ownBadge, statBoost, boostPower, gymOrder));
            }

            connection.close();
            return badgeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getTypesFromPokemonStatsId(int pokemonStatsId) {
        try {
            connectToDB();

            String query = "SELECT typename FROM pokemontype WHERE pokemonstatsid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonStatsId);

            ResultSet rs = stmt.executeQuery();

            ArrayList<String> pokemonTypes = new ArrayList<String>();
            while (rs.next()) {
                String type = rs.getString("TYPENAME");
                pokemonTypes.add(type);
            }

            connection.close();
            return pokemonTypes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getMultiplyFactorForTypeAttack(String gameName, String attackType, String defendType) {
        try {
            connectToDB();

            String query = "SELECT multiplyfactor FROM typechart WHERE gamename = ? AND typeattack = ? AND typedefend = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            stmt.setString(2, attackType);
            stmt.setString(3, defendType);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            double multiplyFactor = rs.getDouble("MULTIPLYFACTOR");

            connection.close();
            return multiplyFactor;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public Trainer getTrainerFromTrainerId(int activeTrainerId) {
        try {
            connectToDB();

            String query = "SELECT * FROM trainer WHERE trainerid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, activeTrainerId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int trainerId = rs.getInt("TRAINERID");
            int routeId = rs.getInt("ROUTEID");
            String nameOfTrainer = rs.getString("NAMEOFTRAINER");
            int fightNumber = rs.getInt("FIGHTNUMBER");
            boolean foughtAlready = rs.getBoolean("FOUGHTALREADY");

            Trainer trainer = new Trainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);

            connection.close();
            return trainer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ---------------------- Set ----------------------
    public void setStartPageImage(String locationOfImage) {
        try {
            connectToDB();

            // get spriteid
            String query = "Select spriteId from sprite where locationOfSprite = ?";
            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, locationOfImage);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int spriteid = rs.getInt("spriteId");

            // input into Table: StartPageImage
            query = "update startPageImage set backgroundimageid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, spriteid);
            stmt.execute();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ---------------------- Add ----------------------
    public boolean addGame(String name, int spriteid, String template) {
        try {
            connectToDB();
            PreparedStatement stmt = null;

            if (spriteid != -1) {
                String query = "INSERT INTO gamename (gamename, spriteid) " +
                        "VALUES (?, ?)";

                // prepare query
                stmt = connection.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setInt(2, spriteid);
            } else {
                String query = "INSERT INTO gamename (gamename) " +
                        "VALUES (?)";

                // prepare query
                stmt = connection.prepareStatement(query);
                stmt.setString(1, name);
            }

            // execute query
            stmt.execute();
            if (template.equals("Default")) return true;
            // todo -> template of new Game
            // else get the template setting and set them



            connection.close();
            return true;
        } catch (Exception e) {
//            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public void addTrainerUser(String gamename) {
        try {
            connectToDB();

            String query = "INSERT INTO Trainer (nameoftrainer, gamename) " +
                    "VALUES (?, ?)";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "User");
            stmt.setString(2, gamename);

            // execute query
            stmt.execute();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int addPokemon(String gameName, int pokemonStatsId, int itemId, int abilityId, String natureName, int pokeball, int metLocation,
    String nickname, int level, String gender, int friendship, boolean isShiny, int ivHp, int ivAttack, int ivDefense, int ivSpecialAttack,
                              int ivSpecialDefense, int ivSpeed, int evHp, int evAttack, int evDefense, int evSpecialAttack, int evSpecialDefense, int evSpeed,
                              int attack1, int attack2, int attack3, int attack4) {
        try {
            connectToDB();

            String query = "INSERT INTO pokemon (pokemonstatsid, itemid, abilityid, naturename, pokeball, metlocation, " +
                    "nickname, level, gender, friendship, isshiny, ivhp, ivattack, ivdefense, ivspecialattack, ivspecialdefense, ivspeed, " +
                    "evhp, evattack, evdefense, evspecialattack, evspecialdefense, evspeed) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, pokemonStatsId);          // pokemonStatsId
            if (itemId > 0) stmt.setInt(2, itemId); else stmt.setNull(2, java.sql.Types.INTEGER);    // itemId
            stmt.setInt(3, abilityId);               // abilityId
            stmt.setString(4,  natureName);          // natureName
            stmt.setInt(5, pokeball);                // pokeball
            stmt.setInt(6, metLocation);             // metLocation
            stmt.setString(7, nickname);             // nickName
            stmt.setInt(8, level);                   // level
            stmt.setString(9, gender);               // gender
            stmt.setInt(10, friendship);             // friendship
            stmt.setBoolean(11, isShiny);            // isShiny
            stmt.setInt(12, ivHp);                   // ivHp
            stmt.setInt(13, ivAttack);               // ivAttack
            stmt.setInt(14, ivDefense);              // ivDefense
            stmt.setInt(15, ivSpecialAttack);        // ivSpecialAttack
            stmt.setInt(16, ivSpecialDefense);       // ivSpecialDefense
            stmt.setInt(17, ivSpeed);                // ivSpeed
            stmt.setInt(18, evHp);                   // evHp
            stmt.setInt(19, evAttack);               // evAttack
            stmt.setInt(20, evDefense);              // evDefense
            stmt.setInt(21, evSpecialAttack);        // evSpecialAttack
            stmt.setInt(22, evSpecialDefense);       // evSpecialDefense
            stmt.setInt(23, evSpeed);                // evSpeed

            // execute query
            stmt.executeUpdate();
            // get id from the last input
            ResultSet result = stmt.getGeneratedKeys();
            result.next();

            // insert into pokemonSpecific
            query = "INSERT INTO pokemonspecific (gamename, pokemonid) VALUES (?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            stmt.setInt(2, result.getInt(1));

            stmt.executeUpdate();

            // add attacks
            addPokemonAttacks(result.getInt(1), attack1, attack2, attack3, attack4);

            return result.getInt(1);
        } catch (Exception e) {
//            System.out.println(e);
            e.printStackTrace();
        }

        return 0;
    }

    public boolean addPokemonAttacks(int pokemonId, int attack1, int attack2, int attack3, int attack4) {
        try {
            connectToDB();
            // insert Attacks
            int[] attacks = {attack1, attack2, attack3, attack4};
            for (int i = 1; i <= attacks.length; i++) {
                if (attacks[i-1] > 0) {
                    String query = "INSERT INTO pokemonattack (pokemonid, attackid, attackposition) VALUES (?, ?, ?)";

                    PreparedStatement stmt2 = connection.prepareStatement(query);
                    stmt2.setInt(1, pokemonId);
                    stmt2.setInt(2, attacks[i - 1]);
                    stmt2.setInt(3, i);

                    stmt2.executeUpdate();
                }
            }
            connection.close();
            return true;
        } catch (Exception e) {
//            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public int addPokeStatsToGame(String gameName, String nameOfPokemon, int dexNr, String expGrowthRate, double height, double weight,
                                   int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack,
                                   int baseSpecialDefense, int baseSpeed, int spriteId, String typename1, String typename2) {
        try {
            connectToDB();

            String query = "INSERT INTO pokestats (nameofpokemon, dexnr, expgrowthrate, height, weight, " +
                    "basehp, baseattack, basedefense, basespecialattack, basespecialdefense, basespeed) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nameOfPokemon.substring(0, 1).toUpperCase() + nameOfPokemon.substring(1));
            stmt.setInt(2, dexNr);
            stmt.setString(3, expGrowthRate);
            stmt.setDouble(4, height);
            stmt.setDouble(5, weight);
            stmt.setInt(6, baseHp);
            stmt.setInt(7, baseAttack);
            stmt.setInt(8, baseDefense);
            stmt.setInt(9, baseSpecialAttack);
            stmt.setInt(10, baseSpecialDefense);
            stmt.setInt(11, baseSpeed);

            stmt.execute();

            // get id from the last input
            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            int pokeStatsId = result.getInt(1);

            // insert into pokemonSpecific
            query = "INSERT INTO pokestatsspecific (gamename, pokemonstatsid) VALUES (?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setString(1, gameName);
            stmt.setInt(2, pokeStatsId);

            stmt.executeUpdate();

            // insert into pokemonSprite
            query = "INSERT INTO pokemonsprite (pokemonstatsid, spriteid) VALUES (?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokeStatsId);
            stmt.setInt(2, spriteId);
            stmt.executeUpdate();

            // insert Pokmeontypes
            if (typename1.equals(typename2)) typename2 = "";
            String[] types = {typename1, typename2};

            for (String type : types) {
                if (type != null && !type.isEmpty()) {
                    query = "INSERT INTO pokemontype (pokemonstatsid, typename) VALUES (?, ?)";

                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, pokeStatsId);
                    stmt.setString(2, type);
                    stmt.executeUpdate();
                }
            }

            return pokeStatsId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int addTrainer(String gameName, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready, int trainerSpriteId) {
        try {
            connectToDB();

            String query = "INSERT INTO trainer (routeid, nameoftrainer, fightnumber, foughtalready, gamename) " +
                    "Values (?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, routeId);
            stmt.setString(2, nameOfTrainer);
            stmt.setInt(3, fightNumber);
            stmt.setBoolean(4, foughtAlready);
            stmt.setString(5, gameName);
            stmt.execute();

            // get id from the last input
            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            int trainerId = result.getInt(1);

            // Trainer Sprite
            query = "INSERT INTO trainersprite (trainerid, spriteid, identify) VALUES (?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.setInt(2, trainerSpriteId);
            stmt.setString(3, "Small");
            stmt.execute();

            connection.close();
            return trainerId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int addTrainer(String gameName, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready, int trainerSpriteId, int pokemonId1, int pokemonId2, int pokemonId3, int pokemonId4, int pokemonId5, int pokemonId6) {
        try {
            connectToDB();

            String query = "INSERT INTO trainer (routeid, nameoftrainer, fightnumber, foughtalready, gamename) " +
                    "Values (?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, routeId);
            stmt.setString(2, nameOfTrainer);
            stmt.setInt(3, fightNumber);
            stmt.setBoolean(4, foughtAlready);
            stmt.setString(5, gameName);
            stmt.execute();

            // get id from the last input
            ResultSet result = stmt.getGeneratedKeys();
            result.next();
            int trainerId = result.getInt(1);

            // Trainer Sprite
            query = "INSERT INTO trainersprite (trainerid, spriteid, identify) VALUES (?, ?, ?)";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.setInt(2, trainerSpriteId);
            stmt.setString(3, "Small");
            stmt.execute();

            // Trainer Pokemon
            int[] pokemonIdArray = {pokemonId1, pokemonId2, pokemonId3, pokemonId4, pokemonId5, pokemonId6};
            for (int i = 0; i < 6; i++) {
                if (pokemonIdArray[i] > 0) {
                    query = "INSERT INTO trainerpokemon (trainerid, pokemonid, positionofpokemon) VALUES (?, ?, ?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, trainerId);
                    stmt.setInt(2, pokemonIdArray[i]);
                    stmt.setInt(3, i+1);
                    stmt.execute();
                }
            }

            connection.close();
            return trainerId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean addPokemonToUser(String gamename, int trainerId, int pokemonId, int positionOfPokemon) {
        try {
            connectToDB();

            String query = "INSERT INTO trainerpokemon (trainerid, pokemonid, positionofpokemon) " +
                            "VALUES (?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.setInt(2, pokemonId);
            stmt.setInt(3, positionOfPokemon);
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------------------- Update ----------------------
    public boolean updatePokemon(int pokemonId, int pokemonStatsId, int itemId, int abilityId, String natureName, int pokeball, int metLocation,
                              String nickname, int level, String gender, int friendship, boolean isShiny, int ivHp, int ivAttack, int ivDefense, int ivSpecialAttack,
                              int ivSpecialDefense, int ivSpeed, int evHp, int evAttack, int evDefense, int evSpecialAttack, int evSpecialDefense, int evSpeed,
                              int attack1, int attack2, int attack3, int attack4) {
        try {
            connectToDB();

            String query = "UPDATE pokemon " +
                    "SET pokemonstatsid = ?, itemid = ?, abilityid = ?, naturename = ?, pokeball = ?, metlocation = ?, nickname = ?, level = ?, gender = ?, " +
                    "friendship = ?, isshiny = ?, ivhp = ?, ivattack = ?, ivdefense = ?, ivspecialattack = ? , ivspecialdefense = ?, " +
                    "ivspeed = ?, evhp = ?, evattack = ?, evdefense = ?, evspecialattack = ?, evspecialdefense = ?, evspeed = ? " +
                    "WHERE pokemonid = ?";
            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, pokemonStatsId);
            if (itemId > 0) stmt.setInt(2, itemId); else stmt.setNull(2, java.sql.Types.INTEGER);    // itemId
            stmt.setInt(3, abilityId);               // abilityId
            stmt.setString(4,  natureName);          // natureName
            stmt.setInt(5, pokeball);                // pokeball
            stmt.setInt(6, metLocation);             // metLocation
            stmt.setString(7, nickname);             // nickName
            stmt.setInt(8, level);                   // level
            stmt.setString(9, gender);               // gender
            stmt.setInt(10, friendship);             // friendship
            stmt.setBoolean(11, isShiny);            // isShiny
            stmt.setInt(12, ivHp);                   // ivHp
            stmt.setInt(13, ivAttack);               // ivAttack
            stmt.setInt(14, ivDefense);              // ivDefense
            stmt.setInt(15, ivSpecialAttack);        // ivSpecialAttack
            stmt.setInt(16, ivSpecialDefense);       // ivSpecialDefense
            stmt.setInt(17, ivSpeed);                // ivSpeed
            stmt.setInt(18, evHp);                   // evHp
            stmt.setInt(19, evAttack);               // evAttack
            stmt.setInt(20, evDefense);              // evDefense
            stmt.setInt(21, evSpecialAttack);        // evSpecialAttack
            stmt.setInt(22, evSpecialDefense);       // evSpecialDefense
            stmt.setInt(23, evSpeed);                // evSpeed
            stmt.setInt(24, pokemonId);               // where pokemonID

            // execute query
            stmt.executeUpdate();

            // delete Attacks first
            deleteAllAttacksFromSpecificPokemon(pokemonId);
            // insert Attacks
            return addPokemonAttacks(pokemonId, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
//            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePokeStats(int pokemonStatsId, String nameOfPokemon, int dexNr, String expGrowthRate, double height, double weight,
                                   int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack, int baseSpecialDefense, int baseSpeed, int spriteId,
                                    String typename1, String typename2) {
        try {
            connectToDB();

            String query = "UPDATE pokestats " +
                    "set nameofpokemon = ?, dexnr = ?, expgrowthrate = ?, height = ?, weight = ?, basehp = ?, " +
                    "baseattack = ?, basedefense = ?, basespecialattack = ?, basespecialdefense = ?, basespeed = ? " +
                    "WHERE pokemonstatsid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nameOfPokemon.substring(0,1).toUpperCase() + nameOfPokemon.substring(1));
            stmt.setInt(2, dexNr);
            stmt.setString(3, expGrowthRate);
            stmt.setDouble(4, height);
            stmt.setDouble(5, weight);
            stmt.setInt(6, baseHp);
            stmt.setInt(7, baseAttack);
            stmt.setInt(8, baseDefense);
            stmt.setInt(9, baseSpecialAttack);
            stmt.setInt(10, baseSpecialDefense);
            stmt.setInt(11, baseSpeed);
            stmt.setInt(12, pokemonStatsId);

            stmt.executeUpdate();

            // Update Sprite
            query = "UPDATE pokemonsprite set spriteid = ? WHERE pokemonstatsid = ?";

            stmt = connection.prepareStatement(query);
            stmt.setInt(1, spriteId);
            stmt.setInt(2, pokemonStatsId);

            stmt.executeUpdate();

            // Delete old Type
            query = "DELETE FROM pokemontype WHERE pokemonstatsid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonStatsId);
            stmt.execute();

            // insert Pokmeontypes
            if (typename1.equals(typename2)) typename2 = "";
            String[] types = {typename1, typename2};

            for (String type : types) {
                if (type != null && !type.isEmpty()) {
                    query = "INSERT INTO pokemontype (pokemonstatsid, typename) VALUES (?, ?)";

                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, pokemonStatsId);
                    stmt.setString(2, type);
                    stmt.executeUpdate();
                }
            }


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTrainer(int trainerId, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready, int trainerSpriteId, int pokemonId1, int pokemonId2, int pokemonId3, int pokemonId4, int pokemonId5, int pokemonId6) {
        try {
            connectToDB();

            String query = "UPDATE trainer " +
                            "SET routeid = ?, nameoftrainer = ?, fightnumber = ?, foughtalready = ? " +
                            "WHERE trainerid = ?";

            // Edit Trainer
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, routeId);
            stmt.setString(2, nameOfTrainer);
            stmt.setInt(3, fightNumber);
            stmt.setBoolean(4, foughtAlready);
            stmt.setInt(5, trainerId);
            stmt.executeUpdate();

            // Edit TrainerSprite
            query = "UPDATE trainersprite SET spriteid = ? " +
                    "WHERE trainerid = ? and identify = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerSpriteId);
            stmt.setInt(2, trainerId);
            stmt.setString(3,"Small");
            stmt.executeUpdate();

            // delete aLl Pokemon from Trainer
            query = "DELETE FROM trainerpokemon WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // Add Pokemon to the Trainer
            // Trainer Pokemon
            int[] pokemonIdArray = {pokemonId1, pokemonId2, pokemonId3, pokemonId4, pokemonId5, pokemonId6};

            for (int i = 0; i < 6; i++) {
                if (pokemonIdArray[i] > 0) {
                    query = "INSERT INTO trainerpokemon (trainerid, pokemonid, positionofpokemon) VALUES (?, ?, ?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, trainerId);
                    stmt.setInt(2, pokemonIdArray[i]);
                    stmt.setInt(3, i+1);
                    stmt.execute();
                }
            }

            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTrainerPokemon(int trainerId, int pokemonId1, int pokemonId2, int pokemonId3, int pokemonId4, int pokemonId5, int pokemonId6) {
        try {
            connectToDB();

            String query = "UPDATE trainer " +
                    "SET routeid = ?, nameoftrainer = ?, fightnumber = ?, foughtalready = ? " +
                    "WHERE trainerid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            // delete aLl Pokemon from Trainer
            query = "DELETE FROM trainerpokemon WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // Add Pokemon to the Trainer
            int[] pokemonIdArray = {pokemonId1, pokemonId2, pokemonId3, pokemonId4, pokemonId5, pokemonId6};

            for (int i = 0; i < 6; i++) {
                if (pokemonIdArray[i] > 0) {
                    query = "INSERT INTO trainerpokemon (trainerid, pokemonid, positionofpokemon) VALUES (?, ?, ?)";
                    stmt = connection.prepareStatement(query);
                    stmt.setInt(1, trainerId);
                    stmt.setInt(2, pokemonIdArray[i]);
                    stmt.setInt(3, i+1);
                    stmt.execute();
                }
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // ---------------------- Delete ----------------------
    public boolean deleteGame(String name) {
        try {
            connectToDB();

            // ------ delete all trainers ------
            String query = "DELETE FROM trainersprite " +
                    "WHERE trainerid IN ( " +
                    "SELECT trainer.trainerid FROM trainer " +
                    "INNER JOIN trainersprite ON trainer.trainerid = trainersprite.trainerid " +
                    "WHERE gamename = ? )";
            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            // execute query
            stmt.execute();

            // ------ delete all trainers pokemons ------
            query = "DELETE FROM trainerpokemon " +
                    "WHERE trainerid IN ( " +
                    "SELECT trainer.trainerid FROM trainer " +
                    "INNER JOIN trainerpokemon ON trainer.trainerid = trainerpokemon.trainerid " +
                    "WHERE gamename = ? )";
            // prepare query
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            // execute query
            stmt.execute();

            // ------ delete all badges ------
            query = "DELETE FROM badge " +
                    "WHERE gamename = ?";
            // prepare query
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            // execute query
            stmt.execute();

            // ------ delete all movesets ------
            query = "DELETE FROM moveset " +
                    "WHERE gamename = ?";
            // prepare query
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            // execute query
            stmt.execute();

            // ------ delete Game ------
            query = "DELETE FROM gamename " +
                    "WHERE gamename = ?";
            // prepare query
            stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            // execute query
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePokemon(int pokemonId) {
        try {
            connectToDB();

            // make query
            String query = "DELETE FROM pokemon " +
                    "WHERE pokemonid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonId);
            // execute query
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Check if a Trainer has a Pokemon. Error: " + e);
//            e.printStackTrace();
            return false;
        }
    }

    public void deleteAllAttacksFromSpecificPokemon(int pokemonId) {
        try {
            connectToDB();

            String query = "DELETE FROM pokemonattack " +
                    "WHERE pokemonid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonId);

            stmt.execute();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean deletePokeStats(int pokemonStatsId) {
        try {
            connectToDB();

            // make query
            String query = "DELETE FROM pokestats WHERE pokemonstatsid = ?";

            // prepare query
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemonStatsId);
            // execute query
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteOnlyTrainer(int trainerId) {
        try {
            connectToDB();
            // there is something wrong with the foreign key that doesn't allow me just to delete the trainer
            // so i have to delete everything else first. (probably no delete on cascade)

            // Delete from Sprite
            String query = "DELETE FROM trainersprite WHERE trainerid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // delete from TrainerPokemon
            query = "DELETE FROM trainerpokemon WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // Delete from Trainer
            query = "DELETE FROM trainer WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTrainerAndPokemons(int trainerId) {
        try {
            connectToDB();
            // there is something wrong with the foreign key that doesn't allow me just to delete the trainer
            // so i have to delete everything else first. (probably no delete on cascade)

            // Delete from Sprite
            String query = "DELETE FROM trainersprite WHERE trainerid = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // delete from TrainerPokemon
            query = "DELETE FROM trainerpokemon WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            // Delete from Trainer
            query = "DELETE FROM trainer WHERE trainerid = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.execute();

            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removePokemonFromUser(int trainerId, int pokemonId) {
        try {
            connectToDB();

            String query = "DELETE FROM TRAINERPOKEMON WHERE trainerid = ? AND pokemonid = ?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, trainerId);
            stmt.setInt(2, pokemonId);
            stmt.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------------------- Database Path ----------------------
    private void setPath() {
        path = String.valueOf(this.getClass().getResource("/Database"));

        // jar
        if (path.contains("jar:")) {
            path = "Database";
//            path = path.substring(0, path.length() - 9); // removes "/Database" -> go to parent file
//            path = path.replace("file:/", "");
//            path = path.substring(0, 4) + "(" + path.substring(4, path.length() - 1) + ")Database"; // jar:(path)db
        }
        // IDE
        else {
            path = path.replace("file:/", "");
        }
    }
}

// Old query for 'getAllPokemonFromUser()'
// String query = "SELECT pokemon.pokemonid, pokemon.nickname, pokestats.pokemonstatsid, pokestats.dexnr FROM pokemonspecific " +
// "INNER JOIN pokemon ON pokemonspecific.pokemonid = pokemon.pokemonid " +
// "INNER JOIN pokestats ON pokemon.pokemonstatsid = pokestats.pokemonstatsid " +
// "INNER JOIN trainerpokemon ON pokemon.pokemonid = trainerpokemon.pokemonid " +
// "INNER JOIN trainer ON trainerpokemon.trainerid = trainer.trainerid " +
// "WHERE trainer.nameoftrainer = 'User' " +
// "AND pokemonspecific.gamename IN (SELECT gamename.gamename FROM gamename WHERE gamename.gamename = ?)";

// Query to find the Pokemon and Sprite on what the User has in a specific Game
    // select pokemon.pokemonid, sprite.locationofsprite from pokemonspecific
    // inner join pokemon on pokemonspecific.pokemonid = pokemon.pokemonid
    // inner join trainerpokemon on pokemon.pokemonid = trainerpokemon.pokemonid
    // inner join trainer on trainerpokemon.trainerid = trainer.trainerid
    // inner join pokemonsprite on pokemon.pokemonstatsid = pokemonsprite.pokemonstatsid
    // inner join sprite on pokemonsprite.spriteid = sprite.spriteid
    // where trainer.nameoftrainer = 'User'
    // and pokemonspecific.gamename in (Select gamename.gamename from gamename where gamename.gamename = 'Emerald')
