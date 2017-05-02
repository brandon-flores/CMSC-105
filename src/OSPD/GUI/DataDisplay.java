package OSPD.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by brandon on 3/24/2017.
 */
public class DataDisplay extends JPanel{
    private Main main;
    private String title = "Input Display", output = "";
    private ArrayList<String> frame;
    private JButton proceed;
    private JTextArea display;
    private JLabel header;

    DataDisplay(Main main){
        this.main = main;
        frame = new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        header = new JLabel("Data Input", SwingConstants.CENTER);
        header.setFont(new Font("Goudy Old Style", Font.BOLD, 45));

        display = new JTextArea(8, 5);

        Font f = new Font("Bitstream Vera Serif", Font.BOLD, 35);

        //title
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        //c.gridx = 0;
        //c.gridy = 0;
        c.ipady = 40;
        c.weightx = 0.5;
        c.gridwidth = 4;
        //c.weighty = 0.1;
        //c.insets = new Insets(120,0,0,0);
        c.gridx = 0;
        c.gridy = 0;
        add(header, c);

        //display
        JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(720, 720));

        c.ipady = 400;
        c.gridwidth = 5;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        add(scrollPane, c);

        //proceed
        proceed = new JButton("Proceed");
        c.ipady = 0;
        c.weighty = 1.0;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 3;
        add(proceed, c);

        /*JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(600, 600));*/

        proceed.setFont(f);
        proceed.addActionListener(new MyActionListener());

        display.setEditable(false);
       // add(scrollPane);

    }

    void setFrame() {
        this.frame = main.getMenu().getMethods().getData();
    }

    public String getTitle() {
        return title;
    }

    public void display(){
        int ctr = 1;
        for(String e: frame){
            display.append("Index " + ctr + ": " + e + "\n");
            //output += "Index " + ctr + ": " + e + "\t";
            ctr++;
        }
        //display.replaceSelection(output);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*if(e.getSource() == proceed){
                main.as.getMethods().printResult();
                main.sr.display();
                main.cardLayout.show(main.mainWindow, main.sr.getTitle());
                JOptionPane.showMessageDialog(null, "These are the randomized samples\n");
            }*/
           // main.getCardLayout().show(main.getMainWindow(), main.getGraph().getTitle());
            main.getCardLayout().show(main.getMainWindow(), main.getTd().getTitle());
        }
    }
}
