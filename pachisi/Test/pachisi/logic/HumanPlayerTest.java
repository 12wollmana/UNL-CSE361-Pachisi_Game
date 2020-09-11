package pachisi.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HumanPlayerTest {
	private HumanPlayer test;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHumanPlayer() {
		test = new HumanPlayer("Larry", 0);
		assertEquals(test.name, "Larry");
		assertEquals(test.color, 0);
	}

	@Test
	public void testCalcColorString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColorString() {
		fail("Not yet implemented");
	}

}
