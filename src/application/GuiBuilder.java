package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GuiBuilder extends GuiFrame {
	
	private Text contentText = new Text("");
	private CommonEHandler eHandler = Controller.eHandler;
	private String[][] btn=Controller.btn;
	private Tetris tetris; 
	private Snake snake;
	private VBox root;	
	private AnchorPane mainframe =new AnchorPane();
	
	public void setDrawToMainFrame() {
		mainframe= Controller.draw.getContent();
		mainframe.setVisible(true);
		contentText.setVisible(false);
	}
	
	public void setTetrisToMainFrame() {
		this.tetris= (Tetris) Controller.currentGame;
		mainframe= tetris.getContent();
		mainframe.setVisible(true);
		contentText.setVisible(true);
	}
	
	public void setSnakeToMainFrame() {
		this.snake= (Snake) Controller.currentGame;
		mainframe= snake.getContent();
		mainframe.setVisible(true);
		contentText.setVisible(false);
	}
	
	public void setText(String inc) {
		this.contentText.setText(inc);	
	}
	
	public void setContentFrame(boolean text){	
		mainframe.setVisible(!text);
	}
		
	GuiBuilder(double width, double height){
		setContentFrame(true);
		this.width=width;
		this.height=height;
	}
	
	public void setSize(double w ,double h) {
		this.width=w;
		this.height=h;
		root.resize(w, h);
	}

	public Scene starter() {

		this.root = getRoot();		
		AnchorPane wrapper=new AnchorPane();
		try {
			wrapper = FXMLLoader.load(getClass().getResource("templates/anchor.fxml"));	
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		AnchorPane topBar = getMenuBar();	
		AnchorPane content = new AnchorPane();
		AnchorPane.setLeftAnchor(this.contentText, (double) 10);
		AnchorPane.setTopAnchor(this.contentText, (double) 10);
		
		content.getChildren().addAll(this.contentText,mainframe);
	
		AnchorPane.setTopAnchor(content, (double) 50);
		AnchorPane.setLeftAnchor(content, (double) 200);
		AnchorPane.setBottomAnchor(content, (double) 50);
		AnchorPane.setRightAnchor(content, (double) 50);
		
		content.addEventHandler(KeyEvent.KEY_PRESSED, eHandler);			
		content.setFocusTraversable(true);
					
		wrapper.getChildren().addAll(bttnBuilderY(this.btn), content);
		root.getChildren().addAll(wrapper, topBar);
	
		Scene scene = new Scene(root);			
		scene.setRoot(root);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
		
		return scene;
	}
}