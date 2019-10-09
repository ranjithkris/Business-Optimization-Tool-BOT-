package MainPackage.Game_mixedstrategy;


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
public class checkDominance 
{
    private static final long serialVersionUID = 9220036263068464736L;
    public static boolean compute(int subgame[][])
    {
        int subgame_flag[][] = new int[2][2];

        for(int i = 0; i<2; i++)
        {
            for(int j = 0; j<2; j++)
            {
                subgame_flag[i][j] = 0;
            }
        }
        


        int A_min[] = new int[2];
        for(int i = 0; i < 2; i++)
        {
            int min = subgame[i][0];
            for(int j = 1; j < 2; j++)
            {
                if(min > subgame[i][j])
                    min = subgame[i][j];
            }
            A_min[i] = min;
        }

        
        int B_max[] = new int[2];
        for(int j = 0; j < 2; j++)
        {
            int max = subgame[0][j];
            for(int i = 1; i < 2; i++)
            {
                if(max < subgame[i][j])
                    max = subgame[i][j];
            }
            B_max[j] = max;
        }

        for(int i = 0; i < 2; i++)
        {
            int min = A_min[i];
            for(int j = 0; j < 2; j++)
            {
                if(min == subgame[i][j])
                    subgame_flag[i][j]++;
            }
        }
        
        for(int j = 0; j < 2; j++)
        {
            int max = B_max[j];
            for(int i = 0; i < 2; i++)
            {
                if(max == subgame[i][j])
                    subgame_flag[i][j]++;
            }
        }
        
        int count = 0;
        int index[][] = new int[10][2];
        int saddle = 0;
        int p = 0;
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                if(subgame_flag[i][j] == 2)
                {
                    count++;
                    index[p][0] = i;
                    index[p++][1] = j;
                    saddle = subgame[i][j];
                }
            }
        }
        
        if(count == 0)
        {
            return false;
        }
        else if(count == 1)
        {
            implementMixedStrategy.msg += ("Value of the Game(saddle point) is : " + saddle + "<br>");
            implementMixedStrategy.msg += ("A's Strategy is : " + (index[0][0]+1) + "<br>");
            implementMixedStrategy.msg += ("B's Strategy is : " + (index[0][1]+1) + "<br>");
            implementMixedStrategy.subgamevalue[implementMixedStrategy.index++] = saddle; 
        //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
        }
        else
        {
            implementMixedStrategy.msg += "Value of the Game is : " + saddle +"<br>";
            implementMixedStrategy.msg += "Game has " + count + " saddle points <br>";
            implementMixedStrategy.msg += "set of player's strategies are : <br>";
            implementMixedStrategy.subgamevalue[implementMixedStrategy.index++] = saddle; 
            for(int i = 0 ; i < count ; i++)
            {
                implementMixedStrategy.msg += "(A" +(i+1)+",B" +(i+1)+ ") : ";
                implementMixedStrategy.msg +="("+(index[i][0]+1)+","+(index[i][1]+1)+")<br>";
            }
         //   JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
        }
    
        return true;
    }
}
