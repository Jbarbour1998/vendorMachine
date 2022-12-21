import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Tracking;
import project.accessingItemFile;
import project.accessingTracking10File;


class TrackingTest {

	String[] Tracking10Rows = new String[10];
	String[] ItemsRows = new String[25];
	
	@BeforeEach
	void setUp() throws Exception {
		Tracking10Rows = accessingTracking10File.readFile();
		ItemsRows = accessingItemFile.readFile();
	}

	@AfterEach
	void tearDown() throws Exception {
		accessingTracking10File.writeFile(Tracking10Rows);
		accessingItemFile.writeFile(ItemsRows);
	}

	@Test
	void testAddPurchases10() {
		Tracking.addPurchases10("A5","Card");
		String[] actualRow = accessingTracking10File.readFile();
		String[] sections = actualRow[0].split(",");
		
		int itemRow = accessingItemFile.itemLineFinder("A5") - 1;
		String[] rows = accessingItemFile.readFile();
		String[] parts = (rows[itemRow]).split(",");
		String Price = parts[0];
		String Type = parts[2];
		
		String expectedRow = Price + "," +  Type + "," +  sections[2] + "," +  "Card" + "," +  sections[4] + "," +  sections[5];
		assertEquals(actualRow[0], expectedRow);
	}
	
	@Test
	void testPlusAddPurchases10() {
		Tracking.addPurchases10("A5","Card");
		Tracking.addPurchases10("A2","Cash");
		Tracking.addPurchases10("A4","Contactless");
		Tracking.addPurchases10("B1","Coin");
		Tracking.addPurchases10("B3","Cash");
		Tracking.addPurchases10("C2","Card");
		Tracking.addPurchases10("C4","Cash");
		Tracking.addPurchases10("D4","Cash");
		Tracking.addPurchases10("D2","Coin");
		Tracking.addPurchases10("E3","Coin");
		Tracking.addPurchases10("E5","Card");
		
		String[] actualRow = accessingTracking10File.readFile();
		for(int i = 0; i < 10; i++) {
			System.out.println(actualRow[i]);
		}
		String[] sections = actualRow[9].split(",");
		
		int itemRow = accessingItemFile.itemLineFinder("A2") - 1;
		String[] rows = accessingItemFile.readFile();
		
		String[] parts = (rows[itemRow]).split(",");
		String Price = parts[0];
		String Type = parts[2];
		
		String expectedRow = Price + "," +  Type + "," +  sections[2] + "," +  "Cash" + "," +  sections[4] + "," +  sections[5];
		assertEquals(actualRow[9], expectedRow);
	}

	@Test
	void testLowStockLevel() {
		String[] changingStocks = accessingItemFile.readFile();
		String[] parts = changingStocks[0].split(",");
		parts[1] = "2";
		changingStocks[0] = parts[0] + "," + parts[1] + "," + parts[2];
		accessingItemFile.writeFile(changingStocks);
		
		Boolean actual = Tracking.stockLevel();

		assertTrue(actual);
	}
	
	@Test
	void testHighStockLevel() {
		String[] changingStocks = accessingItemFile.readFile();
		for (int i = 0; i < 25; i++) {
			String[] parts = changingStocks[i].split(",");
			parts[1] = "6";
			changingStocks[i] = parts[0] + "," + parts[1] + "," + parts[2];
		}
		accessingItemFile.writeFile(changingStocks);
		
		Boolean actual = Tracking.stockLevel();

		assertFalse(actual);
	}
}

