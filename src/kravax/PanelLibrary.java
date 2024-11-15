package kravax;

import library.Library;
import use.Address;
import use.Files;
import use.NumericDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLibrary extends Panels implements ActionListener {

    JDialog frame;
    JButton save =        new JButton("Запази библиотеката");
    JButton edit =        new JButton("Редактирай библиотеката");
    JButton close =       new JButton("Изтрий и затвори");
    JButton addBook =     new JButton("Добави кига към библиотеката");
    JButton showBooks =   new JButton("Покажи всички книги");
    JTextField            libraryName = new JTextField();
    String                libraryNameReturn;
    JTextField            libraryAddressStreet = new JTextField();
    JTextField            libraryAddressNumber = new JTextField();
    JTextField            libraryAddressTown = new JTextField();
    JTextField            libraryAddressCountry = new JTextField();
    Address               libraryAddressReturn;
    JTextField            libraryWorkers = new JTextField();
    int                   libraryWorkersReturn;

    public PanelLibrary(int x, int y, boolean editable, String lName, Address lAddress, int lWorkers, int lBooks) {

        // Create frame
        setPanelExist(true);
        frame = new JDialog(PanelMain.frame, "Информация за твоята библиотека");

        frame.setLocationRelativeTo(PanelMain.frame);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(x - 200, y - 250, 400, 500);
        frame.setResizable(false);

        // Colors
        Color colorLabel = (editable) ? Color.black : Color.gray;

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);

        // Library name
        JLabel libraryNameLabel = new JLabel("Име:");
        libraryNameLabel.setBounds(20, 17, 100, 30);
        libraryNameLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryNameLabel.setForeground(colorLabel);
        if (!lName.isEmpty()) libraryName.setText(lName);
        libraryName.setBounds(120, 20, 250, 25);
        libraryName.setFont(new Font("Sans Serif", 0, 15));
        libraryName.setFocusable(editable);
        libraryName.setEnabled(editable);
        libraryName.setDisabledTextColor(Color.darkGray);

        // Library workers
        JLabel libraryWorkersLabel = new JLabel("Работници:");
        libraryWorkersLabel.setBounds(20, 47, 100, 30);
        libraryWorkersLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryWorkersLabel.setForeground(colorLabel);
        if (lWorkers != -1) libraryWorkers.setText("" + lWorkers);
        libraryWorkers.setBounds(120, 50, 250, 25);
        libraryWorkers.setFont(new Font("Sans Serif", 0, 15));
        libraryWorkers.setFocusable(editable);
        libraryWorkers.setEnabled(editable);
        libraryWorkers.setDisabledTextColor(Color.darkGray);
        ((AbstractDocument) libraryWorkers.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        //region Library address

        // Title label
        JLabel libraryAddressLabel = new JLabel("Физически адрес на библиотекста:");
        libraryAddressLabel.setBounds(20, 77, 400, 30);
        libraryAddressLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));

        // Street
        JLabel libraryAddressStreetLabel = new JLabel("Улица:");
        libraryAddressStreetLabel.setBounds(20, 107, 100, 30);
        libraryAddressStreetLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryAddressStreetLabel.setForeground(colorLabel);
        if (lAddress != null) libraryAddressStreet.setText(lAddress.getStreetName());
        libraryAddressStreet.setBounds(120, 110, 250, 25);
        libraryAddressStreet.setFont(new Font("Sans Serif", 0, 15));
        libraryAddressStreet.setFocusable(editable);
        libraryAddressStreet.setEnabled(editable);

        // Number
        JLabel libraryAddressNumberLabel = new JLabel("Номер/Вход:");
        libraryAddressNumberLabel.setBounds(20, 137, 100, 30);
        libraryAddressNumberLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryAddressNumberLabel.setForeground(colorLabel);
        if (lAddress != null) libraryAddressNumber.setText(lAddress.getStreetNumber());
        libraryAddressNumber.setBounds(120, 140, 250, 25);
        libraryAddressNumber.setFont(new Font("Sans Serif", 0, 15));
        libraryAddressNumber.setFocusable(editable);
        libraryAddressNumber.setEnabled(editable);

        // Town
        JLabel libraryAddressTownLabel = new JLabel("Град:");
        libraryAddressTownLabel.setBounds(20, 167, 100, 30);
        libraryAddressTownLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryAddressTownLabel.setForeground(colorLabel);
        if (lAddress != null) libraryAddressTown.setText(lAddress.getTown());
        libraryAddressTown.setBounds(120, 170, 250, 25);
        libraryAddressTown.setFont(new Font("Sans Serif", 0, 15));
        libraryAddressTown.setFocusable(editable);
        libraryAddressTown.setEnabled(editable);

        // Country
        JLabel libraryAddressCountryLabel = new JLabel("Държава:");
        libraryAddressCountryLabel.setBounds(20, 197, 100, 30);
        libraryAddressCountryLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
        libraryAddressCountryLabel.setForeground(colorLabel);
        if (lAddress != null) libraryAddressCountry.setText(lAddress.getCountry());
        libraryAddressCountry.setBounds(120, 200, 250, 25);
        libraryAddressCountry.setFont(new Font("Sans Serif", 0, 15));
        libraryAddressCountry.setFocusable(editable);
        libraryAddressCountry.setEnabled(editable);

        // Output
        JTextField libraryAddressOutput = new JTextField();
        if (lAddress != null) libraryAddressOutput.setText(lAddress.toString());
        libraryAddressOutput.setBounds(20, 240, 350, 25);
        libraryAddressOutput.setFont(new Font("Sans Serif", 0, 15));
        libraryAddressOutput.setFocusable(false);
        libraryAddressOutput.setEnabled(false);
        libraryAddressOutput.setDisabledTextColor(Color.darkGray);

        //endregion

        // Add book
        addBook.setBounds(20, 280, 350, 25);
        addBook.addActionListener(this);
        addBook.setFocusable(true);

        // Show all books
        showBooks.setBounds(20, 310, 350, 25);
        showBooks.addActionListener(this);
        showBooks.setFocusable(true);

        // Save button
        save.setBounds(205, 415, 160, 30);
        save.addActionListener(this);
        save.setFocusable(true);

        // Edit button
        edit.setBounds(205, 415, 160, 30);
        edit.addActionListener(this);
        edit.setFocusable(true);

        // Close button
        close.setText((editable) ? "Изтрий и затвори" : "Затвори");
        close.setBounds(17, 415, 160, 30);
        close.addActionListener(this);
        close.setFocusable(true);

        panel.add(libraryNameLabel);
        panel.add(libraryName);
        panel.add(libraryAddressLabel);
        panel.add(libraryAddressStreetLabel);
        panel.add(libraryAddressStreet);
        panel.add(libraryAddressNumberLabel);
        panel.add(libraryAddressNumber);
        panel.add(libraryAddressTownLabel);
        panel.add(libraryAddressTown);
        panel.add(libraryAddressCountryLabel);
        panel.add(libraryAddressCountry);
        panel.add(libraryAddressOutput);
        panel.add(libraryWorkersLabel);
        panel.add(libraryWorkers);
        panel.add(addBook);
        panel.add(showBooks);
        panel.add(close);
        panel.add((editable) ? save : edit);
        frame.add(panel);
        frame.setVisible(true);


    }

    public PanelLibrary(int x, int y, boolean editable) {
        this(x, y, editable, "", null, -1, -1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBook) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelBookAdd(xCenter, yCenter, true);

        } else if (e.getSource() == showBooks) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelAllBooks(xCenter, yCenter);

        } else if (e.getSource() == save) {

            libraryNameReturn = libraryName.getText();
            libraryWorkersReturn = (libraryWorkers.getText().isEmpty()) ? 0 : Integer.parseInt(libraryWorkers.getText());
            libraryAddressReturn = new Address(
                    libraryAddressStreet.getText(),
                    libraryAddressNumber.getText(),
                    libraryAddressTown.getText(),
                    libraryAddressCountry.getText()
            );

            Library libraryToSave = new Library(libraryNameReturn, libraryAddressReturn, libraryWorkersReturn, null);
            Files.LibrarySave(libraryToSave);

            int xStart = frame.getX() + (frame.getWidth() / 2);
            int yStart = frame.getY() + (frame.getHeight() / 2);

            new PanelLibrary(xStart, yStart, false, libraryToSave.getName(), libraryToSave.getAddress(), libraryToSave.getWorkers(), -1);
            frame.dispose();

        } else if (e.getSource() == save) {

            int xStart = (frame.getX() + frame.getWidth()) / 2;
            int yStart = (frame.getY() + frame.getHeight()) / 2;
            new PanelLibrary(
                    xStart, yStart, false, libraryName.getText(),
                    new Address(libraryAddressStreet.getText(), libraryAddressNumber.getText(), libraryAddressTown.getText(), libraryAddressCountry.getText()),
                    (libraryWorkers.getText().isEmpty()) ? 0 : Integer.parseInt(libraryWorkers.getText()), -1
            );
            frame.dispose();

        }

    }

}
