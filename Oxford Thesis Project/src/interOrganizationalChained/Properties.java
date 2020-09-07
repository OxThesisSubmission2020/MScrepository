package interOrganizationalChained;

import java.util.LinkedList;
import interOrganizationalChained.Group;

public class Properties {
	static String[][] eventLog;
	static String[][] eventLogA;
	static String[][] eventLogB;
	static String[][] eventLogC;
	static LinkedList<String> groupNames; 
	static LinkedList<Group> groups; 

	/*We now generate groups as:
	 *Group A = From START to ER Triage
	 *Group B = From ER Sepsis Triage to Leucocytes
	 *Group C = From CRP to END */
	
	public Properties(String[][] eventLog) {
		this.eventLog = eventLog; 
		this.groupNames = generateGroupNames();
		groups= new LinkedList<Group>();
		splitLog();
	}
	
	public void printAllGroups() {
		for(int i=0;i<groups.size();i++) {
			groups.get(i).print();
		}
	}
	
	public static LinkedList<String> generateGroupNames() {
		//Just returns Groups A,B,C
		LinkedList<String> groupNames = new LinkedList<String>();
		groupNames.add("A"); groupNames.add("B"); groupNames.add("C");
		return groupNames;
	}
	
	public void splitLog() {
		//Find Case ID's which have the right hand-overs
		LinkedList<String> relevantCaseID = new LinkedList<String>();
		LinkedList<Integer> relevantCaseIDEventsA = new LinkedList<Integer>();
		LinkedList<Integer> relevantCaseIDEventsB = new LinkedList<Integer>();
		LinkedList<Integer> relevantCaseIDEventsC = new LinkedList<Integer>();
		
		for(int i=0;i<eventLog.length;i++) {
			if(eventLog[i][2].equals("ER Sepsis Triage")&&eventLog[i-1][2].equals("ER Triage")) {
				for(int j=0; j<eventLog.length;j++) {
					if(eventLog[i][0].equals(eventLog[j][0])&&eventLog[j][2].equals("CRP")&&eventLog[j-1][2].equals("Leucocytes")){
						if(!relevantCaseID.contains(eventLog[j][0])&&(i<j)) {
							relevantCaseID.add(eventLog[j][0]);
							relevantCaseIDEventsA.add(i);
							relevantCaseIDEventsB.add(j);
						}
					}
				}
			}
		}
		
		//Find the number of entries per Group EventLog
		int counterA = 0;
		int counterB = 0;
		int counterC = 0;
		
		for(int i=0; i<relevantCaseID.size();i++) {
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][0].equals(relevantCaseID.get(i))) {
					if(j<relevantCaseIDEventsA.get(i)) {
						counterA++;
					}
					else if(j<relevantCaseIDEventsB.get(i)) {
						counterB++;
					}
					else {
						counterC++;
					}
				}
			}
		}
		
		//Use the Counters to Generate the Sub-EventLogs
		String[][] eventLogA = new String[counterA][5];
		String[][] eventLogB = new String[counterB][5];
		String[][] eventLogC = new String[counterC][5];
		
		//Reset the Counters 
		counterA = 0;
		counterB = 0;
		counterC = 0;
		
		//Fill each EventLog
		for(int i=0; i<relevantCaseID.size();i++) {
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][0].equals(relevantCaseID.get(i))) {
					if(j<relevantCaseIDEventsA.get(i)) {
						for(int n=0;n<5;n++) {
							eventLogA[counterA][n]= eventLog[j][n];
						}
						counterA++;
					}
					else if(j<relevantCaseIDEventsB.get(i)) {
						for(int n=0;n<5;n++) {
							eventLogB[counterB][n]= eventLog[j][n];
						}
						counterB++;
					}
					else {
						for(int n=0;n<5;n++) {
							eventLogC[counterC][n]= eventLog[j][n];
						}
						counterC++;
					}
				}
			}
		}
		
		
		System.out.println(relevantCaseID);
		System.out.println(relevantCaseIDEventsA);
		System.out.println(relevantCaseIDEventsB);
		System.out.println(relevantCaseIDEventsC);
		
		//Create all new Groups
		Group groupA = new Group(eventLogA, "A");
		groupA.print();
		groups.add(groupA);
		Group groupB = new Group(eventLogB, "B");
		groupB.print();
		groups.add(groupB);
		Group groupC = new Group(eventLogC, "C");
		//groupC.print();
		groups.add(groupC);
	}
	
}
