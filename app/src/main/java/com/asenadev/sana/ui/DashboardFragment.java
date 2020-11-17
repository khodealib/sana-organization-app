package com.asenadev.sana.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.TokenHolder;
import com.asenadev.sana.model.customer.get.Customer;
import com.asenadev.sana.model.customer.get.ReferralItem;
import com.asenadev.sana.model.employee.Employee;
import com.asenadev.sana.model.remote.ApiService;
import com.asenadev.sana.model.remote.ApiServiceProvider;
import com.asenadev.sana.model.viewmodel.DashboardViewModel;
import com.asenadev.sana.model.viewmodel.ViewModelFactory;
import com.asenadev.sana.ui.adapter.CustomerReferralItemAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.kusu.library.LoadingButton;

public class DashboardFragment extends Fragment implements SearchDialog.SearchDialogCallBack, CustomerReferralItemAdapter.ReferralItemCallBack {
    private static final String TAG = "DashboardFragment";
    private static boolean isCreate = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return view;
    }

    private TextInputEditText referralNationalCodeEt;
    private LoadingButton checkCustomerBtn;
    private DashboardViewModel dashboardViewModel;
    private View createCustomerView;
    private View profileCustomerView;
    private RecyclerView referralRecyclerView;

    //profile
    private TextView customerNameTv;
    private TextView customerLastNameTv;
    private TextView customerFatherNameTv;
    private TextView customerNationalCodeTv;
    private TextView customerPhoneNumberTv;
    private ImageView customerPicIv;
    private LoadingButton refersToBtn;
    private View searchBoxView;
    private SearchDialog searchDialog;
    private TextView employeeNameTv;
    private LoadingButton setExitedBtn;

    //create profile
    private TextInputEditText customerFirstNameEt;
    private TextInputEditText customerLastNameEt;
    private TextInputEditText customerFatherNameEt;
    private TextInputEditText customerNationalCodeEt;
    private TextInputEditText customerPhoneNumberEt;
    private LoadingButton saveCustomerProfileBtn;
    private Button customerChoosePicBtn;
    private Customer customerGet;

    private CustomerReferralItemAdapter adapterReferralList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TokenHolder tokenHolder = new TokenHolder(getContext());
        String token = tokenHolder.getUserLoginToken();
        Log.i(TAG, "onViewCreated: ");
        dashboardViewModel = new ViewModelProvider(
                getActivity(),
                new ViewModelFactory(getActivity().getApplication(), tokenHolder,
                        ApiServiceProvider.createService(ApiService.class, token)))
                .get(DashboardViewModel.class);


        referralNationalCodeEt = view.findViewById(R.id.et_dashboard_referral_national_code);
        checkCustomerBtn = view.findViewById(R.id.btn_dashboard_check);
        createCustomerView = view.findViewById(R.id.ic_dashboard_not_found_customer);
        profileCustomerView = view.findViewById(R.id.ic_dashboard_customer_profile);
        referralRecyclerView = view.findViewById(R.id.rv_dashboard_referral_item);

        //profile
        customerNameTv = view.findViewById(R.id.tv_customer_name);
        customerLastNameTv = view.findViewById(R.id.tv_customer_last_name);
        customerFatherNameTv = view.findViewById(R.id.tv_customer_father_name);
        customerPhoneNumberTv = view.findViewById(R.id.tv_customer_phone_number);
        customerNationalCodeTv = view.findViewById(R.id.tv_customer_national_code);
        customerPicIv = view.findViewById(R.id.iv_customer_pic);
        refersToBtn = view.findViewById(R.id.btn_dashboard_refersTo);
        searchBoxView = view.findViewById(R.id.ic_dashboard_search_box);
        employeeNameTv = view.findViewById(R.id.tv_searchBox_employeeName);
        setExitedBtn = view.findViewById(R.id.btn_dashboard_set_exited);

        // create profile
        customerFirstNameEt = view.findViewById(R.id.et_dashboard_firstName);
        customerLastNameEt = view.findViewById(R.id.et_dashboard_lastName);
        customerFatherNameEt = view.findViewById(R.id.et_dashboard_fatherName);
        customerPhoneNumberEt = view.findViewById(R.id.et_dashboard_phoneNumber);
        customerNationalCodeEt = view.findViewById(R.id.et_dashboard_national_code);
        saveCustomerProfileBtn = view.findViewById(R.id.btn_dashboard_saveCustomerProfile);

        if (createCustomerView.getVisibility() == View.VISIBLE) {
            createCustomerView.setVisibility(View.GONE);
            Log.i(TAG, "onViewCreated: " + createCustomerView.getVisibility());
        }

        if (profileCustomerView.getVisibility() == View.VISIBLE) {
            profileCustomerView.setVisibility(View.GONE);
            Log.i(TAG, "onViewCreated: " + profileCustomerView.getVisibility());
        }

        if (referralRecyclerView.getVisibility() == View.VISIBLE) {
            referralRecyclerView.setVisibility(View.GONE);
            Log.i(TAG, "onViewCreated: " + referralRecyclerView.getVisibility());
        }

        if (searchBoxView.getVisibility() == View.VISIBLE) {
            searchBoxView.setVisibility(View.GONE);
            Log.i(TAG, "onViewCreated: " + searchBoxView.getVisibility());
        }

        if (refersToBtn.getVisibility() == View.VISIBLE) {
            refersToBtn.setVisibility(View.GONE);
            Log.i(TAG, "onViewCreated: " + refersToBtn.getVisibility());
        }
        setExitedBtn.setVisibility(View.GONE);

        checkCustomerBtn.setOnClickListener(view1 -> {


            checkCustomerBtn.showLoading();
            if (!referralNationalCodeEt.getText().toString().equals("")) {
                String nationalCode = referralNationalCodeEt.getText().toString();
                dashboardViewModel.checkExistCustomer(nationalCode)
                        .observe(getActivity(), isExist -> {
                            Log.i(TAG, "onViewCreated: " + isExist.toString());
                            if (!isExist) {
                                createCustomerProfile(nationalCode);
                                checkCustomerBtn.hideLoading();
                                searchBoxView.setVisibility(View.GONE);
                                refersToBtn.setVisibility(View.GONE);
                                referralRecyclerView.setVisibility(View.GONE);
                                profileCustomerView.setVisibility(View.GONE);
                                setExitedBtn.setVisibility(View.GONE);
                            } else {
                                showCustomerAndReferrals(nationalCode);
                                checkCustomerBtn.hideLoading();
                                createCustomerView.setVisibility(View.GONE);
                            }
                        });
            }
        });

        searchBoxView.setOnClickListener(view1 -> {
            dashboardViewModel.getEmployeeList().observe(this, employees -> {

                searchDialog = new SearchDialog(employees, this);
                searchDialog.setCancelable(false);
                searchDialog.show(getFragmentManager().beginTransaction(), null);

            });
        });

        setExitedBtn.setOnClickListener(view1 -> {
            dashboardViewModel.setExitCustomer(customerGet.getId())
                    .observe(getActivity(), isExited -> {
                        if (isExited) {
                            searchBoxView.setVisibility(View.GONE);
                            refersToBtn.setVisibility(View.GONE);
                            referralRecyclerView.setVisibility(View.GONE);
                            profileCustomerView.setVisibility(View.GONE);
                            setExitedBtn.setVisibility(View.GONE);

                            Toast.makeText(getContext(), "کاربر خارج شد.", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getContext(), "آخرین ارجاع کامل نشده است!", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    public void showCustomerAndReferrals(String nationalCode) {

        dashboardViewModel.getCustomerProfile(nationalCode).observe(getActivity(), customer -> {

            customerGet = customer;

            Log.i(TAG, "showCustomerAndReferrals: " + customer.toString());
            customerNameTv.setText(customer.getFirstName());
            customerLastNameTv.setText(customer.getLastName());
            customerFatherNameTv.setText(customer.getFatherName());
            customerPhoneNumberTv.setText(customer.getMobile());
            customerNationalCodeTv.setText(customer.getNationalCode());

            if (customer.getPicture() != null) {
                Glide.with(getContext())
                        .load(customer.getPicture())
                        .into(customerPicIv);
            }

            profileCustomerView.setVisibility(View.VISIBLE);

            if (customer.getReferrals() != null) {
                referralRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                adapterReferralList = new CustomerReferralItemAdapter(customer.getReferrals(), this);
                referralRecyclerView.setAdapter(adapterReferralList);
                referralRecyclerView.setVisibility(View.VISIBLE);
            }

            refersToBtn.setVisibility(View.VISIBLE);
            searchBoxView.setVisibility(View.VISIBLE);
            setExitedBtn.setVisibility(View.VISIBLE);
        });


    }

    public void createCustomerProfile(String nationalCode) {

        createCustomerView.setVisibility(View.VISIBLE);
        customerNationalCodeEt.setText(nationalCode);

        saveCustomerProfileBtn.setOnClickListener(view -> {
            saveCustomerProfileBtn.showLoading();
            if (!customerFirstNameEt.getText().toString().equals("") && !customerLastNameEt.getText().toString().equals("")
                    && !customerFatherNameEt.getText().toString().equals("") && !customerPhoneNumberEt.getText().toString().equals("")) {

                dashboardViewModel.addCustomerProfile(
                        customerFirstNameEt.getText().toString(),
                        customerLastNameEt.getText().toString(),
                        customerFatherNameEt.getText().toString(),
                        customerPhoneNumberEt.getText().toString(),
                        customerNationalCodeEt.getText().toString()
                ).observe(getActivity(), isCreated -> {
                    Log.i(TAG, "createCustomerProfile: " + isCreated.toString());
                    if (isCreated)
                        Toast.makeText(getContext(), "ثبت شد", Toast.LENGTH_SHORT).show();
                    createCustomerView.setVisibility(View.GONE);
                });
            }
            saveCustomerProfileBtn.hideLoading();
        });
    }


    @Override
    public void onEmployeeItemListener(Employee employee) {
        employeeNameTv.setText(employee.getName());
        refersToBtn.setOnClickListener(view -> {
            refersToBtn.showLoading();
            dashboardViewModel.setCustomerReferral(
                    customerGet.getId(), employee.getId(), "test"
            ).observe(getActivity(), isRefersTo -> {
                Toast.makeText(getContext(), "ارجاع داده شد", Toast.LENGTH_SHORT).show();
            });
            refersToBtn.hideLoading();

            searchBoxView.setVisibility(View.GONE);
            refersToBtn.setVisibility(View.GONE);
            referralRecyclerView.setVisibility(View.GONE);
            profileCustomerView.setVisibility(View.GONE);
            setExitedBtn.setVisibility(View.GONE);

            employeeNameTv.setText("ارجاع به");
        });
    }

    @Override
    public void onClickOnComplete(ReferralItem referralItem) {
        dashboardViewModel.completeReference(referralItem.getId())
                .observe(getActivity(), isCompleted -> {
                    if (isCompleted) {

                        dashboardViewModel.getCustomerProfile(referralNationalCodeEt.getText().toString())
                                .observe(getActivity(), customer -> {
                                    Log.i(TAG, "onClickOnComplete: " + customer.getReferrals().toString());
                                    if (customer.getReferrals() != null)
                                        adapterReferralList.updateStatus(customer.getReferrals());
                                });

                        Toast.makeText(getContext(), "فرآیند تمکیل شد", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
