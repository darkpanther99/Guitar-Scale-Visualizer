package guitarfretboardscale;
import java.awt.*;  
import javax.swing.JFrame;  
import java.awt.Font;


public class Fretboardgraphics extends Canvas{
	
	private Fretboard fret;
	private int hurszam;
	private int fretszam;
	
	//konstruktor, ami egy fretboardot k�r be param�terk�nt
	//a k�rt sk�l�t m�r a beolvas�s sor�n be�ll�totta a program a fretboardra, itt csak a bennevan �s az isrootnote adattag alapj�n vizualiz�l
	public Fretboardgraphics(Fretboard f) {
		fret=f;
		hurszam=fret.getHurszam();
		fretszam=fret.getFretszam();
	}
    
	/*a Canvas oszt�ly paint f�ggv�ny�t overrideol� f�ggv�ny
	 * Ez felel a fretboard �s a megjelen�tett sk�la grafikus megjelen�t�s��rt
	 */
	public void paint(Graphics g) { 
		
	g.setFont(new Font("Arial", Font.PLAIN, 15));
	
	final int ablakmagassag=500;
	final int alapablakszelesseg=1000;
	final int eltolas=40;
	final int eltoltmagassag=ablakmagassag-eltolas;
	final int gitarablakszeltav=50;
	final int hasznosmagassag=ablakmagassag-(eltolas+2*gitarablakszeltav); //ez 360 lesz
	final int frethossz=25;
	final int fretjelolesmagassag=25;
	final int koratmero=30;
	
	//kezd� t�glalap �s a vizszintes vonalak
	//az�rt van hurszam-1 a h�nyadosban, mert siman hurszamot haszn�lva elcs�szn�nak a vonalak
	//fretszam+1 pedig az�rt kell, mert sim�n fretszamot haszn�lva nem �rne el a git�r v�g�ig a vonal
      g.fillRect(45, 50, 5, 361);
      for (int i=0; i<hurszam; ++i) {
    	  g.drawLine(gitarablakszeltav, gitarablakszeltav+hasznosmagassag/(hurszam-1)*i, ((fretszam+1)*40)+10, gitarablakszeltav+hasznosmagassag/(hurszam-1)*i);
      }
      
      //f�gg�leges vonalak es fejlec
      //az�rt 1-t�l megy a ciklus, mert a nulladik frethez nem teszek jelz�st, a mellette l�v� f�gg�leges vonal pedig a kezd� t�glalap
      for (int i=1; i<=fretszam; ++i) {
    	  g.drawLine(gitarablakszeltav+alapablakszelesseg/frethossz*i, gitarablakszeltav, gitarablakszeltav+alapablakszelesseg/frethossz*i, eltoltmagassag-gitarablakszeltav);
    	  String s=String.valueOf(i);
    	  g.drawString(s, 25+alapablakszelesseg/frethossz*i, fretjelolesmagassag);
      }
      
      //kezdohangok megjelen�t�se
      //az�rt van k�l�nszedve, mert a h�rok kezd�hangj�t mindenk�pp megjelen�tem, csak nem teszek r� k�rt, ha a keresett sk�l�n k�v�li
      for (int i=hurszam-1; i>=0; --i) {
    	  String str=fret.getHur(i).getKezdo().getNev();
    	  g.setColor(Color.BLACK);
    	  g.drawString(str, 19, 60+hasznosmagassag/(hurszam-1)*(hurszam-i-1));
    	  if (fret.getHur(i).getHang(0).getBennevan()==true) {
    		  if (fret.getHur(i).getKezdo().getIsrootnote()) {
    			  g.setColor(Color.RED);
    			  g.drawOval(9, gitarablakszeltav-10+hasznosmagassag/(hurszam-1)*(hurszam-i-1), koratmero, koratmero);
    			  g.drawString(str, 19, gitarablakszeltav+10+hasznosmagassag/(hurszam-1)*(hurszam-i-1));
    		  }
    		  else {
    			  g.setColor(Color.BLACK);
    			  g.drawOval(9, gitarablakszeltav-10+hasznosmagassag/(hurszam-1)*(hurszam-i-1), koratmero, koratmero);
    		  }
    	  	
    	  }
      }
      
      //skala minden hangj�nak grafikus megjelen�t�se
      for (int i=hurszam-1; i>=0; --i) {
    	  for (int j=1; j<=fretszam; ++j) {
    		  if (fret.getHur(i).getHang(j).getBennevan()==true) {
    			  if (fret.getHur(i).getHang(j).getIsrootnote()) {
    				  g.setColor(Color.RED);
    				  String s=fret.getHur(i).getHang(j).getNev();
    	    		  g.drawString(s, 25+alapablakszelesseg/frethossz*j, gitarablakszeltav+10+hasznosmagassag/(hurszam-1)*(hurszam-i-1));
    	    		  g.drawOval(15+alapablakszelesseg/frethossz*j, gitarablakszeltav-10+hasznosmagassag/(hurszam-1)*(hurszam-i-1), koratmero, koratmero);
    			  }
    			  else {
    			  g.setColor(Color.BLACK);
	    		  String s=fret.getHur(i).getHang(j).getNev();
	    		  g.drawString(s, 25+alapablakszelesseg/frethossz*j, gitarablakszeltav+10+hasznosmagassag/(hurszam-1)*(hurszam-i-1));
	    		  g.drawOval(15+alapablakszelesseg/frethossz*j, gitarablakszeltav-10+hasznosmagassag/(hurszam-1)*(hurszam-i-1), koratmero, koratmero);
    			  }
    		  }
    	  } 
      }   
  }  

}  


