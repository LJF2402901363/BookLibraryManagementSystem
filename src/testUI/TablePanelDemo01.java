package testUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.eltima.components.ui.DatePicker;

import UI.AdminitratorAllImageIcons;
import UI.MyScrollBarUI;
import domain.PageBean;
import service.BaseService;

/**
 * @author 陌意随影
 TODO :测试的TablePanelDemo01
 *2020年3月18日  下午12:01:25
 */
public class TablePanelDemo01<T> extends JPanel {
	private static final long serialVersionUID = 1L;
    private JTable table = null;
    private BaseTableModelDemo<T>  tableModel = null;
    private JPanel searchPanel = null;
    private JPanel tablePanel = null;
    private JPanel buttonPanel = null;
    /*----------搜索组件------------*/
    private JTextField  searchText = null;
    private JButton  searchBtn = null;
    private JButton  clearBtn = null;
    private DatePicker datePickerFrom = null;
    private DatePicker datePickerTo = null;
    /*----------分页组件------------*/
    /**首页按钮*/
    private JButton btn_home = null;
    /**尾页按钮*/
    private JButton btn_end = null;
    /**上一页按钮*/
    private JButton btn_previous = null;
    /**下一页按钮*/
    private JButton btn_next = null;
    /**确认跳转按钮*/
    private JButton btn_ok = null;
    /**分页跳转下拉框*/
    private JComboBox<Integer> pageCombox = null;
    /**每页大小下拉框*/
    private JComboBox<Integer> pageSizeCombox = null;
    private JTextField totalCountText  = null;
    private JTextField totalPagesText = null;
    private PageBean<T> pageBean = null;
	private BaseService<T> baseService = null;
    /**默认的每页的数量     */
    public static final int DEFAULT_PAGESIZE = 15;
    /**分页每页的数量     */
    public   int pageSize = DEFAULT_PAGESIZE;
    @SuppressWarnings("javadoc")
	public TablePanelDemo01(BaseService<T> baseService,BaseTableModelDemo<T>  tableModel) {
    	this.setLayout(new BorderLayout());
    	this.table = new JTable();
    	this.tableModel = tableModel;
    	this.tablePanel = new JPanel();
    	this.searchPanel = new JPanel();
    	this.datePickerFrom = new DatePicker();
    	this.datePickerTo = new DatePicker();
    	this.searchText = new JTextField();
    	this.searchBtn = new JButton("搜索");
    	this.clearBtn = new JButton("清除");
    	btnStyle(searchBtn);
    	btnStyle(clearBtn);
//    	searchPanel.setBorder(BorderFactory.createLineBorder(Color.PINK,2));
    	this.searchPanel.setPreferredSize(new Dimension(530,50));
    	this.tablePanel.setPreferredSize(new Dimension(530,600));
    	this.buttonPanel= new JPanel();
//    	this.buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
//    	this.tablePanel.setBorder(BorderFactory.createLineBorder(Color.gray,2));
    	this.buttonPanel.setPreferredSize(new Dimension(500,50));
    	this.table = new JTable();
    	this.baseService = baseService;
    	initComponents();
    	initComponentEvent();
    	this.add(searchPanel,BorderLayout.NORTH);
    	this.add(tablePanel,BorderLayout.CENTER);
    	this.add(buttonPanel,BorderLayout.SOUTH);
    }
    private void btnStyle(JButton btn) {
    	btn.setBackground(new Color(30,159,255));
		//该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
    }
	private void initComponentEvent() {
		//给首页添加点击事件
		this.btn_home.addActionListener(e->{
			if(pageBean.getCurrPage() == 1) {
				return;
			}
			 pageBean = baseService.fuzzySearchByPage(0, pageSize,searchText.getText(), null,datePickerFrom.getText(), datePickerTo.getText(),null,null);
			 rendDataByPageBean(pageBean);
		});
		//给尾页添加点击事件
		this.btn_end.addActionListener(e->{
			//获取尾页
			int totalPage = pageBean.getTotalPage();
			if(pageBean.getCurrPage() == totalPage) {
				return;
			}
			//计算开始查找的起始位置
			int offset = (totalPage-1) * pageSize;
			if(offset < 0) {
				offset =0;
			}
			 pageBean = baseService.fuzzySearchByPage(offset, pageSize,searchText.getText(),null, datePickerFrom.getText(), datePickerTo.getText(),null,null);
			 rendDataByPageBean(pageBean);
		});
		//给下一页添加点击事件
		this.btn_next.addActionListener(e->{
			//获取当前页码
			int currPage = pageBean.getCurrPage();
			if(currPage > pageBean.getTotalPage()) {
				return;
			}
			//开始查找的起始位置
			int offset =  currPage * pageSize;
			 pageBean = baseService.fuzzySearchByPage(offset, pageSize,searchText.getText(), null,datePickerFrom.getText(), datePickerTo.getText(),null,null);
			 rendDataByPageBean(pageBean);
		});
		// 给上一页添加点击事件
		this.btn_previous.addActionListener(e -> {
			// 获取当前页码
			int currPage = pageBean.getCurrPage();
			// 开始查找的起始位置
			int offset = (currPage-2 )* pageSize;
			if(offset < 0) {
				return;
			}
			 pageBean = baseService.fuzzySearchByPage(offset, pageSize,searchText.getText(), null,datePickerFrom.getText(), datePickerTo.getText(),null,null);
			 rendDataByPageBean(pageBean);
		});
		//给选择每页数据量的添加点击事件
		this.pageSizeCombox.addActionListener(e->{
			initPageSizeCombox();
			 renderTabeData(0, pageSize);
		});
		//给确定跳转页面的按钮添加点击事件
		this.btn_ok.addActionListener(e->{
			//获取选中的页码
			 Object selected = this.pageCombox.getSelectedItem();
			if(selected instanceof Integer) {
				Integer selectedItem = (Integer)selected;
				// 开始查找的起始位置
				int offset = (selectedItem-1 )* pageSize;
				 pageBean = baseService.fuzzySearchByPage(offset, pageSize,searchText.getText(),null, datePickerFrom.getText(), datePickerTo.getText(),null,null);
				 rendDataByPageBean(pageBean);
			}else {
				JOptionPane.showConfirmDialog(this,"请输入要跳转的页码！");
				int totalPages = this.pageBean.getTotalPage();
				//首先清空下拉框的所有元素
				if(this.pageCombox.getItemCount() != 0) {
					this.pageCombox.removeAllItems();
				}
				//重新加入元素
				if(totalPages > 0) {
					for(int i = 0;i < totalPages;i++) {
						this.pageCombox.addItem(new Integer(i+1));
					}
				}
				//设置当前选中的item
				this.pageCombox.setSelectedIndex(pageBean.getCurrPage()-1);
					
			}
			
		});
		//给搜索按钮添加点击事件
		 this.searchBtn.addActionListener(e->{
			 pageBean = baseService.fuzzySearchByPage(0, pageSize,searchText.getText(), null,datePickerFrom.getText(), datePickerTo.getText(),null,null);
			 rendDataByPageBean(pageBean);
		 });
		 //给清除按钮添加点击事件
		 this.clearBtn.addActionListener(e->{
			 searchText.setText("");
			 datePickerTo.setToolTipText("");
			 renderTabeData(0,pageSize);
		 });
	}
	private void initPageSizeCombox() {
		  Integer selectPageSize =	(Integer) pageSizeCombox.getSelectedItem();
		  if(selectPageSize == null) {
			  setPageSize(DEFAULT_PAGESIZE);
		  }else {
			  setPageSize(selectPageSize);
		  }
		 
		//获取总的分页数集合
		 int total = pageBean.getTotal() ;
		  int size = (total + DEFAULT_PAGESIZE - 1 ) / DEFAULT_PAGESIZE;
		  System.out.println("分页数：" + size);
		  pageSizeCombox.removeAllItems();
		  for(int i  = 0 ;i < size;i++) {
			  pageSizeCombox.addItem((i+1) * DEFAULT_PAGESIZE);
		  }
		 pageSizeCombox.setSelectedItem(pageSize);
	}
	private void initComponents() {
		this.table.setModel(tableModel);
		//设置唯一主键的id列不可见
		this.table.getColumn("id").setMaxWidth(0);;
		this.table.getColumn("id").setMinWidth(0);
		this.table.getColumn("id").setPreferredWidth(0);
		this.table.setRowHeight(25);
//		this.table.getColumn("创建时间").setPreferredWidth(160);
//		this.table.getColumn("简介").setPreferredWidth(180);
		this.table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JPanel panel = new JPanel();
	      JScrollPane scrollpane = new JScrollPane(this.table); 
	      scrollpane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
	      scrollpane.getVerticalScrollBar().setUI(new MyScrollBarUI());
	      scrollpane.setOpaque(false);
	      scrollpane.setBorder(BorderFactory.createEmptyBorder());
	      scrollpane.setAutoscrolls(true);
	      scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
	      scrollpane.setPreferredSize(new Dimension(850,350));
	      panel.add(scrollpane);
		this.tablePanel.add(scrollpane);
		initButtons();
		renderTabeData(0, pageSize);
		initPageSizeCombox();
		initSearchComponent();
	}
	private void initSearchComponent() {
    		this.searchText.setPreferredSize(new Dimension(90,20));
    		this.searchBtn.setToolTipText("点击搜索一下吧");
    		this.datePickerFrom.setPreferredSize(new Dimension(150,25));
    		this.datePickerTo.setPreferredSize(new Dimension(150,25));
    		Font font = new Font("微软雅黑",Font.BOLD,14);
    		JLabel lbl = new JLabel("搜索内容");
    		lbl.setFont(font);
    		this.searchPanel.add(lbl);
    		this.searchPanel.add(searchText);
    		JLabel lbl1 = new JLabel("开始时间");
    		lbl1.setFont(font);
    		JLabel lbl2 = new JLabel("结束时间");
    		lbl2.setFont(font);
    		this.clearBtn.setFont(font);
    		this.searchPanel.add(lbl1);
    		this.searchPanel.add(datePickerFrom);
    		this.searchPanel.add(lbl2);
    		this.searchPanel.add(datePickerTo);
    		this.searchPanel.add(searchBtn);
    		this.searchPanel.add(clearBtn);
	}
	/**
	 * 通过其实索引位置和当前页面数据量来获取分页
	 * @param offset
	 * @param pageSize
	 */
	public  void renderTabeData(int offset,int pageSize) {
		this.pageBean = this.baseService.fuzzySearchByPage(offset, pageSize, null, null,null, null, null, null);
		System.out.println(this.pageBean);
		rendDataByPageBean( this.pageBean);
	}
	private void rendDataByPageBean(PageBean<T> pageBean) {
		if(this.pageBean.getTotal() == 0) {
			this.tableModel.removeAllRow();
		}
		this.tableModel.setPageBean(pageBean);
		int totalPages = pageBean.getTotalPage();
		//首先清空下拉框的所有元素
		if(this.pageCombox.getItemCount() != 0) {
			this.pageCombox.removeAllItems();
		}
		
		//重新加入元素
		if(totalPages > 0) {
			for(int i = 0;i < totalPages;i++) {
				this.pageCombox.addItem(new Integer(i+1));
			}
			//设置当前选中的item
			if(this.pageCombox.getItemCount() >0 ) {
				this.pageCombox.setSelectedIndex(pageBean.getCurrPage()-1);
			}
		}else if(totalPages ==0) {
			this.pageCombox.addItem(new Integer(0));
		}
		
		//设置总数据量
		 totalCountText.setText(pageBean.getTotal()+"");
		 //设置总页数
		 totalPagesText.setText(totalPages+"");
		 //获取当期页
		 if(pageBean.getCurrPage() == totalPages ) {
			 //当处在最后一页的时候下一页的按钮不可点击
			 this.btn_next.setEnabled(false);
		 }else {
			 //当不是处在最后一页的时候下一页的按钮可点击
			 this.btn_next.setEnabled(true);
		 }
		 if(pageBean.getCurrPage() == 1 ) {
			 //当处在第一页的时候上一页的按钮不可点击
			 this.btn_previous.setEnabled(false);
		 }else {
			 //当不是处在第一页的时候上一页的按钮可点击
			 this.btn_previous.setEnabled(true);
		 }
	}
	
	private void initButtons() {
		this.btn_home = new JButton("首页");
		this.btn_previous = new JButton(AdminitratorAllImageIcons.BTN_PREVIOUS);
		btnStyle(btn_home);
		 Font font = new Font("微软雅黑",Font.BOLD,14);
		JLabel lbl = new JLabel("共");
		lbl.setFont(font);
		 totalCountText = new JTextField();
		 totalCountText.setEditable(false);
		 totalCountText.setPreferredSize(new Dimension(25,20));
		JLabel lbl0 = new JLabel("条");
		lbl0.setFont(font);
		totalPagesText = new JTextField();
		totalPagesText.setEditable(false);
		totalPagesText.setPreferredSize(new Dimension(25,20));
		JLabel lbl3 = new JLabel("页");
		//每页数据量组件
		JLabel lbl4 = new JLabel("每页");
		this.pageSizeCombox = new JComboBox<Integer>();
		pageSizeCombox.setPreferredSize(new Dimension(50,25));
		this.pageSizeCombox.setEditable(false);
		JLabel lbl5 = new JLabel("条");
		lbl3.setFont(font);
		lbl4.setFont(font);
		lbl5.setFont(font);
		JLabel lbl1 = new JLabel("到第");
		JLabel lbl2 = new JLabel("页");
		lbl2.setFont(font);
		this.pageCombox = new JComboBox<>();
		this.pageCombox.setEditable(true);
		pageCombox.setPreferredSize(new Dimension(50,25));
		this.btn_ok = new JButton("确定");
		btnStyle(btn_ok);
		this.btn_next = new JButton(AdminitratorAllImageIcons.BTN_NEXT);
		this.btn_end = new JButton("尾页");
		btnStyle(btn_end);
		btn_next.setPreferredSize(new Dimension(25,25));
		btn_previous.setPreferredSize(new Dimension(25,25));
		this.buttonPanel.add(btn_home);
		this.buttonPanel.add(btn_previous);
		this.buttonPanel.add(lbl);
		this.buttonPanel.add(totalCountText);
		this.buttonPanel.add(lbl0);
		this.buttonPanel.add(totalPagesText);
		this.buttonPanel.add(lbl3);
		this.buttonPanel.add(lbl4);
		this.buttonPanel.add(pageSizeCombox);
		this.buttonPanel.add(lbl5);
		this.buttonPanel.add(lbl1);
		this.buttonPanel.add(pageCombox);
		this.buttonPanel.add(lbl2);
		this.buttonPanel.add(btn_ok);
		this.buttonPanel.add(btn_next);
		this.buttonPanel.add(btn_end);
		
	}
	/**
	 * @return JTable
	 */
	public JTable getTable() {
		return table;
	}
	/**
	 * @return tableModel
	 */
	public BaseTableModelDemo<T> getTableModel() {
		return tableModel;
	}
	/**
	 * @return  返回表格所有的行数
	 */
	public int getAllRowCount() {
		return this.pageBean.getTotal();
	}
	/**
	 * @return  返回当前分页数
	 */
	public  int getPageSize() {
		if(this.pageSize == 0) {
			this.pageSize = DEFAULT_PAGESIZE;
		}
		return this.pageSize;
	}
	/**
	 * @param pageSize 设置分页数
	 */
	public  void setPageSize(int pageSize) {
			this.pageSize = pageSize;
	}
	
}
