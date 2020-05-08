package testRoleTableUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author 陌意随影
 TODO :BaseTableModel
 *2020年3月17日  下午11:12:56
 */
public abstract class BaseTableModel<T>  extends AbstractTableModel implements Serializable{
	private static final long serialVersionUID = 1L;
	protected List<T> list = null;
	//编号
	 protected  int[] IDs = null;
	@SuppressWarnings("javadoc")
	public  BaseTableModel () {
		this.list = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return this.list.size();
	}
//	/**
//	 * @param pageBean
//	 */
//	public void setPageBean(PageBean<T> pageBean) {
//		if(pageBean == null) {
//			return;
//		}
//		//首先清空所有元素
//		this.list.clear();
//		if(pageBean.getList()== null || pageBean.getList().size() == 0) {
//			return;
//		}
//		//重新添加元素
//		this.list.addAll(pageBean.getList());
//		this.IDs = new int[pageBean.getPageSize()];
//		int index = pageBean.getOffset()+1;
//		for(int i =0;i < this.IDs.length;i++) {
//			this.IDs[i] =index++;
//		}
//		this.fireTableDataChanged();
//	}
	 /**
	  * 移除指定的行
	 * @param row
	 */
	public void removeRow(int row) {
	       this.list.remove(row);
	        fireTableRowsDeleted(row, row);
	    }

	/**
	 * @return  list
	 */
	public List<T> getList() {
		return list;
	}

	protected  void removeAllRow() {
		this.list.clear();
		this.fireTableDataChanged();
};



}
