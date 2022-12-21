package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This Class deals with tracking the all purchases of the machine and placing the purchase info into the
 * appropriate file.
 * @author Lauren 40300800 & Sinead 40332821
 */
public class Tracking {
	public static String[][] items = new String[3][25];	

	public static void storeItem(String code, String payment)
	{

	    String[] item = new String[3];
	    if(code.length() == 2)
	    {
	    char ItemCodeLetter = code.toLowerCase().charAt(0);
	    int ItemCodeNumber = Character.getNumericValue(code.charAt(1));

	    if(ItemCodeLetter >= 'a'&& ItemCodeLetter <= 'e' &&
	            ItemCodeNumber >= 1 && ItemCodeNumber <= 5)
	    {

	        int FileCode = (((Character.getNumericValue(ItemCodeLetter)-10)*5) + ItemCodeNumber);

	        //getting items availability and prices
	        Customer.GetItems();

	        if(items[0][FileCode-1] != "" &&
	                items[1][FileCode-1] != "" &&
	                        items[2][FileCode-1] != "" 
	                )
	        {
	            item [0] = items [0][FileCode-1]; //price 
	            item [2] = items [2][FileCode-1]; //name

	            String[] machine = (accessingMachineFile.readFile()).split(",");
	            String GPS = machine[4];
	            String VendID = machine[3];

	            //store item 
	             String content = item[0] + "," +  item[2] + "," + java.time.LocalDateTime.now() + "," + payment + ","+ GPS + "," + VendID;
	             try(FileWriter fw = new FileWriter("PurchasedItems.txt", true);
	                        BufferedWriter bw = new BufferedWriter(fw);
	                        PrintWriter out = new PrintWriter(bw))
	                    {
	                        out.println(content);

	                    } catch (IOException e) {

	                    }


	        }
	    }
	    }




	    }

	/**
	 * This method takes the information of the most recent purchase and adds it to
	 * the file containing the ten most recent purchases. It will remove the least
	 * reason purchase and add the most recent.
	 * 
	 * @param itemCode - The itemCode of the new purchase being added
	 * @param Payment  - The payment type that is being used in the purchase
	 */
	public static void addPurchases10(String itemCode, String Payment) {

		String[] machineParts = (accessingMachineFile.readFile()).split(",");
		String GPS = machineParts[4];
		String VendID = machineParts[3];

		int itemRow = accessingItemFile.itemLineFinder(itemCode) - 1;
		String[] rows = accessingItemFile.readFile();
		String[] parts = (rows[itemRow]).split(",");
		String Price = parts[0];
		String Type = parts[2];

		String[] purchaseRows = new String[10];
		purchaseRows = accessingTracking10File.readFile();
		for (int i = 8; i >= 0 ; i--) {
			purchaseRows[i + 1] = purchaseRows[i];
		}
		purchaseRows[0] = Price + "," + Type + "," + java.time.LocalDateTime.now() + "," + Payment + "," + GPS + ","
				+ VendID;

		accessingTracking10File.writeFile(purchaseRows);
	}

	/**
	 * This method checks the stock levels of the items, if there is less than 3 in
	 * any of the items row, then the method returns true
	 * 
	 * @return - a boolean value indicating if there is low stock in the machine
	 */
	public static boolean stockLevel() {
		String[] items = accessingItemFile.readFile();
		for (int i = 0; i < 25; i++) {

			String[] parts = (items[i]).split(",");

			int stock = Integer.parseInt(parts[1]);
			if (stock < 3) {
				return true;
			}
		}
		return false;
	}
}

