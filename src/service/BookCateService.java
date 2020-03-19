package service;

import java.util.List;

import domain.BookCate;

/**
 * @author 陌意随影
 TODO :图书分类的业务逻辑
 *2020年3月8日  下午4:37:05
 */
public interface BookCateService extends BaseService<BookCate> {

	/**
	 * 保存对象到数据库中去
	 * @param bookCate
	 * @return  保存成功返回true，否则返回false
	 */
	boolean saveBookCate(BookCate bookCate);

	/**
	 * @return  获取所有的对象
	 */
	List<BookCate> getAllBookCate();

	/**
	 * 更新指定的对象
	 * @param node
	 */
	boolean updateBookCate(BookCate node);

	/**
	 * 从数据库中删除主键id指定的数据
	 * @param id
	 */
	boolean removeBookCateById(int id);

	/**
	 * 通过图书类型名查找对应的数据
	 * @param newName
	 * @return  返回对应名字的 数据
	 */
	BookCate findBookCateByName(String name);

	/**
	 * @return 获取所有的图书分类记录
	 */
	int getAllBookCateCount();

}
