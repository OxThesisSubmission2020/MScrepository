package selectiveReveal;

public class main {

	public static void main(String[] args) {
		
		System.out.println("Generating an Event Log");
		SampleEventlog sample = new SampleEventlog();
		System.out.println(sample.toString());

		System.out.println("Generating the Selective Reveal Table for Medicines");
		System.out.println();

		
		System.out.println("Generating the unconnected Event Log");
		TupleLog sampleTupleLog = new TupleLog(sample.trace);
		System.out.println(sampleTupleLog.toString());
		
		System.out.println("Ask for reveal: What patients where treated with Medicine B?");
		String reveal = sampleTupleLog.revealMedicineType("Medicine B");
		System.out.println(reveal);
	}

}
