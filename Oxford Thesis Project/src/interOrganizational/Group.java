package interOrganizational;

import java.util.LinkedList;

public class Group {
	public String name; 
	private String[][] eventLog;
	public LinkedList<String> caseIDs;
	public LinkedList<String> activeCaseIDs; 
	public LinkedList<String> activities;
	public LinkedList<String> startActivities;
	private String[][] DFGWorkingLog;
	
	public Group(String[][] eventLog, String name) {
		this.eventLog = eventLog; 
		this.DFGWorkingLog = eventLog; 
		this.name = name; 
		this.caseIDs = generateCaseIDList();
		this.activeCaseIDs = generateCaseIDList();
		this.activities=uniqueActivities(eventLog);
		this.startActivities = startActivities(eventLog);
		
		//this is required to work with all the time stamps that are null in the original log
		markAllEmptyStamps(); 
	}
	
	public void markAllEmptyStamps() {
		for(int i=0;i<DFGWorkingLog.length;i++) {
			if(DFGWorkingLog[i][1].equals("null")) {
				this.DFGWorkingLog[i][0]="0";
			}
		}
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
	
	public LinkedList<String> startActivities(String[][] eventLog){
		LinkedList<String> startActivities = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(eventLog[i][2].equals("ER Registration")) {
				if(startActivities.contains(eventLog[i][0])) {}
				else {startActivities.add(eventLog[i][0]);
				}
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
	
	public boolean caseIDEmpty(String caseID) {
		boolean empty = true; 
		for(int i=0;i<DFGWorkingLog.length;i++) {
			if(DFGWorkingLog[i][0].equals(caseID)) {
				empty= false; 
			}
		}
		return empty; 
	}
	
	public void deleteLatestEntry(String caseID) {
		for(int i=0;i<DFGWorkingLog.length;i++) {
			if(DFGWorkingLog[i][0].equals(caseID)) {
				DFGWorkingLog[i][0] = "0";
				//double check this if else (built to bridge a problem w case 366
				if(i==DFGWorkingLog.length-1) {
					activeCaseIDs.remove(caseID);
				} else {
				if(DFGWorkingLog[i+1][0].equals(caseID)) {}
				else {activeCaseIDs.remove(caseID);}
				i = DFGWorkingLog.length+1;
				}
			}	
		}
	}
	
	public String latestEntryTime(String caseID) {
		for(int i=0;i<DFGWorkingLog.length;i++) {
			if(DFGWorkingLog[i][0].equals(caseID)) {
				return DFGWorkingLog[i][1];
			}
		}
		return null;
	}
	
	public String latestEntryName(String caseID) {
		for(int i=0;i<DFGWorkingLog.length;i++) {
			if(DFGWorkingLog[i][0].equals(caseID)) {
				return DFGWorkingLog[i][2];
			}
		}
		return null;
	}
	
}
	
 
