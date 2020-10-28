package com.community.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.community.exception.CustomException;

/**
 * excel操作
 * @author king
 *
 */
public class Poi {
	private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    
	public static Sheet read(MultipartFile file) {
		Sheet sheet = null;
		try  {
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			Workbook workbook = null;
	        if (XLS.equalsIgnoreCase(fileType)) {
	            workbook = new HSSFWorkbook(file.getInputStream());
	        } else if (XLSX.equalsIgnoreCase(fileType)) {
	            workbook = new XSSFWorkbook(file.getInputStream());
	        }
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			throw new CustomException("文件读取异常, " + e.getMessage());
		}
		
		return sheet;
	}
	
	/**
	 * 读入excel的内容转换成字符串
	 * 
	 * @author Rex.Tan
	 * @date 2019年11月29日 下午2:24:04
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStringValueFromCell(Cell cell) {
		String value = "";
		if (cell == null) {
			return value;
		}
		// 判断数据类型
		switch (cell.getCellType()) {
		case 2://FORMULA
			value = "" + cell.getCellFormula();
			break;
		case 0: //NUMERIC
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				return date2StringNormal(date, null);
			} else {
				cell.setCellType(CellType.STRING);
				value = "" + cell.getStringCellValue();
				if (value.endsWith(".0")) {
					value = value.substring(0, value.indexOf("."));
				}
			}
			break;
		case 1: //String
			value = cell.getStringCellValue();
			break;
		default:
			break;
		}
		if(value != null) {
			value = value.trim();
		}

		return value;
	}
	
	public static String date2StringNormal(Date date, String format) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotBlank(format)) {
			sdf = new SimpleDateFormat(format);
		}
		return sdf.format(date);
	}
}
