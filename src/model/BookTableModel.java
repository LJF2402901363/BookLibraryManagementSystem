package model;

import java.util.Date;
import java.util.List;
import domain.Book;
import util.DateUtil;

/**
 * @author 陌意随影
 TODO :
 *2020年2月17日  下午11:12:56
 */
public class BookTableModel  extends BaseTableModel<Book>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public  BookTableModel () {
		super();
	}
	// id | name     | author | price | type | status | description    | sbn     | createTime
    private static final  String[] columnNames = {
    	"编号","id","版本号","书名","类型","作者","价格","状态","创建时间","简介"	
    };
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch(col) {
		case 0:
			return this.IDs[row];
		case 1:
			return this.list.get(row).getId();
		case 2:
			return this.list.get(row).getSbn();
		case 3:
			return this.list.get(row).getName();
		case 4:
			return this.list.get(row).getType();
		case 5:
			return this.list.get(row).getAuthor();
		case 6:
			return this.list.get(row).getPrice();
		case 7:
			return this.list.get(row).getStatus();
		case 8:
			Date createTime = this.list.get(row).getCreateTime();
			return  DateUtil.DateToStr(createTime);
		case 9:
			return this.list.get(row).getDescription();
			
		}
		return null;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return super.isCellEditable(rowIndex, columnIndex);
	}
 @Override
public String getColumnName(int column) {
	return columnNames[column];
}

}
