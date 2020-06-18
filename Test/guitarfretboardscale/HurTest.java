package guitarfretboardscale;
import static org.junit.Assert.*;
import java.util.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class HurTest {
	private Hur hu;
	private Hang h;
	@Before
	public void setUp() throws Exception {
		hu=new Hur("E", 20);
		h=new Hang("a");
	}

	@Test
	public void testGetters() throws HangException {
		assertEquals("getkezdoszam", 0, hu.getKezdoSzam());
		assertEquals("getkezdo", "E", hu.getKezdo().getNev());
		assertEquals("getfretszam", 20, hu.getFretszam());
	}
	
	@Test
	public void testKeres() {
		//Az E húr 5. hangja A
		assertFalse("nincsbenne", hu.getHang(5).getBennevan());
		hu.keres(h);
		assertTrue("bennevan", hu.getHang(5).getBennevan());
	}
	
	@Test
	public void testSkalaKeres() throws HangException {
		Skala s=new Skala(new Hang("C"), "major");
		for (int i=0; i<hu.getFretszam(); ++i) {
			assertFalse("nincsbenne", hu.getHang(i).getBennevan());
		}
		
		hu.keres(s);
		
		Integer[] irossztomb={2, 4, 6, 9, 11, 14, 16, 18, 21, 23};//a húr ezen bundjain nincs C dúrbeli hang
		List<Integer> irosszlista=Arrays.asList(irossztomb);
		
		for (int i=0; i<hu.getFretszam(); ++i) {
			if (irosszlista.contains(Integer.valueOf(i))) {
				assertFalse("nincsbenne", hu.getHang(i).getBennevan());
			}
			else {
				assertTrue("bennevan", hu.getHang(i).getBennevan());
			}
		}
	}
	
	@Test
	public void testNullaz() {
		//Az E húr 5. hangja A
		hu.keres(h);
		assertTrue("bennevan", hu.getHang(5).getBennevan());
		hu.nullaz();
		assertFalse("bennevan", hu.getHang(5).getBennevan());
	}
	
	@Test
	public void testRootNote() throws HangException {
		Skala s=new Skala(new Hang("C"), "major");
		hu.keres(s);
		assertTrue("rootNoteTest", hu.getHang(8).getIsrootnote());
	}

}
