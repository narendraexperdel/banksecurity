package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.WemPromotion;

@Component
public interface WempromotionRepository extends JpaRepository<WemPromotion, Integer>,WempromotionCustomRepository{

}
