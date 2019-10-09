package MainPackage.Lpp;

import MainPackage.MainWindow;
import java.awt.Color;
import javax.swing.*;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.net.URI;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.border.TitledBorder;


public class LppInput extends JFrame{
	
    private static final long serialVersionUID = 9220036263068464736L;
	public static final int numOfIndependentVar=2;
	public static int numOfConstraints=0;
	public static boolean isMaximizing=true;
	private static final int textFieldSize=3;
        JFrame inputFrame = new JFrame(); 
        
	
	private String varName="x";
	
	private static String[] dropMinMax={"MAX","MIN"};
	static JComboBox<String> dropDown = new JComboBox<String>(dropMinMax);
	
	//	2264 for <=, 2265 for >=
	private String[] dropEquals={""+'\u2264',"=",""+'\u2265'};
	
	static JComboBox<String> constraintEq[] = new JComboBox[numOfConstraints];
	
	ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	
	JButton bCompute = new JButton(("Compute"));
	JButton bAbout = new JButton(("About Graphical Method"));
        JButton bHome = new JButton(("Home"));
	JButton bNew = new JButton(("New"));
        JButton bReset = new JButton(("Reset"));
	
	
	
	
	static JTextField inputObjectFunc[] = new JTextField[numOfIndependentVar];
	
	
	static JTextField inputConstraintMatrix[][] = new JTextField[numOfConstraints][numOfIndependentVar];
	static JTextField inputConstraintVec[] = new JTextField[numOfConstraints];
	

	
	
	JPanel inputPanel = new JPanel();
		

	public void compute(int width,int height, int con_value){
		
                numOfConstraints = con_value;
		inputFrame.setSize(width,height);
		inputFrame.setLocationRelativeTo(null);
                inputFrame.setResizable(false);
                ImageIcon ico_img=new ImageIcon(MainWindow.class.getResource("bot.png"));
        try
        {
            inputFrame.setIconImage(ico_img.getImage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
		
		inputFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		inputFrame.setTitle(("GRAPHICAL METHOD"));
		
		
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
		
		
		
		theLayout.gridwidth=20;
		inputPanel.add(new JLabel(("Objective Function:")),theLayout);
		theLayout.gridwidth=2;
		
		theLayout.gridy=2;
		inputPanel.add(dropDown,theLayout);
		theLayout.gridwidth=1;
		theLayout.gridx+=1;
		
		
		inputObjectFunc = new JTextField[numOfIndependentVar];
		for(int i=0;i<inputObjectFunc.length;i++){
			inputObjectFunc[i]= new JTextField("0");
			inputObjectFunc[i].setColumns(textFieldSize);
			inputObjectFunc[i].setHorizontalAlignment(JTextField.CENTER);
			inputObjectFunc[i].addFocusListener(listenFocus);
		}
	
		for(int i=0;i<inputObjectFunc.length;i++){
			theLayout.gridx+=1;
			inputPanel.add(inputObjectFunc[i],theLayout);
			theLayout.gridx+=1;
			if(i!=inputObjectFunc.length-1){
				labels.add(new JLabel(varName + String.valueOf(i+1) + "+"));
			} else {
				labels.add(new JLabel(varName + String.valueOf(i+1)));		
			}
			inputPanel.add(labels.get(labels.size()-1),theLayout);
		}
		
		
		
		inputConstraintVec = new JTextField[numOfConstraints];
		constraintEq = new JComboBox[numOfConstraints];
		dropDown.addActionListener(listen);
		for(int i=0;i<inputConstraintVec.length;i++){
			inputConstraintVec[i]= new JTextField("0");
			inputConstraintVec[i].setColumns(textFieldSize);
			inputConstraintVec[i].setHorizontalAlignment(JTextField.CENTER);
			inputConstraintVec[i].addFocusListener(listenFocus);
			constraintEq[i]= new JComboBox(dropEquals);
		}
		
		inputConstraintMatrix =  new JTextField[numOfConstraints][numOfIndependentVar];
		for(int i=0;i<numOfConstraints;i++){
			for(int j=0;j<numOfIndependentVar;j++){
				inputConstraintMatrix[i][j] = new JTextField("0");
				inputConstraintMatrix[i][j].setColumns(textFieldSize);
				inputConstraintMatrix[i][j].setHorizontalAlignment(JTextField.CENTER);
				inputConstraintMatrix[i][j].addFocusListener(listenFocus);
			}
		}
			
	
		theLayout.gridx=1;
		theLayout.gridy+=1;
		theLayout.gridwidth=20;
		inputPanel.add(new JLabel(("Subjected to Constraints:")),theLayout);
		theLayout.gridwidth=1;
		for(int i=0;i<inputConstraintVec.length;i++){
			theLayout.gridx=1;
			theLayout.gridy+=1;
			for(int j=0;j<numOfIndependentVar;j++){
				inputPanel.add(inputConstraintMatrix[i][j],theLayout);
				theLayout.gridx+=1;
				if(j!=numOfIndependentVar-1){
					labels.add(new JLabel(varName + String.valueOf(j+1)+ "+"));
				} else {
					labels.add(new JLabel(varName + String.valueOf(j+1)));
				}
				inputPanel.add(labels.get(labels.size()-1),theLayout);
				theLayout.gridx+=1;
			}
			
			inputPanel.add(constraintEq[i],theLayout);
			theLayout.gridx+=1;
			inputPanel.add(inputConstraintVec[i],theLayout);
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
		bCompute.addActionListener(listen);
		
		
                
                
                bCompute.addActionListener(new ActionListener()
    		{
            	int constraint[][] = new int[100][100];
            	int objective[] = new int[100];
            	int con_rhs[] = new int[100];
            	int con_equation[] = new int[numOfConstraints]; 
            	
            	
    			public void actionPerformed(ActionEvent ae)
    			{
                            try {
                                for(int i = 0;i<numOfIndependentVar;i++)
                                    objective[i] = Integer.parseInt(inputObjectFunc[i].getText());
                                
                                if(objective[0] == 0 && objective[1] == 0)
                                        throw new Exception();
                                
                            if(dropDown.getSelectedIndex() == 0)
                                isMaximizing = true;
                            else
                                isMaximizing = false;
                            
                            for(int i = 0;i<numOfConstraints;i++)
                            {
                                con_equation[i] = constraintEq[i].getSelectedIndex();
                                con_rhs[i] = Integer.parseInt(inputConstraintVec[i].getText());
                                for(int j=0;j<2;j++)
                                {
                                    constraint[i][j] = Integer.parseInt(inputConstraintMatrix[i][j].getText());
                                }
                                
                                if(constraint[i][0] == 0 && constraint[i][1] == 0)
                                    throw new Exception();
                            }
                            
                            
             /*               System.out.println("Objective function");
                            for(int i = 0;i<2;i++)
                                System.out.print(objective[i]+" ");
                            
                            for(int i = 0;i<numOfConstraints;i++)
                            {
                                for(int j=0;j<numOfIndependentVar;j++)
                                {
                                    System.out.print(constraint[i][j]+" ");
                                }
                                System.out.println(con_rhs[i]);
                            }
    		*/		
                            implementLpp ob = new implementLpp();
                            ob.compute(objective,constraint,con_rhs,numOfIndependentVar,numOfConstraints,con_equation);
                            inputFrame.dispose();
                            
                            } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null,"Both co-efficients of X and Y  in Objective Function and Constarints can not be 0","ERROR",WARNING_MESSAGE);
                                    
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
                    int constraint[][] = new int[100][100];
                    int objective[] = new int[100];
                    int con_rhs[] = new int[100];
                    int con_equation[] = new int[numOfConstraints]; 
                    
                    if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        try {
                                for(int i = 0;i<numOfIndependentVar;i++)
                                    objective[i] = Integer.parseInt(inputObjectFunc[i].getText());
                                
                                if(objective[0] == 0 && objective[1] == 0)
                                        throw new Exception();
                                
                            if(dropDown.getSelectedIndex() == 0)
                                isMaximizing = true;
                            else
                                isMaximizing = false;
                            
                            for(int i = 0;i<numOfConstraints;i++)
                            {
                                con_equation[i] = constraintEq[i].getSelectedIndex();
                                con_rhs[i] = Integer.parseInt(inputConstraintVec[i].getText());
                                for(int j=0;j<2;j++)
                                {
                                    constraint[i][j] = Integer.parseInt(inputConstraintMatrix[i][j].getText());
                                }
                                
                                if(constraint[i][0] == 0 && constraint[i][1] == 0)
                                    throw new Exception();
                            }
                            
                            
                 /*           System.out.println("Objective function");
                            for(int i = 0;i<2;i++)
                                System.out.print(objective[i]+" ");
                            
                            for(int i = 0;i<numOfConstraints;i++)
                            {
                                for(int j=0;j<numOfIndependentVar;j++)
                                {
                                    System.out.print(constraint[i][j]+" ");
                                }
                                System.out.println(con_rhs[i]);
                            }
    		*/		
                            implementLpp ob = new implementLpp();
                            ob.compute(objective,constraint,con_rhs,numOfIndependentVar,numOfConstraints,con_equation);
                            inputFrame.dispose();
                            
                            } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null,"Both co-efficients of X and Y  in Objective Function and Constarints can not be 0","ERROR",WARNING_MESSAGE);
                                    
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
                            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    LppMainFrame.start();
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
                                    LppMainFrame.start();
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
            
            
             bReset.addActionListener(new ActionListener()
    		{
    			public void actionPerformed(ActionEvent ae)
                        {
                            for(int i=0;i<inputObjectFunc.length;i++)
                                inputObjectFunc[i].setText("0");
                            
                            
                            for(int i=0;i<inputConstraintVec.length;i++)
                            {
                                inputConstraintVec[i].setText("0");
                                constraintEq[i].setSelectedIndex(0);
                            }
                                
                            dropDown.setSelectedIndex(0);
                            
                            for(int i=0;i<numOfConstraints;i++)
                                for(int j=0;j<numOfIndependentVar;j++)
                                    inputConstraintMatrix[i][j].setText("0");
		
                            
                            
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
                        for(int i=0;i<inputObjectFunc.length;i++)
                                inputObjectFunc[i].setText("0");
                            
                            
                            for(int i=0;i<inputConstraintVec.length;i++)
                            {
                                inputConstraintVec[i].setText("0");
                                constraintEq[i].setSelectedIndex(0);
                            }
                                
                            dropDown.setSelectedIndex(0);
                            
                            for(int i=0;i<numOfConstraints;i++)
                                for(int j=0;j<numOfIndependentVar;j++)
                                    inputConstraintMatrix[i][j].setText("0");
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
                                    File file = new File(source+"\\html\\lpp.html");
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
                                    File file = new File(source+"\\html\\lpp.html");
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
	
	
	
	//	Implementering af listeners
	private class ListenForButton implements ActionListener{

		 public void actionPerformed(ActionEvent e){
			 
			
		 }
	}
	
	// Implementering af focus-listener
	private class ListenForFocus extends FocusAdapter{

		@Override
		public void focusGained(FocusEvent e) {
			// Marker alt i feltet
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
	
	
	