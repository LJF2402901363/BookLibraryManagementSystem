package render;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import UI.ReturnBookListCellPanel;
import domain.BorrowBook;

/**
 * @author 陌意随影
 TODO :还书的渲染器
 *2020年3月8日  下午5:32:18
 */
public class ReturnBookListCellRender implements  ListCellRenderer<Object>{
    private ReturnBookListCellPanel panel = null;
   @SuppressWarnings("javadoc")
public ReturnBookListCellRender() {
}
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if(value instanceof BorrowBook) {
			panel = new ReturnBookListCellPanel((BorrowBook)value);
		}else {
			panel = new ReturnBookListCellPanel(null);
		}
		return panel;
	}

}
