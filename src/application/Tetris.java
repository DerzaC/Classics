package application;

public class Tetris extends GEngine {
	private int score =0;
	private int timer=500;
	private boolean tetris=false;
	private LinkedBlox figure;
	private Block[] fig = new Block[4];
	private int spawncounter=1;
	//private GuiBuilder guiBuild=Controller.guiBuild;
			
	public void linkedBlox() {
		int x = field.length/2;
		int ttl=super.field[0].length-2;
		double box=super.getBoxSize();
		figure = new LinkedBlox(x,ttl,box);
		this.fig=figure.getBlockFig();
		for(int i=0;i<4;i++) {
			if (is3D()) contentFrame.getChildren().addAll(fig[i].block3D.g);
			else contentFrame.getChildren().add(fig[i].block);		
		}
	}
		//devMode only possible in 2d (shows collision
		// and rotation axis in red)
	Tetris(boolean is3D,double xPos,double yPos){
		set3D(is3D);
		Block.set3D(is3D);
		setDeveloperMode(false);	
	}
	
	public void setPixel(double height) {
		setHeight(height);
		setWidth(height/2);
		super.box = getWidth()/10;
		field = new Value[(int) (getWidth()/box)][(int)(height/box)+1];
	}
	
	public String getCurrentGame() {
		return "tetris";	
	}
	
	Tetris(boolean is3D, double height){
		super.set3D(is3D);
		Block.set3D(is3D);
	}
		//exit
	public void exit() {
		killTimer();
		tetris=false;
		contentFrame.getChildren().clear();
		Controller.startPage();
	}
	
	public boolean isTetris() {
		return tetris; 
	}
	
	public void move(int x,int y) {
		if(y<0) figure.rotate();
		else if(y>0)pulse();
		else if (x!=0)figure.move(x); 
		newFrame();
	}
		// manual graphic refresh
	public void newFrame() {
		for(int i=0;i<4;i++) {
			fig[i].block();
		}
	}
		//rearrange after lines collapsed
	public void refresh() {
		for(int y=field[0].length-3;y>=0;y--) {
			for(int x=0;x<field.length;x++){
				if(field[x][y].isFilled()) {
				if(field[x][y].b.maxTTL>0) {
						field[x][y].unFill();
						field[x][y].b.reActivate();	
					}
				}
			}
		}
	}
		//Search for complete filled lines, increase ttl(time to live) of all blocks above a filled line
		//and starts refresh to execute pulse (automove) for each added ttl
	public void lineCheck() {
		int ttl=0;
		int count=0;
		for(int y=field[0].length-2;y>=0;y--) {
			for(int x=0;x<field.length;x++){					
				if(field[x][y].isFilled()&&field[x][y].b!=null){									
					count++;   
				}
				if(ttl>0&&field[x][y].isFilled()) {						
					field[x][y].b.maxTTL=ttl;
				}
				if(count==field.length) { 		
					killLine(y,ttl);
					ttl++;	
					count=0;	
				}
			}
			count=0;
		}
		if(ttl>0) refresh();
	}

		//remove filled line, increase score
	public void killLine(int y,int bonus) {
		for(int x=0;x<field.length;x++) {
			field[x][y].b.selfDestruct();
			score += 100*(bonus+1);
		}
	}
		//game start
	public void go() {
		linkedBlox();
		super.startTimer(timer);
		tetris=true;		
	}
		//game over
	public void gameOver() {
		Controller.guiBuild.setText("Game Over \n\nScore: "+score+"\n\nTimer"+timer);
		killTimer();
		tetris=false;
	}
		//search for filled lines and spawn a new figure
	public void respawn() {		
		lineCheck();		
		linkedBlox();	
		spawncounter++;
	}
		//Deactivation of Moveability after a Collision occurred/ Game Over Condition
	public void deActivate(){
		if(fig[0].getPulseCount()==0) {
			gameOver();
		}
		for (int i=0;i<4;i++) {
			fig[i].deActivate();	
		}
	}
		//Auto-move after a given period of time
	public void pulse() {		
		Controller.guiBuild.setText("Spawn: "+spawncounter+"\n\nScore: "+score+"\n\nTimer"+timer);
		if (!figure.pulse()) {
			deActivate();
			respawn();
		}
	}
}
