package testUI;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author 陌意随影
 TODO :
 *2020年2月21日  下午12:52:37
 */
public class testSwingHtml extends JFrame{
	private static final String html ="<html> <h1>你好啊</h1></br><p>是啊</p><a href></a></html>";
	private static final String html_link ="<html> <a href=\"http://www.w3school.com.cn/\">Visit W3School</a></html>";
	private static final String path="http://www.w3school.com.cn/";
	private JEditorPane panel = new JEditorPane();
   public testSwingHtml() {
	   this.setTitle("测试HTML标签");
	   this.setBounds(100, 100, 500, 500);
	   JLabel html_lbl = new JLabel(html);
	   this.getContentPane().add(html_lbl,BorderLayout.NORTH);
	   this.getContentPane().add(new  JButton(html_link));
	   this.panel.setEditable(false);
	   try {
		this.panel.setPage(path);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   this.getContentPane().add(panel,BorderLayout.SOUTH);
	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	   this.setVisible(true);
   }
   public static void main(String[] args) {
	new testSwingHtml();
}
}
