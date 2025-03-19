package Vehicles;

public class Car extends Vehicle{

    public Car(int id,String brand,String model,String year,String price, boolean rented) {
        this.rented = rented;
        this.year = year;
        this.model = model;
        this.price = price;
        this.id = id;
        this.brand = brand;
    }

    @Override
    public String toString(){
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", rented=" + rented +
                '}';
    }

    @Override
    public String toCsv(){
        return id + ";" + brand + ";" + model + ";" + year + ";" + price + ";" + rented +"\n";
    }

    @Override
    public boolean equals(Object o){
        return super.equals(o);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public Car clone(){
        Car customer = null;
        try {
            customer = (Car) super.clone();
        }catch (CloneNotSupportedException e) {
            customer = new Car(id,brand,model,year,price,rented);
        }
        return customer;
    }
}
