package org.example.Repositories.UserRepositories;

import lombok.NoArgsConstructor;
import org.example.models.Rental;
import org.example.models.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RentalRepository{
    private List<Rental> rentedVehicles = new ArrayList<>();
    public void addRental(Rental rental){
        rentedVehicles.add(rental);
    }
    public int findByModel(String model){
        for(int i = 0;i < rentedVehicles.size();i++){
            if(rentedVehicles.get(i).getVehicle().getModel().equals(model))return i;
        }
        return -1;
    }

    public Rental findById(int id){
        return rentedVehicles.get(id);
    }

    public void returnRental(int id){
        if(!rentedVehicles.isEmpty()){
            findById(id).setReturnedTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

    }
    public void getRentedVehicles(){
        StringBuilder sb = new StringBuilder();
        rentedVehicles.forEach(r->{
            sb.append(r.getUser().toString()).append("  Car:   ")
                    .append(r.getVehicle().toString()).append("   Rented time:    ").append(r.getRentedTime())
                    .append("  Returned time: ").append(r.getReturnedTime()).append("\n");
        });
        System.out.println(sb);
    }
}
