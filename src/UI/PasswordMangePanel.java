package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import domain.Account;
import factory.ServiceFactory;
import service.AdministratortService;
import service.ReaderServiceImpl;

/**
 * @author 陌意随影
 TODO :角色管理面板
 *2020年2月17日  下午10:10:32
 */
public class PasswordMangePanel extends JPanel{
	private static final long serialVersionUID = 1L;
    private JPasswordField oldPasswordField = null;
    private JPasswordField newPasswordField1 = null;
    private JPasswordField newPasswordField2 = null;
    private Account account = null;
    private JButton btn_ok = null;
    private JButton btn_reset = null;
    private AdministratortService accountService = null;
    @SuppressWarnings("javadoc")
	public PasswordMangePanel(Account account) {
    	this.account = account;
    	this.oldPasswordField = new JPasswordField();
    	this.newPasswordField1 = new JPasswordField();
    	this.newPasswordField2 = new JPasswordField();
    	this.btn_ok = new JButton("确定");
		this.btn_reset = new JButton("重置");
		this.accountService = (AdministratortService) ServiceFactory.newInstanceService("AccountService");;
		this.setPreferredSize(new Dimension(350,300));
//		this.setBorder(BorderFactory.createLineBorder(Color.red,2));
//		this.setLayout(new GridLayout(4,1));
    	initComponents();
    	initBtnEvents();
    }
	private void initBtnEvents() {
	 this.btn_reset.addActionListener(e->{
		 oldPasswordField.setText("请输入原来的密码");
		 newPasswordField1.setText("请输入新的密码");
		 newPasswordField2.setText("请再次输入新的密码");
		 oldPasswordField.setEchoChar('\0');
		 newPasswordField1.setEchoChar('\0');
		 newPasswordField2.setEchoChar('\0');
	 });
	this.btn_ok.addActionListener(e->{
		String oldpassword = new String(oldPasswordField.getPassword());
		if(oldpassword.trim().length() == 0 ||"请输入原来的密码".equals(oldpassword) ) {
			JOptionPane.showMessageDialog(this, "请输入原来的密码！");
			return;
		}
		String newpassword1 = new String(newPasswordField1.getPassword());
		if(newpassword1.trim().length() == 0 ||"请输入新的密码".equals(newpassword1) ) {
			JOptionPane.showMessageDialog(this, "请输入新的密码！");
			return;
		}
		String newpassword2 = new String(newPasswordField2.getPassword());
		if(newpassword2.trim().length() == 0 ||"请再次输入新的密码".equals(newpassword2) ) {
			JOptionPane.showMessageDialog(this, "请再次输入新的密码！");
			return;
		}
		if(!oldpassword.equals(account.getPassword())) {
			JOptionPane.showMessageDialog(this, "原来的密码不正确！");
			return;
		}
		if(newpassword2.equals(newpassword1)) {
			account.setPassword(newpassword2);
			boolean fla = this.accountService.updateAccount(account);
			if(fla) {
				JOptionPane.showMessageDialog(this, "密码修改成功！");
				return;	
			}else {
				JOptionPane.showMessageDialog(this, "密码修改失败！");
			}
		}else {
			JOptionPane.showMessageDialog(this, "两次新的密码不一致！");
			return;
		}
	}
	);	
	}
	private void initComponents() {
	   JPanel oldPwdPanel = addPasswordFiledStyle(oldPasswordField,"原来的密码","请输入原来的密码");
	   JPanel newPwdPanel1 = addPasswordFiledStyle(newPasswordField1,"输入新密码","请输入新的密码");
	   JPanel newPwdPanel2 = addPasswordFiledStyle(newPasswordField2,"确认新密码","请再次输入新的密码");
		this.add(oldPwdPanel);
		this.add(newPwdPanel1);
		this.add(newPwdPanel2);
		addBtnStyle(btn_ok);
		addBtnStyle(btn_reset);    
	   JPanel btnPanel = new JPanel();
	   btnPanel.setPreferredSize(new Dimension(250,50));
//	   btnPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
	   btnPanel.add(btn_ok);
	   btnPanel.add(btn_reset);
	   this.add(btnPanel);
	}
	private JPanel addPasswordFiledStyle(JPasswordField passwordFiled,String lblName,String info) {
		passwordFiled.setPreferredSize(new Dimension(250,30));
		passwordFiled.setEchoChar('\0');
		passwordFiled.setText(info);
		Font font = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,15);
		passwordFiled.setFont(font);
    	FlowLayout flowLayout = new FlowLayout();
    	flowLayout.setHgap(2);
    	JPanel panel = new JPanel(flowLayout);
    	panel.setPreferredSize(new Dimension(400,50));
//    	panel.setBorder(BorderFactory.createLineBorder(Color.red,2));
    	passwordFiled.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			passwordFiled.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			passwordFiled.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
    			String password = new String(passwordFiled.getPassword());
        		if(password.trim().length() == 0 ) {
        			passwordFiled.setText(info);
        			passwordFiled.setEchoChar('\0');
        		}
    		}
    		@Override
    		public void mouseClicked(MouseEvent e) {
    		String password = new String(passwordFiled.getPassword());
    		if(info.equals(password)) {
    			passwordFiled.setText("");
    			passwordFiled.setEchoChar('*');
    		}
    		}
		});
    	JLabel lbl = new JLabel(lblName);
    	lbl.setFont(font);
    	panel.add(lbl);
    	panel.add(passwordFiled);
    	return panel;
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
		}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 600, 600);
		f.getContentPane().add(new PasswordMangePanel(null));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
