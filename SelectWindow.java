package defPackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class SelectWindow extends JFrame {
	/**
	 * 
	 */
	

	
	public SelectWindow() throws ClassNotFoundException, IOException{
		
		super("15_puzzle");
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(200, 200, 335, 266);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) d.getWidth();
		int h = (int) d.getHeight();
		setLocation((int) (w / 2 - getWidth() / 2), (int) (h / 2 - getHeight() / 2));
		
        ///////////////////////////////////////////////////////
		JButton btnExit = new JButton("Main menu");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				MainMenu m = new MainMenu();
				m.setVisible(true);
			}
		});
		btnExit.setBounds(10, 192, 307, 30);
		getContentPane().add(btnExit);
		DefaultListModel<String> lModel = new DefaultListModel<String>();
		JList<String> prof_list = new JList<String>(lModel);
		prof_list.setBounds(10, 41, 307, 106);
		getContentPane().add(prof_list);
		Select.deserialize();
		for (ProfileObj p : Select.prf){
			lModel.addElement(p.username);
			
		    }
		///////////////////////////////////////////////////////
		JLabel lblSelectProfile = new JLabel("Select profile:");
		lblSelectProfile.setBounds(10, 16, 143, 14);
		getContentPane().add(lblSelectProfile);

		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u=prof_list.getSelectedValue();
				for (int i=0;i<Select.prf.size();i++){
					if (Select.prf.get(i).getUname().equals(u)){
						Select.prf.remove(i);
							lModel.removeAllElements();
							try {
								Select.serialize();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
					try {
							Select.deserialize();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						for (ProfileObj p : Select.prf){
						   lModel.addElement(p.username);
						    }
					}
					}
				}
		});
		btnDel.setBounds(163, 158, 154, 23);
		getContentPane().add(btnDel);
		///////////////////////////////////////////////////////
		JButton btnPlay = new JButton("Select");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (prof_list.isSelectionEmpty()!=true){
				setVisible(false);
				Puzzle_Game game = new Puzzle_Game ();
				setVisible(false);
				
				
				String n = prof_list.getSelectedValue();
				for (int i=0;i<Select.prf.size();i++){
					if (Select.prf.get(i).getUname().equals(n)){
						long mil=Select.prf.get(i).getMil();
						int pos[][] = Select.prf.get(i).getProgress();
						
					//	System.out.println(pos[0][0]);
						//System.out.println(pos[0][1]);
						game.setTime(mil);
						game.setProgress(pos);	
						game.repaintField(true);
						game.setVisible(true);
						
					}
					}
				
				}
			}
		});
		btnPlay.setBounds(10, 158, 154, 23);
		getContentPane().add(btnPlay);
		
	}
}
