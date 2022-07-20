package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.stage.Screen;



public class Controller {
	public static GuiBuilder guiBuild;
	static ModDraw draw;
	static double h;
	static double w;
	static GEngine currentGame;
	static String[][]btn = new String[][]{ {"Snake","Tetris","3D Model","Tetris 3D"},{"snake","T2D","mod","T3D"}};
	static CommonEHandler eHandler = new CommonEHandler();  
	static Main view;
	static boolean backBttnVis=false;
	static double[] winLoc;	
	
	public static Main getView() {
		return view;
	}
	
	public static void startPage() {
		if (currentGame != null) {
			currentGame.killTimer();
			currentGame = null;
		}		
		guiBuild.setText("");
		Block.resetStaticTTL();
		Block.set3D(false);
		backBttnVis=false;
		view.reload();
	}
	
	public static void modelView() {
		backBttnVis=true;
		draw=new ModDraw();
		guiBuild.setDrawToMainFrame();
		view.reload();
	}
		
	public static void startSnake() {
		if(currentGame!=null) {currentGame.exit();}
		backBttnVis=true;
		currentGame = new Snake();
		currentGame.setSize(w, h-100);
		guiBuild.setSnakeToMainFrame();
		view.reload();
		currentGame.go();
	}
	
	public static void startTetris(boolean t3D) {
		backBttnVis=true;
		currentGame = new Tetris(t3D,250,10);
		currentGame.setSize(w, h-100);
		guiBuild.setTetrisToMainFrame();
		view.reload();
		currentGame.go();
	}
	
	public static void startText() {
		guiBuild.setContentFrame(true);		
	}
	
	public static void setSize(){
		guiBuild.setSize(w, h);
		//if (tetris!=null){tetris.setSize(w,h-100);}
		view.reload();						
	}
	
	public static void scale(double inc) {
		h=h+inc;
		w=h*1.5;
		setSize();	
	}
	
	public static void main(String[] args){
		h=500;
		w=h*1.5;
		guiBuild = new GuiBuilder(w,h);		
		view = new Main();
		view.run();	
	}
}
	
