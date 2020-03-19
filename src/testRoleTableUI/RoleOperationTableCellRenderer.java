package testRoleTableUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.EventObject;
import java.util.Objects;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import domain.Account;
import testUI.AccountTableDemo;

/**
 * @author 陌意随影
 TODO :单元格渲染
 *2020年2月20日  下午9:46:23
 */
public class RoleOperationTableCellRenderer extends AbstractCellEditor
implements  TableCellEditor,TableCellRenderer{
	private static final long serialVersionUID = 1L;
      private int roleTypeId = -1;
      private JPanel columnPanel = null;
      /**删除按钮*/
      private JButton btn_delete = null;
      /**修改按钮*/
      private JButton btn_update = null;
      private AccountTyeMode model = null;
  @SuppressWarnings("javadoc")
public RoleOperationTableCellRenderer() {
	  FlowLayout flowLayout = new FlowLayout();
	  flowLayout.setHgap(4);
	  this.columnPanel = new JPanel(flowLayout);
	  this.columnPanel.setOpaque(false);
	  this.btn_delete = new JButton("删除");
	  this.btn_update = new JButton("修改");
	  initComponents();
	  initComponentsEvents();
	  columnPanel.add(btn_update);
	  columnPanel.add(btn_delete);
  }
  private void initComponentsEvents() {
	  //给删除按钮添加点击事件
	this.btn_delete.addActionListener(e->{
		System.out.println("删除："+roleTypeId);
	});
	//给修改按钮添加点击事件
	this.btn_update.addActionListener(e->{
		System.out.println("更新："+roleTypeId);
	});
	
}
  private void removeEvents() {
	  
  }
private void initComponents() {
	  btnStyle(btn_delete);
	  btnStyle(btn_update);
	  btn_delete.setBackground(new Color(255,87,34));
}
  
  /**
   * 给指定的按钮添加样式
   * @param btn
   */
private void btnStyle(JButton btn) {
  	btn.setBackground(new Color(30,159,255));
		//该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
  }
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		model = (AccountTyeMode) table.getModel();
		System.out.println(model);
//		if(Objects.nonNull(value)) {
			Object valueAt = table.getValueAt(row, 1);
//			 System.out.println(valueAt);
			 if(valueAt instanceof Integer) {
				 this.roleTypeId = (int) valueAt;
				 System.out.println(roleTypeId);
				 this.columnPanel.validate();
				 this.columnPanel.repaint();
			 }
//		}
		return columnPanel;
	}

	@Override
	public Object getCellEditorValue() {
		return columnPanel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	
		if(Objects.nonNull(value)) {
			if(isSelected) {
				Object valueAt = table.getValueAt(row, 1);
				 System.out.println(valueAt);
				 if(valueAt instanceof Integer) {
					 this.roleTypeId = (int) valueAt;
//					 this.columnPanel.removeAll();
//					 this.columnPanel.add(btn_update);
////					 this.columnPanel.add(btn_delete);
//					 this.columnPanel.validate();
//					 this.columnPanel.repaint();
				 }
			}
		}
		return columnPanel;
	}
  @Override
public boolean isCellEditable(EventObject e) {
	return true;
}
}
