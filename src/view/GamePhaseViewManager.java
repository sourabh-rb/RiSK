package view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import constants.Constants;
import constants.GamePhase;
import gameEngine.Card;
import gameEngine.Country;
import gameEngine.PhaseManager;
import gameEngine.Player;
import gameEngine.StartUpPhase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;
/**
* <h1>GamePhaseViewManager</h1>
* The GamePhaseViewManager is responsible for loading
* the game phases on the screen, this includes
* initialization phase, reinforcement phase, attack phase, and fortification phase.
*
* @author  Sourabh Rajeev Badagandi
* @author  Arvind Korchibettu Adiga
* @version 1.0.0
*
*/
public class GamePhaseViewManager
{
	private final static int WIDTH = 1824;
	private final static int HEIGHT = 900;
	
	private AnchorPane gamePhasePane;
	private Scene gamePhaseScene;
	private Stage gamePhaseStage;
	
	RiskLabel phaseName;
	RiskLabel playerName;
	
	RiskButton reinforceButton;
	RiskButton attackButton;
	RiskButton fortifyButton;
	RiskButton doneButton;
	RiskButton dominationButton;
	RiskButton exitButton;
	
	GridPane phaseInfoPane;
	PlayerDominationViewManager domView;
	
	
	PhaseManager playerPhase;
	
	static Country selectedCountry;
	static Country enemyCountry;
	int defenderSpinnerValue = 0;
	int attackerSpinnerValue = 0;
	
	/**
	 * The constructor initializes game play elements and calls respective functions.
	 */
	
	public GamePhaseViewManager()
	{
		gamePhasePane = new AnchorPane();
		gamePhaseScene = new Scene(gamePhasePane, WIDTH, HEIGHT);
		gamePhaseStage = new Stage();
		gamePhaseStage.setScene(gamePhaseScene);
		gamePhaseStage.setResizable(false);
		gamePhaseStage.initStyle(StageStyle.UNDECORATED);
		
		playerPhase = new PhaseManager();
		phaseName = new RiskLabel();
		playerName = new RiskLabel();
		phaseInfoPane = new GridPane();
		
		createBackground();
		createLogo();
		createPhase();
		
		domView = new PlayerDominationViewManager();
		selectedCountry = new Country();
		enemyCountry = new Country();
		
		gamePhasePane.getChildren().add(phaseInfoPane);
	}

	/**
	 * This method sets up the game phase background with appropriate background
	 * image and screen width.
	 * 
	 */
	private void createBackground()
	{
		Image backgroundImage = new Image("view/resources/mainBackground.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		gamePhasePane.setBackground(new Background(background));
		
	}
	
	
	/**
	 * This method sets up the game logo.
	 * 
	 */
	private void createLogo()
	{
		ImageView logo = new ImageView("/view/resources/riskLogo.png");
		logo.setLayoutX(800);
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
		
		gamePhasePane.getChildren().add(logo);
	}
	
	/**
	 * This method calls the methods that displays the game play elements.
	 */
	
	public void createPhase()
	{
		
		createPhaseHeader();
		createPhaseInfo();	
		createPhaseButtons();
		
	}
	
	/**
	 * This method is used to manage game play depending upon current phase,
	 * such as INITIALIZATION,REINFORCEMENT,ATTACK or FORTIFICATION
	 */
	private void createPhaseInfo()
	{
		switch(playerPhase.getCurrentGamePhase())
		{
		case INITIALIZATION:
			createInitializationPhaseElements();
			break;
		case REINFORCEMENT:
			createReinforcementPhaseElements();
			break;
		case ATTACK:
			createAttackPhaseElements();
			break;
		case FORTIFICATION:
			createFortificationPhaseElements();
			break;
		case END:
			//TODO: handle end of turn.
			break;
		}
	}
	
	/**
	 * This method is used to create and manage Initialization phase elements
	 */
	
	private void createInitializationPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel armyCountryheader = new RiskLabel("Army Count");
		RiskLabel armyCountryLabel = new RiskLabel();
		VBox armyCountryVBox = new VBox(20, armyCountryheader, armyCountryLabel);
		
		RiskLabel countryLabel = new RiskLabel("Owned Countries");
		ComboBox<Country> countriesCombobox = new ComboBox<Country>();
		
		countriesCombobox.setItems(playerPhase.getCountriesOwnedObservableList());
		
		StringConverter<Country> converter = new StringConverter<Country>() {
	         @Override
	         public String toString(Country object) {
	             return object.getName();
	         }

	         @Override
	         public Country fromString(String string) {
	             return null;
	         }
		};
		countriesCombobox.setConverter(converter);
		
		countriesCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			@Override
			public void changed(ObservableValue<? extends Country> observable,
					Country oldValue, Country newValue)
			{
				armyCountryLabel.setText("" + newValue.getArmies());				
			}
		});
		
		countriesCombobox.getSelectionModel().selectFirst();
		
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox, armyCountryVBox);
	    
		
		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		armyCountLabel.textProperty().bind(playerPhase.armyLeftProperty().asString());
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);
		
		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerPhase.armyLeftProperty().get(), 0);
		armySpinner.setValueFactory(armyValueFactory);
		//armySpinner.getValueFactory().valueProperty().bindBidirectional();
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);
		
		RiskButton confirmButton = new RiskButton("CONFIRM");
		confirmButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				Country selectedCountryObj=null;
				selectedCountryObj=countriesCombobox.getSelectionModel().getSelectedItem();
                //decrementing player's left over armies
				//incrementing selected country armies
				playerPhase.getCurrentPlayer().reinforceArmies(selectedCountryObj,armyValueFactory.getValue(),Constants.HUMAN);
				
				
				playerPhase.nextPhase();
				createPhaseInfo();
				domView.updateDominationView();
			}
		});

		RiskLabel infoLabel = new RiskLabel("In this phase, each player is given a chance to assign the left over armies to "
				+ "their respective countries");
	
		
		phaseInfoPane.add(countryVBox, 1, 0);
		phaseInfoPane.add(armyVBox, 2, 0);
		phaseInfoPane.add(armySelectVBox, 3, 0);
		phaseInfoPane.add(confirmButton, 3, 1);
		phaseInfoPane.add(infoLabel, 1, 2,3,2);
		
		phaseInfoPane.setHgap(50);
		phaseInfoPane.setVgap(20);
		
		phaseInfoPane.setLayoutX(85);
		phaseInfoPane.setLayoutY(350);		
		
	}
	
	/**
	 * This method is used to display and manage Reinforcement phase elements
	 */
	
	private void createReinforcementPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel armyCountryheader = new RiskLabel("Army Count");
		RiskLabel armyCountryLabel = new RiskLabel();
		VBox armyCountryVBox = new VBox(20, armyCountryheader, armyCountryLabel);

		RiskLabel countryLabel = new RiskLabel("Owned Countries");
		ComboBox<Country> countriesCombobox = new ComboBox<Country>();

		countriesCombobox.setItems(playerPhase.getCountriesOwnedObservableList());

		StringConverter<Country> converter = new StringConverter<Country>() {
			@Override
			public String toString(Country object) {
				return object.getName();
			}

			@Override
			public Country fromString(String string) {
				return null;
			}
		};
		countriesCombobox.setConverter(converter);
		
		countriesCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			@Override
			public void changed(ObservableValue<? extends Country> observable,
					Country oldValue, Country newValue)
			{
				armyCountryLabel.setText("" + newValue.getArmies());				
			}
		});
		
		countriesCombobox.getSelectionModel().selectFirst();
		
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox, armyCountryVBox);


		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		armyCountLabel.textProperty().bind(playerPhase.getReinforcementArmies().asString());
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);

		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,playerPhase.getReinforcementArmies().get(), 0);
		armySpinner.setValueFactory(armyValueFactory);
		//armySpinner.getValueFactory().valueProperty().bindBidirectional();
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);

		RiskButton confirmButton = new RiskButton("CONFIRM");
		confirmButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				Country selectedCountryObj=null;
				
				selectedCountryObj=countriesCombobox.getSelectionModel().getSelectedItem();
				//incrementing selected country armies
				//decrementing player's left over armies 
				playerPhase.getCurrentPlayer().reinforceArmies(selectedCountryObj,armyValueFactory.getValue(),Constants.HUMAN);
				playerPhase.setReinforcementArmies();
				System.out.println(playerPhase.getCurrentPlayer().getNumberOfArmiesLeft());
				//playerPhase.nextPhase();
				createPhaseInfo();
				domView.updateDominationView();
				

			}
		});

		RiskLabel infoLabel = new RiskLabel("Reinforce countries and exchange cards ");

		//createCardsPhaseElements();
		phaseInfoPane.add(countryVBox, 1, 0);
		phaseInfoPane.add(armyVBox, 2, 0);
		phaseInfoPane.add(armySelectVBox, 3, 0);
		phaseInfoPane.add(confirmButton, 3, 1);
		phaseInfoPane.add(infoLabel, 1, 2,3,2);
		phaseInfoPane.add(createCardsPhaseElements(), 4, 0);

		phaseInfoPane.setHgap(50);
		phaseInfoPane.setVgap(20);

		phaseInfoPane.setLayoutX(85);
		phaseInfoPane.setLayoutY(350);

	}

	/**
	 * This method is used to display and manage card exchange phase in the 
	 * Reinforcement phase
	 * @return cardsHBox is the cards exchange view
	 */
	
	private HBox createCardsPhaseElements()
	{	
		//create card type text,display count of each type of card and add spinner to select count for exchange
		
		Label cardExchangeMessage=new Label("");
		cardExchangeMessage.setFont(Font.font("Cambria", 20));
		cardExchangeMessage.setTextFill(Color.ALICEBLUE);
		
		RiskLabel infantryHeaderLabel = new RiskLabel("Infantry : ");
		RiskLabel infantryCountLabel = new RiskLabel();
		infantryCountLabel.textProperty().bind(playerPhase.infantryCardCountProperty().asString());
		HBox infantryBox = new HBox(10, infantryHeaderLabel, infantryCountLabel);
		
		Spinner<Integer> infantrySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> infantryValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerPhase.infantryCardCountForSpinner(), 0);
		infantrySpinner.setValueFactory(infantryValueFactory);	
		
		RiskLabel cavalryHeaderLabel = new RiskLabel("Cavalry : ");
		RiskLabel cavalryCountLabel = new RiskLabel();
		cavalryCountLabel.textProperty().bind(playerPhase.cavalryCardCountProperty().asString());
		HBox cavalryBox = new HBox(10, cavalryHeaderLabel, cavalryCountLabel);
				
		Spinner<Integer> cavalrySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> cavalaryValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerPhase.cavalryCardCountForSpinner(), 0);
		cavalrySpinner.setValueFactory(cavalaryValueFactory);
		
		RiskLabel artilleryHeaderLabel = new RiskLabel("Artillery");
		RiskLabel artilleryCountLabel = new RiskLabel();
		artilleryCountLabel.textProperty().bind(playerPhase.artilleryCardCountProperty().asString());
		HBox artilleryBox = new HBox(10, artilleryHeaderLabel, artilleryCountLabel);
		
		Spinner<Integer> artillerySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> artilleryValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerPhase.artilleryCardCountForSpinner(), 0);
		artillerySpinner.setValueFactory(artilleryValueFactory);
		
		
		RiskLabel cardCountHeader = new RiskLabel("Total Cards:");
		RiskLabel cardCountLabel = new RiskLabel();
		cardCountLabel.textProperty().bind(playerPhase.totalcardsCountProperty().asString());
		HBox totCardsBox = new HBox(10, cardCountHeader, cardCountLabel);
		
		RiskButton cardsExchangeButton=new RiskButton("Exchange");
		//to implement card exchange for armies on click of the EXCHANGE button
		cardsExchangeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
		public void handle(ActionEvent event) {
				
			int armyReceivedAfterExchange=playerPhase.getArmiesForCards(artillerySpinner.getValue(), infantrySpinner.getValue(), 
					cavalrySpinner.getValue());
				System.out.println(cavalrySpinner.getValue());
			if(armyReceivedAfterExchange==0)
				{
					cardExchangeMessage.setText("Invalid user input. Please select 3 cards of same type or 3 cards of different type");
				doneButton.setDisable(true);
				}
				else
				{
					cardExchangeMessage.setText("Successful army exchange with cards. Proceed to Attack phase");
					cardsExchangeButton.setDisable(true);
					doneButton.setDisable(false);
					//cardCountLabel.setText("Total Cards :" + (count[0]+count[1]+count[2]));
			}
			
			}
		});
		
		VBox cardsVBox = new VBox(20, infantryBox,cavalryBox,artilleryBox,totCardsBox);
		VBox spinnerBox=new VBox(20,infantrySpinner,cavalrySpinner,artillerySpinner,cardsExchangeButton,cardExchangeMessage);
		HBox cardsHBox = new HBox(30, cardsVBox, spinnerBox);
		return cardsHBox;
		
	}
	
	/**
	 * This method is used to create and manage Attack phase elements
	 */
	private void createAttackPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel countryLabel = new RiskLabel("Select Country");
		ComboBox<Country> countriesCombobox = new ComboBox<Country>();
		RiskLabel attackLabel = new RiskLabel("Attack Country");
		ComboBox<Country> attackCombobox = new ComboBox<Country>();
		
		
		RiskLabel armyRemainingCountLabel = new RiskLabel();
		countriesCombobox.setItems(playerPhase.getCountriesOwnedObservableList());
		
		countriesCombobox.valueProperty().addListener(new ChangeListener<Country>() {
			
			@Override
			public void changed(ObservableValue<? extends Country> observable,
					Country oldValue, Country newValue)
			{
				selectedCountry = newValue;
				attackCombobox.setItems(playerPhase.attackableCountries(selectedCountry));
				StringConverter<Country> attackConverter = new StringConverter<Country>() {
					@Override
					public String toString(Country object) {
						return object.getName();
					}

					@Override
					public Country fromString(String string) {
						return null;
					}
				};
				attackCombobox.setConverter(attackConverter);
				attackCombobox.getSelectionModel().selectFirst();
								
			}
		});

		StringConverter<Country> converter = new StringConverter<Country>() {
			@Override
			public String toString(Country object) {
				return object.getName();
			}

			@Override
			public Country fromString(String string) {
				return null;
			}
		};
		countriesCombobox.setConverter(converter);
		
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox);
		
		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		
		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		armySpinner.setValueFactory(armyValueFactory);
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);
		
		RiskLabel attackDiceLabel = new RiskLabel("Attacker Dice");
		Spinner<Integer> attackDiceSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> attackDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, 0);
		attackDiceSpinner.setValueFactory(attackDiceFactory);
		VBox attackDiceVBox = new VBox(20, attackDiceLabel, attackDiceSpinner);
		
		
		countriesCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			@Override
			public void changed(ObservableValue<? extends Country> observable,
					Country oldValue, Country newValue)
			{
				
				armyCountLabel.setText("" + newValue.getArmies());
//				SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, (newValue.getArmies() - 1), 0);
//				armySpinner.setValueFactory(armyValueFactory);
				
				attackerSpinnerValue = playerPhase.getMaxAttackerDice(newValue);
				System.out.println(attackerSpinnerValue);
				SpinnerValueFactory<Integer> attackDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, attackerSpinnerValue, 0);
				attackDiceSpinner.setValueFactory(attackDiceFactory);
			}
		});
		countriesCombobox.getSelectionModel().selectFirst();
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);
		
		System.out.println(selectedCountry.getName());
		
		RiskLabel defenderDiceLabel = new RiskLabel("Defender Dice");
		Spinner<Integer> defenderDiceSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> defenderDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 1);
		defenderDiceSpinner.setValueFactory(defenderDiceFactory);
		VBox defenderDiceVBox = new VBox(20, defenderDiceLabel, defenderDiceSpinner);
		
		attackCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			@Override
			public void changed(ObservableValue<? extends Country> observable,
					Country oldValue, Country newValue)
			{
				enemyCountry = newValue;
				System.out.println(newValue.getArmies());
				armyRemainingCountLabel.setText("" + newValue.getArmies());
				
				defenderSpinnerValue = playerPhase.getMaxDefenderDice(newValue);
				SpinnerValueFactory<Integer> defenderDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, defenderSpinnerValue, 0);
				defenderDiceSpinner.setValueFactory(defenderDiceFactory);
			}
		});
		attackCombobox.getSelectionModel().selectFirst();
		VBox attackVBox = new VBox(20, attackLabel, attackCombobox);
		//System.out.println(enemyCountry.getName());
		RiskLabel armyRemainingLabel = new RiskLabel("Armies Remaining");
		
		VBox armyRemainingVBox = new VBox(20, armyRemainingLabel, armyRemainingCountLabel);		
		
		RiskButton rollButton = new RiskButton("ROLL");
		
		RiskLabel attackResult = new RiskLabel();
		RiskButton attackButton = new RiskButton("ATTACK");
		attackButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println(selectedCountry.getName() + " " + attackerSpinnerValue);
				System.out.println(enemyCountry.getName() + " " + defenderSpinnerValue);
				attackResult.setText(playerPhase.attackButtonFunctionality(selectedCountry, enemyCountry, attackerSpinnerValue, defenderSpinnerValue, "attack"));
				//System.out.println(playerPhase.attackButtonFunctionality(selectedCountry, enemyCountry, attackerSpinnerValue, defenderSpinnerValue, "attack"));
				//playerPhase.attackButtonFunctionality(selectedCountry, enemyCountry, attackerSpinnerValue, defenderSpinnerValue, "attack");
				
			}
		});
		
		
		RiskButton allOutAttackButton = new RiskButton("ALL OUT");
		allOutAttackButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				attackResult.setText(playerPhase.attackButtonFunctionality(selectedCountry, enemyCountry, attackerSpinnerValue, defenderSpinnerValue, "allOutWinner"));
				attackResult.setWrapText(true);
			}
		});
		
		HBox attackButtonBox = new HBox(20,attackButton, allOutAttackButton);
		

		RiskLabel infoLabel = new RiskLabel("In this phase players attack their neighbouring enemies.");
	
		phaseInfoPane.add(attackResult, 6, 0);
		phaseInfoPane.add(countryVBox, 1, 0);
		phaseInfoPane.add(armyVBox, 2, 0);
		phaseInfoPane.add(armySelectVBox, 3, 0);
		
		phaseInfoPane.add(attackVBox, 1, 1);
		phaseInfoPane.add(armyRemainingVBox, 2, 1);
		
		phaseInfoPane.add(attackDiceVBox, 1, 2);
		phaseInfoPane.add(defenderDiceVBox, 2, 2);
		phaseInfoPane.add(rollButton, 3, 2);
		phaseInfoPane.add(attackButtonBox, 1, 3);
		
		phaseInfoPane.add(infoLabel, 1, 4,4,4);
		
		phaseInfoPane.setHgap(50);
		phaseInfoPane.setVgap(20);
		
		phaseInfoPane.setLayoutX(85);
		phaseInfoPane.setLayoutY(250);
		
		
		
		
	}
	
	
	public static Country sendOwnerCountry()
	{
		return selectedCountry;
	}
	
	/**
	 * This method is used to display and manage Fortification phase elements
	 *
	 */
	private void createFortificationPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel countryLabel = new RiskLabel("Select Country");
		ComboBox<Country> countriesCombobox = new ComboBox<Country>();
		countriesCombobox.setItems(playerPhase.getCountriesOwnedObservableList());

		StringConverter<Country> converter = new StringConverter<Country>() {
			@Override
			public String toString(Country object) {
				return object.getName();
			}

			@Override
			public Country fromString(String string) {
				return null;
			}
		};
		countriesCombobox.setConverter(converter);
		countriesCombobox.getSelectionModel().selectFirst();
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox);
		
		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);
		
		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		armySpinner.setValueFactory(armyValueFactory);
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);
		
		RiskLabel neighbourLabel = new RiskLabel("Neighbour Country");
		ComboBox<Country> neighbourCombobox = new ComboBox<Country>();
		
		VBox neighbourVBox = new VBox(20, neighbourLabel, neighbourCombobox);
		
		countriesCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			

			@Override
			public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue) {
				// TODO Auto-generated method stub
				
				armyCountLabel.setText("" + newValue.getArmies());
				
				armySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,newValue.getArmies() , 0));
				neighbourCombobox.setItems(FXCollections.observableArrayList(newValue.getNeighborCounties()));
				neighbourCombobox.setConverter(converter);
				
			}
		});
		
		
		
		RiskLabel armyRemainingLabel = new RiskLabel("Armies");
		RiskLabel armyRemainingCountLabel = new RiskLabel();
		VBox armyRemainingVBox = new VBox(20, armyRemainingLabel, armyRemainingCountLabel);
		
		neighbourCombobox.valueProperty().addListener(new ChangeListener<Country>()
		{

			

			@Override
			public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue) {
				// TODO Auto-generated method stub
				
				armyRemainingCountLabel.setText("" + newValue.getArmies());
				
				
			}
		});
		
		RiskButton confirmButton = new RiskButton("CONFIRM");
		
		RiskLabel infoLabel = new RiskLabel("In this phase players can transfer armies between countries");
		confirmButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				Country selectedFirstCountryObj=null,selectedSecondCountryObj=null;
				selectedFirstCountryObj=countriesCombobox.getSelectionModel().getSelectedItem();
				selectedSecondCountryObj=neighbourCombobox.getSelectionModel().getSelectedItem();
				
                //decrementing player's left over armies
				//incrementing selected country armies
				playerPhase.getCurrentPlayer().fortifyArmies(selectedFirstCountryObj,selectedSecondCountryObj,armySpinner.getValue());
				
				RiskButton oKButton = new RiskButton("OK");
				infoLabel.setText("Armies moved to neighbour country");
				//oKButton.setLayoutX(800);
				//oKButton.setLayoutY(900);
				phaseInfoPane.add(oKButton,6,0);
				
				oKButton.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						playerPhase.nextPhase();
						createPhaseInfo();
					}
				});
				
				
			}
		});
		
		
		phaseInfoPane.add(countryVBox, 1, 0);
		phaseInfoPane.add(armyVBox, 2, 0);
		phaseInfoPane.add(armySelectVBox, 3, 0);
		
		phaseInfoPane.add(neighbourVBox, 1, 1);
		phaseInfoPane.add(armyRemainingVBox, 2, 1);
		
		phaseInfoPane.add(confirmButton, 3, 2);
		
		phaseInfoPane.add(infoLabel, 1, 3,3,3);
		
		phaseInfoPane.setHgap(50);
		phaseInfoPane.setVgap(20);
		
		phaseInfoPane.setLayoutX(85);
		phaseInfoPane.setLayoutY(350);
		
	}
	
	/**
	 * This method is used to bind and display the current player
	 */
	
	private void createPhaseHeader()
	{
		playerName.textProperty().bind(playerPhase.playerNameProperty());
		phaseName.textProperty().bind(playerPhase.phaseNameProperty());
		VBox headerVbox = new VBox(20, playerName, phaseName);
		headerVbox.setLayoutX(150);
		headerVbox.setLayoutY(150);
		
		gamePhasePane.getChildren().add(headerVbox);
		
	}
	
	/**
	 * Creates the DONE button in each phase
	 */
	private void createPhaseButtons()
	{
		doneButton = new RiskButton("DONE");
		doneButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				playerPhase.nextPhase();
				createPhaseInfo();
				
			}
		});
		

		doneButton.setLayoutX(150);
		doneButton.setLayoutY(800);
		
		gamePhasePane.getChildren().add(doneButton);
		
		
		dominationButton = new RiskButton("Domination View");
		dominationButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				PlayerDominationViewManager domView = new PlayerDominationViewManager();
				domView.showView();
				
			}
		});
		
		exitButton = new RiskButton("SAVE & EXIT");
		
		exitButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				FileChooser fileChooser = new FileChooser();
	             
	            //Set extension filter
	            FileChooser.ExtensionFilter extFilter = 
	                new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
	            fileChooser.getExtensionFilters().add(extFilter);
	            fileChooser.setInitialFileName("*.ser");
	             
	            //Show save file dialog
	            File file = fileChooser.showSaveDialog(gamePhaseStage);
	             
	            if(file != null){
						
	            	try {
	            		 
	                    FileOutputStream fileOut = new FileOutputStream(file);
	                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	                    objectOut.writeObject(StartUpPhase.getInstance());
	                    objectOut.close();
	                    System.out.println("Start Up Object saved.");
	         
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
				gamePhaseStage.close();
				
			}
		});
		
		HBox buttonBox = new HBox(25, dominationButton, exitButton);
		buttonBox.setLayoutX(650);
		buttonBox.setLayoutY(800);
		
		gamePhasePane.getChildren().add(buttonBox);
		
		
	}
	
	
	public void showView()
	{
		gamePhaseStage.show();
	}
	
	public Stage getGameStage()
	{
		return gamePhaseStage;
	}
	
	public void setInitializationPhase()
	{
		createInitializationPhaseElements();
	}
	
	public void setReinforcementPhase()
	{
		createReinforcementPhaseElements();
	}
	
	public void setAttackPhase()
	{
		createAttackPhaseElements();
	}
	
	public void setFortificationPhase()
	{
		createFortificationPhaseElements();
	}
	

}