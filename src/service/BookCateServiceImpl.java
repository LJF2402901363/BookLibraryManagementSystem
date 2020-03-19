package service;

import java.util.List;
import java.util.Map;

import dao.BookCateDao;
import dao.impl.BookCateDaoImpl;
import domain.BookCate;
import domain.PageBean;
import factory.DaoFactory;

/**
 * @author 陌意随影
 TODO : 图书分类的业务逻辑实现类
 *2020年3月8日  下午4:37:56
 */
public class BookCateServiceImpl  implements BookCateService{
   private  BookCateDao dao =  (BookCateDao) DaoFactory.newInstanceDao("BookCateDao");;
	@Override
	public PageBean<BookCate> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		return dao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder);
	}
	@Override
	public boolean saveBookCate(BookCate bookCate) {
		return dao.save(bookCate) == 1;
	}
	@Override
	public List<BookCate> getAllBookCate() {
		return dao.getAll();
	}
	@Override
	public boolean updateBookCate(BookCate node) {
		return dao.upDate(node) == 1;
		
	}
	@Override
	public boolean removeBookCateById(int id) {
	  
		return dao.remove(id) == 1;
	}
	@Override
	public BookCate findBookCateByName(String name) {
		
		return dao.findBookCateByName( name);
	}
	@Override
	public int getAllBookCateCount() {
		return dao.getAllCount();
	}
	

}
