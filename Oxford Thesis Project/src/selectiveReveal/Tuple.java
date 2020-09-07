package selectiveReveal;

public class Tuple {
	String activity; 
	String prevActivity;
	int duration;
	int identifier;
	
	public Tuple(String activity, String prevActivity, int duration, int identifier) {
			this.activity=activity; 
			this.prevActivity=prevActivity; 
			this.duration=duration; 
			this.identifier=identifier; 
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getPrevActivity() {
		return prevActivity;
	}

	public void setPrevActivity(String prevActivity) {
		this.prevActivity = prevActivity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
	public String toString() {
		return "("+activity+", "+prevActivity+") Duration: "+duration+" ID: "+identifier;
	}

}
