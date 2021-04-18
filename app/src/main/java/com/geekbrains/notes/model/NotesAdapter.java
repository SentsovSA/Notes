package com.geekbrains.notes.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.notes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private final List<NotesInfo> dataSource;

    public NotesAdapter(List<NotesInfo> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_list, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesInfo data = dataSource.get(position);
        holder.title.setText(data.getNoteName());
        holder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSource == null ? 0 :dataSource.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        AppCompatImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
