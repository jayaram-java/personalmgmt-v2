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

import com.company.Personalmgmt.model.ApplicationFeature;
import com.company.Personalmgmt.model.ClientCategoryMaster;
import com.company.Personalmgmt.pdf.ClientApplicationProposalPDFGenerator;
import com.company.Personalmgmt.repository.ApplicationFeatureRepository;
import com.company.Personalmgmt.repository.ClientCategoryMasterRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ClientApplicationProposalController {

	@Autowired
	ApplicationFeatureRepository applicationFeatureRepository;

	@Autowired
	ClientCategoryMasterRepository clientCategoryMasterRepository;

	@RequestMapping(value = "/clientproposalpdf")
	@ResponseBody
	public void clientProposalPdf(HttpServletResponse response, String name) throws DocumentException, IOException {

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf");

		HttpHeaders headers = new HttpHeaders();
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);

		ClientCategoryMaster clientCategoryMaster = clientCategoryMasterRepository.findByName(name);
		List<ApplicationFeature> applicationFeature = applicationFeatureRepository
				.findByClientCategoryMaster(clientCategoryMaster);

		ClientApplicationProposalPDFGenerator clientpdf = new ClientApplicationProposalPDFGenerator();

		document.open();
		document.addTitle("project proposal - vendor");

		Paragraph ob = clientpdf.addTitlePage();
		
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
			OutputStream os = response.getOutputStream();
			out.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {

		}

	}

}
