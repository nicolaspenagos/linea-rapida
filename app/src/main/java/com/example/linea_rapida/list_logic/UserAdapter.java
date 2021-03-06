package com.example.linea_rapida.list_logic;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linea_rapida.HomeFragmentAdmin;
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

    private HomeFragmentAdmin fragment;

    public UserAdapter(User user, HomeFragmentAdmin fragment){
        this.user = user;
        this.fragment = fragment;


        if (user.getRole().getRole() == Role.ADMIN_ROLE){
            users = new ArrayList<>();
            showlist = new ArrayList<>();

        }else if(user.getRole().getRole() == Role.DOCTOR_ROLE){
            tickets = new ArrayList<>();
        }

    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull UserView holder, int position) {
        holder.getTextView_name().setText(showlist.get(position).getFullName());
        holder.setUserId(showlist.get(position).getId());
        holder.setFragment(fragment);
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

    public ArrayList<User> getUsers() {
        return users;
    }
}
