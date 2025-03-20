package org.example.Repositories.VehicleRepository;

import org.example.Repositories.UserRepositories.User;
import org.example.Vehicles.Car;
import org.example.Vehicles.Motorcycle;
import org.example.Vehicles.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleRepositoryImpl implements IVehicleRepository {
    String path = "lab.csv";
    private final List<Vehicle>vehicles;
    private List<Vehicle>deepCopyVehicles;

    public Vehicle getVehicleById(int id){
        return vehicles.get(id);
    }
    public VehicleRepositoryImpl(){
        vehicles = new ArrayList<>();
        try {
            load();
            deepCopyVehicles = getVehicles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while (( line = br.readLine()) !=  null){
            String[] v = line.split(";");
            if(v[0].equals("0")){
                vehicles.add(new Car(0,v[1],v[2],v[3],v[4],Boolean.parseBoolean(v[5])));
            }else{
                vehicles.add(new Motorcycle(1,v[1],v[2],v[3],v[4],Boolean.parseBoolean(v[5]),v[6]));
            }
        }
    }

    @Override
    public void removeVehicle() throws CloneNotSupportedException {
        getVehiclesToString();
        System.out.println("Podaj index:");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        if(vehicles.size() > index && index >= 0){
            System.out.print("Vehicle removed!");
            System.out.println(vehicles.get(index).toCsv());
            vehicles.remove(index);
        }

    }

    @Override
    public void addVehicle(){
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        String brand,model, year, price;
        brand = scanner.nextLine();
        model = scanner.nextLine();
        year = scanner.nextLine();
        price = scanner.nextLine();

        if(type.equals("car")){
            Car car = new Car(vehicles.size(), brand,model,year,price,false);
            vehicles.add(car);
            System.out.print("Dodano ");
            System.out.println(car.toCsv());
        }else{
            String category = scanner.nextLine();
            Motorcycle motorcycle = new Motorcycle(vehicles.size(), brand,model,year,price,false,category);
            vehicles.add(motorcycle);
            System.out.print("Dodano ");
            System.out.println(motorcycle.toCsv());
        }
    }

    @Override
    public void rentVehicle(int index, User user){
        boolean canRent = true;
        for(Vehicle vehicle:deepCopyVehicles){
            if(vehicle.isRented()){
                canRent = false;
                System.out.println("You have rented car!");
                break;
            }
        }
        if(canRent && index >= 0){
            deepCopyVehicles.get(index - 1).setRented(true);
            user.setRentedVehicle(index - 1);
            System.out.println(deepCopyVehicles.get(index - 1).toString() + " rented");
        }
    }

    @Override
    public void returnVehicle(User user){
        deepCopyVehicles.forEach(vehicle -> {
            if(vehicle.isRented()){
                vehicle.setRented(false);
                System.out.print("Vehicle returned:" + vehicle.toCsv());
                user.setRentedVehicle(0);
            }
        });
    }


    @Override
    public List<Vehicle> getVehicles() throws CloneNotSupportedException {
        List<Vehicle> vehicleToCopy = new ArrayList<>();
        for(Vehicle v:vehicles){
            Vehicle vehicle = v.clone();
            vehicleToCopy.add(vehicle);

        }

        return vehicleToCopy;
    }

    public void getVehiclesToString() throws CloneNotSupportedException {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger ai = new AtomicInteger(0);
        getVehicles().forEach(vehicle -> {
            stringBuilder.append(ai.getAndIncrement()).append(". ").append(vehicle.toString()).append("\n");
        });
        System.out.println(stringBuilder);
    }

    @Override
    public void save() throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        vehicles.forEach(vehicle -> {
            try {
                bufferedWriter.write(vehicle.toCsv());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bufferedWriter.close();
    }
}
