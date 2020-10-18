package com.health29.ustawi.view.recview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.health29.ustawi.R;
import com.health29.ustawi.models.Drug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derrick.kaffo on 13/10/2020.
 * kaffoderrick@gmail.com
 */
public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {

    List<Drug> mDrugs = new ArrayList<>();

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DrugViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        final Drug drug = mDrugs.get(position);
        holder.mTvName.setText(drug.getName());
        holder.mTvPrice.setText(drug.getPrice());
        holder.mTvCategory.setText(drug.getCategory());
        holder.mShAvailable.setChecked(drug.isAvailable());
    }

    @Override
    public int getItemCount() {
        return mDrugs.size();
    }

    class DrugViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final TextView mTvPrice;
        private final TextView mTvCategory;
        private final Switch mShAvailable;

        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.text_name);
            mTvPrice = itemView.findViewById(R.id.text_price);
            mTvCategory = itemView.findViewById(R.id.text_category);
            mShAvailable = itemView.findViewById(R.id.sh_available);
        }
    }

    public void setDrugs(List<Drug> drugs) {
        this.mDrugs.clear();
        this.mDrugs.addAll(drugs);
        notifyDataSetChanged();
    }
}
