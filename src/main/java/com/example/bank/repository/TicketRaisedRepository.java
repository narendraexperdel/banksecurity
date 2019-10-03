package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bank.model.TicketRaised;

@Component
public interface TicketRaisedRepository extends JpaRepository<TicketRaised, Integer>{

}
