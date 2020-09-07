package input;
import java.util.Arrays;
import java.util.List;

public class Structure {
public String log[][] = new String[5][6];
List<String> CaseIDs;
List<String> Activities;
List<String> TimeStamp;
List<String> Cost;
String Header;
int idcolumn; 
int nextidcolumn;
int columns;
int rows;
int timecolumn;
int activitycolumn;
int caseIDcolumn;
int measurementscolumn;
String startactivityname;
List<Boolean> encryptAES;
List<Boolean> encryptPaillier;

	public Structure() {
		// TODO Auto-generated constructor stub
		Header = "ID      Act     Time    		Cost    ID      NextID   ";
		List<String> CaseIDs = Arrays.asList("1", "1", "1", "2", "2");
		List<String> Activities = Arrays.asList("A", "B", "C", "A", "C");
		List<String> TimeStamp = Arrays.asList("10-12-2010:09.12", "10-12-2010:10.14", "10-12-2010:11.22", "10-12-2010:13.47", "10-12-2010:17.12");
		List<String> Cost = Arrays.asList("40", "30", "15", "30", "35");
		rows = 5;
		columns=6;
		activitycolumn=1;
		timecolumn=2;
		idcolumn= 4; 
		caseIDcolumn= 0;
		nextidcolumn=5;
		encryptAES = Arrays.asList(false, false, false, false, true);
		encryptPaillier = Arrays.asList(false, false, false, true, false);
		for(int i=0; i<5; i=i+1) {
			log[i][0] = CaseIDs.get(i);
			log[i][1] = Activities.get(i);
			log[i][2] = TimeStamp.get(i);
			log[i][3] = Cost.get(i);
		}
	}
	
	public Structure(String[][] log) {
		Header = "ID     Time    		Status    Group      Measurements		Activity   "; 
		rows = log.length;
		columns= log[0].length;
		activitycolumn=4;
		timecolumn=1;
		caseIDcolumn=0;
		idcolumn= 6; 
		nextidcolumn=7;
		startactivityname = log[0][activitycolumn];
		encryptAES = Arrays.asList(false, false, true, false, false, false, false);
		encryptPaillier = Arrays.asList(false, false, false, true, false, false, false);
		this.log= log;
		}
	
	public void printlog() {
		System.out.println('\n'+Header);
		for(int i=0; i<rows; i=i+1) {
			boolean four=false;
			boolean five=false;
			if(log[i][4].equals("CRP")) {four=true;}
			if(i-1>0 && log[i-1][4].equals("CRP")) {five=true;}
			for(int j=0; j<columns; j=j+1) {
				if(log[i][j]!=null) {
				System.out.print(log[i][j]);}
				if(j==4 && four) {System.out.print('\t');}
				if(j==5 && five) {System.out.print('\t');}
				if(j==4 && log[i][4].equals("ER Sepsis Triage")) {}
				if(j==5 && log[i][4].equals("ER Sepsis Triage")) {}
				else System.out.print('\t');
			}
			System.out.println();
		}
	}
	
	public String [][] getTestLog() {
		return log;
	}
	
	public void setTestLog(String testlog[][]) {
		this.log=testlog;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	//Previous activity has to be in the row before the activity column
	public int getActivityColumn() {
		return activitycolumn;
	}
	
	public int getTimeColumn() {
		return timecolumn;
	}

	public String getStartActivity() {
		// TODO Auto-generated method stub
		return startactivityname;
	}
	
	public int getCaseIDColumn() {
		return caseIDcolumn;
	}
	
	public void deleteCaseID() {
		for(int i =0; i<rows;i++) {
			log[i][caseIDcolumn]=null;
		}
	}

}
