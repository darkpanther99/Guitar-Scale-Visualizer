package guitarfretboardscale;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HangTest {
	private Hang h;
	
	@Before
	public void setUp() throws Exception {
		h=new Hang("E");
	}

	@Test
	public void testGetters() {
		assertEquals("getszamtest", 0, h.getSzam());
		assertEquals("getnevtest", "E", h.getNev());
		assertFalse("getnevtest", h.getBennevan());
		assertFalse("getnevtest", h.getIsrootnote());
	}
	@Test
	public void testSzamosCtor() throws HangException{
		Hang h2=new Hang(0);
		assertEquals("getszamtest", 0, h2.getSzam());
		assertEquals("getnevtest", "E", h2.getNev());
		Hang h3=new Hang(12);
		assertEquals("egyenloekaszamok?", h2.getSzam(), h3.getSzam());
	}
	@Test
	public void testNagybetusites() throws HangException {
		h=new Hang("e");
		assertEquals("getszamtest", 0, h.getSzam());
		assertEquals("getnevtest", "E", h.getNev());
	}
	@Test
	public void testBennevan() {
		assertFalse("bennevantest1", h.getBennevan());
		h.setBennevan(true);
		assertTrue("bennevantest2", h.getBennevan());
	}
	@Test
	public void testflatorsharp() throws Exception{
		h=new Hang("Db");
		assertEquals("mostSharp", "C#", h.getNev());
		Hang.flatInstead(true);
		h=new Hang("Db");
		assertEquals("mostFlat", "Db", h.getNev());
		Hang.flatInstead(false);
		h=new Hang("Ab");
		assertEquals("mostSharp", "G#", h.getNev());
		Hang.flatInstead(true);
		h=new Hang("Ab");
		assertEquals("mostFlat", "Ab", h.getNev());
		h=new Hang("G#");
		assertEquals("mostFlat", "Ab", h.getNev());
	}
	
	@Test(expected=HangException.class)
	public void testException() throws HangException{
		h=new Hang("Q");
	}
	
	@Test(expected=HangException.class)
	public void testUresHang() throws HangException{
		h=new Hang("");
	}
	

}
