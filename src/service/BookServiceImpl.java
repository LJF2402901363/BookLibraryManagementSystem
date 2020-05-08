package service;
import java.util.Map;
import dao.BookDao;
import domain.Book;
import domain.PageBean;
import factory.DaoFactory;

/**
 * @author 陌意随影
 * @create 2020-03-09 12:58
 * @desc 这是图书业务逻辑类
 **/
public class BookServiceImpl implements BookService {
	//通过工厂模式获取一个dao
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
		return bookDao.getAllCount();
	}
}
