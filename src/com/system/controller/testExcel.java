package com.system.controller;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yousihan on 2017/4/6.
 */
public class testExcel {
    public static void main(String[] args) {
        HashMap h=new HashMap();
        h.put("1",2);

        System.out.println(h.put("1",2));
        System.out.println(h.put("1",2));
    }
    public static void testExport() {
        try {
            //创建一个Excel文件
            WritableWorkbook book = Workbook.createWorkbook(new File("/Users/yousihan/Documents/PMS/new.xls"));

            //创建Excel中的页面，设置页面名称，页面号由0开始，页面会按页面号从小到大的顺序在Excel中从左向右排列
            WritableSheet sheet1 = book.createSheet("第一页", 0);
            //设置要合并单元格的下标
            sheet1.mergeCells(0, 0, 1, 1);
            //作用是指定第i+1行的高度，比如将第一行的高度设为200
            sheet1.setRowView(1, 2000);
            //作用是指定第i+1列的宽度，比如将第一列的宽度设为30
            //sheet1.setColumnView(1, 160);
            WritableSheet sheet2 = book.createSheet("Sheet_2", 1);

            //设置单元格的样式
            WritableCellFormat cellFormat = new WritableCellFormat();
            //设置水平居中
            cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
            //设置垂直居中
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            //设置自动换行
            cellFormat.setWrap(true);
            //设置显示的字体样式，字体，字号，是否粗体，字体颜色
            cellFormat.setFont(new WritableFont(WritableFont.createFont("楷体_GB2312"), 12, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.RED));
            //设置单元格背景色
            cellFormat.setBackground(jxl.format.Colour.BRIGHT_GREEN);

            //创建一个单元格，并按行列坐标进行指定的内容写入 ，最后加入显示的样式
            Label label = new Label(0, 0, "Excel", cellFormat);

            Label labe2 = new Label(0, 0, "test22222222");



            //将行列的值写入页面
            sheet1.addCell(label);
            //将图片插入页面
            sheet2.addCell(labe2);

            //创建数字类型的行列值
            Number number1 = new Number(4, 0, 789.123);
            Number number2 = new Number(4, 0, 789.12345678910);

            //将数字类型的行列值插入指定的页面
            sheet1.addCell(number1);
            sheet2.addCell(number2);

            //创建日期类型数据，并添加
            jxl.write.DateTime dateTime = new DateTime(5, 0, new Date());
            sheet1.addCell(dateTime);

            //开始执行写入操作
            book.write();
            //关闭流
            book.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }


    }
}
