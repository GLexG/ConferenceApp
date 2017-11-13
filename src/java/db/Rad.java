/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Leon
 */
public class Rad {
    public int radID;
    public String naslov;
    public String kljucnereci;
    public String apstrakt;
    public String oblast;
    public String fileurl;
    public String status;
    //public List<String> autori;
    public String autori;
    
    //hocemo jos
    public int konfID;
    public int korisnikID;
    public String knaziv;
    
    //status formulara
    public String statusF;
    
    
    public Date rok;
    public Date datum;
    
    
    
    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String oblast, String fileurl, String status, String autori, Date datum, int konfID, int korisnikID, String knaziv) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.fileurl = fileurl;
        this.status = status;
        this.autori = autori;
        this.konfID = konfID;
        this.korisnikID = korisnikID;
        this.knaziv = knaziv;
        this.datum = datum;
    }
    
    
    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String oblast, String fileurl, String status, String autori, Date datum) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.fileurl = fileurl;
        this.status = status;
        this.autori = autori;
        this.datum = datum;
    }

    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String status, String statusF, Date rok) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.status = status;
        this.statusF = statusF;
        this.rok = rok;
    }
    
    
    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String status, Date rok) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.status = status;
        this.rok = rok;
    }
    
    public Rad(String naslov, String kljucnereci, String apstrakt, String status, Date rok) {
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.status = status;
        this.rok = rok;
    }


    

    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String oblast, String fileurl, String status, String autori) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.fileurl = fileurl;
        this.status = status;
        this.autori = autori;
    }
     
     
    public Rad(String naslov, String kljucnereci, String apstrakt, String oblast, String fileurl, String status, String autori) {
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.fileurl = fileurl;
        this.status = status;
        this.autori = autori;
    }
            
            
    
    public Rad(int radID, String naslov, String kljucnereci, String apstrakt, String oblast,String fileurl, String status, int konfID, int korisnikID,String knaziv) {
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.status = status;
        this.konfID = konfID;
        this.korisnikID = korisnikID;
        this.knaziv = knaziv;
    }
    
    
    

    
    
    
    public Rad(int radID, String naslov, String kljucnereci,String apstrakt, String oblast, String fileurl, String status) {
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.fileurl = fileurl;
        this.status = status;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
    
    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }
    
    
    
    public String getAutori() {
        return autori;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }
    
    
    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }
    
    
    public int getKonfID() {
        return konfID;
    }

    public void setKonfID(int konfID) {
        this.konfID = konfID;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }
    
    
    

    public int getRadID() {
        return radID;
    }

    public void setRadID(int radID) {
        this.radID = radID;
    }
    
    
    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getKljucnereci() {
        return kljucnereci;
    }

    public void setKljucnereci(String kljucnereci) {
        this.kljucnereci = kljucnereci;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
    
    
    
}
