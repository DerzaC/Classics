package gamez;

import application.Controller;

public class Tetris1 extends GEngine {
//	private int round=1;
//	private int score =0;
//	private int timer=500;
//	public Block tBlock;
//	private boolean tetris=false;
//	private LinkedBlox figure;
//	private Block[] fig = new Block[4];
//	
//
//
//	public void testBlock() {
//		tBlock=new Block(0,0,this.box,0,1,super.field[0].length-1,250,10);
//		contentFrame.getChildren().addAll(tBlock.block,tBlock.element);
//	}
//	
//	public void linkedBlox() {
//		int x = field.length/2;
//		int ttl=super.field[0].length-2;
//		double box=super.getBoxSize();
//		double xpos=super.getXPos();
//		double ypos=super.getyPos();
//		figure = new LinkedBlox(x,ttl,box,xpos,ypos);
//		this.fig=figure.getBlockFig();
//		for(int i=0;i<4;i++) {
//			fig[i].setIndex(contentFrame.getChildren().size());
//			contentFrame.getChildren().addAll(fig[i].block,fig[i].element);
//		}
//	}
//	
//	
//	
//	public Tetris1(){
//		
//	}
//	
//	
//	public void move(int x, int y) {
//		if(y<0) {figure.xChange();}
//		Block.left=true;
//		Block.right=true;
//		for(int i=0;i<4;i++) {
//			fig[i].tryMove(x, 0,field.length-1);
//			fig[i].sync(0);
//		}		
//		if(fig[0].sync(0)){
//			for(int i=0;i<4;i++) {
//				fig[i].move(x, 0,field.length-1);
//			}
//			figure.reCalculate();
//		}
//		fig[0].reset(0);
//		if(y>0)pulse();	
//	}
//	
//	public void refresh() {
//		for(int y=field[0].length-2;y>=0;y--) {
//			for(int x=0;x<field.length;x++){
//				if(field[x][y].isFilled()) {
//					field[x][y].unFill();
//					field[x][y].b.reActivate();				
//				}
//			}
//		}
//	}
//		
//	public void lineCheck() {
//		int ttl=0;
//		int count=0;
//		for(int y=field[0].length-2;y>=0;y--) {
//			for(int x=0;x<field.length;x++){	
//				
//				if(field[x][y].isFilled()){
//					count++;   
//				}
//				if(ttl>0&&field[x][y].isFilled()) {		
//					field[x][y].b.maxTTL=ttl;
//				}
//				if(count==field.length) { 
//					killLine(y,ttl);
//					ttl++;	
//					count=0;
//				}
//			}
//			count=0;
//		}
//		if(ttl>0) refresh();
//	}
//			
//	public void killLine(int y,int bonus) {
//		for(int x=0;x<field.length;x++) {
//			field[x][y].b.selfDestruct();
//			score += 100*(bonus+1);
//			}
//		}
//	
//	public void go() {
//		setDeveloperMode(false);
//		linkedBlox();
//		startTimer(timer);
//		tetris=true;		
//	}
//	
//	public void respawn(int i) {		
//			lineCheck();		
//		if(i==3) {linkedBlox();}		
//	}
//	
//	public void deActivate(){
//		for (int i=0;i<4;i++) {
//			fig[i].isActive=false;
//			fig[i].isLinked=false;
//			fig[i].maxTTL=0;
//			fig[i].block();
//			field[fig[i].xloc][fig[i].yloc].fill((fig[i]));
//		}
//	}
//		
//	public void pulse() {
//		figure.reCalculate();
//		for(int i=0;i<4;i++) {
//			fig[i].prep();
//			fig[i].sync(1);			
//			if (!fig[i].isActive()) {
//				deActivate();
//				break;
//			}
//		}
//		if (fig[0].isActive())
//			for (int j=0;j<4;j++) {
//			fig[j].pulse();	
//		}else {
//			respawn(3);
//		}
//		Controller.guiBuild.setText("Runde: "+round+"\n\nScore: "+score+"\n\nTimer"+timer+"\nV2.0");
//		this.round++;
//	}
//
//	public boolean isTetris() {
//		return tetris; 
//	}
}
