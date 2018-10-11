package com.achievement.utils;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.achievement.constants.GlobalConstants.EXCEL_TYPE_XLS;
import static com.achievement.constants.GlobalConstants.EXCEL_TYPE_XLSX;

/**
 * excel读写工具类
 *
 * @author weiQiang
 * @date 2018/10/5
 */
@Slf4j
public class PoiUtil {
  /**
   * @param workbook
   * @param sheetNum   (sheet的位置，0表示第一个表格中的第一个sheet)
   * @param sheetTitle （sheet的名称）
   * @param headers    （表格的标题）
   * @param result     （表格的数据）
   * @throws Exception
   */
  public static void exportExcel(HSSFWorkbook workbook, int sheetNum, String sheetTitle, List<String> headers, List<List<String>> result) {
    // 生成一个表格
    HSSFSheet sheet = workbook.createSheet();
    workbook.setSheetName(sheetNum, sheetTitle);
    // 设置表格默认列宽度为20个字节
    sheet.setDefaultColumnWidth((short) 20);
    // 生成一个样式
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 生成一个字体
    HSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.BLACK.index);
    font.setFontHeightInPoints((short) 12);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 把字体应用到当前的样式
    style.setFont(font);
    // 指定当单元格内容显示不下时自动换行
    style.setWrapText(true);
    // 产生表格标题行
    HSSFRow row = sheet.createRow(0);
    for (int i = 0; i < headers.size(); i++) {
      HSSFCell cell = row.createCell(i);
      cell.setCellStyle(style);
      HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
      cell.setCellValue(text.toString());
    }
    // 遍历集合数据，产生数据行
    if (result != null) {
      int index = 1;
      for (List<String> m : result) {
        row = sheet.createRow(index);
        int cellIndex = 0;
        for (String str : m) {
          row.createCell(cellIndex).setCellValue(str);
          cellIndex++;
        }
        index++;
      }
    }
  }

  /**
   * 读入excel文件，解析后返回
   *
   * @param file 文件
   * @throws IOException 异常
   */
  public static Map<String, List<String[]>> readExcel(MultipartFile file) throws IOException {
    Map<String, List<String[]>> fileResultMap = new LinkedHashMap<>();
    //检查文件
    checkFile(file);
    //获得Workbook工作薄对象
    Workbook workbook = getWorkBook(file);
    //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
    if (workbook != null) {
      for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
        List<String[]> list = new ArrayList<>();
        //获得当前sheet工作表
        Sheet sheet = workbook.getSheetAt(sheetNum);
        String sheetName = sheet.getSheetName();
        if (sheet == null) {
          continue;
        }
        //获得当前sheet的开始行
        int firstRowNum = sheet.getFirstRowNum();
        //获得当前sheet的结束行
        int lastRowNum = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        int numberOfCells = firstRow.getPhysicalNumberOfCells();
        //循环所有行
        for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
          //获得当前行
          Row row = sheet.getRow(rowNum);
          if (row == null) {
            continue;
          }
          //获得当前行的开始列
          int firstCellNum = row.getFirstCellNum();
          //获得当前行的列数
          int lastCellNum = numberOfCells;
          String[] cells = new String[numberOfCells];
          //循环当前行
          for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            cells[cellNum] = getCellValue(cell);
          }
          list.add(cells);
        }
        fileResultMap.put(sheetName, list);
      }
      workbook.close();
    }
    return fileResultMap;
  }

  /**
   * 检查文件
   *
   * @param file 文件
   * @throws IOException 异常
   */
  private static void checkFile(MultipartFile file) throws IOException {
    //判断文件是否存在
    if (null == file) {
      log.error("文件不存在！");
      throw new FileNotFoundException("文件不存在！");
    }
    //获得文件名
    String fileName = file.getOriginalFilename();
    //判断文件是否是excel文件
    assert fileName != null;
    if (!fileName.endsWith(EXCEL_TYPE_XLS) && !fileName.endsWith(EXCEL_TYPE_XLSX)) {
      log.error(fileName + "不是excel文件");
      throw new IOException(fileName + "不是excel文件");
    }
  }

  /**
   * 获得Workbook工作薄对象
   *
   * @param file 文 件
   * @return Workbook 工作薄对象
   */
  private static Workbook getWorkBook(MultipartFile file) {
    //获得文件名
    String fileName = file.getOriginalFilename();
    //创建Workbook工作薄对象，表示整个excel
    Workbook workbook = null;
    try {
      //获取excel文件的io流
      @Cleanup
      InputStream is = file.getInputStream();
      //根据文件后缀名不同(EXCEL_TYPE_XLS和EXCEL_TYPE_XLSX)获得不同的Workbook实现类对象
      if (fileName.endsWith(EXCEL_TYPE_XLS)) {
        //2003
        workbook = new HSSFWorkbook(is);
      } else if (fileName.endsWith(EXCEL_TYPE_XLSX)) {
        //2007
        workbook = new XSSFWorkbook(is);
      }
    } catch (Exception e) {
      log.info(e.getMessage());
    }
    return workbook;
  }

  /**
   * 获取单元格值
   *
   * @param cell 单元格
   * @return String
   */
  private static String getCellValue(Cell cell) {
    String cellValue = "";
    if (cell == null) {
      return cellValue;
    }
    //把数字当成String来读，避免出现1读成1.0的情况
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
      cell.setCellType(Cell.CELL_TYPE_STRING);
    }
    //判断数据的类型
    switch (cell.getCellType()) {
      //数字
      case Cell.CELL_TYPE_NUMERIC:
        cellValue = String.valueOf(cell.getNumericCellValue());
        break;
      //字符串
      case Cell.CELL_TYPE_STRING:
        cellValue = String.valueOf(cell.getStringCellValue());
        break;
      //Boolean
      case Cell.CELL_TYPE_BOOLEAN:
        cellValue = String.valueOf(cell.getBooleanCellValue());
        break;
      //公式
      case Cell.CELL_TYPE_FORMULA:
        cellValue = String.valueOf(cell.getCellFormula());
        break;
      //空值
      case Cell.CELL_TYPE_BLANK:
        cellValue = "";
        break;
      //故障
      case Cell.CELL_TYPE_ERROR:
        cellValue = "非法字符";
        break;
      default:
        cellValue = "未知类型";
        break;
    }
    return cellValue;
  }
}
