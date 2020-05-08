package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author 陌意随影
 TODO :图书分类管理
 *2020年3月17日  下午10:09:34
 */
public class BookCateManagePanel  extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = null;
	private BookCatePanel bookCatePanel = null;
	@SuppressWarnings("javadoc")
	public BookCateManagePanel() {
		this.bookCatePanel = new BookCatePanel();
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.add("分类管理",bookCatePanel);
		this.add(tabbedPane);
		tabbedPane.setPreferredSize(new Dimension(800, 600));
	}
}

