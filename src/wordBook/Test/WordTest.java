package wordBook.Test;
import wordBook.*;
import wordBook.Word.AddWordButtonListener;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class WordTest implements ActionListener  {

	Frame testmenuf = null;
	Frame kormenuf = null;
	Frame engmenuf = null;
	Frame no = null;

	Font font = new Font(Font.SANS_SERIF,Font.PLAIN, 18);
	WordBook wb;



	String answer = "";
	int count = 0; //정답 갯수  ->문제 맞출 때 만 증가
	int total = 0; //전체 문제 ->문제 할 때 마다 다 증가
	int i = 0;
	JLabel jla;

	String wrongWord = "";


	ArrayList<String> al = new ArrayList<>();
	int[] arr = new int[10];

	@Override
	public void actionPerformed(ActionEvent e) {
		testmenuf = new Frame("단어 시험");
		testmenuf.setLayout(null);
		testmenuf.setBounds(500, 140, 420, 750);

		Button korTest = new Button("한글 가리기");
		Button engTest = new Button("영어 가리기");
		Button backHome = new Button("HOME");

		ImageIcon img = new ImageIcon("무민_테스트.jpg");
		JLabel j1 = new JLabel(img);
		j1.setBounds(0,0,450,750);

		korTest.setBounds(85, 60, 250, 70);
		engTest.setBounds(85, 150, 250, 70);
		backHome.setBounds(85, 620, 250, 70);

		//한글 가리기---------------------------------------------------------------------
		korTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator<String> map = wb.wbhash.keySet().iterator();

				//al 어레이리스트에 키값 넣기 -> 중복없이 잘 들어가 있다
				while(map.hasNext()) {
					String key = map.next();
					al.add(key);

				}//while

				//단어가 10문제 이상 저장 되어 있을 때
				if(al.size() >= 10) {
					kormenuf = new Frame("한글 가리기");
					WordBook wb = new WordBook();


					AddWordButtonListener ab = new AddWordButtonListener();
					kormenuf.setBounds(400, 300, 700, 400);

					Panel pCenter = new Panel(); 
					pCenter.setBackground(Color.WHITE);

					JLabel label = new JLabel("알맞는 뜻을 입력하세요");
					label.setHorizontalAlignment(SwingConstants.CENTER);


					String path = "C:\\yujin\\English\\WordBook.txt";
					File f = new File(path);

					FileInputStream fis = null;
					ObjectInputStream ois = null;
					String str = "";

					//영어 문제 화면에 띄우기
					jla = null;
					long start = System.currentTimeMillis();

					//답 입력
					TextField tf = new TextField(20);
					Button btn = new Button("입력");



					//arr배열에 키값 범위 안의 랜덤 숫자 안겹치게 넣기
					outer : for(int i = 0; i< 10;) {
						arr[i] = new Random().nextInt(10);
						for(int j = 0; j<i; j++) {
							if(arr[i] == arr[j]) {
								
								continue outer;
							}
						}//inner for
						i++;
					}//outer for

					//영어문제 랜덤 출제
					
					jla = new JLabel(al.get(arr[i]));
					jla.setHorizontalAlignment(SwingConstants.CENTER);
					jla.setFont(new Font("serif", Font.BOLD, 80));

					//콘솔에 문제의 답 출력
					//System.out.println("test : " + wb.wbhash.get(al.get(arr[i])));


					//버튼 감지 --------------------------------------------------------------
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//맞틀 판별
							answer = tf.getText();
							i++;

							//i가 10문제 다 안풀었을 경우
							if(i < 10) {
								//정답 맞출 때
								if(answer.equals(WordBook.wbhash.get(jla.getText())) && jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer))) {

									count++;
									total++;

									tf.setText("");		
									tf.requestFocus();
									JOptionPane.showMessageDialog(kormenuf, "정답입니다!!\n"+ count + " / " + total);

									jla.setText(al.get(arr[i]));

								} else {
									//틀렸을 때
									total++;
									tf.setText("");

									wrongWord += jla.getText() + "\n";
									JOptionPane.showMessageDialog(kormenuf, "틀렸습니다.\n"+count + " / " + total);


									jla.setText(al.get(arr[i]));
								}
							}else {//다 풀었을 때
								//마지막 문제 단어 틀렸을 경우 리스트에 넣어야 함
								if(!(answer.equals(WordBook.wbhash.get(jla.getText()))&& jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer)))) {
									wrongWord += jla.getText() + "\n";
									total++;
									JOptionPane.showMessageDialog(kormenuf, "틀렸습니다.\n" + count + "/" + total);
								} else {
									count++; 
									total++;
									JOptionPane.showMessageDialog(kormenuf, "정답입니다!!\n"+ count + " / " + total);
								}
								i = 0;
								
								//시험 시간 재기
								long end = System.currentTimeMillis();

								int result = JOptionPane.showConfirmDialog(kormenuf, "시험 종료" + "\n" + "완료  : " + ( end - start )/1000 + "초!!"
								+ "\n\n"+ "틀린 단어들 : " + "\n"+ wrongWord, "시험종료", JOptionPane.CLOSED_OPTION);

								wrongWord = "";
								count = 0;
								total = 0;

								if(result == JOptionPane.CLOSED_OPTION) {
									//사용자가 창 그냥 닫을 경우
									kormenuf.dispose();
								} else {
									kormenuf.dispose();
								}
							}
						}
					});


					//엔터값 감지------------------------------------------------------------------------
					tf.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							if(e.getKeyChar() == KeyEvent.VK_ENTER) {
								//맞틀 판별
								answer = tf.getText();
								i++;

								//i가 10문제 다 안풀었을 경우
								if(i < 10) {
									//정답 맞출 때
									if(answer.equals(WordBook.wbhash.get(jla.getText())) && jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer))) {

										count++;
										total++;

										tf.setText("");		
										tf.requestFocus();
										JOptionPane.showMessageDialog(kormenuf, "정답입니다!!\n"+ count + " / " + total);

										jla.setText(al.get(arr[i]));

									} else {
										//틀렸을 때
										total++;
										tf.setText("");

										wrongWord += jla.getText() + "\n";
										JOptionPane.showMessageDialog(kormenuf, "틀렸습니다.\n"+count + " / " + total);


										jla.setText(al.get(arr[i]));
									}
								}else {//다 풀었을 때
									//마지막 문제 단어 틀렸을 경우 리스트에 넣어야 함
									if(!(answer.equals(WordBook.wbhash.get(jla.getText()))&& jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer)))) {
										wrongWord += jla.getText() + "\n";
										total++;
										JOptionPane.showMessageDialog(kormenuf, "틀렸습니다.\n" + count + "/" + total);
									} else {
										count++; 
										total++;
										JOptionPane.showMessageDialog(kormenuf, "정답입니다!!\n"+ count + " / " + total);
									}
									i = 0;
									long end = System.currentTimeMillis();

									int result = JOptionPane.showConfirmDialog(kormenuf, "시험 종료" + "\n" + "완료  : " + ( end - start )/1000 + "초!!" + "\n\n"+ "틀린 단어들 : " + "\n"+ wrongWord,
											"시험종료", JOptionPane.CLOSED_OPTION);

									wrongWord = "";
									count = 0;
									total = 0;

									if(result == JOptionPane.CLOSED_OPTION) {
										//사용자가 창 그냥 닫을 경우
										kormenuf.dispose();
									} else {
										kormenuf.dispose();
									}
								}
							}
						}
					});

					pCenter.add(tf);
					pCenter.add(btn);
					pCenter.setFont(font);

					kormenuf.add(jla, BorderLayout.CENTER);
					kormenuf.add(label, BorderLayout.NORTH);
					kormenuf.add(pCenter, BorderLayout.SOUTH);



					//영어 가리기 실행
					kormenuf.setVisible(true);
					kormenuf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							kormenuf.dispose();
						}
					});

				}else {//단어가 10문제 이상 저장 되어 있지 않을 때
					JOptionPane.showMessageDialog(testmenuf, "단어를 10개 이상 저장하세요!!");


				}
			}
		});   



		//영어 가리기---------------------------------------------------------------------
		engTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Iterator<String> map = wb.wbhash.keySet().iterator();


				while(map.hasNext()) {
					String key = map.next();
					al.add(key);

				}//while

				//단어가 10문제 이상 저장 되어 있을 때
				if(al.size() >= 10) {
					engmenuf = new Frame("영어 가리기");
					WordBook wb = new WordBook();

					AddWordButtonListener ab = new AddWordButtonListener();
					engmenuf.setBounds(400, 300, 700, 400);


					Panel pCenter = new Panel(); 
					pCenter.setBackground(Color.WHITE);

					JLabel label = new JLabel("알맞는 단어를 입력하세요");
					label.setHorizontalAlignment(SwingConstants.CENTER);

					String path = "C:\\yujin\\English\\WordBook.txt";
					File f = new File(path);

					FileInputStream fis = null;
					ObjectInputStream ois = null;
					String str = "";

					//한글 문제 화면에 띄우기
					jla = null;
					long start = System.currentTimeMillis();

					//답 입력
					TextField tf = new TextField(20);
					Button btn = new Button("입력");


					//arr배열에 키값 범위 안의 랜덤 숫자 안겹치게 넣기
					outer : for(int i = 0; i< 10;) {
						arr[i] = new Random().nextInt(10);
						for(int j = 0; j<i; j++) {
							if(arr[i] == arr[j]) {
								
								continue outer;
							}
						}//inner for
						i++;
					}//outer for
					
				
					//한글문제 랜덤 출제
					jla = new JLabel(wb.wbhash.get(al.get(arr[i])));
					jla.setHorizontalAlignment(SwingConstants.CENTER);
					jla.setFont(new Font("serif", Font.BOLD, 80));



					//버튼 감지 --------------------------------------------------------------
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//맞틀 판별
							answer = tf.getText();
							i++;

							for(int i = 0 ; i< 10;i++) {
								System.out.println(arr[i]);
							}
							//i가 10문제 다 안풀었을 경우
							if(i<10) {
								if(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText()))) {
									//답 맞췄을때
									count++;
									total++;

									tf.setText("");

									JOptionPane.showMessageDialog(engmenuf, "정답입니다!!\n"+ count + " / " + total);

									jla.setText(wb.wbhash.get(al.get(arr[i])));
								} else {
									//틀렸을 때
									total++;
									tf.setText("");

									JOptionPane.showMessageDialog(engmenuf, "틀렸습니다.\n"+count + " / " + total);
									wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";
									jla.setText(wb.wbhash.get(al.get(arr[i])));
								}
							} else {//10문제 다 풀었을 경우
								//마지막문제 단어 틀렸을 경우 리스트에 넣어야 된다.
								if(!(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText())))) {
									wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";                        
									total++;
									JOptionPane.showMessageDialog(engmenuf, "틀렸습니다.\n"+count + " / " + total);
								} else {
									count++; total++;
									JOptionPane.showMessageDialog(engmenuf, "정답입니다!!\n"+ count + " / " + total);
								}
								i = 0;
								long end = System.currentTimeMillis();
								int result = JOptionPane.showConfirmDialog(engmenuf, 
										"시험 종료" + "\n"+ "완료 : " + (end - start )/1000 + "초!"+"\n\n"+ "틀린 단어들 : " + "\n"+ wrongWord,
										"시험종료", JOptionPane.CLOSED_OPTION);
								wrongWord = "";
								count = 0; total = 0;
								if(result == JOptionPane.CLOSED_OPTION) {
									//사용자가 창 그냥 닫을 경우
									engmenuf.dispose();
								} else {
									engmenuf.dispose();
								}
							}
						}
					});

					//엔터값 감지------------------------------------------------------------------------
					tf.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {

							if(e.getKeyChar() == KeyEvent.VK_ENTER) {
								//맞틀 판별
								answer = tf.getText();
								i++;

								for(int i = 0 ; i< 10;i++) {
									System.out.println(arr[i]);
								}
								//i가 10문제 다 안풀었을 경우
								if(i<10) {
									if(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText()))) {
										//답 맞췄을때
										count++;
										total++;

										tf.setText("");

										JOptionPane.showMessageDialog(engmenuf, "정답입니다!!\n"+ count + " / " + total);

										jla.setText(wb.wbhash.get(al.get(arr[i])));
									} else {
										//틀렸을 때
										total++;
										tf.setText("");

										JOptionPane.showMessageDialog(engmenuf, "틀렸습니다.\n"+count + " / " + total);
										wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";
										jla.setText(wb.wbhash.get(al.get(arr[i])));
									}
								} else {//10문제 다 풀었을 경우
									//마지막문제 단어 틀렸을 경우 리스트에 넣어야 된다.
									if(!(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText())))) {
										wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";                        
										total++;
										JOptionPane.showMessageDialog(engmenuf, "틀렸습니다.\n"+count + " / " + total);
									} else {
										count++; total++;
										JOptionPane.showMessageDialog(engmenuf, "정답입니다!!\n"+ count + " / " + total);
									}
									i = 0;
									long end = System.currentTimeMillis();
									int result = JOptionPane.showConfirmDialog(engmenuf, 
											"시험 종료" + "\n"+ "완료 : " + (end - start )/1000 + "초!"+"\n\n"+ "틀린 단어들 : " + "\n"+ wrongWord,
											"시험종료", JOptionPane.CLOSED_OPTION);
									wrongWord = "";
									count = 0; total = 0;
									if(result == JOptionPane.CLOSED_OPTION) {
										//사용자가 창 그냥 닫을 경우
										engmenuf.dispose();
									} else {
										engmenuf.dispose();
									}
								}
							}
						}
					});



					pCenter.add(tf);
					pCenter.add(btn);
					pCenter.setFont(font);

					engmenuf.add(jla, BorderLayout.CENTER);
					engmenuf.add(label, BorderLayout.NORTH);
					engmenuf.add(pCenter, BorderLayout.SOUTH);



					//영어 가리기 실행
					engmenuf.setVisible(true);
					engmenuf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							engmenuf.dispose();
						}
					});

				}
				else {//단어가 10문제 이상 저장 되어 있지 않을 때
					JOptionPane.showMessageDialog(testmenuf, "단어를 10개 이상 저장하세요!!");

				}	

			}

		});   




		//home버튼

		backHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testmenuf.dispose();
			}
		});


		//실행
		testmenuf.add(korTest);
		testmenuf.add(engTest);
		testmenuf.add(backHome);
		testmenuf.add(j1);

		testmenuf.setVisible(true);

		//종료
		testmenuf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}  
