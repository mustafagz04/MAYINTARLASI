import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Kutular extends JButton{
	 int bm,tik;  
	 int count;
	boolean isMine;
  boolean isaret;
	
	public Kutular(int bm, int tik) {
		this.bm = bm;
		this.tik = tik;
		isaret = false;
	}

  public void toggle(){
	  isaret = !isaret;
    if(isaret){
      this.setIcon(new ImageIcon("bayrak.png"));
    }else{
      this.setIcon(null);
    }
  }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getR() {
		return bm;
	}

	public void setR(int bm) {
		this.bm = bm;
	}

	public int getC() {
		return tik;
	}

	public void setC(int tik) {
		this.tik = tik;
	}
	
	
	
	
}
