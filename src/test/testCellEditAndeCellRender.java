package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class testCellEditAndeCellRender extends JFrame {
 JTable table;
 String[] states = new String[]{"stop", "stop", "stop"};
 
 // model
 class TableModel extends AbstractTableModel{
  public int getColumnCount() {
   return 2;
  }
  public int getRowCount() {
   return states.length;
  }
  public Object getValueAt(int rowIndex, int columnIndex) {
   if(columnIndex == 0){
    return states[rowIndex];
   }
   return null;
  }
     public String getColumnName(int columnIndex) {
   if(columnIndex == 0){
    return "state";
   }else{
    return "operate";
   }
     }
     public boolean isCellEditable(int rowIndex, int columnIndex) {
   if(columnIndex == 0){
    return false;
   }else{
    return true;
   }
     }
 }
 
 // cell editor
 class Editor extends AbstractCellEditor implements TableCellEditor, ActionListener {
  int row;
  JTable table;
  JPanel panel;
  JButton start;
  JButton stop;
  Editor(){
   panel = new JPanel();
   panel.setLayout(new GridLayout(1, 2));
   start = new JButton("start");
   stop = new JButton("stop");
   start.addActionListener(this);
   stop.addActionListener(this);
   panel.add(start);
   panel.add(stop);
  }
     public Object getCellEditorValue() {
         return null;
     }
     public Component getTableCellEditorComponent(JTable table,
                                                  Object value,
                                                  boolean isSelected,
                                                  int row,
                                                  int column) {
      this.table = table;
      this.row = row;
      return panel;
     }
     
  public void actionPerformed(ActionEvent e) {
   if(e.getSource() == start){
    states[row] = "start";
   }else{
    states[row] = "stop";
   }
   ((AbstractTableModel)table.getModel()).fireTableCellUpdated(row, 0);
  }
  
 }
 
 // cell render
 class Renderer extends JComponent implements TableCellRenderer{
  JPanel panel;
  JButton start;
  JButton stop;
  Renderer(){
   panel = new JPanel();
   panel.setLayout(new GridLayout(1, 2));
   start = new JButton("start");
   stop = new JButton("stop");
   panel.add(start);
   panel.add(stop);
  }
  public Component getTableCellRendererComponent(JTable table, Object value, 
    boolean isSelected, boolean hasFocus, int row, int column) {
   return panel;
  }
 }
 
 public testCellEditAndeCellRender(){
  super("renderer and editor self-existent");
  table = new JTable(new TableModel());
  TableColumn tableColumn = table.getColumnModel().getColumn(1);
  tableColumn.setCellRenderer(new Renderer());
  tableColumn.setCellEditor(new Editor());
  
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 public static void main(String[] args) {
  new testCellEditAndeCellRender().setVisible(true);;
 }
}
