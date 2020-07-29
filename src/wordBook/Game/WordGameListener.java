package wordBook.Game;
import wordBook.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.LayerUI;


public class WordGameListener implements ActionListener{
	WordBook wb;
	Frame wordmenuf = null;
	WordGameScramble ws = new WordGameScramble(); 
	BoxGameListener bgl = new BoxGameListener();
	int cnt = 1;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		


		Frame wordGamef = new Frame("게임");
		wordGamef.setBounds(490,140,438,750);
		wordGamef.setLayout(null);

		Button MixGame = new Button("MIXGAME");
		Button BoxGame = new Button("BOXGAME");
		Button Home = new Button("HOME"); 

		ImageIcon img = new ImageIcon("무민게임화면.jpg");		
		JLabel j1 = new JLabel(img);
		j1.setBounds(0,0,438,750);

		MixGame.setBounds(95, 60, 250, 70);
		BoxGame.setBounds(95, 150, 250, 70);   
		Home.setBounds(95, 640, 250, 70);

		MixGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Frame Mixf = new Frame();
				Mixf.setBounds(100,100,500,250);
				
				// 북쪽
				Panel pNorth = new Panel();
				Label l1 = new Label("순서를 맞춰주세요");
				Font font = new Font("궁서", Font.BOLD, 15);

				l1.setBackground(Color.LIGHT_GRAY);
				l1.setFont(font);

				pNorth.add(l1);

				// 중앙
				Panel pMiddle = new Panel();
				TextArea ta = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
				String answer = ws.getAnswer();
				ta.setText(ws.getScrambleWord(answer));
				ta.setEditable(false);
				pMiddle.add(ta);

				
				// 하단
				Panel pSouth = new Panel();
				TextField mixTf = new TextField(20);
				Button MixBtn = new Button("입력");
				MixBtn.setEnabled(false); //입력버튼 비활성화
				pSouth.add(mixTf);
				pSouth.add(MixBtn);
				
				
				
				// 텍스트 필드에 값이 한글자라도 들어왔을때 입력 버튼 활성화
				mixTf.addTextListener(new TextListener() {

					@Override
					public void textValueChanged(TextEvent e) {
						if(mixTf.getText().trim().equals("")) {
							MixBtn.setEnabled(false);
						}else {
							MixBtn.setEnabled(true);
						}
					}
				});
				
				mixTf.addKeyListener(new KeyAdapter() {
					//엔터치면 다음 텍스트필드로 넘어감
					public void keyTyped(KeyEvent e) {
						
							MixBtn.requestFocus();
						
					}
				});
				
				mixTf.requestFocus();
				
				// 입력 버튼 클릭 시
				MixBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(ws.getStr().equals(mixTf.getText().trim())) {
							JOptionPane.showMessageDialog(Mixf, "정답입니다!!\n"+"("+cnt+"회 시도)");
							//Mixf.dispose();
							cnt = 1;
							ta.setText("");
							String answer = ws.getAnswer();
							ta.setText(ws.getScrambleWord(answer));
							mixTf.setText("");
							mixTf.requestFocus();
							
						}else {
							JOptionPane.showMessageDialog(Mixf, "틀렸습니다..\n"+"("+cnt+"회 시도)");
							cnt++;
							mixTf.setText("");
							mixTf.requestFocus();
						}
					}
				});
				
				// 입력 버튼 엔터 눌렀을 떄
				MixBtn.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							if(ws.getStr().equals(mixTf.getText().trim())) {
								JOptionPane.showMessageDialog(Mixf, "정답입니다!!\n"+"("+cnt+"회 시도)");
								//Mixf.dispose();
								cnt = 1;
								ta.setText("");
								String answer = ws.getAnswer();
								ta.setText(ws.getScrambleWord(answer));
								mixTf.setText("");
								mixTf.requestFocus();
							}else {
								JOptionPane.showMessageDialog(Mixf, "틀렸습니다..\n"+"("+cnt+"회 시도)");
								cnt++;
								mixTf.setText("");
								mixTf.requestFocus();
							}
						}
					}
				});
				
				Mixf.add(pNorth, BorderLayout.NORTH);
				Mixf.add(pMiddle, BorderLayout.CENTER);
				Mixf.add(pSouth, BorderLayout.SOUTH);


				Mixf.setVisible(true);
				Mixf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						Mixf.dispose();
					}
				});

			}
		});

		// boxgame 버튼 클릭시
		BoxGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoxGame.addActionListener(bgl);
			}
		});
		
		// home 버튼 클릭시
		Home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wordGamef.dispose();
			}
		});

		

		wordGamef.add(MixGame);
		wordGamef.add(BoxGame);
		wordGamef.add(Home);
		wordGamef.add(j1);
		
		wordGamef.setVisible(true);
		wordGamef.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				wordGamef.dispose();
			}
		});


	}
}