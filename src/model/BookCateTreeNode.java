package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import domain.BookCate;


/**
 * @author 陌意随影
 TODO :图书分类的节点
 *2020年3月7日  下午7:50:38
 */
public class BookCateTreeNode implements TreeNode{
    private BookCate node;
	private List<TreeNode> children;//孩子节点
	private TreeNode parent;//父亲节点
	
	@SuppressWarnings("javadoc")
	public BookCateTreeNode( BookCate node) {	
		this.node=node;		
	}
	@SuppressWarnings("javadoc")
	public BookCateTreeNode() {	
	
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(BookCateTreeNode parent) {
		this.parent = parent;
	}


	/**
	 * @param aChild  添加孩子节点
	 */
	public void addChild(BookCateTreeNode aChild){
		
		if(children==null){
			children=new ArrayList<TreeNode>();
		}
		children.add(aChild);
		aChild.parent=this;
	}
	/**
	 * 添加孩子节点
	 * @param bookCateTreeNodes  添加孩子节点
	 */
	public void addAllChild(BookCateTreeNode...bookCateTreeNodes ){
		if(bookCateTreeNodes == null || bookCateTreeNodes.length == 0) {
			return;
		}
		if(children==null){
			children=new ArrayList<TreeNode>();
		}
		for(BookCateTreeNode bookCateNode:bookCateTreeNodes) {
			bookCateNode.parent=this;
			children.add(bookCateNode);
		}
	}
	/***
	 * 判断是否为根节点
	 * @return 如果是根节点则返回true，否则返回false
	 */
	public boolean isroot(){
		
		return (getParent()==null);
			
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		if (children == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
		return children.get(childIndex) ;
	}

	@Override
	public int getChildCount() {
		if (children == null) {
            return -1 ;
        }
		return children.size();
	}

	@Override
	public TreeNode getParent() {
		
		return parent;
	}	
	@Override
	public int getIndex(TreeNode aChild) {
		
		if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild); 	
	}

	@Override
	public boolean getAllowsChildren() {
		
		return true;
	}
  
	/**
	 * @return  返回节点的值
	 */
	public BookCate getNode() {
		return node;
	}
	/**
	 * @return 返回所有孩子节点的集合
	 */
	public List<TreeNode> getAllChildren() {
		return children;
	}
	/**
	 * @param node
	 */
	public void setNode(BookCate node) {
		this.node = node;
	}
	/**
	 * @param children
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	/**
	 * @param parent
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	/**
	 * 判断是否是叶子节点
	 */
	@Override
	public boolean isLeaf() {
		
		return (getChildCount() ==-1)&&(getParent()!=null);
		
	}
 /**
 * @return  返回是否有子节点
 */
public boolean  hasChildren(){
	return getChildCount() > 0;   
   }
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return null;
	}

	 /**
	  * 判断是否是孩子节点
	 * @param aNode
	 * @return  如果是孩子节点返回true，否则返回false
	 */
	public boolean isNodeChild(TreeNode aNode) {
	        boolean retval;

	        if (aNode == null) {
	            retval = false;
	        } else {
	            if (getChildCount() == 0) {
	                retval = false;
	            } else {
	                retval = (aNode.getParent() == this);
	            }
	        }

	        return retval;
	    }
}