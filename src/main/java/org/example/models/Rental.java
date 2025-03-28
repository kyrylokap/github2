package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rental{
    private String id,vehicleId,userId,rentDate,returnDate;

    @Override
    public String toString() {
        return "Rental{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", rentDate='" + rentDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
