package com.example.peter.smartfarepayer.view.adapter;

/**
 * Created by Peter on 12/7/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.viewModel.FareHistoryModel;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FareRecyclerAdapter extends RecyclerView.Adapter<FareRecyclerAdapter.RecyclerHolder> {

    private  ArrayList<FareHistoryModel> list;

    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public FareRecyclerAdapter(ArrayList<FareHistoryModel>models) {
        this.list=models;
        expansionsCollection.openOnlyOne(true);

    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bind(list.get(position));

        expansionsCollection.add(holder.getExpansionLayout());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(ArrayList<FareHistoryModel> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
        Log.e("setItems: ", ""+items.size());
    }

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.fare_recycler_layout;

        @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;

        public static RecyclerHolder buildFor(ViewGroup viewGroup) {
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false));
        }

        public RecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Object object) {
            expansionLayout.collapse(false);
        }

        public ExpansionLayout getExpansionLayout() {
            return expansionLayout;
        }
    }
}