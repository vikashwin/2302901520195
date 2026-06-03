package vehicle_maintenance_scheduler.demo.service;

import org.springframework.stereotype.Service;
import vehicle_maintenance_scheduler.demo.model.Vehicle;
import vehicle_maintenance_scheduler.demo.util.KnapsackUtil;

import java.util.List;

@Service
public class SchedulerService {

    public List<Vehicle> schedule(List<Vehicle> vehicles, int mechanicHours) {

        return KnapsackUtil.optimize(vehicles, mechanicHours );
    }
}