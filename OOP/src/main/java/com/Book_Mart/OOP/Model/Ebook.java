package com.Book_Mart.OOP.Model;

public class Ebook extends Book {
    private final String downloadUrl;

    public Ebook(int bookId, String bookName, String bookAuthor, double bookPrice, String downloadUrl) {
        setBookId(bookId);
        setBookName(bookName);
        setAuthor(bookAuthor);
        setPrice(bookPrice);
        this.downloadUrl = downloadUrl;
    }

    // Polymorphism: Override method from Book
    @Override
    public String printDetails() {
        return super.printDetails() + ", Download URL: " + downloadUrl;
    }
}
