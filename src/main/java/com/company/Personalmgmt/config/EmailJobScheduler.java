package com.company.Personalmgmt.config;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.company.Personalmgmt.pdf.ExpensePDFGeneratorV2;
import com.company.Personalmgmt.pdf.ExpensePdfGenerator;
import com.company.Personalmgmt.repository.SecondaryUserDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.ExpenseService;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;



@Component
public class EmailJobScheduler extends QuartzJobBean {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailJobScheduler.class);

	@Autowired
	private JavaMailSender mailSender;

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

		sendMailV2("", recipientEmail, subject, body);

	}
	
	private void sendMailV2(String fromEmail, String toEmail, String subject, String body){
		
		System.out.println("#################### v2 Email");
		
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


					ExpensePDFGeneratorV2 pdfUtils = new ExpensePDFGeneratorV2();
					HttpHeaders headers = new HttpHeaders();
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					
					List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonthForEmailScheduling(1);
					List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonthForEmailScheduling(1);
					List<JSONObject> jsonList2 = new ArrayList<>();
					
					int n = 1;
					double totalAmtCategory = 0.0;
					for (ExpenseDto category : expensedtoforCategory) {

						JSONObject ob = new JSONObject();
						ob.put("S.No.", n);
						ob.put("Category", category.getCategoryName());
						ob.put("Amount", category.getAmount());
						totalAmtCategory = totalAmtCategory + category.getAmount();
						n++;
						jsonList2.add(ob);
					}
					
			        JSONObject ob1 = new JSONObject();
					ob1.put("S.No.", "");
					ob1.put("Category", "Total Expenses");
					ob1.put("Amount", totalAmtCategory);
					jsonList2.add(ob1);
					
					List<JSONObject> jsonList = new ArrayList<>();
					double totalAmt = 0.0;
					String month = "";
					
					int n1 = 1;
					for (ExpenseDto expenseDto : expensedtos) {
						JSONObject ob = new JSONObject();
						ob.put("S.No.", n1);
						ob.put("Name", expenseDto.getName());
						ob.put("Description", expenseDto.getDescription());
						ob.put("Date", convertDateFormat(expenseDto.getDate()));
						ob.put("Amount", expenseDto.getAmount());
						totalAmt = totalAmt + expenseDto.getAmount();
						month = expenseDto.getMonth();
						n1++;
						jsonList.add(ob);
					}
					
					JSONObject ob = new JSONObject();
					ob.put("S.No.", "");
					ob.put("Name", "");
					ob.put("Description", "");
					ob.put("Date", "Total");
					ob.put("Amount", totalAmt);
					jsonList.add(ob);
					
					float headerFontSize = 12f;
					String fontName = "Helvetica";
					PdfFont headerFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, headerFontSize);
					
					float dataFontSize = 10f;
					PdfFont dataFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, dataFontSize);
					PdfDocument pdf = new PdfDocument(new PdfWriter(out));
					Document document = new Document(pdf);
					
					Div divLine = pdfUtils.createHeader(dataFont,month);
					Div divDate = pdfUtils.createTableDiv(jsonList, headerFont,dataFont);
					Div divDate2 = pdfUtils.categoryTableDiv(jsonList2, headerFont,dataFont);
					
					document.add(divLine);
					document.add(divDate);
					document.add(divDate2);
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
	
	
	public static String convertDateFormat(String input) {

		String output = "";

		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");

		try {
			Date date = inputFormat.parse(input);
			output = outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;
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
					/*Document document = new Document();
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					PdfWriter.getInstance(document, out);
					document.open();*/
					
					List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonthForEmailScheduling(1);
					List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonthForEmailScheduling(1);

					//Paragraph paragraph1 = expensePdfGenerator.addSubTitlePage("Expense Details Based on Day");
					//PdfPTable header = expensePdfGenerator.headertwo(expensedtos);
					//Paragraph paragraph = expensePdfGenerator.addSubTitlePage("Expense Details Based on Category");
					//PdfPTable expenseDetailCategory = expensePdfGenerator.expenseDetailsBasedOnCategory(expensedtoforCategory);
					
					/*document.add(paragraph1);
					document.add(header);
					document.add(paragraph);
					document.add(expenseDetailCategory);
					document.close();*/

					try {
						//headers.setContentLength(out.size());

					} catch (Exception e) {
						
						log.info("Exception " + e);

					}
					//byte a[] = out.toByteArray();
					
					//helper.addAttachment(subject.concat(".pdf"), new ByteArrayResource(a),"application/pdf");
				}

			};


			mailSender.send(preparator);
		} catch (Exception ex) {
			log.error("Failed to send email to {}", toEmail);
			ex.printStackTrace();
		}
	}

}
