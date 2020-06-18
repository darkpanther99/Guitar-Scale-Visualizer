package guitarfretboardscale;
public class HangException extends Exception{
	private String hiba;
	
	public HangException(String hiba) {
		this.hiba=hiba;	
	}
	
	public String getHiba() {
		return hiba;
	}
	
	public void printStackTrace() {
		System.out.println(hiba);
	}

}
