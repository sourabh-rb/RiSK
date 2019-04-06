package view.ui_elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
* <h1>RiskButton</h1>
* The RiskButton class extends the Button class of JavaFX,
* it is used to create risk game styled buttons.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class RiskButton extends Button
{
	private final String FONT_PATH = "src/view/resources/Kenney Pixel.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;"
			+ "-fx-background-image: url('/view/resources/buttonPressed.png');";
	private final String BUTTON_STYLE = "-fx-background-color: transparent; "
			+ "-fx-background-image: url('/view/resources/button.png');";

	public RiskButton(String text)
	{
		initDefaults(text);
		initEventListeners();
	}
	
	/**
	 * The method configures button pressed style.
	 * 
	 */
	private void setButtonPressedStyle()
	{
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	/**
	 * The method configures button released style.
	 * 
	 */
	private void setButtonReleasedStyle()
	{
		setStyle(BUTTON_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

	/**
	 * The method initializes event listeners to handle mouse events.
	 * <ul>Following events are handled:
	 * <li> Mouse Pressed
	 * <li> Mouse Released
	 * <li> Mouse Enter
	 * <li> Mouse Exit
	 * </ul>
	 */
	private void initEventListeners()
	{
		setOnMousePressed(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				setButtonPressedStyle();
				
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				setButtonReleasedStyle();
				
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				setEffect(new DropShadow());
				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				setEffect(null);
				
			}
		});

	}

	/**
	 * The method initializes default RiskButton styled button.
	 * 
	 * @param text : which appears on the button.
	 */
	private void initDefaults(String text)
	{
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_STYLE);
		
	}

	/**
	 * The method initializes default font RiskButton styled button.
	 * 
	 */
	private void setButtonFont()
	{
		try
		{
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
		} catch (FileNotFoundException e)
		{
			setFont(Font.font("Verdana", 23));
		}


	}
}
