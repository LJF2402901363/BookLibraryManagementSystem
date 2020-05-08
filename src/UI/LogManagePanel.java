package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import domain.SysLog;
import editor.SysLogCellEdictor;
import factory.ServiceFactory;
import model.BaseTableModel;
import model.SysLogTableModel;
import service.SysLogService;
import service.SysLogServiceImpl;
import util.ConfigContant;
import util.DateUtil;
import util.TableDataOutputToExcel;

/**
 * @author 陌意随影
 TODO :日志面板
 *2020年3月27日  下午10:12:53
 */
public class LogManagePanel  extends JPanel{
	private static final long serialVersionUID = 1L;
	private SysLogTablePanel  sysLogTablePanel = null;
	private SysLogTableModel sysLogTableModel = null;
	private SysLogService sysLogService = null;
	private JTable table = null;
	private JTabbedPane tabbedPane = null;
	/*----------底部按钮------------*/
	/** 更新按钮 */
	private JButton btn_update = null;
	/** 导出数据按钮 */
	private JButton btn_out = null;
	/** 删除按钮 */
	private JButton btn_remove = null;
	public LogManagePanel() {
		this.table = new JTable();
		this.tabbedPane = new JTabbedPane();
		this.sysLogTableModel = new SysLogTableModel();
		this.sysLogService = (SysLogService) ServiceFactory.newInstanceService("SysLogService");;
		this.sysLogTablePanel = new SysLogTablePanel(sysLogService, table, sysLogTableModel);
		this.add(sysLogTablePanel);
		this.setPreferredSize(new Dimension(ConfigContant.WEIGHT, ConfigContant.HIGHT));
//    	this.setBorder(BorderFactory.createLineBorder(Color.yellow));
		this.table.setRowHeight(40);
		TableColumn column = this.table.getColumn("id");
		
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setPreferredWidth(0);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.setAutoscrolls(true);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.sysLogTablePanel.setSearchPanelDimension(new Dimension(550,50));
	    this.sysLogTablePanel.setButtomDimension(new Dimension(300,40));
	    this.sysLogTablePanel.setTableScrollPaneDimension(new Dimension(800,420));
		this.sysLogTablePanel.setFirstPageSize(6);;
		this.table.setAutoscrolls(true);
		int columnCount = table.getColumnCount();
		for(int i = 0;i < columnCount;i++) {
			table.getColumnModel().getColumn(i).setCellEditor(new SysLogCellEdictor());
		}
		tabbedPane.setPreferredSize(new Dimension(900,600));
		initTabbedPane();
		this.add(tabbedPane);
	}

	private void initTabbedPane() {
		JPanel searchPanel = createSearchPanel();
		this.tabbedPane.addTab("日志管理", searchPanel);

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
		this.btn_update = new JButton("修改");
		this.btn_out = new JButton("数据导出");
		this.btn_remove = new JButton("删除选中");
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
		btnPanel.add(btn_update);
		btnPanel.add(btn_out);
		btnPanel.add(btn_remove);
		panel.add(sysLogTablePanel, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		return panel;

	}

	private void addBtnEvent() {
		this.btn_update.addActionListener(e -> {
			JTable sysLogTable = LogManagePanel.this.sysLogTablePanel.getTable();
			      
			// 获取选中行
			int[] selectedRows = sysLogTable.getSelectedRows();
			if (selectedRows == null || selectedRows.length == 0) {
				JOptionPane.showMessageDialog(this, "您尚未选择要修改的日志记录！");
			}else {
				if("修改".equals(btn_update.getText())) {
					btn_update.setText("确认修改");
					sysLogTableModel.setEditable(true);
				}else {
					btn_update.setText("修改");
					int result = JOptionPane.showConfirmDialog(this, "确认修改?");
					if(result == JOptionPane.OK_OPTION) {
					  for(int i  = 0;i < sysLogTable.getRowCount();i++) {
						  SysLog sysLog = new SysLog();
						  int id = (Integer) sysLogTableModel.getValueAt(i, 1);
						  sysLog.setId(id);
						  sysLog.setOperator((String) sysLogTableModel.getValueAt(i,2));
						  sysLog.setOperationType((String) sysLogTableModel.getValueAt(i, 3));
						  sysLog.setCreateTime(DateUtil.StrToDate((String) sysLogTableModel.getValueAt(i, 4)));
						  sysLog.setDetails((String) sysLogTableModel.getValueAt(i, 5));
						  System.out.println(sysLog);
						  sysLogService.updateSysLog(sysLog);
					  }
					}else {
						// 更新数据
						LogManagePanel.this.sysLogTablePanel.renderTableData(0, sysLogTablePanel.getPageSize());
					}
					sysLogTableModel.setEditable(false);
					
				}
				
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
			JTable sysLogTable = LogManagePanel.this.sysLogTablePanel.getTable();
			// 获取选中行
			int[] selectedRows = sysLogTable.getSelectedRows();

			if (selectedRows == null || selectedRows.length == 0) {
				JOptionPane.showConfirmDialog(this, "您尚未选择要删除的日志记录！");
				return;
			} else {
				for (int i = 0; i < selectedRows.length; i++) {
					// 获取选中行的唯一主键id
					Integer id = (Integer) sysLogTable.getModel().getValueAt(selectedRows[i], 1);
					sysLogService.removeSysLogById(id);
				}
				JOptionPane.showMessageDialog(this, "删除成功！");
				// 更新数据
				LogManagePanel.this.sysLogTablePanel.renderTableData(0, sysLogTablePanel.getPageSize());
			}
		});
	}

	private void outPutDataBtnEvents(JButton btn) {
		btn.addActionListener(e -> {
			JTable sysLogTable = LogManagePanel.this.sysLogTablePanel.getTable();
			BaseTableModel<SysLog> tableModel = LogManagePanel.this.sysLogTablePanel.getTableModel();

			if ("导出当选中数据".equals(btn.getText())) {
				// 获取所有选中行的索引
				int[] selectedRows = sysLogTable.getSelectedRows();
				int len = selectedRows.length;
				if (len > 0) {
					Integer[] ids = new Integer[len];
					// 获取所有的行的唯一主键id
					for (int i = 0; i < len; i++) {
						ids[i] = (Integer) sysLogTable.getModel().getValueAt(selectedRows[i], 1);
					}
					while (ids.length != sysLogTable.getRowCount()) {
						int i = 0;
						for (; i < sysLogTable.getRowCount(); i++) {
							// 默认是未选行
							boolean fla = false;

							for (int j = 0; j < ids.length; j++) {
								Integer rowId = (Integer) sysLogTable.getModel().getValueAt(i, 1);
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
				int allRowCount = LogManagePanel.this.sysLogTablePanel.getAllRowCount();
				if (allRowCount > 0) {
					// 将sysLogTable的所有数据都渲染出来
					LogManagePanel.this.sysLogTablePanel.renderTableData(0, allRowCount);
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
				LogManagePanel.this.sysLogTablePanel.renderTableData(0, sysLogTablePanel.getPageSize());
				return;
			}
			// 完整的文件输出路径
			String outputPath = directory + fileName + ".xlsx";
			// 开始导出数据
			boolean fla = TableDataOutputToExcel.tableDataOutputToExcel(sysLogTable, outputPath, false);
			if (fla) {
				JOptionPane.showMessageDialog(this, "导出数据成功！");
			} else {
				JOptionPane.showMessageDialog(this, "导出数据失败！");
			}
			// 更新数据
			LogManagePanel.this.sysLogTablePanel.renderTableData(0, sysLogTablePanel.getPageSize());
		});
	}
}
