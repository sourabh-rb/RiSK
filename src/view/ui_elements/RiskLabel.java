package view.ui_elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class RiskLabel extends Label
{
	private final String FONT_PATH = "src/view/resources/Kenney Pixel Square.ttf";
	
	public RiskLabel(String text)
	{
		prefHeight(60);
		prefWidth(400);
		setText(text);
		setWrapText(true);
		setAlignment(Pos.CENTER);
		setLabelFont();
	}
	
	public RiskLabel()
	{
		prefHeight(60);
		prefWidth(400);
		setWrapText(true);
		setAlignment(Pos.CENTER);
		setLabelFont();
	}
	private void setLabelFont()
	{
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}

}
