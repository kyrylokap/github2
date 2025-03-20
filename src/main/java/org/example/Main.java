package org.example;

import org.example.Repositories.UserRepositories.User;
import org.example.Repositories.VehicleRepository.VehicleRepositoryImpl;
import org.example.Services.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException{
        Scanner scanner = new Scanner(System.in);
        User user = new User("","","");
        UserService userService = new UserService();
        userService.logOrReg(user,scanner);

        VehicleRepositoryImpl vehicleRepositoryImpl = new VehicleRepositoryImpl();
        if(user.getRole().equals("user")){
            while(true){
                System.out.println();
                if(user.getRentedVehicle()!=0){
                    System.out.print("Rented: " +vehicleRepositoryImpl.getVehicleById(user.getRentedVehicle()).toCsv());
                }
                System.out.println("1.Rent vehicle");
                System.out.println("2.Return vehicle");
                System.out.println("3.Show vehicles");
                int choose = scanner.nextInt();
                if(choose == 1){
                    System.out.println("Enter index: ");
                    int index = scanner.nextInt();
                    vehicleRepositoryImpl.rentVehicle(index,user);
                }if(choose == 2){
                    vehicleRepositoryImpl.returnVehicle(user);
                }if(choose == 3){
                    vehicleRepositoryImpl.getVehiclesToString();
                }
                vehicleRepositoryImpl.save();
            }
        }else{
            while (true){
                System.out.println("1.Add vehicle");
                System.out.println("2.Remove vehicle");
                System.out.println("3.Show vehicles");
                System.out.println("4.Show users");
                int c = scanner.nextInt();
                if(c == 1){
                    vehicleRepositoryImpl.addVehicle();
                }if(c == 2){
                    vehicleRepositoryImpl.removeVehicle();
                }
                if(c == 3){
                    vehicleRepositoryImpl.getVehiclesToString();
                }if(c == 4){
                    System.out.println(userService.getUsers());
                }

            }
        }

    }
}