package dao.impl;

import dao.AbstractAccountDao;
import dao.ReaderDao;
import domain.Account;

/**
 * @author 陌意随影
 * @create 2020-02-22 20:58
 * @desc AccountDao的实现类
 **/
public class ReaderDaoImpl extends AbstractAccountDao implements ReaderDao{
	@SuppressWarnings("javadoc")
	public ReaderDaoImpl() {
		super(Account.TYPE_USER);
		
	}
}