package testTableUI;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import domain.Account;

/**
 * @author 陌意随影
 TODO :单元格渲染
 *2020年3月20日  下午9:46:23
 */
public class NewTableCellEditor extends AbstractCellEditor
implements  TableCellEditor{
	private static final long serialVersionUID = 1L;
     private TableCellPanel tableCellPanel = null;
     private AccountTableModelDemo model = null;
  @SuppressWarnings("javadoc")
public NewTableCellEditor() {
	  
  }

	@Override
	public Object getCellEditorValue() {
		return tableCellPanel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		model = (AccountTableModelDemo) table.getModel();
		Account valueAt = (Account) model.getValueAt(row, column);
		tableCellPanel = new TableCellPanel();
		tableCellPanel.initBtnEvents();
		tableCellPanel.setAccount(valueAt);
		return tableCellPanel;
	}
  @Override
public boolean isCellEditable(EventObject e) {
	return true;
}
  
  
}
