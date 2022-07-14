package gamez;

import javafx.scene.paint.Color;

public class LinkedBlox {
//	
//	private int xStartPos;
//	private int startTTL;
//	private double boxSize;
//	private Block[] fig = new Block[4];
//	private double xpos;
//	private double ypos;
//	private boolean rotate=false;
//	int[][][][] partey;
//	private int rot=0;
//	private int roll=1;	
//		
//	public void reCalculate() {		
//	}
//
//	public Block[] getBlockFig() {
//		return fig;
//	}
//	
//	private void randomizOr() {
//		roll = (int)(Math.random()*6);
//		figBuilder(roll, 0);
//	}
//	
//	public LinkedBlox(int x, int ttl,double box,double xpos,double ypos) {
//		this.xStartPos=x;
//		this.startTTL=ttl;
//		this.boxSize=box;
//		this.xpos=xpos;
//		this.ypos=ypos;
//		turboArray2000();
//		reCalculate();
//		randomizOr();	
//	}	
//			
//	public void turboArray2000() {	
//						//block	-----[fig][rot][blockindex][x,y] 	
//		partey= new int[][][][]	{{{{0,0},{1,0},{0,1 },{1,1}}},		
//							//I	=2	o:[1][1][0&2&3][]
//								{{{0,-1},{0,0},{0,1 },{0,2}},
//								{{-1,-2},{0,0},{1,2 },{2,4}}},				
//							//J	=4	o:[2][3][0&2&3][]
//								{{{0,-1},{0,0},{0,1 },{-1,1}},
//								{{-1,-2},{0,0},{1,2 },{-1,3}},
//								{{0,-3},{0,0},{0,3 },{-3,3}},
//								{{1,-2},{0,0},{-1,2 },{-3,1}}},									
//							//L =4	o:[3][3][0&2&3][]
//								{{{0,-1},{0,0},{0,1 },{1,1}},
//								{{-1,-2},{0,0},{1,2 },{3,1}},
//								{{0,-3},{0,0},{0,3 },{3,3}},
//								{{1,-2},{0,0},{-1,2 },{1,3}}},							
//							//z	=2	o:[4][1][0&3][]
//								{{{0,2},{1,1},{0,1},{1,0}},
//								{{1,4},{1,1},{0,1},{2,0}}},																	
//							//s	=2	o:[5][1][0&3][]					    
//								{{{0,0},{0,1},{1,1 },{1,2}},
//								{{-2,0},{0,1},{1,1 },{1,4}}}};	
//								//2do T
//								
//	}
//		
//	public void figBuilder(int f, int rot) {
//		Color bla = new Color(1, 0, 0, 1);
//		for(int i =0;i<=3;i++) {
//			fig[i]=new Block(xStartPos+partey[f][rot][i][0],1+partey[f][rot][i][1], 
//								boxSize, 0, 1,startTTL-1,xpos,ypos);
//		if(i==1) {fig[i].setColor(bla);}
//		}
//	}	
//	private int m=0;
//	private int n=1;
//	
//	public void xChange() {	
//		int f=this.roll;
//		if(rot+n>partey[f].length-1) {n=0;};
//		if(rot+m>partey[f].length-1) {m=0;};	
//			for (int i=0;i<4;i++) {
//				int x=partey[f][rot+m][i][0]-partey[f][rot+n][i][0];
//				int y=partey[f][rot+m][i][1]-partey[f][rot+n][i][1];
//				if((x!=0)||(y!=0)) {
//					fig[i].rotate(x, y);
//				System.out.print("x: "+x+" y: "+y+" i: "+i+"\n");	
//				}
//			}			
//			m++;
//			n++;
//	}
//		
//		class ObserveRot{
//			private static int index =0;
//			private int i;
//			public static boolean rotate;
//			private int xListenerPos;
//			private int yListenerPos;
//			public boolean rot;
//			
//			ObserveRot(){
//				this.i = index++;
//			}			
//		}

}
