package project;

import java.util.Random;

/**
 * This Class deals with creating a new GPS Co-ordinate and changing the location in the machine file
 * @author Lauren 40300800 & Holly.J 40296314
 *
 */
public class GPS {
	
	/** This method randomly generates the GPS coordinates of the machine
	 * @return New GPS coordinate
	 */
	
	public static String gpsCoordinates() {
		
		//generating the latitude
		Random latitude = new Random();
		int lat = latitude.nextInt(90+90)-90; //(latmax-latmin)+latmin
		
		//generating the longitude 
		Random longitude = new Random();
		int lon = longitude.nextInt(180+180)-180; // (longmax-longmin)+longmin
		
		//generating the time
		Random time = new Random();
		int t1 = time.nextInt(60); //----> latitude minutes (max-min)+min min=0
		int t2 = time.nextInt(60); //----> latitude seconds (max-min)+min min=0
		int t3 = time.nextInt(60); //----> longitude minutes (max-min)+min min=0
		int t4 = time.nextInt(60); //----> longitude seconds (max-min)+min min=0
		
		String newCoordinates = lat + "°" + t1 + "'"  + t2 + "''."+ lon +"°"+ t3 +  "'" + t4 +"''";
		
		return newCoordinates;
	}
	
	/**
	 * This method when called, changes the location of the machine in the machine file
	 * @return - a boolean value indicating if the method was successful
	 */
	public static Boolean changeLocation() {
		String read = accessingMachineFile.readFile();
		String sections[] = read.split(",");
		sections[4] = gpsCoordinates();
		String write = sections[0] + "," + sections[1] + "," + sections[2] + "," + sections[3] + "," + sections[4];
		Boolean success = accessingMachineFile.writeFile(write);
		return success;
	}
}

