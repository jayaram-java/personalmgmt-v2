/**
 * 
 */
package com.company.Personalmgmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.pdf.DepositListPDFGenerator;
import com.company.Personalmgmt.pdf.ExpensePDFGeneratorV2;
import com.company.Personalmgmt.service.InvestmentService;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.DocumentException;

/**
* This class is used for 
*
* @author Jayaram
*/

@RestController
@RequestMapping("/pdf/investment")
public class InvestmentPDFController {
	
	@Autowired
	InvestmentService investmentService;
	
	public static String username = "";
	
	private static final float DEFAULT_HEADER_SPACE = 40;
	
	@RequestMapping(value = "/deposit/{bank}")
	@ResponseBody
	public void depositDetailPDF(HttpServletResponse response,@PathVariable String bank) throws DocumentException, IOException {
	
		Map<String, Object> depositDetails = investmentService.getDepositDetails("9999", bank);
		List<Map<String, Object>> finalResponse = (List<Map<String, Object>>) depositDetails.get("DepositDetails");
		List<JSONObject> jsonList2 = new ArrayList<>();
		
		double principleAmt = 0.0;
		double maturityAmt = 0.0;

		int n = 1;
		for (Map<String, Object> res : finalResponse) {

			JSONObject ob = new JSONObject();

			ob.put("S.No.", n);
			ob.put("Account Number", res.get("AccountNumber"));
			ob.put("Open Date", res.get("OpenDate"));
			ob.put("Maturity Date", res.get("MaturityDate"));
			ob.put("ROI", res.get("ROI(%)"));
			ob.put("Principal amt", res.get("PrincipalAmt"));
			ob.put("Maturity amt", res.get("MaturityAmount"));
			principleAmt = principleAmt + Double.parseDouble(String.valueOf(res.get("PrincipalAmt"))); 
			maturityAmt = maturityAmt + Double.parseDouble(String.valueOf(res.get("MaturityAmount"))); 
			n++;
			jsonList2.add(ob);
		}
		
		JSONObject ob = new JSONObject();

		ob.put("S.No.", "");
		ob.put("Account Number", "");
		ob.put("Open Date", "");
		ob.put("Maturity Date", "");
		ob.put("ROI", "Total");
		ob.put("Principal amt", principleAmt);
		ob.put("Maturity amt", maturityAmt);
		jsonList2.add(ob);
		
		DepositListPDFGenerator pdfUtils = new DepositListPDFGenerator();
		
		response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
       	response.setContentType("application/pdf");
		
		HttpHeaders headers = new HttpHeaders();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		float headerFontSize = 12f;
		String fontName = "Helvetica";
		PdfFont headerFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, headerFontSize);
		
		float dataFontSize = 10f;
		PdfFont dataFont = ExpensePDFGeneratorV2.createEnglishFont(fontName, dataFontSize);
		PdfDocument pdf = new PdfDocument(new PdfWriter(out));
		Document doc = new Document(pdf,PageSize.A4,false);
		
		doc.setMargins(doc.getLeftMargin()+ DEFAULT_HEADER_SPACE, doc.getRightMargin(), doc.getTopMargin() , doc.getBottomMargin());
		
		Div divLine = pdfUtils.createHeader(dataFont,bank);
		Div divDate = pdfUtils.createTableDiv(jsonList2, headerFont,dataFont);
		
		doc.add(divLine);
		doc.add(divDate);
		
		
		int numberOfPages = pdf.getNumberOfPages();
		
		IntStream.rangeClosed(1, numberOfPages)
	    .forEach(i -> {
	        String content = String.format("page %s of %s", numberOfPages, i);

	        doc.showTextAligned(new Paragraph(content), 559, 806, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
	    });
		
		doc.close();
		
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
