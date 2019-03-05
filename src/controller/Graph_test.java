package controller;


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

public  class Graph_test 

{
    // number of vertices
    private static final int SIZE = 10;

    /**
     * This method scrapes and extracts the  data from the .map file into a hashmap of  countries and continents.
     * This method also checks the credibility of the .map file given as input and displays error messages if there are any
     * @param file_contents contains the contents of the .map file in string format
     * @return returns the continent,country hashmaps and also the error messages if the map has any errors otherwise returns success
     * @throws IOException 
     */
    
    public List<Object>  initiate_check(String file_contents) throws IOException
    {
    	int continent_start=0;
    	int territory_start=0;
    	
    	String[] continent_dissected;
    	String[] territory_dissected;
    	
    	ArrayList<String> parameters=new ArrayList<String>();
    	ArrayList<String> territory_list=new ArrayList<String>();
    	ArrayList<String> ter_con_list=new ArrayList<String>();
    	
    	HashMap<String,Integer> continent_hashMap = new HashMap<String,Integer>();
    	HashMap<String,ArrayList<String>> territory_hashMap = new HashMap<String,ArrayList<String>>();
    	
    	graphs g1=new graphs();
    	
    	
    
    	//List<String> lines = file_contents.split("\n")
    	List<String> lines = Arrays.asList(file_contents.split("\n"));
    	
    	//HEADING FORMAT CHECK FOR CONTINENTS
    	
    	if(lines.contains("[Continents]"))
    	{
    	continent_start=lines.indexOf("[Continents]");
    	}
    	else  
    		return Arrays.asList("Wrong heading format for continents",continent_hashMap , territory_hashMap) ;
    	
    	//HEADING FORMAT CHECK FOR TERRITORIES
    	
    	if(lines.contains("[Territories]"))
    	{
    	territory_start=lines.indexOf("[Territories]");
    	}
    	else  
    		return Arrays.asList("Wrong heading format for territories",continent_hashMap , territory_hashMap);
    	
    	//CONTINENT FORMAT CHECKING AND DATA EXTRACTION
    	
    	for(int i=continent_start+1;i<territory_start;i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+=[0-9]+$", lines.get(i))) 
    			return Arrays.asList("wrong continent line format",continent_hashMap , territory_hashMap);
    		continent_dissected = lines.get(i).split("=");
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+$", continent_dissected[0])) 
    			return Arrays.asList("wrong continent format",continent_hashMap , territory_hashMap);
    		if(!Pattern.matches("^[0-9]+$", continent_dissected[1])) 
    			return Arrays.asList("wrong continent score format",continent_hashMap , territory_hashMap);
    		if(continent_hashMap.containsKey(continent_dissected[0])) 
    			return Arrays.asList("Continent repeated",continent_hashMap , territory_hashMap);
    		continent_hashMap.put(continent_dissected[0], Integer.parseInt(continent_dissected[1]));
    	}
    	
    	
    	
    	//TERRITORY FORMAT CHECKING AND DATA EXTRACTION
    	
    	for(int i=territory_start+1;i<lines.size();i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+,[0-9]+,[0-9]+,[a-zA-Z0-9_ ]+(,[a-zA-Z0-9_ ]+)*$", lines.get(i))) 
    			return Arrays.asList("wrong territory line format",continent_hashMap , territory_hashMap);
    		territory_dissected = lines.get(i).split(",");
    		
    		if(territory_list.contains(territory_dissected[0])) 
    			return Arrays.asList("Territory repeated",continent_hashMap , territory_hashMap);
    		if(!continent_hashMap.containsKey(territory_dissected[3])) 
    		    return Arrays.asList("No continent with the name "+territory_dissected[3],continent_hashMap , territory_hashMap);
    		territory_list.add(territory_dissected[0]);
    		ter_con_list.add(territory_dissected[3]);	
    		for(int j=3;j<territory_dissected.length;j++)
    		{
    			parameters.add(territory_dissected[j]);
    		}
    		
    		territory_hashMap.put(territory_dissected[0],(ArrayList<String>) parameters.clone());
    		
    		
    		parameters.clear();
    	}
    	
    	//FOR CHECKING WHETHER EACH NEIGHBOURING COUNTRY HAS AN ENTRY IN THE ARRAYLIST OF COUNTRIES
    	for(int i=territory_start+1;i<lines.size();i++)
    	{
    		if(lines.get(i).length()==0) continue;
    		territory_dissected = lines.get(i).split(",");
    		for(int j=4;j<territory_dissected.length;j++)
    		{
    			if(!territory_list.contains(territory_dissected[j])) 	
    			return Arrays.asList("country "+territory_dissected[j]+" doesn't have an entry in territory list",continent_hashMap , territory_hashMap);
    		}
    		g1.graph_populater(territory_dissected);  
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
    				 return Arrays.asList(m.group(1)+" to "+m.group(2)+" connectivity is mentioned but not vice versa",continent_hashMap , territory_hashMap) ;
    			 }
    		 }		
    	}
    	System.out.println(territory_hashMap);
    	System.out.println(continent_hashMap);
    	
    	
    	
    	
    	if(continent_hashMap.isEmpty() || territory_hashMap.isEmpty())
    	{
    		return Arrays.asList("One of the hashmaps is empty",continent_hashMap,territory_hashMap);
    	}
    	
    	if(g1.connectivity_check().contentEquals("graph disconnected"))
    	{
    		return Arrays.asList("Map Disconnected",continent_hashMap,territory_hashMap);
    	}
		
		return Arrays.asList("Success",continent_hashMap , territory_hashMap);
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
	 
	   
	public String connectivity_check() throws IOException {
		
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
     * @param territory_dissected contains each country details(country name,continent, neigh countries)
     */   
	
	
	
	public void graph_populater(String[] territory_dissected)
	{
		int i;
	    for(i=0;i<territory_dissected.length;i++)
	    {
	    	//System.out.println(territory_dissected[i]);
	    	if(i!=1 && i!=2 && i!=3)
	    	{
	    	if(!g.containsVertex(territory_dissected[i]))
	    	{
	    		
	    		g.addVertex(territory_dissected[i]);
	    		
	    	}
	    		
	    	}
	    }
	    
	    
		for(i=4;i<territory_dissected.length;i++)
		{
			g.addEdge(territory_dissected[0], territory_dissected[i]);
		}
		
		
		
	}




}