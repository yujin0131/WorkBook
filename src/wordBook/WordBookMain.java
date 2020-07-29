package wordBook;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import wordBook.Game.WordGameListener;
import wordBook.Test.WordTest;
import wordBook.Word.WordBtnListener;



public class WordBookMain {
	public static void main(String[] args) {
		//���θ޴�
		Frame f = new Frame("����");
		WordBook wb = new WordBook();
		f.setBounds(500, 140, 420, 746);
		f.setLayout(null);
		
		new WordLoader(wb);
		
		ImageIcon img = new ImageIcon("����.jpg");		
		JLabel j1 = new JLabel(img);
		j1.setBounds(0,0,420,746);
		
		
		Label label = new Label("HOME");
		Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 33);
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(160, 100, 110, 60);
		label.setFont(font);
		
		Button wordBtn = new Button("�ܾ���");
		Button testBtn = new Button("����");
		Button gameBtn = new Button("����");
		
		wordBtn.setBounds(30, 230, 100, 100);
		testBtn.setBounds(160, 230, 100, 100);
		gameBtn.setBounds(280, 230, 100, 100);
		
		WordBtnListener wbl = new WordBtnListener();
		wordBtn.addActionListener(wbl);
		
		WordTest wtest = new WordTest();
		testBtn.addActionListener(wtest);
		
		WordGameListener wgbl = new WordGameListener();
		gameBtn.addActionListener(wgbl);
		
		f.add(label);
		f.add(wordBtn);
		f.add(testBtn);
		f.add(gameBtn);
		f.add(j1);
		f.setVisible(true);
		//����
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
