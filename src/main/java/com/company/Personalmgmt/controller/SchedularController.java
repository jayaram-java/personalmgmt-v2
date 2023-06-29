package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.model.SecondaryUserDetails;
import com.company.Personalmgmt.pdf.CheckListPdfGenerator;
import com.company.Personalmgmt.repository.SecondaryUserDetailsRepository;
import com.company.Personalmgmt.service.CheckListService;
import com.company.Personalmgmt.service.EmailService;
import com.company.Personalmgmt.service.GeneralService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class SchedularController {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	SecondaryUserDetailsRepository secondaryUserDetailsRepository;
	
	@Autowired
	CheckListService checkListService;
	
	@Autowired
	GeneralService generalService;
	
	public static String username = "";
	
	
	@Scheduled(cron = "0 0/5 20-23 6-15 * ?")
	public void triggerExpense(){
		
		String result = generalService.enableExpenseDetail();
		
	}
	
	
	//@SuppressWarnings("deprecation")
	//@Transactional
	@Scheduled(cron = "0 49 7 ? * fri")
	public void sendEmail(){
		
		emailService.sendEmailForScheduling();
		
	}
	
	//@Scheduled(cron = "0 20 19 ? * SUN")
	public void cronTime(){
		
		System.out.println("cron expression - 2022");
		
		//emailService.sendEmailToAll();
	}
	
	
	/*second, minute, hour, day, month, weekday*/

	//@Scheduled(cron = "* 20 21 * * MON-FRI")
	public void ScheduledWeekEnd() {
		
		
		final String sendto = "jayaramp51096@gmail.com";

		final String subject = "Checklist";

		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress("jayaramp51096@gmail.com");

		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
				mimeMessage.setFrom(new InternetAddress("jeyaramp51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setText(
						"<html><body><h3>Hi " + username
								+ ",</h3>&nbsp;&nbsp;&nbsp;&nbsp;I have attached checklist.This is cron checking purpose.<br>ThankYou.<br><br>Warm regards,<br><b>P.JAYARAM B.E(ECE).,</b><br><b>Software Developer,</b><br><b>+91 88386 19054</b><br>jayaramp51096@gmail.com<br><br>This email is sent by ja(v)yaram from SpringFramework.*<br><br>All rights reserved.</body></html>",
						true);

				CheckListPdfGenerator checkListPdfGenerator = new CheckListPdfGenerator();

				HttpHeaders headers = new HttpHeaders();

				Document document = new Document();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				PdfWriter.getInstance(document, out);

				List<CheckListDetailDto> checkListDetailDtos = checkListService.getAllCheckListDetails();

				document.open();

				document.addTitle("Check Title");

				document.addSubject("Take care");

				document.addAuthor("Ram");

				PdfPTable header = checkListPdfGenerator.headertwo(checkListDetailDtos);

				Paragraph ob = checkListPdfGenerator.addTitlePage();

				document.add(ob);
				document.add(header);

				document.close();

				try {
					headers.setContentLength(out.size());

				} catch (Exception e) {

				}
				byte a[] = out.toByteArray();

				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};
		
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {

			System.err.println(ex.getMessage());
		}


	}

}
