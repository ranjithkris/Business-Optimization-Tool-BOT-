package MainPackage.Lpp;

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
public class implementLpp 
{
    private static final long serialVersionUID = 9220036263068464736L;
    static int x_max = 0,y_max = 0,numberOfCon = 0,numberOfVar = 0;
    static boolean flag = true;
    static int check = 0;
    static int num_intersection = 0;
    public static float coordinate[][] = new float[100][100];
    static String msg;
    
    public boolean check_feasibility(int constraint[][],int con_rhs[],float x,float y)
    {
        for(int i = 0;i<numberOfCon;i++)
        {
            if(LppInput.constraintEq[i].getSelectedIndex() == 0)
            {
                if(!(((constraint[i][0]*x)+(constraint[i][1]*y)) <= con_rhs[i]))
                    return false;
            }
            else if(LppInput.constraintEq[i].getSelectedIndex() == 1)
            {
                if(!(((constraint[i][0]*x)+(constraint[i][1]*y)) == con_rhs[i]))
                    return false;
            }
            else if(LppInput.constraintEq[i].getSelectedIndex() == 2)
            {
                if(!(((constraint[i][0]*x)+(constraint[i][1]*y)) >= con_rhs[i]))
                    return false;
            }
        }
        return true;
    }
    
    public void compute(int objective[],int constraint[][],int con_rhs[],int num_IndVar,int num_Con,int con_equation[])
    {
        x_max = 0;y_max = 0;numberOfCon = 0;numberOfVar = 0;
    flag = true;
    check = 0;
    num_intersection = 0;
    for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j++)
            {
                coordinate[i][j] =  0;
            }
        }
    
        numberOfCon = num_Con;
        numberOfVar = num_IndVar;
        
        msg = "<html><u>Objective Function</u>: ";
        
        if(LppInput.isMaximizing)
            msg += "<p style=color:'blue'>&emsp;Maximize Z = ";
        else
            msg += "<p style=color:'blue'>&emsp;Minimize Z = ";
        
        if(Integer.parseInt(LppInput.inputObjectFunc[0].getText()) != 0)
        {
            msg += LppInput.inputObjectFunc[0].getText()+"x ";
        
            if(Integer.parseInt(LppInput.inputObjectFunc[1].getText()) < 0)
                msg += "- "+(-Integer.parseInt(LppInput.inputObjectFunc[1].getText()))+"y";
            else if(Integer.parseInt(LppInput.inputObjectFunc[1].getText()) > 0)
                msg += "+ "+Integer.parseInt(LppInput.inputObjectFunc[1].getText())+"y";
        }
        else
        {
                msg += Integer.parseInt(LppInput.inputObjectFunc[1].getText())+"y";
        }
        msg += "</p><br><u>Subjected to constraints</u>:\n";
        
        for(int i = 0;i<LppInput.numOfConstraints;i++)
        {
            msg += "<p style=color:'blue'>&emsp;";
            if(constraint[i][0] != 0)
            {
                msg += constraint[i][0]+"x ";
                
                if(constraint[i][1] < 0)
                    msg += "- "+(-constraint[i][1])+"y ";
                else if(constraint[i][1] > 0)
                    msg += "+ "+constraint[i][1]+"y ";
            }
            else
            {
                    msg += constraint[i][1]+"y ";
            }
            
            if(LppInput.constraintEq[i].getSelectedIndex() == 0)
                msg += '\u2264'+" ";
            else if(LppInput.constraintEq[i].getSelectedIndex() == 1)
                msg += "= ";
            else if(LppInput.constraintEq[i].getSelectedIndex() == 2)
                msg += '\u2265'+" ";
            
            msg += con_rhs[i]+"<br>";
        }
        
        msg += "</p><br><u>Non-Negativity constraint</u>:<br><html><p style=color:'blue'>&emsp; x , y "+'\u2265'+" 0</p>";
        msg += "<p style=color:'red'><u>Solution</u>:<br><br></p>";
        
        
        //Finding co-ordinates of each constraints
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j++)
            {
                if(j==0)
                {
                    coordinate[i][j] = 0;
                }
                else if(j==1)
                {
                    if(constraint[i][1] != 0)
                    {
                        coordinate[i][j] = (float)con_rhs[i]/constraint[i][1];
                    }
                    else
                    {
                        coordinate[i][0] = (float)con_rhs[i]/constraint[i][0];
                        coordinate[i][j] = -999;
                    }
                }
                else if(j==2)
                {
                    if(constraint[i][0] != 0)
                    {
                        coordinate[i][j] = (float)con_rhs[i]/constraint[i][0];
                    }
                    else
                    {
                        coordinate[i][3] = (float)con_rhs[i]/constraint[i][1];
                        coordinate[i][j] = -999;
                        j++;
                    }
                    
                }
                else if(j==3)
                {
                    coordinate[i][j] = 0;
                }
        //        System.out.println("Doubt "+coordinate[i][j]);
            }
            msg += "&emsp;<u>Constraint " + (i+1)+"</u>:  &nbsp;&nbsp;&nbsp;"; 
         
        
            if(constraint[i][0] != 0)
            {
                msg += constraint[i][0]+"x ";
                
                if(constraint[i][1] < 0)
                    msg += "- "+(-constraint[i][1])+"y ";
                else if(constraint[i][1] > 0)
                    msg += "+ "+constraint[i][1]+"y ";
            }
            else
            {
                msg += constraint[i][1]+"y ";
            }
            
            if(LppInput.constraintEq[i].getSelectedIndex() == 0)
                msg += '\u2264'+" ";
            else if(LppInput.constraintEq[i].getSelectedIndex() == 1)
                msg += "= ";
            else if(LppInput.constraintEq[i].getSelectedIndex() == 2)
                msg += '\u2265'+" ";
            
            msg += con_rhs[i]+"<br><br>";
            if(coordinate[i][1] != -999.0 && coordinate[i][2] != -999.0)
            {
                msg += "&emsp;&emsp;(" + coordinate[i][0]+","+coordinate[i][1]+")    "+" ("+ coordinate[i][2]+","+coordinate[i][3]+")<br><br>";
            }
            else if(coordinate[i][1] == -999.0)
            {
                msg += "&emsp;&emsp;"+"("+ coordinate[i][2]+","+coordinate[i][3]+")<br><br>";
            }
            else if(coordinate[i][2] == -999.0)
            {
                msg += "&emsp;&emsp;"+"("+ coordinate[i][0]+","+coordinate[i][1]+")<br><br>";
            }
        }
        
        
        float original[][] = new float[num_Con][num_IndVar*2];
        
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j++)
            {
                original[i][j] = coordinate[i][j];
            }
        }
        
        
        //Finding maximum value for X and Y axis
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j++)
            {
                if(j==0)
                {
                    if(x_max<coordinate[i][j])
                        x_max = (int)coordinate[i][j] + 1;
                }
                else if(j==1)
                {
                    if(y_max<coordinate[i][j])
                        y_max = (int)coordinate[i][j] + 1;
                }
                else if(j==2)
                {
                    if(x_max<coordinate[i][j])
                        x_max = (int)coordinate[i][j] + 1;
                }
                else if(j==3)
                {
                    if(y_max<coordinate[i][j])
                        y_max = (int)coordinate[i][j] + 1;
                }
            }
        }
        
        if(x_max == 0 && y_max == 0)
        {
            x_max = 10;
            y_max = 10;
        }
        else if(x_max == 0)
        {
            x_max = y_max;
        }
        else if(y_max == 0)
        {
            y_max = x_max;
        }
        
    //    System.out.println("coordinate[0][0] = " + coordinate[0][2]);
        for(int i = 0;i<numberOfCon;i++)
        {
            if((coordinate[i][0] == 0) && (coordinate[i][1] == 0) && (coordinate[i][2] == 0) && (coordinate[i][3] == 0))
            {
                coordinate[i][2] = x_max;
                coordinate[i][3] = y_max;
            }
        }
        
    //    System.out.println("coordinate[1][0] = " + coordinate[0][2]);
        //Extending the straight line
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j++)
            {
                if(j==1)
                {
                    if(coordinate[i][j] == -999)
                    {
                        coordinate[i][j] = y_max;
                        y_max+=1;
                    }
                }
                else if(j==2)
                {
                    if(coordinate[i][j] == -999)
                    {
                        coordinate[i][j] = x_max;
                        x_max+=1;
                    }
                }
            }
        }        
        
    //    System.out.println("coordinate[2][0] = " + coordinate[0][2]);

      
        //Finding the slope for each line
        float slope[][] = new float[numberOfCon][2];
        for(int i = 0;i<numberOfCon;i++)
        {
            int k = 0;
            float denominator = (float)Math.sqrt((Math.pow((coordinate[i][k+2]-coordinate[i][k]), 2)+(Math.pow((coordinate[i][k+3]-coordinate[i][k+1]), 2))));
            slope[i][0] = (float)(coordinate[i][k+2]-coordinate[i][k])/denominator;
            slope[i][1] = (float)(coordinate[i][k+3]-coordinate[i][k+1])/denominator;
     //       System.out.println("slope[i][0] : " + slope[i][0]);
     //       System.out.println("slope[i][1] : " + slope[i][1]);
        }
        
        
            
        //Finding the intersection of all lines
        num_intersection = (numberOfCon*(numberOfCon-1))/2;
        float intersection[][] = new float[num_intersection][2];
        int intersection_array[][] = new int[numberOfCon][numberOfCon];
        int index=0;
        
        for(int i = 0;i<numberOfCon-1;i++)
        {
            intersection_array[i][i] = 0;
        }
        
        for(int i = 0;i<numberOfCon-1;i++)
        {
            for(int j = i+1;j<numberOfCon;j++)
            {
                
                if(constraint[i][0] != 0 && constraint[i][1] != 0 && constraint[j][0] != 0 && constraint[j][1] != 0)
                {
                    intersection[index][0] = (float)((con_rhs[i]*constraint[j][1])-(constraint[i][1]*con_rhs[j]))/((constraint[j][1]*constraint[i][0])-(constraint[i][1]*constraint[j][0]));
                    intersection[index][1] = (float)((con_rhs[i])-(constraint[i][0]*intersection[index][0]))/(constraint[i][1]);
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] == 0 && constraint[i][1] != 0 && constraint[j][0] != 0 && constraint[j][1] != 0)
                {
                    intersection[index][1] = (float)con_rhs[i]/constraint[i][1];
                    intersection[index][0] = (float)((con_rhs[j])-(constraint[j][1]*intersection[index][1]))/(constraint[j][0]);
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] != 0 && constraint[i][1] == 0 && constraint[j][0] != 0 && constraint[j][1] != 0)
                {
                    intersection[index][0] = (float)con_rhs[i]/constraint[i][0];
                    intersection[index][1] = (float)((con_rhs[j])-(constraint[j][0]*intersection[index][0]))/(constraint[j][1]);
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] != 0 && constraint[i][1] != 0 && constraint[j][0] == 0 && constraint[j][1] != 0)
                {
                    intersection[index][1] = (float)con_rhs[j]/constraint[j][1];
                    intersection[index][0] = (float)((con_rhs[i])-(constraint[i][1]*intersection[index][1]))/(constraint[i][0]);
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] != 0 && constraint[i][1] != 0 && constraint[j][0] != 0 && constraint[j][1] == 0)
                {
                    intersection[index][0] = (float)con_rhs[j]/constraint[j][0];
                    intersection[index][1] = (float)((con_rhs[i])-(constraint[i][0]*intersection[index][0]))/(constraint[i][1]);
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] == 0 && constraint[i][1] != 0 && constraint[j][0] != 0 && constraint[j][1] == 0)
                {
                    intersection[index][1] = (float)con_rhs[i]/constraint[i][1];
                    intersection[index][0] = (float)con_rhs[j]/constraint[j][0];
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
                else if(constraint[i][0] != 0 && constraint[i][1] == 0 && constraint[j][0] == 0 && constraint[j][1] != 0)
                {
                    intersection[index][0] = (float)con_rhs[i]/constraint[i][0];
                    intersection[index][1] = (float)con_rhs[j]/constraint[j][1];
                    intersection_array[i][j] = intersection_array[j][i] = index++;
                }
            }
        }

        
        int count = 0;
        for(int i = 0;i<index;i++)
        {
            if(!(intersection[i][0] == Double.NEGATIVE_INFINITY && intersection[i][1] == Double.NEGATIVE_INFINITY))
                count++;
        }
   //     System.out.println("count = " + count);
        
        //Clipping the negative line and extending the lines for intersection

        for(int i = 0;i<numberOfCon;i++)
        {
            if(coordinate[i][1] < 0 && coordinate[i][2] > 0)
            {
                coordinate[i][0] = coordinate[i][2];
                coordinate[i][1] = coordinate[i][3];
                float x_axis_max = 0,y_axis_max = 0;
                int x_x_index_i = 0, x_y_index_j=0;
                int y_x_index_i = 0, y_y_index_j=0;
                
                for(int j = 0;j<numberOfCon;j++)
                {
                    if(i!=j)
                    {
                        if(x_axis_max < intersection[intersection_array[i][j]][0])
                        {
                            x_axis_max = intersection[intersection_array[i][j]][0];
                            x_x_index_i = i;
                            x_y_index_j = j;
                        }
                        
                        if(y_axis_max < intersection[intersection_array[i][j]][1])
                        {
                            y_axis_max = intersection[intersection_array[i][j]][1];
                            y_x_index_i = i;
                            y_y_index_j = j;
                        }
                    }
                }
                
                if(x_axis_max > y_axis_max)
                {
                    if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                }
                else if(x_axis_max < y_axis_max)
                {
                    if(intersection[intersection_array[y_x_index_i][y_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[y_x_index_i][y_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[y_x_index_i][y_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[y_x_index_i][y_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                }
                else
                {
                    if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*-slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][2] = coordinate[i][2]+((x_max)*slope[i][0]);
                            coordinate[i][3] = coordinate[i][3]+((x_max)*slope[i][1]);
                        }
                    }
                }
                
            }
        }
        
        
        for(int i = 0;i<numberOfCon;i++)
        {
            if(coordinate[i][2] < 0 && coordinate[i][1] > 0)
            {
                coordinate[i][2] = coordinate[i][0];
                coordinate[i][3] = coordinate[i][1];
                float x_axis_max = 0,y_axis_max = 0;
                int x_x_index_i = 0, x_y_index_j=0;
                int y_x_index_i = 0, y_y_index_j=0;
                
                for(int j = 0;j<numberOfCon;j++)
                {
                    if(i!=j)
                    {
                        if(x_axis_max < intersection[intersection_array[i][j]][0])
                        {
                            x_axis_max = intersection[intersection_array[i][j]][0];
                            x_x_index_i = i;
                            x_y_index_j = j;
                        }
                        
                        if(y_axis_max < intersection[intersection_array[i][j]][1])
                        {
                            y_axis_max = intersection[intersection_array[i][j]][1];
                            y_x_index_i = i;
                            y_y_index_j = j;
                        }
                    }
                }
                
                if(x_axis_max > y_axis_max)
                {
                    if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double. NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                    
                }
                else if(x_axis_max < y_axis_max)
                {
                    if(intersection[intersection_array[y_x_index_i][y_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[y_x_index_i][y_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[y_x_index_i][y_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[y_x_index_i][y_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[y_x_index_i][y_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                }
                else
                {
                    if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY && intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double. NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][0] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = intersection[intersection_array[x_x_index_i][x_y_index_j]][0]+((x_max/3)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                    else if(intersection[intersection_array[x_x_index_i][x_y_index_j]][1] != Double.NEGATIVE_INFINITY)
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = intersection[intersection_array[x_x_index_i][x_y_index_j]][1]+((x_max/3)*slope[i][1]);
                        }
                    }
                    else
                    {
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else if(slope[i][0] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*-slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                        else if(slope[i][1] < 0)
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*-slope[i][1]);
                        }
                        else
                        {
                            coordinate[i][0] = coordinate[i][0]+((x_max)*slope[i][0]);
                            coordinate[i][1] = coordinate[i][1]+((x_max)*slope[i][1]);
                        }
                    }
                }
                
            }
        }

    
        for(int i = 0;i<numberOfCon;i++)
        {
            if(coordinate[i][2] < 0 && coordinate[i][1] > 0)
            {
                coordinate[i][2] = coordinate[i][0];
                coordinate[i][3] = coordinate[i][1];
        //       System.out.println("coordinate[4][0] = " + coordinate[0][2]);
          //      System.out.println("coordinate[4][0] = " + coordinate[0][3]);
                
                coordinate[i][0] = (x_max*2)+((x_max/3)*slope[i][0]);
                coordinate[i][1] = (y_max*2)+((x_max/3)*slope[i][1]);
        //        System.out.println("coordinate[5][0] = " + x_max);
          //      System.out.println("coordinate[5][0] = " + y_max);
                               
            }
        }
   
        //Finding the feasible region
        float feasible_region[][] = new float[100][2];
        float infeasible_region[][] = new float[100][2];
        int num_points_in_feasible_region = 0;
        int num_points_in_infeasible_region = 0;
        
        if(check_feasibility(constraint,con_rhs,0,0))
        {
            feasible_region[num_points_in_feasible_region][0] = (float)0;
            feasible_region[num_points_in_feasible_region++][1] = (float)0;
        }
        else
        {
            infeasible_region[num_points_in_infeasible_region][0] = (float)0;
            infeasible_region[num_points_in_infeasible_region++][1] = (float)0;
        }
        
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j=j+2)
            {
                if(check_feasibility(constraint,con_rhs,coordinate[i][j],coordinate[i][j+1]))
                {
                    feasible_region[num_points_in_feasible_region][0] = (float)coordinate[i][j];
                    feasible_region[num_points_in_feasible_region++][1] = (float)coordinate[i][j+1];
                }
                else
                {
                    infeasible_region[num_points_in_infeasible_region][0] = (float)coordinate[i][j];
                    infeasible_region[num_points_in_infeasible_region++][1] = (float)coordinate[i][j+1];
                }
            }
        }
        
        for(int i = 0;i<num_intersection;i++)
        {
            if(intersection[i][0] != Double.NEGATIVE_INFINITY && intersection[i][1] != Double.NEGATIVE_INFINITY)
            {
                if(check_feasibility(constraint,con_rhs,intersection[i][0],intersection[i][1]))
                {
                    feasible_region[num_points_in_feasible_region][0] = (float)intersection[i][0];
                    feasible_region[num_points_in_feasible_region++][1] = (float)intersection[i][1];
                }
                else
                {
                    infeasible_region[num_points_in_infeasible_region][0] = (float)intersection[i][0];
                    infeasible_region[num_points_in_infeasible_region++][1] = (float)intersection[i][1];
                }
            }        
        }
        
        
        
        
    //    System.out.println("Real intersection");
     //   for(int i = 0;i<num_intersection;i++)
       //     System.out.println(intersection[i][0]+" "+intersection[i][1]);
        
        
        
        
        
        
        //Finding Infeasible problem
        
        boolean infeasible_flag = true;
        for(int i = 0;i<numberOfCon;i++)
        {
            for(int j=0;j<numberOfVar*2;j=j+2)
            {
                if(check_feasibility(constraint,con_rhs,coordinate[i][j],coordinate[i][j+1]))
                    infeasible_flag = false;
            }
        }
        
        if(infeasible_flag)
        {
            for(int i = 0;i<index;i++)
            {
                if(check_feasibility(constraint,con_rhs,intersection[i][0],intersection[i][1]))
                    infeasible_flag = false;
            }
        }
        
        if(!infeasible_flag)
        {
            //unbounded checking
            boolean unbounded_check1 = true;
            boolean unbounded_check2 = false;
            for(int i = 1;i<50;i++)
            {
                if(!check_feasibility(constraint,con_rhs,(1000*i),(1000*i)))
                {
                    unbounded_check1 = false;
                }
            }
        
            for(int i = 0;i<numberOfCon;i++)
            {
                    for(int k=1;k<50;k++)
                    {
                        float x,y;
                        if(slope[i][0] < 0 && slope[i][1] < 0)
                        {
                            x = (float)(coordinate[i][2]+((1000*k)*-slope[i][0]));
                            y = (float)(coordinate[i][3]+((1000*k)*-slope[i][1]));
                        }
                        else if(slope[i][0] < 0)
                        {
                            x = (float)(coordinate[i][2]+((1000*k)*-slope[i][0]));
                            y = (float)(coordinate[i][3]+((1000*k)*slope[i][1]));
                        }
                        else if(slope[i][1] < 0)
                        {
                            x = (float)(coordinate[i][2]+((1000*k)*slope[i][0]));
                            y = (float)(coordinate[i][3]+((1000*k)*-slope[i][1]));
                        }
                        else
                        {
                            x = (float)(coordinate[i][2]+((1000*k)*slope[i][0]));
                            y = (float)(coordinate[i][3]+((1000*k)*slope[i][1]));
                        }
                            
                        
                        if(check_feasibility(constraint,con_rhs,x,y))
                        {
                            if(k == 49)
                            {
                                unbounded_check2 = true;
                                break;
                            }
                        }
                        
                    }
            }
        
            if((unbounded_check1 || unbounded_check2) && LppInput.isMaximizing)
            {
                float obj_value=0;
                float cur_obj_value[] = new float[num_points_in_feasible_region];
          
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {
                        cur_obj_value[i] = (feasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    }
             
                
                msg += "<html>&emsp;"
                        + "<table border=1>"
                        + "<tr align=center>"
                        + "<td>Co-ordinates (x)</td>"
                        + "<td>Co-ordinates (y)</td>"
                        + "<td>Value of the objective function (Z)</td>"
                        + "</tr>";
                if(num_points_in_feasible_region > 0)
                {
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {
                            msg += "<tr bgcolor='white'>"
                                + "<td>"+feasible_region[i][0]+"</td>"
                                + "<td>"+feasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                    }
                }    
                    
                    float cur_infeasible_obj_value[] = new float[num_points_in_infeasible_region];

                    for(int i = 0;i<num_points_in_infeasible_region;i++)
                    {
                        cur_infeasible_obj_value[i] = (infeasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (infeasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    }
                    
                    
                    if(num_points_in_infeasible_region > 0)
                    {
                        for(int i = 0;i<num_points_in_infeasible_region;i++)
                        {   
                            msg += "<tr bgcolor='red'>"
                                + "<td>"+infeasible_region[i][0]+"</td>"
                                + "<td>"+infeasible_region[i][1]+"</td>"
                                + "<td>"+cur_infeasible_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                    }
                    
                    msg += "</table>";
                msg += ("<br>Given LPP is a unbounded problem");
            }
            else if((unbounded_check1 || unbounded_check2) && !LppInput.isMaximizing)
            {
                float obj_value=0;
                float cur_obj_value[] = new float[num_points_in_feasible_region];
                
                
                    obj_value = (feasible_region[0][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[0][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {
                        cur_obj_value[i] = (feasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                        if(obj_value > cur_obj_value[i])
                            obj_value = cur_obj_value[i];
                    }
                
    //            System.out.println(obj_value);
                
                int sol_index=0;;
                msg += "<html>&emsp;"
                        + "<table border=1>"
                        + "<tr align=center>"
                        + "<td>Co-ordinates (x)</td>"
                        + "<td>Co-ordinates (y)</td>"
                        + "<td>Value of the objective function (Z)</td>"
                        + "</tr>";
                if(num_points_in_feasible_region > 0)
                {
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {   
                        if(cur_obj_value[i] == obj_value)
                        {
                            sol_index = i;
                            msg += "<tr bgcolor='green'>"
                                + "<td>"+feasible_region[i][0]+"</td>"
                                + "<td>"+feasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                        else
                        {
                            msg += "<tr bgcolor='white'>"
                                + "<td>"+feasible_region[i][0]+"</td>"
                                + "<td>"+feasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                    }
                    
                    
                    float cur_infeasible_obj_value[] = new float[num_points_in_infeasible_region];

                    for(int i = 0;i<num_points_in_infeasible_region;i++)
                    {
                        cur_infeasible_obj_value[i] = (infeasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (infeasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    }
                    
                    
                    if(num_points_in_infeasible_region > 0)
                    {
                        for(int i = 0;i<num_points_in_infeasible_region;i++)
                        {   
                            msg += "<tr bgcolor='red'>"
                                + "<td>"+infeasible_region[i][0]+"</td>"
                                + "<td>"+infeasible_region[i][1]+"</td>"
                                + "<td>"+cur_infeasible_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                    }
                    
                    msg += "</table>";
                    
                    msg += "<br>Therefore the solution is "+obj_value+" at x="+feasible_region[sol_index][0]+" and y="+feasible_region[sol_index][1];
                }
            }
            else
            {
                float obj_value=0;
                float cur_obj_value[] = new float[num_points_in_feasible_region];
                if(LppInput.isMaximizing)
                {
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {
                        cur_obj_value[i] = (feasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                        if(obj_value < cur_obj_value[i])
                            obj_value = cur_obj_value[i];
                    }
                }
                else
                {
                    obj_value = (feasible_region[0][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[0][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {
                        cur_obj_value[i] = (feasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (feasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                        if(obj_value > cur_obj_value[i])
                            obj_value = cur_obj_value[i];
                    }
                }
        //        System.out.println(obj_value);
                
                int sol_index=0;;
                msg += "<html>&emsp;"
                        + "<table border=1>"
                        + "<tr align=center>"
                        + "<td>Co-ordinates (x)</td>"
                        + "<td>Co-ordinates (y)</td>"
                        + "<td>Value of the objective function (Z)</td>"
                        + "</tr>";
                if(num_points_in_feasible_region > 0)
                {
                    for(int i = 0;i<num_points_in_feasible_region;i++)
                    {   
                        if(cur_obj_value[i] == obj_value)
                        {
                            sol_index = i;
                            msg += "<tr bgcolor='green'>"
                                + "<td>"+feasible_region[i][0]+"</td>"
                                + "<td>"+feasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                        else
                        {
                            msg += "<tr bgcolor='white'>"
                                + "<td>"+feasible_region[i][0]+"</td>"
                                + "<td>"+feasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                    }
                    
                    
                    float cur_infeasible_obj_value[] = new float[num_points_in_infeasible_region];

                    for(int i = 0;i<num_points_in_infeasible_region;i++)
                    {
                        cur_infeasible_obj_value[i] = (infeasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (infeasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    }
                    
                    
                    if(num_points_in_infeasible_region > 0)
                    {
                        for(int i = 0;i<num_points_in_infeasible_region;i++)
                        {   
                            msg += "<tr bgcolor='red'>"
                                + "<td>"+infeasible_region[i][0]+"</td>"
                                + "<td>"+infeasible_region[i][1]+"</td>"
                                + "<td>"+cur_infeasible_obj_value[i]+"</td>";
                            msg += "</tr>";
                        }
                    }
                    
                    msg += "</table>";
                    
                    msg += "<br>Therefore the solution is "+obj_value+" at x="+feasible_region[sol_index][0]+" and y="+feasible_region[sol_index][1];
                }
            }
        }
        else
        {
                float cur_obj_value[] = new float[num_points_in_infeasible_region];

                    for(int i = 0;i<num_points_in_infeasible_region;i++)
                    {
                        cur_obj_value[i] = (infeasible_region[i][0] * Integer.parseInt(LppInput.inputObjectFunc[0].getText())) + (infeasible_region[i][1] * Integer.parseInt(LppInput.inputObjectFunc[1].getText()));
                    }
            
                msg += "<html>&emsp;"
                        + "<table border=1>"
                        + "<tr align=center>"
                        + "<td>Co-ordinates (x)</td>"
                        + "<td>Co-ordinates (y)</td>"
                        + "<td>Value of the objective function (Z)</td>"
                        + "</tr>";
                if(num_points_in_infeasible_region > 0)
                {
                    for(int i = 0;i<num_points_in_infeasible_region;i++)
                    {   
                            msg += "<tr bgcolor='red'>"
                                + "<td>"+infeasible_region[i][0]+"</td>"
                                + "<td>"+infeasible_region[i][1]+"</td>"
                                + "<td>"+cur_obj_value[i]+"</td>";
                            msg += "</tr>";
                        
                        
                                
        //                System.out.println(infeasible_region[i][0]+" "+infeasible_region[i][1]);
                    }
                    msg += "</table>";
                }
            msg += "<br>Given LPP is a Infeasible solution";
        }

     /*   
        
        boolean initial_flag = true;
        for(int i = 0;i<numberOfCon-1;i++)
        {
            
            for(int j = i+1;j<numberOfCon;j++)
            {
                flag = true;
                int k=0;
                

                float s1_x, s1_y, s2_x, s2_y;
                s1_x = coordinate[i][k+2] - coordinate[i][k];     
                s1_y = coordinate[i][k+3] - coordinate[i][k+1];
                s2_x = coordinate[j][k+2] - coordinate[j][k];     
                s2_y = coordinate[j][k+3] - coordinate[j][k+1];

                float s, t;
                s = (-s1_y * (coordinate[i][k] - coordinate[j][k]) + s1_x * (coordinate[i][k+1] - coordinate[j][k+1])) / (-s2_x * s1_y + s1_x * s2_y);
                t = ( s2_x * (coordinate[i][k+1] - coordinate[j][k+1]) - s2_y * (coordinate[i][k] - coordinate[j][k])) / (-s2_x * s1_y + s1_x * s2_y);

                if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
                {
                        intersection[index][0] = coordinate[i][k] + (t * s1_x);
                        intersection[index++][1] = coordinate[i][k+1] + (t * s1_y);
                        
                        
                }
            }
        }

    //    System.out.println("Intersection");
        for(int i = 0;i<numberOfCon;i++)
        {
            System.out.println(intersection[i][0]+" "+intersection[i][1]);
        }
    
        
        */

        
    /*    
        for(int i = 0;i<numberOfCon;i++)
        {
            System.out.println("Constraint "+(i+1));
            for(int j=0;j<numberOfVar*2;j++)
            {
                System.out.print(coordinate[i][j]+" ");
            }
            System.out.println("");
        }
     */   
       
      //  Graph.draw(coordinate);
        
        new Thread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    display.start(new String[]{});
                                }
                            }).start();
        
       
        
        
    }
}
