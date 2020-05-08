package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * @author 陌意随影
 TODO :
 *2020年2月31日  下午7:36:38
 */
public class TableDataOutputToExcel {
/**
 * 将表格的数据写出到指定文件路径的Excel表中
 * @param table 含有数据的JTable
 * @param outPutPath  数据存储路径
 * @param append  是否追加写入
 */
public static boolean  tableDataOutputToExcel(JTable table,String outPutPath,boolean append) {
	File file = new File(outPutPath);
	boolean fla = false;
	if(!file.exists()) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	FileWriter  fileWriter = null;
	 try {
		  fileWriter = new FileWriter(file,append);
		  //获取表格模型
		  TableModel tableModel = table.getModel();
		  //获取表格的列总数
		  int columnCount = tableModel.getColumnCount();
		  //获取表格的行的总数
		   int rowCount = tableModel.getRowCount();
		   //将table的表头名写入到Excel表中
		  for(int i = 0 ; i < columnCount;i++) {
			  fileWriter.write(tableModel.getColumnName(i)+"\t");
		  }
		  //换行
		  fileWriter.write("\n");
		  //将表格的数据写入到Excel中
		  for(int i = 0;i < rowCount;i++) {
			  for(int j = 0 ; j < columnCount;j++) {
				  fileWriter.write(tableModel.getValueAt(i, j)+""+"\t");
			  }
			  //换行
			  fileWriter.write("\n");
		  }
		  fla = true;
		  if(fileWriter != null) {
			  fileWriter.close();
		  }
	} catch (IOException e) {
		e.printStackTrace();
	}
	 
	 return fla;
  }
}
