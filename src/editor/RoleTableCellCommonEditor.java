package editor;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.AccountTyeMode;

/**
 * @author 陌意随影
 TODO :角色管理的普通编辑器
 *2020年3月2日  下午7:45:20
 */
public class RoleTableCellCommonEditor extends DefaultCellEditor{
    private int row;
    private int column;
    private  AccountTyeMode model = null;
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public RoleTableCellCommonEditor() {
		super(new JTextField());
	}
@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	this.row =row;
	this.column =column;
	model = (AccountTyeMode) table.getModel();
	return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
 @Override
	public Object getCellEditorValue() {
	 Object cellEditorValue = super.getCellEditorValue();
	 model.setValueAt(cellEditorValue, row, column);
		return cellEditorValue;
	}

}
