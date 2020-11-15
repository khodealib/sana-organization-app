package com.asenadev.sana.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;

import java.util.List;

public class CustomerExitedItemAdapter extends RecyclerView.Adapter<CustomerExitedItemAdapter.CustomerExitedItemViewHolder> {


    private List<ArrivalsItem> arrivalsItems;

    public CustomerExitedItemAdapter(List<ArrivalsItem> arrivalsItems) {

        this.arrivalsItems = arrivalsItems;
    }

    @NonNull
    @Override
    public CustomerExitedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_list_exited, parent, false);
        return new CustomerExitedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerExitedItemViewHolder holder, int position) {
        holder.bind(arrivalsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return arrivalsItems.size();
    }

    public void addAll(List<ArrivalsItem> arrivalsItems) {
        this.arrivalsItems = arrivalsItems;
        notifyDataSetChanged();
    }

    public void clear() {
        arrivalsItems.clear();
        notifyDataSetChanged();
    }

    public class CustomerExitedItemViewHolder extends RecyclerView.ViewHolder {

        private TextView fullNameTv;
        private TextView nationalCode;
        private TextView arrivalDateTv;
        private TextView exitedDateTv;

        public CustomerExitedItemViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameTv=itemView.findViewById(R.id.tv_exited_customer_fullName);
            nationalCode = itemView.findViewById(R.id.tv_exited_customer_nationalCode);
            arrivalDateTv = itemView.findViewById(R.id.tv_exited_customer_arrivalDate);
            exitedDateTv = itemView.findViewById(R.id.tv_exited_customer_exitedDate);
        }

        public void bind(ArrivalsItem arrivalsItem) {
            fullNameTv.setText(arrivalsItem.getCustomerArrivalDeparture().getFirstName()+" "+arrivalsItem.getCustomerArrivalDeparture().getLastName());
            nationalCode.setText(arrivalsItem.getCustomerArrivalDeparture().getNationalCode());
            arrivalDateTv.setText(arrivalsItem.getArrivalTime());
            exitedDateTv.setText(arrivalsItem.getDepartureTime());
        }
    }
}
