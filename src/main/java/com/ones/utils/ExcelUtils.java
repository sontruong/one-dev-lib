/**
 * 
 */
package com.ones.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ones.encoding.Encoding;
import com.ones.encoding.VnConvert;

/**
 * @author sontruong
 *
 */
public class ExcelUtils {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelUtils.class);

	public static List<String> getListString(File file, String sheetName) throws IOException {
		List<String> result = new ArrayList<String>();
		XSSFWorkbook myWorkBook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
			Iterator<Row> rowIterator = mySheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				if (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					result.add(cell.getStringCellValue());
				}
			}
		} catch (IOException e) {
			LOG.error("== [getListString] ", e);
		} finally {
			if (null != myWorkBook) {
				myWorkBook.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return result;
	}

	public static Workbook getWorkbook(File file) throws IOException {
		XSSFWorkbook myWorkBook = null;
		try {
			InputStream is = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myWorkBook;
	}

	public static List<Row> getListRows(File file, String sheetName, long from) throws IOException {
		List<Row> result = new ArrayList<Row>();
		XSSFWorkbook myWorkBook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
			if (null == mySheet) {
				return result;
			}
			Iterator<Row> rowIterator = mySheet.iterator();
			long count = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (count >= from) {
					result.add(row);
				}
				count++;
			}
		} catch (IOException e) {
			LOG.error("== [getListRows] ", e);
		} finally {
			if (null != myWorkBook) {
				myWorkBook.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return result;
	}
	
	public static List<Row> getListRows(InputStream is, String sheetName, long from) throws IOException {
		List<Row> result = new ArrayList<Row>();
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
			if (null == mySheet) {
				return result;
			}
			Iterator<Row> rowIterator = mySheet.iterator();
			long count = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (count >= from) {
					result.add(row);
				}
				count++;
			}
		} catch (IOException e) {
			LOG.error("== [getListRows] ", e);
		} finally {
			if (null != myWorkBook) {
				myWorkBook.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return result;
	}

	public static Row getRow(File file, String sheetName, int rIndex) throws IOException {
		XSSFWorkbook myWorkBook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
			if (null == mySheet) {
				return null;
			}
			Iterator<Row> rowIterator = mySheet.iterator();
			long count = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (count == rIndex) {
					return row;
				}
				count++;
			}
		} catch (IOException e) {
			LOG.error("== [getListRows] ", e);
		} finally {
			if (null != myWorkBook) {
				myWorkBook.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return null;
	}
	
	public static Row getRow(InputStream is, String sheetName, int rIndex) throws IOException {
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
			XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
			if (null == mySheet) {
				return null;
			}
			Iterator<Row> rowIterator = mySheet.iterator();
			long count = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (count == rIndex) {
					return row;
				}
				count++;
			}
		} catch (IOException e) {
			LOG.error("== [getListRows] ", e);
		} finally {
			if (null != myWorkBook) {
				myWorkBook.close();
			}
			if (null != is) {
				is.close();
			}
		}
		return null;
	}

	public static String getStringValue(Row row, int index) {
		try {
			if (null == row) {
				return null;
			}
			Cell c = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (null == c) {
				return null;
			}
			if (CellType.STRING == c.getCellTypeEnum()) {
				return c.getStringCellValue().trim();
			}
			if (CellType.NUMERIC == c.getCellTypeEnum()) {
				return c.getNumericCellValue() + "";
			}
			if (HSSFDateUtil.isCellDateFormatted(c)) {
				return c.getDateCellValue().getTime() + "";
			}
		} catch (Exception e) {

			LOG.error("== [getStringValue] ", e);
		}
		return null;
	}

	public static Date getDateValue(Row row, int index) {
		try {
			if (null == row) {
				return null;
			}
			Cell c = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (null == c) {
				return null;
			}
			return c.getDateCellValue();
		} catch (Exception e) {

			LOG.error("== [getStringValue] ", e);
		}
		return null;
	}

	public static Double getNumberValue(Row row, int index) {
		try {
			if (null == row) {
				return null;
			}
			Cell c = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (null == c) {
				return null;
			}
			return c.getNumericCellValue();
		} catch (Exception e) {

			LOG.error("== [getStringValue] ", e);
		}
		return null;
	}

	public static boolean checkHasNumberValueGreaterThan(Row row, int iStart, int iEnd, Double d) {
		try {
			if (null == row) {
				return false;
			}
			if (iStart >= iEnd) {
				return false;
			}
			for (int i = iStart; i < iEnd; i++) {
				Cell c = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
				if (null != c) {
					double value = c.getNumericCellValue();
					if (value > d) {
						return true;
					}
				}
			}
		} catch (Exception e) {

			LOG.error("== [checkHasNumberValueGreaterThan] ", e);
		}
		return false;
	}

	public static String getVNIStringValue(Row row, int index, Encoding from, Encoding to) {
		try {
			if (null == row) {
				return null;
			}
			Cell c = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (null == c) {
				return null;
			}
			if (CellType.STRING == c.getCellTypeEnum()) {
				String srcValue = c.getStringCellValue().trim();
				if (null == from || null == to) {
					return srcValue;
				}
				return VnConvert.convert(srcValue, from, to);
			}
			if (CellType.NUMERIC == c.getCellTypeEnum()) {
				return c.getNumericCellValue() + "";
			}
			if (HSSFDateUtil.isCellDateFormatted(c)) {
				return c.getDateCellValue().getTime() + "";
			}
		} catch (Exception e) {

			LOG.error("== [getStringValue] ", e);
		}
		return null;
	}

	public static MyReflectField[] getFieldIndex(File file, String sheetName, int index, MyReflectField[] fields)
			throws IOException {

		if (null == fields || 0 == fields.length) {
			return fields;
		}

		Row row = getRow(file, sheetName, index);
		for (int j = 0; j < fields.length; j++) {
			String fieldName = fields[j].field.getName();
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (fieldName.equalsIgnoreCase(getStringValue(row, i))) {
					fields[j].index = i;
				}
			}
		}

		return fields;
	}

	/*
	 * Create excel
	 */

	private static CellStyle createHeaderStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
		style.setFont(headerFont);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		Font font = wb.createFont();
		font.setBold(true);
		style.setFont(font);
		return style;
	}

	private static void createHeaderRow(CellStyle cellStyle, Sheet sheet, MyReflectField[] fields) {
		if (null == fields || 0 == fields.length || null == sheet) {
			return;
		}
		Row rowTitle = sheet.createRow(0);
		Row header = sheet.createRow(1);
		rowTitle.createCell(0).setCellStyle(cellStyle);
		header.createCell(0);
		for (int i = 0; i < fields.length; i++) {
			MyReflectField field = fields[i];
			Cell cell1 = rowTitle.createCell(i + 1);
			String title = field.getTitle();
			if (StringUtils.isEmpty(title)) {
				title = field.getField().getName();
			}
			cell1.setCellValue(title);
			cell1.setCellStyle(cellStyle);
			Cell cell2 = header.createCell(i + 1);
			cell2.setCellValue(field.getField().getName());
			sheet.autoSizeColumn(i);
		}
		header.setZeroHeight(true);
	}

	private static void createRow(MyReflectField[] fields, Object obj, Row row, CellStyle cellStyle) {
		if (null == fields || 0 == fields.length || null == obj || null == row) {
			return;
		}
		if (null != cellStyle) {
			row.setRowStyle(cellStyle);
		}
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i].field;
			Cell cell1 = row.createCell(i + 1);
			Object value = ReflectionUtils.getFieldValue(obj, field, false);
			if (field.getType().getName().equalsIgnoreCase(Date.class.getName())) {
				cell1.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("dd-MM-yyyy"));
				cell1.setCellType(CellType.NUMERIC);
				cell1.setCellValue((Date) value);
				return;
			}

			if (value instanceof Integer || value instanceof Double || value instanceof Float
					|| value instanceof Long || value instanceof BigDecimal) {
				cell1.setCellType(CellType.NUMERIC);
				cell1.setCellValue(Double.valueOf("" + value));
				return;
			}

			cell1.setCellValue((String) value);
		}
	}

	public static ArrayList<?> getData(MyReflectField[] fields, Row row, Object object) throws IOException {
		if (null == fields || 0 == fields.length) {
			return null;
		}
		ArrayList<?> result = new ArrayList<>();
		
		return result;
	}
	
	public static File createTemplate(MyReflectField[] fields, String sheetName) throws IOException {
		if (null == fields || 0 == fields.length) {
			return null;
		}
		Workbook workbook = new XSSFWorkbook();
		CellStyle headerCellStyle = createHeaderStyle(workbook);
		Sheet sheet = workbook.createSheet(sheetName);
		createHeaderRow(headerCellStyle, sheet, fields);
		File file = File.createTempFile("sheetName", "xlsx");
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		return file;
	}

	public static File createList(MyReflectField[] fields, String sheetName, Collection<?> objects,
			boolean createHeader) throws IOException {
		if (null == fields || 0 == fields.length) {
			return File.createTempFile("sheetName", "xlsx");
		}
		Workbook workbook = new XSSFWorkbook();
		CellStyle headerCellStyle = createHeaderStyle(workbook);
		CellStyle cellStyle = workbook.createCellStyle();
		Sheet sheet = workbook.createSheet(sheetName);
		int rIndex = 0;
		if (createHeader) {
			createHeaderRow(headerCellStyle, sheet, fields);
			rIndex = 2;
		}
		Object[] objs = new Object[objects.size()];
		objs = objects.toArray(objs);
		for (int i = 0; i < objects.size(); i++) {
			Row row = sheet.createRow(i + rIndex);
			Cell cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(i + 1);
			createRow(fields, objs[i], row, cellStyle);
		}
		File file = File.createTempFile("sheetName", "xlsx");
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		return file;
	}
}
