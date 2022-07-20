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
	private double box;
	private Color mainFrame =	new Color(0.4, 0.4, 0.4, 1);
	private Color b1 =			new Color(0.8, 0.8, 0.8, 1);
	private Color b2 =			new Color(0.9, 0.9, 0.9, 1);
	private Value[][] field;
	private AnchorPane contentFrame = new AnchorPane();
	private double xPos= 	250;
	private double yPos=	10;
	private boolean init=	false;
	private boolean dev = 	true;
	private boolean is3D = 	true;
	
	//start Conditions
	public abstract void go();
	
	//"left","right",A,D as x +-1; "up","down",W,S, as y +-1;
	public abstract void move(int x, int y);
	
	//reset statics, clear contentFrame, kill Timer, load Startpage;
	public abstract void exit();
	
	// return name of the Game(lower Case)
	public abstract String getCurrentGame();
	
	//setXPos(); setYPos(); setWidth(in relation to height); set width of a single Block object;
	// init: field = new Value[width][height]
	public abstract void setPixel(double height);
	
	//Actions to be done periodically 
	public abstract void pulse();
	
	//getter -----------------------------
	public boolean is3D() {
		return is3D;
	}
	public double getBoxSize() {
		return getBox();
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
	public double getWidth() {
		return width;
	}
	//setter ---------------------------
	public void setDeveloperMode(boolean settings) {
		this.dev=settings;
	}
	public void set3D(boolean is3D) {
		this.is3D=is3D;
	}
	public void setTimer(int intervall) {
		timer.setTimer(intervall);
	}
	public void setSize(double w ,double h) {
		this.width=w;
		this.height=h;
		setPixel(h);
	}
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	public double setYPos(double yPos) {
		this.yPos = yPos;
		return yPos;
	}
	public void setWidth(double width) {
		this.width = width;
	}	
	public void setHeight(double height) {
		this.height = height;
	}	
	//-------------------------
	public GEngine(){	
		this(400);
	}
	public GEngine(double height){
		setPixel(height);
	}
					
	public AnchorPane getContent(){	
		getContentFrame().getChildren().addAll(backGround());	
		coords();
		return getContentFrame();
	}
	
	public Group backGround() {	
		Group mFrame = new Group();
		double customWidth = (getField().length)*getBox();
		double customHeight=(getField()[0].length-1)*getBox();
		if (!is3D) {
			Rectangle bgFrame = new Rectangle();
			bgFrame.setX(getXPos()-5);
			bgFrame.setY(getYPos()-5);
			bgFrame.setWidth(customWidth+10);
			bgFrame.setHeight(customHeight+10);
			bgFrame.setArcWidth(10);
			bgFrame.setArcHeight(10);
			bgFrame.setFill(mainFrame);
			bgFrame.setEffect(new DropShadow(30, 10, 10, new Color(0.0, 0.0, 0.0, 0.5)));
			mFrame.getChildren().add(bgFrame);
		}else {
			Gfx bg3DFrame= new Gfx(getXPos(),getYPos(),customWidth, customHeight, 0,0, 0.15,0.8 );
			Gfx.setCommon(getXPos()+0.5*getWidth(), getYPos()+0.5*height);
			bg3DFrame.setCol(0.5, 0.5, 0.7, 0);
			bg3DFrame.setCustomStroke(new Color(0.5,0.5,0.7,1));
			bg3DFrame.reLoad();
			mFrame.getChildren().add(bg3DFrame.getCube());
		}
	return mFrame;
	}

		class Value{
			public Block b;
			private Color c;		
			private double xpos;
			private double ypos;
			private boolean filled=false;			
			Rectangle mFrame;
			
				public void unFill() {
					if(dev) mFrame.setFill(c);
					filled = false;
				}
				
				public boolean isFilled() {
					return filled;
				}
				
				public void fill(Block binc) {
					this.b=binc;
					if(dev) mFrame.setFill(new Color(1, 0, 0, 1));
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
		for (int i = 0; i < getField().length; i++) {
			for (int j = 0; j < getField()[i].length; j++) {					
				if(!init) {
					if(j < getField()[i].length-1) {
					getField()[i][j] = new Value(j, i, ctest(i,j), getBox());
					getContentFrame().getChildren().add(getField()[i][j].mFrame);
					}else {
						getField()[i][j] = new Value(j, i, ctest(i,j), getBox());
						getField()[i][j].filled=true;
					}
				}								
			}		
		} 
		init=true;
	}
	
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
	
	public void startTimer(int intervall) {
		timer = new timerObject();
		timer.setTimer(intervall);
		Thread t1 = new Thread(timer); 
		t1.setDaemon(true);
		t1.start();	
	}
	
		public AnchorPane getContentFrame() {
		return contentFrame;
	}

	public void setContentFrame(AnchorPane contentFrame) {
		this.contentFrame = contentFrame;
	}

		public Value[][] getField() {
		return field;
	}

	public void setField(Value[][] field) {
		this.field = field;
	}

		public double getBox() {
		return box;
	}

	public void setBox(double box) {
		this.box = box;
	}

		class platformRun implements Runnable{
			public void run() {
				pulse();
			}
		}
}
