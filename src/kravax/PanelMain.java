package kravax;

import library.Library;
import use.Files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMain extends Panels implements ActionListener {

    //region Menu bar

    // Create menu bar
    JMenuBar menuBar = new JMenuBar();

    // Profile menu
    JMenu     menuProfile = new JMenu("Профил");
    JMenuItem menuProfileSwitchUser = new JMenuItem("Влез в друг профил");
    JMenuItem menuProfileLogout = new JMenuItem("Излез");

    // Books menu
    JMenu     menuBooks = new JMenu("Книги");
    JMenuItem menuBooksAdd = new JMenuItem("Добавяне на нова книга");
    JMenuItem menuBooksSearch = new JMenuItem("Търсене на книги");
    JMenuItem menuBooksEdit = new JMenuItem("Редактиране на книга");
    JMenuItem menuBooksFullList = new JMenuItem("Пълен списък с книги");

    JMenu     menuLibrary = new JMenu("Библиотека");
    JMenuItem menuLibraryShow = new JMenuItem("Виж твоята библиотека");

    //endregion

    static JFrame frame;
    public PanelMain() {

        // Create frame
        setPanelExist(true);
        frame = new JFrame("THE BEST PROGRAM EVER - admin - D.GOGOV - by KRAVA TECHNOLOGIES LLC");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1100 , 650);

        // Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(null);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        //region Menu - profile

        // Add listeners
        menuProfileSwitchUser.addActionListener(this);
        menuProfileLogout.addActionListener(this);

        // Add options
        menuProfile.add(menuProfileSwitchUser);
        menuProfile.add(menuProfileLogout);

        // Add main
        menuBar.add(menuProfile);

        //endregion

        //region Menu - books

        // Add listeners
        menuBooksAdd.addActionListener(this);
        menuBooksSearch.addActionListener(this);
        menuBooksEdit.addActionListener(this);
        menuBooksFullList.addActionListener(this);

        // Add options
        menuBooks.add(menuBooksAdd);
        menuBooks.add(menuBooksSearch);
        menuBooks.add(menuBooksEdit);
        menuBooks.add(menuBooksFullList);

        // Add books
        menuBar.add(menuBooks);

        //endregion

        //region Menu - library

        // Add listeners
        menuLibraryShow.addActionListener(this);

        // Add options
        menuLibrary.add(menuLibraryShow);

        // Add books
        menuBar.add(menuLibrary);

        //endregion

        frame.add(panel);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //region Main

        if (e.getSource() == menuProfileSwitchUser) {

            // Logout
            PanelSignin.setLoginStatus(false);
            frame.dispose();
            new PanelSignin();

        } else if (e.getSource() == menuProfileLogout) {

            // Logout and exit program
            PanelSignin.setLoginStatus(false);
            frame.dispose();

        }

        //endregion

        //region Books

        if (e.getSource() == menuBooksAdd) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelBookAdd(xCenter, yCenter, true);

        } else if (e.getSource() == menuBooksSearch) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelSearch(xCenter, yCenter);

        } else if (e.getSource() == menuBooksFullList) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelAllBooks(xCenter, yCenter);

        } else if (e.getSource() == menuBooksEdit) {

            int xCenter = (frame.getX() + frame.getWidth()) / 2;
            int yCenter = (frame.getY() + frame.getHeight()) / 2;
            new PanelOpenBook(xCenter, yCenter);

        }

        //endregion

        //region Library

        if (e.getSource() == menuLibraryShow) {

            Library library = Files.ReadLibraryFromFile();
            if (library == null) {
                int xCenter = (frame.getX() + frame.getWidth()) / 2;
                int yCenter = (frame.getY() + frame.getHeight()) / 2;
                new PanelLibrary(xCenter, yCenter, true);
            } else {
                int xCenter = (frame.getX() + frame.getWidth()) / 2;
                int yCenter = (frame.getY() + frame.getHeight()) / 2;
                new PanelLibrary(xCenter, yCenter, false, library.getName(), library.getAddress(), library.getWorkers(), -1);
            }

        }

        //endregion

    }

}
