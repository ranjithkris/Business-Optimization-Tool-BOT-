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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith
 */
public class implementVAM
{
private static final long serialVersionUID = 9220036263068464736L;
public static void sort(int a[],int n)
{ 
    int temp,j,k;
  
    for(j=0;j<n;j++)
    { 
        for(k=j+1;k<n;k++)
        { 
            if(a[j]>a[k])
            { 
                temp=a[j];
                a[j]=a[k];
                a[k]=temp;
            }
        }
    }
}

public void compute(int m,int n,int c[][],int dem[],int sup[])
{ 
    int i,j,b,p,d,k,count=0,count1=1;
 
    int cf[] = new int[20];
    int rf[] = new int[20];
    int a[] = new int[20];
    int cp[] = new int[20];
    int rp[] = new int[20];
  

    int max,min,s=0,t=0,sum=0;
  
    
     
  for(i=0;i<m;i++)
   rf[i]=0;
  for(i=0;i<n;i++)
   cf[i]=0;
  
  b=m;d=n;
  while(b>0&&d>0)
  { 
    for(i=0;i<m;i++)
        rp[i]=-1;
    for(i=0;i<n;i++)
        cp[i]=-1;
    
    for(i=0;i<m;i++)
    {
        k=0;
        if(rf[i]!=1)
        { 
            for(j=0;j<n;j++)
            { 
                if(cf[j]!=1)
                    a[k++]=c[i][j];
            }
            if(k==1)
                rp[i]=a[0];
            else
            { 
                sort(a,k);
                rp[i]=a[1]-a[0];
            }
        }                     
    }
    for(i=0;i<n;i++)
    { 
        k=0;
        if(cf[i]!=1)
        { 
            for(j=0;j<m;j++)
            { 
                if(rf[j]!=1)
                    a[k++]=c[j][i];
            }
            if(k==1)
                cp[i]=a[0];
            else
            { 
                sort(a,k);
                cp[i]=a[1]-a[0];
            }
        }
    }
    for(i=0;i<m;i++)
        a[i]=rp[i];
    for(j=0;j<n;j++)
        a[i+j]=cp[j];
    max=a[0];
    p=0;
    for(i=1;i<m+n;i++)
    { 
        if(max<a[i])
        {	
            max=a[i];
            p=i;
        }
    }
    
    //System.out.println("\n\n"+max+p);
    
    min=1000;
    if(p>m-1)
    { 
        p=p-m;
        if(cf[p]!=1)
        { 
            for(i=0;i<m;i++)
            { 
                if(rf[i]!=1)
                { 
                    if(min>c[i][p])
                    { 
                        min=c[i][p];
                        s=i;
                        t=p;
                    }
                }
            }
        }
    }
    else
    { 
        if(rf[p]!=1)
        { 
            for(i=0;i<n;i++)
            { 
                if(cf[i]!=1)
                {  
                    if(min>c[p][i])
                    { 
                        min=c[p][i];
                        s=p;
                        t=i;
                    }
                }
            }
        }
    }
    
    
    if(sup[s]<dem[t])
    { 
        sum+=c[s][t]*sup[s];
        rf[s]=1;
        b--;
        count++;
        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
        {
            try
            {
                TransportationInput.inputCost[s][t].setText((count1++)+"("+c[s][t]+" * S["+sup[s]+"])");
                TransportationInput.inputCost[s][t].setBackground(Color.white);
            }
            catch(Exception e)
            {}
        }
        dem[t]-=sup[s];
    }
    else
    if(sup[s]>dem[t])
    { 
        sum+=c[s][t]*dem[t];
        cf[t]=1;
        d--;
        count++;
        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
        {
            try
            {
                TransportationInput.inputCost[s][t].setText((count1++)+"("+c[s][t]+" * D["+dem[t]+"])");
                TransportationInput.inputCost[s][t].setBackground(Color.white);
            }
            catch(Exception e)
            {}
        }
        sup[s]-=dem[t];
    }
    else
    if(sup[s]==dem[t])
    { 
        sum+=c[s][t]*dem[t];
        cf[t]=1;
        rf[s]=1;
        b--;
        d--;
        count++;
        if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
        {
            try
            {
                TransportationInput.inputCost[s][t].setText((count1++)+"("+c[s][t]+" * S,D["+dem[t]+"])");
                TransportationInput.inputCost[s][t].setBackground(Color.white);
            }
            catch(Exception e)
            {}
        }
       
    }
  }
  
  if(!(TransportationInput.dropDownMenu.getSelectedIndex() == 4))
  {
        if(count==(m+n-1))
            TransportationInput.displayCost.setText(Integer.toString(sum)+" (Basic Feasible Solution)");
        else
            TransportationInput.displayCost.setText(Integer.toString(sum)+" (Not a Basic Feasible Solution)");
  }
  else
  {
                implementCompare.vam_cost = sum;
  }
  
}
}

