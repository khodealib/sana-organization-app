package com.asenadev.sana.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.customer.get.ReferralItem;
import com.kusu.library.LoadingButton;

import java.util.List;

public class CustomerReferralItemAdapter extends RecyclerView.Adapter<CustomerReferralItemAdapter.CustomerItemViewHolder> {


    private static final String TAG = "CustomerReferralItemAda";
    private List<ReferralItem> referrals;
    private ReferralItemCallBack callBack;

    public CustomerReferralItemAdapter(List<ReferralItem> referrals, ReferralItemCallBack callBack) {
        this.referrals = referrals;
        this.callBack = callBack;
    }


    @NonNull
    @Override
    public CustomerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.refferral_list, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerItemViewHolder holder, int position) {
        holder.bind(
                referrals.get(position)
        );
    }

    @Override
    public int getItemCount() {
        return referrals.size();
    }

    public void updateStatus(List<ReferralItem> referralItems) {

        this.referrals = referralItems;
        notifyDataSetChanged();
    }

    public interface ReferralItemCallBack {
        void onClickOnComplete(ReferralItem referralItem);
    }

    public class CustomerItemViewHolder extends RecyclerView.ViewHolder {

        private TextView referralToTv;
        private TextView referralDateTv;
        private TextView referralStatusTv;
        private LoadingButton completeBtn;

        public CustomerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            referralToTv = itemView.findViewById(R.id.tv_referralItem_refersTo);
            referralDateTv = itemView.findViewById(R.id.tv_referralItem_date);
            referralStatusTv = itemView.findViewById(R.id.tv_referralItem_status);
            completeBtn = itemView.findViewById(R.id.btn_referralItem_complete);


        }

        public void bind(ReferralItem referralItem) {


            referralToTv.setText(referralItem.getEmployeeName());
            referralDateTv.setText(referralItem.getReferred());
            referralStatusTv.setText(referralItem.getStatusLabel());


            if (referralItem.getStatus() == 1) {
                completeBtn.setVisibility(View.GONE);
            }

            completeBtn.setOnClickListener(view -> {
                completeBtn.showLoading();
                callBack.onClickOnComplete(referralItem);
                completeBtn.hideLoading();
                if (completeBtn.getVisibility() == View.VISIBLE) {
                    completeBtn.setVisibility(View.GONE);
                }
            });


        }
    }
}
