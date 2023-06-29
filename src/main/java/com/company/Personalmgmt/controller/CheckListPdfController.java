package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.pdf.CheckListPdfGenerator;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.CheckListService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class CheckListPdfController {
	
	
	@Autowired
	CheckListService checkListService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession httpsession;
	
	// private static final byte[] userPassword = "Ram".getBytes();
	 private static final byte[] ownerPassword = "javaRam".getBytes();
	
	@RequestMapping(value = "/checklistpdf")
	@ResponseBody
	public void checkListPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		long userId = (Long)httpsession.getAttribute("userId");
		
		Optional<User> user = userRepository.findById(userId);
		
		byte[] userPassword = user.get().getUsername().getBytes();

		CheckListPdfGenerator checkListPdfGenerator = new CheckListPdfGenerator();

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
		
		pdfWriter.setEncryption(userPassword, 
			    ownerPassword,
	                    PdfWriter.ALLOW_PRINTING, 
	                    PdfWriter.ENCRYPTION_AES_256);


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
			OutputStream os = response.getOutputStream();
			out.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {

		}

	}

}
