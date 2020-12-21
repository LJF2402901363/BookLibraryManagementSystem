package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import domain.Account;
import factory.ManageFactory;

/**
 * @author 陌意随影 TODO :管理员的界面 2020年3月6日 下午9:26:25
 */
public class AdministratorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WEIHt = 1100;
	private static final int HIGHT = 700;
	private Container contentPanel = null;
	private JPanel mainPanel = null;
	private  JPanel navCenterPanel = null;
	private  JPanel centerPanel = null;
	private Account administrator = null;
	private JPopupMenu popMenu = null;
	private JButton btn_close = null;
	private JLabel lbl_currPanel = null;
	@SuppressWarnings("javadoc")
	public AdministratorFrame(LoginFrame loginFrame, Account administrator) {
		this.setTitle("图书管理系统");
		this.administrator = administrator;
		this.setTitle("图书管理系统");
		this.setBounds(100, 50, WEIHt, HIGHT);
		this.contentPanel = this.getContentPane();
		this.mainPanel = new JPanel(new BorderLayout());
		this.navCenterPanel=new JPanel();
		this.centerPanel = new JPanel();
		initConponents();
		this.setVisible(true);
		this.popMenu = new JPopupMenu();
		this.btn_close = new JButton("关闭");
		popMenu.setBorder(null);
		btnStyle(btn_close);
		this.popMenu.add(btn_close);
		initPopMenuEvent();
		// 给窗口添加监听事件
				this.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						AdministratorFrame.this.dispose();
					}
				});
		// 给窗口添加监听事件
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (loginFrame != null) {
					loginFrame.setVisible(true);
				}
				AdministratorFrame.this.dispose();
			}
		});
	}

	/**
	 * 给按钮添加通用的样式
	 * 
	 * @param btn
	 */
	private void btnStyle(JButton btn) {
		btn.setBackground(new Color(30, 159, 255));
		// 该按钮的字体取消点击时显示聚焦边框
		btn.setFocusPainted(false);
		btn.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn.setForeground(Color.WHITE);
	}
	private void initConponents() {
		// 初始化导航栏
		initNav();
		// 初始化左侧栏
		initLeft();
		// 初始化中间部分
		initCenter();
		// 初始化底部面板
		initButtom();
	}
   private void initPopMenuEvent() {
	   this.btn_close.addMouseListener(new MouseAdapter() {
		   @Override
		public void mouseClicked(MouseEvent e) {
			   removeNavCenterLabel(lbl_currPanel.getText());
			   popMenu.setVisible(false);
		}
	});
   }
   
	private void initNav() {
		BorderLayout borderLayout = new BorderLayout();
		JPanel panel = new JPanel(borderLayout);
		borderLayout.setVgap(15);
		borderLayout.setHgap(20);
		panel.setBackground(new Color(57, 61, 73));
		panel.setPreferredSize(new Dimension(1000, 40));
		JPanel panel0 = navLeftPanel();
		panel.add(panel0,BorderLayout.WEST);
		initNavCenterPanel();
		panel.add(this.navCenterPanel,BorderLayout.CENTER);
		JPanel panel2 = navRightPanel();
		panel.add(panel2,BorderLayout.EAST);
		this.contentPanel.add(panel, BorderLayout.NORTH);
	}
	/**
	 * 导航栏左侧的面板
	 * @return
	 */
	private JPanel navLeftPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(250,40));
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		JLabel lbl_tittle = new JLabel("图书管理系统");
		lbl_tittle.setFont(font);
		lbl_tittle.setForeground(Color.WHITE);
		panel.add(lbl_tittle);
		return panel;
	}
/**
 * 初始化导航栏中间的面板
 */
	private void initNavCenterPanel() {
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		flowLayout.setHgap(10);
		this.navCenterPanel.setLayout(flowLayout);
		this.navCenterPanel.setPreferredSize(new Dimension(660, 40));
		this.navCenterPanel.setBackground(new Color(57, 61, 73));
		createNavCenterLabel("首页");
	}
	
	
/**
 * 通过标签的名字来移除导航栏JLabel
 * @param text
 */
public void removeNavCenterLabel(String text) {
	if(text == null || text.trim().length() == 0) {
		return;
	}
	Component[] components = navCenterPanel.getComponents();
	if(components == null || components.length == 0) {
		return;
	}
	int index  = 0;
	JLabel lbl_curr  =null;
	for(int i = 0 ;i < components.length;i++) {
		 lbl_curr = (JLabel) components[i];
		if(text.equals(lbl_curr.getText())) {
			index = i;
			break;
		}
	}
  JPanel currPanel =	ManageFactory.getManagePanel(text, administrator,this);
  if(currPanel != null) {
	  centerPanel.remove(currPanel);
	  centerPanel.invalidate();
	  centerPanel.repaint();
  }
  if(lbl_curr != null) {
	  navCenterPanel.remove(lbl_curr);
	  navCenterPanel.invalidate();
	  navCenterPanel.repaint();
  }
  if(index  > 0) {
		JLabel lbl = (JLabel) components[index-1];
		createNavCenterLabel(lbl.getText());
	}
}
/**
 * 通过标签的名字来创建一个导航栏JLabel
 * @param text
 */
public void createNavCenterLabel(String text) {
	if(text == null || text.trim().length() == 0) {
		return ;
	}
	//获取导航栏中间面板的所有组件
	Component[] components = this.navCenterPanel.getComponents();
	boolean  isContained = false;
	//查看是否该text已经存在
	if(components!= null && components.length !=0) {
		for(Component c :components) {
			if(c instanceof JLabel) {
				JLabel lbl = (JLabel)c;
				if(text.trim().equalsIgnoreCase(lbl.getText().trim())) {
					isContained = true;
					lbl.setForeground(new Color(10,173,237));
					lbl_currPanel =lbl;
					continue;
				}else {
					lbl.setForeground(Color.WHITE);
				}
			}
		}
	}
	
	if(isContained == false) {
		Font font = new Font("微软雅黑", Font.BOLD, 16);
		JLabel lbl_index = new JLabel(text);
		lbl_index.setForeground(new Color(10,173,237));
		lbl_currPanel =lbl_index;
		lbl_index.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
				//获取导航栏中间面板的所有组件
				Component[] components = navCenterPanel.getComponents();
				//查看是否该text已经存在
				if(components!= null && components.length !=0) {
					for(Component c :components) {
						if(c instanceof JLabel) {
							JLabel lbl = (JLabel)c;
							if(text.trim().equalsIgnoreCase(lbl.getText().trim())) {
								lbl.setForeground(new Color(10,173,237));
								lbl_currPanel =lbl_index;
								
								continue;
							}else {
								lbl.setForeground(Color.WHITE);
							}
						}
					}
				}
				//清空所有的组件
				centerPanel.removeAll();
				//通过工厂模式来获取对应的panel
				JPanel managePanel = ManageFactory.getManagePanel(text,administrator,AdministratorFrame.this);
				if(managePanel != null ) {
					centerPanel.add(managePanel);
				}
				//验证容器
				centerPanel.validate();
				//重画
				centerPanel.repaint();
				lbl_index.setForeground(new Color(10,173,237));
				}else if(e.getButton() == MouseEvent.BUTTON3){
					popMenu.show(lbl_index, e.getX(), e.getY());
					popMenu.setVisible(true);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			
				lbl_index.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lbl_index.setFont(new Font("微软雅黑", Font.BOLD, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl_index.setFont(new Font("微软雅黑", Font.BOLD, 16));
			}
		});
		lbl_index.setFont(font);
//		lbl_index.setForeground(Color.WHITE);
		this.navCenterPanel.add(lbl_index);
		//验证容器
		this.navCenterPanel.validate();
		//重画容器
		this.navCenterPanel.repaint();
	}
	//清空所有的组件
	centerPanel.removeAll();
	//通过工厂模式来获取对应的panel
	JPanel managePanel = ManageFactory.getManagePanel(text,administrator,AdministratorFrame.this);
	if(managePanel != null ) {
		centerPanel.add(managePanel);
	}
	//验证容器
	centerPanel.validate();
	//重画
	centerPanel.repaint();
	
	
}


/**
 * 导航栏右侧的面板
 * @return
 */
	private JPanel navRightPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setPreferredSize(new Dimension(190, 40));
		panel.setBackground(new Color(57, 61, 73));
		Font font1 = new Font("微软雅黑", Font.BOLD, 14);
		JLabel lbl_welcome = new JLabel("欢迎您,Admin：" + administrator.getName());
		lbl_welcome.setFont(font1);
		lbl_welcome.setForeground(Color.WHITE);
		panel.add(lbl_welcome);
		return panel;
	}
   
	private void initLeft() {
		FlowLayout flowLayout = new FlowLayout();
		JPanel panel = new JPanel(flowLayout);
	     flowLayout.setVgap(1);
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(200, 50));
		FlowLayout flowLayout1 = new FlowLayout(FlowLayout.LEFT);
		JPanel panel1 = new JPanel(flowLayout1);
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(195, 30));
	     createBtnPanel(panel1,"首页",AdminitratorAllImageIcons.INDEX);
	     createBtnPanel(panel1,"图书管理",AdminitratorAllImageIcons.BOOKMANAGE);
	     createBtnPanel(panel1,"删除",AdminitratorAllImageIcons.DELETE);
	     createBtnPanel(panel1,"读者管理",AdminitratorAllImageIcons.USERMANAGE);
	     createBtnPanel(panel1,"设置",AdminitratorAllImageIcons.SYSMANAGE);
		panel.add(panel1);
		//绘制管理菜单
		JPanel panel2 = new JPanel(flowLayout1);
		panel2.setOpaque(false);
		panel2.setPreferredSize(new Dimension(195, 400));
		//系统管理菜单
         JMenuBar sysMenuBar = createMenuBar("系统管理","设置","管理员管理","管理员个人信息","修改密码","日志管理");
		//用户管理菜单
		  JMenuBar uerMenuBar  = createMenuBar("用户管理","读者管理","角色管理");
		//图书管理菜单
		  JMenuBar bookMenuBar = createMenuBar("图书管理","图书管理","分类管理");
		  //借阅管理菜单
		  JMenuBar borrowbookMenuBar = createMenuBar("借阅管理","借书管理","还书管理","借书和还书书");

		panel2.add(sysMenuBar);
		panel2.add(uerMenuBar);
		panel2.add(bookMenuBar);
		panel2.add(borrowbookMenuBar);
		panel.add(panel2);
		this.contentPanel.add(panel, BorderLayout.WEST);
	}

	private JPanel createBtnPanel(JPanel panel,String name,ImageIcon icon) {
		JButton btns = new JButton();
		Dimension dimension = new Dimension(35, 22);
		btns.setOpaque(false);
		btns.setBorder(BorderFactory.createEmptyBorder());
		btns.setPreferredSize(dimension);
		btns.setIcon(icon);
		btns.addActionListener(e->{
			createNavCenterLabel(name);
		});
		btns.setToolTipText(name);
		btns.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btns.setBorder(BorderFactory.createLineBorder(new Color(89,134,129),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btns.setBorder(null);
			}
		});
		panel.add(btns);
		return panel;
	}


	private void initCenter() {
	this.centerPanel.setPreferredSize(new Dimension(600,500));
    this.contentPanel.add(this.centerPanel,BorderLayout.CENTER);
	}

	private void initButtom() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(57, 61, 73));
		panel.setPreferredSize(new Dimension(1000, 30));
		this.contentPanel.add(panel, BorderLayout.SOUTH);
	}
	/**
	 * 通过JMenu的名字和item的名字来创建一个JmenuBar
	 * @param menuName
	 * @param itemsName
	 * @return  JMenuBar
	 */
	private JMenuBar createMenuBar(String menuName,String...itemsName) {
		if (menuName == null) {
			menuName = "";
		}
		JMenuBar sysMenuBar = new JMenuBar();
		Dimension dimension = new Dimension(190, 30);
		sysMenuBar.setPreferredSize(dimension);
		JMenu ysymenu = new JMenu(menuName);
		ysymenu.setPreferredSize(dimension);
		if (itemsName != null) {
			for (String name : itemsName) {
				JMenuItem item = new JMenuItem(name);
				item.setPreferredSize(dimension);
				item.addActionListener(e->{
					//添加点击事件通过item的text来创建卡片面板显示在导航栏中
					createNavCenterLabel(name);
				});
				ysymenu.add(item);
				
			}
		}
		sysMenuBar.add(ysymenu);
		sysMenuBar.setBackground(Color.WHITE);
		return sysMenuBar;

	}
}
