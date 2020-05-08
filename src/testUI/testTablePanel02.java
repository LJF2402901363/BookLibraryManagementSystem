package testUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.eltima.components.ui.DatePicker;

import UI.TablePanel;
import domain.Account;
import domain.Book;
import model.BookTableModel;
import service.ReaderServiceImpl;
import service.BookServiceImpl;

/**
 * @author 陌意随影
 TODO :
 *2020年3月18日  下午12:08:55
 */
public class testTablePanel02  extends JFrame{
	private static final long serialVersionUID = 1L;
//	private TableCellRendererDemo render = null;
	private AccountTableModelDemo model = null;
	private JTable table = null;
	private TablePanelDemo02<Account> tablePanel = null;
	@SuppressWarnings("javadoc")
	public testTablePanel02() {
		this.setBounds(100, 50, 1000, 700);
		this.table = new JTable();
		this.model = new AccountTableModelDemo();
		this.tablePanel = new TablePanelDemo02<Account>(new ReaderServiceImpl(),this.table,this.model );
		this.tablePanel.setSearchPanelDimension(new Dimension(530,50));
	    this.tablePanel.setButtomDimension(new Dimension(500,50));
	    this.tablePanel.setTableScrollPaneDimension(new Dimension(900,520));
	    this.tablePanel.setFirstPageSize(6);
		this.table.setRowHeight(200);
		this.table.setAutoscrolls(true);
		this.table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.getTableHeader().setPreferredSize(new Dimension(0,0));
		table.setRowMargin(10);
		table.getColumnModel().setColumnMargin(10);
		for(int i = 0 ;i < table.getColumnCount();i++ ) {
			table.getColumnModel().getColumn(i).setPreferredWidth(330);
		   TableCellRendererDemo render = new TableCellRendererDemo();
			table.getColumnModel().getColumn(i).setCellRenderer(new TableRendererDemo());
			table.getColumnModel().getColumn(i).setCellEditor(new TableCellEditorDemo());
//		   table.getColumnModel().getColumn(i).setCellRenderer(render);
//			table.getColumnModel().getColumn(i).setCellEditor(render);
	}
			
		this.getContentPane().add(tablePanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new testTablePanel02();
	}

}
