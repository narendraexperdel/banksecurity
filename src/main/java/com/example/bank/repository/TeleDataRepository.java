package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Teledata;

@Component
public interface TeleDataRepository extends JpaRepository<Teledata, Integer>,TeleDataCustomRepository{

}
