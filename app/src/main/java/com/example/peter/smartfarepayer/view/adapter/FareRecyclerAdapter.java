package com.example.peter.smartfarepayer.view.adapter;

/**
 * Created by Peter on 12/7/17.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peter.smartfarepayer.R;
import com.example.peter.smartfarepayer.models.FareHistoryModel;
import com.example.peter.smartfarepayer.retrofit.model.HistoryResponse;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FareRecyclerAdapter extends RecyclerView.Adapter<FareRecyclerAdapter.RecyclerHolder> {

    private List<HistoryResponse> list;
    HistoryResponse fareHistoryModel;


    private final ExpansionLayoutCollection expansionsCollection = new ExpansionLayoutCollection();

    public FareRecyclerAdapter(List<HistoryResponse> models) {
        this.list = models;
        expansionsCollection.openOnlyOne(true);

    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerHolder.buildFor(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.bind(list.get(position));
        fareHistoryModel = list.get(position);
        //Log.e("listData__",fareHistoryModel.getBusSacco()+"/"+fareHistoryModel.getFareAmount());
        expansionsCollection.add(holder.getExpansionLayout());
        holder.contentData.setText(fareHistoryModel.getMpesaRefno() + " sh "+fareHistoryModel.getAmountPaid()+".00" );
        holder.tvSaccoHeader.setText(fareHistoryModel.getSaccoName());
        holder.tvTravelDate.setText(fareHistoryModel.getVehicleNumber());
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

   /* public void setItems(ArrayList<FareHistoryModel> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
        Log.e("setItems: ", "" + items.size());
    }*/

    public final static class RecyclerHolder extends RecyclerView.ViewHolder {

        private static final int LAYOUT = R.layout.farehistory_recycler_layout;

        @BindView(R.id.expansionLayout)
        ExpansionLayout expansionLayout;
        @BindView(R.id.contentData)
        TextView contentData;
        @BindView(R.id.saccoHeader)
        TextView tvSaccoHeader;
        @BindView(R.id.travelDate)
        TextView tvTravelDate;


        public static RecyclerHolder buildFor(ViewGroup viewGroup) {
            return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT,
                    viewGroup, false));
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