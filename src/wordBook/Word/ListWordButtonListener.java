package wordBook.Word;
import wordBook.*;
import wordBook.Game.ModiWordListener;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class ListWordButtonListener implements ActionListener {
	WordBook wb;
	ModiWordListener awbListener = new ModiWordListener();
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Frame wordListf = new Frame("단어보기");
		WordBook wb = new WordBook();
		int size = wb.getSize();

		wordListf.setBounds(500, 140, 600, 300);

		//상단
		Panel pNorth = new Panel();


		Label q1 = new Label("단어 개수 : "+size);

		Font font = new Font("궁서", Font.BOLD, 15);

		q1.setBackground(Color.WHITE);
		q1.setFont(font);


		// 중단 (키 값)
		//System.out.println(wb.wbhash.size());

		TextArea keyTa = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		keyTa.setBackground(Color.WHITE);
		keyTa.setEditable(false); //ta를 직접 수정 불가

		// 서쪽단 (벨류 값)
		TextArea valueTa = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		valueTa.setBackground(Color.WHITE);
		valueTa.setEditable(false); //ta를 직접 수정 불가

		///////////ta에 wb.wbhash불러와서 읽기//////////////////////
		String path = "C:\\yujin\\English\\WordBook.txt";
		File f = new File(path);

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String keyStr = "";
		String valueStr = "";

		if(f.exists()) {
			//물리적인 경로 존재하면 로드작업 수행
			try {
				fis = new FileInputStream(path);
				ois = new ObjectInputStream(fis);

				wb.wbhash = (HashMap<String, String>)ois.readObject();
				// 키값만 볼때
				//Set keyset = wb.wbhash.keySet(); 
				//str += keyset;
				//str += wb.wbhash;
				//str += wb.key;
				//ta.setText(str);
				Iterator<String> map = wb.wbhash.keySet().iterator();
				while(map.hasNext()) {
					wb.key = map.next();
					wb.value = wb.wbhash.get(wb.key);
					keyStr += wb.key+"\n";
					valueStr += wb.value+"\n";
					keyTa.setText(keyStr);
					valueTa.setText(valueStr);
					
				}
				
				System.out.println("로드 성공");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

		//////////////////////////////

		//남쪽단
		Panel pSouth = new Panel();
		pSouth.setBackground(Color.LIGHT_GRAY);
		Button btnModify = new Button("수정");
		Button btnDelete = new Button("삭제");

		pSouth.add(btnDelete);
		pSouth.add(btnModify);
		/////////////////////////////
		// 수정 버튼 클릭시
		btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame modif = new Frame("수정");
				modif.setBounds(50, 50, 400, 200);

				// 북쪽
				Panel pNorth = new Panel();
				Label q1 = new Label("수정할 단어를 적어주세요");
				Font font = new Font("궁서", Font.BOLD, 15);

				q1.setBackground(Color.CYAN);
				q1.setFont(font);

				pNorth.add(q1);

				// 중앙
				Panel pMiddle = new Panel();
				TextField modiTf = new TextField(20);
				modiTf.setBackground(Color.WHITE);
				pMiddle.add(modiTf);

				// 하단
				Panel pSouth = new Panel();
				Button modiBtn = new Button("입력");
				modiBtn.setEnabled(false); //입력버튼 비활성화
				pSouth.add(modiBtn);
				// 텍스트 필드에 값이 한글자라도 들어왔을때 입력 버튼 활성화
				modiTf.addTextListener(new TextListener() {

					@Override
					public void textValueChanged(TextEvent e) {
						if(modiTf.getText().trim().equals("")) {
							modiBtn.setEnabled(false);
						}else {
							modiBtn.setEnabled(true);
						}
					}
				});

				// 엔터치면 입력으로 넘어감
				modiTf.addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							modiBtn.requestFocus();
						}
					}
				});

				// 클릭으로 입력버튼 눌렸을 떄
				modiBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e1) {
						if(wb.wbhash.containsKey(modiTf.getText().trim())) {
							wb.wbhash.remove((Object)modiTf.getText().trim());
							WordWriter ww = new WordWriter(wb);
							modif.dispose();
							wordListf.dispose();
							System.out.println(wb.wbhash);
							// 단어수정 프레임을 킨다
							if(!modif.isVisible()) {
								awbListener.actionPerformed(e);
							}
						}else {
							JOptionPane.showMessageDialog(modif, "존재하지 않는 단어입니다.");
							modiTf.setText("");
							modiTf.requestFocus();
						}
					}
				});

				// 엔터로 입력버튼 눌렀을 떄
				modiBtn.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							if(wb.wbhash.containsKey(modiTf.getText().trim())) {
								wb.wbhash.remove((Object)modiTf.getText().trim());
								WordWriter ww = new WordWriter(wb);
								modif.dispose();
								wordListf.dispose();
								System.out.println(wb.wbhash);
								// 단어수정 프레임을 킨다
								
								awbListener.actionPerformed(e);
							}else {
								JOptionPane.showMessageDialog(modif, "존재하지 않는 단어입니다.");
								modiTf.setText("");
								modiTf.requestFocus();
							}
						}
					}
				});



				modif.add(pNorth, BorderLayout.NORTH);
				modif.add(pMiddle, BorderLayout.CENTER);
				modif.add(pSouth, BorderLayout.SOUTH);

				modif.setVisible(true);
				modif.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						modif.dispose();
					}
				});
			}
		});
		///////////////////////////////
		// 삭제 버튼 클릭시
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame delef = new Frame("삭제");
				delef.setBounds(50, 50, 400, 200);

				// 북쪽
				Panel pNorth = new Panel();
				Label q2 = new Label("삭제할 단어를 적어주세요");
				Font font = new Font("궁서", Font.BOLD, 15);

				q2.setBackground(Color.CYAN);
				q2.setFont(font);

				pNorth.add(q2);

				// 중앙
				Panel pMiddle = new Panel();
				TextField deleTf = new TextField(20);
				deleTf.setBackground(Color.WHITE);
				pMiddle.add(deleTf);

				// 하단
				Panel pSouth = new Panel();
				Button deleBtn = new Button("입력");
				deleBtn.setEnabled(false); //입력버튼 비활성화
				pSouth.add(deleBtn);

				// 텍스트 필드에 값이 한글자라도 들어왔을때 입력 버튼 활성화
				deleTf.addTextListener(new TextListener() {

					@Override
					public void textValueChanged(TextEvent e) {
						if(deleTf.getText().trim().equals("")) {
							deleBtn.setEnabled(false);
						}else {
							deleBtn.setEnabled(true);
						}
					}
				});

				// 엔터치면 입력으로 넘어감
				deleTf.addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							deleBtn.requestFocus();
						}
					}
				});

				// 엔터 눌러서 끌 때
				deleBtn.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							if(wb.wbhash.containsKey(deleTf.getText().trim())) {
								wb.wbhash.remove((Object)deleTf.getText().trim());
								JOptionPane.showMessageDialog(delef, "삭제가 완료되었습니다.");
								delef.dispose();
								wordListf.dispose();
								// 삭제된 wb.wbhash를 다시 업로드 시켜야됨
								WordWriter ww = new WordWriter(wb);
							}else {
								JOptionPane.showMessageDialog(delef, "존재하지 않는 단어입니다.");
								deleTf.setText("");
								deleTf.requestFocus();
							}
						}
					}
				});

				// 입력버튼 클릭 했을 떄
				deleBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(wb.wbhash.containsKey(deleTf.getText().trim())) {
							wb.wbhash.remove((Object)deleTf.getText().trim());
							JOptionPane.showMessageDialog(delef, "삭제가 완료되었습니다.");
							delef.dispose();
							wordListf.dispose();
							// 삭제된 wb.wbhash를 다시 업로드 시켜야됨
							WordWriter ww2 = new WordWriter(wb);
						}else {
							JOptionPane.showMessageDialog(delef, "존재하지 않는 단어입니다.");
							deleTf.setText("");
							deleTf.requestFocus();
						}
					}
				});

				delef.add(pNorth, BorderLayout.NORTH);
				delef.add(pMiddle, BorderLayout.CENTER);
				delef.add(pSouth, BorderLayout.SOUTH);

				delef.setVisible(true);
				delef.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						delef.dispose();
					}
				});
			}
		});
		////////////////////////////////
		wordListf.add(q1,BorderLayout.NORTH);
		wordListf.add(keyTa,BorderLayout.WEST);
		wordListf.add(valueTa,BorderLayout.CENTER);
		wordListf.add(pSouth, BorderLayout.SOUTH);

		wordListf.setVisible(true);
		wordListf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				wordListf.dispose();
			}
		});
	}
}
