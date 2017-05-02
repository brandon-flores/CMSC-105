package BasicSamplingMethods.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by brandon on 3/3/2017.
 */
public class DisplayFrame extends JPanel {
    private String title = "Sampling Frame Output", output = "";
    private ArrayList<String> frame;
    private JButton proceed;
    private JTextArea display;
    private MAIN main;

    DisplayFrame(MAIN main){
        this.main = main;
        frame = new ArrayList<>();

        display = new JTextArea(40, 40);
        Font f = new Font("Cambria", Font.BOLD, 20);
        proceed = new JButton("Proceed");

        JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(600, 600));

        proceed.setFont(f);
        proceed.addActionListener(new MyActionListener());

        display.setEditable(false);
        add(scrollPane, BorderLayout.CENTER);
        add(proceed);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == proceed){
                main.as.getMethods().printResult();
                main.sr.display();
                main.cardLayout.show(main.mainWindow, main.sr.getTitle());
                JOptionPane.showMessageDialog(null, "These are the randomized samples\n");
            }
        }
    }

    String getTitle(){
        return title;
    }

    void setFrame(ArrayList<String> frame) {
        this.frame = frame;
    }

    void display(){
        int ctr = 1;
        for(String e: frame){
            output += "Index " + ctr + ": " + e + "\t";
            ctr++;
        }
        display.replaceSelection(output);
    }
}
