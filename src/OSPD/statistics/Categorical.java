package OSPD.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by brandon on 3/29/2017.
 */
public class Categorical extends CategoricalNumerical{
    private ArrayList<String> mainItems;
    private HashMap<String, String> itemCount = new HashMap<>();
    private int total;

    public Categorical(ArrayList<String> data){
        super(data);
        total = 0;
        mainItems = new ArrayList<>();
        solveCategorical();
    }

    public void solveCategorical(){
        uniqueItems();
        for(String s : mainItems){
            int freq = Collections.frequency(data, s);
            //System.out.println("tae: " + freq);
           // total += freq;

            float percent = (float)freq / (float)data.size() * 100;
            //System.out.println("freq: " + freq + "size: " + data.size() + "percent: " + percent);
            String per = Float.toString(percent);

            itemCount.put(per, s);

            //System.out.println("String: " + s + " Key: " + percent);
        }
    }

    private void uniqueItems(){
        boolean flag = true;
        for(String s : data){
            if(flag){
                flag = false;
                mainItems.add(s);
            }else{
                if(!mainItems.contains(s)){
                    mainItems.add(s);
                }
            }
        }
        Collections.sort(mainItems);
    }

    public HashMap<String, String> getItemCount() {
        return itemCount;
    }
}
