package service;
import dao.AdministratorDao;
import domain.Account;
import domain.PageBean;
import factory.DaoFactory;
import service.AdministratortService;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-02-01 13:52
 * @desc 用户业务逻辑类的实现类
 **/
public class AdministratorServiceImpl implements AdministratortService {
	AdministratorDao dao = (AdministratorDao) DaoFactory.newInstanceDao("AdministratorDao");

	@Override
	public Account login(Account account) {
		return dao.login(account);
	}
	@Override
	public boolean saveAccount(Account user) {
		int result = dao.save(user);

		return result == 1;
	}

	@Override
	public Account findAccountByName(String name) {
		return dao.findAccountByName(name);
	}

	@Override
	public PageBean<Account> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,String fromDateTime,String toDateTime, 
			String tableSortName, String tableOrder) {
		return dao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent,condition,fromDateTime, toDateTime,tableSortName, tableOrder);
	}


	@Override
	public boolean updateAccount(Account account) {
		int result = this.dao.upDate(account);
		return result == 1;
	}

	@Override
	public boolean addNewAccount(Account account) {
		int result = this.dao.save(account);
		return result == 1;
	}

	@Override
	public Account findAccountById(int id) {
		return this.dao.get(id);
	}

	@Override
	public boolean removeAccountById(int id) {
		int result = this.dao.remove(id);
		return result == 1;
	}
	@Override
	public int getAllAdministratorCount() {
		// TODO Auto-generated method stub
		return dao.getAllAdministratorCount();
	}
	@Override
	public int getAllReaderCount() {
		// TODO Auto-generated method stub
		return dao.getAllReaderCount();
	}
}
