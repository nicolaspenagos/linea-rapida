package com.example.linea_rapida.model;

import java.util.ArrayList;
import java.util.List;

public class Role {

    public static final int ADMIN_ROLE = 0;
    public static final int DOCTOR_ROLE = 1;
    public static final int REPORTER_ROLE = 2;


    private int roleId;
    private String name;
    private List<String> privileges;

    public Role(){

    }

    public Role(int roleId){

        this.roleId = roleId;


        switch (roleId){
            
            case ADMIN_ROLE:

                this.name = "Administrator";
                break;
            case DOCTOR_ROLE:
                this.name = "Doctor";
                break;
            case REPORTER_ROLE:
                this.name = "Reporter";
                break;
        }

        privileges =  new ArrayList<String>();

    }

    public Role(int roleId, String name, List<String> privileges) {
        this.roleId = roleId;
        this.name = name;
        this.privileges = privileges;
    }

    public int getRole() {
        return roleId;
    }

    public void setRole(int role) {
        this.roleId = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

}
