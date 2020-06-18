package guitarfretboardscale;
import java.io.*;

public class Skala implements Serializable{

	private Hang kezdohang;
	private String tipus;
	private int fok;
	private Hang[] hangok;
		
	//hangok t�mbj�t �tv�ve l�trehoz egy megadott fok� sk�l�t ctor
	public Skala(Hang[] tomb){
		fok=tomb.length;
		hangok=tomb;
	}
	
	//l�trehoz egy h�tfok� alap sk�l�t a kezd�hangb�l �s a sk�latipusbol ctor
	public Skala(Hang kezdo, String mod)throws HangException{
		this(kezdo, mod, 7);
	}
	
	//kezd�hang, sk�lat�pus �s sk�lafok param�ter� ctor
	public Skala(Hang kezdo, String mod, int hanyfoku) throws HangException{
		fok = hanyfoku;
		hangok = new Hang[fok];
		kezdohang=kezdo;
		tipus = mod;
		hangok[0] = kezdohang;
		if (fok==7){
			switch(tipus) {
			/*szerintem �gy egyszer�bb �s olvashat�bb a t�mb felt�lt�se, mint egy for looppal, mivel csak 6 elemet kell beletenni
			 *�s bonyolultabb megcsin�lni azt, hogy n�ha 1-et, n�ha 2-t adni az el�z� hanghoz
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

	
	//visszaadja a sk�la k�rt hangj�t
	public Hang get(int idx)throws ArrayIndexOutOfBoundsException{
	if (idx >= fok || idx < 0)
	{
		throw new ArrayIndexOutOfBoundsException("Rossz eleres!");
		}
	return hangok[idx];
	}
		
	



		
		
}

