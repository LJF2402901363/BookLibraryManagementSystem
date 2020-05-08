package service;
import domain.Book;

/**
 * @author 陌意随影
 * @create 2020-03-08 12:57
 * @desc 书籍的业务逻辑接口
 **/
public interface BookService extends BaseService<Book> {

	/**
	 * @Description :通过ID来删除书籍
	 * @Date 21:51 2020/2/5 0005
	 * @Param * @param id ：
	 * @return boolean
	 **/
	boolean removeBookById(int id);

	/**
	 * @Description :更新数据库中的书籍
	 * @Date 23:17 2020/2/6 0006
	 * @Param * @param book ：
	 * @return boolean
	 **/
	boolean updateBook(Book book);

	/**
	 * @Description :通过id获取书籍
	 * @Date 19:17 2020/2/9 0009
	 * @Param * @param bookid ：
	 * @return domain.Book
	 **/
	Book findBookById(int bookid);

	/**
	 * @Description :向数据库中添加新的书籍
	 * @Date 14:22 2020/2/10 0010
	 * @Param * @param book ：
	 * @return boolean
	 **/
	boolean addNewBook(Book book);

	/**
	 * @return 获取所有的数量
	 */
	int getAllBookCount();
}
