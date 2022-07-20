package application;

import application.GEngine.Value;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Block {
	private static int count=0;
	private static int  commonTTL=0;
	private int xloc; //x coordinate in field-Array
	private int yloc; //y coordinate in field-Array
	private int id;
	private double wh;
	private int pulsecount =0; 
	private int xPDirection=0;  
	private int yPDirection=0;
	private boolean isActive;
	private boolean isAlive;
	private Group block=new Group();
	private int maxTTL;
	private Value field[][];
	private double xPos=0;//pixelvalue in parent Anchorpane
	private double yPos=0;//pixelvalue in parent Anchorpane
	private Color customColor = new Color(1, 1, 1, 0.4);
	private Gfx block3D;						//extent
	private static boolean is3D=false;
	private boolean isImmortal=false;
	
	public void initField() {    
		this.field = Controller.currentGame.getField();
	}
	
	private void getXYPos() {  
		this.xPos = Controller.currentGame.getXPos();
		this.yPos = Controller.currentGame.getYPos();
	}
		
	public Block(int xloc, int yloc,double width,int xPDirection,int yPDirection,int maxTTL){
		initField();
		getXYPos();
		isActive=isAlive=true;		
		this.xloc=xloc;
		this.yloc=yloc;
		this.wh=width;	
		this.xPDirection=xPDirection;
		this.yPDirection=yPDirection;
		this.setMaxTTL(maxTTL);		
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
		this.getBlock().getChildren().clear();
		this.getBlock().getChildren().addAll(bl,element);
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
		if (this.getBlock3D() != null) {
			this.getBlock3D().setAbsoluteLoc(xPos+wh*xloc, yPos+wh*yloc);	
			this.getBlock3D().setVisible(isAlive);
			this.getBlock3D().reLoad();
		}
	}
	
	public static void set3D(boolean is3Dinc) {
		is3D=is3Dinc;
	}
	
	public void setColor(Color customColor,int colorSet) {
		if(!is3D) {
			this.customColor=customColor;
			((Circle) getBlock().getChildren().get(1)).setFill(customColor);
		}else{
			getBlock3D().colorSet(colorSet);
			getBlock3D().reLoad();
		}
	}
	
	public void selfDestruct() {
		field[xloc][yloc].unFill();
		isActive=isAlive=false;
		block();
		this.block = null;	
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
		pulsecount=0;
		setMaxTTL(0);
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
	}
	public void setImmortal(boolean isImmortal) { //extend
		this.isImmortal=isImmortal;
	}
	public void setMaxTTL(int maxTTL) {
		this.maxTTL = maxTTL;
	}
	
	// getter
	public Group getBlock() {
		return block;
	}
	public int getMaxTTL() {
		return maxTTL;
	}
	public Gfx getBlock3D() {
		return block3D;
	}
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
		return getMaxTTL();
	}
	public int getPulseCount() {
		return pulsecount;
	}
		
	public void killCounter() {  //Extend/override
		if (pulsecount >=getMaxTTL()+commonTTL) {isActive = false;
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
