package application;


import application.GEngine.Value;
import javafx.scene.paint.Color;

public class LinkedBlox {
	
	private int xStartPos;
	private int startTTL;
	private double boxSize;
	private Block[] fig = new Block[4];
	private int[][][][] basic;
	private int rot=0;
	private int roll;	
	private ObserveEmAll[] obs = new ObserveEmAll[4];
	
	public int getStartTTL() {
		return startTTL;
	}
		
	public void reCalculate() {	
		ObserveEmAll.reset();
	for ( int i=0;i<4;i++) {
		obs[i].prepare();
		}
	}

	public Block[] getBlockFig() {
		return fig;
	}
	
	private void randomizOr() {
		roll = (int)(Math.random()*7);
	}
	
	public LinkedBlox(int x, int ttl,double box) {
		this.xStartPos=x;
		this.startTTL=ttl;
		this.boxSize=box;
		turboArray2000();
		randomizOr();	
		render();
	}	
	
	// working math rotation(mirroring around axis) - too "jumpy" in y direction 
	// because of the lack of symmetry axis
	// 
	//
//	public void initData() {
//		this.rot=0;
//		this.rotateY=false;
//		this.rotateX=false;		
//			//block	-----[fig][blockindex][x,y] 		
//		basic = new int[][][]	
//				{{{0,0},{1,0},{0,1 },{1,1}},//O		
//				{{0,-1},{0,0},{0,1 },{0,2}},//I						
//				{{0,-1},{0,0},{0,1 },{-1,1}},//J											
//				{{0,-1},{0,0},{0,1 },{1,1}},//L									
//				{{0,2},{1,1},{0,1},{1,0}},	//Z																						    
//				{{0,-1},{0,0},{1,0 },{1,1}},//S
//				{{-1,0},{0,0},{1,0},{0,1}},//T
//				{{0,2,4,4,2,2,4}}};	//[7][0][roll]=allowed rotations
//	}
//	
//	
//	int value1=1;
//	int value2=1;
//	int value3=1;
//	int value4=1;
//	
//	public void rotate() {
//		
//		this.rot= (rot<basic[7][0][roll]-1)? rot=rot+1: 0;
//		value1 = (rot%2==0)?1:0;
//		value3 = (rot%2==0)?value3*=-1:value3*1;	
//		value4 = (rot%2==0)?value4*=-1:value4*1;	
//		value4 = (rot==1)?value4*=-1:value4*-1;
//		value2 = 1-value1;
//		
//		for (int i=0;i<4;i++) {
//			int x=(basic[roll][i][value1]*value3)-(obs[i].xCustomPos);
//			int y=(basic[roll][i][value2]*value4)-(obs[i].yCustomPos);		
//			fig[i].rotate(x,y);
//			obs[i].xCustomPos=basic[roll][i][value1]*value3;
//			obs[i].yCustomPos=basic[roll][i][value2]*value4;		
//		}
//	}

	// pre-test: possibility of rotation
	public void rotate() {
		reCalculate();
		if (ObserveEmAll.rotation == 1) {
			rotate1();
		}
	}
		
	// feed the observe class with the relative coords of next rotation
	public void getNextRotation(int i) {
		int nRot = (rot==basic[roll].length-1)? 0: rot+1;
		obs[i].xListenerPos=basic[roll][nRot][i][0];
		obs[i].yListenerPos=basic[roll][nRot][i][1];	
	}
		
	// execute rotation + actualization of current coords in the observe class
	public void rotate1() {
		if(rot==basic[roll].length) {rot=0;}	
		for (int i=0;i<4;i++) {
			int x=(basic[roll][rot][i][0])-(obs[i].xCustomPos);
			int y=(basic[roll][rot][i][1])-(obs[i].yCustomPos);		
			fig[i].changePosition(x,y);
			obs[i].xCustomPos=basic[roll][rot][i][0];
			obs[i].yCustomPos=basic[roll][rot][i][1];	
			getNextRotation(i);
		}
		rot++;
	}
	
	// generate 3 bit random color 
	public Color getRndColor() {	
		return new Color((int)(Math.random()*2),(int)(Math.random()*2),(int)(Math.random()*2),1);			
	}
	
	// build figure with 4 block objects and colorize them in 2d and generate random nr for
	// 3d color preset
	public void render() {		
		Color rnd = getRndColor();
		int c3D=(int)(Math.random()*5);
		for(int i =0;i<=3;i++) {
			fig[i]=new Block(xStartPos+basic[roll][rot][i][0],2+basic[roll][rot][i][1], 
								boxSize, 0, 1,startTTL-1);
			fig[i].setColor(rnd,c3D);		
			obs[i]=new ObserveEmAll(fig[i],i);
			getNextRotation(i);
		}		
		rot++;
		if(Controller.currentGame.getDevMode()){fig[1].setColor(new Color(1, 0, 0, 1), c3D);}
	}
			
	//database for figures and their rotations; smoother than the math-way
	// addressable with -----[figureNr(0-6)][rotationNr(0-3)][blockNr(0-3)][x,y] 
	public void turboArray2000() {	
											//block figure
		basic= new int[][][][]	{{{	{0,0},{1,0},{0,1},{1,1}}},		
											//I	figure
								{{	{0,-1},{0,0},{ 0,1},{ 0,2}},
								{	{1, 0},{0,0},{-1,0},{-2,0}}},				
											//J figure
								{{	{ 0,-1},{0,0},{ 0, 1},{-1, 1}},
								{	{ 1, 0},{0,0},{-1, 0},{-1,-1}},
								{	{ 0, 1},{0,0},{ 0,-1},{ 1,-1}},
								{	{-1, 0},{0,0},{ 1, 0},{ 1, 1}}},									
											//L figure
								{{	{ 0,-1},{0,0},{ 0, 1},{ 1, 1}},
								{	{ 1, 0},{0,0},{-1, 0},{-1, 1}},
								{	{ 0, 1},{0,0},{ 0,-1},{-1,-1}},
								{	{-1, 0},{0,0},{ 1, 0},{ 1,-1}}},						
											//z	figure	
								{{	{0,2},{1,1},{0,1},{1,0}},
								{	{0,0},{1,1},{1,0},{2,1}}},																	
											//s	figure				    
								{{	{1,2},{1,1},{0,1},{0,0}},
								{	{0,1},{1,1},{1,0},{2,0}}},	
											//T figure
								{{	{-1, 0},{0,0},{ 1, 0},{ 0, 1}},
								{	{ 0,-1},{0,0},{ 0, 1},{-1, 0}},
								{	{ 1, 0},{0,0},{-1, 0},{ 0,-1}},
								{	{ 0, 1},{0,0},{ 0,-1},{ 1, 0}}}};	
	}
	// pre-test: possibility of left/right movement, move if possible
	public void move(int xMovement) {
		reCalculate();
		if (xMovement<0&&ObserveEmAll.moveLeft==1) {
			for(int i=0;i<4;i++) {
				fig[i].changePosition(xMovement,0);
			}
		}
		if (xMovement>0&&ObserveEmAll.moveRight==1) {
			for(int i=0;i<4;i++) {
				fig[i].changePosition(xMovement,0);
			}
		}
	}
	
	//trigger automovement(x,y pulsedirection in blockclass)
	public boolean pulse() {
		reCalculate();
		if (ObserveEmAll.pulse==1) {
			for (int i=0;i<4;i++) {
				fig[i].pulse();
			}
			return true;
		}else { 			
			return false;
		}
	}
			// observed positions and movement conditions class
		class ObserveEmAll{
			private int index;
			public static int rotation=1;
			public static int moveLeft =1;
			public static int moveRight=1;
			public static int pulse=1;
			private int xListenerPos;
			private int xCustomPos;
			private int yCustomPos;
			private int yListenerPos;
			
			public static void reset() {
				rotation=moveLeft=moveRight=pulse=1;
			}
			
			//movement conditions
			public void prepare() {
				Value[][] field = Controller.currentGame.getField();
				
				//identify actual absolute position of observed position
				int x=fig[index].getXloc()-(obs[index].xCustomPos)+(obs[index].xListenerPos);
				int y=fig[index].getYloc()-(obs[index].yCustomPos)+(obs[index].yListenerPos);	
				
				//actual absolute position
				int xLoc=fig[index].getXloc();
				int yLoc=fig[index].getYloc();
				
				// rotate fail conditions			
				if(!(x>=0 && x < field.length)^y>=field[1].length) rotation *=0;
				else if(field[x][y].isFilled()) rotation *=0;
				
				// move left fail conditions
				if(xLoc-1<0)moveLeft *=0;
				else if(field[xLoc-1][yLoc].isFilled()) moveLeft *=0;
				
				// move right fail conditions
				if(xLoc+1>=field.length)moveRight *=0;
				else if(field[xLoc+1][yLoc].isFilled()) moveRight *=0;
				
				//automove fail conditions
				if(!(yLoc+1<field[1].length-1)) pulse *=0;
				else if(field[xLoc][yLoc+1].isFilled()) pulse *=0;
				else if(!fig[index].isActive()) pulse *=0;
			}
			
			//constructor with initial data
			ObserveEmAll(Block observedBlock,int index){
				this.index = index;
				this.xCustomPos=basic[roll][rot][index][0];
				this.yCustomPos=basic[roll][rot][index][1];
			}			
		}

}
