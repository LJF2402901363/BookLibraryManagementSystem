package editor;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

import UI.BookCateTableModel;
/**
 * @author 陌意随影
 TODO :图书分类的Table编辑器
 *2020年3月9日  上午12:43:00
 */
public class BookCateTableEditor  extends DefaultCellEditor{
	private static final long serialVersionUID = 1L;
	private BookCateTableModel model = null;
	private	int rowIndex;
	private int columnIndex;
	
	@SuppressWarnings("javadoc")
	public BookCateTableEditor() {
		super(new JTextField());
	}
	@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		    this.model = (BookCateTableModel) table.getModel();
		    this.columnIndex = column;
		    this.rowIndex = row;
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
  @Override
	public Object getCellEditorValue() {
		Object value = super.getCellEditorValue();
		if(model != null) {
			model.setValueAt(value, rowIndex, columnIndex);;
		}
		System.out.println(value);
		return value;
	}
}
