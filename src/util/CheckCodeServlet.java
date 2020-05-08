package util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-03-01 12:14
 */
public class CheckCodeServlet {
   public static  Image getImage()  {
     //设置编码
       final  int weight=40;
       final  int hight=30;
        //加载一个图片缓冲流进内存
        BufferedImage img=new BufferedImage(weight,hight,BufferedImage.TYPE_INT_RGB);
        //获取图片的画笔
        Graphics graphics = img.getGraphics();
        //给图片填充颜色
        graphics.setColor(new Color(255,87,34));
        graphics.fillRect(0,0,weight,hight);
        //绘画边框
        graphics.drawRect(0,0,weight-1,hight-1);
        graphics.setColor(new Color(255,255,255));
        graphics.setFont(new Font("微软雅黑",Font.BOLD,14));
        graphics.drawString("删除", 10,20);
        try {
			ImageIO.write(img, "png", new File("resources/imgs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

    }
    public static void main(String[] args) {
    	CheckCodeServlet.getImage();
	}
}
