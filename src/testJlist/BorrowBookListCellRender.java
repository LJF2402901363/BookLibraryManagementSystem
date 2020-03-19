package testJlist;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import domain.BorrowBook;

/**
 * @author 陌意随影
 TODO :
 *2020年3月18日  下午5:32:18
 */
public class BorrowBookListCellRender implements  ListCellRenderer<Object>{
    private ListCellPanel panel = null;
   @SuppressWarnings("javadoc")
public BorrowBookListCellRender() {
}
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if(value instanceof BorrowBook) {
			panel = new ListCellPanel((BorrowBook)value);
		}else {
			panel = new ListCellPanel(null);
		}
		return panel;
	}

}
