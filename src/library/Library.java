package library;

import use.Address;

public class Library {

    private String name;
    private Address address;
    private int workers;
    private Book books;

    public Library(String name, Address address, int workers, Book books) {
        this.name = name;
        this.address = address;
        this.workers = workers;
        this.books = books;
    }

    public Library(String name, Address address, int workers) {
        this.name = name;
        this.address = address;
        this.workers = workers;
    }

    //region Getters

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getWorkers() {
        return workers;
    }

    public Book getBooks() {
        return books;
    }

    //endregion

    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    //endregion


}
