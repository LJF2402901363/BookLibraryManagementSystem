package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import util.ImageIconUtilTools;

/**
 * @author 陌意随影
 TODO :测试工具类
 *2020年3月11日  下午5:50:10
 */
public class ImageUtil {
	/**
	 * 将指定路径的图片路径按照对应宽和高的比例来进行截取一个圆形的头像
	 * @param path   图片的路径
	 * @param weightScale  要缩放的宽比例
	 * @param heightScale  要缩放的高比例
	 */
	public static ImageIcon createHeadImage(String path, double weightScale, double heightScale) {
		ImageIcon icon = new ImageIcon(path);
		return createHeadImage(icon, weightScale,  heightScale) ;
	}
	/**
	 * 将指定的的ImageIcon的图片以指定的文件格式写入到本地指定的文件中去
	 * @param imgIcon
	 * @param desPath
	 */
	public static void saveImageIconToLocal(ImageIcon imgIcon,String desPath,String format) {
		Image image = imgIcon.getImage();
		//获取图片的宽和高
		int height = image.getHeight(null);
		int width = image.getWidth(null);
		//创建一个背景透明的图片缓存流
		BufferedImage imgBuf = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		//获取画笔
		Graphics2D graphics = imgBuf.createGraphics();
		// 圆形
		Rectangle2D.Double rec = new Rectangle2D.Double(0, 0, width, height);
		// 设置绘画区域
		graphics.setClip(rec);
		//设置背景颜色
		graphics.setBackground(Color.white);
		//绘画图片
		graphics.drawImage(image, 0, 0, null);
		//使用抗锯齿
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try {
			//以指定的格式写入到指定的文件中去
			ImageIO.write(imgBuf, format, new File(desPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将指定路径的图片路径按照对应宽和高的比例来进行截取一个圆形的头像
	 * @param imgIcon   图片的路径
	 * @param weightScale  要缩放的宽比例
	 * @param heightScale  要缩放的高比例
	 */
	public static ImageIcon createHeadImage(ImageIcon imgIcon, double weightScale, double heightScale) {
		Image img = null;
		//通过工具类获取获取缩放后的图片
		ImageIcon scaleImageIcon = ImageIconUtilTools.getScaleImageIcon(imgIcon, weightScale, heightScale);
		//获取图片
		img = scaleImageIcon.getImage();
		//获取图片的宽和高
		int weiht = img.getHeight(null);
		int hight = img.getWidth(null);
		//创建一个背景透明的缓冲流
		BufferedImage imgBuf = new BufferedImage(weiht, hight, BufferedImage.TYPE_4BYTE_ABGR);
		//获取缓冲流的画笔
		Graphics2D graphics = imgBuf.createGraphics();
		// 圆形
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, weiht, hight);
		//设置绘画区域
		graphics.setClip(circle);
		//设置绘画区域的背景颜色
		graphics.setBackground(Color.white);
		//开始绘画图片
		graphics.drawImage(img, 0, 0, null);
		//设置抗锯齿
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			//将图片缓冲流写入到一个ByteArrayOutputStream对象中
			 ImageIO.write(imgBuf, "png", out);
			 //获取图片的字节数组
			 byte[] byteArray = out.toByteArray();
			 //返回一个ImageIcon
			 return new ImageIcon(byteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ImageIcon img =ImageUtil.createHeadImage("C:\\Users\\Administrator\\workspace03\\java-booksystem\\src\\下载 (1).jpg", 0.4, 0.4);
		ImageIcon img = new ImageIcon("C:\\Users\\Administrator\\workspace03\\java-booksystem\\src\\下载 (1).jpg");
		ImageUtil.saveImageIconToLocal(img, "test3.png", "png");
	}
}
