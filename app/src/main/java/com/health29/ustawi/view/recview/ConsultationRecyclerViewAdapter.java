package com.health29.ustawi.view.recview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.health29.ustawi.R;
import com.health29.ustawi.models.ConsultationModel;
import com.health29.ustawi.view.activities.ReplayActivity;

import java.util.List;

public class ConsultationRecyclerViewAdapter extends RecyclerView.Adapter<ConsultationRecyclerViewAdapter.ViewHolder> {
    private List<ConsultationModel> mConsultationModelList;
    private Context mContext;

    public ConsultationRecyclerViewAdapter(List<ConsultationModel> consultationModelList, Context context) {
        mConsultationModelList = consultationModelList;
        mContext = context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultation_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsultationModel consultationModel = mConsultationModelList.get(position);

        String consultationTitle = "Some test title";
        String consultationDetail = "Some test details";
        String consultationReplyCount = "replies (14)";

//        holder.consultationTitle.setText(consultationModel.getConsultationTitle());
//        holder.consultationDetail.setText(consultationModel.getConsultationDetails());
//        holder.consultationReplyCount.setText(consultationModel.getConsultationReplyCount());

        holder.consultationTitle.setText(consultationTitle);
        holder.consultationDetail.setText(consultationDetail);
        holder.consultationReplyCount.setText(consultationReplyCount);
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mConsultationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView consultationTitle;
        TextView consultationDetail;
        TextView consultationReplyCount;
        public int mCurrentPosition;
        public int mId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            consultationTitle = itemView.findViewById(R.id.consultation_title);
            consultationDetail = itemView.findViewById(R.id.consultation_detail);
            consultationReplyCount = itemView.findViewById(R.id.consultation_replies_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ReplayActivity.class);
                    intent.putExtra(ReplayActivity.CONSULTATION_POSITION, mId);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
