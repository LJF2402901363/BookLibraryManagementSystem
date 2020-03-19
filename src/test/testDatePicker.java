package test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.eltima.components.ui.DatePicker;

import UI.TablePanel;
import domain.Book;
import model.BookTableModel;
import service.BookServiceImpl;

/**
 * @author 陌意随影
 TODO :
 *2020年2月18日  下午12:08:55
 */
public class testDatePicker  extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel  panel = null;
	@SuppressWarnings("javadoc")
	public testDatePicker() {
		this.setBounds(200, 200, 700, 600);
		panel =new JPanel();
		DatePicker datePicker = new DatePicker();
		panel.add(datePicker);
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new testDatePicker();
	}

}
