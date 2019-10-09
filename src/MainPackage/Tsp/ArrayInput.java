package MainPackage.Tsp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import MainPackage.MainWindow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import javax.naming.Context;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.border.TitledBorder;

public class ArrayInput extends JFrame{
	
    private static final long serialVersionUID = 9220036263068464736L;
	
	public static int city_value;
        JFrame inputFrame = new JFrame(); 
	
	private static final int JTextFieldSize=3;
	
	 
	JButton bCompute = new JButton(("Compute"));
	static JButton bAbout = new JButton(("About TSP"));
	JButton bReset = new JButton(("Reset"));
        JButton bHome = new JButton(("Home"));
        JButton bNew = new JButton(("New"));

	static JTextField inputCost[][] = new JTextField[city_value][city_value];
	
	JPanel inputPanel = new JPanel();
        JLabel rowLabel[] = new JLabel[city_value];
        JLabel colLabel[] = new JLabel[city_value];
        JLabel city_label = new JLabel();
	
	
	public ArrayInput(int width,int height,int cityValue)
        {
            
            city_value = cityValue;
            
            inputFrame.setSize(width,height);
            inputFrame.setResizable(false);
            inputFrame.setLocationRelativeTo(null);
            inputFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            inputFrame.setTitle(("TSP"));
            ImageIcon ico_img=new ImageIcon(MainWindow.class.getResource("bot.png"));
        try
        {
            inputFrame.setIconImage(ico_img.getImage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
            ActionListener listen = new ListenForButton();
            bAbout.addActionListener(listen);
		
            FocusListener listenFocus = new ListenForFocus();
		
            inputPanel.setLayout(new GridBagLayout());
            inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Window",TitledBorder.LEFT,TitledBorder.CENTER));
				
            GridBagConstraints theLayout = new GridBagConstraints();
		
            theLayout.gridx=1;
            theLayout.gridy=1;
            theLayout.gridwidth=1;
            theLayout.gridheight=1;
            theLayout.weightx=50;
            theLayout.weighty=10;
            theLayout.insets= new Insets(5,5,5,5);
            theLayout.anchor = GridBagConstraints.CENTER;
            theLayout.fill = GridBagConstraints.HORIZONTAL;
		
            theLayout.gridwidth = 2;
	
            theLayout.gridwidth = 1;
            colLabel = new JLabel[city_value];
           
            theLayout.gridx=1;
            theLayout.gridy+=1;
            city_label = new JLabel("Cities",SwingConstants.CENTER);
            inputPanel.add(city_label,theLayout);
            theLayout.gridx+=1;
            
            
            for(int i=0;i<city_value;i++)
            {
		colLabel[i]= new JLabel(""+(i+1),SwingConstants.CENTER);
                inputPanel.add(colLabel[i],theLayout);
                theLayout.gridx+=1;
            }

            inputCost =  new JTextField[city_value][city_value];
            for(int i=0;i<city_value;i++)
            {
		for(int j=0;j<city_value;j++)
		{
                    inputCost[i][j] = new JTextField(" ");
                    inputCost[i][j].setColumns(JTextFieldSize);
                    inputCost[i][j].setHorizontalAlignment(JTextField.CENTER);
                    inputCost[i][j].addFocusListener(listenFocus);
                    inputCost[i][j].setBackground(Color.green);
                    inputCost[i][j].setText("Cost["+(i+1)+"]["+(j+1)+"]");
		}
            }
	
            theLayout.gridx=1;
            theLayout.gridy+=1;

            rowLabel = new JLabel[city_value];
            
            for(int i=0;i<city_value;i++)
            {
                
		theLayout.gridx=1;
		theLayout.gridy+=1;
                
                rowLabel[i]= new JLabel(""+(i+1),SwingConstants.CENTER);
                inputPanel.add(rowLabel[i],theLayout);
                theLayout.gridx+=1;
                
		for(int j=0;j<city_value;j++)
		{
                    inputPanel.add(inputCost[i][j],theLayout);
                    theLayout.gridx+=1;
		}
		
            }
		
            theLayout.gridy+=1;	
           
            theLayout.gridx = 1;

           
		
            theLayout.gridwidth=50;
            
            
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
            
	
            bottomPanel.add(bHome);
            bottomPanel.add(Box.createHorizontalGlue());
            bottomPanel.add(bNew);
            bottomPanel.add(Box.createHorizontalGlue());
            bottomPanel.add(bReset);
            bottomPanel.add(Box.createHorizontalGlue());
            bottomPanel.add(bAbout);
            bottomPanel.add(Box.createHorizontalGlue());
            bottomPanel.add(bCompute);
            
            theLayout.gridy+=1;
            theLayout.gridx=1;
            inputPanel.add(new JSeparator(SwingConstants.HORIZONTAL),theLayout);
            theLayout.gridy+=1;
            inputPanel.add(bottomPanel,theLayout);
            

            
            bCompute.addActionListener(new ActionListener()
    		{
                    int cost[][] = new int[100][100];
                    public void actionPerformed(ActionEvent ae)
                    {	
                        try
                        {
                            for(int i=0;i<city_value;i++)
                            {
                                for(int j=0;j<city_value;j++)
                                {
                                    cost[i][j] = Integer.parseInt(inputCost[i][j].getText());
                                }
                            }
    			
                            
                            implementTsp.compute(cost, city_value);
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,"Enter numbers","ERROR",WARNING_MESSAGE);
                        }		
                    }
    		});
            
            
            bCompute.addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) 
                {
                    int cost[][] = new int[100][100];
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        try
                        {
                            for(int i=0;i<city_value;i++)
                            {
                                for(int j=0;j<city_value;j++)
                                {
                                    cost[i][j] = Integer.parseInt(inputCost[i][j].getText());
                                }
                            }
    			
                            
                            implementTsp.compute(cost, city_value);
                        }
                        catch(Exception evt)
                        {
                            JOptionPane.showMessageDialog(null,"Enter numbers","ERROR",WARNING_MESSAGE);
                        }	
                }
            }

                @Override
                public void keyReleased(KeyEvent e) 
                {
                    
                }
            });
            
            
            bReset.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
    			{
    				for(int i=0;i<city_value;i++)
    				{
    					for(int j=0;j<city_value;j++)
    					{
    						inputCost[i][j].setEditable(true);
    						inputCost[i][j].setBackground(Color.green);
                                                inputCost[i][j].setText("Cost["+(i+1)+"]["+(j+1)+"]");
    					}
    				}
    			}
    		});
            
            
            bReset.addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) 
                {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        for(int i=0;i<city_value;i++)
    				{
    					for(int j=0;j<city_value;j++)
    					{
    						inputCost[i][j].setEditable(true);
    						inputCost[i][j].setBackground(Color.green);
                                                inputCost[i][j].setText("Cost["+(i+1)+"]["+(j+1)+"]");
    					}
    				}
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) 
                {
                    
                }
            });
            
            
            bHome.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
                        {
                            inputFrame.dispose();
                    new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                        }
    		});
            
            
            bHome.addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) 
                {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        inputFrame.dispose();
                    new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) 
                {
                    
                }
            });
            
            
            bNew.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
                        {
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TspMainFrame.start();
                                }
                            }).start();
                            inputFrame.dispose();
                            
                        }
    		});
            
            
            
            bNew.addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) 
                {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                         new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TspMainFrame.start();
                                }
                            }).start();
                            inputFrame.dispose();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) 
                {
                    
                }
            });
            

            
            bAbout.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
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
            

            bAbout.addKeyListener(new KeyListener()
            {
                @Override
                public void keyTyped(KeyEvent e) 
                {
                    
                }

                @Override
                public void keyPressed(KeyEvent e) 
                {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        try {
                                    String source = System.getProperty("user.dir");
                                    File file = new File(source+"\\html\\meta.html");
                                    URI uri = file.toURI();
                                    java.awt.Desktop.getDesktop().browse(uri);
                                } catch (Exception ex) {
                                }
                }
            }

                @Override
                public void keyReleased(KeyEvent e) 
                {
                    
                }
            });
           
	
            inputFrame.add(inputPanel);
            inputFrame.setVisible(true);
            
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class ListenForButton implements ActionListener{

		 public void actionPerformed(ActionEvent e)
                 {
			 
		 }}

	private class ListenForFocus extends FocusAdapter{

		@Override
		public void focusGained(FocusEvent e) {
			Component comp = e.getComponent();
			if(comp instanceof JTextField){
				((JTextField) comp).selectAll();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// Afmarker feltet
			Component comp = e.getComponent();
			if(comp instanceof JTextField){
				((JTextField) comp).select(0, 0);
			}
		}
	}
}
