package model;

import domain.BorrowBook;
import util.DateUtil;

/**
 * @author 陌意随影
 TODO :还书的数据模型
 *2020年3月19日  下午2:38:17
 */
public class ReturnBookModel extends BaseTableModel<BorrowBook>{
	private static final long serialVersionUID = 1L;
    
	  private static final String[] columnNames= {"编号","id","读者姓名","读者id","图书名","图书id","归还时间"};
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
			case 0:
				return this.IDs[rowIndex];
			case 1:
				return this.list.get(rowIndex).getId();
			case 2:
				return this.list.get(rowIndex).getReader().getName();
			case 3:
				return this.list.get(rowIndex).getReader().getId();
			case 4:
				return this.list.get(rowIndex).getIsBorrowedBook().getName();
			case 5:
				return this.list.get(rowIndex).getIsBorrowedBook().getId();
			case 6:
				String dateToStr = DateUtil.DateToStr(this.list.get(rowIndex).getReturnTime());
				return dateToStr ;
			}
			return null;
		}
		@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
