package utils;

import java.util.Comparator;
import java.util.Map;

public class MapComparator implements Comparator<Map<String, Double>> {

	public MapComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Map<String, Double> o1, Map<String, Double> o2) {
		double value1=0.0;
		double value2=0.0;
		for(String key:o1.keySet()){
			value1=o1.get(key);
		}
		for(String key:o2.keySet()){
			value2=o2.get(key);
		}
		
		if(value1<value2){
			return -1;
		}
		else{
			if(value1>value2){
				return 1;
			}
			else{
				return 0;
			}
			
		}
	}

	
	

}
