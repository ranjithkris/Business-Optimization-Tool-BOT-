/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage.Transportation;

import java.awt.Color;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;


/**
 *
 * @author ranjith
 */
class implementModi
{
    private static final long serialVersionUID = 9220036263068464736L;
    static int a[][]=new int[100][100];
    static  int b[][]=new int[100][100];
    static int a1[][]=new int[100][100];
    static int x[][]=new int[100][100];
    static  int loop[][]=new int[100][100];
    static int nbc[][]=new int[100][100];
    static  int s[]=new int[100];
    static int s1[]=new int[100];
    static int d[]=new int[100];
    static int d1[]=new int[100];
    static int pi[]=new int[100];
    static int pj[]=new int[100];
    static int ss=0,sd=0,min,min1,min2,maxp=0;
    static char sign[][]=new char[100][100];
    static int u[]=new int[100];
    static int v[]=new int[100];
    static  int r,c,i,j,k,lc=0,mi,mj,max=0,tc=0;
    static  int flag=0,opt;
    static int tempx[][]=new int[100][100];
    static int min_allocation[][] = new int[100][100];;
    static int min_cost = 999999;
    static int initial_cost = 0; 
   

    public static void compute(int row,int col,int cost[][],int demand[],int supply[],int option,boolean isDisplay)
    {
        
  
        r = row;
        c = col;
        for(i=1;i<=r;i++)
            for(j=1;j<=c;j++)
                a[i][j] = cost[i-1][j-1];
        
        for(i=1;i<=r;i++)
            s[i] = supply[i-1];
        
        for(i=1;i<=c;i++)
            d[i] = demand[i-1];
        
        opt=option;
  
            
            switch(opt)
            {
                case 0: initialize();
                        nwc();
                        break;

                case 1: initialize();
                        lcm();
                        break;

                case 2: initialize();
                        vam();
                        break;
            }
            //end of switch
  
            k=0;
            for(i=1;i<=r;i++)
                for(j=1;j<=c;j++)
                {
                    b[i][j]=a[i][j];
                    if(x[i][j]!=0)
                        k++;
                }
  
            for(i=0;i<r;i++)
                for(j=0;j<c;j++)
                {
                    nbc[i][j]=0;
                    loop[i][j]=0;
                }
  
            for(i=0;i<r;i++)
                u[i]=0;
  
            for(j=0;j<c;j++)
                v[j]=0;
  
            mi=0;mj=0;
            
            int test = 0;
            while(test < 40)
            { 
                test++;
                /* FOR BASIC CELL */
                /* Counting the no.of allocations in row & column */
            
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                        if(x[i][j]!=0)
                            u[i]++;
                for(j=1;j<=c;j++)
                    for(i=1;i<=r;i++)
                        if(x[i][j]!=0)
                            v[j]++;
    
                /* Selecting the row or column having max no.of allocations */
                max=0;flag=0;
                for(i=1;i<=r;i++)
                    if(max<u[i])
                    {
                        max=u[i];
                        mi=i;
                        flag=1;
                    }
    
                for(j=1;j<=c;j++)
                    if(max<v[j])
                    {
                        max=v[j];
                        mj=j;
                        flag=2;
                    }
     
                for(i=1;i<=r;i++)
                    u[i]=0;
     
                for(j=1;j<=c;j++)
                    v[j]=0;
    
                /* Assigning value for u and v */
    
                if(flag==1) 
                {
                    for(j=1;j<=c;j++)
                        if(x[mi][j]!=0)
                            v[j]=b[mi][j];
	 
                    for(k=1;k<=r;k++)
                    {
                        for(i=1;i<=r;i++)
                            for(j=1;j<=c;j++)
                                if(x[i][j]!=0 && v[j]!=0)
                                    u[i]=b[i][j]-v[j];
      
                        for(j=1;j<=c;j++)
                            for(i=1;i<=r;i++)
                                if(x[i][j]!=0 && u[i]!=0)
                                    v[j]=b[i][j]-u[i];
                    }
                }
	
                if(flag==2)
                {
                    for(i=1;i<=r;i++)
                        if(x[i][mj]!=0)
                            u[i]=b[i][mj];
	 
                    for(k=1;k<=r;k++)
                    {
                        for(j=1;j<=c;j++)
                            for(i=1;i<=r;i++)
                                if(x[i][j]!=0 && u[i]!=0)
                                    v[j]=b[i][j]-u[i];
                
                        for(i=1;i<=r;i++)
                            for(j=1;j<=c;j++)
                                if(x[i][j]!=0 && v[j]!=0)
                                    u[i]=b[i][j]-v[j];
                    }
                }
    
                /* FOR NON BASIC CELL */
    
                max=0;
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                        if(x[i][j]==0)
                        {
                            nbc[i][j]=b[i][j]-(u[i]+v[j]);
                            if(max>nbc[i][j])
                            {
                                max=nbc[i][j];
                                mi=i;
                                mj=j;
                            }
                        }

                /* Loop Formation */
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                    {
                        if(x[i][j]!=0)
                            loop[i][j]=1;
                        else
                            loop[i][j]=0;
                        sign[i][j]=' ';
                    }
    
                for(k=1;k<=r;k++)
                {
                    for(i=1;i<=r;i++)
                    {
                        for(j=1;j<=c;j++)
                            if(loop[i][j]==1)
                                lc++;
                            if(lc == 1 && i != mi)    
                                for(j=1;j<=c;j++)
                                    loop[i][j]=0;
                                lc=0;
                        }
     
                    lc=0;
     
                    for(j=1;j<=c;j++){
                        for(i=1;i<=r;i++)
                            if(loop[i][j]==1)
                                lc++;
      
                        if(lc==1 && j!=mj)
                            for(i=1;i<=r;i++)
                                loop[i][j]=0;
                        lc=0;
                    }
                }
    
                
                /* Assigning the Sign */
    
                sign[mi][mj]='+';
                i=mi;
                for(k=1;k<=3;k++)
                {
                    for(j=1;j<=c;j++)
                        if(loop[i][j]==1 && sign[i][j]==' ')
                        {
                            sign[i][j]='-';
                            break;
                        }
                      
                    for(i=1;i<=r;i++)
                        if(loop[i][j]==1 && sign[i][j]==' ')
                        {
                            sign[i][j]='+';
                            break;
                        }
                }
                
                
                
                /* Finding @ Value */
                min=999999;
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                        if(sign[i][j]=='-' && min>x[i][j])
                            min=x[i][j];
                
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                        if(sign[i][j]=='+')
                        {
                            x[i][j]+=min;
                        }
                        else if(sign[i][j]=='-')
                            x[i][j]-=min;
    
                /* Checking m+n-1 Condition */
                k=0;
    
                for(i=1;i<=r;i++)
                    for(j=1;j<=c;j++)
                        if(x[i][j]!=0)
                            k++;
                
                 boolean check = true;
            
                for(i=1;i<=r;i++)
                {
                    for(j=1;j<=c;j++)
                    {
                        tempx[i][j] = x[i][j];
                    }
                }
                
                while(check)
                {
                    check = false;
                    for(i=1;i<=r;i++)
                    {
                        for(j=1;j<=c;j++)
                        {   
                            if(tempx[i][j] >= 999999)
                            {
                                tempx[i][j] -= 999999;
                                check = true;
                            }
                        }
                    }
                }
                
                int total_cost = 0;
                for(i=1;i<=r;i++)
                {
                    for(j=1;j<=c;j++)
                    {
                        total_cost=total_cost+(a[i][j]*tempx[i][j]);
                    }
                }
                
                if(min_cost > total_cost)
                {
                    min_cost = total_cost;
                    for(i=1;i<=r;i++)
                    {
                        for(j=1;j<=c;j++)
                        {
                            min_allocation[i][j] = tempx[i][j];
                        }
                    }
                }
                
                
            } /* End of While */
            
            if(isDisplay)
            {
                display();
            }
            else
            {
                switch(opt)
                {
                    case 0: implementCompare.nwcr_modi_cost = min_cost;
                            break;
                        
                    case 1: implementCompare.lcm_modi_cost = min_cost;
                            break;
                        
                    case 2: implementCompare.vam_modi_cost = min_cost;
                            break;
                        
                }
            }
            
  /*
           
            tc = 0;
            for(i=1;i<=r;i++)
            {
                for(j=1;j<=c;j++)
                {
                    System.out.print(x[i][j]+"\t");
                        tc=tc+(a[i][j]*x[i][j]);
                }
                System.out.println();
            }
  
            System.out.println("Total Cost is "+min_cost);
        */
    }
    
    
/* To get input values */
static void initialize()
{
    for(int i = 0;i < 100;i++)
    {
        for(int j = 0 ;j < 100; j++)
        {
            b[i][j] = 0;
            a1[i][j] = 0;
            x[i][j] = 0;
            loop[i][j] = 0;
            nbc[i][j] = 0;
            sign[i][j] = ' ';
            tempx[i][j] = 0;
            min_allocation[i][j] = 0;
        }
        s1[i] = 0;
        d1[i] = 0;
        pi[i] = 0;
        pj[i] = 0;
        u[i] = 0;
        v[i] = 0;
    }
    ss=0;sd=0;min=0;min1=0;min2=0;maxp=0;
    i=0;j=0;k=0;lc=0;mi=0;mj=0;max=0;tc=0;
    flag=0;
    min_cost = 999999;
    initial_cost = 0; 
  
    for(i=1;i<=r;i++)
    {
        for(j=1;j<=c;j++)
        {
            a1[i][j]=b[i][j]=a[i][j];
	}
    }
    
    for(i=0;i<10;i++)
        for(j=0;j<10;j++)
            x[i][j]=0;
    
    for(i=1;i<=r;i++)
    {
        s1[i]=s[i];
        ss+=s[i];
    }
    
    for(j=1;j<=c;j++)
    {
        d1[j]=d[j];
        sd+=d[j];
    }
}

/* Function of North West Corner Rule */
static void nwc()
{
    k=0;i=1;j=1;
    while(k<(r+c)-1)
    {
        if(s[i]>d[j])
        {
            k++;
            x[i][j]=d[j];
            s[i]=s[i]-d[j];
            ss-=d[j];
            sd-=d[j];
            d[j]=0;
            j++;
	}
	else if(s[i]<d[j])
	{
            k++;
            x[i][j]=s[i];
            d[j]=d[j]-s[i];
            ss-=s[i];
            sd-=s[i];
            s[i]=0;
            i++;
        }
	else
	{
            k++;
            x[i][j]=s[i];
            ss-=s[i];
            sd-=s[i];
            s[i]=0;
            d[j]=0;
            i++;
            j++;
        }
    
        if((ss==0) && (sd==0))
            break;
    }
    
    tc = 0;
    for(i=1;i<=r;i++)
    {
        for(j=1;j<=c;j++)
        {
            tc=tc+(a[i][j]*x[i][j]);
        }
    }
    initial_cost = tc;
}

/* Function of Least Cost Method */
static void lcm()
{
    for(i=1;i<=r;i++)
        for(j=1;j<=c;j++)
            b[i][j]=a[i][j];
  
    k=0;mi=1;mj=1;
    while(k<(r+c)-1)
    {
        min=999999;
        for(i=1;i<=r;i++)
            for(j=1;j<=c;j++)
                if(min>b[i][j] && b[i][j]!=-1)
                {
                    min=b[i][j];
                    mi=i;mj=j;
                }
	
        if(s[mi]>d[mj])
	{
            k++;
            x[mi][mj]=d[mj];
            s[mi]=s[mi]-d[mj];
            ss-=d[mj];
            sd-=d[mj];
            d[mj]=0;
            for(i=1;i<=r;i++)
                b[i][mj]=-1;
	}
	if(s[mi]<d[mj])
	{
            k++;
            x[mi][mj]=s[mi];
            d[mj]=d[mj]-s[mi];
            ss-=s[mi];
            sd-=s[mi];
            s[mi]=0;
            for(j=1;j<=c;j++)
            b[mi][j]=-1;
        }
	if(s[mi]==d[mj])
	{
            k++;
            x[mi][mj]=s[mi];
            ss-=s[mi];
            sd-=s[mi];
            s[mi]=0;
            d[mj]=0;
            for(i=1;i<=r;i++)
                b[i][mj]=-1;
            for(j=1;j<=c;j++)
                b[mi][j]=-1;
        }
        
        if((ss==0)&&(sd==0))
            break;
    }
    tc = 0;
    for(i=1;i<=r;i++)
    {
        for(j=1;j<=c;j++)
        {
            tc=tc+(a[i][j]*x[i][j]);
        }
    }
    initial_cost = tc;
}

/* Function of Vogel's Approximation Method */
static void vam()
{
    for(i=1;i<=r;i++)
        for(j=1;j<=c;j++)
            b[i][j]=a[i][j];
    
    k=0;mi=0;mj=0;
    while(k<(r+c)-1)
    {
        /* Selecting Penalty Value for Row */
	for(i=1;i<=r;i++)
	{
            min1=999999;min2=999999;
            for(j=1;j<=c;j++)
                if(min1>b[i][j] && b[i][j]!=-1)
                {
                    min1=b[i][j];
                    mj=j;
                }
     
            for(j=1;j<=c;j++)
                if(min2>b[i][j] && b[i][j]!=-1 && min2>=min1 && j!=mj)
                    min2=b[i][j];
	 
            pi[i]=min2-min1;
            if(pi[i]>9000)
                pi[i]=min1;
	}
        /* Selecting Penalty Value for Column */
	for(j=1;j<=c;j++)
	{
            min1=999999;min2=999999;
            for(i=1;i<=r;i++)
                if(min1>b[i][j] && b[i][j]!=-1)
                {
                    min1=b[i][j];
                    mi=i;
                }
     
            for(i=1;i<=r;i++)
                if(min2>b[i][j] && b[i][j]!=-1 && min2>=min1 && i!=mi)
                    min2=b[i][j];
     
            pj[j]=min2-min1;
     
            if(pj[j]>9000)
                pj[j]=min1;
        }
        /* Selecting Max. Penalty Value */
        maxp=0;flag=0;
    
        for(i=1;i<=r;i++)
            if(maxp<pi[i])
            {
                maxp=pi[i];
                mi=i;
                flag=1;
            }
    
        for(j=1;j<=c;j++)
            if(maxp<pj[j])
            {
                maxp=pj[j];
                mj=j;
                flag=2;
            }
        /* Selecting min value in max penalty row or column */
        min1=999999;
        if(flag==1)
        for(j=1;j<=c;j++)
            if(min1>b[mi][j] && b[mi][j]!=-1)
            {
                min1=b[mi][j];
                mj=j;
            }
     
        if(flag==2)
            for(i=1;i<=r;i++)
                if(min1>b[i][mj] && b[i][mj]!=-1)
                {
                    min1=b[i][mj];
                    mi=i;
                }
   
        /* Allocation */
        if(s[mi]>d[mj])
	{
            k++;
            x[mi][mj]=d[mj];
            s[mi]=s[mi]-d[mj];
            ss-=d[mj];
            sd-=d[mj];
            d[mj]=0;
      
            for(i=1;i<=r;i++)
                b[i][mj]=-1;
        }
	if(s[mi]<d[mj])
	{
            k++;
            x[mi][mj]=s[mi];
            d[mj]=d[mj]-s[mi];
            ss-=s[mi];
            sd-=s[mi];
            s[mi]=0;
      
            for(j=1;j<=c;j++)
                b[mi][j]=-1;
        }
	if(s[mi]==d[mj])
	{
            k++;
            x[mi][mj]=s[mi];
            ss-=s[mi];
            sd-=s[mi];
            s[mi]=0;
            d[mj]=0;
      
            for(i=1;i<=r;i++)
                b[i][mj]=-1;
      
            for(j=1;j<=c;j++)
                b[mi][j]=-1;
        }
        
        if((ss==0)&&(sd==0))
            break;
    }
    tc = 0;
    for(i=1;i<=r;i++)
    {
        for(j=1;j<=c;j++)
        {
            tc=tc+(a[i][j]*x[i][j]);
        }
    }
    initial_cost = tc;
}

static void display()
{
    for(i=1;i<=r;i++)
    {
        for(j=1;j<=c;j++)
        {
            if(min_allocation[i][j]!=0)
            {
                try
                {
                    TransportationInput.inputCost[i-1][j-1].setText("("+a[i][j]+","+min_allocation[i][j]+")");
                    TransportationInput.inputCost[i-1][j-1].setBackground(Color.white);
                }
                catch(Exception e)
                {}
            }
        } 
    }
    
    TransportationInput.displayCost.setText(Integer.toString(min_cost));
    String msg;
    if(opt == 0)
    {
        msg = "Initial Solution using NWCR = "+initial_cost+"\n";
        msg += "After Optimality test, Cost = "+min_cost;
    }
    else if(opt == 1)
    {
        msg = "Initial Solution using LCM = "+initial_cost+"\n";
        msg += "After Optimality test, Cost = "+min_cost;
    }
    else
    {
        msg = "Initial Solution using VAM = "+initial_cost+"\n";
        msg += "After Optimality test, Cost = "+min_cost;
    }
    JOptionPane.showMessageDialog(null, msg, "SOLUTION", PLAIN_MESSAGE);

    }
}