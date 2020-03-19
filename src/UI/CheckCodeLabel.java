package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;

/**
 * @author 陌意随影 TODO :验证码的JLabel重写控件 2020年1月8日 下午11:53:39
 */
public class CheckCodeLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private static Random random = new Random();
	private int width = 60;// 宽
	private int height = 25;// 高
	private int font_size = 20;// 字体的大小
	private int x = 0;// 坐标x
	private int y = 0;// 坐标y
	private int jam = 5;// 干扰元素 建议使用 4~7 之间的数字
	private String code = "";// 验证码的内容

	@SuppressWarnings("javadoc")
	public CheckCodeLabel() {
		super("验证码");
		this.setPreferredSize(new Dimension(width, height));
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
	}

	/**
	 * 
	 * @return 返回随机生成的颜色
	 */
	public Color getRandomColor() {
		int R = random.nextInt(255), G = random.nextInt(255), B = random.nextInt(255);
		return new Color(R, G, B);
	}

	/**
	 * 绘画验证码
	 * 
	 * @param g
	 */
	public void checkCode(Graphics g) {
		drawBorder(g);
		drawCode(g);
		drawJam(g);
	}

	/**
	 * 绘画边框
	 * 
	 * @param g
	 */
	public void drawBorder(Graphics g) {
		// 记录初始的颜色
		Color gc = g.getColor();
		// 设置矩形
		g.setColor(Color.WHITE);
		// 填充矩形
		g.fillRect(x, y, width, height);
		// 设置颜色
		g.setColor(Color.BLACK);
		// 绘画矩形
		g.drawRect(x, y, width, height);
		g.setColor(gc);
	}

	/**
	 * 绘画验证码的内容
	 * 
	 * @param g
	 */
	public void drawCode(Graphics g) {
		Color c = g.getColor();
		g.setFont(new Font("微软雅黑", Font.BOLD, font_size));
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwsyz0123456789".toCharArray();
		int index = 0;
		int len = ch.length;
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawString(ch[index] + "", i * 12 + 2, font_size);
			sb.append(ch[index]);
		}
		g.setColor(c);
		// 设置验证码的内容
		this.code = sb.toString().trim();
	}

	/**
	 * 绘画干扰元素
	 * 
	 * @param g
	 */
	public void drawJam(Graphics g) {
		Color gc = g.getColor();
		for (int i = 0; i < jam; i++) {
			g.setColor(getRandomColor());
			g.drawLine(x + random.nextInt(width), y + random.nextInt(height), x + random.nextInt(width),
					y + random.nextInt(height));
		}
		g.setColor(gc);
	}

	/**
	 * 重写父类的paint方法
	 */
	public void paint(Graphics g) {
		Color c = g.getColor();
		checkCode(g);
		g.setColor(c);
	}

	/**
	 * @return 返回验证码的内容
	 */
	public String getCode() {
		return code.trim();
	}
}