package MainPackage.Game_Dominance;


import java.util.Scanner;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith
 */
public class implementDominance 
{
    private static final long serialVersionUID = 9220036263068464736L;
    public static void compute(int strategy[][],int m,int n)
    {
        
        int strategy_flag[][] = new int[m][n];

        for(int i = 0; i<m; i++)
        {
            for(int j = 0; j<n; j++)
            {
                strategy_flag[i][j] = 0;
            }
        }
        


        int A_min[] = new int[m];
        for(int i = 0; i < m; i++)
        {
            int min = strategy[i][0];
            for(int j = 1; j < n; j++)
            {
                if(min > strategy[i][j])
                    min = strategy[i][j];
            }
            A_min[i] = min;
        }

        
        int B_max[] = new int[n];
        for(int j = 0; j < n; j++)
        {
            int max = strategy[0][j];
            for(int i = 1; i < m; i++)
            {
                if(max < strategy[i][j])
                    max = strategy[i][j];
            }
            B_max[j] = max;
        }

        for(int i = 0; i < m; i++)
        {
            int min = A_min[i];
            for(int j = 0; j < n; j++)
            {
                if(min == strategy[i][j])
                    strategy_flag[i][j]++;
            }
        }
        
        for(int j = 0; j < n; j++)
        {
            int max = B_max[j];
            for(int i = 0; i < m; i++)
            {
                if(max == strategy[i][j])
                    strategy_flag[i][j]++;
            }
        }
        
        int count = 0;
        int index[][] = new int[100][100];
        int saddle = 0;
        int p = 0;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(strategy_flag[i][j] == 2)
                {
                    count++;
                    index[p][0] = i;
                    index[p++][1] = j;
                    saddle = strategy[i][j];
                }
            }
        }
        
        if(count == 0)
        {
            JOptionPane.showMessageDialog(null,"Game has no saddle point\nbecause the given game can not be solved using Dominance Rule.","SOLUTION",PLAIN_MESSAGE);
        }
        else if(count == 1)
        {
            String message = ("Value of the Game(saddle point) is : " + saddle + "\n");
            message += ("A's Strategy is : " + (index[0][0]+1) + "\n");
            message += ("B's Strategy is : " + (index[0][1]+1) + "\n");
            JOptionPane.showMessageDialog(null,message,"SOLUTION",PLAIN_MESSAGE);
        }
        else
        {
            String message = "Value of the Game is : " + saddle +"\n";
            message += "Game has " + count + " saddle points \n";
            message += "set of player's strategies are : \n";
            for(int i = 0 ; i < count ; i++)
            {
                message += "(A" +(i+1)+",B" +(i+1)+ ") : ";
                message +="("+(index[i][0]+1)+","+(index[i][1]+1)+")\n";
            }
            JOptionPane.showMessageDialog(null,message,"SOLUTION",PLAIN_MESSAGE);
        }
    }
}
