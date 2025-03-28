package org.example;

import org.example.models.User;
import org.example.Repositories.VehicleRepository.VehicleRepositoryImpl;
import org.example.Services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        User user = new User("","","");
        UserService userService = new UserService();
        userService.logOrReg(user,scanner);
        VehicleRepositoryImpl vehicleRepositoryImpl = new VehicleRepositoryImpl();

        InterfaceImpl anInterface = new InterfaceImpl();
        anInterface.checkRole(user,vehicleRepositoryImpl,scanner,userService);

    }
}