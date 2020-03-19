package UI;
import javax.swing.JTable;

import domain.AccountType;
import model.BaseTableModel;
import service.BaseService;

/**
 * @author 陌意随影
 TODO :
 *2020年2月18日  下午12:01:25
 */
public class AccountTypeTablePanel extends TablePanel<AccountType>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public AccountTypeTablePanel(BaseService<AccountType> baseService, JTable table, BaseTableModel<AccountType> tableModel) {
		super(baseService, table, tableModel);
	}
	protected void initTableModelComBoxEvent() {
		this.tableTyeCombox.setVisible(false);
	}
}
