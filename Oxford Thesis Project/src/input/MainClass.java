package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.out.XSerializer;
import org.deckfour.xes.out.XesXmlSerializer;

class MainClass {
/*
 * Citing the dataset Sepsis Cases= Mannhardt, F (Felix) (2016) Sepsis Cases - Event Log. Eindhoven University of Technology. 
 * Dataset. https://doi.org/10.4121/uuid:915d2bfb-7e84-49ad-a286-dc35f063a460
 * */
	
	public static void main(String[] args) throws Exception {
		XLog log = readSingleLog("C:\\Users\\bodob\\Documents\\eclipse-workspace\\NewPackageIvy\\src\\Input\\Sepsis Cases - Event Log.xes");
		System.out.println("Sucessfully loaded");
		Read readObject = new Read();
		String [][] output = readObject.initiateMatrix(log);
		readObject.print(output);
	}
	
		public static XLog readSingleLog(String fileName) throws Exception {
			File initialFile = new File(fileName);
			InputStream inputStream = new FileInputStream(initialFile);
			XesXmlParser parser = new XesXmlParser();
			List<XLog> parsedLogs = parser.parse(inputStream);
			
			if (parsedLogs.size() > 0) {
				System.out.println(parser.description());
				return parsedLogs.get(0);
			}
			return null;
		}

		
		public static void exportSingleLog(XLog log, String targetName) throws IOException {
			FileOutputStream out = new FileOutputStream(targetName);
			XSerializer logSerializer = new XesXmlSerializer();
			logSerializer.serialize(log, out);
			out.close();
		}

}