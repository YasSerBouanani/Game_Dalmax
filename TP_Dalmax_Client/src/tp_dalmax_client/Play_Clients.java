package tp_dalmax_client;

import java.awt.Color;
import javax.swing.JOptionPane;


public class Play_Clients {
    
    public static String turn="2";
    public static int dirction  = 0;
    public static String nember;
    public static boolean kill = false;
    public static boolean click = false;
    public static Case last_move = null;
    public static Case killer_case = null;
    public static Case killed = null;
    public static Case [] killed_dam = new Case[6];
    public static boolean Exist_kill = false;
     
    public void Play(){
         
    }
    
    
    public void Select(Case c){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
              if ( Grid.Grid_Case[i][j]!=c && Grid.Grid_Case[i][j].acpt && !Grid.Grid_Case[i][j].exist_kills) {

                         Grid.Grid_Case[i][j].setBackground(Color.GRAY);
                         Grid.Grid_Case[i][j].select = false;

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
            
                if(i-1>=0 && j-1>=0 && !Grid.Grid_Case[i-1][j-1].busy){

                    Grid.Grid_Case[i-1][j-1].setBackground(Color.ORANGE);
                    Grid.Grid_Case[i-1][j-1].select = true;
                   
                    
                }
               
                if(i-1>=0 && j+1<8 && !Grid.Grid_Case[i-1][j+1].busy){

                    Grid.Grid_Case[i-1][j+1].setBackground(Color.ORANGE);
                    Grid.Grid_Case[i-1][j+1].select = true;
                   
                }
                
        }else if(c.player==1){
            
                if(i+1<8 && j+1<8 && !Grid.Grid_Case[i+1][j+1].busy){

                    Grid.Grid_Case[i+1][j+1].setBackground(Color.CYAN);
                    Grid.Grid_Case[i+1][j+1].select = true;
                   
                }
                
                if(i+1<8 && j-1>=0 && !Grid.Grid_Case[i+1][j-1].busy){

                    Grid.Grid_Case[i+1][j-1].setBackground(Color.CYAN);
                    Grid.Grid_Case[i+1][j-1].select = true;
                    

                }

            }
      }
    }
    
    /************************************************************************************************************************/
    
    public void Move(Case c){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (turn.equals("2")) {
                    if (Grid.Grid_Case[i][j].player==2 && Grid.Grid_Case[i][j].select) {
                        
                        if (Grid.Grid_Case[i][j].dalmax) {
                           new Player_2(c,"dam");
                           Grid.Grid_Case[i][j].dalmax=false;
                        }else{
                           new Player_2(c);
                            Dalmax(c);
                        }
                       
                        last_move = c;
                        Remove(Grid.Grid_Case[i][j]);
                        Revalidate_and_repaint();
                        turn = "1";
                        Last_Move();
                        Reset(Grid.Grid_Case);
                        Auto_Select("1");
                        killer_case = null; 
                    }
                     
                    }else if (turn.equals("1")){
                            
                      if (Grid.Grid_Case[i][j].player==1 && Grid.Grid_Case[i][j].select) { 
                        if (Grid.Grid_Case[i][j].dalmax) {
                             new Player_1(c,"dam");
                             Grid.Grid_Case[i][j].dalmax=false;
                        }else{  
                         new Player_1(c);
                         Dalmax(c);
                        }
                       
                        last_move = c;
                        Remove(Grid.Grid_Case[i][j]);
                        Revalidate_and_repaint();
                        turn = "2";
                        Last_Move();
                        Reset(Grid.Grid_Case);
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
           
        if (Grid.Grid_Case[i][j].dalmax) {
            
            Dam_Selection_kills(Grid.Grid_Case[i][j]);

        }else{

        if (p.equals("2")) {

          if(i-1>=0 && j-1>=0 && Grid.Grid_Case[i-1][j-1].player==1 && i-2>=0 && j-2>=0 && !Grid.Grid_Case[i-2][j-2].busy && Grid.Grid_Case[i][j].player==2){
              Grid.Grid_Case[i-2][j-2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i-2][j-2].setBackground(Color.ORANGE);
             

          }

          if(i-1>=0 && j+1<8 && Grid.Grid_Case[i-1][j+1].player==1 && i-2>=0 && j+2<8 && !Grid.Grid_Case[i-2][j+2].busy &&  Grid.Grid_Case[i][j].player==2 ){
              Grid.Grid_Case[i-2][j+2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i-2][j+2].setBackground(Color.ORANGE);
             

          }

        }else if (p.equals("1")) {

          if(i+1<8 && j+1<8 && Grid.Grid_Case[i+1][j+1].player==2 && i+2<8 && j+2<8 && !Grid.Grid_Case[i+2][j+2].busy &&  Grid.Grid_Case[i][j].player==1 ){
              Grid.Grid_Case[i+2][j+2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              Grid.Grid_Case[i+2][j+2].setBackground(Color.CYAN);
              kill = true;
              

          }

          if(i+1<8 && j-1>=0 && Grid.Grid_Case[i+1][j-1].player==2 && i+2<8 && j-2>=0 && !Grid.Grid_Case[i+2][j-2].busy &&  Grid.Grid_Case[i][j].player==1){
              Grid.Grid_Case[i+2][j-2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i+2][j-2].setBackground(Color.CYAN);
              

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
                    
                    if (c[i][j].acpt) {
                         c[i][j].killer = false;
                         c[i][j].exist_kills = false;
                         c[i][j].setBackground(Color.GRAY);
                    }
                    
  
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
                                if (Grid.Grid_Case[i][j]==killer_case && x==2 && y==2) {
                                    new Player_1(c);
                                    Dalmax(c);
                                    last_move = c;                                  
                                    kill = false;
                                    Serche_and_Remove(c,Grid.Grid_Case[i][j]);
                                    Remove(Grid.Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(Grid.Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    More_Kill(c);
                                    
                                    if (!Exist_kill) {
                                        Reset(Grid.Grid_Case);
                                        click = false;
                                        turn = "2";
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
                                if (Grid.Grid_Case[i][j]==killer_case && x==2 && y==2) {
                                    new Player_2(c);
                                    Dalmax(c);
                                    last_move = c;                                   
                                    kill = false;
                                    Serche_and_Remove(c,Grid.Grid_Case[i][j]); 
                                    Remove(Grid.Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(Grid.Grid_Case);
                                    killer_case = null;
                                    Last_Move();
                                    Exist_kill = false;
                                    More_Kill(c);
                                    
                                    if (!Exist_kill ) {
                                        Reset(Grid.Grid_Case);
                                        click = false;
                                        turn = "1";
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
                                if (Grid.Grid_Case[i][j]==killer_case && x == y  &&  Grid.Grid_Case[i][j]!=c) {
                                    new Player_1(c,"dam");
                                    last_move = c;
                                    kill = false;
                                    Dam_Serche_and_Remove(c,Grid.Grid_Case[i][j]);
                                    Remove(Grid.Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(Grid.Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    dirction =  Break_Dam(killer_case,c);
                                    Dam_Selection_kills(c);
                                    
                                    if (!Exist_kill) {
                                        Reset(Grid.Grid_Case);
                                        click = false;
                                        turn = "2";
                                        dirction = 0;
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
                                if (Grid.Grid_Case[i][j]==killer_case && x==y &&  Grid.Grid_Case[i][j]!=c) {
                                    new Player_2(c,"dam");
                                    last_move = c;
                                    kill = false;
                                    Dam_Serche_and_Remove(c,Grid.Grid_Case[i][j]); 
                                    Remove(Grid.Grid_Case[i][j]);
                                    Revalidate_and_repaint();
                                    Reset(Grid.Grid_Case);
                                    Last_Move();
                                    Exist_kill = false;
                                    dirction = Break_Dam(killer_case,c);
                                    Dam_Selection_kills(c);
                                    
                                    if (!Exist_kill) {                                      
                                        Reset(Grid.Grid_Case);
                                        click = false;
                                        turn = "1";
                                        dirction = 0;
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
        c.revalidate();
        c.repaint();
    }
    
  /*************************************************************************************************************************/
    public void Revalidate_and_repaint(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Grid.Grid_Case[i][j].revalidate();
                Grid.Grid_Case[i][j].repaint();
                
                
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
                    Remove(Grid.Grid_Case[i][j]);
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
                    if (Grid.Grid_Case[i][j] == killed_dam[k] &&  x==y && xx == yy && x<xxx && y<yyy) {
                     
                        Remove(Grid.Grid_Case[i][j]);
                    }
                }
                
                
 
            }   
        }
            
    }
    
    
   /*****************************************************************************************************************/

   public void Last_Move(){
       
       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               
               if (Grid.Grid_Case[i][j]!=last_move && Grid.Grid_Case[i][j].acpt) {
                   
                   Grid.Grid_Case[i][j].setBackground(Color.GRAY);
                   Grid.Grid_Case[i][j].select = false;
                   
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
     
     
         if (c.posX==7 && c.player==1) {
             new Player_1(c,"dam");
            }
         
     
         if (c.posX==0 && c.player==2) {
             new Player_2(c,"dam");
         }
     
 }
 
/***************************************************************************************************************/
 public void Dam_Selection_kills_2(Case c){
            
            int i = c.posX;
            int j = c.posY;
          
            
            int m = 0;
     
            int x =1;
            int y =1;
            
            boolean b = false;
            
             while (i-x>=0 && j-y>=0 && Grid.Grid_Case[i-x][j-y].player!=Grid.Grid_Case[i][j].player && nember.equals(Integer.toString(c.player)) && dirction != -1) {
                 
                if (Grid.Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (!Grid.Grid_Case[i-x-1][j-y-1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i-x][j-y];
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
            
            while (b && i-x>=0 && j-y>=0 && Grid.Grid_Case[i-x][j-y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (Grid.Grid_Case[i-x-1][j-y-1].busy) {
                        //Exist_kill = false;
                        break;
                     }
                 }
                }
            
                if (i-x!=0 && j-y!=0 || !Grid.Grid_Case[i-x][j-y].busy && Exist_kill) {
                    
                        if (Grid.Grid_Case[i][j].player==1) {
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.CYAN);
                        }else if (Grid.Grid_Case[i][j].player==2){
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.ORANGE);
                        }
                        
                         if (Math.abs(Grid.Grid_Case[i-x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i-x][j-y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }
   
                           

                        }
                 x++;y++;
                }
            x =1;
            y =1;
            b = false;
            
             while (i-x>=0 && j+y<8 && Grid.Grid_Case[i-x][j+y].player!=Grid.Grid_Case[i][j].player && nember.equals(Integer.toString(c.player)) && dirction != -11) {
                 
                if (Grid.Grid_Case[i-x][j+y].busy) {
                 if (i-x>=1 && j+y<7) {
                     if (!Grid.Grid_Case[i-x-1][j+y+1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i-x][j+y];
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
            while (b && i-x>=0 && j+y<8 && Grid.Grid_Case[i-x][j+y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i-x][j+y].busy) {
                   if (i-x>=1 && j+y<7) {
                       if (Grid.Grid_Case[i-x-1][j+y+1].busy) {
                         // Exist_kill = false;
                          break;
                       }
                   }
                }

              
                if (i-x!=0 && j+y!=7 || !Grid.Grid_Case[i-x][j+y].busy && Exist_kill) {

                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(Grid.Grid_Case[i-x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i-x][j+y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }

                }
                 x++;y++;
            }
            
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j-y>=0 && Grid.Grid_Case[i+x][j-y].player!=Grid.Grid_Case[i][j].player && nember.equals(Integer.toString(c.player)) && dirction != 11) {
                 
                if (Grid.Grid_Case[i+x][j-y].busy) {
                 if (i+x<7 && j-y>=1) {
                     if (!Grid.Grid_Case[i+x+1][j-y-1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i+x][j-y];
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
            
            while (b && i+x<8 && j-y>=0 && Grid.Grid_Case[i+x][j-y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i+x][j-y].busy) {
                   if (i+x<7 && j-y>0) {
                       if (Grid.Grid_Case[i+x+1][j-y-1].busy) {
                          // Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j-y!=0 || !Grid.Grid_Case[i+x][j-y].busy && Exist_kill) {
                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(Grid.Grid_Case[i+x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i+x][j-y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                    }
                }
                x++;y++;
            }
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j+y<8 && Grid.Grid_Case[i+x][j+y].player!=Grid.Grid_Case[i][j].player && nember.equals(Integer.toString(c.player)) && dirction != 1) {
                 
                if (Grid.Grid_Case[i+x][j+y].busy) {
                 if (i+x<7 && j+y<7) {
                     if (!Grid.Grid_Case[i+x+1][j+y+1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i+x][j+y];
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
            
            
            while (b && i+x<8 && j+y<8 && Grid.Grid_Case[i+x][j+y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i+x][j+y].busy) {
                   if (i+x<7 && j+y<7) {
                       if (Grid.Grid_Case[i+x+1][j+y+1].busy) {
                          //Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j+y!=7 || !Grid.Grid_Case[i+x][j+y].busy && Exist_kill) {
                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.ORANGE);
                    }
                        
                     if (Math.abs(Grid.Grid_Case[i+x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i+x][j+y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }
                }
                x++;y++;
            }
     
     
 }
/***************************************************************************************************************/
  public void Dam_Selection_kills(Case c){
            
            int i = c.posX;
            int j = c.posY;
            
            
            int m = 0;
     
            int x =1;
            int y =1;
            
            boolean b = false;
            
             while (i-x>=0 && j-y>=0 && Grid.Grid_Case[i-x][j-y].player!=Grid.Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dirction!=-1) {
                 
                if (Grid.Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (!Grid.Grid_Case[i-x-1][j-y-1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i-x][j-y];
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
            
            while (b && i-x>=0 && j-y>=0 && Grid.Grid_Case[i-x][j-y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (Grid.Grid_Case[i-x-1][j-y-1].busy) {
                        //Exist_kill = false;
                        break;
                     }
                 }
                }
            
                if (i-x!=0 && j-y!=0 || !Grid.Grid_Case[i-x][j-y].busy && Exist_kill) {
                    
                        if (Grid.Grid_Case[i][j].player==1) {
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.CYAN);
                        }else if (Grid.Grid_Case[i][j].player==2){
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.ORANGE);
                        }
                        
                         if (Math.abs(Grid.Grid_Case[i-x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i-x][j-y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }
   
                           

                        }
                 x++;y++;
                }
            x =1;
            y =1;
            b = false;
            
             while (i-x>=0 && j+y<8 && Grid.Grid_Case[i-x][j+y].player!=Grid.Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dirction != -11 ) {
                 
                if (Grid.Grid_Case[i-x][j+y].busy) {
                 if (i-x>=1 && j+y<7) {
                     if (!Grid.Grid_Case[i-x-1][j+y+1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i-x][j+y];
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
            while (b && i-x>=0 && j+y<8 && Grid.Grid_Case[i-x][j+y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i-x][j+y].busy) {
                   if (i-x>=1 && j+y<7) {
                       if (Grid.Grid_Case[i-x-1][j+y+1].busy) {
                          //Exist_kill = false;
                          break;
                       }
                   }
                }

              
                if (i-x!=0 && j+y!=7 || !Grid.Grid_Case[i-x][j+y].busy && Exist_kill) {

                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(Grid.Grid_Case[i-x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i-x][j+y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }

                }
                 x++;y++;
            }
            
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j-y>=0 && Grid.Grid_Case[i+x][j-y].player!=Grid.Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dirction!=11) {
                 
                if (Grid.Grid_Case[i+x][j-y].busy) {
                 if (i+x<7 && j-y>=1) {
                     if (!Grid.Grid_Case[i+x+1][j-y-1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i+x][j-y];
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
            
            while (b && i+x<8 && j-y>=0 && Grid.Grid_Case[i+x][j-y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i+x][j-y].busy) {
                   if (i+x<7 && j-y>0) {
                       if (Grid.Grid_Case[i+x+1][j-y-1].busy) {
                          // Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j-y!=0 || !Grid.Grid_Case[i+x][j-y].busy && Exist_kill) {
                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.ORANGE);
                    }
                    
                    if (Math.abs(Grid.Grid_Case[i+x][j-y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i+x][j-y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                    }
                }
                x++;y++;
            }
            
            x =1;
            y =1;
            b = false;
            
             while (i+x<8 && j+y<8 && Grid.Grid_Case[i+x][j+y].player!=Grid.Grid_Case[i][j].player && turn.equals(Integer.toString(c.player)) && dirction != 1) {
                 
                if (Grid.Grid_Case[i+x][j+y].busy) {
                 if (i+x<7 && j+y<7) {
                     if (!Grid.Grid_Case[i+x+1][j+y+1].busy) {
                        b = true;
                        killed = Grid.Grid_Case[i+x][j+y];
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
            
            
            while (b && i+x<8 && j+y<8 && Grid.Grid_Case[i+x][j+y].player!=Grid.Grid_Case[i][j].player) {
                
                if (Grid.Grid_Case[i+x][j+y].busy) {
                   if (i+x<7 && j+y<7) {
                       if (Grid.Grid_Case[i+x+1][j+y+1].busy) {
                         // Exist_kill = false;
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j+y!=7 || !Grid.Grid_Case[i+x][j+y].busy && Exist_kill) {
                    if (Grid.Grid_Case[i][j].player==1) {
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.CYAN);
                    }else if (Grid.Grid_Case[i][j].player==2){
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.ORANGE);
                    }
                        
                     if (Math.abs(Grid.Grid_Case[i+x][j+y].posX - c.posX) > Math.abs(killed.posX - c.posX)) {
                               Grid.Grid_Case[i+x][j+y].exist_kills=true;
                               Grid.Grid_Case[i][j].killer = true;
                               kill = true;
                         }
                }
                x++;y++;
            }
     
     
 }
 
/******************************************************************************************************************/
 public void Dam_View_Possibility(Case c ){
        
            int i = c.posX;
            int j = c.posY;
     
            int x =1;
            int y =1;
            
            while (i-x>=0 && j-y>=0 && Grid.Grid_Case[i-x][j-y].player!=c.player) {
                
                if (Grid.Grid_Case[i-x][j-y].busy) {
                 if (i-x>=1 && j-y>=1) {
                     if (Grid.Grid_Case[i-x-1][j-y-1].busy) {
                        break;
                     }
                 }
                }
            
                if (i-x!=0 && j-y!=0 || !Grid.Grid_Case[i-x][j-y].busy) {
                    
                        if (c.player==1) {
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.CYAN);
                        }else if (c.player==2){
                                Grid.Grid_Case[i-x][j-y].setBackground(Color.ORANGE);
                        }
                            Grid.Grid_Case[i-x][j-y].select = true;

                        }
                 x++;y++;
                }
            
            x =1;
            y =1;
            
            while (i-x>=0 && j+y<8 && Grid.Grid_Case[i-x][j+y].player!=c.player) {
                
                if (Grid.Grid_Case[i-x][j+y].busy) {
                   if (i-x>=1 && j+y<7) {
                       if (Grid.Grid_Case[i-x-1][j+y+1].busy) {
                          break;
                       }
                   }
                }

              
                if (i-x!=0 && j+y!=7 || !Grid.Grid_Case[i-x][j+y].busy) {

                    if (c.player==1) {
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       Grid.Grid_Case[i-x][j+y].setBackground(Color.ORANGE);
                    }
                    Grid.Grid_Case[i-x][j+y].select = true;

                }
                 x++;y++;
            }
            
            
            x =1;
            y =1;
            
            while (i+x<8&& j-y>=0 && Grid.Grid_Case[i+x][j-y].player!=c.player) {
                
                if (Grid.Grid_Case[i+x][j-y].busy) {
                   if (i+x<7 && j-y>0) {
                       if (Grid.Grid_Case[i+x+1][j-y-1].busy) {
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j-y!=0 || !Grid.Grid_Case[i+x][j-y].busy) {
                    if (c.player==1) {
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       Grid.Grid_Case[i+x][j-y].setBackground(Color.ORANGE);
                    }
                Grid.Grid_Case[i+x][j-y].select = true;
                }
                x++;y++;
            }
            
            x =1;
            y =1;
            
            while (i+x<8 && j+y<8 && Grid.Grid_Case[i+x][j+y].player!=c.player) {
                
                if (Grid.Grid_Case[i+x][j+y].busy) {
                   if (i+x<7 && j+y<7) {
                       if (Grid.Grid_Case[i+x+1][j+y+1].busy) {
                          break;
                       }
                   }
                }
                
                if (i+x!=7 && j+y!=7 || !Grid.Grid_Case[i+x][j+y].busy) {
                    if (c.player==1) {
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.CYAN);
                    }else if (c.player==2){
                       Grid.Grid_Case[i+x][j+y].setBackground(Color.ORANGE);
                    }
                Grid.Grid_Case[i+x][j+y].select = true;
                }
                x++;y++;
            }
     
 }


/***************************************************************************************************************/
public void  More_Kill(Case c){
     
        int i = c.posX;
        int j = c.posY;
        
        if (c.dalmax) {
            if (nember==turn) {
                Dam_Selection_kills(c);
            }else{
                Dam_Selection_kills_2(c);
            }
            
            
        }else{
    
        if (c.player == 2) {
           
          if(i-1>=0 && j-1>=0 && Grid.Grid_Case[i-1][j-1].player==1 && i-2>=0 && j-2>=0 && !Grid.Grid_Case[i-2][j-2].busy && Grid.Grid_Case[i][j].player==2){
              Grid.Grid_Case[i-2][j-2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i-2][j-2].setBackground(Color.ORANGE);
              Exist_kill = true;
           

          }

          if(i-1>=0 && j+1<8 && Grid.Grid_Case[i-1][j+1].player==1 && i-2>=0 && j+2<8 && !Grid.Grid_Case[i-2][j+2].busy &&  Grid.Grid_Case[i][j].player==2 ){
              Grid.Grid_Case[i-2][j+2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i-2][j+2].setBackground(Color.ORANGE);
              Exist_kill = true;
             
          }

        }else if (c.player == 1) {

          if(i+1<8 && j+1<8 && Grid.Grid_Case[i+1][j+1].player==2 && i+2<8 && j+2<8 && !Grid.Grid_Case[i+2][j+2].busy &&  Grid.Grid_Case[i][j].player==1 ){
              Grid.Grid_Case[i+2][j+2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              Grid.Grid_Case[i+2][j+2].setBackground(Color.CYAN);
              kill = true;
              Exist_kill = true;

          }

          if(i+1<8 && j-1>=0 && Grid.Grid_Case[i+1][j-1].player==2 && i+2<8 && j-2>=0 && !Grid.Grid_Case[i+2][j-2].busy &&  Grid.Grid_Case[i][j].player==1){
              Grid.Grid_Case[i+2][j-2].exist_kills=true;
              Grid.Grid_Case[i][j].killer = true;
              kill = true;
              Grid.Grid_Case[i+2][j-2].setBackground(Color.CYAN);
              Exist_kill = true;

          }
        }
      
    }

}


public void Game(Case c){
    
     if (!kill) {
                 
                if (c.select && !c.busy){
                    
                       Move(c);
                        
                }else{
                    
                       Select(c);
                }    
               
                }else{
                 
                     Click_Killer_Case(c);
                 
                    if (click) {
                         
                        Kill(c);   
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

