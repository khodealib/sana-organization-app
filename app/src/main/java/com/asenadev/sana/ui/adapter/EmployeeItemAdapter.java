package com.asenadev.sana.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;
import com.asenadev.sana.model.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeItemAdapter extends RecyclerView.Adapter<EmployeeItemAdapter.EmployeeItemHolder> {

    private List<Employee> employees;
    private EmployeeItemCallBack callBack;
    private List<Employee> employeesFilter;
    private static final String TAG = "EmployeeItemAdapter";

    public EmployeeItemAdapter(List<Employee> employees, EmployeeItemCallBack callBack){

        this.employeesFilter=employees;
        this.callBack = callBack;
        this.employees=new ArrayList<>();
        this.employees.addAll(employeesFilter);
    }

    @NonNull
    @Override
    public EmployeeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: "+employees.toString() + employeesFilter.toString());
        return new EmployeeItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeItemHolder holder, int position) {
        holder.bind(employeesFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return employeesFilter.size();
    }

    public void searchFilter(String query) {

        if (!query.equals("")) {
            employeesFilter.clear();
            Log.i(TAG, "searchFilter:"+ query);
            Log.i(TAG, "searchFilter: "+ employees.toString() + employeesFilter.toString());
            for (Employee employee : employees) {
                Log.i(TAG, "searchFilter: "+employees.size());
                if (employee.getName().contains(query)){
                    employeesFilter.add(employee);

                }
                Log.i(TAG, "searchFilter: "+ employee.getName());
            }
        }
        if (query.equals("")) {
            employeesFilter.clear();
            employeesFilter.addAll(employees);
        }


        notifyDataSetChanged();
    }

    public class EmployeeItemHolder extends RecyclerView.ViewHolder{

        private TextView employeeNameTv;
        private View itemView;
        public EmployeeItemHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;

            employeeNameTv =itemView.findViewById(R.id.tv_employeeItem_employeeName);

        }

        public void bind(Employee employee) {
            employeeNameTv.setText(employee.getName());

            itemView.setOnClickListener(view -> {

                callBack.onEmployeeItemListener(employee);
            });
        }
    }

    public interface EmployeeItemCallBack {
         void onEmployeeItemListener(Employee employee);
    }
}
