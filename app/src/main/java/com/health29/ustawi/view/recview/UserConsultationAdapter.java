package com.health29.ustawi.view.recview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.health29.ustawi.R;
import com.health29.ustawi.models.Drug;
import com.health29.ustawi.models.UserConsultation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserConsultationAdapter<T> extends RecyclerView.Adapter<UserConsultationAdapter.ViewHolder> {

    ArrayList<UserConsultation> muserConsultations;
    private final Context mContext;


    public UserConsultationAdapter(Context Context,ArrayList<UserConsultation> userConsultations) {
        this.mContext = Context;
        muserConsultations = userConsultations;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.consultation_list_item, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull UserConsultationAdapter.ViewHolder holder, int position) {

        final UserConsultation consultion = muserConsultations.get(position);
        holder.title.setText("Title: "+consultion.getTitle());
        holder.details.setText("Detail "+consultion.getDetails());
        //holder.mTvCategory.setText(consultion.getNumberOfReplies());
    }

    @Override
    public int getItemCount() {
        return muserConsultations.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView details;


        public ViewHolder(View inflate) {
            super(inflate);

            title = inflate.findViewById(R.id.consultation_title);
           details = inflate.findViewById(R.id.consultation_detail);
//            mTvCategory = itemView.findViewById(R.id.text_numberOfReplies);
//            mShAvailable = itemView.findViewById(R.id.sh_available);
        }
    }

    public void setConsultation(List<UserConsultation> consultations) {
        this.muserConsultations.clear();
        this.muserConsultations.addAll(consultations);
        notifyDataSetChanged();
    }
}
