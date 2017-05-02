package OSPD2;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Categorical extends JFrame implements ActionListener
{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 550;
  public static final int LINES = 10;
  public static final int CHAR_PER_LINE = 40;
  private int dataType;
 
  private JTextField theInput;
  private JTextArea theOutput;
  private JLabel label;
  private ArrayList<String> sampleData;
  private Container contentPane;
  private JPanel outputPanel;

  public Categorical()
  {
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setTitle("Categorical"); 
    contentPane = getContentPane();
    
    label = new JLabel("CATEGORICAL");
    label.setMaximumSize(new Dimension(100,100));
    
    
    JPanel inputPanel = new JPanel(new GridLayout(1,4));
    inputPanel.setBorder(BorderFactory.createTitledBorder("Text Fields: "));
    
    theInput = new JTextField(100);
    inputPanel.add(theInput);
    
    JButton back = new JButton("Back to Main Menu");
    back.addActionListener(this);
    inputPanel.add(back);
//    
//    JButton displayButton = new JButton("Display Summary Table");
//    inputPanel.add(displayButton);

    
    outputPanel = new JPanel(new GridLayout(1,2)); 
    
    theOutput = new JTextArea("\n");
    theOutput.setEditable(false);
    theOutput.setLineWrap(true);      
    theOutput.setWrapStyleWord(true);  
    theOutput.setBackground(new Color(204, 238, 241)); 
    
    
    JScrollPane tAreaScrollPane = new JScrollPane(theOutput);
    tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    tAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    outputPanel.add(tAreaScrollPane);
    
    
    contentPane.setLayout(new BorderLayout(5,5));
    contentPane.add(inputPanel, BorderLayout.SOUTH);
    contentPane.add(outputPanel, BorderLayout.CENTER);
    contentPane.add(label,BorderLayout.NORTH);
    sampleData = new ArrayList<String>();
    
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent ev) {
                 System.exit(0);
        }
    });

  }
  
  public void run(){
	  SampleInfoC s = new SampleInfoC();
	  s.display(this);
	  
	  
	  label.setText(s.getTitle());
	  theOutput.append("\t");
	  
	  dataType = s.getType();
	  
	  
	  theInput.addActionListener(new ActionListener() {
		  int ctr = 0;
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		if(checkValidity(theInput.getText(),dataType)){
//	        			System.out.println(checkValidity(theInput.getText(),dataType));
		        		 theOutput.append("\t" + theInput.getText());
		        		 sampleData.add(theInput.getText());
		        		 ctr++;
		        		 if(ctr%5==0){
		        			 theOutput.append("\n\t");
		        		 }
		        		 if(ctr == s.getSize()){
		        			 JOptionPane.showMessageDialog(Categorical.this, "Proceed to summary table.");
		        			 if(JOptionPane.OK_OPTION == 0){
		        				 showSummaryTable(s.getSize());
		        			 }
		        			 
		        		 }
	        		}else{
	        			JOptionPane.showMessageDialog(Categorical.this, "Invalid input");
	        		}
	        			
	        		 
	        }
	     });
  }
  
  private boolean checkValidity(String input, int dataType){
	  if(dataType == 0){ //numeric
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
	  }else if(dataType == 1){//alphabetic
		  Pattern p = Pattern.compile("^[ A-z]+$");
	        Matcher m = p.matcher(input);
	        boolean b = m.matches();
	        if(input.length() != 1){return false;}
	        else{
	        	if (input.trim().length() <= 0 || input.length() <= 0
		                || input.charAt(0) == ' ' || !b){
		                //|| !stringInput.chars().allMatch(Character::isLetter)){
		            return false;
		        }
		        return true;
	        }
	        
	  }else{//string
		  Pattern p = Pattern.compile("^[ A-z]+$");
	        Matcher m = p.matcher(input);
	        boolean b = m.matches();
		  if (input.trim().length() <= 0 || input.length() <= 0
	                || input.charAt(0) == ' ' || !b){
	                //|| !stringInput.chars().allMatch(Character::isLetter)){
	            return false;
	        }
	        return true;
	  }
  }
  
  
  private  void showSummaryTable(int n){
	  if(dataType == 0){
		  Collections.sort(sampleData,new intComparator());
	  }else{
		  Collections.sort(sampleData);
	  }
	  	
		
		ArrayList<Integer> freq = new ArrayList<Integer>();
		ArrayList<String> vlabel = new ArrayList<String>();
		String output = new String();
		
		int init = 1;
		for(int index = 0 ; index!=n ; index++){
			if(index+1!=sampleData.size() && sampleData.get(index).equals(sampleData.get(index+1))){
				init++;
				
			}else{
				vlabel.add(sampleData.get(index));
				freq.add(init);
				init = 1;
			}
		}
		
		output+=("\n\nCategorical Data: Summary Table");
		output+=("\n\n\tVALUE LABELS\t\tPERCENTAGE\n\n");
		float total = 0;
		for(int c = 0 ; c!= vlabel.size() ; c++){
			float percentage = (float)((int)freq.get(c)*100/n);
			total+=percentage;
			output+=("\n\t    " + vlabel.get(c) + "\t\t  " + percentage  + "%");
		}
		output+=("\n\n\t---------------------------------------------------------" );
		output+=("\n\n\t   total\t\t  " + total + "%" );
		
		theOutput.setText(output);
		
		
		int result = JOptionPane.showConfirmDialog(Categorical.this, "Create Graph","Create",JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION){
			PieCharty pie = new PieCharty(vlabel,freq,label.getText());
			pie.display(this);
		}
  }


	class intComparator implements Comparator<String> {
	    @Override
	    public int compare(String a, String b) {
	    	return new BigDecimal(a).compareTo(new BigDecimal(b));
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

public Container getOutputPanel() {
	return outputPanel;
} 
  
}