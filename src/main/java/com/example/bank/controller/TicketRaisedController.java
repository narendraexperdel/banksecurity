package com.example.bank.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.bank.bean.TicketRaised;
import com.example.bank.bean.TicketRaisedBean;
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
	
	@RequestMapping(value = "ticketraised", method = RequestMethod.POST)
	public ResponseEntity<Object> saveticketraised(TicketRaisedBean ticketraised,@RequestParam("image") MultipartFile image,
			HttpServletRequest request) throws SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime1 = now.format(formatter);
		
		Date formatDateTime = new Date();
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		if(ticketraised.getSubject().equals("Claim Promotion")) {
			
			if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() !=null && ticketraised.getUserid() !=null) {
				
				String ticketgenerated = Integer.toString(random.nextInt());
				String ticketid = ticketgenerated.replace("-", "");
				
				/*String filename = image.getOriginalFilename();
				ServletContext sc = request.getServletContext();
				String path = sc.getRealPath("/");
				File dir = null;
				File serverFile = null;
				String line = "";
				String cvsSplitBy = ",";
				Map<String, Object> bulkreaddata = new HashMap<>();
				String modifyfilename = ticketid+""+filename;
				
				if (filename.length() > 0) {
					try {
						byte barr[] = image.getBytes();
						dir = new File(path + File.separator + "ticketraiseddata");
						if (!dir.exists())
							dir.mkdirs();

						serverFile = new File(dir + File.separator + modifyfilename);
						BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
						bout.write(barr);
						bout.flush();
						bout.close();
					} catch (Exception e) {
						System.out.println(e);
					}
					
					
				}*/
				
				
				ticketraised.setTicketraiseddate(formatDateTime);
				ticketraised.setTicketdate(formatDateTime1);
				ticketraised.setLasteditdate(formatDateTime);
			
				ticketraised.setCasenumber("0003");
				
				ticketraised.setTicketid(ticketid);
				ticketraised.setAssignto("CS1");
				ticketraised.setStatus("Pending");
				
				
				TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
				
				model.put("code", HttpStatus.CREATED);
				model.put("msg", "Ticket Raised Successfully");
				model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			
				
			}else {
				
				model.put("code", HttpStatus.BAD_REQUEST);
				model.put("msg", "Please Pass all fields");
				model.put("data", "Your Ticket is not created");
				
			}
			
			
		}else if(ticketraised.getSubject().equals("Deposit")) {
			if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() !=null && ticketraised.getPlayername() !=null) {
				
				String ticketgenerated = Integer.toString(random.nextInt());
				String ticketid = ticketgenerated.replace("-", "");
				
				String filename = image.getOriginalFilename();
				ServletContext sc = request.getServletContext();
				String path = sc.getRealPath("/");
				File dir = null;
				File serverFile = null;
				String line = "";
				String cvsSplitBy = ",";
				Map<String, Object> bulkreaddata = new HashMap<>();
				String modifyfilename = ticketid+""+filename;
				
				if (filename.length() > 0) {
					try {
						byte barr[] = image.getBytes();
						dir = new File(path + File.separator + "ticketraiseddata");
						if (!dir.exists())
							dir.mkdirs();

						serverFile = new File(dir + File.separator + modifyfilename);
						BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
						bout.write(barr);
						bout.flush();
						bout.close();
					} catch (Exception e) {
						System.out.println(e);
					}
					
					
				}
				
				ticketraised.setTicketraiseddate(formatDateTime);
				ticketraised.setTicketdate(formatDateTime1);
				ticketraised.setLasteditdate(formatDateTime);
				ticketraised.setImage1(modifyfilename);
				ticketraised.setCasenumber("0001");
				
				ticketraised.setTicketid(ticketid);
				ticketraised.setAssignto("CS1");
				ticketraised.setStatus("Pending");
				
				TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
				
				model.put("code", HttpStatus.CREATED);
				model.put("msg", "Ticket Raised Successfully");
				model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			
				
			}else {
				
				model.put("code", HttpStatus.BAD_REQUEST);
				model.put("msg", "Please Pass all fields");
				model.put("data", "Your Ticket is not created");
			
			}
		
		
	}else if(ticketraised.getSubject().equals("Bonus Available")) {
		if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() !=null ) {
			ticketraised.setTicketraiseddate(formatDateTime);
			ticketraised.setTicketdate(formatDateTime1);
			ticketraised.setLasteditdate(formatDateTime);
			String ticketgenerated = Integer.toString(random.nextInt());
			String ticketid = ticketgenerated.replace("-", "");
			ticketraised.setCasenumber("0006");
			
			ticketraised.setTicketid(ticketid);
			ticketraised.setAssignto("CS1");
			ticketraised.setStatus("Pending");
			
			TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
			
			model.put("code", HttpStatus.CREATED);
			model.put("msg", "Ticket Raised Successfully");
			model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			
			
		}else {
			
			model.put("code", HttpStatus.BAD_REQUEST);
			model.put("msg", "Please Pass all fields");
			model.put("data", "Your Ticket is not created");
		
		}
	
	
} else if(ticketraised.getSubject().equals("Withdraw")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getPlayername() !=null && ticketraised.getUserid() !=null && ticketraised.getAmount() !=null && ticketraised.getBankname() !=null && ticketraised.getBankaccount() !=null) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0002");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS1");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");

	}


}else if(ticketraised.getSubject().equals("Check Balance")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null && ticketraised.getUserid() !=null ) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0005");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS1");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}else if(ticketraised.getSubject().equals("Report Issue")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null && ticketraised.getUserid() !=null && ticketraised.getPlayername() != null && ticketraised.getGamename() != null) {
		
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		
		String filename = image.getOriginalFilename();
		ServletContext sc = request.getServletContext();
		String path = sc.getRealPath("/");
		File dir = null;
		File serverFile = null;
		String line = "";
		String cvsSplitBy = ",";
		Map<String, Object> bulkreaddata = new HashMap<>();
		String modifyfilename = ticketid+""+filename;
		
		if (filename.length() > 0) {
			try {
				byte barr[] = image.getBytes();
				dir = new File(path + File.separator + "ticketraiseddata");
				if (!dir.exists())
					dir.mkdirs();

				serverFile = new File(dir + File.separator + modifyfilename);
				BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
				bout.write(barr);
				bout.flush();
				bout.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
		}
		
		
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		ticketraised.setCasenumber("0010");
		ticketraised.setImage1(modifyfilename);
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS1");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}else if(ticketraised.getSubject().equals("Transfer Balance")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null && ticketraised.getUserid() !=null && ticketraised.getGamename() != null && ticketraised.getAmount() !=null && ticketraised.getTogame() !=null && ticketraised.getTouserid() !=null) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0009");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS2");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}
		
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "ticketid", method = RequestMethod.POST , produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveticketraised(@RequestBody TicketRaisedBean ticketraised) throws SQLException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime1 = now.format(formatter);
		
		Date formatDateTime = new Date();
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		
		if(ticketraised.getSubject().equals("Claim Promotion")) {
			
			if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() !=null && ticketraised.getUserid() !=null) {
				
				String ticketgenerated = Integer.toString(random.nextInt());
				String ticketid = ticketgenerated.replace("-", "");
				
				/*String filename = image.getOriginalFilename();
				ServletContext sc = request.getServletContext();
				String path = sc.getRealPath("/");
				File dir = null;
				File serverFile = null;
				String line = "";
				String cvsSplitBy = ",";
				Map<String, Object> bulkreaddata = new HashMap<>();
				String modifyfilename = ticketid+""+filename;
				
				if (filename.length() > 0) {
					try {
						byte barr[] = image.getBytes();
						dir = new File(path + File.separator + "ticketraiseddata");
						if (!dir.exists())
							dir.mkdirs();

						serverFile = new File(dir + File.separator + modifyfilename);
						BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
						bout.write(barr);
						bout.flush();
						bout.close();
					} catch (Exception e) {
						System.out.println(e);
					}
					
					
				}*/
				
				
				ticketraised.setTicketraiseddate(formatDateTime);
				ticketraised.setTicketdate(formatDateTime1);
				ticketraised.setLasteditdate(formatDateTime);
			
				ticketraised.setCasenumber("0003");
				
				ticketraised.setTicketid(ticketid);
				ticketraised.setAssignto("CS1");
				ticketraised.setStatus("Pending");
				
				
				TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
				
				model.put("code", HttpStatus.CREATED);
				model.put("msg", "Ticket Raised Successfully");
				model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			
				
			}else {
				
				model.put("code", HttpStatus.BAD_REQUEST);
				model.put("msg", "Please Pass all fields");
				model.put("data", "Your Ticket is not created");
				
			}
			
			
		}else if(ticketraised.getSubject().equals("Bonus Available")) {
		if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() !=null ) {
			ticketraised.setTicketraiseddate(formatDateTime);
			ticketraised.setTicketdate(formatDateTime1);
			ticketraised.setLasteditdate(formatDateTime);
			String ticketgenerated = Integer.toString(random.nextInt());
			String ticketid = ticketgenerated.replace("-", "");
			ticketraised.setCasenumber("0006");
			
			ticketraised.setTicketid(ticketid);
			ticketraised.setAssignto("CS1");
			ticketraised.setStatus("Pending");
			
			TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
			
			model.put("code", HttpStatus.CREATED);
			model.put("msg", "Ticket Raised Successfully");
			model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
			
			
		}else {
			
			model.put("code", HttpStatus.BAD_REQUEST);
			model.put("msg", "Please Pass all fields");
			model.put("data", "Your Ticket is not created");
		
		}
	
	
} else if(ticketraised.getSubject().equals("Withdraw")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getPlayername() !=null && ticketraised.getUserid() !=null && ticketraised.getAmount() !=null && ticketraised.getBankname() !=null && ticketraised.getBankaccount() !=null) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0002");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS1");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");

	}


}else if(ticketraised.getSubject().equals("Check Balance")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null && ticketraised.getUserid() !=null ) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0005");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS1");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}else if(ticketraised.getSubject().equals("Transfer Balance")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null && ticketraised.getUserid() !=null && ticketraised.getGamename() != null && ticketraised.getAmount() !=null && ticketraised.getTogame() !=null && ticketraised.getTouserid() !=null) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0009");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS2");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}else if(ticketraised.getSubject().equals("Game List")) {
	if(ticketraised.getUsercontact() != null && ticketraised.getSubject() != null  && ticketraised.getGamename() != null ) {
		ticketraised.setTicketraiseddate(formatDateTime);
		ticketraised.setTicketdate(formatDateTime1);
		ticketraised.setLasteditdate(formatDateTime);
		String ticketgenerated = Integer.toString(random.nextInt());
		String ticketid = ticketgenerated.replace("-", "");
		ticketraised.setCasenumber("0011");
		
		ticketraised.setTicketid(ticketid);
		ticketraised.setAssignto("CS2");
		ticketraised.setStatus("Pending");
		
		TicketRaised ticketraisedsaved = ticketraisedService.saveticketraised(ticketraised);
		
		model.put("code", HttpStatus.CREATED);
		model.put("msg", "Ticket Raised Successfully");
		model.put("data", "Your Ticket Id is : "+ticketraisedsaved.getTicketid());
		
		
	}else {
		
		model.put("code", HttpStatus.BAD_REQUEST);
		model.put("msg", "Please Pass all fields");
		model.put("data", "Your Ticket is not created");
		
	}


}
		
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "ticketraised", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllticket() throws SQLException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("code", HttpStatus.OK);
		model.put("msg", "All Ticket Details");
		model.put("data", ticketraisedService.allTicket());
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "pendingticket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPendingticket() throws SQLException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("code", HttpStatus.OK);
		model.put("msg", "All Ticket Details");
		model.put("data", ticketraisedService.pendingticketcount());
		return new ResponseEntity<Object>(model, HttpStatus.OK);
		
	}

}
