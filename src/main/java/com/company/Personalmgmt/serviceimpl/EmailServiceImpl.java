package com.company.Personalmgmt.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.dto.SecondaryUserDetailsDto;
import com.company.Personalmgmt.model.ApplicationFeature;
import com.company.Personalmgmt.model.ClientCategoryMaster;
import com.company.Personalmgmt.model.SecondaryUserDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.pdf.CheckListPdfGenerator;
import com.company.Personalmgmt.pdf.ClientApplicationProposalPDFGenerator;
import com.company.Personalmgmt.pdf.ExpensePdfGenerator;
import com.company.Personalmgmt.repository.ApplicationFeatureRepository;
import com.company.Personalmgmt.repository.ClientCategoryMasterRepository;
import com.company.Personalmgmt.repository.SecondaryUserDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.CheckListService;
import com.company.Personalmgmt.service.EmailService;
import com.company.Personalmgmt.service.ExpenseService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



@Service
public class EmailServiceImpl implements EmailService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	SecondaryUserDetailsRepository secondaryUserDetailsRepository;
	
	@Autowired
	CheckListService checkListService;
	
	@Autowired
	HttpSession httpsession;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ApplicationFeatureRepository applicationFeatureRepository;

	@Autowired
	ClientCategoryMasterRepository clientCategoryMasterRepository;
	
	
	public static String username = "";
	
	
	public List<SecondaryUserDetailsDto> getAllEmailAddress() {

		log.info("API name = *getAllEmailAddress");

		List<SecondaryUserDetailsDto> secondaryUserDetailsDtos = new ArrayList<SecondaryUserDetailsDto>();

		List<SecondaryUserDetails> secondaryUserDetails = secondaryUserDetailsRepository.findAll();

		try {

			for (SecondaryUserDetails secondaryUserDetail : secondaryUserDetails) {

				SecondaryUserDetailsDto secondaryUserDetailsDto = new SecondaryUserDetailsDto();

				BeanUtils.copyProperties(secondaryUserDetail, secondaryUserDetailsDto);

				secondaryUserDetailsDtos.add(secondaryUserDetailsDto);

			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return secondaryUserDetailsDtos;
	}

	public boolean sendEmailForExpense() {
		
		log.info("API name = *sendEmailForExpense");
		
		LocalDate now = LocalDate.now(); 
		LocalDate earlier = now.minusMonths(1);
		
		String month = String.valueOf(earlier.getMonth());
		
		long userId = (Long)httpsession.getAttribute("userId");
		
		Optional<User> user = userRepository.findById(userId);

		final String sendto = user.get().getEmail();

		final String subject = "EXPENSE - ".concat(month).concat(" REPORT");
		
		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(user.get().getEmail());
		
		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
				mimeMessage.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setText(
						" <html>    <body>  <h3>Hi " + username+ ",</h3>  Thank you for using this application. Your expense details are attached below.</br> </br>  </br>  </br> "
								+ " <br><b>Warm Regards,</b><br><b>From Team.</b> </body></html>",
						true);

				ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();

				HttpHeaders headers = new HttpHeaders();

				Document document = new Document();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				PdfWriter.getInstance(document, out);

				document.open();
				
				List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonth();
				List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonth();

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
				
				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			
			log.info("Exception " + ex);


			System.err.println(ex.getMessage());
		}

		return true;
	}

	
	public boolean sendEmailForCheckList() {
		
		log.info("API name = *sendEmailForCheckList" );
		
		long userId = (Long)httpsession.getAttribute("userId");
		
		Optional<User> user = userRepository.findById(userId);

		final String sendto = user.get().getEmail();

		final String subject = "Checklist";

		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(user.get().getEmail());
		
		List<CheckListDetailDto> checkListDetailDtos = checkListService.getAllCheckListDetails();

		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
				mimeMessage.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setText(
						"<html><body><h3>Hi " + username
								+ ",</h3>&nbsp;&nbsp;&nbsp;&nbsp;Checklist attached for " + checkListDetailDtos.get(0).getParent() + ". Please check.<br><br>Warm regards,<br><b>P.JAYARAM B.E(ECE).,</b><br><b>Software Developer,</b><br><b>+91 88386 19054</b><br>jayaramp51096@gmail.com<br><br>This email is sent by ja(v)yaram from SpringFramework.*<br><br>All rights reserved.</body></html>",
						true);

				CheckListPdfGenerator checkListPdfGenerator = new CheckListPdfGenerator();

				HttpHeaders headers = new HttpHeaders();

				Document document = new Document();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				PdfWriter.getInstance(document, out);

			

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
					log.info("Exception " + e);
				}
				byte a[] = out.toByteArray();

				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};
		
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			log.info("MailException " + ex);

			System.err.println(ex.getMessage());
		}


		return true;
	}


	@Override
	public void sendEmailToAll() {
		
		log.info("API name = *sendEmailToAll" );

		final String sendto = "jayaramp51096@gmail.com";

		final String subject = "Expense";
		
		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(sendto);
		
		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
				mimeMessage.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setText(
						" <html>  <style> table, th, td {   border: 1px solid black;    border-collapse: collapse;  background-color: Gray; } th, td {   padding: 5px; } </style>  <body>  <h3>Hi " + username+ ",</h3>  I have attached the expense details.</br> </br>  "
								+ "+<table style="+"width:50%"+">   <tr>    "
								+ " <th>S.no</th>    "
								+ " <th>Expense name</th>     "
								+ "<th>cost</th>   </tr>  "
								+ " <tr>     "
								+ "<td>1</td>     "
								+ "<td>online class</td>    "
								+ " <td>5000</td>   </tr> "
								+ " </table> <br>ThankYou.</br><br>Warm regards,<br><b>P.JAYARAM B.E(ECE).,</b><br><b>Software Developer,</b><br><b>+91 88386 19054</b><br>jayaramp51096@gmail.com<br><br>This email is sent by ja(v)yaram from SpringFramework.*<br><br>All rights reserved. </body></html>",
						true);

				ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();

				HttpHeaders headers = new HttpHeaders();

				Document document = new Document();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				PdfWriter.getInstance(document, out);

				document.open();
				
				List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonth();
				List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonth();

			//	List<ExpenseDto> expensedtos = expenseService.getAllExpenseDetails();

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
				
				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			
			log.info("MailException " + ex);

			System.err.println(ex.getMessage());
		}


		
	}


	@Override
	public boolean sendEmailtoClients(String toAddress) {
		
		log.info("API name = *sendEmailtoClients" );
		
		final String subject = "Project Proposal - Vendor ";
		
		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(toAddress);
		
		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
				mimeMessage.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setText(
						" <html>    <body>  <h3>Hi " + username+ ",</h3>  I've attached the project proposal for vendor . This application will enhance your business.<br> <br> "
								+ " <br>Thanks,<br><b>From Team.</b> </body></html>",
						true);
				
				ClientApplicationProposalPDFGenerator clientpdf = new ClientApplicationProposalPDFGenerator();
				
				HttpHeaders headers = new HttpHeaders();
				Document document = new Document();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PdfWriter.getInstance(document, out);
				
				document.open();
				document.addTitle("project proposal - vendor");
				
				ClientCategoryMaster clientCategoryMaster = clientCategoryMasterRepository.findByName("Vendor");
				List<ApplicationFeature> applicationFeature = applicationFeatureRepository
						.findByClientCategoryMaster(clientCategoryMaster);

				PdfPTable title = clientpdf.getTitle("Project Proposal - Vendor");
				PdfPTable content = clientpdf.headertwo(applicationFeature);
				PdfPTable end = clientpdf.cmpsigndtls();
				PdfPTable finals = clientpdf.juriticationdetails();

				document.add(title);
				document.add(content);
				document.add(end);
				document.add(finals);

				document.close();
				
				try {
					headers.setContentLength(out.size());

				} catch (Exception e) {
					
					log.info("Exception " + e);

					
					e.printStackTrace();
				}

				byte a[] = out.toByteArray();
				
				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {

			log.info("MailException " + ex);

			
			System.err.println(ex.getMessage());
		}
		return true;
	}

	@Override
	public boolean sendEmailForScheduling() {
	log.info("API name = *sendEmailForScheduling");
		
		LocalDate now = LocalDate.now(); 
		LocalDate earlier = now.minusMonths(1);
		
		String month = String.valueOf(earlier.getMonth());
		
		long userId = 1;
		
		Optional<User> user = userRepository.findById(userId);

		final String sendto = user.get().getEmail();

		final String subject = "EXPENSE - ".concat(month).concat(" REPORT");
		
		SecondaryUserDetails secondaryUserDetails = secondaryUserDetailsRepository.findByEmailAddress(user.get().getEmail());
		
		username = secondaryUserDetails.getName();

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(sendto));
				mimeMessage.setFrom(new InternetAddress("jayaramdeveloper51096@gmail.com"));
				mimeMessage.setSubject(subject);

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setText(
						" <html>    <body>  <h3>Hi " + username+ ",</h3>  Thank you for using this application. Your expense details are attached below.</br> </br>  </br>  </br> "
								+ " <br><b>Warm Regards,</b><br><b>From Team.</b> </body></html>",
						true);

				ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();

				HttpHeaders headers = new HttpHeaders();

				Document document = new Document();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				PdfWriter.getInstance(document, out);

				document.open();
				
				List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonth();
				List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonth();

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
				
				helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a));
			}

		};

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			
			log.info("Exception " + ex);


			System.err.println(ex.getMessage());
		}

		return true;
	}

	@Override
	public boolean sendEmailChecking() {
		
		
		return true;
	}

}
