package interOrganizationalChained;

import java.util.LinkedList;

public class Group {
	public String name; 
	private String[][] eventLog;
	public LinkedList<String> caseIDs;
	public LinkedList<String> activeCaseIDs; 
	public LinkedList<String> activities;
	public LinkedList<String> startActivities;
	private String[][] unconnectedLog;
	
	public Group(String[][] eventLog, String name) {
		this.eventLog = eventLog; 
		this.unconnectedLog = generateUnconntedLog(); 
		this.name = name; 
		this.caseIDs = generateCaseIDList();
		this.activeCaseIDs = generateCaseIDList();
		this.activities=uniqueActivities(eventLog);
		this.startActivities = startActivities(eventLog);
		
	}

	
	public LinkedList<String> uniqueActivities(String[][] eventLog) {
		//get unique Activities
		LinkedList<String> activities = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(activities.contains(eventLog[i][2])) {}
			else {activities.add(eventLog[i][2]);}
		}
		return activities;
	}
	
	//Method used to find number of case IDs but still generates start activities
	public LinkedList<String> startActivities(String[][] eventLog){
		LinkedList<String> startActivities = new LinkedList<String>();
		for(int i=1;i<eventLog.length;i++) {
			if(!eventLog[i][0].equals(eventLog[i-1][0])) {
				startActivities.add(eventLog[i][0]);
			}
		}
		return startActivities; 
	}
	
	public void print() {
		System.out.println("This group is called "+name); 	
		for(int i=0; i<eventLog.length;i++) {
         		for(int j=0; j<5;j++) {
         			if(j==2) {}
         			else {System.out.print(eventLog[i][j]+'\t');}
         		}
         		System.out.print(eventLog[i][2]+'\t');
         		System.out.println();
        }
    }
	
	private LinkedList<String> generateCaseIDList() {
		LinkedList<String> caseIDList = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(caseIDList.contains(eventLog[i][0])) {}
			else {
				caseIDList.add(eventLog[i][0]);
			}
		}
		return caseIDList; 
	}
	
	public String[][] releaseUnconntedLog() {
		return this.unconnectedLog;
	}
	
	public String[][] generateUnconntedLog() {
		String [][] unconnectedLog = new String[eventLog.length][5];
		int counter =0;
		for(int i=1;i<eventLog.length;i++) {
			if(eventLog[i][0].equals(eventLog[i-1][0])) {
				unconnectedLog[counter][0]=eventLog[i][2];
				unconnectedLog[counter][1]=eventLog[i-1][2];
				counter++;
			}
		}
		return unconnectedLog; 
	}
	
}
	
 
