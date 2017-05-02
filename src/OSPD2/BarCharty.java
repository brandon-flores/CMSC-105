package OSPD2;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class BarCharty implements ExampleChart<CategoryChart> {
	
		String title,xlabel,ylabel;
		ArrayList x,y;
	 
		
		public BarCharty(ArrayList x,ArrayList y,String title,String xlabel,String ylabel){
			this.x = x;
			this.y = y;
			this.title = title;
			this.xlabel = xlabel;
			this.ylabel = ylabel;
		}
	 
		@Override
	  	public CategoryChart getChart() {
	 
		    // Create Chart
		    CategoryChart chart = new CategoryChartBuilder().width(800).height(500).title(title).xAxisTitle(xlabel).yAxisTitle(ylabel).build();
		 
		    // Customize Chart
		    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		    chart.getStyler().setHasAnnotations(true);
		 
		    // Series
		    chart.addSeries("test 1", x, y);
		 
		    return chart;
		}
		
		public void display(Numerical numerical){
			
			CategoryChart chart = this.getChart();
			//SwingWrapper sw = new SwingWrapper<CategoryChart>(chart);
			
//			numerical.getContentPane().add(sw.getXChartPanel());
			JPanel pnlChart = new XChartPanel(chart);    
			numerical.getOutputPanel().add(pnlChart);
			numerical.getContentPane().validate();
			
		}
}

