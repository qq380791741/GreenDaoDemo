package com.test.tcc.greendaodemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.tcc.greendaodemo.R;
import com.test.tcc.greendaodemo.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private List<User2> users;
    private Context context;
    OnDeleteUserClickListener onDeleteUserClickListener;

    public UserAdapter(List<User2> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new UserHolder(LayoutInflater.from(context).inflate(R.layout.item_user,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        UserHolder holder = (UserHolder) viewHolder;
        holder.id.setText(users.get(i).getId()+"");
        holder.username.setText(users.get(i).getUserName());
        holder.password.setText(users.get(i).getPassWord());
        holder.lv.setText(users.get(i).getLv());
        holder.address.setText(users.get(i).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteUserClickListener!=null){
                    onDeleteUserClickListener.delete(users.get(i));
                }
            }
        });
    }

    public void setUsers(List<User2> users){
        if (users!=null){
            this.users.clear();
            this.users.addAll(users);
        }else {
            this.users = new ArrayList<>();
            this.users.addAll(users);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (users!=null){
            return users.size();
        }
        return 0;
    }

    interface OnDeleteUserClickListener{
        void delete(User2 user);
    }

    public User2 getDeleteItem(){
        if (users.size()<1){
            return null;
        }
        return users.get(users.size()-1);
    }

    public void setOnDeleteUserListener(OnDeleteUserClickListener onDeleteUserListener){
        this.onDeleteUserClickListener = onDeleteUserListener;
    }


    class UserHolder extends RecyclerView.ViewHolder{
        TextView id ,username,password,lv,address;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_text);
            username = itemView.findViewById(R.id.username_text);
            password = itemView.findViewById(R.id.passwrod_text);
            lv = itemView.findViewById(R.id.lv_text);
            address = itemView.findViewById(R.id.address_text);
        }
    }
}
