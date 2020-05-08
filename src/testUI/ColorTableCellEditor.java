package testUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * @author 陌意随影
 TODO :测试的ColorTableCellEditor
 *2020年3月20日  下午11:05:14
 */
public class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor{
	private JColorChooser colorChooser;
	private JDialog colorDialog;
	private JPanel panel ;
	private static final long serialVersionUID = 1L;
  @SuppressWarnings("javadoc")
public ColorTableCellEditor() {
	  this.panel = new JPanel();
	  colorChooser = new JColorChooser();
	  colorDialog = JColorChooser.createDialog(null, "Planet Color", false, colorChooser,
			  EventHandler.create(ActionListener.class, this, "stopCellEditing"),
		EventHandler.create(ActionListener.class, this, "cancelCellEditing" ) );
};
	@Override
	public Object getCellEditorValue() {
		
		return colorChooser.getColor();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		colorChooser.setColor((Color)value);
		
		return this.panel;
	}
 @Override
public boolean shouldSelectCell(EventObject anEvent) {
	 this.colorDialog.setVisible(true);;
	return true;
}
      @Override
    public void cancelCellEditing() {
     colorDialog.setVisible(false);
     super.cancelCellEditing();
    }
      @Override
    public boolean stopCellEditing() {
    	this.colorDialog.setVisible(false);
    	return  super.stopCellEditing();
    }
      
}
