package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * This Class deals with accessing the Items File, through a reading method and a writing method
 * @author Lauren 40300800
 *
 */
public class accessingItemFile {

	/**
	 * This method finds the appropriate row within the item file based on the
	 * item code that is passed through as a parameter
	 * 
	 * @param itemCode - The ID used to identify each row within the vending machine.
	 * @return - The location of the item in the file based on its item code.
	 */
	public static int itemLineFinder(String itemCode) {
		// Number from item code
		int num1 = Integer.parseInt(itemCode.substring((itemCode.length() / 2)));
		// Will contain the number form of the letter
		int num2 = 0;
		String ltr = itemCode.substring(0, (itemCode.length() / 2));
		// Allocates a number
		if (ltr.equals("A")) {
			num2 = 0;
		} else if (ltr.equals("B")) {
			num2 = 5;
		} else if (ltr.equals("C")) {
			num2 = 10;
		} else if (ltr.equals("D")) {
			num2 = 15;
		} else if (ltr.equals("E")) {
			num2 = 20;
		}
		return (num2 + num1);
	}

	/**
	 * This method reads from the items file and returns the file's existing data as an
	 * array of strings
	 * 
	 * @return - an array of strings containing the rows within the items file
	 */
	public static String[] readFile() {
		String[] rows = new String[25];
		int e = 0;

		try {
			File myFile = new File("Items.csv");
			Scanner mySc = new Scanner(myFile);

			while (mySc.hasNextLine()) {
				rows[e] = mySc.nextLine();
				e++;
			}
			mySc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}

	/**
	 * This method takes in an array of strings of the new updated item file information, and
	 * uses this data to replace the original data within the item file.
	 * 
	 * @param rows - the updated items file data which will replace the old data
	 * @return - a boolean value showing if writing to the file was successful
	 */
	public static boolean writeFile(String[] rows) {
		boolean result;

		try {
			File file = new File("Items.csv");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < rows.length; i++) {
				bw.write(rows[i] + "\n");
			}
			result = true;
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}

		return result;
	}
}

