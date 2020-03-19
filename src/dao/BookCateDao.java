package dao;

import domain.BookCate;

/**
 * @author 陌意随影
 TODO :
 *2020年3月8日  下午4:08:58
 */
public interface BookCateDao extends DAO<BookCate> {
	/**
	 * 通过图书类型名查找对应的数据
	 * @param newName
	 * @return  返回对应名字的 数据
	 */
	BookCate findBookCateByName(String name);


}
