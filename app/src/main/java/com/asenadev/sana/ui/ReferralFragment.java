package com.asenadev.sana.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.referral.ReferralDataItem;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.ReferralViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.asenadev.sana.ui.adapter.ReferralItemAdapter;
import com.github.pierry.simpletoast.SimpleToast;

public class ReferralFragment extends Fragment implements ReferralItemAdapter.ReferralItemCallBack{

    private RecyclerView referralItemRv;
    private ReferralItemAdapter referralItemAdapter;
    private ReferralViewModel referralViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_referrals, container, false);
        TokenHolder tokenHolder = new TokenHolder(getContext());
        referralViewModel = new ViewModelProvider(getActivity(),
                new ViewModelFactory(getActivity().getApplication(), ApiServiceProvider.createService(ApiService.class, tokenHolder.getUserLoginToken())))
                .get(ReferralViewModel.class);


        updateListReferral(view);
        return view;
    }

    private void updateListReferral(View view) {
        referralViewModel.getReferralList()
                .observe(this, referralDataItems -> {
                    if (referralItemAdapter == null) {

                        referralItemRv = view.findViewById(R.id.rv_referral_item);
                        referralItemAdapter = new ReferralItemAdapter(referralDataItems, this);
                        referralItemRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                        referralItemRv.setAdapter(referralItemAdapter);
                    } else referralItemAdapter.setReferralItemList(referralDataItems);
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCompleteProcessButtonClickListener(ReferralDataItem referralDataItem) {

        SetDescriptionDialog setDescriptionDialog = new SetDescriptionDialog(description -> {
            referralViewModel.setCompleteProcess(description , referralDataItem.getId())
                    .observe(this,isSuccess ->{
                        if (isSuccess) SimpleToast.ok(getContext(),"فرآیند تکمیل شد");
                    });
        });
        setDescriptionDialog.setCancelable(false);
        setDescriptionDialog.show(getFragmentManager().beginTransaction(),null);
    }


}
