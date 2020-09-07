package selectiveReveal;

public class SimpleEvent {
	Timestamp timestamp; 
	int caseID;
	String activityName;
	String MedicineType; 
	
	public SimpleEvent(Timestamp timestamp, int caseID, String activityName, String MedicineType) {
		this.timestamp= timestamp; 
		this.caseID= caseID; 
		this.activityName= activityName; 
		this.MedicineType = MedicineType; 
	}

	public void setMedicineType(String MedicineType) {
		this.MedicineType=MedicineType;
	}
	
	public String getMedicineType() {
		return MedicineType;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getCaseID() {
		return caseID;
	}

	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public String toString() {
		return timestamp.toString()+" ID:"+caseID+" "+activityName+" "+MedicineType;
	}
	
	
}
