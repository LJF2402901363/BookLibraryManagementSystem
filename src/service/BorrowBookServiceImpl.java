package service;

import java.util.Map;

import domain.BorrowBook;
import domain.PageBean;
/**
 * @author 陌意随影
 TODO :借书记录的业务逻辑类
 *2020年3月19日  下午3:00:33
 */
public class BorrowBookServiceImpl extends AbstractBorrowRecordBookService implements BorrowBookService{
	@Override
	protected PageBean<BorrowBook> fuzzySearchBorroBookByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		return this.borrowBookDao.fuzzySearchBorrowBookByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder);
	}

}
