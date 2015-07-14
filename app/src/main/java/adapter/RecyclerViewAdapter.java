package adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atf.tic.R;

import java.util.ArrayList;

/**
 * Created by tom on 7/14/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    ArrayList<String> list;
    Activity a;
    public RecyclerViewAdapter(ArrayList<String> l, Activity a){
        this.list = l;
        this.a = a;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new RecyclerViewHolder(view, a);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.va.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
