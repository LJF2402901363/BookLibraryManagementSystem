package domain;

import java.util.Date;

/**
 * @author 陌意随影
 TODO :图书分类
 *2020年3月7日  下午7:44:58
 */
public class BookCate {
	//主键id
  private int id;
  //父节点的类型编码
  private String  parentTypeCode;
  //图书类型名称
  private String name;
  //创建时间
  private Date createTime;
  
@SuppressWarnings("javadoc")
public BookCate() {
}

@SuppressWarnings("javadoc")
public BookCate(int id,  String  parentTypeCode,String name,Date createTime) {
	this.id = id;
	this.parentTypeCode = parentTypeCode;
	this.name = name;
	this.createTime= createTime;
}

public int getId() {
	return id;
}


public String getName() {
	return name;
}

public void setId(int id) {
	this.id = id;
}


public void setName(String name) {
	this.name = name;
}

public Date getCreateTime() {
	return createTime;
}

public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}



public String getParentTypeCode() {
	return parentTypeCode;
}

public void setParentTypeCode(String parentTypeCode) {
	this.parentTypeCode = parentTypeCode;
}

@Override
public String toString() {
	return "BookCate [id=" + id +  ", parentTypeCode=" + parentTypeCode + ", name=" + name
			+ ", createTime=" + createTime + "]";
}

  
}
