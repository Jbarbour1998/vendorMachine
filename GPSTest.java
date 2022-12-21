import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.GPS;
import project.accessingMachineFile;

class GPSTest {

	String MachineFile;
	@BeforeEach
	void setUp() throws Exception {
		MachineFile = accessingMachineFile.readFile();
	}

	@AfterEach
	void tearDown() throws Exception {
		accessingMachineFile.writeFile(MachineFile);
	}

	@Test
	void testChangeLocation() {
		String machineFileBefore = accessingMachineFile.readFile();
		String[] partsBefore = machineFileBefore.split(",");
		String before = partsBefore[4];
		
		GPS.changeLocation();
		
		String machineFileAfter = accessingMachineFile.readFile();
		String[] partsAfter = machineFileAfter.split(",");
		String after = partsAfter[4];
		
		assertNotEquals(before, after);
	}

}
