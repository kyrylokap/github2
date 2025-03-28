package org.example.Repositories.VehicleRepository;

import org.example.models.User;
import org.example.models.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleRepository {
    public void rentVehicle(int index, User user);
    public void returnVehicle( User user) throws IOException;
    public List<Vehicle> getVehicles() throws CloneNotSupportedException;
    public void load() throws IOException;
    public void removeVehicle() throws CloneNotSupportedException;
    public void addVehicle();
}
