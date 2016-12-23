package defPackage;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


	
	class Select  {
	
	
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
		


		

}