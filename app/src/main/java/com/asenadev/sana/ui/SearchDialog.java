package com.asenadev.sana.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.employee.Employee;
import com.asenadev.sana.ui.adapter.EmployeeItemAdapter;

import java.util.List;

public class SearchDialog extends DialogFragment implements EmployeeItemAdapter.EmployeeItemCallBack {

    private SearchView searchView;
    private RecyclerView employeeRecyclerView;
    private List<Employee> employees;
    private SearchDialogCallBack callBack;
    private Button cancelBtn;
    private static final String TAG = "SearchDialog";

    public SearchDialog(List<Employee> employees, SearchDialogCallBack callBack) {
        this.employees = employees;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(view);


        searchView = view.findViewById(R.id.searchView);
        employeeRecyclerView = view.findViewById(R.id.rv_employee_list);
        cancelBtn=view.findViewById(R.id.btn_employee_cancel);

        EmployeeItemAdapter employeeItemAdapter = new EmployeeItemAdapter(employees, this);
        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        employeeRecyclerView.setAdapter(employeeItemAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s!=null) {
                    Log.i(TAG, "onQueryTextChange: " + s);
                    employeeItemAdapter.searchFilter(s);
                }
                return false;
            }
        });

        cancelBtn.setOnClickListener(view1 -> {
            dismiss();
        });

        return builder.create();
    }
    // TODO create exit button in searchDialog and handle it

    @Override
    public void onEmployeeItemListener(Employee employee) {
        callBack.onEmployeeItemListener(employee);
        dismiss();
    }

    public interface SearchDialogCallBack {
        void onEmployeeItemListener(Employee employee);
    }
}
