package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.bank.model.MasterWithdraw;

@Component
public interface MasterWithdrawRepository extends JpaRepository<MasterWithdraw, Integer>, MasterWithdrawCustomRepository{

}
