package UI;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import domain.Account;
import domain.BorrowBook;
import factory.ServiceFactory;
import render.BorrowBookListCellRender;
import render.ReturnBookListCellRender;
import service.AdministratortService;
import service.BookCateService;
import service.BookService;
import service.BorrowBookRecordService;
import service.SysLogService;

/**
 * @author 陌意随影
 TODO :主页面板
 *2020年3月17日  下午8:25:40
 */
public class HomePanel extends JPanel  {
	private static final long serialVersionUID = 1L;
    private JPanel homesPnael = null;
    private JPanel bottomPanel = null;
    private final static int WEIHT = 150;
    private final static int HRIGHT = 100;
    private static final Color[] colors = {new Color(10,173,237),new Color(238,171,80),new Color(216,83,79)} ;
    private BookService bookService = null;
    private BookCateService bookCateService = null;
    private AdministratortService accountService = null;
    private BorrowBookRecordService borrowBookService  = null;
    private SysLogService sysLogService = null;
    private Account administrator = null;
    private DefaultListModel<BorrowBook>  borrowBookmodel = null;
    private DefaultListModel<BorrowBook>  returnBookmodel = null;
	private BorrowBookListCellRender borrowBookRender = null;
	private ReturnBookListCellRender returnBookListCellRender = null;
	private JList<BorrowBook> returnBooklist = null;
	private JList<BorrowBook> borrowBooklist = null;
	private List<BorrowBook>  borrowBookData = null;
	private List<BorrowBook>  returnBookData = null;
	private BorrowBookRecordService service = null;
    private  AdministratorFrame administratorFrame = null;
    private JComboBox<String> combox = null;
    private final static Map<String,Integer> dateTimeMap = new HashMap<>();
    private  final static String[] dateStr =  { "1天之内的动态","3天之内的动态","7天之内的动态","30天之内的动态","90天之内的动态","180天之内的动态","365天之内的动态","所有动态"}; 
    private int currIndex = -1;
    private static  Map<String,String> map = new HashMap<>();
     static {
    	 map.put("图书类别", "分类管理");
    	 map.put("读者", "读者管理");
    	 map.put("图书", "图书管理");
    	 map.put("管理员", "管理员管理");
    	 map.put("今日借书", "借书管理");
    	 map.put("今日还书", "还书管理");
    	 map.put("今日新增读者", "读者管理");
    	 map.put("今日删除读者", "日志管理");
    	 dateTimeMap.put(dateStr[0], 1);
    	 dateTimeMap.put(dateStr[1], 3);
    	 dateTimeMap.put(dateStr[2], 17);
    	 dateTimeMap.put(dateStr[3], 30);
    	 dateTimeMap.put(dateStr[4], 90);
    	 dateTimeMap.put(dateStr[5], 180); 
    	 dateTimeMap.put(dateStr[6], 365);
    	 
     }
	@SuppressWarnings("javadoc")
	public HomePanel(Account administrator,AdministratorFrame administratorFrame) {
		this.administrator = administrator;
		this.administratorFrame = administratorFrame;
		this.setBackground(new Color(255,255,255));
		this.setLayout(new BorderLayout());
		GridLayout gridLayout = new GridLayout(2,4);
		gridLayout.setHgap(25);
		gridLayout.setVgap(20);
		this.homesPnael = new JPanel(gridLayout);
	    this.bottomPanel = new JPanel();
	    homesPnael.setPreferredSize(new Dimension(800,200));
	    bottomPanel.setPreferredSize(new Dimension(800,400));
	    this.service = (BorrowBookRecordService) ServiceFactory.newInstanceService("BorrowBookService");
	    this.returnBookmodel=new DefaultListModel<>();
		this.borrowBookmodel = new DefaultListModel<>();
		this.borrowBookRender = new BorrowBookListCellRender();
		this.borrowBooklist = new JList<BorrowBook>();
		this.returnBooklist = new JList<BorrowBook>();
		this.borrowBooklist.setModel(borrowBookmodel);
		this.returnBooklist.setModel(returnBookmodel);
		this.borrowBooklist.setCellRenderer(borrowBookRender);
		this.returnBookListCellRender = new ReturnBookListCellRender();
		this.returnBooklist.setCellRenderer(returnBookListCellRender);
		 this.combox = new JComboBox<String> (dateStr);
		 this.combox.setSelectedIndex(0);
	    this.add(homesPnael,BorderLayout.CENTER);
	    this.add(bottomPanel,BorderLayout.SOUTH);
	    this.bookCateService =(BookCateService) ServiceFactory.newInstanceService("BookCateService");
	    this.bookService = (BookService) ServiceFactory.newInstanceService("BookService");
	    this.accountService =  (AdministratortService) ServiceFactory.newInstanceService("AdministratortService");
	    this.borrowBookService =(BorrowBookRecordService) ServiceFactory.newInstanceService("BorrowBookService");
	    this.sysLogService =(SysLogService) ServiceFactory.newInstanceService("SysLogService");
	    initHomePanel();
	    initBottomPanel();
	    initComBoxEvent();
	    reflushData();
	    
	    this.setPreferredSize(new Dimension(870,600));
	    
	}
	private void initPanelEvent(JPanel panel,String text) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(Objects.nonNull(administratorFrame)) {
				administratorFrame.createNavCenterLabel(map.get(text));
			}
			}
		    	@Override
		    	public void mouseEntered(MouseEvent e) {
		    		panel.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
		    	}
		    	@Override
		    	public void mouseExited(MouseEvent e) {
		    		panel.setBorder(null);
		    	}
			});
		  
		    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
				@Override
				public void eventDispatched(AWTEvent event) {
					Object source = event.getSource();
			        if (source instanceof JComponent) {
			            JComponent comp = (JComponent) source;
			            if (SwingUtilities.isDescendingFrom(comp, panel)) {
			            	panel.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
			            }else {
			            	panel.setBorder(null);
			            }
			        }
				}
			}, AWTEvent.MOUSE_EVENT_MASK);
	
}
	private void initBottomPanel() {
		FlowLayout layout = new FlowLayout();
		layout.setHgap(15);
      	JPanel panel = new JPanel(layout);
      	JLabel lbl = new JLabel("最新动态");
    	panel.setBackground(new Color(255,184,0));
      	lbl.setFont(new Font("微软雅黑",Font.BOLD,18));
      	lbl.setForeground(Color.white);
      	panel.setPreferredSize(new Dimension(600,40));
        this.combox.setPreferredSize(new Dimension(140,30));
        combox.setBorder(null);
        combox.setBackground(Color.WHITE);
        combox.setFont(new Font("微软雅黑",Font.BOLD,14));
      	panel.add(lbl);
      	panel.add(combox);
      	JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerSize(3);
      	JScrollPane jScrollPane1 = new JScrollPane(borrowBooklist);
      	jScrollPane1.getVerticalScrollBar().setUI(new MyScrollBarUI());
      	jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.ALLBITS);
    	jScrollPane1.getHorizontalScrollBar().setUI(new MyScrollBarUI());
      	splitPane.add(jScrollPane1, JSplitPane.LEFT);
      	JScrollPane  jScrollPane2= new JScrollPane(returnBooklist);
      	jScrollPane2.getVerticalScrollBar().setUI(new MyScrollBarUI());
      	jScrollPane2.getHorizontalScrollBar().setUI(new MyScrollBarUI());
      	jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.ALLBITS);
      	splitPane.add(jScrollPane2, JSplitPane.RIGHT);
      	this.bottomPanel.add(panel);
    	this.bottomPanel.add(splitPane);
    	
	}
	private void initComBoxEvent() {
	  this.combox.addActionListener(e->{
		 reflushData();
	  });
		
	}
	private void reflushData() {
		int selectedIndex = combox.getSelectedIndex();
		 if(selectedIndex == currIndex) {
			 //如果当前选择的索引和之前的索引一致则不用刷新
			 return;
		 }
		 currIndex = selectedIndex;
		 //获取代表的时间日期
		 String itemAt = combox.getItemAt(selectedIndex);
		//获取对应的日期天数
		 Integer days = dateTimeMap.get(itemAt);
		 //获取起始的日期
		 String toDateStr = "";
		  String fromDateStr = "";
		 if(itemAt.equals(dateStr[dateStr.length -1])) {
			 //获取所有的动态
			 toDateStr = null;
			 fromDateStr = null;
		 }else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.now();
			toDateStr = formatter.format(localDateTime);
			LocalDateTime minusDays = localDateTime.minusDays(days);
			fromDateStr = formatter.format(minusDays);
		 }
			this.borrowBookData = borrowBookService.getAllBorrowBookRecord(fromDateStr, toDateStr);
			this.returnBookData =borrowBookService.getAllReturnBookRecord(fromDateStr, toDateStr); 
			borrowBookmodel.removeAllElements();
			returnBookmodel.removeAllElements();
			for (BorrowBook element : borrowBookData) {
				borrowBookmodel.addElement(element);
			}
			for (BorrowBook element : returnBookData) {
				returnBookmodel.addElement(element);
			}
	}
	private JPanel createPane(String name,String value) {
		GridLayout gridLayout = new GridLayout(2,1);
		JPanel panel  = new JPanel(gridLayout);
		JLabel lbl_name = new JLabel(name);
		JLabel lbl_value = new JLabel(value);
		JPanel namePanel = new JPanel();
		namePanel.add(lbl_name);
		namePanel.setOpaque(false);
		JPanel valuePanel = new JPanel();
		valuePanel.setOpaque(false);
		valuePanel.add(lbl_value);
		lbl_name.setFont(new Font("微软雅黑",Font.BOLD,22));
		lbl_value.setFont(new Font("微软雅黑",Font.PLAIN,18));
		panel.setPreferredSize(new Dimension(WEIHT,HRIGHT));
		Random  r = new Random();
		panel.setBackground(colors[r.nextInt(colors.length)]);
		panel.add(namePanel);
		panel.add(valuePanel);
		initPanelEvent(panel,name);
		return panel;
	}
	private  String getTodayTimeStr() {
		 LocalDate date =LocalDate.now();
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 String dateStr = format.format(date);
		 dateStr = dateStr +" 00:00:00";
		return dateStr;
		
	}
	private void initHomePanel() {
		JPanel catePanel =createPane("图书类别",bookCateService.getAllBookCateCount()+"种");	
      	this.homesPnael.add(catePanel);
    	JPanel bookPanel =createPane("图书",bookService.getAllBookCount()+"本");	
      	this.homesPnael.add(bookPanel);
    	JPanel readerPanel =createPane("读者",accountService.getAllReaderCount()+"个");	
      	this.homesPnael.add(readerPanel);
    	JPanel administratorPanel =createPane("管理员",accountService.getAllAdministratorCount()+"个");	
      	this.homesPnael.add(administratorPanel);
    	JPanel borrowBookPanel =createPane("今日借书",this.borrowBookService.getAllBorrowBookRecordCount(getTodayTimeStr(), null)+"本");	
      	this.homesPnael.add(borrowBookPanel);
    	JPanel returnBookPanel =createPane("今日还书",this.borrowBookService.getAllReturnBookRecordCount(getTodayTimeStr(), null)+"本");	
      	this.homesPnael.add(returnBookPanel);
    	JPanel addReaderPanel =createPane("今日新增读者",this.sysLogService.getAllNewAccountCount(getTodayTimeStr(), null)+"个");	
      	this.homesPnael.add(addReaderPanel);
    	JPanel deleteReaderPanel =createPane("今日删除读者",this.sysLogService.getAllDelAccountCount(getTodayTimeStr(), "")+"个");	
      	this.homesPnael.add(deleteReaderPanel);
	}

}
