package com.example.linea_rapida.list_logic;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linea_rapida.R;
import com.example.linea_rapida.model.CaseTicket;
import com.example.linea_rapida.model.Role;
import com.example.linea_rapida.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class UserAdapter extends RecyclerView.Adapter<UserView>{

    private ArrayList<User> users;
    private ArrayList<User> showlist;
    private ArrayList<CaseTicket> tickets;
    private User user;

    public UserAdapter(User user){
        this.user = user;
        if (user.getRole().getRole() == Role.ADMIN_ROLE){
            users = new ArrayList<>();
            showlist = new ArrayList<>();

            //Datos dummy
            Role rol1 = new Role();
            Role rol2 = new Role();
            rol1.setRole(1);
            rol2.setRole(2);

            for (int i = 0; i < 5; i++) {
                User u = new User("username", UUID.randomUUID().toString(), rol1,"planta " + i, "email", "M");
                //users.add(u);

                User w = new User("username", UUID.randomUUID().toString(), rol2,"campo " + i, "email", "M");
                //users.add(w);
            }

        }else if(user.getRole().getRole() == Role.DOCTOR_ROLE){
            tickets = new ArrayList<>();
        }

        //onlyPlant();
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull UserView holder, int position) {
        holder.getTextView_name().setText(showlist.get(position).getFullName());
    }


    @NonNull
    @NotNull
    @Override
    public UserView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //XML --> View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View row = inflater.inflate(R.layout.userrow, parent, false);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        UserView userView = new UserView(rowroot);

        return userView;
    }

    @Override
    public int getItemCount() {

        if (user.getRole().getRole() == Role.ADMIN_ROLE){
            return showlist.size();
        }else{
            return tickets.size();
        }

    }

    public void onlyPlant(){
        showlist.clear();
        for (User u : users) {
            if (u.getRole().getRole() == Role.DOCTOR_ROLE){
                showlist.add(u);
            }
        }

        this.notifyDataSetChanged();
    }

    public void onlyField(){
        showlist.clear();
        for (User u : users) {
            if (u.getRole().getRole() == Role.REPORTER_ROLE){
                showlist.add(u);
            }
        }

        this.notifyDataSetChanged();
    }

    public void addUser(User user){
        users.add(user);

    }

}
