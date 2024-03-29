/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage.Game_mixedstrategy;

import MainPackage.MainWindow;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ranjith
 */
public class display extends javax.swing.JFrame {

    private static final long serialVersionUID = 9220036263068464736L;
    static display display_window;
    static boolean single_instance = false;
    Graph ob = new Graph();
    /**
     * Creates new form display
     */
    public display() {
        initComponents();
        display.setText(implementMixedStrategy.msg);
        implementMixedStrategy.msg = "<html><u>SOLUTION FOR THE GIVEN GAME IS</u><br>";
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        graph = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOLUTION");
        setResizable(false);

        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Solution Window",TitledBorder.LEFT,TitledBorder.CENTER));

        display.setEditable(false);
        display.setContentType("text/html"); // NOI18N
        display.setFocusable(false);
        jScrollPane1.setViewportView(display);

        jButton3.setText("Home");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton4.setText("New");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jButton2.setText("About Mixed Strategy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        graph.setText("Graph");
        graph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphActionPerformed(evt);
            }
        });
        graph.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                graphKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(61, 61, 61)
                        .addComponent(jButton2)
                        .addGap(56, 56, 56)
                        .addComponent(graph)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void graphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphActionPerformed
        // TODO add your handling code here:
        try
        {
            if(!single_instance)
            {
                single_instance = true;
                if(!(MixedStrategyInput.row_value == 2 && MixedStrategyInput.col_value == 2))
                    ob.setVisible(true);
                else
                    JOptionPane.showMessageDialog(null, "Bar-Graph not required because there is only one sub-game.", "ALERT", INFORMATION_MESSAGE);
            }
            else if(!ob.isVisible())
            {
                ob = null;
                single_instance = false;
                ob = new Graph();
                if(!(MixedStrategyInput.row_value == 2 && MixedStrategyInput.col_value == 2))
                    ob.setVisible(true);
                else
                    JOptionPane.showMessageDialog(null, "Bar-Graph not required because there is only one sub-game.", "ALERT", INFORMATION_MESSAGE); 
                single_instance = true;
            }
        }
        catch(Exception e)
        {
        }
    }//GEN-LAST:event_graphActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
                                    String source = System.getProperty("user.dir");
                                    File file = new File(source+"\\html\\game.html");
                                    URI uri = file.toURI();
                                    java.awt.Desktop.getDesktop().browse(uri);
                                } catch (Exception ex) {
                                }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                            display_window.dispose();
                            ob.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MixedStrategyMainFrame.start();
                                }
                            }).start();
                            display_window.dispose();
                            ob.dispose();
        }
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MixedStrategyMainFrame.start();
                                }
                            }).start();
                            display_window.dispose();
                            ob.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                            display_window.dispose();
                            ob.dispose();
        }
    }//GEN-LAST:event_jButton4KeyPressed

    private void graphKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_graphKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
         {
            try
            {
                if(!single_instance)
                {
                    single_instance = true;
                    if(!(MixedStrategyInput.row_value == 2 && MixedStrategyInput.col_value == 2))
                        ob.setVisible(true);
                    else
                        JOptionPane.showMessageDialog(null, "Bar-Graph not required because there is only one sub-game.", "ALERT", INFORMATION_MESSAGE);
                }
                else if(!ob.isVisible())
                {
                    ob = null;
                    single_instance = false;
                    ob = new Graph();
                    if(!(MixedStrategyInput.row_value == 2 && MixedStrategyInput.col_value == 2))
                        ob.setVisible(true);
                    else
                        JOptionPane.showMessageDialog(null, "Bar-Graph not required because there is only one sub-game.", "ALERT", INFORMATION_MESSAGE); 
                    single_instance = true;
                }
            }
            catch(Exception e)
            {
            }            
        }
    }//GEN-LAST:event_graphKeyPressed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
         {
            try {
                                    String source = System.getProperty("user.dir");
                                    File file = new File(source+"\\html\\game.html");
                                    URI uri = file.toURI();
                                    java.awt.Desktop.getDesktop().browse(uri);
                                } catch (Exception ex) {
                                }
         }
    }//GEN-LAST:event_jButton2KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void start() {
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
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                display_window = new display();
                display_window.setLocationRelativeTo(null);
                display_window.setVisible(true);
                ImageIcon ico_img=new ImageIcon(MainWindow.class.getResource("bot.png"));
        try
        {
        display_window.setIconImage(ico_img.getImage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane display;
    private javax.swing.JButton graph;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
