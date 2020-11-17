package com.asenadev.sana.ui;

import android.os.Bundle;
import android.util.Log;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    private static final String TAG = "CustomerFragment";
    private CustomerViewModel customerViewModel;
    private SingleSelectToggleGroup toggleGroup;
    private TextInputEditText nationalCode;
    private RecyclerView recyclerViewCustomerList;
    private CustomerState customerState = CustomerState.PRESENT;
    private CustomerExitedItemAdapter exitedItemAdapter;
    private CustomerPresentItemAdapter presentItemAdapter;
    private LoadingButton searchBtn;
    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        TokenHolder tokenHolder = new TokenHolder(getContext());
        customerViewModel = new ViewModelProvider(
                getActivity(), new ViewModelFactory(getActivity().getApplication(),
                tokenHolder, ApiServiceProvider.createService(ApiService.class, tokenHolder.getUserLoginToken()))
        ).get(CustomerViewModel.class);


        toggleGroup = view.findViewById(R.id.toggleButton);
        nationalCode = view.findViewById(R.id.et_customer_national_search);
        searchBtn = view.findViewById(R.id.btn_customer_search);
        swipeContainer = view.findViewById(R.id.srl_customer_refresh);

        recyclerViewCustomerList = view.findViewById(R.id.rv_customer_list);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewCustomerList.setLayoutManager(linearLayoutManager);

        updatePresentList();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.ltb_customer_present) {

                customerState = CustomerState.PRESENT;
                updatePresentList();

            } else if (checkedId == R.id.ltb_customer_exited) {

                customerState = CustomerState.EXITED;
                updateExitedList();
            }
        });


        searchBtn.setOnClickListener(view1 -> {

            if (customerState == CustomerState.PRESENT) {

                updatePresentListWithQuery(nationalCode.getText().toString());

            } else {

                updateExitedListWithQuery(nationalCode.getText().toString());
            }
        });

        swipeContainer.setOnRefreshListener(() -> {
            nationalCode.clearComposingText();
            if (customerState == CustomerState.PRESENT) {

                updatePresentList();
            }

            if (customerState == CustomerState.EXITED) {

                updateExitedList();
            }

            swipeContainer.setRefreshing(false);
        });
    }

    public void updatePresentList() {

        customerViewModel.getPresentList()
                .observe(getActivity(), arrivalsItems -> {
                    Log.i(TAG, "onCreateView: ");
                    if (customerState == CustomerState.PRESENT) {

                        if (presentItemAdapter == null)
                            presentItemAdapter = new CustomerPresentItemAdapter(arrivalsItems, this);
                        else presentItemAdapter.setArrivalsItemList(arrivalsItems);
                        recyclerViewCustomerList.setAdapter(presentItemAdapter);
                    }
                });
    }

    public void updatePresentListWithQuery(String query) {
        customerViewModel.getPresentListByNationalCode(query, 1)
                .observe(getActivity(), arrivalsItems -> {
                    Log.i(TAG, "onCreateView: ");
                    if (customerState == CustomerState.PRESENT) {

                        if (presentItemAdapter == null)
                            presentItemAdapter = new CustomerPresentItemAdapter(arrivalsItems, this);
                        else presentItemAdapter.setArrivalsItemList(arrivalsItems);
                        recyclerViewCustomerList.setAdapter(presentItemAdapter);
                    }
                });
    }

    public void updateExitedList() {

        customerViewModel.getExitedList()
                .observe(getActivity(), arrivalsItems -> {
                    if (customerState == CustomerState.EXITED) {

                        if (exitedItemAdapter == null) {
                            exitedItemAdapter = new CustomerExitedItemAdapter(arrivalsItems);
                        } else exitedItemAdapter.setArrivalsItems(arrivalsItems);
                        recyclerViewCustomerList.setAdapter(exitedItemAdapter);
                    }
                });
    }

    public void updateExitedListWithQuery(String query) {

        customerViewModel.getPresentListByNationalCode(query, 0)
                .observe(getActivity(), arrivalsItems -> {
                    if (customerState == CustomerState.EXITED) {

                        if (exitedItemAdapter == null) {
                            exitedItemAdapter = new CustomerExitedItemAdapter(arrivalsItems);
                        } else exitedItemAdapter.setArrivalsItems(arrivalsItems);
                        recyclerViewCustomerList.setAdapter(exitedItemAdapter);
                    }
                });
    }


    @Override
    public void onExitButtonClickListener(ArrivalsItem arrivalsItem) {

        customerViewModel.setDeparture(arrivalsItem.getId())
                .observe(getActivity(), isExit -> {

                    Log.i(TAG, "onExitButtonClickListener: "+isExit);
                    if (isExit) {
                        updatePresentList();
                        Toast.makeText(getContext(), "خروج ثبت شد", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getContext(), "آخرین ارجاع تکمیل نشده است!", Toast.LENGTH_SHORT).show();

                });
    }
}
