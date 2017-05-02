package OSPD2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.knowm.xchart.XChartPanel;

import java.awt.*; 
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numerical extends JFrame implements ActionListener
{
  public static final int WIDTH = 850;
  public static final int HEIGHT = 550;
  public static final int LINES = 10;
  public static final int CHAR_PER_LINE = 40;
  
  private int dataType;
  private String output ="";
  private JTextField theInput;
  private JTextPane theOutput;
  private JLabel label;
  private ArrayList<Double> sampleData;
  private static Container contentPane;
  JComboBox<String> collapseMenu;
  private JPanel outputPanel;
  private JPanel inputPanel;
  private  DecimalFormat formatter;

  public JPanel getOutputPanel() {
	return outputPanel;
}


public Numerical()
  {
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setTitle("Numerical"); 
    contentPane = getContentPane();
    
    formatter = new DecimalFormat( "#.#### " );
    formatter.setRoundingMode(RoundingMode.CEILING);
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setDecimalSeparator('.');
    formatter.setDecimalFormatSymbols(dfs);
    double dd = 100.2397;
    double dd2dec = new Double(formatter.format(dd)).doubleValue();

    
    label = new JLabel("NUMERICAL");
    label.setMaximumSize(new Dimension(100,100));
    
    
    inputPanel = new JPanel(new GridLayout(1,4));
    inputPanel.setBorder(BorderFactory.createTitledBorder("Text Fields: "));
    
    theInput = new JTextField(100);
    inputPanel.add(theInput);
    
    String[] collapse = new String[] {"Collapse None","Collapse Upper Limit","Collapse Lower Limit","Collapse Both"};
    collapseMenu = new JComboBox<>(collapse);
    collapseMenu.setEnabled(false);
    inputPanel.add(collapseMenu);
    
    JButton back = new JButton("Back to Main Menu");
    back.addActionListener(this);
    inputPanel.add(back);
    
    
    outputPanel = new JPanel(new GridLayout(2,1)); 
    
    theOutput = new JTextPane();
    theOutput.setText("\n");
    theOutput.setEditable(false);
    theOutput.setBackground(new Color(204, 238, 241)); 
    
    
    JScrollPane tAreaScrollPane = new JScrollPane(theOutput);
    tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    tAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    outputPanel.add(tAreaScrollPane);
    
    contentPane.setLayout(new BorderLayout());
    contentPane.add(inputPanel, BorderLayout.SOUTH);
    contentPane.add(outputPanel, BorderLayout.CENTER);
    contentPane.add(label,BorderLayout.NORTH);
    
    
    
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent ev) {
                 System.exit(0);
        }
    });

  }
  

public void run(){
	
	  SampleInfoN s = new SampleInfoN();
	  s.display(this);

	  
	  label.setText(s.getTitle());
	  output += "\t";
	  
	  
	  dataType = s.getDataType();
	  sampleData = new ArrayList<Double>();
	  
	  
	  
	  theInput.addActionListener(new ActionListener() {
		  int ctr = 0;
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(ctr>=s.getSize()){
	        		theInput.setEnabled(false);
	        	}else{
	        		
	        		if(checkValidity(theInput.getText(),dataType)){
//		        		 output+=("\t" + theInput.getText());
		        		
		        		 if(dataType == 0){
//		        			 System.out.println("INTEGER");
		        			 sampleData.add((double) Integer.parseInt(theInput.getText()));
		        			 output+=("\t" + theInput.getText());
		        		 }else{
		        			
		        			 double input = new Double(formatter.format(Float.parseFloat(theInput.getText())));
		        			 sampleData.add(input);
		        			 output+=("\t" + input);
		        		 }
		        		 theOutput.setText(output);
		        		 ctr++;
		        		 if(ctr%5==0){
		        			 output+=("\n\t");
		        			 theOutput.setText(output);
		        		 }
		        		 if(ctr == s.getSize()){
		        			 JOptionPane.showMessageDialog(Numerical.this, "Proceed to summary table.");
		        			 if(JOptionPane.OK_OPTION == 0){
		        				 
		        			      showSummaryTable(s.getSize());
		        			      
		        			 }
		        			 
		        		 }
	        		}else{
	        			JOptionPane.showMessageDialog(Numerical.this, "Invalid input");
	        		}
	        	}
	        	
	        		 
	        }
	     });
  }



private boolean checkValidity(String input, int dataType){
	  if(dataType == 0){ //integer
		  try {
	            int number = Integer.parseInt(input);
	            if(number < 1){
	                return false;
	            }
	        } catch (NumberFormatException e) {
	            if(input == null) {
	                return false;
	            }
	            return false;
	        }
	        return true;
	  }else{//float
		  try {
	            float number = Float.parseFloat(input);
	            if(number < 1){
	                return false;
	            }
	        } catch (NumberFormatException e) {
	            if(input == null) {
	                return false;
	            }
	            return false;
	        }
	        return true;
	  }
}


private boolean checkStringValidity(String stringInput){
	Pattern p = Pattern.compile("^[ A-z]+$");
    Matcher m = p.matcher(stringInput);
    boolean b = m.matches();
    if (stringInput.trim().length() <= 0 || stringInput.length() <= 0
            || stringInput.charAt(0) == ' ' || !b){
            //|| !stringInput.chars().allMatch(Character::isLetter)){
        return false;
    }
    return true;
}

  
  
  private  void showSummaryTable(int n){
	  theInput.setEnabled(false);
      collapseMenu.setEnabled(true);
      
      
      
	  
	  	Collections.sort(sampleData,new numComparator());
	  	
		ArrayList<Integer> freq = new ArrayList<Integer>();
		output ="";
		
		ArrayList<Double> vlabel = new ArrayList<Double>();
		
		int ssize = sampleData.size();
		
		
		double max = ((Double)sampleData.get(ssize-1)).doubleValue();
		double min = ((Double)sampleData.get(0)).doubleValue();
		double range = max-min;
		double k = Math.ceil(1+3.322*Math.log10(n));
		double classWidth = Math.ceil(range/k)-1;
		List<Object[]> data = new ArrayList<Object[]>();
		
//		Object[][] data = new Object[range.intValue()/classWidth][];

		double upper = 0;
		float cf=0,cp = 0;
		int frqncy;
		for(double lower= min;lower<=max;){
			
			upper = lower + classWidth;
			
			
			
			
			
			String boundary = "" + lower;
			String[] result = boundary.split("\\.");
//			System.out.println(result[0] + " " + result[1].length());
			double tens;
			if((result[1].charAt(result[1].length()-1)) == '0'){
				tens = Math.pow(10, (result[1].length()-1)*-1);
			}else{
				tens = Math.pow(10, (result[1].length())*-1);
			}
			
			//double tens = Math.pow(10, (result[1].length())*-1);
			double bound = tens/2;
//			System.out.println("bouhnds:" + bound + "LOWER" + lower);
			
			 
			
			NumberFormat formatter2 = NumberFormat.getInstance(Locale.US);
		    formatter2.setMaximumFractionDigits(5);
		    formatter2.setMinimumFractionDigits(0);
		    formatter2.setRoundingMode(RoundingMode.HALF_UP); 
			
			String CL = formatter.format(lower) + "  -  " + formatter.format(upper);
			double lowerTrue = lower - bound;
			double upperTrue = upper + bound;
			String trueCL = formatter.format(lowerTrue) + "  -  " + formatter.format(upperTrue);
			
			double midpoint = (lowerTrue+upperTrue)/2;
			//midpoint = new Double(formatter.format(midpoint));
//			System.out.println("mid" + formatter.format(midpoint));
			String midpointT = midpoint + "";
			midpointT = formatter.format(midpoint);
			vlabel.add(midpoint);
			
			
			Number curr;
			frqncy = 0;
			
			
			for(int index = 0 ; index!=n ; index++){
				curr =  (Number) sampleData.get(index);
				if(curr.floatValue()>=lower && curr.floatValue()<=upper){
					frqncy++;
				}
			}
			
			
			freq.add(frqncy);
			cf+=frqncy;
			float percentage = (float)((int)frqncy*100/n);
			cp+=percentage;

			data.add(new Object[]{CL,trueCL,midpointT,frqncy,percentage,cf,cp});
			
			lower = lower + classWidth+1;
			
		}
		
		theOutput.setText(output);
		Table t = new Table(data);
		theOutput.insertComponent(t.getTable());
		t.getModel().addRow(new Object[]{" "," ","total",cf,cp,cf,cp});
		
		//theOutput.add(comp)
		
		collapseMenu.addActionListener(
	    		  new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if(collapseMenu.getSelectedItem().equals("Collapse None")){
							int i = 0;
							for(Object[] a : data){
								((DefaultTableModel)t.getModel()).setValueAt(a[2], i++, 2);
							}
							((DefaultTableModel)t.getModel()).setValueAt(data.get(0)[0], 0, 0);
							((DefaultTableModel)t.getModel()).setValueAt(data.get(data.size()-1)[0], data.size()-1, 0);
						}else{
							if(collapseMenu.getSelectedItem().equals("Collapse Upper Limit")){
								((DefaultTableModel)t.getModel()).setValueAt(data.get(data.size()-1)[2], data.size()-1, 2);
								((DefaultTableModel)t.getModel()).setValueAt("-", 0, 2);
								((DefaultTableModel)t.getModel()).setValueAt(data.get(data.size()-1)[0], data.size()-1, 0);
								((DefaultTableModel)t.getModel()).setValueAt("below " + min, 0, 0);
							}else if(collapseMenu.getSelectedItem().equals("Collapse Lower Limit")){
								((DefaultTableModel)t.getModel()).setValueAt(data.get(0)[2], 0, 2);
								((DefaultTableModel)t.getModel()).setValueAt("-", data.size()-1, 2);
								((DefaultTableModel)t.getModel()).setValueAt(data.get(0)[0], 0, 0);
								((DefaultTableModel)t.getModel()).setValueAt(max + " and above", data.size()-1,0);
							}else{
								((DefaultTableModel)t.getModel()).setValueAt("below " + min, 0, 0);
								((DefaultTableModel)t.getModel()).setValueAt(max + " and above", data.size()-1,0);
								((DefaultTableModel)t.getModel()).setValueAt("-", data.size()-1, 2);
								((DefaultTableModel)t.getModel()).setValueAt("-", 0, 2);
							}
						}
						
					}
	    		  }
	    );
		
		JPanel prompt = new JPanel(new GridLayout(0, 1));

		prompt.add(new JLabel("Generate Graph"));

		this.add(prompt);
		
		int result = JOptionPane.showConfirmDialog(Numerical.this,prompt,"Generate Graph",JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			JTextField xtext = new JTextField("");
	    	JTextField ytext = new JTextField("");
	    	JLabel errorMessage = new JLabel("");
	    	
	    	prompt.add(errorMessage);
	    	prompt.add(new JLabel("Provide x and y labels"));
			prompt.add(new JLabel("x"));
			prompt.add(xtext);
			prompt.add(new JLabel("y"));
			prompt.add(ytext);
	        
			
			boolean valid = true;
			do{
				int result2 = JOptionPane.showConfirmDialog(Numerical.this, prompt,"Generate Graph",JOptionPane.OK_CANCEL_OPTION);
				if(result2 == JOptionPane.OK_OPTION){
					if(!checkStringValidity(xtext.getText()) || !checkStringValidity(ytext.getText())){
						errorMessage.setText("INVALID INPUT!");
						valid = false;
					}else{
						valid = true;
						BarCharty bar = new BarCharty(vlabel,freq,label.getText(),xtext.getText(),ytext.getText());
						bar.display(this);
					}
					
				}else{
					prompt.setVisible(false);
					break;
				}
			}while(!valid);
			
			

		}
  }
  
  	static <T extends Number> T parseString(String str, Class<T> cls) {
	    if (cls == Float.class) {
	        return (T) Float.valueOf(str);
	    } else if (cls == Integer.class) {
	        return (T) Integer.valueOf(str);
	    }
	    throw new IllegalArgumentException();
	}
  
	class numComparator implements Comparator<Object> {
	    @Override
	    public int compare(Object a, Object b) {
	    	return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString()));
	    }
	}
	

  public void actionPerformed(ActionEvent e)
  {
    String actionCommand = e.getActionCommand();
    if (actionCommand.equals("Back to Main Menu")){
//    	System.out.println("sdaas");
    	Main m = new Main();
    	m.setVisible(true);
    	this.setVisible(false);
    }
   } 
  
}