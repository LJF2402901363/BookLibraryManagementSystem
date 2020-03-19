package service;

import java.util.Map;

import domain.Account;
import domain.PageBean;

/**
 * 
 * @author 陌意随影
 TODO :基础的业务逻辑接口
 *2020年2月18日  下午5:15:49
 */
public interface BaseService<T> {

	/**
	 * @Description : 通过数据的起始位置，页面大小，搜索的条件，排序的名称以及排序方式来进行获得排序的对象
	 * @Date 18:59 2020/2/2 0002
	 * @param offset
	 * @param pageSize           页面的大小
	 * @param fuzzySearchContent 模糊搜索的内容
	 * @Param condition 搜索的条件
	 * @param tableSortName      排序的列名
	 * @param tableOrder         ：排序的方式
	 * @return domain.PageBean<domain.Book>
	 **/
	PageBean<T> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent,Map<String, String> condition, String fromDateTime,String toDateTime, String tableSortName,
			String tableOrder);

	
}
