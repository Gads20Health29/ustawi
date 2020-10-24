package com.health29.ustawi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.health29.ustawi.models.ReplayModel;

import java.util.List;

public class ReplayRecyclerViewAdapter extends RecyclerView.Adapter<ReplayRecyclerViewAdapter.ViewHolder>  {
  private final Context mContext;
  private final   LayoutInflater mLayoutInflater;
  private final List<ReplayModel> mReplayModelList;

    public ReplayRecyclerViewAdapter(Context pContext, List<ReplayModel> pReplayModelList) {
        mContext = pContext;
        mReplayModelList = pReplayModelList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= mLayoutInflater.inflate(R.layout.replies_list,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReplayModel replay = mReplayModelList.get(position);
        String  replayText= "Some test replay";
        holder.mTextReplay.setText(replayText);
    }

    @Override
    public int getItemCount() {
        return mReplayModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTextReplay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextReplay = (TextView)itemView.findViewById(R.id.text_replies);
        }
    }
}
