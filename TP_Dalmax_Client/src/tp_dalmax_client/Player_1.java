package tp_dalmax_client;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player_1 {
    
    JLabel l = new JLabel(new ImageIcon(getClass().getResource("icons/pp1.png")));
    JLabel d = new JLabel(new ImageIcon(getClass().getResource("icons/dd1.png")));
   
   
    public Player_1(Case c){ 
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=1;
        c.busy=true;
        
    }
    
    public Player_1(Case c, String dam){ 
        
        c.add(d);
        c.revalidate();
        c.repaint();
        c.player=1;
        c.busy=true;
        c.dalmax = true;
        
    }
    
    
  
    
}
