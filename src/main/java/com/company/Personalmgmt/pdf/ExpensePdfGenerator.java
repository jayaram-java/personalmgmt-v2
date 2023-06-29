package com.company.Personalmgmt.pdf;

import java.util.List;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ExpensePdfGenerator {
	
	
	
	 private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD, BaseColor.BLUE);

	public PdfPTable headertwo(List<ExpenseDto> expensedtos) throws DocumentException {

		String[] header = new String[] { "Id", "Name", "Expense description", "Date", "Amount" };
	
		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 1, 3, 4, 2, 2 });
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
		
		double sum = 0;
		
		PdfPCell cell = new PdfPCell();
		
		for(ExpenseDto expenseDto : expensedtos){
			
			
			cell = new PdfPCell(new Phrase(String.valueOf(n), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(expenseDto.getName(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(expenseDto.getDescription(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(expenseDto.getDate(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(String.valueOf(expenseDto.getAmount()), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			
			sum = sum + expenseDto.getAmount();
			
			n++;
		}
		
		cell = new PdfPCell(new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(String.valueOf(sum), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);
		
		return table;
	}
	
	public Paragraph addSubTitlePage(String name) {
		Paragraph preface = new Paragraph();

	//	addEmptyLine(preface, 1);
		addEmptyLine(preface, 1);

		preface.add(new Paragraph(name.concat(" :"), smallBold));

		return preface;

	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public PdfPTable expenseDetailsBasedOnCategory(List<ExpenseDto> expensedtos) throws DocumentException {

		String[] header = new String[] { "Id", "Name", "Amount" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 1, 3, 2 });
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

		double sum = 0;

		PdfPCell cell = new PdfPCell();

		for (ExpenseDto expenseDto : expensedtos) {

			cell = new PdfPCell(new Phrase(String.valueOf(n), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(expenseDto.getCategoryName(),FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(expenseDto.getAmount()),FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			sum = sum + expenseDto.getAmount();

			n++;
		}

		cell = new PdfPCell(new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Total", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.valueOf(sum), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		table.addCell(cell);

		return table;
	}
	
	public PdfPTable go() throws DocumentException{
		  PdfPTable table = new PdfPTable(3);
		    // The first column appears to have double the width of the other columns
		    table.setWidths(new int[]{ 2, 1, 1});
		    // the first row consists of 1 cell that spans the 3 columns
		    PdfPCell c1 = new PdfPCell(new Phrase("Content 1"));
		    c1.setColspan(3);
		    table.addCell(c1);
		    // Then follows a row with normal cells
		    table.addCell("Content 2");
		    table.addCell("Content 3");
		    table.addCell("Content 4");
		    // Again we have a row with 1 cell that spans 3 columns
		    PdfPCell c5 = new PdfPCell(new Phrase("Content 5"));
		    c5.setColspan(3);
		    table.addCell(c5);
		    // Now we have a row with 1 normal cell and 1 that spans 2 columns
		    table.addCell("Content 7.1");
		    PdfPCell c7 = new PdfPCell(new Phrase("Content 7.2"));
		    c7.setRowspan(2);
		    table.addCell(c7);
		    
		    return table;
	}
	
	
	
	

}
