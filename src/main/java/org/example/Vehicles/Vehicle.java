package Vehicles;

import java.util.Objects;

public abstract class Vehicle{
    int id;
    String brand, model, year, price;
    boolean rented;
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
    @Override
    public abstract String toString();
    public abstract String toCsv();

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
    public Vehicle clone() throws CloneNotSupportedException{
        return (Vehicle) super.clone();
    }

}
