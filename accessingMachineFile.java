package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This Class deals with accessing the Machine File, through a reading method and a writing method
 * @author Lauren 40300800
 *
 */
public class accessingMachineFile {

	/**
	 * This method creates a sample machine file in case that the original file would 
	 * be deleted. It creates the new machine file and adds sample data to the file.
	 */
	public static void createFile() {
		try {
			File file = new File("machine.csv");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("000000,GBP,1.00");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will read the machine file and return the values within the file
	 * in the format of a string.
	 * 
	 * @return - a string containing the information stored in the file, all 
	 * separated by commas
	 */
	public static String readFile() {
		String fileInfo = null;
		
		try {
			File myFile = new File("machine.csv");
			Scanner mySc = new Scanner(myFile);
			
			fileInfo = mySc.nextLine();
			
			mySc.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return fileInfo;
	}

	/**
	 * This method takes the string parameter and replaces the existing code
	 * within the machine file by writing to the file.
	 * 
	 * @param line - the new file values, separated by a comma, that will replace
	 * the existing information in the file
	 * @return - a boolean value showing if the method was successful in writing
	 * to the file.
	 */
	public static boolean writeFile(String line) {
		boolean result;

		try {
			File file = new File("machine.csv");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(line);
			result = true;
			bw.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}

		return result;
	}

}
