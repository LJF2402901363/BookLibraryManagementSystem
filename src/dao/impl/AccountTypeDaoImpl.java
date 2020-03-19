package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.AccountTypeDao;
import dao.ResultSetHandler;
import domain.Account;
import domain.AccountType;
import domain.PageBean;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

/**
 * @author 陌意随影
 TODO :
 *2020年2月23日  下午8:49:59
 */
public class AccountTypeDaoImpl implements AccountTypeDao{
   private  final static String TABLENAME = ConfigContant.ACCOUNTTYPE_TABLE ;
	@Override
	public int save(AccountType t) {
		String sql = "insert into " + TABLENAME +"( typeName,canborrowdays ,canborrowTimes , canborrowbooks) values(?,?,?,?)";
		int upDate = JdbcUtil.upDate(sql,t.getTypeName(), t.getCanborrowdays(),t.getCanborrowTimes(),t.getCanborrowbooks());
		return upDate;
	}

	@Override
	public int upDate(AccountType t) {
		String sql = "update " +TABLENAME +" set typeName=?, canborrowdays=? ,canborrowTimes=? ,  canborrowbooks=? where id=?";
		int upDate = JdbcUtil.upDate(sql, t.getTypeName(),t.getCanborrowdays(),t.getCanborrowTimes(),t.getCanborrowbooks(),t.getId());
		return upDate;
	}

	@Override
	public int remove(int id) {
		String sql = "delete from "+ TABLENAME + " where id=?";
		int upDate = JdbcUtil.upDate(sql, id);
		return upDate;
	}

	@Override
	public AccountType get(int id) {
		String sql = "select *from " + TABLENAME+"  where id=?";
		List<AccountType> list = JdbcUtil.query(sql, new AccountTypeResultSetHander(), id);
		if(list == null || list.size()!=1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<AccountType> getAll() {
		String sql = "select *from " + TABLENAME;
		List<AccountType> list = JdbcUtil.query(sql, new AccountTypeResultSetHander());
		if(list == null ||list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public int getCount(String sql) {
		
		return JdbcUtil.query(sql,new ResultSetHandler<Integer>() {

			@Override
			public Integer resultSetHandler(ResultSet resultSet) {
				try {
					if(resultSet.next()) {
						return resultSet.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
			}
			
		});
	}

	@Override
	public AccountType findAccountTypeByName(String name) {
		String sql = "select *from " + TABLENAME + " where  binary  typeName=? ";
		List<AccountType> query = JdbcUtil.query(sql, new AccountTypeResultSetHander(), name);
		if (query.size() != 1) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public PageBean<AccountType> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,String fromDateTime,String toDateTime,
		String tableSortName, String tableOrder) {
		StringBuilder sb = new StringBuilder();
		// 获取account数据库的列名
		String[] columnNames = ConfigContant.ACCOUNTTYPECOLUMNNAMES;
		// 获取模糊查询的关键语句
		if (columnNames != null) {
			int len = columnNames.length;
			boolean fla = false;
			if (fuzzySearchContent != null && fuzzySearchContent.trim().length() != 0) {
				for (int i = 0; i < len; i++) {
					// 要排除id模糊查询，id不参与查询
					if ("id".equalsIgnoreCase(columnNames[i])) {
						continue;
					}
					fla =true;
					if (i == len - 1) {
						sb.append(columnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"");
					} else {
						sb.append(columnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"")
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
		if (fromDateTime != null && fromDateTime.trim().length() == 0) {
			fromDateTime = null;
		}
		if (toDateTime != null && toDateTime.trim().length() == 0) {
			toDateTime = null;
		}
		if (fuzzySearchContent != null && fuzzySearchContent.trim().length() != 0) {
			if (fromDateTime != null || toDateTime != null) {
				sb.append("  and ");
			}
		}

		if (fromDateTime != null && toDateTime != null) {
			sb.append("  (  createTime ").append(" Between '").append(fromDateTime).append("' and '").append(toDateTime)
					.append("')");
		} else if (fromDateTime == null && toDateTime != null) {
			sb.append("   (  createTime ").append(" > '").append(toDateTime).append("')");
		} else if (fromDateTime != null && toDateTime == null) {
			sb.append("   (  createTime ").append(" < '").append(fromDateTime).append("')");
		}

		String totalSql = "select count(*)from " + TABLENAME;
		// 标识要不要加模糊查询的语句sql要不要加where
		boolean fla = false;
		if (sb.toString().trim().length() != 0) {
			totalSql = totalSql + " where  " + sb.toString();
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
		// 模糊查询的sql语句
		String sql = "select *from " + TABLENAME;
		if (fla) {
			sql = sql + " where  " + sb.toString();
		} else {
			sql = sql + sb.toString();
		}
		List<AccountType> accountList = JdbcUtil.query(sql, new AccountTypeResultSetHander(), offset, pageSize);
		// 计算当前页面
		int currPage = 0;
		if (pageSize != 0) {
			// 计算当前页面
			currPage = offset / pageSize + 1;
		}
		//计算总页数
		int totalPage = (total+pageSize-1)/pageSize;
		PageBean<AccountType> pageBean = new PageBean<>();
		pageBean.setPageSize(pageSize);
		pageBean.setOffset(offset);
		pageBean.setCurrPage(currPage);
		pageBean.setTotal(total);
		pageBean.setList(accountList);
		pageBean.setTotalPage(totalPage);
		System.out.println(pageBean);
		return pageBean;
	}

	@Override
	public AccountType findAccountTypeById(int id) {
		String sql = "select *from  "+TABLENAME + " where id = ?";
		List<AccountType> list = JdbcUtil.query(sql, new AccountTypeResultSetHander(), id);
		  if(list == null || list.size() != 1) {
			  return null;
		  }
		return list.get(0);
	}

	@Override
	public int getAllCount() {
		return this.getCount("select count(*) from " + TABLENAME);
	}
 
}
class AccountTypeResultSetHander implements ResultSetHandler<List<AccountType>>{

	@Override
	public List<AccountType> resultSetHandler(ResultSet resultSet) {
		List<AccountType> list = new ArrayList<>();
		EncapsulateJavaBean.encapsulateJavaBean(list, AccountType.class, resultSet);
		return list;
	}
	
}

