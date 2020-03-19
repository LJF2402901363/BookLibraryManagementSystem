package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BookCateDao;
import dao.ResultSetHandler;
import domain.BookCate;
import domain.PageBean;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

/**
 * @author 陌意随影
 TODO :
 *2020年3月8日  下午4:09:19
 */
public class BookCateDaoImpl  implements BookCateDao{
    private static final String tableName = ConfigContant.BOOKCATE_TABLE;
	@Override
	public int save(BookCate t) {
		// parentTypeCode | name
		String sql = "insert into "+tableName+"(parentTypeCode,name,createTime)  values(?,?,?)";
		return JdbcUtil.upDate(sql, t.getParentTypeCode(),t.getName(),t.getCreateTime());
	}

	@Override
	public int upDate(BookCate t) {
		String sql = "update  "+tableName+" set parentTypeCode=?,name=?,createTime=? where id=?";
		return  JdbcUtil.upDate(sql, t.getParentTypeCode(),t.getName(),t.getCreateTime(),t.getId());
	}

	
	@Override
	public int remove(int id) {
		String sql = "delete  from "+tableName+"  where id=?";
		return  JdbcUtil.upDate(sql,id);
	}

	@Override
	public BookCate get(int id) {
		String sql = "select*  from "+tableName+"  where id=?";
		List<BookCate> list = JdbcUtil.query(sql, new BookCateResultSetHandler(),id);
		return list.size() == 1?list.get(0):null;
	}

	@Override
	public List<BookCate> getAll() {
		String sql = "select*  from "+tableName;
		List<BookCate> list = JdbcUtil.query(sql, new BookCateResultSetHandler());
		return list.size() == 0 ?null:list;
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
	public PageBean<BookCate> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		StringBuilder sb = new StringBuilder();
		// 获取book数据库的列名
		String[] bookColumnNames = ConfigContant.BOOKCATECOLUMNNAMES;
		// 获取模糊查询的关键语句
		if (bookColumnNames != null) {
			int len = bookColumnNames.length;
			boolean fla  =false;
			if (fuzzySearchContent != null && fuzzySearchContent.trim().length() != 0) {
				for (int i = 0; i < len; i++) {
					// 要排除id模糊查询，id不参与查询
					if ("id".equalsIgnoreCase(bookColumnNames[i])) {
						continue;
					}
					fla =true;
					if (i == len - 1) {
						sb.append(bookColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"");
					} else {
						sb.append(bookColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"")
								.append(" or ");
					}
				}
				
			}
			if (condition != null && condition.size() >= 0) {
				int index =1;
				for (Map.Entry<String, String> entry : condition.entrySet()) {
					String value = entry.getValue();
					if (value != null && value.trim().length() != 0) {
						if(index == 1) {
							if(fla) {
								sb.append(" and ");
							}
							sb.append(" "+entry.getKey()).append(" like \"").append(entry.getValue())
							.append("\"");
							index=0;
						}else {
							sb.append(" and ").append(entry.getKey()).append(" like \"").append(entry.getValue())
							.append("\"");
						}
					}
				}
       
			}
		}
		if(fromDateTime != null && fromDateTime.trim().length() == 0) {
			fromDateTime = null;
		}
		if(toDateTime != null && toDateTime.trim().length() == 0) {
			toDateTime = null;
		}
		if(fuzzySearchContent != null&& fuzzySearchContent.trim().length() != 0) {
			if(fromDateTime != null || toDateTime != null) {
				sb.append("  and ");
			}
		}
		
		
		if(fromDateTime != null && toDateTime != null ) {
				sb.append("  (  createTime ").append(" Between '").append(fromDateTime).append("' and '").append(toDateTime).append("')");
		}else if(fromDateTime == null&& toDateTime != null) {
			sb.append("   (  createTime ").append(" > '").append(toDateTime).append("')");
		}else if(fromDateTime != null&& toDateTime == null) {
			sb.append("   (  createTime ").append(" < '").append(fromDateTime).append("')");
		}
		String totalSql = "select count(*)from " + tableName ;
		//标识要不要加模糊查询的语句sql要不要加where
		boolean fla = false;
		if(sb.toString().trim().length()!=0) {
			totalSql = totalSql+" where  " + sb.toString();
			fla = true;
		}
		
		// 获取总记录数
		int total = this.getCount(totalSql);
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
		String sql = "select *from " + tableName ;
		if(fla) {
			sql = sql +" where  " + sb.toString();
		}else {
			sql = sql+sb.toString();
		}
		// 模糊查询的sql语句
		List<BookCate> bookList = JdbcUtil.query(sql, new BookCateResultSetHandler(), offset, pageSize);
		// 计算当前页面
		int currPage = 0;
		if (pageSize != 0) {
			// 计算当前页面
			currPage = offset / pageSize + 1;
		}
		//计算总页数
		int totalPage = (total % pageSize == 0)? (total/pageSize):  (total/pageSize+1);
		PageBean<BookCate> pageBean = new PageBean<>();
		pageBean.setTotalPage(totalPage);
		pageBean.setPageSize(pageSize);
		pageBean.setOffset(offset);
		pageBean.setCurrPage(currPage);
		pageBean.setTotal(total);
		pageBean.setList(bookList);
		return pageBean;
	}

	@Override
	public BookCate findBookCateByName(String name) {
		 String sql = "select *from " + tableName +" where name =?";
		   List<BookCate> list = JdbcUtil.query(sql, new BookCateResultSetHandler(), name);
		   if(list.size() == 1) {
			   return list.get(0);
		   }
		return null;
	}


	@Override
	public int getAllCount() {
		return this.getCount("select count(*) from " + tableName );
	}

}
class BookCateResultSetHandler implements ResultSetHandler<List<BookCate>> {
	@Override
	public List<BookCate> resultSetHandler(ResultSet resultSet) {
		List<BookCate> list = new ArrayList<>();
		EncapsulateJavaBean.encapsulateJavaBean(list, BookCate.class, resultSet);
		return list;
	}
}