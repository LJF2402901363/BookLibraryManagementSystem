package model;
import domain.SysLog;
import util.DateUtil;
/**
 * @author 陌意随影
 TODO :系统日志的表格模型
 *2020年3月28日  下午1:01:28
 */
public class SysLogTableModel extends BaseTableModel<SysLog>{
	private static final long serialVersionUID = 1L;
	//id | operator | createTime          | operationType | details
    private final static String[] columnNames= {"编号","id","operator","operationType","createTime","details"};
    private boolean isEditable = false;
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
  @Override
public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//	  System.out.println("模型里是：("+rowIndex +","+columnIndex+")");
	  switch(columnIndex) {
		case 2:
			this.list.get(rowIndex).setOperator((String) aValue);
			break;
		case 3:
			this.list.get(rowIndex).setOperationType((String) aValue);
			break;
		case 4:
			this.list.get(rowIndex).setCreateTime(DateUtil.StrToDate((String) aValue));
			break;
		case 5:
			 this.list.get(rowIndex).setDetails((String) aValue);
			 break;
		}
}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return this.IDs[rowIndex];
		case 1:
			return this.list.get(rowIndex).getId();
		case 2:
			return this.list.get(rowIndex).getOperator();
		case 3:
			return this.list.get(rowIndex).getOperationType();
		case 4:
			return DateUtil.DateToStr(this.list.get(rowIndex).getCreateTime());
		case 5:
			return this.list.get(rowIndex).getDetails();
		}
		return null;
	}
  @Override
public String getColumnName(int column) {
	// TODO Auto-generated method stub
	return columnNames[column];
}
  @Override
public boolean isCellEditable(int rowIndex, int columnIndex) {
	return isEditable;
}


public void setEditable(boolean isEditable) {
	this.isEditable = isEditable;
}
  
}
