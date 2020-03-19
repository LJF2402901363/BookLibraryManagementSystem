package service;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.Book;
import domain.PageBean;
import factory.DaoFactory;
import service.BookService;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-02-02 12:58
 * @desc
 **/
public class BookServiceImpl implements BookService {
	private BookDao bookDao = (BookDao) DaoFactory.newInstanceDao("BookDao");;
	@Override
	public PageBean<Book> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,Map<String, String> condition, String fromDateTime,String toDateTime,
			String tableSortName, String tableOrder) {
		return this.bookDao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent,condition,fromDateTime,toDateTime, tableSortName, tableOrder);
	}

	@Override
	public boolean removeBookById(int id) {
		int result = this.bookDao.remove(id);
		return result == 1;
	}

	@Override
	public boolean updateBook(Book book) {
		int result = this.bookDao.upDate(book);
		return result == 1;
	}

	@Override
	public Book findBookById(int bookid) {
		return (Book) this.bookDao.get(bookid);
	}

	@Override
	public boolean addNewBook(Book book) {
		int result = this.bookDao.save(book);
		return result == 1;
	}

	@Override
	public int getAllBookCount() {
		// TODO Auto-generated method stub
		return bookDao.getAllCount();
	}
}
