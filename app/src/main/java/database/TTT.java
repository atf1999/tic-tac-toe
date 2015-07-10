package database;

/**
 * Created by tom on 7/10/15.
 * Class that is used to be a temporary holder for the data of a Tic-Tac-Toe game
 */
public class TTT {
    private String name = "",
                   p1 = "",
                   p2 = "";
    private int MAX_ROUNDS = 0, sround = 0;
    private String[] tiles = new String[9];
    public TTT(){}

    /**
     * Builds the object with basic params
     * @param name - name of the game
     * @param p1 - p1 name
     * @param p2 - p2 name
     * @param rounds - round limit
     * @param sround - starting round
     */
    public TTT(String name, String p1, String p2, int rounds, int sround){
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.MAX_ROUNDS = rounds;
        this.sround = sround;
    }
    /**
     * Builds the object with basic params
     * @param name - name of the game
     * @param p1 - p1 name
     * @param p2 - p2 name
     * @param rounds - round limit
     * @param sround - starting round
     * @param tiles - the tiles on the board
     */
    public TTT(String name, String p1, String p2, int rounds, int sround, String[] tiles){
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.MAX_ROUNDS = rounds;
        this.sround = sround;
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
    public int getRounds(){
        return MAX_ROUNDS;
    }
    public int getSround(){
        return sround;
    }
    public String[] getTiles(){
        return tiles;
    }
}
