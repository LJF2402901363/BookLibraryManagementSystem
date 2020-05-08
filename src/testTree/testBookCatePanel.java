package testTree;

import javax.swing.JFrame;

/**
 * @author 陌意随影
 TODO :测试图书分类面板
 *2020年3月27日  下午5:52:49
 */
public class testBookCatePanel {
  /**
 * @param args
 */
public static void main(String[] args) {
	  JFrame f= new JFrame();
	  f.setBounds(100, 100, 900, 600);
	  f.getContentPane().add(new BookCatePanel());
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  f.setVisible(true);
}
}

