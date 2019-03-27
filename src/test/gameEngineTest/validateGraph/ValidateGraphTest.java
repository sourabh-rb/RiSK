package test.gameEngineTest.validateGraph;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gameEngine.ValidateGraph;



/**
 * This class is used to test the Validate graph class using predefined
 * incorrect. This class has a total of 8 test cases.
 * 
 * @author Charan
 * @version 2.0.0
 */
public class ValidateGraphTest {

	ValidateGraph graph;
	String filePath;
	HashMap<String, Integer> continentHashMap = new HashMap<String, Integer>();
	HashMap<String, ArrayList<String>> territoryHashMap = new HashMap<String, ArrayList<String>>();

	/**
	 * This method is used to initialize the variables before each test is run.
	 */
	@Before
	public void set() {
		graph = new ValidateGraph();
	}

	/**
	 * This method checks that the graph is completely connected.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDisconnectedGraph() throws IOException {
		filePath = ".//graph_tests/disconnected_graph_mini.map";
		continentHashMap.put("North America", 5);
		continentHashMap.put("Australia", 2);
		territoryHashMap.put("Alaska", new ArrayList<String>(Arrays.asList("North America", "Northwest Territory")));
		territoryHashMap.put("Northwest Territory", new ArrayList<String>(Arrays.asList("North America", "Alaska")));
		territoryHashMap.put("Solo_country", new ArrayList<String>(Arrays.asList("Australia")));

		List<Object> expected = Arrays.asList("Map Disconnected", continentHashMap, territoryHashMap);

		String mapFileContent = mapRead(filePath);

		List<Object> ls = graph.initiateCheck(mapFileContent);
		System.out.println(expected);
		System.out.println("END OF EXPECTED");
		System.out.println(ls);
		assertEquals(ls, expected);
	}

	/**
	 * This method checks if the connection between countries is bidirectional, if
	 * India has China as its neighbor then China should also have India as its
	 * neighbor.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testBidirectionalConnectivity() throws IOException {
		filePath = ".//graph_tests/bidirectional_connectivity_check_mini.map";
		continentHashMap.put("North America", 5);
		territoryHashMap.put("Northwest Territory", new ArrayList<String>(Arrays.asList("North America", "Alberta")));
		territoryHashMap.put("Alberta",
				new ArrayList<String>(Arrays.asList("North America", "Alaska", "Northwest Territory")));
		territoryHashMap.put("Alaska",
				new ArrayList<String>(Arrays.asList("North America", "Northwest Territory", "Alberta")));

		List<Object> expected = Arrays.asList(
				"Alaska to Northwest Territory connectivity is mentioned but not vice versa", continentHashMap,
				territoryHashMap);

		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method checks if the user has entered correct continent control value.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testIncorrectContinentValue() throws IOException {

		List<Object> expected = Arrays.asList("wrong continent line format", continentHashMap, territoryHashMap);

		filePath = ".//graph_tests/incorrect_continent_value_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This methods checks any incorrect spellings in the neighbor countries.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMisspelledNeighborCountryName() throws IOException {

		continentHashMap.put("North America", 5);
		territoryHashMap.put("Alaska",
				new ArrayList<String>(Arrays.asList("North America", "Northwest Territory", "Albrta")));
		territoryHashMap.put("Northwest Territory",
				new ArrayList<String>(Arrays.asList("North America", "Alaska", "Alberta")));
		territoryHashMap.put("Alberta",
				new ArrayList<String>(Arrays.asList("North America", "Alaska", "Northwest Territory")));

		List<Object> expected = Arrays.asList("country Albrta doesn't have an entry in territory list",
				continentHashMap, territoryHashMap);

		filePath = ".//graph_tests/mispelled_neigh_country_name_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method checks the format in which the user enters the territories.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMissingCommaBetweenTerritories() throws IOException {
		continentHashMap.put("North America", 5);
		territoryHashMap.put("Alaska",
				new ArrayList<String>(Arrays.asList("North America", "Northwest Territory", "Alberta Kamchatka")));
		territoryHashMap.put("Northwest Territory",
				new ArrayList<String>(Arrays.asList("North America", "Alaska", "Alberta")));
		territoryHashMap.put("Alberta",
				new ArrayList<String>(Arrays.asList("North America", "Alaska", "Northwest Territory")));

		List<Object> expected = Arrays.asList("country Alberta Kamchatka doesn't have an entry in territory list",
				continentHashMap, territoryHashMap);

		filePath = ".//graph_tests/missing_comma_between_territories_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method checks if each country is a part of a continent.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMissingContinentCountry() throws IOException {
		continentHashMap.put("North America", 5);

		List<Object> expected = Arrays.asList("No continent with the name Northwest Territory", continentHashMap,
				territoryHashMap);

		filePath = ".//graph_tests/missing_continent_for_country_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method is used to validate that the user has entered continents that
	 * exist.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNonExistingContinent() throws IOException {
		continentHashMap.put("North America", 5);

		List<Object> expected = Arrays.asList("No continent with the name North India", continentHashMap,
				territoryHashMap);

		filePath = ".//graph_tests/Non_existing_continent_in_territories_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method is used to check if a territory is a part of multiple countries.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testTerritoryInMultipleContinents() throws IOException {
		continentHashMap.put("North America", 5);
		territoryHashMap.put("Alaska", new ArrayList<String>(Arrays.asList("North America", "Northwest Territory")));
		territoryHashMap.put("Northwest Territory", new ArrayList<String>(Arrays.asList("North America", "Alaska")));

		List<Object> expected = Arrays.asList("Territory repeated", continentHashMap, territoryHashMap);

		filePath = ".//graph_tests/territory_belongs_to_multiple_continents_mini.map";
		String mapFileContent = mapRead(filePath);
		List<Object> ls = graph.initiateCheck(mapFileContent);
		assertEquals(ls, expected);
	}

	/**
	 * This method reads the map file that is to be validated in the test cases.
	 * 
	 * @param file
	 *            THe path of the file that is to be validated.
	 * @return The contents of the file are returned as a string.
	 * @throws IOException
	 */
	public static String mapRead(String file) throws IOException {
		String line;
		String fileContents = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			while ((line = reader.readLine()) != null)
				fileContents = fileContents + line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;

	}
}