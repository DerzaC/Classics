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



public class CommonEHandler implements ChangeListener, ListChangeListener, EventHandler<Event>  {
	@FXML
	public MouseEventHandler MouseEvent=new MouseEventHandler();


	public void handle(KeyEvent event){
		String inputKey = event.getCode().toString();
			switch(inputKey) {
			case"RIGHT":
			case"D":
				Controller.getCurrentGame().move(1,0);
				break;
			case"UP":
			case"W":
				Controller.getCurrentGame().move(0,-1);
				break;
			case "LEFT":
			case "A":
				Controller.getCurrentGame().move(-1,0);
				break;
			case "DOWN":
			case "S":
				Controller.getCurrentGame().move(0,1);
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

			@Override
			public void handle(MouseEvent event) {
				String eventID=getID(event.getSource().toString());
				doAction(eventID,event);
				event.consume();
			}

			private void doAction(String eventID,MouseEvent event) {
				switch (eventID) {
				case "modDraw":
					Gfx.setCommon(event.getSceneX()-200, event.getSceneY()-50);
					Controller.getDraw().refresh();
					break;
				case "drag":
					double xOffset=0;
					double yOffset=0;
					if(event.getEventType().toString().equals("MOUSE_PRESSED")) {
			            xOffset = Controller.getWinLoc()[0] - event.getScreenX();
			            yOffset = Controller.getWinLoc()[1] - event.getScreenY();
					}else {
						Controller.getView().getPrimaryStage().setX(event.getScreenX() + xOffset);
						Controller.getView().getPrimaryStage().setY(event.getScreenY() + yOffset);
						Controller.getWinLoc()[0]=event.getScreenX() + xOffset;
						Controller.getWinLoc()[1]=event.getScreenY() + xOffset;
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
		buttonEventID(event.getSource().toString().substring(startIndex, endIndex));
		event.consume();
	}

	private void buttonEventID(String buttonID){
		switch (buttonID) {
		case "exit":
			Platform.exit();
			break;
		case "back":
			if (Controller.getCurrentGame()!=null) Controller.getCurrentGame().exit();
			if (Controller.getDraw()!=null) Controller.getDraw().exit();
			break;
		case "snake":
			Controller.startSnake();
			break;
		case "T3D":
			Controller.startTetris(true);
			break;
		case "T2D":
			Controller.startTetris(false);
			break;
		case "mod":
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

	@Override
	public void handle(Event event) {
		if (event.getEventType().toString()=="KEY_PRESSED") {handle((KeyEvent) event);}
		else {handleButtonAction((ActionEvent) event);}
	}

	public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		System.out.println("public void changed(ObservableValue observable, Object oldValue, Object newValue)");
	}

	@Override
	public void changed(ObservableValue observable, Object oldValue, Object newValue) {

	}
	@Override
	public void onChanged(Change arg0) {

	}
}
