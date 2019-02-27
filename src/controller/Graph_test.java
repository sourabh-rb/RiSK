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

public  class Graph_test 

{
    // number of vertices
    private static final int SIZE = 10;

    /**
     * Main demo entry point.
     * 
     * @param args command line arguments
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
    		if(!Pattern.matches("^[a-zA-Z0-9_ ]+,[0-9]+,[0-9]+,[a-zA-Z0-9_ ]+(,[a-zA-Z0-9_ ]+)+$", lines.get(i))) 
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
    	
    	g1.visualize();
		
		return Arrays.asList("Success",continent_hashMap , territory_hashMap);
     }
    
    
    
}

class graphs
{
	 DefaultDirectedGraph<String, DefaultEdge> g;
	
	
	public  graphs() throws IOException {
	     g= new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);     
	}
	 
	   
	 
	   
	public void visualize() throws IOException {
		
		 
	    JGraphXAdapter<String, DefaultEdge> graphAdapter = 
	      new JGraphXAdapter<String, DefaultEdge>(g);
	    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
	    layout.execute(graphAdapter.getDefaultParent());
	     
	    BufferedImage image = 
	      mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
	    File imgFile = new File("D:\\APPRisk/graph.png");
	    ImageIO.write(image, "PNG", imgFile);
	    ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(g);
		if(inspector.isConnected())
		{
			System.out.println("connected");
		}
		else
		{
			System.out.println("graph disconnected");
		}
	 
	    //assertTrue(imgFile.exists());
	}
	
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