package service;

import java.util.List;

/**
* @TODO:
* @author: 陌意随影
* @date: 2020-02-09 20:36
*/

import domain.BorrowBook;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:36
 * @desc 借书的业务逻辑接口
 **/
public interface BorrowBookRecordService  extends BaseService<BorrowBook>{
	/**
	 * @Description :将借书记录存储到数据库中去
	 * @Date 20:38 2020/2/9 0009
	 * @Param * @param borrowBook ：
	 * @return boolean
	 **/
	boolean saveBorrowBook(BorrowBook borrowBook);

	/**
	 * 获取起始日期到结束日期的借书记录数
	 * @param fromDate
	 * @param toDate
	 * @return 返回该时间段的借书记录数
	 */
	int getAllBorrowBookRecordCount(String fromDate,String toDate);
	/**
	 * 获取起始日期到结束日期的还书记录数
	 * @param fromDateStr
	 * @param toDateStr
	 * @return 返回该时间段的借书记录数
	 */
	int getAllReturnBookRecordCount(String fromDateStr, String toDateStr);

	/**
	 * @return  获取所有的借阅书籍
	 */
	List<BorrowBook> getAllBorrowBook();

	/**
	 * 获取起始日期到结束日期的借书记录
	 * @param fromDateStr
	 * @param toDateStr
	 * @return 返回该时间段的借书记录
	 */
	List<BorrowBook> getAllBorrowBookRecord(String fromDateStr, String toDateStr);

	/**
	 * 获取起始日期到结束日期的还书记录
	 * @param fromDateStr
	 * @param toDateStr
	 * @return 返回该时间段的借书记录
	 */
	List<BorrowBook> getAllReturnBookRecord(String fromDateStr, String toDateStr);

	/**
	 * @param id
	 * @return  返回id指定的借书记录
	 */
	BorrowBook getBorrowBookById(Integer id);
}
