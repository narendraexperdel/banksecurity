package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.MasterKiosk;

@Component
public interface MasterKioskRepository extends JpaRepository<MasterKiosk, Integer>,MasterKioskCustomRepository{

}
