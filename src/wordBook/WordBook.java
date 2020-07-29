package wordBook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WordBook implements Serializable{
	String eng;
	String kor;
	public static HashMap<String, String> wbhash = new HashMap<>();
	public static String key;
	public static String value;
	static int size;
	private String mixKey;

	public static void setKey(String key) {
		WordBook.key = key;
	}
	public String getMixKey() {
		return mixKey;
	}
	public void setMixKey(String mixKey) {
		this.mixKey = mixKey;
	}

	//Ű ���鸸 arraylist�� �ֱ� ���� ����
	ArrayList<String> al = new ArrayList<>();

	public String getEng() {
		return eng;
	}
	public void setEng(String eng) {
		this.eng = eng;
	}
	public String getKor() {
		return kor;
	}
	public void setKor(String kor) {
		this.kor = kor;
	}


	public static String getKey() {
		return key;
	}

	public static int getSize() {
		size=wbhash.size(); 
		return size;
	}


	public static Object getKeyFromValue(HashMap wbhash, Object value) {
		for (Object o : wbhash.keySet()) {
			if (wbhash.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}
	public void setHash(String eng, String kor) {
		this.eng = eng;
		this.kor = kor;

		wbhash.put(eng, kor);

		Iterator<String> map = wbhash.keySet().iterator();
		while(map.hasNext()) {
			this.key = map.next();
			//key������ arraylist�� ����
			al.add(key);
			this.value = wbhash.get(key);
			System.out.println(key + " : "+ value);
		}



		//�������� key�� �ƹ��ų� ��°���
		System.out.println(al.get(0));
	}
	public static HashMap<String, String> getWbhash() {
		return wbhash;
	}
	public static void setWbhash(HashMap<String, String> wbhash) {
		WordBook.wbhash = wbhash;
	}

}