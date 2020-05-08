package UI;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import domain.Account;
import editor.AccountGongGeTableCellEditor;
import model.BaseTableModel;
import render.AccountGongGeTableCellRender;
import service.BaseService;

/**
 * @author 陌意随影
 TODO :用户表格面板
 *2020年3月8日  下午12:01:25
 */
public class AccountTablePanel extends TablePanel<Account>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public AccountTablePanel(BaseService<Account> baseService, JTable table, BaseTableModel<Account> tableModel) {
		super(baseService, table, tableModel);
	}
	protected void initTableModelComBoxEvent() {
		this.tableTyeCombox.addActionListener(e->{
			String selectedItem = (String) tableTyeCombox.getSelectedItem();
			if(selectedItem == null) {
				return;
			}
			if(tableTypes[0].equals(selectedItem)) {
				if(currTableModel != listModel ) {
					currTableModel.removeAllRow();
					currTableModel =listModel;
					currTableModel.setPageBean(pageBean);
					table.setModel(currTableModel);
					 table.getColumn("创建时间").setPreferredWidth(140);
					table.getColumn("签名").setPreferredWidth(200);
					TableColumn column = table.getColumn("id");
					column.setMaxWidth(0);
					column.setMinWidth(0);
					column.setPreferredWidth(0);
					table.setRowHeight(40);
					table.getTableHeader().setVisible(true);;
					renderTableData(0, pageBean.getPageSize());
				}
			}else if(tableTypes[1].equals(selectedItem)) {
				if(currTableModel != gonggeModel ) {
					currTableModel.removeAllRow();
					currTableModel =gonggeModel;
					currTableModel.setPageBean(pageBean);
					table.setModel(currTableModel);
					table.getTableHeader().setVisible(false);;
					this.table.setRowHeight(180);
					for (int i = 0; i < table.getColumnCount(); i++) {
						table.getColumnModel().getColumn(i).setPreferredWidth(340);
						table.getColumnModel().getColumn(i).setCellRenderer(new AccountGongGeTableCellRender());
						table.getColumnModel().getColumn(i).setCellEditor(new AccountGongGeTableCellEditor());
					}
					
					renderTableData(0, pageBean.getPageSize());
				}
			}
		});
	}
}

