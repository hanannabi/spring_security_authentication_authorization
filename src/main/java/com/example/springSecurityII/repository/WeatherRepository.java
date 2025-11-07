package com.example.springSecurityII.repository;

import com.example.springSecurityII.entity.WeatherLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherLog, Long> {
}
