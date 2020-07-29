package wordBook;

import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class WordWriter {
	
	public WordWriter(WordBook wordBook) {
	
		
		String path = "C:\\yujin\\English\\WordBook.txt";
		File dir = new File("C:\\yujin\\English");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(wordBook.wbhash);
			
			System.out.println("-------------------------------");
					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("기록저장 실패");
		} finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
