package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.ScrapperWithdrawal;

@Component
public interface ScrapperWithdrawalRepository extends JpaRepository<ScrapperWithdrawal, Integer>, ScrapperWithdrawalCustomRepository{

}
