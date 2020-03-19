package editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import domain.AccountType;
import model.AccountTyeMode;
import service.AccountTypeService;
import service.AccountTypeServiceImpl;
/**
 * @author 陌意随影
 TODO :单元格渲染
 *2020年2月20日  下午9:46:23
 */
public class AccountTypeTableOperationCellEditor extends AbstractCellEditor
implements  TableCellEditor{
	private static final long serialVersionUID = 1L;
    private int roleTypeId = -1;
    private JPanel columnPanel = null;
    /**删除按钮*/
    private JButton btn_delete = null;
    /**修改按钮*/
    private JButton btn_update = null;
    private AccountTypeService accountTypeService = null;
    private AccountTyeMode  model = null;
    private int row = -1;
@SuppressWarnings("javadoc")
public AccountTypeTableOperationCellEditor() {
	  FlowLayout flowLayout = new FlowLayout();
	  flowLayout.setHgap(4);
	  this.columnPanel = new JPanel(flowLayout);
	  this.columnPanel.setOpaque(false);
	  this.btn_delete = new JButton("删除");
	  this.btn_update = new JButton("修改");
	  this.accountTypeService = new AccountTypeServiceImpl();
	  initComponents();
	  initComponentsEvents();
	  columnPanel.add(btn_update);
	  columnPanel.add(btn_delete);
}
private void initComponentsEvents() {
	  //给删除按钮添加点击事件
	this.btn_delete.addActionListener(e->{
		int result = JOptionPane.showConfirmDialog(null, "确定删除？");
		if(result == JOptionPane.OK_OPTION) {
			boolean fla = accountTypeService.removeAccountTypeById(roleTypeId);
			if(fla) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				model.removeRow(row);
			}else {
				JOptionPane.showMessageDialog(null, "删除失败！");
			}
		}
	});
	//给修改按钮添加点击事件
	this.btn_update.addActionListener(e->{
		if(row != -1) {
			AccountType accountType = model.getList().get(row);
			int result = JOptionPane.showConfirmDialog(null, "确定更新？");
			if(result == JOptionPane.OK_OPTION) {
				boolean fla = accountTypeService.updateAccountType(accountType);
				if(fla) {
					JOptionPane.showMessageDialog(null, "更新成功！");
				}else {
					JOptionPane.showMessageDialog(null, "更新失败！");
				}
			}
		}
		
	});
	
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
	public Object getCellEditorValue() {
		return columnPanel;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		        this.row =row;
				Object valueAt = table.getValueAt(row, 1);
				 System.out.println("id"+valueAt);
				 model =(AccountTyeMode) table.getModel();
				 if(valueAt instanceof Integer) {
					 this.roleTypeId = (int) valueAt;
				 }
		return columnPanel;
	}
@Override
public boolean isCellEditable(EventObject e) {
	return true;
}
}
