package project;

/**
 * This Class deals with all the methods which will be accessed by the owner to make changes to the vending machine and the 
 * items within the vending machine
 * @author Lauren 40300800
 */
public class Owner {
	static coins coins = new coins();
	static payment payment = new payment();
	/**
	 * This method changes the item price of the chosen vending machine row
	 * 
	 * @param itemCode  - The chosen item row which's price is being updated
	 * @param itemPrice - The new chosen price for the chosen item row
	 * @return - a boolean value indicating if the change was successful
	 */
	public static boolean changeItemPrice(String itemCode, int itemPrice) {

		int itemRow = accessingItemFile.itemLineFinder(itemCode) - 1;
		String[] rows = accessingItemFile.readFile();
		boolean result = false;

		String item = rows[itemRow];
		String[] parts = item.split(",");
		String stock = parts[1].trim();
		String itemName = parts[2].trim();

		rows[itemRow] = Integer.toString(itemPrice) + "," + stock + "," + itemName;

		boolean writeResult = accessingItemFile.writeFile(rows);

		if (writeResult == true) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * This method changes the item name of the chosen vending machine row
	 * 
	 * @param itemCode - the item code of the row you want to change
	 * @param itemName - the new item name you wish to allocate to the specified row
	 * @return - a boolean value showing if changing the item row name was successful
	 */
	public static boolean changeItemName(String itemCode, String itemName) {
		
		int itemRow = accessingItemFile.itemLineFinder(itemCode) - 1;
		String[] rows = accessingItemFile.readFile();
		boolean result = false;

		String item = rows[itemRow];
		String[] parts = item.split(",");
		String itemPrice = parts[0].trim();
		String stock = parts[1].trim();

		rows[itemRow] = itemPrice + "," + stock + "," + itemName;

		boolean writeResult = accessingItemFile.writeFile(rows);

		if (writeResult == true) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * This method checks the current value of the Owner PIN and checks it against the entered PIN to
	 * see if they match.
	 * 
	 * @param enteredPIN - the PIN entered by the user
	 * @return - a boolean value showing if the entered PIN matches the Owner's Pin stored in the machine file
	 */
	public static boolean checkPIN(String enteredPIN) {
		enteredPIN = enteredPIN.trim();
		String machineLine = accessingMachineFile.readFile();
		String[] parts = machineLine.split(",");
		if (enteredPIN.equalsIgnoreCase(parts[0])) {
			return true;
		}
		return false;
	}

	/**
	 * This method changes the owner's PIN, which allows the user to access the
	 * extra machine options only available to owner
	 * 
	 * @param newPIN - the new PIN the user wants to change the owner access PIN to
	 * @return - a boolean value showing if the changing of PIN was successful or
	 *         not.
	 */
	public static boolean changePIN(String newPIN) {

		Boolean result = false;
		String fileLine = accessingMachineFile.readFile();
		String[] parts = fileLine.split(",");
		parts[0] = newPIN;
		result = accessingMachineFile.writeFile(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4]);

		return result;
	}

	/**
	 * This method changes the currency name and rate and updates the file accordingly, as well as reseting the money storages due
	 * to the new currency being incompatible with them
	 * 
	 * @param currencyName - the name/symbol of the new currency
	 * @param exchangeRate - the exchange rate of the new currency
	 * @return - a boolean value showing if the change was successful
	 */
	public static boolean changeCurrencyRate(String currencyName, String exchangeRate) {

		Boolean result = false;
		String fileLine = accessingMachineFile.readFile();
		String[] parts = fileLine.split(",");
		parts[1] = currencyName;
		parts[2] = exchangeRate;
		result = accessingMachineFile.writeFile(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4]);
		payment.reset();

		return result;

	}

	/**
	 * This method uses the coin re-stocking method from the Coin Collector class to
	 * re-stock the vending machines coin slot of choosing.
	 * 
	 * @param chosenCoinSlot - This is the coin slot that is being re-stocked
	 * @param addAmount      - This is the amount of the chosen coin that is being
	 *                       added to the chosen slot
	 */
	public static void restockCoinSlots(int chosenCoinSlot, int addAmount) {
		for (int i = addAmount; i > 0; i--) {
			coins.addCoins(chosenCoinSlot);
		}
	}

	/**
	 * This method will update the stock number of the chosen row to the chosen
	 * value
	 * 
	 * @param itemCode  - The item row which's stock is being updated
	 * @param addAmount - The amount of stock being added to the chosen item row
	 * @return - the amount of items that could not be added if the row became/was
	 *         full
	 */
	public static int restockItemSlots(String itemCode, int newAmount) {

		int itemRow = accessingItemFile.itemLineFinder(itemCode) - 1;
		String[] rows = accessingItemFile.readFile();
		int remainder = 0;
		int result = -1;

		String item = rows[itemRow];
		String[] parts = item.split(",");
		String price = parts[0].trim();
		int stock = Integer.parseInt(parts[1].trim());
		String itemName = parts[2].trim();
		
		if (stock == 25 || newAmount == 0) {
			remainder = newAmount;
		} else if (stock + (newAmount) <= 25) {
			remainder = 0;
			rows[itemRow] = price + "," + (Integer.toString(stock + newAmount)) + "," + itemName;
		} else if (stock + newAmount > 25) {
			remainder = stock + newAmount - 25;
			rows[itemRow] = price + "," + Integer.toString(25) + "," + itemName;
		}

		boolean writeResult = accessingItemFile.writeFile(rows);

		if (writeResult == true) {
			result = remainder;
		} else {
			result = -1;
		}
		return result;
	}

}
