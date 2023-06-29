package com.company.Personalmgmt.pdf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.company.Personalmgmt.dto.SyllabusListDetailDto;
import com.company.Personalmgmt.model.ApplicationFeature;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ClientApplicationProposalPDFGenerator {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
	
	public Paragraph addTitlePage() {
		
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Project Proposal - Vendor", catFont));
		
	//	preface.setAlignment(Element.ALIGN_CENTER);
		
		preface.setIndentationRight(300);
		
		addEmptyLine(preface, 1);
		return preface;
	}
	
	public PdfPTable getTitle(String title){
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100.0f);
		PdfPCell cell = new PdfPCell(new Phrase());
		Paragraph p1;
		p1 = new Paragraph(title, catFont);
		p1.setAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.addElement(p1);
		table.addCell(cell);
		
		return table;
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public PdfPTable headertwo(List<ApplicationFeature> applicationFeatures) throws DocumentException {

		String[] header = new String[] { "S.No", "Feature", "Description","Benefit" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 1, 2, 4,2 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);
		for (String columnHeader : header) {
			PdfPCell headerCell = new PdfPCell();
			headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
			headerCell.setPadding(8);
			table.addCell(headerCell);
		}
		int n = 1;

		PdfPCell cell = new PdfPCell();

		for (ApplicationFeature applicationFeature : applicationFeatures) {

			cell = new PdfPCell(new Phrase(String.valueOf(n), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(applicationFeature.getFeature(),
			FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(applicationFeature.getDescription(),
			FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(applicationFeature.getBenefit(),
			FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);


			n++;
		}

		return table;
	}
	
	public PdfPTable cmpsigndtls() throws DocumentException {

		Date datey = new Date();

		SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");

		String currentDate = formatz.format(datey);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 50f, 50f });
		Font f2 = FontFactory.getFont("Cambria", 9, Font.NORMAL);

		PdfPCell cell8 = new PdfPCell(new Phrase());// 1

		Phrase second = new Phrase("\n\n    Warm regards, \n  Jayaram Pandiyan,    \n Software Developer | Bengalore ", f2);
		

		cell8.addElement(second);
		cell8.setVerticalAlignment(Element.ALIGN_LEFT);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(100f);
		cell8.setBorderWidthLeft(1);
		cell8.setBorderWidthRight(0);
		cell8.setBorderWidthBottom(1);
		cell8.setBorderWidthTop(1);

		PdfPCell cell = new PdfPCell(new Phrase("\n\n\n\n"+ "Contact : +91 8838619054  \n\n  " + "Date:" + " " + currentDate, f2));// 1s

		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setRowspan(1);
		cell.setFixedHeight(100f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(1);
		cell.setBorderWidthBottom(1);
		cell.setBorderWidthTop(1);

		table.addCell(cell8);
		table.addCell(cell);
		return table;

	}
	
	public PdfPTable juriticationdetails() throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100.0f);
		Font f3 = FontFactory.getFont("Cambria", 7, Font.NORMAL);
		PdfPCell cell8 = new PdfPCell();

		cell8 = new PdfPCell(new Phrase("Hope. This PDF is generated via java project.©Jayaram2022"
				, f3));

		cell8.setVerticalAlignment(Element.ALIGN_CENTER);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setFixedHeight(30f);
		cell8.setBorderWidthLeft(0);
		cell8.setBorderWidthRight(0);
		cell8.setBorderWidthBottom(0);
		cell8.setBorderWidthTop(0);

		table.addCell(cell8);
		return table;

	}



}
