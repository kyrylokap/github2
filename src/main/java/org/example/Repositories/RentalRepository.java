package org.example.Repositories;

import com.google.gson.reflect.TypeToken;
import lombok.NoArgsConstructor;
import org.example.JsonFileStorage;
import org.example.models.Rental;
import org.example.models.User;
import org.example.models.Vehicle;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RentalRepository{
    private List<Rental> rentedVehicles = new ArrayList<>();
    private JsonFileStorage<Rental> jsonFileStorage;
    public RentalRepository(){

        jsonFileStorage = new JsonFileStorage<>("rentals.json", new TypeToken<List<Rental>>(){}.getType());
        load();
    }
    public void load(){
        rentedVehicles = jsonFileStorage.load();
    }

    public void save(){
        jsonFileStorage.save(rentedVehicles);
    }
    public void addRental(Rental rental){
        rentedVehicles.add(rental);
        save();
    }


    public Rental findById(String id){
        Rental rent = null;
        for(int i = 0;i < rentedVehicles.size();i++){
            if(id.equals(rentedVehicles.get(i).getVehicleId())){
                rent = rentedVehicles.get(i);
                break;
            }
        }
        return rent;
    }
    public String getId(){
        return String.valueOf(rentedVehicles.size());
    }

    public void returnRental(String id){
        if(!rentedVehicles.isEmpty()){
            findById(id).setReturnDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        save();
    }

    public void getRentedVehicles(){
        StringBuilder sb = new StringBuilder();
        rentedVehicles.forEach(r->{
            sb.append(r.toString()).append("\n");
        });
        System.out.println(sb);
    }
}
