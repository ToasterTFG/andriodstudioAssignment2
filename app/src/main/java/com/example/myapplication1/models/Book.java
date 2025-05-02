package com.example.myapplication1.models;

public class Book {
    private String title;
    private String author;
    private int copies;
    private double price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    private String bank;

    public Book(String title, String author, int copies, double price, String bank) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.price = price;
        this.bank = bank;
    }

    public String getTitle() {
        return title; }
    public String getAuthor() {
        return author; }
    public int getCopies() {
        return copies; }
    public double getPrice() {
        return price; }
    public String getBank() {
        return bank; }

    public double getTotalPrice(){
        return getPrice()*getQuantity();
    }
}