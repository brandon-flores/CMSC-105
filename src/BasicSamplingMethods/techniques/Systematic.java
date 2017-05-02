package BasicSamplingMethods.techniques;

import java.util.Random;

/**
 * Created by brandon on 3/3/2017.
 */
public class Systematic extends Methods {
    public Systematic(int popu, int sample) {
        super(popu, sample);
    }

    public void printResult(){
        int k = population/sampleSize;
        Random r = new Random();
        int sp = r.nextInt(k);
        int ctr = 1;
        while(sampleSize!=0){
            output += "Index " + ctr + ":  " + sframe.get(sp) + "\t";
            sp += k;
            if(sp > population) sp -= population;
            sampleSize--;
            ctr++;
        }
    }
}
