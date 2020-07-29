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

		Frame wordListf = new Frame("�ܾ��");
		WordBook wb = new WordBook();
		int size = wb.getSize();

		wordListf.setBounds(500, 140, 600, 300);

		//���
		Panel pNorth = new Panel();


		Label q1 = new Label("�ܾ� ���� : "+size);

		Font font = new Font("�ü�", Font.BOLD, 15);

		q1.setBackground(Color.WHITE);
		q1.setFont(font);


		// �ߴ� (Ű ��)
		//System.out.println(wb.wbhash.size());

		TextArea keyTa = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		keyTa.setBackground(Color.WHITE);
		keyTa.setEditable(false); //ta�� ���� ���� �Ұ�

		// ���ʴ� (���� ��)
		TextArea valueTa = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		valueTa.setBackground(Color.WHITE);
		valueTa.setEditable(false); //ta�� ���� ���� �Ұ�

		///////////ta�� wb.wbhash�ҷ��ͼ� �б�//////////////////////
		String path = "C:\\yujin\\English\\WordBook.txt";
		File f = new File(path);

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String keyStr = "";
		String valueStr = "";

		if(f.exists()) {
			//�������� ��� �����ϸ� �ε��۾� ����
			try {
				fis = new FileInputStream(path);
				ois = new ObjectInputStream(fis);

				wb.wbhash = (HashMap<String, String>)ois.readObject();
				// Ű���� ����
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
				
				System.out.println("�ε� ����");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("�ε����");
			} finally {
				try {
					ois.close();
					fis.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else {
			//������ ��� �������� ���� ���( ��� �����ϰ� ���� ó�� ���� �� )
			System.out.println("���ο� �ܾ���� �����մϴ�.");
		}

		//////////////////////////////

		//���ʴ�
		Panel pSouth = new Panel();
		pSouth.setBackground(Color.LIGHT_GRAY);
		Button btnModify = new Button("����");
		Button btnDelete = new Button("����");

		pSouth.add(btnDelete);
		pSouth.add(btnModify);
		/////////////////////////////
		// ���� ��ư Ŭ����
		btnModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame modif = new Frame("����");
				modif.setBounds(50, 50, 400, 200);

				// ����
				Panel pNorth = new Panel();
				Label q1 = new Label("������ �ܾ �����ּ���");
				Font font = new Font("�ü�", Font.BOLD, 15);

				q1.setBackground(Color.CYAN);
				q1.setFont(font);

				pNorth.add(q1);

				// �߾�
				Panel pMiddle = new Panel();
				TextField modiTf = new TextField(20);
				modiTf.setBackground(Color.WHITE);
				pMiddle.add(modiTf);

				// �ϴ�
				Panel pSouth = new Panel();
				Button modiBtn = new Button("�Է�");
				modiBtn.setEnabled(false); //�Է¹�ư ��Ȱ��ȭ
				pSouth.add(modiBtn);
				// �ؽ�Ʈ �ʵ忡 ���� �ѱ��ڶ� �������� �Է� ��ư Ȱ��ȭ
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

				// ����ġ�� �Է����� �Ѿ
				modiTf.addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							modiBtn.requestFocus();
						}
					}
				});

				// Ŭ������ �Է¹�ư ������ ��
				modiBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e1) {
						if(wb.wbhash.containsKey(modiTf.getText().trim())) {
							wb.wbhash.remove((Object)modiTf.getText().trim());
							WordWriter ww = new WordWriter(wb);
							modif.dispose();
							wordListf.dispose();
							System.out.println(wb.wbhash);
							// �ܾ���� �������� Ų��
							if(!modif.isVisible()) {
								awbListener.actionPerformed(e);
							}
						}else {
							JOptionPane.showMessageDialog(modif, "�������� �ʴ� �ܾ��Դϴ�.");
							modiTf.setText("");
							modiTf.requestFocus();
						}
					}
				});

				// ���ͷ� �Է¹�ư ������ ��
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
								// �ܾ���� �������� Ų��
								
								awbListener.actionPerformed(e);
							}else {
								JOptionPane.showMessageDialog(modif, "�������� �ʴ� �ܾ��Դϴ�.");
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
		// ���� ��ư Ŭ����
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame delef = new Frame("����");
				delef.setBounds(50, 50, 400, 200);

				// ����
				Panel pNorth = new Panel();
				Label q2 = new Label("������ �ܾ �����ּ���");
				Font font = new Font("�ü�", Font.BOLD, 15);

				q2.setBackground(Color.CYAN);
				q2.setFont(font);

				pNorth.add(q2);

				// �߾�
				Panel pMiddle = new Panel();
				TextField deleTf = new TextField(20);
				deleTf.setBackground(Color.WHITE);
				pMiddle.add(deleTf);

				// �ϴ�
				Panel pSouth = new Panel();
				Button deleBtn = new Button("�Է�");
				deleBtn.setEnabled(false); //�Է¹�ư ��Ȱ��ȭ
				pSouth.add(deleBtn);

				// �ؽ�Ʈ �ʵ忡 ���� �ѱ��ڶ� �������� �Է� ��ư Ȱ��ȭ
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

				// ����ġ�� �Է����� �Ѿ
				deleTf.addKeyListener(new KeyAdapter() {

					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							deleBtn.requestFocus();
						}
					}
				});

				// ���� ������ �� ��
				deleBtn.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(e.getKeyChar() == KeyEvent.VK_ENTER) {
							if(wb.wbhash.containsKey(deleTf.getText().trim())) {
								wb.wbhash.remove((Object)deleTf.getText().trim());
								JOptionPane.showMessageDialog(delef, "������ �Ϸ�Ǿ����ϴ�.");
								delef.dispose();
								wordListf.dispose();
								// ������ wb.wbhash�� �ٽ� ���ε� ���Ѿߵ�
								WordWriter ww = new WordWriter(wb);
							}else {
								JOptionPane.showMessageDialog(delef, "�������� �ʴ� �ܾ��Դϴ�.");
								deleTf.setText("");
								deleTf.requestFocus();
							}
						}
					}
				});

				// �Է¹�ư Ŭ�� ���� ��
				deleBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(wb.wbhash.containsKey(deleTf.getText().trim())) {
							wb.wbhash.remove((Object)deleTf.getText().trim());
							JOptionPane.showMessageDialog(delef, "������ �Ϸ�Ǿ����ϴ�.");
							delef.dispose();
							wordListf.dispose();
							// ������ wb.wbhash�� �ٽ� ���ε� ���Ѿߵ�
							WordWriter ww2 = new WordWriter(wb);
						}else {
							JOptionPane.showMessageDialog(delef, "�������� �ʴ� �ܾ��Դϴ�.");
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
