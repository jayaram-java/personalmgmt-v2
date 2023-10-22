/**
 * 
 */
package com.company.Personalmgmt.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
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

public class ExpensePDFGeneratorV2 {


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

    public Div createTableDiv(List<JSONObject> rowDataList, PdfFont headerFont, PdfFont dataFont) {
        List<String> headerList = new ArrayList<>();
        headerList.add("Id");
        headerList.add("Name");
        headerList.add("Description");
        headerList.add("Date");
        headerList.add("Amount");

        Div divDate = new Div();
        try {
            Table table = createTable(headerList, headerList, headerFont, dataFont);
            addRowsToTable(table, headerList, rowDataList, dataFont);

            divDate.add(table);
        } catch (Exception e) {
            // Handle the exception
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

            for (JSONObject rowDataMap : rowDataList) {
                rowIndexTable1++;
                for (String row : headerList) {
                    String cellValue = rowDataMap.optString(row, "");
                    Paragraph cellText = new Paragraph();
                    Paragraph text = new Paragraph(cellValue).setFont(dataFont).setFontSize(10f);
                    cellText.add(text);

                    if (rowIndexTable1 % 2 == 0) {
                        table.addCell(new Cell().add(cellText).setBackgroundColor(rowColor).setBorder(Border.NO_BORDER));
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
