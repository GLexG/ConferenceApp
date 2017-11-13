/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Korisnik;
import db.Rad;
import db.Konferencija;
import db.Oblast;
import db.Recezent;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class DodajKonferencijuBean {
    
    private List<Korisnik> recezenti;
    
    private List<Recezent> recezentiK;
    
    private List<Korisnik> selektovaniRecezenti;
    private Korisnik selektovaniRecezent;
    private String listaRecezenata;
    
    private List<Oblast> oblasti;
    private List<Oblast> selektovaneOblasti;
    
    private String knaziv;
    private String mesto;
    private Date vreme_od;
    private Date vreme_do;
    private Date vreme_odrzavanja;
    private int maxrad;
    
    private int newKonfID;

    public DodajKonferencijuBean(List<Korisnik> recezenti, List<Korisnik> selektovaniRecezenti, Korisnik selektovaniRecezent, String listaRecezenata, List<Oblast> oblasti, List<Oblast> selektovaneOblasti) {
        this.recezenti = recezenti;
        this.selektovaniRecezenti = selektovaniRecezenti;
        this.selektovaniRecezent = selektovaniRecezent;
        this.listaRecezenata = listaRecezenata;
        this.oblasti = oblasti;
        this.selektovaneOblasti = selektovaneOblasti;
    }
    
    public DodajKonferencijuBean(){}

    public int getNewKonfID() {
        return newKonfID;
    }

    public void setNewKonfID(int newKonfID) {
        this.newKonfID = newKonfID;
    }
    
    
    
    public Date getVreme_odrzavanja() {
        return vreme_odrzavanja;
    }

    public void setVreme_odrzavanja(Date vreme_odrzavanja) {
        this.vreme_odrzavanja = vreme_odrzavanja;
    }
    
    
    public List<Korisnik> getRecezenti() {
        return recezenti;
    }

    public void setRecezenti(List<Korisnik> recezenti) {
        this.recezenti = recezenti;
    }

    public List<Korisnik> getSelektovaniRecezenti() {
        return selektovaniRecezenti;
    }

    public void setSelektovaniRecezenti(List<Korisnik> selektovaniRecezenti) {
        this.selektovaniRecezenti = selektovaniRecezenti;
    }

    public Korisnik getSelektovaniRecezent() {
        return selektovaniRecezent;
    }

    public void setSelektovaniRecezent(Korisnik selektovaniRecezent) {
        this.selektovaniRecezent = selektovaniRecezent;
    }

    public String getListaRecezenata() {
        return listaRecezenata;
    }

    public void setListaRecezenata(String listaRecezenata) {
        this.listaRecezenata = listaRecezenata;
    }

    public List<Oblast> getOblasti() {
        return oblasti;
    }

    public void setOblasti(List<Oblast> oblasti) {
        this.oblasti = oblasti;
    }

    public List<Oblast> getSelektovaneOblasti() {
        return selektovaneOblasti;
    }

    public void setSelektovaneOblasti(List<Oblast> selektovaneOblasti) {
        this.selektovaneOblasti = selektovaneOblasti;
    }

    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
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

    

    
    public List<Oblast> prikaziOblasti() { //Za prikaz
        oblasti = new ArrayList<Oblast>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM oblast;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int oID = rs.getInt("oblastID");
                String oNaziv = rs.getString("naziv");
                 
                Oblast oblast = new Oblast(oID,oNaziv);
                oblasti.add(oblast);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return oblasti;
    }
    
    public List<Korisnik> prikaziRecezente() { //Za prikaz
        recezenti = new ArrayList<Korisnik>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM korisnik WHERE is_recezent = 1;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int kID = rs.getInt("korisnikID");
                String kIme = rs.getString("ime");
                String kPrezime = rs.getString("prezime");
                 
                Korisnik korisnik = new Korisnik(kID,kIme,kPrezime);
                recezenti.add(korisnik);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recezenti;
    }
    
    public List<Recezent> prikaziRecezenteK() { //Za prikaz
        recezentiK = new ArrayList<Recezent>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
//            String upit = "SELECT * FROM korisnik WHERE is_recezent = 1;";
            String upit =   "SELECT DISTINCT * FROM konf_rad_recezent " +
                          "LEFT JOIN korisnik ON korisnik.korisnikID = konf_rad_recezent.korisnikID " +
                          "WHERE konf_rad_recezent.konfID = 29";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int kID = rs.getInt("korisnikID");
                String kIme = rs.getString("ime");
                String kPrezime = rs.getString("prezime");
                 
                Recezent rec = new Recezent(kID,kIme,kPrezime);
                recezentiK.add(rec);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recezentiK;
    }
    
    public void napraviKonferenciju() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            
//            String upit = "INSERT into RAD (naslov, kljucnereci, apstrakt, oblastID, fileurl, status) values (";
//            upit += "'" + newNaslov + "', ";
//            upit += "'" + newKljucneReci + "', ";
//            upit += "'" + newApstrakt + "', ";
//            upit += newOblastID + ", ";
//            upit += newOblastID + ", ";
            
            String upit = "INSERT INTO konferencija(knaziv, mesto, vreme_odrzavanja, vreme_od, vreme_do, maxrad) values (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            
//            String upit2 = "SELECT oblastID FROM oblast WHERE naziv = '" + selektovanaOblast + "';";
//            ResultSet rs1 = stmt1.executeQuery(upit2);
//            if (rs1.next()) { newOblastID = rs1.getInt("oblastID");}
            
//            
//            String upit4 = "SELECT konfID FROM konferencija WHERE knaziv = '" + selektovanaKonferencija + "';";
//            ResultSet rs3 = stmt2.executeQuery(upit4);
//            if (rs3.next()) { newKonfID = rs3.getInt("konfID");}
            
            //KONVERZIJA DATUMA
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(rad.getDatum()); 
//                    Date vreme = new Timestamp(cal.getTime().getTime());
//                    java.sql.Timestamp timestamp = new java.sql.Timestamp(vreme.getTime());
//                    ps.setTimestamp(1, timestamp);

            Calendar cal = Calendar.getInstance();
            cal.setTime(vreme_od);
            Date vreme_od = new Timestamp(cal.getTime().getTime());
            java.sql.Timestamp sqlDate_od = new java.sql.Timestamp(vreme_od.getTime());
            
            
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(vreme_do);
            Date vreme_do = new Timestamp(cal2.getTime().getTime());
            java.sql.Timestamp sqlDate_do = new java.sql.Timestamp(vreme_do.getTime());
            
            Calendar cal3 = Calendar.getInstance();
            cal3.setTime(vreme_odrzavanja);
            Date vreme_odrzavanja = new Timestamp(cal3.getTime().getTime());
            java.sql.Timestamp sqlDate_odrzavanja = new java.sql.Timestamp(vreme_odrzavanja.getTime());
            
            
            
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(vreme_od);
//            Date vreme_od = new Timestamp(cal.getTime().getTime());
//            
//            Calendar cal2 = Calendar.getInstance();
//            cal2.setTime(vreme_do);
//            Date vreme_do = new Timestamp(cal2.getTime().getTime());
//            
//            Calendar cal3 = Calendar.getInstance();
//            cal3.setTime(vreme_odrzavanja);
//            Date vreme_odrzavanja = new Timestamp(cal3.getTime().getTime());
//            //konverzija iz UTIL.DATE => SQL.DATE
//            java.util.Date utilDate = new java.util.Date();
//            java.util.Date utilDate2 = new java.util.Date();
//            java.util.Date utilDate3 = new java.util.Date();
//            utilDate = vreme_od;
//            utilDate2 = vreme_do;
//            utilDate3 = vreme_odrzavanja;
//            java.sql.Date sqlDate_od = new java.sql.Date(utilDate.getTime());
//            java.sql.Date sqlDate_do = new java.sql.Date(utilDate2.getTime());
//            java.sql.Date sqlDate_odrzavanja = new java.sql.Date(utilDate3.getTime());
            
                if (vreme_od.after(vreme_do)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datum pocetka prijave radova Konferencija je posle datuma kraja!", ""));
                    return;
                }
                if (vreme_od.after(vreme_odrzavanja)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datum pocetka Konferencija je posle datuma odrzavanja!", ""));
                    return;
                }
                if (vreme_do.after(vreme_odrzavanja) || vreme_od.after(vreme_odrzavanja)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datum pocetka prijave radova Konferencije je posle datuma odrzavanja!", ""));
                    return;
                }
            
            ps.setString(1, knaziv);
            ps.setString(2, mesto);
            ps.setTimestamp(3, sqlDate_odrzavanja);
            ps.setTimestamp(4, sqlDate_od);
            ps.setTimestamp(5, sqlDate_do);
            
//            ps.setDate(3, sqlDate_odrzavanja);
//            ps.setDate(4, sqlDate_od);
//            ps.setDate(5, sqlDate_do);
            
            ps.setInt(6, maxrad);
                 
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) { newKonfID = rs.getInt(1); }

            
            //SASTAVALJANJE LISTE Oblasti tj konf_oblast
            Oblast oblast;
            //listaRecezenata="";
            for(Iterator<Oblast> iterator = selektovaneOblasti.iterator(); iterator.hasNext();){
                oblast = iterator.next();
                if(oblast != null){
                    String upit2 = "INSERT INTO konf_oblast(konfID,oblastID) values(?,?);";
                    PreparedStatement ps2 = con.prepareStatement(upit2);
                    ps2.setInt(1, newKonfID);
                    ps2.setInt(2, oblast.getOblastID());
                    
                    affectedRows = ps2.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Update failed, no rows affected.");
                    }
                }
            }
            
            Korisnik koris;
            for(Iterator<Korisnik> iterator = selektovaniRecezenti.iterator(); iterator.hasNext();){
                koris = iterator.next();
                if(koris != null){
                    String upit3 = "INSERT INTO konf_rad_recezent(konfID,korisnikID,radID) values(?,?,?);";
                    PreparedStatement ps3 = con.prepareStatement(upit3);
                    ps3.setInt(1, newKonfID);
                    ps3.setInt(2, koris.getKorisnikID());
                    ps3.setInt(3,0); //stavljam 0 tamo gde nije dat rad na recenziju
                    
                    affectedRows = ps3.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Update failed, no rows affected.");
                    }
                    
                    String upit7 = "INSERT INTO poruka(korisnikID,konfID,naslov,tekst) values(?,?,?,?);";
                    PreparedStatement ps7 = con.prepareStatement(upit7);
                    ps7.setInt(1, koris.getKorisnikID());
                    ps7.setInt(2, newKonfID);
                    ps7.setString(3,"Recenzija");
                    String poruka = "Pozvani ste da budete Recezent konferencije "+knaziv+" Molim vas popunite ocene datih oblasti";
                    ps7.setString(4, poruka);
                    
                    affectedRows = ps7.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Update failed, no rows affected.");
                    }
                    
                    
                    
                }
            }
            
            stmt.close();stmt1.close();stmt2.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno kreirana konferencija!", ""));
    }
    
}
