package guitarfretboardscale;
import java.io.*;

public class Skala implements Serializable{

	private Hang kezdohang;
	private String tipus;
	private int fok;
	private Hang[] hangok;
		
	//hangok tömbjét átvéve létrehoz egy megadott fokú skálát ctor
	public Skala(Hang[] tomb){
		fok=tomb.length;
		hangok=tomb;
	}
	
	//létrehoz egy hétfokú alap skálát a kezdõhangból és a skálatipusbol ctor
	public Skala(Hang kezdo, String mod)throws HangException{
		this(kezdo, mod, 7);
	}
	
	//kezdõhang, skálatípus és skálafok paraméterû ctor
	public Skala(Hang kezdo, String mod, int hanyfoku) throws HangException{
		fok = hanyfoku;
		hangok = new Hang[fok];
		kezdohang=kezdo;
		tipus = mod;
		hangok[0] = kezdohang;
		if (fok==7){
			switch(tipus) {
			/*szerintem így egyszerûbb és olvashatóbb a tömb feltöltése, mint egy for looppal, mivel csak 6 elemet kell beletenni
			 *és bonyolultabb megcsinálni azt, hogy néha 1-et, néha 2-t adni az elõzõ hanghoz
			 */
			case "major":
				hangok[1] = new Hang((kezdo.getSzam() + 2) );
				hangok[2] = new Hang((kezdo.getSzam() + 4) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 9) );
				hangok[6] = new Hang((kezdo.getSzam() + 11) );
				break;
			
			case "dorian":
				hangok[1] = new Hang((kezdo.getSzam() + 2) );
				hangok[2] = new Hang((kezdo.getSzam() + 3) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 9) );
				hangok[6] = new Hang((kezdo.getSzam() + 10) );
				break;
			
			case "phrygian":
				hangok[1] = new Hang((kezdo.getSzam() + 1) );
				hangok[2] = new Hang((kezdo.getSzam() + 3) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 8) );
				hangok[6] = new Hang((kezdo.getSzam() + 10) );
				break;
			
			case "lydian":
				hangok[1] = new Hang((kezdo.getSzam() + 2) );
				hangok[2] = new Hang((kezdo.getSzam() + 4) );
				hangok[3] = new Hang((kezdo.getSzam() + 6) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 9) );
				hangok[6] = new Hang((kezdo.getSzam() + 11) );
				break;
			
			case "mixolydian":
				hangok[1] = new Hang((kezdo.getSzam() + 2) );
				hangok[2] = new Hang((kezdo.getSzam() + 4) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 9) );
				hangok[6] = new Hang((kezdo.getSzam() + 10) );
				break;
			
			case "minor":
				hangok[1] = new Hang((kezdo.getSzam() + 2) );
				hangok[2] = new Hang((kezdo.getSzam() + 3) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 7) );
				hangok[5] = new Hang((kezdo.getSzam() + 8) );
				hangok[6] = new Hang((kezdo.getSzam() + 10) );
				break;
			
			case "locrian":
				hangok[1] = new Hang((kezdo.getSzam() + 1) );
				hangok[2] = new Hang((kezdo.getSzam() + 3) );
				hangok[3] = new Hang((kezdo.getSzam() + 5) );
				hangok[4] = new Hang((kezdo.getSzam() + 6) );
				hangok[5] = new Hang((kezdo.getSzam() + 8) );
				hangok[6] = new Hang((kezdo.getSzam() + 10) );
				break;
			default:
				break;
			}
		}
		}
			
		
	
	
	//getter
	public int getFok(){
		return fok;
	}

	
	//visszaadja a skála kért hangját
	public Hang get(int idx)throws ArrayIndexOutOfBoundsException{
	if (idx >= fok || idx < 0)
	{
		throw new ArrayIndexOutOfBoundsException("Rossz eleres!");
		}
	return hangok[idx];
	}
		
	



		
		
}

