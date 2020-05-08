package test;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import UI.MyScrollBarUI;

/**
 * @author 陌意随影
 TODO :测试滚动面板
 *2020年3月10日  下午12:10:26
 */
public class testScopanel  extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanel = null;
	private JTextArea Text = null;
    @SuppressWarnings("javadoc")
	public testScopanel() {
    	this.setBounds(200, 100, 600, 600);
    	this.Text = new JTextArea();
    	this.scrollPanel = new JScrollPane(Text);
    	
    	this.scrollPanel.setPreferredSize(new Dimension(300,300));
//    	this.scrollPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
    	this.scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
    	this.getContentPane().add(scrollPanel);
    	initComponents();
    	initScrollPanel();
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setVisible(true);
    }
    
    private void initScrollPanel() {
		JScrollBar scrollBar = this.scrollPanel.getHorizontalScrollBar();
//		ScrollBarUI ui = scrollBar.getUI();
		BasicScrollBarUI  barUI = new BasicScrollBarUI();
		scrollBar.setUI(barUI);
		scrollPanel.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		scrollPanel.getVerticalScrollBar().setUI(new MyScrollBarUI());
		
	}

	private void initComponents() {
		this.Text.setText("MySQL查看最大连接数和修改最大连接数\r\n" + 
				"\r\n" + 
				"1、查看最大连接数\r\n" + 
				"show variables like '%max_connections%';\r\n" + 
				"2、修改最大连接数\r\n" + 
				"set GLOBAL max_connections = 200;\r\n" + 
				"\r\n" + 
				"  以下的文章主要是向大家介绍的是MySQL最大连接数的修改，我们大家都知道MySQL最大连接数的默认值是100, 这个数值对于并发连接很多的数据库的应用是远不够用的，当连接请求大于默认连接数后，就会出现无法连接数据库的错误，因此我们需要把它适当调大一些。在使 用MySQL数据库的时候，经常会遇到这么一个问题，就是“Can not connect to MySQL server. Too many connections”-mysql 1040错误，这是因为访问MySQL且还未释放的连接数目已经达到MySQL的上限。通常，mysql的最大连接数默认是100, 最大可以达到16384。\r\n" + 
				"\r\n" + 
				"    常用的修改最大连接数的最常用的两种方式如下:\r\n" + 
				"     <ImageView\r\n" + 
				"                android:layout_width=\"50dp\"\r\n" + 
				"                android:layout_height=\"50dp\"\r\n" + 
				"                android:src=\"@drawable/icon\" />\r\n" + 
				"\r\n" + 
				"            <TextView\r\n" + 
				"                android:layout_width=\"wrap_content\"\r\n" + 
				"                android:layout_height=\"wrap_content\"\r\n" + 
				"                android:layout_gravity=\"center\"\r\n" + 
				"                android:gravity=\"center_horizontal\"\r\n" + 
				"                android:text=\"小桂子\"\r\n" + 
				"                android:textSize=\"12sp\" />\r\n" + 
				"        </LinearLayout>\r\n" + 
				"\r\n" + 
				"        <TextView\r\n" + 
				"            android:id=\"@+id/robot_msg\"\r\n" + 
				"            android:layout_width=\"wrap_content\"\r\n" + 
				"            android:layout_height=\"wrap_content\"\r\n" + 
				"            android:layout_marginLeft=\"5dp\"\r\n" + 
				"            android:background=\"@drawable/chatfrom_bg_normal\"\r\n" + 
				"            android:gravity=\"center\"\r\n" + 
				"            android:layout_gravity=\"bottom\"\r\n" + 
				"            android:text=\"你好，我是小桂子\" />\r\n" + 
				"    </LinearLayout>\r\n" + 
				"————————————————\r\n" + 
				"版权声明：本文为CSDN博主「ishxiao」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\r\n" + 
				"原文链接：https://blog.csdn.net/ishxiao/article/details/80125027\r\n" 
				+"    第一种：命令行查看和修改最大连接数(max_connections)。\r\n" + 
				"\r\n" + 
				"  >mysql -uuser -ppassword(命令行登录MySQL)\r\n" + 
				"\r\n" + 
				"    mysql>show variables like 'max_connections';(查可以看当前的最大连接数)\r\n" + 
				"    msyql>set global max_connections=1000;(设置最大连接数为1000，可以再次查看是否设置成功)\r\n" + 
				"    mysql>exit  ");
		
	}
	/**
     * @param args
     */
    public static void main(String[] args) {
		new testScopanel();
	}
}
