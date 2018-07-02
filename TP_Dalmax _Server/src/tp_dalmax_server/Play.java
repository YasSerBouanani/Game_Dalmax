package tp_dalmax_server;

import java.awt.Color;
import javax.swing.JOptionPane;

public class Play {
    
    public String turn = "2";
    public int dir = 0;
    public boolean kill = false;
    public boolean click = false;
    public Case last_move = null;
    public Case killer_case = null;
    public Case killed = null;
    public Case [] killed_dam = new Case[6];
    public boolean Exist_kill = false;
   
    Thread_Server server;
    public  int index;
        
    public Play( Thread_Server server,int index){
        
        this.index = index;
        this.server = server;
        
    }
    
    /*************************************************************FUNCTIONS********************************************************************/

    
    public void Select(Case c){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
              if ( server.list_scene.get(index).Grid_Case[i][j]!=c && server.list_scene.get(index).Grid_Case[i][j].acpt && !server.list_scene.get(index).Grid_Case[i][j].exist_kills) {

                         server.list_scene.get(index).Grid_Case[i][j].setBackground(Color.GRAY);
                         server.list_scene.get(index).Grid_Case[i][j].select = false;

                    }
              
            }
         }

               if (c.player==1 && turn.equals("1")) {
                   c.setBackground(Color.CYAN);
                   c.select=true;
                   View_possibilities(c);
                  

               }else if(c.player==2 && turn.equals("2")){
                   c.setBackground(Color.ORANGE);
                   c.select=true;
                   View_possibilities(c);
                   
               }
                  
                
         
          
    
        
    
    }

    public void View_possibilities(Case c){
        
        int i = c.posX;
        int j = c.posY;
        
        
        if (c.dalmax) {
            
            Dam_View_Possibility(c);
            
        }else{
   
        if(c.player==2){
            
                if(i-1>=0 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i-1][j-1].busy){

                    server.list_scene.get(index).Grid_Case[i-1][j-1].setBackground(Color.ORANGE);
                    server.list_scene.get(index).Grid_Case[i-1][j-1].select = true;
                   
                    
                }
               
                if(i-1>=0 && j+1<8 && !server.list_scene.get(index).Grid_Case[i-1][j+1].busy){

                    server.list_scene.get(index).Grid_Case[i-1][j+1].setBackground(Color.ORANGE);
                    server.list_scene.get(index).Grid_Case[i-1][j+1].select = true;
                   
                }
                
        }else if(c.player==1){
            
                if(i+1<8 && j+1<8 && !server.list_scene.get(index).Grid_Case[i+1][j+1].busy){

                    server.list_scene.get(index).Grid_Case[i+1][j+1].setBackground(Color.CYAN);
                    server.list_scene.get(index).Grid_Case[i+1][j+1].select = true;
                   
                }
                
                if(i+1<8 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i+1][j-1].busy){

                    server.list_scene.get(index).Grid_Case[i+1][j-1].setBackground(Color.CYAN);
                    server.list_scene.get(index).Grid_Case[i+1][j-1].select = true;
                    

                }

          }
      }
    }
    
    /************************************************************************************************************************/
    
    public void Move(Case c){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (turn.equals("2")) {
                    if (server.list_scene.get(index).Grid_Case[i][j].player==2 && server.list_scene.get(index).Grid_Case[i][j].select) {
                        
                        if (server.list_scene.get(index).Grid_Case[i][j].dalmax) {
                           new Player_2(c,"dam",server,index);
                           server.list_scene.get(index).Grid_Case[i][j].dalmax=false;
                        }else{
                           new Player_2(c,server,index);
                            Dalmax(c);
                        }
                       
                        last_move = c;
                        Remove(server.list_scene.get(index).Grid_Case[i][j]);
                        Revalidate_and_repaint();
                        turn = "1";
                        Find_Winner(1);
                        Reset_Winner();
                        Last_Move();
                        Reset(server.list_scene.get(index).Grid_Case);
                        Auto_Select("1");
                        killer_case = null; 
                    }
                     
                    }else if (turn.equals("1")){
                            
                      if (server.list_scene.get(index).Grid_Case[i][j].player==1 && server.list_scene.get(index).Grid_Case[i][j].select) { 
                        if (server.list_scene.get(index).Grid_Case[i][j].dalmax) {
                             new Player_1(c,"dam",server,index);
                             server.list_scene.get(index).Grid_Case[i][j].dalmax=false;
                        }else{  
                        new Player_1(c,server,index);
                         Dalmax(c);
                        }
                       
                        last_move = c;
                        Remove(server.list_scene.get(index).Grid_Case[i][j]);
                        Revalidate_and_repaint();
                        turn = "2";
                        Find_Winner(2);
                        Reset_Winner();
                        Last_Move();
                        Reset(server.list_scene.get(index).Grid_Case);
                        Auto_Select("2");
                        killer_case = null;
                     

                    }

                }
            }
        }
                                   
        }
        
 /****************************************************************************************************************/
    
    public void  Auto_Select(String p){
        
    for (int i = 0; i < 8; i++) {
       for (int j = 0; j < 8; j++) {
           
        if (server.list_scene.get(index).Grid_Case[i][j].dalmax) {
            
            Dam_Selection_kills(server.list_scene.get(index).Grid_Case[i][j]);
      

        }else{

 
        if (p.equals("2")) {

          if(i-1>=0 && j-1>=0 && server.list_scene.get(index).Grid_Case[i-1][j-1].player==1 && i-2>=0 && j-2>=0 && !server.list_scene.get(index).Grid_Case[i-2][j-2].busy && server.list_scene.get(index).Grid_Case[i][j].player==2){
              server.list_scene.get(index).Grid_Case[i-2][j-2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i-2][j-2].setBackground(Color.ORANGE);
              server.Send_to_p2(index,"K"+i+","+j);
             

          }

          if(i-1>=0 && j+1<8 && server.list_scene.get(index).Grid_Case[i-1][j+1].player==1 && i-2>=0 && j+2<8 && !server.list_scene.get(index).Grid_Case[i-2][j+2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==2 ){
              server.list_scene.get(index).Grid_Case[i-2][j+2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i-2][j+2].setBackground(Color.ORANGE);
              server.Send_to_p2(index,"K"+i+","+j);
             

          }

        }else if (p.equals("1")) {

          if(i+1<8 && j+1<8 && server.list_scene.get(index).Grid_Case[i+1][j+1].player==2 && i+2<8 && j+2<8 && !server.list_scene.get(index).Grid_Case[i+2][j+2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==1 ){
              server.list_scene.get(index).Grid_Case[i+2][j+2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              server.list_scene.get(index).Grid_Case[i+2][j+2].setBackground(Color.CYAN);
              kill = true;
              server.Send_to_p1(index,"K"+i+","+j);
              

          }

          if(i+1<8 && j-1>=0 && server.list_scene.get(index).Grid_Case[i+1][j-1].player==2 && i+2<8 && j-2>=0 && !server.list_scene.get(index).Grid_Case[i+2][j-2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==1){
              server.list_scene.get(index).Grid_Case[i+2][j-2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i+2][j-2].setBackground(Color.CYAN);
              server.Send_to_p1(index,"K"+i+","+j);

          }
        }
      }
     }
    }
        
         
      
       
 }
    
/****************************************************************************************************/
    public void Reset(Case c [][]){
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                     c[i][j].killer = false;
                     c[i][j].exist_kills = false;
                     
                     
  
            }
        }
     
    }
    
 /********************************************************************************************************/
    
    public void Kill(Case c){
        if (c.exist_kills) {

                if (killer_case.dalmax) {
                    
                    Dam_kill(c);

                }else{
            
                    if (turn.equals("1")) {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                int x = Math.abs(c.posX-i);
                                int y = Math.abs(c.posY-j);
                                if (server.list_scene.get(index).Grid_Case[i][j]==killer_case && x==2 && y==2) {
                                   new Player_1(c,server,index);
                                    Dalmax(c);
                                    last_move = c;                                  
                                    kill = false;
                                    Serche_and_Remove(c,server.list_scene.get(index).Grid_Case[i][j]);
                                    Remove(server.list_scene.get(index).Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(server.list_scene.get(index).Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    More_Kill(c);
                                    
                                    if (!Exist_kill) {
                                        Reset(server.list_scene.get(index).Grid_Case);
                                        click = false;
                                        turn = "2";
                                        Find_Winner(2);
                                        Reset_Winner();
                                        
                                        if (!c.dalmax) {
                                             Auto_Select("2");
                                        }
                                                                  
                                    }else{
                                        killer_case = c;
                                    }
                                    
                                }
                            }
                        }
                       
                        

                    }else if (turn.equals("2")){
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                int x = Math.abs(c.posX-i);
                                int y = Math.abs(c.posY-j);
                                if (server.list_scene.get(index).Grid_Case[i][j]==killer_case && x==2 && y==2) {
                                    new Player_2(c,server,index);
                                    Dalmax(c);
                                    last_move = c;                                   
                                    kill = false;
                                    Serche_and_Remove(c,server.list_scene.get(index).Grid_Case[i][j]); 
                                    Remove(server.list_scene.get(index).Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(server.list_scene.get(index).Grid_Case);
                                    killer_case = null;
                                    Last_Move();
                                    Exist_kill = false;
                                    More_Kill(c);
                                    
                                    if (!Exist_kill) {
                                        Reset(server.list_scene.get(index).Grid_Case);
                                        click = false;
                                        turn = "1";
                                        Find_Winner(1);
                                        Reset_Winner();
                                        
                                        if (!c.dalmax) {
                                             Auto_Select("1");
                                        }
                                                                  
                                    }else{
                                        killer_case = c;
                                    }
                                    
                                }
                            }
                        }
                       
                        
                        
                    }
                    
                  
               }
        }
    }
   /***********************************************************************************************************************/
    public void Dam_kill(Case c){
        
         if (turn.equals("1")) {
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                int x = Math.abs(c.posX-i);
                                int y = Math.abs(c.posY-j);
                                if (server.list_scene.get(index).Grid_Case[i][j]==killer_case && x == y  &&  server.list_scene.get(index).Grid_Case[i][j]!=c) {
                                    new Player_1(c,"dam",server,index);
                                    last_move = c;
                                    kill = false;
                                    Dam_Serche_and_Remove(c,server.list_scene.get(index).Grid_Case[i][j]);
                                    Remove(server.list_scene.get(index).Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(server.list_scene.get(index).Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    dir = Break_Dam(killer_case, c);
                                    Dam_Selection_kills(c);
                                    
                                    if (!Exist_kill) {
                                        Reset(server.list_scene.get(index).Grid_Case);
                                        click = false;
                                        turn = "2";
                                        Find_Winner(2);
                                        Reset_Winner();
                                        dir = 0;
                                        Auto_Select("2");
                                                                  
                                    }else{
                                        killer_case = c;
                                    }
                                   

                                }
                            }
                        }
                       
                        

                    }else if (turn.equals("2")){
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                int x = Math.abs(c.posX-i);
                                int y = Math.abs(c.posY-j);
                                if (server.list_scene.get(index).Grid_Case[i][j]==killer_case && x==y &&  server.list_scene.get(index).Grid_Case[i][j]!=c) {
                                    new Player_2(c,"dam",server,index);
                                    last_move = c;
                                    kill = false;
                                    Dam_Serche_and_Remove(c,server.list_scene.get(index).Grid_Case[i][j]); 
                                    Remove(server.list_scene.get(index).Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(server.list_scene.get(index).Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    dir = Break_Dam(killer_case, c);
                                    Dam_Selection_kills(c);
                                    
                                    if (!Exist_kill) {                                      
                                        Reset(server.list_scene.get(index).Grid_Case);
                                        click = false;
                                        turn = "1";
                                        Find_Winner(1);
                                        dir = 0;
                                        Reset_Winner();
                                        Auto_Select("1");
                                       
                                        
                                    }else{
                                       killer_case = c;  
                                    }
                                   
                                   
                                    
                                }
                            }
                        }
        
                    }
    }    
    
   /***********************************************************************************************************************/ 
    public void Remove(Case c){
        
        c.busy = false;
        c.dalmax = false;
        c.player = 0;
        c.removeAll();
        if (turn.equals("2")) {
             server.Send_to_p1(index,"R"+c.posX+","+c.posY);
        }else if (turn.equals("1")){
             server.Send_to_p2(index,"R"+c.posX+","+c.posY);

        }
       
        
    }
    
  /*************************************************************************************************************************/
    public void Revalidate_and_repaint(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                server.list_scene.get(index).Grid_Case[i][j].revalidate();
                server.list_scene.get(index).Grid_Case[i][j].repaint();
                
                 
            }
        }
   
    }
    
   /******************************************************************************************************************/
    
    public void Serche_and_Remove(Case c1,Case c2){
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = Math.abs(c1.posX-i);
                int y = Math.abs(c1.posY-j);
                int xx = Math.abs(c2.posX-i);
                int yy = Math.abs(c2.posY-j);
                if (x==1&& y==1 && xx==1 && yy==1) {
                    Remove(server.list_scene.get(index).Grid_Case[i][j]);
                    
                if (turn.equals("2")) {
                     server.Send_to_p1(index,"R"+i+","+j);
                }else if (turn.equals("1")){
                     server.Send_to_p2(index,"R"+i+","+j);

                }


                }
            }
        }
            
    }
    
   /******************************************************************************************************************/
    public void Dam_Serche_and_Remove(Case c1,Case c2){
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = Math.abs(c1.posX-i);
                int y = Math.abs(c1.posY-j);
                int xx = Math.abs(c2.posX-i);
                int yy = Math.abs(c2.posY-j);
                int xxx =  Math.abs(c1.posX-c2.posX);
                int yyy =  Math.abs(c1.posY-c2.posY);
                
                for (int k = 0; k < 6; k++) {
                    if (server.list_scene.get(index).Grid_Case[i][j] == killed_dam[k] &&  x==y && xx == yy && x<xxx && y<yyy) {
                     
                       Remove(server.list_scene.get(index).Grid_Case[i][j]);
                        
                       if (turn.equals("2")) {
                           
                            server.Send_to_p1(index,"R"+i+","+j);
                            
                       }else if (turn.equals("1")){
                           
                            server.Send_to_p2(index,"R"+i+","+j);

                       }
                        
                    }
                }
                
                
 
            }   
        }
            
    }
    
    
   /*****************************************************************************************************************/

   public void Last_Move(){
       
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               
               if (server.list_scene.get(index).Grid_Case[i][j]!=last_move && server.list_scene.get(index).Grid_Case[i][j].acpt) {
                   
                   server.list_scene.get(index).Grid_Case[i][j].setBackground(Color.GRAY);
                   server.list_scene.get(index).Grid_Case[i][j].select = false;
                   
               }
           }
       }
   }
  /*******************************************************************************************************************/
   
   public void Click_Killer_Case( Case c){
            
            if (c.killer) {
                
                if (turn.equals("1")) {
                    
                    c.setBackground(Color.CYAN);
                    killer_case = c;
                    click = true;
                    
                }else if (turn.equals("2")){
                    
                    c.setBackground(Color.ORANGE);
                    killer_case = c;
                    click =  true;
                            
                }
                
                
            }
 
   }
   

    
/***************************************************************************************************************************/
   

 /**************************************************************************************************************/

 public void Dalmax(Case c){
     
     if (turn.equals("1")) {
         if (c.posX==7 && c.player==1) {
             new Player_1(c,"dam",server,index);
            }
         
     }else if (turn.equals("2")){
         if (c.posX==0 && c.player==2) {
             new Player_2(c,"dam",server,index);
         }
     }
 }
 
 
 /****************************************************************************************************************/

 
/***************************************************************************************************************/
 public void Dam_Selection_kills(Case c){
            
            int i = c.posX;
            int j = c.posY;
            
             if (turn.equals("1")) {
                 
                server.Send_to_p1(index,"K"+i+","+j);
             
             }else if (turn.equals("2")){
                server.Send_to_p2(index,"K"+i+","+j);

            }
             
            
            int m = 0;
     
            int x =1;
            int y =1;
            
            boolean b = false;
            
             while (i-x>=0 && j-y>=0 && server.list_scene.get(index).Grid_Case[i-x][j-y].player!=server.list_scene.get(index).Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dir != -1) {
                 
                if (server.list_scene.get(index).Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (!server.list_scene.get(index).Grid_Case[i-x-1][j-y-1].busy) {
                        b = true;
                        killed = server.list_scene.get(index).Grid_Case[i-x][j-y];
                        killed_dam[m] = killed;m++;
                        Exist_kill = true;
                        break;
                        
                     }
                 }
                }
                 
                 x++;
                 y++;
             }
             
             x = 1;
             y = 1;
            
            while (b && i-x>=0 && j-y>=0 && server.list_scene.get(index).Grid_Case[i-x][j-y].player!=server.list_scene.get(index).Grid_Case[i][j].player) {
                
                if (server.list_scene.get(index).Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (server.list_scene.get(index).Grid_Case[i-x-1][j-y-1].busy) {
                       // Exist_kill = false;
                        break;
                     }
                 }
                }
            
                if (i-x!=0 && j-y!=0 || !server.list_scene.get(index).Grid_Case[i-x][j-y].busy) {
                    
                        if (server.list_scene.get(index).Grid_Case[i][j].player==1) {
                                server.list_scene.get(index).Grid_Case[i-x][j-y].setBackground(Color.CYAN);
                        }else if (server.list_scene.get(index).Grid_Case[i][j].player==2){
                                server.list_scene.get(index).Grid_Case[i-x][j-y].setBackground(Color.ORANGE);
                        }
                        
                         if (Math.abs(server.list_scene.get(index).Grid_Case[i-x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               server.list_scene.get(index).Grid_Case[i-x][j-y].exist_kills=true;
                               server.list_scene.get(index).Grid_Case[i][j].killer = true;
                               kill = true;
                         }
   
                           

                        }
                 x++;y++;
                }
            x =1;
            y =1;
            b = false;
            
             while (i-x>=0 && j+y<8 && server.list_scene.get(index).Grid_Case[i-x][j+y].player!=server.list_scene.get(index).Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dir != -11) {
                 
                if (server.list_scene.get(index).Grid_Case[i-x][j+y].busy) {
                 if (i-x>=1 && j+y<7) {
                     if (!server.list_scene.get(index).Grid_Case[i-x-1][j+y+1].busy) {
                        b = true;
                        killed = server.list_scene.get(index).Grid_Case[i-x][j+y];
                        killed_dam[m] = killed;m++;
                        Exist_kill = true;
                        break;
                        
                     }
                 }
                }
                 
                 x++;
                 y++;
             }
                
                x = 1;
                y = 1;
            while (b && i-x>=0 && j+y<8 && server.list_scene.get(index).Grid_Case[i-x][j+y].player!=server.list_scene.get(index).Grid_Case[i][j].player) {
                
                if (server.list_scene.get(index).Grid_Case[i-x][j+y].busy) {
                   if (i-x>=1 && j+y<7) {
                       if (server.list_scene.get(index).Grid_Case[i-x-1][j+y+1].busy) {
                          //Exist_kill = false;
                          break;
                       }
                   }
                }

              
                if (i-x!=0 && j+y!=7 || !server.list_scene.get(index).Grid_Case[i-x][j+y].busy) {

                    if (server.list_scene.get(index).Grid_Case[i][j].player==1) {
                       server.list_scene.get(index).Grid_Case[i-x][j+y].setBackground(Color.CYAN);
                    }else if (server.list_scene.get(index).Grid_Case[i][j].player==2){
                       server.list_scene.get(index).Grid_Case[i-x][j+y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(server.list_scene.get(index).Grid_Case[i-x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               server.list_scene.get(index).Grid_Case[i-x][j+y].exist_kills=true;
                               server.list_scene.get(index).Grid_Case[i][j].killer = true;
                               kill = true;
                     }
                            

                }
                 x++;y++;
            }
            
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j-y>=0 && server.list_scene.get(index).Grid_Case[i+x][j-y].player!=server.list_scene.get(index).Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dir != 11) {
                 
                if (server.list_scene.get(index).Grid_Case[i+x][j-y].busy) {
                 if (i+x<7 && j-y>=1) {
                     if (!server.list_scene.get(index).Grid_Case[i+x+1][j-y-1].busy) {
                        b = true;
                        killed = server.list_scene.get(index).Grid_Case[i+x][j-y];
                        killed_dam[m] = killed;m++;
                        Exist_kill = true;
                        break;
                        
                     }
                 }
                }
                 
                 x++;
                 y++;
             }
                
                x = 1;
                y = 1;
            
            while (b && i+x<8 && j-y>=0 && server.list_scene.get(index).Grid_Case[i+x][j-y].player!=server.list_scene.get(index).Grid_Case[i][j].player) {
                
                if (server.list_scene.get(index).Grid_Case[i+x][j-y].busy) {
                   if (i+x<7 && j-y>0) {
                       if (server.list_scene.get(index).Grid_Case[i+x+1][j-y-1].busy) {
                          // Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j-y!=0 || !server.list_scene.get(index).Grid_Case[i+x][j-y].busy) {
                    if (server.list_scene.get(index).Grid_Case[i][j].player==1) {
                       server.list_scene.get(index).Grid_Case[i+x][j-y].setBackground(Color.CYAN);
                    }else if (server.list_scene.get(index).Grid_Case[i][j].player==2){
                       server.list_scene.get(index).Grid_Case[i+x][j-y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(server.list_scene.get(index).Grid_Case[i+x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               server.list_scene.get(index).Grid_Case[i+x][j-y].exist_kills=true;
                               server.list_scene.get(index).Grid_Case[i][j].killer = true;
                               kill = true;
                    }
                }
                x++;y++;
            }
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j+y<8 && server.list_scene.get(index).Grid_Case[i+x][j+y].player!=server.list_scene.get(index).Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dir != 1) {
                 
                if (server.list_scene.get(index).Grid_Case[i+x][j+y].busy) {
                 if (i+x<7 && j+y<7) {
                     if (!server.list_scene.get(index).Grid_Case[i+x+1][j+y+1].busy) {
                        b = true;
                        killed = server.list_scene.get(index).Grid_Case[i+x][j+y];
                        killed_dam[m] = killed;m++;
                        Exist_kill = true;
                        break;
                        
                     }
                 }
                }
                 
                 x++;
                 y++;
             }
                
                x = 1;
                y = 1;
            
            
            while (b && i+x<8 && j+y<8 && server.list_scene.get(index).Grid_Case[i+x][j+y].player!=server.list_scene.get(index).Grid_Case[i][j].player) {
                
                if (server.list_scene.get(index).Grid_Case[i+x][j+y].busy) {
                   if (i+x<7 && j+y<7) {
                       if (server.list_scene.get(index).Grid_Case[i+x+1][j+y+1].busy) {
                          //Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j+y!=7 || !server.list_scene.get(index).Grid_Case[i+x][j+y].busy) {
                    if (server.list_scene.get(index).Grid_Case[i][j].player==1) {
                       server.list_scene.get(index).Grid_Case[i+x][j+y].setBackground(Color.CYAN);
                    }else if (server.list_scene.get(index).Grid_Case[i][j].player==2){
                       server.list_scene.get(index).Grid_Case[i+x][j+y].setBackground(Color.ORANGE);
                    }
                        
                     if (Math.abs(server.list_scene.get(index).Grid_Case[i+x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               server.list_scene.get(index).Grid_Case[i+x][j+y].exist_kills=true;
                               server.list_scene.get(index).Grid_Case[i][j].killer = true;
                               kill = true;
                         }
                }
                x++;y++;
            }
     
     
 }
/***************************************************************************************************************/
 public void Dam_View_Possibility(Case c ){
        
            int i = c.posX;
            int j = c.posY;
     
            int x =1;
            int y =1;
            
            while (i-x>=0 && j-y>=0 && server.list_scene.get(index).Grid_Case[i-x][j-y].player!=c.player) {
                
                if (server.list_scene.get(index).Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (server.list_scene.get(index).Grid_Case[i-x-1][j-y-1].busy) {
                        break;
                     }
                 }
                }
            
                if (i-x!=0 && j-y!=0 || !server.list_scene.get(index).Grid_Case[i-x][j-y].busy) {
                    
                        if (c.player==1) {
                                server.list_scene.get(index).Grid_Case[i-x][j-y].setBackground(Color.CYAN);
                        }else if (c.player==2){
                                server.list_scene.get(index).Grid_Case[i-x][j-y].setBackground(Color.ORANGE);
                        }
                            server.list_scene.get(index).Grid_Case[i-x][j-y].select = true;

                        }
                 x++;y++;
                }
            
            x =1;
            y =1;
            
            while (i-x>=0 && j+y<8 && server.list_scene.get(index).Grid_Case[i-x][j+y].player!=c.player) {
                
                if (server.list_scene.get(index).Grid_Case[i-x][j+y].busy) {
                   if (i-x>=1 && j+y<7) {
                       if (server.list_scene.get(index).Grid_Case[i-x-1][j+y+1].busy) {
                          break;
                       }
                   }
                }

              
                if (i-x!=0 && j+y!=7 || !server.list_scene.get(index).Grid_Case[i-x][j+y].busy) {

                    if (c.player==1) {
                       server.list_scene.get(index).Grid_Case[i-x][j+y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       server.list_scene.get(index).Grid_Case[i-x][j+y].setBackground(Color.ORANGE);
                    }
                    server.list_scene.get(index).Grid_Case[i-x][j+y].select = true;

                }
                 x++;y++;
            }
            
            
            x =1;
            y =1;
            
            while (i+x<8&& j-y>=0 && server.list_scene.get(index).Grid_Case[i+x][j-y].player!=c.player) {
                
                if (server.list_scene.get(index).Grid_Case[i+x][j-y].busy) {
                   if (i+x<7 && j-y>0) {
                       if (server.list_scene.get(index).Grid_Case[i+x+1][j-y-1].busy) {
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j-y!=0 || !server.list_scene.get(index).Grid_Case[i+x][j-y].busy) {
                    if (c.player==1) {
                       server.list_scene.get(index).Grid_Case[i+x][j-y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       server.list_scene.get(index).Grid_Case[i+x][j-y].setBackground(Color.ORANGE);
                    }
                server.list_scene.get(index).Grid_Case[i+x][j-y].select = true;
                }
                x++;y++;
            }
            
            x =1;
            y =1;
            
            while (i+x<8 && j+y<8 && server.list_scene.get(index).Grid_Case[i+x][j+y].player!=c.player) {
                
                if (server.list_scene.get(index).Grid_Case[i+x][j+y].busy) {
                   if (i+x<7 && j+y<7) {
                       if (server.list_scene.get(index).Grid_Case[i+x+1][j+y+1].busy) {
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j+y!=7 || !server.list_scene.get(index).Grid_Case[i+x][j+y].busy) {
                    if (c.player==1) {
                       server.list_scene.get(index).Grid_Case[i+x][j+y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       server.list_scene.get(index).Grid_Case[i+x][j+y].setBackground(Color.ORANGE);
                    }
                server.list_scene.get(index).Grid_Case[i+x][j+y].select = true;
                }
                x++;y++;
            }
     
 }
/***************************************************************************************************************/
public void Find_Winner(int player){
        
    String s = " ";
    String ss = " ";
    boolean dam = false;
    
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            
            
            if (server.list_scene.get(index).Grid_Case[i][j].player == player){
                
                if (server.list_scene.get(index).Grid_Case[i][j].dalmax) {
                   
                        dam  = true;
                       /* if(i+1<8 && j+1<8 && !server.list_scene.get(index).Grid_Case[i+1][j+1].busy){
                            server.list_scene.get(index).Grid_Case[i+1][j+1].winner = player;
                        }

                        if(i+1<8 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i+1][j-1].busy){
                            server.list_scene.get(index).Grid_Case[i+1][j-1].winner = player;
                          
                        }
                        
                         if(i-1>=0 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i-1][j-1].busy){
                            server.list_scene.get(index).Grid_Case[i-1][j-1].winner = player;
                         
                        }

                        if(i-1>=0 && j+1<8 && !server.list_scene.get(index).Grid_Case[i-1][j+1].busy){
                            server.list_scene.get(index).Grid_Case[i-1][j+1].winner = player;
                          
                        }*/

                        
                    
                }else{
                    
                    if (player==1) {
                                               
                        if(i+1<8 && j+1<8 && !server.list_scene.get(index).Grid_Case[i+1][j+1].busy && server.list_scene.get(index).Grid_Case[i+1][j+1].acpt && server.list_scene.get(index).Grid_Case[i][j].busy){
                            server.list_scene.get(index).Grid_Case[i+1][j+1].winner = 1;
                            s = "1";
                        }

                        if(i+1<8 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i+1][j-1].busy && server.list_scene.get(index).Grid_Case[i+1][j-1].acpt && server.list_scene.get(index).Grid_Case[i][j].busy){
                            server.list_scene.get(index).Grid_Case[i+1][j-1].winner = 1;
                            s = "1";
                        }
                        
                        
                    }else if(player==2){
                       
                        if(i-1>=0 && j-1>=0 && !server.list_scene.get(index).Grid_Case[i-1][j-1].busy && server.list_scene.get(index).Grid_Case[i-1][j-1].acpt && server.list_scene.get(index).Grid_Case[i][j].busy){
                            server.list_scene.get(index).Grid_Case[i-1][j-1].winner = 2;
                            ss = "2";
                        }

                        if(i-1>=0 && j+1<8 && !server.list_scene.get(index).Grid_Case[i-1][j+1].busy && server.list_scene.get(index).Grid_Case[i-1][j+1].acpt && server.list_scene.get(index).Grid_Case[i][j].busy){
                            server.list_scene.get(index).Grid_Case[i-1][j+1].winner = 2;
                           ss = "2";
                        }
                   }
                    
                }
                
            }
                
            
        }
    
    }
           
    
    
    if (player == 1 &&  s.equals(" ") && !dam) {
      
        server.Send_to_p1(index,"L");
        server.Send_to_p2(index,"W");
      
        
    }else if ( player == 2 && ss.equals(" ") && !dam ){
      
        server.Send_to_p1(index,"W");
        server.Send_to_p2(index,"L");
        
    }
    
 }
 
/***************************************************************************************************************/
public void Reset_Winner(){
    
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            if (server.list_scene.get(index).Grid_Case[i][j].winner!=0) {
                server.list_scene.get(index).Grid_Case[i][j].winner = 0;
            }
        }
    }
}

/***************************************************************************************************************/
public void  More_Kill(Case c){
        
        int i = c.posX;
        int j = c.posY;
    
        if (c.player == 2) {
           
          if(i-1>=0 && j-1>=0 && server.list_scene.get(index).Grid_Case[i-1][j-1].player==1 && i-2>=0 && j-2>=0 && !server.list_scene.get(index).Grid_Case[i-2][j-2].busy && server.list_scene.get(index).Grid_Case[i][j].player==2){
              server.list_scene.get(index).Grid_Case[i-2][j-2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i-2][j-2].setBackground(Color.ORANGE);
              Exist_kill = true;
           

          }

          if(i-1>=0 && j+1<8 && server.list_scene.get(index).Grid_Case[i-1][j+1].player==1 && i-2>=0 && j+2<8 && !server.list_scene.get(index).Grid_Case[i-2][j+2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==2 ){
              server.list_scene.get(index).Grid_Case[i-2][j+2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i-2][j+2].setBackground(Color.ORANGE);
              Exist_kill = true;
             
          }

        }else if (c.player == 1) {

          if(i+1<8 && j+1<8 && server.list_scene.get(index).Grid_Case[i+1][j+1].player==2 && i+2<8 && j+2<8 && !server.list_scene.get(index).Grid_Case[i+2][j+2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==1 ){
              server.list_scene.get(index).Grid_Case[i+2][j+2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              server.list_scene.get(index).Grid_Case[i+2][j+2].setBackground(Color.CYAN);
              kill = true;
              Exist_kill = true;

          }

          if(i+1<8 && j-1>=0 && server.list_scene.get(index).Grid_Case[i+1][j-1].player==2 && i+2<8 && j-2>=0 && !server.list_scene.get(index).Grid_Case[i+2][j-2].busy &&  server.list_scene.get(index).Grid_Case[i][j].player==1){
              server.list_scene.get(index).Grid_Case[i+2][j-2].exist_kills=true;
              server.list_scene.get(index).Grid_Case[i][j].killer = true;
              kill = true;
              server.list_scene.get(index).Grid_Case[i+2][j-2].setBackground(Color.CYAN);
              Exist_kill = true;

          }
        }
      
    

}


public void Game(Case c){
    
     if (!kill) {
                 
        if (c.select && !c.busy){

               Move(c);
               server.Send_to_p1(index,"T"+turn);
               server.Send_to_p2(index,"T"+turn);


        }else{

               Select(c);
        }    

        }else{

            Click_Killer_Case(c);

            if (click) {

                Kill(c);
                server.Send_to_p1(index,"T"+turn);
                server.Send_to_p2(index,"T"+turn);
            }
      }
     
             
             
    }


public int Break_Dam(Case c1 , Case c2){
    
    int x = c1.posX - c2.posX;
    int y = c1.posY - c2.posY;
    int d = 0 ;
    if (x>0) {
        if (y<0) {
            d =  11;
        }else{
            d =  1;
        }
    }else{
        if (y<0) {
            d = -1;
        }else{
            d = -11;
        }
    }
    return d;
}
}//class
