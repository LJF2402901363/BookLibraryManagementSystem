package testTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import domain.BookCate;

/**
 * @author 陌意随影
 TODO :测试testTree
 *2020年3月22日  下午10:32:57
 */
public class testTree  extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel = null;
   public testTree() {
	   this.setBounds(100, 100, 700, 400);
	   this.panel = new JPanel();
	   BookCateTreeNode root = new BookCateTreeNode(new BookCate(1,"A","编程",new Date()));
	   root.addChild(new BookCateTreeNode(new BookCate(2,"A","文学",new Date())));
	   root.addChild(new BookCateTreeNode(new BookCate(3,"A","玄幻",new Date())));
	   BookCateTreeNode root1 = new BookCateTreeNode(new BookCate(4,"A","戏剧",new Date()));
	   root1.addChild(new BookCateTreeNode(new BookCate(5,"A","莎士比亚全集",new Date())));
	   root.addChild(root1);
	   DefaultTreeModel jMode = new DefaultTreeModel(root);
	   	JTree tree = new JTree(jMode);
	   	tree.addMouseListener(new MouseAdapter() {
	   		@Override
	   		public void mouseClicked(MouseEvent e) {
	   		if(e.getButton() ==MouseEvent.BUTTON1) {
	   			System.out.println(55);
	   		}
	   		}
		});
	   	tree.setEditable(true);
//	     tree.setCellRenderer(new FriTreeRender());
	    tree.setCellRenderer(new TreeCellRenderDemo());
    tree.setCellEditor(new BookCateTreeEditor());
//	   	tree.putClientProperty("JTree.lineStyle", "Horizontal");
//	   	tree.putClientProperty("JTree.lineStyle", "None");
	   	JScrollPane js = new JScrollPane(tree);
//	   	js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
////	   	tree.setPreferredSize(new Dimension(200,200));
	   	panel.add(js);
	   	panel.setPreferredSize(new Dimension(50,200));
	   	panel.setBorder(BorderFactory.createLineBorder(Color.red,2));
	   this.getContentPane().add(panel);
	   this.setVisible(true);
	   this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }	
public static void main(String[] args) {
	new testTree();
//	File f = new File("resources/testImgs/open.png");
//	System.out.println(f.length());
}
}

