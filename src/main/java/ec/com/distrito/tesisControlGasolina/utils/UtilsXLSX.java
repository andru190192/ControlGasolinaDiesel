package ec.com.mariscalSucre.tesisMatriculacion.utils;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilsXLSX {

	public static void crearXLSX(List<String> list, String namesheet,
			String filename) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(namesheet);
			XSSFRow row;

			// XSSFCellStyle styleCabecera = wb.createCellStyle();
			// XSSFFont fontCabecera = wb.createFont();
			// fontCabecera.setFontHeightInPoints((short) 12);
			// fontCabecera.setFontName("Arial");
			// fontCabecera.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			// fontCabecera.setColor(IndexedColors.AUTOMATIC.getIndex());
			// styleCabecera.setFont(fontCabecera);
			//
			// row = sheet.createRow(0);
			// createCell(wb, row, 0, "SREDEPRONIK", styleCabecera);
			// row = sheet.createRow(1);
			// createCell(wb, row, 0, "SPagina prueba", styleCabecera);

			XSSFCellStyle styleCuerpo = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 9);
			font.setFontName("Arial");
			font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			font.setColor(IndexedColors.AUTOMATIC.getIndex());
			styleCuerpo.setFont(font);

			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i);
				String[] as = list.get(i).split("¬");
				for (int col = 0; col < as.length; col++)
					createCell(wb, row, col, as[col], styleCuerpo);
				sheet.autoSizeColumn(i);
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createCell(XSSFWorkbook wb, XSSFRow row, int col,
			String value, XSSFCellStyle style) {
		XSSFCell cell = row.createCell(col);
		if (value.charAt(0) == 'I' || value.charAt(0) == 'D') {
			if (value.charAt(0) == 'I')
				style.setDataFormat(wb.createDataFormat().getFormat("0"));
			else if (value.charAt(0) == 'D')
				style.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
			cell.setCellStyle(style);
			cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(new Double(value.substring(1).trim()));
		} else if (value.charAt(0) == 'S') {
			style.setDataFormat(wb.createDataFormat().getFormat("text"));
			cell.setCellStyle(style);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new XSSFRichTextString(value.substring(1).trim()));
		}
	}

}
