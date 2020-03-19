package dao.impl;

import dao.BookDao;
import dao.BorrowBookDao;
import dao.ReaderDao;
import dao.ResultSetHandler;
import domain.Account;
import domain.Book;
import domain.BorrowBook;
import domain.PageBean;
import factory.DaoFactory;
import util.ConfigContant;
import util.DateUtil;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:00
 * @desc 借书dao的实现类
 **/
public class BorrowBookDaoImpl implements BorrowBookDao {
	private static String tableName = ConfigContant.BORROWBOOK_TABLE;
	
	@Override
	public int save(BorrowBook borrowBook) {
		String sql = "insert into " + tableName
				+ "  (accountid,bookid,borrowtime,returntime) values(?,?,?,?)";
		int result = JdbcUtil.upDate(sql, borrowBook.getReader().getId(), borrowBook.getIsBorrowedBook().getId(), DateUtil.DateToStr(borrowBook.getBorrowTime()),
				DateUtil.DateToStr(borrowBook.getReturnTime()));
		return result;
	}

	
	@Override
	public int upDate(BorrowBook borrowBook) {
		String sql="update "+tableName +" set  accountid=?,bookid=?,borrowtime=?,returntime=? where id=?";
		return JdbcUtil.upDate(sql, borrowBook.getReader().getId(),borrowBook.getIsBorrowedBook().getId(),DateUtil.DateToStr(borrowBook.getBorrowTime()),
				DateUtil.DateToStr(borrowBook.getReturnTime()),borrowBook.getId());
	}

	@Override
	public int remove(int id) {
		String sql = "delete from " + tableName +" where id=?";
		return JdbcUtil.upDate(sql, id);
	}

	@Override
	public BorrowBook get(int id) {
		String sql = "select *from " + tableName +" where id=?";
		List<BorrowBook> list = JdbcUtil.query(sql, new BorrowBookResultSetHandler(), id);
		if(list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BorrowBook> getAll() {
		String sql = "select *from " + tableName;
		List<BorrowBook> borrowBookList = JdbcUtil.query(sql, new BorrowBookResultSetHandler());
		return borrowBookList;
	}

	@Override
	public int getCount(String sql) {
		ResultSetHandler<Integer> resultSetHandler = new ResultSetHandler<Integer>() {
			@Override
			public Integer resultSetHandler(ResultSet resultSet) {
				try {
					if (resultSet.next()) {
						return resultSet.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
		return JdbcUtil.query(sql, resultSetHandler);
	}
	@Override
	public PageBean<BorrowBook> fuzzySearchBorrowBookByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,
			String fromDateTime, String toDateTime, String tableSortName, String tableOrder) {
		if(fromDateTime != null && fromDateTime.trim().length() == 0) {
			fromDateTime = null;
		}
		if(toDateTime != null && toDateTime.trim().length() == 0) {
			toDateTime = null;
		}
	
		if(fuzzySearchContent == null||fuzzySearchContent.trim().length()==0) {
			fuzzySearchContent="";
		}

		String tatolSql =" select count(*) from account,book,borrowbook where ";
		StringBuilder sb = new StringBuilder(" (account.name like '%"+fuzzySearchContent+"%' or  book.name like '%"+ fuzzySearchContent+"%')");
		if(fromDateTime == null && toDateTime == null ) {
		}else if(fromDateTime == null&& toDateTime != null) {
			sb.append(" and  (  borrowbook. borrowtime ").append(" < '").append(toDateTime).append("')");
		}else if(fromDateTime != null&& toDateTime == null) {
			sb.append("  and  (  borrowbook. borrowtime ").append(" > '").append(fromDateTime).append("')");
		}else {
			sb.append(" and (  borrowbook. borrowtime ").append(" Between '").append(fromDateTime).append("' and '").append(toDateTime).append("')");
		}
		sb.append(" and borrowbook.accountid=account.id and borrowbook.bookid=book.id   and  borrowbook.borrowtime   is not  null");
		String dateSql = sb.toString();
		tatolSql=tatolSql+dateSql;
		System.out.println(tatolSql);
		//获取总数
		int total = this.getCount(tatolSql);
		// 计算当前页面
		
		if (tableSortName == null || tableSortName.trim().length() == 0) {
			sb.append(" limit ?,?");
		} else {
			if (tableOrder == null || tableOrder.trim().length() == 0) {
				// 默认的排序
				tableOrder = "asc";
			}
			sb.append(" order by ").append(tableSortName + "  ").append(tableOrder + " limit ?,?");
		}
		String[] borrowbookcolumnnames = ConfigContant.BORROWBOOKCOLUMNNAMES;
		 String showNames = "";
		 for(int i = 0;i < borrowbookcolumnnames.length;i++) {
			 if(i == borrowbookcolumnnames.length -1) {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i];
			 }else {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i]+",";
			 }
		 }
		  String sql = " select  "+ showNames + " from  account,book,borrowbook where " +sb.toString();
		  System.out.println(sql);
			List<BorrowBook> borrowbookList = JdbcUtil.query(sql, new BorrowBookResultSetHandler(), offset,pageSize);
	         PageBean<BorrowBook> pageBean = new PageBean<>();
		   // 计算当前页面
		   int currPage = 0;
		   if (pageSize != 0) {
			   // 计算当前页面
			   currPage = offset / pageSize + 1;
		   }
		   //计算总页数
		   int totalPage = (total % pageSize == 0)? (total/pageSize):  (total/pageSize+1);
		   pageBean.setCurrPage(currPage);
		   pageBean.setList(borrowbookList);
		   pageBean.setPageSize(pageSize);
		   pageBean.setTotalPage(totalPage);
		   pageBean.setTotal(total);
		return pageBean;
	}


	@Override
	public PageBean<BorrowBook> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,
			String fromDateTime, String toDateTime, String tableSortName, String tableOrder) {
		if(fromDateTime != null && fromDateTime.trim().length() == 0) {
			fromDateTime = null;
		}
		if(toDateTime != null && toDateTime.trim().length() == 0) {
			toDateTime = null;
		}
	
		if(fuzzySearchContent == null||fuzzySearchContent.trim().length()==0) {
			fuzzySearchContent="";
		}

		String tatolSql =" select count(*) from account,book,borrowbook where ";
		StringBuilder sb = new StringBuilder(" (account.name like '%"+fuzzySearchContent+"%' or  book.name like '%"+ fuzzySearchContent+"%')");
		if(fromDateTime != null ) {
			sb.append(" and  (  borrowbook. borrowtime ").append(" > '").append(toDateTime).append("')");
		}
		if(toDateTime != null) {
			sb.append(" and  (  borrowbook. returntime ").append(" > '").append(toDateTime).append("')");

		}
		sb.append(" and borrowbook.accountid=account.id and borrowbook.bookid=book.id ");
		String dateSql = sb.toString();
		tatolSql=tatolSql+dateSql;
		System.out.println(tatolSql);
		//获取总数
		int total = this.getCount(tatolSql);
		// 计算当前页面
		
		if (tableSortName == null || tableSortName.trim().length() == 0) {
			sb.append(" limit ?,?");
		} else {
			if (tableOrder == null || tableOrder.trim().length() == 0) {
				// 默认的排序
				tableOrder = "asc";
			}
			sb.append(" order by ").append(tableSortName + "  ").append(tableOrder + " limit ?,?");
		}
		String[] borrowbookcolumnnames = ConfigContant.BORROWBOOKCOLUMNNAMES;
		 String showNames = "";
		 for(int i = 0;i < borrowbookcolumnnames.length;i++) {
			 if(i == borrowbookcolumnnames.length -1) {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i];
			 }else {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i]+",";
			 }
		 }
		  String sql = " select  "+ showNames + " from  account,book,borrowbook where " +sb.toString();
		  System.out.println(sql);
			List<BorrowBook> borrowbookList = JdbcUtil.query(sql, new BorrowBookResultSetHandler(), offset,pageSize);
	         PageBean<BorrowBook> pageBean = new PageBean<>();
		   // 计算当前页面
		   int currPage = 0;
		   if (pageSize != 0) {
			   // 计算当前页面
			   currPage = offset / pageSize + 1;
		   }
		   //计算总页数
		   int totalPage = (total % pageSize == 0)? (total/pageSize):  (total/pageSize+1);
		   pageBean.setCurrPage(currPage);
		   pageBean.setList(borrowbookList);
		   pageBean.setPageSize(pageSize);
		   pageBean.setTotalPage(totalPage);
		   pageBean.setTotal(total);
		return pageBean;
	}
	@Override
	public PageBean<BorrowBook> fuzzySearchReturnBookByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,
			String fromDateTime, String toDateTime, String tableSortName, String tableOrder) {
		if(fromDateTime != null && fromDateTime.trim().length() == 0) {
			fromDateTime = null;
		}
		if(toDateTime != null && toDateTime.trim().length() == 0) {
			toDateTime = null;
		}
	
		if(fuzzySearchContent == null||fuzzySearchContent.trim().length()==0) {
			fuzzySearchContent="";
		}

		String tatolSql =" select count(*) from account,book,borrowbook where ";
		StringBuilder sb = new StringBuilder(" (account.name like '%"+fuzzySearchContent+"%' or  book.name like '%"+ fuzzySearchContent+"%')");
		if(fromDateTime == null && toDateTime == null ) {
		}else if(fromDateTime == null&& toDateTime != null) {
			sb.append(" and  (  borrowbook. returntime ").append(" < '").append(toDateTime).append("')");
		}else if(fromDateTime != null&& toDateTime == null) {
			sb.append("  and  (  borrowbook. returntime ").append(" > '").append(fromDateTime).append("')");
		}else {
			sb.append(" and (  borrowbook. returntime ").append(" Between '").append(fromDateTime).append("' and '").append(toDateTime).append("')");
		}
		sb.append(" and borrowbook.accountid=account.id and borrowbook.bookid=book.id    and  borrowbook.returntime   is not  null");
		String dateSql = sb.toString();
		tatolSql=tatolSql+dateSql;
		System.out.println(tatolSql);
		//获取总数
		int total = this.getCount(tatolSql);
		// 计算当前页面
		
		if (tableSortName == null || tableSortName.trim().length() == 0) {
			sb.append(" limit ?,?");
		} else {
			if (tableOrder == null || tableOrder.trim().length() == 0) {
				// 默认的排序
				tableOrder = "asc";
			}
			sb.append(" order by ").append(tableSortName + "  ").append(tableOrder + " limit ?,?");
		}
		String[] borrowbookcolumnnames = ConfigContant.BORROWBOOKCOLUMNNAMES;
		 String showNames = "";
		 for(int i = 0;i < borrowbookcolumnnames.length;i++) {
			 if(i == borrowbookcolumnnames.length -1) {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i];
			 }else {
				 showNames=showNames+tableName+"."+borrowbookcolumnnames[i]+",";
			 }
		 }
		  String sql = " select  "+ showNames + " from  account,book,borrowbook where " +sb.toString();
		  System.out.println(sql);
			List<BorrowBook> borrowbookList = JdbcUtil.query(sql, new BorrowBookResultSetHandler(), offset,pageSize);
	         PageBean<BorrowBook> pageBean = new PageBean<>();
		   // 计算当前页面
		   int currPage = 0;
		   if (pageSize != 0) {
			   // 计算当前页面
			   currPage = offset / pageSize + 1;
		   }
		   //计算总页数
		   int totalPage = (total % pageSize == 0)? (total/pageSize):  (total/pageSize+1);
		   pageBean.setCurrPage(currPage);
		   pageBean.setList(borrowbookList);
		   pageBean.setPageSize(pageSize);
		   pageBean.setTotalPage(totalPage);
		   pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public int getAllCount() {
		return this.getCount("select count(*) from " + tableName);
	}


	@Override
	public int getAllBorrowBookRecordCount(String fromDate, String toDate) {
		if(fromDate!=null&&fromDate.trim().length() == 0) {
			fromDate = null;
		}
		if(toDate!=null&&toDate.trim().length() == 0) {
			toDate = null;
		}
		String sql = "select count(*) from " + tableName;
		if(fromDate!=null && toDate != null) {
			sql = sql+" where  borrowtime Between  '" + fromDate +"' and '" + toDate+"'";
		}else if(fromDate!=null && toDate == null) {
		
			sql = sql+" where  borrowtime >  '" + fromDate+"'";
		}else if(fromDate==null && toDate != null) {
			sql = sql+" where  borrowtime < '" + toDate+"'";
		}else {
			return 0;
		}
		
		return this.getCount(sql);
	}


	@Override
	public int getAllReturnBookRecordCount(String fromDateStr, String toDateStr) {
		String sql = "select count(*) from " + tableName;
		if(fromDateStr!=null&&fromDateStr.trim().length() == 0) {
			fromDateStr = null;
		}
		if(toDateStr!=null&&toDateStr.trim().length() == 0) {
			toDateStr = null;
		}
		if(fromDateStr!=null && toDateStr != null) {
			sql = sql+" where  returntime Between  '" + fromDateStr +"' and '" + toDateStr+"'";
		}else if(fromDateStr!=null && toDateStr == null) {
		
			sql = sql+" where  returntime >  '" + fromDateStr+"'";
		}else if(fromDateStr==null && toDateStr != null) {
			sql = sql+" where  returntime < '" + toDateStr+"'";
		}else {
			return 0;
		}
		
		return this.getCount(sql);
	}


	@Override
	public List<BorrowBook> getAllBorrowBookRecord(String fromDateStr, String toDateStr) {
		String sql = "select * from " + tableName;
		if(fromDateStr!=null&&fromDateStr.trim().length() == 0) {
			fromDateStr = null;
		}
		if(toDateStr!=null&&toDateStr.trim().length() == 0) {
			toDateStr = null;
		}
		if(fromDateStr!=null && toDateStr != null) {
			sql = sql+" where  borrowtime Between  ?  and  ?";
		   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),fromDateStr,toDateStr);
		}else if(fromDateStr!=null && toDateStr == null) {
			sql = sql+" where  borrowtime > ?";
			   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),fromDateStr);
		}else if(fromDateStr==null && toDateStr != null) {
			sql = sql+" where  borrowtime < ?";
			   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),toDateStr);
		}
		   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler());
	}


	@Override
	public List<BorrowBook> getAllReturnBookRecord(String fromDateStr, String toDateStr) {
		String sql = "select * from " + tableName;
		if(fromDateStr!=null&&fromDateStr.trim().length() == 0) {
			fromDateStr = null;
		}
		if(toDateStr!=null&&toDateStr.trim().length() == 0) {
			toDateStr = null;
		}
		if(fromDateStr!=null && toDateStr != null) {
			sql = sql+" where  returntime Between  ?  and  ?";
		   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),fromDateStr,toDateStr);
		}else if(fromDateStr!=null && toDateStr == null) {
			sql = sql+" where  returntime > ?";
			   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),fromDateStr);
		}else if(fromDateStr==null && toDateStr != null) {
			sql = sql+" where  returntime < ?";
			   return	JdbcUtil.query(sql, new BorrowBookResultSetHandler(),toDateStr);
		}
		
		 return	JdbcUtil.query(sql, new BorrowBookResultSetHandler());
	}


}

class BorrowBookResultSetHandler implements ResultSetHandler<List<BorrowBook>> {
    private ReaderDao readerDao = (ReaderDao) DaoFactory.newInstanceDao("ReaderDao");
    private BookDao bookDao = (BookDao) DaoFactory.newInstanceDao("BookDao");
	@Override
	public List<BorrowBook> resultSetHandler(ResultSet resultSet) {
		List<BorrowBook> list = new ArrayList<BorrowBook>();
		try {
			BorrowBook borrowBook = null;
			while(resultSet.next()) {
				int   id = resultSet.getInt("id");
				int  accountid = resultSet.getInt("accountid");
				int  bookid = resultSet.getInt("bookid");
				String  borrowtime = resultSet.getString("borrowtime");
				String  returntime = resultSet.getString("returntime");
				Account reader = readerDao.get(accountid);
				Book book = bookDao.get(bookid);
				borrowBook = new BorrowBook();
				borrowBook.setIsBorrowedBook(book);
				borrowBook.setId(id);
				borrowBook.setReader(reader);
				borrowBook.setReturnTime(DateUtil.StrToDate(returntime));
				borrowBook.setBorrowTime(DateUtil.StrToDate(borrowtime));
				list.add(borrowBook);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}


