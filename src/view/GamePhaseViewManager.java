package view;

import constants.GamePhase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PhaseManager;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;
/**
* <h1>GamePhaseViewManager</h1>
* The GamePhaseViewManager is responsible for loading
* the game phases on the screen, this includes
* initialization phase, reinforcement phase, attack phase, and fortification phase.
*
* @author  Sourabh Rajeev Badagandi
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
	
	GridPane phaseInfoPane;
	
	PhaseManager playerPhase;
	
	
	public GamePhaseViewManager()
	{
		gamePhasePane = new AnchorPane();
		gamePhaseScene = new Scene(gamePhasePane, WIDTH, HEIGHT);
		gamePhaseStage = new Stage();
		gamePhaseStage.setScene(gamePhaseScene);
		gamePhaseStage.setResizable(false);
		//gamePhaseStage.initStyle(StageStyle.UNDECORATED);
		gamePhaseStage.show();
		
		playerPhase = new PhaseManager();
		phaseName = new RiskLabel();
		playerName = new RiskLabel();
		phaseInfoPane = new GridPane();
		
		createBackground();
		createLogo();
		createPhase();
		
		gamePhasePane.getChildren().add(phaseInfoPane);
	}
	
	/**
	 * This method sets up the game phase background.
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
	
	
	public void createPhase()
	{
		
		createPhaseHeader();
		createPhaseInfo();	
		createPhaseButtons();
		
	}
	
	private void createPhaseInfo()
	{
		switch(playerPhase.getCurrentGamePhase())
		{
		case INITIALIZATION:
			createInitializationPhaseElements();
			break;
		case REINFORCEMENT:
			createInitializationPhaseElements();
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
	
	private void createInitializationPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel countryLabel = new RiskLabel("Owned Countries");
		ComboBox<String> countriesCombobox = new ComboBox<String>();
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox);
		
		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);
		
		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		armySpinner.setValueFactory(armyValueFactory);
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);
		
		RiskButton confirmButton = new RiskButton("CONFIRM");

		RiskLabel infoLabel = new RiskLabel("Phase Info goes here!");
	
		
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
	
	private void createAttackPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel countryLabel = new RiskLabel("Select Country");
		ComboBox<String> countriesCombobox = new ComboBox<String>();
		VBox countryVBox = new VBox(20, countryLabel, countriesCombobox);
		
		RiskLabel armyLabel = new RiskLabel("Armies");
		RiskLabel armyCountLabel = new RiskLabel();
		VBox armyVBox = new VBox(20, armyLabel, armyCountLabel);
		
		RiskLabel armySelectLabel = new RiskLabel("Select Army");
		Spinner<Integer> armySpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> armyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0);
		armySpinner.setValueFactory(armyValueFactory);
		VBox armySelectVBox = new VBox(20, armySelectLabel, armySpinner);
		
		RiskLabel attackLabel = new RiskLabel("Attack Country");
		ComboBox<String> attackCombobox = new ComboBox<String>();
		VBox attackVBox = new VBox(20, attackLabel, attackCombobox);
		
		RiskLabel armyRemainingLabel = new RiskLabel("Armies Remaining");
		RiskLabel armyRemainingCountLabel = new RiskLabel();
		VBox armyRemainingVBox = new VBox(20, armyRemainingLabel, armyRemainingCountLabel);
		
		
		RiskLabel attackDiceLabel = new RiskLabel("Attacker Dice");
		Spinner<Integer> attackDiceSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> attackDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1);
		attackDiceSpinner.setValueFactory(attackDiceFactory);
		VBox attackDiceVBox = new VBox(20, attackDiceLabel, attackDiceSpinner);
		
		RiskLabel defenderDiceLabel = new RiskLabel("Defender Dice");
		Spinner<Integer> defenderDiceSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> defenderDiceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 1);
		defenderDiceSpinner.setValueFactory(defenderDiceFactory);
		VBox defenderDiceVBox = new VBox(20, defenderDiceLabel, defenderDiceSpinner);
		
		RiskButton rollButton = new RiskButton("ROLL");

		RiskLabel infoLabel = new RiskLabel("Phase Info goes here!");
	
		
		phaseInfoPane.add(countryVBox, 1, 0);
		phaseInfoPane.add(armyVBox, 2, 0);
		phaseInfoPane.add(armySelectVBox, 3, 0);
		
		phaseInfoPane.add(attackVBox, 1, 1);
		phaseInfoPane.add(armyRemainingVBox, 2, 1);
		
		phaseInfoPane.add(attackDiceVBox, 1, 2);
		phaseInfoPane.add(defenderDiceVBox, 2, 2);
		phaseInfoPane.add(rollButton, 3, 2);
		
		phaseInfoPane.add(infoLabel, 1, 3,3,3);
		
		phaseInfoPane.setHgap(50);
		phaseInfoPane.setVgap(20);
		
		phaseInfoPane.setLayoutX(85);
		phaseInfoPane.setLayoutY(350);
		
		
	}
	
	private void createFortificationPhaseElements()
	{
		phaseInfoPane.getChildren().clear();
		
		RiskLabel countryLabel = new RiskLabel("Select Country");
		ComboBox<String> countriesCombobox = new ComboBox<String>();
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
		ComboBox<String> neighbourCombobox = new ComboBox<String>();
		VBox neighbourVBox = new VBox(20, neighbourLabel, neighbourCombobox);
		
		RiskLabel armyRemainingLabel = new RiskLabel("Armies");
		RiskLabel armyRemainingCountLabel = new RiskLabel();
		VBox armyRemainingVBox = new VBox(20, armyRemainingLabel, armyRemainingCountLabel);
		
		RiskButton confirmButton = new RiskButton("CONFIRM");
		
		RiskLabel infoLabel = new RiskLabel("Phase Info goes here!");
		
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
	
	private void createPhaseHeader()
	{
		playerName.textProperty().bind(playerPhase.playerNameProperty());
		phaseName.textProperty().bind(playerPhase.phaseNameProperty());
		VBox headerVbox = new VBox(20, playerName, phaseName);
		headerVbox.setLayoutX(150);
		headerVbox.setLayoutY(150);
		
		gamePhasePane.getChildren().add(headerVbox);
		
	}
	
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
		
//		reinforceButton = new RiskButton("REINFORCE");
//		reinforceButton.disableProperty().bind(playerPhase.phaseNameProperty().isEqualTo("ATTACK")
//									.or(playerPhase.phaseNameProperty().isEqualTo("FORTIFICATION")));
//		attackButton = new RiskButton("ATTACK");
//		
//		fortifyButton = new RiskButton("FORTIFY");
		
//HBox buttonBox = new HBox(25, doneButton, reinforceButton, attackButton, fortifyButton);
		doneButton.setLayoutX(150);
		doneButton.setLayoutY(800);
		
		gamePhasePane.getChildren().add(doneButton);
	}
	
	public Stage getGameStage()
	{
		return gamePhaseStage;
	}
	

}