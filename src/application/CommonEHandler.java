package application;




import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static application.Controller.*;



public class CommonEHandler implements ChangeListener, ListChangeListener, EventHandler<Event>  {
	@FXML
	public MouseEventHandler MouseEvent=new MouseEventHandler();
	public Main view;
	

	
	
	public void handle(KeyEvent event){		
//		boolean istetris = tetris!=null;
//		boolean issnake = Controller.snake!=null;
		String inputKey = event.getCode().toString();
			switch(inputKey) {
			case"RIGHT":
			case"D":
//				if(istetris) {tetris.moveX(1);}
//				if(issnake) {Controller.snake.move(1, 0);}
				Controller.currentGame.move(1,0);
				break;
			case"UP":
			case"W":
//				if(istetris) {Controller.tetris.moveY(-1);}
//				if(issnake) {Controller.snake.move(0, -1);}
				Controller.currentGame.move(0,-1);
				break;
			case "LEFT":
			case "A":
//				if(istetris) {Controller.tetris.moveX(-1);}
//				if(issnake) {Controller.snake.move(-1, 0);}
				Controller.currentGame.move(-1,0);
				break;
			case "DOWN":
			case "S":
//				if(istetris) {Controller.tetris.moveY(1);}
//				if(issnake) {Controller.snake.move(0, 1);}
				Controller.currentGame.move(0,1);
				break;
			case "PLUS":
			case "ADD":
				Controller.scale(50);
				break;
			case "MINUS":
			case "SUBSTRACT":
				Controller.scale(-50);
				break;
			default:
				System.out.println(event.getCode());
				
			}
	}
	
		class MouseEventHandler implements EventHandler<MouseEvent>{			
			public String getID (String inc) {
				int startIndex=inc.indexOf("id=")+3;
				int endIndex= inc.indexOf(",")!=-1?inc.indexOf(","):startIndex;
				return inc.substring(startIndex, endIndex);
			}
			
			public void handle(MouseEvent event) {				
				String eventID=getID(event.getSource().toString());				
				doAction(eventID,event);
				event.consume();
			}
			
			private void doAction(String eventID,MouseEvent event) {
				switch (eventID) {
				case "modDraw":
					Gfx.setCommon(event.getSceneX()-200, event.getSceneY()-50);
					Controller.draw.refresh();
					break;
				case "drag":
					double xOffset=0;
					double yOffset=0;
					if (Controller.view.primaryStage==null) {
						Controller.view.reload();	
					}					
					if(event.getEventType().toString().equals("MOUSE_PRESSED")) {						
			            xOffset = winLoc[0] - event.getScreenX();
			            yOffset = winLoc[1] - event.getScreenY();				            
					}else {  
						Controller.view.primaryStage.setX(event.getScreenX() + xOffset);
						Controller.view.primaryStage.setY(event.getScreenY() + yOffset);
						winLoc[0]=event.getScreenX() + xOffset;
						winLoc[1]=event.getScreenY() + xOffset;
					}
						break;
					default:
						break;
				}
			}
	}
	
	public void handleButtonAction(ActionEvent event){
		int startIndex=event.getSource().toString().indexOf("id=")+3;
		int endIndex=event.getSource().toString().indexOf(",");		
		initTrigger(event.getSource().toString().substring(startIndex, endIndex));
		event.consume();	
	}
	
	private void initTrigger(String buttonID){

		switch (buttonID) {
		case "exit":
			Platform.exit();
			//System.exit(0);
			break;
		case "back":
			if (Controller.currentGame!=null) Controller.currentGame.exit();
			if (Controller.draw!=null) Controller.draw.exit();

			break;
		case "snake":
			startPage();
			Controller.startSnake();
			break;
		case "T3D":
			startPage();
			Controller.startTetris(true);	
			break;
		case "T2D":
			startPage();
			Controller.startTetris(false);	
			break;
		case "mod":
			startPage();
			Controller.modelView();
			break;
		case"-":
			
			break;
		default:
			break;
		}	
	}




	public void changed(ObservableValue<?extends String> observable, String oldValue, String newValue) {
		System.out.println(oldValue+" changed to "+newValue);			
	}

	public void handle(Event event) {	
		if (event.getEventType().toString()=="KEY_PRESSED") {handle((KeyEvent) event);}
		else {handleButtonAction((ActionEvent) event);} 
	}

	public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		System.out.println("public void changed(ObservableValue observable, Object oldValue, Object newValue)");		
	}

	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {
		//System.out.println("turbochange");
	}
	@Override
	public void onChanged(Change arg0) {
		//System.out.println("turbochange 1");
		
	}






	






	


}
