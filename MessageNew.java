package project;

//import java.util.Scanner;



/**
 * This class contains the outputs of the vending machine
 * @author Holly.B 40324690 & Jordan 40303629
 *
 */
public class MessageNew {

	// Setup the data/state
	private double itemCost;
	private String itemName;
	//private String message;
	//private double tube;// this will obviously be a link to coin collector
	//private int coin;

	// method which warns the user that there is not change available in the vending
	// machine;
	// constructor for instantiation
	public void Message(String itemName, double cost, double tube) {
		this.itemCost = cost;
		this.itemName = itemName;
		//this.tube = tube;
	}

	public void Message(String itemName, double cost, double tube, int coin) {
		this.itemCost = cost;
		this.itemName = itemName;
		//this.tube = tube;
		//this.coin = coin;
	}
	//public String message(String message) {
		//return this.message = message;
	//}

	// Skeleton
/*	public boolean getWarning() {
		String str = "";
		if (tube == 0) {
			str += "There is no change avaible in the machine";
		} else {
			str += "continue with your purchase";
		}
		return true;

	}*/

	// Method which tells the user how much their item costs
	public double getItemCost() {
		return this.itemCost;
	}

	public String getName() {
		return this.itemName;
	}

/*	public String getDetails() {
		displayProducts();
		String msg = "Item: " + this.itemName + "\n";
		msg += "Cost: £" + this.itemCost;
		displayChangeMessage();
		return msg;
	}*/

/*	public  double displayChangeMessage() {
		Scanner input = new Scanner(System.in);
		// double total will reference the product item
		double total = itemCost;
		double coin = 0;
		int count = 1;
		String str = null;
		
		str += this.getWarning();
		while (count <= 10) {

			coin = input.nextDouble();
			count++;
			total = total - coin;
			
			

			str += "Remaining: £" + total;

			if (total <= 0.0) {
				break;
			}
			str +="                                              ";
			str +="Your change is : cents splitted as follows: ";
			str +="    200 cents coins: ";
			str +="    100 cents coins: ";
			str +="    50 cents coins: ";
			str +="    20 cents coins: ";
			str +="    10 cents coins: ";
			
			System.out.println(
				"Your change is : + "cents splitted as follows: ");


		}
		return coin;

	}*/

	// Method which tells the user how much money they still need to insert into the
	// machine


	public static void displayProducts() {
		
		System.out.println(" ********************");
		System.out.println("       WELCOME       ");
		System.out.println(" ********************");
		System.out.println("   Input Item Code: ");

	}

	public static void displayEnterCoinMessage() {
		System.out.println(" Please enter coins ");
	}
	public static void displayEnterNotesMessage() {
		System.out.println(" Please enter Notes ");
	}
	public static void displayEnterItemCode() {
		System.out.println("Enter item code");
	}
	public static void displayEnterItemPricePounds() {
		System.out.println("Enter item price");
		System.out.println("Enter whole pounds");
	}
	public static void displayEnterItemPricePence() {
		System.out.println("Enter whole 10 p's");
	}
	public static void displayOwnerLeaveMessage() {
		System.out.println("Have you finished");
	}
	public static void displayCashBoxMenu() {
		System.out.println("Select what you would like to Empty:");
		System.out.println("1. Empty Coin Box Only");
		System.out.println("2. Empty Notes Box Only");
		System.out.println("3. Empty Both Coin and Notes Box Only");
	}
	public static void  displayCoinTypeOption() {
		System.out.println("Enter coin type");
	}
	public static void displayAmountOption() {
		System.out.println("Enter Amount");
	}
	public static void displayOwnerModeSelected() {
		System.out.println("Owner mode Selected");
		System.out.println("Enter the Owners pin to continue:");
	}
	public static void displayOwnerOptionchoices() {
		System.out.println("Owner mode successfully entered");
		System.out.println("Select desired process:");
		System.out.println("Change Price = P");
		System.out.println("Change Currency = C");
		System.out.println("Change Item Name = N");
		System.out.println("Change Owner PIN = L");
		System.out.println("Restock Coin Slots = R");
		System.out.println("Restock Item Rows = S");
		System.out.println("Empty Money = Q");
		System.out.println("Change Location = W");
	}
	public static void displayChangeItemName() {
		System.out.println("Change Item Name");
		System.out.println("Enter Item Row:");
	}
	public static void displayChangeLocation() {
		System.out.println("Change Location");
	}
	public static void displayItemChangeNewName() {
		System.out.println("Enter New Name:");
	}
	public static void displayPinChangeMessage() {
		System.out.println("Enter New Pin: ");
	}
	public static void displayCurrencyChangeMessage() {
		System.out.println("Enter Name of Currency: ");
	}
	public static void displayExchangeRateMessage() {
		System.out.println("Enter Exchange Rate: ");
	}
	public  static void displayPaymentChoice() {
		System.out.println("Select Payment Method:");
		System.out.println("1. Card");
		System.out.println("2. Cash");
	}
	public  static void displayCashPaymentChoice() {
		System.out.println("Select Cash Payment Method:");
		System.out.println("1. Coins");
		System.out.println("2. Notes");
	}
	public static void displayIncorrectCoin() {
		System.out.println("The coin is invalid");
	}
	//Add a method a method which displays a message saying that there is low stock in particular row 
	public String rowLowStock () {
		
		String str = "This row is out of Stock";
		return str;
	}
	//Add a method which displays change given, in the appropriate currency 
	public String UKCurrency () {
		String str = null; 
		str+= "£";
		return str;
	}
	public String EuCurrency () {
		String str = null; 
		str+= "€";
		return str;
	}
	//Convert any current text displays to method in the message class.
	 

}

