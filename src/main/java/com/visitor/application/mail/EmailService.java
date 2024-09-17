package com.visitor.application.mail;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.visitor.application.model.Visit;
import com.visitor.application.service.VisitService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private VisitService visitService;

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmail(String to, String subject, String text) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		emailSender.send(message);
	}

	public void sendVisitorDetailsEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason, String link) throws MessagingException {
		String subject = "New Visitor Request";
		String text = String.format(
				"<html><body>" + "<p>\n\nIf you want to approve the Request\n\nPlease Click the Visit Link\n\n</p>"
						+ "<p>Visitor Details:</p>" + "<p>Name: %s<br>" + "Email: %s<br>" + "Phone: %s<br>"
						+ "Reason: %s<br>" + "Link: <a href=\"%s\">Visit Link</a></p>" + "</body></html>",
				visitorName, visitorEmail, visitorPhone, reason, link);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendVisitorStatusEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String status) throws MessagingException {
		String subject = "Visitor Request Status Update";
		String messageBody;

		switch (status.toLowerCase()) {
		case "approved":
			messageBody = String.format(
					"<html><body>" + "<p>I approved the Request of visit for this Visitor:</p>" + "<p>Name: %s<br>"
							+ "Email: %s<br>" + "Phone: %s<br>" + "</p>" + "</body></html>",
					visitorName, visitorEmail, visitorPhone);
			break;
		case "rejected":
			messageBody = String.format(
					"<html><body>" + "<p>I rejected the Request of visit for this Visitor:</p>" + "<p>Name: %s<br>"
							+ "Email: %s<br>" + "Phone: %s<br>" + "</p>" + "</body></html>",
					visitorName, visitorEmail, visitorPhone);
			break;
		case "waiting":
			messageBody = String.format(
					"<html><body>" + "<p>I am busy with some Important work, So the visitor has to wait for until I Approve the Request</p>"
							+ "<p>Name: %s<br>" + "Email: %s<br>" + "Phone: %s<br>" + "</p>" + "</body></html>",
					visitorName, visitorEmail, visitorPhone);
			break;
		default:
			throw new IllegalArgumentException("Invalid status: " + status);
		}

		sendEmail(employeeEmail, subject, messageBody);
	}

	public void sendVisitorRemainderEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason) throws MessagingException {
		String subject = "Visitor Arrival Remainder";
		String text = String.format("<html><body>" + "<p>\n\nVisitor was Checked In and,</p>"
				+ "<p>They Entered Into the IT Park, </p>" + "<p>\n\nThey will meet you Soon\n\n</p>"
				+ "<p>\n\nThankyou\n\n</p>" + "<p>Visitor Details:</p>" + "<p>Name: %s<br>" + "Email: %s<br>"
				+ "Phone: %s<br>" + "Reason: %s<br>" + "</body></html>", visitorName, visitorEmail, visitorPhone,
				reason);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendVisitorFarewellEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason) throws MessagingException {
		String subject = "Visitor Checked Out Remainder";
		String text = String.format("<html><body>" + "<p>\n\nVisitor was Checked out and,</p>"
				+ "<p>\n\nThey left the It Park Just Now.</p>" + "<p>\n\nThankyou\n\n</p>" + "<p>Visitor Details:</p>"
				+ "<p>Name: %s<br>" + "Email: %s<br>" + "Phone: %s<br>" + "Reason: %s<br>" + "</body></html>",
				visitorName, visitorEmail, visitorPhone, reason);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendVisitorOfficeEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason) throws MessagingException {
		String subject = "Visitor At Your Office Entrance : Remainder";
		String text = String.format("<html><body>" + "<p>\n\nVisitor is Waiting at your Office Entrance</p>"
				+ "<p>\n\nThankyou\n\n</p>" + "<p>Visitor Details:</p>" + "<p>Name: %s<br>" + "Email: %s<br>"
				+ "Phone: %s<br>" + "Reason: %s<br>" + "</body></html>", visitorName, visitorEmail, visitorPhone,
				reason);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendVisitorRemainderOthersEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason, String type) throws MessagingException {
		String subject = "Visitor Arrival Remainder";
		String text = String.format(
				"<html><body>" + "<p>\n\nVisitor was Checked In and,</p>" + "<p>They Entered Into the IT Park, </p>"
						+ "<p>\n\nThey will meet you Soon\n\n</p>" + "<p>\n\nThankyou\n\n</p>"
						+ "<p>Visitor Details:</p>" + "<p>Name: %s<br>" + "Email: %s<br>" + "Phone: %s<br>"
						+ "Reason: %s<br>" + "Type: %s<br>" + "</body></html>",
				visitorName, visitorEmail, visitorPhone, reason, type);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendVisitorFarewellOthersEmail(String employeeEmail, String visitorName, String visitorEmail,
			String visitorPhone, String reason, String type) throws MessagingException {
		String subject = "Visitor Checked Out Remainder";
		String text = String.format(
				"<html><body>" + "<p>\n\nVisitor was Checked out and,</p>"
						+ "<p>\n\nThey left the It Park Just Now.</p>" + "<p>\n\nThankyou\n\n</p>"
						+ "<p>Visitor Details:</p>" + "<p>Name: %s<br>" + "Email: %s<br>" + "Phone: %s<br>"
						+ "Reason: %s<br>" + "Type: %s<br>" + "</body></html>",
				visitorName, visitorEmail, visitorPhone, reason, type);
		sendEmail(employeeEmail, subject, text);
	}

	public void sendEmailMoreThanSix(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
	}
	
	@Scheduled(cron = "0 00 18 * * ?") // This schedules the task to run every day at 6 PM
    public void sendEmailAtSixPM() throws MessagingException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        List<Visit> visits = visitService.getAllVisits();
        
        StringBuilder emailBody = new StringBuilder();
        for (Visit visit : visits) {
            Timestamp visitTimestamp = visit.getCheckInDateTime(); 
            
            if (visitTimestamp == null) {
                continue;
            }
            
            if (visitTimestamp.toLocalDateTime().toLocalDate().isEqual(currentTimestamp.toLocalDateTime().toLocalDate()) &&
                "checked-in".equalsIgnoreCase(visit.getCheckIn()) &&
                visit.getCheckOut() == null) { // Check-out status is null
                
                emailBody.append(visit.toString().replace("\r\n", "<br>")).append("<br><br><br><br>");
            }
        }

        if (emailBody.length() > 0) {
        	emailBody.append("</body></html>");
            sendEmail("nandhakumaran.hariharan@relevantz.com", "Daily Visit Report of Not Checkout Visits", emailBody.toString());
        }
    }


}
