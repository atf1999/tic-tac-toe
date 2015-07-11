package com.atf.tic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import database.DatabaseHandler;
import database.TTT;
import de.greenrobot.event.EventBus;


public class NewGameActivity extends Activity {
    //Text fields
    EditText name, p1, p2, sR, rounds;
    //Strings to submit
    String sName, sP1, sP2, ssR, sRounds;
    //Holds Game
    TTT game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

    }
    public void create(View view){
        //Intialize the fields
        name = (EditText)findViewById(R.id.name);
        p1 = (EditText)findViewById(R.id.p1);
        p2 = (EditText)findViewById(R.id.p2);
        new AlertDialog.Builder(this)
                .setTitle("Create new game?")
                .setMessage("Are you sure you want to create this game? Once created, you cannot edit it")
                .setPositiveButton("Yes", (dialog, which) -> {
                    sName = name.getText().toString();
                    sP1 = p1.getText().toString();
                    sP2 = p2.getText().toString();
                    game = new TTT(sName, sP1, sP2, new String[]{"","","","","","","","",""});
                    EventBus.getDefault().postSticky(game);
                    DatabaseHandler.getInstance(getApplicationContext()).add(game);
                    Intent tic = new Intent(getApplicationContext(), TicTacToeActivity.class);
                    startActivity(tic);
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create()
                .show();
    }

}