package UI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.eltima.components.ui.DatePicker;

import domain.Account;
import domain.Book;
import domain.SysLog;
import factory.ServiceFactory;
import service.BookService;
import service.BookServiceImpl;
import service.SysLogService;
import util.ConfigContant;
import util.DateUtil;

/**
 * @author 陌意随影
 TODO :添加新书和修改书的面板当传入的book为null时表示的是添加新书，不为null时表示的是修改书籍
 *2020年2月19日  上午11:26:05
 */
public class UpdateBookPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField bookName =null;
	private JTextField bookSbn =null;
	private JTextField bookPrice =null;
	private JTextField bookAuthor =null;
	private JComboBox<String> bookType =null;
	private JComboBox<String> bookStatus =null;
	private JTextArea bookDescription =null;
	private DatePicker createTimePicker =null;
	private JButton btn_ok =  null;
	private JButton btn_reset = null;
	private JPanel timePickePanel  = null;
	private BookService bookService = null;
	private Book book =null;
	 private SysLogService sysLogService = null;
	 private Account administrator = null;
	@SuppressWarnings("javadoc")
	public UpdateBookPanel(Account administrator,Book book) {
		this.setPreferredSize(new Dimension(700,500));
		this.bookName = new JTextField("请输入书名");
		this.bookAuthor=new JTextField("请输入作者");
		this.bookSbn = new JTextField("请输入版本号");
		this.bookPrice = new JTextField("请输入价格");
		this.bookType=new JComboBox<String>(ConfigContant.BOOKTYPES);
		this.bookStatus = new JComboBox<String>(ConfigContant.BOOKTSTATUS);
		this.bookDescription = new JTextArea("请输入简介");
		this.createTimePicker = new DatePicker();
		this.bookService = (BookService) ServiceFactory.newInstanceService("BookService");;
		this.book = book;
		this.administrator = administrator;
		initComponents();
		initComponentsEvents();
		//图书信息回显
		setFieldValue();
	}
	private void  setFieldValue() {
		if(Objects.nonNull(book)) {
			String name = book.getName();
			if(name != null && name.trim().length() != 0) {
				this.bookName.setText(name);
			}
			String sbn = book.getSbn();
			if(sbn != null && sbn.trim().length() != 0) {
				this.bookSbn.setText(sbn);
			}
			String author = book.getAuthor();
			if(author != null && author.trim().length() != 0) {
				this.bookAuthor.setText(author);
			}
			String description = book.getDescription();
			if(description != null && description.trim().length() != 0) {
				this.bookDescription.setText(description);
			}
			double price = book.getPrice();
			this.bookPrice.setText(price+"");
			int status = this.book.getStatus();
			int statusCount = this.bookStatus.getItemCount();
			for(int i = 0 ;i < statusCount;i++) {
				String itemAt = this.bookStatus.getItemAt(i);
				if(status == Integer.parseInt(itemAt)) {
					this.bookStatus.setSelectedIndex(i);
					break;
				}
			}
			String type = this.book.getType();
			int bookTypeCount = this.bookType.getItemCount();
			for(int i = 0;i < bookTypeCount;i++) {
				String itemAt = this.bookType.getItemAt(i);
				if(itemAt.equals(type)) {
					this.bookType.setSelectedIndex(i);
					break;
				}
			}
		}else {
			this.bookName.setText("请输入书名");
			this.bookAuthor.setText("请输入作者");
			this.bookSbn.setText("请输入版本号");
			this.bookPrice.setText("请输入价格");
			this.bookDescription.setText("请输入简介");
			this.bookStatus.setSelectedIndex(0);
			this.bookType.setSelectedIndex(0);
		}
	}
	private void initComponentsEvents() {
		//给书名添加事件
		addActionEvents(this.bookName,"请输入书名");
		//给作者文本添加点击事件
		addActionEvents(this.bookAuthor,"请输入作者");
		//给版本号添加事件
		addActionEvents(this.bookSbn,"请输入版本号");
		//给价格文本添加点击事件
		addActionEvents(this.bookPrice,"请输入价格");
		//给简介文本添加点击事件
		addActionEvents(this.bookDescription,"请输入简介");
		//给确定按钮添加单击事件
		this.btn_ok.addActionListener(e->{
			String name=bookName.getText();
			if(name==null || name.trim().length() == 0 || "请输入书名".equals(name)) {
				JOptionPane.showConfirmDialog(this, "请输入书名!");
				return;
			}
			String sbn=bookSbn.getText();
			 if(sbn==null || sbn.trim().length() == 0 || "请输入版本号".equals(sbn)) {
					JOptionPane.showConfirmDialog(this, "请输入版本号!");
					return;
				}
		    String author=bookAuthor.getText();
		    if(author==null || author.trim().length() == 0 || "请输入作者".equals(author)) {
				JOptionPane.showConfirmDialog(this, "请输入作者!");
				return;
			}
			
			String _price = bookPrice.getText();
			 double price = 0;
			 if(_price==null || _price.trim().length() == 0 || "请输入价格".equals(_price)) {
					JOptionPane.showConfirmDialog(this, "请输入价格!");
					return;
				}else {
					try {
						  price = Double.parseDouble(_price.trim());
					}catch(Exception e1) {
						JOptionPane.showConfirmDialog(this, "输入的价格格式有误");
						return;
					}
				}
			String description =bookDescription.getText();
			 if(description==null || description.trim().length() == 0 || "请输入简介".equals(description)) {
					JOptionPane.showConfirmDialog(this, "请输入简介!");
					return;
				}
			 String createTime = createTimePicker.getText();
			 if(createTime == null || createTime.trim().length() == 0) {
					JOptionPane.showConfirmDialog(this, "请输入时间!");
					return;
			 }
			 Date createDate =DateUtil.StrToDate(createTime);
			 if(createDate == null) {
				 JOptionPane.showConfirmDialog(this, "请输入正确的时间!");
					return;
			 }
			Integer status =Integer.parseInt((String)bookStatus.getSelectedItem());
			String type =(String) bookType.getSelectedItem();
			
			if(book == null) {
				//为空，则说明是添加新书
				Book newBook = new Book();
				newBook.setAuthor(author);
				newBook.setName(name);
				newBook.setPrice(price);
				newBook.setType(type);
				newBook.setCreateTime(createDate);
				newBook.setDescription(description);
				newBook.setSbn(sbn);
				newBook.setStatus(status);
				 int result = JOptionPane.showConfirmDialog(this, "是否确认添加?");
				 if(result == JOptionPane.OK_OPTION) {
					 boolean fla = bookService.addNewBook(newBook);
					 if(fla) {
						 JOptionPane.showConfirmDialog(this, "添加成功！");
						 SysLog log = new  SysLog();
							log.setCreateTime(new Date());
							log.setOperationType(SysLog.OPERATIONTYPE_ADDNEWBOOK);
							log.setOperator(administrator.getName());
							log.setDetails("添加图书");
							 sysLogService.saveSysLog(log);
					 }else {
						 JOptionPane.showConfirmDialog(this, "添加失败！");
					 }
				 }
			}else {
				//不为空，说明是修改书籍
				book.setAuthor(author);
				book.setName(name);
				book.setPrice(price);
				book.setType(type);
				book.setCreateTime(createDate);
				book.setDescription(description);
				book.setSbn(sbn);
				book.setStatus(status);
				 int result = JOptionPane.showConfirmDialog(this, "是否确认修改?");
				 if(result == JOptionPane.OK_OPTION) {
					 boolean fla = bookService.updateBook(book);
					 if(fla) {
						 JOptionPane.showConfirmDialog(this, "添加成功！");
						 SysLog log = new  SysLog();
							log.setCreateTime(new Date());
							log.setOperationType(SysLog.OPERATIONTYPE_UPDATEBOOK);
							log.setOperator(administrator.getName());
							log.setDetails("修改图书");
							 sysLogService.saveSysLog(log);
						 JOptionPane.showConfirmDialog(this, "修改成功！");
					 }else {
						 JOptionPane.showConfirmDialog(this, "修改失败！");
					 }
				 }
			}
			
			
		});
		//给重置按钮添加点击事件
		this.btn_reset.addActionListener(e->{
			 setFieldValue();
		});
		
	}
	private void addActionEvents(JTextComponent  com,String content) {
		com.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = com.getText();
				if(content.equals(name)) {
					com.setText("");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				String name = com.getText();
				
				if(name == null || name.trim().length() == 0) {
					com.setText(content);
				}
			}
		});
	}
	private void btnStyle(JButton btn) {
    	btn.setBackground(new Color(30,159,255));
		//该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
    }
	private void initComponents() {
   		JPanel panel1 =  createPanel(this.bookName,"书名");
   		JPanel panel2 = createPanel(this.bookSbn,"版本号");
   		JPanel panel3 = createPanel(this.bookPrice,"价格");
   		JPanel panel4 = createPanel(this.bookAuthor,"作者");
   		JPanel panel5 = createPanel(this.bookDescription,"简介");
   		 this.timePickePanel = createPanel(this.createTimePicker,"创建时间");
   		JPanel panel7 = createPanel(this.bookType,"类型");
   		JPanel panel8 = createPanel(this.bookStatus,"状态");
   		JPanel panel9 = new JPanel();
   		 panel9.setPreferredSize(new Dimension(680,40));
   		 this.btn_ok = new JButton("确认添加");
   		 this.btn_reset = new JButton("重置");
   		btnStyle(btn_reset) ;
   		btnStyle(btn_ok) ;
   		panel9.add(btn_ok);
   		panel9.add(btn_reset);
   		this.add(panel1);
   		this.add(panel2);
   		this.add(panel3);
   		this.add(panel4);
   		this.add(panel5);
   		this.add(timePickePanel);
   		this.add(panel7);
   		this.add(panel8);
   		this.add(panel9);
	}
	/**
	 * 通过标签名和JTextField创建一个小型的面板
	 * @param textField
	 * @param lblName
	 * @return
	 */
	private JPanel createPanel(Component textField,String lblName) {
		Font lbl_font = new Font("微软雅黑",Font.BOLD,16);
		Font text_font = new Font("微软雅黑",Font.BOLD,14);
   		JPanel panel = new JPanel();
   		textField.setFont(text_font);
   		JLabel lbl = new JLabel(lblName);
   		lbl.setFont(lbl_font);
   		if(textField instanceof JTextArea) {
   			JTextArea taArea=	(JTextArea)textField;
   			taArea.setLineWrap(true);
   			taArea.setPreferredSize(new Dimension(300,50));
   			panel.setPreferredSize(new Dimension(680,60));
   		}else {
   			textField.setPreferredSize(new Dimension(300,30));
   			panel.setPreferredSize(new Dimension(680,40));
   		}
   		panel.add(lbl);
   		panel.add(textField);
   		return panel;
	}

}
