
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

 
 @SuppressWarnings("serial")
class Puzzle_Game extends JFrame {
	ArrayList<ProfileObj> prf = new ArrayList<ProfileObj>();
    private JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
    private static Random Random_Pos_Generetor = new Random();
    private static int[][] num_array = new int[4][4];
    private  javax.swing.Timer t;
    private static long second , minute;
    private JTextField txtUname;
 
    private static void prfAdd(String uname,int[][] pos) throws IOException{
    	Select.prf.add(new ProfileObj(uname,pos));
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
        init();
    }
    
    public void init(){
    	 
    	 JLabel Time_Label = new JLabel(""); 
    	 Time_Label.setBackground(Color.LIGHT_GRAY);
    	 Time_Label.setFont(new Font("Showcard Gothic", Font.ITALIC, 17));
    	 Time_Label.setHorizontalAlignment(SwingConstants.CENTER);
    	 
    	 
    	 
    	
    	final DecimalFormat dc = new DecimalFormat("00");
    	 //Time_Label.setText(dc.format(minute) + ":" + dc.format(second));
    	Time_Label.setText(dc.format(minute) + ":" + dc.format(second));
        t = new javax.swing.Timer( 1000,
        		new ActionListener() 
        	{
                public void actionPerformed(ActionEvent e) {
                    Time_Label.setText(dc.format(minute) + ":" + dc.format(second));
                    second++;
                    if (second >= 60) {
                        second %= 60;
                        minute++;
                    }
                }
            }
        );
        
        
        setVisible(true);
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int w=(int)d.getWidth();
        int h=(int)d.getHeight();
        setLocation((int)(w/2-getWidth()/2),(int)(h/2-getHeight()/2));
       
    
    	
        getContentPane().setBackground(SystemColor.menu);
        setBounds(200, 200, 458, 392);
        setResizable(false);
        
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
      
        getContentPane().setLayout(null);
        panel.setBounds(135, 22, 300, 300);
        panel.setBackground(SystemColor.inactiveCaption);
        container.add(panel);
        
     
        
       
       
        JButton NewGameButton = new JButton("New Position");// New_game_button
        NewGameButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
        NewGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		second =0;
        		minute = 0;
        		Time_Label.setText(dc.format(minute) + ":" + dc.format(second));
        		
        		t.start();
        		
        		panel.removeAll();
        		container.repaint();
        		
        	
        		Scramble();
        		repaintField();
        		
        	}
        });
        
        
        JButton ExitButton = new JButton("Exit"); //Exit_button
        ExitButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11)); 
        ExitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		t.stop();
        		
        		setVisible(false);
        		MainMenu m = new MainMenu();
        		m.setVisible(true);
        	}
        });
        
        NewGameButton.setBounds(10, 25, 115, 41);
        getContentPane().add(NewGameButton);
        
       
      
        ExitButton.setBounds(10, 77, 115, 41);
        getContentPane().add(ExitButton);
        
       
        Time_Label.setBounds(19, 236, 106, 75);
        getContentPane().add(Time_Label);
        
        JLabel lblUname = new JLabel("Username:");
        lblUname.setBounds(135, 336, 90, 14);
        getContentPane().add(lblUname);
        
        txtUname = new JTextField();
        txtUname.setBounds(199, 333, 163, 20);
        getContentPane().add(txtUname);
        txtUname.setColumns(10);
        
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					if (txtUname.getText()!=""){
						prfAdd(txtUname.getText(),num_array);
						txtUname.setText(null);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
        	}
        });
        btnSave.setBounds(372, 332, 63, 23);
        getContentPane().add(btnSave);
      
        
        
        repaintField();
    }
    
    

    public void Scramble() {
    	
    	
          
        int[] invariants = new int[16];
 do{
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                num_array[i][j] = 0;
                invariants[i*4+j] = 0;
            }
        }
 
        for (int i=1; i<16; i++) {
            int k;
            int l;
            do {
                k = Random_Pos_Generetor.nextInt(4) ;
                l = Random_Pos_Generetor.nextInt(4) ;
            }
            while (num_array[k][l] != 0);
            num_array[k][l] = i;
            invariants[k*4+l] = i;
        }
 }
 	while (!isSolvable(invariants));
 	repaintField();
    }
    
    private boolean isSolvable(int[] invariants) {
        int row = 0;
        int parity = 0;
        int blankrow = 0;
        int gridwidth = (int) Math.sqrt(invariants.length);
        
        for (int i = 0  ; i < invariants.length; i ++){
        	if (i%gridwidth == 0){
        		row++;
        	}
        	if (invariants[i] == 0){
        		blankrow = row;
        		continue;
        	}
        	for (int j = i+1;j<invariants.length; j++){
        		if (invariants[i]>invariants[j] && invariants[j]!=0){
        			parity++;
        		}
        	}
        	
        }
       
        if (gridwidth % 2 == 0){
        	if (blankrow % 2 == 0 ){
        		return parity %2 ==0;
        	}else {return parity %2 !=0;}
        	
        }else {return parity %2 ==0;}
       
    }
 
    public void repaintField() {
        panel.removeAll();
 
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                JButton button = new JButton(Integer.toString(num_array[i][j]));
                button.setFocusable(false);
                panel.add(button);
                if (num_array[i][j] == 0) {
                    button.setVisible(false);
                } else
                    button.addActionListener(new ClickListener());
            }
        }
 
        //panel.validate();
    }
 
    public boolean Check_if_Win() {
        boolean status = true;
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (i == 3 && j>2)
                    break;
                if (num_array[i][j] != i*4+j+1) {
                    status = false;
                }
            }
        }
        return status;
    }
    
 
  
 
 
 
    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            String name = button.getText();
            change(Integer.parseInt(name));
        }
    }
   
 
    

 
    public void change(int num) {
        int i=0;
        int j=0;
        for (int k=0; k<4; k++) {
            for (int l=0; l<4; l++) {
                if (num_array[k][l] == num) {
                    i = k;
                    j = l;
                }
            }
        }
        if (i>0) {
            if (num_array[i-1][j] == 0) {
                num_array[i-1][j] = num;
                num_array[i][j] = 0;
            }
        }
        if (i<3) {
            if (num_array[i+1][j] == 0) {
                num_array[i+1][j] = num;
                num_array[i][j] = 0;
            }
        }
        if (j>0) {
            if (num_array[i][j-1] == 0) {
                num_array[i][j-1] = num;
                num_array[i][j] = 0;
            }
        }
        if (j<3) 
        {
            if (num_array[i][j+1] == 0) {
                num_array[i][j+1] = num;
                num_array[i][j]=0;
            }
        }
        repaintField();
        if (Check_if_Win()) {
        	t.stop();
            
           // Scramble();
            repaintField();
          //  setVisible(false);
            //setVisible(true);
        }
    }
}