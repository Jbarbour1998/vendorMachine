package project;

/**
 * This class inherits all methods from payment and has a few of its own
 * @author Holly.B 40324690
 *
 */
public class Cards extends payment{
	private int card = 5000;
	/**
	 * This takes in the price of the item being brought and adds it to the total in the file simulating the payment
	 * @param itemPrice - this is the cost of the item they have selected
	 */
	public static void paybyCard(int itemPrice) {
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
		
		cardcount+= itemPrice;
		
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	/**
	 * This resets the card count to 0 
	 */
	public void reset() {
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int tpound = Integer.parseInt(coinSep[0].trim());
		int opound = Integer.parseInt(coinSep[1].trim());
		int fifty = Integer.parseInt(coinSep[2].trim());
		int twenty = Integer.parseInt(coinSep[3].trim());
		int ten = Integer.parseInt(coinSep[4].trim());
		int cashbox = Integer.parseInt(coinSep[5].trim());
		int NotesBox = Integer.parseInt(coinSep[6].trim());
		int cardcount = 0;
		
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	public void deductFromCard(int itemPrice) {
		card = card - itemPrice;
	}
	
}
