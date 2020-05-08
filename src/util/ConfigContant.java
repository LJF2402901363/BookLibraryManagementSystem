package util;

import domain.Account;

/**
 * @author 陌意随影
 * @create 2020-02-31 18:30
 * @desc 常量文件
 **/
public class ConfigContant {
	/*-------------------数据库名--------------------*/
	/** 用户表 */
	public static final String ACCOUNT_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("AccountTable");
	/** 图书表 */
	public static final String BOOK_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("BookTable");
	/** 借书表 */
	public static final String BORROWBOOK_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("BorrowBookTable");
	/** 日志表*/
	public static final String SYSLOG_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("SysLog");
	/** 用户角色表*/
	public static final String ACCOUNTTYPE_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("AccountType");
	/** 图书分类表*/
	public static final String BOOKCATE_TABLE = ConfigPropertiesUtil.getPropertiesValueByName("BookCate");
	/*-------------------登录页面的按钮图片--------------------*/
	/** 登录按钮图片的路径 */
	public static final String BTN_LOGIN = ConfigPropertiesUtil.getPropertiesValueByName("btn_login");
	/** 登录背景图0片的路径 */
	public static final String LOGIN_BG0 = ConfigPropertiesUtil.getPropertiesValueByName("login_bg0");
	/** 登录背景图1图片的路径 */
	public static final String LOGIN_BG1 = ConfigPropertiesUtil.getPropertiesValueByName("login_bg1");
	/** 管理员登录背景图片的路径 */
	public static final String MANAGER_LOGIN_BG = ConfigPropertiesUtil.getPropertiesValueByName("Manager_Login_Bg");
	/** QQ图片的路径 */
	public static final String QQ = ConfigPropertiesUtil.getPropertiesValueByName("qq");
	/** 记住密码按下图片的路径 */
	public static final String REMEBER_PRESS = ConfigPropertiesUtil.getPropertiesValueByName("remeber_press");
	/** 记住密码图片的路径 */
	public static final String REMEBER = ConfigPropertiesUtil.getPropertiesValueByName("remeber");
	/** 微信图片的路径 */
	public static final String WECHAT = ConfigPropertiesUtil.getPropertiesValueByName("wechat");
	/** 微博表图片的路径 */
	public static final String WEIBO = ConfigPropertiesUtil.getPropertiesValueByName("weibo");
	/** 注册的提交按钮图片的路径 */
	public static final String SUBMITBUTTON = ConfigPropertiesUtil.getPropertiesValueByName("submitButton");
	/** 默认头像的图片的路径 */
	public static final String DEFAULTHEADIMG = ConfigPropertiesUtil.getPropertiesValueByName("defaultHeadImg");
	/*-------------------管理员页面的按钮图片路径--------------------*/
	/** 管理员主页面的五个小按钮图片中的图书管理按钮图片路径 */
	public static final String BOOKMANAGE = ConfigPropertiesUtil.getPropertiesValueByName("bookmanage");
	/** 管理员主页面的五个小按钮图片中的删除管理按钮图片路径 */
	public static final String DELETE = ConfigPropertiesUtil.getPropertiesValueByName("delete");
	/** 管理员主页面的五个小按钮图片中的首页按钮图片路径 */
	public static final String INDEX = ConfigPropertiesUtil.getPropertiesValueByName("index");
	/** 管理员主页面的五个小按钮图片中的系统管理管理按钮图片路径 */
	public static final String SYSMANAGE = ConfigPropertiesUtil.getPropertiesValueByName("sysmanage");
	/** 管理员主页面的五个小按钮图片中的用户管理按钮图片路径 */
	public static final String USERMANAGE = ConfigPropertiesUtil.getPropertiesValueByName("usermanage");
	/** 管理员主页面的五个小按钮图片中的用户管理按钮图片路径 */
	public static final String SYSMANAGEITEM = ConfigPropertiesUtil.getPropertiesValueByName("sysmanageItem");
  /*----------------管理面板的宽和高-----------------------*/
	/**管理面板的高*/
	
	public static final int HIGHT = 590;
	/**管理面板的宽*/
	public static final int WEIGHT = 900;
	/*-------------------------table分页按钮的图片路径---------*/
	/**table分页按钮的图片上一页路径 */
	public static final String BTN_PREVIOUS = ConfigPropertiesUtil.getPropertiesValueByName("btn_previous");
	/** table分页按钮的图片下一页路径 */
	public static final String BTN_NEXT = ConfigPropertiesUtil.getPropertiesValueByName("btnnext");
	/*---------------图书信息-------------------*/
	/**图书类型*/
	public static final String[] BOOKTYPES =ConfigPropertiesUtil.getPropertiesValuesByName("bookType");
	/**图书状态*/
	public static final String[] BOOKTSTATUS =ConfigPropertiesUtil.getPropertiesValuesByName("bookStatus");
	/*------------------用户信息-------------*/
	/**用户类型*/
	public static final String[] ACCOUNTYPES =ConfigPropertiesUtil.getPropertiesValuesByName("accountType");
	/**用户状态*/
	public static final String[] ACCOUNTSTATUS =ConfigPropertiesUtil.getPropertiesValuesByName("accountStatus");
	/**用户性别*/
	public static final String[] SEX = {Account.SEX_FEMALE,Account.SEX_MALE,Account.SEX_UNKNOWN};
	/**默认的密码*/
	public static final String DEFAULT_PASSWORD ="123456";
	/*------------------数据库的列名-------------------*/
	/**book的数据库列名*/
	public static final  String[] BOOKCOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.BOOK_TABLE);
	/**account数据库的列名*/
	public static final String[] ACCOUNTCOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.ACCOUNT_TABLE);
	/**account数据库的列名*/
	public static final String[] ACCOUNTTYPECOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.ACCOUNTTYPE_TABLE);
	/**syslog数据库的列名*/
	public static final String[] SYSLOGCOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.SYSLOG_TABLE);
	/**bookcate数据库的列名*/
	public static final String[] BOOKCATECOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.BOOKCATE_TABLE);
	/**borrowBook数据库的列名*/
	public static final String[] BORROWBOOKCOLUMNNAMES = JdbcUtil.getTableColumnsName(ConfigContant.BORROWBOOK_TABLE);
	
	
	
	
	
	
}
