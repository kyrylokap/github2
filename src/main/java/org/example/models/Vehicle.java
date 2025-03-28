package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Vehicle{
    private String id, category,brand, model,  plate;
    private int year;
    private Map<String,Object> attributes;
    private boolean rented;

    public Vehicle(String id, String category, String brand, String model,int year, String plate, Map<String,Object> attributes) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.attributes = attributes;
        rented = false;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", plate='" + plate + '\'' +
                ", attributes=" + attributes +
                ", rented=" + rented +
                '}';
    }

    public String toCsv(){
        return id + ";" + brand + ";" + model + ";" + year + ";" + plate + ";" + rented + ";"+ attributes + "\n";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id)&& rented == vehicle.rented && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,category,brand,model,year,plate,attributes,rented);
    }
    @Override
    public Vehicle clone(){
        Vehicle vehicle = null;
        try {
            vehicle = (Vehicle) super.clone();
        }catch (CloneNotSupportedException e) {
            vehicle = new Vehicle(id,category,brand,model,year,plate,attributes);
        }
        return vehicle;
    }

}
