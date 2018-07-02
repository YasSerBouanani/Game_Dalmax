package tp_dalmax_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Scene extends javax.swing.JFrame  {
    
    static Socket socket ;
    static BufferedReader reader;
    static PrintWriter writer ;
    static String name;
  
    public Scene() {
        Theme();
        initComponents();
        super.setTitle(name);
        setLocation(200,20);
        setResizable(false);
        setSize(600,575);
        setLayout(null);
        JPanel Main_Panel = new JPanel(new BorderLayout());
        Main_Panel.setBounds(0,0,600,550);
        Main_Panel.setBackground(Color.GRAY);
        add(Main_Panel);
        JPanel p1 = new Dalmax_Panel(new Grid(this));
        JMenuBar menu = new JMenuBar();    
        Main_Panel.add(p1,BorderLayout.CENTER);
        p1.setBackground(Color.BLACK);  
        
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

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Scene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Scene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Scene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Scene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    
                    Connect();
                    Receive_output_input();
                    new Client_Reception(new Scene()).start();
                    new Scene().setVisible(true); 
                   
                    
            }
        });
    }
    
  
     public static void Connect(){
        try { 
            
            socket = new Socket("localhost",2018);
            System.out.println("Connection Accepted");

            
        } catch (IOException ex) {
            System.out.println("Connection Eroor!!");
        }
        
    }
    
    public static void Receive_output_input(){
         try {               
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Receive output input from server");
            name = JOptionPane.showInputDialog("Entre Your Name !!");
            Send(name);
           

            
        } catch (Exception e) {
            System.out.println("Receive output input echec");
        }
    }
    
    public static void Send(String message){
            writer.println(message);
            writer.flush();
       
    }
    
    
   
        /***************************************************************Theme_Noire****************************************/
       public void Theme(){ 
       try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            
        } catch (ClassNotFoundException ex) {
           
        } catch (InstantiationException ex) {
            
        } catch (IllegalAccessException ex) {
           
        } catch (UnsupportedLookAndFeelException ex) {
        }
       
   }
       
       public static void Repaint_Grid(){
           new Grid(new Scene());  
       }
       
       
       public static void Exit(){
           System.exit(0);
       }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

