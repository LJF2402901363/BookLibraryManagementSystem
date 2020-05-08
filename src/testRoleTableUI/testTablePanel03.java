package testRoleTableUI;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * @author 陌意随影
 TODO :测试table数据
 *2020年3月18日  下午12:08:55
 */
public class testTablePanel03  extends JFrame{
	private static final long serialVersionUID = 1L;
	private AccountTyeMode model = null;
	private JTable table = null;
	@SuppressWarnings("javadoc")
	public testTablePanel03() {
		this.setBounds(100, 50, 1000, 700);
		this.table = new JTable();
		this.model = new AccountTyeMode();
		this.table.setRowHeight(40);
		this.table.setAutoscrolls(true);
		this.table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setModel(model);
		table.setRowMargin(10);
		table.getColumnModel().setColumnMargin(10);
		table.getColumnModel().getColumn(table.getColumnCount() - 1).setPreferredWidth(150);
		TableColumn column = table.getColumnModel().getColumn(table.getColumnCount() - 1);
//		RoleOperationTableCellRenderer cell = new RoleOperationTableCellRenderer();
		column.setCellRenderer(new RoleOperationCellRenderer());
		column.setCellEditor(new RoleOperationTableCellEditor());
		JScrollPane js = new JScrollPane(table);
		this.getContentPane().add(js);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new testTablePanel03();
	}

}
