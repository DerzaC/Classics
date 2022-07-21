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

public  class GuiEle {
	private String buttonSrc = "templates/button.fxml";
	private String rootSrc = "templates/root.fxml";
	private String mBarSrc = "templates/menubar.fxml";
	private CommonEHandler eHandler = Controller.getEventHandler();
	private double width;
	private double height;
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
			root.setPrefWidth(Controller.getWindowWidth());
			root.setPrefHeight(Controller.getWindowHeight());
			return root;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Button buttonBuilder(String theSame){
		Button bttn = buttonBuilder(theSame, theSame);
		return bttn;
	}

	public Button buttonBuilder(String id, String name){
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

	public String getmBarSrc() {
		return mBarSrc;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
