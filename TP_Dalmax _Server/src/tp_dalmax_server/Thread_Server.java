package tp_dalmax_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;




public class Thread_Server{
    
    static ServerSocket server ;
    static Socket accept_connection;
    static ArrayList<Socket> socket_p1 = new ArrayList<>();
    static ArrayList<Socket> socket_p2 = new ArrayList<>();
    static ArrayList<BufferedReader> reader_p1 = new ArrayList<>();
    static ArrayList<PrintWriter> writer_p1 = new ArrayList<>();
    static ArrayList<BufferedReader> reader_p2 = new ArrayList<>();
    static ArrayList<PrintWriter> writer_p2 = new ArrayList<>();
    static ArrayList<Scene> list_scene = new ArrayList<>();
    static ArrayList<Play> PLAY = new ArrayList<>();
   

    static int i = 0;
    
    public Thread_Server(){
      
    }
    
   
    public static void main(String args[]){
        
        try{
           
          server = new ServerSocket(2018);
           
        } catch (IOException ex) {
            
           System.out.println("Server Is't working");
        }
        
        while (true) {  
            
            if (i == 0) {
                list_scene.add(new Scene(i));
                list_scene.get(0).setVisible(true);
            }
            
            Connect();
         
            PLAY.add(new Play(new Thread_Server(),i));
            
            if (i!=0) list_scene.add(new Scene(i));
            
            i++;    
          
        }
        
    }
    
    public static void Connect(){
        
         try {
            
            System.out.println("Server Is Waiting...");
            
            socket_p1.add(server.accept());
           
            System.out.println("player_1 is connected");
            
            Receive_output_input_P1(i);
           
            socket_p2.add(server.accept());
          
            System.out.println("player_2 is connected");
            
            Receive_output_input_P2(i);
           
            
        } catch (IOException ex) {
            System.out.println("Connection Eroor!!");
        }
    }
    
    
    public static void Receive_output_input_P1(int i){
        
         try {
                           
                reader_p1.add(new BufferedReader(new InputStreamReader(socket_p1.get(i).getInputStream())));
                writer_p1.add(new PrintWriter(socket_p1.get(i).getOutputStream()));
                System.out.println("Receive output input p1"); 
                
                Send_to_p1(i,"1");
               
                new Server_Reception_From_P1(new Thread_Server(),i).start();
           
             
            
        } catch (Exception e) {
            System.out.println("Receive output input echec");
        }
    }
    public static void Receive_output_input_P2(int i){
        
         try {
                           
                   
                reader_p2.add(new BufferedReader(new InputStreamReader(socket_p2.get(i).getInputStream())));
                writer_p2.add(new PrintWriter(socket_p2.get(i).getOutputStream()));
                System.out.println("Receive output input p2");
                
                Send_to_p2(i,"2");
               
                new Server_Reception_From_P2(new Thread_Server(),i).start();
               
           
             
            
        } catch (Exception e) {
            System.out.println("Receive output input echec");
        }
    }
    
    
    public static void Send_to_p1(int i,String message){
        
        writer_p1.get(i).println(message);
        writer_p1.get(i).flush();
    }
    
    public static void Send_to_p2(int i,String message){
        
        writer_p2.get(i).println(message);
        writer_p2.get(i).flush();
    }
    
    
    
    /*********************************************************************************/
   
   
      
}
