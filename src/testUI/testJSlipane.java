 package testUI;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import UI.BookCateManagePanel;

/**
 * @author 陌意随影
 TODO :
 *2020年2月20日  下午2:45:08
 */
public class testJSlipane  extends JFrame {
	private static final long serialVersionUID = 1L;
	BookCateManagePanel  editorPanel = null;
	@SuppressWarnings("javadoc")
	public testJSlipane() {
		this.setBounds(200, 100, 1000, 600);
		this.editorPanel = new BookCateManagePanel ();
		this.getContentPane().add(editorPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new testJSlipane();
	}
}
