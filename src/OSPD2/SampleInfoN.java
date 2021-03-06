package OSPD2;
import java.awt.GridLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SampleInfoN {
	

	private static JTextField title;
    private static JTextField size;
    private static int sampleSize;
    private static int dtype;
    
    public SampleInfoN(){
    	title = new JTextField("");
    	size = new JTextField("");
    	dtype = 0;
    }
    
	 static void display(Numerical numerical) {
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        
	        String[] type = new String[] {"int","float"};
	        JComboBox<String> typeList = new JComboBox<>(type);
	        JLabel errorMessage = new JLabel("");
	        
	        panel.add(errorMessage);
	        panel.add(new JLabel("Title"));
	        panel.add(title);
	        panel.add(new JLabel("Sample Size"));
	        panel.add(size);
	        panel.add(new JLabel("Select data input type"));
	        panel.add(typeList);
	        
	        boolean valid = true;
	        
	        do{
	        	int result = JOptionPane.showConfirmDialog(null, panel, "INPUT DETAILS:\t",
	    	        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    	        if (result == JOptionPane.OK_OPTION) {
	    	        	if(sampleSizeValidity(size.getText()) != 1 || !titleValidity(title.getText())){
	    	        		errorMessage.setText("INVALID INPUT");
	    	        		valid = false;
	    	        	}else{
	    	        		String selectedType = (String) typeList.getSelectedItem();
	    	        		if(selectedType == "int"){
	    	        			dtype = 0;
	    	        		}else{
	    	        			dtype = 1;
	    	        		}
//	    	        		System.out.println("You selected the type: " + selectedType);
	    	        		valid = true;
	    	        	}
	    	            
	    	        } else {
	    	        	Main m = new Main();
	    	        	m.setVisible(true);
	    	        	numerical.setVisible(false);
//	    	            System.out.println("Cancelled");
	    	            valid = true;
	    	        }
	        }while(!valid);
	    }
	 
	 private static boolean titleValidity(String stringInput){
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
	 
	 
	 
	 private static int sampleSizeValidity(String stringInput){
	        try {
	            int number = Integer.parseInt(stringInput);
	            if(number < 1){
	                return 0;
	            }else{
	                sampleSize = number;
	            }
	        } catch (NumberFormatException e) {
	            if(stringInput == null) {
	                return -1;
	            }
	            return 0;
	        }
	        return 1;
	    }
	 
	 	public String getTitle() {
			return title.getText();
		}
	 
	 	public int getDataType() {
			return dtype;
		}

	 	public static void setTitle(JTextField title) {
			SampleInfoN.title = title;
		}

		public int getSize() {
			return sampleSize;
		}

		public static void setSize(JTextField size) {
			SampleInfoN.size = size;
		}
	 
	 
}
