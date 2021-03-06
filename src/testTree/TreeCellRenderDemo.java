package testTree;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
/**
 * @author 陌意随影
 TODO :测试TreeCellRenderDemo
 *2020年3月25日  下午7:15:26
 */

public class TreeCellRenderDemo  implements TreeCellRenderer{
	private BookCateNodePanel  panel = null;
	
	ImageIcon Arrow_right = new ImageIcon("resources/testImgs/right.png");//节点折叠时的图片
//	DefaultTreeCellRenderer
	ImageIcon Arrow_down = new ImageIcon("resources/testImgs/down.png");//节点展开式的图片
	ImageIcon root_png = new ImageIcon("resources/testImgs/root.png");//节点展开式的图片
  @SuppressWarnings("javadoc")
public TreeCellRenderDemo() {
  }
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
          BookCateTreeNode f = null;
          this.panel = new BookCateNodePanel();
		if(value instanceof BookCateTreeNode) {
			 f = (BookCateTreeNode) value;//把value转换为节点
		}
		if(f.hasChildren()) {
			this.panel.getText().setFont(new Font("微软雅黑",Font.BOLD,14));
			if(expanded) {
				this.panel.setIcon(Arrow_down);
				this.panel.setExpanded(true);
				this.panel.setText(f.getNode().getName());
			}else {
				this.panel.setIcon(Arrow_right);
				this.panel.setText(f.getNode().getName());
				this.panel.setExpanded(false);
			}
		}else {
			this.panel.setIcon(null);
			this.panel.getText().setFont(new Font("微软雅黑",Font.PLAIN,12));
			this.panel.setText(f.getNode().getName());
		}
		
		return this.panel;
	}
	public BookCateNodePanel getPanel() {
		return panel;
	}

}
