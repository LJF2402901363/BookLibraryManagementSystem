package testTableUI;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import domain.Account;

/**
 * @author 陌意随影
 TODO :单元格渲染
 *2020年3月20日  下午9:46:23
 */
public class NewTableCellRenderer
implements TableCellRenderer{
     private TableCellPanel tableCellPanel = null;
  @SuppressWarnings("javadoc")
public NewTableCellRenderer() {
	  
  }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
//		if(Objects.isNull(value)) {
//			JPanel panel =  new JPanel();
//			panel.setBackground(new Color(164,188,196));;
//			return panel;
//		}else {
		tableCellPanel = new TableCellPanel();
			if(value instanceof Account) {
				Account account = (Account) table.getModel().getValueAt(row, column);
				tableCellPanel.setAccount(account);
			}
			
			return tableCellPanel;
			
//		}
	}
	/**
	 * @return tableCellPanel
	 */
	public TableCellPanel getTableCellPanel() {
		return tableCellPanel;
	}
}
