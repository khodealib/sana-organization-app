package com.asenadev.sana.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.customer.arrival.departuelist.ArrivalsItem;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.CustomerViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.asenadev.sana.ui.adapter.CustomerExitedItemAdapter;
import com.asenadev.sana.ui.adapter.CustomerPresentItemAdapter;
import com.asenadev.sana.utils.CustomerState;
import com.google.android.material.textfield.TextInputEditText;
import com.kusu.library.LoadingButton;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

public class CustomerFragment extends Fragment implements CustomerPresentItemAdapter.CustomerPresentItemCallBack {

    private CustomerViewModel customerViewModel;
    private SingleSelectToggleGroup toggleGroup;
    private TextInputEditText nationalCode;
    private RecyclerView recyclerViewCustomerList;
    private CustomerState customerState;
    private CustomerExitedItemAdapter exitedItemAdapter;
    private CustomerPresentItemAdapter presentItemAdapter;
    private LoadingButton searchBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TokenHolder tokenHolder = new TokenHolder(getContext());

        customerViewModel = new ViewModelProvider(
                getActivity(), new ViewModelFactory(getActivity().getApplication(),
                tokenHolder, ApiServiceProvider.createService(ApiService.class, tokenHolder.getUserLoginToken()))
        ).get(CustomerViewModel.class);

        toggleGroup = view.findViewById(R.id.toggleButton);
        nationalCode = view.findViewById(R.id.et_customer_national_search);
        searchBtn = view.findViewById(R.id.btn_customer_search);

        recyclerViewCustomerList = view.findViewById(R.id.rv_customer_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        customerViewModel.getPresentList().observe(getActivity(), arrivalsItems -> {
            presentItemAdapter = new CustomerPresentItemAdapter(arrivalsItems, this);
        });

        customerViewModel.getExitedList().observe(getActivity(), arrivalsItems -> {
            exitedItemAdapter = new CustomerExitedItemAdapter(arrivalsItems);
        });

        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.ltb_customer_present) {
                customerState = CustomerState.PRESENT;
                recyclerViewCustomerList.setAdapter(presentItemAdapter);
                recyclerViewCustomerList.setLayoutManager(linearLayoutManager);

            } else if (checkedId == R.id.ltb_customer_exited) {
                customerState = CustomerState.EXITED;
                recyclerViewCustomerList.setAdapter(exitedItemAdapter);
                recyclerViewCustomerList.setLayoutManager(linearLayoutManager);
            }
        });


        searchBtn.setOnClickListener(view1 -> {
            if (customerState == CustomerState.PRESENT) {
                customerViewModel.getPresentListByNationalCode(nationalCode.getText().toString(), 1)
                        .observe(getActivity(), arrivalsItems -> presentItemAdapter.addAllItems(arrivalsItems));
            } else {
                customerViewModel.getPresentListByNationalCode(nationalCode.getText().toString(), 0)
                        .observe(getActivity(), arrivalsItems -> exitedItemAdapter.addAll(arrivalsItems));
            }
        });
    }

    @Override
    public void setExitButtonClickListener(ArrivalsItem arrivalsItem) {
        customerViewModel.setDeparture(arrivalsItem.getId())
                .observe(getActivity(), aBoolean -> {
                    if (aBoolean)
                        Toast.makeText(getContext(), "آخرین ارجاع تکمیل نشده است!", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getContext(), "خروج ثبت شد.", Toast.LENGTH_SHORT).show();
                        customerViewModel.getPresentList()
                                .observe(getActivity(), arrivalsItems -> presentItemAdapter.addAllItems(arrivalsItems));
                    }
                });
    }
}
