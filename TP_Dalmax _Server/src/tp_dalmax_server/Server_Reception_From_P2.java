package tp_dalmax_server;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;


public class Server_Reception_From_P2 extends Thread{
    
    String message_recu;
    Thread_Server server;
    
    
    public int x ;
    public int y ;
    public int i ;
    
    public Server_Reception_From_P2(Thread_Server server,int i){
        this.server = server;
        this.i = i;
        
    }
    
    @Override
    public void run(){
        
        try {
            
            while (true) {    
               
                message_recu = (String)server.reader_p2.get(i).readLine();
                System.out.println(message_recu);
                
                if (message_recu.charAt(0)=='C' && message_recu.charAt(2)==',') {
                    
                    x = Integer.parseInt(Character.toString(message_recu.charAt(1)));
                    y = Integer.parseInt(Character.toString(message_recu.charAt(3)));

                    Case c = server.list_scene.get(i).Grid_Case[x][y];

                    server.PLAY.get(i).Game(c);
                    
                }else{
                    
                   

                    JButton player = new JButton(message_recu);
                   
                    player.setBackground(Color.BLACK);
                    player.setForeground(Color.white);
                    player.setFont(new Font("",1,13));
                    player.setBorder(null);
                    
                    JButton VS = new JButton("VS");VS.setBackground(Color.BLACK);VS.setForeground(Color.ORANGE);
                    VS.setBorder(null);VS.setFont(new Font("",1,18));VS.setBorder(null);VS.setBounds(0,0,10,10);
                      
                    VS.addActionListener(ev->{
                        
                       
                            
                        for (int j = 0; j <  Thread_Server.list_scene.get(0).list.size(); j++) {
                             Thread_Server.list_scene.get(i).panel_list.add(Thread_Server.list_scene.get(0).list.get(j));
                        }

                        Thread_Server.list_scene.get(i).panel_list.revalidate();
                        Thread_Server.list_scene.get(i).panel_list.repaint();
                        
                        
                        for (int j = 0; j <  Thread_Server.list_scene.size(); j++) {
                            if (j!=i) {
                                 Thread_Server.list_scene.get(j).dispose();
                            }
                            
                        }
                        
                        Thread_Server.list_scene.get(i).show();
                    }); 
                    
                    Thread_Server.list_scene.get(0).list.get(i).add(VS);
                    Thread_Server.list_scene.get(0).list.get(i).add(player);
                     
                   
                    /*for (int k = 0; k < Thread_Server.list_scene.size(); k++) {
                        
                        if (k!=i ) {
                        for (int j = 0; j <  Thread_Server.list_scene.get(0).list.size(); j++) {
                             Thread_Server.list_scene.get(k).panel_list.add(Thread_Server.list_scene.get(0).list.get(j));
                        }

                        Thread_Server.list_scene.get(k).panel_list.revalidate();
                        Thread_Server.list_scene.get(k).panel_list.repaint();
                        }
                        
                    }*/
                    
                    
                        Thread_Server.list_scene.get(0).panel_list.revalidate();
                        Thread_Server.list_scene.get(0).panel_list.repaint();
                    
                  
                }
               
               
            }
        } catch (Exception e) {
            System.out.println("Error Receive!!");
        }
        
    }
}
