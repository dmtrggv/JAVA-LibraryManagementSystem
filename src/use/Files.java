package use;

import library.Book;
import library.Library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Files {

    public static String getFileDirectory() {
        String projectRoot = System.getProperty("user.dir");
        String srcPath = Paths.get(projectRoot, "src").toString();
        String filePath = Paths.get(srcPath, "files").toString();

        return filePath;
    }

    public static void LibrarySave(Library library) {
        String filePath = getFileDirectory() + "\\libraries.txt";

        String libraryData = String.format(
                "%s, %d, %s, %s, %s, %s",
                library.getName(),
                library.getWorkers(),
                library.getAddress().getStreetName(),
                library.getAddress().getStreetNumber(),
                library.getAddress().getTown(),
                library.getAddress().getCountry()
        );

        // Now overwrite the content of the file with this single library
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(libraryData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library ReadLibraryFromFile() {
        String filePath = getFileDirectory() + "\\libraries.txt";
        Library library = new Library("", null, -1);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();

            if (line != null) {
                String[] fields = line.split(",\\s*");

                if (fields.length == 6) {
                    String name = fields[0];
                    int workers = Integer.parseInt(fields[1]);
                    String streetName = fields[2];
                    String streetNumber = fields[3];
                    String town = fields[4];
                    String country = fields[5];
                    library = new Library(name, new Address(streetName, streetNumber, town, country), workers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return library;
    }

    public static void BookSave(Book book) {
        String filePath = getFileDirectory() + "\\books.txt";
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean bookExists = false;

            // Read each line and modify if ISBN matches
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");
                int fileISBN = Integer.parseInt(fields[0]);

                // Check if the ISBN matches
                if (fileISBN == book.getISBN()) {
                    // Update this line with the new book data
                    String orderDate = (book.getDateOrder() != null) ? book.getDateOrder().toString(false) : "N/A";
                    String returnDate = (book.getDateReturn() != null) ? book.getDateReturn().toString(false) : "N/A";

                    String bookData = String.format(
                            "%d, %s, %s, %s, %s, %d, %d, %s, %d, %d, %s, %s",
                            book.getISBN(), book.getName(), book.getGenre(), book.getAuthor(), book.getPublisher(),
                            book.getYear(), book.getPageNumber(), book.getLanguage(),
                            book.getBookNumber(), book.getOrderTimes(), orderDate, returnDate
                    );

                    fileContent.add(bookData);
                    bookExists = true;
                } else {
                    // Keep the original line if ISBN does not match
                    fileContent.add(line);
                }
            }

            // If the book does not exist, add it as a new entry
            if (!bookExists) {
                String orderDate = (book.getDateOrder() != null) ? book.getDateOrder().toString(true) : "N/A";
                String returnDate = (book.getDateReturn() != null) ? book.getDateReturn().toString(true) : "N/A";

                String bookData = String.format(
                        "%d, %s, %s, %s, %s, %d, %d, %s, %d, %d, %s, %s",
                        book.getISBN(), book.getName(), book.getGenre(), book.getAuthor(), book.getPublisher(),
                        book.getYear(), book.getPageNumber(), book.getLanguage(),
                        book.getBookNumber(), book.getOrderTimes(), orderDate, returnDate
                );

                fileContent.add(bookData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean BookExistsByISBN(int searchISBN) {

        String filePath = getFileDirectory() + "\\books.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");
                int fileISBN = Integer.parseInt(fields[0]);
                if (fileISBN == searchISBN) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    public static Object[][] readBooksFromFile(String filterName, String filterAuthor, String filterYear, String filterISBN) {
        String filePath = getFileDirectory() + "\\books.txt";
        List<Object[]> bookDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");

                // Check if the current row matches all filters (name, author, and ISBN)
                boolean matches = true;

                // Filter by Name (if provided)
                if (!filterName.isEmpty() && !fields[1].toLowerCase().contains(filterName.toLowerCase())) {
                    matches = false;
                }

                // Filter by Author (if provided)
                if (!filterAuthor.isEmpty() && !fields[3].toLowerCase().contains(filterAuthor.toLowerCase())) {
                    matches = false;
                }

                // Filter by Year (if provided)
                if (!filterYear.isEmpty() && !fields[3].toLowerCase().contains(filterYear.toLowerCase())) {
                    matches = false;
                }

                // Filter by ISBN (if provided)
                if (!filterISBN.isEmpty() && !fields[0].equals(filterISBN)) {
                    matches = false;
                }

                // If the line matches all the filters, add it to the list
                if (matches) {
                    Object[] bookData = new Object[6];
                    bookData[0] = Integer.parseInt(fields[0]);  // ISBN
                    bookData[1] = fields[1];                    // Book name
                    bookData[2] = fields[3];                    // Book author
                    bookData[3] = Integer.parseInt(fields[5]);  // Year
                    bookData[4] = fields[10];                   // Order date
                    bookData[5] = fields[11];                   // Return date

                    // Add the filtered book data to the list
                    bookDataList.add(bookData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array and return
        Object[][] bookDataArray = new Object[bookDataList.size()][];
        return bookDataList.toArray(bookDataArray);
    }

}
