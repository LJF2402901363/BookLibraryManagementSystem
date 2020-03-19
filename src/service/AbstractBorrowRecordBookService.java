package service;

import java.util.List;
import java.util.Map;

import dao.BorrowBookDao;
import dao.impl.BorrowBookDaoImpl;
import domain.BorrowBook;
import domain.PageBean;
import factory.DaoFactory;
import service.BorrowBookRecordService;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:37
 * @desc 借书业务逻辑的实现
 **/
public abstract class AbstractBorrowRecordBookService implements BorrowBookRecordService {
  protected BorrowBookDao borrowBookDao =  (BorrowBookDao) DaoFactory.newInstanceDao("BorrowBookDao");
	protected abstract PageBean<BorrowBook> fuzzySearchBorroBookByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder);
	@Override
	public boolean saveBorrowBook(BorrowBook borrowBook) {
		int result = borrowBookDao.save(borrowBook);
		return result == 1;
	}
	@Override
	public int getAllBorrowBookRecordCount(String fromDate, String toDate) {
		return borrowBookDao.getAllBorrowBookRecordCount(fromDate,toDate);
	}
	@Override
	public int getAllReturnBookRecordCount(String fromDateStr, String toDateStr) {
		// TODO Auto-generated method stub
		return  borrowBookDao.getAllReturnBookRecordCount(fromDateStr,toDateStr);
	}
	@Override
	public List<BorrowBook> getAllBorrowBook() {
		return borrowBookDao.getAll();
	}
	@Override
	public List<BorrowBook> getAllBorrowBookRecord(String fromDateStr, String toDateStr) {
		// TODO Auto-generated method stub
		return borrowBookDao.getAllBorrowBookRecord(fromDateStr, toDateStr);
	}
	@Override
	public List<BorrowBook> getAllReturnBookRecord(String fromDateStr, String toDateStr) {
		// TODO Auto-generated method stub
		return borrowBookDao.getAllReturnBookRecord(fromDateStr, toDateStr);
	}
	@Override
	public PageBean<BorrowBook> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		// TODO Auto-generated method stub
		return fuzzySearchBorroBookByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder)	;
	}
	@Override
	public BorrowBook getBorrowBookById(Integer id) {
		return borrowBookDao.get(id);
	}
}
