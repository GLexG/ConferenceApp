/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Leon
 */
public class Korisnik {
    private int korisnikID;
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String datum;
    private String slika;

    public Korisnik(int korisnikID, String ime, String prezime) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
    }
    
    
    
    public Korisnik(String username, String password, String ime, String prezime, String telefon, String email, String datum, String slika) {
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.datum = datum;
        this.slika = slika;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    
    
    
    
}
        
