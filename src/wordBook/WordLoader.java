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


	//파일 저장된 거 불러오는 클래스
	public WordLoader(WordBook wb) {
		this.wb = wb;
		
		String path = "C:\\yujin\\English\\WordBook.txt";
		File f = new File(path);
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		if(f.exists()) {
			//물리적인 경로 존재하면 로드작업 수행
			try {
				fis = new FileInputStream(path);
				ois = new ObjectInputStream(fis);
				
				wb.wbhash = (HashMap<String, String>)ois.readObject();
				System.out.println("로드 성공");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("로드실패");
			} finally {
				try {
					ois.close();
					fis.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else {
			//물리적 경로 존재하지 않을 경우( 경로 지정하고 완전 처음 실행 시 )
			System.out.println("새로운 단어들을 생성합니다.");
		}
	}
}
