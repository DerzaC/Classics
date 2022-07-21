package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;



public class HighScore implements Serializable{

	private static final long serialVersionUID = 1L;
	private Data scorez = new Data();
	private static HighScore hs;
	private GAMES GAME;
	private boolean written =false;
	
	static {
		hs=new HighScore();
	}
	public static GAMES getGame() {
		return hs.GAME;
	}

	//-------------------------------------------------------------
	class Data implements Serializable{
		private static final long serialVersionUID = 1L;
		private ScoreSet tetris = new ScoreSet(5);
		private ScoreSet snake = new ScoreSet(5);
		
		public boolean isNewScore(int value) {		
			switch (getGame()) {
			case TETRIS:
				return tetris.isNewScore(value);
			case SNAKE:
				return snake.isNewScore(value);
			}			
			return false;
		}
		
		public void putNewScore(String name, int score) {
			switch (getGame()) {
			case TETRIS:
				tetris.put(name, score);
				break;
			case SNAKE:
				snake.put(name, score);	
				break;
			}
			toFile();
		}
		
		public String getScore() {
			switch(getGame()) {
			case TETRIS:
				return tetris.getScore();			
			case SNAKE:
				return snake.getScore();
			}
			return null;
		}
	}
	//---------------------------------------------------
	class ScoreSet implements Serializable{
		private static final long serialVersionUID = 1L;
		private int length = 5;
		private String[] name = new String[length];
		private int[] score = new int[length];
		
		public void put(String name,int value) {
			int pos = getPosition(value);
			if(pos!=-1) {
				for(int i=length-2;i>pos;i--) {
					this.score[i+1]=this.score[i];
					this.name[i+1]=this.name[i];
				}
				this.name[pos]=name;
				this.score[pos]=value;
			}
		}
		
		ScoreSet(int length){
			this.length=length;
		}
		
		public boolean isNewScore(int value) {
			return getPosition(value)!=-1;
		}
		
		private int getPosition(int value) {
			for (int i=0;i<length;i++ ) {
				if(score[i]<value) return i;
			}
			return -1;
		}	
		
		public String getScore() {
			String tmp=""+getGame()+"\n\n";
			for(int i=0;i<length;i++) {
				tmp += name[i]+"\t"+score[i]+"\n";
			}
			return tmp;
		}
	}
	//-------------------------------------------------------
	
	
	public static void setGame(GAMES GAME) {
		hs.GAME=GAME;
	}
	
	public static boolean isNewScore(int value) {
		if (!hs.written) {
			hs.written=true;
			return hs.scorez.isNewScore(value);
		}
		return false;
	}
	
	private HighScore(){		
		File f = new File("src\\application\\object.dat");
		if(f.exists() && !f.isDirectory()) { 
			fromFile();  
		}
	}
	
	public static String getScore(GAMES g) {
		hs.GAME=g;
		return hs.scorez.getScore();		
	}
	
	public static void putNewScore(String name, int score) {
		hs.written=true;
		hs.scorez.putNewScore(name, score);
	}
	
	public static void newGame(GAMES g) {
		hs.GAME=g;
		hs.written=false;
	}
	
	
	
	
	
	
	public void fromFile() {
		try (FileInputStream fis = new FileInputStream("src\\application\\object.dat");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			scorez=(Data) ois.readObject();
			System.out.println("done");
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Error: cannot read file");
		    ex.printStackTrace();
		}	
	}
	
	public void toFile() {
		try (FileOutputStream fos = new FileOutputStream("src\\application\\object.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
		    oos.writeObject(scorez);
		} catch (IOException ex) {
			System.out.println("Error: cannot write to file");
		    ex.printStackTrace();
		}
	}

}
