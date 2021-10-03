package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;

    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);

            return new EventDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id event not found " + id);
        }
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {
        try {
            entity.setName(dto.getName());
            entity.setDate(dto.getDate());
            entity.setUrl(dto.getUrl());
            City city = cityRepository.getOne(dto.getCityId());
            entity.setCity(city);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id city not found " + dto.getCityId());
        }
    }
}
