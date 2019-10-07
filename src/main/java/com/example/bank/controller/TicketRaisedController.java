package com.example.bank.controller;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.TicketRaised;
import com.example.bank.service.TicketRaisedService;

@RestController
@RequestMapping("/api/")
public class TicketRaisedController {
	
	@Autowired
	TicketRaisedService ticketraisedService;
	
	SecureRandom random = new SecureRandom();
	
	
	  @RequestMapping(value ="", method = RequestMethod.GET)
	    public String  index() {
	       return "Ticket-Raised Application is working!";
	    	/*return "   ";*/
	    }
	
	@RequestMapping(value = "ticketraised", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveticketraised(@RequestBody TicketRaised ticketraised) throws SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime = now.format(formatter);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(ticketraised.getEmail() != null && ticketraised.getSubject() != null && ticketraised.getDescription() != null) {
			
			ticketraised.setTicketraiseddate(formatDateTime);
			String ticketgenerated = Integer.toString(random.nextInt());
			String ticketid = ticketgenerated.replace("-", "");
			
			ticketraised.setTicketid(ticketid);
			
			TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
			
			model.put("code", HttpStatus.CREATED);
			model.put("msg", "Ticket Raised Successfully");
			model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			return new ResponseEntity<Object>(model, HttpStatus.OK);
			
			
		}else {
			
			model.put("code", HttpStatus.BAD_REQUEST);
			model.put("msg", "Please Pass subject,Description and Email");
			model.put("data", "Your Ticket is not created");
			return new ResponseEntity<Object>(model, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@RequestMapping(value = "ticketraised", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllticket() throws SQLException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("code", HttpStatus.OK);
		model.put("msg", "All Ticket Details");
		model.put("data", ticketraisedService.allTicket());
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}

}
