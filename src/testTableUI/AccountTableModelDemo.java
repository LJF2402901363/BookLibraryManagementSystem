package testTableUI;

import java.util.ArrayList;

import dao.AccountDao;
import dao.impl.ReaderDaoImpl;
import domain.Account;
import service.AdministratortService;

/**
 * @author 陌意随影
 TODO :
 *2020年2月20日  下午9:40:02
 */
public class AccountTableModelDemo extends BaseTableModelDemo<Account>{
	private static final long serialVersionUID = 1L;
	/** 默认的表格的列*/
	public static final int DEFAUTL_COLUMN_COUNT = 3 ;
	private int columnCount = DEFAUTL_COLUMN_COUNT;
	private AccountDao dao = new ReaderDaoImpl();
     @SuppressWarnings("javadoc")
	public AccountTableModelDemo() {
    	 for(int i  = 0 ; i  < 20;i++) {
       		 Account account = new Account();
       		 account.setId(i+1);
       		 account.setName((char)('A'+i)+"");
       		 account.setSignature("我的世界:"+i+1);
       		this.data.add(account);
       	 }
     }
	@Override
	public int getColumnCount() {
//		System.out.println("columnCount:"+columnCount);
		return columnCount;
	}
 @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
 
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnCount <= 0) {
			columnCount = DEFAUTL_COLUMN_COUNT;
		}
		//获取单元格对应的Account的索引
		int index  = (rowIndex) * columnCount + columnIndex;
//		System.out.println();
		 if(index < 0 ||index >= this.list.size()) {
			 return null;
		 }
		 fireTableDataChanged();
//		 System.out.println("("+rowIndex +","+columnIndex+")----index:"+index +"--------value:"+this.list.get(index));
		 return this.list.get(index);
	}
	@Override
	public int getRowCount() {
		int totalRows = (this.list.size() + columnCount - 1 )/ columnCount;
//		System.out.println("totalRows："+totalRows);
          return totalRows;
	}
	/**
	 * @param columnCount
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

}
