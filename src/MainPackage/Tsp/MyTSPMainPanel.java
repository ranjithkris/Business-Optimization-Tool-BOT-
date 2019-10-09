/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage.Tsp;

import MainPackage.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ranjith
 */
public class MyTSPMainPanel extends JFrame {
     
    private static final long serialVersionUID = 9220036263068464736L;
    private JButton addCity = new JButton("Add City");
    private JButton addDistance = new JButton("Add Distance , Click on any 2 cities");
    private JButton clearAll = new JButton("Clear All");
    private JButton home = new JButton("Home");
    private JButton change_mode = new JButton("Change Input Mode");
    private JButton bAbout = new JButton("About TSP");
    private JButton bCompute = new JButton("Apply TSP"); 
    public static JTextField event = new JTextField();
	
	JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel leftPanel = new JPanel();
    CityPanel centerPanel = new CityPanel();
    
    public MyTSPMainPanel() {
        super("TSP");
        
        Dimension minimumSize = new Dimension(1000,600);
        
        GridLayout leftPanelLayout = new GridLayout(10,1);
        leftPanelLayout.setHgap(0);
        leftPanelLayout.setVgap(10);
        leftPanel.setLayout(leftPanelLayout);
        
        mainPanel.add(leftPanel,  BorderLayout.WEST);
        
        centerPanel.setBorder(
                BorderFactory.createTitledBorder("City View"));
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        centerPanel.resetEverything();
        
        leftPanel.add(addCity);
        leftPanel.add(addDistance);
        leftPanel.add(clearAll);
        leftPanel.add(home);
        leftPanel.add(change_mode);
        leftPanel.add(bAbout);
        leftPanel.add(bCompute);
 /*      
        ImageIcon pic = new ImageIcon(System.getProperty("user.dir")+"\\images\\tsp\\salesLabel.gif");
        leftPanel.add(new JLabel(pic));*/
        leftPanel.add(event);
        event.setEditable(false);
        event.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Event",TitledBorder.CENTER,TitledBorder.CENTER));
        
        addCity.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.activateAddCity();
            }
        });
        
        addDistance.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.activateAddPaths();
                addCity.setEnabled(false);
            }
        });
        
        clearAll.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addCity.setEnabled(true);
                centerPanel.resetEverything();
            }
        });
        
        home.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                dispose();
            }
        });
        
        
        bAbout.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               try {
                                    String source = System.getProperty("user.dir");
                                    File file = new File(source+"\\html\\meta.html");
                                    URI uri = file.toURI();
                                    java.awt.Desktop.getDesktop().browse(uri);
                                } catch (Exception ex) {
                                }
            }
        });
        
        change_mode.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TspMainFrame.start();
                                }
                            }).start();
                            dispose();
            }
        });
        
        
        bCompute.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                centerPanel.getDistanceMatrix();
            }
        });
       // set border for the panel
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Travelling Salesman Window",TitledBorder.LEFT,TitledBorder.CENTER));
         
        // add the panel to this frame
        add(mainPanel);
        
        setMinimumSize(minimumSize);
        pack();
        setLocationRelativeTo(null);
        ImageIcon ico_img=new ImageIcon(MainWindow.class.getResource("bot.png"));
        try
        {
            setIconImage(ico_img.getImage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
     
    public static void start() {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyTSPMainPanel().setVisible(true);
            }
        });
    }
}
