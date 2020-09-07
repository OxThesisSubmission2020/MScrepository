package selectiveReveal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SampleEventlog {
	public List<SimpleEvent> trace = new LinkedList<SimpleEvent>();
	
	public SampleEventlog() {
		generateEvents();
	}
	
	private void generateEvents() {
		
		List<String> activityNames = new LinkedList<String>(); 
		Collections.addAll(activityNames, "Register Patient","IV Antibiotics","See Medical Professional", "Release Patient");
		Collections.addAll(activityNames, "Register Patient","ER Registration","Sepsis Triage", "Release Patient");

		List<Integer> times = new LinkedList<Integer>(); 
		Collections.addAll(times,9,10,11,13);
		Collections.addAll(times,8,9,14,18);

		
		for(int i=0;i<4;i++) {
		Timestamp tempTime = new Timestamp(2020,12,12,times.get(i),45);
		SimpleEvent tempEvent= new SimpleEvent(tempTime,1,activityNames.get(i), "Medicine A");
		trace.add(tempEvent);
		}
		for(int i=0;i<4;i++) {
			Timestamp tempTime = new Timestamp(2020,12,12,times.get(i+4),45);
			SimpleEvent tempEvent= new SimpleEvent(tempTime,2,activityNames.get(i+4), "Medicine B");
			trace.add(tempEvent);
			}
	}
	
	public String toString() {
		String traceString = "";
		for(int i=0;i<trace.size();i++) {
			traceString = traceString +trace.get(i).toString()+" \r\n";
		}
		return traceString; 
	}
	
}
