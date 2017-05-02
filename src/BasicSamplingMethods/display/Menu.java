package BasicSamplingMethods.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brandon on 3/1/2017.
 */
public class Menu extends JPanel{
    private JButton simRan, sys, strat, quit;
    private int N, n;
    private String title = "Menu";
    private MAIN main;

    Menu(MAIN main){
        this.main = main;
        N = 0;
        n = 0;

        Font f = new Font("Cambria", Font.BOLD, 20);
        simRan = new JButton("Simple Random Sampling");
        sys = new JButton("Systematic Sampling");
        strat = new JButton("Stratified Sampling");
        quit = new JButton("Quit");
        JPanel buttons = new JPanel(new GridLayout(4, 1, 0, 0));

        simRan.setFont(f);
        sys.setFont(f);
        strat.setFont(f);
        quit.setFont(f);

        buttons.add(simRan);
        buttons.add(sys);
        buttons.add(strat);
        buttons.add(quit);

        simRan.addActionListener(new MyActionListener());
        sys.addActionListener(new MyActionListener());
        strat.addActionListener(new MyActionListener());
        quit.addActionListener(new MyActionListener());

        add(buttons);
    }

    public String getTitle() {
        return title;
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == simRan){
                askPopulation(0);
                main.as.setMethod(1, N, n);
            }
            if(e.getSource() == sys){
                askPopulation(0);
                main.as.setMethod(2, N, n);
            }
            if(e.getSource() == strat){
                askPopulation(1);
                main.as.setMethod(3, N, n);
            }
            if(e.getSource() == quit){
                System.exit(1);
            }

        }
    }

    private void alert(){
        JOptionPane.showMessageDialog(null, "In this section, you will be asked to input\n"
                + "the items you want to add in your sample");
        main.cardLayout.show(main.mainWindow, main.as.getTitle());
    }

    private void askPopulation(int type){
        boolean proceed = true;
        String errorMessage = "";
        String[] ask = {"Enter population size: ", "Enter sample size",
                "Please specify percentage of the items\nfor each strata to be sampled randomly:"};
        int ctr = 0;
        do {
            String stringInput;
            if(type == 0) stringInput = JOptionPane.showInputDialog(errorMessage + ask[ctr]);
            else {
                if(ctr == 1) ctr++;
                stringInput = JOptionPane.showInputDialog(errorMessage + ask[ctr]);
            }
            try {
                int number = Integer.parseInt(stringInput);
                if ((ctr == 1 && number > N || number <= 0 || checkDefault(N, number)) ||
                        (ctr == 2 && number >= 100 || number <= 0 || checkDefault(N, number)) ||
                        (ctr == 0 && number <= 0)) {
                    errorMessage = "Invalid input!\n";
                    ctr--;
                }
                else {
                    if(ctr == 0) N = number;
                    else n = number;
                    errorMessage = ""; // no more error
                }
            } catch (NumberFormatException e) {
                if(stringInput == null) {
                    proceed = false;
                    break;
                }
                errorMessage = "Not a number!\n";
                ctr--;
            }
            ctr++;
        } while (!errorMessage.isEmpty() || ctr <= 1);
        if(proceed) alert();
    }

    private boolean checkDefault(int x, int y){
        int z = (int) Math.ceil((float)( (x * 20 ) / 100));
        return y < z;
    }
}
