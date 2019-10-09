package MainPackage.Lpp;

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
    static float coordinate[][] = new float[100][100];
 
    public Graph()
    {
        super("SOLUTION");
    }
    
    public void draw(float c[][]) {
        
        
        
        for(int i = 0;i<implementLpp.numberOfCon;i++)
                {
                    for(int j=0;j<implementLpp.numberOfVar*2;j++)
                    {
                        coordinate[i][j] = c[i][j];
                    }
                }
        
        
 
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
        // creates a line chart object
        // returns the chart panel
        String chartTitle = "GRAPH";
    String xAxisLabel = "X";
    String yAxisLabel = "Y";
 
    XYDataset dataset = createDataset();
 
    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,xAxisLabel, yAxisLabel, dataset);

   
    return new ChartPanel(chart);
    }
 
    private XYDataset createDataset() {
        // creates an XY dataset...
        // returns the dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
 
    for(int i = 0;i<implementLpp.numberOfCon;i++)
    {
        for(int j=0;j<implementLpp.numberOfVar*2;j++)
        {
                XYSeries series = new XYSeries("Constraint "+i);
                series.add(coordinate[i][j++],coordinate[i][j++]);
                series.add(coordinate[i][j++],coordinate[i][j++]);
                dataset.addSeries(series);
        }
    }
   

  
 
    return dataset;
    }
 /*
    public void draw(float c[][]) {
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                
                new Graph().setVisible(true);
            }
        });
    }*/
}