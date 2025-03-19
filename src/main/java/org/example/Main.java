package org.example;

import org.example.Repositories.UserRepositories.User;
import org.example.Repositories.UserRepositories.UserRepository;
import org.example.Repositories.VehicleRepository.VehicleRepositoryImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        User user = new User("","");
        UserRepository userRepository = new UserRepository();

        while (!userRepository.login(user)){
            System.out.println("Podaj username:");
            user.setUsername(scanner.nextLine());
            System.out.println("Podaj haslo:");
            user.setPassword(scanner.nextLine());
            System.out.println();
        }





        VehicleRepositoryImpl vehicleRepositoryImpl = new VehicleRepositoryImpl();
        while(true){
            System.out.println("1.Rent vehicle");
            System.out.println("2.Return vehicle");
            System.out.println("3.Show vehicles");
            int choose = scanner.nextInt();
            if(choose == 1){
                System.out.println("Enter index: ");
                int index = scanner.nextInt();
                vehicleRepositoryImpl.rentVehicle(index);
            }if(choose == 2){
                vehicleRepositoryImpl.returnVehicle();
            }if(choose == 3){
                AtomicInteger ai = new AtomicInteger(1);
                vehicleRepositoryImpl.getVehicles().forEach(vehicle -> {
                    System.out.println(ai.getAndIncrement() + " " +vehicle.toString());
                });
            }
            vehicleRepositoryImpl.save();
        }

    }
}