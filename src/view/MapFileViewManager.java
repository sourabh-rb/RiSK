package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import controller.Graph_test;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.ui_elements.RiskLabel;
/**
* <h1>MapFileViewManager</h1>
* The MapFileViewManager is responsible for loading
* the Map file editor on the screen. The user is able
* create new maps or edit existing maps using the the
*  editor.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class MapFileViewManager
{
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	private AnchorPane mapFilePane;
	private Scene mapFileScene;
	private Stage mapFileStage;
	
	private File chosenFile;
	private TextArea displayArea;
	private Button saveButton;
	private Button cancelButton;
	private Button helpButton;
	
	public MapFileViewManager(File file)
	{
		chosenFile = file;
		initStage();
		addDisplayElements();
		mapFileStage.show();
		
		
	}
	
	/**
	 * This method initializes view elements.
	 * 
	 */
	private void initStage()
	{
		mapFilePane = new AnchorPane();
		mapFileScene = new Scene(mapFilePane, WIDTH, HEIGHT);
		mapFileStage = new Stage();
		mapFileStage.setTitle("Map Editor (" + chosenFile.getPath() + ")");
		mapFileStage.setScene(mapFileScene);	
	}
	
	/**
	 * This method sets up the main display layout.
	 * 
	 */
	private void addDisplayElements()
	{
		// Add file name label
		Label header = new Label("File: " + chosenFile.getName());
		header.setPrefSize(200, 20);
		header.setLayoutX(10);
		header.setLayoutY(10);
		
		mapFilePane.getChildren().add(header);
		
		//Display contents
		addDisplayArea();
		
		//Add control buttons (save and cancel)
		/*NOTE: choosing save calls the validator before saving it.
		 * File is saved only if it proven to be valid.
		 */
		addSaveButton();
		addCancelButton();
		
		HBox  hButtonBox = new HBox(20, saveButton, cancelButton);
		hButtonBox.setLayoutX(500);
		hButtonBox.setLayoutY(500);
		
		mapFilePane.getChildren().add(hButtonBox);
	}
	
	/**
	 * This method adds text area to view/edit contents of a file.
	 * 
	 */
	private void addDisplayArea()
	{
		displayArea = new TextArea();
		displayArea.setPrefHeight((HEIGHT - 130));
		displayArea.setPrefWidth(WIDTH);
		displayArea.setLayoutY(50);
		
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
	
	/**
	 * This method adds save button, to save into specific file.
	 * 
	 */
	private void addSaveButton()
	{
		saveButton = new Button("Validate & Save");
		
		saveButton.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event)
			{
				Graph_test gt=new Graph_test();
				System.out.println("going to graph checking ");
				try {
					System.out.println(gt.initiate_check(displayArea.getText()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("came out from  graph checking ");
				
				
				FileChooser fileChooser = new FileChooser();
	             
	            //Set extension filter
	            FileChooser.ExtensionFilter extFilter = 
	                new FileChooser.ExtensionFilter("MAP files (*.map)", "*.map");
	            fileChooser.getExtensionFilters().add(extFilter);
	            fileChooser.setInitialFileName("*.map");
	             
	            //Show save file dialog
	            File file = fileChooser.showSaveDialog(mapFileStage);
	             
	            if(file != null){
	                try {
						saveFile(file, displayArea.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	            }
				mapFileStage.close();
			}
		});
	}
	
	/**
	 * This method adds cancel button, to discard changes.
	 * 
	 */
	private void addCancelButton()
	{
		cancelButton = new Button("Cancel");
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
			
				mapFileStage.close();
				
			}
			
		});
	}
	
	/**
	 * This method writes the contents of display area 
	 * into a specified file.
	 * @throws IOException 
	 * 
	 */
	private void saveFile(File file, String content) throws IOException
	{
		
		
		try {
            FileWriter fileWriter;
              
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            
        }
		
	}
	

}
