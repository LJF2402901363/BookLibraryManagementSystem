package testUI;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import java.util.Objects;

import javax.swing.AbstractCellEditor;
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
public class TableCellRendererDemo extends AbstractCellEditor
implements  TableCellEditor,TableCellRenderer{
	public TableCellPanel getTableCellPanel() {
		return tableCellPanel;
	}
	private static final long serialVersionUID = 1L;
     private TableCellPanel tableCellPanel = null;
  @SuppressWarnings("javadoc")
public TableCellRendererDemo() {
	  
  }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		tableCellPanel = new TableCellPanel();
		if(Objects.isNull(value)) {
			JPanel panel =  new JPanel();
			panel.setBackground(new Color(164,188,196));;
			return panel;
		}else {
			if(value instanceof Account) {
				Account account = (Account) table.getModel().getValueAt(row, column);
				tableCellPanel.setAccount(account);
					
			}
			
			return tableCellPanel;
			
		}
	}

	@Override
	public Object getCellEditorValue() {
		return tableCellPanel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		TableCellRendererDemo cellRenderer = (TableCellRendererDemo) table.getCellRenderer(row, column);
		TableCellPanel tableCellPanel =(TableCellPanel) cellRenderer.getCellEditorValue();
		if(Objects.isNull(value)) {
			JPanel panel =  new JPanel();
			panel.setBackground(new Color(164,188,196));;
			return panel;
		}
		
		//获取单元格对应的Account的索引
				int index  = (row) * table.getColumnCount() + column;
//				 System.out.println("rowIndex:"+row +"----columnIndex:"+column+"----index:"+index +"--------value:"+value);
		    System.out.println(tableCellPanel== this.tableCellPanel);
		return tableCellPanel;
	}
  @Override
public boolean isCellEditable(EventObject e) {
	return true;
}
  
  
}
