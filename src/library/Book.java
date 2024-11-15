package library;

import use.Date;
import use.Files;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Book {

    // default variables for all books
    private int orderTimes = 0;         // how many times we ordered the book
    private int bookNumber = 0;         // how many books we have

    private String   name;              // book name - A very good book
    private String   genre;             // book genre - action/mistery/drama/etc.
    private String   author;             // book autor - D. Gogov, 2101261032 :)
    private String   publisher;         // book publisher - Studio KRAVA
    private int      year;              // book release date
    private int      ISBN;              // ISBN - the id code of the book
    private int      pageNumber;        // book pages number
    private String   language;          // original book langage
    private Date     dateOrder;         // when the book is ordered
    private Date     dateReturn;        // when the book must be returned
    private Date     periodOfOrder;     //

    public Book(String name) {
        this.name = name;
        this.genre = "unnown";
        this.author = "unnown";
        this.publisher = "unnown";
        this.year = 0;
        this.ISBN = 0;
        this.pageNumber = 0;
        this.language = "unnown";
    }

    public Book(int searchISBN) {

        String filePath = Files.getFileDirectory() + "\\books.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");
                if (fields.length >= 12) {
                    try {
                        int fileISBN = Integer.parseInt(fields[0].trim());
                        if (fileISBN == searchISBN) {
                            this.ISBN =                 fileISBN;
                            this.name =                 fields[1];
                            this.genre =                fields[2];
                            this.author =               fields[3];
                            this.publisher =            fields[4];
                            this.year =                 Integer.parseInt(fields[5]);
                            this.pageNumber =           Integer.parseInt(fields[6]);
                            this.language =             fields[7];
                            this.bookNumber =           Integer.parseInt(fields[8]);
                            this.orderTimes =           Integer.parseInt(fields[9]);
                            String orderDateString =    fields[10];
                            String returnDateString =   fields[11];
                            this.dateOrder = orderDateString.equals("N/A") ? null : new Date(orderDateString);
                            this.dateReturn = returnDateString.equals("N/A") ? null : new Date(returnDateString);
                            this.periodOfOrder = new Date(this.dateReturn, this.dateOrder);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Book(String name, String genre, String author, String publisher, int year, int ISBN, int pageNumber, String language, Date orderDate, Date returnDate) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.ISBN = ISBN;
        this.pageNumber = pageNumber;
        this.language = language;
        this.dateOrder = orderDate;
        this.dateReturn = returnDate;
        this.periodOfOrder = new Date(this.dateReturn, this.dateOrder);
    }

    //region Getters

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getLanguage() {
        return language;
    }

    public int getOrderTimes() {
        return orderTimes;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    //endregion

    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOrderTimes(int orderTimes) {
        this.orderTimes = orderTimes;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    //endregion


    @Override
    public String toString() {
        return "Book{" +
                "orderTimes=" + orderTimes +
                ", bookNumber=" + bookNumber +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", ISBN=" + ISBN +
                ", pageNumber=" + pageNumber +
                ", language='" + language + '\'' +
                ", dateOrder=" + dateOrder +
                ", dateReturn=" + dateReturn +
                ", periodOfOrder=" + periodOfOrder +
                '}';
    }
}
