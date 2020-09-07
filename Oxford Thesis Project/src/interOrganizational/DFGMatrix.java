package interOrganizational;
import interOrganizational.YaosOracle;
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
		//TODO add all the follows relations into the DFG
		
				for(int i=1; i<numberOfCaseIDs+1;i++) {
					int caseID = i;
					boolean loop = true;
					String lastEntryName = "";
					
					while(loop) {
								
					LinkedList<String> eventTimes = new LinkedList<String>();
					for(int j=0;j<groups.size();j++) {
						if(groups.get(j).caseIDs.contains(""+caseID)) {
							if(!(groups.get(j).latestEntryTime(""+caseID)==null)) {
							eventTimes.add(groups.get(j).latestEntryTime(""+caseID)+" "+j);
							}
						}
					}
					String lowestTime = YaosOracle.listTimeComparison(eventTimes);	
					int lowestTimeGroup = Integer.parseInt(lowestTime.substring(26,lowestTime.length()));
					String nextEntryName = groups.get(lowestTimeGroup).latestEntryName(caseID+"");
					
					//Adding to the Matrix
					if(lastEntryName.equals("")) {}
					else {
						System.out.println(caseID +" "+ lastEntryName+", "+nextEntryName);
						addToMatrix(lastEntryName, nextEntryName);
					}
					lastEntryName = nextEntryName; 
					groups.get(lowestTimeGroup).deleteLatestEntry(caseID+"");
					
					//Check if the CaseID has been cleared
					loop=false; 
					for(int n=0;n<groups.size();n++) {
						if(!(groups.get(n).caseIDEmpty(caseID+""))){
							loop=true;
						}
					}

					}
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
