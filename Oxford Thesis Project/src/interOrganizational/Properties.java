package interOrganizational;

import java.util.LinkedList;

public class Properties {
	static String[][] eventLog;
	static LinkedList<String> groupNames; 
	static LinkedList<Group> groups; 

	
	public Properties(String[][] eventLog) {
		this.eventLog = eventLog; 
		this.groupNames = generateGroupNames(eventLog);
		this.groups = new LinkedList<Group>();
		splitLog();
	}
	
	public void printAllGroups() {
		for(int i=0;i<groups.size();i++) {
			groups.get(i).print();
		}
	}
	
	public static LinkedList<String> generateGroupNames(String[][] eventLog) {
		//get unique groups
		LinkedList<String> groupNames = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(groupNames.contains(eventLog[i][3])) {}
			else {groupNames.add(eventLog[i][3]);}
		}
		return groupNames;
	}
	
	public static void splitLog() {
		for(int i=0;i<groupNames.size();i++) {
			//Check how many entries will be needed for a group
			int numberOfGroupEvents =0;
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][3].equals(groupNames.get(i))) {
					numberOfGroupEvents++;
				}
			}
			
			//Generate the partial log of each group
			String[][] tempEventLog = new String[numberOfGroupEvents][5]; 
			int counter = 0; 
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][3].equals(groupNames.get(i))) {
					
					for(int m=0;m<5;m++) {
						tempEventLog[counter][m]=eventLog[j][m];
					}
					counter++;
				}
			}
			Group temporary = new Group(tempEventLog, groupNames.get(i));
			//PRINT SURPRESSED temporary.print();
			groups.add(temporary);
		}
	}

}
