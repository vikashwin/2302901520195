package vehicle_maintenance_scheduler.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vehicle_maintenance_scheduler.demo.model.Depot;

@Service
public class ApiService {

    private final RestTemplate restTemplate =
            new RestTemplate();

    public Depot[] getDepots() {

        String url = "http://4.224.186.213/evaluation-service/depots";

        return restTemplate
                .getForObject(url, Depot[].class);
    }

}