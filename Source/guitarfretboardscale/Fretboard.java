package guitarfretboardscale;
import java.io.*;


public class Fretboard implements Serializable{
	private int hurszam;
	private int fretszam;
	private Hur[] hurok;
	private Hang kezdo;
	private String hangolastipus;
	private String hangszer;
	

	
	
	/*l�trehoz egy fretboardot a legm�lyebb h�r kezd�hangja, a hangol�s t�pusa, a h�rok �s fretek sz�ma �s a hangszer t�pusa alapj�n
	 * hangszert�pus b�v�thet� k�s�bb
	 * l�trehoz annyi h�rt, amennyi kell, azokat a szab�ly szerint behangolja �s r�rakja a fretboardra
	 * Szab�ly:
	 * Bass minden h�rj�nak kezd�hangja standard hangol�sban 5 hang t�vols�gra van a n�la eggyel arr�bb l�v�t�l
	 * (igaz�b�l f�lhang, de a programban most 1 hang a f�lhang)
	 * Guitar minden h�rj�nak kezd�hangja standard hangol�sban 5 hang t�vols�gra van, kiv�ve az utols� el�tti h�r, mert az 4
	 * Drop hangol�sban a legm�lyebb h�r 2 f�lhanggal lentebb van
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
	
	
	//h�rok t�mbj�t, azok �s a fretek sz�m�t megkapva fretboardot l�trehoz� ctor, custom hangol�sn�l haszn�lt
	public Fretboard(Hur[] huroktombje, int fretekszama) {
		hurszam=huroktombje.length;
		fretszam=fretekszama;
		hurok=huroktombje;
		hangszer="mindegy";//mindegy mi a hangszer, ha a h�rokb�l rakjuk �ssze, legal�bbis a program sz�m�ra
	}
	


	//minden h�ron keres egy param�terk�nt k�rt hangot
	public void keres(Hang h){
		for (int i = 0; i < hurszam; ++i)
		{
			hurok[i].keres(h);
		}
	}
	
	//minden h�ron keres egy param�terk�nt kapott sk�l�t
	public void keres(Skala s){
		for (int i = 0; i < hurszam; ++i)
		{
			hurok[i].keres(s);
		}
	}
	
	//�rv�nytelen�ti a kor�bbi keres�seket a fretboardon
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
