/**
 * 
 */
package com.company.Personalmgmt.pdf;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
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

public class DepositListPDFGenerator {

	
	public Div createHeader(PdfFont headerFont,String bank) {

		String header = "Fixed Deposits - "+bank;

		Div divDate = new Div();

		try {

			Paragraph headerText = new Paragraph();
			Paragraph text = new Paragraph(header).setFont(headerFont).setFontSize(15f);
			Color darkBlue = new DeviceRgb(0, 0, 139);
			text.setFontColor(darkBlue);
			headerText.add(text);

			divDate.add(headerText);

			divDate.setMarginTop(50);
			
			PDFGeneratorCommon PGC = new PDFGeneratorCommon();
			
			Div divLine = PGC.createLineDivFromJSONObject();

			divDate.add(divLine);
		} catch (Exception e) {
			// Handle the exception
		}

		return divDate;
	}
	
	public Div createTableDiv(List<JSONObject> rowDataList, PdfFont headerFont, PdfFont dataFont) {
		List<String> headerList = new ArrayList<>();
		headerList.add("S.No.");
		headerList.add("Account Number");
		headerList.add("Open Date");
		headerList.add("Maturity Date");
		headerList.add("ROI");
		headerList.add("Principal amt");
		headerList.add("Maturity amt");
	

		Div divDate = new Div();
		try {
			Table table = createTable(headerList, headerList, headerFont, dataFont);
			addRowsToTable(table, headerList, rowDataList, dataFont);

			divDate.add(table);
		} catch (Exception e) {

		}
		return divDate;
	}
	
	public Table createTable(List<String> headerList, List<String> orderedList, PdfFont headerFont, PdfFont dataFont) {
		Table table = new Table(headerList.size());
		try {
			Color headerColor = new DeviceRgb(173, 216, 230);
			Cell headerCell;

			for (String header : headerList) {
				Paragraph headerText = new Paragraph();
				Paragraph text = new Paragraph(header).setFont(headerFont).setFontSize(10f);
				headerText.add(text);

				headerCell = new Cell().add(headerText).setBackgroundColor(headerColor).setBorder(Border.NO_BORDER);
				table.addHeaderCell(headerCell);
			}

			table.setTextAlignment(TextAlignment.CENTER);
			table.setWidth(UnitValue.createPercentValue(100));

		} catch (Exception e) {
			// Handle the exception
		}

		return table;
	}

	public void addRowsToTable(Table table, List<String> headerList, List<JSONObject> rowDataList, PdfFont dataFont) {
		int rowIndexTable1 = 0;
		try {
			Color rowColor = new DeviceRgb(248, 248, 248);
			Color rowColor2 = new DeviceRgb(255, 213, 128);

			for (JSONObject rowDataMap : rowDataList) {
				rowIndexTable1++;
				for (String row : headerList) {
					String cellValue = rowDataMap.optString(row, "");
					Paragraph cellText = new Paragraph();
					Paragraph text = new Paragraph(cellValue).setFont(dataFont).setFontSize(10f);
					if (row.equals("Maturity Date")) {
						text.setFontColor(ColorConstants.BLUE); // Set font color to blue
					}
					cellText.add(text);

					if (row.equals("ROI") && cellValue.equals("Total")) {
						table.addCell(
								new Cell().add(cellText).setBackgroundColor(rowColor2).setBorder(Border.NO_BORDER));
					} else if (row.equals("Category") && cellValue.equals("Total Expenses")) {
						table.addCell(
								new Cell().add(cellText).setBackgroundColor(rowColor2).setBorder(Border.NO_BORDER));
					} else if (rowIndexTable1 % 2 == 0) {
						table.addCell(
								new Cell().add(cellText).setBackgroundColor(rowColor).setBorder(Border.NO_BORDER));
					} else {
						table.addCell(new Cell().add(cellText).setBorder(Border.NO_BORDER));
					}
				}
			}

		} catch (Exception e) {
			// Handle the exception
		}
	}

}
