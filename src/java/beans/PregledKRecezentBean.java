/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Korisnik;
import db.Konferencija;
import db.Rad;
import db.Recezent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped
public class PregledKRecezentBean {
    
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    
    private int konfID;
    private String naziv;
    private String mesto;
    private Date vreme_odrzavanja;
    private Date vreme_od;
    private Date vreme_do;
    private int maxrad;
    
    private Date rok;
    private String ocena_status;
    
    private List<Konferencija> konferencije;
    private Konferencija konferencija;

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getVreme_odrzavanja() {
        return vreme_odrzavanja;
    }

    public void setVreme_odrzavanja(Date vreme_odrzavanja) {
        this.vreme_odrzavanja = vreme_odrzavanja;
    }

    public Date getVreme_od() {
        return vreme_od;
    }

    public void setVreme_od(Date vreme_od) {
        this.vreme_od = vreme_od;
    }

    public Date getVreme_do() {
        return vreme_do;
    }

    public void setVreme_do(Date vreme_do) {
        this.vreme_do = vreme_do;
    }

    public int getMaxrad() {
        return maxrad;
    }

    public void setMaxrad(int maxrad) {
        this.maxrad = maxrad;
    }

    public List<Konferencija> getKonferencije() {
        return konferencije;
    }

    public void setKonferencije(List<Konferencija> konferencije) {
        this.konferencije = konferencije;
    }

    public PregledKRecezentBean(int korisnikID, int konfID, String naziv, String mesto, Date vreme_odrzavanja, Date vreme_od, Date vreme_do, int maxrad, List<Konferencija> konferencije) {
        this.korisnikID = korisnikID;
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_odrzavanja = vreme_odrzavanja;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
        this.konferencije = konferencije;
    }
    
    public PregledKRecezentBean(){}


    @PostConstruct 
    public void ucitajKonferencije() {
   
        konferencije = new ArrayList<Konferencija>(); 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
           
            String upit =   "SELECT * FROM konf_rad_recezent " +
                            "LEFT JOIN konferencija ON konf_rad_recezent.konfID = konferencija.konfID " +
                            "LEFT JOIN recezent_oblast_ocena ON recezent_oblast_ocena.konfID = konf_rad_recezent.konfID AND recezent_oblast_ocena.korisnikID = konf_rad_recezent.korisnikID " +
                            "WHERE konf_rad_recezent.korisnikID = " + korisnikID + " " + 
                            "GROUP BY konf_rad_recezent.konfID";
                            
                            //"WHERE korisnikID = "+ korisnikID +"";
                            
            
            //String upit = "SELECT * FROM RAD WHERE radID = " + radID + ";";
            

            ResultSet rs = stmt.executeQuery(upit);
            while(rs.next()) {
                konfID = rs.getInt("konfID");
                naziv = rs.getString("knaziv");
                mesto = rs.getString("mesto");
                vreme_odrzavanja = rs.getTimestamp("vreme_odrzavanja");
                vreme_od = rs.getTimestamp("vreme_od");
                vreme_do = rs.getTimestamp("vreme_do");
                maxrad = rs.getInt("maxrad");
                rok = rs.getTimestamp("rok");
                ocena_status = "NEOCENJEN";
                if (rs.getString("status") == null){
                    ocena_status = "NEOCENJEN";
                }
                else ocena_status = rs.getString("status");
                
//                if (ocena_status == null) {
//                    ocena_status = "NEOCENJEN";
//                }
                
               
                //Konferencija konf;
                konferencija = new Konferencija(konfID,naziv,mesto,vreme_odrzavanja,vreme_od,vreme_do,maxrad,rok,ocena_status);
                //rad = new Rad(naslov,kljucnereci,apstrakt,oblast,fileurl,status,listaautora);
               
                konferencije.add(konferencija);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return radovi;
        
    }

    
}
