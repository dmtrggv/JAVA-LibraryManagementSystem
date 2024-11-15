package kravax;

import library.Book;
import library.Library;
import use.Date;
import use.Files;
import use.NumericDocumentFilter;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

public class PanelBookAdd extends Panels implements ActionListener {

    JDialog frame;
    JButton save =        new JButton("Запази книгата");
    JButton close =       new JButton("Изтрий и затвори");
    JButton edit =        new JButton("Редактирай книгата");
    JButton orderBook =   new JButton("Вземи книгата");
    JButton returnBook =  new JButton("Върни книгата");
    JTextField            bookName = new JTextField();
    String                bookNameReturn;
    JTextField            bookAuthor = new JTextField();
    String                bookAuthorReturn;
    JTextField            bookPublisher = new JTextField();
    String                bookPublisherReturn;
    JComboBox<String>     bookGenre;
    String                bookGenreReturn;
    JComboBox<String>     bookLanguage;
    String                bookLanguageReturn;
    JTextField            bookYear = new JTextField();
    int                   bookYearReturn;
    JTextField            bookISBN = new JTextField();
    int                   bookISBNReturn;
    JTextField            bookPages = new JTextField();
    int                   bookPagesReturn;
    JTextField            bookDateOrder = new JTextField();
    Date                  bookDateOrderReturn;
    JTextField            bookDateReturn = new JTextField();
    Date                  bookDateReturnReturn;
    JTextField            bookLibraryId = new JTextField();
    String                bookLibraryIdReturn;

    public PanelBookAdd(int x, int y, boolean editable, String bName, String bAuthor, String bPublisher, String bGenre, String bLanguage, int bYear, int bISBN, int bPages, Date bDateOrder, Date bDateReturn) {

        // Create frame
        setPanelExist(true);
        if (editable) {
            frame = (bISBN == -1) ?
                    new JDialog(PanelMain.frame, "Добави книга") :
                    new JDialog(PanelMain.frame, "Редактиране на книга");
        } else frame = new JDialog(PanelMain.frame, "Информация за книга");
        frame.setLocationRelativeTo(PanelMain.frame);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(x - 200, y - 270, 400, 540);
        frame.setResizable(false);

        // Colors
        Color colorLabel = (editable) ? Color.black : Color.gray;
        Color colorISBN = (bISBN != -1) ? Color.gray : colorLabel;

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        // Book name
        JLabel bookNameLabel = new JLabel("Заглавие:");
        bookNameLabel.setBounds(15, 17, 100, 30);
        bookNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookNameLabel.setForeground(colorLabel);
        if (!bName.isEmpty()) bookName.setText(bName);
        bookName.setBounds(120, 20, 250, 25);
        bookName.setFont(new Font("Sans Serif", 0, 15));
        bookName.setFocusable(editable);
        bookName.setEnabled(editable);
        bookName.setDisabledTextColor(Color.darkGray);

        // Book author
        JLabel bookAuthorLabel = new JLabel("Автор:");
        bookAuthorLabel.setBounds(15, 47, 100, 30);
        bookAuthorLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookAuthorLabel.setForeground(colorLabel);
        if (!bAuthor.isEmpty()) bookAuthor.setText(bAuthor);
        bookAuthor.setBounds(120, 50, 250, 25);
        bookAuthor.setFont(new Font("Sans Serif", 0, 15));
        bookAuthor.setFocusable(editable);
        bookAuthor.setEnabled(editable);
        bookAuthor.setDisabledTextColor(Color.darkGray);

        // Book author
        JLabel bookPublisherLabel = new JLabel("Издател:");
        bookPublisherLabel.setBounds(15, 77, 100, 30);
        bookPublisherLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookPublisherLabel.setForeground(colorLabel);
        if (!bPublisher.isEmpty()) bookPublisher.setText(bPublisher);
        bookPublisher.setBounds(120, 80, 250, 25);
        bookPublisher.setFont(new Font("Sans Serif", 0, 15));
        bookPublisher.setFocusable(editable);
        bookPublisher.setEnabled(editable);
        bookPublisher.setDisabledTextColor(Color.darkGray);

        // Book genre
        JLabel bookGenreLabel = new JLabel("Жанр:");
        bookGenreLabel.setBounds(15, 107, 100, 30);
        bookGenreLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookGenreLabel.setForeground(colorLabel);
        String[] optionsGenre = { "Любовна", "Екшън", "Ностагична", "Автобиография", "Митко-гогов-градия", "Проект за ПУ-то" };
        bookGenre = new JComboBox<>(optionsGenre);
        if (!bGenre.isEmpty()) bookGenre.setSelectedItem(bGenre);
        bookGenre.setBounds(120, 110, 250, 25);
        bookGenre.setFocusable(editable);
        bookGenre.setEnabled(editable);

        // Book genre
        JLabel bookLanguageLabel = new JLabel("Език:");
        bookLanguageLabel.setBounds(15, 137, 100, 30);
        bookLanguageLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookLanguageLabel.setForeground(colorLabel);
        String[] optionsLanguage = { "Български", "Английски", "Немски", "Испански", "Инджикитайски", "Маняшки", "Майненски" };
        bookLanguage = new JComboBox<>(optionsLanguage);
        if (!bLanguage.isEmpty()) bookLanguage.setSelectedItem(bLanguage);
        bookLanguage.setBounds(120, 140, 250, 25);
        bookLanguage.setFocusable(editable);
        bookLanguage.setEnabled(editable);

        // Book year
        JLabel bookYearLabel = new JLabel("Година:");
        bookYearLabel.setBounds(15, 167, 100, 30);
        bookYearLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookYearLabel.setForeground(colorLabel);
        if (bYear != -1) bookYear.setText("" + bYear);
        bookYear.setBounds(120, 170, 250, 25);
        bookYear.setFont(new Font("Sans Serif", 0, 15));
        bookYear.setFocusable(editable);
        bookYear.setEnabled(editable);
        bookYear.setDisabledTextColor(Color.darkGray);
        ((AbstractDocument) bookYear.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Book ISBN
        JLabel bookISBNLabel = new JLabel("ISBN код:");
        bookISBNLabel.setBounds(15, 197, 100, 30);
        bookISBNLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookISBNLabel.setForeground(colorISBN);
        if (bISBN != -1) bookISBN.setText("" + bISBN);
        bookISBN.setBounds(120, 200, 250, 25);
        bookISBN.setFont(new Font("Sans Serif", 0, 15));
        bookISBN.setFocusable(editable);
        bookISBN.setDisabledTextColor(Color.darkGray);
        if (bISBN != -1 && editable) bookISBN.setEnabled(false); else bookISBN.setEnabled(editable);
        ((AbstractDocument) bookISBN.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Book pages
        JLabel bookPagesLabel = new JLabel("Страници:");
        bookPagesLabel.setBounds(15, 227, 100, 30);
        bookPagesLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookPagesLabel.setForeground(colorLabel);
        if (bPages != -1) bookPages.setText("" + bPages);
        bookPages.setBounds(120, 230, 250, 25);
        bookPages.setFont(new Font("Sans Serif", 0, 15));
        bookPages.setFocusable(editable);
        bookPages.setEnabled(editable);
        bookPages.setDisabledTextColor(Color.darkGray);
        ((AbstractDocument) bookPages.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Book order date
        JLabel bookDateOrderLabel = new JLabel("Взета:");
        bookDateOrderLabel.setBounds(15, 257, 100, 30);
        bookDateOrderLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookDateOrderLabel.setForeground(colorLabel);
        if (bDateOrder != null) bookDateOrder.setText(bDateOrder.toString(false));
        bookDateOrder.setBounds(120, 260, 250, 25);
        bookDateOrder.setFont(new Font("Sans Serif", 0, 15));
        bookDateOrder.setEnabled(false);
        bookDateOrder.setDisabledTextColor(Color.darkGray);

        // Book return date
        JLabel bookDateReturnLabel = new JLabel("Върната:");
        bookDateReturnLabel.setBounds(15, 287, 100, 30);
        bookDateReturnLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookDateReturnLabel.setForeground(colorLabel);
        if (bDateReturn != null) bookDateReturn.setText(bDateReturn.toString(false));
        bookDateReturn.setBounds(120, 290, 250, 25);
        bookDateReturn.setFont(new Font("Sans Serif", 0, 15));
        bookDateReturn.setEnabled(false);
        bookDateReturn.setDisabledTextColor(Color.darkGray);

        // Library
        Library library = Files.ReadLibraryFromFile();
        JLabel bookLibraryIdLabel = new JLabel("Библиотека:");
        bookLibraryIdLabel.setBounds(15, 317, 100, 30);
        bookLibraryIdLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        bookLibraryIdLabel.setForeground(colorLabel);
        if (library != null) bookLibraryId.setText(library.getName());
        bookLibraryId.setBounds(120, 320, 250, 25);
        bookLibraryId.setFont(new Font("Sans Serif", 0, 15));
        bookLibraryId.setEnabled(false);
        bookLibraryId.setDisabledTextColor(Color.darkGray);

        //region Buttons

        // Save button
        save.setBounds(205, 455, 160, 30);
        save.addActionListener(this);
        save.setFocusable(true);

        // Close button
        close.setText((editable) ? "Изтрий и затвори" : "Затвори");
        close.setBounds(17, 455, 160, 30);
        close.addActionListener(this);
        close.setFocusable(true);

        // Edit button
        edit.setBounds(205, 455, 160, 30);
        edit.addActionListener(this);
        edit.setFocusable(true);

        // Order button
        orderBook.setBounds(120, 350, 250, 25);
        orderBook.addActionListener(this);
        orderBook.setFocusable(true);
        orderBook.setEnabled((editable && bISBN != -1) && (bDateOrder == null || bDateReturn != null));

        // Return button
        returnBook.setBounds(120, 380, 250, 25);
        returnBook.addActionListener(this);
        returnBook.setFocusable(true);
        returnBook.setEnabled((editable && bISBN != -1) && (bDateOrder != null && bDateReturn == null));

        //endregion

        //region Add to the panel

        panel.add(returnBook);
        panel.add(orderBook);
        panel.add(close);
        if (editable) {
            panel.add(save);
        } else {
            panel.add(edit);
        }

        panel.add(bookNameLabel);
        panel.add(bookName);
        panel.add(bookAuthorLabel);
        panel.add(bookAuthor);
        panel.add(bookPublisherLabel);
        panel.add(bookPublisher);
        panel.add(bookGenreLabel);
        panel.add(bookGenre);
        panel.add(bookLanguageLabel);
        panel.add(bookLanguage);
        panel.add(bookYearLabel);
        panel.add(bookYear);
        panel.add(bookISBNLabel);
        panel.add(bookISBN);
        panel.add(bookPagesLabel);
        panel.add(bookPages);
        panel.add(bookDateOrderLabel);
        panel.add(bookDateOrder);
        panel.add(bookDateReturnLabel);
        panel.add(bookDateReturn);
        panel.add(bookLibraryIdLabel);
        panel.add(bookLibraryId);

        frame.add(panel);
        frame.setVisible(true);

        //endregion
    }

    public PanelBookAdd(int x, int y, boolean editable) {
        this(x, y, editable, "", "", "", "", "", -1, -1, -1, null, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == close) {

            // Delete and close
            frame.dispose();

        } else if (e.getSource() == save) {

            // Gather data from fields
            bookNameReturn = bookName.getText();
            bookGenreReturn = (String) bookGenre.getSelectedItem();
            bookAuthorReturn = bookAuthor.getText();
            bookPublisherReturn = bookPublisher.getText();
            bookYearReturn = (bookYear.getText().isEmpty()) ? 0 : Integer.parseInt(bookYear.getText());
            bookISBNReturn = (bookISBN.getText().isEmpty()) ? 0 : Integer.parseInt(bookISBN.getText());
            bookPagesReturn = (bookPages.getText().isEmpty()) ? 0 : Integer.parseInt(bookPages.getText());
            bookLanguageReturn = (String) bookLanguage.getSelectedItem();
            bookDateOrderReturn = (bookDateOrder.getText().isEmpty()) ? null : new Date(bookDateOrder.getText());
            bookDateReturnReturn = (bookDateReturn.getText().isEmpty()) ? null : new Date(bookDateReturn.getText());

            // Create a new book object and save it
            Book bookToSave = new Book(
                    bookNameReturn, bookGenreReturn, bookAuthorReturn, bookPublisherReturn,
                    bookYearReturn, bookISBNReturn, bookPagesReturn, bookLanguageReturn, bookDateOrderReturn, bookDateReturnReturn
            );

            Files.BookSave(bookToSave);

            // Open a new panel with the book details
            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);
            new PanelBookAdd(
                    xStart, yStart, false,
                    bookToSave.getName(), bookToSave.getAuthor(), bookToSave.getPublisher(),
                    bookToSave.getGenre(), bookToSave.getLanguage(),
                    bookToSave.getYear(), bookToSave.getISBN(), bookToSave.getPageNumber(),
                    bookDateOrderReturn, bookDateReturnReturn
            );

            // Do not dispose of the frame immediately; only do so when appropriate
            frame.dispose();

        } else if (e.getSource() == edit) {

            // Open for edit
            bookNameReturn = bookName.getText();
            bookGenreReturn = (String) bookGenre.getSelectedItem();
            bookAuthorReturn = bookAuthor.getText();
            bookPublisherReturn = bookPublisher.getText();
            bookYearReturn = (bookYear.getText().isEmpty()) ? -1 : Integer.parseInt(bookYear.getText());
            bookISBNReturn = (bookISBN.getText().isEmpty()) ? -1 : Integer.parseInt(bookISBN.getText());
            bookPagesReturn = (bookPages.getText().isEmpty())? -1 : Integer.parseInt(bookPages.getText());
            bookLanguageReturn = (String) bookLanguage.getSelectedItem();
            bookDateOrderReturn = (bookDateOrder.getText().isEmpty()) ? null : new Date(bookDateOrder.getText());
            bookDateReturnReturn = (bookDateReturn.getText().isEmpty()) ? null : new Date(bookDateReturn.getText());

            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);
            new PanelBookAdd(
                    xStart, yStart, true,
                    bookNameReturn, bookAuthorReturn, bookPublisherReturn,
                    bookGenreReturn, bookLanguageReturn,
                    bookYearReturn, bookISBNReturn, bookPagesReturn,
                    bookDateOrderReturn, bookDateReturnReturn
            );

            frame.dispose();

        } else if (e.getSource() == orderBook) {

            LocalDateTime now = LocalDateTime.now();

            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();
            int currentDay = now.getDayOfMonth();
            int currentHour = now.getHour();
            int currentMinute = now.getMinute();
            int currentSecond = now.getSecond();

            bookNameReturn = bookName.getText();
            bookGenreReturn = (String) bookGenre.getSelectedItem();
            bookAuthorReturn = bookAuthor.getText();
            bookPublisherReturn = bookPublisher.getText();
            bookYearReturn = (bookYear.getText().isEmpty()) ? 0 : Integer.parseInt(bookYear.getText());
            bookISBNReturn = (bookISBN.getText().isEmpty()) ? 0 : Integer.parseInt(bookISBN.getText());
            bookPagesReturn = (bookPages.getText().isEmpty()) ? 0 : Integer.parseInt(bookPages.getText());
            bookLanguageReturn = (String) bookLanguage.getSelectedItem();
            bookDateOrderReturn = new Date(currentYear, currentMonth, currentDay, currentHour, currentMinute, currentSecond);

            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);
            new PanelBookAdd(
                    xStart, yStart, true,
                    bookNameReturn, bookAuthorReturn, bookPublisherReturn,
                    bookGenreReturn, bookLanguageReturn,
                    bookYearReturn, bookISBNReturn, bookPagesReturn,
                    bookDateOrderReturn, null
            );

            JOptionPane.showMessageDialog(null, "Получи твоята книга на " + bookDateOrderReturn.toString(true) + ".\nБъди сигурен, че ще запазиш промените, преди да излезеш :)");
            frame.dispose();

        } else if (e.getSource() == returnBook) {

            LocalDateTime now = LocalDateTime.now();

            int currentYear = now.getYear();
            int currentMonth = now.getMonthValue();
            int currentDay = now.getDayOfMonth();
            int currentHour = now.getHour();
            int currentMinute = now.getMinute();
            int currentSecond = now.getSecond();

            bookNameReturn = bookName.getText();
            bookGenreReturn = (String) bookGenre.getSelectedItem();
            bookAuthorReturn = bookAuthor.getText();
            bookPublisherReturn = bookPublisher.getText();
            bookYearReturn = (bookYear.getText().isEmpty()) ? 0 : Integer.parseInt(bookYear.getText());
            bookISBNReturn = (bookISBN.getText().isEmpty()) ? 0 : Integer.parseInt(bookISBN.getText());
            bookPagesReturn = (bookPages.getText().isEmpty()) ? 0 : Integer.parseInt(bookPages.getText());
            bookLanguageReturn = (String) bookLanguage.getSelectedItem();
            bookDateOrderReturn = (bookDateOrder.getText().isEmpty()) ? null : new Date(bookDateOrder.getText());
            bookDateReturnReturn = new Date(currentYear, currentMonth, currentDay, currentHour, currentMinute, currentSecond);

            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);
            new PanelBookAdd(
                    xStart, yStart, true,
                    bookNameReturn, bookAuthorReturn, bookPublisherReturn,
                    bookGenreReturn, bookLanguageReturn,
                    bookYearReturn, bookISBNReturn, bookPagesReturn,
                    bookDateOrderReturn, bookDateReturnReturn
            );

            JOptionPane.showMessageDialog(null, "Върна твоята книга на " + bookDateReturnReturn.toString(true) + ".\nБъди сигурен, че ще запазиш промените, преди да излезеш :)");
            frame.dispose();

        }

    }

    public static void main(String[] args) {
        new PanelBookAdd(300, 400, true);
    }

}