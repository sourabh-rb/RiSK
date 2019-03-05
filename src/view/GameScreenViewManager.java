package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import controller.initialization.StartUpPhase;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;
import view.ui_elements.RiskSubScene;

/** This class creates a new Screen that appears once user 
 *  selects PLAY option on the main screen.
 *  
 * @author Arvind Korchibettu Adiga
 *
 */

public class GameScreenViewManager {
	
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	private AnchorPane gameScreenPane;
	private Scene gameScreenScene;
	private Stage gameScreenStage;
	private StartUpPhase startObject;
	
	List<RiskButton> menuButtons;
	
	public GameScreenViewManager(StartUpPhase startObject)
	{
		this.startObject = startObject;
		gameScreenPane=new AnchorPane();
		gameScreenScene= new Scene(gameScreenPane,WIDTH,HEIGHT);
		gameScreenStage=new Stage();
		gameScreenStage.setScene(gameScreenScene);
		
		menuButtons = new ArrayList<RiskButton>();
		
		createBackground();
		createMenuButtons();
		createLogo();
		createComboboxes();
		createLabels();
		createTable();
		gameScreenStage.show();
	}	
	
	/**
	 * This method renders a background screen with appropriate dimensions and background images.
	 */
	
	private void createBackground()
	{
		Image backgroundImage = new Image("view/resources/mainBackground.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		gameScreenPane.setBackground(new Background(background));
		
	}
	
	/**
	 * This method sets up the main game logo.
	 * 
	 */
	private void createLogo()
	{
		ImageView logo = new ImageView("/view/resources/riskLogo.png");
		logo.setLayoutX(370);
		logo.setLayoutY(30);
		
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
		
		gameScreenPane.getChildren().add(logo);		
	}
	
	/**Creating buttons on the game screen
	 */
	public void createMenuButtons()
	{
		
		RiskButton cardsButton = new RiskButton("CARDS");
		cardsButton.setLayoutX(40);
		cardsButton.setLayoutY(600);
				
		RiskButton reinforceButton = new RiskButton("REINFORCE");
		reinforceButton.setLayoutX(40);
		reinforceButton.setLayoutY(380);
				
		RiskButton attackButton = new RiskButton("ATTACK");
		attackButton.setLayoutX(40);
		attackButton.setLayoutY(430);
				
		RiskButton fortifyButton = new RiskButton("FORTIFY");
		fortifyButton.setLayoutX(40);
		fortifyButton.setLayoutY(480);
				
		RiskButton helpButton = new RiskButton("HELP");
		helpButton.setLayoutX(420);
		helpButton.setLayoutY(700);
		
		gameScreenPane.getChildren().addAll(cardsButton,reinforceButton,attackButton,fortifyButton,helpButton);
	}
	
	/**Create labels on the game screen
	 * 
	 */
	
	public void createLabels()
	{
		RiskLabel playerDescLabel1=new RiskLabel("Player Description");
		playerDescLabel1.setLayoutX(40);
		playerDescLabel1.setLayoutY(60);
		//playerDescLabel1.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		RiskLabel playerDescLabel5=new RiskLabel("Current Player : "+ startObject.player_List.get(0).getName());
		playerDescLabel5.setLayoutX(40);
		playerDescLabel5.setLayoutY(130);
		playerDescLabel5.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		RiskLabel playerDescLabel4=new RiskLabel("Opponent Description");
		playerDescLabel4.setLayoutX(650);
		playerDescLabel4.setLayoutY(60);
		playerDescLabel4.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		RiskLabel playerDescLabel3=new RiskLabel("Neighbours");
		playerDescLabel3.setLayoutX(40);
		playerDescLabel3.setLayoutY(280);
		playerDescLabel3.setTextFill(Color.BLACK);
		
			
		RiskLabel playerDescLabel2=new RiskLabel("Countries Assigned");
		playerDescLabel2.setLayoutX(40);
		playerDescLabel2.setLayoutY(200);
		playerDescLabel2.setTextFill(Color.BLACK);
		
		gameScreenPane.getChildren().addAll(playerDescLabel2,playerDescLabel1,playerDescLabel3,playerDescLabel4,playerDescLabel5);
	}
	
	
	/** This method creates combo boxes to display player-owned countries 
	 * and neighboring countries.
	 */
	public void createComboboxes()
	{
		ComboBox<String> playerOwnedCombobox = new ComboBox<String>();
		playerOwnedCombobox.getItems().addAll("Country1","Country2","Country3","Country4","Country5");
		playerOwnedCombobox.setEditable(true); 
		playerOwnedCombobox.setLayoutX(40);
		playerOwnedCombobox.setLayoutY(240);

		ComboBox<String> neighboursCombobox = new ComboBox<String>();
		neighboursCombobox.getItems().addAll("Country1","Country2","Country3","Country4","Country5");
		neighboursCombobox.setEditable(true); 
		neighboursCombobox.setLayoutX(40);
		neighboursCombobox.setLayoutY(320);
		
		
		gameScreenPane.getChildren().addAll(neighboursCombobox,playerOwnedCombobox);
	}
	
	/** Creates a table view to display opponent description.
	 * 
	 */
	public void createTable()
	{
		TableView opponentTable=new TableView();
		opponentTable.setEditable(true);
		
		TableColumn opponentName = new TableColumn("Player Name");
		TableColumn opponentCountries = new TableColumn("Territories");
		TableColumn opponentScore= new TableColumn("Score");
		opponentTable.setLayoutX(700);
		opponentTable.setLayoutY(120);
		opponentTable.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));        
		opponentTable.getColumns().addAll(opponentName,opponentCountries,opponentScore);
		gameScreenPane.getChildren().addAll(opponentTable);
	}

}
