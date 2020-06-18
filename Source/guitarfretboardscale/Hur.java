package guitarfretboardscale;
import java.io.*;
import java.util.*;

public class Hur implements Serializable{
	private int fretszam;
	private ArrayList<Hang> tomb;
	private Hang kezdohang;

	//kezdohang+fretszam parameter� ctor
	public Hur(Hang kezdo, int fretekszama) throws HangException{
		fretszam=fretekszama;
		tomb=new ArrayList<Hang>(fretszam);
		kezdohang = kezdo;
		for (int i = 0; i <= fretszam; i++)
		{
			tomb.add(i, new Hang((i + kezdohang.getSzam()) ));
		}
	}
	
	//kezdohang neve+fretszam param�ter� ctor
	public Hur(String kezdo, int fretekszama) throws HangException{
		fretszam=fretekszama;
		tomb=new ArrayList<Hang>(fretszam);
		kezdohang = new Hang(kezdo);
		for (int i = 0; i <= fretszam; i++)
		{
			tomb.add(i, new Hang((i + kezdohang.getSzam()) ));
		}
	}
	
	
	
	
	//h�ron megkeres egy adott hangot
	public void keres(Hang h){
		for (Hang iter : tomb){
			if (iter.getSzam() == h.getSzam()){
				iter.setBennevan(true);
			}
		}
	}
	
	//a kor�bban megkeresett hangokat �rv�nytelen�ti a h�ron
	public void nullaz() {
		for (Hang iter : tomb) {
			iter.setBennevan(false);
			iter.setIsrootnote(false);
		}
	}
	
	//egy teljes sk�l�t megkeres a h�ron
	public void keres(Skala s){
		roototKeres(s.get(0));
		for (int i = 1; i < s.getFok(); ++i){
			keres(s.get(i));
		}
	}
	

	public void roototKeres(Hang h) {
		for (Hang iter : tomb){
			if (iter.getSzam() == h.getSzam()){
				iter.setBennevan(true);
				iter.setIsrootnote(true);
			}
		}
	}
	
	//ez a fura getter az�rt van, hogy elker�lje a hossz� l�ncol�s�t az oszt�lymet�dush�v�soknak a Fretboard konstruktor�ban.
	public int getKezdoSzam() {	
		return this.getKezdo().getSzam();
	}
	
	//getterek
	public Hang getKezdo() {
		return tomb.get(0);
	}
	
	public Hang getHang(int hanyadik) {
		return tomb.get(hanyadik);
	}
	
	public int getFretszam() {
		return fretszam;
	}




}
