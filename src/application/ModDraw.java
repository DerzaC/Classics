package application;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class ModDraw {
	private AnchorPane drawPane = new AnchorPane();
	private Rectangle mFrame;
	
	public void exit() {		
		drawPane.getChildren().clear();
		Controller.startPage();
		Controller.draw=null;			
	}
		
	public void build() {		
			double abx=20;
			int x=50;
			int y=350;
			int m=0;
			int n=0;		
			for (int i=0;i<5;i++) {
				for(int d=0;d<4;d++) {						
					Gfx b00 = new Gfx(x,y,abx,m,n,0.1,0.7);	
					b00.colorSet(i);	
					m= m<1?m+=1:0;
					n= m==0? 1:n;				
					b00.reLoad();
					drawPane.getChildren().addAll(b00.getCube());
				}
				n=m=0;
				x+=abx*4;
			}			
		}
		
	public AnchorPane getContent() {	
			mFrame = new Rectangle();
			mFrame.setId("modDraw");
			mFrame.setX(0);
			mFrame.setY(0);
			mFrame.setWidth(500);
			mFrame.setHeight(400);	
			mFrame.setFill(new Color(0,0,0,0.1));
			drawPane.getChildren().addAll(mFrame);
			mFrame.addEventHandler(MouseEvent.MOUSE_MOVED, Controller.eHandler.MouseEvent);			
			build();
			return drawPane;
	}
	
	public void refresh() {
		drawPane.getChildren().clear();
    	drawPane.getChildren().addAll(mFrame);
        build();		
	}

	class TBox{
		public Group g=new Group();
		public double axb;
		public double a=axb;
		public double b=axb;		
		private double xloc=100;
		private double yloc=150;
		private double depth;
		double BaseTransp=0.15;
		double red=1;
		double green=0;
		double blue=0.3;
		static double[] common;
		double[] custom_new;
		double[][][] coords;
		
		public static void setCommon(double x,double y) {	
			common[0]=x;
			common[1]=y;
		}
		
		public void initCustom() {
			custom_new = new double[] {xloc+a/2,yloc+b/2};
			common = new double[]{400,0};	
		}

		public void setCol(double red,double green, double blue) {
			this.red=red;
			this.green=green;
			this.blue=blue;
			reLoad();
		}
		
		public TBox(double a,double b,int x,int y,double BaseTransp,double depth) {
			this.depth=depth;
			this.BaseTransp=BaseTransp;
			this.xloc+=a*x;
			this.yloc+=b*y;
			this.a=axb;
			this.b=axb;
			initCustom();
			figure();
		}
		
		public TBox(double axb,int x,int y,double BaseTransp,double depth) {
			this.depth=depth;
			this.BaseTransp=BaseTransp;
			this.axb=axb;
			this.xloc+=axb*x;
			this.yloc+=axb*y;
			a=axb;
			b=axb;
			initCustom();
			figure();
		}
		
		public void reLoad() {
			figure();
		}

		public void fig() {
			double cScaleTB=BaseTransp*1.5;
			double cScaleLRB=BaseTransp*2;
			
			double x= xloc+a/2;
			double y= yloc+b/2;
			double[]A= new double[] {x-a/2,y-b/2};
			double[]B= new double[] {x+a/2,y-b/2};
			double[]C= new double[] {x+a/2,y+b/2};
			double[]D= new double[] {x-a/2,y+b/2};
			
			double x2 = custom_new[0]+((common[0]-custom_new[0])*(1-depth));
			double y2 = custom_new[1]+((common[1]-custom_new[1])*(1-depth));
			
			double a2=a*depth;
			double b2=b*depth;
			double[]A2= new double[] {x2-a2/2,y2-b2/2};
			double[]B2= new double[] {x2+a2/2,y2-b2/2};
			double[]C2= new double[] {x2+a2/2,y2+b2/2};
			double[]D2= new double[] {x2-a2/2,y2+b2/2};
			
			coords=new double[][][]{	{ A, B, C, D,{BaseTransp*2}}, 	//front
										{A2,B2,C2,D2,{BaseTransp}},		//back
										{ A, B,B2,A2,{cScaleTB}},		//top
										{ D, C,C2,D2,{cScaleTB}},		//bottom
										{ B, C,C2,B2,{cScaleLRB}},		//right
										{ D, A,A2,D2,{cScaleLRB}}};		//left
		}
			
		public void figure() {
			fig();
			g.getChildren().clear();			
			for (int f=coords.length-1;f>0;f--) {
				Polygon top = new Polygon();				
				top.setFill(new Color(red,green,blue,coords[f][4][0]));
			    top.setStroke(new Color(red,green,blue,1)); 
				for (int xy=0;xy<coords[f].length-1;xy++) {	
					top.getPoints().addAll(coords[f][xy][0],coords[f][xy][1]);
				}
				g.getChildren().add(top);				
			}			
		}
	}
}


