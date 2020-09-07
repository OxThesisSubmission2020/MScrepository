package dfgMap;

import org.deckfour.xes.model.XLog;
import input.Read;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.deckfour.xes.in.XesXmlParser;

public class MainClass {

	public static void main(String[] args) throws Exception {
		XLog log = readSingleLog("C:\\Users\\bodob\\Documents\\eclipse-workspace\\NewPackageIvy\\src\\Input\\Sepsis Cases - Event Log.xes");
		System.out.println("Sucessfully loaded");
		Read readObject = new Read();
		String [][] output = readObject.initiateMatrix(log);
		LinkedList<String> activityList = uniqueActivities(output);
		DFGMatrix DFG = new DFGMatrix(output, activityList);
	}
	

	public static LinkedList<String> uniqueActivities(String[][] eventLog) {
		//get unique Activities
		LinkedList<String> activities = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(activities.contains(eventLog[i][2])) {}
			else {activities.add(eventLog[i][2]);}
		}
		return activities;
	}

	public static XLog readSingleLog(String fileName) throws Exception {
		File initialFile = new File(fileName);
		InputStream inputStream = new FileInputStream(initialFile);
		XesXmlParser parser = new XesXmlParser();
		List<XLog> parsedLogs = parser.parse(inputStream);
		
		if (parsedLogs.size() > 0) {
			System.out.println(parser.description());
			return parsedLogs.get(0);
		}
		return null;
	}

}
