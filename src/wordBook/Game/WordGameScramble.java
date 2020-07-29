package wordBook.Game;
import wordBook.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
  
public class WordGameScramble {

	
	private String shake;//정답을 섞을 메서드
	ArrayList<WordBook> list = new ArrayList<>();
	WordBook wb;
	private String str;
	public String getStr() {
		return str;
	}
	//정답을 반환하는 메서드
	public String getAnswer() {
		list.clear();
		str = "";
		Iterator<String> map = wb.wbhash.keySet().iterator();
		while(map.hasNext()) {
			wb = new WordBook();
			wb.key = map.next();
			wb.setMixKey(wb.key);
			list.add(wb);			
		}
		for(int i = 0; i<list.size(); i++) {
			System.out.print(list.get(i).getMixKey()+"\t");
		}

		int idx = new Random().nextInt(list.size());
		this.str = list.get(idx).getMixKey();

		return str;
	}

	//정답을 섞어서 반환하는 메서드
	public String getScrambleWord(String str) {
		shake = "";
		//정답 단어를 문자 단위로 끊어서 저장할 수 있도록 
		//int[]배열 생성
		int[] inArr = new int[ this.str.length() ];

		outer : for( int i = 0; i < inArr.length; ) {

			inArr[i] = new Random().nextInt(this.str.length());

			for( int j = 0; j < i; j++ ) {
				if( inArr[i] == inArr[j] ) {
					continue outer;
				}
			}//inner

			i++;
		}//for

		for( int i = 0; i < str.length(); i++ ) {
			shake += str.charAt( inArr[i] );
		}
		System.out.println("---------------------");
		System.out.println(shake);
		System.out.println("---------------------");
		return shake;
	}//getScrambleWord()

}
