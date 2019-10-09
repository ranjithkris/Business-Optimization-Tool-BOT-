/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage.Transportation;

/**
 *
 * @author ranjith
 */

import java.awt.Color;
import java.util.*;
import javax.swing.JOptionPane;
/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith
 */
public class implementLCM
{    
    private static final long serialVersionUID = 9220036263068464736L;
    public void compute(int m,int n,int c[][],int dem[],int sup[])
    {
    	int i,j,min,b,d,p=0,q=0,a=0,count=1;
    	int rf[] = new int[20];
        int cf[] = new int[20];
        int sum=0;
    	for(i=0;i<m;i++) 
    		rf[i]=0; 
    	for(i=0;i<n;i++) 
    		cf[i]=0; 
        
        
        
    	b=m;d=n; 
    	while(b>0&&d>0) 
    	{ 
    		min=1000; 
    		for(i=0;i<m;i++) 	
    		{ 
    			if(rf[i]!=1) 
    			{ 
    				for(j=0;j<n;j++) 		
    				{ 
    					if(cf[j]!=1) 	 
    					{ 
    						if(min>c[i][j]) 	 
    						{ 
                                                    
    							min=c[i][j]; 	 
    							p=i; 	 
    							q=j; 	 
    						} 	 
    					} 	
    				} 
    			} 
    		} 

 
                
                
    		if(sup[p]<dem[q]) 
    		{ 
    			sum+=c[p][q]*sup[p]; 
    			 
    			rf[p]=1; 
    			b--; 
    			a++;
                        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                        {
                            try
                            {
				TransportationInput.inputCost[p][q].setText((count++)+"("+c[p][q]+" * S["+sup[p]+"])");
				TransportationInput.inputCost[p][q].setBackground(Color.white);
                            }
                            catch(Exception e)
                            {}
                            
                        }
                        dem[q]-=sup[p];
                        
    		} 
    		else 
    		if(sup[p]>dem[q]) 
    		{ 
    			sum=sum+c[p][q]*dem[q]; 
    			 
    			cf[q]=1; d--; 
    			a++;
                        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                        {
                            try
                            {
				TransportationInput.inputCost[p][q].setText((count++)+"("+c[p][q]+" * D["+dem[q]+"])");
				TransportationInput.inputCost[p][q].setBackground(Color.white);
                            }
                            catch(Exception e)
                            {}
                            
                        }
                        
                        sup[p]-=dem[q];
    		} 
    		else 
    		if(sup[p]== dem[q]) 
    		{ 
    			sum=sum+c[p][q]*sup[p]; 
    			rf[p]=1; 
    			cf[q]=1; 
    			b--; 
    			d--; 
    			a++;
                        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                        {
                            try
                            {
                                TransportationInput.inputCost[p][q].setText((count++)+"("+c[p][q]+" * S,D["+dem[q]+"])");
                                TransportationInput.inputCost[p][q].setBackground(Color.white);
                            }
                            catch(Exception e)
                            {}
                            
                        }
    		} 
    		 
    	} 
    	
        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
        {
            if(a==(m+n-1))
		TransportationInput.displayCost.setText(Integer.toString(sum)+" (Basic Feasible Solution)");
            else
		TransportationInput.displayCost.setText(Integer.toString(sum)+" (Not a Basic Feasible Solution)");
        }
        else
        {
                implementCompare.lcm_cost = sum;
        }
    }
   }   


