package interOrganizationalChained;
import java.util.LinkedList;

public class DFGMatrix {
	
	LinkedList<String> activities; 
	LinkedList<String> groupNames;
	LinkedList<Group> groups;
	Integer numberOfCaseIDs;
	String[][] DFG; 

	public DFGMatrix(LinkedList<String> groupNames, LinkedList<Group> groups){
		this.groupNames=groupNames; 
		this.groups=groups;
		this.activities=new LinkedList<String>();
		this.numberOfCaseIDs = findNumberOfCaseIDs();
		generateUniqueActivities();
		blankMatrix(); //initiate the blank Matrix
		generateFollows(); //add the directly follows relations
		print(); //show the result
	}
	
	public void generateUniqueActivities() {
		for(int i=0;i<groups.size();i++) {
			LinkedList<String> tempActivities = groups.get(i).activities;
			for(int j=0;j<tempActivities.size();j++) {
				if(activities.contains(tempActivities.get(j))) {}
				else {activities.add(tempActivities.get(j));}
			}
		}
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
	
	//This is to confirm that the START activities of all cases are being included
	public void caseIDTest() {
		for(int i=0;i<groups.size();i++) {
			System.out.println(groups.get(i).startActivities);
		}
	}
	
	public int findNumberOfCaseIDs() {
		int tempNumberOfCaseIDs = 0;
		for(int i=0;i<groups.size();i++) {
			tempNumberOfCaseIDs= tempNumberOfCaseIDs +groups.get(i).startActivities.size(); 
		}
		return tempNumberOfCaseIDs; 
	}
	
	
	public void generateFollows() {
		//Add all the follows relations into the DFG
		String[][] groupAUnconnected = groups.get(0).releaseUnconntedLog();
		String[][] groupBUnconnected = groups.get(1).releaseUnconntedLog();
		String[][] groupCUnconnected = groups.get(2).releaseUnconntedLog();
		
		for(int i=0;i<groupAUnconnected.length;i++) {
			addToMatrix(groupAUnconnected[i][1]+"", groupAUnconnected[i][0]+"");
		}
		for(int i=0;i<groupBUnconnected.length;i++) {
			addToMatrix(groupBUnconnected[i][1]+"", groupBUnconnected[i][0]+"");
		}
		for(int i=0;i<groupCUnconnected.length;i++) {
			addToMatrix(groupCUnconnected[i][1]+"", groupCUnconnected[i][0]+"");
		}
		for(int i=0;i<groupAUnconnected.length-741;i++) {
			addToMatrix("ER Triage", "ER Sepsis Triage");
			addToMatrix("Leucocytes", "CRP");
		}

	}
	
	public void addToMatrix(String lastEntry, String nextEntry) {
		for(int i=1;i<activities.size()+1;i++) {
			for(int j=1;j<activities.size()+1;j++) {
				if(DFG[j][0].equals(nextEntry)) {
					if(DFG[0][i].equals(lastEntry)) {
						int temp = Integer.parseInt(DFG[j][i])+1;
						DFG[j][i]=temp+"";
					}
				}
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
