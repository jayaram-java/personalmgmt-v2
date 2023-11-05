/**
 * 
 */
package com.company.Personalmgmt.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.pdf.ExpensePDFGeneratorV2;
import com.company.Personalmgmt.service.ExpenseService;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Div;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
* This class is used for 
*
* @author Jayaram
*/

@RestController
@RequestMapping("/api/pdf/v2")
public class PDFV2Controller {
	
	@Autowired
	ExpenseService expenseService;
	
	@RequestMapping(value = "/expensepdf")
	@ResponseBody
	public void expensePDFV2(HttpServletResponse response) throws DocumentException, IOException {

		List<ExpenseDto> expensedtos = expenseService.getExpenseDetailBasedpreviousmonth();

		List<JSONObject> jsonList = new ArrayList<>();
		
		for (ExpenseDto expenseDto : expensedtos) {

			JSONObject ob = new JSONObject();

			ob.put("Id", expenseDto.getId());
			ob.put("Name", expenseDto.getName());
			ob.put("Description", expenseDto.getDescription());
			ob.put("Date", expenseDto.getDate());
			ob.put("Amount", expenseDto.getAmount());

			jsonList.add(ob);
		}

		ExpensePDFGeneratorV2 pdfUtils = new ExpensePDFGeneratorV2();
		
		response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
       // response.setHeader("Content-Disposition", "attachment; filename=" + "expense.pdf");
		response.setContentType("application/pdf");
		
		HttpHeaders headers = new HttpHeaders();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter writer = ExpensePDFGeneratorV2.createPdfWriter("Expense.pdf"); // Specify the file name here
		float headerFontSize = 12f;
		String fontName = "Helvetica";
		PdfFont headerFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, headerFontSize);
		
		float dataFontSize = 10f;
		PdfFont dataFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, dataFontSize);
		
	//	PdfDocument pdf = new PdfDocument(writer);
		
		PdfDocument pdf = new PdfDocument(new PdfWriter(out));
		
		Document doc = new Document(pdf);

		Div divDate = pdfUtils.createTableDiv(jsonList, headerFont,dataFont);

		doc.add(divDate);
		doc.close();

	//	String base64String = pdfUtils.convertPdfToBase64("Expense.pdf");
		
	//	byte[] pdfData = Base64.getDecoder().decode(base64String);
		
		//System.out.println("@@@@@@@@@@@@ "+base64String);
		
		try {
			headers.setContentLength(out.size());
			OutputStream os = response.getOutputStream();
			out.writeTo(os);
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
