package testUI;

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
 *2020年2月20日  下午9:46:23
 */
public class TableCellEditorDemo extends AbstractCellEditor
implements TableCellEditor{
	private static final long serialVersionUID = 1L;
     private TableRendererDemo tableRendererDemo= null;
  @SuppressWarnings("javadoc")
public TableCellEditorDemo() {
  }

	@Override
	public Object getCellEditorValue() {
		return  new JPanel();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		 tableRendererDemo= (TableRendererDemo) table.getCellRenderer(row, column);		
		Account	account = (Account) table.getModel().getValueAt(row, column);
		System.out.println(1);
		System.out.println(value);
		tableRendererDemo.setAccount(account);
		return new JPanel();
		
	}
  @Override
public boolean isCellEditable(EventObject e) {
	return true;
}
  
  
}
