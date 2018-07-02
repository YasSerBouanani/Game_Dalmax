package tp_dalmax_client;

import javax.swing.JOptionPane;

public class Client_Reception extends Thread{
    
    String message_recu;
    Scene client;
    int x;
    int y;
    Case c;
    public Client_Reception(Scene client){
        this.client = client;
    }
    
    @Override
    public void run(){
       
        try {
            while (true) {                
               
                message_recu = (String)client.reader.readLine();
                
                System.out.println(message_recu);
               
                char Char = message_recu.charAt(0); 
                
                switch (Char){
                    
                    
                    case 'K':
                         x = Integer.parseInt(Character.toString(message_recu.charAt(1)));
                         y = Integer.parseInt(Character.toString(message_recu.charAt(3)));
                
                         c = Grid.Grid_Case[x][y];
                       
                         new Play_Clients().More_Kill(c);
                        
                          
                    break;
                    
                    case 'A':
                         x = Integer.parseInt(Character.toString(message_recu.charAt(1)));
                         y = Integer.parseInt(Character.toString(message_recu.charAt(3)));
                
                         c = Grid.Grid_Case[x][y];
                       
                        if (Play_Clients.turn.equals("1")) {
                            new Player_1(c);
                            new Play_Clients().Dalmax(c);
                            
                            Play_Clients.kill = false;
                            new Play_Clients().Reset(Grid.Grid_Case);
                            new Play_Clients().Revalidate_and_repaint();
                        }else if (Play_Clients.turn.equals("2")){
                            new Player_2(c);
                            new Play_Clients().Dalmax(c);
                            
                            Play_Clients.kill = false;
                             new Play_Clients().Reset(Grid.Grid_Case);
                            new Play_Clients().Revalidate_and_repaint();
                        
                        }
  
                    break;
                    
                    case 'D':
                         x = Integer.parseInt(Character.toString(message_recu.charAt(1)));
                         y = Integer.parseInt(Character.toString(message_recu.charAt(3)));
                
                         c = Grid.Grid_Case[x][y];
                       
                         if (Play_Clients.turn.equals("1")) {
                             
                            new Player_1(c,"dam");
                            Play_Clients.kill = false;
                            new Play_Clients().Reset(Grid.Grid_Case);

                        }else if (Play_Clients.turn.equals("2")){
                            
                            new Player_2(c,"dam");
                            Play_Clients.kill = false;
                             new Play_Clients().Reset(Grid.Grid_Case);
                        
                        }
                        
                        
                    break;
                    
                    case 'R':
                        
                         x = Integer.parseInt(Character.toString(message_recu.charAt(1)));
                         y = Integer.parseInt(Character.toString(message_recu.charAt(3)));
                
                         c = Grid.Grid_Case[x][y];
                       
                         new Play_Clients().Remove(c);
                        
                    break;
                    
                    
                    case 'T':
                       
                       Play_Clients.turn = Character.toString(message_recu.charAt(1));
                        
                    break;
                    
                    case 'W':
                       
                         new Fram_End(Scene.name+",you Win !!").show();
                        
                    break;
                    
                    case 'L':
                       
                        new Fram_End(Scene.name+",you Lose !!").show();
                        
                    break;
                    
                    
                    default :
                     
                       Play_Clients.nember=message_recu; 
                       
                         
                }
                
                 
               
            }
        } catch (Exception e) {
            System.out.println("Error Receive!!");
        }
        
    }
}
