package UI;
import java.awt.Dimension;

import javax.swing.JTable;

import domain.SysLog;
import model.BaseTableModel;
import service.BaseService;


/**
 * @author 陌意随影
 TODO :
 *2020年2月18日  下午12:01:25
 */
public class SysLogTablePanel extends TablePanel<SysLog> {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public SysLogTablePanel(BaseService<SysLog> baseService, JTable table, BaseTableModel<SysLog> tableModel) {
		super(baseService, table, tableModel);
	}
	@Override
		protected void showSearchComBoxData() {
		this.searchCombox.addItem("全部");
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_ADDNEWACCOUNT);
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_ADDNEWBOOK);
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_REMOVEACCOUNT);
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_REMOVEBOOK);
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_UPDATEACCOUNT);
		this.searchCombox.addItem(SysLog.OPERATIONTYPE_UPDATEBOOK);
		this.searchCombox.setPreferredSize(new Dimension(80,30));
		this.searchCombox.setVisible(true);
		}

}
