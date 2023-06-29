package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.pdf.ExpensePdfGenerator;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.ExpenseService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ExpensePdfController {

	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession httpsession;
	
	
	 private static final byte[] ownerPassword = "javaRam".getBytes();

	
	@RequestMapping(value = "/expensepdf")
	@ResponseBody
	public void go(HttpServletResponse response) throws DocumentException, IOException {
	
		long userId = (Long)httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		byte[] userPassword = user.get().getUsername().getBytes();
		ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();

		response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
       // response.setHeader("Content-Disposition", "attachment; filename=" + "expense.pdf");
		response.setContentType("application/pdf");
		
		HttpHeaders headers = new HttpHeaders();
		Document document = new Document();
		
		//  Document document = new Document(new Rectangle(612, 242));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter pdfWriter =  PdfWriter.getInstance(document, out);
		pdfWriter.setEncryption(userPassword,   ownerPassword,  PdfWriter.ALLOW_PRINTING,  PdfWriter.ENCRYPTION_AES_256);
		document.open();

		List<ExpenseDto> expensedtos = 	expenseService.getExpenseDetailBasedpreviousmonth();
		List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonth();
		
		Paragraph paragraph1 = expensePdfGenerator.addSubTitlePage("Expense Details Based on Day");
		PdfPTable expenseDetail = expensePdfGenerator.headertwo(expensedtos);
		Paragraph paragraph = expensePdfGenerator.addSubTitlePage("Expense Details Based on Category");
		PdfPTable expenseDetailCategory = expensePdfGenerator.expenseDetailsBasedOnCategory(expensedtoforCategory);
	  

		document.add(paragraph1);
		document.add(expenseDetail);
		document.add(paragraph);
		document.add(expenseDetailCategory);
		document.close();

		try {
			headers.setContentLength(out.size());
			OutputStream os = response.getOutputStream();
			out.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {

		}
	}
	
	@RequestMapping(value = "/expensepdfdownload")
	@ResponseBody
	public void expensePDFController(HttpServletResponse response) throws DocumentException, IOException {

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		byte[] userPassword = user.get().getUsername().getBytes();
		ExpensePdfGenerator expensePdfGenerator = new ExpensePdfGenerator();
		
		LocalDate now = LocalDate.now(); 
		LocalDate earlier = now.minusMonths(1);
		
		String lastMonth = earlier.getMonth().name().toLowerCase();

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		 response.setHeader("Content-Disposition", "attachment; filename=" + lastMonth+"Expense.pdf");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();
		Document document = new Document();
		
		
		// Document document = new Document(new Rectangle(612, 242));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
		pdfWriter.setEncryption(userPassword, ownerPassword, PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256);
		document.open();

		List<ExpenseDto> expensedtos = expenseService.getExpenseDetailBasedpreviousmonth();
		List<ExpenseDto> expensedtoforCategory = expenseService.getExpenseDetailBasedCategoryPreviousMonth();

		Paragraph paragraph1 = expensePdfGenerator.addSubTitlePage("Expense Details Based on Day");
		PdfPTable expenseDetail = expensePdfGenerator.headertwo(expensedtos);
		Paragraph paragraph = expensePdfGenerator.addSubTitlePage("Expense Details Based on Category");
		PdfPTable expenseDetailCategory = expensePdfGenerator.expenseDetailsBasedOnCategory(expensedtoforCategory);

		document.add(paragraph1);
		document.add(expenseDetail);
		document.add(paragraph);
		document.add(expenseDetailCategory);
		document.close();

		try {
			headers.setContentLength(out.size());
			OutputStream os = response.getOutputStream();
			out.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {

		}
	}

}
