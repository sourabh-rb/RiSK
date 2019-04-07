package view;

import java.io.File;

import gameEngine.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import view.ui_elements.RiskButton;
import view.ui_elements.RiskLabel;

/**
* <h1>TournamentModeViewManager</h1>
* The TournamentModeViewManager is responsible for handling 
* tournament view elements which includes,
* Choosing maps, computer strategy players, number of games and the result.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class TournamentModeViewManager
{
	private final static int WIDTH = 1224;
    private final static int HEIGHT = 768;
    
	private AnchorPane tournamentPane;
	private Scene tournamentScene;
	private Stage tournamentStage;
	private GridPane mainLayout;
	
	String borderStyle = "-fx-border-color: black;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-padding: 15;\n" +
            "-fx-border-width: 3;\n";
	
	/**
	 * The constructor initializes tournament view elements.
	 */
	public TournamentModeViewManager()
	{
		tournamentPane = new AnchorPane();
		tournamentScene = new Scene(tournamentPane, WIDTH, HEIGHT);
		tournamentStage = new Stage();
		tournamentStage.setScene(tournamentScene);
		tournamentStage.setResizable(false);
		tournamentStage.setTitle("Tournament Mode");
		
		mainLayout = new GridPane();
		createBackground();
		createLogo();
		createLayout();
		
		tournamentPane.getChildren().add(mainLayout);
	}
	
	/**
	 * The method displays the tournament view.
	 */
	public void showTournamentView()
	{
		tournamentStage.show();
	}
	
	/**
	 * This method sets up the tournament view  background with appropriate background
	 * image and screen width.
	 * 
	 */
	private void createBackground()
	{
		Image backgroundImage = new Image("view/resources/tournament_background.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(
				backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		tournamentPane.setBackground(new Background(background));
		
	}
	
	
	/**
	 * This method sets up the game logo.
	 * 
	 */
	private void createLogo()
	{
		ImageView logo = new ImageView("/view/resources/riskLogo.png");
		logo.setLayoutX(450);
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
		
		tournamentPane.getChildren().add(logo);
	}
	
	/**
	 * This method sets up the map chooser portion of the tournament mode view.
	 * 
	 */
	private VBox createMapOptionsLayout()
	{
		File mapDir = new File(".//Maps"); 
		ObservableList<File> mapsList =  FXCollections.observableArrayList();
		if(mapDir.isDirectory())
		{
			File[] fileList = mapDir.listFiles();
			
			mapsList.add(new File("null"));
			for(File mapFile : fileList)
			{
				mapsList.add(mapFile);
			}
		}
		
		StringConverter<File> converter = new StringConverter<File>() {
			@Override
			public String toString(File object) {
				return object.getName();
			}

			@Override
			public File fromString(String string) {
				return null;
			}
		};
		
		RiskLabel mapLabel1 = new RiskLabel("Map  1");
		ComboBox<File> mapCombobox1 = new ComboBox<File>();
		mapCombobox1.setItems(mapsList);
		mapCombobox1.setConverter(converter);
		mapCombobox1.getSelectionModel().selectFirst();
		HBox mapBox1 = new HBox(20, mapLabel1, mapCombobox1);
		
		RiskLabel mapLabel2 = new RiskLabel("Map 2");
		ComboBox<File> mapCombobox2 = new ComboBox<File>();
		mapCombobox2.setItems(mapsList);
		mapCombobox2.setConverter(converter);
		mapCombobox2.getSelectionModel().selectFirst();
		HBox mapBox2 = new HBox(20, mapLabel2, mapCombobox2);
		
		RiskLabel mapLabel3 = new RiskLabel("Map 3");
		ComboBox<File> mapCombobox3 = new ComboBox<File>();
		mapCombobox3.setItems(mapsList);
		mapCombobox3.setConverter(converter);
		mapCombobox3.getSelectionModel().selectFirst();
		HBox mapBox3 = new HBox(20, mapLabel3, mapCombobox3);
		
		RiskLabel mapLabel4 = new RiskLabel("Map 4");
		ComboBox<File> mapCombobox4 = new ComboBox<File>();
		mapCombobox4.setItems(mapsList);
		mapCombobox4.setConverter(converter);
		mapCombobox4.getSelectionModel().selectFirst();
		HBox mapBox4 = new HBox(20, mapLabel4, mapCombobox4);
		
		RiskLabel mapLabel5 = new RiskLabel("Map 5");
		ComboBox<File> mapCombobox5 = new ComboBox<File>();
		mapCombobox5.setItems(mapsList);
		mapCombobox5.setConverter(converter);
		mapCombobox5.getSelectionModel().selectFirst();
		HBox mapBox5 = new HBox(20, mapLabel5, mapCombobox5);
		
		VBox mapsVBox = new VBox(10, mapBox1, mapBox2, mapBox3, mapBox4, mapBox5);
		mapsVBox.setStyle(borderStyle);
		return mapsVBox;
	}
	
	/**
	 * This method sets up the player chooser portion of the tournament mode view.
	 * 
	 */
	private VBox createPlayerOptionsLayout()
	{
		ObservableList<String> playerOptions = 
		        FXCollections.observableArrayList(
		            "null",
		            "Human",
		            "Aggressive",
		            "Benevolent",
		            "Random",
		            "Cheater"
		        );
		
		RiskLabel playerLabel1 = new RiskLabel("Player  1");
		ComboBox<String> playerCombobox1 = new ComboBox<String>();
		playerCombobox1.setItems(playerOptions);
		playerCombobox1.getSelectionModel().selectFirst();
		HBox playerBox1 = new HBox(20, playerLabel1, playerCombobox1);
		
		RiskLabel playerLabel2 = new RiskLabel("Player 2");
		ComboBox<String> playerCombobox2 = new ComboBox<String>();
		playerCombobox2.setItems(playerOptions);
		playerCombobox2.getSelectionModel().selectFirst();
		HBox playerBox2 = new HBox(20, playerLabel2, playerCombobox2);
		
		RiskLabel playerLabel3 = new RiskLabel("Player 3");
		ComboBox<String> playerCombobox3 = new ComboBox<String>();
		playerCombobox3.setItems(playerOptions);
		playerCombobox3.getSelectionModel().selectFirst();
		HBox playerBox3 = new HBox(20, playerLabel3, playerCombobox3);
		
		RiskLabel playerLabel4 = new RiskLabel("Player 4");
		ComboBox<String> playerCombobox4 = new ComboBox<String>();
		playerCombobox4.setItems(playerOptions);
		playerCombobox4.getSelectionModel().selectFirst();
		HBox playerBox4 = new HBox(20, playerLabel4, playerCombobox4);
		
		
		VBox playersVBox = new VBox(10, playerBox1, playerBox2, playerBox3, playerBox4);
		playersVBox.setStyle(borderStyle);
		return playersVBox;
	}
	
	/**
	 * This method sets up the game options portion of the tournament mode view.
	 * 
	 */
	private VBox createGameOptionsLayout()
	{
		
		RiskLabel gameLabel = new RiskLabel("Games to be played:");
		Spinner<Integer> gameCountSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> gameCountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
		gameCountSpinner.setValueFactory(gameCountValueFactory);
		HBox gameBox = new HBox(10, gameLabel, gameCountSpinner);
		
		RiskLabel turnLabel = new RiskLabel("Maximum Turns :      ");
		Spinner<Integer> turnCountSpinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> turnCountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50, 10);
		turnCountSpinner.setValueFactory(turnCountValueFactory);
		HBox turnBox = new HBox(20, turnLabel, turnCountSpinner);
		
		
		VBox gameVBox = new VBox(10, gameBox, turnBox);
		gameVBox.setStyle(borderStyle);
		return gameVBox;
	}
	/**
	 * This method sets up the tournament mode view elements.
	 * 
	 */
	private void createLayout()
	{
		mainLayout.setLayoutX(30);
		mainLayout.setLayoutY(200);
		mainLayout.add(createMapOptionsLayout(), 0, 0);
		mainLayout.add(createPlayerOptionsLayout(), 1, 0);
		mainLayout.add(createGameOptionsLayout(), 2, 0);
		
		RiskButton startTournamentButton = new RiskButton("START TOURNAMENT");
		startTournamentButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		startTournamentButton.setLayoutX(980);
		startTournamentButton.setLayoutY(700);
		
		tournamentPane.getChildren().add(startTournamentButton);
		
	}
	
}
	
	
