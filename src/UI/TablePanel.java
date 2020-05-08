package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

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

import domain.PageBean;
import domain.SysLog;
import model.BaseTableModel;
import model.TableGonggeModel;
import service.BaseService;

/**
 * @author 陌意随影
 TODO :table的基准父类
 *2020年2月28日  下午12:01:25
 */
public class TablePanel<T> extends JPanel {
	private static final long serialVersionUID = 1L;
    private JPanel searchPanel = null;
    private JPanel tablePanel = null;
    private JPanel buttonPanel = null;
    private JScrollPane scrollPane = null;
    /*----------搜索组件------------*/
    protected JTextField  searchText = null;
    protected JButton  searchBtn = null;
    protected JButton  clearBtn = null;
    protected JComboBox<String> searchCombox =null;
    protected DatePicker datePickerFrom = null;
    protected DatePicker datePickerTo = null;
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
    /**列表模式下拉框*/
    protected JComboBox<String> tableTyeCombox = null;
    protected static final String[] tableTypes = {"列表模式" ,"九宫格模式"  };
    /**分页跳转下拉框*/
    private JComboBox<Integer> pageCombox = null;
    /**每页大小下拉框*/
    private JComboBox<Integer> pageSizeCombox = null;
    private JTextField totalCountText  = null;
    private JTextField totalPagesText = null;
    protected PageBean<T> pageBean = null;
	protected BaseService<T> baseService = null;
    /**默认第一页的数量     */
    public static final int DEFAULT_FIRST_PAGESIZE = 12;
    /**第一页的数据量*/
    private int firstPageSize  =  DEFAULT_FIRST_PAGESIZE;
    /**当前分页的数量     */
    public   int currPageSize = DEFAULT_FIRST_PAGESIZE;
    /*-----默认的组件的尺寸-----------*/
    /**默认的搜索框部分的尺寸*/
    public static final Dimension SEARCHPANLDIMENSION = new Dimension(550,45);
    /**默认的表格主体部框部分的尺寸*/
    public static final Dimension TABLEPANELDIMENSION = new Dimension(550,650);
    /**默认的地步分页组件的尺寸*/
    public static final Dimension BUTTOMDIMENSION = new Dimension(550,45);
    /**默认的表格滚动面板的大小     */
    public static final Dimension TABLESCROLLPANEDIMENSION = new Dimension((int)TABLEPANELDIMENSION.getWidth()+40,(int)TABLEPANELDIMENSION.getHeight()+40);
    /*-----------------表格模式----------------------*/
    protected JTable table = null;
    /**当前的列表模式*/
    protected BaseTableModel<T>  currTableModel = null;
    /**列表模式*/
    protected BaseTableModel<T>  listModel = null;
    /**宫格模式*/
    protected BaseTableModel<T>  gonggeModel = null;
    @SuppressWarnings("javadoc")
	public TablePanel(BaseService<T> baseService,JTable table,BaseTableModel<T>  tableModel) {
    	this.setLayout(new BorderLayout());
    	this.table = table;
    	this.gonggeModel = new TableGonggeModel<>();
    	this.currTableModel = tableModel;
    	this.listModel = tableModel;
    	this.table.setModel(tableModel);
    	this.scrollPane = new JScrollPane();
    	this.tablePanel = new JPanel();
    	this.searchPanel = new JPanel();
    	this.datePickerFrom = new DatePicker();
		this.datePickerTo = new DatePicker();
		this.searchText = new JTextField();
		this.searchBtn = new JButton("搜索");
		this.clearBtn = new JButton("清除");
		this.searchCombox = new JComboBox<>();
    	this.buttonPanel= new JPanel();
		this.setTablePanelDimension(TABLEPANELDIMENSION);
		this.setButtomDimension(BUTTOMDIMENSION);
		this.setTableScrollPaneDimension(TABLESCROLLPANEDIMENSION);
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
    private void initComponents() {
		JPanel panel = new JPanel();
		scrollPane.getViewport().add(table);
		scrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(TABLEPANELDIMENSION);
		panel.add(scrollPane);
		this.tablePanel.add(scrollPane);
		initSearchComponent();
		initButtons();
		renderTableData(0, currPageSize);
		initPageSizeCombox();
	}
	
	private void initPageSizeCombox() {
		  Integer selectPageSize =	(Integer) pageSizeCombox.getSelectedItem();
		  if(selectPageSize == null) {
			  setPageSize(getFirstPageSize());
		  }else {
			  setPageSize(selectPageSize);
		  }
		 
		//获取总的分页数集合
		 int total = pageBean.getTotal() ;
		  int size = (total + this.firstPageSize - 1 ) / this.firstPageSize;
		  pageSizeCombox.removeAllItems();
		  for(int i  = 0 ;i < size;i++) {
			  pageSizeCombox.addItem((i+1) * this.firstPageSize);
		  }
		 pageSizeCombox.setSelectedItem(currPageSize);
	}
	
	protected void initSearchComponent() {
		this.setSearchPanelDimension(SEARCHPANLDIMENSION);
		btnStyle(searchBtn);
		btnStyle(clearBtn);
		this.searchText.setPreferredSize(new Dimension(90, 20));
		this.searchBtn.setToolTipText("点击搜索一下吧");
		this.datePickerFrom.setPreferredSize(new Dimension(150, 25));
		this.datePickerTo.setPreferredSize(new Dimension(150, 25));
		Font font = new Font("微软雅黑", Font.BOLD, 14);
		JLabel lbl = new JLabel("搜索内容");
		lbl.setFont(font);
		JLabel lbl1 = new JLabel("开始时间");
		lbl1.setFont(font);
		JLabel lbl2 = new JLabel("结束时间");
		lbl2.setFont(font);
	     showSearchComBoxData();
		this.searchCombox.setPreferredSize(new Dimension(80,30));
		this.searchPanel.add(searchCombox);
		this.searchPanel.add(lbl);
		this.searchPanel.add(searchText);
		this.clearBtn.setFont(font);
		this.searchPanel.add(lbl1);
		this.searchPanel.add(datePickerFrom);
		this.searchPanel.add(lbl2);
		this.searchPanel.add(datePickerTo);
		this.searchPanel.add(searchBtn);
		this.searchPanel.add(clearBtn);
	}
	protected void showSearchComBoxData() {
		//默认的搜索ComBox为不可见
		this.searchCombox.setVisible(false);
		
	}
	/**
	 * 通过其实索引位置和当前页面数据量来获取分页
	 * @param offset
	 * @param pageSize
	 */
	public  void renderTableData(int offset,int pageSize) {
		Map<String,String> condition = new HashMap<>();
		String conditiontext =(String) this.searchCombox.getSelectedItem();
		if("全部".equalsIgnoreCase(conditiontext)) {
			conditiontext=null;
		}
		condition.put("operationType", conditiontext);
		this.pageBean = this.baseService.fuzzySearchByPage(offset, pageSize, this.searchText.getText(),condition, this.datePickerFrom.getText(), this.datePickerTo.getText(), null, null);
		rendDataByPageBean();
	}
	private void rendDataByPageBean() {
		
		if(this.pageBean.getTotal() == 0) {
			this.currTableModel.removeAllRow();
		}
		
		this.currTableModel.setPageBean(pageBean);
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
		this.tableTyeCombox = new JComboBox<>(tableTypes);
		tableTyeCombox.setPreferredSize(new Dimension(80,25));
		this.tableTyeCombox.setSelectedItem(tableTypes[0]);
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
		this.buttonPanel.add(tableTyeCombox);
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
	private void initComponentEvent() {
		//初始化表格模型事件
		initTableModelComBoxEvent();
		//给首页添加点击事件
		this.btn_home.addActionListener(e->{
			if(pageBean.getCurrPage() == 1) {
				return;
			}
			renderTableData(0, currPageSize);
		});
		//给尾页添加点击事件
		this.btn_end.addActionListener(e->{
			//获取尾页
			int totalPage = pageBean.getTotalPage();
			if(pageBean.getCurrPage() == totalPage) {
				return;
			}
			//计算开始查找的起始位置
			int offset = (totalPage-1) * currPageSize;
			if(offset < 0) {
				offset =0;
			}
			 renderTableData(offset, currPageSize);
		});
		//给下一页添加点击事件
		this.btn_next.addActionListener(e->{
			//获取当前页码
			int currPage = pageBean.getCurrPage();
			if(currPage > pageBean.getTotalPage()) {
				return;
			}
			//开始查找的起始位置
			int offset =  currPage * currPageSize;
			 renderTableData(offset, currPageSize);
		});
		// 给上一页添加点击事件
		this.btn_previous.addActionListener(e -> {
			// 获取当前页码
			int currPage = pageBean.getCurrPage();
			// 开始查找的起始位置
			int offset = (currPage-2 )* currPageSize;
			if(offset < 0) {
				return;
			}
			 renderTableData(offset, currPageSize);
		});
		//给选择每页数据量的添加点击事件
		this.pageSizeCombox.addActionListener(e->{
			initPageSizeCombox();
			 renderTableData(0, currPageSize);
		});
		//给确定跳转页面的按钮添加点击事件
		this.btn_ok.addActionListener(e->{
			//获取选中的页码
			 Object selected = this.pageCombox.getSelectedItem();
			if(selected instanceof Integer) {
				Integer selectedItem = (Integer)selected;
				if(selectedItem == pageBean.getCurrPage()) {
					return;
				}
				if(selectedItem > pageBean.getTotalPage() || selectedItem <= 0) {
					JOptionPane.showMessageDialog(this, "您选择的页码超出范围！");
					return;
				}
				
				// 开始查找的起始位置
				int offset = (selectedItem-1 )* currPageSize;
				 renderTableData(offset, currPageSize);
			}else {
				JOptionPane.showConfirmDialog(this,"请输入正整数的要跳转的页码！");
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
		initSearPanelEvent();
	}
	protected void initSearPanelEvent() {
		//给搜索按钮添加点击事件
		 this.searchBtn.addActionListener(e->{
			 renderTableData(0, currPageSize);
		 });
		 //给清除按钮添加点击事件
		 this.clearBtn.addActionListener(e->{
			 searchText.setText("");
			 datePickerTo.setToolTipText("");
			 renderTableData(0,currPageSize);
		 });
		 this.searchCombox.addActionListener(e->{
			 String  selectedItem = (String) searchCombox.getSelectedItem();
			 if("全部".equalsIgnoreCase(selectedItem)) {
				 selectedItem=null;
			 }
			 renderTableData(0, getPageSize());
		 });
	}
	
	protected void initTableModelComBoxEvent() {
		
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
	public BaseTableModel<T> getTableModel() {
		return currTableModel;
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
		return this.currPageSize;
	}
	/**
	 * @param pageSize 设置分页数
	 */
	public  void setPageSize(int pageSize) {
			this.currPageSize = pageSize;
	}
	/**
	 * 设置搜索面板的尺寸
	 * @param dimension
	 */
	public void setSearchPanelDimension(Dimension dimension ) {
		this.searchPanel.setPreferredSize(dimension);
	}
	/**
	 * 设置表格主体的尺寸
	 * @param dimension
	 */
	public void setTablePanelDimension(Dimension dimension ) {
		this.tablePanel.setPreferredSize(dimension);
	}
	/**
	 * 设置分页组件的尺寸
	 * @param dimension
	 */
	public void setButtomDimension(Dimension dimension ) {
		this.buttonPanel.setPreferredSize(dimension);
	}
	/**
	 * 设置表格滚动框的尺寸
	 * @param dimension
	 */
	public void setTableScrollPaneDimension(Dimension dimension ) {
		this.scrollPane.setPreferredSize(dimension);
	}
	/**
	 * @return  返回表格起始页数据量大小
	 */
	public int getFirstPageSize() {
		if(firstPageSize <= 0) {
			this.firstPageSize = DEFAULT_FIRST_PAGESIZE;
		}
		return firstPageSize;
	}
	/**
	 * @param firstPageSize 设置起始页数据量大小
	 */
	public void setFirstPageSize(int firstPageSize) {
		if(firstPageSize <= 0) {
			this.firstPageSize = DEFAULT_FIRST_PAGESIZE;
		}
		this.firstPageSize = firstPageSize;
		initPageSizeCombox();
	}
	
}
