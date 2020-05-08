package testTree;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 * @author 陌意随影
 TODO :节点面板
 *2020年3月26日  下午11:46:24
 */
public class BookCateNodePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField text = null;
	private JButton btn = null;
	private boolean isExpanded = false;
	@SuppressWarnings("javadoc")
	public BookCateNodePanel() {
		 this.text = new JTextField();
		  this.btn = new JButton();
		  this.text.setPreferredSize(new Dimension(90,30));
		  this.add(btn);
		  this.add(text);
		  this.btn.setBorder(null);
		  this.btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBorder(BorderFactory.createLineBorder(new Color(0,150,136),1));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBorder(BorderFactory.createLineBorder(new Color(30,159,255),1));
			}
		  });
		  this.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseEntered(MouseEvent e) {
		    		BookCateNodePanel.this.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
		    	}
		    	@Override
		    	public void mouseExited(MouseEvent e) {
		    		BookCateNodePanel.this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
		    	}
			});
		  
		    Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
				@Override
				public void eventDispatched(AWTEvent event) {
					Object source = event.getSource();
			        if (source instanceof JComponent) {
			            JComponent comp = (JComponent) source;
			            if (SwingUtilities.isDescendingFrom(comp, BookCateNodePanel.this)) {
			            	BookCateNodePanel.this.setBorder(BorderFactory.createLineBorder(new Color(95,184,120),2));
			            }else {
				    		BookCateNodePanel.this.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));
			            }
			        }
				}
			}, AWTEvent.MOUSE_EVENT_MASK);
		  this.setOpaque(false);
		  this.text.setOpaque(false);
		  this.text.setEditable(false);
	}
	
	/**
	 * 设置图片
	 */
	public void setIcon(Icon icon) {
		this.btn.setIcon(icon);
	}
	/**
	 * 设置文本
	 * @param text
	 */
	public void setText(String text) {
		this.text.setText(text);
	}
	public JTextField getText() {
		return text;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	
}
