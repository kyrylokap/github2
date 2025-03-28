package org.example.models;

import lombok.Data;

@Data
public class User {
    private String id;
    private String login;
    private String password;
    private String role;
    private int rentedVehicle = 0;

    public User(String username, String password,String role){
        this.role = role;
        this.password = password;
        this.login = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", rentedVehicle=" + rentedVehicle +
                '}';
    }
}
