package com.company.Personalmgmt.pdf;

import java.util.List;

import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class CheckListPdfGenerator {
	
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD, BaseColor.RED);

	
	
	public PdfPTable headertwo(List<CheckListDetailDto> checkListDetailDtos) throws DocumentException {

		String[] header = new String[] { "Id", "Name", "Parent" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 1, 4, 2 });
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
		int n = 1;

		PdfPCell cell = new PdfPCell();
		
		checkListDetailDtos.stream().forEach(checkListDetailDto ->{
			
		});

		for (CheckListDetailDto checkListDetailDto : checkListDetailDtos) {

			cell = new PdfPCell(
					new Phrase(String.valueOf(n), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(checkListDetailDto.getName(),
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
	
	public Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Check List", catFont));

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Go Forward. Be positive", smallBold));

		return preface;

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	
	
	

}
