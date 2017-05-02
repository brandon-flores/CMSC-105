package OSPD2;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Table {
	private JTable table;
	private DefaultTableModel model;
	private Object[][] dataCopy;
	private List<Object[]> data;
	
	public Table(List<Object[]> data)
    {
        //headers for the table
        String[] columns = new String[] {
            "CL", "True CL", "Midpoint", "FREQ" , "%","CF","C%"
        };
         
        this.data = data;
        
        dataCopy = new Object[data.size()][];
        for (int i = 0; i < dataCopy.length; i++) 
        { Object[] row = data.get(i); dataCopy[i] = row; }
         
        final Class[] columnClass = new Class[] {
            String.class, String.class, String.class, Integer.class,Float.class,Float.class,Float.class
        };
 
        //create table model with data
        model = new DefaultTableModel(dataCopy, columns) {
 
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
 
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
//            @Override
//            public void setValueAt(Object value, int row, int col) {
//        	    data[row][col] = value;
//        	    
//        	    System.out.println(data[row][col]);
//        	    fireTableStructureChanged();
//        	    
//        	}
        };
         
        table = new JTable(model);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowGrid(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Integer.class, centerRenderer);
        table.setDefaultRenderer(Float.class, centerRenderer);
    }
	public JScrollPane getTable(){
		JScrollPane jpTable = new JScrollPane(table);
//		Dimension d = table.getPreferredSize();
		jpTable.setBorder(BorderFactory.createEmptyBorder());
//		jpTable.setPreferredSize(
//		    new Dimension(d.width,table.getRowHeight()*(data.length+1)));
		return jpTable;
	}
	
	public JTable getTable2(){
		return table;
	}
	
	
	public DefaultTableModel getModel(){
		return (DefaultTableModel)model;
	}
	
	

	
	public Object[][] getTableData (JTable table) {
	    TableModel dtm = table.getModel();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    Object[][] tableData = new Object[nRow][nCol];
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)
	            tableData[i][j] = dtm.getValueAt(i,j);
	    return tableData;
	}
	
	
       
}
