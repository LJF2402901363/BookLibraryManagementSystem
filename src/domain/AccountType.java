package domain;

/**
 * @author 陌意随影
 TODO :用户类型
 *2020年2月23日  下午8:45:15
 */
public class AccountType {
   // id | canborrowdays | canborrowTimes | renewDays |
	//用户类型的主键id
	private int id;
	private String typeName;
	//可以借阅天数
	private int canborrowdays;
	//可以借阅次数
	private int canborrowTimes;
	//可以续借的天数
	private int canborrowbooks;
	
	
	public AccountType() {
		super();
	}
	
	public AccountType(int id, String typeName, int canborrowdays, int canborrowTimes, int canborrowbooks) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.canborrowdays = canborrowdays;
		this.canborrowTimes = canborrowTimes;
		this.canborrowbooks = canborrowbooks;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}
	public int getCanborrowdays() {
		return canborrowdays;
	}
	public int getCanborrowTimes() {
		return canborrowTimes;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCanborrowdays(int canborrowdays) {
		this.canborrowdays = canborrowdays;
	}
	public void setCanborrowTimes(int canborrowTimes) {
		this.canborrowTimes = canborrowTimes;
	}
	public int getCanborrowbooks() {
		return canborrowbooks;
	}

	public void setCanborrowbooks(int canborrowbooks) {
		this.canborrowbooks = canborrowbooks;
	}

	@Override
	public String toString() {
		return "AccountType [id=" + id + ", canborrowdays=" + canborrowdays + ", canborrowTimes=" + canborrowTimes
				+ ", renewDays=" + canborrowbooks + "]";
	}
	
}
