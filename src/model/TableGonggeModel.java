package model;

/**
 * @author 陌意随影
 TODO :宫格模型
 *2020年3月20日  下午9:40:02
 */
public class TableGonggeModel<T> extends BaseTableModel<T>{
	private static final long serialVersionUID = 1L;
	/** 默认的表格的列*/
	public static final int DEFAUTL_COLUMN_COUNT = 3 ;
	private int columnCount = DEFAUTL_COLUMN_COUNT;
     @SuppressWarnings("javadoc")
	public TableGonggeModel() {
     }
	@Override
	public int getColumnCount() {
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
		 if(index < 0 ||index >= this.list.size()) {
			 return null;
		 }
		 return this.list.get(index);
	}
	@Override
	public int getRowCount() {
		int totalRows = (this.list.size() + columnCount - 1 )/ columnCount;
          return totalRows;
	}
	/**
	 * @param columnCount
	 */
	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

}
