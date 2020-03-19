package service;

import java.util.Map;

import domain.BorrowBook;
import domain.PageBean;

/**
 * @author 陌意随影
 TODO : 借书和还书的业务
 *2020年3月19日  下午3:55:25
 */
public class BorrowBookRecordServiceImpl  extends AbstractBorrowRecordBookService implements BorrowBookRecordService{

	@Override
	protected PageBean<BorrowBook> fuzzySearchBorroBookByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		return this.borrowBookDao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder);
	}

}
