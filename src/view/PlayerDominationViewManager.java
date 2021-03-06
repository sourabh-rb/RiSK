package view;
import java.util.ArrayList;
import gameEngine.Country;
import gameEngine.Player;
import gameEngine.StartUpPhase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import view.ui_elements.RiskLabel;
/**
 * Displays the player domination view, consisting of PlayerName,Countries owned,
 * Continents owned and percentage countries conquered
 * @author Sourabh Rajeev Badagandi
 *
 */

public class PlayerDominationViewManager
{
    private final static int WIDTH = 760;
    private final static int HEIGHT = 900;
    
    private ScrollPane dominationPane;
    private Scene dominationScene;
    private Stage dominationStage;
    private VBox mainLayout;
    
    
    
    
    String borderStyle = "-fx-border-color: black;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-padding: 15;\n" +
            "-fx-border-width: 3;\n";
    
    /**
     * This is the constructor that calls respective functions
     */
    public PlayerDominationViewManager()
    {
        initStage();
        createDominationView();
        
    }
    
    /**
     * This method initializes JavaFX stage elements.
     * 
     */
    public void initStage()
    {
        dominationPane = new ScrollPane();
        dominationScene = new Scene(dominationPane, WIDTH, HEIGHT);
        dominationStage = new Stage();
        dominationStage.setTitle("Player Domination View");
        dominationStage.setScene(dominationScene);
        dominationStage.setResizable(false);
        mainLayout = new VBox();
    }
    
    /**
     * Creates the view for domination with player details
     */
    private void createDominationView()
    {
        
        mainLayout.getChildren().clear();
        
        Image backgroundImage = new Image("view/resources/mainBackground.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(
                backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
                BackgroundPosition.DEFAULT, null);
        mainLayout.setBackground(new Background(background));
        StartUpPhase startObject = StartUpPhase.getInstance();
        ArrayList<Player> playerList = startObject.getPlayerList();
        
        for(int i = 0; i < playerList.size(); i++)
        {
            StringProperty name = new SimpleStringProperty(this, "name", "");
            name.set(playerList.get(i).getName());
            
            IntegerProperty countryOwned = new SimpleIntegerProperty();
            countryOwned.set(playerList.get(i).getCountries().size());
            
            IntegerProperty continentOwned = new SimpleIntegerProperty();
            //continentOwned.set(playerList.get(i).getContinents().size());
            
            
            
            DoubleProperty totalCountries = new SimpleDoubleProperty();
            totalCountries.set(startObject.getMapCountries().size());
            NumberBinding percentage = countryOwned.divide(totalCountries).multiply(100);
            ObservableList<Country> observableCountries = 
                    FXCollections.observableArrayList(playerList.get(i).getCountries());
            
            
            
            GridPane infoPane = new GridPane();
            
            RiskLabel playerName = new RiskLabel();
            playerName.textProperty().bind(name);
            
            RiskLabel countryHeader = new RiskLabel("Countries");
            RiskLabel countryCountLabel = new RiskLabel();
            countryCountLabel.textProperty().bind(countryOwned.asString());
            
            IntegerProperty armies = new SimpleIntegerProperty();
            
            ComboBox<Country> countryCombobox = new ComboBox<Country>();
            
            countryCombobox.setItems(observableCountries);
            
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
            countryCombobox.setConverter(converter);
            
            countryCombobox.valueProperty().addListener(new ChangeListener<Country>()
    		{

    			@Override
    			public void changed(ObservableValue<? extends Country> observable,
    					Country oldValue, Country newValue)
    			{
    				armies.set(countryCombobox.getSelectionModel().getSelectedItem().getArmies());			
    			}
    		});
            countryCombobox.getSelectionModel().selectFirst();
            VBox countryBox = new VBox(20, countryHeader, countryCountLabel, countryCombobox);
            
            
            RiskLabel continentsHeader = new RiskLabel("Continents");
            RiskLabel continentCountLabel = new RiskLabel();
            continentCountLabel.textProperty().bind(continentOwned.asString());
            VBox continentBox = new VBox(20, continentsHeader, continentCountLabel);
        
            
            RiskLabel armyHeader = new RiskLabel("Army");
            RiskLabel armyCountLabel = new RiskLabel();
            armyCountLabel.textProperty().bind(armies.asString());
            VBox armyBox = new VBox(20, armyHeader, armyCountLabel);
            
            RiskLabel percentageHeader = new RiskLabel("% Conqured");
            RiskLabel percentageLabel = new RiskLabel();
            percentageLabel.textProperty().bind(percentage.asString("%.2f"));
            VBox percentageBox = new VBox(20, percentageHeader, percentageLabel);
            
            
            infoPane.add(playerName, 0, 0);
            infoPane.add(countryBox, 0, 1);
            infoPane.add(continentBox, 1, 1);
            infoPane.add(armyBox, 2, 1);
            infoPane.add(percentageBox, 3, 1);
            
            infoPane.setStyle(borderStyle);
            
            infoPane.setLayoutX(30);
            infoPane.setLayoutY(150);
            
            infoPane.setHgap(50);
            infoPane.setVgap(10);
            
            mainLayout.getChildren().add(infoPane);
        }
        
        dominationPane.setContent(mainLayout);
    }
    /**
     * Place holder for player domination information
     * @return infoPane
     */
    private GridPane createPlayerDominationInfobar()
    {
        GridPane infoPane = new GridPane();
        
        RiskLabel playerName = new RiskLabel();
        
        RiskLabel countryHeader = new RiskLabel("Countries");
        RiskLabel countryCountLabel = new RiskLabel();
        ComboBox<String> countryCombobox = new ComboBox<String>();
        VBox countryBox = new VBox(20, countryHeader, countryCountLabel, countryCombobox);
        
        RiskLabel continentsHeader = new RiskLabel("Continents");
        RiskLabel continentCountLabel = new RiskLabel();
        VBox continentBox = new VBox(20, continentsHeader, continentCountLabel);
        
        RiskLabel armyHeader = new RiskLabel("Army");
        RiskLabel armyCountLabel = new RiskLabel();
        VBox armyBox = new VBox(20, armyHeader, armyCountLabel);
        
        RiskLabel percentageHeader = new RiskLabel("% Conqured");
        RiskLabel percentageLabel = new RiskLabel();
        VBox percentageBox = new VBox(20, percentageHeader, percentageLabel);
        
        
        infoPane.add(playerName, 0, 0);
        infoPane.add(countryBox, 0, 1);
        infoPane.add(continentBox, 1, 1);
        infoPane.add(armyBox, 2, 1);
        infoPane.add(percentageBox, 3, 1);
        
        infoPane.setStyle(borderStyle);
        
        infoPane.setLayoutX(30);
        infoPane.setLayoutY(150);
        
        infoPane.setHgap(50);
        infoPane.setVgap(10);
        
        return infoPane;
            
    }
    
    /**
     * This method is used to update the domination view. 
     */
    public void updateDominationView()
    {
    	createDominationView();
    }
    
    /**
     * This method is used to show the main view. 
     */
    public void showView()
    {
        dominationStage.show();
    }
}