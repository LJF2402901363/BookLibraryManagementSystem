package testTree;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author 陌意随影
 TODO : 测试JPanel的事件
 *2020年3月8日  下午1:51:31
 */
public class testJPanelEvent  extends JFrame{
	private JPanel panel = null;
    private JPanel panel2 = null;
    private JTextField text = null;
    private JButton btn = new JButton("你是");
	 public testJPanelEvent() {
		 this.setLayout(new FlowLayout());
		 this.setBounds(100, 100, 600, 600);
	    this.panel = new JPanel();
	    btn.setPreferredSize(new Dimension(50,50));
	    panel2= new JPanel();
	    text = new JTextField();
	    text.setPreferredSize(new Dimension(200,40));
//	    this.panel.setBorder(BorderFactory.createLineBorder(Color.red,2));
	    this.panel.setPreferredSize(new Dimension(300,300));
	    this.panel2.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
	    this.panel2.setPreferredSize(new Dimension(150,150));
	   
	    panel.add(text);
	    panel2.add(btn);
	    text.setEditable(false);
	    this.panel.add(panel2);
	    this.getContentPane().add(panel);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	 }
 public static void main(String[] args) {
	new testJPanelEvent();
}
}

