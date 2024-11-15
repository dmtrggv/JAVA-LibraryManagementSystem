package kravax;

import library.Book;
import use.Files;
import use.NumericDocumentFilter;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpenBook extends Panels implements ActionListener {

    JDialog frame;
    JTextField searchISBN = new JTextField();
    JButton searchAndOpen = new JButton("Отвори");

    public PanelOpenBook(int x, int y) {

        // Create frame
        setPanelExist(true);
        frame = new JDialog(PanelMain.frame, "Намери и редактирай");
        frame.setLocationRelativeTo(PanelMain.frame);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(x - 100, y - 100, 300, 150);
        frame.setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        // Search box
        JLabel searchISBNLabel = new JLabel("Търси по ISBN");
        searchISBNLabel.setBounds(15, 10, 100, 20);
        searchISBNLabel.setFont(new Font("Sans Serif", Font.BOLD, 12));
        searchISBN.setBounds(15, 30, 255, 30);
        searchISBN.setFocusable(true);
        ((AbstractDocument) searchISBN.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Search button
        searchAndOpen.setBounds(15, 65, 254, 30);
        searchAndOpen.setFocusable(true);
        searchAndOpen.addActionListener(this);

        panel.add(searchISBNLabel);
        panel.add(searchISBN);
        panel.add(searchAndOpen);
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchAndOpen) {
            if (Files.BookExistsByISBN(Integer.parseInt(searchISBN.getText()))) {
                Book book = new Book(Integer.parseInt(searchISBN.getText()));
                int xStart = frame.getX() + (frame.getWidth() / 2);
                int yStart = frame.getY() + (frame.getHeight() / 2);
                new PanelBookAdd(xStart, yStart, false, book.getName(), book.getAuthor(), book.getPublisher(), book.getGenre(),
                        book.getLanguage(), book.getYear(), book.getISBN(), book.getPageNumber(), book.getDateOrder(), book.getDateReturn());
            } else {
                JOptionPane.showMessageDialog(null, "Тази книга не съществува, ако искаш я добави!");
            }
        }
    }

    public static void main(String[] args) {
        new PanelOpenBook(1000, 600);
    }

}
