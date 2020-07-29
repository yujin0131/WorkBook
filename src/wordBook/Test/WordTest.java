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
	int count = 0; //���� ����  ->���� ���� �� �� ����
	int total = 0; //��ü ���� ->���� �� �� ���� �� ����
	int i = 0;
	JLabel jla;

	String wrongWord = "";


	ArrayList<String> al = new ArrayList<>();
	int[] arr = new int[10];

	@Override
	public void actionPerformed(ActionEvent e) {
		testmenuf = new Frame("�ܾ� ����");
		testmenuf.setLayout(null);
		testmenuf.setBounds(500, 140, 420, 750);

		Button korTest = new Button("�ѱ� ������");
		Button engTest = new Button("���� ������");
		Button backHome = new Button("HOME");

		ImageIcon img = new ImageIcon("����_�׽�Ʈ.jpg");
		JLabel j1 = new JLabel(img);
		j1.setBounds(0,0,450,750);

		korTest.setBounds(85, 60, 250, 70);
		engTest.setBounds(85, 150, 250, 70);
		backHome.setBounds(85, 620, 250, 70);

		//�ѱ� ������---------------------------------------------------------------------
		korTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator<String> map = wb.wbhash.keySet().iterator();

				//al ��̸���Ʈ�� Ű�� �ֱ� -> �ߺ����� �� �� �ִ�
				while(map.hasNext()) {
					String key = map.next();
					al.add(key);

				}//while

				//�ܾ 10���� �̻� ���� �Ǿ� ���� ��
				if(al.size() >= 10) {
					kormenuf = new Frame("�ѱ� ������");
					WordBook wb = new WordBook();


					AddWordButtonListener ab = new AddWordButtonListener();
					kormenuf.setBounds(400, 300, 700, 400);

					Panel pCenter = new Panel(); 
					pCenter.setBackground(Color.WHITE);

					JLabel label = new JLabel("�˸´� ���� �Է��ϼ���");
					label.setHorizontalAlignment(SwingConstants.CENTER);


					String path = "C:\\yujin\\English\\WordBook.txt";
					File f = new File(path);

					FileInputStream fis = null;
					ObjectInputStream ois = null;
					String str = "";

					//���� ���� ȭ�鿡 ����
					jla = null;
					long start = System.currentTimeMillis();

					//�� �Է�
					TextField tf = new TextField(20);
					Button btn = new Button("�Է�");



					//arr�迭�� Ű�� ���� ���� ���� ���� �Ȱ�ġ�� �ֱ�
					outer : for(int i = 0; i< 10;) {
						arr[i] = new Random().nextInt(10);
						for(int j = 0; j<i; j++) {
							if(arr[i] == arr[j]) {
								
								continue outer;
							}
						}//inner for
						i++;
					}//outer for

					//����� ���� ����
					
					jla = new JLabel(al.get(arr[i]));
					jla.setHorizontalAlignment(SwingConstants.CENTER);
					jla.setFont(new Font("serif", Font.BOLD, 80));

					//�ֿܼ� ������ �� ���
					//System.out.println("test : " + wb.wbhash.get(al.get(arr[i])));


					//��ư ���� --------------------------------------------------------------
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//��Ʋ �Ǻ�
							answer = tf.getText();
							i++;

							//i�� 10���� �� ��Ǯ���� ���
							if(i < 10) {
								//���� ���� ��
								if(answer.equals(WordBook.wbhash.get(jla.getText())) && jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer))) {

									count++;
									total++;

									tf.setText("");		
									tf.requestFocus();
									JOptionPane.showMessageDialog(kormenuf, "�����Դϴ�!!\n"+ count + " / " + total);

									jla.setText(al.get(arr[i]));

								} else {
									//Ʋ���� ��
									total++;
									tf.setText("");

									wrongWord += jla.getText() + "\n";
									JOptionPane.showMessageDialog(kormenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);


									jla.setText(al.get(arr[i]));
								}
							}else {//�� Ǯ���� ��
								//������ ���� �ܾ� Ʋ���� ��� ����Ʈ�� �־�� ��
								if(!(answer.equals(WordBook.wbhash.get(jla.getText()))&& jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer)))) {
									wrongWord += jla.getText() + "\n";
									total++;
									JOptionPane.showMessageDialog(kormenuf, "Ʋ�Ƚ��ϴ�.\n" + count + "/" + total);
								} else {
									count++; 
									total++;
									JOptionPane.showMessageDialog(kormenuf, "�����Դϴ�!!\n"+ count + " / " + total);
								}
								i = 0;
								
								//���� �ð� ���
								long end = System.currentTimeMillis();

								int result = JOptionPane.showConfirmDialog(kormenuf, "���� ����" + "\n" + "�Ϸ�  : " + ( end - start )/1000 + "��!!"
								+ "\n\n"+ "Ʋ�� �ܾ�� : " + "\n"+ wrongWord, "��������", JOptionPane.CLOSED_OPTION);

								wrongWord = "";
								count = 0;
								total = 0;

								if(result == JOptionPane.CLOSED_OPTION) {
									//����ڰ� â �׳� ���� ���
									kormenuf.dispose();
								} else {
									kormenuf.dispose();
								}
							}
						}
					});


					//���Ͱ� ����------------------------------------------------------------------------
					tf.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							if(e.getKeyChar() == KeyEvent.VK_ENTER) {
								//��Ʋ �Ǻ�
								answer = tf.getText();
								i++;

								//i�� 10���� �� ��Ǯ���� ���
								if(i < 10) {
									//���� ���� ��
									if(answer.equals(WordBook.wbhash.get(jla.getText())) && jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer))) {

										count++;
										total++;

										tf.setText("");		
										tf.requestFocus();
										JOptionPane.showMessageDialog(kormenuf, "�����Դϴ�!!\n"+ count + " / " + total);

										jla.setText(al.get(arr[i]));

									} else {
										//Ʋ���� ��
										total++;
										tf.setText("");

										wrongWord += jla.getText() + "\n";
										JOptionPane.showMessageDialog(kormenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);


										jla.setText(al.get(arr[i]));
									}
								}else {//�� Ǯ���� ��
									//������ ���� �ܾ� Ʋ���� ��� ����Ʈ�� �־�� ��
									if(!(answer.equals(WordBook.wbhash.get(jla.getText()))&& jla.getText().equals(WordBook.getKeyFromValue(wb.wbhash, answer)))) {
										wrongWord += jla.getText() + "\n";
										total++;
										JOptionPane.showMessageDialog(kormenuf, "Ʋ�Ƚ��ϴ�.\n" + count + "/" + total);
									} else {
										count++; 
										total++;
										JOptionPane.showMessageDialog(kormenuf, "�����Դϴ�!!\n"+ count + " / " + total);
									}
									i = 0;
									long end = System.currentTimeMillis();

									int result = JOptionPane.showConfirmDialog(kormenuf, "���� ����" + "\n" + "�Ϸ�  : " + ( end - start )/1000 + "��!!" + "\n\n"+ "Ʋ�� �ܾ�� : " + "\n"+ wrongWord,
											"��������", JOptionPane.CLOSED_OPTION);

									wrongWord = "";
									count = 0;
									total = 0;

									if(result == JOptionPane.CLOSED_OPTION) {
										//����ڰ� â �׳� ���� ���
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



					//���� ������ ����
					kormenuf.setVisible(true);
					kormenuf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							kormenuf.dispose();
						}
					});

				}else {//�ܾ 10���� �̻� ���� �Ǿ� ���� ���� ��
					JOptionPane.showMessageDialog(testmenuf, "�ܾ 10�� �̻� �����ϼ���!!");


				}
			}
		});   



		//���� ������---------------------------------------------------------------------
		engTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Iterator<String> map = wb.wbhash.keySet().iterator();


				while(map.hasNext()) {
					String key = map.next();
					al.add(key);

				}//while

				//�ܾ 10���� �̻� ���� �Ǿ� ���� ��
				if(al.size() >= 10) {
					engmenuf = new Frame("���� ������");
					WordBook wb = new WordBook();

					AddWordButtonListener ab = new AddWordButtonListener();
					engmenuf.setBounds(400, 300, 700, 400);


					Panel pCenter = new Panel(); 
					pCenter.setBackground(Color.WHITE);

					JLabel label = new JLabel("�˸´� �ܾ �Է��ϼ���");
					label.setHorizontalAlignment(SwingConstants.CENTER);

					String path = "C:\\yujin\\English\\WordBook.txt";
					File f = new File(path);

					FileInputStream fis = null;
					ObjectInputStream ois = null;
					String str = "";

					//�ѱ� ���� ȭ�鿡 ����
					jla = null;
					long start = System.currentTimeMillis();

					//�� �Է�
					TextField tf = new TextField(20);
					Button btn = new Button("�Է�");


					//arr�迭�� Ű�� ���� ���� ���� ���� �Ȱ�ġ�� �ֱ�
					outer : for(int i = 0; i< 10;) {
						arr[i] = new Random().nextInt(10);
						for(int j = 0; j<i; j++) {
							if(arr[i] == arr[j]) {
								
								continue outer;
							}
						}//inner for
						i++;
					}//outer for
					
				
					//�ѱ۹��� ���� ����
					jla = new JLabel(wb.wbhash.get(al.get(arr[i])));
					jla.setHorizontalAlignment(SwingConstants.CENTER);
					jla.setFont(new Font("serif", Font.BOLD, 80));



					//��ư ���� --------------------------------------------------------------
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//��Ʋ �Ǻ�
							answer = tf.getText();
							i++;

							for(int i = 0 ; i< 10;i++) {
								System.out.println(arr[i]);
							}
							//i�� 10���� �� ��Ǯ���� ���
							if(i<10) {
								if(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText()))) {
									//�� ��������
									count++;
									total++;

									tf.setText("");

									JOptionPane.showMessageDialog(engmenuf, "�����Դϴ�!!\n"+ count + " / " + total);

									jla.setText(wb.wbhash.get(al.get(arr[i])));
								} else {
									//Ʋ���� ��
									total++;
									tf.setText("");

									JOptionPane.showMessageDialog(engmenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);
									wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";
									jla.setText(wb.wbhash.get(al.get(arr[i])));
								}
							} else {//10���� �� Ǯ���� ���
								//���������� �ܾ� Ʋ���� ��� ����Ʈ�� �־�� �ȴ�.
								if(!(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText())))) {
									wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";                        
									total++;
									JOptionPane.showMessageDialog(engmenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);
								} else {
									count++; total++;
									JOptionPane.showMessageDialog(engmenuf, "�����Դϴ�!!\n"+ count + " / " + total);
								}
								i = 0;
								long end = System.currentTimeMillis();
								int result = JOptionPane.showConfirmDialog(engmenuf, 
										"���� ����" + "\n"+ "�Ϸ� : " + (end - start )/1000 + "��!"+"\n\n"+ "Ʋ�� �ܾ�� : " + "\n"+ wrongWord,
										"��������", JOptionPane.CLOSED_OPTION);
								wrongWord = "";
								count = 0; total = 0;
								if(result == JOptionPane.CLOSED_OPTION) {
									//����ڰ� â �׳� ���� ���
									engmenuf.dispose();
								} else {
									engmenuf.dispose();
								}
							}
						}
					});

					//���Ͱ� ����------------------------------------------------------------------------
					tf.addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {

							if(e.getKeyChar() == KeyEvent.VK_ENTER) {
								//��Ʋ �Ǻ�
								answer = tf.getText();
								i++;

								for(int i = 0 ; i< 10;i++) {
									System.out.println(arr[i]);
								}
								//i�� 10���� �� ��Ǯ���� ���
								if(i<10) {
									if(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText()))) {
										//�� ��������
										count++;
										total++;

										tf.setText("");

										JOptionPane.showMessageDialog(engmenuf, "�����Դϴ�!!\n"+ count + " / " + total);

										jla.setText(wb.wbhash.get(al.get(arr[i])));
									} else {
										//Ʋ���� ��
										total++;
										tf.setText("");

										JOptionPane.showMessageDialog(engmenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);
										wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";
										jla.setText(wb.wbhash.get(al.get(arr[i])));
									}
								} else {//10���� �� Ǯ���� ���
									//���������� �ܾ� Ʋ���� ��� ����Ʈ�� �־�� �ȴ�.
									if(!(jla.getText().equals(WordBook.wbhash.get(answer)) && answer.equals(wb.getKeyFromValue(wb.wbhash, jla.getText())))) {
										wrongWord += wb.getKeyFromValue(wb.wbhash, jla.getText())+ "\n";                        
										total++;
										JOptionPane.showMessageDialog(engmenuf, "Ʋ�Ƚ��ϴ�.\n"+count + " / " + total);
									} else {
										count++; total++;
										JOptionPane.showMessageDialog(engmenuf, "�����Դϴ�!!\n"+ count + " / " + total);
									}
									i = 0;
									long end = System.currentTimeMillis();
									int result = JOptionPane.showConfirmDialog(engmenuf, 
											"���� ����" + "\n"+ "�Ϸ� : " + (end - start )/1000 + "��!"+"\n\n"+ "Ʋ�� �ܾ�� : " + "\n"+ wrongWord,
											"��������", JOptionPane.CLOSED_OPTION);
									wrongWord = "";
									count = 0; total = 0;
									if(result == JOptionPane.CLOSED_OPTION) {
										//����ڰ� â �׳� ���� ���
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



					//���� ������ ����
					engmenuf.setVisible(true);
					engmenuf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							engmenuf.dispose();
						}
					});

				}
				else {//�ܾ 10���� �̻� ���� �Ǿ� ���� ���� ��
					JOptionPane.showMessageDialog(testmenuf, "�ܾ 10�� �̻� �����ϼ���!!");

				}	

			}

		});   




		//home��ư

		backHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testmenuf.dispose();
			}
		});


		//����
		testmenuf.add(korTest);
		testmenuf.add(engTest);
		testmenuf.add(backHome);
		testmenuf.add(j1);

		testmenuf.setVisible(true);

		//����
		testmenuf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}  
