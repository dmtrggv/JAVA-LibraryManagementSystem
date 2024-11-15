package kravax;

import library.Book;
import use.NumericDocumentFilter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static use.Files.readBooksFromFile;

public class PanelSearch extends Panels implements ActionListener {

    JDialog frame;
    JButton search =                    new JButton("Търси");
    JButton clear =                     new JButton("Изчисти търсенето");
    JButton view =                      new JButton("Виж книгата");
    JTextField searchByName =           new JTextField();
    JCheckBox searchByNameCheck =       new JCheckBox();
    JTextField searchByAuthor =         new JTextField();
    JCheckBox searchByAuthorCheck =     new JCheckBox();
    JTextField searchByYear =           new JTextField();
    JCheckBox searchByYearCheck =       new JCheckBox();
    JTextField searchByISBN =           new JTextField();
    JCheckBox searchByISBNCheck =       new JCheckBox();
    JPanel panelTable;
    JTable searchTable;

    public PanelSearch(int x, int y) {

        // Create frame
        setPanelExist(true);
        frame = new JDialog(PanelMain.frame, "Търсене на книги");
        frame.setLocationRelativeTo(PanelMain.frame);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(x - 400, y - 200, 800, 400);
        frame.setResizable(false);

        //region Panels

        // Panel - main
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        // Panel - search attributes
        JPanel panelSearch = new JPanel();
        panelSearch.setBounds(0, 0, frame.getWidth(), 60);
        panelSearch.setBackground(Color.lightGray);
        panelSearch.setLayout(null);

        // Panel - bottom
        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(0, frame.getHeight() - 80, frame.getWidth(), 60);  // Positioned at the bottom, taking 60px height
        panelBottom.setBackground(Color.lightGray);
        panelBottom.setLayout(null);

        // Panel - table
        panelTable = new JPanel();
        panelTable.setBounds(0, 60, frame.getWidth(), frame.getHeight() - 140);
        panelTable.setBackground(Color.white);

        //endregion

        // Table
        String[] tableTitles = {"Заглавие", "Автор", "Гофина", "ISBN", "Дата на взимане", "Дата на връщане"};
        DefaultTableModel tableModel = new DefaultTableModel(tableTitles, 0);
        searchTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(searchTable);
        panelTable.setLayout(null);
        scrollPane.setBounds(0, 0, panelTable.getWidth(), panelTable.getHeight());

        // Search by name
        JLabel searchByNameLabel = new JLabel("По заглавие:");
        searchByNameLabel.setBounds(20, 7, 100, 20);
        searchByNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByName.setBounds(105, 7, 180, 20);
        searchByName.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByNameCheck.setBounds(295, 6, 20, 20);
        searchByNameCheck.setBackground(panelSearch.getBackground());

        // Search by author
        JLabel searchByAuthorLabel = new JLabel("По автор:");
        searchByAuthorLabel.setBounds(20, 32, 100, 20);
        searchByAuthorLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByAuthor.setBounds(105, 32, 180, 20);
        searchByAuthor.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByAuthorCheck.setBounds(295, 32, 20, 20);
        searchByAuthorCheck.setBackground(panelSearch.getBackground());

        // Search by year
        JLabel searchByYearLabel = new JLabel("По година:");
        searchByYearLabel.setBounds(390, 7, 100, 20);
        searchByYearLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByYear.setBounds(475, 7, 180, 20);
        searchByYear.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByYearCheck.setBounds(665, 6, 20, 20);
        searchByYearCheck.setBackground(panelSearch.getBackground());
        ((AbstractDocument) searchByYear.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Search by ISBN
        JLabel searchByISBNLabel = new JLabel("По ISBN код:");
        searchByISBNLabel.setBounds(390, 32, 100, 20);
        searchByISBNLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByISBN.setBounds(475, 32, 180, 20);
        searchByISBN.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchByISBNCheck.setBounds(665, 32, 20, 20);
        searchByISBNCheck.setBackground(panelSearch.getBackground());
        ((AbstractDocument) searchByISBN.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Edit
        view.setBounds(panelBottom.getWidth() - 385, 5, 120, 30);
        view.addActionListener(this);
        view.setFocusable(true);
        // edit.setEnabled(false);

        // Clear search
        clear.setBounds(panelBottom.getWidth() - 260, 5, 150, 30);
        clear.addActionListener(this);
        clear.setFocusable(true);

        // Search
        search.setBounds(panelBottom.getWidth() - 105, 5, 80, 30);
        search.addActionListener(this);
        search.setFocusable(true);

        panelBottom.add(search);
        panelBottom.add(clear);
        panelBottom.add(view);
        panelSearch.add(searchByNameLabel);
        panelSearch.add(searchByName);
        panelSearch.add(searchByNameCheck);
        panelSearch.add(searchByAuthorLabel);
        panelSearch.add(searchByAuthor);
        panelSearch.add(searchByAuthorCheck);
        panelSearch.add(searchByYearLabel);
        panelSearch.add(searchByYear);
        panelSearch.add(searchByYearCheck);
        panelSearch.add(searchByISBNLabel);
        panelSearch.add(searchByISBN);
        panelSearch.add(searchByISBNCheck);
        panel.add(panelTable);
        panel.add(panelSearch);
        panel.add(panelBottom);
        frame.add(panel);
        frame.setVisible(true);

    }

    private void updateTable(String filterName, String filterAuthor, String filterYear, String filterISBN) {

        // Get data
        Object[][] tableData = readBooksFromFile(filterName, filterAuthor, filterYear, filterISBN);
        String[] tableTitles = {"ISBN", "Заглавие", "Автор", "Година", "Дата на взимане", "Дата на връщане"};

        // Disable editable cells
        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableTitles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        searchTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(searchTable);

        panelTable.setLayout(null);
        scrollPane.setBounds(0, 0, panelTable.getWidth() - 15, panelTable.getHeight());
        scrollPane.setBackground(Color.white);

        panelTable.add(scrollPane);
        panelTable.revalidate();
        panelTable.repaint();
    }

    private void clearTable() {
        panelTable.removeAll();
        panelTable.revalidate();
        panelTable.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == search) {

            String filterName, filterAuthor, filterYear, filterISBN;

            if (!searchByName.getText().isEmpty() && searchByNameCheck.isSelected()) {
                filterName = searchByName.getText();
            } else filterName = "";

            if (!searchByAuthor.getText().isEmpty() && searchByAuthorCheck.isSelected()) {
                filterAuthor = searchByAuthor.getText();
            } else filterAuthor = "";

            if (!searchByYear.getText().isEmpty() && searchByYearCheck.isSelected()) {
                filterYear = searchByYear.getText();
            } else filterYear = "";

            if (!searchByISBN.getText().isEmpty() && searchByISBNCheck.isSelected()) {
                filterISBN = searchByISBN.getText();
            } else filterISBN = "";

            clearTable();
            updateTable(filterName, filterAuthor, filterYear, filterISBN);

        } else if (e.getSource() == view) {

            int selectedRow = searchTable.getSelectedRow();
            Object firstCellValue = searchTable.getValueAt(selectedRow, 0);
            Book book = new Book(Integer.parseInt(firstCellValue.toString()));
            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);
            new PanelBookAdd(xStart, yStart, false, book.getName(), book.getAuthor(), book.getPublisher(), book.getGenre(),
                    book.getLanguage(), book.getYear(), book.getISBN(), book.getPageNumber(), book.getDateOrder(), book.getDateReturn());

        } else if (e.getSource() == clear) {
            clearTable();
        }

    }

}
