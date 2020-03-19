package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import domain.Account;
import factory.ServiceFactory;
import service.AdministratortService;
import util.DateUtil;
import util.ImageIconUtilTools;

/**
 * @author 陌意随影
 TODO :
 *2020年2月21日  下午2:06:27
 */
public class TableCellPanel  extends JPanel{
	private static final long serialVersionUID = 1L;
	private Account account = null;
	private JPanel leftPanel = null;
	private JPanel rightPanel = null;
	private JPanel rightTopPanel = null;
	private JButton btn_update  = null;
	private	JButton btn_delete = null;
	private JButton btn_details =null;
	private AdministratortService service = null;
	private JPanel buttomPanel = null;
	protected int id = 0;
		@SuppressWarnings("javadoc")
		public TableCellPanel() {
		this.setLayout(new BorderLayout());
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				TableCellPanel.this.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				TableCellPanel.this.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),2));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				TableCellPanel.this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),1));
				
			}
		});
		this.leftPanel = new JPanel(new BorderLayout());
		this.setPreferredSize(new Dimension(320, 250));
		this.leftPanel.setPreferredSize(new Dimension(110, 200));
		this.rightPanel = new JPanel(new BorderLayout());
		this.leftPanel.setOpaque(false);
		this.rightPanel.setOpaque(false);
		this.rightPanel.setPreferredSize(new Dimension(210, 200));
		 btn_delete = new JButton("删除");
		 btn_update = new JButton("更新");
	     btn_details = new JButton("详细");
	     this.service = (AdministratortService) ServiceFactory.newInstanceService("AccountService");;
		initComponents();
		this.setBackground(new Color(164, 188, 196));
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.CENTER);
	    }
		private void initComponents() {
		initRightPanel();
		}
		
	/**
	 * 展示数据
	 */
	public void showData() {
		 if(!Objects.isNull(account)) {
			 this.rightTopPanel.removeAll();
			JLabel lbl_name = new JLabel(account.getName());
			id = account.getId();
			lbl_name.setToolTipText("用户名");
			JLabel lbl_type = new JLabel(account.getType() + "");
			lbl_type.setToolTipText("用户类型");
			String dateToStr = DateUtil.DateToStr(account.getCreateTime());
			JLabel lbl_createTime = new JLabel(dateToStr);
			lbl_createTime.setToolTipText("用户创建时间");
			JPanel namePanel = new JPanel();
			 JPanel createTimePanel=new JPanel();
			createTimePanel.setOpaque(false);
			JPanel typePanel = new JPanel();
			typePanel.setOpaque(false);
			namePanel.add(lbl_name);
			typePanel.add(lbl_type);
			createTimePanel.add(lbl_createTime);
			namePanel.setPreferredSize(new Dimension(200,30));
			typePanel.setPreferredSize(new Dimension(200,30));
			createTimePanel.setPreferredSize(new Dimension(200,30));
			ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\workspace03\\java-booksystem\\src\\下载.jpg");
			ImageIcon createHeadImage = ImageIconUtilTools.createHeadImage(icon, 0.25, 0.25);
			JButton btn = new JButton(createHeadImage);
			btn.setBackground(new Color(242,242,242));
			btn.setBorder(null);
			btn.setFocusPainted(false);
//			btn.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
			btn.setToolTipText(account.getName()+"的头像");
			btn.setPreferredSize(new Dimension(80,50));
			this.leftPanel.add(btn,BorderLayout.CENTER);
			JTextPane textPanel = new JTextPane();
			textPanel.setToolTipText("签名");
			textPanel.setBackground(new Color(240,237,232));
			textPanel.setText(account.getSignature());
			textPanel.setFont(new Font("宋体",Font.ITALIC,14));
			textPanel.setEditable(false);
			textPanel.setPreferredSize(new Dimension(80,50));
			textPanel.setForeground(new Color(1,170,237));
			this.leftPanel.add(textPanel,BorderLayout.SOUTH);
			rightTopPanel.add(namePanel);
			rightTopPanel.add(typePanel);
			rightTopPanel.add(createTimePanel);
			rightTopPanel.validate();
			rightTopPanel.repaint();
		 }else {
			 buttomPanel.removeAll();
			 buttomPanel.invalidate();
			 buttomPanel.repaint();
		 }
			

	  }
		private void initRightPanel() {
		 rightTopPanel = new JPanel();
		rightTopPanel.setPreferredSize(new Dimension(210,100));
		buttomPanel = new JPanel();
		btnStyle(btn_update);
		btnStyle(btn_delete);
		btnStyle(btn_details);
		btn_delete.setBackground(new Color(255,87,34));
		 rightTopPanel.setOpaque(false);
		 buttomPanel.setOpaque(false);
		 buttomPanel.add(btn_details);
		 buttomPanel.add(btn_update);
		 buttomPanel.add(btn_delete);
		 if(this.account == null) {
			 btn_delete.setVisible(false);
			 btn_update.setVisible(false);
			 btn_details.setVisible(false);
		 }else {
			 btn_delete.setVisible(true);
			 btn_delete.setVisible(true);
			 btn_details.setVisible(true);
		 }
		 btn_update.setPreferredSize(new Dimension(300,30));
		this.rightPanel.add(rightTopPanel,BorderLayout.CENTER);
		this.rightPanel.add(buttomPanel,BorderLayout.SOUTH);
		}
		/**
		 * 初始化按钮的事件
		 */
		public void initBtnEvents() {
		  	
			btn_update.addActionListener(e->{
				System.out.println("更改："+id);
			});
			btn_delete.addActionListener(e->{
				if(id > 0) {
					int result = JOptionPane.showConfirmDialog(null, "是否确定删除该用户？");
					if(result == JOptionPane.OK_OPTION) {
						boolean fla = service.removeAccountById(id);
						if(fla) {
						 JOptionPane.showMessageDialog(null, "删除成功！");
						}else {
							 JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}
				}
			});
			btn_details.addActionListener(e->{
				System.out.println("详细："+id);
			});
			
		}
		
		/**
		 * 给按钮添加通用的样式
		 * @param btn
		 */
		 private void btnStyle(JButton btn) {
		    	btn.setBackground(new Color(30,159,255));
		    	btn.setPreferredSize(new Dimension(40,25));
				//该按钮的字体取消点击时显示聚焦边框
				btn.setFocusPainted(false);
				btn.setFont(new Font("微软雅黑", Font.BOLD, 13));
				btn.setMargin(new Insets(10, 1, 10, 1));
				btn.setForeground(Color.WHITE);
		    }
		/**
		 * @param account
		 */
		public void setAccount(Account account) {
			if(Objects.isNull(this.account)) {
					//第一次数据
					this.account = account;
					showData();
			}
			
		}
}
