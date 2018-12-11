package com.meicloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

public class ExportOrImpotExcelUtil {

   private static Logger LOG = Logger.getLogger("ExportOrImpotExcelUtil");


   public static void exportExcel(List list, String saveFilePath, String[] colName, String[] col, int[] colWidth, boolean flag) throws IOException {
      int length = colName.length;
      if(flag) {
         ++length;
      }

      File infile = new File(saveFilePath);
      FileOutputStream outputStream = new FileOutputStream(infile);
      HSSFWorkbook workbook = new HSSFWorkbook();
      HSSFCellStyle createStyle1 = createStyle(workbook, (short)16);
      createStyle1.setFillPattern((short)1);
      createStyle1.setFillForegroundColor((short)55);
      new CellRangeAddress(0, 0, 0, length - 1);
      HSSFSheet sheet = workbook.createSheet("用户列表");

      for(int cellStyle3 = 0; cellStyle3 < length; ++cellStyle3) {
         if(flag) {
            if(cellStyle3 == 0) {
               sheet.setColumnWidth((short)cellStyle3, 1800);
            } else {
               sheet.setColumnWidth((short)cellStyle3, (short)colWidth[cellStyle3 - 1]);
            }
         } else {
            sheet.setColumnWidth((short)cellStyle3, (short)colWidth[cellStyle3]);
         }
      }

      HSSFCellStyle var22 = workbook.createCellStyle();
      var22.setAlignment((short)2);
      var22.setVerticalAlignment((short)1);
      HSSFRow row2 = sheet.createRow(0);

      int i;
      for(i = 0; i < length; ++i) {
         HSSFCell row3 = row2.createCell(i);
         row3.setCellStyle(var22);
         if(flag) {
            if(i == 0) {
               row3.setCellValue("序号");
            } else {
               row3.setCellValue(colName[i - 1]);
            }
         } else {
            row3.setCellValue(colName[i]);
         }
      }

      if(list != null) {
         for(i = 0; i < list.size(); ++i) {
            HSSFRow var23 = sheet.createRow(i + 3);

            for(int j = 0; j < length; ++j) {
               try {
                  HSSFCell e = var23.createCell(j);
                  e.setCellStyle(var22);
                  Class cs = list.get(i).getClass();
                  Field field;
                  if(flag) {
                     if(j == 0) {
                        e.setCellValue((double)(i + 1));
                     } else {
                        field = cs.getDeclaredField(col[j - 1]);
                        field.setAccessible(true);
                        e.setCellValue(field.get(list.get(i)).toString());
                     }
                  } else {
                     field = cs.getDeclaredField(col[j]);
                     field.setAccessible(true);
                     e.setCellValue(field.get(list.get(i)).toString());
                  }
               } catch (Exception var21) {
                  LOG.error(var21.getMessage());
               }
            }
         }
      }

      workbook.write(outputStream);
      outputStream.close();
   }

   private static HSSFCellStyle createStyle(HSSFWorkbook workbook, short s) {
      HSSFCellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setAlignment((short)2);
      cellStyle.setVerticalAlignment((short)1);
      HSSFFont font = workbook.createFont();
      font.setBoldweight((short)700);
      font.setFontHeightInPoints(s);
      font.setColor((short)10);
      cellStyle.setFont(font);
      return cellStyle;
   }

   public static List readExcel(String saveFilePath, String[] col, Class[] colType, Class clazz) throws Exception {
      FileInputStream is = new FileInputStream(saveFilePath);
      HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
      ArrayList list = new ArrayList();

      for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); ++numSheet) {
         HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
         if(hssfSheet != null) {
            for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); ++rowNum) {
               HSSFRow hssfRow = hssfSheet.getRow(rowNum);
               if(hssfRow != null) {
                  Object object = clazz.newInstance();

                  for(int j = 0; j < col.length; ++j) {
                     HSSFCell cell = hssfRow.getCell(j);
                     String value = getValue(cell, colType[j]);
                     Method method = clazz.getMethod(col[j], new Class[]{colType[j]});
                     String s = colType[j].getSimpleName();
                     if(s.equals("Integer")) {
                        if("序号".equals(value) || "".equals(value)) {
                           value = "0";
                        }

                        int i = (int)Double.parseDouble(value);
                        method.invoke(object, new Object[]{Integer.valueOf(i)});
                     } else {
                        method.invoke(object, new Object[]{value});
                     }
                  }

                  list.add(object);
               }
            }
         }
      }

      return list;
   }

   private static String getValue(HSSFCell hssfCell, Class clazz) {
      return hssfCell != null?(hssfCell.getCellType() == 4?String.valueOf(hssfCell.getBooleanCellValue()):(hssfCell.getCellType() == 0?String.valueOf(hssfCell.getNumericCellValue()):String.valueOf(hssfCell.getStringCellValue()))):"";
   }
}
