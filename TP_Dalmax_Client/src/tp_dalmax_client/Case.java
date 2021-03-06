package tp_dalmax_client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Case extends JPanel implements MouseListener{
    
    int posX,posY;
    boolean acpt = true;
    int player;
    boolean busy=false;
    boolean select = false;
    boolean dalmax = false;
    boolean exist_kills= false;
    boolean killer = false;
    boolean click = false;
    public int winner = 0;
 
    public Scene client;
    
    public Case(int posx,int posy,Scene client){
        
        this.client = client;
        
        setLayout(new BorderLayout());
        this.posX = posx;
        this.posY = posy;
        Build_Dalmax(this,posX,posY);
        Add_Piece(this, posX, posY);
      
        
    }
    
  
    
   
    
    /*******************************Functions**************************************/
    
    
    public void Build_Dalmax(Case c,int posx,int posy){
        
        if (posx%2==0) {
            if (posy%2==0) {
                 setBackground(Color.GRAY);
                 c.addMouseListener(this);
                 
            }else{
                 //setBackground(new Color(139,69,19));
                 setBackground(new Color(0,0,31));
                 c.acpt=false;  
                 
            }
           
        }else{
            if (posy%2==0) {
                 //setBackground(new Color(139,69,19));
                 setBackground(new Color(0,0,31));
                 c.acpt=false;
            }else{
                 setBackground(Color.GRAY);
                 c.addMouseListener(this);
            }
        }
    }
    
     public void Add_Piece(Case c,int i,int j){
         
           /* if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
					new Player_1(c);
				}
				else{
					new Player_2(c);
				}
            }*/
            if (i%2==0) {
                if (j%2==0 && i<3) {
                     
                   new Player_1(c);

                }

            }else{
                
                if (j%2!=0 && i<3) {
                    new Player_1(c);

                }
            }
            
             if (i%2==0) {
                if (j%2==0 && i>4 && i<8) {
                     
                   new Player_2(c);

                }

            }else{
                
                if (j%2!=0  && i>4 && i<8) {
                    new Player_2(c);

                }
            }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
        if (Play_Clients.turn.equals(Play_Clients.nember)) {
            
            client.Send("C"+this.posX+","+this.posY);
            
            new Play_Clients().Game(this);
            
        }else{
            JOptionPane.showMessageDialog(null,"Not your Turn !!");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
        
    
}
