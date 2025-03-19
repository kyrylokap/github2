package Vehicles;

import java.util.Objects;

public class Motorcycle extends Vehicle{

    String category;
    public Motorcycle(int id,String brand,String model,String year,String price, boolean rented,String category){
        this.rented = rented;
        this.year = year;
        this.model = model;
        this.price = price;
        this.id = id;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString(){
        return "Motorcycle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", rented=" + rented +
                ",category=" + category +
                '}';
    }

    @Override
    public String toCsv() {
        return id + ";" + brand + ";" + model + ";" + year + ";" + price + ";" + rented + ";" +category + "\n";
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o) && ((Motorcycle)o).category.equals(category);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, brand, model, year, price, rented,category);
    }

    @Override
    public Motorcycle clone(){
        Motorcycle customer = null;
        try {
            customer = (Motorcycle) super.clone();
        }catch (CloneNotSupportedException e) {
            customer = new Motorcycle(id,brand,model,year,price,rented,category);
        }
        return customer;
    }
}
