package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Objects;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import domain.Account;

/**
 * @author 陌意随影
 TODO :单元格渲染
 *2020年3月20日  下午9:46:23
 */
public class TableCellEditorDemo extends AbstractCellEditor
implements TableCellEditor{
	private static final long serialVersionUID = 1L;
     private boolean cellEditable = false;
     private TableRendererDemo tableRendererDemo= null;
     
     private Account account = null;
  @SuppressWarnings("javadoc")
public TableCellEditorDemo() {
  }

	@Override
	public Object getCellEditorValue() {
		return account;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    tableRendererDemo = (TableRendererDemo) table.getColumnModel().getColumn(column).getCellRenderer();
		if(Objects.isNull(value)) {
			JPanel panel =  new JPanel();
			panel.setBackground(new Color(164,188,196));;
			return panel;
		}
		
		if(isSelected) {
			account = (Account) table.getModel().getValueAt(row, column);
//			 System.out.println("("+row+","+column+")--->"+account);
			this.cellEditable = true;
		}else {
			
		}
		//获取单元格对应的Account的索引
				int index  = (row) * table.getColumnCount() + column;
//				 System.out.println("rowIndex:"+row +"----columnIndex:"+column+"----index:"+index +"--------value:"+value);
//		if(value instanceof Account) {
//			account = (Account) value;
//			System.out.println();
//			tableCellPanel.setAccount(account);
//		}
				return tableRendererDemo;
		
	}
  @Override
public boolean isCellEditable(EventObject e) {
////	  System.out.println((JTable)e.getSource());
//	 if(cellEditable) {
//		 System.out.println("(" + x +","+y+")"+"可编辑");
//	 }else {
//		 System.out.println("(" + x +","+y+")"+"不可编辑");
//	 }
	return true;
}
  
  
}
