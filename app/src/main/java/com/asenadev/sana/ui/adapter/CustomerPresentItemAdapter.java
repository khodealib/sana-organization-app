package com.asenadev.sana.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;
import com.kusu.library.LoadingButton;

import java.util.List;

public class CustomerPresentItemAdapter extends RecyclerView.Adapter<CustomerPresentItemAdapter.CustomerPresentItemViewHolder> {

    private List<ArrivalsItem> arrivalsItems;
    private CustomerPresentItemCallBack callBack;

    public CustomerPresentItemAdapter(List<ArrivalsItem> arrivalsItem,CustomerPresentItemCallBack callBack) {
        this.arrivalsItems = arrivalsItem;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public CustomerPresentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_list, parent, false);
        return new CustomerPresentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPresentItemViewHolder holder, int position) {
        holder.bind(arrivalsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return arrivalsItems.size();
    }

    public void addAllItems(List<ArrivalsItem> arrivalsItems) {
        this.arrivalsItems = arrivalsItems;
        notifyDataSetChanged();
    }

    public void clear() {
        arrivalsItems.clear();
        notifyDataSetChanged();
    }

    public class CustomerPresentItemViewHolder extends RecyclerView.ViewHolder {

        private TextView firstNameTv;
        private TextView lastNameTv;
        private TextView nationalCodeTv;
        private TextView arrivalDateTv;
        private LoadingButton exitBtn;
        public CustomerPresentItemViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTv = itemView.findViewById(R.id.tv_present_customer_firstName);
            lastNameTv=itemView.findViewById(R.id.tv_present_cusomer_lastName);
            nationalCodeTv=itemView.findViewById(R.id.tv_present_customer_nationalCode);
            arrivalDateTv = itemView.findViewById(R.id.tv_present_customer_arrivalDate);
            exitBtn = itemView.findViewById(R.id.btn_present_customer_exit);
        }

        public void bind(ArrivalsItem arrivalsItem) {
            firstNameTv.setText(arrivalsItem.getCustomerArrivalDeparture().getFirstName());
            lastNameTv.setText(arrivalsItem.getCustomerArrivalDeparture().getLastName());
            nationalCodeTv.setText(arrivalsItem.getCustomerArrivalDeparture().getNationalCode());
            arrivalDateTv.setText(arrivalsItem.getArrivalTime());

            exitBtn.setOnClickListener(view -> callBack.setExitButtonClickListener(arrivalsItem));
        }
    }

    public interface CustomerPresentItemCallBack{
        void setExitButtonClickListener(ArrivalsItem arrivalsItem);
    }
}
