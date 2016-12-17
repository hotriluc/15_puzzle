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
    public long mil;
    public int nums[][] = new int[4][4];

	public ProfileObj(String uname, long m,int[][] pos) {
		this.username=uname;
		this.mil=m;
		this.nums=pos;
		
	}
		
		public String getUname(){
			return this.username;
		}
		
		public long getMil(){
			return this.mil;
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
	 class Select extends JFrame {
		private static final long serialVersionUID = -5782103906826880235L;
		public static ArrayList<ProfileObj> prf = new ArrayList<ProfileObj>();
	    private static final String fname="users.prf";
	    
		@SuppressWarnings("unchecked")
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
		Select window = new Select();
		window.setVisible(true);
	}

		public Select() throws ClassNotFoundException, IOException {
			super("15_puzzle");
			setBounds(200, 200, 343, 266);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);

			JButton btnExit = new JButton("Exit");
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
			
			JButton btnPlay = new JButton("Select");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					
					MainMenu m = new MainMenu();
					m.setVisible(true);
					String n = prof_list.getSelectedValue();
					for (int i=0;i<prf.size();i++){
						if (prf.get(i).getUname().equals(n)){
							long mil=prf.get(i).getMil();
							int pos[][] = prf.get(i).getProgress();
							Puzzle_Game.setTime(mil);
							Puzzle_Game.setProgress(pos);
							
						}
					}
				}
			});
			btnPlay.setBounds(10, 158, 154, 23);
			getContentPane().add(btnPlay);
			
		}

}