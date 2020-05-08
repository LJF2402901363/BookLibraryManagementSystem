package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import domain.Account;
import factory.ServiceFactory;
import service.AdministratortService;
import util.ImageIconUtilTools;

/**
 * 
 * @author 陌意随影 TODO :登录主页面 2020年3月26日 下午2:25:34
 */
public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField userNameField = null;
	private JPasswordField password = null;
	private JTextField chekCodeField = null;
	private JButton btn_login = null;
	private JButton btn_remebner = null;
	private JPanel contentPanel = null;
	private JLayeredPane layeredPane = null;
	private GridLayout gridLayout = null;
	private CheckCodeLabel checkCode = null;
	private AdministratortService accountService = null;
	/** 界面的宽 */
	public static final int WIDTH = 800;
	/** 界面的高 */
	public static final int HEIGHT = 600;
	@SuppressWarnings("javadoc")
	public LoginFrame() {
		this.contentPanel = (JPanel) this.getContentPane();
		this.setBounds(300, 100, WIDTH, HEIGHT);
		this.userNameField = new JTextField();
		this.password = new JPasswordField();
		this.chekCodeField = new JTextField();
		this.gridLayout = new GridLayout(10, 1);
		this.contentPanel.setLayout(gridLayout);
		this.layeredPane = new JLayeredPane();
		this.accountService = (AdministratortService) ServiceFactory.newInstanceService("AdministratortService");;
		this.contentPanel.setOpaque(false);
		this.setLayeredPane(layeredPane);
		setBackGroudImage();
		layeredPane.setOpaque(false);
		this.layeredPane.add(this.contentPanel, new Integer(1));
		// 初始化所有的控件
		initConponents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 设置背景图片
	 */
	private void setBackGroudImage() {
		ImageIcon bg = AdminitratorAllImageIcons.LOGIN_BG;
		bg = ImageIconUtilTools.getScaleImageIcon(bg, 0.7, 0.8);
		JLabel lbl_bg = new JLabel(bg);
		lbl_bg.setOpaque(false);
		lbl_bg.setBounds(0, 0, WIDTH, HEIGHT);
		this.layeredPane.add(lbl_bg, new Integer(-1));
		ImageIcon bg1 = AdminitratorAllImageIcons.MANNAGWE_LOGIN_BG;
		bg1 = ImageIconUtilTools.getScaleImageIcon(bg1, 0.75, 0.82);
		JLabel lbl_bg1 = new JLabel(bg1);
		lbl_bg1.setOpaque(false);
		lbl_bg1.setBounds(0, 0, WIDTH, HEIGHT);
		this.layeredPane.add(lbl_bg1, new Integer(0));
	}

	private void initConponents() {
		// 初始化登录的标题
		initTitle();
		// 初始化用户框
		initUserNameField();
		// 初始化用户密码框
		initPasswordField();
		// 初始化验证码框
		initCheckField();
		// 初始化按钮框
		initBtn();
		// 初始化登录按钮
		initLoginBtn();
		// 初始化最上层面板
		intTopPanel();
	}

	private void initTitle() {

		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setPreferredSize(new Dimension(600, 10));
		JLabel lbl_title1 = new JLabel("图书管理系统");
		lbl_title1.setFont(new Font("微软雅黑", Font.BOLD, 22));
		panel1.setOpaque(false);
		panel1.add(lbl_title1);
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setPreferredSize(new Dimension(600, 10));
		JLabel lbl_title2 = new JLabel("用户登录界面");
		lbl_title2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		panel2.setOpaque(false);
		panel2.add(lbl_title2);
		this.contentPanel.add(panel1);
		this.contentPanel.add(panel2);
	}

	private void initUserNameField() {
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(600, 10));
		JLabel lbl_name = new JLabel("用户名");
		panel1.add(lbl_name);
		this.userNameField.setPreferredSize(new Dimension(250, 30));
		panel1.add(this.userNameField);
		this.contentPanel.add(panel1);
		userNameField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		this.userNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				userNameField.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				userNameField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}
			
		});
	}

	private void initPasswordField() {
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(600, 10));
		JLabel lbl_name = new JLabel("密码");
		panel1.add(lbl_name);
		this.password.setPreferredSize(new Dimension(250, 30));
		panel1.add(this.password);
		this.contentPanel.add(panel1);
		password.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		this.password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				password.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				password.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}
			
		});
	}

	private void initCheckField() {
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(200, 10));
		JLabel lbl_name = new JLabel("验证码");
		panel1.add(lbl_name);
		this.chekCodeField.setPreferredSize(new Dimension(90, 30));
		chekCodeField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		this.chekCodeField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				chekCodeField.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				chekCodeField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}
			
		});
		panel1.add(this.chekCodeField);
		this.checkCode = new CheckCodeLabel();
		checkCode.setToolTipText("看不清？点击刷新一下");
		JLabel lbl_reflush = new JLabel("看不清？刷新一下");
		lbl_reflush.addMouseListener(new MouseAdapter() {
			Color color = lbl_reflush.getForeground();

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_reflush.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_reflush.setForeground(color);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				checkCode.repaint();

			}
		});
		lbl_reflush.setFont(new Font("微软雅黑", Font.BOLD, 12));
		panel1.add(checkCode);
		panel1.add(lbl_reflush);
		this.contentPanel.add(panel1);

	}

	private void initBtn() {
		ImageIcon remeber_press_img = AdminitratorAllImageIcons.BTN_REMEBER_PRESS;
		ImageIcon remeber_img = AdminitratorAllImageIcons.BTN_REMEBER;
		this.btn_remebner = new JButton("记住密码", remeber_img);
		this.btn_remebner.setFont(new Font("微软雅黑", Font.BOLD, 12));
		FlowLayout flowLayout = new FlowLayout();
		JPanel panel1 = new JPanel(flowLayout);
		flowLayout.setHgap(60);
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(200, 10));
		panel1.add(this.btn_remebner);
		this.btn_remebner.setBorder(BorderFactory.createEmptyBorder());
		this.btn_remebner.setOpaque(false);

		this.btn_remebner.addMouseListener(new MouseAdapter() {
			boolean fla = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!fla) {
					btn_remebner.setIcon(remeber_press_img);
					fla = true;
				} else {
					btn_remebner.setIcon(remeber_img);
					fla = false;
				}
			}
		});
		JLabel lbl_forget = new JLabel("忘记密码");
		lbl_forget.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lbl_forget.setForeground(Color.red);
		panel1.add(lbl_forget);
		this.contentPanel.add(panel1);
	}

	private void initLoginBtn() {
		ImageIcon login_img = AdminitratorAllImageIcons.BTN_LOGIN;
		this.btn_login = new JButton(login_img);
		this.btn_login.setBorder(BorderFactory.createEmptyBorder());
		this.btn_login.setOpaque(false);
		this.btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_login.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn_login.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}
			
		});
		// 添加登录提交事件
		this.btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputcheckCode = chekCodeField.getText();
				if (inputcheckCode == null || inputcheckCode.trim().length() == 0 || "请输入验证码".equals(inputcheckCode)) {
					JOptionPane.showConfirmDialog(LoginFrame.this, "请输入验证码！");
					return;
				}
				// 获取验证码
				String code = checkCode.getCode();
				if (!inputcheckCode.equalsIgnoreCase(code)) {
					// 验证码错误
					JOptionPane.showConfirmDialog(LoginFrame.this, "验证码错误！");
					return;
				}
				
				// 获取用户名
				String name = userNameField.getText();
				// 获取密码
				String pwd = new String(password.getPassword());
				Account account = new Account();
				account.setName(name);
				account.setPassword(pwd);
				Account loginAccount = accountService.login(account);
				if (loginAccount == null) {
					// 用户名或密码错误
					JOptionPane.showConfirmDialog(LoginFrame.this, "用户名或密码错误，请重新输入！");
					return;
				} else {
					// 用户登录成功
					if (loginAccount.getType() == Account.TYPE_USER) {
						// 普通用户

					} else if (loginAccount.getType() == Account.TYPE_ADMINISTRATOR) {
						// 管理员
                       new AdministratorFrame(LoginFrame.this,loginAccount);
                       LoginFrame.this.setVisible(false);
					}
				}

			}
		});
		FlowLayout flowLayout = new FlowLayout();
		JPanel panel1 = new JPanel(flowLayout);
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(200, 10));
		panel1.add(this.btn_login);
		this.contentPanel.add(panel1);
	}

	private void intTopPanel() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		JPanel panel1 = new JPanel(flowLayout);
		panel1.setOpaque(false);
		panel1.setPreferredSize(new Dimension(200, 10));
		JLabel lbl1 = new JLabel("社交登录");
		lbl1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		ImageIcon login_qq = AdminitratorAllImageIcons.QQ_LOGIN;
		JLabel lbl_qq = new JLabel(login_qq);
		ImageIcon login_wechat = AdminitratorAllImageIcons.WECHAT_LOGIN;
		JLabel lbl_wechat = new JLabel(login_wechat);
		ImageIcon login_weibo = AdminitratorAllImageIcons.WEIBO_LOGIN;
		JLabel lbl_weibo = new JLabel(login_weibo);
		JLabel lbl_register = new JLabel("注册");
		// 给注册添加点击事件
		lbl_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterFrame(LoginFrame.this);
				LoginFrame.this.setVisible(false);
			}
		});
		lbl_register.setForeground(Color.green);
		lbl_register.setFont(new Font("微软雅黑", Font.BOLD, 12));
		panel1.add(lbl1);
		panel1.add(lbl_qq);
		panel1.add(lbl_wechat);
		panel1.add(lbl_weibo);
		panel1.add(lbl_register);
		this.contentPanel.add(panel1);
	}

}
