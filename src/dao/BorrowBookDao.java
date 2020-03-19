package dao;

import java.util.List;
import java.util.Map;

import domain.BorrowBook;
import domain.PageBean;

/**
 * @author 陌意随影
 * @create 2020-02-09 19:59
 * @desc 借书的dao
 **/
public interface BorrowBookDao extends DAO<BorrowBook> {


	/**
	 * 获取起始日期到结束日期的借书记录数
	 * @param fromDate
	 * @param toDate
	 * @return 返回该时间段的借书记录数
	 */	int getAllBorrowBookRecordCount(String fromDate, String toDate);
		/**
		 * 获取起始日期到结束日期的还书记录数
		 * @param fromDateStr
		 * @param toDateStr
		 * @return 返回该时间段的借书记录数
		 */
	int getAllReturnBookRecordCount(String fromDateStr, String toDateStr);
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
		 * @param offset
		 * @param pageSize
		 * @param fuzzySearchContent
		 * @param condition
		 * @param fromDateTime
		 * @param toDateTime
		 * @param tableSortName
		 * @param tableOrder
		 * @return 
		 */
		PageBean<BorrowBook> fuzzySearchBorrowBookByPage(int offset, int pageSize, String fuzzySearchContent,
				Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
				String tableOrder);
		/**
		 * @param offset
		 * @param pageSize
		 * @param fuzzySearchContent
		 * @param condition
		 * @param fromDateTime
		 * @param toDateTime
		 * @param tableSortName
		 * @param tableOrder
		 * @return
		 */
		PageBean<BorrowBook> fuzzySearchReturnBookByPage(int offset, int pageSize, String fuzzySearchContent,
				Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
				String tableOrder);
}
