package MainPackage.Game_mixedstrategy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ranjith
 */
public class gcd 
{
    private static final long serialVersionUID = 9220036263068464736L;
    public static int getGCD(int x,int y)
    {
        int r = 0, a, b;
        a = (x > y)? x : y;
        b = (x < y)? x : y;
        
        r = b;
        
        while((a % b) != 0)
        {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
}
