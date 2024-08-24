package Helper;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

    public NonEditableTableModel() {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // All cells are not editable
        return false;
    }

	
}
