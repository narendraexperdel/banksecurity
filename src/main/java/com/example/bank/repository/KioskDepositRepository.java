package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.KioskDeposit;

public interface KioskDepositRepository extends JpaRepository<KioskDeposit, Integer>,KioskDepositCustomRepository{

}
