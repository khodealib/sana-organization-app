package com.asenadev.sana.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.referral.ReferralDataItem;
import com.kusu.library.LoadingButton;

import java.util.List;

public class ReferralItemAdapter extends RecyclerView.Adapter<ReferralItemAdapter.ReferralItemViewHolder> {

    private List<ReferralDataItem> referralDataItems;
    private ReferralItemCallBack callBack;
    private static final String TAG = "CustomerPresentItemAdap";

    public ReferralItemAdapter(List<ReferralDataItem> arrivalsItemList, ReferralItemCallBack callBack) {
        this.referralDataItems = arrivalsItemList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ReferralItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_list, parent, false);
        return new ReferralItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferralItemViewHolder holder, int position) {
        holder.bind(referralDataItems.get(position));
    }

    @Override
    public int getItemCount() {
        return referralDataItems.size();
    }

    public void setReferralItemList(List<ReferralDataItem> referralDataItems) {
        this.referralDataItems = referralDataItems;
        notifyDataSetChanged();
    }

    public interface ReferralItemCallBack {
        void onCompleteProcessButtonClickListener(ReferralDataItem referralDataItem);
    }

    public class ReferralItemViewHolder extends RecyclerView.ViewHolder {

        private TextView firstName;
        private TextView lastName;
        private TextView nationalCode;
        private TextView arrivalDate;
        private LoadingButton setExit;

        public ReferralItemViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tv_present_customer_firstName);
            lastName = itemView.findViewById(R.id.tv_present_customer_lastName);
            nationalCode = itemView.findViewById(R.id.tv_present_customer_nationalCode);
            arrivalDate = itemView.findViewById(R.id.tv_present_customer_arrivalDate);
            setExit = itemView.findViewById(R.id.btn_present_customer_exit);
        }

        public void bind(ReferralDataItem referralDataItem) {
            firstName.setText(referralDataItem.getCustomer().getFirstName());
            lastName.setText(referralDataItem.getCustomer().getLastName());
            nationalCode.setText(referralDataItem.getCustomer().getNationalCode());
            arrivalDate.setText(referralDataItem.getTime());

            setExit.setText("تکمیل فرآیند");

            setExit.setOnClickListener(view -> {

                setExit.showLoading();
                callBack.onCompleteProcessButtonClickListener(referralDataItem);
                setExit.hideLoading();
            });
        }
    }
}
