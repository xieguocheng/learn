package com.learn.utils;

import com.learn.pojo.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 表格导出工具类
 * @Author： XO
 * @Description：
 * @Date： 2019/4/9 22:22
 */

public class ExcelUtil {

    public static HSSFWorkbook export(List<User> list) {

        String[] excelHeader = { "ID", "姓名", "手机号","邮箱"};
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("user");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();


        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            User user = list.get(i);
            row.createCell(0).setCellValue(user.getUserId());
            row.createCell(1).setCellValue(user.getUsername());

            if(user.getTelephone()!=null){
                row.createCell(2).setCellValue(user.getTelephone());
            }else{
                row.createCell(2).setCellValue("null");
            }
            if(user.getEmail()!=null){
                row.createCell(3).setCellValue(user.getEmail());
            }else{
                row.createCell(3).setCellValue("null");
            }
        }
        return wb;
    }



    public static void exportUserList(List<User> userList, ServletOutputStream outputStream)
            throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();//创建Excel工作簿对象
        XSSFSheet sheet = workbook.createSheet();//在工作簿中创建工作表对象

        sheet.setColumnWidth( 0, 128*14);
        sheet.setColumnWidth( 1, 256*14);
        sheet.setColumnWidth( 2, 128*14);
        sheet.setColumnWidth( 3, 600*14);
        sheet.setColumnWidth( 4, 300*14);
        sheet.setColumnWidth( 5, 350*14);
        sheet.setColumnWidth( 6, 256*14);
        sheet.setColumnWidth( 7, 600*14);

        sheet.setColumnWidth( 8, 600*14);
        sheet.setColumnWidth( 9, 256*14);
        sheet.setColumnWidth( 10, 400*14);
        workbook.setSheetName(0, "应聘生成绩信息");//设置工作表的名称
        XSSFRow row1 = sheet.createRow(0);//在工作表中创建行对象
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 10));//合并第1行的第8个到第12个之间的单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并第1行的第1个到第7个之间的单元格
        XSSFFont font = workbook.createFont();//创建字体对象
        font.setColor(new XSSFColor(java.awt.Color.BLACK));//设置字体颜色
        font.setFontHeightInPoints((short)12);//设置字号
        font.setFontName("楷体");//设置字体样式

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());// 设置背景色
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle.setFont(font);
        titleStyle.setAlignment( HorizontalAlignment.CENTER);//设置水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中

        XSSFCellStyle titleStyle2 = workbook.createCellStyle();
        titleStyle2.setFillForegroundColor(IndexedColors.GREEN.getIndex());// 设置背景色
        titleStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle2.setBorderTop(BorderStyle.THIN);
        titleStyle2.setBorderLeft(BorderStyle.THIN);
        titleStyle2.setBorderRight(BorderStyle.THIN);
        titleStyle2.setBorderBottom(BorderStyle.THIN);
        titleStyle2.setTopBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle2.setBottomBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle2.setLeftBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle2.setRightBorderColor(new XSSFColor(java.awt.Color.BLACK));
        titleStyle2.setFont(font);
        titleStyle2.setAlignment( HorizontalAlignment.CENTER);//设置水平居中
        titleStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中

        XSSFFont fontc = (XSSFFont) workbook.createFont();//创建字体对象
        fontc.setFontName("微软雅黑");
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直居中
        cellStyle.setFont(fontc);

        XSSFCell titleCell = row1.createCell(0);
        titleCell.setCellValue("用户信息");
        titleCell.setCellStyle(titleStyle);
        XSSFCell titleCell2 = row1.createCell(8);
        titleCell2.setCellValue("成绩信息");
        titleCell2.setCellStyle(titleStyle2);

        XSSFRow row2 = sheet.createRow(1);
        XSSFCell businessCell = row2.createCell(0);//在第1行中创建单元格对象
        businessCell.setCellValue("ID");
        businessCell.setCellStyle(titleStyle);

        XSSFCell custelephoneCell = row2.createCell(1);//在行中创建单元格对象
        custelephoneCell.setCellValue("姓名");
        custelephoneCell.setCellStyle(titleStyle);
        XSSFCell ivrCell = row2.createCell(2);//在行中创建单元格对象
        ivrCell.setCellValue("性别");
        ivrCell.setCellStyle(titleStyle);
        XSSFCell telephoneCell = row2.createCell(3);//在行中创建单元格对象
        telephoneCell.setCellValue("院校专业");
        telephoneCell.setCellStyle(titleStyle);
        XSSFCell itSystemCell = row2.createCell(4);//在行中创建单元格对象
        itSystemCell.setCellValue("电话号码");
        itSystemCell.setCellStyle(titleStyle);
        XSSFCell itRemarkCell = row2.createCell(5);//在行中创建单元格对象
        itRemarkCell.setCellValue("邮箱");
        itRemarkCell.setCellStyle(titleStyle);

        XSSFCell bigTypeCell = row2.createCell(6);//在行中创建单元格对象
        bigTypeCell.setCellValue("学历");
        bigTypeCell.setCellStyle(titleStyle);
        XSSFCell cusNameCell = row2.createCell(7);//在行中创建单元格对象
        cusNameCell.setCellValue("注册时间");
        cusNameCell.setCellStyle(titleStyle);
        XSSFCell chaSystemCell = row2.createCell(8);//在行中创建单元格对象
        chaSystemCell.setCellValue("考场名称");
        chaSystemCell.setCellStyle(titleStyle2);
        XSSFCell custCell = row2.createCell(9);//在行中创建单元格对象
        custCell.setCellValue("分数");
        custCell.setCellStyle(titleStyle2);
        XSSFCell telCell = row2.createCell(10);//在行中创建单元格对象
        telCell.setCellValue("面试官");
        telCell.setCellStyle(titleStyle2);

        for(int i=0;i < userList.size();i++){//遍历保存数据对象的集合
            User user = (User)userList.get(i);//获取封装数据的对象
            XSSFRow dataRow = sheet.createRow(i+2);//创建行
            XSSFCell business = dataRow.createCell(0);//创建单元格
            business.setCellValue(user.getUserId());//将数据添加到单元格中
            business.setCellStyle(cellStyle);
            XSSFCell cusTelephone = dataRow.createCell(1);
            cusTelephone.setCellValue(user.getUsername());
            cusTelephone.setCellStyle(cellStyle);
            XSSFCell ivr = dataRow.createCell(2);
            ivr.setCellValue(user.getSex()==0?"男":"女");
            ivr.setCellStyle(cellStyle);
            XSSFCell telephone = dataRow.createCell(3);
            telephone.setCellValue(user.getSchoolId()+"-"+user.getDept());
            telephone.setCellStyle(cellStyle);
            XSSFCell itSystem = dataRow.createCell(4);
            itSystem.setCellValue(user.getTelephone());
            itSystem.setCellStyle(cellStyle);

            XSSFCell itRemark = dataRow.createCell(5);
            itRemark.setCellValue(user.getEmail());
            itRemark.setCellStyle(cellStyle);
            /*
            XSSFCell bigType = dataRow.createCell(6);
            bigType.setCellValue(candidate.getEducation());
            bigType.setCellStyle(cellStyle);
            XSSFCell cusName = dataRow.createCell(7);
            cusName.setCellValue(candidate.getRegisterTime());
            cusName.setCellStyle(cellStyle);
            XSSFCell chaSystem = dataRow.createCell(8);
            chaSystem.setCellValue(candidate.getExamRoomName());
            chaSystem.setCellStyle(cellStyle);
            XSSFCell cust = dataRow.createCell(9);
            cust.setCellValue(candidate.getGrade());
            cust.setCellStyle(cellStyle);
            XSSFCell tel = dataRow.createCell(10);
            tel.setCellValue(candidate.getInterviewer());
            tel.setCellStyle(cellStyle);*/
        }
            outputStream.flush();
            workbook.write(outputStream);//将文档对象写入文件输出流
            outputStream.close();

    }




}
