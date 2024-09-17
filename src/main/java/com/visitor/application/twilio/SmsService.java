package com.visitor.application.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.phone.number}")
	private String twilioNumber;

	public void sendSms(String to, String visitorName, String appointmentDateTime, String employeeName) {
		String formattedTo = formatPhoneNumber(to);
		PhoneNumber toPhoneNumber = new PhoneNumber(formattedTo);

		String body = String.format("\n\nHi %s, \n\n You have an appointment with %s on %s at Nandha IT Park. \n\nThank you.",
				visitorName, employeeName, appointmentDateTime);

		try {
			Message.creator(toPhoneNumber, new PhoneNumber(twilioNumber), body).create();
		} catch (Exception e) {
			throw new RuntimeException("Failed to send SMS", e);
		}
	}

	private String formatPhoneNumber(String phoneNumber) {

		if (phoneNumber.startsWith("+")) {
			return phoneNumber;
		}
		return "+91" + phoneNumber;
	}
}
