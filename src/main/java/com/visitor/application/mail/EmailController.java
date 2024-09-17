package com.visitor.application.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send-email")
	public void sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text)
			throws MessagingException {
		emailService.sendEmail(to, subject, text);
	}

	@PostMapping("/sendvisitordetails")
	public String sendVisitorDetailsEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorDetailsEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason(),
					emailRequest.getLink());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendStatusUpdation")
	public String sendVisitorStatusEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorStatusEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getStatus());
			return "Email sent successfully";
		} catch (MessagingException e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendvisitremainder")
	public String sendVisitorRemainderEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorRemainderEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendvisitfarewell")
	public String sendVisitorFarewellEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorFarewellEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendvisitoffice")
	public String sendVisitorOfficeEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorOfficeEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendvisitremainderothers")
	public String sendVisitorRemainderOthersEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorRemainderOthersEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason(),
					emailRequest.getType());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendvisitfarewellothers")
	public String sendVisitorFarewellOthersEmail(@RequestBody EmailRequest emailRequest) {
		try {
			emailService.sendVisitorFarewellOthersEmail(emailRequest.getEmployeeEmail(), emailRequest.getVisitorName(),
					emailRequest.getVisitorEmail(), emailRequest.getVisitorPhone(), emailRequest.getReason(),
					emailRequest.getType());
			return "Email sent successfully";
		} catch (Exception e) {
			return "Failed to send email";
		}
	}

	@PostMapping("/sendemailmorethansix")
	public void sendEmailMoreThanSix(@RequestBody EmailRequest emailRequest) {
		emailService.sendEmailMoreThanSix(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
	}

}
