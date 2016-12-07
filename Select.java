package defPackage;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ProfileObj implements Serializable {
	private static final long serialVersionUID = 1266395111447933184L;
	public String username;
	public int nums[][] = new int[4][4];

	public ProfileObj(String uname, int[][] pos) {
		this.username=uname;
		this.nums=pos;
	}
	
	public ProfileObj(){
		
	}

	public String getName() {
		return username;
	}

	public int[][] getProgress() {
		return nums;
	}

}
	public class Select extends JFrame {
		private static final long serialVersionUID = -5782103906826880235L;
		public static ArrayList<ProfileObj> prf = new ArrayList<ProfileObj>();
	    private static final String fname="users.prf";
	    
		public static void deserialize() throws IOException,ClassNotFoundException {
            FileInputStream fis = new FileInputStream(fname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            prf = (ArrayList<ProfileObj>) ois.readObject();
            ois.close();
            fis.close();
		}

		public static void serialize() throws IOException {
	         FileOutputStream fos= new FileOutputStream(fname);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(prf);
	         oos.close();
	         fos.close();
		}
		
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
	}

		public Select() throws ClassNotFoundException, IOException {
			super("15_puzzle");
			setBounds(200, 200, 343, 266);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);

			JButton btnPlay = new JButton("Select");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					MainMenu m = new MainMenu();
					m.setVisible(true);
				}
			});
			btnPlay.setBounds(10, 158, 154, 23);
			getContentPane().add(btnPlay);

			JButton btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnExit.setBounds(10, 192, 307, 30);
			getContentPane().add(btnExit);
			DefaultListModel<String> lModel = new DefaultListModel<String>();
			JList<String> prof_list = new JList<String>(lModel);
			prof_list.setBounds(10, 41, 307, 106);
			getContentPane().add(prof_list);
			deserialize();
			for (ProfileObj p : prf){
				lModel.addElement(p.username);
			    }
			JLabel lblSelectProfile = new JLabel("Select profile:");
			lblSelectProfile.setBounds(10, 16, 143, 14);
			getContentPane().add(lblSelectProfile);

			JButton btnDel = new JButton("Delete");
			btnDel.setBounds(163, 158, 154, 23);
			getContentPane().add(btnDel);
			
		}

}
