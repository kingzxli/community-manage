package com.community.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.community.exception.CustomException;


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
}
