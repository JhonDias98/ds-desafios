package com.desafio.dto;

import com.desafio.entities.Client;

import javax.persistence.Column;
import java.time.Instant;

public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;

    public ClientDTO(String name, String cpf, Double income, Instant birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public ClientDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public Client toModel() {
        return new Client(name, cpf, income, birthDate, children);
    }


}
