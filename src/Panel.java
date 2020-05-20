import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel implements MouseListener{
	
	
	Kutular[][] board = new Kutular[8][10]; 
	
	//10 mayin �retme y�ntemi
	public void generateMines() {
		
		int count = 0;
		
		
		while(count < 10) {
			
			
			
			// rastgele bir konum olu�turma (sat�r, s�tun)
			int sat�r = (int)(Math.random()*board.length);
			int sutun = (int)(Math.random()*board[0].length);
			
			
			while(board[sat�r][sutun].isMine) {
				sat�r = (int)(Math.random()*board.length);
				sutun = (int)(Math.random()*board[0].length);
			}

			board[sat�r][sutun].isMine= true;
			count++;
			
		}
		
	}



	

	public void updateCount(int bm, int tik) {
		
		
		if(!board[bm][tik].isMine) return; 
		
		for(int sat�r = bm-1; sat�r <= bm+1; sat�r++) {
			for(int sutun = tik-1; sutun <= tik+1; sutun++) {
				
				try {
					board[sat�r][sutun].count++;
				}catch(Exception e) {
				//Hic bir sey yapma sinirin disina ciktin.
				}
			}
		}
		
	}
	

  public void checkWon(){
   


  }



	
	// her kutu ba��na d��en may�n say�s�
	public void countMines() {
		
		for(int sat�r = 0; sat�r < board.length; sat�r++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
				
				updateCount(sat�r,sutun);
			}
		}	
		
	}
	
	
	public void displayMines() {
		for(int sat�r = 0; sat�r < board.length; sat�r++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
					if(board[sat�r][sutun].isMine) {
						board[sat�r][sutun].setIcon(new ImageIcon("may�n.png"));
            	board[sat�r][sutun].setText("*");
					}else{
           board[sat�r][sutun].setText(board[sat�r][sutun].getCount()+"");
          }
					
			}
		}
		repaint();
	
	}
	

	public Panel() {
		
		JFrame frame = new JFrame("MAY�NTARLAS�!!");
		frame.setSize(750,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		frame.setLayout(new GridLayout(8,10));
		
		
	
		for(int sat�r = 0; sat�r < board.length; sat�r++) {
			for(int sutun = 0; sutun < board[0].length; sutun++) {
				Kutular t = new Kutular(sat�r, sutun);		
				t.addMouseListener(this);		
				frame.add(t); 		
				board[sat�r][sutun] = t;
				
			}
		}		
		
		
		generateMines();
		countMines();
	
		frame.setVisible(true);
	}

  public void gameOver(){
    System.out.println("OYUNB�TT�");
    for(int bm = 0; bm < board.length; bm++){
      for(int tik = 0; tik < board[0].length; tik++){
        if(board[bm][tik].isMine){
          board[bm][tik].setIcon(new ImageIcon("may�n.png"));
        }
      }
    }
  }


  public void reveal(int bm, int tik){
    System.out.println("A�IK!");
   

    if(bm<0 || bm>=board.length || tik<0|| tik>=board[0].length ||
    board[bm][tik].getText().length()>0 || board[bm][tik].isEnabled()==false){
      return;
    }else if(board[bm][tik].count!=0){
      board[bm][tik].setText(board[bm][tik].count+"");
      board[bm][tik].setEnabled(false);
    }else{
      board[bm][tik].setEnabled(false);
      reveal(bm-1,tik);//kuzeyinde
      reveal(bm+1,tik);//g�neyinde	
      
      reveal(bm,tik-1);//do�da
      reveal(bm,tik+1);//bat�da
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
			
		
			System.out.println("SA�");
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