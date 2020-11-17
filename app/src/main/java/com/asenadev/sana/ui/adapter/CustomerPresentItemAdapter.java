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

    private List<ArrivalsItem> arrivalsItemList;
    private CustomerPresentItemCallBack callBack;
    private static final String TAG = "CustomerPresentItemAdap";

    public CustomerPresentItemAdapter(List<ArrivalsItem> arrivalsItemList, CustomerPresentItemCallBack callBack) {
        this.arrivalsItemList = arrivalsItemList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public CustomerPresentItemAdapter.CustomerPresentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_list, parent, false);
        return new CustomerPresentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPresentItemAdapter.CustomerPresentItemViewHolder holder, int position) {
        holder.bind(arrivalsItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrivalsItemList.size();
    }

    public void setArrivalsItemList(List<ArrivalsItem> arrivalsItemList) {
        this.arrivalsItemList = arrivalsItemList;
        notifyDataSetChanged();
    }

    public interface CustomerPresentItemCallBack {
        void onExitButtonClickListener(ArrivalsItem arrivalsItem);
    }

    public class CustomerPresentItemViewHolder extends RecyclerView.ViewHolder {

        private TextView firstName;
        private TextView lastName;
        private TextView nationalCode;
        private TextView arrivalDate;
        private LoadingButton setExit;

        public CustomerPresentItemViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_present_customer_firstName);
            lastName = itemView.findViewById(R.id.tv_present_customer_lastName);
            nationalCode = itemView.findViewById(R.id.tv_present_customer_nationalCode);
            arrivalDate = itemView.findViewById(R.id.tv_present_customer_arrivalDate);
            setExit = itemView.findViewById(R.id.btn_present_customer_exit);
        }

        public void bind(ArrivalsItem arrivalsItem) {
            firstName.setText(arrivalsItem.getCustomerArrivalDeparture().getFirstName());
            lastName.setText(arrivalsItem.getCustomerArrivalDeparture().getLastName());
            nationalCode.setText(arrivalsItem.getCustomerArrivalDeparture().getNationalCode());
            arrivalDate.setText(arrivalsItem.getArrivalTime());

            setExit.setOnClickListener(view -> {

                setExit.showLoading();
                callBack.onExitButtonClickListener(arrivalsItem);
                setExit.hideLoading();
            });
        }
    }
}
