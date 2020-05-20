import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	
	Kutular[][] board = new Kutular[8][10]; 
	
	//10 mayin üretme yöntemi
	public void generateMines() {
		
		int count = 0;
		
		
		while(count < 10) {
			
			
			
			// rastgele bir konum oluþturma (satir, sütun)
			int satir = (int)(Math.random()*board.length);
			int sutun = (int)(Math.random()*board[0].length);
			
			
			while(board[satir][sutun].isMine) {
				satir = (int)(Math.random()*board.length);
				sutun = (int)(Math.random()*board[0].length);
			}

			board[satir][sutun].isMine= true;
			count++;
			
		}
		
	}



	

	public void updateCount(int bm, int tik) {
		
		
		if(!board[bm][tik].isMine) return; 
		
		for(int satir = bm-1; satir <= bm+1; satir++) {
			for(int sutun = tik-1; sutun <= tik+1; sutun++) {
				
				try {
					board[satir][sutun].count++;
				}catch(Exception e) {
				//Hic bir sey yapma sinirin disina ciktin.
				}
			}
		}
		
	}
	

  public void checkWon(){
   


  }



	
	// her kutu basina dusen mayin sayisi
	public void countMines() {
		
		for(int satir = 0; satir < board.length; satir++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
				
				updateCount(satir,sutun);
			}
		}	
		
	}
	
	
	public void displayMines() {
		for(int satir = 0; satir < board.length; satir++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
					if(board[satir][sutun].isMine) {
						board[satir][sutun].setIcon(new ImageIcon("mayin.png"));
            	board[satir][sutun].setText("*");
					}else{
           board[satir][sutun].setText(board[satir][sutun].getCount()+"");
          }
					
			}
		}
		repaint();
	
	}
	

	public Panel() {
		
		JFrame frame = new JFrame("MAYINTARLASI!!");
		frame.setSize(750,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		frame.setLayout(new GridLayout(8,10));
		
		
	
		for(int satir = 0; satir < board.length; satir++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
				Kutular t = new Kutular(satir, sutun);		
				t.addMouseListener(this);		
				frame.add(t); 		
				board[satir][sutun] = t;
				
			}
		}		
		
		
		generateMines();
		countMines();
	
		frame.setVisible(true);
	}

  public void gameOver(){
    System.out.println("OYUNBITTI");
    for(int bm = 0; bm < board.length; bm++){
      for(int tik = 0; tik < board[0].length; tik++){
        if(board[bm][tik].isMine){
          board[bm][tik].setIcon(new ImageIcon("mayin.png"));
        }
      }
    }
  }


  public void reveal(int bm, int tik){
    System.out.println("AÇiK!");
   

    if(bm<0 || bm>=board.length || tik<0|| tik>=board[0].length ||
    board[bm][tik].getText().length()>0 || board[bm][tik].isEnabled()==false){
      return;
    }else if(board[bm][tik].count!=0){
      board[bm][tik].setText(board[bm][tik].count+"");
      board[bm][tik].setEnabled(false);
    }else{
      board[bm][tik].setEnabled(false);
      reveal(bm-1,tik);//kuzeyinde
      reveal(bm+1,tik);//guneyinde	
      
      reveal(bm,tik-1);//doguda
      reveal(bm,tik+1);//batida
    }

  }




	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	
		if(arg0.getButton()==1) {
			System.out.println("SOL");
			Kutular t = (Kutular)(arg0.getComponent());
      if(t.isMine){
        
        displayMines();
      }else{
        reveal(t.bm, t.bm);
      }
			
		}else if(arg0.getButton()==3) {
			
		
			System.out.println("SAg");
			Kutular t = (Kutular)(arg0.getComponent());
      t.toggle();
			//t.setIcon(new ImageIcon("bayrak.png"));
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}