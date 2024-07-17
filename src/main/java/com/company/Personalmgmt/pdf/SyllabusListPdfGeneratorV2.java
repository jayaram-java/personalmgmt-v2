/**
 * 
 */
package com.company.Personalmgmt.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
* This class is used for 
*
* @author Jayaram
*/

public class SyllabusListPdfGeneratorV2 {
	
	public static PdfWriter createPdfWriter(String dest) throws FileNotFoundException {
		return new PdfWriter(dest);
	}

	public String convertPdfToBase64(String dest) throws IOException {
		File file = new File(dest);
		byte[] fileContent = new byte[(int) file.length()];
		try (FileInputStream inputStream = new FileInputStream(file)) {
			inputStream.read(fileContent);
		}
		return Base64.getEncoder().encodeToString(fileContent);
	}

	public static PdfFont createEnglishFont(String fontName, float fontSize) throws IOException {
		return PdfFontFactory.createFont(fontName, "UTF-8");
	}

	public Div createHeader(PdfFont headerFont, String month,String header) {

	

		Div divDate = new Div();

		try {

			Paragraph headerText = new Paragraph();
			Paragraph text = new Paragraph(header).setFont(headerFont).setFontSize(15f);
			Color darkBlue = new DeviceRgb(0, 0, 139);
			text.setFontColor(darkBlue);
			headerText.add(text);

			divDate.add(headerText);

			divDate.setMarginTop(50);
			
			Div divLine = createLineDivFromJSONObject();

			divDate.add(divLine);
		} catch (Exception e) {
			// Handle the exception
		}

		return divDate;
	}

	public Div createTableDiv(List<JSONObject> rowDataList, PdfFont headerFont, PdfFont dataFont) {
		List<String> headerList = new ArrayList<>();
		headerList.add("S.No.");
		headerList.add("Topics");
		headerList.add("Parent");
		headerList.add("Level");
	

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
				Paragraph text = new Paragraph(header).setFont(headerFont).setFontSize(12f);
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
					cellText.add(text);

					if (row.equals("Date") && cellValue.equals("Total")) {
						table.addCell(
								new Cell().add(cellText).setBackgroundColor(rowColor2).setBorder(Border.NO_BORDER));
					} else if(row.equals("Category") && cellValue.equals("Total Expenses")) {
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

	private Div createLineDivFromJSONObject() {
		String lineBase64 = "iVBORw0KGgoAAAANSUhEUgAABXwAAAAECAYAAAA9OPdQAAAACXBIWXMAABYlAAAWJQFJUiTwAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAA9SURBVHgB7dgxEQAgDASwghQ2/AtCCtXRv0RG1jv3FwAAAAAA4+0CAAAAACCC8AUAAAAACCF8AQAAAABCNFLPAjGeV/beAAAAAElFTkSuQmCC";
		Div divLine = new Div();
		Image imgLine = createImageFromBase64(lineBase64, 20, 760, 550, 2);
		divLine.add(imgLine);
		return divLine;
	}

	private Image createImageFromBase64(String base64Data, float x, float y, float width, float height) {
		byte[] imageBytes = Base64.getDecoder().decode(base64Data);
		ImageData imageData = ImageDataFactory.create(imageBytes);
		Image img = new Image(imageData);
		img.setFixedPosition(x, y);
		img.setWidth(UnitValue.createPointValue(width));
		img.setHeight(height);
		return img;
	}
	
	public Div trainerDetails() {
	    Div signatureDiv = new Div();
	    try {
	        SimpleDateFormat formatz = new SimpleDateFormat("dd-MM-yyyy");
	        String currentDate = formatz.format(new Date());

	       
	        float fontSize = 9; // Example font size
	        Color fontColor = new DeviceRgb(0, 0, 0); // Example font color, black

	       
	        Paragraph createdByPara = new Paragraph("Created By. \nJayaram\nEmail : jayaramp51096@gmail.com")
	                .setFontSize(fontSize)
	                .setFontColor(fontColor);
	        createdByPara.setMarginBottom(10); // Add some space between the lines if needed

	        Paragraph datePara = new Paragraph("Date: " + currentDate)
	                .setFontSize(fontSize)
	                .setFontColor(fontColor);
	        datePara.setTextAlignment(TextAlignment.RIGHT); // Aligning the date to the right

	        signatureDiv.add(createdByPara);
	        signatureDiv.add(datePara);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return signatureDiv;
	}

	
	public Div juridicationDetails(PdfFont font) throws IOException {
	    
	    Div div = new Div();
	    
	    String textContent = "Hope. This PDF is generated via java project";
	    
	    Paragraph p = new Paragraph(textContent).setFont(font).setFontSize(7);
	    
	    p.setTextAlignment(TextAlignment.CENTER);
	    
	    div.add(p);
	    
	    return div;
	}




}
