package dao;

import domain.Account;
import domain.PageBean;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:33
 * @desc 账户的dao
 **/
public interface AccountDao extends DAO<Account> {
	/**
	 * @Description :通过只封装了用户名和密码的账户来向数据库中查询并获取完整的用户信息
	 * @Date 13:50 2020/2/1 0001
	 * @Param * @param account ： 封装了用户名和密码的账户
	 * @return domain.Account 查找到则返回完整信息的对象，否则返回null
	 **/
	Account login(Account account);
    
	/**
	 * @Description :通过账号名来获取用户
	 * @Date 13:49 2020/2/5 0005
	 * @Param * @param user ：
	 * @return domain.Account
	 **/
	Account findAccountByName(String name);

	/**
	 * @return  获取所有的管理员数量个数
	 */
	int getAllAdministratorCount();

	/** 
	 * @return  获取所有的读者数量个数
	 */
	int getAllReaderCount();
	
	
}
