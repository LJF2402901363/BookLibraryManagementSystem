package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import domain.Account;
import domain.PageBean;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

/**
 * @author 陌意随影
 TODO :管理员的dao抽象类
 *2020年3月18日  下午12:48:24
 */
public abstract class AbstractAccountDao  implements AccountDao{
	private static String  accountTable = ConfigContant.ACCOUNT_TABLE;
	private int type = 0;
	@SuppressWarnings("javadoc")
	public  AbstractAccountDao(int type ) {
		this.type = type;
		
	}
	@Override
	public int save(Account account) {
		// type | sex | hobby | signature
		String sql = "insert into " +accountTable
				+ "(name,password, createTime, status,type,sex,hobby,signature,age,headImgPath) values(?,?,?,?,?,?,?,?,?,?)";
		int result = JdbcUtil.upDate(sql, account.getName(), account.getPassword(), account.getCreateTime(),
				account.getStatus(), account.getType(), account.getSex(), account.getHobby(), account.getSignature(),
				account.getAge(),account.getHeadImgPath());
		return result;
	}

	@Override
	public int upDate(Account account) {
		// password | createTime | status | type | sex | hobby | signature | age |
		String sql = "update  " + accountTable
				+ "  set name=?,password=?,createTime=?,status=?,type=?,sex=?,hobby=?,signature=?,age=?,headImgPath=? where id=?";
		int result = JdbcUtil.upDate(sql, account.getName(), account.getPassword(), account.getCreateTime(),
				account.getStatus(), account.getType(), account.getSex(), account.getHobby(), account.getSignature(),
				account.getAge(), account.getHeadImgPath(),account.getId());
		return result;
	}

	@Override
	public int remove(int id) {
		String sql = "delete from " + accountTable + " where id=?";
		int result = JdbcUtil.upDate(sql, id);
		return result;
	}

	@Override
	public Account get(int id) {
		String sql = "select *from " + accountTable +" where id=?";
	 List<Account> list = JdbcUtil.query(sql, new AccountResultSetHandler(),id);
	 if(list == null || list.size() != 1) {
		 return null;
	 }
		return list.get(0);
	}

	@Override
	public List<Account> getAll() {
		String sql = "select *from " + accountTable;
		List<Account> list = JdbcUtil.query(sql, new AccountResultSetHandler());
		return list;
	}

	@Override
	public Account login(Account account) {
		String sql = "select *from " + accountTable + " where  binary name=? and  binary password=?";
		List<Account> query = JdbcUtil.query(sql, new AccountResultSetHandler(), account.getName(),
				account.getPassword());
		if (query.size() != 1) {
			return null;
		}
		return query.get(0);
	}


	@Override
	public Account findAccountByName(String name) {
		String sql = "select *from " +accountTable + " where  binary name=? ";
		List<Account> query = JdbcUtil.query(sql, new AccountResultSetHandler(), name);
		if (query.size() != 1) {
			return null;
		}
		return query.get(0);
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
	public int getAllCount() {
		return this.getCount("select count(*) from " + accountTable);
	}

	@Override
	public int getAllAdministratorCount() {
		return this.getCount("select count(*) from " + accountTable+" where type="+Account.TYPE_ADMINISTRATOR);
	}

	@Override
	public int getAllReaderCount() {
		return this.getCount("select count(*) from " + accountTable+" where type="+Account.TYPE_USER);

	}
	private PageBean<Account> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder, int type) {
		StringBuilder sb = new StringBuilder();
		// 获取account数据库的列名
		String[] accountColumnNames = ConfigContant.ACCOUNTCOLUMNNAMES;
		// 获取模糊查询的关键语句
		if (accountColumnNames != null) {
			int len = accountColumnNames.length;
			boolean fla = false;
			if (fuzzySearchContent != null && fuzzySearchContent.trim().length() != 0) {
				for (int i = 0; i < len; i++) {
					// 要排除id模糊查询，id不参与查询
					if (!"id".equalsIgnoreCase(accountColumnNames[i]) ) {
						fla = true;
						if (i == len - 1) {
							sb.append(accountColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"");
						} else {
							sb.append(accountColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"")
							.append(" or ");
						}
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

		String totalSql = "select count(*)from " + accountTable ;
		// 标识要不要加模糊查询的语句sql要不要加where
		boolean fla = false;
		if (sb.toString().trim().length() != 0 ) {
			totalSql = totalSql + " where  " + sb.toString()+" and type="+type;
			fla = true;
		}else {
			totalSql = "select count(*)from " + accountTable +" where type="+type;
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
		String sql = "select *from " + accountTable;
		if (fla) {
			sql = sql + " where  type ="+type +" and " + sb.toString() ;
		} else {
			sql = sql + " where type = "+type +" "+sb.toString() ;
		}
		
		List<Account> accountList = JdbcUtil.query(sql, new AccountResultSetHandler(), offset, pageSize);
		// 计算当前页面
		int currPage = 0;
		if (pageSize != 0) {
			// 计算当前页面
			currPage = offset / pageSize + 1;
		}
		//计算总页数
		int totalPage = (total+pageSize-1)/pageSize;
		PageBean<Account> pageBean = new PageBean<>();
		pageBean.setPageSize(pageSize);
		pageBean.setOffset(offset);
		pageBean.setCurrPage(currPage);
		pageBean.setTotal(total);
		pageBean.setList(accountList);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	@Override
	public PageBean<Account> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,
			Map<String, String> condition, String fromDateTime, String toDateTime, String tableSortName,
			String tableOrder) {
		return this.fuzzySearchByPage(offset, pageSize, fuzzySearchContent, condition, fromDateTime, toDateTime, tableSortName, tableOrder, this.type );
	}
}
class AccountResultSetHandler implements ResultSetHandler<List<Account>> {

	@Override
	public List<Account> resultSetHandler(ResultSet resultSet) {
		List<Account> list = new ArrayList<>();
		EncapsulateJavaBean.encapsulateJavaBean(list, Account.class, resultSet);
		return list;
	}
}