package wordBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

public class WordLoader {
WordBook wb;
int size;
	public WordBook getWb() {
		return wb;
	}
	
	
	public int getSize() {
		size = wb.wbhash.size(); 
		return size;
	}


	//���� ����� �� �ҷ����� Ŭ����
	public WordLoader(WordBook wb) {
		this.wb = wb;
		
		String path = "C:\\yujin\\English\\WordBook.txt";
		File f = new File(path);
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		if(f.exists()) {
			//�������� ��� �����ϸ� �ε��۾� ����
			try {
				fis = new FileInputStream(path);
				ois = new ObjectInputStream(fis);
				
				wb.wbhash = (HashMap<String, String>)ois.readObject();
				System.out.println("�ε� ����");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�ε����");
			} finally {
				try {
					ois.close();
					fis.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else {
			//������ ��� �������� ���� ���( ��� �����ϰ� ���� ó�� ���� �� )
			System.out.println("���ο� �ܾ���� �����մϴ�.");
		}
	}
}
