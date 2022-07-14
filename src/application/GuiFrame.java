package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public abstract class GuiFrame extends GuiEle{
	public Button back;
	
	
//	public void swButton(boolean isVisible) {
//		boolean bla = this.back.isVisible();
//		this.back.setVisible(isVisible);
//		System.out.println(bla);
//	}
	
	public String[][] buttonString(String[][]toModify){
		String[][] add = new String[][]{{"go back","Beenden"},{"back", "exit"}};
		String[][] btnStr = new String[2][];
		for(int i=0;i<2;i++) {
			btnStr[i]=new String[toModify[i].length+add[i].length];
			for (int j=0;j<btnStr[i].length;j++) {
				if (j<toModify[i].length) {btnStr[i][j]=toModify[i][j];
				}else {btnStr[i][j]=add[i][j-toModify[i].length];};				
			}			
		}
		return btnStr;
	}
	
	
	
	public AnchorPane babyBuilderY(String[][] inc){
		String[][]dnaBringer=buttonString(inc);
		AnchorPane mother = new AnchorPane();
		double y = 0;
		for (int i = 0; i < dnaBringer[0].length; i++) {
			Button child = buttonBuilder(dnaBringer[1][i], dnaBringer[0][i]);
			child.setEffect(setShadow());
			if(i < dnaBringer[0].length-2) {AnchorPane.setTopAnchor(child, y);}
			else {AnchorPane.setTopAnchor(child,getHeight()-(100+(dnaBringer[0].length-i)*50));};
			mother.getChildren().add(child);
			y += 50;
		}
		
		int backbutton =mother.getChildren().size()-2;
		mother.getChildren().get(backbutton).setVisible(Controller.backBttnVis);
		
		
		AnchorPane.setTopAnchor(mother, (double) 60);
		AnchorPane.setLeftAnchor(mother, (double) 70);	
		
		return mother;
	}
	
	public AnchorPane getMenuBar(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource(mBarSrc));
		loader.setController(eHandler);
		AnchorPane menuBar = new AnchorPane();
		try {
			menuBar = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Button drag = new Button();
		Button kill = new Button("X");
		drag.setId("drag");
		drag.addEventHandler(MouseEvent.MOUSE_PRESSED, eHandler.MouseEvent);
		drag.addEventHandler(MouseEvent.MOUSE_DRAGGED, eHandler.MouseEvent);
		kill.setId("exit");
		kill.setFocusTraversable(false);
		drag.setFocusTraversable(false);
		kill.addEventHandler(ActionEvent.ACTION, eHandler);		
		AnchorPane.setRightAnchor(kill, (double) 0);
		AnchorPane.setLeftAnchor(drag,(double) 130) ;
		AnchorPane.setRightAnchor(drag, (double) 24);
		menuBar.setEffect(setReflection());
		menuBar.getChildren().addAll(drag,kill);	
		return menuBar;
	}
	
		public void getContent() {
			
		}

}
