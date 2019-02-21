package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MapFileViewManager
{
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	private AnchorPane mapFilePane;
	private Scene mapFileScene;
	private Stage mapFileStage;
	
	private TextArea displayArea;
	private File chosenFile;
	
	public MapFileViewManager(File file)
	{
		chosenFile = file;
		initStage();
		addDisplayArea();
		mapFileStage.show();
		
		
	}
	
	private void initStage()
	{
		mapFilePane = new AnchorPane();
		mapFileScene = new Scene(mapFilePane, WIDTH, HEIGHT);
		mapFileStage = new Stage();
		mapFileStage.setScene(mapFileScene);	
	}
	
	private void addDisplayArea()
	{
		displayArea = new TextArea();
		displayArea.setPrefHeight(HEIGHT);
		displayArea.setPrefWidth(WIDTH);
		try (BufferedReader reader = new BufferedReader(new FileReader(chosenFile))) {

	        String line;
	        while ((line = reader.readLine()) != null)
	            //System.out.println(line);
	        	displayArea.appendText(line + "\n");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		displayArea.positionCaret(1);
		mapFilePane.getChildren().add(displayArea);
	}
	

}
