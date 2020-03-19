package testTree;

import javax.swing.JFrame;

/**
 * @author 陌意随影
 TODO :
 *2020年3月7日  下午5:52:49
 */
public class testBookCatePanel {
  public static void main(String[] args) {
	  JFrame f= new JFrame();
	  f.setBounds(100, 100, 900, 600);
	  f.getContentPane().add(new BookCatePanel());
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  f.setVisible(true);
}
}

