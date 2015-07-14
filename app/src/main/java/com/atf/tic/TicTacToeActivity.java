package com.atf.tic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import database.DatabaseHandler;
import database.TTT;
import de.greenrobot.event.EventBus;


public class TicTacToeActivity extends Activity {
    //Header text
    TextView gameT, playerT;
    //Tic tac game
    TTT game;
    //if it is p1 turn
    boolean isP1;
    //the board
    Button t1, t2, t3, t4, t5, t6, t7, t8, t9;
    //SharedPreferences used to hold the bool
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        //Gets game data
        game = (TTT) EventBus.getDefault().removeStickyEvent(TTT.class);
        //Intialize the prefs
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //sets textviews
        gameT = (TextView) findViewById(R.id.game_name);
        playerT = (TextView) findViewById(R.id.player_title);
        gameT.setText(game.getName());

        isP1 = prefs.getBoolean("isP1", true);

        if(isP1){playerT.setText("Player: " + game.getP1());}
        else{playerT.setText("Player: " + game.getP2());}

        //Sets up button
        t1 = (Button) findViewById(R.id.tile_one);
        t2 = (Button) findViewById(R.id.tile_two);
        t3 = (Button) findViewById(R.id.tile_three);
        t4 = (Button) findViewById(R.id.tile_four);
        t5 = (Button) findViewById(R.id.tile_five);
        t6 = (Button) findViewById(R.id.tile_six);
        t7 = (Button) findViewById(R.id.tile_seven);
        t8 = (Button) findViewById(R.id.tile_eight);
        t9 = (Button) findViewById(R.id.tile_nine);

        t1.setText(game.getTiles()[0]);
        t2.setText(game.getTiles()[1]);
        t3.setText(game.getTiles()[2]);
        t4.setText(game.getTiles()[3]);
        t5.setText(game.getTiles()[4]);
        t6.setText(game.getTiles()[5]);
        t7.setText(game.getTiles()[6]);
        t8.setText(game.getTiles()[7]);
        t9.setText(game.getTiles()[8]);





    }

    public void tictactoe(View view) {
        Button btn = (Button) view;
        if (isP1) {
            btn.setText("X");
        } else {
            btn.setText("O");
        }
        btn.setEnabled(false);
        TTT newg = new TTT(game.getName(), game.getP1(), game.getP2(), new String[]{t1.getText().toString(), t2.getText().toString(), t3.getText().toString(),
                t4.getText().toString(), t5.getText().toString(), t6.getText().toString(),
                t7.getText().toString(), t8.getText().toString(), t9.getText().toString()});
        DatabaseHandler.getInstance(this).update(newg);
        win();
        isP1 = !isP1;
        prefs.edit().putBoolean("isP1", isP1).apply();
        changePlayer();
    }

    public void win() {
        //Checks if p1 is winner
        if ((t1.getText().toString().equals("X") && t2.getText().toString().equals("X") && t3.getText().toString().equals("X"))
                || (t4.getText().toString().equals("X") && t5.getText().toString().equals("X") && t6.getText().toString().equals("X"))
                || (t7.getText().toString().equals("X") && t8.getText().toString().equals("X") && t9.getText().toString().equals("X"))
                || (t1.getText().toString().equals("X") && t4.getText().toString().equals("X") && t7.getText().toString().equals("X"))
                || (t2.getText().toString().equals("X") && t5.getText().toString().equals("X") && t8.getText().toString().equals("X"))
                || (t3.getText().toString().equals("X") && t6.getText().toString().equals("X") && t9.getText().toString().equals("X"))
                || (t1.getText().toString().equals("X") && t5.getText().toString().equals("X") && t9.getText().toString().equals("X"))
                || (t3.getText().toString().equals("X") && t5.getText().toString().equals("X") && t7.getText().toString().equals("X"))) {
            Intent winner = new Intent(this, WinnerActivity.class);
            EventBus.getDefault().postSticky("p1");
            EventBus.getDefault().postSticky(game);
            startActivity(winner);
        }
        //Checks if p2 is winner
        else if ((t1.getText().toString().equals("O") && t2.getText().toString().equals("O") && t3.getText().toString().equals("O"))
                || (t4.getText().toString().equals("O") && t5.getText().toString().equals("O") && t6.getText().toString().equals("O"))
                || (t7.getText().toString().equals("O") && t8.getText().toString().equals("O") && t9.getText().toString().equals("O"))
                || (t1.getText().toString().equals("O") && t4.getText().toString().equals("O") && t7.getText().toString().equals("O"))
                || (t2.getText().toString().equals("O") && t5.getText().toString().equals("O") && t8.getText().toString().equals("O"))
                || (t3.getText().toString().equals("O") && t6.getText().toString().equals("O") && t9.getText().toString().equals("O"))
                || (t1.getText().toString().equals("O") && t5.getText().toString().equals("O") && t9.getText().toString().equals("O"))
                || (t3.getText().toString().equals("O") && t5.getText().toString().equals("O") && t7.getText().toString().equals("O"))) {
            Intent winner = new Intent(this, WinnerActivity.class);
            EventBus.getDefault().postSticky("p2");
            EventBus.getDefault().postSticky(game);
            startActivity(winner);

        }

    }
    public void reset(View view){
        //Updates bool
        isP1 = true;
        prefs.edit().putBoolean("isP1", true).apply();
        playerT.setText(game.getP1());

        t1.setText("");
        t2.setText("");
        t3.setText("");

        t1.setEnabled(true);
        t2.setEnabled(true);
        t3.setEnabled(true);

        t4.setText("");
        t5.setText("");
        t6.setText("");

        t4.setEnabled(true);
        t5.setEnabled(true);
        t6.setEnabled(true);

        t7.setText("");
        t8.setText("");
        t9.setText("");

        t7.setEnabled(true);
        t8.setEnabled(true);
        t9.setEnabled(true);

        TTT g = new TTT(game.getName(), game.getP1(), game.getP2(), new String[]{t1.getText().toString(), t2.getText().toString(), t3.getText().toString(),
                t4.getText().toString(), t5.getText().toString(), t6.getText().toString(),
                t7.getText().toString(), t8.getText().toString(), t9.getText().toString()});
        DatabaseHandler.getInstance(this).update(g);
    }
    public void tie(View view){
        Intent winner = new Intent(this, WinnerActivity.class);
        EventBus.getDefault().postSticky("tie");
        EventBus.getDefault().postSticky(game);
        startActivity(winner);
    }
    public void changePlayer(){
        if(isP1){
            playerT.setText(game.getP1());
        }
        else{
            playerT.setText(game.getP2());
        }
    }
}