package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This Class deals with accessing the Tracking10 File, through a reading method and a writing method
 * @author Lauren 40300800
 *
 */
public class accessingTracking10File {

	public static void createFile() {
		try {
			File file = new File("tracking10.csv");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] readFile() {
		String[] rows = new String[10];
		int e = 0;

		try {
			File myFile = new File("tracking10.csv");
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

	public static boolean writeFile(String[] rows) {
		boolean result;

		try {
			File file = new File("tracking10.csv");
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

