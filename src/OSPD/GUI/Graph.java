package OSPD.GUI;


import org.knowm.xchart.*;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by brandon on 3/24/2017.
 */
public class Graph extends JPanel {

    private String title = "Graph";
    private Main main;
    String chartTitle = "";
    private XYChart chart;

    // label
    JLabel label;
    JPanel chartPanel;
    JButton proceed;

    CategoryChart chart2;
    // Customize Chart
   // chart.getStyler()


    /*chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

    // Series
    chart.addSeries("a", new double[] { 0, 3, 5, 7, 9 }, new double[] { -3, 5, 9, 6, 5 });
    chart.addSeries("b", new double[] { 0, 2, 4, 6, 9 }, new double[] { -1, 6, 4, 0, 4 });
    chart.addSeries("c", new double[] { 0, 1, 3, 8, 9 }, new double[] { -2, -1, 1, 0, 1 });

    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {

            // Create and set up the window.
            JFrame frame = new JFrame("Advanced Example");
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // chart
            JPanel chartPanel = new XChartPanel<XYChart>(chart);
            frame.add(chartPanel, BorderLayout.CENTER);

            // label
            JLabel label = new JLabel("Blah blah blah.", SwingConstants.CENTER);
            frame.add(label, BorderLayout.SOUTH);

            // Display the window.
            frame.pack();
            frame.setVisible(true);
        }*/

    Graph(Main main){
        this.main = main;

        proceed = new JButton("Proceed");
        proceed.addActionListener(new MyActionListener());

        /*chart = new XYChartBuilder().width(1080).height(620).title(chartTitle).xAxisTitle("Midpoint").yAxisTitle("Frequency").build();

        chartPanel = new XChartPanel<>(chart);
        //label = new JLabel("Blah blah blah.", SwingConstants.CENTER);


        //customize chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

        //series
        chart.addSeries("a", new double[] { 0, 3, 5, 7, 9 }, new double[] { -3, 5, 9, 6, 5 });
        chart.addSeries("b", new double[] { 0, 2, 4, 6, 9 }, new double[] { -1, 6, 4, 0, 4 });
        chart.addSeries("c", new double[] { 0, 1, 3, 8, 9 }, new double[] { -2, -1, 1, 0, 1 });*/
        // Create Chart
        chart2 = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();
        chartPanel = new XChartPanel<>(chart2);

        // Customize Chart
        chart2.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart2.getStyler().setHasAnnotations(true);

        // Series
        chart2.addSeries("test 1", Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }), Arrays.asList(new Integer[] { 4, 5, 9, 6, 5 }));




        add(chartPanel);
        add(proceed);
        //add(label);


    }

    public String getTitle() {
        return title;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
        //chart.setTitle(chartTitle);
        chart2.setTitle(chartTitle);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == proceed){
                ask();
            }
        }
    }

    private void ask(){
        String[] options = {"YES", "NO"};
        int dataNumber = 1;
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Do you want to re-display table?");
        //JTextField txt = new JTextField(10);
        panel.add(lbl);
        //panel.add(txt);
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
        if(selectedOption == 0) {
            main.getCardLayout().show(main.getMainWindow(), main.getTd().getTitle());
        }
        if(selectedOption == 1) {
            main.getCardLayout().show(main.getMainWindow(), main.getMenu().getTitle());
            main.reset();
        }

    }
}
