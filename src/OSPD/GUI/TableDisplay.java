package OSPD.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by brandon on 3/25/2017.
 */
public class TableDisplay extends JPanel {

    private final static String DONT = "Do not collapse";
    private final static String FIRST = "Collapse first class limits";
    private final static String LAST = "Collapse last class limits";
    private final static String BOTH = "Collapse first and last class limits";
    private Main main;
    private String title = "Table Display";
    private JButton proceed;
    private JComboBox collapse;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel dont, first, both, last, cards;
    private CardLayout cl;
    DefaultTableModel model;
    HashMap<String, Integer> items;

    public TableDisplay(Main main) {
        this.main = main;
        String[] choices = {DONT, FIRST, LAST, BOTH};
        cl = new CardLayout();


        proceed = new JButton("Proceed");
        proceed.addActionListener(new MyActionListener());
        collapse = new JComboBox(choices);
        collapse.setSelectedIndex(0);
        collapse.addActionListener(new MyActionListener());



/*
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };*/

        /*table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1080, 620));
        table.setFillsViewportHeight(true);*/


        model = new DefaultTableModel();
        table = new JTable(model);

        //dont
        dont = new JPanel();
        dont.add(table);

        //first
        first = new JPanel();
        first.add(new JTextField(10));

        //last
        last = new JPanel();
        last.add(table);

        //both
        both = new JPanel();
        both.add(new JButton("tae"));

        //all
        cards = new JPanel();
        cards.setLayout(cl);
        cards.add(dont, DONT);
        cards.add(first, FIRST);
        cards.add(last, LAST);
        cards.add(both, BOTH);

        scrollPane = new JScrollPane(cards, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(1080, 620));

        add(scrollPane);

        cl.show(cards, DONT);
        add(collapse);
        add(proceed);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == collapse){
                JComboBox cb = (JComboBox)e.getSource();
                updateTable((String)cb.getSelectedItem());
            }

            if(e.getSource() == proceed){
                ask();
            }
        }
    }

    private void updateTable(String s) {
        String[] columnNames = {"tae",
                "tobol",
                "igit"};

        Object[][] data = {
                {"a", "b",
                        "c"},
                {"d", "e",
                        "f"},
                {"g", "h",
                        "i"}
        };

        //System.out.println(s);

        if(s.equals(DONT)){
            System.out.println(11);
            cl.show(cards, DONT);
        }if(s.equals(FIRST)){
            System.out.println(22);
            cl.show(cards, FIRST);
        }if(s.equals(LAST)){
            System.out.println(33);
            cl.show(cards, LAST);
        }if(s.equals(BOTH)){
            System.out.println(44);
            cl.show(cards, BOTH);
        }
        //table = new JTable(data, columnNames);
        //scrollPane.set(table);
    }

    public String getTitle() {
        return title;
    }

    private void ask(){
        String[] options = {"YES", "NO"};
        int dataNumber = 1;
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Show graph? ");
        //JTextField txt = new JTextField(10);
        panel.add(lbl);
        //panel.add(txt);
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
        if(selectedOption == 0) {
            main.getCardLayout().show(main.getMainWindow(), main.getGraph().getTitle());
        }
        if(selectedOption == 1) {
            main.getCardLayout().show(main.getMainWindow(), main.getMenu().getTitle());
            main.reset();
        }

    }

    public void setType(int type){
        if(type == 2){
            add(collapse);
        }
        add(proceed);
    }

    public void setMethod(int type){
        if(type == 1) {

        }
        if(type == 2){

        }
    }

    public void updateTable(){
        HashMap<String, String> items = main.getMenu().getMethods().getItemCount();
        // Get a set of the entries
        Set set = items.entrySet();

        // Get an iterator


        // Display elements
        for (Object aSet : set) {
            Map.Entry me = (Map.Entry) aSet;
            model.addRow(new Object[]{me.getValue(), me.getKey()});
            System.out.println("value: " + me.getValue() + "\t\tkey: " + me.getKey() + "\n");
        }

        model.addRow(new Object[]{"TOTAL", "100%"});
        //System.out.println("nisulod ko diri");
    }
}
