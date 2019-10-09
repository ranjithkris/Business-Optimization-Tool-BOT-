package MainPackage.Tsp;

import java.util.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class implementTsp
{

    private static final long serialVersionUID = 9220036263068464736L;
    static int cost[][],n,tour[],finalCost;
    static final int INF=1000;

    public static void compute(int[][] c,int node)
    {
        n=node;
        cost=new int[n][n];
        tour=new int[n-1];
        
        

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j)
                {
                    cost[i][j]=c[i][j];
                }
            }
        }
        
    
                try
                {
                    for(int i = 0; i< node ; i ++)
                    {
    			for(int j = 0; j< node ; j ++)
                        {
    				if(cost[i][j] < 999 && cost[i][j] != 0)
                                    throw new Exception();
    			}
                    }
                    JOptionPane.showMessageDialog(null, "There is no Path in the given Graph.\nGive Valid Input", "ERROR", WARNING_MESSAGE);
                }
                catch(Exception e)
                {
                   try
                    {
                        evaluate();
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Graph/Something went wrong \nPlease check the Input once!!", "ERROR", ERROR_MESSAGE);
                    } 
                }
                
        
    }

    public static int COST(int currentNode,int inputSet[],int setSize)
    {
        if(setSize==0)
            return cost[currentNode][0];

        int min=INF,minindex=0;
        int setToBePassedOnToNextCallOfCOST[]=new int[n-1];

        for(int i=0;i<setSize;i++)
        {
            int k=0;//initialise new set
            
            for(int j=0;j<setSize;j++)
            {
                if(inputSet[i]!=inputSet[j])
                    setToBePassedOnToNextCallOfCOST[k++]=inputSet[j];
            }

            int temp=COST(inputSet[i],setToBePassedOnToNextCallOfCOST,setSize-1);

            if((cost[currentNode][inputSet[i]]+temp) < min)
            {
                min=cost[currentNode][inputSet[i]]+temp;
                minindex=inputSet[i];
            }
        }
        return min;
    }

    public static int MIN(int currentNode,int inputSet[],int setSize)
    {
        if(setSize==0)
            return cost[currentNode][0];
        
        int min=INF,minindex=0;

        int setToBePassedOnToNextCallOfCOST[]=new int[n-1];

        for(int i=0;i<setSize;i++)//considers each node of inputSet
        {
            int k=0;

            for(int j=0;j<setSize;j++)
            {
                if(inputSet[i]!=inputSet[j])
                    setToBePassedOnToNextCallOfCOST[k++]=inputSet[j];
            }

            int temp=COST(inputSet[i],setToBePassedOnToNextCallOfCOST,setSize-1);
    
            if((cost[currentNode][inputSet[i]]+temp) < min)
            {
                min=cost[currentNode][inputSet[i]]+temp;
                minindex=inputSet[i];
            }
        }
        
        return minindex;
    }

    public static void evaluate()
    {
        int dummySet[]=new int[n-1];
        
        for(int i=1;i<n;i++)
            dummySet[i-1]=i;

        finalCost=COST(0,dummySet,n-1);
        constructTour();
    }

    public static void constructTour()
    {
        
        int previousSet[]=new int[n-1];
        int nextSet[]=new int[n-2]; for(int i=1;i<n;i++)

        previousSet[i-1]=i;
        int setSize = n-1;
        
        tour[0]= MIN(0,previousSet,setSize);

        for(int i=1;i<n-1;i++)
        {
            int k=0;
            for(int j=0;j<setSize;j++)
            {
                if(tour[i-1]!=previousSet[j])
                    nextSet[k++]=previousSet[j];
            }
            
            --setSize;
            tour[i]=MIN(tour[i-1],nextSet,setSize);
            
            System.arraycopy(nextSet, 0, previousSet, 0, setSize);
        }
        
        display();
    }

    public static void display()
    {
        String msg = "The path is 1-";

        for(int i=0;i<n-1;i++)
            msg += (tour[i]+1)+"-";

        msg += "1\n";

        msg += "The final cost is: "+finalCost;
        JOptionPane.showMessageDialog(null, msg, "SOLUTION", PLAIN_MESSAGE);
    }

}