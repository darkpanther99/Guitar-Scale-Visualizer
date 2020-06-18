package guitarfretboardscale;
import java.io.*;


public class Hang implements Serializable
{
	private int szam;
	private String nev;
	private boolean bennevan;
	private boolean isrootnote;
	private static boolean flatinsteadofsharp=false;
	
	
	/*Sztringes ctor
	 * Ha a megadott sztring elsõ karaktere kicsi, a program nagybetûsíti a konzisztens megjelenítés miatt,
	 * viszont mindkettõt nem, mivel a félhangok jele flat módban szigorúan kis b betû.
	 */
	public Hang(String milyen) throws HangException{
		bennevan = false;
		isrootnote=false;
		if (milyen.length()==0) {
			throw new HangException("Non-existent note!");
		}
		//lekezeli a félhangokat, a hang betûjének ASCII kódjával kicsit trükközve, emiatt az Ab és a D# hangokat külön lekezeli
		if(milyen.length()==2) {
			if (flatinsteadofsharp==false) {
				if(milyen.charAt(1)=='b') {
					if(milyen.charAt(0)=='A') {
						milyen="G#";
					}
					else {
					milyen=String.valueOf((char)(milyen.charAt(0)-1))+"#";
					}
				}
			}
			else {
				if(milyen.charAt(1)=='#') {
					if(milyen.charAt(0)=='G') {
						milyen="Ab";
					}
					else {
					milyen=String.valueOf((char)(milyen.charAt(0)+1))+"b";
					}
				}
			}

		}
		
		if (Character.isUpperCase(milyen.charAt(0))) {
			nev = milyen;
		}
		else {
			char c= milyen.charAt(0);
			c=Character.toUpperCase(c);
			String s1=String.valueOf(c);
			if (milyen.length()==1) {
				nev=s1;
			}
			else {
				String s2=String.valueOf(milyen.charAt(1));
				nev=s1+s2;
			}
		}
			switch (nev){
			case "E":	szam = 0; break;
			case "F":   szam = 1; break;
			case "F#":	case "Gb":	szam = 2; break;
			case "G":	szam = 3; break;
			case "G#":	case "Ab": 	szam = 4; break;
			case "A":	szam = 5; break;
			case "A#":	case "Bb":	szam = 6; break;
			case "B":	szam = 7; break;
			case "C":	szam = 8; break;
			case "C#":	case "Db":	szam = 9; break;
			case "D": 	szam = 10; break;	
			case "D#":	case "Eb":	szam = 11; break;
			default:	szam = -1; throw new HangException("Non-existent note!");
			}
	}

	//számos ctor
   public Hang(int szam) throws HangException{
		bennevan = false;
		isrootnote=false;
		this.szam = szam % 12;
			switch(this.szam) {
			case 0: 	nev = "E"; break;
			case 1: 	nev = "F"; break;
			case 2: 	if (flatinsteadofsharp) { nev = "Gb"; } else { nev = "F#";} break;
			case 3:		nev = "G"; break;
			case 4:		if (flatinsteadofsharp) { nev = "Ab"; } else { nev = "G#";} break;
			case 5: 	nev = "A"; break;
			case 6: 	if (flatinsteadofsharp) { nev = "Bb"; } else { nev = "A#";} break;
			case 7:		nev = "B"; break;
			case 8:		nev = "C"; break;
			case 9: 	if (flatinsteadofsharp) { nev = "Db"; } else { nev = "C#";} break;
			case 10:	nev = "D"; break;
			case 11:	if (flatinsteadofsharp) { nev = "Eb"; } else { nev = "D#";} break;
			default:	nev = "hiba"; throw new HangException("Non-existent note!");
			}
	  }
   

	//static függvény, ami megváltoztatja, hogy Flat(b) vagy Sharp(#) jelölésû félhangok legyenek.
   public static void flatInstead(boolean b) {
	   flatinsteadofsharp=b;
   }
   
	
	//getterek és setterek	
	public int getSzam(){
		return szam;
	}

	public String getNev(){
		return nev;
	}

	public void setBennevan(boolean b){
		bennevan = b;
	}

	public boolean getBennevan(){
		return bennevan;
	}
	
	public void setIsrootnote(boolean b) {
		isrootnote=b;
	}
	
	public boolean getIsrootnote() {
		return isrootnote;
	}
	
	
}