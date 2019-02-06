package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.ui.RiskButton;
import model.ui.RiskSubScene;

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
//aravind commmmmmmmmmit
public class MainMenuViewManager
{
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	private static final int MENU_BUTTON_STARTX = 100;
	private static final int MENU_BUTTON_STARTY = 200;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private RiskSubScene playSubScene;
	private RiskSubScene helpSubScene;
	private RiskSubScene creditsSubScene;
	
	private RiskSubScene sceneToHide;
	
	List<RiskButton> menuButtons;
	
	
	
	
	public MainMenuViewManager()
	{
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		menuButtons = new ArrayList<RiskButton>();
		
		createBackground();
		createMenuButtons();
		createLogo();
		
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
	 * 2. HELP
	 * 3. CREDITS
	 * 4. EXIT
	 */
	private void createMenuButtons()
	{
		addPlayButton();
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
	 * This method set up subscenes for menu buttons.
	 * 
	 */
	private void addSubScenes()
	{
		playSubScene = new RiskSubScene();
		mainPane.getChildren().add(playSubScene);
		
		helpSubScene = new RiskSubScene();
		mainPane.getChildren().add(helpSubScene);
		
		creditsSubScene = new RiskSubScene();
		mainPane.getChildren().add(creditsSubScene);
		
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
				
			}
		});
	}
	
	/**
	 * This method is used to show only the current chosen subscene.
	 * 
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
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}

	/**
	 * This method returns the created Main Menu layout.
	 * 
	 * @return mainStage : the main menu layout.
	 */
	public Stage getMainStage()
	{
		return mainStage;
	}

}
