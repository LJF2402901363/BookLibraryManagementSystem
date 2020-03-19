package render;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import UI.BorrowBookListCellPanel;
import domain.BorrowBook;

/**
 * @author 陌意随影
 TODO :
 *2020年3月18日  下午5:32:18
 */
public class BorrowBookListCellRender implements  ListCellRenderer<Object>{
    private BorrowBookListCellPanel panel = null;
   @SuppressWarnings("javadoc")
public BorrowBookListCellRender() {
}
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
	
		if(value instanceof BorrowBook) {
			panel = new BorrowBookListCellPanel((BorrowBook)value);
		}else {
			panel = new BorrowBookListCellPanel(null);
		}
		return panel;
	}

}
