package BasicSamplingMethods.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brandon on 3/3/2017.
 */
public class ShowRandom extends JPanel {

    private String title = "Show Random";
    private JButton finish;
    private JTextArea display;
    private MAIN main;

    ShowRandom(MAIN main){
        this.main = main;
        display = new JTextArea(40, 40);
        Font f = new Font("Cambria", Font.BOLD, 20);
        finish = new JButton("Finish");
        JScrollPane scrollPane = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(600, 600));

        finish.setFont(f);
        finish.addActionListener(new MyActionListener());

        display.setEditable(false);
        add(scrollPane, BorderLayout.CENTER);
        add(finish);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == finish){
                main.cardLayout.show(main.mainWindow, main.menu.getTitle());
                main.reset();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    void display(){
        display.replaceSelection(main.as.getMethods().getOutput());
    }
}
