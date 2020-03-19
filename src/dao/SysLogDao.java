package dao;

import java.util.Map;

import domain.PageBean;
import domain.SysLog;

/**
 * @author 陌意随影
 TODO :日志的数据库接口
 *2020年2月27日  下午4:59:43
 */
public interface SysLogDao extends DAO<SysLog> {
	/**
	 * 获取指定时间内新增的用户
	 * @param todayTimeStr
	 * @param toTimeStr
	 * @return 返回获取的数量
	 */
	int getAllNewAccountCount(String todayTimeStr, String toTimeStr);
	/**
	 *获取指定日期删除用户数量
	 * @param todayTimeStr
	 * @param string
	 * @return 返回删除的总数
	 */
	int getAllDelAccountCount(String todayTimeStr, String toTimeStr);

}
