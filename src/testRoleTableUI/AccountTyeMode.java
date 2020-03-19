package testRoleTableUI;

import domain.AccountType;

/**
 * @author 陌意随影
 TODO :图书类型的模型
 *2020年2月23日  下午8:44:29
 */
public class AccountTyeMode extends BaseTableModel<AccountType> {
	private static final long serialVersionUID = 1L;
	public static final String[] COLUMNNAMES= {"编号","id","类型名称","可以借阅的天数","可借次数","可续借天数","操作"};
   @SuppressWarnings("javadoc")
public AccountTyeMode() {
	   this.IDs = new int[10];
	   for(int i = 0;i < 10;i++) {
		   AccountType accountType = new AccountType();
		   accountType.setId(i+1);
		   accountType.setCanborrowdays(4+i*2);
		   accountType.setCanborrowTimes(i*2+1);
		   accountType.setTypeName(((char)('A'+i))+"");
		   accountType.setCanborrowbooks(i*3+1);
		   this.list.add(accountType);
		   this.IDs[i]=i+1;
	   }
	   
   }
	@Override
	public int getColumnCount() {
		return COLUMNNAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return this.IDs[rowIndex];
		case 1:
			return this.list.get(rowIndex).getId();
		case 2:
			return this.list.get(rowIndex).getTypeName();
		case 3:
			return this.list.get(rowIndex).getCanborrowdays();
		case 4:
			return this.list.get(rowIndex).getCanborrowTimes();
		case 5:
			return this.list.get(rowIndex).getCanborrowbooks();
		}
		return null;
	}
@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return COLUMNNAMES[column];
	}
 @Override
public boolean isCellEditable(int rowIndex, int columnIndex) {
	return true;
}
}
