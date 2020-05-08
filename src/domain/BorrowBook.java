package domain;

import java.util.Date;

/**
 * @author 陌意随影
 * @create 2020-02-22 19:56
 * @desc 借书情况的实体类
 **/
public class BorrowBook {
	// 借书记录的id主键
	private int id;
	//被借的书籍
    private Book isBorrowedBook;
     //借书者
    private Account reader ;
	// 借书时间
	private Date borrowTime;
	// 还书时间
	private Date returnTime;

	@SuppressWarnings("javadoc")
	public BorrowBook() {
	}

	@SuppressWarnings("javadoc")
	public BorrowBook(int id,Account reader, Book isBorrowedBook, Date borrowTime, Date returnTime) {
		this.id = id;
		this.reader = reader;
		this.isBorrowedBook = isBorrowedBook;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Book getIsBorrowedBook() {
		return isBorrowedBook;
	}

	public Account getReader() {
		return reader;
	}

	public void setIsBorrowedBook(Book isBorrowedBook) {
		this.isBorrowedBook = isBorrowedBook;
	}

	public void setReader(Account reader) {
		this.reader = reader;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@Override
	public String toString() {
		return "BorrowBook [id=" + id + ", isBorrowedBook=" + isBorrowedBook + ", reader=" + reader + ", borrowTime="
				+ borrowTime + ", returnTime=" + returnTime + "]";
	}

	
}
