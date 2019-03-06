package view;

import java.util.ArrayList;
import controller.fortification.*;
import java.util.List;

import constants.GamePhase;
import constants.LogLevel;
import controller.initialization.StartUpPhase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Country;
import model.Player;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;
import utilities.Utilities;
import controller.reinforcement.*;

/** This class creates a new Screen that appears once user 
 *  selects PLAY option on the main screen.
 *  
 * @author Arvind Korchibettu Adiga
 * @version 1.0.0
 */

public class GameScreenViewManager {
	
	//Declare data members
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	private AnchorPane gameScreenPane;
	private Scene gameScreenScene;
	private Stage gameScreenStage;
	private StartUpPhase startObject;
	private static int index = 0;
	private RiskLabel currentPlayerLabel,armiesAssignedLabel,reinforcementLabelC,reinforcementLabelA;
	private RiskLabel playerInitCountryLabel,playerInitLabel,attackMessageLabel,fortifyMessageLabel,fortifyCountryLabel;
	private ComboBox<String> playerInitCombobox,reinforcementCombobox,fortifyCountryCombobox;
	private TextField playerInitTextfield,reinforcementTextfield;
	private ImageView reinforcementSoldierImage,playerInitSoldierImage;
	private RiskButton reinforcementButton,fortifyMessageButton,finalButton;
	private RiskButton playerInitOkButton;
	private RiskLabel playerInitArmy;
	private Reinforcement reinforce=new Reinforcement();
	ArrayList<Country> playerNeighborList; 
	
	//Initialize reusable data members
	private RiskButton reinforceButton=new RiskButton("");
	private RiskLabel welcomeLabel=new RiskLabel("");
	private RiskLabel armyNotAssignedLabel=new RiskLabel("");
	private RiskLabel playerInitMessageLabel=new RiskLabel("");
	private RiskButton attackButton = new RiskButton("");
	private RiskButton fortifyButton = new RiskButton("");
	private RiskLabel reinforcementMessageLabel = new RiskLabel("");
	private ComboBox<String> fortifyNeighborCombobox = new ComboBox<>();
	private RiskButton fortifyCountryButton=new RiskButton("");
	private RiskButton fortifyNeighborButton=new RiskButton("");
	private RiskLabel fortifyNeighborLabel= new RiskLabel("");
	private RiskLabel fortifyEachCountryArmyCount= new RiskLabel("");
	private TextField fortifyTextField=new TextField();
	private ImageView fortifySoldierImage;
	
	List<RiskButton> menuButtons;
	
	/**
	 * This is the Constructor for GameScreenViewManager
	 * @param startObject initializes player data members
	 * 
	 */
	public GameScreenViewManager(StartUpPhase startObject)
	{
		this.startObject = startObject;
		
		gameScreenPane=new AnchorPane();
		gameScreenScene= new Scene(gameScreenPane,WIDTH,HEIGHT);
		gameScreenStage=new Stage();
		gameScreenStage.setScene(gameScreenScene);
		
		menuButtons = new ArrayList<RiskButton>();
		
		playerInit();
		createBackground();
		setFullScreen();
		createMenuButtons();
		createLogo();
		createComboboxes();
		createLabels();
		createTable();
		createWelcomeMessage();
		gameScreenStage.show();
		reinforceButton.setDisable(true);
		attackButton.setDisable(true);
		fortifyButton.setDisable(true);
	}	
	
	/**
	 * This method allows the player to initialize his armies at the beginning of the game
	 * prior to Reinforcement phase.
	 */
	public void playerInit()
	{
		reinforceButton.setText("REINFORCE");
		reinforceButton.setLayoutX(40);
		reinforceButton.setLayoutY(480);
		
		attackButton.setText("ATTACK");
		attackButton.setLayoutX(40);
		attackButton.setLayoutY(530);
		
		fortifyButton.setText("FORTIFY");
		fortifyButton.setLayoutX(40);
		fortifyButton.setLayoutY(580);
		
		if(startObject.player_List.get(index).getNumberOfArmiesLeft() > 0)
		{
			reinforceButton.setDisable(true);
			attackButton.setDisable(true);
			fortifyButton.setDisable(true);
		}
		
		playerInitLabel = new RiskLabel("Initialize unassigned armies");
		playerInitLabel.setLayoutX(720);
		playerInitLabel.setLayoutY(250);
		
		playerInitCombobox = new ComboBox<String>();
		for(Country country: startObject.player_List.get(index).getCountries())
		{
			playerInitCombobox.getItems().add(country.getName());
		}
		
		//remove the welcome message 
		playerInitCombobox.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				welcomeLabel.setText("");
				playerInitTextfield.clear();
				
			}
		});
		playerInitCombobox.setLayoutX(460);
		playerInitCombobox.setLayoutY(400);
		playerInitCombobox.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		playerInitCombobox.getValue();
		
		playerInitCountryLabel=new RiskLabel("Country");
		playerInitCountryLabel.setLayoutX(480);
		playerInitCountryLabel.setLayoutY(350);
		
		playerInitArmy = new RiskLabel("Select Army");
		playerInitArmy.setLayoutX(800);
		playerInitArmy.setLayoutY(350);
		
		playerInitTextfield=new TextField();
		playerInitTextfield.setPrefColumnCount(2);
		playerInitTextfield.getText();
		playerInitTextfield.setLayoutX(800);
		playerInitTextfield.setLayoutY(400);
		
		playerInitSoldierImage=new ImageView("/view/resources/gamePlaySoldier.png");
		playerInitSoldierImage.setFitHeight(50);
		playerInitSoldierImage.setFitWidth(50);
		playerInitSoldierImage.setLayoutX(900);
		playerInitSoldierImage.setLayoutY(390);
		
		playerInitOkButton=new RiskButton("OK");
		playerInitOkButton.setLayoutX(1200);
		playerInitOkButton.setLayoutY(390);
		
		
		//Assign action on click of OK button.
		
		playerInitOkButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) throws NullPointerException {
						playerInitTextfield.getText();
					
							if((startObject.player_List.get(index).getNumberOfArmiesLeft())>0)
							{
								//Retrieve user inputed combo box and text field value
								String playerInitComboboxValue = null;	
								int playerInitArmyInput=0;		
								Country playerInitCountryObject=null;
								try {
									playerInitComboboxValue = playerInitCombobox.getSelectionModel().getSelectedItem().toString();
									playerInitArmyInput = Integer.parseInt(playerInitTextfield.getText());
									playerInitCountryObject = Utilities.getCountryFromPlayer(startObject.player_List.get(index),playerInitComboboxValue);						
								}
								catch(Exception e) {
									playerInitMessageLabel.setText("Invalid user input");
								}
								
								
								//Retrieve country object from Utilities.getCountryFromPlayer method
								
								
								if(Utilities.isUserInputValid(playerInitArmyInput, startObject.player_List.get(index).getNumberOfArmiesLeft(), GamePhase.INITIALIZATION))
								{
								//This method is used to make changes in the number of armies in a country when the player is in the reinforcement stage
								reinforce.reinforceArmies(startObject.player_List.get(index),playerInitCountryObject,playerInitArmyInput);
								
								armyNotAssignedLabel.setText("Army not assigned : " +startObject.player_List.get(index).getNumberOfArmiesLeft());
															
								playerInitMessageLabel.setText(playerInitComboboxValue+" got additional "+playerInitArmyInput+" armies");
								playerInitMessageLabel.setLayoutX(650);
								playerInitMessageLabel.setLayoutY(575);						
								//gameScreenPane.getChildren().add(playerInitMessageLabel);
								}
								else {
									playerInitMessageLabel.setText("Invalid user input");
									playerInitMessageLabel.setLayoutX(575);
									playerInitMessageLabel.setLayoutY(575);
								}
								try {
									gameScreenPane.getChildren().add(playerInitMessageLabel);
								}
								catch(Exception e) {
									Utilities.gameLog("test",LogLevel.ERROR);
								}
								
							}
							
							else
							{
								playerInitMessageLabel.setText("Cannot assign : no more armies remaining."+"\n"+
																"Proceed to Reinforcement");	
								reinforceButton.setDisable(false);
								playerInitOkButton.setDisable(true);
							}
											
					}
				});
				
		
		gameScreenPane.getChildren().addAll(playerInitLabel,playerInitCombobox,playerInitCountryLabel,playerInitArmy,playerInitSoldierImage,
				playerInitTextfield,playerInitOkButton,playerInitMessageLabel);
		
	}
	/**
	 * This method creates the message when user begins the turn
	 */
	
	private void createWelcomeMessage()
	{		
		welcomeLabel.setText(startObject.player_List.get(index).getName()+" begins");
		welcomeLabel.setLayoutX(810);
		welcomeLabel.setLayoutY(530);
		welcomeLabel.setTextFill(Color.BEIGE);
		gameScreenPane.getChildren().addAll(welcomeLabel);
	}

	/**
	 * This method renders a background screen with appropriate dimensions and background images.
	 */
	
	private void createBackground()
	{
		Image backgroundImage = new Image("view/resources/gamePlayBackground.png",1950,1050, false, true);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		gameScreenPane.setBackground(new Background(background));
		
	}
	
	/** 
	 * This function sets the stage area to occupy the full screen size
	 */
	
	private void setFullScreen()
	{
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		gameScreenStage.setX(bounds.getMinX());
		gameScreenStage.setY(bounds.getMinY());
		gameScreenStage.setWidth(bounds.getWidth());
		gameScreenStage.setHeight(bounds.getHeight());
	}
	
	/**
	 * This method sets up the game logo and add effects on it.
	 * 
	 */
	private void createLogo()
	{
		ImageView logo = new ImageView("/view/resources/riskLogo.png");
		logo.setLayoutX(830);
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
	
	/**
	 * Creating game menu buttons on the game screen
	 */
	public void createMenuButtons()
	{
		
		RiskButton cardsButton = new RiskButton("CARDS");
		cardsButton.setLayoutX(40);
		cardsButton.setLayoutY(700);
		cardsButton.setDisable(true);
			
		reinforceButton.setText("REINFORCE");
		reinforceButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				updateReinforcementScreen();			
			}
		});
				
		attackButton.setText("ATTACK");
		attackButton.setLayoutX(40);
		attackButton.setLayoutY(530);
		attackButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gameScreenPane.getChildren().removeAll(reinforcementCombobox,reinforcementMessageLabel,reinforcementLabelC,
						reinforcementLabelA,reinforcementTextfield,reinforcementSoldierImage,reinforcementButton);
				attackMessageLabel = new RiskLabel("Attack phase");
				attackMessageLabel.setLayoutX(690);
				attackMessageLabel.setLayoutY(430);
				
				gameScreenPane.getChildren().add(attackMessageLabel);
			}
		});
		
				
		fortifyButton.setText("FORTIFY");
		fortifyButton.setLayoutX(40);
		fortifyButton.setLayoutY(580);	
		fortifyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				updateFortifyScreen();
				
			}
		});
				
		RiskButton GamePlayExitButton = new RiskButton("EXIT");
		GamePlayExitButton.setLayoutX(850);
		GamePlayExitButton.setLayoutY(900);
		
		GamePlayExitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				gameScreenStage.close();
				
			}
		});
		
		gameScreenPane.getChildren().addAll(cardsButton,reinforceButton,attackButton,fortifyButton,GamePlayExitButton);
	}
	
	/**
	 * Create labels on the game screen
	 */
	
	public void createLabels()
	{
		RiskLabel playerDescLabel1=new RiskLabel("Player Description");
		playerDescLabel1.setLayoutX(40);
		playerDescLabel1.setLayoutY(60);
		//playerDescLabel1.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		RiskLabel playerDescLabel2=new RiskLabel("Countries Assigned");
		playerDescLabel2.setLayoutX(40);
		playerDescLabel2.setLayoutY(300);
		playerDescLabel2.setTextFill(Color.BLACK);
		
		RiskLabel playerDescLabel4=new RiskLabel("Opponent Description");
		playerDescLabel4.setLayoutX(1570);
		playerDescLabel4.setLayoutY(60);
		//playerDescLabel4.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		currentPlayerLabel=new RiskLabel("Current Player : " + startObject.player_List.get(index).getName());
		currentPlayerLabel.setLayoutX(40);
		currentPlayerLabel.setLayoutY(100);
		//playerDescLabel5.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		
		armiesAssignedLabel=new RiskLabel("Armies assigned : " +startObject.player_List.get(index).getArmies());
		armiesAssignedLabel.setLayoutX(40);
		armiesAssignedLabel.setLayoutY(150);
		
		armyNotAssignedLabel.setText("Army not assigned : " +startObject.player_List.get(index).getNumberOfArmiesLeft());
		armyNotAssignedLabel.setLayoutX(40);
		armyNotAssignedLabel.setLayoutY(190);
		
		gameScreenPane.getChildren().addAll(playerDescLabel2,playerDescLabel1,playerDescLabel4,
				currentPlayerLabel,armiesAssignedLabel,armyNotAssignedLabel);
	}
	

	/** This method creates combo boxes to display player-owned countries 
	 * and neighboring countries.
	 */
	public void createComboboxes()
	{
		ComboBox<String> playerOwnedCombobox = new ComboBox<String>();
		for(Country country: startObject.player_List.get(index).getCountries())
		{
			playerOwnedCombobox.getItems().addAll(country.getName(),String.valueOf(country.getArmies()));
		}
		playerOwnedCombobox.getSelectionModel().select(0);
	
		playerOwnedCombobox.setEditable(true); 
		playerOwnedCombobox.setLayoutX(40);
		playerOwnedCombobox.setLayoutY(340);	
			
		gameScreenPane.getChildren().addAll(playerOwnedCombobox);
	}
	
	/** 
	 * Creates a table view to display player description.
	 */
	private void createTable()
	{
		TableView opponentTable=new TableView();
		opponentTable.setEditable(true);
		
		 final ObservableList<Player> data = FXCollections.observableArrayList();
		 for (Player player : startObject.player_List) {
			 data.addAll(player);
		}
		        				 
		TableColumn playerName = new TableColumn("Player Name");
		playerName.setMinWidth(100);
		playerName.setCellValueFactory(
                new PropertyValueFactory<Player, String>("name"));

		TableColumn playerArmies= new TableColumn("Total armies");
		playerArmies.setMinWidth(100);
		playerArmies.setCellValueFactory(
                new PropertyValueFactory<Player, String>("armies"));
		
		opponentTable.setLayoutX(1570);
		opponentTable.setLayoutY(120);
		opponentTable.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));  
		opponentTable.setItems(data);
		opponentTable.getColumns().addAll(playerName,playerArmies);
		gameScreenPane.getChildren().addAll(opponentTable);
	}
	
	
	/**
	 * This method is used to update the game screen when user clicks on Reinforce.
	 */
	
	private void updateReinforcementScreen()
	{
		gameScreenPane.getChildren().removeAll(welcomeLabel,playerInitLabel,playerInitCombobox,playerInitCountryLabel,
				playerInitArmy,playerInitSoldierImage,playerInitMessageLabel,playerInitTextfield,playerInitOkButton);
		
		playerInitLabel.setText("Reinforcement Phase");
		reinforceButton.setDisable(true);
		
		
		//This method returns the number of armies that the player will get in the reinforcement phase of the game.
		reinforce=new Reinforcement();
		reinforce.getReinforcementArmies(startObject.player_List.get(index));
		armyNotAssignedLabel.setText("Army not assigned : " 
							+(startObject.player_List.get(index).getNumberOfArmiesLeft()));
		
		reinforcementCombobox=new ComboBox<String>();
		for(Country country: startObject.player_List.get(index).getCountries())
		{
			reinforcementCombobox.getItems().add(country.getName());
		}
		reinforcementCombobox.setLayoutX(460);
		reinforcementCombobox.setLayoutY(400);
		reinforcementCombobox.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
		reinforcementCombobox.getValue();
		
		reinforcementCombobox.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				gameScreenPane.getChildren().remove(reinforcementMessageLabel);
				reinforcementTextfield.clear();
			}
		});
		
		reinforcementLabelC=new RiskLabel("Country");
		reinforcementLabelC.setLayoutX(480);
		reinforcementLabelC.setLayoutY(350);
			
		reinforcementLabelA = new RiskLabel("Select Army");
		reinforcementLabelA.setLayoutX(800);
		reinforcementLabelA.setLayoutY(350);
		
		reinforcementTextfield=new TextField();
		reinforcementTextfield.setPrefColumnCount(2);
		reinforcementTextfield.getText();
		reinforcementTextfield.setLayoutX(800);
		reinforcementTextfield.setLayoutY(400);
		
		reinforcementSoldierImage=new ImageView("/view/resources/gamePlaySoldier.png");
		reinforcementSoldierImage.setFitHeight(50);
		reinforcementSoldierImage.setFitWidth(50);
		reinforcementSoldierImage.setLayoutX(900);
		reinforcementSoldierImage.setLayoutY(390);
		
		reinforcementButton=new RiskButton("OK");
		reinforcementButton.setLayoutX(1200);
		reinforcementButton.setLayoutY(390);
		
		//Assign action on click of OK button.
		
		reinforcementButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if((startObject.player_List.get(index).getNumberOfArmiesLeft())>0)
				{
					//Retrieve user inputed combo box and text field value
					String reinforcementComboboxValue = reinforcementCombobox.getSelectionModel().getSelectedItem().toString();	
					int userArmyInput=0;
					try {
						userArmyInput = Integer.parseInt(reinforcementTextfield.getText());
					}
					catch(Exception e) {
						reinforcementMessageLabel.setText("Invalid user input");
					}
					
					//Retrieve country object from Utilities.getCountryFromPlayer method
					Country reinforcementCountry = Utilities.getCountryFromPlayer(startObject.player_List.get(index),reinforcementComboboxValue);
					
					if(Utilities.isUserInputValid(userArmyInput, startObject.player_List.get(index).getNumberOfArmiesLeft(), GamePhase.REINFORCEMENT))
					{													
						//This method is used to make changes in the number of armies in a country when the player is in the reinforcement stage			
						reinforce.reinforceArmies(startObject.player_List.get(index),reinforcementCountry,userArmyInput);
					
						armyNotAssignedLabel.setText("Army not assigned : " +startObject.player_List.get(index).getNumberOfArmiesLeft());
					
						reinforcementMessageLabel.setText(reinforcementComboboxValue+" got additional "+userArmyInput+" armies");
						reinforcementMessageLabel.setLayoutX(575);
						reinforcementMessageLabel.setLayoutY(575);
						//gameScreenPane.getChildren().add(reinforcementMessageLabel);
						
					}
					else {
						reinforcementMessageLabel.setText("Invalid user input");
						reinforcementMessageLabel.setLayoutX(575);
						reinforcementMessageLabel.setLayoutY(575);
						//gameScreenPane.getChildren().add(reinforcementMessageLabel);
					}
					try {
						gameScreenPane.getChildren().add(reinforcementMessageLabel);
					}
					catch(Exception e) {
						Utilities.gameLog("test",LogLevel.ERROR);
					}
					
				}
				
				else
				{
					reinforcementMessageLabel.setText("Cannot assign : no more armies remaining"+"\n"+"Proceed to next phase");
					fortifyButton.setDisable(false);
					reinforcementButton.setDisable(true);
					try {
					gameScreenPane.getChildren().add(reinforcementMessageLabel);
					}
					catch(Exception e) {
						Utilities.gameLog("test",LogLevel.ERROR);
					}
				}
				//gameScreenPane.getChildren().add(reinforcementMessageLabel);
			}
		});
		
		gameScreenPane.getChildren().addAll(reinforcementCombobox,reinforcementLabelC,reinforcementLabelA, reinforcementTextfield,
				reinforcementSoldierImage,reinforcementButton,playerInitLabel);
				
	}
	
	/**
	 * This method is used to update the game screen when user clicks on Fortify.
	 */
	private void updateFortifyScreen()
	{ 	
		if(Utilities.isFortificationPossible(startObject.player_List.get(index)))
		{
		//remove reinforce screen elements.
		reinforceButton.setDisable(true);
		fortifyButton.setDisable(true);
		gameScreenPane.getChildren().removeAll(reinforcementCombobox,reinforcementLabelC,reinforcementLabelA, reinforcementTextfield,
				reinforcementSoldierImage,playerInitLabel,reinforcementButton,reinforcementMessageLabel);
				
		//create fortification phase message and button
		fortifyMessageLabel=new RiskLabel(startObject.player_List.get(index).getName()+" has completed attack phase"+"\n"+"Begin fortification");
		fortifyMessageLabel.setLayoutX(690);
		fortifyMessageLabel.setLayoutY(430);
		fortifyMessageLabel.setTextFill(Color.BEIGE);
		
		fortifyMessageButton=new RiskButton("BEGIN");
		fortifyMessageButton.setLayoutX(800);
		fortifyMessageButton.setLayoutY(520);
		
		gameScreenPane.getChildren().addAll(fortifyMessageLabel,fortifyMessageButton);
		
		//create combo box for countries
		fortifyMessageButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				gameScreenPane.getChildren().removeAll(fortifyMessageButton);
				fortifyMessageLabel.setText("Move your armies to friendly neighbors");
				fortifyMessageLabel.setLayoutX(650);
				fortifyMessageLabel.setLayoutY(320);
				
				fortifyCountryLabel=new RiskLabel("Countries");
				fortifyCountryLabel.setLayoutX(470);
				fortifyCountryLabel.setLayoutY(380);
				
				fortifyCountryCombobox=new ComboBox<String>();
				System.out.println(index);
				for(Country country: startObject.player_List.get(index).getCountries())
				{
					fortifyCountryCombobox.getItems().add(country.getName());
				}
				fortifyCountryCombobox.setLayoutX(460);
				fortifyCountryCombobox.setLayoutY(420);
				fortifyCountryCombobox.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
				fortifyCountryCombobox.getValue();
				
				fortifyCountryButton.setText("OK");
				fortifyCountryButton.setLayoutX(450);
				fortifyCountryButton.setLayoutY(500);
				
				fortifyNeighborLabel= new RiskLabel("Neighbouring countries");
				fortifyNeighborLabel.setLayoutX(800);
				fortifyNeighborLabel.setLayoutY(380);				
				
				fortifyNeighborCombobox.setLayoutX(800);
				fortifyNeighborCombobox.setLayoutY(420);
				
				fortifyNeighborButton.setText("MOVE");
				fortifyNeighborButton.setLayoutX(1000);
				fortifyNeighborButton.setLayoutY(510);
				
				fortifyEachCountryArmyCount=new RiskLabel("");
				fortifyEachCountryArmyCount.setLayoutX(450);
				fortifyEachCountryArmyCount.setLayoutY(600);
				
				//fortifyTextField=new TextField();
				fortifyTextField.getText();
				fortifyTextField.setLayoutX(800);
				fortifyTextField.setLayoutY(500);
				fortifyTextField.setPrefColumnCount(2);
				
				fortifySoldierImage=new ImageView("/view/resources/gamePlaySoldier.png");
				fortifySoldierImage.setFitHeight(50);
				fortifySoldierImage.setFitWidth(50);
				fortifySoldierImage.setLayoutX(855);
				fortifySoldierImage.setLayoutY(490);
						
								
				//create OK button on selecting country
				fortifyCountryCombobox.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						fortifyNeighborLabel.setText("");
						fortifyNeighborLabel.setText("");
						fortifyCountryButton.setDisable(false);
						fortifyNeighborButton.setDisable(false);
						//create label & combo box for neighboring countries
						fortifyCountryButton.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								
								fortifyCountryButton.setDisable(true);
								String fortifyCountryValue = fortifyCountryCombobox.getSelectionModel().getSelectedItem().toString();
								Country fortifyCountryObject = Utilities.getCountryFromPlayer(startObject.player_List.get(index),fortifyCountryValue);
							
								if(isArmyTransferrable(fortifyCountryObject)) 
								{
								fortifyEachCountryArmyCount.setText(fortifyCountryObject.getName()+" has "
								+fortifyCountryObject.getArmies()+" armies"+"\n"+
								"Player can move upto "+(fortifyCountryObject.getArmies()-1)+" armies");
								fortifyTextField.setDisable(false);
								}
								else
								{
									fortifyEachCountryArmyCount.setText("WARNING : Cannot move Armies to Neighbors as Country has single army");
									fortifyTextField.setDisable(true);
									fortifyNeighborButton.setDisable(true);
									
								}
								
								playerNeighborList = new ArrayList<Country>();
								playerNeighborList = Utilities.getNeighborList(startObject.player_List.get(index),fortifyCountryObject);
								
								fortifyNeighborCombobox.getItems().removeAll(fortifyNeighborCombobox.getItems());							
								
								if(playerNeighborList.isEmpty())
								{
									fortifyNeighborCombobox.getItems().add("No neighbors");
									fortifyNeighborButton.setDisable(true);
								}
								
								for(Country country : playerNeighborList)
								{						
									fortifyNeighborCombobox.getItems().add(country.getName());
								}
								
								
								//set action for MOVE button
								fortifyNeighborButton.setOnAction(new EventHandler<ActionEvent>() {
									
									@Override
									public void handle(ActionEvent event) throws NullPointerException {
										int userArmyInput=0;
										String fortifyNeighborValue =null;
										Country fortifyNeighborCountryObject=null;
										try {
											userArmyInput = Integer.parseInt(fortifyTextField.getText());
											fortifyNeighborValue=fortifyNeighborCombobox.getSelectionModel().getSelectedItem().toString();
											fortifyNeighborCountryObject = Utilities.getCountryFromPlayer(startObject.player_List.get(index),fortifyNeighborValue);
										}
										catch(Exception e) {
											fortifyNeighborLabel.setText("Invalid user input");
											fortifyNeighborLabel.setLayoutX(1000);
											fortifyNeighborLabel.setLayoutY(420);
										}																													
						
										if((Utilities.isUserInputValid(userArmyInput, fortifyCountryObject.getArmies(), GamePhase.FORTIFICATION)))
										{
											Fortification.fortifyArmies(startObject.player_List.get(index),fortifyCountryObject,fortifyNeighborCountryObject,userArmyInput);											
											fortifyNeighborLabel.setText(userArmyInput+" armies moved from "+fortifyCountryValue+" to "+fortifyNeighborValue);
											fortifyNeighborLabel.setLayoutX(650);
											fortifyNeighborLabel.setLayoutY(430);	
										
											fortifyCountryLabel.setText("Fortification phase has ended. "+"\n"
													+" Next player will begin turn.");
											fortifyCountryLabel.setLayoutX(650);
											fortifyCountryLabel.setLayoutY(550);
										    
											finalButton=new RiskButton("OK");
											finalButton.setLayoutX(700);
											finalButton.setLayoutY(700);
											
											finalButton.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent event) {
													if(index >=(startObject.player_List.size() - 1))
													{
														index = 0;
													}
													else 
													{
														index++;
													}
													gameScreenStage.hide();
													GameScreenViewManager gsvm=new GameScreenViewManager(startObject);
												}												
											});
											
											gameScreenPane.getChildren().add(finalButton);
											
											gameScreenPane.getChildren().removeAll(fortifyCountryCombobox,fortifyCountryButton,fortifyMessageLabel,
												fortifyNeighborButton,fortifyNeighborCombobox,fortifyTextField,fortifySoldierImage,fortifyEachCountryArmyCount);																	
										}
										else
										{
											fortifyNeighborLabel.setText("Invalid user input");
											fortifyNeighborLabel.setLayoutX(1000);
											fortifyNeighborLabel.setLayoutY(420);
										}
										
									}
								});
															
							}
						});
						//OK button ends here
					}
				});
				gameScreenPane.getChildren().addAll(fortifyCountryCombobox,fortifyCountryLabel,fortifyCountryButton,
						fortifyNeighborLabel,fortifyNeighborButton,fortifyNeighborCombobox,fortifyEachCountryArmyCount,fortifyTextField,
						fortifySoldierImage);
				//fortify BEGIN ends here
				
				//Fortify neighbor country on click of MOVE button
				
			}
		});
	
	}
		else {
			if(index >=(startObject.player_List.size() - 1))
			{
				index = 0;
			}
			else 
			{
				index++;
			}
			gameScreenStage.hide();
			GameScreenViewManager gsvm=new GameScreenViewManager(startObject);
		}
	}
	
	/**
	 * This method is used by player to determine if armies can be transferred from country to neighbor
	 * @param country The country object
	 * @return Booleyan
	 */
	
	private boolean isArmyTransferrable(Country country)
	{
		if(country.getArmies() - 1 > 1) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
