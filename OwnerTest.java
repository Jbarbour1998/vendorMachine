import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Owner;
import project.accessingItemFile;
import project.accessingMachineFile;

class OwnerTest {

	String[] ItemsRows = new String[25];
	String MachineRows;
	
	@BeforeEach
	void setUp() throws Exception {
		ItemsRows = accessingItemFile.readFile();
		MachineRows = accessingMachineFile.readFile();
	}

	@AfterEach
	void tearDown() throws Exception {
		accessingItemFile.writeFile(ItemsRows);
		accessingMachineFile.writeFile(MachineRows);
	}

	@Test
	void testChangeItemPrice() {
		Owner.changeItemPrice("A1", 100);
		
		String[] itemRows = accessingItemFile.readFile();
		String[] newParts = itemRows[0].split(",");
		int newAmount = Integer.parseInt(newParts[0]);
		
		assertEquals(newAmount, 100);
	}

	@Test
	void testChangeItemName() {
		Owner.changeItemName("A1", "BTS Wings Album");
		
		String[] newItemRows = accessingItemFile.readFile();
		String[] newParts = newItemRows[0].split(",");
		String newName = newParts[2];
		
		assertEquals(newName,"BTS Wings Album");
	}

	@Test
	void testWrongCheckPIN() {
		String currentMachineFile = accessingMachineFile.readFile();
		String[] parts = currentMachineFile.split(",");
		String currentPIN = parts[0];
		boolean result;
		
		if (currentPIN != "000000") {
			result = Owner.checkPIN("000000");
		}
		else {
			result = Owner.checkPIN("123456");
		}
		
		assertFalse(result);
	}
	
	@Test
	void testRightCheckPIN() {
		
		String currentMachineFile = accessingMachineFile.readFile();
		String[] parts = currentMachineFile.split(",");
		String currentPIN = parts[0];
		
		boolean result = Owner.checkPIN(currentPIN);
		
		assertTrue(result);
	}

	@Test
	void testChangePIN() {
		String newPIN = "902819";
		String currentMachineFile = accessingMachineFile.readFile();
		String[] parts = currentMachineFile.split(",");
		parts[0] = newPIN;
		String newMachineFile = parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4];
		accessingMachineFile.writeFile(newMachineFile);
		
		String CheckMachineFile = accessingMachineFile.readFile();
		String[] checkParts =  CheckMachineFile.split(",");
		
		assertEquals(newPIN, checkParts[0]);
	}

	@Test
	void testRestockItemSlots() {
		String[] currentItemRows = accessingItemFile.readFile();
		String[] parts = currentItemRows[0].split(",");
		parts[1] = "5";
		currentItemRows[0] = parts[0] + "," + parts[1] + "," + parts[2];
		System.out.println(parts[0] + "," + parts[1] + "," + parts[2]);
	    accessingItemFile.writeFile(currentItemRows);
		
		Owner.restockItemSlots("A1", 10);
		
		String[] newItemRows = accessingItemFile.readFile();
		String[] newParts = newItemRows[0].split(",");
		String newAmount = newParts[1];
		
		assertEquals(newAmount,"15");
	}

}
