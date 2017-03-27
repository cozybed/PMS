package com.common;

import java.io.BufferedInputStream;   
import java.io.FileInputStream;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.OutputStream;   
import java.lang.reflect.Field;   
import java.lang.reflect.InvocationTargetException;   
import java.lang.reflect.Method;   
import java.text.SimpleDateFormat;   
import java.util.ArrayList;   
import java.util.Collection;   
import java.util.Date;   
import java.util.Iterator;   
import java.util.List;   
import java.util.regex.Matcher;   
import java.util.regex.Pattern;   

import org.apache.poi.hssf.usermodel.HSSFCell;    
import org.apache.poi.hssf.usermodel.HSSFFont;  
import org.apache.poi.hssf.usermodel.HSSFRichTextString;   
import org.apache.poi.hssf.usermodel.HSSFRow;   
import org.apache.poi.hssf.usermodel.HSSFSheet;   
import org.apache.poi.hssf.usermodel.HSSFWorkbook;   
import org.apache.poi.hssf.util.HSSFColor;   
import org.junit.Test;


  

public class ExportExcel< T>  {   

    public void exportExcel(Collection< T> dataset, OutputStream out)  {   
        exportExcel("Sheet1", null, dataset, out, "yyyy-MM-dd");   
    }   

  

    public void exportExcel(String[] headers, Collection< T> dataset,   OutputStream out)     {   
        exportExcel("Sheet1", headers, dataset, out, "yyyy-MM-dd");   
    }   

   

    public void exportExcel(String[] headers, Collection< T> dataset,   OutputStream out, String pattern)     {   
        exportExcel("Sheet1", headers, dataset, out, pattern);   
    }   

   

      

    @SuppressWarnings({ "unchecked", "deprecation" })   
    public void exportExcel(String title, String[] headers,    Collection< T> dataset, OutputStream out, String pattern)    {   

        // 声明一个工作薄   
        HSSFWorkbook workbook = new HSSFWorkbook();   
        // 生成一个表格   
        HSSFSheet sheet = workbook.createSheet(title);   

        // 产生表格标题行   
        HSSFRow row = sheet.createRow(0);   
        for (short i = 0; i <  headers.length; i++)      {   
            HSSFCell cell = row.createCell(i);   
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);   
            cell.setCellValue(text);   
        }   
        
    	if (dataset.size() == 0) {
    		 try   {   
    	        	workbook.write(out);   
    	        }  catch (IOException e)  {   
    	            e.printStackTrace();   
    	        }   finally {
    	        	try {
    					workbook.close();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	        }
    		 return;
    	}
        // 遍历集合数据，产生数据行   
        Iterator< T> it = dataset.iterator();   
        int index = 0;   
        while (it.hasNext())     {   
            index++;   
            row = sheet.createRow(index);   
            T t = (T) it.next();   

            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值   

            Field[] fields = t.getClass().getDeclaredFields();   

            for (short i = 0; i <  fields.length; i++)     {   
                HSSFCell cell = row.createCell(i);   
                Field field = fields[i];   
                String fieldName = field.getName();   
                String getMethodName = "get"     + fieldName.substring(0, 1).toUpperCase()    + fieldName.substring(1);   

                try     {   
                    Class tCls = t.getClass();   
                    Method getMethod = tCls.getMethod(getMethodName,    new Class[]  {});   
                    Object value = getMethod.invoke(t, new Object[]    {});   

                    // 判断值的类型后进行强制类型转换   
                    String textValue = null;   
                    if (value instanceof Date)    {   
                        Date date = (Date) value;   
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);   
                        textValue = sdf.format(date);  
                    }   else    {   // 其它数据类型都当作字符串简单处理   
                        textValue = value.toString();   
                    }   
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成   
                    if (textValue != null)  {   
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");   
                        Matcher matcher = p.matcher(textValue);   
                        if (matcher.matches())  {   
                            // 是数字当作double处理   
                            cell.setCellValue(Double.parseDouble(textValue));   
                        }  else    {   
                            HSSFRichTextString richString = new HSSFRichTextString( textValue);   
                            HSSFFont font3 = workbook.createFont();   
                            font3.setColor(HSSFColor.BLACK.index);   
                            richString.applyFont(font3);   
                            cell.setCellValue(richString);   
                        }   
                    }   
                }   catch (SecurityException e)    {   
                    e.printStackTrace();   
                }   catch (NoSuchMethodException e)  {   
                    e.printStackTrace();   
                }   catch (IllegalArgumentException e)   {   
                    e.printStackTrace();   
                }    catch (IllegalAccessException e)  {   
                    e.printStackTrace();   
                }   catch (InvocationTargetException e)    {   
                    e.printStackTrace();   
                }  finally    {   
                	// 清理资源   
                	
                }   
            }   
        }   
        try   {   
        	workbook.write(out);   
        }  catch (IOException e)  {   
            e.printStackTrace();   
        }   finally {
        	try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }   

   /*
    @Test
    public static void main(String[] args)   

    {   

        // 测试学生   

        ExportExcel< Student> ex = new ExportExcel< Student>();   

        String[] headers =   

        { "学号", "姓名", "年龄", "性别", "出生日期" };   

        List< Student> dataset = new ArrayList< Student>();   

        dataset.add(new Student(10000001, "张三", 20, true, new Date()));   

        dataset.add(new Student(20000002, "李四", 24, false, new Date()));   

        dataset.add(new Student(30000003, "王五", 22, true, new Date()));   

        // 测试图书   

        ExportExcel< Book> ex2 = new ExportExcel< Book>();   

        String[] headers2 =   

        { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社" };   

        List< Book> dataset2 = new ArrayList< Book>();   

        try   

        {   
            dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",   

                    "清华出版社"));   

            dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",   

                    "阳光出版社"));   

            dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",   

                    "清华出版社"));   

            dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",   

                    "清华出版社"));   

            dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",   

                    "汤春秀出版社"));   

   

            OutputStream out = new FileOutputStream("/home/luojiarui/a.xls");   

            OutputStream out2 = new FileOutputStream("/home/luojiarui/d.xls");   

            ex.exportExcel(headers, dataset, out);   

            ex2.exportExcel(headers2, dataset2, out2);   

            out.close();   

            System.out.println("excel导出成功！");   

        }   

        catch (FileNotFoundException e)   

        {   

            e.printStackTrace();   

        }   

        catch (IOException e)   

        {   

            e.printStackTrace();   

        }   

    }   */

}   
