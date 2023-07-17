package com.company.Personalmgmt.controller;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class AgendaPDFGenerator {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD,BaseColor.BLUE);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);

	
	public Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Daily Agenda", catFont));

		addEmptyLine(preface, 1);

		// preface.add(new Paragraph("Until Feb end.", smallBold));

		return preface;

	}
	

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public PdfPTable headertwo() throws DocumentException {

		String[] header = new String[] { "Content", "Time" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 2,4 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);

		PdfPCell cell = new PdfPCell();
		
		cell = new PdfPCell(new Phrase("Bus time",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("8:45 AM",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Daily Sync call",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("10:00 AM",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Interview time",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("-",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Bus time",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("05:00 PM",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		
		return table;
	

	}
	
public PdfPTable headerFive() throws DocumentException {
		
		Paragraph preface = new Paragraph();
		
		addEmptyLine(preface, 1);


		String[] header = new String[] { "Name", "Responsibility" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 2,4 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);

		PdfPCell cell = new PdfPCell();
		
		cell = new PdfPCell(new Phrase("Meet jo,ISMS,Software install,Project|vijay|patch",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Monday",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase(" --- , ISMS,Toastmasters meeting",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Tuesday",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("ICICI - salary account open | kirthiga",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Wednesday",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" Toastmasters meeting ",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Thursday",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" --- ",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Friday",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		return table;
	

	}
	
	
	public PdfPTable headerFour() throws DocumentException {
		
		Paragraph preface = new Paragraph();
		
		addEmptyLine(preface, 1);


		String[] header = new String[] { "Name", "Responsibility" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 2,4 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);

		PdfPCell cell = new PdfPCell();
		
		cell = new PdfPCell(new Phrase("AP_P3_TM_ASIPL_SHT-ATP",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Project Name",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase(" 10-Jul-2023 ",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Start Date",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("31-Dec-2023",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("End Date",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		return table;
	

	}
	
public PdfPTable headerthree() throws DocumentException {
		
		Paragraph preface = new Paragraph();
		
		addEmptyLine(preface, 1);


		String[] header = new String[] { "Name", "Responsibility" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 2,4 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);

		PdfPCell cell = new PdfPCell();
		
		cell = new PdfPCell(new Phrase(" --- ",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Team Leader",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase(" --- ",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Mentor",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Satees Kandasamy",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Sr.Manager | daily sync call",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Manoj paulpandian",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Interview",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Vipenchander Sekar",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("HR Partner",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Ishwarya Murugesan",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("HR Recruiter",
				FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		
		return table;
	

	}
	

}
