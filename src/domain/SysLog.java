package domain;

import java.util.Date;

/**
 * @author 陌意随影
 TODO :系统操作日志
 *2020年2月27日  下午4:40:49
 */
public class SysLog {
	//系统记录的主键id
	private int id ;
	//创建者
   private String operator;
   //创建时间
   private Date  createTime;
   //日志类型
   private String operationType;
   /**添加新账户的操作*/
   public static final String OPERATIONTYPE_ADDNEWACCOUNT="添加账户";
   /**修改账户的操作*/
   public static final String OPERATIONTYPE_UPDATEACCOUNT="修改账号";
   /**删除账户的操作*/
   public static final String OPERATIONTYPE_REMOVEACCOUNT="删除账户";
   /**添加新书的操作*/
   public static final String OPERATIONTYPE_ADDNEWBOOK="添加书籍";
   /**修改书籍的操作*/
   public static final String OPERATIONTYPE_UPDATEBOOK="修改书籍";
   /**删除书籍的操作*/
   public static final String OPERATIONTYPE_REMOVEBOOK="删除书籍";
   //操作的细节
   private String details;
   
@SuppressWarnings("javadoc")
public SysLog() {
	super();
}
@SuppressWarnings("javadoc")
public SysLog(int id, String operator, Date createTime, String operationType, String details) {
	super();
	this.id = id;
	this.operator = operator;
	this.createTime = createTime;
	this.operationType = operationType;
	this.details = details;
}
public int getId() {
	return id;
}
public String getOperator() {
	return operator;
}
public Date getCreateTime() {
	return createTime;
}
public String getOperationType() {
	return operationType;
}
public String getDetails() {
	return details;
}
public void setId(int id) {
	this.id = id;
}
public void setOperator(String operator) {
	this.operator = operator;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public void setOperationType(String operationType) {
	this.operationType = operationType;
}
public void setDetails(String details) {
	this.details = details;
}
@Override
public String toString() {
	return "SysLog [id=" + id + ", operator=" + operator + ", createTime=" + createTime + ", operationType="
			+ operationType + ", details=" + details + "]";
}
   
}
