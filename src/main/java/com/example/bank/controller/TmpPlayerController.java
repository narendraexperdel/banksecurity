package com.example.bank.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.TmpPlayer;
import com.example.bank.service.TicketRaisedService;
import com.example.bank.service.TmpPlayerService;

@RestController
@RequestMapping("/api/")
public class TmpPlayerController {
	
	@Autowired
	TmpPlayerService tmpplayerservice;
	
	@RequestMapping(value = "userid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAlluserid(@RequestParam("productid") String productid, @RequestParam("companyid") Integer companyid, @RequestParam("userid") String userid) throws SQLException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("all user id");
		model.put("code", HttpStatus.OK);
		model.put("msg", "All UserId Details");
		model.put("data", tmpplayerservice.getalluserid(productid, userid, companyid));
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}

}
