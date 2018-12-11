package com.meicloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {

   private static Logger LOG = Logger.getLogger("ExcelExport");
   private SimpleDateFormat dateFormat;
   private String excel_date_format;
   private String number_format;
   private String xlsFileName;
   private Workbook workbook;
   private Sheet sheet;
   private Row row;
   private CellStyle stringStyle;
   private CellStyle doubleStyle;
   private CellStyle dateStyle;
   private Integer columnWidth;
   private Integer rowHeight;


   public ExcelExport(String sheetName) {
      this(sheetName, 10);
   }

   public ExcelExport(String sheetName, int fontSize) {
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      this.excel_date_format = "m/d/yy h:mm";
      this.number_format = "#,##0.#";
      this.stringStyle = null;
      this.doubleStyle = null;
      this.dateStyle = null;
      this.columnWidth = null;
      this.rowHeight = null;
      this.xlsFileName = sheetName;
      this.workbook = new HSSFWorkbook();
      this.sheet = this.workbook.createSheet(sheetName);
      Font font = this.workbook.createFont();
      font.setFontHeightInPoints((short)fontSize);
      font.setColor((short)32767);
      font.setFontName("宋体");
      this.stringStyle = this.workbook.createCellStyle();
      this.stringStyle.setFont(font);
      this.stringStyle.setAlignment((short)1);
      this.stringStyle.setWrapText(false);
      DataFormat format = this.workbook.createDataFormat();
      this.doubleStyle = this.workbook.createCellStyle();
      this.doubleStyle.setFont(font);
      this.doubleStyle.setAlignment((short)3);
      this.doubleStyle.setWrapText(false);
      this.doubleStyle.setDataFormat(format.getFormat(this.number_format));
      this.dateStyle = this.workbook.createCellStyle();
      this.dateStyle.setFont(font);
      this.dateStyle.setAlignment((short)2);
      this.dateStyle.setWrapText(false);
      this.dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(this.excel_date_format));
      this.columnWidth = Integer.valueOf(8000);
      this.rowHeight = Integer.valueOf(450);
   }

   public ExcelExport(File file, int sheetIndex) throws IOException {
      this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      this.excel_date_format = "m/d/yy h:mm";
      this.number_format = "#,##0.#";
      this.stringStyle = null;
      this.doubleStyle = null;
      this.dateStyle = null;
      this.columnWidth = null;
      this.rowHeight = null;
      if(file.getName().toLowerCase().endsWith("xls")) {
         this.workbook = new HSSFWorkbook(new FileInputStream(file));
      } else if(file.getName().toLowerCase().endsWith("xlsx")) {
         this.workbook = new XSSFWorkbook(new FileInputStream(file));
      }

      this.sheet = this.workbook.getSheetAt(sheetIndex);
      this.stringStyle = this.workbook.createCellStyle();
      this.stringStyle.setAlignment((short)1);
      DataFormat format = this.workbook.createDataFormat();
      this.doubleStyle = this.workbook.createCellStyle();
      this.doubleStyle.setAlignment((short)1);
      this.doubleStyle.setWrapText(false);
      this.doubleStyle.setDataFormat(format.getFormat(this.number_format));
      this.dateStyle = this.workbook.createCellStyle();
      this.dateStyle.setAlignment((short)1);
      this.dateStyle.setWrapText(false);
      this.dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(this.excel_date_format));
      this.rowHeight = Integer.valueOf(450);
   }

   public void setColumnWidth(int columnIndex, int width) {
      this.sheet.setColumnWidth(columnIndex, (short)width);
   }

   public void exportXLS() throws IOException {
      FileOutputStream fOut = new FileOutputStream(this.xlsFileName);
      this.workbook.write(fOut);
      fOut.flush();
      fOut.close();
   }

   public Row createRow(int rowIndex) {
      return this.createRow(rowIndex, this.rowHeight);
   }

   public void deleteTemplate() {
      for(int i = 1; i < 4; ++i) {
         Row row = this.sheet.getRow(i);
         this.sheet.removeRow(row);
      }

   }

   public Row createRow(int rowIndex, Integer rowHeight) {
      this.row = this.sheet.getRow(rowIndex);
      if(this.row == null) {
         this.row = this.sheet.createRow(rowIndex);
         this.row.setRowStyle(this.stringStyle);
      }

      if(rowHeight != null) {
         this.row.setHeight(rowHeight.shortValue());
      }

      return this.row;
   }

   public void setCell(int columnIndex, Object value) {
      this.setCellValue(this.row, columnIndex, value);
   }

   public void setCell(int rowIndex, int columnIndex, Object value) {
      Row r = this.sheet.getRow(rowIndex);
      if(r == null) {
         r = this.createRow(rowIndex);
      }

      this.setCellValue(r, columnIndex, value);
   }

   private void setCellValue(Row row, int columnIndex, Object value) {
      if(value != null) {
         Cell cell = row.createCell(columnIndex);
         if(value instanceof String) {
            cell.setCellValue((String)value);
            cell.setCellType(1);
            cell.setCellStyle(this.stringStyle);
         } else if(value instanceof Number) {
            cell.setCellValue(((Number)value).doubleValue());
            cell.setCellType(0);
            cell.setCellStyle(this.doubleStyle);
         } else if(value instanceof Boolean) {
            cell.setCellValue(((Boolean)value).booleanValue());
            cell.setCellType(4);
            cell.setCellStyle(this.stringStyle);
         } else if(value instanceof Date) {
            cell.setCellValue((Date)value);
            cell.setCellStyle(this.dateStyle);
         } else {
            cell.setCellValue(value.toString());
            cell.setCellType(1);
            cell.setCellStyle(this.stringStyle);
         }

      }
   }

   public void setRow(int rowIndex, Object ... objs) {
      this.createRow(rowIndex);

      for(int i = 0; i < objs.length; ++i) {
         this.setCell(i, objs[i]);
      }

   }

   public void setHeader(String ... titles) {
      this.setHeader((short)12, titles);
   }

   public void setHeader(short fontSize, String ... titles) {
      if(titles != null && titles.length != 0) {
         this.createRow(0);
         CellStyle cellStyle = this.workbook.createCellStyle();
         Font font = this.workbook.createFont();
         font.setFontHeightInPoints(fontSize);
         font.setColor((short)32767);
         font.setFontName("宋体");
         font.setBoldweight((short)700);
         cellStyle.setFont(font);
         cellStyle.setAlignment((short)2);
         cellStyle.setWrapText(false);
         int i = 0;
         String[] var9 = titles;
         int var8 = titles.length;

         for(int var7 = 0; var7 < var8; ++var7) {
            String title = var9[var7];
            this.setColumnWidth(i, this.columnWidth.intValue());
            Cell cell = this.row.createCell(i++);
            cell.setCellType(1);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
         }

      }
   }

   public void autoSizeColumn() {
      int count = this.sheet.getFirstRowNum();

      for(int i = 0; i < count; ++i) {
         this.sheet.autoSizeColumn(i);
      }

   }

   public void write(OutputStream os) {
      try {
         this.workbook.write(os);
      } catch (IOException var6) {
         LOG.error(var6.getMessage());
      } finally {
         IOUtils.closeQuietly(os);
      }

   }

   public void write(HttpServletResponse response, String fileName) throws IOException {
      response.reset();
      response.setContentType("application/octet-stream; charset=utf-8");
      String encodedFileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
      response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
      this.write(response.getOutputStream());
      response.flushBuffer();
   }

   public void writeToFile(String filePath) throws FileNotFoundException {
      this.write(new FileOutputStream(filePath));
   }

   public Workbook getWorkbook() {
      return this.workbook;
   }

   public Sheet getSheet() {
      return this.sheet;
   }

   public SimpleDateFormat getDateFormat() {
      return this.dateFormat;
   }

   public String formatDate(Date date) {
      return date != null?this.dateFormat.format(date):"";
   }

   public void setColumnWidth(Integer columnWidth) {
      this.columnWidth = columnWidth;
   }

   public void setRowHeight(Integer rowHeight) {
      this.rowHeight = rowHeight;
   }

   public static File getExcelDemoFile(String fileDir) throws Exception {
      String classDir = null;
      File file = null;
      classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
      file = new File(classDir + fileDir);
      if(!file.exists()) {
         throw new Exception("模板文件不存在！");
      } else {
         return file;
      }
   }
}
