package input;

import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

//Feeding in the log 
@Plugin(name = "Securing Eventlogs", parameterLabels = { "Event Log"}, returnLabels = {
                               "Output log" }, returnTypes = {XLog.class })


public class Read {
 

                @UITopiaVariant(affiliation = "University of Oxford", author = "Leopold von Waldthausen", email = "leopold.vonwaldthausen@magd.ox.ac.uk")
                @PluginVariant(requiredParameterLabels = {0})
                                
                public static String[][] initiateMatrix(XLog log) {
                	XLogInfo info  = XLogInfoFactory.createLogInfo(log); 
                    int eventNum = info.getNumberOfEvents();
                              String[][] outputLog = new String[eventNum][8];
                              int counter = 0;
                              int caseID = 0;
                              for (XTrace trace : log) { 
                                         /// For each trace create an array
                            	   		 caseID++;
                            	   		 String DisfuncOrg = "false"; 
                                               for (XEvent event : trace) {
                                                   //for all events add to array..
                                           	   		outputLog[counter][0]= caseID+"";
                                           	   		//looking for typical time format
                                           	   		if(event.getAttributes().get("time:timestamp")!=null) {
                                            	   	outputLog[counter][1]= event.getAttributes().get("time:timestamp").toString();}
                                           	   		//looking for the benchmarking time format
                                           	   		if (event.getAttributes().get("E:time:time")!=null) {outputLog[counter][1]= event.getAttributes().get("E:time:time").toString();}
                                            	   	//activity names
                                            	   	outputLog[counter][2]= event.getAttributes().get("concept:name").toString();
                                            	   	//sepsis log group name
                                           	   		if (event.getAttributes().get("org:group")!=null) {
                                            	   		outputLog[counter][3]= event.getAttributes().get("org:group").toString();
                                            	   	}
                                           	   		if (event.getAttributes().get("org:group")==null) {
                                           	   			outputLog[counter][3]= "";
                                           	   		}
                                           	   		//disfunctional organ criteria
                                           	   		if (event.getAttributes().get("concept:name").toString().equals("ER Registration")) {
                                           	   			DisfuncOrg = event.getAttributes().get("DisfuncOrg").toString();
                                           	   		}
                                           	   			outputLog[counter][4]= DisfuncOrg; 
                                           	   		//increment counter for next matrix row
                                           	   		counter++;
                                               }
                               }
                               return outputLog;
                }
                
                public static void print(String[][] log) {
                	for(int i=0; i<log.length;i++) {
                		for(int j=0; j<5;j++) {
                			if(j==2) {}
                			else {System.out.print(log[i][j]+'\t');}
                		}
                		System.out.print(log[i][2]+'\t');
                		System.out.println();
                	}
                }

                /*This method was replaced by the new initiateMatrix Method above, however, it remains for further reference*/
                public static String[][] run(XLog log) {
                	XLogInfo info  = XLogInfoFactory.createLogInfo(log); 
                    int eventNum = info.getNumberOfEvents();
                              String[][] outputLog = new String[eventNum][8];
                              int counter = 0;
                              int caseID = 0;
                              for (XTrace trace : log) { 
                                         /// For each trace create an array
                            	   		 caseID++;
                                               for (XEvent event : trace) {
                                                   //for all events add to array..
                                           	   		outputLog[counter][0]= caseID+"";
                                           	   		//looking for typical time format
                                           	   		if(event.getAttributes().get("time:timestamp")!=null) {
                                            	   	outputLog[counter][1]= event.getAttributes().get("time:timestamp").toString();}
                                           	   		//looking for the benchmarking time format
                                           	   		if (event.getAttributes().get("E:time:time")!=null) {outputLog[counter][1]= event.getAttributes().get("E:time:time").toString();}
                                            	   	//sepsis log group name
                                           	   		if (event.getAttributes().get("org:group")!=null) {
                                            	   		outputLog[counter][2]= event.getAttributes().get("org:group").toString();
                                            	   	}
                                           	   		if (event.getAttributes().get("org:group")==null) {
                                           	   			outputLog[counter][2]= "";
                                           	   		}
                                           	   		//sepsis log measurements
                                            	   	if(event.getAttributes().get("Leucocytes")!=null) {outputLog[counter][3]= event.getAttributes().get("Leucocytes").toString();}
                                            	   	else if (event.getAttributes().get("CRP")!=null) {outputLog[counter][3]= event.getAttributes().get("CRP").toString();}
                                            	   	else outputLog[counter][3]="---";
                                            	   	outputLog[counter][4]= event.getAttributes().get("concept:name").toString();
                                            	   	counter++;
                                            	   	System.out.println(event.getAttributes());
                                            	   	System.out.println(counter);
                                               }
                               }
                               return outputLog;
                }
            
}