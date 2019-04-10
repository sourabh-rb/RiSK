package view.ui_elements;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

/**
* <h1>RiskSubScene</h1>
* The RiskButton class extends the SubScene class of JavaFX,
* it is used to create risk game menu panels.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class RiskSubScene extends SubScene
{
	//private final String FONT_PATH = "/src/view/resources/Kenny Pixel.ttf";
	private final String BACKGROUND_PATH = "/view/resources/panelSubScene.png";
	
	private Boolean isHidden;
	/**
	 * This constructor sets up a pane and loads the background image.
	 * 
	 */
	public RiskSubScene()
	{
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(
				new Image(BACKGROUND_PATH, 600, 400, false, true)
				, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT 
				, BackgroundPosition.DEFAULT, null
				);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		setLayoutX(1024);
		setLayoutY(180);
		isHidden = true;
	}
	
	/**
	 * This method is used to show/hide subscene.
	 * 
	 */
	public void moveSubScene()
	{
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.seconds(0.3));
		translate.setNode(this);
		
		if(isHidden)
		{
			translate.setToX(-676);
			isHidden = false;
		}
		else
		{
			translate.setToX(0);
			isHidden = true;
		}
		
		translate.play();
	}
	
	/**
	 * This method is used to get the current subscene.
	 * 
	 * @return AnchorPane the current sub menu panel.
	 */
	public AnchorPane getPane()
	{
		return (AnchorPane) getRoot();
	}

}
