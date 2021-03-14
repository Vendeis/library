package com.example.library.user;

import javax.persistence.*;

@Entity
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

}
