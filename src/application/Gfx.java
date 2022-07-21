package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Gfx {

	private Group cube=new Group();
	private double a;
	private double b;
	private double xloc;
	private double yloc;
	private double depth;
	private double BaseTransp=0.15;
	private	double red=1;
	private	double green=0;
	private	double blue=0.3;
	private	static double[] common;					//Vanishing Point
	private	static boolean commonIsSet=false;
	private	double[] custom_new;
	private	double[][][] coords;
	private boolean customStroke=false;
	private boolean isVisible=true;
	private	double frontOpacity=1;
	private	Color stroke;
	private double[][] colorset;

	public void setCustomStroke(Color stroke) {
		this.customStroke=true;
		this.stroke = stroke;
	}

	public void colorDB() {
		colorset=new double[][] {
				{1,0,0.3},{0,1,0.3},{0,0.2,0.8},{0,0.9,0.7},{0.6,0.1,0.5}};
	}

	public void colorSet(int c) {
		red= 	colorset[c][0];
		green= 	colorset[c][1];
		blue= 	colorset[c][2];
	}

	public void setVisible(boolean isVisible) {
		this.isVisible=isVisible;
	}

	public void setAbsoluteLoc(double x,double y ) {
		this.xloc=x;
		this.yloc=y;
		custom_new = new double[] {xloc+a/2,yloc+b/2};
	}
		//common Vanishing Point
	public static void setCommon(double x,double y) {
		common[0]=x;
		common[1]=y;
		commonIsSet=true;
	}

	public void initCustom() {
		custom_new = new double[] {xloc+a/2,yloc+b/2};
		if(!commonIsSet) {common = new double[]{0,0};}
	}

	public void setCol(double red,double green, double blue,double frontOpacity) {
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.frontOpacity=frontOpacity;
		reLoad();
	}

	public Gfx(double centerX,double centerY ,double a,double b,int x,int y,double BaseTransp,double depth) {
		this.depth=depth;
		this.BaseTransp=BaseTransp;
		this.xloc= centerX+a*x;
		this.yloc= centerY+b*y;
		this.a=a;
		this.b=b;
		colorDB();
		initCustom();
		figure();
	}

	public Gfx(double centerX,double centerY ,double axb,int x,int y,double BaseTransp,double depth) {
		this.depth=depth;
		this.BaseTransp=BaseTransp;
		this.xloc= centerX+axb*x;
		this.yloc= centerY+axb*y;
		a=axb;
		b=axb;
		colorDB();
		initCustom();
		figure();
	}
		//recalculate coords after movement
	public void reLoad() {
		figure();
	}
		//generate coords for 3D projection
	public void fig() {

		double cScaleTB= BaseTransp*1.5>1? 1 : BaseTransp*1.5;
		double cScaleLRB=BaseTransp*2>1? 1 : BaseTransp*2;

		double x= custom_new[0];
		double y= custom_new[1];

		double[] A = new double[] {x - a/2, y - b/2};
		double[] B = new double[] {x + a/2, y - b/2};
		double[] C = new double[] {x + a/2, y + b/2};
		double[] D = new double[] {x - a/2, y + b/2};

		double x2 = custom_new[0]+((common[0]-custom_new[0])*(1-depth));
		double y2 = custom_new[1]+((common[1]-custom_new[1])*(1-depth));
		double a2 = a*depth;
		double b2 = b*depth;

		double[] A2= new double[] {x2 - a2/2, y2 - b2/2};
		double[] B2= new double[] {x2 + a2/2, y2 - b2/2};
		double[] C2= new double[] {x2 + a2/2, y2 + b2/2};
		double[] D2= new double[] {x2 - a2/2, y2 + b2/2};

		coords=new double[][][]{	{ A, B, C, D,{frontOpacity}}, 				//front
									{A2,B2,C2,D2,{BaseTransp*frontOpacity}},	//back
									{ A, B,B2,A2,{cScaleTB}},					//top
									{ D, C,C2,D2,{cScaleTB}},					//bottom
									{ B, C,C2,B2,{cScaleLRB}},					//right
									{ D, A,A2,D2,{cScaleLRB}}};					//left
	}

	public void figure() {
		fig();
		getCube().getChildren().clear();
		for (double[][] coord : coords) {
			Polygon top = new Polygon();
			top.setFill(new Color(red,green,blue,coord[4][0]));
			   top.setStroke(customStroke?stroke:new Color(1-red,0,1-blue,coord[4][0]));
			   for (int xy=0;xy<coord.length-1;xy++) {
				   top.getPoints().addAll(coord[xy][0],coord[xy][1]);
			   }
			top.setVisible(isVisible);
			getCube().getChildren().add(top);
		}
	}

	public Group getCube() {
		return cube;
	}

	public void setCube(Group cube) {
		this.cube = cube;
	}
}
