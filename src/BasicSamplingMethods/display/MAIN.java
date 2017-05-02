package BasicSamplingMethods.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by brandon on 3/1/2017.
 */
public class MAIN extends JFrame {
    JPanel mainWindow;
    CardLayout cardLayout;
    Menu menu;
    AskSample as;
    DisplayFrame df;
    ShowRandom sr;

    public MAIN(){
        super("Basic Sampling Methods");
        reset();
        setResizable(false);
        setSize(720, 720);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    void reset(){
        //new MAIN();
        mainWindow = new JPanel();
        cardLayout = new CardLayout();
        menu = new Menu(this);
        as = new AskSample(this);
        df = new DisplayFrame(this);
        sr = new ShowRandom(this);
        mainWindow.setLayout(cardLayout);
        mainWindow.add(menu, menu.getTitle());
        mainWindow.add(as, as.getTitle());
        mainWindow.add(df, df.getTitle());
        mainWindow.add(sr, sr.getTitle());

        setContentPane(mainWindow);
        cardLayout.show(mainWindow, "main");
    }

}
