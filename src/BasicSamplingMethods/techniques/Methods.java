package BasicSamplingMethods.techniques;

import org.jfree.chart.JFreeChart;
import org.knowm.xchart.XChartPanel;

import java.util.ArrayList;

/**
 * Created by brandon on 3/3/2017.
 */
public class Methods {
    int population, sampleSize;
    String output;
    ArrayList<String> sframe;

    Methods(int popu, int sample){
        population = popu;
        sampleSize = sample;
        sframe = new ArrayList<>();
        output = "";
    }

    public String getOutput() {
        return output;
    }

    public void printResult(){}

    public void setSframe(ArrayList<String> sframe) {
        this.sframe = sframe;
    }
}
