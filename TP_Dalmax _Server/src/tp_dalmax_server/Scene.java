package tp_dalmax_server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Scene extends javax.swing.JFrame {
    
    public  Case Grid_Case [][] = new Case[8][8];
   
    public  JPanel panel_list;
    public  ArrayList<JPanel> list = new ArrayList<>();
   
    int i ;
    int j ;
    
    public Scene(int i) {
        
        this.i = i ;
        
        Theme();
        this.i = i;
        this.Grid_Case =  new Grid().Grid_Case;
        initComponents();
        super.setTitle("SERVER");
        setResizable(false);
        setLocation(200,70);
        setSize(900,625);
        setBackground(new Color(0,0,30));
        JPanel Main_Panel = new JPanel(new BorderLayout());
        Main_Panel.setBounds(0,0,900,600);
        Main_Panel.setBackground(new Color(0,0,30));
        add(Main_Panel);
        JPanel Dalmax = new Dalmax_Panel(this);
        Dalmax.setBounds(0, 0, 700, 600);
        Dalmax.setBackground(new Color(0,0,30));
        Main_Panel.add(Dalmax,BorderLayout.CENTER);
        
        JPanel Menu = new JPanel(new BorderLayout());
       
        JPanel Menu2 = new JPanel(new BorderLayout());
        panel_list = new JPanel(new GridLayout(10,1));
        panel_list.setBackground(new Color(50,50,50));
        Menu.setBackground(Color.BLACK);
        Menu.setBounds(0,0,700,50);
        JButton match = new JButton(i+1+"");match.setBackground(Color.BLACK);match.setForeground(Color.ORANGE);match.setFont(new Font("",1,20));match.setBorder(null);
        ImageIcon n = new ImageIcon(getClass().getResource("icons/next.png"));
        ImageIcon p = new ImageIcon(getClass().getResource("icons/prev.png"));
        JButton next = new JButton();next.setBackground(Color.BLACK);next.setForeground(Color.WHITE);next.setBorder(null);
        JButton prev = new JButton();prev.setBackground(Color.BLACK);prev.setForeground(Color.WHITE);prev.setBorder(null);
        
        JButton VS = new JButton("VS");VS.setBackground(Color.BLACK);VS.setForeground(Color.ORANGE);
        VS.setBorder(null);VS.setFont(new Font("",1,20));VS.setBorder(null);VS.setBounds(0,0,10,10);
        
        
        next.setIcon(n);
        prev.setIcon(p);
        Menu2.add(next,BorderLayout.EAST);
        Menu2.add(prev,BorderLayout.WEST);
        Menu2.add(match,BorderLayout.CENTER);
        Menu.add(Menu2,BorderLayout.SOUTH);
        Main_Panel.add(Menu,BorderLayout.WEST);
        
        Menu.add(panel_list,BorderLayout.CENTER);
 
        next.addActionListener(e->{
            if (i<Thread_Server.list_scene.size()-1) {
                
                for (int j = 0; j <  Thread_Server.list_scene.get(0).list.size(); j++) {
                             Thread_Server.list_scene.get(i+1).panel_list.add(Thread_Server.list_scene.get(0).list.get(j));
                        }

                        Thread_Server.list_scene.get(i+1).panel_list.revalidate();
                        Thread_Server.list_scene.get(i+1).panel_list.repaint();
                
                
                Thread_Server.list_scene.get(i).dispose();
                Thread_Server.list_scene.get(i+1).setVisible(true);
                match.setText((i+1)+"");
            }
        });
        prev.addActionListener(e->{
            if (i>0) {
                
                 for (int j = 0; j <  Thread_Server.list_scene.get(0).list.size(); j++) {
                             Thread_Server.list_scene.get(i-1).panel_list.add(Thread_Server.list_scene.get(0).list.get(j));
                        }

                        Thread_Server.list_scene.get(i-1).panel_list.revalidate();
                        Thread_Server.list_scene.get(i-1).panel_list.repaint();
                
                
                Thread_Server.list_scene.get(i).dispose();
                Thread_Server.list_scene.get(i-1).show();
                match.setText((i+1)+"");
            }
        });
       
    
      
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   public void Theme(){
        /***************************************************************Theme_Noire****************************************/
            
       try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            
        } catch (ClassNotFoundException ex) {
           
        } catch (InstantiationException ex) {
            
        } catch (IllegalAccessException ex) {
           
        } catch (UnsupportedLookAndFeelException ex) {
        }
       
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

