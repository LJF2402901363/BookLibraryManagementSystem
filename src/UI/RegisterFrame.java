package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import domain.Account;
import factory.ServiceFactory;
import service.AdministratortService;
import util.ConfigContant;
import util.ImageIconUtilTools;

/**
 * @author 陌意随影 TODO :用户注册页面 2020年3月26日 下午3:45:25
 */
public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// 注册框的宽和高
	private static final int WEIGHT = 400;
	private static final int HIGHT = 300;
	private Container contentPanel = null;
	private LoginFrame loginFrame = null;
	/** 用户名框 */
	private JTextField userNameField = null;
	/** 密码框1 */
	private JPasswordField password1 = null;
	/** 密码框2 */
	private JPasswordField password2 = null;
	// 验证码框
	private JTextField checkCodeField = null;
	private CheckCodeLabel checkCodeLabel = null;
	private AdministratortService accountService = null;

	@SuppressWarnings("javadoc")
	public RegisterFrame(LoginFrame loginFrame) {
		this();
		this.loginFrame = loginFrame;
		// 给窗口添加监听事件
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (loginFrame != null) {
					RegisterFrame.this.loginFrame.setVisible(true);
				}
			}
		});
	}

	@SuppressWarnings("javadoc")
	public RegisterFrame() {
		this.setTitle("用户注册页面");
		this.setBounds(400, 200, WEIGHT, HIGHT);
		this.contentPanel = this.getContentPane();
		this.accountService =(AdministratortService) ServiceFactory.newInstanceService("AccountService");;
		initConponents();
		// 给窗口添加监听事件
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RegisterFrame.this.dispose();
			}
		});
		this.setVisible(true);

	}

	private void initConponents() {
		FlowLayout flowLayout = new FlowLayout();
		JPanel panel = new JPanel(flowLayout);
		JPanel panel1 = new JPanel(flowLayout);
		this.userNameField = new JTextField("请输入用户名");
		this.userNameField.setPreferredSize(new Dimension(200, 30));
		componentsEvent(userNameField, "请输入用户名");
		JPanel panel2 = new JPanel(flowLayout);
		JLabel lbl_userName = new JLabel("用户名");
		JLabel lbl_password1 = new JLabel("密码");
		this.password1 = new JPasswordField("请输入密码");
		this.password1.setEchoChar('\0');
		this.password1.setPreferredSize(new Dimension(200, 30));
		componentsEvent(password1, "请输入密码");
		JPanel panel3 = new JPanel(flowLayout);
		JLabel lbl_password2 = new JLabel("确认密码");
		this.password2 = new JPasswordField("请再次输入密码");
		this.password2.setEchoChar('\0');
		componentsEvent(password2, "请再次输入密码");
		this.password2.setPreferredSize(new Dimension(190, 30));
		JPanel panel4 = new JPanel(flowLayout);
		JLabel lbl_checkCode = new JLabel("验证码");
		this.checkCodeField = new JTextField("请输入验证码");
		componentsEvent(checkCodeField, "请输入验证码");
		JLabel lbl_reflush = new JLabel("看不清，点解刷新一下");
		this.checkCodeLabel = new CheckCodeLabel();

		this.checkCodeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkCodeLabel.repaint();
			}
		});
		this.checkCodeField.setPreferredSize(new Dimension(100, 30));
		Color color = lbl_reflush.getForeground();
		lbl_reflush.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkCodeLabel.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_reflush.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_reflush.setForeground(color);
			}
		});
		JPanel panel5 = new JPanel(flowLayout);
		ImageIcon submitImg = ImageIconUtilTools.getImageIcon(ConfigContant.SUBMITBUTTON);
		JButton btn_submit = new JButton(submitImg);
		btn_submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 获取验证码
				String checkCode1 = checkCodeField.getText();
				if (checkCode1 == null || checkCode1.trim().length() == 0 || "请输入验证码".equals(checkCode1)) {
					JOptionPane.showConfirmDialog(RegisterFrame.this, "请输入验证码！");
					return;
				}
				String code = checkCodeLabel.getCode();
				if (!code.equalsIgnoreCase(checkCode1)) {
					JOptionPane.showConfirmDialog(RegisterFrame.this, "验证码错误！");
					return;
				}
				// 获取用户名
				String name = userNameField.getText();
				if (name == null || name.trim().length() == 0 || name.equals("请输入用户名")) {
					JOptionPane.showConfirmDialog(RegisterFrame.this, "请输入用户名！");
					return;
				}
				Account account = accountService.findAccountByName(name);
				if (account == null) {
					// 用户尚未被注册
					int result = JOptionPane.showConfirmDialog(RegisterFrame.this, "注册成功！");
					if (result == JOptionPane.OK_OPTION) {
						boolean fla = accountService.saveAccount(account);
						if (fla) {
							// 注册成功
							JOptionPane.showConfirmDialog(RegisterFrame.this, "注册成功！");

						} else {
							// 注册失败
							JOptionPane.showConfirmDialog(RegisterFrame.this, "注册失败！");
						}
					}
				} else {
					// 用户应已经被注册
					JOptionPane.showConfirmDialog(RegisterFrame.this, "该用户已经注册，请重新输入！");
				}

			}
		});
		btn_submit.setOpaque(false);
		btn_submit.setBorder(BorderFactory.createEmptyBorder());
		panel5.add(btn_submit);
		panel1.add(lbl_userName);
		panel1.add(userNameField);
		panel.add(panel1);
		panel2.add(lbl_password1);
		panel2.add(password1);
		panel.add(panel2);
		panel3.add(lbl_password2);
		panel3.add(password2);
		panel.add(panel3);
		panel4.add(lbl_checkCode);
		panel4.add(checkCodeField);
		panel4.add(checkCodeLabel);
		panel4.add(lbl_reflush);
		panel.add(panel4);
		panel.add(panel5);
		this.contentPanel.add(panel);
	}

	private void componentsEvent(JTextComponent textField, String str) {
		textField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name ="";
				JPasswordField pwd = null;
				if(textField instanceof JPasswordField) {
					 pwd = (JPasswordField) textField;
					 name = new String(pwd.getPassword());
				}else {
					name = textField.getText();
				}
	             
				if (str.equals(name)) {
					textField.setText("");
					if(pwd!=null) {
						pwd.setEchoChar('*');
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				textField.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
				String name ="";
				JPasswordField pwd = null;
				if(textField instanceof JPasswordField) {
					 pwd = (JPasswordField) textField;
					 name = new String(pwd.getPassword());
				}else {
					name = textField.getText();
				}
				if (name == null || name.trim().length() == 0) {
					textField.setText(str);
					if(pwd!=null) {
						pwd.setEchoChar('\0');
					}
				}
			}
		});

	}
}
