package service;

import java.util.Map;

import dao.AccountTypeDao;
import domain.AccountType;
import domain.PageBean;
import factory.DaoFactory;

/**
 * @author 陌意随影
 TODO :账户中来业务逻辑的实现类
 *2020年3月3日  下午10:05:39
 */
public class AccountTypeServiceImpl implements AccountTypeService{
     private AccountTypeDao accountTypeDao =  (AccountTypeDao) DaoFactory.newInstanceDao("AccountTypeDao");
 	@Override
 	public PageBean<AccountType> fuzzySearchByPage(int offset, int pageSize, String fuzzySearchContent, Map<String, String> condition,String fromDateTime,String toDateTime,
 			String tableSortName, String tableOrder) {
 		return this.accountTypeDao.fuzzySearchByPage(offset, pageSize, fuzzySearchContent,condition,fromDateTime,toDateTime, tableSortName, tableOrder);
 	}

	@Override
	public AccountType findAccountTypeById(int id) {
		return this.accountTypeDao.findAccountTypeById(id);
	}

	@Override
	public boolean   removeAccountTypeById(int id) {
		return this.accountTypeDao.remove(id) == 1;
		
	}

	@Override
	public AccountType findAccountTypeByName(String name) {
	   
		return this.accountTypeDao.findAccountTypeByName(name);
	}

	@Override
	public boolean saveNewAccountType(AccountType newAccountType) {
	return	this.accountTypeDao.save(newAccountType) == 1;
		
	}

	@Override
	public boolean updateAccountType(AccountType accountType) {
		return this.accountTypeDao.upDate(accountType) ==1 ;
	}

}
