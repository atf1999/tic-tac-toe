package com.atf.tic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
    //Intent for switching
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void newGame(View view){
       intent = new Intent(this, NewGameActivity.class);
       startActivity(intent);
   }
   public void openGame(View view){
       intent = new Intent(this, OpenGameActivity.class);
       startActivity(intent);
   }
}
