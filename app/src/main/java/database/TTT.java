package database;

/**
 * Created by tom on 7/10/15.
 * Class that is used to be a temporary holder for the data of a Tic-Tac-Toe game
 */
public class TTT {
    private String name = "",
                   p1 = "",
                   p2 = "";
    private String[] tiles = new String[9];
    public TTT(){}

    /**
     * Builds the object with basic params
     * @param name - name of the game
     * @param p1 - p1 name
     * @param p2 - p2 name
     */
    public TTT(String name, String p1, String p2){
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
    }
    /**
     * Builds the object with basic params
     * @param name - name of the game
     * @param p1 - p1 name
     * @param p2 - p2 name
     * @param tiles - the tiles on the board
     */
    public TTT(String name, String p1, String p2, String[] tiles){
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.tiles = tiles;
    }
    public void addTiles(String[] add){
        this.tiles = add;
    }
    public String getName(){
        return name;
    }
    public String getP1(){
        return p1;
    }
    public String getP2(){
        return p2;
    }
    public String[] getTiles(){
        return tiles;
    }
}
