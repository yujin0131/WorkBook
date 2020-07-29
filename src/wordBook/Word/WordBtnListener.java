package wordBook.Word;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WordBtnListener implements ActionListener{
	
	protected static Object addWord;
	Frame wordmenuf = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wordmenuf = new Frame("단어장");
		wordmenuf.setLayout(null);
		wordmenuf.setBounds(500, 100, 449, 800);

		Button addWord = new Button("단어 추가");
		Button wordList = new Button("단어 보기");
		Button backHome = new Button("HOME");
		
		ImageIcon img = new ImageIcon("무민단어장.jpg");		
		JLabel j1 = new JLabel(img);
		j1.setBounds(0,0,449,800);
		
		addWord.setBounds(80, 100, 300, 80);
		wordList.setBounds(80, 210, 300, 80);
		backHome.setBounds(80, 660, 300, 80);

		//단어추가
		AddWordButtonListener awbListener = new AddWordButtonListener();
		addWord.addActionListener(awbListener);
		
		//단어보기
		ListWordButtonListener lwbListener = new ListWordButtonListener();
		wordList.addActionListener(lwbListener);

		backHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wordmenuf.dispose();
			}
		});
		wordmenuf.add(addWord);
		wordmenuf.add(wordList);
		wordmenuf.add(backHome);
		wordmenuf.add(j1);
		wordmenuf.setVisible(true);
		//종료
		wordmenuf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				wordmenuf.dispose();
			}
		});
	}
}
