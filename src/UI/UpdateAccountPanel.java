package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.eltima.components.ui.DatePicker;

import domain.Account;
import domain.SysLog;
import factory.ServiceFactory;
import service.AdministratortService;
import service.ReaderServiceImpl;
import service.SysLogService;
import service.SysLogServiceImpl;
import util.ConfigContant;
import util.DateUtil;
import util.ImageIconUtilTools;

/**
 * @author 陌意随影
 TODO :添加新用户和修改密码的面板，若  account为null则说明是添加新用户，否则是更新用户
 *2020年2月28日  上午11:26:05
 */
public class UpdateAccountPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField accountName =null;
	private JTextField accountHobby =null;
	private JTextField accountAge =null;
	private JComboBox<String> accountSex =null;
	private JComboBox<String> accountType =null;
	private JComboBox<String> accountStatus =null;
	private JTextArea accountSignature =null;
	private DatePicker createTimePicker =null;
	private JButton btn_ok =  null;
	private JButton btn_reset = null;
	private JPanel timePickePanel  = null;
	private AdministratortService accountService = null;
	private Account account =null;
	private Account administrator = null;
	private String headImagePath = null;
	private JButton btn_headImg = null;
	private JButton btn_chooseImg = null;
	private SysLogService sysLogService = null;
	@SuppressWarnings("javadoc")
	public UpdateAccountPanel(Account administrator,Account account) {
		this.setLayout(new BorderLayout());
		this.sysLogService = (SysLogService) ServiceFactory.newInstanceService("SysLogService");;
		this.accountName = new JTextField("请输入用户名");
		this.accountHobby = new JTextField("请输入用户爱好");
		this.accountAge = new JTextField("请输入用户年龄");
		this.accountSex = new JComboBox<String>(ConfigContant.SEX);
		this.accountType=new JComboBox<String>(ConfigContant.ACCOUNTYPES);
		this.accountStatus = new JComboBox<String>(ConfigContant.ACCOUNTSTATUS);
		this.accountSignature = new JTextArea("请输入用户签名");
		this.createTimePicker = new DatePicker();
		this.accountService = (AdministratortService) ServiceFactory.newInstanceService("AdministratortService");;
		this.account = account;
		this.administrator = administrator;
		this.btn_chooseImg = new JButton("上传头像");
		this.btn_headImg = new JButton();
		btn_headImg.setPreferredSize(new Dimension(100, 100));
		btnStyle(btn_chooseImg);
		btn_headImg.setBorder(null);
		btn_headImg.setBackground(Color.white);
		initComponents();
		initComponentsEvents();
		//用户信息回显
		setFieldValue();
	}
	private void  setFieldValue() {
		if(Objects.nonNull(account)) {
			String name = account.getName();
			if(name != null && name.trim().length() != 0) {
				this.accountName.setText(name);
			}
			String headImgPath = account.getHeadImgPath();
			if(headImgPath == null || headImgPath.trim().length() == 0) {
				ImageIcon defaultheadimg = AdminitratorAllImageIcons.DEFAULTHEADIMG;
				defaultheadimg = ImageIconUtilTools.createHeadImage(defaultheadimg, 0.2, 0.2);
				this.btn_headImg.setIcon(defaultheadimg);
			}else {
				ImageIcon defaultheadimg = new ImageIcon(headImgPath);
				defaultheadimg = ImageIconUtilTools.createHeadImage(defaultheadimg, 0.2, 0.2);
				this.btn_headImg.setIcon(defaultheadimg);
			}
			String hobby = account.getHobby();
			if(hobby != null && hobby.trim().length() != 0) {
				this.accountHobby.setText(hobby);
			}
			String signature = account.getSignature();
			if(signature != null && signature.trim().length() != 0) {
				this.accountSignature.setText(signature);
			}
			int  age = account.getAge();
			this.accountAge.setText(age+"");
			int status = this.account.getStatus();
			int statusCount = this.accountStatus.getItemCount();
			for(int i = 0 ;i < statusCount;i++) {
				String itemAt = this.accountStatus.getItemAt(i);
				if(status == Integer.parseInt(itemAt)) {
					this.accountStatus.setSelectedIndex(i);
					break;
				}
			}
			String sex = this.account.getSex();
			int sexCount = accountSex.getItemCount();
			for(int i = 0 ;i < sexCount;i++) {
				String itemAt = this.accountSex.getItemAt(i);
				if(itemAt.equals(sex)) {
					this.accountStatus.setSelectedIndex(i);
					break;
				}
			}
			
			int type = this.account.getType();
			int accountTypeCount = this.accountType.getItemCount();
			for(int i = 0;i < accountTypeCount;i++) {
				String itemAt = this.accountType.getItemAt(i);
				if(itemAt.equals(type+"")) {
					this.accountType.setSelectedIndex(i);
					break;
				}
			}
		}else {
			ImageIcon defaultheadimg = AdminitratorAllImageIcons.DEFAULTHEADIMG;
			defaultheadimg = ImageIconUtilTools.createHeadImage(defaultheadimg, 0.2, 0.2);
			this.btn_headImg.setIcon(defaultheadimg);
			this.accountName.setText("请输入用户名");
			this.accountHobby.setText("请输入用户爱好");
			this.accountSex.setSelectedIndex(0);
			this.accountStatus.setSelectedIndex(0);
			this.accountType.setSelectedIndex(0);
			this.accountSignature.setText("请输入用户签名");
			this.accountAge.setText("请输入用户年龄");
		}
	}
	private void initComponentsEvents() {
		//给书名添加事件
		addActionEvents(this.accountName,"请输入用户名");
		//给版本号添加事件
		addActionEvents(this.accountHobby,"请输入用户爱好");
		//给价格文本添加点击事件
		addActionEvents(this.accountAge,"请输入用户年龄");
		//给简介文本添加点击事件
		addActionEvents(this.accountSignature,"请输入用户签名");
		//给确定按钮添加单击事件
		this.btn_ok.addActionListener(e->{
			String name=accountName.getText();
			if(name==null || name.trim().length() == 0 || "请输入用户名".equals(name)) {
				JOptionPane.showMessageDialog(this, "请输入用户名!");
				return;
			}
			String sex=(String) accountSex.getSelectedItem();
			if(sex==null || sex.trim().length() == 0 ) {
				JOptionPane.showMessageDialog(this, "请选择用户性别!");
				return;
			}
			
			String _age = accountAge.getText();
			 int age = 0;
			 if(_age==null || _age.trim().length() == 0 || "请输入用户年龄".equals(_age)) {
				 JOptionPane.showMessageDialog(this, "请输入用户年龄!");
					return;
				}else {
					try {
						  age = Integer.parseInt(_age.trim());
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(this, "输入的年龄格式有误");
						return;
					}
				}
			 String hobby=accountHobby.getText();
			 if(hobby==null || hobby.trim().length() == 0 || "请输入用户爱好".equals(hobby)) {
				 JOptionPane.showMessageDialog(this, "请输入用户爱好!");
					return;
				}
			String signature =accountSignature.getText();
			 if(signature==null || signature.trim().length() == 0 || "请输入用户签名".equals(signature)) {
				 JOptionPane.showMessageDialog(this, "请输入用户签名!");
					return;
				}
			 String createTime = createTimePicker.getText();
			 if(createTime == null || createTime.trim().length() == 0) {
				 JOptionPane.showMessageDialog(this, "请输入时间!");
					return;
			 }
			 Date createDate =DateUtil.StrToDate(createTime);
			 if(createDate == null) {
				 JOptionPane.showMessageDialog(this, "请输入正确的时间!");
					return;
			 }
			Integer status =Integer.parseInt((String)accountStatus.getSelectedItem());
			String _type =(String) accountType.getSelectedItem();
			  int type = Integer.parseInt(_type);
			  if(account == null) {
				  //用户为null，说明是添加用户模式
				  int result = JOptionPane.showConfirmDialog(this, "是否确认添加?");
				  if(result == JOptionPane.OK_OPTION) {
					  Account newAccount = new Account();
					  newAccount.setStatus(status);
					  newAccount.setType(type);
					  newAccount.setName(name);
					  newAccount.setAge(age);
					  newAccount.setHobby(hobby);
					  newAccount.setSex(sex);
					  newAccount.setSignature(signature);
					  newAccount.setCreateTime(createDate);
					  boolean fla = accountService.updateAccount(newAccount);
					  if(fla) {
						  SysLog log = new SysLog();
						  log.setCreateTime(new Date());
						   log.setOperationType(SysLog.OPERATIONTYPE_ADDNEWACCOUNT);
						   log.setOperator(administrator.getName());
						   log.setDetails("更新用户");
						   sysLogService.saveSysLog(log);
						  JOptionPane.showMessageDialog(this, "添加成功！");
					  }else {
						  JOptionPane.showMessageDialog(this, "添加失败！");
					  }
				  }
			  }else {
				//用户为不为null，说明是修改用户模式
				  int result = JOptionPane.showConfirmDialog(this, "是否确认修改?");
				  if(result == JOptionPane.OK_OPTION) {
					  account.setStatus(status);
					  account.setType(type);
					  account.setName(name);
					  account.setAge(age);
					  account.setHobby(hobby);
					  account.setSex(sex);
					  account.setSignature(signature);
					  account.setCreateTime(createDate);
					  System.out.println(accountService);
					  boolean fla = accountService.updateAccount(account);
					  if(fla) {
						  SysLog log = new SysLog();
						  log.setCreateTime(new Date());
						   log.setOperationType(SysLog.OPERATIONTYPE_UPDATEACCOUNT);
						   log.setOperator(administrator.getName());
						   log.setDetails("更新用户");
						   sysLogService.saveSysLog(log);
						  JOptionPane.showMessageDialog(this, "修改成功！");
					  }else {
						  JOptionPane.showMessageDialog(this, "修改失败！");
					  }
				  }
				  
			  }
			
			
		});
		//给重置按钮添加点击事件
		this.btn_reset.addActionListener(e->{
			 setFieldValue();
		});
		this.btn_chooseImg.addActionListener(e->{
			String text = btn_chooseImg.getText();
			if("上传头像".equals(text)) {
				FileDialog fileDialog = new FileDialog(new JFrame(),"请选择一个图片",FileDialog.LOAD);
				fileDialog.setVisible(true);
				// 获取选择的文件夹路径
				String directory = fileDialog.getDirectory();
				// 获取选择的文件名
				String fileName = fileDialog.getFile();
				if (fileName == null || fileName.trim().length() == 0) {
					return;
				}
				//获取图片的路径
				String imgPath = directory +fileName;
				ImageIcon headImg = new ImageIcon(imgPath);
				headImg = ImageIconUtilTools.createHeadImage(headImg);
				System.out.println(headImg.getIconHeight());
				btn_headImg.setIcon(headImg);
				btn_chooseImg.setText("确定上传");
			}else {
				//将图片上传到服务器中
				btn_chooseImg.setText("上传头像");
				
				
			}
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
	   JPanel leftPanel = new JPanel();
	   leftPanel.setPreferredSize(new Dimension(300,100));
	
	    leftPanel.add(this.btn_headImg);
	    leftPanel.add(this.btn_chooseImg);
	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    panel.add(btn_headImg);
	    JPanel btnImgPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    btnImgPanel.add(btn_chooseImg);
	    leftPanel.add(panel);
	    leftPanel.add(btnImgPanel);
	    panel.setPreferredSize(new Dimension(250,100));
	    btnImgPanel.setPreferredSize(new Dimension(250,100));
	   JPanel rightPanel = new JPanel();
	   rightPanel.setLayout(new GridLayout(9,1));
   		JPanel panel1 =  createPanel(this.accountName,"用户名");
   		JPanel panel2 = createPanel(this.accountHobby,"爱好");
   		JPanel panel3 = createPanel(this.accountAge,"年龄");
   		JPanel panel5 = createPanel(this.accountSignature,"签名");
   		 this.timePickePanel = createPanel(this.createTimePicker,"创建时间");
   		JPanel panel7 = createPanel(this.accountType,"类型");
   		JPanel panel8 = createPanel(this.accountStatus,"状态");
   		JPanel panel9 = new JPanel();
   		 panel9.setPreferredSize(new Dimension(300,40));
   		 this.btn_ok = new JButton("确认");
   		 this.btn_reset = new JButton("重置");
   		btnStyle(btn_reset) ;
   		btnStyle(btn_ok) ;
   		panel9.add(btn_ok);
   		panel9.add(btn_reset);
   		rightPanel.add(panel1);
   		rightPanel.add(panel2);
   		rightPanel.add(panel3);
   		rightPanel.add(timePickePanel);
   		rightPanel.add(panel7);
   		rightPanel.add(panel8);
   		rightPanel.add(panel5);
   		rightPanel.add(panel9);
   		this.add(rightPanel,BorderLayout.CENTER);
   		this.add(leftPanel,BorderLayout.WEST);
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
   			panel.setPreferredSize(new Dimension(300,60));
   		}else {
   			textField.setPreferredSize(new Dimension(300,30));
   			panel.setPreferredSize(new Dimension(300,40));
   		}
   		panel.add(lbl);
   		panel.add(textField);
   		return panel;
	}

}
