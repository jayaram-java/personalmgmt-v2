package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.company.Personalmgmt.service.PersonalInfoService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/pdf")
public class PDFController {
	
	@Autowired
	PersonalInfoService personalInfoService;
	
	@RequestMapping(value = "/personalInfo")
	public void personalInfo(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();

		Document document = new Document();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);

		PersonalInfoPDFGenerator personalInfoPDFGenerator = new PersonalInfoPDFGenerator();

		List<PersonalInfoDto> personalInfo = personalInfoService.fetchAllPersonalInfo();

		document.open();

		document.addTitle("Personal Info");

		document.addSubject("Java");

		document.addAuthor("Jayaram");

		Paragraph ob = personalInfoPDFGenerator.addTitlePage();

		PdfPTable content = personalInfoPDFGenerator.headertwo(personalInfo.get(0));

		//PdfPTable end = syllabusListPdfGenerator.cmpsigndtls();
		
		//PdfPTable finals = syllabusListPdfGenerator.juriticationdetails();

		document.add(ob);
		document.add(content);
	//	document.add(end);
	//	document.add(finals);

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
	
	@RequestMapping(value = "/agenda")
	public void agendainfo(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();

		Document document = new Document();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);

		AgendaPDFGenerator agendaPDFGenerator = new AgendaPDFGenerator();

		document.open();

		document.addTitle("Daily Agenda");

		document.addSubject("Java");

		document.addAuthor("Jayaram");

		Paragraph ob = agendaPDFGenerator.addTitlePage();

		PdfPTable content = agendaPDFGenerator.headertwo();
		
		PdfPTable content2 = agendaPDFGenerator.headerthree();
		
		PdfPTable content3 = agendaPDFGenerator.headerFour();
		
		PdfPTable content4 = agendaPDFGenerator.headerFive();

		document.add(ob);
		document.add(content);
		document.add(content4);
		document.add(content2);
		document.add(content3);
		
	
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
