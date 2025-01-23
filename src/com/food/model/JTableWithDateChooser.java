
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class JTableWithDateChooser {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("JTable with JDateChooser Column Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table model with column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Date of Birth");

        // Add initial rows
        model.addRow(new Object[]{"John Doe", new Date()});
        model.addRow(new Object[]{"Jane Smith", new Date()});
         model.addRow(new Object[]{"John Doe", new Date()});
        model.addRow(new Object[]{"Jane Smith", new Date()});
         model.addRow(new Object[]{"John Doe", new Date()});
        model.addRow(new Object[]{"Jane Smith", new Date()});
         model.addRow(new Object[]{"John Doe", new Date()});
        model.addRow(new Object[]{"Jane Smith", new Date()});

        // Create the JTable with the model
        JTable table = new JTable(model);

        // Set the custom editor and renderer for the Date column
        table.getColumnModel().getColumn(1).setCellEditor(new DatePickerCellEditor());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new DatePickerCellRenderer());

        // Add the table to a scroll pane and then to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Set the frame size and make it visible
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    // Custom TableCellEditor using JDateChooser
    static class DatePickerCellEditor extends AbstractCellEditor implements TableCellEditor {

        private JDateChooser dateChooser = new JDateChooser();

        @Override
        public Object getCellEditorValue() {
            return dateChooser.getDate();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Date) {
                dateChooser.setDate((Date) value);
            }
            return dateChooser;
        }
    }

    // Custom TableCellRenderer using JDateChooser
    static class DatePickerCellRenderer extends DefaultTableCellRenderer {

        private JDateChooser dateChooser = new JDateChooser();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Date) {
                dateChooser.setDate((Date) value);
            }
            return dateChooser;
        }
    }
}
