package BasicSamplingMethods.techniques;

import java.util.Random;

/**
 * Created by brandon on 3/3/2017.
 */
public class SimpleRandom extends Methods {

    public SimpleRandom(int popu, int sample) {
        super(popu, sample);
    }

    public void printResult(){
        Random r = new Random();
        int ctr = 1;
        while(sampleSize != 0){
            output = output + "Index " + ctr + ":   " + sframe.get(r.nextInt(sframe.size())) + "\t";
            sampleSize--;
            ctr++;
        }
   }
}
