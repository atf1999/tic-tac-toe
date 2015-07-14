package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by tom on 7/10/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //DB utils
    public static final String DB_NAME = "TTT",
                               DB_TABLE = "games";
    public static final String KEY_NAME = "Name",
                               KEY_P1 = "Player_one",
                               KEY_P2 = "Player_two",
                               KEY_TILES = "Tiles";
    private static final int DB_VERSION = 1;
    private static DatabaseHandler sInstance;
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creats Db
        db.execSQL("CREATE TABLE " + DB_TABLE + "("
                + KEY_NAME + " TEXT,"
                + KEY_P1 + " TEXT,"
                + KEY_P2 + " TEXT,"
                + KEY_TILES + " TEXT,"
                + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Updates
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    /**
     * Converts array to string seperated by comma
     * @return string of tiles
     */
    private String getTiles(TTT game){
        StringBuilder builder = new StringBuilder();
        String tiles[] = game.getTiles();
        for(int i = 0; i < tiles.length - 1; ++i){
            builder.append(tiles[i]);
            builder.append(",");
        }
        builder.append(tiles[8]);
        return builder.toString();

    }
    public void add(TTT game){
        ContentValues vals = new ContentValues();
        vals.put(KEY_NAME, game.getName());
        vals.put(KEY_P1, game.getP1());
        vals.put(KEY_P2, game.getP2());
        vals.put(KEY_TILES, getTiles(game));
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DB_TABLE, null, vals);
    }
    public void update(TTT game){
        ContentValues val = new ContentValues();
        //Intialize db
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + DB_TABLE + " SET " + KEY_TILES + " = " + getTiles(game) + " WHERE " + KEY_NAME + " = " + game.getName());
    }

    /**
     * Gets all matches
     * @return list of matches
     */
    public ArrayList<String> getMatches(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE, null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public TTT getGame(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE " + KEY_NAME + " = " + name, null);
        TTT game = new TTT();
        if(cursor.moveToFirst()){
            do{
               TTT ng = new TTT(cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_P1)), cursor.getString(cursor.getColumnIndex(KEY_P2)), cursor.getString(cursor.getColumnIndex(KEY_TILES)).split(","));
               game = ng;
               break;
            }while (cursor.moveToNext());
        }
        return game;
    }
}
