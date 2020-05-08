package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.BorrowBook;
import util.DateUtil;

/**
 * @author 陌意随影
 TODO :返还书籍的面板
 *2020年3月28日  下午5:49:45
 */
public class ReturnBookListCellPanel extends JPanel{
private static final long serialVersionUID = 1L;
private  BorrowBook borrowBook = null;
 private JLabel lbl_readerName = null;
 private JLabel lbl_bookName = null;
 private JLabel lbl_time = null;
 
 @SuppressWarnings("javadoc")
public ReturnBookListCellPanel( BorrowBook borrowBook) {
	 FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
	 layout.setHgap(10);
	 this.setLayout(layout);
	 this.borrowBook = borrowBook;
	 this.lbl_bookName = new JLabel();
	 this.lbl_readerName = new JLabel();
	 this.lbl_time = new JLabel();
	 this.setPreferredSize(new Dimension(430,35));
	 showData();
	 this.add(lbl_readerName);
	 this.add(lbl_bookName);
	 this.add(lbl_time);
 }
private void showData() {
	if(borrowBook!=null) {
		String dateToStr = DateUtil.DateToStr(borrowBook.getReturnTime());
		this.setBackground(new Color(255,255,255));
		lbl_readerName.setText("还书");
		lbl_readerName.setForeground(new Color(30,159,255));
		lbl_readerName.setFont(new Font("微软雅黑",Font.BOLD,18));
		String bookName = borrowBook.getIsBorrowedBook().getName();
		String readerName = borrowBook.getReader().getName();
		String str = readerName +"  归还了《"+ bookName+"》";
		lbl_bookName.setText(str);
		lbl_bookName.setFont(new Font("微软雅黑",Font.BOLD,15));
		lbl_bookName.setForeground(new Color(47,64,86));
		lbl_time.setText("时间 : "+ dateToStr);
		lbl_time.setFont(new Font("微软雅黑",Font.ITALIC,15));
	}
	
}
}
