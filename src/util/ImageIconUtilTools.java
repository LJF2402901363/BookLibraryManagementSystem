package util;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * @author 陌意随影
 TODO :ImageIcon工具类
 *2020年2月31日  下午9:57:13
 */
public class ImageIconUtilTools {
	private static HashMap<String, ImageIcon> IconMap = new HashMap<>();

	/**
	 * 获取指定路径(本地路径或者网络URL路径)的ImageIcon
	 * @param path
	 * @return 返回一个ImageIcon
	 */
	public static ImageIcon getImageIcon(String path) {
		//获取键值对中已经包含则直接返回，否则先创建再返回。
		if (IconMap.containsKey(path)) {
			return IconMap.get(path);
		}
		if(path.startsWith("http://")||path.startsWith("https://")) {
			ImageIcon img = getImageIconByUrlPath(path);
			IconMap.put(path, img);
			return img;
		}else {
			ImageIcon img = new ImageIcon(path);
			IconMap.put(path, img);
			return img;
		}
	}
  /**
   * 根据指定的URL路径来获取指定的ImageIcon
 * @param UrlPath
 * @return 返回获取的图片
 */
public  static ImageIcon getImageIconByUrlPath(String UrlPath) {
	  if(UrlPath  == null || UrlPath.trim().length() == 0) {
		  return null;
	  }
	  if(UrlPath.startsWith("http://")||UrlPath.startsWith("https://") ) {
		  BufferedImage bufImg = null;
		  ByteArrayOutputStream out = new ByteArrayOutputStream();
		  try {
			  bufImg =ImageIO.read(new URL(UrlPath));
			  ImageIO.write(bufImg, "png", out);
			  byte[] byteArray = out.toByteArray();
			  ImageIcon img = new ImageIcon(byteArray);
			  out.close();
			  return img;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  return null;
  }
	/**
	 * 将一个ImageIcon以指定的比例进行宽和高的缩放
	 * @param imgIcon   要缩放的图片
	 * @param scaleWidth  图片宽缩放的比例
	 * @param scaleHight  图片高缩放的比例
	 * @return  返回缩放后的图片
	 */
	public static ImageIcon getScaleImageIcon(ImageIcon imgIcon, double scaleWidth, double scaleHight) {
		int width = (int) (imgIcon.getIconWidth() * scaleWidth);
		int height = (int) (imgIcon.getIconHeight() * scaleHight);
		Image img = imgIcon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		return new ImageIcon(img);

	}
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
	 * 使用默认的尺寸大小：weight = 100 ,height =100 将指定路径的图片路径按照对应宽和高的比例来进行截取一个圆形的头像
	 * @param imgIcon   图片的路径
	 */
	public static ImageIcon createHeadImage(ImageIcon imgIcon) {
		Image img = null;
		int default_weight = 100 ;
		int default_height =100;
		//获取图片
		img = imgIcon.getImage();
		// 获取图片的宽和高
		int weiht = img.getHeight(null);
		int hight = img.getWidth(null);
		if(weiht <= 0) {
			return null;
		}
		double weightScale = default_weight * 1.0 /weiht;
		double heightScale = default_height * 1.0 /hight;
		
		return createHeadImage( imgIcon,  weightScale, heightScale) ;
	}
}
