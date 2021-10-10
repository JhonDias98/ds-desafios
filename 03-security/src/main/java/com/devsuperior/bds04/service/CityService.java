package com.devsuperior.bds04.service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public List<CityDTO> findAll() {
        List<City> cities = repository.findAll(Sort.by("name"));
        return cities.stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName(dto.getName());

        entity = repository.save(entity);
        return new CityDTO(entity);
    }
}
