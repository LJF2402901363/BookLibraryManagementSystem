package dao;

import java.util.List;
import java.util.Map;

import domain.Account;
import domain.PageBean;
import domain.SysLog;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:30
 * @desc 所有数据库访问的总接口
 **/
public interface DAO<T> {
	/**
	 * @Description :添加对应的对象，添加成功返回1，否则返回0
	 * @Date 19:49 2020/2/2 0002
	 * @Param * @param id ：
	 * @return int
	 **/
	int save(T t);

	/**
	 * @Description :更新对应的对象，更新成功返回1，否则返回0
	 * @Date 19:49 2020/2/2 0002
	 * @Param * @param id ：
	 * @return int
	 **/
	int upDate(T t);

	/**
	 * @Description :删除指定id对应的对象，删除成功返回1，否则返回0
	 * @Date 19:49 2020/2/2 0002
	 * @Param * @param id ：
	 * @return int
	 **/
	int remove(int id);

	/**
	 * @Description :获取指定id对应的对象
	 * @Date 19:49 2020/2/2 0002
	 * @Param * @param id ：
	 * @return T
	 **/
	T get(int id);

	/**
	 * @Description :获取所有的数据
	 * @Date 19:48 2020/2/2 0002
	 * @Param * @param ：
	 * @return java.util.List<T>
	 **/
	List<T> getAll();
	/**
	 * @return 获取所有的数量
	 */
	int getAllCount();
	/**
	 * @Description :获取总记录数
	 * @Date 12:40 2020/2/2 0002
	 * @Param * @param ：
	 * @return int
	 **/
	int getCount(String sql);
	/**
	 * @Description : 通过数据的起始位置，页面大小，模糊搜索的条件，搜索的条件集合，排序的名称以及排序方式来进行获得排序的对象
	 * @Date 18:59 2020/2/2 0002
	 * @Param * @param offset
	 * @param pageSize           页面的大小
	 * @param fuzzySearchContent 模糊搜索的内容
	 * @param tableSortName      排序的列名
	 * @param tableOrder         ：排序的方式
	 * @return domain.PageBean<domain.Book>
	 **/
	PageBean<T> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition, String fromDateTime,String toDateTime,String tableSortName,
			String tableOrder);
}
