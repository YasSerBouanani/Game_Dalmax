package tp_dalmax_server;


public class Grid{
    
    public  Case Grid_Case [][] = new Case[8][8];
   
    public Grid(){
       
        Add_Cases();

    }
    
 
    
    public void Add_Cases(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                 
                 Grid_Case[i][j] = new Case(i,j);   
                 
            }
        }
    }
    
   
   
    
    
}
