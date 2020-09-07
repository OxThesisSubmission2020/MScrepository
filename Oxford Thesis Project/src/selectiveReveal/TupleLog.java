package selectiveReveal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random; 

public class TupleLog {
	public List<Tuple> trace = new LinkedList<Tuple>();
	public int[][] revealMatrix; 

	
	public TupleLog(List<SimpleEvent> trace) {
		revealMatrix= new int[8][2]; 
		for(int i =0; i<trace.size();i++){
			//calculate the differences in timestamps
			Timestamp time = new Timestamp(0,0,0,0,0);
			if(i>0&&i!=4) {time = trace.get(i-1).timestamp;}
			int duration = trace.get(i).timestamp.difference(time);
			String activityName = trace.get(i).activityName;
			String prevActivityName= "START";
			if(i>0) {prevActivityName = trace.get(i-1).activityName;}
			int identifier=-1;
			while(identifier<0) {identifier = new Random().nextInt();}
			
			//adding to reveal matrix 
			if(trace.get(i).MedicineType.equals("Medicine A")) {revealMatrix[i][0]=identifier;}
			if(trace.get(i).MedicineType.equals("Medicine B")) {revealMatrix[i][1]=identifier;}
			
			Tuple tuple = new Tuple(activityName, prevActivityName, duration, identifier);
			this.trace.add(tuple);
		}
	}
	
	public String revealMedicineType(String MedicineType) {
		String returnString = "";
		if(MedicineType.equals("Medicine B")) {
			for(int i=0;i<revealMatrix.length;i++) {
				if(revealMatrix[i][1]!=0) {
					returnString = returnString+revealMatrix[i][1]+" ";
				}
			}
		}
		return returnString;
	}
	
	public String toString() {
		String string = "";
		for(int i=0;i<trace.size();i++) {
			string = string+trace.get(i).toString()+"\r\n";
		}
	return string;
	}

}
