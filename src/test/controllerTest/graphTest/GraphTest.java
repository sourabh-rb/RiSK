
package test.controllerTest.graphTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ValidateGraph;

public class GraphTest {
	
	ValidateGraph graph= new ValidateGraph();
	String filePath;
	HashMap<String,Integer> continentHashMap= new HashMap<String,Integer>();
	HashMap<String,ArrayList<String>> territoryHashMap= new HashMap<String,ArrayList<String>>();
	
	/**
	 * This method is used to initialise the variables before each test is run.
	 */
	@Before
	public void set() {
	ValidateGraph graph= new ValidateGraph();
	}
	
	/**
	 * This method checks that the graph is completely connected.
	 * @throws IOException
	 */
	@Test
	public void testDisconnectedGraph() throws IOException {
		filePath = "/home/charan/group_project/RiSK/graph_tests/disconnected_graph_mini.map";
		continentHashMap.put("North America", 5);
		continentHashMap.put("Australia",2);
		territoryHashMap.put("Alaska",new ArrayList<String>( Arrays.asList("North America","Northwest Territory")));
		territoryHashMap.put("Northwest Territory",new ArrayList<String>( Arrays.asList("North America","Alaska")));
		territoryHashMap.put("Solo_country",new ArrayList<String>( Arrays.asList("Australia")));
		
		List<Object> expected= Arrays.asList("Map Disconnected",continentHashMap , territoryHashMap);
		
		String mapFileContent = mapRead(filePath);
		
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
		System.out.println(expected);
		System.out.println("END OF EXPECTED");
		System.out.println(ls);
		assertEquals(ls, expected);
	}
	
	
	
	/**
	 * This method checks if the connection between countries is bidirectional, if India has China as its neighbor then China should also have India as its neighbor.
	 * @throws IOException
	 */
	/*@Test
	public void testBidirectionalConnectivity() throws IOException {
		file_path ="D:\\graph_tests\\bidirectional_connectivity_check_mini.map"; 
		continentHashMap.put("North America", 5);
		continentHashMap.put("Australia", 2);
		territoryHashMap.put("Northwest Territory",new ArrayList<String>( Arrays.asList("North America", "Alaska") ));
		territoryHashMap.put("Solo_country",new ArrayList<String>( Arrays.asList("Australia") ));
		territoryHashMap.put("Alaska",new ArrayList<String>( Arrays.asList("North America", "Northwest Territory") ));
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method checks if the user has entered correct continent control value.
	 * @throws IOException
	 */
/*	@Test
	public void testIncorrectContinentValue() throws IOException {
		file_path ="D:\\graph_tests\\incorrect_continent_value.txt"; 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This methods checks any incorrect spellings in the neighbor countries.
	 * @throws IOException
	 */
/*	@Test
	public void testMisspelledNeighborCountryName() throws IOException {
		file_path ="D:\\graph_tests\\mispelled_neigh_country_name.txt"; 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method checks the format in which the user enters the territories.
	 * @throws IOException
	 */
/*	@Test
	public void testMissingCommaBetweenTerritories() throws IOException {
		file = Paths.get("D:\\graph_tests\\missing_comma_between_territories.txt"); 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method checks if each country is a part of a continent.
	 * @throws IOException
	 */
/*	@Test
	public void testMissingContinentCountry() throws IOException {
		file_path ="D:\\graph_tests\\missing_continent_for_country.txt"; 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method is used to validate that the user has entered continents that exist.
	 * @throws IOException
	 */
/*	@Test
	public void testNonExistingContinent() throws IOException {
		file_path ="D:\\graph_tests\\Non_existing_continent_in_territories.txt"; 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method is used to check if a territory is a part of multiple countries. 
	 * @throws IOException
	 */
/*	@Test
	public void testTerritoryInMultipleContinents() throws IOException {
		file_path ="D:\\graph_tests\\territory_belongs_to_multiple_continents.txt"; 
		String mapFileContent = mapRead(file_path);
		List<String> lines = Arrays.asList(mapFileContent.split("\n"));
		List<Object> ls= graph.initiateCheck(mapFileContent);
	}
	
	/**
	 * This method reads the map file that is to be validated.
	 * @param file THe path of the file that is to be validated.
	 * @return The contents of the file are returned as a string.
	 * @throws IOException
	 */
	public static String mapRead(String file) throws IOException {
        String line;
        String fileContents="";
//        System.out.println("came here");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

	        
	        while ((line = reader.readLine()) != null)
	        	fileContents=fileContents+line + "\n";

	    } catch (IOException e) {
	    	System.out.println("came here 2");
	        e.printStackTrace();
	    }
		return fileContents;
		
	}
}