package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.Cmcbank;

@Component
public interface CmcBankRepository extends JpaRepository<Cmcbank, Integer>,CmcBankCustomRepository{

}
