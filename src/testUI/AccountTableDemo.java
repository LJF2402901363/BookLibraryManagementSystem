package testUI;

import javax.swing.JTable;

/**
 * @author 陌意随影
 TODO :
 *2020年2月21日  下午3:58:25
 */
public class AccountTableDemo  extends JTable {
	private static final long serialVersionUID = 1L;
	private AccountTableModelDemo model;
   @SuppressWarnings("javadoc")
public AccountTableDemo() {
	   super();
   }
   @SuppressWarnings("javadoc")
public AccountTableDemo(AccountTableModelDemo model) {
	   super(model);
	   this.model = model;
   }
public AccountTableModelDemo getModel() {
	return model;
}
/**
 * @param model
 */
public void setModel(AccountTableModelDemo model) {
	super.setModel(model);
	this.model = model;
}
   
   
   
}
