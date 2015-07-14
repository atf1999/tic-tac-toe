package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.atf.tic.R;
import com.atf.tic.TicTacToeActivity;

import database.DatabaseHandler;
import de.greenrobot.event.EventBus;

/**
 * Created by tom on 7/14/15.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView va;
    Activity a;
    public RecyclerViewHolder(View itemView, Activity a) {
        super(itemView);
        va = (TextView)itemView.findViewById(R.id.name);
        va.setOnClickListener(this);
        this.a = a;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(a.getApplicationContext(), TicTacToeActivity.class);
        EventBus.getDefault().postSticky(DatabaseHandler.getInstance(a.getApplicationContext()).getGame(va.getText().toString()));
        a.startActivity(intent);
    }
}
