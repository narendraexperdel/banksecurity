package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.ReferalBonus;

@Component
public interface ReferalbonusRepository extends JpaRepository<ReferalBonus, Integer>, ReferalbonusCustomRepository{

}
