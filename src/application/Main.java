package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class Main extends Application implements Runnable {

	private GuiBuilder guiBuild = Controller.guiBuild;
	public Stage primaryStage;
	private boolean init=false;
	
	public void run() {	
		launch();
	}
		
	public void reload()  {
		Stage stage = new Stage();
		stage.setX(Controller.winLoc[0]);
		stage.setY(Controller.winLoc[1]);
		try {		
			start(stage);
		}catch(Exception e) {
			e.printStackTrace();
		}			
	}	
	

	public void ini(Stage primaryStage) {
		primaryStage.centerOnScreen();
		Controller.winLoc = new double[] {primaryStage.getX(),primaryStage.getY()};
		init=true;
	}

	public void start(Stage primaryStage) {		
		try {
		     Scene scene = this.guiBuild.starter();			     
		     primaryStage.setScene(scene);
		     primaryStage.setHeight(Controller.h);
			 primaryStage.setWidth(Controller.w);
			 primaryStage.sizeToScene();
			 primaryStage.setResizable(true);
			 primaryStage.initStyle(StageStyle.TRANSPARENT);
			 this.primaryStage=primaryStage;	
			 if(!init) {ini(primaryStage);}
			 primaryStage.show();
			 if(Window.getWindows().size()>1) Window.getWindows().get(0).hide();	 
		}
		catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}			
	}	
}
