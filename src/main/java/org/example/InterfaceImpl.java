package org.example;

import lombok.NoArgsConstructor;
import org.example.Repositories.VehicleRepository.VehicleRepositoryImpl;
import org.example.Services.UserService;
import org.example.models.User;

import java.util.Scanner;

@NoArgsConstructor
public class InterfaceImpl {
    public void checkRole(User user, VehicleRepositoryImpl vehicleRepositoryImpl, Scanner scanner, UserService userService){
        if(user.getRole().equals("USER")){
            userInterface(user,vehicleRepositoryImpl,scanner);
        }else{
            adminInterface(scanner,vehicleRepositoryImpl,userService);
        }
    }
    public void userInterface(User user,VehicleRepositoryImpl vehicleRepositoryImpl,Scanner scanner){
        while(true){
            System.out.println();
            if(user.getRentedVehicle() !=0 ){
                System.out.print("Rented: " + vehicleRepositoryImpl.getVehicleById(user.getRentedVehicle()).toString());
                System.out.println();
            }
            System.out.println("1.Rent vehicle");
            System.out.println("2.Return vehicle");
            System.out.println("3.Show vehicles");
            int choose = scanner.nextInt();
            if(choose == 1){
                System.out.println("Enter index: ");
                int index = scanner.nextInt();
                vehicleRepositoryImpl.rentVehicle(index,user);
            }if(choose == 2)vehicleRepositoryImpl.returnVehicle(user);
            if(choose == 3)vehicleRepositoryImpl.getVehiclesToString();

        }
    }
    public void adminInterface(Scanner scanner,VehicleRepositoryImpl vehicleRepositoryImpl,UserService userService){
        while (true){
            System.out.println("1.Add vehicle");
            System.out.println("2.Remove vehicle");
            System.out.println("3.Show vehicles");
            System.out.println("4.Show users");
            System.out.println("5.Show history rented cars");
            int c = scanner.nextInt();
            if(c == 1)vehicleRepositoryImpl.addVehicle();
            if(c == 2)vehicleRepositoryImpl.removeVehicle();
            if(c == 3)vehicleRepositoryImpl.getVehiclesToString();
            if(c == 4)System.out.println(userService.getUsers());
            if (c == 5)vehicleRepositoryImpl.getRentedVehicles();

        }
    }
}
