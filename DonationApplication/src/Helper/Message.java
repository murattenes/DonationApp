package Helper;

import javax.swing.JOptionPane;

public class Message {
	
	
	public static void showMsg(String str) {
		String msg;
		
		if (str == "fill"){
				msg = "Please don't leave any unfilled area!";
		} else {
			msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg);
	}

}
