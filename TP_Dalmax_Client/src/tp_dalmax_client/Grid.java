
package tp_dalmax_client;


public class Grid{
    
    public static Case Grid_Case [][] = new Case[8][8];
   
    Scene Client;
    public Grid(Scene client){
        
        Add_Cases();
        this.Client = client;

    }
    
 
    
    public void Add_Cases(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                 
                 Grid_Case[i][j] = new Case(i,j,Client);   
                 
            }
        }
    }
    
   
   
    
    
}
