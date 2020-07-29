package wordBook.Game;
import wordBook.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoxGameListener implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      //프레임 및 패널
      Frame f = new Frame();
      Panel p1 = new Panel(){
    	  Image background = new ImageIcon("무민_박스게임.jpg").getImage();
    	  public void paint(Graphics g) {
    		  g.drawImage(background, 0, 0, null);
    	  }
      };
      Panel p2 = new Panel();
      Panel p3 = new Panel();
      Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
      Font font2 = new Font("돋움", Font.BOLD, 16);
      f.setBackground(Color.WHITE);

 





      //프레임 셋팅
      f.setBounds(500, 140, 500, 700);
      f.setLayout(null);

      p3.setBounds(15, 30, 470, 40);
      p3.setVisible(true);
      Label lb = new Label("입력 칸에 뜻을 적고 영어 박스를 누르세요");
      lb.setFont(font);
      lb.setVisible(true);
      p3.add(lb);



      p1.setBounds(15, 70, 470, 470);
      p1.setLayout(new GridLayout(4,4,5,5));


      p1.setBackground(null);


      MakeString makeString = new MakeString();
      WordBook wb = new WordBook();
      ArrayList<String> al = new ArrayList<>();
      Iterator<String> map = wb.wbhash.keySet().iterator();
      while(map.hasNext()) {
         String key = map.next();
         //key값들을 arraylist에 넣음
         al.add(key);
      }
      System.out.println(al);

      if(al.size() <=15) {
         JOptionPane.showMessageDialog(f, "단어가 충분하지 못해 게임이 실행안됨. 최소 16개는 저장해야 됨.");
         f.dispose();
      }

      //키 값을 랜덤으로 불러오기 위한 배열 만들기
      int[] arr = new int[al.size()];
      outer : for(int i = 0; i<al.size();) {
         arr[i] = new Random().nextInt(al.size());
         for(int j= 0; j<i;j++) {
            if(arr[i] == arr[j]) {
               continue outer;
            }
         }
         i++;
      }



      Button btn1 = new Button(al.get(arr[0])); p1.add(btn1);
      Button btn2 = new Button(al.get(arr[1])); p1.add(btn2);
      Button btn3 = new Button(al.get(arr[2])); p1.add(btn3);
      Button btn4 = new Button(al.get(arr[3])); p1.add(btn4);

      Button btn5 = new Button(al.get(arr[4])); p1.add(btn5);
      Button btn6 = new Button(al.get(arr[5])); p1.add(btn6);
      Button btn7 = new Button(al.get(arr[6])); p1.add(btn7);
      Button btn8 = new Button(al.get(arr[7])); p1.add(btn8);

      Button btn9 = new Button(al.get(arr[8])); p1.add(btn9);
      Button btn10 = new Button(al.get(arr[9])); p1.add(btn10);
      Button btn11 = new Button(al.get(arr[10])); p1.add(btn11);
      Button btn12 = new Button(al.get(arr[11])); p1.add(btn12);

      Button btn13 = new Button(al.get(arr[12])); p1.add(btn13);
      Button btn14 = new Button(al.get(arr[13])); p1.add(btn14);
      Button btn15 = new Button(al.get(arr[14])); p1.add(btn15);
      Button btn16 = new Button(al.get(arr[15])); p1.add(btn16);




      p2.setBounds(15, 570, 480, 250);
      p2.setLayout(null);

      JTextField tf1 = new JTextField();
      tf1.setBounds(170, 10, 100, 30);
      tf1.setFont(font2);

      //시작부분
      long start = System.currentTimeMillis();

      SimpleDateFormat format1 = new SimpleDateFormat ( "mm:ss");
      Date time = new Date();

      String time1 = format1.format(time);

      ///////////////////
      btn1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn1.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn1);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn2.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn2.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn2);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn3.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn3.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn3);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn4.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn4.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn4);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn5.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn5.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn5);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn6.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn6.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn6);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn7.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn7.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn7);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn8.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn8.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn8);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn9.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn9.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn9);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn10.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn10.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn10);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn11.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn11.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn11);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn12.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn12.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn12);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn13.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn13.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn13);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn14.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn14.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn14);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");

               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn15.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn15.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn15);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");

               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );
            }
         }
      });

      btn16.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<al.size(); i++) {

               if(tf1.getText().equals(wb.wbhash.get(al.get(i))) && al.get(i).equals(btn16.getActionCommand())) {
                  makeString.setStr(al.get(i));
                  makeString.number++;
               }
            }
            if(e.getActionCommand().equals(makeString.str)) {
               p1.remove(btn16);
               tf1.setText("");
               tf1.requestFocus();
            }
            if(makeString.number == 16) {
               JOptionPane.showMessageDialog(f, "박스를 다 없앴습니다!!!");
               //끝

               long end = System.currentTimeMillis();
               JOptionPane.showMessageDialog(f, "완료  : " + ( end - start )/1000 + "초!!"  );

            }
         }
      });

      


      p2.add(tf1);

      f.add(p1);
      f.add(p2);
      f.add(p3);

      f.setVisible(true);


      f.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            f.dispose();
         }
      });
   }

	}