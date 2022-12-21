import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Customer;

class CustomerTest {

	@Test
	void addCash() {
		//Vending machine should accept 10 and 20 notes 
		System.out.println("Entering 10 note: "+Customer.AddCash(10));
		
		//Extreme case -  should return 0 as 0 was allowed into the machine
		System.out.println("Entering 30 note: "+Customer.AddCash(30));
		
		//Extreme case	-  should return 0 as 0 was allowed into the machine
		System.out.println("Entering -5 note: "+Customer.AddCash(-5));
	}

	@Test
	void useCard()
	{
		System.out.println(Customer.useCard(10)); //Multiply by currency rate and output
		System.out.println(Customer.useCard(5.99)); //Multiply by currency rate and output
		System.out.println(Customer.useCard(0)); //Extreme case - shouldn't make payment
		System.out.println(Customer.useCard(-5));//Extreme case - shouldn't make payment
	}

}
