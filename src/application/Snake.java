package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Snake extends GEngine{

	private int timer = 200;
	private int[]PDirection = {1,0};//Relative change of X,Y Position when pulse occurs
	private int[] actualPos= {20,20};//actual X,Y Head Position in Field Array
	private int[] foodLoc= {0,0};
	private Circle ele= new Circle();

	// Change direction of new Head spawn, only allowed 90/-90 degree
	@Override
	public void move(int x, int y) {
		this.PDirection[0]= (PDirection[0]+x!=0)? x:PDirection[0];
		this.PDirection[1]= (PDirection[1]+y!=0)? y:PDirection[1];
	}

	@Override
	public void exit() {
		killTimer();
		getContentFrame().getChildren().clear();
		Controller.startPage();
	}

	@Override
	public String getCurrentGame() {
		return "snake";
	}

	@Override
	public void setPixel(double height) {
		setXPos(10);
		setYPos(10);
		setWidth(height*1.25);
		this.setBox(height/24);
		setField(new Value [(int) (getWidth()/getBox())][(int)(height/getBox())]);
	}

	Snake(){
	Block.set3D(false);
	super.set3D(false);
	}

	@Override
	public void go() {
		super.setDeveloperMode(false);
		startTimer(timer);
		genValidRndLoc();
		getContentFrame().getChildren().add(ele);
		border();
	}
		//spawns a new block (Snake Head) in given x,y direction
	@Override
	public void pulse( ) {
		actualPos[0]+=PDirection[0];
		actualPos[1]+=PDirection[1];
		if(checkGOConditions()) {
			blockWrapper(actualPos[0],actualPos[1],5);
			if(actualPos[0]==foodLoc[0]&&actualPos[1]==foodLoc[1]) {
				feast();
			}
			refresh();
		}else {System.out.println("Game Over");
		killTimer();}
	}
		//Game over conditions
	public boolean checkGOConditions() {
		if(super.getField()[actualPos[0]][actualPos[1]].isFilled() || ((actualPos[0]<=0)^(actualPos[1]<=0)) || (actualPos[0]>=super.getField().length-1))return false;
		if(actualPos[1]>=super.getField()[0].length-2) return false;
		return true;
	}

	public Block blockWrapper(int xBlockCoord,int yBlockCoord,int TTL) {
		Block staticBlock = new Block(xBlockCoord,yBlockCoord,getBox(),0,0,TTL);
		super.getField()[xBlockCoord][yBlockCoord].fill(staticBlock);
		getContentFrame().getChildren().add(staticBlock.getBlock());
		staticBlock.setColor(new Color(0.2,0.8,0.4,1), 0);
		return staticBlock;
	}
		//get random x,y location and check for free space, spawn foodobject
	public void genValidRndLoc() {
		int x;
		int y;
		do {
			x = (int) (1+((super.getField().length-2)*Math.random()));
			y = (int) (1+((super.getField()[0].length-2)*Math.random()));
		}while(super.getField()[x][y].isFilled());
		this.foodLoc = new int[] {x,y};
		foodGFX();
	}

	public void feast() {
		Block.feast(5);
		genValidRndLoc();
	}

	public void foodGFX() {
		ele.setRadius(getBox()/2);
		ele.setCenterX(getXPos()+(getBox()*foodLoc[0]+getBox()/2));
		ele.setCenterY(getYPos()+(getBox()*foodLoc[1]+getBox()/2));
		ele.setFill(Color.RED);
	}

	public void refresh() {
		for (int y=0;y<super.getField()[0].length-1;y++) {
			for(int x=0; x< super.getField().length;x++) {
				if(super.getField()[x][y].b!=null&&super.getField()[x][y].isFilled()) {
					super.getField()[x][y].b.pulse();
				}
			}
		}
	}

	private void border() {
		Color bColor = new Color(0.3,0.3,0.3,0.8);
		for (int c=0;c<4;c++) {
			int n = (c<=1)?getField().length:getField()[0].length-1;
			for (int i=0;i<n;i++) {
				if (c <= 1) {
					Block border = blockWrapper(i,getField()[0].length-2,50);
					border.setImmortal(true);
					border.setColor(bColor, 0);
					Block border1 = blockWrapper(i,0,50);
					border1.setImmortal(true);
					border1.setColor(bColor, 0);
				}else {
					Block border = blockWrapper(getField().length-1,i,50);
					border.setImmortal(true);
					border.setColor(bColor, 0);
					Block border1 = blockWrapper(0,i,50);
					border1.setImmortal(true);
					border1.setColor(bColor, 0);
				}
			}
		}
	}
}
