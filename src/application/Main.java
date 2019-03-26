package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.GamePhaseViewManager;
import view.MainMenuViewManager;
import view.PlayerDominationViewManager;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MainMenuViewManager mainMenu = new MainMenuViewManager();
			primaryStage = mainMenu.getMainStage();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
