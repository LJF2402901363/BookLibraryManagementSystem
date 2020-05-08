package model;

import java.util.Date;

import domain.Account;
import util.DateUtil;

/**
 * @author 陌意随影
 TODO :账户的表格模型
 *2020年3月17日  下午11:12:56
 */
public class AccountTableListModel  extends BaseTableModel<Account>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public  AccountTableListModel () {
		super();
	}
	// id | name     | author | price | type | status | description    | sbn     | createTime
    private static final  String[] columnNames = {
    	"编号","id","姓名","性别","年龄","状态","类型","创建时间","签名","爱好"	
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
			return this.list.get(row).getName();
		case 3:
			return this.list.get(row).getSex();
		case 4:
			return this.list.get(row).getAge();
		case 5:
			return this.list.get(row).getStatus();
		case 6:
			return this.list.get(row).getType();
		case 7:
			Date createTime = this.list.get(row).getCreateTime();
			return  DateUtil.DateToStr(createTime);
		case 8:
			return  this.list.get(row).getSignature();
		case 9:
			return this.list.get(row).getHobby();
			
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
