package com.company.Personalmgmt.property;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfMaterial {
	
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	//  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	//  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

	

	
	public PdfPTable header() throws DocumentException, IOException {

		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorderWidth(0.5f);
		table.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

		table.addCell("key ");
		table.addCell("value ");
		return table;

	}
	
	public PdfPTable headertwo() throws DocumentException {

		String[] header = new String[] { "Header1", "Header2", "Header3", "Header4", "Header5" };
		String[] content = new String[] { "column 1", "column 2", "some Text in column 3", "Test data ", "column 5" };
		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 3, 2, 4, 3, 2 });
		table.setWidthPercentage(98);
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
		for (int i = 0; i < 15; i++) {
			int j = 0;
			for (String text : content) {
				if (i == 13 && j == 3) {
					text = "Test data \n Test data \n Test data";
				}
				j++;
				PdfPCell cell = new PdfPCell();
				cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
			}
		}

		return table;
	}
	
	public Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Check List", catFont));

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Until Feb end.", smallBold));

		return preface;

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
