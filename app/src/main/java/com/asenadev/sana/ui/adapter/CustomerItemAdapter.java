package com.asenadev.sana.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerItemAdapter extends RecyclerView.Adapter<CustomerItemAdapter.CustomerItemViewHolder> {


    @NonNull
    @Override
    public CustomerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomerItemViewHolder extends RecyclerView.ViewHolder{

        public CustomerItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
