package testUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.eltima.components.ui.DatePicker;

import UI.TablePanel;
import domain.Book;
import model.BookTableModel;
import service.BookServiceImpl;

/**
 * @author 陌意随影
 TODO :
 *2020年3月18日  下午12:08:55
 */
public class testTablePanel01  extends JFrame{
	private static final long serialVersionUID = 1L;
	private TableCellRendererDemo render = null;
	private AccountTableModelDemo model = null;
	private JTable table = null;
	@SuppressWarnings("javadoc")
	public testTablePanel01() {
		this.setBounds(100, 100, 900, 600);
		this.table = new JTable();
		this.table.setRowHeight(200);
		this.table.setAutoscrolls(true);
		this.render = new TableCellRendererDemo();
		this.model = new AccountTableModelDemo();
		this.table.setModel(model);
		this.table.getTableHeader().setPreferredSize(new Dimension(0,0));
//		this.table.setCellEditor(new TableCellEditorDemo());
			for(int i = 0 ;i < this.table.getColumnCount();i++ ) {
				this.table.getColumnModel().getColumn(i).setPreferredWidth(330);
				this.render = new TableCellRendererDemo();
				table.getColumnModel().getColumn(i).setCellRenderer(render);
				table.getColumnModel().getColumn(i).setCellEditor(render);
		}
//		  Object valueAt = table.getValueAt(1, 1);
//		  System.out.println("获取单元值："+valueAt);
		  this.table.getTableHeader().setReorderingAllowed(false);
		JScrollPane js = new JScrollPane(this.table);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(js);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new testTablePanel01();
	}

}
