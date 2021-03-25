package Classes;

public class Route {
    public int routeId;
    public String gameName;
    public String routeName;
    public boolean pokemonCaught;

    public Route(int routeId, String gameName, String routeName, boolean pokemonCaught) {
        this.routeId = routeId;
        this.gameName = gameName;
        this.routeName = routeName;
        this.pokemonCaught = pokemonCaught;
    }

    @Override
    public String toString() {
        return routeName;
    }
}
