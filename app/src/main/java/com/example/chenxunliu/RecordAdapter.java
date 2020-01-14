package com.example.chenxunliu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecordAdapter
 *
 * @date 2019-11-26
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    List<RecordBean> data = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecordAdapter(List<RecordBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecordBean r = data.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(r);
                }
            }
        });
        holder.tv_name.setText(r.getName());
        holder.tv_due_date.setText(r.getDueDate());
        holder.tv_book_title.setText(r.getBookTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public final TextView tv_name;
        public final TextView tv_due_date;
        public final TextView tv_book_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_due_date = itemView.findViewById(R.id.tv_due_date);
            tv_book_title = itemView.findViewById(R.id.tv_book_title);
        }
    }

    interface OnItemClickListener{
        void onClick(RecordBean r);
    }
}
