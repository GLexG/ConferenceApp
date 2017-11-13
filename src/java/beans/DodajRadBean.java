/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Korisnik;
import db.Rad;
import db.Konferencija;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;




/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped
public class DodajRadBean {
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private Korisnik korisnik;
    private int korisnikID;
    private List<Rad> mojiAktivniRadovi;
    private List<Korisnik> autori;
    private int glavniAutorID;
    //private int registrovan;
    private int idKonferencije;
    
    private List<Rad> rezultatPretrage2;
    private String pretragaNaziv;
    private String pretragaOblast;
    private String pretragaKonferencija;
    
    private String izabranaKonferencija;
    private List<String> sveKonferencije;
    private String izabranaOblast;


    public DodajRadBean(){}

    public List<String> getSveKonferencije() {
        return sveKonferencije;
    }

    public void setSveKonferencije(List<String> sveKonferencije) {
        this.sveKonferencije = sveKonferencije;
    }

    
    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getIzabranaKonferencija() {
        return izabranaKonferencija;
    }

    public void setIzabranaKonferencija(String izabranaKonferencija) {
        this.izabranaKonferencija = izabranaKonferencija;
    }

    public String getIzabranaOblast() {
        return izabranaOblast;
    }

    public void setIzabranaOblast(String izabranaOblast) {
        this.izabranaOblast = izabranaOblast;
    }

    
    public String getPretragaKonferencija() {
        return pretragaKonferencija;
    }

    public void setPretragaKonferencija(String pretragaKonferencija) {
        this.pretragaKonferencija = pretragaKonferencija;
    }
    
    public List<Rad> getRezultatPretrage2() {
        return rezultatPretrage2;
    }

    public void setRezultatPretrage2(List<Rad> rezultatPretrage2) {
        this.rezultatPretrage2 = rezultatPretrage2;
    }

    public String getPretragaNaziv() {
        return pretragaNaziv;
    }

    public void setPretragaNaziv(String pretragaNaziv) {
        this.pretragaNaziv = pretragaNaziv;
    }

    public String getPretragaOblast() {
        return pretragaOblast;
    }

    public void setPretragaOblast(String pretragaOblast) {
        this.pretragaOblast = pretragaOblast;
    }
    
    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }
    
    public int getIdKonferencije() {
        return idKonferencije;
    }

    public void setIdKonferencije(int idKonferencije) {
        this.idKonferencije = idKonferencije;
    }
    
    
    public List<Rad> getMojiAktivniRadovi() {
        return mojiAktivniRadovi;
    }

    public void setMojiAktivniRadovi(List<Rad> mojiRadovi) {
        this.mojiAktivniRadovi = mojiRadovi;
    }

    public List<Korisnik> getAutori() {
        return autori;
    }

    public void setAutori(List<Korisnik> autori) {
        this.autori = autori;
    }

    public int getGlavniAutorID() {
        return glavniAutorID;
    }

    public void setGlavniAutorID(int glavniAutorID) {
        this.glavniAutorID = glavniAutorID;
    }
    

    
    public List<String> dohvatiSveOblasti() { //Za prikaz
        List<String> sveOblasti = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM oblast;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString("naziv");
                sveOblasti.add(naziv);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sveOblasti;
    }
    
    public List<String> dohvatiSveKonferencije() { //Za prikaz
        sveKonferencije = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt1 = con.createStatement();
            String upit = "SELECT * FROM konferencija;";
            ResultSet rs = stmt1.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString("knaziv");
                sveKonferencije.add(naziv);
            }
            stmt1.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sveKonferencije;
    }
    
    
    
        public List<Korisnik> prikaziAutore() { //Za prikaz
        autori = new ArrayList<Korisnik>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM korisnik WHERE korisnik.is_autor = 1;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int kID = rs.getInt("korisnikID");
                String kIme = rs.getString("ime");
                String kPrezime = rs.getString("prezime");
                 
                korisnik = new Korisnik(kID,kIme,kPrezime);
                autori.add(korisnik);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return autori;
    }
    
}
