package testTree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import util.DateUtil;

/**
 * @author 陌意随影
 TODO :图书分类的table模型
 *2020年3月27日  下午7:06:50
 */
public class BookCateTableModel  extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
    private List<BookCateTreeNode> list = null;
    private static final String[] columnNames = {"序号","id","图书类别父类编码","图书类别名称","创建时间"};
    private int[] IDs  = null;
    boolean isEditable = false;
   
	@SuppressWarnings("javadoc")
	public BookCateTableModel() {
    	this.list = new ArrayList<>();
    	
    }
	@Override
	public int getRowCount() {
		
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
   @Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex) {
		case 0:
			return this.IDs[rowIndex];
		case 1:
			return this.list.get(rowIndex).getNode().getId();
		case 2:
			return this.list.get(rowIndex).getNode().getParentTypeCode();
		case 3:
			return this.list.get(rowIndex).getNode().getName();
		case 4:
			return DateUtil.DateToStr(this.list.get(rowIndex).getNode().getCreateTime());
		}
		return null;
		
	}
/**
 * @param list  重新设置数据
 */
public void setBookCateNodeList(List<BookCateTreeNode> list) {
	this.list.clear();
	if(list != null && list.size() != 0) {
		 this.list.addAll(list);
		 this.IDs = new int[list.size()];
		 for(int i = 0;i < list.size();i++) {
		    	this.IDs[i] = i+1;
		    }
	}else {
		this.IDs=new int[0];
	}
    this.fireTableDataChanged();
  }
/**
 * 移除指定的节点
 * @param node
 * @return 返回是否移除成功
 */
public boolean removeBookCateNode(BookCateTreeNode node) {
	boolean fla = this.list.remove(node);
	this.fireTableDataChanged();
	return fla;
}
/**
 * 移除指定的父节点下的子节点
 * @param parentNode
 * @param index
 * @return 返回是否移除成功
 */
public boolean removeBookCateNode(BookCateTreeNode parentNode,int index) {
	return false;
	
}
@Override
public boolean isCellEditable(int rowIndex, int columnIndex) {
	 if(columnIndex == columnNames.length -2) {
		 return isEditable;
	 }else {
		 return false;
	 }
}
public void setEditable(boolean isEditable) {
	this.isEditable = isEditable;
}
@Override
public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	switch(columnIndex) {
		case 1:
			if (aValue instanceof Integer) {
				this.list.get(rowIndex).getNode().setId((int) aValue);
			}
			break;
		case 2:
			if (aValue instanceof String) {
				this.list.get(rowIndex).getNode().setParentTypeCode((String)aValue);
			}
			break;
		case 3:
			if (aValue instanceof String) {
				this.list.get(rowIndex).getNode().setName((String)aValue);
			}
		break;
		case 4:
			Date strToDate = DateUtil.StrToDate((String)aValue);
			this.list.get(rowIndex).getNode().setCreateTime(strToDate);
			break;
	}
}
/**
 * 通过主键id来获取对应的节点
 * @param id
 * @return  返回对应的节点
 */
public BookCateTreeNode getTreeNode(int id) {
	for(int i = 0 ;i < this.list.size();i++) {
		if(this.list.get(i).getNode().getId() == id) {
		return this.list.get(i);
		}
	}
	return null;
}
/**
 * @return  获取所有的数据集合
 */
public List<BookCateTreeNode> getList() {
	return list;
}
}
