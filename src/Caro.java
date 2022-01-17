import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Caro extends JFrame implements ActionListener{

	JButton[][] buttons = new JButton[3][3];
	int mark = 1;

	Caro() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3,3));
		this.setSize(500,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(new Color(0xabf5a2));
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFocusable(false);
				buttons[i][j].setFont(new Font("NewellsHand", Font.CENTER_BASELINE, 100));
				this.add(buttons[i][j]);
			}
		}

		this.setVisible(true);
	}

	private boolean checkWin(int x, int y, String s) {
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for(int i = 0; i < 3; i++) {
			if(buttons[x][i].getText() == s) {
				count1++;
			}
			if(buttons[i][y].getText() == s) {
				count2++;
			}
			if(buttons[i][i].getText() == s) {
				count3++;
			}
		}
		if(count1 == 3 || count2 == 3 || count3 == 3) return true;
		if(x + y == 2 &&
			buttons[0][2].getText() == s &&
			buttons[1][1].getText() == s &&
			buttons[2][0].getText() == s
		) return true;
		return false;
	}
	
	private boolean hasValue(int i, int j) {
		return buttons[i][j].getText() != "";
	}

	private boolean isDraw() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(!hasValue(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	private void newGame() {
		int response = JOptionPane.showConfirmDialog(null, "do you want to play another game?", "Caro", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			new Caro();
			this.dispose();
		} else {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String x = mark == 1 ? "X" : "O";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				mark *= -1;
				if(e.getSource() == buttons[i][j]) {
					if(hasValue(i, j)) return;
					buttons[i][j].setText(x);
					if(checkWin(i,j,x) == true) {
						JOptionPane.showMessageDialog(this, x + " WIN");
						newGame();
					}
					else if(isDraw()) {
						JOptionPane.showMessageDialog(null, "DRAW");
						newGame();
					}
				}
			}
		}
	}
}
