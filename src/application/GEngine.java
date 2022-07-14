package application;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class GEngine {
	private double height;
	private double width;
	protected double box;
	private Color mainFrame =	new Color(0.4, 0.4, 0.4, 1);
	private Color b1 =			new Color(0.8, 0.8, 0.8, 1);
	private Color b2 =			new Color(0.9, 0.9, 0.9, 1);
	protected Value[][] field;
	protected AnchorPane contentFrame = new AnchorPane();
	private double xPos= 	250;
	private double yPos=	10;
	private boolean init=	false;
	private boolean dev = true;
	private boolean is3D = true;
	private boolean isTetris=Controller.tetris!=null;
	private boolean isSnake=Controller.snake!=null;
	//getter
	public boolean is3D() {
		return is3D;
	}
	public double getBoxSize() {
		return box;
	}
	public double getXPos() {
		return xPos;
	}
	public double getYPos() {
		return yPos;
	}
	public boolean getDevMode(){
		return dev;
	}
	//setter
	public void setDeveloperMode(boolean settings) {
		this.dev=settings;
	}
	public void set3D(boolean is3D) {
		this.is3D=is3D;
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
	
	private void setPixel(double height){
		this.isTetris=Controller.tetris!=null;
		this.isSnake=Controller.snake!=null;
		if (isTetris) {setPixelTetris(height);}
		if (isSnake) {setPixelSnake();}
	}
		
	private void setPixelSnake() {
		xPos=yPos=10;
		System.out.println("h"+height+"w:"+width);
		this.width=height*1.25;
		this.box=height/24;
		board();
	}
	private void setPixelTetris(double height) {
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
	
	public Group backGround() {
		
	Group mFrame = new Group();
	double customWidth = (field.length)*box;
	double customHeight=(field[0].length-1)*box;
	if (!is3D) {
		Rectangle bgFrame = new Rectangle();
		bgFrame.setX(xPos-5);
		bgFrame.setY(yPos-5);
		bgFrame.setWidth(customWidth+10);
		bgFrame.setHeight(customHeight+10);
		bgFrame.setArcWidth(10);
		bgFrame.setArcHeight(10);
		bgFrame.setFill(mainFrame);
		bgFrame.setEffect(new DropShadow(30, 10, 10, new Color(0.0, 0.0, 0.0, 0.5)));
		mFrame.getChildren().add(bgFrame);
	}else {
		Gfx bg3DFrame= new Gfx(xPos,yPos,customWidth, customHeight, 0,0, 0.15,0.8 );
		Gfx.setCommon(xPos+0.5*width, yPos+0.5*height);
		bg3DFrame.setCol(0.5, 0.5, 0.7, 0);
		bg3DFrame.setCustomStroke(new Color(0.5,0.5,0.7,1));
		bg3DFrame.reLoad();
		mFrame.getChildren().add(bg3DFrame.g);
	}
	return mFrame;
	}
	
	public void board(){ 
		if (isTetris) {field = new Value[(int) (width/box)][(int)(height/box)+1];}
		if (isSnake) {field = new Value [(int) (width/box)][(int)(height/box)];}
	}
	
		class Value{
			public Block b;
			private Color c;		
			private double xpos;
			private double ypos;
			private boolean filled=false;			
			Rectangle mFrame;
			
				public void unFill() {
					if(dev) {mFrame.setFill(c);};
					filled = false;
				}		
				public boolean isFilled() {
					return filled;
				}
				public void fill(Block binc) {
					this.b=binc;
					if(dev) {mFrame.setFill(new Color(1, 0, 0, 1));};
					filled = true;
				}
				public Value(int j, int i,Color c,double box) {
					this.c=c;
					this.xpos=GEngine.this.getXPos();
					this.ypos=GEngine.this.getYPos();
					mFrame = new Rectangle();
					mFrame.setWidth(box);
					mFrame.setHeight(box);
					mFrame.setFill(c);
					mFrame.setX(xpos+(i*box));
					mFrame.setY(ypos+j*box);			
				}
	}
	
	public Color ctest(int j, int i) {
		if(!is3D) {
			if ((j+i)%2==0) return b1;
			return b2;	
		}
		return new Color(1,1,1,0);
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
	
	public abstract  void pulse();
	
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
