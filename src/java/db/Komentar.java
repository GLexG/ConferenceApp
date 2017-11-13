/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;

/**
 *
 * @author Leon
 */
public class Komentar {
    
    private int radID;
    private int korisnikID;
    
    private int relevantnost;
    private int originalnost;
    private int primenljivost;
    private int doprinost;
    private int publikovao;        
    private int konciznost;
    private int format;
    private int kvalitet;
    private int naslovodgovara;
    private int opstaocena;
    private int ukupnaocena;
    private int preporuka1;
    private int preporuka2;
    private int preporuka3;
    private int preporuka4;
    private int preporuka5;
    private String komentar1;
    private String komentar2;
    private String komentar3;
    private String komentar4;
    
    private String ime_recezenta;
    
    private String naslov;
    private String kljucnereci;
    private String apstrakt;
    private String status;
    private String statusF;
    private Date rok;
    
    private String preporukaKoordinatoru;

    public String getPreporukaKoordinatoru() {
        return preporukaKoordinatoru;
    }

    public void setPreporukaKoordinatoru(String preporukaKoordinatoru) {
        this.preporukaKoordinatoru = preporukaKoordinatoru;
    }
    
    
    public int getRadID() {
        return radID;
    }

    public void setRadID(int radID) {
        this.radID = radID;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public int getRelevantnost() {
        return relevantnost;
    }

    public void setRelevantnost(int relevantnost) {
        this.relevantnost = relevantnost;
    }

    public int getOriginalnost() {
        return originalnost;
    }

    public void setOriginalnost(int originalnost) {
        this.originalnost = originalnost;
    }

    public int getPrimenljivost() {
        return primenljivost;
    }

    public void setPrimenljivost(int primenljivost) {
        this.primenljivost = primenljivost;
    }

    public int getDoprinost() {
        return doprinost;
    }

    public void setDoprinost(int doprinost) {
        this.doprinost = doprinost;
    }

    public int getPublikovao() {
        return publikovao;
    }

    public void setPublikovao(int publikovao) {
        this.publikovao = publikovao;
    }

    public int getKonciznost() {
        return konciznost;
    }

    public void setKonciznost(int konciznost) {
        this.konciznost = konciznost;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getKvalitet() {
        return kvalitet;
    }

    public void setKvalitet(int kvalitet) {
        this.kvalitet = kvalitet;
    }

    public int getNaslovodgovara() {
        return naslovodgovara;
    }

    public void setNaslovodgovara(int naslovodgovara) {
        this.naslovodgovara = naslovodgovara;
    }

    public int getOpstaocena() {
        return opstaocena;
    }

    public void setOpstaocena(int opstaocena) {
        this.opstaocena = opstaocena;
    }

    public int getUkupnaocena() {
        return ukupnaocena;
    }

    public void setUkupnaocena(int ukupnaocena) {
        this.ukupnaocena = ukupnaocena;
    }

    public int getPreporuka1() {
        return preporuka1;
    }

    public void setPreporuka1(int preporuka1) {
        this.preporuka1 = preporuka1;
    }

    public int getPreporuka2() {
        return preporuka2;
    }

    public void setPreporuka2(int preporuka2) {
        this.preporuka2 = preporuka2;
    }

    public int getPreporuka3() {
        return preporuka3;
    }

    public void setPreporuka3(int preporuka3) {
        this.preporuka3 = preporuka3;
    }

    public int getPreporuka4() {
        return preporuka4;
    }

    public void setPreporuka4(int preporuka4) {
        this.preporuka4 = preporuka4;
    }

    public int getPreporuka5() {
        return preporuka5;
    }

    public void setPreporuka5(int preporuka5) {
        this.preporuka5 = preporuka5;
    }

    public String getKomentar1() {
        return komentar1;
    }

    public void setKomentar1(String komentar1) {
        this.komentar1 = komentar1;
    }

    public String getKomentar2() {
        return komentar2;
    }

    public void setKomentar2(String komentar2) {
        this.komentar2 = komentar2;
    }

    public String getKomentar3() {
        return komentar3;
    }

    public void setKomentar3(String komentar3) {
        this.komentar3 = komentar3;
    }

    public String getKomentar4() {
        return komentar4;
    }

    public void setKomentar4(String komentar4) {
        this.komentar4 = komentar4;
    }

    public String getIme_recezenta() {
        return ime_recezenta;
    }

    public void setIme_recezenta(String ime_recezenta) {
        this.ime_recezenta = ime_recezenta;
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

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }
    
    
    
    public Komentar(int radID, String naslov, String kljucnereci, String apstrakt, String status, String statusF, Date rok,int ukupnaocena, int preporuka1, int preporuka2, int preporuka3, int preporuka4, int preporuka5, int korisnikID, String ime_recezenta) {
        this.radID = radID;
        this.korisnikID = korisnikID;
        this.ukupnaocena = ukupnaocena;
        this.preporuka1 = preporuka1;
        this.preporuka2 = preporuka2;
        this.preporuka3 = preporuka3;
        this.preporuka4 = preporuka4;
        this.preporuka5 = preporuka5;
        this.ime_recezenta = ime_recezenta;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.status = status;
        this.statusF = statusF;
        this.rok = rok;
    }

    public Komentar(int radID,String naslov, String kljucnereci, String apstrakt, String status, String statusF, Date rok,int ukupnaocena, int preporuka1, int preporuka2, int preporuka3, int preporuka4, int preporuka5, int korisnikID,String ime_recezenta, String komentar1, String komentar2, String komentar3, String komentar4) {
        this.radID = radID;
        this.korisnikID = korisnikID;
        this.ukupnaocena = ukupnaocena;
        this.preporuka1 = preporuka1;
        this.preporuka2 = preporuka2;
        this.preporuka3 = preporuka3;
        this.preporuka4 = preporuka4;
        this.preporuka5 = preporuka5;
        this.komentar1 = komentar1;
        this.komentar2 = komentar2;
        this.komentar3 = komentar3;
        this.komentar4 = komentar4;
        this.ime_recezenta = ime_recezenta;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.status = status;
        this.statusF = statusF;
        this.rok = rok;
    }
    
    
     
}   
