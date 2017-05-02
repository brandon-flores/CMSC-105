package OSPD.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by brandon on 3/22/2017.
 */
public class Main extends JFrame {
        private JPanel mainWindow;
        private CardLayout cardLayout;
        private Menu menu;
        private DataDisplay dd;
        private Graph graph;
        private TableDisplay td;

        public Main(){
            super("Organizing, Summarizing, and Presenting Data");
            reset();
            setResizable(false);
            setSize(1080, 720);
            setVisible(true);
            setLocationRelativeTo(null);
        }

        public void reset(){

            mainWindow = new JPanel();
            cardLayout = new CardLayout();
            menu = new Menu(this);

            dd = new DataDisplay(this);
            graph = new Graph(this);
            td = new TableDisplay(this);
            mainWindow.setLayout(cardLayout);
            mainWindow.add(menu, menu.getTitle());
            mainWindow.add(dd, dd.getTitle());
            mainWindow.add(graph, graph.getTitle());
            mainWindow.add(td, td.getTitle());

            setContentPane(mainWindow);
            cardLayout.show(mainWindow, "main");
        }

    public DataDisplay getDd() {
        return dd;
    }

    public JPanel getMainWindow() {
        return mainWindow;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public Menu getMenu() {
        return menu;
    }

    public Graph getGraph() {
        return graph;
    }

    public TableDisplay getTd() {
        return td;
    }
}
