package editor;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import model.SysLogTableModel;

/**
 * @author 陌意随影
 TODO :SysLog的表格单元
 *2020年2月28日  下午4:09:06
 */
public class SysLogCellEdictor  extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	private SysLogTableModel sysLogTableModel = null;
	private JTextField text = null;
	private int row;
	private int column;
	@SuppressWarnings("javadoc")
	public SysLogCellEdictor() {
		text = new JTextField();
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				  sysLogTableModel.setValueAt(text.getText(), row, column);
			}
		});
	}
	@Override
	public Object getCellEditorValue() {
		return text.getText();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		sysLogTableModel = (SysLogTableModel) table.getModel();
		if(value instanceof String) {
			text.setText((String) value);
		}else if(value instanceof Integer) {
			text.setText((Integer) value+"");
		}
		
		this.row =row;
		this.column = column;
		return text;
	}
}
