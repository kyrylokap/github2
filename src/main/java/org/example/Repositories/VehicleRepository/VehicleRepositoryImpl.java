package org.example.Repositories.VehicleRepository;

import com.google.gson.reflect.TypeToken;
import org.example.JsonFileStorage;
import org.example.Repositories.RentalRepository;
import org.example.models.User;
import org.example.models.Rental;
import org.example.models.Vehicle;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleRepositoryImpl implements IVehicleRepository {
    JsonFileStorage<Vehicle> jsonFileStorage = new JsonFileStorage<>("vehicles.json", new TypeToken<List<Vehicle>>(){}.getType());
    private RentalRepository rentalRepository = new RentalRepository();
    public void getRentedVehicles(){
        rentalRepository.getRentedVehicles();
    }

    private List<Vehicle>vehicles;
    private List<Vehicle>deepCopyVehicles;


    public Vehicle getVehicleById(int id){
        return vehicles.get(id);
    }
    public VehicleRepositoryImpl(){
        try {
            vehicles = jsonFileStorage.load();
            deepCopyVehicles = getVehicles();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void load(){
        vehicles = jsonFileStorage.load();
    }

    @Override
    public void removeVehicle(){
        getVehiclesToString();
        System.out.println("Podaj index:");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        if(vehicles.size() > index && index >= 0){
            System.out.print("Vehicle removed!");
            System.out.println(vehicles.get(index).toCsv());
            vehicles.remove(index);
            
        }
        jsonFileStorage.save(vehicles);
    }

    @Override
    public void addVehicle(){
        Scanner scanner = new Scanner(System.in);
        String brand,model,  plate,category,year;
        System.out.println("Category:");
        category = scanner.nextLine();
        System.out.println("Brand:");
        brand = scanner.nextLine();
        System.out.println("Model:");
        model = scanner.nextLine();
        System.out.println("Year:");
        year = scanner.nextLine();
        System.out.println("Plate:");
        plate = scanner.nextLine();
        System.out.println("Attributes:");
        Map<String,Object> attr = new HashMap<>();
        while(true){
            System.out.println("Key");
            String at = scanner.nextLine();
            if(at.equals("stop"))break;
            System.out.println("Value");
            String ob = scanner.nextLine();
            try{
                attr.put(at,Integer.parseInt(ob));
            }catch (NumberFormatException e){
                attr.put(at,ob);
            }
        }
        Vehicle vehicle = new Vehicle(String.valueOf(vehicles.size() - 1),brand,category,model,Integer.parseInt(year),plate,attr);
        vehicles.add(vehicle);
        jsonFileStorage.save(vehicles);
        System.out.print("Dodano : ");
        System.out.println(vehicle.toString());
    }

    @Override
    public void rentVehicle(int index, User user){
        boolean canRent = true;
        for(Vehicle vehicle:vehicles){
            if(vehicle.isRented()){
                canRent = false;
                System.out.println("You have rented car!");
                System.out.println(vehicle.toString());
                break;
            }
        }
        Vehicle vehicle;
        if(canRent && index >= 0){
            deepCopyVehicles.get(index - 1).setRented(true);
            user.setRentedVehicle(index - 1);
            vehicle = vehicles.get(index - 1);
            vehicle.setRented(true);
            System.out.println(vehicle.toString() + " rented");
            Rental rental = new Rental(BCrypt.hashpw(String.valueOf(rentalRepository.getId()), BCrypt.gensalt()), vehicle.getId(), BCrypt.hashpw(String.valueOf(user.getId()), BCrypt.gensalt()), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),"0");
            rentalRepository.addRental(rental);
        }
        jsonFileStorage.save(vehicles);
    }

    @Override
    public void returnVehicle(User user){
        vehicles.forEach(vehicle -> {
            if(vehicle.isRented()){
                vehicle.setRented(false);
                System.out.print("Vehicle returned:" + vehicle.toString());
                user.setRentedVehicle(0);
                rentalRepository.returnRental(vehicle.getId());
            }
        });
        jsonFileStorage.save(vehicles);
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

    public void getVehiclesToString(){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger ai = new AtomicInteger(1);
        vehicles.forEach(vehicle -> {
            stringBuilder.append(ai.getAndIncrement()).append(". ").append(vehicle.toString()).append("\n");
        });
        System.out.println(stringBuilder);
    }


}
