package defPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;



public class MainMenu extends JFrame  {
	/**
	 * 
	 */
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	 public static void main(String[] args) {
    	 EventQueue.invokeLater(new Runnable() {
    	      public void run() {
    	        try {
    	          MainMenu window = new MainMenu();
    	          window.setVisible(true);
    	        } catch (Exception e) {
    	          e.printStackTrace();
    	        }
    	      }
    	    });
    }

	/**
	 * Create the application.
	 */
	public MainMenu() {
		super("15_puzzle");
		setLocationRelativeTo(null);
		setTitle("15 Puzzle");
		setResizable(false);
	
		setBounds(100, 100, 240, 311);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) d.getWidth();
		int h = (int) d.getHeight();
		setLocation((int) (w / 2 - getWidth() / 2), (int) (h / 2 - getHeight() / 2));
		
	/////////////////////////////////////////////////////////////////	
		JButton PlayButton = new JButton("Play");
		PlayButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		PlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Puzzle_Game g = new Puzzle_Game();
				g.setVisible(true);
				
			}
		});
		
		///////////////////////////////////////////////////////////
		JButton Select_P_Button = new JButton("Select profile");
		Select_P_Button.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		Select_P_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				JFrame fr = null;
				try {
					fr = new SelectWindow();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fr.setVisible(true);
				
			}
		});
		///////////////////////////////////////////////////////////
		JButton ExitButton = new JButton("Exit");
		ExitButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	///////////////////////////////////////////////////////////////////	
		PlayButton.setBounds(37, 63, 150, 45);
		getContentPane().add(PlayButton);
		
		Select_P_Button.setBounds(37, 119, 150, 45);
		getContentPane().add(Select_P_Button);
		
		
		ExitButton.setBounds(37, 215, 150, 45);
		getContentPane().add(ExitButton);
		
		JLabel lblNewLabel = new JLabel("MAIN MENU");
		lblNewLabel.setFont(new Font("Roboto Bk", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 214, 41);
		getContentPane().add(lblNewLabel);
	}
}
