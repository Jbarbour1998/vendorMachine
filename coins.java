package project;

/**
 * This class inherits all methods from payment and has a few of its own
 * @author Holly.B 40324690
 *
 */
public class coins extends payment {
	
	/**
	 * This takes in the coin type and if there is space adds  it to the refund tubes if 
	 *  they are full it is added to the cash box
	 * @param coin
	 */
	public void addCoins(int coin) {
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
		if(coin ==200) {
			if (tpound<50) {
				tpound+=1;
			}
			else {
				cashbox+=200;
			}
			
		}
		else if(coin ==100 ) {
			if (opound<50) {
				opound+=1;
			}
			else {
				cashbox+=100;
			}
			
		}
		else if(coin ==50 ) {
			if (fifty<50) {
				fifty+=1;
			}
			else {
				cashbox+=50;
			}
			
		}
		else if(coin ==20) {
			if (twenty<50) {
				twenty+=1;
			}
			else {
				cashbox+=20;
			}
			
		}
		else if(coin ==10 ) {
			if (ten<50) {
				ten+=1;
			}
			else {
				cashbox+=10;
			}
			
		}
		else {
			System.out.println("Coin not valid");
			
		}
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
		
	}
	
	/**
	 * This  resets the coin box to 0 to simulate the emptying of the coins
	 */
	public  void emptyCoinCashBox() {
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
		
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	/**
	 * This gets the value within the coin box and returns the value.
	 * @return cash box which  is the value of the total coins it is holding
	 */
	public int cashboxValue(){
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int cashbox = Integer.parseInt(coinSep[5].trim());
		return cashbox;
	}
	
}
