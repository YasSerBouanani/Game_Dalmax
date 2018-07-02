package tp_dalmax_server;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player_1 {
    
    JLabel l = new JLabel(new ImageIcon(getClass().getResource("icons/pp1.png")));
    JLabel d = new JLabel(new ImageIcon(getClass().getResource("icons/dd1.png")));
    
    Thread_Server server;
    int i ;
    
    public Player_1(Case c){ 
        
      
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=1;
        c.busy=true;
        
    }
    
    
    
    public Player_1(Case c,Thread_Server server,int i){ 
        
        this.server = server;
        this.i = i;
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=1;
        c.busy=true;
        
        server.Send_to_p2(i,"A"+c.posX+","+c.posY);
        
        
    }
    
    public Player_1(Case c, String dam,Thread_Server server ,int i){ 
        
        this.server = server;
        this.i = i;
        c.add(d);
        c.revalidate();
        c.repaint();
        c.player=1;
        c.busy=true;
        c.dalmax = true;
       
        server.Send_to_p2(i,"D"+c.posX+","+c.posY);
       

        
        
    }
    
    
  
    
}
