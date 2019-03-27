package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.GameScreenViewManager;
import view.MainMenuViewManager;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MainMenuViewManager mainMenu = new MainMenuViewManager();
			primaryStage = mainMenu.getMainStage();
			//GameScreenViewManager gsvm=new GameScreenViewManager();
			//primaryStage=gsvm.getGameScreenStage();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
