package service;

import domain.AccountType;

/**
 * @author 陌意随影
 TODO :
 *2020年2月23日  下午9:07:27
 */
public interface AccountTypeService extends BaseService<AccountType> {

	/**
	 * 通过id来查找元素
	 * @param id
	 * @return AccountType
	 */
	AccountType findAccountTypeById(int id);

	/**
	 * 通过指定的id删除对象
	 * @param id
	 * @return 返回是否删除成功
	 */
	boolean removeAccountTypeById(int  id);

	/**
	 * 通过指定的名字查找是否存在
	 * @param name
	 * @return AccountType
	 */
	AccountType findAccountTypeByName(String name);

	/**
	 * 将新的类型写入到数据库中去
	 * @param newAccountType
	 */
	boolean saveNewAccountType(AccountType newAccountType);

	/**
	 * 更新指定的对象并返回是否更新成功
	 * @param accountType
	 * @return boolean
	 */
	boolean  updateAccountType(AccountType accountType);


}
