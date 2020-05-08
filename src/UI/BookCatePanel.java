package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import domain.BookCate;
import editor.BookCateTableEditor;
import editor.BookCateTreeEditor;
import factory.ServiceFactory;
import render.BookCateTreeCellRender;
import service.BookCateService;
import util.TableDataOutputToExcel;

/**
 * @author 陌意随影
 TODO :图书分类面板
 *2020年3月27日  下午5:39:34
 */

public class BookCatePanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private JTree tree = null;
    private  JTable table = null;
    private BookCateTableModel tableModel = null;
    private JPanel leftPanel = null;
    private JPanel rightPanel = null;
    private JPanel btnPanel = null;
    private JSplitPane splitPane = null;
    /**---------按钮组件-------------*/
    private JButton btn_add = null;
    private JButton btn_update = null;
    private JButton btn_remove = null;
    private JButton btn_out = null;
//    private JButton btn_search = null;
    private JButton pop_btn_add = null;
    private JButton pop_btn_update = null;
    private JButton pop_btn_remove = null;
    private JButton pop_out_currPage = null;
    private  JButton pop_out_selected = null;
    private JButton pop_out_all = null;
    private JPopupMenu popMenu = null;
    /**---------其他-------------*/
    private BookCateService bookCateService = null;
    private  TreePath currSelectionPath = null;
    private   DefaultTreeModel treeModel = null;
    List<BookCate> dataList = null;
    private  TreePath currPath = null;
@SuppressWarnings("javadoc")
public BookCatePanel() {
	this.table = new JTable();
	 this.tableModel = new BookCateTableModel();
	 this.tree =new JTree();
	 this.leftPanel = new JPanel(new BorderLayout());
	 this.rightPanel = new JPanel(new BorderLayout());
	 this.btnPanel = new JPanel();
	 this.splitPane = new JSplitPane();
	 this.btn_add = new JButton("增加");
	 this.btn_update = new JButton("修改");
	 this.btn_remove = new JButton("删除选中");
	 this.pop_btn_add = new JButton("增加");
	 this.pop_btn_update = new JButton("修改");
	 this.pop_btn_remove = new JButton("删除");
//	 this.btn_search = new JButton("查询");
	 this.btn_out = new JButton("导出");
	 this.pop_out_currPage = new JButton("导出当前页数据");
	 this.pop_out_all = new JButton("导出所有的数据");
	 this.pop_out_selected = new JButton("导出选中的数据");
	 this.popMenu = new JPopupMenu();
	 this.bookCateService =(BookCateService) ServiceFactory.newInstanceService("BookCateService");;
//	 this.setDividerLocation(0.5);
	 this.splitPane.setDividerLocation(240);
	 initSplitPanel();
	 this.setLayout(new BorderLayout());
	 this.add(splitPane,BorderLayout.CENTER);
	 this.rightPanel.add(btnPanel,BorderLayout.SOUTH);
}
private void initSplitPanel() {
	//左边面板
	 initTree();
	 initTreeBtnPanel();
	 initTreeBtnEvent();
	 //右边面板
	 initTable();
	 initTableBtnPanel();
	 initTableBtnEvent();
	 initOutDataBtnEvent();
	this.splitPane.add(leftPanel, JSplitPane.LEFT);
	this.splitPane.add( this.rightPanel, JSplitPane.RIGHT );
}
private void initOutDataBtnEvent() {
	this.pop_out_currPage.addActionListener(e->{
		 outPutDataBtnEvents(table);
	});
	this.pop_out_selected.addActionListener(e->{
		
		  int selectedRowCount = table.getSelectedRowCount();
		  if(selectedRowCount == 0) {
			  JOptionPane.showMessageDialog(null, "您尚未选择导出的数据!");
			  return;
		  }
		  int[] selectedRows = table.getSelectedRows();
		   JTable  selectedTable = new JTable();
			BookCateTableModel selectedModel = new BookCateTableModel();
			List<BookCateTreeNode> list = new ArrayList<>();
			List<BookCateTreeNode> list2 = tableModel.getList();
			for(int i = 0;i <selectedRowCount;i++ ) {
				 int id = (int) tableModel.getValueAt(selectedRows[i], 1);
			   for(int j = 0;j < list2.size();j++) {
				   BookCateTreeNode bookCateNode = list2.get(j);
				   if(bookCateNode.getNode().getId() == id) {
					   list.add(bookCateNode);
					   break;
				   }
			   }
			}
			selectedModel.setBookCateNodeList(list);
			selectedTable.setModel(selectedModel);
		   outPutDataBtnEvents(selectedTable);
	});
	this.pop_out_all.addActionListener(e->{
		List<BookCate> dataList = bookCateService.getAllBookCate();
		 if(dataList == null || dataList.size() == 0) {
			 JOptionPane.showMessageDialog(null, "没有可导出的数据!");
			 return;
		 }
		
		 JTable  selectedTable = new JTable();
			BookCateTableModel selectedModel = new BookCateTableModel();
			List<BookCateTreeNode> list = new ArrayList<>();
			 for(int i = 0;i < dataList.size();i++) {
				 BookCateTreeNode node =new BookCateTreeNode();
				 node.setNode(dataList.get(i));
				 list.add(node);
			 }
			selectedModel.setBookCateNodeList(list);
			selectedTable.setModel(selectedModel);
		   outPutDataBtnEvents(selectedTable);
	});
	
	
}
/**
 * 给导出数据的按钮添加事件
 * @param btn
 * @param table
 */
private void outPutDataBtnEvents(JTable table) {
	    if(table.getRowCount() == 0) {
	    	JOptionPane.showMessageDialog(null, "请选择导出的数据!");
	    	return;
	    }
		FileDialog fileDialog = new FileDialog(new JFrame(), "请选择导出数据的路径", FileDialog.SAVE);
		fileDialog.setVisible(true);
		// 获取选择的文件夹路径
		String directory = fileDialog.getDirectory();
		// 获取选择的文件名
		String fileName = fileDialog.getFile();
		if (fileName == null || fileName.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "输入的文件名不能为空，请重新输入！");
			return;
		}
		// 完整的文件输出路径
		String outputPath = directory + fileName + ".xlsx";
		// 开始导出数据
		boolean fla = TableDataOutputToExcel.tableDataOutputToExcel(table, outputPath, false);
		if (fla) {
			JOptionPane.showMessageDialog(this, "导出数据成功！");
		} else {
			JOptionPane.showMessageDialog(this, "导出数据失败！");
		}
}
/**
 * 初始化Tree的按钮事件
 */
private void initTreeBtnEvent() {
	this.pop_btn_add.addActionListener(e->{
		if(currSelectionPath != null) {
			Object lastPathComponent = currSelectionPath.getLastPathComponent();
			if(lastPathComponent instanceof BookCateTreeNode) {
				 BookCateTreeNode currNode = (BookCateTreeNode) lastPathComponent;
				 String name = JOptionPane.showInputDialog(this, "请输入图书类别名字：");
				 if(name != null  && name.trim().length() != 0 ) {
						//在父节点上添加
						 int result = JOptionPane.showConfirmDialog(this, "确定添加？");
						 if(result != JOptionPane.OK_OPTION) {
							 return;
						 }
						 BookCate bookCate = new BookCate();
						 bookCate.setName(name);
						 //设置新图书类别的父类名称
						 bookCate.setParentTypeCode(currNode.getNode().getName());
						 bookCate.setCreateTime(new Date());
						 boolean fla = bookCateService.saveBookCate(bookCate);
						 if(fla) {
							 BookCateTreeNode newNode = new BookCateTreeNode();
							 newNode.setNode(bookCate);
							 //设置新添加的节点的层级数，比父类节点的层级数多1
							 newNode.setLayerIndex(currNode.getLayerIndex()+1);
							 //设置关系
							 newNode.setParent(currNode);
							 currNode.addAllChild(newNode);
							 //重新加载
							 treeModel.reload();
							 tableModel.fireTableDataChanged();
						 }else {
							 //由于图书类型名是唯一的，所以如果保存失败则说明该类姓名已存在
							 JOptionPane.showMessageDialog(this, "该图书类别名已存在，请重新输入！");
							 return;
						 }
						
				 }else {
					 JOptionPane.showMessageDialog(this, "您未输入图书类别名！");
				 }
				 
			 }
	
			
		}else {
			JOptionPane.showMessageDialog(null, "请先选择一个一个节点作为父节点!");
		}
	});
	this.pop_btn_update.addActionListener(e->{
		if(currSelectionPath == null) {
			JOptionPane.showMessageDialog(null, "请先选择一个修改的节点!");
			return;
		}
		    Object lastPathComponent = currSelectionPath.getLastPathComponent();
		    if(lastPathComponent instanceof BookCateTreeNode) {
		    	BookCateTreeNode boNode = (BookCateTreeNode) lastPathComponent;	
		    	BookCate bookCate = boNode.getNode();
		    	String oldName = bookCate.getName();
		    	String newName = JOptionPane.showInputDialog("当前选择的节点类型名为："+oldName+"\n请输入新的图书类型名:");
		    	if(newName == null || newName.trim().length() == 0) {
		    		JOptionPane.showMessageDialog(null, "您输入为空!");
		    		return;
		    	}
		    	if(oldName.equals(newName)) {
		    		JOptionPane.showMessageDialog(null, "新的类型名和原来的类型名一致，未进行修改!");
		    	  return;
		    	}
		    	int result = JOptionPane.showConfirmDialog(null, "是否确认修改？");
		    	if(result != JOptionPane.OK_OPTION) {
		    		return;
		    	}
		    	//设置新的名称
		    	bookCate.setName(newName);
		    	boolean fla = bookCateService.updateBookCate(bookCate);
		    	if(fla) {
		    		  //更新成功，判定是否是父节点，如果是父节点，则需要把子节点的所有ParentTypeCode修改
					  if(boNode.hasChildren()) {
						  //如果是父节点，首先获取所有的子节点修改子节点的父节点
						  List<BookCateTreeNode> allChildren = boNode.getAllChildren();
						  for(BookCateTreeNode bookCateTreeNode :allChildren) {
							  //修改每个节点的父节点类型名
							  BookCate chilrenbookCate = bookCateTreeNode.getNode();
							  chilrenbookCate.setParentTypeCode(newName);
							  //保存到数据库更新
							  bookCateService.updateBookCate(chilrenbookCate);
						  }
						  
					  }
					  JOptionPane.showMessageDialog(null, "更新类型名成功！");
					  //重新加载
				      treeModel.reload();
				      tableModel.fireTableDataChanged();
		    	}else {
		    		JOptionPane.showMessageDialog(null, "更新类型名失败!");
		    	}
		    	
		    }
			
		
	});
	
	this.pop_btn_remove.addActionListener(e->{
		if(currSelectionPath  == null) {
			JOptionPane.showMessageDialog(null, "请选择一个一个要删除的图书类型!");
			return;
		}
		Object lastPathComponent = currSelectionPath.getLastPathComponent();
		if(lastPathComponent instanceof BookCateTreeNode) {
			BookCateTreeNode node = (BookCateTreeNode) lastPathComponent;
			if(node.isroot()) {
				int result = JOptionPane.showConfirmDialog(null, "该节点是根节点，只能删除其所有的子图书类型，是否确定删除？");
				if(result == JOptionPane.OK_OPTION) {
					removeNodeFromParent(node);
				 JOptionPane.showMessageDialog(null, "删除成功!");
				}
				
			}else {
				int result = JOptionPane.showConfirmDialog(null, "是否确定删除？");
				if(result == JOptionPane.OK_OPTION) {
					removeNodeFromParent(node);
					 //通知数据更新了
				    treeModel.reload();
				    tableModel.fireTableDataChanged();
				 JOptionPane.showMessageDialog(null, "删除成功!");
				}
			}
			
		}
		
	});
}
/**
 * 初始化Tree的按钮面板
 */
private void initTreeBtnPanel() {
	FlowLayout flowLayout = new FlowLayout();
	flowLayout.setHgap(1);
	JPanel panel = new JPanel(flowLayout);
	 btnStyle(pop_btn_add);
	 btnStyle(pop_btn_update);
	 btnStyle(pop_btn_remove);
	 this.pop_btn_remove.setBackground(new Color(255,121,78));
	 panel.add(pop_btn_add);
	 panel.add(pop_btn_update);
	 panel.add(pop_btn_remove);
	 panel.setPreferredSize(new Dimension(100,50));
	 this.leftPanel.add(panel,BorderLayout.SOUTH);
	 
}

private void initTableBtnEvent() {
	this.btn_add.addActionListener(e->{
		if(currSelectionPath == null) {
			return;
		}
		Object lastPathComponent = currSelectionPath.getLastPathComponent();
		if(lastPathComponent instanceof BookCateTreeNode) {
			BookCateTreeNode  node = (BookCateTreeNode) lastPathComponent;
             if(node.hasChildren()) {
            	String newName = JOptionPane.showInputDialog("请输入新的图书类型名：");
            	if(newName== null || newName.trim().length() ==0) {
            		JOptionPane.showMessageDialog(null,"输入为空!");
            		return;
            	}
            	 BookCate newBookCate = new BookCate();
            	 newBookCate.setCreateTime(new Date());
            	 newBookCate.setName(newName);
            	 newBookCate.setParentTypeCode(node.getNode().getName());
            	 boolean fla = bookCateService.saveBookCate(newBookCate);
            	 if(fla) {
            		 //添加成功,重新获取完整信息的BookCate
            		 newBookCate = bookCateService.findBookCateByName(newName);
            		 if(newBookCate!= null) {
            			 this.dataList.add(newBookCate);
            			 //设置节点的关系
            			 BookCateTreeNode newNode = new BookCateTreeNode(newBookCate,node.getLayerIndex()+1);
            			 newNode.setParent(node);
            			 node.addChild(newNode);
            			 List<BookCateTreeNode> newDateList  = new ArrayList<>();
            			 newDateList.addAll(tableModel.getList());
            			 newDateList.add(newNode);
            			 //重新设置tableModel的数据模型
            			 tableModel.setBookCateNodeList(newDateList);
            			 currPath = currSelectionPath;
            			 treeModel.reload();
            			 //展开当前路径
            			 tree.expandPath(currPath);  
            		 }
            	 }else {
            		 JOptionPane.showMessageDialog(null, "该图书类型名为"+newName+"的已存在，添加失败");
            		 return;
            	 }
            	 
            	
             }
		}
	});
	this.btn_update.addActionListener(e->{
		String text = btn_update.getText();
		if("修改".equals(text)) {
			btn_update.setText("确定修改");
			//设置可编辑
			tableModel.setEditable(true);
		}else {
			btn_update.setText("修改");
			tableModel.setEditable(false);
			//获取当前table的所有行
			int rowCount = table.getRowCount();
			//获取id所在列
			if(rowCount > 0) {
				for(int i = 0 ;i < rowCount;i++) {
					//获取每行的id
					Object valueAt = table.getValueAt(i, 1);
					if(valueAt instanceof Integer) {
						int id = (int) valueAt;
						BookCateTreeNode treeNode = tableModel.getTreeNode(id);
						if(treeNode != null) {
							BookCate node = treeNode.getNode();
							if(node != null) {
								bookCateService.updateBookCate(node);
							}
						}
					}
				}
			}
		}
	});
	this.btn_remove.addActionListener(e->{
		int selectedRowCount = table.getSelectedRowCount();
		if(selectedRowCount  == 0) {
			JOptionPane.showMessageDialog(null, "请至少选择一项删除！");
			return;
		}
	  int[] selectedRows = table.getSelectedRows();
	  int result = JOptionPane.showConfirmDialog(null, "确定删除？");
	  if(result != JOptionPane.OK_OPTION) {
		  return;
	  }
	  //获取当前的父节点
	  Object lastPathComponent = currSelectionPath.getLastPathComponent();
	  BookCateTreeNode parentNode = null;
	  List<BookCateTreeNode> allChildren = null;
	  if(lastPathComponent instanceof BookCateTreeNode) {
		  parentNode = (BookCateTreeNode) lastPathComponent;
		  if(parentNode.hasChildren()) {
			  allChildren = parentNode.getAllChildren();
		  }
	  }else {
		  return;
	  }
	  for(int i = 0; i < selectedRowCount;i++) {
		  Object valueAt = table.getModel().getValueAt(selectedRows[i], 1);
		  if(valueAt instanceof Integer) {
			  int id = (int) valueAt;
			  for(int j = 0 ;j < allChildren.size();j++) {
				  BookCateTreeNode bookCateTreeNode  = allChildren.get(j);
				  if(bookCateTreeNode.getNode().getId() == id) {
					  //从父节点中删除当前要删除的节点
					  removeNodeFromParent(bookCateTreeNode);
				  }
			  }
			
		  }
	  }
	    //通知数据更新了
	    treeModel.reload();
	    tableModel.fireTableDataChanged();
	  JOptionPane.showMessageDialog(null, "删除成功！");
	});
	this.btn_out.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				popMenu.show(btn_out, e.getX(), e.getY());
				
			}
		}
	});
}
private void initTableBtnPanel() {
	this.btnPanel.setPreferredSize(new Dimension(100,50));
//	this.btnPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
	 btnStyle(btn_add);
	 btnStyle(btn_update);
	 btnStyle(btn_remove);
	 this.btn_remove.setBackground(new Color(255,121,78));
//	 btnStyle(btn_search);
	 btnStyle(btn_out);
	 btnStyle(this.pop_out_currPage);
	 btnStyle(this.pop_out_all);
	 btnStyle(this.pop_out_selected);
	 this.popMenu.add(pop_out_currPage);
	 this.popMenu.add(pop_out_all);
	 this.popMenu.add(pop_out_selected);
	 this.btnPanel.add(btn_add);
	 this.btnPanel.add(btn_update);
	 this.btnPanel.add(btn_remove);
//	 this.btnPanel.add(btn_search);
	 this.btnPanel.add(btn_out);
}
private void btnStyle(JButton btn) {
	btn.setBackground(new Color(30,159,255));
	//该按钮的字体取消点击时显示聚焦边框
	btn.setFocusPainted(false);
	btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
	btn.setForeground(Color.WHITE);
}

private void initTable() {
	this.table.setModel(tableModel);
	this.table.getColumn("图书类别名称").setCellEditor(new BookCateTableEditor());
	this.table.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,14));
	this.table.setRowHeight(30);
	this.table.setAutoscrolls(true);
	this.table.getTableHeader().setReorderingAllowed(false);
	TableColumn column = this.table.getColumn("id");
	column.setMaxWidth(0);
	column.setMinWidth(0);
	column.setPreferredWidth(0);
	JScrollPane js = new JScrollPane();
 	MyScrollBarUI scrollBarUI = new MyScrollBarUI();
	js.getHorizontalScrollBar().setUI(scrollBarUI );
	js.getVerticalScrollBar().setUI(scrollBarUI);
  	js.setMinimumSize(new Dimension(150,200));
   	js.setMaximumSize(new Dimension(450,400));
	js.getViewport().add(this.table);
	this.table.getColumn("id").setPreferredWidth(0);
	
	this.rightPanel.add(js,BorderLayout.CENTER);
	
  	
}
private void initTree() {
	   this.dataList = this.bookCateService.getAllBookCate();
	    BookCateTreeNode root = showTreeData(this.dataList);
	    treeModel = new DefaultTreeModel(root);
	   	JTree tree = new JTree(treeModel);
	   	tree.addTreeSelectionListener(e -> {
		   currSelectionPath = tree.getSelectionPath();
		   if(currSelectionPath == null) {
			   tableModel.setBookCateNodeList(null);
			   return;
		   }
		  //获取当前选择的节点
		  Object obj = currSelectionPath.getLastPathComponent();
		  if(obj instanceof BookCateTreeNode) {
			  BookCateTreeNode lastPathComponent = (BookCateTreeNode) obj;
			  if(lastPathComponent.hasChildren()) {
				  boolean expanded = tree.isExpanded(currSelectionPath);
				  if(expanded) {
					  //父节点显示全部子节点
					  List<BookCateTreeNode> allChildren = lastPathComponent.getAllChildren();
					  tableModel.setBookCateNodeList(allChildren);
				  }else {
					  tableModel.setBookCateNodeList(null);
				  }
			  }else {
				  tableModel.setBookCateNodeList(null);
			  }
		  }
		  
		});
	   	tree.addTreeExpansionListener(new TreeExpansionListener() {
	   		@Override
			public void treeExpanded(TreeExpansionEvent event) {
	   			TreePath currSelectionPath = tree.getSelectionPath();
	   			if(currSelectionPath !=null) {
	   				//获取当前选择的节点
	   				Object obj = currSelectionPath.getLastPathComponent();
	   				if(obj instanceof BookCateTreeNode) {
	   					BookCateTreeNode lastPathComponent = (BookCateTreeNode) obj;
	   					if(lastPathComponent.hasChildren()) {
	   						boolean expanded = tree.isExpanded(currSelectionPath);
	   						if(expanded) {
	   							//父节点显示全部子节点
	   							List<BookCateTreeNode> allChildren = lastPathComponent.getAllChildren();
	   							tableModel.setBookCateNodeList(allChildren);
	   						}else {
	   							tableModel.setBookCateNodeList(null);
	   						}
	   					}else {
	   					  tableModel.setBookCateNodeList(null);
	   				  }
	   				}	
	   			}
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				TreePath selectionPath = tree.getSelectionPath();
				if(selectionPath != null) {
					//获取当前选择的节点
					Object obj = selectionPath.getLastPathComponent();
					if(obj instanceof BookCateTreeNode) {
						BookCateTreeNode lastPathComponent = (BookCateTreeNode) obj;
						if(lastPathComponent.hasChildren()) {
							tableModel.setBookCateNodeList(null);
						}else {
							  tableModel.setBookCateNodeList(null);
						  }
					}	
				}
			}
		});
	   	tree.setEditable(true);
	   	tree.setToggleClickCount(2);
	    tree.setCellRenderer(new BookCateTreeCellRender());
        tree.setCellEditor(new BookCateTreeEditor());
	   	JScrollPane js = new JScrollPane();
	   	MyScrollBarUI scrollBarUI = new MyScrollBarUI();
	   	js.getHorizontalScrollBar().setUI(scrollBarUI);
	   	js.getVerticalScrollBar().setUI(scrollBarUI);
	   	 js.getViewport().add(tree);
	   	js.setMinimumSize(new Dimension(150,200));
	   	js.setMaximumSize(new Dimension(450,400));
	   	leftPanel.add(js,BorderLayout.CENTER);
	   	leftPanel.setPreferredSize(new Dimension(50,200));
//	   	leftPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
}

/**
 * 构建节点数据
 */
public BookCateTreeNode showTreeData(List<BookCate> dataList) {
	BookCateTreeNode root = getRootNode(dataList, "全部类别");
	Stack<BookCateTreeNode> stack = new Stack<>();
	stack.push(root);
	BookCateTreeNode node  = root;
	while(dataList.size() > 0 || stack.size() > 0) {
		//寻找到父节点是该节点的对象
		String parentName = node.getNode().getName();
		BookCate bookCate = getNode(dataList,parentName);
		if(bookCate != null) {
			BookCateTreeNode newNode = new BookCateTreeNode(bookCate,node.getLayerIndex()+1);
			newNode.setParent(node);
			node.addChild(newNode);
			if(bookCate != null) {
				dataList.remove(bookCate);
			}
			stack.push(newNode);
		}else {
			node =stack.pop();
		}
	}
	return root;
  }
 /**
 * @param list
 * @param parentCode
 * @return BookCate
 */
public BookCate getNode(List<BookCate> list,String parentCode) {
	if(list.size() == 0|| parentCode == null|| parentCode.trim().length() == 0) {
		return null;
	}
	for(BookCate bCate:list) {
		if(parentCode.equals(bCate.getParentTypeCode())) {
		 return bCate;
		}
	}
	return null;
	 
 }
 /**
 * @param list
 * @return 根节点
 */
public BookCateTreeNode getRootNode(List<BookCate> list,String name) {
	 for(BookCate b:list) {
		 if(name.equals(b.getName())&& (name == null ||"".equals(b.getParentTypeCode()))) {
			 list.remove(b);
			 return new BookCateTreeNode(b,1);
		 }
		 
	 }
	 return null;
 }

/**
 * 移除指定节点的对象,并更新数据库
 * @param treeNode  
 */
public void removeNodeFromParent(BookCateTreeNode treeNode) {
	if(treeNode.hasChildren()){
		//移除除了根节点外的所有节点
		//所有父节点的栈
		Stack<BookCateTreeNode> parentStack = new Stack<>();
		//所有节点的栈
		Stack<BookCateTreeNode> stack = new Stack<>();
		parentStack.push(treeNode);
		BookCateTreeNode node = treeNode;
		while(parentStack.size() > 0) {
			node=parentStack.pop();
			if(node.hasChildren()) {
				List<BookCateTreeNode> allChildren = node.getAllChildren();
				//父节点入栈
				for(BookCateTreeNode b:allChildren) {
					//节点入栈
					parentStack.push(b);
				}
			}
			stack.push(node);
		}
	while(stack.size() > 0) {
		BookCateTreeNode pop = stack.pop();
		if(pop == treeNode) {
			if(treeNode.isroot()) {
				//根节点不删除
			}else {
				//不是根节点要删除
				//获取父节点
				BookCateTreeNode parentNode=  pop.getParent();	
				//父节点移除子节点
				parentNode.removeChildNode(pop);
			}
			
		}
		//在数据库中删除数据
		BookCate bookCate = pop.getNode();
		bookCateService.removeBookCateById(bookCate.getId());
	}
	}else if(treeNode.isLeaf()){
		//移除叶子节点
		//获取父节点
		BookCateTreeNode parentNode=  treeNode.getParent();	
		//父节点移除子节点
		parentNode.removeChildNode(treeNode);
		//在数据库中删除数据
		BookCate bookCate = treeNode.getNode();
		bookCateService.removeBookCateById(bookCate.getId());
	}
	
}

}
