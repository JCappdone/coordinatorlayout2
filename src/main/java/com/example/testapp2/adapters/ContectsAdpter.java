package com.example.testapp2.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapp2.R;
import com.example.testapp2.models.AdpterContectModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContectsAdpter extends RecyclerView.Adapter<ContectsAdpter.ContectViewHolder> {

    private Context mContext;
    private List<AdpterContectModel> mList;

    public ContectsAdpter(Context context, List<AdpterContectModel> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ContectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adpterlayout_contects, parent, false);
        return new ContectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContectViewHolder holder, int position) {
        AdpterContectModel contects = mList.get(position);
        holder.txtName.setText(contects.getName());
        holder.txtPhoneNumber.setText(contects.getNumber());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ContectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPhoneNumber)
        TextView txtPhoneNumber;
        public ContectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
