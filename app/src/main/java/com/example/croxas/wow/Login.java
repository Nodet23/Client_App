package com.example.croxas.wow;

/**
 * Created by Croxas on 20/12/17.
 */

public class Login {

    private String nom;
    private String password;

    public Login() {
        nom = "";
        password = "";
    }

    public Login(String nom, String pass) {
        password = pass;
        this.nom = nom;

    }

    public Login(String pass){
        password = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
