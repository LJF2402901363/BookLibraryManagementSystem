package testJlist;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import domain.BorrowBook;
import factory.ServiceFactory;
import render.ReturnBookListCellRender;
import service.BorrowBookRecordService;

/**
 * @author 陌意随影
 TODO :测试控件
 *2020年3月18日  下午7:33:17
 */
public class TestListFrame  extends JFrame{
	private static final long serialVersionUID = 1L;
	private JSplitPane panel = null;
	private JListModel model = null;
	private BorrowBookListCellRender render = null;
	private JList<BorrowBook> list = null;
	private List<BorrowBook>  dataList = null;
	private BorrowBookRecordService service = null;
	  private JListModel returnBookmodel = null;
	private ReturnBookListCellRender returnBookListCellRender = null;
	private JList<BorrowBook> returnBooklist = null;

	@SuppressWarnings("javadoc")
	public TestListFrame() {
		this.panel = new JSplitPane();
		this.service = (BorrowBookRecordService) ServiceFactory.newInstanceService("BorrowBookService");
		this.model = new JListModel();
		this.render = new BorrowBookListCellRender();
		this.list = new JList<>();
		this.list.setModel(model);
		this.dataList = service.getAllBorrowBook();
		this.model.setListData(dataList);
		this.list.setCellRenderer(render);
		returnBooklist =new JList<BorrowBook>();
		this.returnBookmodel=new JListModel();
		this.returnBooklist = new JList<BorrowBook>();
		this.returnBooklist.setModel(returnBookmodel);
		returnBookmodel.setListData(dataList);
		this.returnBookListCellRender = new ReturnBookListCellRender();
		this.returnBooklist.setCellRenderer(returnBookListCellRender);
		this.setBounds(100, 100, 800, 600);
		JScrollPane jScrollPane = new JScrollPane(this.list);
//		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.ALLBITS);
		this.panel.add(jScrollPane,JSplitPane.LEFT);
		this.panel.add(new JScrollPane(this.returnBooklist),JSplitPane.RIGHT);
		panel.setDividerLocation(300);
		panel.setDividerSize(2);
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
