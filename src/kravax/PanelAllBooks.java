package kravax;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static use.Files.readBooksFromFile;

public class PanelAllBooks extends Panels {

    public PanelAllBooks(int x, int y) {

        // Add frame
        JDialog frame = new JDialog(PanelMain.frame, "Списък със всички книги");
        frame.setLocationRelativeTo(PanelMain.frame);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(x - 400, y - 200, 800, 400);
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);

        // Table data
        Object[][] tableData = readBooksFromFile("", "", "", "");
        String[] tableTitles = { "ISBN", "Заглавие", "Автор", "Година", "Дата на взимане", "Дата на връщане" };

        // Disable cells to be editable
        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableTitles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable searchTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(searchTable);
        scrollPane.setBackground(Color.white);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

    }

}
