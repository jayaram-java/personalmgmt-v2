package com.company.Personalmgmt.controller;

import java.util.List;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.company.Personalmgmt.dto.SyllabusListDetailDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PersonalInfoPDFGenerator {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);

	
	public Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();

		addEmptyLine(preface, 1);

		preface.add(new Paragraph("Personal info", catFont));

		addEmptyLine(preface, 1);

		// preface.add(new Paragraph("Until Feb end.", smallBold));

		return preface;

	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public PdfPTable headertwo(PersonalInfoDto personalInfoDto) throws DocumentException {

		String[] header = new String[] { "Topics", "Parent" };

		PdfPTable table = new PdfPTable(header.length);
		table.setHeaderRows(1);
		table.setWidths(new int[] { 2,4 });
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(15);
		table.setSplitLate(false);

		PdfPCell cell = new PdfPCell();
		
		
		if(personalInfoDto.getName() != null) {
			cell = new PdfPCell(new Phrase("Name",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(personalInfoDto.getName(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		}
		
		if(personalInfoDto.getAadhaarNo() != null) {
			cell = new PdfPCell(new Phrase("Aadhaar",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(personalInfoDto.getAadhaarNo(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		}
		
		if(personalInfoDto.getAadhaarNo() != null) {
			cell = new PdfPCell(new Phrase("PAN",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(personalInfoDto.getPanNo(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		}
		
		if(personalInfoDto.getUan() != null) {
			cell = new PdfPCell(new Phrase("UAN",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(personalInfoDto.getUan(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		}
		

		if(personalInfoDto.getPassportNumber() != null) {
			cell = new PdfPCell(new Phrase("Passport",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(personalInfoDto.getPassportNumber(),
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		}
		
		
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			if(personalInfoDto.getPrimaryMobileNumber() != null) {
				cell = new PdfPCell(new Phrase("Primary mobile number",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getPrimaryMobileNumber(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}

			if(personalInfoDto.getPhoneNumber() != null) {
				cell = new PdfPCell(new Phrase("Office mobile number",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getPhoneNumber(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			if(personalInfoDto.getIfsc() != null) {
				cell = new PdfPCell(new Phrase("IFS Code",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getIfsc(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			if(personalInfoDto.getBankAccountNumber() != null) {
				cell = new PdfPCell(new Phrase("Account number",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getBankAccountNumber(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			if(personalInfoDto.getBloodGroup() != null) {
				cell = new PdfPCell(new Phrase("Blood Group",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getBloodGroup(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			if(personalInfoDto.getBloodGroup() != null) {
				cell = new PdfPCell(new Phrase("DOB",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getDob(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			if(personalInfoDto.getPrimaryEmailId() != null) {
				cell = new PdfPCell(new Phrase("Primary Email(Outlook)",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getPrimaryEmailId(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			if(personalInfoDto.getSecondaryEmailId() != null) {
				cell = new PdfPCell(new Phrase("Secondary Email(Gmail)",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getSecondaryEmailId(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			
				cell = new PdfPCell(new Phrase("Employee ID",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("ACE11267",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("Login ID",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Jayaram Pandiajothi|66557248",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase("Designation",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Sr. Engineer | Development | Java | B2",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
		
				cell = new PdfPCell(new Phrase("Office Email(Outlook)",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("jayaram.pandiajothi@aspiresys.com",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase("##########",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("##########----------------------------------------------->",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
			if(personalInfoDto.getCurrentAddress() != null) {
				cell = new PdfPCell(new Phrase("Home Address",
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(personalInfoDto.getCurrentAddress(),
						FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setPadding(5);
				table.addCell(cell);
				
			}
			
			cell = new PdfPCell(new Phrase("Present Address",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase( "Shree PG - 2/305,Sardar Patel Road, Navalur,Kelambakkam , Tamil Nadu - 603103",
					FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPadding(5);
			table.addCell(cell);
			
		
		

		return table;
	}


}
