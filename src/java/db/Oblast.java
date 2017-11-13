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
public class Oblast {
    private int oblastID;
    private String naziv;
    
    private String knaziv;
    private int ocena;

    public Oblast(int oblastID, String naziv, String knaziv, int ocena) {
        this.oblastID = oblastID;
        this.naziv = naziv;
        this.knaziv = knaziv;
        this.ocena = ocena;
    }
    
    
    
    public Oblast(int oblastID, String naziv) {
        this.oblastID = oblastID;
        this.naziv = naziv;
    }

    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
    
    
    
    public Oblast(String naziv) {
        this.naziv = naziv;
    }
    
    public int getOblastID() {
        return oblastID;
    }

    public void setOblastID(int oblastID) {
        this.oblastID = oblastID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    
    
    
}
