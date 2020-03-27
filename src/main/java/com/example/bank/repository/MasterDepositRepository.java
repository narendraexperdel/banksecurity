package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.MasterDeposit;

public interface MasterDepositRepository extends JpaRepository<MasterDeposit, Integer>,MasterDepositCustomRepository{

}
