package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
 
 
 /**
 * @author 陌意随影
 TODO :自定义样式的滚动条
 *2020年3月18日  下午9:43:52
 */
public class MyScrollBarUI extends BasicScrollBarUI {
     @Override
     protected JButton createDecreaseButton(int orientation) {
    	
    	 
         JButton button =  new BasicArrowButton(orientation,
        		 new Color(0,150,136),
        		 new Color(47,64,86),
        		 new Color(47,64,86),
        		 new Color(47,64,86));
         button.setBorderPainted(false);
 
         button.setContentAreaFilled(false);
 
         button.setFocusable(false);
 
         button.setBorder(null);
         return button;
     }
     protected JButton createIncreaseButton(int orientation)  {
         JButton button =  new BasicArrowButton(orientation,
        		 new Color(0,150,136),
        		 new Color(47,64,86),
        		 new Color(47,64,86),
        		 new Color(47,64,86));
         button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont(new Font("微软雅黑",Font.BOLD,14));
         button.setContentAreaFilled(false);
         button.setFocusable(false);
         button.setBorder(null);
         return button;
     }
     
    /**
     * 重写滚动条背景区域
     */
     protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
     {
         g.setColor(new Color(241,241,241));
         g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

         if(trackHighlight == DECREASE_HIGHLIGHT)        {
             paintDecreaseHighlight(g);
         }
         else if(trackHighlight == INCREASE_HIGHLIGHT)           {
             paintIncreaseHighlight(g);
         }
     }
     protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
     {
          super.paintThumb(g, c, thumbBounds);
     }
   
 }