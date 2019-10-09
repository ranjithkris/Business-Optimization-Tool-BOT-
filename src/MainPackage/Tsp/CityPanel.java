/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage.Tsp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ranjith
 */
public class CityPanel extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 9220036263068464736L;
	private static Map<Integer,Point> cityLocations = new HashMap<Integer, Point>();
	private static Map<String, Point[]> cityDistances = new HashMap<String, Point[]>();
	Point[] cityPoints12 = new Point[2];
	Integer[] city12 = new Integer[2];
	private static int cityOrder = 1;
        public static int index = 0;
        public static int num = 0;
	
	private boolean drawCity = false;
	private boolean activateDrawCity = false;
	private boolean activatePaths = false;
	private boolean drawPathMode = false;
	
	private boolean city1selected = false;
	private boolean city2selected = false;
        
	
	public void activateAddCity(){
		activateDrawCity = true;
	}
	
	public void activateAddPaths(){
		activatePaths = true;
		activateDrawCity = false;
	}
	
	public void resetEverything(){
            MyTSPMainPanel.event.setText("");
		cityLocations = new HashMap<Integer, Point>();
		cityDistances = new HashMap<String, Point[]>();
		cityPoints12 = new Point[2];
		cityOrder = 1;
		activateDrawCity = false;
		drawCity = false;
		activatePaths = false;
		drawPathMode = false;
		city1selected = false;
		city2selected = false;
		repaint();
	}
	
	public void getDistanceMatrix(){
		
    	
    		MyTSPMainPanel.event.setText("");
    		String distanceMatrix[][] = new String[100][100];
    	if(cityOrder > 1)
        {
    		for(int i = 1; i< cityOrder ; i ++){
    			for(int j = 1; j< cityOrder ; j ++){
    				distanceMatrix[i][j] = "999";
    			}
    		}
    		for(int i = 1; i< cityOrder ; i ++){
    			for(int j = 1; j< cityOrder ; j ++){
    				Iterator<Map.Entry<String,Point[]>> cityDistancesMapping = cityDistances.entrySet().iterator();
    				while (cityDistancesMapping.hasNext()) {
    		    		Map.Entry<String,Point[]> mapping = cityDistancesMapping.next();
    		    		String[] cityInfo = mapping.getKey().split("S<>M");
    		    		String weight = cityInfo[0];
    		    		String cities = cityInfo[1];
    		    		
    		    		String city1 = cities.split("-")[0];
    		    		String city2 = cities.split("-")[1];
    		    		if(i == Integer.parseInt(city1) && j == Integer.parseInt(city2)){
    		    			distanceMatrix[i][j] = weight;
    		    			distanceMatrix[j][i] = weight;
    		    		}
    		    	}
    			}
    		}
                int cost[][] = new int[100][100];
                int cities = cityOrder - 1;
    	     
                
                for(int i = 1; i< cityOrder ; i ++)
                {
                    for(int j = 1; j< cityOrder ; j ++)
                    {
                        if(i != j)
                            cost[i-1][j-1] = Integer.parseInt(distanceMatrix[i][j]);
                        else if(i == j)
                            cost[i][j] = 0;
                    }
    		}
                implementTsp.compute(cost, cities);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Draw the graph first");
        }
        
       	}
	
	
	public CityPanel(){
		addMouseListener(this);
		
	}
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(activateDrawCity){
                    if(cityOrder <= 10)
                    {
			Point whereClicked = e.getPoint();
			cityLocations.put(cityOrder++,whereClicked);
			drawCity = true;
                        MyTSPMainPanel.event.setText("Created city "+(cityOrder-1));
			repaint();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Cannot create more than 10 cities");
                    
                    }
		}else if (!activatePaths){
			JOptionPane.showMessageDialog(this, "Click on Add City button to activate add city feature");
		}else{
			
			Point whereClicked = e.getPoint();
			 
			int maxX = whereClicked.x + 40;
			int minX = whereClicked.x - 40;
			
			int maxY = whereClicked.y + 40;
			int minY = whereClicked.y - 40;
			
			Iterator<Map.Entry<Integer,Point>> cities = cityLocations.entrySet().iterator();
        	while (cities.hasNext()) {
        	    Map.Entry<Integer,Point> city = cities.next();
        	    if(maxX >= city.getValue().x && minX <= city.getValue().x &&
        	    		maxY >= city.getValue().y && minY <= city.getValue().y){
        	    	String message = "You have selected city " + city.getKey();
        	  //  	JOptionPane.showMessageDialog(this, message);
                        
                        MyTSPMainPanel.event.setText(message);
        	    	
        	    	if(!city1selected && !city2selected){
        	    		city1selected = true;
        	    		cityPoints12[0] = city.getValue();
        	    		city12[0] = city.getKey();
        	    		
        	    	}else if (city1selected && !city2selected){
                            if(city12[0] != city.getKey())
                            {
        	    		city2selected = true;
        	    		cityPoints12[1] = city.getValue();
        	    		city12[1] = city.getKey();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "You have selected same city");
                            }
        	    	}
        	    	
        	    	if(city1selected && city2selected){
        	    		drawPathMode = true;
        	    		city1selected = false;
        	    		city2selected = false;
        	    		String pathDistance = (String)JOptionPane.showInputDialog(this, "Enter the distance between "+city12[0]+" and "+city12[1]);
        	    		
                                if(pathDistance == null)
                                {
                                    city1selected = false;
                                    city2selected = false;
                                    MyTSPMainPanel.event.setText("");
                                }
                                else
                                {
                                    while(pathDistance.isEmpty())
                                    {   
                                        pathDistance = (String)JOptionPane.showInputDialog(this, "You have not entered the distance.\nEnter the distance between "+city12[0]+" and "+city12[1]);
                                    }
                                
                                    MyTSPMainPanel.event.setText("Distance from city "+city12[0]+" to "+city12[1]+" is "+pathDistance);
                                    cityDistances.put(pathDistance +"S<>M" +city12[0]+"-"+city12[1], cityPoints12);
                                    index++;
                                    cityPoints12 = new Point[2];
                                    city12 = new Integer[2];
                                    repaint();
                                }
                                
        	    	}
        	    	
        	//    	9591302233
        	    }
        	}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xCordinate = 0;
        int yCordinate = 0;
        
        
        String cityLabel = "City";
        if((drawCity && activateDrawCity) || activatePaths){
        	Iterator<Map.Entry<Integer,Point>> cities = cityLocations.entrySet().iterator();
        	while (cities.hasNext()) {
        	    Map.Entry<Integer,Point> city = cities.next();
        	    xCordinate = city.getValue().x;
        	    yCordinate = city.getValue().y;
                    try {
                        BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir")+"\\images\\tsp\\cityImage"+city.getKey()+".gif"));
                         g.drawImage(image,xCordinate,yCordinate,null);
		} catch (IOException e) {
			System.out.println("Error Reading Image");
		}
        	   
        	 //   g.drawString(cityLabel+city.getKey(),xCordinate,yCordinate);
        	}
        }
        
        
        
        if(drawPathMode){
        	Iterator<Map.Entry<String,Point[]>> cityDistancesMapping = cityDistances.entrySet().iterator();
                int i = index-1;
        	while (cityDistancesMapping.hasNext()) {
                    
        		Map.Entry<String,Point[]> mapping = cityDistancesMapping.next();
        		int city1X = mapping.getValue()[0].x+22;
        		int city1Y = mapping.getValue()[0].y+22;
        		
        		int city2X = mapping.getValue()[1].x+22;
        		int city2Y = mapping.getValue()[1].y+22;
        		
        		String[] cityInfo = mapping.getKey().split("S<>M");
        		String weight = cityInfo[0];
        		String cities = cityInfo[1];
                        
                        g.setColor(Color.BLUE);
                        g.drawLine(city1X,city1Y,city2X,city2Y);
                            g.drawString(weight,(city1X+city2X)/2 +10, (city1Y+city2Y)/2 + 10);
                        
                           
                        
        	}
                
                
        	
        	
        }
    }

}

