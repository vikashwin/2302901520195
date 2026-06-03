package vehicle_maintenance_scheduler.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicle_maintenance_scheduler.demo.model.RequestDTO;
import vehicle_maintenance_scheduler.demo.model.Vehicle;
import vehicle_maintenance_scheduler.demo.service.SchedulerService;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @PostMapping
    public List<Vehicle> optimize(@RequestBody RequestDTO dto) {

        return schedulerService.schedule(
                dto.getVehicles(),
                dto.getMechanicHours()
        );
    }
}