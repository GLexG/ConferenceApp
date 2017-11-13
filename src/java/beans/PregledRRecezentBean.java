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
public class PregledRRecezentBean {
    
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    
    private int konfID;
    private int radID;
    private String naslov;
    private String kljucnereci;
    private String apstrakt;
    private String status;
    private Date rok;
    
    private String statusF;

    
    private List<Rad> radovi;
    private Rad rad;

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
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

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }

    
    public PregledRRecezentBean(){}


    @PostConstruct 
    public void ucitajRadove() {
   
        radovi = new ArrayList<Rad>(); 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
           
            String upit =   "SELECT * FROM konf_rad_recezent " +
                            "LEFT JOIN konferencija ON konf_rad_recezent.konfID = konferencija.konfID " +
                            "LEFT JOIN rad ON konf_rad_recezent.radID = rad.radID " +
                            "LEFT JOIN formular ON formular.radID = rad.radID AND formular.korisnikID = "+ korisnikID +" " +
                            "WHERE konf_rad_recezent.korisnikID = "+ korisnikID +" " + 
                            "GROUP BY konf_rad_recezent.radID ORDER BY konf_rad_recezent.rok";
                            
                            //"WHERE korisnikID = "+ korisnikID +"";
                            
            //String upit = "SELECT * FROM RAD WHERE radID = " + radID + ";";

            ResultSet rs = stmt.executeQuery(upit);
            while(rs.next()) {
                radID = rs.getInt("radID");
                if (radID != 0){
                    naslov = rs.getString("naslov");   
                    kljucnereci = rs.getString("kljucnereci");
                    apstrakt = rs.getString("apstrakt");
                    status = rs.getString("status");   
                    rok = rs.getTimestamp("rok"); 
                    
                    ///RESAVANJE ZABRANE RECENZIJE AKO JE RAD ODBIJEN/PRIHVACEN
                    if(status.equals("PRIHVACEN") || status.equals("ODBIJEN") ){
                        statusF = "POSLAT";
                    }
                    else{
                        statusF = rs.getString("statusF");
                    }
                    rad = new Rad(radID,naslov,kljucnereci,apstrakt,status,statusF,rok);
                    //rad = new Rad(radID,naslov,kljucnereci,apstrakt,status,rok);
                    radovi.add(rad);
                }
                
//                if (ocena_status == null) {
//                    ocena_status = "NEOCENJEN";
//                }
                
                
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return radovi;
        
    }

    
}
