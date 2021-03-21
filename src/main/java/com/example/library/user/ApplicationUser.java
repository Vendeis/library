package com.example.library.user;

import com.example.library.model.Book;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String password;
    @Column(unique = true)
    private String email;
    private String verificationCode;
    private boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "rentals",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> borrowedBooks;

    public long getId() {
        return id;
    }

    public ApplicationUser(long id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public ApplicationUser(long id, String password, String email, String verificationCode) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.verificationCode = verificationCode;
    }

    public ApplicationUser(String password, String email, String verificationCode, boolean isActive, List<Book> borrowedBooks) {
        this.password = password;
        this.email = email;
        this.verificationCode = verificationCode;
        this.isActive = isActive;
        this.borrowedBooks = borrowedBooks;
    }

    public ApplicationUser() {
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode){
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
