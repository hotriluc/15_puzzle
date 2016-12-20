import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class Puzzle_Game extends JFrame {
	
	
	public static ArrayList<ProfileObj> prf = new ArrayList<ProfileObj>();
	private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
	private static Random Random_Pos_Generetor = new Random();
	private static int[][] num_array = new int[4][4];
	private javax.swing.Timer t;
	private static long mil;
	private JTextField txtUname;

	
	
	private static int get_userID(String uname) {
		int index = -1;
		for (int i = 0; i < prf.size(); i++) {
			if (prf.get(i).getName().equals(uname)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static void setTime(long m) {
		mil = m;

	}

	public static void setProgress(int[][] na) {
		num_array = na;
	}

	private static void prfAdd(String uname, long mil, int[][] num_array) throws IOException {

			Select.prf.add(new ProfileObj(uname, mil, num_array));
			Select.serialize();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Puzzle_Game window = new Puzzle_Game();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Puzzle_Game() {
		super("15_puzzle");
		setLocationRelativeTo(null);
		setTitle("15 Puzzle");
		init();
	}

	public void init() {

		JLabel Time_Label = new JLabel("");
		Time_Label.setBackground(Color.LIGHT_GRAY);
		Time_Label.setFont(new Font("Showcard Gothic", Font.ITALIC, 17));
		Time_Label.setHorizontalAlignment(SwingConstants.CENTER);

		DecimalFormat dc = new DecimalFormat("000");
		// Time_Label.setText(dc.format(minute) + ":" + dc.format(second));
		Time_Label.setText(dc.format(mil) + " sec");

		t = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Time_Label.setText(dc.format(mil) + " sec");
				mil++;

			}
		});
		
		setVisible(true);
	
		
		getContentPane().setBackground(SystemColor.menu);
		setBounds(200, 200, 458, 392);
		setResizable(false);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) d.getWidth();
		int h = (int) d.getHeight();
		setLocation((int) (w / 2 - getWidth() / 2), (int) (h / 2 - getHeight() / 2));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();

		getContentPane().setLayout(null);
		panel.setBounds(135, 22, 300, 300);
		panel.setBackground(SystemColor.inactiveCaption);
		container.add(panel);

		////////////////////////////////////////////////////////////////////
		JButton NewGameButton = new JButton("New Position");// New_game_button
		NewGameButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		NewGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mil = 0;
				Time_Label.setText(dc.format(mil) + " sec");

				t.start();

				panel.removeAll();
				container.repaint();

				Scramble();
				repaintField(true);

			}
		});

		/////////////////////////////////////////////////////////
		JButton ExitButton = new JButton("Main menu"); // Exit_button
		ExitButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.stop();

				repaintField(false);
				setVisible(false);
				MainMenu m = new MainMenu();
				m.setVisible(true);
			}
		});
		/////////////////////////////////////////////////////////
		NewGameButton.setBounds(10, 25, 115, 41);
		getContentPane().add(NewGameButton);

		ExitButton.setBounds(10, 207, 115, 41);
		getContentPane().add(ExitButton);

		Time_Label.setBounds(19, 236, 106, 75);
		getContentPane().add(Time_Label);

		JLabel lblUname = new JLabel("Save as: ");
		lblUname.setBounds(135, 336, 90, 14);
		getContentPane().add(lblUname);

		txtUname = new JTextField();
		txtUname.setBounds(191, 333, 171, 20);
		getContentPane().add(txtUname);
		txtUname.setColumns(10);
		////////////////////////////////////////////

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUname.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Enter a valid username!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						t.stop();
						prfAdd(txtUname.getText(), mil, num_array);
						txtUname.setText(null);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		btnSave.setBounds(372, 332, 63, 23);
		getContentPane().add(btnSave);
		//////////////////////////////////////

		JButton PauseButton = new JButton("Pause");
		PauseButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));

		PauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t.stop();
				repaintField(false);
				PauseButton.setVisible(false);

				JButton ResumeButton = new JButton("Resume");
				ResumeButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
				ResumeButton.setBounds(10, 77, 115, 41);

				getContentPane().add(ResumeButton);
				ResumeButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						ResumeButton.setVisible(false);
						t.start();
						repaintField(true);
						PauseButton.setVisible(true);
					}

				});

			}
		});

		PauseButton.setBounds(10, 77, 115, 41);
		getContentPane().add(PauseButton);
		/////////////////////////////////////

		repaintField(true);
	}

	public void Scramble() {

		int[] invariants = new int[16];
		do {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					num_array[i][j] = 0;
					invariants[i * 4 + j] = 0;
				}
			}

			for (int i = 1; i < 16; i++) {
				int k;
				int l;
				do {
					k = Random_Pos_Generetor.nextInt(4);
					l = Random_Pos_Generetor.nextInt(4);
				} while (num_array[k][l] != 0);
				num_array[k][l] = i;
				invariants[k * 4 + l] = i;
			}
		} while (!isSolvable(invariants));
		repaintField(true);
	}

	private boolean isSolvable(int[] invariants) {
		int row = 0;//текущая строка
		int parity = 0;
		int blankrow = 0;//строка с пустой ячейкой
		int gridwidth = (int) Math.sqrt(invariants.length);

		for (int i = 0; i < invariants.length; i++) {
			if (i % gridwidth == 0) {
				row++;
			}
			if (invariants[i] == 0) {
				blankrow = row;
				continue;
			}
			for (int j = i + 1; j < invariants.length; j++) {
				if (invariants[i] > invariants[j] && invariants[j] != 0) {
					parity++;
				}
			}

		}

		if (gridwidth % 2 == 0) {
			if (blankrow % 2 == 0) {
				return parity % 2 == 0;
			} else {
				return parity % 2 != 0; 
			}

		} else {
			return parity % 2 == 0;
		}

	}

	public void repaintField(boolean clickable) {

		panel.removeAll();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				if (clickable == true) {
					JButton button = new JButton(Integer.toString(num_array[i][j]));
					button.setFocusable(false);
					panel.add(button);

					if (num_array[i][j] == 0) {
						button.setVisible(false);
					} else {
						button.addActionListener(new ClickListener());
					}
				} else {
					JButton button = new JButton(Integer.toString(num_array[i][j]));
					button.setEnabled(false);
					panel.add(button);
					if (num_array[i][j] == 0) {
						button.setVisible(false);

					}
				}

			}
		}

	}

	// panel.validate();

	public boolean Check_if_Win() {
		boolean status = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 3 && j > 2)
					break;
				if (num_array[i][j] != i * 4 + j + 1) {
					status = false;
				}
			}
		}
		return status;
	}

	private class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			t.start();///
			JButton button = (JButton) e.getSource();
			button.setVisible(false);
			String name = button.getText();
			change(Integer.parseInt(name));
		}
	}

	public void change(int num) {
		int i = 0;
		int j = 0;
		for (int k = 0; k < 4; k++) {
			for (int l = 0; l < 4; l++) {
				if (num_array[k][l] == num) {
					i = k;
					j = l;
				}
			}
		}
		if (i > 0) {
			if (num_array[i - 1][j] == 0) {
				num_array[i - 1][j] = num;
				num_array[i][j] = 0;
			}
		}
		if (i < 3) {
			if (num_array[i + 1][j] == 0) {
				num_array[i + 1][j] = num;
				num_array[i][j] = 0;
			}
		}
		if (j > 0) {
			if (num_array[i][j - 1] == 0) {
				num_array[i][j - 1] = num;
				num_array[i][j] = 0;
			}
		}
		if (j < 3) {
			if (num_array[i][j + 1] == 0) {
				num_array[i][j + 1] = num;
				num_array[i][j] = 0;
			}
		}
		repaintField(true);
		if (Check_if_Win()) {
			t.stop();

			JOptionPane.showMessageDialog(null, "YOU WON", "MESSAGE", JOptionPane.PLAIN_MESSAGE);

			panel.removeAll();

			setVisible(false);
			setVisible(true);
		}
	}
}