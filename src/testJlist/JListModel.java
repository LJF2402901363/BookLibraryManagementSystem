package testJlist;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import domain.BorrowBook;

/**
 * @author 陌意随影
 TODO :JList的数据模型
 *2020年3月18日  下午5:39:07
 */
public class JListModel extends AbstractListModel<BorrowBook> {
	private static final long serialVersionUID = 1L;
	private List<BorrowBook> list = null;
	@SuppressWarnings("javadoc")
 public JListModel() {
	   list = new ArrayList<>();
   }
	 @SuppressWarnings("javadoc")
	public JListModel(List<BorrowBook> list) {
		  this();
		  if(list!=null && list.size() > 0) {
			 this.list.addAll(list); 
		  }
		  fireContentsChanged(this, 0, this.list.size()-1);
	   }
	@Override
	public int getSize() {
		return list.size();
	}
 /**
 * @param list 设置数据
 */
public void setListData( List<BorrowBook> list) {
	 this.list.clear();
	 if(list!=null && list.size() > 0) {
		this.list.addAll(list);
	  }
   this.fireIntervalAdded(this, 0, this.list.size() -1);
	 
 }
	@Override
	public BorrowBook getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

}
