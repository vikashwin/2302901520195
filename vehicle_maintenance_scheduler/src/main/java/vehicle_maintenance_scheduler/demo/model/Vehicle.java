package vehicle_maintenance_scheduler.demo.model;

import lombok.Data;

@Data
public class Vehicle {

    private String taskID;

    private int duration;

    private int impact;
}