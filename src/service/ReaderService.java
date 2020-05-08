package service;

import domain.Account;

/**
 * @author 陌意随影
 * @create 2020-03-11 13:49
 * @desc 用户业务逻辑类的接口
 **/
public interface ReaderService extends BaseService<Account>{
	/**
	 * @Description :通过只封装了用户名和密码的账户来向数据库中查询并获取完整的用户信息
	 * @Date 13:50 2020/2/1 0001
	 * @Param * @param account ： 封装了用户名和密码的账户
	 * @return domain.Account 查找到则返回完整信息的对象，否则返回null
	 **/
	Account login(Account account);


	/**
	 * @Description :将指定的用户添加到数据库中去
	 * @Date 12:51 2020/2/5 0005
	 * @Param * @param user ：
	 * @return boolean
	 **/
	boolean saveAccount(Account user);

	/**
	 * @Description :通过账号名来获取用户
	 * @Date 13:49 2020/2/5 0005
	 * @Param * @param user ：
	 * @return domain.Account
	 **/
	Account findAccountByName(String name);
	/**
	 * @Description :更新指定用户数据
	 * @Date 13:14 2020/2/9 0009
	 * @Param * @param account ：
	 * @return boolean
	 **/
	boolean updateAccount(Account account);

	/**
	 * @Description :向数据库中添加新的用户账号
	 * @Date 14:33 2020/2/10 0010
	 * @Param * @param account ：
	 * @return boolean
	 **/
	boolean addNewAccount(Account account);


	/**
	 * 通过id查找指定用户
	 * @param id
	 * @return   Account
	 */
	Account findAccountById(int  id);


	/**
	 * 通过指定的id来删除对应数据
	 * @param id
	 */
	boolean removeAccountById(int id);


	/**
	 * @return 获取所有管理员的总个数
	 */
	int getAllAdministratorCount();


	/**
	 * @return 获取所有读者的总个数
	 */
	int getAllReaderCount();
}
