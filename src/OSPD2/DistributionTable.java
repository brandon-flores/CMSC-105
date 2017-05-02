package OSPD2;
public class DistributionTable {
	private Object[][] data;
	
	
	public DistributionTable(Object[][] data){
		this.data = data;
	}
	
	public String getTable(){
		String table = "";
		
		for(Object[] row : data){
        	for(Object column : row){table+=(column + "\t\t");}
        	table+="\n";
        }
		
		return table;
	}
}
