package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.SyllabusListDetailDto;
import com.company.Personalmgmt.pdf.SyllabusListPdfGenerator;
import com.company.Personalmgmt.service.SyllabusListService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class SyllabusListPdfController {

	@Autowired
	SyllabusListService syllabusListService;

	@RequestMapping(value = "/javaSyllabusListpdf")
	@ResponseBody
	public void syllabusListPdf(HttpServletResponse response) throws DocumentException, IOException {

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();

		Document document = new Document();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);

		SyllabusListPdfGenerator syllabusListPdfGenerator = new SyllabusListPdfGenerator();

		List<SyllabusListDetailDto> syllabusListDetailDtos = syllabusListService.getSyllabusList();

		document.open();

		document.addTitle("Java Syllabus");

		document.addSubject("Java");

		document.addAuthor("Jayaram");

		Paragraph ob = syllabusListPdfGenerator.addTitlePage();

		PdfPTable content = syllabusListPdfGenerator.headertwo(syllabusListDetailDtos);

		PdfPTable end = syllabusListPdfGenerator.cmpsigndtls();
		
		PdfPTable finals = syllabusListPdfGenerator.juriticationdetails();

		document.add(ob);
		document.add(content);
		document.add(end);
		document.add(finals);

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
