package render;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import UI.TableCellPanel;
import domain.Account;
/**
 * @author 陌意随影
 *  TODO :单元格渲染
 *  2020年3月10日 下午9:46:23
 */
public class AccountGongGeTableCellRender
implements TableCellRenderer{
     private TableCellPanel tableCellPanel = null;
  @SuppressWarnings("javadoc")
public AccountGongGeTableCellRender() {
	  
  }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		  tableCellPanel = new TableCellPanel();
			if(value instanceof Account) {
				Account account = (Account) table.getModel().getValueAt(row, column);
				tableCellPanel.setAccount(account);
			}
			return tableCellPanel;
	}
	/**
	 * @return tableCellPanel
	 */
	public TableCellPanel getTableCellPanel() {
		return tableCellPanel;
	}
}
