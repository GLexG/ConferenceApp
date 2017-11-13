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
public class Poruka {
    
    private int porukaID;
    private int korisnikID;
    private int konfID;
    private int radID;
    private String tekst;
    private String naslov;

    public Poruka(int porukaID, int korisnikID, int konfID, int radID, String text) {
        this.porukaID = porukaID;
        this.korisnikID = korisnikID;
        this.konfID = konfID;
        this.radID = radID;
        this.tekst = tekst;
    }

    public Poruka(int korisnikID, String tekst, String naslov) {
        this.korisnikID = korisnikID;
        this.tekst = tekst;
        this.naslov = naslov;
    }
    
    
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }
    
    
    
    
    
    public int getPorukaID() {
        return porukaID;
    }

    public void setPorukaID(int porukaID) {
        this.porukaID = porukaID;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public int getKonfID() {
        return konfID;
    }

    public void setKonfID(int konfID) {
        this.konfID = konfID;
    }

    public int getRadID() {
        return radID;
    }

    public void setRadID(int radID) {
        this.radID = radID;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String text) {
        this.tekst = text;
    }
    
    
    
}
