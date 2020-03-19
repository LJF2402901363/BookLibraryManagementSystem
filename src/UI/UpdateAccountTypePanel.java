package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.AccountType;
import factory.ServiceFactory;
import service.AccountTypeService;
import service.AccountTypeServiceImpl;

/**
 * @author 陌意随影
 TODO :添加新的角色类型和修改类型的面板，当accountType为null时为添加新的类型，不为空的时候为修改类型
 *2020年2月26日  上午10:09:43
 */
public class UpdateAccountTypePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	//新的角色类型名称
   private JTextField nameField = null;
   //可借天数
   private JTextField borrowDaysField = null;
   //可借次数
   private JTextField borrowTimesField = null;
   //可持续借阅天数
   private JTextField borrowBooksField = null;
   private JButton btn_ok = null;
   private JButton btn_reset = null;
   private JPanel btnPanel = null;
   private AccountTypeService accountTypeService = null;
  //角色类型
   private AccountType accountType = null;
	@SuppressWarnings("javadoc")
	public UpdateAccountTypePanel(AccountType accountType ) {
		this.setLayout(new  GridLayout(5,1));
		this.nameField = new JTextField();
		this.borrowDaysField = new JTextField("请输入可借天数");
		this.borrowTimesField = new JTextField("请输入可借次数");
		this.borrowBooksField = new JTextField("请输入可借书数");
		this.btn_ok = new JButton("确定");
		this.btn_reset = new JButton("重置");
		this.btnPanel = new JPanel();
		this.accountTypeService=(AccountTypeService) ServiceFactory.newInstanceService("AccountTypeService");;
		this.accountType =accountType;
		 addTextFiledStyle(nameField,"角色类型名");
		 addTextFiledStyle(borrowDaysField,"可借天数");
		 addTextFiledStyle(borrowTimesField,"可借次数");
		 addTextFiledStyle(borrowBooksField,"可借书数");
		 addFiledEvent(nameField,"请输入新角色名");
		 addFiledEvent(borrowDaysField,"请输入可借天数");
		 addFiledEvent(borrowTimesField,"请输入可借次数");
		 addFiledEvent(borrowBooksField,"请输入可借书数");
		addBtnStyle(btn_ok);
		addBtnStyle(btn_reset);
		initBtnEvents();
		showData();
		this.setPreferredSize(new Dimension(400,500));
		this.add(btnPanel);
	}
	/**
	 * 信息回显
	 */
	private void showData() {
		if(Objects.nonNull(this.accountType)) {
			nameField.setText(this.accountType.getTypeName());
			borrowDaysField.setText(this.accountType.getCanborrowdays()+"");
			borrowTimesField.setText(this.accountType.getCanborrowTimes()+"");
			borrowBooksField.setText(this.accountType.getCanborrowbooks()+"");
		}else {
			nameField.setText("请输入新角色名");
			borrowDaysField.setText("请输入可借天数");
			borrowTimesField.setText("请输入可借次数");
			borrowBooksField.setText("请输入可借书数");
		}
	}
	/**
	 * 给按钮添加事件
	 */
	private void initBtnEvents() {
		//给重置按钮添加事件
		this.btn_reset.addActionListener(e->{
			 showData();
		});
		//给提交按钮添加事件
		this.btn_ok.addActionListener(e->{
			int borrowDays  = -1;
			int borrowTimes =-1;
			int borrowBooks = -1;
			String name = nameField.getText();
			if(name == null || name.trim().length() == 0 || "请输入新角色名".equals(name)) {
				JOptionPane.showMessageDialog(this, "请输入新的角色类型名！");
				return;
			}
			String _borrowDays = borrowDaysField.getText();
			if(_borrowDays == null ||_borrowDays.trim().length() ==0 || "请输入可借天数".equals(_borrowDays) ) {
				JOptionPane.showMessageDialog(this, "请输入可借天数！");
				return;
			}else {
				try {
					 borrowDays = Integer.parseInt(_borrowDays);
					 if(borrowDays<=0) {
							JOptionPane.showMessageDialog(this, "请输入大于零的正整数的可借天数！");
							return;
					 }
				}catch(Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "请输入正确的可借天数格式！");
					return;
				}
			
				
			}
			String _borrowTimes = borrowTimesField.getText();
			if(_borrowTimes == null ||_borrowTimes.trim().length() ==0 || "请输入可借次数".equals(_borrowTimes) ) {
				JOptionPane.showMessageDialog(this, "请输入可借次数！");
				return;
			}else {
				try {
					borrowTimes = Integer.parseInt(_borrowTimes);
					 if(borrowTimes<=0) {
							JOptionPane.showMessageDialog(this, "请输入大于零的正整数的可借次数！");
							return;
					 }
				}catch(Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(this, "请输入正确的可借次数格式！");
					return;
				}
			}
			
			String _borrowBooks = borrowBooksField.getText();
			if(_borrowBooks == null ||_borrowBooks.trim().length() ==0 || "请输入可借书数".equals(_borrowBooks) ) {
				JOptionPane.showMessageDialog(this, "请输入可借书数！");
				return;
			}else {
				try {
					borrowBooks = Integer.parseInt(_borrowBooks);
					 if(borrowBooks<=0) {
							JOptionPane.showMessageDialog(this, "请输入大于零的正整数的可借书数！");
							return;
					 }
				}catch(Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(this, "请输入正确的可借书数格式！");
					return;
				}
			}
			if(accountType== null) {
				//添加模式
				AccountType	  accountType =	accountTypeService.findAccountTypeByName(name);
				if(accountType != null) {
					JOptionPane.showMessageDialog(this, "该类型已经存在，请重新添加新的类型！");
					return;
				}else {
					AccountType newAccountType = new AccountType();
					newAccountType.setTypeName(name);
					newAccountType.setCanborrowbooks(borrowBooks);
					newAccountType.setCanborrowdays(borrowDays);
					newAccountType.setCanborrowTimes(borrowTimes);
					boolean fla = accountTypeService.saveNewAccountType(newAccountType);
					if(fla ) {
						JOptionPane.showMessageDialog(this, "添加角色新类型成功！");
					}else {
						JOptionPane.showMessageDialog(this, "添加角色新类型失败！");
					}
				}
				
			}else {
				//修改模式
				accountType.setTypeName(name);
				accountType.setCanborrowbooks(borrowBooks);
				accountType.setCanborrowdays(borrowDays);
				accountType.setCanborrowTimes(borrowTimes);
				accountTypeService.updateAccountType(accountType);
			}
		});
	}

	/**
	 * 给指定的JTextField添加点击框的提示
	 * @param nameField
	 * @param name
	 */
    private void addFiledEvent(JTextField nameField, String name) {
    	nameField.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			String text = nameField.getText();
    			if(name.equals(text)) {
    				nameField.setText("");
    			}
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			String text = nameField.getText();
    			if(text == null || text.trim().length() == 0) {
    				nameField.setText(name);
    			}
    		}
		});
		
	}
/**
 * 给指定的按钮添加通用样式
 * @param btn
 */
	private void addBtnStyle(JButton btn) {
    	btn.setBackground(new Color(30, 159, 255));
		// 该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);		
		btnPanel.add(btn);
	}
/**
 * 给指定的JTextField添加样式并添加到面板中
 * @param textfield
 * @param lblname
 */
	private void addTextFiledStyle(JTextField textfield,String lblname) {
    	Font font = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,14);
    	FlowLayout flowLayout = new FlowLayout();
    	flowLayout.setHgap(2);
    	JPanel panel = new JPanel(flowLayout);
    	panel.setPreferredSize(new Dimension(1000,50));
    	textfield.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			textfield.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			textfield.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
    		}
		});
    	JLabel lbl = new JLabel(lblname);
    	lbl.setFont(font);
    	textfield.setPreferredSize(new Dimension(250,30));
//    	panel.setBorder(BorderFactory.createLineBorder(Color.red,2));
    	panel.add(lbl);
    	panel.add(textfield);
    	this.add(panel);
    }

}
