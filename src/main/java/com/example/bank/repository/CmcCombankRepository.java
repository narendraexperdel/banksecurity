package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmccombank;

@Component
public interface CmcCombankRepository extends JpaRepository<Cmccombank, Integer>, CmcCombankCustomRepository {

}
