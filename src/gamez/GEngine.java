package gamez;

import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GEngine {
	private double height;
	private double width;
	protected double box;
	private Color mainFrame =	new Color(0.4, 0.4, 0.4, 1);
	private Color b1 =			new Color(0.8, 0.8, 0.8, 1);
	private Color b2 =			new Color(0.9, 0.9, 0.9, 1);
	protected Value[][] field;
	protected AnchorPane contentFrame = new AnchorPane();
	private double xpos= 	250;
	private double ypos=	10;
	private boolean init=false;
	private boolean dev = false;
	
	public double getBoxSize() {return box;}
	public double getXPos() {return xpos;}
	public double getyPos() {return ypos;}
	
	public void setDeveloperMode(boolean settings) {
		this.dev=settings;
	}
	
	public GEngine(){
		setPixel(400);
	}
	public GEngine(double height){
		setPixel(height);
	}
	
	public void setSize(double w ,double h) {
		this.width=w;
		this.height=h;
		setPixel(h);
	}
		
	private void setPixel(double height) {
		this.height = height;
		this.width = (height/2);
		this.box = width/10;
		board();
	}
			
	public AnchorPane getContent(){	
		contentFrame.getChildren().addAll(backGround());	
		coords();
		return contentFrame;
	}
	
	public Rectangle backGround() {
	Rectangle mFrame = new Rectangle();
	mFrame.setX(xpos-5);
	mFrame.setY(ypos-5);
	mFrame.setWidth(width+10);
	mFrame.setHeight(height+10);
	mFrame.setArcWidth(10);
	mFrame.setArcHeight(10);
	mFrame.setFill(mainFrame);
	mFrame.setEffect(new DropShadow(30, 10, 10, new Color(0.0, 0.0, 0.0, 0.5)));
	return mFrame;
	}
	
	public void board() {
		field = new Value[(int) (width/box)][(int)(height/box)+1];
		//2do: var 4 Snake
	}
	
		class Value{
			public Block b;
			private Color c;
			private boolean devMode = dev;
			private double xpos= 	250;
			private double ypos=	10;
			private boolean filled=false;			
			Rectangle mFrame;
			
				public void unFill() {
					if(devMode) {mFrame.setFill(c);};
					filled = false;
				}		
				public boolean isFilled() {
					return filled;
				}
				public void fill(Block binc) {
					this.b=binc;
					if(devMode) {mFrame.setFill(new Color(1, 0, 0, 1));};
					filled = true;
				}
				public Value(int j, int i,Color c,double box) {
					this.c=c;
					mFrame = new Rectangle();
					mFrame.setWidth(box);
					mFrame.setHeight(box);
					mFrame.setFill(c);
					mFrame.setX(xpos+(i*box));
					mFrame.setY(ypos+j*box);			
				}
	}
	
	public Color ctest(int j, int i) {		
		if ((j+i)%2==0) return b1;
		return b2;		
	}
	
	public void coords() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {					
				if(!init) {
					if(j < field[i].length-1) {
					field[i][j] = new Value(j, i, ctest(i,j), box);
					contentFrame.getChildren().add(field[i][j].mFrame);
					}else {
						field[i][j] = new Value(j, i, ctest(i,j), box);
						field[i][j].filled=true;
					}
				}								
			}		
		} 
		init=true;
	}
	public void pulse(){}
	
		class timerObject implements Runnable{
			int intervall = 1000;
			boolean run = true;
				public void setTimer(int intervall){
					this.intervall=intervall;
				}
				public void kill() {
					this.run = false;
				}
				public void run() {
					while(run) {
						Platform.runLater(new platformRun());				
						try {
							Thread.sleep(intervall);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
		}
	
	timerObject timer;
	
	public void killTimer() {
		timer.kill();
	}
	
	public void setTimer(int intervall) {
		timer.setTimer(intervall);
	}

	public void startTimer(int intervall) {
		timer = new timerObject();
		timer.setTimer(intervall);
		Thread t1 = new Thread(timer); 
		t1.setDaemon(true);
		t1.start();	
	}
	
		class platformRun implements Runnable{
			public void run() {
				pulse();
			}
		}
}
