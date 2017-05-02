package OSPD.statistics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by brandon on 3/24/2017.
 */
public class CategoricalNumerical {
    int population, sampleSize;
    String output;
    ArrayList<String> data;

    public CategoricalNumerical(ArrayList<String> data){
        //data = new ArrayList<>();
        this.data = data;
        output = "";
    }

    public String getOutput() {
        return output;
    }

    public void printResult(){}

    public void setSframe(ArrayList<String> data) {
        this.data = data;
    }

    public HashMap<String, String> getItemCount(){return null;}

    public ArrayList<String> getData() {
        return data;
    }
}
