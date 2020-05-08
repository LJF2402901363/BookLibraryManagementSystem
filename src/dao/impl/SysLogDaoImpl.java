package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import dao.ResultSetHandler;
import dao.SysLogDao;
import domain.PageBean;
import domain.SysLog;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

/**
 * @author 陌意随影
 TODO :日志的数据库接口实现类
 *2020年3月17日  下午5:03:45
 */
public class SysLogDaoImpl implements SysLogDao {
	private static String  sysLogName = ConfigContant.SYSLOG_TABLE;
	@Override
	public int save(SysLog syslog) {
		// operator | createTime          | operationType | details |
		String sql = "insert into " +sysLogName
				+ "( operator, createTime, operationType,details) values(?,?,?,?)";
		int result = JdbcUtil.upDate(sql, syslog.getOperator(),syslog.getCreateTime(),syslog.getOperationType(),syslog.getDetails());
		return result;
	}

	@Override
	public int upDate(SysLog syslog) {
		// password | createTime | status | type | sex | hobby | signature | age |
		String sql = "update  " + sysLogName
				+ "  set operator=?,createTime=?,operationType=?,details=? where id=?";
		int result = JdbcUtil.upDate(sql,syslog.getOperator(),
				syslog.getCreateTime(),syslog.getOperationType()
				,syslog.getDetails(),syslog.getId() );
		return result;
	}

	@Override
	public int remove(int id) {
		String sql = "delete from " + sysLogName + " where id=?";
		int result = JdbcUtil.upDate(sql, id);
		return result;
	}

	@Override
	public SysLog get(int id) {
		String sql = "select *from " + sysLogName +" where id=?";
	 List<SysLog> list = JdbcUtil.query(sql, new SysLogResultSetHandler(),id);
	 if(list == null || list.size() != 1) {
		 return null;
	 }
		return list.get(0);
	}

	@Override
	public List<SysLog> getAll() {
		String sql = "select *from " + sysLogName;
		List<SysLog> list = JdbcUtil.query(sql, new SysLogResultSetHandler());
		return list;
	}
	@Override
	public PageBean<SysLog> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,String fromDateTime,String toDateTime,
		String tableSortName, String tableOrder) {
		StringBuilder sb = new StringBuilder();
		// 获取account数据库的列名
		String[] accountColumnNames = ConfigContant.SYSLOGCOLUMNNAMES;
		// 获取模糊查询的关键语句
		if (accountColumnNames != null) {
			int len = accountColumnNames.length;
			boolean fla = false;
			if (fuzzySearchContent != null && fuzzySearchContent.trim().length() != 0) {
				for (int i = 0; i < len; i++) {
					// 要排除id模糊查询，id不参与查询
					if ("id".equalsIgnoreCase(accountColumnNames[i])) {
						continue;
					}
					fla = true;
					if (i == len - 1) {
						sb.append(accountColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"");
					} else {
						sb.append(accountColumnNames[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"")
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

		String totalSql = "select count(*)from " + sysLogName;
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
		String sql = "select *from " + sysLogName;
		if (fla) {
			sql = sql + " where  " + sb.toString();
		} else {
			sql = sql + sb.toString();
		}
		List<SysLog> accountList = JdbcUtil.query(sql, new SysLogResultSetHandler(), offset, pageSize);
		// 计算当前页面
		int currPage = 0;
		if (pageSize != 0) {
			// 计算当前页面
			currPage = offset / pageSize + 1;
		}
		//计算总页数
		int totalPage = (total+pageSize-1)/pageSize;
		PageBean<SysLog> pageBean = new PageBean<>();
		pageBean.setPageSize(pageSize);
		pageBean.setOffset(offset);
		pageBean.setCurrPage(currPage);
		pageBean.setTotal(total);
		pageBean.setList(accountList);
		pageBean.setTotalPage(totalPage);
		return pageBean;
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
		return this.getCount("select count(*) from " + sysLogName);
	}

	@Override
	public int getAllNewAccountCount(String todayTimeStr, String toTimeStr) {
		if(todayTimeStr!=null&&todayTimeStr.trim().length() == 0) {
			todayTimeStr = null;
		}
		if(toTimeStr!=null&&toTimeStr.trim().length() == 0) {
			toTimeStr = null;
		}
		String sql = "select count(*) from " + sysLogName;
		if(todayTimeStr!=null && toTimeStr != null) {
			sql = sql+" where   createTime Between  '" + todayTimeStr +"' and '" + toTimeStr+"'";
		}else if(todayTimeStr!=null && toTimeStr == null) {
		
			sql = sql+" where   createTime >  '" + todayTimeStr+"'";
		}else if(todayTimeStr==null && toTimeStr != null) {
			sql = sql+" where   createTime < '" + toTimeStr+"'";
		}else {
			return 0;
		}
		sql = sql +" and operationType='"+SysLog.OPERATIONTYPE_ADDNEWACCOUNT+"'";;
		return this.getCount(sql);
	}

	@Override
	public int getAllDelAccountCount(String todayTimeStr, String toTimeStr) {
		if(todayTimeStr!=null&&todayTimeStr.trim().length() == 0) {
			todayTimeStr = null;
		}
		if(toTimeStr!=null&&toTimeStr.trim().length() == 0) {
			toTimeStr = null;
		}
		String sql = "select count(*) from " + sysLogName;
		if(todayTimeStr!=null && toTimeStr != null) {
			sql = sql+" where   createTime Between  '" + todayTimeStr +"' and '" + toTimeStr+"'";
		}else if(todayTimeStr!=null && toTimeStr == null) {
		
			sql = sql+" where   createTime >  '" + todayTimeStr+"'";
		}else if(todayTimeStr==null && toTimeStr != null) {
			sql = sql+" where   createTime < '" + toTimeStr+"'";
		}else {
			return 0;
		}
		sql = sql +" and operationType='"+SysLog.OPERATIONTYPE_REMOVEACCOUNT+"'";;
		return this.getCount(sql);
	}
}

class SysLogResultSetHandler implements ResultSetHandler<List<SysLog>> {

	@Override
	public List<SysLog> resultSetHandler(ResultSet resultSet) {
		List<SysLog> list = new ArrayList<>();
		EncapsulateJavaBean.encapsulateJavaBean(list, SysLog.class, resultSet);
		return list;
	}
}