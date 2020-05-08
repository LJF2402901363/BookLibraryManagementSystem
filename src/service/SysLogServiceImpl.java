package service;

import java.util.Map;

import dao.SysLogDao;
import domain.PageBean;
import domain.SysLog;
import factory.DaoFactory;

/**
 * @author 陌意随影
 TODO :SysLog业务逻辑实现类
 *2020年3月17日  下午5:27:35
 */
public class SysLogServiceImpl implements SysLogService {
   private SysLogDao dao = (SysLogDao) DaoFactory.newInstanceDao("SysLogDao");
	@Override
	public PageBean<SysLog> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition, String fromDateTime,
			String toDateTime, String tableSortName, String tableOrder) {
		return dao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent,condition, fromDateTime, toDateTime, tableSortName, tableOrder);
	}

	@Override
	public boolean removeSysLogById(Integer id) {
		return dao.remove(id) ==1;
	}

	@Override
	public SysLog findSysLogById(Integer id) {
		return dao.get(id);
	}

	@Override
	public boolean updateSysLog(SysLog sysLog) {
	return 	dao.upDate(sysLog) ==1;
		
	}

	@Override
	public boolean saveSysLog(SysLog log) {
		return dao.save(log) == 1;
	}

	@Override
	public int getAllNewAccountCount(String todayTimeStr, String toTimeStr) {
		// TODO Auto-generated method stub
		return dao.getAllNewAccountCount(todayTimeStr,toTimeStr);
	}

	@Override
	public int getAllDelAccountCount(String todayTimeStr, String toTimeStr) {
		return dao.getAllDelAccountCount(todayTimeStr,toTimeStr);
	}
    
}
