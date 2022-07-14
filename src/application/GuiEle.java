package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public  class GuiEle {
	

	protected StageStyle init = StageStyle.TRANSPARENT;
	protected String importCss = "application.css";
	protected String buttonSrc = "templates/button.fxml";
	protected String rootSrc = "templates/root.fxml";
	protected String mBarSrc = "templates/menubar.fxml";
	protected CommonEHandler eHandler = Controller.eHandler;
	protected double width;//=750;
	protected double height;//= 500;
	VBox root;

	public double getHeight() {
		return height;
	}
	
	public Effect setReflection() {
		Reflection reflect = new Reflection();
		return reflect;
	}
	public Effect setShadow() {
		DropShadow shadow = new DropShadow(30, 10, 10, new Color(0.0, 0.0, 0.0, 0.5));
		return shadow;
	}

	protected VBox getRoot(){
		VBox root;
		try {
			root = FXMLLoader.load(getClass().getResource(rootSrc));
			root.setAlignment(Pos.TOP_CENTER);
			root.setPrefWidth(Controller.w);
			root.setPrefHeight(Controller.h);
			return root;
		} catch (IOException e) {		
			e.printStackTrace();
		}		
		return null;		
	}


	protected Button buttonBuilder(String theSame){
		Button bttn = buttonBuilder(theSame, theSame);
		return bttn;
	}

	protected Button buttonBuilder(String id, String name){
		FXMLLoader loader = new FXMLLoader(getClass().getResource(buttonSrc));
		loader.setController(eHandler);
		Button bttn = new Button(name);
		try {
			bttn = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bttn.setId(id);
		bttn.setFocusTraversable(false);
		bttn.setText(name);
		return bttn;
	}
}
