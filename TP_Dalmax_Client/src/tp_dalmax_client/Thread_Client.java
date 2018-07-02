package tp_dalmax_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Thread_Client extends Thread {
    
    Socket socket ;
    BufferedReader reader;
    PrintWriter writer ;
    
    public Thread_Client(){
      
    }
     
                           
    @Override
    public void run(){
        Connect();
        Receive_output_input();
       // new Client_Reception().start();
        
       
        
    }
    
    public void Connect(){
        try { 
            
            socket = new Socket("localhost",2018);
            System.out.println("Connection Accepted");

            
        } catch (IOException ex) {
            System.out.println("Connection Eroor!!");
        }
        
    }
    
    public void Receive_output_input(){
         try {
                             
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Succes");

            
        } catch (Exception e) {
            System.out.println("Receive output input echec");
        }
    }
    
    public void Send(String message){
        if (writer!=null) {
             writer.println(message);
            writer.flush();
        }else{
            System.out.println("writer is null");
        }
       
    }
}
