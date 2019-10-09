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
public class computeMixedStrategy 
{
    private static final long serialVersionUID = 9220036263068464736L;
    public static void compute(int strategy[][],int m,int n)
    {
        if(m == 2 && n == 2)
        {
            int p1_num = 0,p1_den = 0,p2_num = 0,p2_den = 0;
            int q1_num = 0,q1_den = 0,q2_num = 0,q2_den = 0; 
            int p_gcd = 0, q_gcd = 0;
            
            p1_num = strategy[1][1] - strategy[1][0];
            p1_den = strategy[0][0] - strategy[0][1] + strategy[1][1] - strategy[1][0];
            p_gcd = gcd.getGCD(p1_num, p1_den);
            p1_num = p1_num/p_gcd;
            p1_den = p1_den/p_gcd;
            
            p2_num = p1_den - p1_num;
            p2_den = p1_den;
            p_gcd = gcd.getGCD(p2_num, p2_den);
            p2_num = p2_num/p_gcd;
            p2_den = p2_den/p_gcd;
            
            q1_num = strategy[1][1] - strategy[0][1];
            q1_den = strategy[0][0] - strategy[1][0] + strategy[1][1] - strategy[0][1];
            q_gcd = gcd.getGCD(q1_num, q1_den);
            q1_num = q1_num/q_gcd;
            q1_den = q1_den/q_gcd;
            
            q2_num = q1_den - q1_num;
            q2_den = q1_den;
            q_gcd = gcd.getGCD(q2_num, q2_den);
            q2_num = q2_num/q_gcd;
            q2_den = q2_den/q_gcd;
            
            int A_payoff_num = 0, A_payoff_den = 0, B_payoff_num = 0, B_payoff_den = 0;
            A_payoff_num = (strategy[0][0] * p1_num) +  (strategy[1][0] * p2_num);
            A_payoff_den = p1_den;
            int A_gcd = 0, B_gcd = 0;
            A_gcd = gcd.getGCD(A_payoff_num, A_payoff_den);
            A_payoff_num = A_payoff_num/A_gcd;
            A_payoff_den = A_payoff_den/A_gcd;
            
            B_payoff_num = (strategy[0][0] * q1_num) +  (strategy[0][1] * q2_num);
            B_payoff_den = q1_den;
            B_gcd = gcd.getGCD(B_payoff_num, B_payoff_den);
            B_payoff_num = B_payoff_num/B_gcd;
            B_payoff_den = B_payoff_den/B_gcd;
            
            if(A_payoff_num == B_payoff_num && A_payoff_den == B_payoff_den)
            {
                implementMixedStrategy.msg += ("Player A<br>");
                implementMixedStrategy.msg += ("Strategy (A1,A2) is : (" + p1_num+"/"+p1_den + ","+ p2_num+"/"+p2_den+")<br>");
                implementMixedStrategy.msg += ("Player A's Payoff is : " + A_payoff_num+"/"+A_payoff_den+"<br>");
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
                
                implementMixedStrategy.msg += ("Player B<br>");
                implementMixedStrategy.msg += ("Strategy (B1,B2) is : (" + q1_num+"/"+q1_den + ","+ q2_num+"/"+q2_den+")<br>");
                implementMixedStrategy.msg += ("Player B's Payoff is : " + B_payoff_num+"/"+B_payoff_den+"<br>");
                implementMixedStrategy.subgamevalue[implementMixedStrategy.index++] = (double)B_payoff_num/B_payoff_den;
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
            }
        }
        else if(m == 2)
        {
            int p1_num = 0,p1_den = 0,p2_num = 0,p2_den = 0;
            int q1_num = 0,q1_den = 0,q2_num = 0,q2_den = 0; 
            int p_gcd = 0, q_gcd = 0;
            
            p1_num = strategy[1][1] - strategy[1][0];
            p1_den = strategy[0][0] - strategy[0][1] + strategy[1][1] - strategy[1][0];
            p_gcd = gcd.getGCD(p1_num, p1_den);
            p1_num = p1_num/p_gcd;
            p1_den = p1_den/p_gcd;
            
            p2_num = p1_den - p1_num;
            p2_den = p1_den;
            p_gcd = gcd.getGCD(p2_num, p2_den);
            p2_num = p2_num/p_gcd;
            p2_den = p2_den/p_gcd;
            
            q1_num = strategy[1][1] - strategy[0][1];
            q1_den = strategy[0][0] - strategy[1][0] + strategy[1][1] - strategy[0][1];
            q_gcd = gcd.getGCD(q1_num, q1_den);
            q1_num = q1_num/q_gcd;
            q1_den = q1_den/q_gcd;
            
            q2_num = q1_den - q1_num;
            q2_den = q1_den;
            q_gcd = gcd.getGCD(q2_num, q2_den);
            q2_num = q2_num/q_gcd;
            q2_den = q2_den/q_gcd;
            
            int A_payoff_num = 0, A_payoff_den = 0, B_payoff_num = 0, B_payoff_den = 0;
            A_payoff_num = (strategy[0][0] * p1_num) +  (strategy[1][0] * p2_num);
            A_payoff_den = p1_den;
            int A_gcd = 0, B_gcd = 0;
            A_gcd = gcd.getGCD(A_payoff_num, A_payoff_den);
            A_payoff_num = A_payoff_num/A_gcd;
            A_payoff_den = A_payoff_den/A_gcd;
            
            B_payoff_num = (strategy[0][0] * q1_num) +  (strategy[0][1] * q2_num);
            B_payoff_den = q1_den;
            B_gcd = gcd.getGCD(B_payoff_num, B_payoff_den);
            B_payoff_num = B_payoff_num/B_gcd;
            B_payoff_den = B_payoff_den/B_gcd;
            
            if(A_payoff_num == B_payoff_num && A_payoff_den == B_payoff_den)
            {
                implementMixedStrategy.msg += ("Player A<br>");
                implementMixedStrategy.msg += ("Strategy (A1,A2) is : (" + p1_num+"/"+p1_den + ","+ p2_num+"/"+p2_den+")<br>");
                implementMixedStrategy.msg += ("Player A's Payoff is : " + A_payoff_num+"/"+A_payoff_den+"<br>");
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
                
                implementMixedStrategy.msg += ("Player B<br>");
                implementMixedStrategy.msg += ("Strategy (B1,B2) is : (" + q1_num+"/"+q1_den + ","+ q2_num+"/"+q2_den+")<br>");
                implementMixedStrategy.msg += ("Player B's Payoff is : " + B_payoff_num+"/"+B_payoff_den+"<br>");
                implementMixedStrategy.subgamevalue[implementMixedStrategy.index++] = (double)B_payoff_num/B_payoff_den;
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
            }
        }
        else if(n == 2)
        {
            int p1_num = 0,p1_den = 0,p2_num = 0,p2_den = 0;
            int q1_num = 0,q1_den = 0,q2_num = 0,q2_den = 0; 
            int p_gcd = 0, q_gcd = 0;
            
            p1_num = strategy[1][1] - strategy[1][0];
            p1_den = strategy[0][0] - strategy[0][1] + strategy[1][1] - strategy[1][0];
            p_gcd = gcd.getGCD(p1_num, p1_den);
            p1_num = p1_num/p_gcd;
            p1_den = p1_den/p_gcd;
            
            p2_num = p1_den - p1_num;
            p2_den = p1_den;
            p_gcd = gcd.getGCD(p2_num, p2_den);
            p2_num = p2_num/p_gcd;
            p2_den = p2_den/p_gcd;
            
            q1_num = strategy[1][1] - strategy[0][1];
            q1_den = strategy[0][0] - strategy[1][0] + strategy[1][1] - strategy[0][1];
            q_gcd = gcd.getGCD(q1_num, q1_den);
            q1_num = q1_num/q_gcd;
            q1_den = q1_den/q_gcd;
            
            q2_num = q1_den - q1_num;
            q2_den = q1_den;
            q_gcd = gcd.getGCD(q2_num, q2_den);
            q2_num = q2_num/q_gcd;
            q2_den = q2_den/q_gcd;
            
            int A_payoff_num = 0, A_payoff_den = 0, B_payoff_num = 0, B_payoff_den = 0;
            A_payoff_num = (strategy[0][0] * p1_num) +  (strategy[1][0] * p2_num);
            A_payoff_den = p1_den;
            int A_gcd = 0, B_gcd = 0;
            A_gcd = gcd.getGCD(A_payoff_num, A_payoff_den);
            A_payoff_num = A_payoff_num/A_gcd;
            A_payoff_den = A_payoff_den/A_gcd;
            
            B_payoff_num = (strategy[0][0] * q1_num) +  (strategy[0][1] * q2_num);
            B_payoff_den = q1_den;
            B_gcd = gcd.getGCD(B_payoff_num, B_payoff_den);
            B_payoff_num = B_payoff_num/B_gcd;
            B_payoff_den = B_payoff_den/B_gcd;
            
            if(A_payoff_num == B_payoff_num && A_payoff_den == B_payoff_den)
            {
                implementMixedStrategy.msg += ("Player A<br>");
                implementMixedStrategy.msg += ("Strategy (A1,A2) is : (" + p1_num+"/"+p1_den + ","+ p2_num+"/"+p2_den+")<br>");
                implementMixedStrategy.msg += ("Player A's Payoff is : " + A_payoff_num+"/"+A_payoff_den+"<br>");
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
                
                implementMixedStrategy.msg += ("Player B<br>");
                implementMixedStrategy.msg += ("Strategy (B1,B2) is : (" + q1_num+"/"+q1_den + ","+ q2_num+"/"+q2_den+")<br>");
                implementMixedStrategy.msg += ("Player B's Payoff is : " + B_payoff_num+"/"+B_payoff_den+"<br>");
                implementMixedStrategy.subgamevalue[implementMixedStrategy.index++] = (double)B_payoff_num/B_payoff_den;
            //    JOptionPane.showMessageDialog(null,implementMixedStrategy.msg);
            }
        }
        
    }
}
