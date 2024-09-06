package Helper;

import javax.swing.JTable;

public class SetColumnWidth {
	
	public static void columnWidth(JTable table, int width) {
		int count = table.getColumnCount();
		
		for (int i = 0; i < count; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(width);
		}
	}
}
