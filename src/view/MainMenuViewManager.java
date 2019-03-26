package view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import constants.LogLevel;
import controller.GraphTest;
import controller.initialization.StartUpPhase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.Utilities;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;
import view.ui_elements.RiskSubScene;

/**
* <h1>MainMenuViewManager</h1>
* The MainMenuViewManager is responsible for loading
* the main menu items on the screen, this includes
* play, help, credits, and exit buttons.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class MainMenuViewManager
{
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	private static final int MENU_BUTTON_STARTX = 100;
	private static final int MENU_BUTTON_STARTY = 140;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private RiskSubScene playSubScene;
	private RiskSubScene mapEditorSubScene;
	private RiskSubScene helpSubScene;
	private RiskSubScene creditsSubScene;
	
	private StartUpPhase startPhase;
	
	private RiskSubScene sceneToHide;
	
	List<RiskButton> menuButtons;
	
	
	
	/**
	 * The MainMenuViewManager constructor initializes and sets up
	 * the main menu window.
	 * 
	 */
	public MainMenuViewManager()
	{
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		mainStage.initStyle(StageStyle.UNDECORATED);
		
		menuButtons = new ArrayList<RiskButton>();
		
		createBackground();
		createMenuButtons();
		createLogo();
		
		Utilities.gameLog("Stage: Main Menu View displayed", LogLevel.INFO);
		
	}
	
	/**
	 * This method sets up the main game logo.
	 * 
	 */
	private void createLogo()
	{
		ImageView logo = new ImageView("/view/resources/riskLogo.png");
		logo.setLayoutX(500);
		logo.setLayoutY(50);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
				
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
		});
		
		mainPane.getChildren().add(logo);
		
	}

	/**
	 * This method initializes and displays the main menu buttons:
	 * 1. PLAY
	 * 2. MAP EDITOR
	 * 3. HELP
	 * 4. CREDITS
	 * 5. EXIT
	 */
	private void createMenuButtons()
	{
		addPlayButton();
		addMapEditorButton();
		addHelpButton();
		addCreditsButton();
		addExitButton();
		
		addSubScenes();
		
	} 
	
	/**
	 * This method to add a menu button in a vertical column.
	 * 
	 */
	private void addMenuButton(RiskButton button)
	{
		button.setLayoutX(MENU_BUTTON_STARTX);
		button.setLayoutY(MENU_BUTTON_STARTY + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	/**
	 * This method sets up sub-scenes for menu buttons.
	 * 
	 */
	private void addSubScenes()
	{
		addPlaySubscene();
		
		addMapEditorSubScene();
		
		addHelpSubScene();
		
		addCreditsSubScene();
	}
	
	/**
	 * This method sets up the play sub-scene, 
	 * initiated when map play button is clicked.
	 * 
	 */
	private void addPlaySubscene()
	{
		playSubScene = new RiskSubScene();
		mainPane.getChildren().add(playSubScene);
		
		// Heading
		RiskLabel playLabel = new RiskLabel("PLAY");
		playLabel.setLayoutX(280);
		playLabel.setLayoutY(50);
		
		// Game initial configuration inputs from user
		RiskLabel countLabel = new RiskLabel("Choose no. of players:");
		countLabel.setLayoutX(10);
		countLabel.setLayoutY(150);
		
		ComboBox<Integer> playerCount = new ComboBox<>();
		playerCount.getItems().addAll(3, 4, 5, 6);
		playerCount.getSelectionModel().select(0);
		playerCount.setLayoutX(350);
		playerCount.setLayoutY(155);
		
		RiskLabel mapLabel = new RiskLabel("Choose map:");
		mapLabel.setLayoutX(10);
		mapLabel.setLayoutY(220);
		
		RiskButton mapButton = new RiskButton("CHOOSE MAP");
		mapButton.setLayoutX(200);
		mapButton.setLayoutY(220);
		
		RiskButton startButton = new RiskButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setDisable(true);
		//Map Button Handler
		mapButton.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event)
			{
				Utilities.gameLog("Stage: Play || State: CHOOSE MAP selected", LogLevel.INFO);
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose a map to edit");
		        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		        fileChooser.getExtensionFilters().addAll(
		        	     new FileChooser.ExtensionFilter("MAP Files", "*.map")
		        	    ,new FileChooser.ExtensionFilter("Text Files", "*.txt")
		        	);
		        File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                	
                	Utilities.gameLog("Stage: CHOOSE MAP || File selected: " + file.getName(), LogLevel.INFO);
                    //Validate file
                	List<Object> mapValidation;
                	StringBuffer contents = new StringBuffer();


                	GraphTest gt=new GraphTest();
                	
                	//Read file
                	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            	        String line;
            	        while ((line = reader.readLine()) != null)
            	        	contents.append(line).append("\n");

            	    } catch (IOException e) {
            	        e.printStackTrace();
            	    }
                	
                	
    				
                	try {
	                		HashMap<String,Integer> continentHashMap = new HashMap<String,Integer>();
	                		HashMap<String,ArrayList<String>> territoryHashMap = new HashMap<String,ArrayList<String>>();
	                		String errorMessage;
	                		int players;
	                		Alert alertDialog;
	                		
	                		mapValidation = gt.initiateCheck(contents.toString());
	                		errorMessage = mapValidation.get(0).toString();
	                		if(errorMessage.equals("Success"))
	                		{
	                			continentHashMap = (HashMap<String,Integer>)mapValidation.get(1);
	                			territoryHashMap = (HashMap<String,ArrayList<String>>)mapValidation.get(2);
	                			players = playerCount.getValue();
	                			mapLabel.setText(file.getName());
	                			alertDialog = new Alert(AlertType.INFORMATION);
	                			alertDialog.setTitle("Information Dialog");
	                			alertDialog.setHeaderText(null);
	                			alertDialog.setContentText("Map Valid! Click START to play game.");
	                			alertDialog.showAndWait();
	                			
	                			StartUpPhase startPhase = StartUpPhase.getInstance();
	                			startPhase.mappingElements(continentHashMap, territoryHashMap, players);
	                			startButton.setDisable(false);
	                			
	                			Utilities.gameLog("Stage: PLAY|| File Valid: " + file.getName(), LogLevel.INFO);
	                			Utilities.gameLog("Stage: PLAY || Player Count: " + players, LogLevel.INFO);
	                		}
	                		else
	                		{
	                			alertDialog = new Alert(AlertType.ERROR);
	                			alertDialog.setTitle("Error Dialog");
	                			alertDialog.setHeaderText("Invalid Map Selection");
	                			alertDialog.setContentText("ERROR: " + errorMessage.toString());
	                			alertDialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	                			alertDialog.showAndWait();
	                			Utilities.gameLog("Stage: PLAY || File Invalid: " + file.getName()
	                			+" || Error: " + errorMessage.toString(), LogLevel.INFO);
	                		}
	                		
                		} catch (IOException e1)
                			{
                				// TODO Auto-generated catch block
                				e1.printStackTrace();
                			}
                }
				
			}
		});
		
		
		//Start Button Handler
		startButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				//Initiate start up sequence
				//Load main game screen
				mainStage.hide();
				GamePhaseViewManager gamePhase = new GamePhaseViewManager();
				gamePhase.showView();
			}
		});
		
		playSubScene.getPane().getChildren().add(playLabel);
		playSubScene.getPane().getChildren().add(countLabel);
		playSubScene.getPane().getChildren().add(playerCount);
		playSubScene.getPane().getChildren().add(mapLabel);
		playSubScene.getPane().getChildren().add(mapButton);
		playSubScene.getPane().getChildren().add(startButton);
	}
	
	/**
	 * This method sets up the map editor sub-scene, 
	 * initiated when map editor button is clicked.
	 * 
	 */
	private void addMapEditorSubScene()
	{
		mapEditorSubScene = new RiskSubScene();
		mainPane.getChildren().add(mapEditorSubScene);
		
	
		RiskLabel mapEditorLabel = new RiskLabel("Choose to create/edit a map");
		mapEditorLabel.setLayoutX(100);
		mapEditorLabel.setLayoutY(50);
	
		// Create a edit map button and set it up to access the file system to let user choose a map.
		RiskButton editMapButton = new RiskButton("EDIT MAP");
		FileChooser fileChooser = new FileChooser();
		
		editMapButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Utilities.gameLog("Stage: Main Menu || State: EDIT MAP selected", LogLevel.INFO);
				 
				fileChooser.setTitle("Choose a map to edit");
		        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		        fileChooser.getExtensionFilters().addAll(
		        	     new FileChooser.ExtensionFilter("MAP Files", "*.map")
		        	    ,new FileChooser.ExtensionFilter("Text Files", "*.txt")
		        	);
		        File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    openMapEditFile(file);
                    
                    Utilities.gameLog("Stage: EDIT MAP || File selected:" + file.getName(), LogLevel.INFO);
                }
                
               
				
			}
		});
		
		// Create a new map button to allow user create a new map file from scratch.
		RiskButton createMapButton = new RiskButton("NEW MAP");
		
		createMapButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0)
			{
				File newMapFile = new File(".//Maps//default.map");
				MapFileViewManager fileView = new MapFileViewManager(newMapFile);
				
				Utilities.gameLog("Stage: NEW MAP selected", LogLevel.INFO);
			}
		});
		HBox buttonBox = new HBox(20, editMapButton, createMapButton);
		buttonBox.setLayoutX(100);
		buttonBox.setLayoutY(150);
		
		mapEditorSubScene.getPane().getChildren().add(mapEditorLabel);
		mapEditorSubScene.getPane().getChildren().add(buttonBox);
	}
	
	/**
	 * This method sets up the help sub-scene, 
	 * initiated when help button is clicked.
	 * 
	 */
	private void addHelpSubScene()
	{
		helpSubScene = new RiskSubScene();
		mainPane.getChildren().add(helpSubScene);
		
		RiskLabel helpLabel = new RiskLabel("Game Help");
		helpLabel.setLayoutX(250);
		helpLabel.setLayoutY(20);
		
		Hyperlink helpLink = new Hyperlink("Game Instructions");
		helpLink.setLayoutX(20);
		helpLink.setLayoutY(100);
		helpLink.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				try {
				    Desktop.getDesktop().browse(new URL("https://www.hasbro.com/common/instruct/risk.pdf").toURI());
				} catch (IOException e) {
				    e.printStackTrace();
				} catch (URISyntaxException e) {
				    e.printStackTrace();
				}
				
			}
		});
		
		helpSubScene.getPane().getChildren().add(helpLabel);
		helpSubScene.getPane().getChildren().add(helpLink);
		
	}
	
	/**
	 * This method sets up the credits sub-scene, 
	 * initiated when credits button is clicked.
	 * 
	 */
	private void addCreditsSubScene()
	{
		creditsSubScene = new RiskSubScene();
		mainPane.getChildren().add(creditsSubScene);
		
		RiskLabel creditsLabel = new RiskLabel("\t\t\t\tDeveloped by\n Aravind,Arvind,Charan,Shivani,Sourabh");
		creditsLabel.setLayoutX(10);
		creditsLabel.setLayoutY(180);
		creditsSubScene.getPane().getChildren().add(creditsLabel);
	}
	
	/**
	 * This method initiates the map editor view.
	 * 
	 */
	private void openMapEditFile(File file)
	{
		MapFileViewManager fileView = new MapFileViewManager(file);
	}
	
	/**
	 * This method creates and initializes PLAY Button.
	 * 
	 */
	private void addPlayButton()
	{
		RiskButton playButton = new RiskButton("PLAY");
		addMenuButton(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				showSubScene(playSubScene);
				Utilities.gameLog("Stage: Main Menu || State: PLAY selected", LogLevel.INFO);
				
			}
		});
	}
	
	/**
	 * This method creates and initializes MAP EDITOR Button.
	 * 
	 */
	private void addMapEditorButton()
	{
		RiskButton mapEditorButton = new RiskButton("MAP EDITOR");
		addMenuButton(mapEditorButton);
		
		mapEditorButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				showSubScene(mapEditorSubScene);
				Utilities.gameLog("Stage: Main Menu || State: MAP EDITOR selected", LogLevel.INFO);
				
			}
		});
		
	}
	
	/**
	 * This method creates and initializes HELP Button.
	 * 
	 */
	private void addHelpButton()
	{
		RiskButton helpButton = new RiskButton("HELP");
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				showSubScene(helpSubScene);
				Utilities.gameLog("Stage: Main Menu || State: HELP selected", LogLevel.INFO);
				
			}
		});
	}
	
	/**
	 * This method creates and initializes CREDITS Button.
	 * 
	 */
	private void addCreditsButton()
	{
		RiskButton creditsButton = new RiskButton("CREDITS");
		addMenuButton(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				showSubScene(creditsSubScene);
				Utilities.gameLog("Stage: Main Menu || State: CREDITS selected", LogLevel.INFO);
				
			}
		});
	}
	
	/**
	 * This method creates and initializes EXIT Button.
	 * 
	 */
	private void addExitButton()
	{
		RiskButton exitButton = new RiskButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainStage.close();
				Utilities.gameLog("Stage: Main Menu || State: Exited Game", LogLevel.INFO);
				
			}
		});
	}
	
	
	/**
	 * This method is used to show only the current chosen sub-scene.
	 * 
	 * @param subscene The sub-scene to be shown on screen.
	 */
	protected void showSubScene(RiskSubScene subscene)
	{
		if(sceneToHide != null)
		{
			sceneToHide.moveSubScene();
		}
		
		subscene.moveSubScene();
		sceneToHide = subscene;
		
	}

	/**
	 * This method sets up the main game background.
	 * 
	 */
	private void createBackground()
	{
		Image backgroundImage = new Image("view/resources/mainBackground.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}

	/**
	 * This method returns the created Main Menu layout.
	 * 
	 * @return mainStage The main menu layout.
	 */
	public Stage getMainStage()
	{
		return mainStage;
	}
	

}
