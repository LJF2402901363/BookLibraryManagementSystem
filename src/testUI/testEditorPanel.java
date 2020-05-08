 package testUI;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

/**
 * @author 陌意随影
 TODO :
 *2020年3月20日  下午2:45:08
 */
public class testEditorPanel  extends JFrame {
	private static final long serialVersionUID = 1L;
     JEditorPane editorPanel = null;
	@SuppressWarnings("javadoc")
	public testEditorPanel() {
		this.setBounds(200, 100, 600, 600);
		this.editorPanel = new JEditorPane();
		editorPanel.setContentType("text/html");
		this.editorPanel.setPreferredSize(new Dimension(400,400));
		this.getContentPane().add(editorPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new testEditorPanel();
	}
}
