package OSPD2;
public class Data {
	
	
	String cl;
    String trueCL;
    String midpoint;
    int freq;
    float percentage;
    float cf;
    float cfPercentage;
    
    public Data(String cl, String trueCL, String midpoint, int freq, float percentage, float cf, float cfPercentage) {
		super();
		this.cl = cl;
		this.trueCL = trueCL;
		this.midpoint = midpoint;
		this.freq = freq;
		this.percentage = percentage;
		this.cf = cf;
		this.cfPercentage = cfPercentage;
	}
}
