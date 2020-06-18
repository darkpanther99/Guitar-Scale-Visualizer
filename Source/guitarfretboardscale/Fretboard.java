package guitarfretboardscale;
import java.io.*;


public class Fretboard implements Serializable{
	private int hurszam;
	private int fretszam;
	private Hur[] hurok;
	private Hang kezdo;
	private String hangolastipus;
	private String hangszer;
	

	
	
	/*létrehoz egy fretboardot a legmélyebb húr kezdõhangja, a hangolás típusa, a húrok és fretek száma és a hangszer típusa alapján
	 * hangszertípus bõvíthetõ késõbb
	 * létrehoz annyi húrt, amennyi kell, azokat a szabály szerint behangolja és rárakja a fretboardra
	 * Szabály:
	 * Bass minden húrjának kezdõhangja standard hangolásban 5 hang távolságra van a nála eggyel arrébb lévõtõl
	 * (igazából félhang, de a programban most 1 hang a félhang)
	 * Guitar minden húrjának kezdõhangja standard hangolásban 5 hang távolságra van, kivéve az utolsó elõtti húr, mert az 4
	 * Drop hangolásban a legmélyebb húr 2 félhanggal lentebb van
	 */
	public Fretboard(Hang kezdohang, String milyenhangolas, int hurokszama, String hangszertipus, int fretekszama)throws HangException{
	kezdo=kezdohang;
	hangolastipus = milyenhangolas;
	hurszam = hurokszama;
	hangszer=hangszertipus;
	fretszam=fretekszama;
	hurok = new Hur[hurszam];
	hurok[0] = new Hur(kezdo, fretszam);
	if(hangszer.equals("Guitar")) {
		if (hangolastipus.equals("standard"))
		{
			for (int i=1; i<hurszam-2; ++i) {
				hurok[i] = new Hur(new Hang((kezdo.getSzam() + 5 * i ) ), fretszam);
			}
			hurok[hurszam-2]= new Hur(new Hang((hurok[hurszam-3].getKezdoSzam() + 4) ), fretszam);
			hurok[hurszam-1]= new Hur(new Hang((hurok[hurszam-3].getKezdoSzam() + 9) ), fretszam);

		}
		if (hangolastipus.equals("drop")) {
			hurok[1] = new Hur(new Hang((kezdo.getSzam() + 7) ), fretszam);
			for (int i=2; i<hurszam-2; ++i) {
				hurok[i] = new Hur(new Hang((hurok[i-1].getKezdoSzam() + 5  ) ), fretszam);
			}
			hurok[hurszam-2]= new Hur(new Hang((hurok[hurszam-3].getKezdoSzam() + 4) ), fretszam);
			hurok[hurszam-1]= new Hur(new Hang((hurok[hurszam-3].getKezdoSzam() + 9) ), fretszam);
		}
	}
	if (hangszer.equals("Bass")) {
		if (hangolastipus.equals("standard")) {
			for (int i=1; i<hurszam; ++i) {
				hurok[i] = new Hur(new Hang((kezdo.getSzam() + 5*i) ), fretszam);
			}
		}
		if (hangolastipus.equals("drop")) {
			hurok[1]= new Hur(new Hang((kezdo.getSzam() + 7) ), fretszam);
			for (int i=1; i<hurszam-1; ++i) {
				hurok[i+1] = new Hur(new Hang((kezdo.getSzam() + 7 + 5*i) ), fretszam);
			}
		}
	}
	}
	
	
	//húrok tömbjét, azok és a fretek számát megkapva fretboardot létrehozó ctor, custom hangolásnál használt
	public Fretboard(Hur[] huroktombje, int fretekszama) {
		hurszam=huroktombje.length;
		fretszam=fretekszama;
		hurok=huroktombje;
		hangszer="mindegy";//mindegy mi a hangszer, ha a húrokból rakjuk össze, legalábbis a program számára
	}
	


	//minden húron keres egy paraméterként kért hangot
	public void keres(Hang h){
		for (int i = 0; i < hurszam; ++i)
		{
			hurok[i].keres(h);
		}
	}
	
	//minden húron keres egy paraméterként kapott skálát
	public void keres(Skala s){
		for (int i = 0; i < hurszam; ++i)
		{
			hurok[i].keres(s);
		}
	}
	
	//érvényteleníti a korábbi kereséseket a fretboardon
	public void nullaz() {
		for (int i=0; i<hurszam; ++i) {
			hurok[i].nullaz();
		}
	}
	

	//getterek
	public Hur getHur(int hanyadik) {
		return hurok[hanyadik];
	}
	
	public int getHurszam() {
		return hurszam;
	}
	
	public int getFretszam() {
		return fretszam;
	}
	
	public String getHangszer() {
		return hangszer;
	}
}
