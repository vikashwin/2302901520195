package vehicle_maintenance_scheduler.demo.model;

import java.util.List;

public class RequestDTO {

    private int mechanicHours;

    private List<Vehicle> vehicles;

    public int getMechanicHours() {
        return mechanicHours;
    }

    public void setMechanicHours(int mechanicHours) {
        this.mechanicHours = mechanicHours;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}