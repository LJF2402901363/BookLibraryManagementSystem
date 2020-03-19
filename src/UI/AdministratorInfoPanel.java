package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import domain.Account;
import domain.SysLog;
import factory.ServiceFactory;
import service.AdministratortService;
import service.SysLogServiceImpl;
import service.ReaderServiceImpl;
import service.SysLogService;
import util.DateUtil;
import util.ImageIconUtilTools;

/**
 * @author 陌意随影
 TODO :系统管理员信息
 *2020年2月26日  下午4:59:47
 */
public class AdministratorInfoPanel  extends JPanel{
	private static final long serialVersionUID = 1L;
    private JPanel leftPanel = null;
    private JPanel rightPanel = null;
    private Account administrator = null;
    private JButton btn_ok = null;
    private JButton btn_reset = null;
    private AdministratortService accountService = null;
    private JButton imgBtn = null;
    private JTextArea infoDescription = null;
    private JTextField  nameFiled = null;
    private JTextField  typeFiel = null;
    private JTextField statusFile = null;
    private  JTextField createTime =  null;
    private JTextField  sexField = null;
    private JTextField  ageField = null;
    private  JTextArea  hobbyField =  null;
    private SysLogService sysLogService = null;
	@SuppressWarnings("javadoc")
	public AdministratorInfoPanel(Account administrator) {
		this.administrator = administrator;
		this.leftPanel = new JPanel();
		this.rightPanel = new JPanel(new GridLayout(8,1));
		this.btn_ok = new JButton("修改");
		this.btn_reset= new JButton("重置");
		this.nameFiled  = new JTextField();
		this.typeFiel  = new JTextField();
		this.statusFile  = new JTextField();
		this.createTime  = new JTextField();
		this.sexField  = new JTextField();
		this.ageField  = new JTextField();
		this.hobbyField  = new JTextArea();
		this.accountService = (AdministratortService) ServiceFactory.newInstanceService("AdministratortService");
		this.infoDescription = new JTextArea();
		this.imgBtn =new JButton();
		this.sysLogService = (SysLogService) ServiceFactory.newInstanceService("SysLogService");
		initComponents();
		this.setLayout(new BorderLayout());
		this.add(leftPanel,BorderLayout.WEST);
		this.add(rightPanel,BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(700,600));

	}
	private void initComponents() {
		initLeftPanel();
		initBtnEvent();
		addBtnStyle(this.btn_ok);
		addBtnStyle(this.btn_reset);
        initRightPanel();
	}
	private void initBtnEvent() {
		this.btn_ok.addActionListener(e -> {
			String btnText = btn_ok.getText();
			if("修改".equals(btnText)) {
				this.infoDescription.setEditable(true);
				this.nameFiled.setEditable(true);
				this.ageField.setEditable(true);
				this.hobbyField.setEditable(true);
				this.sexField.setEditable(true);
				btn_ok.setText("确认修改");				
			}else {
				//确认修改
				String name = nameFiled.getText();
				if(name == null || name.trim().length() == 0) {
					JOptionPane.showMessageDialog(null,"请输入正确的用户名！");
					return;
				}
			
				 if(!name.equals(administrator.getName())) {
					Account account = accountService.findAccountByName(name);
					if(account != null) {
						JOptionPane.showMessageDialog(null,"该用户名已经存在！");
						return;
					}
				 }
				 String _age=ageField.getText();
				 if(_age == null || _age.trim().length() == 0) {
						JOptionPane.showMessageDialog(null,"请输入年龄！");
						return;
					}
				 int age = administrator.getAge();
				  try {
					  age = Integer.parseInt(_age);
				  }catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"请输入正确的年龄！");
					  return ;
				  }
				 String hobby = hobbyField.getText();
				 if(hobby == null || hobby.trim().length() == 0) {
						JOptionPane.showMessageDialog(null,"请输入爱好！");
						return;
				}
				 String info = infoDescription.getText();
				 if(info == null || info.trim().length() == 0) {
						JOptionPane.showMessageDialog(null,"请输入个人简介！");
						return;
					}
				 String sex = sexField.getText();
				 if(sex == null || sex.trim().length() == 0) {
						JOptionPane.showMessageDialog(null,"请输入性别！");
						return;
					}
				 if( !(Account.SEX_MALE.equals(sex)||Account.SEX_FEMALE.equals(sex)||Account.SEX_UNKNOWN.equals(sex))) {
						JOptionPane.showMessageDialog(null,"请输入正确性别！");
						return; 
				 }
				 int result = JOptionPane.showConfirmDialog(null, "确定更新吗？");
				 if(result == JOptionPane.OK_OPTION) {
					 administrator.setName(name);
					 administrator.setAge(age);
					 administrator.setHobby(hobby);
					 administrator.setSex(sex);
					 administrator.setSignature(info);
					 boolean fla = accountService.updateAccount(administrator);
					 if(fla) {
						   SysLog log = new SysLog();
						  log.setCreateTime(new Date());
						   log.setOperationType(SysLog.OPERATIONTYPE_UPDATEACCOUNT);
						   log.setOperator(administrator.getName());
						   log.setDetails("更新用户");
						   sysLogService.saveSysLog(log);
						 JOptionPane.showMessageDialog(null,"更新成功!");
					 }else {
						 JOptionPane.showMessageDialog(null,"更新失败!");
					 }
					 
				 }
				   this.infoDescription.setEditable(false);
					this.nameFiled.setEditable(false);
					this.ageField.setEditable(false);
					this.hobbyField.setEditable(false);
					this.sexField.setEditable(false);
					btn_ok.setText("修改");			
			}
		});
		this.btn_reset.addActionListener(e -> {
			if(administrator == null) {
				if("确认修改".equals(btn_ok.getText())) {
					nameFiled.setText("");
					typeFiel.setText("");
					statusFile.setText("");
					createTime.setText("");
					sexField.setText("");
					ageField.setText("");
					hobbyField.setText("");
					this.infoDescription.setText("");
				}
			}else {
				this.infoDescription.setText(administrator.getSignature());
				nameFiled.setText(administrator.getName());
				typeFiel.setText(administrator.getType()+"");
				statusFile.setText(administrator.getStatus()+"");
				createTime.setText(DateUtil.DateToStr(administrator.getCreateTime()));
				sexField.setText(administrator.getSex());
				ageField.setText(administrator.getAge()+"");
				hobbyField.setText(administrator.getHobby());
			}
		});
	}
	private void initRightPanel() {
		if(administrator == null) {
			addTextFieldStyle(nameFiled,"用户名","");
			addTextFieldStyle(typeFiel,"类型","");
			addTextFieldStyle(statusFile,"状态","");
			addTextFieldStyle(createTime,"创建时间","");
			addTextFieldStyle(sexField,"性别","");
			addTextFieldStyle(ageField,"年龄","");
			addTextFieldStyle(hobbyField,"爱好","");
		}else {
			addTextFieldStyle(nameFiled,"用户名",administrator.getName());
			addTextFieldStyle(typeFiel,"类型",administrator.getType()+"");
			addTextFieldStyle(statusFile,"状态",administrator.getStatus()+"");
			addTextFieldStyle(createTime,"创建时间",DateUtil.DateToStr(administrator.getCreateTime()));
			addTextFieldStyle(sexField,"性别",administrator.getSex());
			addTextFieldStyle(ageField,"年龄",administrator.getAge()+"");
			addTextFieldStyle(hobbyField,"爱好",administrator.getHobby());
			this.infoDescription.setText(administrator.getSignature());
		}
		hobbyField.setPreferredSize(new Dimension(300,50));
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(300,100));
		btnPanel.add(btn_ok);
		btnPanel.add(btn_reset);
		this.rightPanel.add(btnPanel);
	}
	private void addTextFieldStyle(JTextComponent textField, String lblName,String value) {
		Font font = new Font("微软雅黑",Font.LAYOUT_NO_LIMIT_CONTEXT,15);
		JPanel  panel = new JPanel();
		panel.setPreferredSize(new Dimension(360,50));
		JLabel lbl = new JLabel(lblName);
		textField.setFont(font);
		 lbl.setFont(font);
		textField.setPreferredSize(new Dimension(250,35));
		panel.add(lbl);
		textField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		textField.addMouseListener(new MouseAdapter() {
			@Override
    		public void mouseEntered(MouseEvent e) {
				textField.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
    		}
			@Override
			public void mouseExited(MouseEvent e) {
				textField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
			}
		});
		if(textField instanceof JTextArea) {
			JTextArea textArea = (JTextArea) textField;
			panel.setPreferredSize(new Dimension(360,65));
			textArea.setLineWrap(true);
			JScrollPane js = new JScrollPane(textArea);
			panel.add(js);
		}else {
			panel.add(textField);
		}
		textField.setText(value);
		textField.setEditable(false);
		this.rightPanel.add(panel);
	}
	private void initLeftPanel() {
		JPanel headPanel = new JPanel();
		ImageIcon img = null;
		if(this.administrator == null|| administrator.getHeadImgPath() == null|| administrator.getHeadImgPath().trim().length() == 0) {
			 img =	AdminitratorAllImageIcons.DEFAULTHEADIMG;
		}else {
			img = new ImageIcon(administrator.getHeadImgPath());
		}
		 img =	ImageIconUtilTools.createHeadImage(img);
		 this.imgBtn.setIcon(img);
	   imgBtn.setFocusPainted(false);
	   imgBtn.setBorder(BorderFactory.createLineBorder(new Color(0,150,136),2));
	   imgBtn.addMouseListener(new MouseAdapter() {
		   @Override
		public void mouseEntered(MouseEvent e) {
			   imgBtn.setBorder(BorderFactory.createLineBorder(new Color(0,150,136),2));
		}
		   @Override
		public void mouseExited(MouseEvent e) {
			   imgBtn.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		}
	});
	   headPanel.add(imgBtn);
		JPanel buttomPanel = new JPanel();
	   JLabel lbl = new JLabel("<html><h3>个人简介</h3><html>");
	   buttomPanel.add(lbl);
	   infoDescription.setPreferredSize(new Dimension(200,60));
	   infoDescription.setEditable(false);
	   infoDescription.addMouseListener(new MouseAdapter() {
		   @Override
		public void mouseEntered(MouseEvent e) {
			   infoDescription.setBorder(BorderFactory.createLineBorder(new Color(0,150,136),2));
		}
		   @Override
		public void mouseExited(MouseEvent e) {
			   infoDescription.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
		}
	});
		buttomPanel.add(infoDescription);
		headPanel.setPreferredSize(new Dimension(200,110));
		buttomPanel.setPreferredSize(new Dimension(200,120));
		leftPanel.setPreferredSize(new Dimension(250,150));
		this.leftPanel.add(headPanel);
		this.leftPanel.add(buttomPanel);
		
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
}
