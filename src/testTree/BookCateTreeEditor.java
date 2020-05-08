package testTree;

import java.awt.Component;
import java.awt.Font;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.TreeCellEditor;

/**
 * @author 陌意随影
 TODO :测试的BookCateTreeEditor
 *2020年3月25日  下午10:06:11
 */
public class BookCateTreeEditor  extends AbstractCellEditor implements TreeCellEditor{
	private static final long serialVersionUID = 1L;
	private BookCateNodePanel  panel = null;
	ImageIcon Arrow_right = new ImageIcon("resources/testImgs/right.png");//节点折叠时的图片
//	DefaultTreeCellRenderer
	ImageIcon Arrow_down = new ImageIcon("resources/testImgs/down.png");//节点展开式的图片
	ImageIcon root_png = new ImageIcon("resources/testImgs/root.png");//节点展开式的图片
  @SuppressWarnings("javadoc")
public BookCateTreeEditor() {
  }

  
	@Override
	public Object getCellEditorValue() {
		String newValue = panel.getText().getText();
		if(newValue == null || newValue.trim().length() == 0) {
			return null;
		}
		return newValue;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		 BookCateTreeNode f = null;
		  this.panel = new BookCateNodePanel();
			if(value instanceof BookCateTreeNode) {
				 f = (BookCateTreeNode) value;//把value转换为节点
			}
			if(f.hasChildren()) {
				this.panel.getText().setFont(new Font("微软雅黑",Font.BOLD,14));
				this.panel.setText(f.getNode().getName());
				panel.getBtn().addActionListener(e->{
					if(panel.isExpanded()) {
						tree.expandPath(tree.getSelectionPath());
						panel.setExpanded(false);
					}else {
						tree.collapsePath(tree.getSelectionPath());
						panel.setExpanded(true);
					}
				});
				if(expanded) {
					this.panel.setIcon(Arrow_down);
				}else {
					this.panel.setIcon(Arrow_right);
				}
			}else {
				this.panel.setIcon(null);
				this.panel.getText().setFont(new Font("微软雅黑",Font.PLAIN,12));
				this.panel.setText(f.getNode().getName());
			}
			
		return panel;
	}
}
