package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import domain.Account;
import domain.AccountType;
import editor.AccountTypeTableOperationCellEditor;
import editor.RoleTableCellCommonEditor;
import factory.ServiceFactory;
import model.AccountTableListModel;
import model.AccountTyeMode;
import model.BaseTableModel;
import render.AccountTypeTableOperationCellRender;
import service.AdministratortService;
import service.ReaderServiceImpl;
import service.AccountTypeService;
import service.AccountTypeServiceImpl;
import testUI.TableCellRendererDemo;
import util.ConfigContant;
import util.TableDataOutputToExcel;

/**
 * @author 陌意随影 TODO :读者管理面板 2020年2月17日 下午10:10:32
 */
public class RoleMangePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = null;
    private AccountTypeTablePanel  accountTypeTablePanel   = null;
    private AccountTyeMode    tableModel = null;
    private AccountTypeService  accountTypeService = null;
    private UpdateAccountTypePanel updateAccountTypePanel = null;
    private JTable table = null;
	/*----------底部按钮------------*/
	/** 新增按钮 */
	private JButton btn_add = null;
	/** 更新按钮 */
	private JButton btn_update = null;
	/** 导出数据按钮 */
	private JButton btn_out = null;
	/** 删除按钮 */
	private JButton btn_remove = null;

	@SuppressWarnings("javadoc")
	public RoleMangePanel() {
		this.setPreferredSize(new Dimension(ConfigContant.WEIGHT, ConfigContant.HIGHT));
//    	this.setBorder(BorderFactory.createLineBorder(Color.yellow));
		this.tabbedPane = new JTabbedPane();
		this.table = new JTable();
		this.tableModel = new AccountTyeMode();
		this.accountTypeService = (AccountTypeService) ServiceFactory.newInstanceService("AccountTypeService");
		this.tabbedPane.setPreferredSize(new Dimension(900, 550));
		this.accountTypeTablePanel = new AccountTypeTablePanel(this.accountTypeService, this.table,this.tableModel);
		this.table.setRowHeight(40);
		TableColumn column = this.table.getColumn("id");
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		 this.table.getTableHeader().setResizingAllowed(false);
		this.table.setAutoscrolls(true);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.accountTypeTablePanel.setSearchPanelDimension(new Dimension(530,50));
	    this.accountTypeTablePanel.setButtomDimension(new Dimension(500,50));
	    this.accountTypeTablePanel.setTableScrollPaneDimension(new Dimension(800,420));
		this.accountTypeTablePanel.setFirstPageSize(6);;
		this.table.setAutoscrolls(true);
		this.table.getColumn("操作").setCellEditor(new AccountTypeTableOperationCellEditor());
		this.table.getColumn("操作").setCellRenderer(new AccountTypeTableOperationCellRender());
		
		this.table.getColumn("类型名称").setCellEditor(new RoleTableCellCommonEditor());
		this.table.getColumn("可以借阅的天数").setCellEditor(new RoleTableCellCommonEditor());
		this.table.getColumn("可借次数").setCellEditor(new RoleTableCellCommonEditor());
		this.table.getColumn("可借书数").setCellEditor(new RoleTableCellCommonEditor());
		initTabbedPane();
		this.add(tabbedPane);
	}

	private void initTabbedPane() {
		JPanel searchPanel = createSearchPanel();
		this.tabbedPane.addTab("搜索", searchPanel);

	}

	private JPanel createUpdaPanel(AccountType accountType) {
		JPanel panel = new JPanel(new BorderLayout());
		this.updateAccountTypePanel = new UpdateAccountTypePanel(accountType);
		panel.add(updateAccountTypePanel);
		panel.setPreferredSize(new Dimension(500,500));
		return panel;
	}

	private JPanel createNewAccountPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		this.updateAccountTypePanel = new UpdateAccountTypePanel(null);
		panel.add(updateAccountTypePanel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(500,500));
		return panel;
	}

	/**
	 * 给按钮添加通用的样式
	 * 
	 * @param btn
	 */
	private void btnStyle(JButton btn) {
		btn.setBackground(new Color(30, 159, 255));
		// 该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
	}

	private JPanel createSearchPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		// 按钮面板
		JPanel btnPanel = new JPanel();
//		btnPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
		btnPanel.setPreferredSize(new Dimension(700, 50));
		Font font = new Font("微软雅黑", Font.BOLD, 14);
		this.btn_add = new JButton("新增");
		this.btn_update = new JButton("更新选中");
		this.btn_out = new JButton("数据导出");
		this.btn_remove = new JButton("删除选中");
		btnStyle(btn_add);
		btnStyle(btn_update);
		btnStyle(btn_out);
		// 设置按钮字体
		this.btn_remove.setFont(font);
		// 设置字体按钮背景颜色
		this.btn_remove.setBackground(new Color(255, 87, 34));
		// 设置按钮字体颜色
		this.btn_remove.setForeground(Color.WHITE);
		// 该按钮的字体取消点击时显示聚焦边框
		this.btn_remove.setFocusPainted(false);
		// 给按钮添加事件
		addBtnEvent();
		btnPanel.add(btn_add);
		btnPanel.add(btn_update);
		btnPanel.add(btn_out);
		btnPanel.add(btn_remove);
		panel.add(accountTypeTablePanel, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		return panel;

	}

	private void addBtnEvent() {
		this.btn_add.addActionListener(e -> {
			for (int i = 0; i < tabbedPane.getTabCount(); i++) {
				String titleAt = tabbedPane.getTitleAt(i);
				// 该标签页已经存在
				if ("添加".equals(titleAt)) {
					tabbedPane.remove(i);
				}
			}
			JPanel addNewAccountPanel = createNewAccountPanel();
			tabbedPane.addTab("添加", addNewAccountPanel);
			tabbedPane.setSelectedComponent(addNewAccountPanel);
			// 更新数据
			RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, accountTypeTablePanel.getPageSize());
		});
		this.btn_update.addActionListener(e -> {
			JTable AccountTable = RoleMangePanel.this.accountTypeTablePanel.getTable();
			// 获取选中行
			int[] selectedRows = AccountTable.getSelectedRows();
			if (selectedRows == null || selectedRows.length == 0) {
				JOptionPane.showMessageDialog(this, "您尚未选择要修改的角色类型！");
				return;
			} else if (selectedRows.length > 1) {
				JOptionPane.showMessageDialog(this, "请选择一个要修改的角色类型！");
				return;
			} else {
				// 获取选中行
				int selectedRow = selectedRows[0];
				 Object valueAt = AccountTable.getModel().getValueAt(selectedRow, 1);
				 Integer id  = 0;
				if(valueAt instanceof Integer) {
					 id = (Integer) valueAt;
				}else if(valueAt instanceof AccountType) {
					AccountType accountType1 = (AccountType)valueAt;
					id = accountType1.getId();
				}
				// 获取选中行的唯一主键id
				
				AccountType accountType = accountTypeService.findAccountTypeById(id);
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					String titleAt = tabbedPane.getTitleAt(i);
					// 该标签页已经存在
					if ("修改".equals(titleAt)) {
						tabbedPane.remove(i);
					}
				}
				JPanel updateAccountTypePanel = createUpdaPanel(accountType);
				tabbedPane.addTab("修改", updateAccountTypePanel);
				tabbedPane.setSelectedComponent(updateAccountTypePanel);
				// 更新数据
				RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, accountTypeTablePanel.getPageSize());
			}
		});
		this.btn_out.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPopupMenu popupMenu = new JPopupMenu();
				JButton btn1 = new JButton("导出当前页数据");
				popupMenu.setPreferredSize(new Dimension(135, 90));
				JButton btn2 = new JButton("导出当选中数据");
				JButton btn3 = new JButton("导出所有的数据");
				btnStyle(btn1);
				btnStyle(btn2);
				btnStyle(btn3);
				btn1.setBackground(new Color(0, 150, 136));
				btn2.setBackground(new Color(0, 150, 136));
				btn3.setBackground(new Color(0, 150, 136));
				popupMenu.add(btn1);
				popupMenu.add(btn2);
				popupMenu.add(btn3);
				outPutDataBtnEvents(btn1);
				outPutDataBtnEvents(btn2);
				outPutDataBtnEvents(btn3);
				popupMenu.show(btn_out, e.getX(), e.getY());
				popupMenu.setVisible(true);
			}
		});

		this.btn_remove.addActionListener(e -> {
			JTable AccountTable = RoleMangePanel.this.accountTypeTablePanel.getTable();
			// 获取选中行
			int[] selectedRows = AccountTable.getSelectedRows();

			if (selectedRows == null || selectedRows.length == 0) {
				JOptionPane.showConfirmDialog(this, "您尚未选择要删除的用户！");
				return;
			} else {
				for (int i = 0; i < selectedRows.length; i++) {
					// 获取选中行的唯一主键id
					Integer id = (Integer) AccountTable.getModel().getValueAt(selectedRows[i], 1);
					accountTypeService.removeAccountTypeById(id);
				}
				JOptionPane.showMessageDialog(this, "删除成功！");
				// 更新数据
				RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, accountTypeTablePanel.getPageSize());
			}
		});
	}

	private void outPutDataBtnEvents(JButton btn) {
		btn.addActionListener(e -> {
			JTable AccountTable = RoleMangePanel.this.accountTypeTablePanel.getTable();
			BaseTableModel<AccountType> tableModel = RoleMangePanel.this.accountTypeTablePanel.getTableModel();

			if ("导出当选中数据".equals(btn.getText())) {
				// 获取所有选中行的索引
				int[] selectedRows = AccountTable.getSelectedRows();
				int len = selectedRows.length;
				if (len > 0) {
					Integer[] ids = new Integer[len];
					// 获取所有的行的唯一主键id
					for (int i = 0; i < len; i++) {
						ids[i] = (Integer) AccountTable.getModel().getValueAt(selectedRows[i], 1);
					}
					while (ids.length != AccountTable.getRowCount()) {
						int i = 0;
						for (; i < AccountTable.getRowCount(); i++) {
							// 默认是未选行
							boolean fla = false;

							for (int j = 0; j < ids.length; j++) {
								Integer rowId = (Integer) AccountTable.getModel().getValueAt(i, 1);
								if (ids[j] == rowId) {
									// 是已选行
									fla = true;

									break;
								}
							}
							if (fla == false) {
								tableModel.removeRow(i);
							}
						}

					}
				} else {
					JOptionPane.showMessageDialog(this, "请选中要导出的数据！");
					return;
				}
			} else if ("导出所有的数据".equals(btn.getText())) {
				int allRowCount = RoleMangePanel.this.accountTypeTablePanel.getAllRowCount();
				if (allRowCount > 0) {
					// 将AccountTable的所有数据都渲染出来
					RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, allRowCount);
				}

			}
			FileDialog fileDialog = new FileDialog(new JFrame(), "请选择导出数据的路径", FileDialog.SAVE);
			fileDialog.setVisible(true);
			// 获取选择的文件夹路径
			String directory = fileDialog.getDirectory();
			// 获取选择的文件名
			String fileName = fileDialog.getFile();
			if (fileName == null || fileName.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "输入的文件名不能为空，请重新输入！");
				// 更新数据
				RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, accountTypeTablePanel.getPageSize());
				return;
			}
			// 完整的文件输出路径
			String outputPath = directory + fileName + ".xlsx";
			// 开始导出数据
			boolean fla = TableDataOutputToExcel.tableDataOutputToExcel(AccountTable, outputPath, false);
			if (fla) {
				JOptionPane.showMessageDialog(this, "导出数据成功！");
			} else {
				JOptionPane.showMessageDialog(this, "导出数据失败！");
			}
			// 更新数据
			RoleMangePanel.this.accountTypeTablePanel.renderTableData(0, accountTypeTablePanel.getPageSize());
		});
	}
}
