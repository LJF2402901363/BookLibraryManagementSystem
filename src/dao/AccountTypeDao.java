package dao;

import domain.AccountType;

/**
 * @author 陌意随影
 TODO :用户类型接口
 *2020年2月23日  下午8:49:26
 */
public interface AccountTypeDao extends DAO<AccountType> {
	/**
	 *通过指定的名字查找角色对象
	 * @param name
	 * @return  AccountType
	 */
	AccountType findAccountTypeByName(String name);

	/**
	 * 通过指定的id查找角色对象
	 * @param id
	 * @return  AccountType
	 */
	AccountType findAccountTypeById(int id);

}
