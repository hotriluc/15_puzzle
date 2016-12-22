package defPackage;
import java.io.Serializable;

class ProfileObj implements Serializable {
	public static final long serialVersionUID = 1266395111447933184L;
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

	
	
	public int[][] getProgress() {
		return nums;
	}


}