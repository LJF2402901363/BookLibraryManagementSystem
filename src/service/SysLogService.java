package service;

import domain.SysLog;

/**
 * @author 陌意随影
 TODO :
 *2020年3月17日  下午5:26:46
 */
public interface SysLogService extends BaseService<SysLog>{

	/**
	 * 通过指定的id删除记录
	 * @param id
	 * @return 返回是否删除成功
	 */
	boolean  removeSysLogById(Integer id);

	/**
	 * 通过id获取指定的日志记录
	 * @param id
	 * @return 返回获得的记录
	 */
	SysLog findSysLogById(Integer id);

	/**
	 * 更新指定用户
	 * @param sysLog
	 */
	boolean  updateSysLog(SysLog sysLog);
    /**
     * 保存到数据库
     * @param log
     * @return 返回是否保存成功
     */
	boolean saveSysLog(SysLog log);
/**
 * 获取指定时间内新增的用户数量
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
int getAllDelAccountCount(String todayTimeStr, String string);

}
