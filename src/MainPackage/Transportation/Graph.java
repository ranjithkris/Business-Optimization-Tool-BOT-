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

import MainPackage.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 * @author www.codejava.net
 *
 */
public class Graph extends JFrame {
    
    private static final long serialVersionUID = 9220036263068464736L;
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    static float coordinate[][] = new float[100][100];
    static Graph ob = new Graph();
 
    public Graph() {
        super("SOLUTION");
 
        JPanel chartPanel = createChartPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Graph Window",TitledBorder.LEFT,TitledBorder.CENTER));
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon ico_img=new ImageIcon(MainWindow.class.getResource("bot.png"));
        try
        {
            setIconImage(ico_img.getImage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
 
    private JPanel createChartPanel()
    {
        String chartTitle = "GRAPH";
    String yAxisLabel = "Total Cost";

 createDataset();
    JFreeChart chart = ChartFactory.createBarChart(chartTitle, null, yAxisLabel, dataset);

   
    return new ChartPanel(chart);
    }
 
    private void createDataset() {
        
        
    Comparable colkey1 = "Initial SOlution";
    Comparable colkey2 = "Optimality Test using MODI";
 

    dataset.addValue(implementCompare.nwcr_cost, (Comparable)("NWCR("+implementCompare.nwcr_cost+")"), colkey1);
    dataset.addValue(implementCompare.lcm_cost, (Comparable)("LCM("+implementCompare.lcm_cost+")"), colkey1);
    dataset.addValue(implementCompare.vam_cost, (Comparable)("VAM("+implementCompare.vam_cost+")"), colkey1);
    dataset.addValue(implementCompare.nwcr_modi_cost, (Comparable)("MODI_NWCR("+implementCompare.nwcr_modi_cost+")"), colkey2);
    dataset.addValue(implementCompare.lcm_modi_cost, (Comparable)("MODI_LCM("+implementCompare.lcm_modi_cost+")"), colkey2);
    dataset.addValue(implementCompare.vam_modi_cost, (Comparable)("MODI_VAM("+implementCompare.vam_modi_cost+")"), colkey2);
    
   

    }
    
    
    
    public static void draw()
    {
        ob = new Graph();
        ob.setVisible(true);
        
    }
    
    public static void close()
    {
        ob.dispose();
    }
}