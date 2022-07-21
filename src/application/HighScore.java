package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighScore implements Serializable{

	private static final long serialVersionUID = 1L;
	Data scorez = new Data();
	
	class Data implements Serializable{

		private static final long serialVersionUID = 1L;
		private int[] tetris= 	new int[]{1,2,3,4,5};
		private int[] snake=	new int[]{0,0,0,0,0};
		public int test = 1;
	}
	
	HighScore(){
		File f = new File("src\\application\\object.dat");
		if(f.exists() && !f.isDirectory()) { 
			fromFile();  
		}
		toFile();
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
