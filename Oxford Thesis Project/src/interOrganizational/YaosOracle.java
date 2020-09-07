package interOrganizational;

import java.util.LinkedList;

public class YaosOracle {

	public static boolean timecomparison(String ftime, String stime) {
		//is the first time stamp more recent than the second? Format: 2014-12-12T05:06:38+01:00
		
		int fYear = Integer.parseInt(ftime.substring(0,4));
		int sYear = Integer.parseInt(stime.substring(0,4));
		
		int fMonth = Integer.parseInt(ftime.substring(5,7));
		int sMonth = Integer.parseInt(stime.substring(5,7));
		
		int fDay = Integer.parseInt(ftime.substring(8,10));
		int sDay = Integer.parseInt(stime.substring(8,10));
		
		int fHour = Integer.parseInt(ftime.substring(11,13));
		int sHour = Integer.parseInt(stime.substring(11,13));
		
		int fMin = Integer.parseInt(ftime.substring(14,16));
		int sMin = Integer.parseInt(stime.substring(14,16));
		
		int fSec = Integer.parseInt(ftime.substring(17,19));
		int sSec = Integer.parseInt(stime.substring(17,19));
		
		double difference = (fYear-sYear)*262800*60 + (fMonth-sMonth)*720*60 + (fDay-sDay)*24*60+ (fHour-sHour)*60 + ((fMin-sMin));
		
		//might need to change the = depending on the order of checks
		if(difference <= 0) {
			return true; 
		}
		else return false; 
	}
	
	public static String listTimeComparison(LinkedList<String> timeList) {
		boolean full = false;
		
		for(int i=0;i<timeList.size();) {
			for(int j=0;j<timeList.size();) {
				if(timecomparison(timeList.get(i),timeList.get(j))){
					full=true;
					j++;
				}
				else {full=false; i++; j=0; }
			}
			if(full=true) {
				return timeList.get(i);
			}
		}
		return null;
	}
	
}
