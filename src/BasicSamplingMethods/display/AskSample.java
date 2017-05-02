package BasicSamplingMethods.display;

import BasicSamplingMethods.techniques.Methods;
import BasicSamplingMethods.techniques.SimpleRandom;
import BasicSamplingMethods.techniques.Stratified;
import BasicSamplingMethods.techniques.Systematic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by brandon on 3/2/2017.
 */
public class AskSample extends JPanel {

    private Methods methods;
    private MAIN main;
    private JButton add;
    private JTextField input;
    private String title = "Ask Sample";
    private ArrayList<String> items;
    private int popu, ctr;
    private int dataType; // 0 - string, 1 - int

    AskSample(MAIN main){
        this.main = main;
        ctr = 0;
        items = new ArrayList<>();
        dataType = -1;

        Font f = new Font("Cambria", Font.BOLD, 20);
        add = new JButton("Add item");
        input = new JTextField(10);

        add.addActionListener(new MyActionListener());
        add.setFont(f);

        add(input);
        add(add);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == add){
                ctr++;
                String data = input.getText();
                if(ctr == 1){
                    if(checkDataType(data)) {
                        dataType = 1;
                        items.add(data);
                    }
                    else {
                        dataType = 0;
                        if(data.length() != 1){
                            ctr--;
                            JOptionPane.showMessageDialog(null, "Invalid input!\n");
                        }else items.add(data);
                    }
                }else{
                    if(dataType == 1){
                        if(checkDataType(data)){
                            items.add(data);
                        }else{
                            ctr--;
                            JOptionPane.showMessageDialog(null, "Invalid input!\n");
                        }
                    }else{
                        if(checkDataType(data) || data.length() != 1){
                            ctr--;
                            JOptionPane.showMessageDialog(null, "Invalid input!\n");
                        }else{
                            items.add(data);
                        }
                    }
                }
                input.setText("");
                if(ctr == popu){
                    main.df.setFrame(items);
                    main.df.display();
                    JOptionPane.showMessageDialog(null, "You have reached your desired total population\n");
                    main.cardLayout.show(main.mainWindow, main.df.getTitle());
                }
            }
        }
    }

    private boolean checkDataType(String data){
        try {
            Integer.parseInt(data);
            return true;
        }
        catch (NumberFormatException err) {
            return false;
        }
    }

    public String getTitle() {
        return title;
    }

    void setMethod(int i, int popu, int frame){
        this.popu = popu;
        if(i == 1) methods = new SimpleRandom(popu, frame);
        if(i == 2) methods = new Systematic(popu, frame);
        if(i == 3) methods = new Stratified(popu, frame);
        methods.setSframe(items);
    }

    Methods getMethods() {
        return methods;
    }
}
