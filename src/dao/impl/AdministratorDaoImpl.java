package dao.impl;

import dao.AbstractAccountDao;
import dao.AdministratorDao;
import domain.Account;

/**
 * @author 陌意随影
 TODO :管理员的dao接口
 *2020年3月18日  下午12:55:20
 */
public class AdministratorDaoImpl  extends AbstractAccountDao implements AdministratorDao {
	@SuppressWarnings("javadoc")
	public AdministratorDaoImpl() {
		super(Account.TYPE_ADMINISTRATOR);
	}
 
}
