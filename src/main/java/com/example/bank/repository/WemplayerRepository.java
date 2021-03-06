package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.WemPlayer;

@Component
public interface WemplayerRepository extends JpaRepository<WemPlayer, Integer>,WemplayerCustomRepository {

}
