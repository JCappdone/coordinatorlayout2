package com.example.testapp2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapp2.R;
import com.example.testapp2.models.OtherFieldModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherFieldAdpter extends RecyclerView.Adapter<OtherFieldAdpter.FieldsViewHolder> {

    private Context mContext;
    private List<OtherFieldModel> mList;

    public OtherFieldAdpter(Context context, List<OtherFieldModel> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public FieldsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpterlayout_otherfields, parent, false);
        return new FieldsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldsViewHolder holder, int position) {
        OtherFieldModel otherField = mList.get(position);
        holder.txtFieldName.setText(otherField.getFields());
        holder.txtFieldValue.setText(otherField.getFieldsValue());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FieldsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtFieldName)
        TextView txtFieldName;
        @BindView(R.id.txtFieldValue)
        TextView txtFieldValue;
        public FieldsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
