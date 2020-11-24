package com.asenadev.sana.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asenadev.sana.R;

import java.util.List;


public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private List<String> permissions;
    private MenuItemCallBack callBack;


    private static final String TAG = "MenuItemAdapter";

    public MenuItemAdapter(List<String> permissions, MenuItemCallBack callBack) {
        this.permissions=permissions;
        this.callBack = callBack;
        Log.i(TAG, "MenuItemAdapter: "+this.permissions.size());
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_menu,parent,false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        holder.bind(permissions.get(position));
    }

    @Override
    public int getItemCount() {
        return this.permissions==null?0:this.permissions.size();

    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder {


        private TextView itemName;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.tv_menuItemDrawer);
        }

        public void bind(String permission) {
            itemName.setText(permission);

            itemView.setOnClickListener(view -> callBack.menuItemOnClickListener(permission,false));
        }
    }

    public interface MenuItemCallBack{
        void menuItemOnClickListener(String permission,boolean isCloseDrawer);
    }
}
