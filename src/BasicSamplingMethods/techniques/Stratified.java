package BasicSamplingMethods.techniques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by brandon on 3/3/2017.
 */
public class Stratified extends Methods{
    public Stratified(int popu, int sample) {
        super(popu, sample);
    }

    public void printResult(){
        ArrayList<ArrayList> strata = new ArrayList<>();
        Random r = new Random();

        Collections.sort(sframe);

        for (int i = 0; i < population; i++) {
            String first = sframe.get(i);
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(sframe.get(i));
            while (i + 1 < sframe.size() && first.equals(sframe.get(i + 1))) {
                tmp.add(sframe.get(i));
                i++;
            }
            strata.add(tmp);
        }

        for(int cnt = 0 ; cnt < strata.size() ; cnt++){
            int rss = (int) Math.ceil((float)( (strata.get(cnt)).size() * sampleSize ) / 100) ; //random sample size
            output += "\nStrata " + (cnt+1) + " (n = " + strata.get(cnt).size() + " )\n";
            for(int ctr = 0 ; ctr < rss ; ctr++){
                output += "\t" + strata.get(cnt).get(r.nextInt(strata.get(cnt).size())) + "\n";
            }

        }
    }
}
