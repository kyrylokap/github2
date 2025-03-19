package org.example.Repositories.VehicleRepository;

import org.example.Repositories.VehicleRepository.IVehicleRepository;
import org.example.Vehicles.Car;
import org.example.Vehicles.Motorcycle;
import org.example.Vehicles.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl implements IVehicleRepository {
    String path = "lab.csv";
    private final List<Vehicle>vehicles;
    private List<Vehicle>deepCopyVehicles;

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
        System.out.println("Finished adding to list!");
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        for(Vehicle v:vehicles){
            if( v.equals(vehicle)){
                vehicles.remove(v);
                System.out.print("Removed vehicle! :");
                System.out.println(vehicle.toCsv());
            }
            break;
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public void rentVehicle(int index){
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
            System.out.println(deepCopyVehicles.get(index - 1).toString() + " rented");
        }
    }

    @Override
    public void returnVehicle(){
        deepCopyVehicles.forEach(vehicle -> {
            if(vehicle.isRented()){
                vehicle.setRented(false);
                System.out.println("Vehicle :" + vehicle.toCsv() + " returned");
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
