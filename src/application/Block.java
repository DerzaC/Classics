package application;

import application.GEngine.Value;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Block {
	private static int count=0;
	private static int  commonTTL=0;
	public int xloc; //x coordinate in field-Array
	public int yloc; //y coordinate in field-Array
	public int id;
	public double wh;
	public int pulsecount =0; 
	public int xPDirection=0;  
	public int yPDirection=0;
	public boolean isActive;
	public boolean isLinked;
	public boolean isAlive;
	//public int linkedID;
	public Group block=new Group();
	public int maxTTL;
	public Value field[][];
	private double xPos=0;//pixelvalue in parent Anchorpane
	private double yPos=0;//pixelvalue in parent Anchorpane
	private Color customColor = new Color(1, 1, 1, 0.4);
	public Gfx block3D;						//extent
	private static boolean is3D=false;
	private boolean isImmortal=false;
	
	public void initField() {    //extend
		//if (Controller.tetris!=null) {this.field= Controller.tetris.field;}
		//if (Controller.snake!=null) {this.field= Controller.snake.field;}
		this.field = Controller.currentGame.field;
	}
	
//	private void getXYPos() {  //extend
//		if (Controller.tetris!=null) {
//			this.xPos= Controller.tetris.getXPos();
//			this.yPos= Controller.tetris.getYPos();
//		}
//		if (Controller.snake!=null) {
//			this.xPos= Controller.snake.getXPos();
//			this.yPos= Controller.snake.getYPos();
//		}
//	}
	
	private void getXYPos() {  //extend
		this.xPos = Controller.currentGame.getXPos();
		this.yPos = Controller.currentGame.getYPos();
	}
	

//	public void finalize() {
//		System.out.println("Object "+id +" has been collected");
//	}
	
	public Block(int xloc, int yloc,double width,int xPDirection,int yPDirection,int maxTTL){
		initField();
		getXYPos();
		isActive=isLinked=isAlive=true;		
		this.xloc=xloc;
		this.yloc=yloc;
		this.wh=width;	
		this.xPDirection=xPDirection;
		this.yPDirection=yPDirection;
		this.maxTTL=maxTTL;		
		if(is3D) {
			block3D = new Gfx(xPos,yPos,wh, xloc, yloc, 0.05, 0.8);
		}
		block();
		setId();	
	}
	
	public void block() {	
		if(!is3D) {
			Rectangle bl = new Rectangle();
			Circle element = new Circle();
		bl.setX(xPos+wh*xloc);
		bl.setY(yPos+wh*yloc);
		bl.setWidth(wh);
		bl.setHeight(wh);
		bl.setArcWidth(10);
		bl.setArcHeight(10);		
		element.setRadius(wh/2.5);
		element.setCenterX(xPos+(wh*xloc+wh/2));
		element.setCenterY(yPos+(wh*yloc+wh/2));	
		element.setFill(customColor);
		bl.setVisible(isAlive);
		element.setVisible(isAlive);
		this.block.getChildren().clear();
		this.block.getChildren().addAll(bl,element);
		}else {
		block3d();
		}
	}
	
	public static void feast(int staticTTLinc) {
		commonTTL += staticTTLinc;
	}
	
	public static void resetStaticTTL() {
		commonTTL=0;
	}
	
	public void block3d() {	
		if (this.block3D != null) {
			this.block3D.setAbsoluteLoc(xPos+wh*xloc, yPos+wh*yloc);	
			this.block3D.setVisible(isAlive);
			this.block3D.reLoad();
		}
	}
	
	public static void set3D(boolean is3Dinc) {
		is3D=is3Dinc;
	}
	
	public void setColor(Color customColor,int colorSet) {
		if(!is3D) {
		this.customColor=customColor;
		((Circle) block.getChildren().get(1)).setFill(customColor);
		}else {
			block3D.colorSet(colorSet);
			block3D.reLoad();
		}
	}
	
	public void selfDestruct() {
		field[xloc][yloc].unFill();
		isActive=isLinked=isAlive=false;
		block();
		this.block = null;
		//this.element = null;	
		field[xloc][yloc].b = null;
	}
	
	public void reActivate() {
		isActive=true;
		pulsecount=0;
		while(isActive) {
			pulse();
		}
		deActivate();
	}
	
	public void deActivate(){
			isActive=false;
			isLinked=false;
			pulsecount=0;
			maxTTL=0;
			block();
			field[xloc][yloc].fill(this);
	}
	
	@Override
	public String toString() {	
		return getId()+" x: "+xloc+" y: "+yloc;
	}	
	// setter
	private void setId() {
		this.id=count++;
	//	this.linkedID=this.id/4;
	}
	public void setImmortal(boolean isImmortal) { //extend
		this.isImmortal=isImmortal;
	}
	// getter
	public int getXloc() {
		return xloc;
	}
	public int getYloc() {
		return yloc;
	}
	public String getId() {
		return ""+id;
	}	
	public int getTTL() {
		return maxTTL;
	}
	public int getPulseCount() {
		return pulsecount;
	}
		
	public void killCounter() {  //Extend/override
		if (pulsecount >=maxTTL+commonTTL) {isActive = false;
			if (Controller.currentGame.getCurrentGame()=="snake") {
				selfDestruct();
			}
		}
	}

	public void pulse() {   //extend / override
		killCounter();		
		if(isActive) {
			xloc += xPDirection;
			yloc += yPDirection;
			if(!isImmortal) {	
				pulsecount++;
			}
			block();
		}	
	}			
	
		
	public boolean isActive() {
		return isActive;
	}
	
	public void changePosition(int xChange, int yChange) {
			xloc += xChange;
			yloc += yChange;
	}
}
