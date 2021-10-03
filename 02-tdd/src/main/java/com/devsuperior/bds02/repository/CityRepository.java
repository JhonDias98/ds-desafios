package com.devsuperior.bds02.repository;

import com.devsuperior.bds02.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
