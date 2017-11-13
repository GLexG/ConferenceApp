/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Komentar;
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
public class PregledKKomentaraBean {
    
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

    
    private List<Komentar> komentari;
    private Rad rad;

    private String paramRadID;
    private String paramKonfID;
    
    private String ime_recezenta;
    
    private int  ukupnaocena; 
    private int  preporuka1;
    private int  preporuka2; 
    private int  preporuka3; 
    private int  preporuka4;
    private int  preporuka5; 
    
    
    private String preporukaKoordinatoru;

    public String getPreporukaKoordinatoru() {
        return preporukaKoordinatoru;
    }

    public void setPreporukaKoordinatoru(String preporukaKoordinatoru) {
        this.preporukaKoordinatoru = preporukaKoordinatoru;
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

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }

    public String getParamRadID() {
        return paramRadID;
    }

    public void setParamRadID(String paramRadID) {
        this.paramRadID = paramRadID;
    }

    public String getParamKonfID() {
        return paramKonfID;
    }

    public void setParamKonfID(String paramKonfID) {
        this.paramKonfID = paramKonfID;
    }

    public String getIme_recezenta() {
        return ime_recezenta;
    }

    public void setIme_recezenta(String ime_recezenta) {
        this.ime_recezenta = ime_recezenta;
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
    
    
    
    
    
    @PostConstruct 
    public void ucitajKomentare() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        paramRadID = request.getParameter("radID");
        paramKonfID = request.getParameter("konfID");
        
        komentari = new ArrayList<Komentar>(); 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
           
            String upit =   "SELECT * FROM konf_rad_recezent " +
                            "LEFT JOIN rad ON konf_rad_recezent.radID = rad.radID " +
                            "LEFT JOIN formular ON formular.korisnikID = konf_rad_recezent.korisnikID AND formular.radID = konf_rad_recezent.radID " +
                            "LEFT JOIN korisnik ON korisnik.korisnikID = konf_rad_recezent.korisnikID " +
                            "WHERE konf_rad_recezent.radID = "+ paramRadID +" AND konf_rad_recezent.konfID = "+ paramKonfID +" AND formular.statusF = 'POSLAT' ";
            
                            //"GROUP BY konf_rad_recezent.radID ORDER BY konf_rad_recezent.rok";
                            //treba proveriti status koji recezent postavlja kada zavrsi RECENZIJU RADA statusF
                            //"AND formular.statusF = "POSLAT" ";
                              
                            
                            //"WHERE korisnikID = "+ korisnikID +"";
                            
            //String upit = "SELECT * FROM RAD WHERE radID = " + radID + ";";

            ResultSet rs = stmt.executeQuery(upit);
            while(rs.next()) {
                    
                    naslov = rs.getString("naslov");   
                    kljucnereci = rs.getString("kljucnereci");
                    apstrakt = rs.getString("apstrakt");
                    status = rs.getString("status");   
                    rok = rs.getTimestamp("rok"); 
                    statusF = rs.getString("statusF");
                    
                    korisnikID = rs.getInt("korisnikID");
                    ime_recezenta =rs.getString("ime");
                    
                    ukupnaocena = rs.getInt("ukupnaocena");
                    preporuka1 = rs.getInt("preporuka1"); //prihvatiti bez izmena
                    preporuka2 = rs.getInt("preporuka2"); //prihvatiti uz sitne izm. tehn. prirode
                    preporuka3 = rs.getInt("preporuka3"); // prihvatiti uz sitne izm. naucno-strucne prirode
                    preporuka4 = rs.getInt("preporuka4"); // prihvatiti ukoliko se ucine sustinske izmene
                    preporuka5 = rs.getInt("preporuka5"); //bezuslovno odbija
                    
                    
                    int pomRadID = Integer.parseInt(paramRadID);
                    //public Komentar(int radID, String naslov, String kljucnereci, String apstrakt, String status, String statusF, Date rok,int ukupnaocena, int preporuka1, int preporuka2, int preporuka3, int preporuka4, int preporuka5, int korisnikID, String ime_recezenta) {
                    Komentar komentar = new Komentar(pomRadID,naslov,kljucnereci,apstrakt,status,statusF,rok,ukupnaocena,preporuka1,preporuka2,preporuka3,preporuka4,preporuka5,korisnikID,ime_recezenta);
                    //rad = new Rad(radID,naslov,kljucnereci,apstrakt,status,rok);
                    komentari.add(komentar);
                }
            
                preporukaKoordinatoru();

                
                
            
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return radovi;
        
    }
    
    public void preporukaKoordinatoru(){
        int uk_ocena = 0;
        int br_rec=0;
        int arit = 0;
        Komentar komentar;
            for(Iterator<Komentar> iterator = komentari.iterator(); iterator.hasNext();){
                komentar = iterator.next();
                if(komentar != null){
                    uk_ocena+= komentar.getUkupnaocena();
                    br_rec++;
                }
            }
            
        //INDEMOOOO
        Komentar komentar2;
            for(Iterator<Komentar> iterator = komentari.iterator(); iterator.hasNext();){
                komentar2 = iterator.next();
                if(komentar2 != null){
                            //PRPORUKA KOORDINATORU NA OSNOVU SPECIFIKACIJE ZADATKA
                            preporukaKoordinatoru = "NEMA";
                            if (preporuka2 == 1 || preporuka3 == 1 || preporuka4 ==1) preporukaKoordinatoru = "NAZAD NA EDIT";
                            if(preporuka1 == 1) preporukaKoordinatoru = "PRIHVATITI";
                            arit = uk_ocena / br_rec;
                            if(arit<40) preporukaKoordinatoru = "ODBITI(<40poena)";
                            if(preporuka5 == 1) preporukaKoordinatoru = "ODBITI!";
                            komentar2.setPreporukaKoordinatoru(preporukaKoordinatoru);
                            
                        }
            }    
        //return preporukaKoordinatoru;
    }
    
    
    
    
    public String prihvatiRad(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "UPDATE rad SET status = ? where radID = ?;";
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setString(1, "PRIHVACEN");
            ps.setInt(2, Integer.parseInt(paramRadID));
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD_Status failed, no rows affected.");
            }
       
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad prihvacen.", ""));
        //poziv konstruktora
        return "pocetnaAdmin";
        
    }
    public String posaljiNaIzmenu(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "UPDATE rad SET status = ? where radID = ?;";
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setString(1, "NA_ISPRAVCI");
            ps.setInt(2, Integer.parseInt(paramRadID));
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD_Status failed, no rows affected.");
            }
       
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad poslat na izmenu.", ""));
        //poziv konstruktora
        return "pocetnaAdmin";
        
        
    }
    public String odbijRad(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "UPDATE rad SET status = ? where radID = ?;";
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setString(1, "ODBIJEN");
            ps.setInt(2, Integer.parseInt(paramRadID));
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD_Status failed, no rows affected.");
            }
       
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad odbijen.", ""));
        //poziv konstruktora
        return "pocetnaAdmin";
        
    }
    
//    public String preporukaKoordinatoru(){
//        int uk_ocena = 0;
//        int br_rec=0;
//        int arit = 0;
//        Komentar komentar;
//            for(Iterator<Komentar> iterator = komentari.iterator(); iterator.hasNext();){
//                komentar = iterator.next();
//                if(komentar != null){
//                    uk_ocena+= komentar.getUkupnaocena();
//                    br_rec++;
//                }
//            }
//            
//        //INDEMOOOO
//        Komentar komentar2;
//            for(Iterator<Komentar> iterator = komentari.iterator(); iterator.hasNext();){
//                komentar2 = iterator.next();
//                if(komentar2 != null){
//                    komentar2.set
//                }
//            }    
//            
//            arit = uk_ocena / br_rec;
//            if(arit<40) preporukaKoordinatoru = "ODBITI RAD(<40poena)";
//                    
//                            //PRPORUKA KOORDINATORU NA OSNOVU SPECIFIKACIJE ZADATKA
//                    if(preporuka1 == 1) preporukaKoordinatoru = "PRIHVATITI RAD";
//                    if (preporuka2 == 1 || preporuka3 == 1 || preporuka4 ==1) preporukaKoordinatoru = "POSLATI NAZAD NA EDITOVANJE";
//        return preporukaKoordinatoru;
//    }
    

    
}
