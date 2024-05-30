/**
 * 
 */
package com.company.Personalmgmt.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.UnitValue;

/**
* This class is used for 
*
* @author Jayaram
*/

public class PDFGeneratorCommon {
	
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
	
	public Div createLineDivFromJSONObject() {
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

}
