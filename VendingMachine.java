package project;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class is our control class that interacts with all other classes
 * 
 * @author Team 16 - Out of Order
 *
 */

public class VendingMachine {
	static Scanner mySc = new Scanner(System.in);
	static payment payment = new payment();
	static coins coins = new coins();
	static Notes Notes = new Notes();
	static Cards cards = new Cards();
	static Customer customer = new Customer();
	private static DecimalFormat df = new DecimalFormat("0.00");
	// Owner owner = new Owner();

	/**
	 * This method is the main control system that branches of to the other classes
	 * for them to carry out processes.
	 */
	public static void vendingMachine() {
		boolean x = false;
		boolean owner = false;

		while (x != true) {
			MessageNew.displayProducts();
			String itemcode = mySc.nextLine();

			if (itemcode.equalsIgnoreCase("k")) {
				MessageNew.displayOwnerModeSelected();
//				owner = true;
				String ownerPin = mySc.nextLine();

				if (Owner.checkPIN(ownerPin)) {
					owner = true;
				} else {
					owner = false;
				}

				while (owner == true) {
					MessageNew.displayOwnerOptionchoices();
					String ownerCode = mySc.nextLine();
					System.out.println("You selected the code " + ownerCode);
					if (ownerCode.equalsIgnoreCase("p")) { // Change stock prices
						MessageNew.displayEnterItemCode();
						String itemCodeP = mySc.nextLine();
						MessageNew.displayEnterItemPricePounds();
						int pounds = mySc.nextInt();
						MessageNew.displayEnterItemPricePence();
						int pence = mySc.nextInt();
						int cost = (pounds * 100) + (pence * 10);
						// System.out.println(pounds+" " +pence+" " + cost);
						Owner.changeItemPrice(itemCodeP, cost);
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}

					}
					if (ownerCode.equalsIgnoreCase("n")) { // change item name
						MessageNew.displayChangeItemName();
						String itemRow = mySc.nextLine();
						MessageNew.displayItemChangeNewName();
						String itemNewName = mySc.nextLine();
						Owner.changeItemName(itemRow, itemNewName);
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}
					}
					if (ownerCode.equalsIgnoreCase("c")) {// change currency
						MessageNew.displayCurrencyChangeMessage();
						String newCurrency = mySc.nextLine();
						MessageNew.displayExchangeRateMessage();
						String newExchangeRate = mySc.nextLine();
						Owner.changeCurrencyRate(newCurrency, newExchangeRate);
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}

					}
					if (ownerCode.equalsIgnoreCase("l")) {// change login PIN
						MessageNew.displayPinChangeMessage();
						String newOwnerPin = mySc.nextLine();
						Owner.changePIN(newOwnerPin);
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}

					}
					if (ownerCode.equalsIgnoreCase("r")) { // re-stocking coin tubes
						MessageNew.displayCoinTypeOption();
						int coin = mySc.nextInt();
						int coinVal = Customer.AddCoins(coin);
						if (coinVal > 0) {
							MessageNew.displayAmountOption();
							int numOfCoins = mySc.nextInt();
							Owner.restockCoinSlots(coin, numOfCoins);
						} else {
							System.out.println("Invalid coin");
						}
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}

					}
					if (ownerCode.equalsIgnoreCase("w")) {
						MessageNew.displayChangeLocation();
						GPS.changeLocation();
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}

					}
					if (ownerCode.equalsIgnoreCase("s")) { // re-stocking stock levels
						MessageNew.displayEnterItemCode();
						String itemCodeS = mySc.nextLine();
						MessageNew.displayAmountOption();
						int numOfItems = mySc.nextInt();
						int rejected = Owner.restockItemSlots(itemCodeS, numOfItems);
						if (rejected == -1) {
							System.out.println("The Restock did not work");
						} else {
							System.out.println("The number of rejected items is " + rejected);
						}
						mySc.nextLine();
						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						}
					}
					if (ownerCode.equalsIgnoreCase("q")) { // Empty Cashbox
						MessageNew.displayCashBoxMenu();
						int optionChoice = mySc.nextInt();
						if (optionChoice == 1) {
							int value = coins.cashboxValue();
							value = value / 100;
							System.out.print("The Cashbox had £" + df.format(value));
							coins.emptyCoinCashBox();
							System.out.println("The Coin Box is now empty");
						} else if (optionChoice == 2) {
							int value = Notes.cashboxValue();
							value = value / 100;
							System.out.print("The Cashbox had £" + df.format(value));
							Notes.emptyNotesCashBox();
							System.out.println("The Notes Box is now empty");
						} else if (optionChoice == 3) {
							int value = payment.cashboxValue();
							value = value / 100;
							System.out.print("The Cashbox had £" + df.format(value));
							payment.emptyAllCashBox();
							System.out.println("The Cashbox is now empty");
						} else {
							System.out.println("Invalid Option Choice");
						}

						MessageNew.displayOwnerLeaveMessage();
						String response = mySc.nextLine();
						if (response.equalsIgnoreCase("y")) {
							owner = false;
						} else if (itemcode.equalsIgnoreCase("x")) {
							System.exit(0);
							;
						}
					}
				}
			}

			else if (itemcode.equalsIgnoreCase("x")) {
				System.exit(0);
			} else {
				int price = customer.ItemCode(itemcode);
				int itemPrice = price;
				int coinAdded = 0;
				String paymentChoice = "";

				if (price == -2) {
					System.out.println(customer.DispenseItem("0"));
				} else if (price == -1) {
					System.out.println("Incorrect input");
				} else if (price == 0) {
					System.out.println("Price is 0");
				} else {
					if (customer.CanGetChange()) {
						MessageNew.displayPaymentChoice();
						int optionChoice = mySc.nextInt();
						if (optionChoice == 1) {
							paymentChoice = "card";
							String Message = Customer.useCard(itemPrice);
							System.out.println(Message);
							Cards.paybyCard(itemPrice);
						} else if (optionChoice == 2) {
							paymentChoice = "cash";
							while (price > 0) {

								MessageNew.displayCashPaymentChoice();
								int cashChoice = mySc.nextInt();
								if (cashChoice == 1) {
									double p = (double) price / (double) 100;
									System.out.println("£" + df.format(p));
									MessageNew.displayEnterCoinMessage();
									int C = mySc.nextInt();
									int Coin = Customer.AddCoins(C);
									if (Coin == 0) {
										MessageNew.displayIncorrectCoin();
									} else {
										coinAdded = coinAdded + Coin;
										coins.addCoins(Coin);
										price = price - Coin; // deduct from price
									}
								} else if (optionChoice == 2) {
									double p = (double) price / (double) 100;
									System.out.println("£" + df.format(p));
									MessageNew.displayEnterNotesMessage();
									int C = mySc.nextInt();
									int Note = Customer.AddCash(C);
									if (Note == 0) {
										MessageNew.displayIncorrectCoin();
									} else {
										coinAdded = coinAdded + Note;
										Notes.addNotes(Note);
										price = price - Note; // deduct from price
									}
								}
							}

							if (price < 0) {
								System.out.println(customer.DispenseItem(itemcode));
								payment.refund(coinAdded, itemPrice);
							} else if (price == 0) {
								System.out.println(customer.DispenseItem(itemcode));

							}
						}

						Tracking.addPurchases10(itemcode, paymentChoice);
						Tracking.storeItem(itemcode, paymentChoice);
						Tracking.stockLevel();
					} else {
						System.out.println("Cant give change, X to continue?");
						// if x repeat above
					}

				}
				mySc.nextLine();
			}

		}
	}
}
