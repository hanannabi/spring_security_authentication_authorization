package com.example.springSecurityII.service;

import com.example.springSecurityII.entity.WeatherLog;
import com.example.springSecurityII.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public WeatherLog createLog(WeatherLog log) {
        return weatherRepository.save(log);
    }

    public WeatherLog geById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log Not Found"));
    }

//    public WeatherLog add(WeatherLog log) {
//        return weatherRepository.save(log);
//    }
}
