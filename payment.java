package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * This class is the parent class of all payment methods and holds the methods all the child classes need.
 * @author Holly.B 40324690
 *
 */
public class payment {

	/**
	 * This method is called to open the  file that stores all information on money
	 * It holds how many there is of each coin and the values in the cashboxes. 
	 * @return either the string which holds all the values or a string saying fail.
	 */
	public static String openFile() {
		File cashFile = new File("CashFileCSV.csv");
		try {
			Scanner mySc = new Scanner(cashFile);
			String coinCommas = mySc.nextLine();
			mySc.close();
			return coinCommas;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			String result = "Fail";
			return result;
		}
	}
	
	/**
	 * This takes the updated values for the file and replaces the current file
	 * @param newCoinLine - this is a string with all values and commas added
	 */
	public static void writeFile(String newCoinLine) {
		File cashFile = new File("CashFileCSV.csv");
		PrintWriter myPw;
		try {
			myPw = new PrintWriter(cashFile);
			myPw.flush();
			myPw.println(newCoinLine);
			myPw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This gets the value within the coin and notes boxes and returns the total value.
	 * @return total - which is the value of the two boxes added together.
	 */
	public int cashboxValue(){
		String coinCommas = openFile();
			String[] coinSep = coinCommas.split(",");
			int cashbox = Integer.parseInt(coinSep[5].trim());
			int NotesBox = Integer.parseInt(coinSep[6].trim());
			int total = cashbox + NotesBox;
			return total;
	}
	
	/**
	 * This resets the coin and note boxes to zero representing the owner emptying the machine
	 */
	public  void emptyAllCashBox() {
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int tpound = Integer.parseInt(coinSep[0].trim());
		int opound = Integer.parseInt(coinSep[1].trim());
		int fifty = Integer.parseInt(coinSep[2].trim());
		int twenty = Integer.parseInt(coinSep[3].trim());
		int ten = Integer.parseInt(coinSep[4].trim());
		int cashbox = Integer.parseInt(coinSep[5].trim());
		int NotesBox = Integer.parseInt(coinSep[6].trim());
		int cardcount = Integer.parseInt(coinSep[7].trim());
		cashbox=0;
		NotesBox=0;
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	/**
	 * This calculates and operates the refund of coins being removed 
	 * @param moneyInput - this is the total amount of money that is a customer adds
	 * @param costOfItem - this is the item cost which is listed in the items flatfile
	 */
	public void refund(int moneyInput, int costOfItem){
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int tpound = Integer.parseInt(coinSep[0].trim());
		int opound = Integer.parseInt(coinSep[1].trim());
		int fifty = Integer.parseInt(coinSep[2].trim());
		int twenty = Integer.parseInt(coinSep[3].trim());
		int ten = Integer.parseInt(coinSep[4].trim());
		int cashbox = Integer.parseInt(coinSep[5].trim());
		int NotesBox = Integer.parseInt(coinSep[6].trim());
		int cardcount = Integer.parseInt(coinSep[7].trim());
		
		int refundNeeded = moneyInput-costOfItem;
		System.out.println("Refund Required: "+refundNeeded);
		while(refundNeeded >0) {
			if (refundNeeded >= 200 && tpound>0) {
				refundNeeded=refundNeeded-200;
				tpound=tpound-1;
			}
			else if (refundNeeded>=100 && opound>0) {
				refundNeeded=refundNeeded-100;
				opound=opound-1;
			}
			else if (refundNeeded >= 50 && fifty>0) {
				refundNeeded=refundNeeded-50;
				fifty=fifty-1;
			}
			else if (refundNeeded >= 20 && twenty>0) {
				refundNeeded=refundNeeded-20;
				twenty=twenty-1;
			}
			else if (refundNeeded >= 10 && ten>0) {
				refundNeeded=refundNeeded-10;
				ten=ten-1;
			}
			
		}
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	
	/**
	 * This completely resets the machine to have nothing in the machine. 
	 */
	public void reset() {
		int tpound = 0;
		int opound = 0;
		int fifty = 0;
		int twenty = 0;
		int ten = 0;
		int cashbox = 0;
		int NotesBox = 0;
		int cardcount = 0;
		
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
}
