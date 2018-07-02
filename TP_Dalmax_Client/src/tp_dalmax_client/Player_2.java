package tp_dalmax_client;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player_2 {
    
    JLabel l = new JLabel(new ImageIcon(getClass().getResource("icons/pp2.png")));
    JLabel d = new JLabel(new ImageIcon(getClass().getResource("icons/dd2.png")));
   

    public Player_2(Case c){
        
        c.add(l);
        c.revalidate();
        c.repaint();
        c.player=2;
        c.busy=true;
    }
    
    public Player_2(Case c, String dam){
        
        c.add(d);
        c.revalidate();
        c.repaint();
        c.player=2;
        c.busy=true;
        c.dalmax = true;
    }
   
    
}
