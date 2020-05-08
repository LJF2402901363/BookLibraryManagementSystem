package service;
import java.util.Map;
import domain.BorrowBook;
import domain.PageBean;
/**
 * @author 陌意随影
 TODO :
 *2020年3月19日  下午2:44:12
 */
public class ReturnBookServiceImpl  extends AbstractBorrowRecordBookService implements ReturnBookService{

	@Override
	protected PageBean<BorrowBook> fuzzySearchBorroBookByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		return this.borrowBookDao.fuzzySearchReturnBookByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder);
	}


}
