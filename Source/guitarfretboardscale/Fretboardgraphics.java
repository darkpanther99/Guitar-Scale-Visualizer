package guitarfretboardscale;
import java.awt.*;  
import javax.swing.JFrame;  
import java.awt.Font;


public class Fretboardgraphics extends Canvas{
	
	private Fretboard fret;
	private int hurszam;
	private int fretszam;
	
	//konstruktor, ami egy fretboardot kér be paraméterként
	//a kért skálát már a beolvasás során beállította a program a fretboardra, itt csak a bennevan és az isrootnote adattag alapján vizualizál
	public Fretboardgraphics(Fretboard f) {
		fret=f;
		hurszam=fret.getHurszam();
		fretszam=fret.getFretszam();
	}
    
	/*a Canvas osztály paint függvényét overrideoló függvény
	 * Ez felel a fretboard és a megjelenített skála grafikus megjelenítéséért
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
	
	//kezdõ téglalap és a vizszintes vonalak
	//azért van hurszam-1 a hányadosban, mert siman hurszamot használva elcsúsznának a vonalak
	//fretszam+1 pedig azért kell, mert simán fretszamot használva nem érne el a gitár végéig a vonal
      g.fillRect(45, 50, 5, 361);
      for (int i=0; i<hurszam; ++i) {
    	  g.drawLine(gitarablakszeltav, gitarablakszeltav+hasznosmagassag/(hurszam-1)*i, ((fretszam+1)*40)+10, gitarablakszeltav+hasznosmagassag/(hurszam-1)*i);
      }
      
      //függõleges vonalak es fejlec
      //azért 1-tõl megy a ciklus, mert a nulladik frethez nem teszek jelzést, a mellette lévõ függõleges vonal pedig a kezdõ téglalap
      for (int i=1; i<=fretszam; ++i) {
    	  g.drawLine(gitarablakszeltav+alapablakszelesseg/frethossz*i, gitarablakszeltav, gitarablakszeltav+alapablakszelesseg/frethossz*i, eltoltmagassag-gitarablakszeltav);
    	  String s=String.valueOf(i);
    	  g.drawString(s, 25+alapablakszelesseg/frethossz*i, fretjelolesmagassag);
      }
      
      //kezdohangok megjelenítése
      //azért van különszedve, mert a húrok kezdõhangját mindenképp megjelenítem, csak nem teszek rá kört, ha a keresett skálán kívüli
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
      
      //skala minden hangjának grafikus megjelenítése
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


