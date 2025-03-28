package org.example.Repositories.UserRepositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class User {
    private String password;
    private String username;
    private String role;
    private int rentedVehicle = 0;

    public User(String username, String password,String role){
        this.role = role;
        this.password = password;
        this.username = username;
    }
/*


    public int getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(int rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    */
    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", rentedVehicle=" + rentedVehicle +
                '}';
    }
}
