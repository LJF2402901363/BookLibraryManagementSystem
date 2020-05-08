package UI;
import javax.swing.JTable;

import domain.Book;
import model.BaseTableModel;
import service.BaseService;

/**
 * @author 陌意随影
 TODO :图书表格面板
 *2020年3月18日  下午12:01:25
 */
public class BookTablePanel extends TablePanel<Book> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("javadoc")
	public BookTablePanel(BaseService<Book> baseService, JTable table, BaseTableModel<Book> tableModel) {
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
					renderTableData(0, pageBean.getPageSize());
				}
			}else if(tableTypes[1].equals(selectedItem)) {
//				if(currTableModel != gonggeModel ) {
//					currTableModel.removeAllRow();
//					currTableModel =gonggeModel;
//					currTableModel.setPageBean(pageBean);
//					table.setModel(currTableModel);
//					this.table.setRowHeight(180);
//					for (int i = 0; i < table.getColumnCount(); i++) {
//						table.getColumnModel().getColumn(i).setPreferredWidth(300);
//						TableCellRendererDemo render = new TableCellRendererDemo();
//						table.getColumnModel().getColumn(i).setCellRenderer(render);
//						table.getColumnModel().getColumn(i).setCellEditor(render);
//					}
//					System.out.println(currTableModel.getList());
//					renderTableData(0, pageBean.getPageSize());
//				}
			}
		});
	}
}
