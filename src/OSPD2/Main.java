package OSPD2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{
	  public static final int WIDTH = 500;
	  public static final int HEIGHT = 500;

	 
	  private JButton categorical;
	  private JButton numerical;
	  private JButton quit;
	  
	  
	  public Main(){
		  	setSize(WIDTH, HEIGHT);
		  	setLocationRelativeTo(null);
		    setTitle("Summarizing and Presenting Data"); 
		    Container contentPane = getContentPane();
		    
		    JPanel inputPanel = new JPanel(new GridLayout(3,1,20,20));
		    
		    categorical = new JButton("CATEGORICAL");
		    categorical.addActionListener(this);
		    inputPanel.add(categorical);
		    
		    numerical = new JButton("NUMERICAL");
		    numerical.addActionListener(this);
		    inputPanel.add(numerical);
		    
		    quit = new JButton("QUIT");
		    quit.addActionListener(this);
		    inputPanel.add(quit);
 

		    
		    contentPane.setLayout(new BorderLayout(5,5));
		    contentPane.add(inputPanel, BorderLayout.CENTER);
		    
		    addWindowListener(new WindowAdapter() {
		        public void windowClosing(WindowEvent ev) {
		                 System.exit(0);
		        }
		    });
	  }
	  
	  public void actionPerformed(ActionEvent e)
	  {
	    String actionCommand = e.getActionCommand();
	    if (actionCommand.equals("NUMERICAL")){
	    	Numerical n = new Numerical();
	    	n.setVisible(true);
	    	this.setVisible(false);
	    	n.run();
	    }
	    else if (actionCommand.equals("CATEGORICAL")){
	    	Categorical c = new Categorical();
	    	c.setVisible(true);
	    	this.setVisible(false);
	    	c.run();
	    }
	    	
	    else if(actionCommand.equals("QUIT"))
	      System.exit(0);
	   } 
	  
	  public static void main(String[] args)
	  {
	    Main main = new Main();
	    main.setVisible(true);
	  }
	  

}
