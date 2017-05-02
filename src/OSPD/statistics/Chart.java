package OSPD.statistics;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChartBuilder;

/**
 * Created by brandon on 3/25/2017.
 */
public class Chart {

    private Object chart;

    public Chart(int type){
        if(type == 1){
            chart = new PieChartBuilder().width(1080).height(620).title(getClass().getSimpleName()).build();
        }else{
            chart = new CategoryChartBuilder().width(800).height(600).title("Histogram").xAxisTitle("Midpoint").yAxisTitle("Frequency").build();
        }
    }


}
