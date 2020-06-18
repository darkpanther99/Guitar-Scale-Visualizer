package guitarfretboardscale;
import java.io.*;
import java.util.*;

public class Hur implements Serializable{
	private int fretszam;
	private ArrayList<Hang> tomb;
	private Hang kezdohang;

	//kezdohang+fretszam parameterû ctor
	public Hur(Hang kezdo, int fretekszama) throws HangException{
		fretszam=fretekszama;
		tomb=new ArrayList<Hang>(fretszam);
		kezdohang = kezdo;
		for (int i = 0; i <= fretszam; i++)
		{
			tomb.add(i, new Hang((i + kezdohang.getSzam()) ));
		}
	}
	
	//kezdohang neve+fretszam paraméterû ctor
	public Hur(String kezdo, int fretekszama) throws HangException{
		fretszam=fretekszama;
		tomb=new ArrayList<Hang>(fretszam);
		kezdohang = new Hang(kezdo);
		for (int i = 0; i <= fretszam; i++)
		{
			tomb.add(i, new Hang((i + kezdohang.getSzam()) ));
		}
	}
	
	
	
	
	//húron megkeres egy adott hangot
	public void keres(Hang h){
		for (Hang iter : tomb){
			if (iter.getSzam() == h.getSzam()){
				iter.setBennevan(true);
			}
		}
	}
	
	//a korábban megkeresett hangokat érvényteleníti a húron
	public void nullaz() {
		for (Hang iter : tomb) {
			iter.setBennevan(false);
			iter.setIsrootnote(false);
		}
	}
	
	//egy teljes skálát megkeres a húron
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
	
	//ez a fura getter azért van, hogy elkerülje a hosszú láncolását az osztálymetódushívásoknak a Fretboard konstruktorában.
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
