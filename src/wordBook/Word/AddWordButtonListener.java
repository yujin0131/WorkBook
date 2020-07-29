package wordBook.Word;
import wordBook.*;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class AddWordButtonListener implements ActionListener{
	
	Frame addWordf = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		//key값 / 나중에 파일에서 읽어서 연결해야 한다.

		addWordf = new Frame("단어추가");
		WordBook wb = new WordBook();
		addWordf.setBounds(500, 140, 310, 200);
		addWordf.setLayout(null);

		HintTextField htf1 = new HintTextField("English");
		HintTextField htf2 = new HintTextField("Korean");
		htf1.setBounds(10, 40, 300, 30);
		htf2.setBounds(10, 75, 300, 30);
		htf1.addKeyListener(new KeyAdapter() {
			//엔터치면 다음 텍스트필드로 넘어감
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					htf2.requestFocus();
				}
			}
		});

		Button save = new Button("입력");
		save.setBounds(105, 120, 100, 60);
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WordLoader wl = new WordLoader(wb);
				
				if(wb.wbhash.containsKey(htf1.getText().trim())) {
					//존재하는 단어입니다.
					JOptionPane.showMessageDialog(addWordf, "존재하는 단어입니다.");
					htf1.setText("");
					htf2.setText("");
					htf1.requestFocus();
					return;
				}
				wb.setHash(htf1.getText().trim(), htf2.getText().trim());
				new WordWriter(wb);

				//팝업창으로 저장된거 확인 ---------------------
				if(wb != null) {
					JOptionPane.showMessageDialog(addWordf, "저장이 완료되었습니다.");
				}
				
				htf1.setText("");
				htf2.setText("");
				htf1.requestFocus();
			}
		});

		//키보드 enter 값 감지
		htf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {//키보드의 엔터값 감지
					WordLoader wl = new WordLoader(wb);
					
					if(wb.wbhash.containsKey(htf1.getText().trim())) {
						//존재하는 단어입니다.
						JOptionPane.showMessageDialog(addWordf, "존재하는 단어입니다.");
						htf1.setText("");
						htf2.setText("");
						htf1.requestFocus();
						return;
					}
					
					wb.setHash(htf1.getText(), htf2.getText());
					new WordWriter(wb);
					
					//팝업창으로 저장된거 확인 ---------------------
					if(wb != null) {
						JOptionPane.showMessageDialog(addWordf, "저장이 완료되었습니다.");
					}

					htf1.setText("");
					htf2.setText("");
					htf1.requestFocus();
					
				}

			}

		});

		addWordf.add(htf1);
		addWordf.add(htf2);
		addWordf.add(save);

		addWordf.setVisible(true);
		addWordf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				addWordf.dispose();
			}
		});
	}
	
}
