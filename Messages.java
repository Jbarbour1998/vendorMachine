package project;
import java.util.Scanner;

/*Message Display Class
	 * Author : Team 16- Out Of Order
	 * 
	 * This class will be the main display class for a vending machine. It defines various methods,
	 * in order for the vending machine to output the right message to the user. 
	 * 
	 * */
public class Messages {

	// Setup the data/state
	private double itemCost;
	private String itemName;
	private double tube;// this will obviously be a link to coin collector
	private int coin;

	// method which warns the user that there is not change available in the vending
	// machine;
	// constructor for instantiation
	public Messages(String itemName, double cost, double tube) {
		this.itemCost = cost;
		this.itemName = itemName;
		this.tube = tube;
	}

	public Messages(String itemName, double cost, double tube, int coin) {
		this.itemCost = cost;
		this.itemName = itemName;
		this.tube = tube;
		this.coin = coin;
	}

	// Skeleton
	public boolean getWarning() {

		if (tube == 0) {
			System.out.println("There is no change avaible in the machine");
		} else {
			System.out.println("continue with your purchase");
		}
		return true;

	}

	// Method which tells the user how much their item costs
	public double getItemCost() {
		return this.itemCost;
	}

	public String getName() {
		return this.itemName;
	}

	public String getDetails() {
		displayProducts();
		String msg = "Item: " + this.itemName + "\n";
		msg += "Cost: £" + this.itemCost;
		displayChangeMessage();
		return msg;
	}

	public  double displayChangeMessage() {
		Scanner input = new Scanner(System.in);
		// double total will reference the product item
		double total = itemCost;
		double coin = 0;
		int count = 1;

		System.out.println(getWarning());

		while (count <= 10) {

			coin = input.nextDouble();
			count++;
			total = total - coin;

			System.out.println("Remaining: £" + total);

			if (total <= 0.0) {
				break;
			}
			System.out.println("                                              ");
			System.out.println(
					"Your change is :"/* +Whatever reference works out the total+ */ + "cents splitted as follows: ");
			System.out.println("    200 cents coins: "/* + change.number 200 CentsCoins */);
			System.out.println("    100 cents coins: "/* + change.number 100 CentsCoins */);
			System.out.println("    50 cents coins: "/* + change.number 50 CentsCoins */);
			System.out.println("    20 cents coins: "/* + change.number 20 CentsCoins */);
			System.out.println("    10 cents coins: "/* + change.number 10 CentsCoins */);

		}
		return coin;

	}

	// Method which tells the user how much money they still need to insert into the
	// machine
	public void changeLeftOver() {

	}

	public static void displayProducts() {
		System.out.println(" ********************");
		System.out.println("       WELCOME       ");
		System.out.println(" ********************");
		System.out.println("   Input Item Code: ");

	}

	public static void displayEnterCoinMessage() {
		System.out.println(" Please enter coins ");

	}

}
