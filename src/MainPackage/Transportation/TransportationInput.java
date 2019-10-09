package MainPackage.Transportation;

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
//package transportation;

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
import javax.naming.Context;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.border.TitledBorder;

public class TransportationInput extends JFrame{
	
    private static final long serialVersionUID = 9220036263068464736L;
	public static int col_value=5;
	public static int row_value=5;
        JFrame inputFrame = new JFrame(); 
	
        
	private static final int JTextFieldSize=3;
	private static String[] dropMethods={"NWCR","LCM","VAM","MODI","COMPARE"};
	static JComboBox<String> dropDownMenu = new JComboBox<String>(dropMethods);
	 
	JButton bCompute = new JButton(("Compute"));
	static JButton bAbout = new JButton(("About Transportation"));
	JButton bReset = new JButton(("Reset"));
        JButton bHome = new JButton(("Home"));
        JButton bNew = new JButton(("New"));

	static JTextField inputCost[][] = new JTextField[row_value][col_value];
	static JTextField inputDemand[] = new JTextField[col_value];
	static JTextField inputSupply[] = new JTextField[row_value];
        static JTextField displayCost = new JTextField();
	
	JPanel inputPanel = new JPanel();
	
	
	public TransportationInput(int width,int height,int rowValue,int colValue)
        {
            
            row_value = rowValue;
            col_value = colValue;
            
            inputFrame.setSize(width,height);
            inputFrame.setResizable(false);
            inputFrame.setLocationRelativeTo(null);
            inputFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            displayCost.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            displayCost.setEditable(false);
            displayCost.setText("");
            inputFrame.setTitle(("TRANSPORTATION PROBLEM"));
		
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
            inputPanel.add(dropDownMenu,theLayout);
            dropDownMenu.setSelectedIndex(0);
            theLayout.gridx+=2;
            inputPanel.add(displayCost,theLayout);
	
            theLayout.gridwidth = 1;
            inputSupply = new JTextField[row_value];
            dropDownMenu.addActionListener(listen);
		
            for(int i=0;i<inputSupply.length;i++)
            {
		inputSupply[i]= new JTextField(" ");
		inputSupply[i].setColumns(JTextFieldSize);
		inputSupply[i].setHorizontalAlignment(JTextField.CENTER);
		inputSupply[i].addFocusListener(listenFocus);
		inputSupply[i].setBackground(Color.pink);
                inputSupply[i].setText("Supply["+(i+1)+"]");			
            }

            inputCost =  new JTextField[row_value][col_value];
            for(int i=0;i<row_value;i++)
            {
		for(int j=0;j<col_value;j++)
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

            for(int i=0;i<inputSupply.length;i++)
            {
		theLayout.gridx=1;
		theLayout.gridy+=1;
		for(int j=0;j<col_value;j++)
		{
                    inputPanel.add(inputCost[i][j],theLayout);
                    theLayout.gridx+=1;
		}
		inputPanel.add(inputSupply[i],theLayout);
            }
		
            theLayout.gridy+=1;	
            inputDemand = new JTextField[col_value];
            for(int i=0;i<inputDemand.length;i++)
            {
                inputDemand[i]= new JTextField(" ");
		inputDemand[i].setColumns(JTextFieldSize);
		inputDemand[i].setHorizontalAlignment(JTextField.CENTER);
		inputDemand[i].addFocusListener(listenFocus);
                inputDemand[i].setBackground(Color.yellow);
                inputDemand[i].setText("Demand["+(i+1)+"]");
                
            }
            theLayout.gridx = 1;

            for(int i=0;i<inputDemand.length;i++)
            {
		inputPanel.add(inputDemand[i],theLayout);
		theLayout.gridx+=1;
            }
            
           
		
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
    			public void actionPerformed(ActionEvent ae)
    			{
    				
    				
    				try
    				{
                                    int Supply_sum = 0;
                                    int Demand_sum = 0;
                                    for(int i = 0; i < row_value;i++)
                                        Supply_sum += Integer.parseInt(inputSupply[i].getText());
                                    
                                    for(int i = 0; i < col_value;i++)
                                        Demand_sum += Integer.parseInt(inputDemand[i].getText());
                                    
                                    int row = 0, col = 0;
                                    if(Demand_sum == Supply_sum)
                                    {
                                        row = row_value;
                                        col = col_value;
                                    }
                                    else if(Supply_sum > Demand_sum)
                                    {
                                        col = col_value + 1;
                                        row = row_value;
                                    }
                                    else if(Demand_sum > Supply_sum)
                                    {
                                        row = row_value + 1;
                                        col = col_value;
                                    }
                                    
                                    int cost[][] = new int[row][col];
                                    int demand[] = new int[col];
                                    int supply[] = new int[row];
                                    
                                    for(int i=0;i<row_value;i++)
                                    {
        				for(int j=0;j<col_value;j++)
        				{
                                            cost[i][j] = Integer.parseInt(inputCost[i][j].getText());
                                            demand[j] = Integer.parseInt(inputDemand[j].getText());
        				}
        				supply[i] = Integer.parseInt(inputSupply[i].getText());
        				
                                    }
    				
                                        
                                    for(int i=0;i<row_value;i++)
                    				{
                    					for(int j=0;j<col_value;j++)
                    					{
                    						inputCost[i][j].setEditable(false);
                    						inputDemand[j].setEditable(false);
                    					}
                    					inputSupply[i].setEditable(false);
                    					
                    				}
                                    
                                    if(Supply_sum > Demand_sum)
                                    {
                                        int difference = Supply_sum - Demand_sum;
                                        demand[col-1] = difference;
                                        JOptionPane.showMessageDialog(null, "Supply is "+difference+" units more than Demand", "ALERT", INFORMATION_MESSAGE);
                                    }
                                    else if(Demand_sum > Supply_sum)
                                    {
                                        int difference = Demand_sum - Supply_sum;
                                        supply[row-1] = difference;
                                        JOptionPane.showMessageDialog(null, "Demand is "+difference+" units more than Supply", "ALERT", INFORMATION_MESSAGE);
                                    }
                                    
                                    
                                    switch(dropDownMenu.getSelectedIndex())
                                    {
                                        case 0: implementNWCR ob =	new implementNWCR();
                                                ob.compute(row,col,cost,demand,supply);
                                                break;
                                            
                                        case 1: implementLCM ob1 =	new implementLCM();
                                                ob1.compute(row,col,cost,demand,supply);
                                             	break;
                                            
                                        case 2: implementVAM ob2 =	new implementVAM();
                                                ob2.compute(row,col,cost,demand,supply);
                                                break;
                                           
                                        case 3: Modi_Input ob3 =	new Modi_Input();
                                                ob3.start(row,col,cost,demand,supply);
                                                break;
                                              
                                        case 4: Graph.close();
                                                implementCompare.compute(row, col, cost, demand, supply);
                                                break;
                                    }
                                    
                                    
                              
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
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        try
    				{
                                    int Supply_sum = 0;
                                    int Demand_sum = 0;
                                    for(int i = 0; i < row_value;i++)
                                        Supply_sum += Integer.parseInt(inputSupply[i].getText());
                                    
                                    for(int i = 0; i < col_value;i++)
                                        Demand_sum += Integer.parseInt(inputDemand[i].getText());
                                    
                                    int row = 0, col = 0;
                                    if(Demand_sum == Supply_sum)
                                    {
                                        row = row_value;
                                        col = col_value;
                                    }
                                    else if(Supply_sum > Demand_sum)
                                    {
                                        col = col_value + 1;
                                        row = row_value;
                                    }
                                    else if(Demand_sum > Supply_sum)
                                    {
                                        row = row_value + 1;
                                        col = col_value;
                                    }
                                    
                                    int cost[][] = new int[row][col];
                                    int demand[] = new int[col];
                                    int supply[] = new int[row];
                                    
                                    for(int i=0;i<row_value;i++)
                                    {
        				for(int j=0;j<col_value;j++)
        				{
                                            cost[i][j] = Integer.parseInt(inputCost[i][j].getText());
                                            demand[j] = Integer.parseInt(inputDemand[j].getText());
        				}
        				supply[i] = Integer.parseInt(inputSupply[i].getText());
        				
                                    }
    				
                                        
                                    for(int i=0;i<row_value;i++)
                    				{
                    					for(int j=0;j<col_value;j++)
                    					{
                    						inputCost[i][j].setEditable(false);
                    						inputDemand[j].setEditable(false);
                    					}
                    					inputSupply[i].setEditable(false);
                    					
                    				}
                                    
                                    if(Supply_sum > Demand_sum)
                                    {
                                        int difference = Supply_sum - Demand_sum;
                                        demand[col-1] = difference;
                                        JOptionPane.showMessageDialog(null, "Supply is "+difference+" units more than Demand", "ALERT", INFORMATION_MESSAGE);
                                    }
                                    else if(Demand_sum > Supply_sum)
                                    {
                                        int difference = Demand_sum - Supply_sum;
                                        supply[row-1] = difference;
                                        JOptionPane.showMessageDialog(null, "Demand is "+difference+" units more than Supply", "ALERT", INFORMATION_MESSAGE);
                                    }
                                    
                                    switch(dropDownMenu.getSelectedIndex())
                                    {
                                            case 0: implementNWCR ob =	new implementNWCR();
                                                    ob.compute(row,col,cost,demand,supply);
                                                    break;
                                            
                                            case 1: implementLCM ob1 =	new implementLCM();
                                                    ob1.compute(row,col,cost,demand,supply);
                                               	break;
                                            
                                            case 2: implementVAM ob2 =	new implementVAM();
                                                    ob2.compute(row,col,cost,demand,supply);
                                                    break;
                                            
                                            case 3: Modi_Input ob3 =	new Modi_Input();
                                                    ob3.start(row,col,cost,demand,supply);
                                                    break;
                                                
                                            case 4: Graph.close();
                                                    implementCompare.compute(row, col, cost, demand, supply);
                                                    break;
                                    }
                                    
                                    
                              
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
                            Graph.close();
    				for(int i=0;i<row_value;i++)
    				{
    					for(int j=0;j<col_value;j++)
    					{
    						inputCost[i][j].setEditable(true);
    						inputCost[i][j].setBackground(Color.green);
    	                    inputCost[i][j].setText("Cost["+(i+1)+"]["+(j+1)+"]");
    	                    inputDemand[j].setEditable(true);
        					inputDemand[j].setBackground(Color.yellow);
    				        inputDemand[j].setText("Demand["+(j+1)+"]");
    	                    displayCost.setText("");
    					}
    					
    					inputSupply[i].setEditable(true);
	                    inputSupply[i].setBackground(Color.pink);
	                    inputSupply[i].setText("Supply["+(i+1)+"]");
    					
    				}
                                dropDownMenu.setSelectedIndex(0);
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
                        Graph.close();
                        for(int i=0;i<row_value;i++)
    				{
    					for(int j=0;j<col_value;j++)
    					{
    						inputCost[i][j].setEditable(true);
    						inputCost[i][j].setBackground(Color.green);
    	                    inputCost[i][j].setText("Cost["+(i+1)+"]["+(j+1)+"]");
    	                    inputDemand[j].setEditable(true);
        					inputDemand[j].setBackground(Color.yellow);
    				        inputDemand[j].setText("Demand["+(j+1)+"]");
    	                    displayCost.setText("");
    					}
    					
    					inputSupply[i].setEditable(true);
	                    inputSupply[i].setBackground(Color.pink);
	                    inputSupply[i].setText("Supply["+(i+1)+"]");
    					
    				}
                        dropDownMenu.setSelectedIndex(0);
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
                            Graph.close();
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
                                }
                            }).start();
                            inputFrame.dispose();
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
                        Graph.close();
                        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    MainWindow.start();
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
            
            
            bNew.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
                        {
                            Graph.close();
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TransportationMainFrame.start();
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
                        Graph.close();
                        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    TransportationMainFrame.start();
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
                                    File file = new File(source+"\\html\\transportation.html");
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
                                    File file = new File(source+"\\html\\transportation.html");
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
			
			Component comp = e.getComponent();
			if(comp instanceof JTextField){
				((JTextField) comp).select(0, 0);
			}
		}
	}
}
