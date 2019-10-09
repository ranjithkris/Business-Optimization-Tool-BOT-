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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


class implementNWCR
{
    private static final long serialVersionUID = 9220036263068464736L;
	public void compute(int m,int n,int cost[][],int dem[],int sup[])
	{
		int i,j,sum=0,a=0,count = 1;

                
              
		for(i=0,j=0;(i<m&&j<n);)
		{
                    
			if(sup[i]<dem[j])
			{
                            sum+=cost[i][j]*sup[i];
                        
                            if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                            {
                                try
                                {
                                    TransportationInput.inputCost[i][j].setText((count++)+"("+cost[i][j]+" * S["+sup[i]+"])");
                                    TransportationInput.inputCost[i][j].setBackground(Color.white);
                                }
                                catch(Exception e)
                                {}
                            }
                                dem[j]-=sup[i];
                                sup[i]=0;
				i++;
				a++;
				
			}
			else
			if(sup[i]>dem[j])
			{
				sum+=cost[i][j]*dem[j];
                            if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                            {
                                try
                                {
                                    TransportationInput.inputCost[i][j].setText((count++)+"("+cost[i][j]+" * D["+dem[j]+"])");
                                    TransportationInput.inputCost[i][j].setBackground(Color.white);
                                }
                                catch(Exception e)
                                {}
                                
                            }
                            
                            
                                sup[i]-=dem[j];
                                dem[j]=0;
				j++;
				a++;
			}
			else
			if(sup[i]== dem[j])
			{
				sum+=cost[i][j]*dem[j];
                            if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
                            {
                                try
                                {
                                    TransportationInput.inputCost[i][j].setText((count++)+"("+cost[i][j]+" * S,D["+dem[j]+"])");
                                    TransportationInput.inputCost[i][j].setBackground(Color.white);
                                }
                                catch(Exception e)
                                {}
                            }
                            
                                sup[i]=dem[j]=0;
				i++;
				j++;
				a++;
					
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
                implementCompare.nwcr_cost = sum;
        }
			
	}
}
