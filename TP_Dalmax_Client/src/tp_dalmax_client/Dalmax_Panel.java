package tp_dalmax_client;

import java.awt.GridLayout;
import javax.swing.JPanel;


public class Dalmax_Panel extends JPanel{
    
    
    public Dalmax_Panel(Grid G){
       
        setLayout(new GridLayout(8,8));
 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               
               add(G.Grid_Case[i][j]);
                
            }
        }
        
        
        
    }


    
}
