package org.example.Repositories.VehicleRepository;

import org.example.Vehicles.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleRepository {
    public void rentVehicle(int index);
    public void returnVehicle();
    public List<Vehicle> getVehicles() throws CloneNotSupportedException;
    public void save() throws IOException;
    public void load() throws IOException;
    public void removeVehicle(Vehicle vehicle);
    public void addVehicle(Vehicle vehicle);
}
