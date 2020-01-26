package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.KioskWithdraw;

public interface KioskWithdrawRepository extends JpaRepository<KioskWithdraw, Integer>,KioskWithdrawCustomRepository{

}
