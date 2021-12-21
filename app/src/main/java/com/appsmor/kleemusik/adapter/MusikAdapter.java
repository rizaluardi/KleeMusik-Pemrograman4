package com.appsmor.kleemusik.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsmor.kleemusik.R;
import com.appsmor.kleemusik.model.Musik;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MusikAdapter extends RecyclerView.Adapter<MusikAdapter.MusikViewHolder> {
    private List<Musik> musikList = new ArrayList<>();
    public MusikAdapter(List<Musik> musikList) {
        this.musikList = musikList;
    }

    @Override
    public MusikAdapter.MusikViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_musik, parent, false);
        MusikViewHolder musikViewHolder = new MusikViewHolder(view);
        return musikViewHolder;
    }

    @Override
    public void onBindViewHolder(MusikAdapter.MusikViewHolder holder, int position) {
        holder.txt_resultmusik.setText(musikList.get(position).getMusik());
        holder.txt_resultartis.setText(musikList.get(position).getArtis());
    }

    @Override
    public int getItemCount() {
        return musikList.size();
    }

    public static class MusikViewHolder extends RecyclerView.ViewHolder {

        TextView txt_resultmusik;
        TextView txt_resultartis;

        public MusikViewHolder(View itemView) {
            super(itemView);

            txt_resultmusik = (TextView) itemView.findViewById(R.id.txt_resultmusik);
            txt_resultartis = (TextView) itemView.findViewById(R.id.txt_resultartis);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
