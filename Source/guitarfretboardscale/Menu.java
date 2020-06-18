package guitarfretboardscale;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;  
	import java.io.*;
	
	public class Menu extends JFrame implements ActionListener {

	private Skala s, beolvasottskala;
	private Fretboard fret, beolvasottfretboard;
	private JMenuBar mb;
	private JMenu menu;
	private JMenuItem scalesave, scaleread, tuningsave, tuningread;
	private JLabel l1, l2;
	private JTextField customskalatextfield, customhangolastextfield, hangolaskezdo, skalakezdo;
	private JSpinner fretszambeolvaso, hurszambeolvaso;
	private JButton finish;
	private JComboBox <String> jc, jc2, jcinstrument, jcflatorsharp;
	private String hangszertipus="Guitar";
	private String hangolastipus="standard";
	private String skalatipus="major";
	private int hurszam, fretszam;
	private boolean customskalagombkint=false;
	private boolean customhangolasgombkint=false;
	private boolean volthangolasexception=false;
	private boolean voltskalaexception=false;
	private JPanel hangolasdialogpanel, skaladialogpanel;
	private JDialog jdhangolas, jdskala;
	
	public Menu() {   
	    super("Menu");
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//a JFrame BoxLayoutot használ, hogy a rajta lévõ panelek egymás alá kerüljenek
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	    
	    //flatorsharp valaszto combobox
	    String[] flatsharp= {"sharp", "flat"};
	    jcflatorsharp=new JComboBox<String>(flatsharp);
	    jcflatorsharp.addActionListener(this);
	    

	    //menu
	    mb = new JMenuBar();
	    menu = new JMenu("File");
	    scalesave = new JMenuItem("Save scale to file");
	    scaleread = new JMenuItem("Read scale from file");
	    tuningsave = new JMenuItem("Save tuning to file");
	    tuningread = new JMenuItem("Read tuning from file");
	    scalesave.addActionListener(this);
	    scaleread.addActionListener(this);
	    tuningsave.addActionListener(this);
	    tuningread.addActionListener(this);
	    mb.add(menu);
	    menu.add(scalesave);
	    menu.add(scaleread);
	    menu.add(tuningsave);
	    menu.add(tuningread);
	    
	    
	    //hangszervalaszto combobox
	    String[] hangszertipusok= {"Guitar", "Bass"};
	    jcinstrument = new  JComboBox<String>(hangszertipusok);
	    jcinstrument.addActionListener(this);
	    
	    //hurszambeolvaso spinner
	    hurszambeolvaso=new JSpinner(new SpinnerNumberModel(6, 4, 9, 1));
	    hurszambeolvaso.setEditor(new JSpinner.DefaultEditor(hurszambeolvaso));
	    
	    //hangolasbeolvaso textfield
	    hangolaskezdo=new JTextField(2);
	    
	    //skalakezdohang beolvaso textfield
	    skalakezdo=new JTextField(2);
	    
	    //fretszambeolvaso spinner
	    fretszambeolvaso=new JSpinner(new SpinnerNumberModel(24, 18, 36, 1));
	    fretszambeolvaso.setEditor(new JSpinner.DefaultEditor(fretszambeolvaso));
	    	    
	    //hangolastipus combobox
	    String[] hangolastipusok= {"standard", "drop", "custom", "read from file"};
	    jc=new JComboBox<String>(hangolastipusok);
	    jc.addActionListener(this);
	    
	    //skalatipus combobox
	    String[] skalatipusok= {"major", "dorian", "phrygian", "lydian", "mixolydian", "minor", "locrian", "custom", "read from file"};
	    jc2=new JComboBox<String>(skalatipusok);
	    jc2.addActionListener(this);
	    
	    //done gomb
	    finish=new JButton("Done");
	    finish.addActionListener(this);
	    
	    //layout elkészítése
	    //felsõ panel
	    JPanel jp1=new JPanel();
		jp1.add(new JLabel("Choose your instrument!"));
		jp1.add(jcinstrument);
		add(jp1);
		
		//2. panel
		JPanel jp2=new JPanel();
		jp2.add(new JLabel("How many strings does it have?"));
		jp2.add(hurszambeolvaso);
		add(jp2);
		
		//3. panel
		JPanel jp3=new JPanel();
		jp3.add(new JLabel("How many frets does it have?"));
		jp3.add(fretszambeolvaso);
		add(jp3);
		
		//4.panel
		JPanel jp4=new JPanel();
		jp4.add(new JLabel("Would you like to use flats or sharps?"));
		jp4.add(jcflatorsharp);
		add(jp4);
		
		//5.panel
		JPanel jp5=new JPanel();
		jp5.add(new JLabel("Choose a tuning!"));
		jp5.add(new JLabel("Choose a scale!"));
		add(jp5);
		
		//6. panel
		JPanel jp6=new JPanel();
		jp6.add(hangolaskezdo);
		jp6.add(jc);
		jp6.add(skalakezdo);
		jp6.add(jc2);
		add(jp6);
		
		//7. panel
		JPanel jp7=new JPanel();
		l1=new JLabel("Custom tuning:      ");
		l2=new JLabel("      Custom scale:");
	    jp7.add(l1);
	    jp7.add(l2);
	    add(jp7);
		
	    //8.panel
	    JPanel jp8=new JPanel();
		customhangolastextfield=new JTextField(15);	
		customhangolastextfield.setEditable(false);
		customskalatextfield=new JTextField(15);
		customskalatextfield.setEditable(false);
		jp8.add(customhangolastextfield);
		jp8.add(customskalatextfield);
		add(jp8);
	    
		//utolsó panel
		JPanel jp9=new JPanel();
		jp9.add(finish);
		add(jp9);
		
		//utolsó lépések
	    setJMenuBar(mb);
	    pack();
	    setMinimumSize(getSize());	//pack összenyomja minimális méretûre az ablakot, emiatt a sor maitt annál kisebbre nem nyomható össze
	    setVisible(true);  
	    
	    //hibához JDialog létrehozása, ami exceptionnál megjelenik majd
	    jdhangolas=new JDialog(this, "Exception occured!");
		hangolasdialogpanel=new JPanel();
		jdhangolas.add(hangolasdialogpanel);
		jdhangolas.setSize(300, 100);
		
		jdskala=new JDialog(this, "Exception occured!");
		skaladialogpanel=new JPanel();
		jdskala.add(skaladialogpanel);
		jdskala.setSize(300, 100);
	}  
	
	//actionlistener a sok eventhez
	public void actionPerformed(ActionEvent ae) {
		
		//filechoosert nyit, majd szerializációval a kiválasztott fileba ment egy skálát
		if (ae.getSource()==scalesave) {
			skalatFilebaMent();
		}
		
		//filechoosert nyit, amivel kiválsztva a filet, deszerializálja
		if(ae.getSource()==scaleread) {
			skalatFilebolBeolvas();	
		}
		
		//filechoosert nyit, majd szerializációval a kiválasztott fileba menti a fretboardot
		if (ae.getSource()==tuningsave) {
			hangolastFilebaMent();
			
		}
		
		//filechoosert nyit, majd deszerializálja a fileba mentett fretboard objektumot
		if (ae.getSource()==tuningread) {
			hangolastFilebolBeolvas();
		}
		
		//beállítja a hangszertípusnak a kiválasztott hangszert
		if(ae.getSource()==jcinstrument) {
			hangszertipus=jcinstrument.getItemAt(jcinstrument.getSelectedIndex());
		}
		
		//beállítja a Hang statikus metódusán keresztül a flat vagy sharpot
		if(ae.getSource()==jcflatorsharp) {
			flatorSharpotKezel();
		}
		
		//beolvassa a hangolástípust, ami ha custom, szerkeszthetõvé teszi a beolvasáshoz használt textfieldet
		if(ae.getSource()==jc) {
			hangolastipustKezel();
		}
		
		//beolvassa a skálatípust, ami ha custom, szerkeszthetõvé teszi a beolvasáshoz használt textfieldet
		if (ae.getSource()==jc2) {
			skalatipustKezel();
		}
		
		//elvégzi az utolsó beolvasásokat, és megnyitja a Fretboardgraphics osztály által létrehozott grafikát
		if (ae.getSource()==finish) {
			hurszam=(Integer)hurszambeolvaso.getValue();
			fretszam=(Integer)fretszambeolvaso.getValue();
			
			skalatVeglegesit();
			hangolastVeglegesit();
			
			if (volthangolasexception==false && voltskalaexception==false) {
				fret.nullaz();
				fret.keres(s);
				Fretboardgraphics m=new Fretboardgraphics(fret);
			      JFrame f=new JFrame("Visualized scale");  
			      f.add(m);
			      f.setSize((fretszam+1)*40+30,500);
			      f.setResizable(false);
			      f.setVisible(true);
			}
			
		}
		
	}
	
	private void skalatFilebaMent() {
		try {
			JFileChooser fc=new JFileChooser();
			int filereturn=fc.showSaveDialog(scalesave);
			if (filereturn==JFileChooser.APPROVE_OPTION) {
				File fajl=fc.getSelectedFile();
				FileOutputStream fout = new FileOutputStream(fajl);
				ObjectOutputStream out=new ObjectOutputStream(fout);
				if (customskalagombkint) {
					String text=customskalatextfield.getText();  
				    String hangok[]=text.split("\\s");  
				    Hang[] skalahangok = new Hang[hangok.length];
				    for (int i=0; i<hangok.length; ++i){
				    	skalahangok[i]=new Hang(hangok[i]);
				    }
				    s=new Skala(skalahangok);
				}
				else {
					String kezdohang=skalakezdo.getText();
					s = new Skala(new Hang(kezdohang), skalatipus, 7);
				}
				out.writeObject(s);
				out.flush();
				out.close();
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void skalatFilebolBeolvas() {
		try{  
			  JFileChooser fc=new JFileChooser();
			  int filereturn=fc.showOpenDialog(scaleread);
			  if (filereturn==JFileChooser.APPROVE_OPTION) {
				  File fajl=fc.getSelectedFile();
				  ObjectInputStream in=new ObjectInputStream(new FileInputStream(fajl));  
				  beolvasottskala=(Skala)in.readObject();
				  in.close();
				  jc2.setSelectedIndex(8);
				  skalatipus=jc2.getItemAt(jc2.getSelectedIndex());
				  voltskalaexception=false;
			  }
			  }catch(Exception ex){
				  voltskalaexception=true;
				  ex.printStackTrace();
			  } 
	}
	
	private void hangolastFilebaMent() {
		try {
			JFileChooser fc=new JFileChooser();
			int filereturn=fc.showSaveDialog(tuningsave);
			if (filereturn==JFileChooser.APPROVE_OPTION) {
				File fajl=fc.getSelectedFile();
				FileOutputStream fout = new FileOutputStream(fajl);
				ObjectOutputStream out=new ObjectOutputStream(fout);
				if (customhangolasgombkint) {
					hurszam=(Integer)hurszambeolvaso.getValue();
					fretszam=(Integer)fretszambeolvaso.getValue();
					hangszertipus=jcinstrument.getItemAt(jcinstrument.getSelectedIndex());
					String text=customhangolastextfield.getText();
					String hangok[]=text.split("\\s");
					hurszam=hangok.length;
					Hur[] hangolas=new Hur[hurszam];
					for (int i=0; i<hurszam; ++i){
				    	hangolas[i]=new Hur(new Hang(hangok[i]), fretszam);
				    }
					fret=new Fretboard(hangolas, fretszam);
					fret.nullaz();
			} else {
				String legmelyebbhur=hangolaskezdo.getText();
				hurszam=(Integer)hurszambeolvaso.getValue();
				fretszam=(Integer)fretszambeolvaso.getValue();
				hangszertipus=jcinstrument.getItemAt(jcinstrument.getSelectedIndex());
				fret = new Fretboard(new Hang(legmelyebbhur), hangolastipus, hurszam, hangszertipus, fretszam);
			}
			out.writeObject(fret);
			out.flush();
			out.close();
			}
			
		}catch(Exception ex) {
			volthangolasexception=true;
			ex.printStackTrace();
		}
	}
	
	private void hangolastFilebolBeolvas() {
		try{  
			  JFileChooser fc=new JFileChooser();
			  int filereturn=fc.showOpenDialog(tuningread);
			  if (filereturn==JFileChooser.APPROVE_OPTION) {
				  File fajl=fc.getSelectedFile();
				  String path=fajl.getPath();
				  ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));
				  beolvasottfretboard=(Fretboard)in.readObject();
				  in.close();
				  jc.setSelectedIndex(3);
				  hangolastipus=jc.getItemAt(jc.getSelectedIndex());
				  hurszambeolvaso.setValue(Integer.valueOf(beolvasottfretboard.getHurszam()));
				  fretszambeolvaso.setValue(Integer.valueOf(beolvasottfretboard.getFretszam()));
				  if (beolvasottfretboard.getHangszer().equals("mindegy")==false) {
					  if (beolvasottfretboard.getHangszer().equals("Guitar")) {
						  jcinstrument.setSelectedIndex(0);
					  }else {
						  jcinstrument.setSelectedIndex(1);
						  }
				  }
				  volthangolasexception=false;
			  }
		}
		catch(Exception ex) {
			volthangolasexception=true;
			ex.printStackTrace();
			  }
	}
	
	private void flatorSharpotKezel() {
		if (jcflatorsharp.getSelectedIndex()==0) {
			Hang.flatInstead(false);
		}
		else {
			Hang.flatInstead(true);
		}
	}
	  
	private void hangolastipustKezel() {
		hangolastipus=jc.getItemAt(jc.getSelectedIndex());
		if (hangolastipus.equals("custom")==false) {
			if (customhangolasgombkint) {
				customhangolasgombkint=false;
				customhangolastextfield.setEditable(false);
			}
		}
		else {
			customhangolasgombkint=true;
			customhangolastextfield.setEditable(true);			
		}
	}
	
	private void skalatipustKezel() {
		skalatipus=jc2.getItemAt(jc2.getSelectedIndex());
		if (skalatipus.equals("custom")==false) {
			if (customskalagombkint) {
				customskalagombkint=false;
				customskalatextfield.setEditable(false);
			}
		}
		else {
			customskalagombkint=true;
			customskalatextfield.setEditable(true);
		}
	}
	
	private void hangolastVeglegesit() {
		if (hangolastipus.equals("custom")==false) {
			if (hangolastipus.equals("read from file")) {
				fret=beolvasottfretboard;
				volthangolasexception=false;
			}else {
				try{
					String legmelyebbhur=hangolaskezdo.getText();
					fret = new Fretboard(new Hang(legmelyebbhur), hangolastipus, hurszam, hangszertipus, fretszam);
					volthangolasexception=false;
				}catch (HangException ex) {
					volthangolasexception=true;
					hangolasdialogpanel.removeAll();
					hangolasdialogpanel.add(new JLabel("Tuning exception:"+ex.getHiba()) , BorderLayout.CENTER);
					jdhangolas.setVisible(true);
					ex.printStackTrace();
				}
			}
			
		}
		
		
		
		if (customhangolasgombkint) {
			try{
				String text=customhangolastextfield.getText();
				String hangok[]=text.split("\\s");
				hurszam=hangok.length;
				Hur[] hangolas=new Hur[hurszam];
				for (int i=0; i<hurszam; ++i){
			    	hangolas[i]=new Hur(new Hang(hangok[i]), fretszam);
			    }
				fret=new Fretboard(hangolas, fretszam);
				fret.nullaz();
				fret.keres(s);
				volthangolasexception=false;
			}catch (HangException ex) {
				volthangolasexception=true;
				hangolasdialogpanel.removeAll();
				hangolasdialogpanel.add(new JLabel("Tuning exception:"+ex.getHiba()) , BorderLayout.CENTER);
				jdhangolas.setVisible(true); 
				ex.printStackTrace();
			}
		}
	}
	
	private void skalatVeglegesit() {
		if (skalatipus.equals("custom")==false) {
			if (skalatipus.equals("read from file")) {
				s=beolvasottskala;
			}else {
				try {
				String kezdohang=skalakezdo.getText();
				s = new Skala(new Hang(kezdohang), skalatipus, 7);
				voltskalaexception=false;
				}catch (HangException ex) {
					voltskalaexception=true;
					skaladialogpanel.removeAll();
					skaladialogpanel.add(new JLabel("Scale exception:"+ex.getHiba()), BorderLayout.CENTER);
					jdskala.setVisible(true); 
					ex.printStackTrace();
				}
			}	
		}
		
		if (customskalagombkint) {
			try{
				String text=customskalatextfield.getText();  
			    String hangok[]=text.split("\\s");  
			    Hang[] skalahangok = new Hang[hangok.length];
			    for (int i=0; i<hangok.length; ++i){
			    	skalahangok[i]=new Hang(hangok[i]);
			    }
			    s=new Skala(skalahangok);
			    voltskalaexception=false;
			}catch (HangException ex) {
				voltskalaexception=true;
				skaladialogpanel.removeAll();
				skaladialogpanel.add(new JLabel("Scale exception:"+ex.getHiba()), BorderLayout.CENTER);
				jdskala.setVisible(true); 
				ex.printStackTrace();
			}
		}
	}
}
