package gameEngine;


import org.jgrapht.*;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import org.jgrapht.util.*;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import java.util.regex.*;


/**
 * This class scrapes the data from .map file and checks the graph for errors.
 * @author charan
 * @version 1.0.0
 */

public  class ValidateGraph 

{
    // number of vertices
    private static final int SIZE = 10;

    /**
     * This method scrapes and extracts the  data from the .map file into a hashmap of  countries and continents.
     * This method also checks the credibility of the .map file given as input and displays error messages if there are any
     * @param fileContents contains the contents of the .map file in string format
     * @return returns the continent,country hashmaps and also the error messages if the map has any errors otherwise returns success
     * @throws IOException 
     */
    
    public List<Object>  initiateCheck(String fileContents) throws IOException
    {
    	int continentStart=0;
    	int territoryStart=0;
    	
    	String[] continentDissected;
    	String[] territoryDissected;
    	
    	ArrayList<String> parameters=new ArrayList<String>();
    	ArrayList<String> territoryList=new ArrayList<String>();
    	ArrayList<String> terConList=new ArrayList<String>();
    	
    	HashMap<String,Integer> continentHashMap = new HashMap<String,Integer>();
    	HashMap<String,ArrayList<String>> territoryHashMap = new HashMap<String,ArrayList<String>>();
    	
    	graphs g1=new graphs();
    	
    	
    
    	//List<String> lines = file_contents.split("\n")
    	List<String> lines = Arrays.asList(fileContents.split("\n"));
    	
    	//HEADING FORMAT CHECK FOR CONTINENTS
    	
    	if(lines.contains("[Continents]"))
    	{
    	continentStart=lines.indexOf("[Continents]");
    	}
    	else  
    		return Arrays.asList("Wrong heading format for continents",continentHashMap , territoryHashMap) ;
    	
    	//HEADING FORMAT CHECK FOR TERRITORIES
    	
    	if(lines.contains("[Territories]"))
    	{
    	territoryStart=lines.indexOf("[Territories]");
    	}
    	else  
    		return Arrays.asList("Wrong heading format for territories",continentHashMap , territoryHashMap);
    	
    	//CONTINENT FORMAT CHECKING AND DATA EXTRACTION
    	
    	for(int i=continentStart+1;i<territoryStart;i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+=[0-9]+$", lines.get(i))) 
    			return Arrays.asList("wrong continent line format",continentHashMap , territoryHashMap);
    		continentDissected = lines.get(i).split("=");
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+$", continentDissected[0])) 
    			return Arrays.asList("wrong continent format",continentHashMap , territoryHashMap);
    		if(!Pattern.matches("^[0-9]+$", continentDissected[1])) 
    			return Arrays.asList("wrong continent score format",continentHashMap , territoryHashMap);
    		if(continentHashMap.containsKey(continentDissected[0])) 
    			return Arrays.asList("Continent repeated",continentHashMap , territoryHashMap);
    		continentHashMap.put(continentDissected[0], Integer.parseInt(continentDissected[1]));
    	}
    	
    	
    	
    	//TERRITORY FORMAT CHECKING AND DATA EXTRACTION
    	
    	for(int i=territoryStart+1;i<lines.size();i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+,[0-9]+,[0-9]+,[a-zA-Z0-9_ ]+(,[a-zA-Z0-9_ ]+)*$", lines.get(i))) 
    			return Arrays.asList("wrong territory line format",continentHashMap , territoryHashMap);
    		territoryDissected = lines.get(i).split(",");
    		
    		if(territoryList.contains(territoryDissected[0])) 
    			return Arrays.asList("Territory repeated",continentHashMap , territoryHashMap);
    		if(!continentHashMap.containsKey(territoryDissected[3])) 
    		    return Arrays.asList("No continent with the name "+territoryDissected[3],continentHashMap , territoryHashMap);
    		territoryList.add(territoryDissected[0]);
    		terConList.add(territoryDissected[3]);	
    		for(int j=3;j<territoryDissected.length;j++)
    		{
    			parameters.add(territoryDissected[j]);
    		}
    		
    		territoryHashMap.put(territoryDissected[0],(ArrayList<String>) parameters.clone());
    		
    		
    		parameters.clear();
    	}
    	
    	//FOR CHECKING WHETHER EACH NEIGHBOURING COUNTRY HAS AN ENTRY IN THE ARRAYLIST OF COUNTRIES
    	for(int i=territoryStart+1;i<lines.size();i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		territoryDissected = lines.get(i).split(",");
    		for(int j=4;j<territoryDissected.length;j++)
    		{
    			if(!territoryList.contains(territoryDissected[j])) 	
    			return Arrays.asList("country "+territoryDissected[j]+" doesn't have an entry in territory list",continentHashMap , territoryHashMap);
    		}
    		g1.graphPopulater(territoryDissected);  
    	}
    	
    	
    	// FOR CHECKING BI DIRECTIONAL CONNECTIVITY(IF INDIA HAS BORDER WITH PAKISTAN THEN PAKISTAN SHOULD ALSO HAVE A BOREDR WITH INDIA)
    	
    	for(DefaultEdge edge: g1.g.edgeSet())
    	{
    		 Pattern p = Pattern.compile("\\(([a-zA-Z0-9_ ]+) : ([a-zA-Z0-9_ ]+)\\)",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    		 Matcher m = p.matcher(edge.toString());
    		 //System.out.println(edge.toString());
    		 //System.out.println(m.group(0));
    		 if (m.find()) 
    		 {
    			    // access groups found. 
    			 if(!g1.g.containsEdge(m.group(2), m.group(1)))
    			 {
    				 return Arrays.asList(m.group(1)+" to "+m.group(2)+" connectivity is mentioned but not vice versa",continentHashMap , territoryHashMap) ;
    			 }
    		 }		
    	}
    	System.out.println(territoryHashMap);
    	System.out.println(continentHashMap);
    	
    	
    	
    	
    	if(continentHashMap.isEmpty() || territoryHashMap.isEmpty())
    	{
    		return Arrays.asList("One of the hashmaps is empty",continentHashMap,territoryHashMap);
    	}
    	
    	if(g1.connectivityCheck().contentEquals("graph disconnected"))
    	{
    		return Arrays.asList("Map Disconnected",continentHashMap,territoryHashMap);
    	}
		return Arrays.asList("Success",continentHashMap , territoryHashMap);
     }
    
    
    
}

class graphs
{
	 DefaultDirectedGraph<String, DefaultEdge> g;
	
	
	/**
	 * This constructor creates the empty graph object when an instance of graphs class is created
	 * @throws IOException
	 */
	 
	public  graphs() throws IOException {
	     g= new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);     
	}
	 
	/**
     * This method checks whether the graph is connected or not.
     * @throws IOException 
     * @return connected if the graph is connected otherwise disconnected 
     */   
	 
	   
	public String connectivityCheck() throws IOException {
		
	    ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(g);
		if(inspector.isConnected())
		{
			return "connected";
		}
		else
		{
			return "graph disconnected";
		}
	 
	    //assertTrue(imgFile.exists());
	}
	
	/**
     * This method populates the graph based on the .map file given as input.
     * This method creates the vertices and edges between the vertices based on .map file
     * @param territoryDissected contains each country details(country name,continent, neigh countries)
     */   
	
	
	
	public void graphPopulater(String[] territoryDissected)
	{
		int i;
	    for(i=0;i<territoryDissected.length;i++)
	    {
	    	//System.out.println(territory_dissected[i]);
	    	if(i!=1 && i!=2 && i!=3)
	    	{
	    	if(!g.containsVertex(territoryDissected[i]))
	    	{
	    		
	    		g.addVertex(territoryDissected[i]);
	    		
	    	}
	    		
	    	}
	    }
	    
	    
		for(i=4;i<territoryDissected.length;i++)
		{
			g.addEdge(territoryDissected[0], territoryDissected[i]);
		}
		
		
		
	}




}
