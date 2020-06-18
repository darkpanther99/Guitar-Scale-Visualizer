package guitarfretboardscale;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FretboardTest {
	private Fretboard f;
	private Skala s;
	
	@Before
	public void setUp() throws Exception {
		f=new Fretboard(new Hang("E"), "standard", 6, "Guitar", 24);
		s=new Skala(new Hang("A"), "minor");
	}

	@Test
	public void testGetters() {
		assertEquals("fretszam", 24, f.getFretszam());
		assertEquals("hurszam", 6, f.getHurszam());
		assertEquals("hangszer", "Guitar", f.getHangszer());
	}
	
	@Test
	public void testFretboardKeres() {
		for (int i=0; i<f.getHurszam(); ++i) {
			for (int j=0; j<f.getFretszam(); ++j) {
				assertFalse("nincsbenne", f.getHur(i).getHang(j).getBennevan());
			}		
		}
		
		//itt a keresés tesztjét csak 3 húron végzem el: Ha ennyin mûködik, 6-on is fog. (Standard hangolásban az utolsó és az elsõ húr ugyanaz)
		Integer[] irossztomb={2, 4, 6, 9, 11, 14, 16, 18, 21, 23};
		List<Integer> irosszlista=Arrays.asList(irossztomb);
		
		Integer[] irossztomb2={1, 4, 6, 9, 11, 13, 16, 18, 21, 23};
		List<Integer> irosszlista2=Arrays.asList(irossztomb2);
		
		f.keres(s);
		
			for (int j=0; j<f.getFretszam(); ++j) {
				if (irosszlista.contains(Integer.valueOf(j))) {
					assertFalse("nincsbenne", f.getHur(0).getHang(j).getBennevan());
					assertFalse("nincsbenne", f.getHur(5).getHang(j).getBennevan());
				}
				else {
					assertTrue("bennevan", f.getHur(0).getHang(j).getBennevan());
					assertTrue("bennevan", f.getHur(5).getHang(j).getBennevan());
				}
				
			}
			
			for (int j=0; j<f.getFretszam(); ++j) {
				if (irosszlista2.contains(Integer.valueOf(j))) {
				assertFalse("nincsbenne", f.getHur(1).getHang(j).getBennevan());
				}
				else {
					assertTrue("bennevan", f.getHur(1).getHang(j).getBennevan());
				}
			}
		
	}
	
	@Test
	public void testFretboardNullaz() {
		Integer[] irossztomb={2, 4, 6, 9, 11, 14, 16, 18, 21, 23};
		List<Integer> irosszlista=Arrays.asList(irossztomb);
		
		Integer[] irossztomb2={1, 4, 6, 9, 11, 13, 16, 18, 21, 23};
		List<Integer> irosszlista2=Arrays.asList(irossztomb2);
		
		f.keres(s);
		
			for (int j=0; j<f.getFretszam(); ++j) {
				if (irosszlista.contains(Integer.valueOf(j))) {
					assertFalse("nincsbenne", f.getHur(0).getHang(j).getBennevan());
					assertFalse("nincsbenne", f.getHur(5).getHang(j).getBennevan());
				}
				else {
					assertTrue("bennevan", f.getHur(0).getHang(j).getBennevan());
					assertTrue("bennevan", f.getHur(5).getHang(j).getBennevan());
				}
				
			}
			
			for (int j=0; j<f.getFretszam(); ++j) {
				if (irosszlista2.contains(Integer.valueOf(j))) {
				assertFalse("nincsbenne", f.getHur(1).getHang(j).getBennevan());
				}
				else {
					assertTrue("bennevan", f.getHur(1).getHang(j).getBennevan());
				}
			}
			
		f.nullaz();
		
		for (int i=0; i<f.getHurszam(); ++i) {
			for (int j=0; j<f.getFretszam(); ++j) {
				assertFalse("nincsbenne", f.getHur(i).getHang(j).getBennevan());
			}		
		}
	}
}
