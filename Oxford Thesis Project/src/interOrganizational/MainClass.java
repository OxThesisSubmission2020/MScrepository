package interOrganizational;

import org.deckfour.xes.model.XLog;
import input.Read;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.deckfour.xes.in.XesXmlParser;
import input.Read;

public class MainClass {

	public static void main(String[] args) throws Exception {
		XLog log = readSingleLog("C:\\Users\\bodob\\Documents\\eclipse-workspace\\NewPackageIvy\\src\\Input\\Sepsis Cases - Event Log.xes");
		System.out.println("Sucessfully loaded");
		Read readObject = new Read();
		String [][] output = readObject.initiateMatrix(log);
		Properties properties = new Properties(output);
		DFGMatrix matrix = new DFGMatrix(properties.groupNames, properties.groups);
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

}
