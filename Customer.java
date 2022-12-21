package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Customer {

	public static String[][] items = new String[3][25];
	private static String currencyName;
	private static double currencyRate;

	/*
	 * Allows the customer to enter coins and validates the entry to see if it is
	 * one of the coins the vending machine accepts
	 */
	public static int AddCoins(int coin) {
		if (coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200) {
			return coin;
		} else {
			// coin invalid
			return 0;
		}

	}

	/*
	 * ItemCode takes the code entered e.g A2 and finds the selected product
	 * allowing the customer to start entering coins
	 */
	public int ItemCode(String code) {

		int price = 0;
		if (code.length() == 2) {
			char ItemCodeLetter = code.toLowerCase().charAt(0);
			int ItemCodeNumber = Character.getNumericValue(code.charAt(1));

			if (ItemCodeLetter >= 'a' && ItemCodeLetter <= 'e' && ItemCodeNumber >= 1 && ItemCodeNumber <= 5) {

				int FileCode = (((Character.getNumericValue(ItemCodeLetter) - 10) * 5) + ItemCodeNumber);

				// getting items availability and prices
				GetItems();

				if (Integer.parseInt(items[1][FileCode - 1]) > 0) {
					price = Integer.parseInt(items[0][FileCode - 1]);

					return price;

				} else {
					return -2;
				}
			}

		}
		return -1;
	}

	/*
	 * Sends a message "dispensing" the chosen item when the price has been paid
	 */
	public String DispenseItem(String Code) {
		String message = "";
		if (Code == "0") {
			message = "Item out of stock";
		} else {
			message = "Item " + Code + " has been dispensed";
		}

		return message;
	}

	/*
	 * Checks if there is change available in the coin tubes and if there isnt a
	 * message will be displayed telling the customer they wont recieve change when
	 * they make a purchase
	 */
	public Boolean CanGetChange() {
		boolean CanGiveCoins = true;
		try {

			String path = "CashFileCSV.csv";
			String line = "";
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			line = bReader.readLine();

			while (line != null) {

				String[] array = line.split(",");
				for (int i = 0; i <= 4; i++) {
					if (Integer.parseInt(array[i]) == 0) {
						CanGiveCoins = false;
					}
				}

				line = bReader.readLine();

			}

			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CanGiveCoins;

	}

	public static void GetItems() {
		try {

//		String path ="Items.csv";	
//		String line = "";
//		BufferedReader bReader = new BufferedReader(new InputStreamReader(
//			      new FileInputStream(path), "UTF-8"));
//		line = bReader.readLine();
			String[] arrayItems = accessingItemFile.readFile();
			// System.out.println(line);

			for (int x = 0; x <= 24; x++) {
				String line = arrayItems[x];
				String[] array = line.split(",");

				if (Arrays.asList(array).size() != 0) {
					// System.out.println(x);
					// System.out.println(array[0]);
					items[0][x] = array[0];
					items[1][x] = array[1];
					items[2][x] = array[2];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// S P R I N T T W O

	/*
	 * Allows the customer to enter cash and validates the entry to see if it is one
	 * of the notes the vending machine accepts
	 */
	public static int AddCash(int note) {
		if (note == 500 || note == 1000 || note == 2000) {
			return note;
		} else {
			// coin invalid
			return 0;
		}

	}

//Tracking method
	public static void storeItem(String code) {

		String[] item = new String[3];
		if (code.length() == 2) {
			char ItemCodeLetter = code.toLowerCase().charAt(0);
			int ItemCodeNumber = Character.getNumericValue(code.charAt(1));

			if (ItemCodeLetter >= 'a' && ItemCodeLetter <= 'e' && ItemCodeNumber >= 1 && ItemCodeNumber <= 5) {

				int FileCode = (((Character.getNumericValue(ItemCodeLetter) - 10) * 5) + ItemCodeNumber);

				// getting items availability and prices
				GetItems();

				if (items[0][FileCode - 1] != "" && items[1][FileCode - 1] != "" && items[2][FileCode - 1] != "") {
					item[0] = items[0][FileCode - 1];
					item[1] = items[1][FileCode - 1];
					item[2] = items[2][FileCode - 1];
				}
			}
		}
		// store item
		String content = item[0] + "," + item[1] + "," + item[2] + "," + java.time.LocalDateTime.now() + "," + code;
		// File file = new File("PurchasedItems.txt");

		try (FileWriter fw = new FileWriter("PurchasedItems.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(content);

		} catch (IOException e) {

		}

	}

	public static String useCard(double itemPrice) {

		try {

			String path = "machine.csv";
			String line = "";
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			line = bReader.readLine();
			line = line.replace("\uFEFF", "");

			String[] array = line.split(",");

			currencyName = array[1];
			currencyRate = Double.parseDouble(array[2]);

			line = bReader.readLine();

			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double finalPrice = Math.round((itemPrice * currencyRate) * 100.0) / 100.0 / 100.0;

		return "Card payment has been made of " + finalPrice + " " + currencyName;

	}

}
