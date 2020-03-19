package test;

import javax.swing.JFrame;

import UI.UpdateBookPanel;

/**
 * @author 陌意随影
 TODO :
 *2020年2月19日  上午11:42:09
 */
public class testNewBookPanel extends JFrame{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public testNewBookPanel() {
    	 this.setBounds(200, 200, 800, 600);
    	 this.getContentPane().add(new UpdateBookPanel(null,null));
    	 this.setVisible(true);
 		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     }
     /**
     * @param args
     */
    public static void main(String[] args) {
		new testNewBookPanel();
	}
}
