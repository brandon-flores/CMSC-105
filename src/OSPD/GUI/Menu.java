package OSPD.GUI;

import OSPD.statistics.Categorical;
import OSPD.statistics.CategoricalNumerical;
import OSPD.statistics.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brandon on 3/22/2017.
 */
public class Menu extends JPanel {
    private JButton categorical, numerical, quit;
    private static final String title = "Menu";
    private Main main;
    private JLabel header;
    private String dataTitle = "";
    private int sampleSize = 0;
    private int type = 0;//1 categorical, 2 numerical
    private ArrayList<String> data = new ArrayList<>();
    private CategoricalNumerical methods;
    //= new CategoricalNumerical();
    private Chart chart;

    Menu(Main main){
        this.main = main;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        header = new JLabel();
        header.setFont(new Font("Goudy Old Style", Font.BOLD, 45));
        header.setText("Summarizing and Presenting Data");

        Font f = new Font("Bitstream Vera Serif", Font.BOLD, 35);

        //title
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.1;
        c.insets = new Insets(120,0,0,0);
        add(header, c);

        //categorical
        categorical = new JButton("Categorical");
        categorical.setFont(f);

        //numerical
        numerical = new JButton("Numerical");
        numerical.setFont(f);

        //quit
        quit = new JButton("Quit");
        quit.setFont(f);

        //Gridlayout
        JPanel buttons = new JPanel(new GridLayout(3, 1, 0, 3));
        buttons.add(categorical);
        buttons.add(numerical);
        buttons.add(quit);
        c.anchor = GridBagConstraints.CENTER;
        add(buttons, c);

        categorical.addActionListener(new MyActionListener());
        numerical.addActionListener(new MyActionListener());
        quit.addActionListener(new MyActionListener());
    }

    public String getTitle() {
        return title;
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == categorical){
                type = 1;
                ask();
                //System.out.println(dataTitle);
                //main.getTd(); = new TableDisplay(main, type);
            }
            if(e.getSource() == numerical){
                type = 2;
                main.getTd().setType(2);
                ask();
                //System.out.println(dataTitle);
            }

            if(e.getSource() == quit){
                System.exit(1);
            }

        }
    }

    private void ask(){
        boolean proceed = true, flag = false;
        int ctr = 0;
        String errorMessage = "";
        String ask = "Describe you data set\n" +
                "(Should only contain alphabets (w/o  Ñ/ñ))";
        do {
            String stringInput;
            stringInput = JOptionPane.showInputDialog(errorMessage + ask);

            if(flag){
                ctr++;
                flag = false;
            }
            if(ctr == 0){
                if(stringInput == null){
                    proceed = false;
                    break;
                }
                flag = titleValidity(stringInput);
                if(flag) {
                    errorMessage = "";
                    dataTitle = stringInput;
                    main.getGraph().setChartTitle(dataTitle);

                    ask = "Enter sample size";
                }else{
                    errorMessage = "Invalid input!\n";
                }
            }
            if(ctr == 1){
                int x = sampleSizeValidity(stringInput);
                if(x == 0){
                    errorMessage = "Invalid input!\n";
                }else
                if(x == 1){
                    errorMessage = "";
                    ctr++;
                }else{
                    proceed = false;
                    break;
                }
            }
            System.out.println(ctr + " ");
        } while (!errorMessage.isEmpty() || ctr < 2);

        if(proceed){
            if(type == 1) askCategorical();
        }
    }

    private void askCategorical(){
        int dataType = 0; // 1 = char, 2 = num, 3 = str, 4 invalid
        int temp = sampleSize;
        JOptionPane.showMessageDialog(null, "Enter data. Inputs should be consistent. \n" +
                "If first input is a:\n1)Character - inputs should be alphabet (w/o Ñ/ñ) characters all throughout" +
                "\n2)Number - inputs should be numbers all throughout" +
                "\n3)String - inputs should be at most one word and have alphabets as it characters. Ñ/ñ and hyphenated words not allowed");
        String[] options = {"OK"};
        int dataNumber = 1;
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Enter data");
        JTextField txt = new JTextField(10);
        panel.add(lbl);
        panel.add(txt);

        while(sampleSize != 0){
            lbl.setText("Enter data " + dataNumber + ": ");
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Alert", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

            if(selectedOption == JOptionPane.CLOSED_OPTION){
                JOptionPane.showMessageDialog(null, "Close operation disabled for this section" +
                        "\nPlease continue setting your inputs");
            }
            if(selectedOption == 0) {
                String text = txt.getText();
                if(sampleSize  == temp){
                    dataType = checkDataType(text);
                }
                if(checkDataType(text) == dataType && checkDataType(text) != 4){
                    data.add(text);
                    txt.setText("");
                    sampleSize--;
                    dataNumber++;
                }
                else{
                    txt.setText("");
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        }
        alert();
    }

    private int checkDataType(String input){
        if(input.isEmpty() || input.charAt(0) == '-' || (input.charAt(0) == '0' && input.length() > 1)
                || input.charAt(0) == ' '){
            return 4;
        }
        if(input.length() == 1 && Character.isLetter(input.charAt(0))){
            return 1;
        }
        if(wordValidity(input)) return 3;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                return 4;
            }
        }
        return 2;
    }

    void setMethod(ArrayList<String> data, int type){
        //if(type == 1) methods = new Categorical(popu, frame);
        //if(i == 2) methods = new Systematic(popu, frame);
        //if(i == 3) methods = new Stratified(popu, frame);
        if(type == 1) {
            methods = new Categorical(data);
            main.getTd().setMethod(1);
        }
    }

    private void alert(){
        //main.getDd().setFrame(data);
        //main.getDd().display();
        main.getMenu().setMethod(data, type);
        main.getDd().setFrame();
        main.getDd().display();
        main.getTd().updateTable();
        JOptionPane.showMessageDialog(null, "You have reached your desired total population\n");
        main.getCardLayout().show(main.getMainWindow(), main.getDd().getTitle());
    }

    CategoricalNumerical getMethods() {
        return methods;
    }

    private boolean titleValidity(String stringInput){

        Pattern p = Pattern.compile("^[ A-z]+$");
        Matcher m = p.matcher(stringInput);
        boolean b = m.matches();
        return !(stringInput.trim().length() <= 0 || stringInput.length() <= 0
                || stringInput.charAt(0) == ' ' || !b);
    }

    private boolean wordValidity(String input){
        return input.chars().allMatch(Character::isLetter);
    }

    private int sampleSizeValidity(String stringInput){
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

}
