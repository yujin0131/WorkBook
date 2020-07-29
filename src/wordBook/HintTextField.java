package wordBook;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField {
	Font font = new Font(Font.SERIF, Font.BOLD, 18);
	Font lostFont = new Font(Font.SERIF, Font.ITALIC, 11);
	
	//텍스트필드에 머적을지 알려줌
	public HintTextField(String hint) {
		setText(hint);
		setFont(lostFont);
		setForeground(Color.GRAY);
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {
				if(getText().equals(hint)) {
					setText("");
					setFont(font);
					setForeground(Color.BLACK);
				} else {
					setText(getText());
					setFont(font);
					setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(getText().equals(hint)||getText().length() == 0) {
					setText(hint);
					setFont(lostFont);
					setForeground(Color.GRAY);
				} else {
					setText(getText());
					setFont(font);
					setForeground(Color.BLACK);
				}
			}
		});
	}
}
