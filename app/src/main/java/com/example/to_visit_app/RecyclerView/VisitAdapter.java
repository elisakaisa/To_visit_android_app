package com.example.to_visit_app.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_visit_app.FragmentHome;
import com.example.to_visit_app.R;
import com.example.to_visit_app.model.VisitList;
import com.example.to_visit_app.model.VisitModel;

import java.util.ArrayList;
import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitAdapter.ViewHolder> {

    ArrayList<VisitList> mVisits;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView whatTV;

        public ViewHolder(View itemView) {
            super(itemView);
            whatTV = itemView.findViewById(R.id.tv_what);
        }
    }

    //constructor
    public VisitAdapter(ArrayList<VisitList> visitItemsList) {
        this.mVisits = visitItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_visit, parent, false);
        return new VisitAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("onBindViewHolder", String.valueOf(position));
        VisitRecycler currentVisit = (VisitRecycler) mVisits.get(position);
        holder.whatTV.setText(currentVisit.getTextWhat());
    }


    @Override
    public int getItemCount() {
        return mVisits.size();
    }

}
