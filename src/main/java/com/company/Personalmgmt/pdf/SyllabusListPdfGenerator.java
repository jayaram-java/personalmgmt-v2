package com.company.Personalmgmt.pdf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.company.Personalmgmt.dto.SyllabusListDetailDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class SyllabusListPdfGenerator {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);

	public Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Java Syllabus", catFont));

		addEmptyLine(preface, 1);

		// preface.add(new Paragraph("Until Feb end.", smallBold));

		return preface;

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public PdfPTable headertwo(List<SyllabusListDetailDto> syllabusListDetailDtos) throws DocumentException {

		String[] header = new String[] { "S.No", "Topics", "Parent" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 1, 4, 2 });
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

		for (SyllabusListDetailDto checkListDetailDto : syllabusListDetailDtos) {

			cell = new PdfPCell(
					new Phrase(String.valueOf(n), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(checkListDetailDto.getTopic(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(checkListDetailDto.getParent(),
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

		Phrase second = new Phrase("\n\n    Created By. \n      Jayaram    \n Software Developer | Chennai ", f2);
		

		cell8.addElement(second);
		cell8.setVerticalAlignment(Element.ALIGN_LEFT);
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setFixedHeight(100f);
		cell8.setBorderWidthLeft(1);
		cell8.setBorderWidthRight(0);
		cell8.setBorderWidthBottom(1);
		cell8.setBorderWidthTop(1);

		PdfPCell cell = new PdfPCell(new Phrase("\n\n\n\n" + "Date:" + " " + currentDate, f2));// 1s

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

		cell8 = new PdfPCell(new Phrase("Hope. This PDF is generated via java project"
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
