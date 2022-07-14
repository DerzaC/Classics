package gamez;

import gamez.GEngine.Value;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Block {
//	public static boolean left = true;
//	public static boolean right = true;
//	
//	private int parentIndex;
//	private static int count=0;
//	public static boolean[][] sync= new boolean[][] {{true,true,true,true},{true,true,true,true}};
//	public int xloc;
//	public int yloc;
//	public int id;
//	public double wh;
//	public int pulsecount =0;
//	public int xPDirection=0;
//	public int yPDirection=0;
//	public boolean isActive;
//	public boolean isLinked;
//	public boolean isAlive;
//	public int linkedID;
//	public Rectangle block = new Rectangle();
//	public Circle element = new Circle();	
//	public int maxTTL;
//	public Value field[][] = Controller.tetris.field;
//	private double xPos=0;
//	private double yPos=0;
//	private Color customColor = new Color(1, 1, 1, 0.4);
//	
//	
//	
//	public Block(int xloc, int yloc,double width,int xPDirection,int yPDirection,int maxTTL,double xPos, double yPos){
//		this.xPos=xPos;
//		this.yPos=yPos;
//		isActive=isLinked=isAlive=true;		
//		this.xloc=xloc;
//		this.yloc=yloc;
//		this.wh=width;	
//		this.xPDirection=xPDirection;
//		this.yPDirection=yPDirection;
//		this.maxTTL=maxTTL;		
//		setId();
//		block();
//	}
//	
//	public void block() {	
//		block.setX(xPos+wh*xloc);
//		block.setY(yPos+wh*yloc);
//		block.setWidth(wh);
//		block.setHeight(wh);
//		block.setFill(new Color(0.4, 0.4, 0.4, 1));
//		block.setArcWidth(10);
//		block.setArcHeight(10);
//		element.setRadius(wh/2.5);
//		element.setCenterX(xPos+(wh*xloc+wh/2));
//		element.setCenterY(yPos+(wh*yloc+wh/2));	
//		element.setFill(customColor);
//		this.block.setVisible(isAlive);
//		this.element.setVisible(isAlive);
//	}
//	
//	public void setColor(Color customColor) {
//		this.customColor=customColor;
//		element.setFill(customColor);
//	}
//	
//	public void selfDestruct() {
//		field[xloc][yloc].unFill();
//		isActive=isLinked=isAlive=false;
//		block();
//		this.block = null;
//		this.element = null;		
//	}
//	
//	public void reActivate() {
//		isActive=true;
//		pulsecount=0;
//		while(isActive) {
//			pulse();
//		}
//		field[xloc][yloc].fill(this);
//	}
//	
//	public void observe() {		
//		if((xloc-1>=0)&&(xloc+1<=field.length-1)) {
//			if(field[xloc-1][yloc].isFilled()) {
//				left=false;}
//			if(field[xloc+1][yloc].isFilled()) {
//				right=false;}
//		}
//	}
//	
//	public void setIndex(int parentIndex) {
//		this.parentIndex = parentIndex;
//	}
//	public int getIndex() {
//		return parentIndex;
//	}	
//	public String toString() {	
//		return getId();
//	}
//	public String getId() {
//		return ""+id;	
//	}	
//	private void setId() {
//		this.id=count++;
//		this.linkedID=this.id/4;
//	}
//		
//	public void prep() {	
//		int y=0;
//		observe();
//		if((!(field[xloc+xPDirection][yloc+yPDirection+y].b == null))&&(field[xloc+xPDirection][yloc+yPDirection+y].b.isActive)) {
//			y++;
//			System.out.println("ff");
//		};
//		if(field[xloc+xPDirection][yloc+yPDirection+y].isFilled()) {
//			isActive=false;
//				if (isLinked) {
//					sync[1][id - linkedID*4]=false;	
//					sync(1);
//				}			
//		}	
//	}
//		
//	public void killCounter() {
//		if (pulsecount >=maxTTL) isActive = false;
//	}
//	public String printL(){
//		return "x:"+(xloc+1)+"/y:"+(yloc+1);
//	}
//	
//	public void pulse() {
//		observe();
//		prep();
//		if(isActive) {
//			xloc += xPDirection;
//			yloc += yPDirection;
//			pulsecount++;
//			killCounter();		
//		}	
//		block();		
//	}
//	
//	public boolean sync(int j) {
//		boolean val = true;
//		for (int i=0;i<4;i++) {
//			if (!sync[j][i]) {
//				sync[j][0]=sync[j][1]=sync[j][2]=sync[j][3] = false;
//				return false;}
//		}	
//		isActive=sync[j][0];
//		return val;
//	}
//	
//	public void reset(int j) {
//		for (int i=0;i<4;i++) {
//			sync[j][i]=true;
//		}				
//	}
//	
//	public boolean tryMove(int x, int y, int bound) {
//			observe();
//			sync[0][id - linkedID*4]=(((xloc+x)>=0)&&((xloc+x)<=bound)&&(!field[xloc+x][y].isFilled()));		
//			return sync[0][id - linkedID*4]; 
//	}
//	
//	
//	public void move(int x, int y, int bound) {	
//		if(!left && (x<0)) {x=0;}
//		if(!right && x>0) {x=0;}
//		if(isLinked) {sync[0][id - linkedID*4] = sync(0);}		
//		if(sync[0][id - linkedID*4]) {		
//			xloc += x;
//			yloc += y;
//		}		
//		block();
//	}
//	
//	public boolean isActive() {
//		return isActive;
//	}
//	
//	public void rotate(int xChange, int yChange) {
//		xloc += xChange;
//		yloc += yChange;		
//	}
//	
	
	
}
