package MainPackage.Game_mixedstrategy;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith
 */
public class implementMixedStrategy 
{
    private static final long serialVersionUID = 9220036263068464736L;
    static String msg = "<html><u>SOLUTION FOR THE GIVEN GAME IS</u> :<br>";
    static double subgamevalue[] = new double[45];
    static int index = 0;
    public static void compute(int strategy[][],int m,int n)
    {
          for(int i = 0; i < implementMixedStrategy.index; i++)
            implementMixedStrategy.subgamevalue[i] = 0.0;
        implementMixedStrategy.index = 0;
        if(m == 2 && n == 2)
        {/*
            ImageIcon icon = new ImageIcon("1.png");
            JOptionPane.showMessageDialog(null,"Hello","Hi",PLAIN_MESSAGE,icon);*/
            msg += "<br><br>";
            if(!checkDominance.compute(strategy))
            {
                computeMixedStrategy.compute(strategy,m,n);
            }
            
            
            
            new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start();
                                }
                            }).start();
         //   JOptionPane.showMessageDialog(null,msg,"SOLUTION",PLAIN_MESSAGE);
         //   msg = "SOLUTION FOR THE GIVEN GAME IS<br>";
        }
        else if(m == 2)
        {
            int subgame[][] = new int[2][2];
            int count_subgame = 0;
        //    int count = 0;
            
            for(int i = 0; i < n-1; i++)
            {
                for(int j = i+1; j < n; j++)
                {
            //        count++;
                    msg += ("<br><u style='color:blue'>SubGame "+ (++count_subgame)+"</u> : (A1,A2)(B"+(i+1)+",B"+(j+1)+")<br>");
                    subgame[0][0] = strategy[0][i]; 
                    subgame[0][1] = strategy[0][j];
                    subgame[1][0] = strategy[1][i];
                    subgame[1][1] = strategy[1][j];
                    
                    if(!checkDominance.compute(subgame))
                    {
                        computeMixedStrategy.compute(subgame,m,n);
                    }
                    
     /*        //       if(count > 3)
               //     {
                 //       count = 0;
                        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start();
                                }
                            }).start();
                     //   JOptionPane.showMessageDialog(null,msg,"SOLUTION",PLAIN_MESSAGE);
                     //   msg = "SOLUTION FOR THE GIVEN GAME IS<br>";
                   // }    */
                }
            }
     //       if(!"SOLUTION FOR THE GIVEN GAME IS<br>".equals(msg))
       //     {
            
            
            int minSubGame = 0;
            for(int i = 1; i < implementMixedStrategy.index; i++)
                if(implementMixedStrategy.subgamevalue[minSubGame] > implementMixedStrategy.subgamevalue[i])
                    minSubGame = i;
            
            msg += "<br>Since Sub-Game "+(minSubGame+1)+" has minimum Pay-Off value, Player B Plays Sub-Game "+(minSubGame+1)+"<br>";
            
                new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start();
                                }
                            }).start();
             //   JOptionPane.showMessageDialog(null,msg,"Solution",PLAIN_MESSAGE);
         //       msg = "SOLUTION FOR THE GIVEN GAME IS<br>";
         //   }
        }
        else if(n == 2)
        {
            int subgame[][] = new int[2][2];
            int count_subgame = 0;
      //      int count = 0;
            for(int i = 0; i < m-1; i++)
            {
                for(int j = i+1; j < m; j++)
                {
          //          count++;
                    msg += ("<br><u style='color:blue'>SubGame "+ (++count_subgame)+"</u> : (A"+(i+1)+",A"+(j+1)+")(B1,B2)<br>");
               //     msg += ("<br>SubGame "+ (++count_subgame)+"<br>");
               //     JOptionPane.show
                    subgame[0][0] = strategy[i][0]; 
                    subgame[0][1] = strategy[i][1];
                    subgame[1][0] = strategy[j][0];
                    subgame[1][1] = strategy[j][1];
                    
                    if(!checkDominance.compute(subgame))
                    {
                        computeMixedStrategy.compute(subgame,m,n);
                    }
                    
        /*            if(count > 3)
                    {
                        count = 0;
                         new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start();
                                }
                            }).start();
                        
                      //  JOptionPane.showMessageDialog(null,msg,"SOLUTION",PLAIN_MESSAGE);
                        msg = "SOLUTION FOR THE GIVEN GAME IS<br>";
                    }*/
                }
            }
      //      if(!"SOLUTION FOR THE GIVEN GAME IS<br>".equals(msg))
        //    {
            
            
            int minSubGame = 0;
            for(int i = 1; i < implementMixedStrategy.index; i++)
                if(implementMixedStrategy.subgamevalue[minSubGame] > implementMixedStrategy.subgamevalue[i])
                    minSubGame = i;
            
            msg += "<br>Since Sub-Game "+(minSubGame+1)+" has minimum Pay-Off value, Player A Plays Sub-Game "+(minSubGame+1)+"<br>";
            
                new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start();
                                }
                            }).start();
            //    JOptionPane.showMessageDialog(null,msg,"Solution",PLAIN_MESSAGE);
             //   msg = "SOLUTION FOR THE GIVEN GAME IS<br>";
        ////    }
        }
    }
    
}
