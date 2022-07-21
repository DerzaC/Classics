package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class Main extends Application implements Runnable {

	private GuiBuilder guiBuild = Controller.getGuiBuild();
	private Stage primaryStage;
	private boolean init=false;

	
	public static void go() {
		launch();
	}

	public void reload()  {
		Stage stage = new Stage();
		stage.setX(Controller.getWinLoc()[0]);
		stage.setY(Controller.getWinLoc()[1]);
		try {
			start(stage);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void ini(Stage primaryStage) {
		primaryStage.centerOnScreen();
		Controller.setWinLoc(new double[] {primaryStage.getX(),primaryStage.getY()});
		init=true;
	}

	@Override
	public void start(Stage primaryStage) {
		Controller.setView(this);
		try {
		     Scene scene = this.guiBuild.starter();
		     primaryStage.setScene(scene);
		     primaryStage.setHeight(Controller.getWindowHeight());
			 primaryStage.setWidth(Controller.getWindowWidth());
			 primaryStage.sizeToScene();
			 primaryStage.setResizable(true);
			 primaryStage.initStyle(StageStyle.TRANSPARENT);
			 this.setPrimaryStage(primaryStage);
			 if(!init) {ini(primaryStage);}
			 primaryStage.show();
			 if(Window.getWindows().size()>1) Window.getWindows().get(0).hide();
		}
		catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
