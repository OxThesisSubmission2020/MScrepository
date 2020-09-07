package dfgMap;

import java.util.LinkedList;
import java.lang.Integer.*;

public class DFGMatrix {
	
	String[][] Eventlog;
	String[][] eventLog;
	LinkedList<String> activities; 
	String[][] DFG; 

	public DFGMatrix(String[][] Eventlog, LinkedList<String> activities){
		this.activities=activities; 
		this.Eventlog = Eventlog;
		this.eventLog = Eventlog;
		
		transformLog(); //only include if we are testing Chained Inter-Organizational Settings
		blankMatrix(); //initiate the blank Matrix
		generateFollows(); //add the directly follows relations
		print(); //show the result
	}
	
	public void blankMatrix(){
		//Create the blank DFG Matrix including an extra row and column to name elements
				String[][] DFG = new String[activities.size()+1][activities.size()+1];
				//Name the elements of the table
		        for(int i=0;i<activities.size();i++) {
		        	DFG[0][i+1]=activities.get(i);
		        	DFG[i+1][0]=activities.get(i);
		        }
		        //Add zeros into all entries
		        for(int i=1;i<DFG.length;i++) {
		        	for(int j=1;j<DFG.length;j++) {
		        		DFG[i][j]="0";
		        	}
		        }
		this.DFG = DFG;
	}
	
	public void transformLog() {
		
	LinkedList<String> relevantCaseID = new LinkedList<String>();
		for(int i=0;i<eventLog.length;i++) {
			if(eventLog[i][2].equals("ER Sepsis Triage")&&eventLog[i-1][2].equals("ER Triage")) {
				for(int j=0; j<eventLog.length;j++) {
					if(eventLog[i][0].equals(eventLog[j][0])&&eventLog[j][2].equals("CRP")&&eventLog[j-1][2].equals("Leucocytes")){
						if(!relevantCaseID.contains(eventLog[j][0])&&(i<j)) {
							relevantCaseID.add(eventLog[j][0]);
						}
					}
				}
			}
		}
		
		int counter=0;
		
		for(int i=0; i<relevantCaseID.size();i++) {
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][0].equals(relevantCaseID.get(i))) {
					counter++;
				}
			}
		}
		
		String[][] transformedLog = new String[counter][5];
		
		counter=0;
		for(int i=0; i<relevantCaseID.size();i++) {
			for(int j=0;j<eventLog.length;j++) {
				if(eventLog[j][0].equals(relevantCaseID.get(i))) {
						for(int n=0;n<5;n++) {
							transformedLog[counter][n]= eventLog[j][n];
						}
						counter++;
					}
				}
			}
		
		this.eventLog=transformedLog;
		this.Eventlog=transformedLog;
	}
	
	public void generateFollows() {
		//add all the follows relations into the DFG
		for(int i=0; i<Eventlog.length;i++) {
			if((Eventlog[i][2].equals("ER Registration"))){}
			else {
				int firstIndex = activities.indexOf(Eventlog[i][2])+1;
				int secondIndex = activities.indexOf(Eventlog[i-1][2])+1;
				int temp = Integer.parseInt(DFG[firstIndex][secondIndex])+1;
				DFG[firstIndex][secondIndex] = temp+"";
			}
		}
	}
	
	public void print() {
		for(int i=0;i<DFG.length;i++) {
			for(int j=0;j<DFG.length;j++) {
				System.out.print(DFG[i][j]+"      ");
			}
			System.out.println();
		}
	}
	
}
