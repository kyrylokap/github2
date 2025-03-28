package org.example.Vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle{
    private int id;
    private String brand, model, year, price,attributes;
    private boolean rented;

    /*public Vehicle(int id, String brand, String model, String year, String price,boolean rented, String attributes ) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.attributes = attributes;
        this.rented = rented;
    }

    /*public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public boolean isRented() {
        return rented;
    }
    public void setRented(boolean rented) {
        this.rented = rented;
    }
*/
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", attributes='" + attributes + '\'' +
                ", rented=" + rented +
                '}';
    }

    public String toCsv(){
        return id + ";" + brand + ";" + model + ";" + year + ";" + price + ";" + rented +"\n";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && rented == vehicle.rented && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id, brand, model, year, price, rented);
    }
    @Override
    public Vehicle clone(){
        Vehicle vehicle = null;
        try {
            vehicle = (Vehicle) super.clone();
        }catch (CloneNotSupportedException e) {
            vehicle = new Vehicle(id,brand,model,year,price,attributes,rented);
        }
        return vehicle;
    }

}
