package factory;

import javax.swing.JPanel;

import UI.AdministratorFrame;
import UI.AdministratorInfoPanel;
import UI.AdministratorMangePanel;
import UI.BookCateManagePanel;
import UI.BookManagePanel;
import UI.BookRecordBookManagePanel;
import UI.BorrowBookManagePanel;
import UI.HomePanel;
import UI.LogManagePanel;
import UI.PasswordMangePanel;
import UI.ReaderMangePanel;
import UI.ReturnBookManagePanel;
import UI.RoleMangePanel;
import domain.Account;

/**
 * @author 陌意随影
 TODO :图书管理的面板
 *2020年3月17日  下午10:07:54
 */
public class ManageFactory  {
	private static HomePanel homePanel = null;
    private static BookManagePanel bookManagePanel = null;
    private static BookCateManagePanel bookCateManagePanel = null;
    private static RoleMangePanel roleMangePanel = null;
    private static PasswordMangePanel passwordMangePanel = null;
    private static LogManagePanel logManagePanel = null;
    private static ReaderMangePanel readerMangePanel = null;
    private static AdministratorInfoPanel  administratorInfoPanel= null;
    private static AdministratorMangePanel  administratorMangePanel= null;
    private static BorrowBookManagePanel  borrwBookManagePanel = null;
    private static ReturnBookManagePanel  returnBookManagePanel = null;
    private static BookRecordBookManagePanel  bookRecordManagePanel = null;

    /**
     * 通过指定的text文本获得对应的ManagePanel
     * @param text  
     * @return JPanel
     */
    public static JPanel getManagePanel(String text,Account account,AdministratorFrame administratorFrame) {
    	switch (text) {
    	case "首页":
    		if(homePanel ==null) {
    			homePanel = new HomePanel(account,administratorFrame);
			}
			return homePanel;
    	case "管理员管理":
    		if(administratorMangePanel ==null) {
    			administratorMangePanel = new AdministratorMangePanel(account);
			}
			return administratorMangePanel;
    	case "管理员个人信息":
			if(administratorInfoPanel ==null) {
				administratorInfoPanel = new AdministratorInfoPanel(account);
			}
			return administratorInfoPanel;
		case "修改密码":
			if(passwordMangePanel ==null) {
				passwordMangePanel = new PasswordMangePanel(account);
			}
			return passwordMangePanel;
		case "日志管理":
			if(logManagePanel == null) {
				logManagePanel  = new LogManagePanel();
			}
			return logManagePanel;
		case "读者管理":
			if(readerMangePanel == null) {
				readerMangePanel  = new ReaderMangePanel(account);
			}
			return readerMangePanel;
		case "角色管理":
			if(roleMangePanel == null) {
				roleMangePanel  = new RoleMangePanel();
			}
			return roleMangePanel;
		case "图书管理":
			if(bookManagePanel == null) {
				bookManagePanel  = new BookManagePanel(account);
			}
			return bookManagePanel;
		case "分类管理":
			if(bookCateManagePanel == null) {
				bookCateManagePanel  = new BookCateManagePanel();
			}
			return bookCateManagePanel;
		case "借书管理":
			if(borrwBookManagePanel == null) {
				borrwBookManagePanel  = new BorrowBookManagePanel(account);
			}
			return borrwBookManagePanel;
		case "还书管理":
			if(returnBookManagePanel == null) {
				returnBookManagePanel  = new ReturnBookManagePanel(account);
			}
			return returnBookManagePanel;
		case "借书和还书书":
			if(bookRecordManagePanel == null) {
				bookRecordManagePanel  = new BookRecordBookManagePanel(account);
			}
			return bookRecordManagePanel;
		}
		return null;
    	
    }
}
