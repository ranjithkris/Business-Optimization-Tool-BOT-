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
public class implementCompare 
{
    private static final long serialVersionUID = 9220036263068464736L;
    static int nwcr_cost = 0;
    static int lcm_cost = 0;
    static int vam_cost = 0;
    static int nwcr_modi_cost = 0;
    static int lcm_modi_cost = 0;
    static int vam_modi_cost = 0;
    static boolean single_instance = false;
    
    
    public static void compute(int row,int col,int cost[][],int demand[],int supply[])
    {
        int m ,n;
        int c[][] = new int[row][col];
        int dem[] = new int[col];
        int sup[] = new int[row];
        
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        

        
        implementNWCR ob1 = new implementNWCR();
        ob1.compute(m,n,c,dem,sup);
        

       
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        
        implementLCM ob2 = new implementLCM();
        ob2.compute(m,n,c,dem,sup);
        
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        
        implementVAM ob3 = new implementVAM();
        ob3.compute(m,n,c,dem,sup);
        
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        
        implementModi.compute(m,n,c,dem,sup,0, false);
        
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        
        implementModi.compute(m,n,c,dem,sup,1, false);
        
        m = row;
        n = col;
        for(int ii = 0 ; ii<m; ii++)
        {
            System.arraycopy(cost[ii], 0, c[ii], 0, n);
        }
        System.arraycopy(supply, 0, sup, 0, m);
        System.arraycopy(demand, 0, dem, 0, n);
        
        implementModi.compute(m,n,c,dem,sup,2, false);
        

   
    Graph.draw();

    }
}
