package com.example.springSecurityII.controler;

import com.example.springSecurityII.entity.WeatherLog;
import com.example.springSecurityII.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/health")
    public String getHealth() {
        return "Healthy";
    }
//    @PreAuthorize("hasAuthority('WEATHER_READ')")

    @GetMapping("/wealth")
    public String getWealth() {
        return "Wealthy";
    }

    @PutMapping("/update")
    public String update() {
        return "updated weather";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('WEATHER_WRITE')")
    public String add() {
        return "weather added";
    }

    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('WEATHER_DELETE')")
    public String delete() {
        return "delete";
    }

//    @PostMapping("/log")   //the client is responsible for sending all required data (like performedBy, timeStamp, etc.).
//    public WeatherLog add(@RequestBody WeatherLog log) {
//        return weatherService.add(log);
//    }

    //For production, ensuring log integrity with authenticated user & timestamp

    @PostMapping("/log")
    public WeatherLog createLog(@RequestBody WeatherLog log, Principal principal) {
        log.setPerformedBy(principal.getName());
        log.setTimeStamp(LocalDateTime.now());
        return weatherService.createLog(log);
    }



    //@postAuthorize
    //If the returned object’s performedBy matches the logged-in username, OR
    //If the user has ROLE_ADMIN,
    @GetMapping("/byId/{id}")
    @PostAuthorize("returnObject.performedBy == authentication.name")  //let users access their own records
    public WeatherLog getById(@PathVariable Long id) {
        return weatherService.geById(id);
    }


}
