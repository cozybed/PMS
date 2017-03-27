package com.common;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.List;  

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

  
/** 
 * 从excel读取数据/往excel中写入 excel有表头，表头每列的内容对应实体类的属性 
 *  
 *  
 */  
public class ImportExcel<T> {  
    private Workbook workbook;  
  
    public ImportExcel(String fileDir){  
        File file = new File(fileDir);
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (fis != null)
        try {
        	//System.out.println("++++++++++++");
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
      }
  
    /** 
     * 读取excel表中的数据. 
     *  
     * @param sheetName 
     *            表格索引(EXCEL 是多表文档,所以需要输入表索引号，如sheet1) 
     */  
    public List readFromExcel( Object object, String[] headers) {  
  
    	if (workbook == null) return null;
        List result = new ArrayList();  
        // 获取该对象的class对象  
        Class class_ = object.getClass();  
        // 获得该类的所有属性  
        Field[] fields = class_.getDeclaredFields();  
  
        // 读取excel数据  
        // 获得指定的excel表  
        //System.out.println(workbook.getNumberOfSheets());
        Sheet sheet = workbook.getSheetAt(0);  
        // 获取表格的总行数  
        int rowCount = sheet.getLastRowNum() + 1; // 需要加一  
        if (rowCount < 1) {  
            return result;  
        }  
        // 获取表头的列数  
        int columnCount = sheet.getRow(0).getLastCellNum();  
        
        // 读取表头信息,确定需要用的方法名---set方法  
        // 用于存储方法名  
        String[] methodNames = new String[columnCount]; // 表头列数即为需要的set方法个数  
        // 用于存储属性类型  
        String[] fieldTypes = new String[columnCount];  
        // 获得表头行对象  
        Row titleRow = sheet.getRow(0);  
        // 遍历  
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头列  
            String data = headers[columnIndex]; // 某一列的内容  
            String Udata = Character.toUpperCase(data.charAt(0))  
                    + data.substring(1, data.length()); // 使其首字母大写  
            methodNames[columnIndex] = "set" + Udata;  
            for (int i = 0; i < fields.length; i++) { // 遍历属性数组  
                if (data.equals(fields[i].getName())) { // 属性与表头相等  
                    fieldTypes[columnIndex] = fields[i].getType().getName(); // 将属性类型放到数组中  
                }  
            }  
        }  
        
        System.out.println(rowCount);
        // 逐行读取数据 从1开始 忽略表头 
        for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {  
            // 获得行对象  
            Row row = sheet.getRow(rowIndex);  
            if (row != null) {  
                Object obj = null;  
                // 实例化该泛型类的对象一个对象  
                try {  
                    obj = class_.newInstance();  
                } catch (Exception e1) {  
                    e1.printStackTrace();  
                }  
  
                // 获得本行中各单元格中的数据  
                for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {  
                	String data = "";
                	if (row.getCell(columnIndex) != null)
                		data = row.getCell(columnIndex).toString();  
                    // 获取要调用方法的方法名  
                    String methodName = methodNames[columnIndex];  
                    Method method = null;  
                    try {  
                    	System.out.println(columnIndex + " : " +fieldTypes[columnIndex]);
                        if (fieldTypes[columnIndex].equals("java.lang.String")) {  
                            method = class_.getDeclaredMethod(methodName,  
                                    String.class); // 设置要执行的方法--set方法参数为String  
                            method.invoke(obj, data); // 执行该方法  
                        } else if (fieldTypes[columnIndex].equals("int")) {  
                            method = class_.getDeclaredMethod(methodName,  
                                    int.class); // 设置要执行的方法--set方法参数为int  
                            double data_double;
                            if (data.equals("")) data_double = 0;
                            else data_double = Double.parseDouble(data);  
                            int data_int = (int) data_double;  
                            method.invoke(obj, data_int); // 执行该方法  
                        }  else if (fieldTypes[columnIndex].equals("float")) {  
                        	method = class_.getDeclaredMethod(methodName,  
                                    int.class); // 设置要执行的方法--set方法参数为int  
                        	double data_double;
                            if (data.equals("")) data_double = 0;
                            else data_double = Double.parseDouble(data);  
                            method.invoke(obj, data); // 执行该方法  
                        } else if (fieldTypes[columnIndex].equals("java.util.Date")) {  
                        	method = class_.getDeclaredMethod(methodName,  
                                    int.class); // 设置要执行的方法--set方法参数为int  
                        	double data_double;
                            if (data.equals("")) data_double = 0;
                            else data_double = Double.parseDouble(data);
                        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                            Date date = HSSFDateUtil.getJavaDate(data_double);  
                            data = sdf.format(date);
                            method.invoke(obj, data); // 执行该方法  
                        } else {
                        	
                        }
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
                result.add(obj);  
            }  
        }  
        return result;  
    }  
  /*
    public static void main(String[] args) {  
        ExcelManage em = new ExcelManage("/home/luojiarui/d.xls");  
        User user = new User();  
        List list = em.readFromExcel("sheet1", user);  
        for (int i = 0; i < list.size(); i++) {  
            User newUser = (User) list.get(i);  
            System.out.println(newUser.getId() + " " + newUser.getName() + " "  
                    + newUser.getPassword());  
        }  
  
    }  */
  
}  