package tp_dalmax_server;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player_2 {
    
    JLabel l = new JLabel(new ImageIcon(getClass().getResource("icons/pp2.png")));
    JLabel d = new JLabel(new ImageIcon(getClass().getResource("icons/dd2.png")));
    
    Thread_Server server;
    int i ;
    
    
      public Player_2(Case c){ 
        
      
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=2;
        c.busy=true;
        
    }
    
    
    
    
    
    public Player_2(Case c,Thread_Server server,int i){
        this.i = i;
        this.server = server;
        
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=2;
        c.busy=true;
        
        server.Send_to_p1(i,"A"+c.posX+","+c.posY);
        
        
    }
    
    public Player_2(Case c, String dam,Thread_Server server,int i){
        
        this.server = server;
        this.i = i;
        c.add(d);
        c.revalidate();
        c.repaint();
        c.player=2;
        c.busy=true;
        c.dalmax = true;
        
        server.Send_to_p1(i,"D"+c.posX+","+c.posY);
       

    }
   
    
}
