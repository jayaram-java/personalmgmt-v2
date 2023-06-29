package com.company.Personalmgmt.config;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.model.SecondaryUserDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.pdf.ExpensePdfGenerator;
import com.company.Personalmgmt.repository.SecondaryUserDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.ExpenseService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class EmailJobScheduler extends QuartzJobBean {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailJobScheduler.class);

	@Autowired
	private JavaMailSender mailSender;

	/*
	 * @Autowired private MailProperties mailProperties;
	 */
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	SecondaryUserDetailsRepository secondaryUserDetailsRepository;
	
	public static String username = "";
	

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		log.info("Executing Job with key {}", context.getJobDetail().getKey());

		JobDataMap jobDataMap = context.getMergedJobDataMap();

		String subject = jobDataMap.getString("subject");
		String body = jobDataMap.getString("body");
		String recipientEmail = jobDataMap.getString("email");

		sendMail("", recipientEmail, subject, body);

	}

	private void sendMail(String fromEmail, String toEmail, String subject, String body) {
		
		System.out.println(" ****** "+toEmail);
		
		try {
			LocalDate now = LocalDate.now(); 
			LocalDate earlier = now.minusMonths(1);
			
			String month = String.valueOf(earlier.getMonth());
			
			long userId = 1;
			
			Optional<User> user = userRepository.findById(userId);

			final String sendto = user.get().getEmail();

			final String subject1 = "EXPENSE - ".concat(month).concat(" REPORT");
			
			SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(user.get().getEmail());
			
			username = secondaryUserDetails.getName();

			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					
					String body = "Dear "+username+","+ '\n'+"  Thank you for using this application. Your expense details are attached below."+'\n'+'\n'+"Regards,"+'\n'+"Jayaram";
					
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
					
					helper.setTo(new InternetAddress(sendto));
					helper.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
					helper.setSubject(subject1);
					helper.setText(body);


					ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();

					HttpHeaders headers = new HttpHeaders();
					Document document = new Document();
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					PdfWriter.getInstance(document, out);
					document.open();
					
					List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonthForEmailScheduling(1);
					List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonthForEmailScheduling(1);

					Paragraph paragraph1 = expensePdfGenerator.addSubTitlePage("Expense Details Based on Day");
					PdfPTable header = expensePdfGenerator.headertwo(expensedtos);
					Paragraph paragraph = expensePdfGenerator.addSubTitlePage("Expense Details Based on Category");
					PdfPTable expenseDetailCategory = expensePdfGenerator.expenseDetailsBasedOnCategory(expensedtoforCategory);
					
					document.add(paragraph1);
					document.add(header);
					document.add(paragraph);
					document.add(expenseDetailCategory);
					document.close();

					try {
						headers.setContentLength(out.size());

					} catch (Exception e) {
						
						log.info("Exception " + e);

					}
					byte a[] = out.toByteArray();
					
					helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a),"application/pdf");
				}

			};


			mailSender.send(preparator);
		} catch (Exception ex) {
			log.error("Failed to send email to {}", toEmail);
			ex.printStackTrace();
		}
	}

}
