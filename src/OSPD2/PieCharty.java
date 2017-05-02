package OSPD2;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.demo.charts.ExampleChart;

public class PieCharty implements ExampleChart<PieChart> {
	
	String title;
	ArrayList label;
	ArrayList freq;

	public PieCharty(ArrayList label,ArrayList freq,String title){
		this.label = label;
		this.freq = freq;
		this.title = title;
	}
 
  @Override
  public PieChart getChart() {
 
    // Create Chart
    PieChart chart = new PieChartBuilder().width(600).height(500).title(title).build();
 
    // Customize Chart
    Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
    chart.getStyler().setSeriesColors(sliceColors);
 
    // Series
    for(int i = 0 ; i<label.size() ; i++){
    	chart.addSeries((String)label.get(i), (int)freq.get(i));
    }
 
    return chart;
  }
 
  public void display(Categorical categorical){
	  PieChart chart = this.getChart();
	  JPanel pnlChart = new XChartPanel(chart);    
		categorical.getOutputPanel().add(pnlChart);
		categorical.getContentPane().validate();
  }
}
