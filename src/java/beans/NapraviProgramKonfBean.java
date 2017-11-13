/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Oblast;
import db.Rad;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped
public class NapraviProgramKonfBean {
    
    private String konfID;
    private List<Rad> radovi;
    private int radID;
    private String naslov;
    private String kljucnereci;
    private String oblast;
    private String apstrakt;
    private String listaautora;
    private String status;
    private String knaziv;
    private String fileurl;
    private Date datum;
    
    private Date pomDate;

    public Date getPomDate() {
        return pomDate;
    }

    public void setPomDate(Date pomDate) {
        this.pomDate = pomDate;
    }
    
    

    public String getKonfID() {
        return konfID;
    }

    public void setKonfID(String konfID) {
        this.konfID = konfID;
    }

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
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

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getApstrakt() {
        return apstrakt;
    }

    public void setApstrakt(String apstrakt) {
        this.apstrakt = apstrakt;
    }

    public String getListaautora() {
        return listaautora;
    }

    public void setListaautora(String listaautora) {
        this.listaautora = listaautora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
    
    
    
    @PostConstruct   
    public void ucitajRadove() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        konfID = request.getParameter("konfID");
        
        
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionKonfID", konfID);
        radovi = new ArrayList<Rad>(); 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
           
            String upit =   "SELECT * FROM rad " +
                            "LEFT JOIN oblast " +
                            "ON rad.oblastID=oblast.oblastID " +
                            "LEFT JOIN konf_rad_autor " +
                            "ON konf_rad_autor.radID = rad.radID " +
                            "LEFT JOIN konferencija " +
                            "ON konferencija.konfID = konf_rad_autor.konfID " +
                            //"WHERE konferencija.konfID = " + konfID + ";";
                            "WHERE konferencija.konfID = " + konfID + " AND rad.status = 'PRIHVACEN' ";
                            
            //String upit = "SELECT * FROM RAD WHERE radID = " + radID + ";";
            

            
            ResultSet rs = stmt.executeQuery(upit);
            while(rs.next()) {
                radID = rs.getInt("radID");
                naslov = rs.getString("naslov");
                kljucnereci = rs.getString("kljucnereci");
                oblast = rs.getString("naziv");
                apstrakt = rs.getString("apstrakt");
                listaautora = rs.getString("listaautora");
                status = rs.getString("status");
                knaziv = rs.getString("knaziv");
                fileurl = rs.getString("fileurl");
                
                datum = rs.getTimestamp("datum");
                if(datum == null){
                    datum = rs.getTimestamp("vreme_odrzavanja");
                }

               
                Rad rad;
                rad = new Rad(radID,naslov,kljucnereci,apstrakt,oblast,fileurl,status,listaautora,datum);
                //rad = new Rad(naslov,kljucnereci,apstrakt,oblast,fileurl,status,listaautora);
               
                radovi.add(rad);
            }
            
            
            
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return radovi;
        
    }
    //sacuvaj raspored
    public String zakaziDatum() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            
            String upit = "UPDATE rad SET datum = ? WHERE radID = ?";
            PreparedStatement ps = con.prepareStatement(upit);
            
            Rad rad;

            for(Iterator<Rad> iterator = radovi.iterator(); iterator.hasNext();){
                rad = iterator.next();
                if(rad != null){
                    
                    //SETOVANJE DATUMA
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(rad.getDatum()); 
//                    Date vreme = new Timestamp(cal.getTime().getTime());
                    
//                    java.util.Date utilDate = new java.util.Date();
//                    utilDate = vreme;
//                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                    
//                    ps.setDate(1, sqlDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(rad.getDatum()); 
                    Date vreme = new Timestamp(cal.getTime().getTime());
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(vreme.getTime());
                    ps.setTimestamp(1, timestamp);

                    
                    ps.setInt(2, rad.getRadID());
                    

                    
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("UPDATE RAD failed, no rows affected.");
                    }
                }
            }
            
            
//            String upit = "INSERT into redosled_izlaganja (konfID, radID, datum_izlaganja) values (?,?,?)";
//            PreparedStatement ps = con.prepareStatement(upit);
//            
//            Rad rad;
//
//            for(Iterator<Rad> iterator = radovi.iterator(); iterator.hasNext();){
//                rad = iterator.next();
//                if(rad != null){
//                    
//                    int pom_radID = rad.getRadID();
//                    Date datum_izlaganja = rad.getDatum();
//                    
//                    ps.setInt(1, Integer.parseInt(konfID));
//                    ps.setInt(2, pom_radID);
//                    
//                    //konverzija util date u sql date
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(rad.getDatum()); 
//                    Date vreme = new Timestamp(cal.getTime().getTime());
//                    
//                    java.util.Date utilDate = new java.util.Date();
//                    utilDate = vreme;
//                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                    
//                    ps.setDate(3, sqlDate);
//
//                    
//                    //String upit2 = "SELECT * FROM recezent_oblast_ocena WHERE"
//                    int affectedRows = ps.executeUpdate();
//                    if (affectedRows == 0) {
//                        throw new SQLException("UPDATE OCENE failed, no rows affected.");
//                    }
//                }
//            }


            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno sacuvan Raspored!", "Uspesno sacuvan Raspored!"));
        return "pocetnaAdmin";
        
    }
    
    public String objaviDatum(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            
            String upit = "INSERT into redosled_izlaganja (konfID, radID, datum_izlaganja) values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(upit);
            
            Rad rad;

            for(Iterator<Rad> iterator = radovi.iterator(); iterator.hasNext();){
                rad = iterator.next();
                if(rad != null){
                    
                    int pom_radID = rad.getRadID();
//                    Date datum_izlaganja = rad.getDatum();
                    
                    ps.setInt(1, Integer.parseInt(konfID));
                    ps.setInt(2, pom_radID);
                    
                    //konverzija util date u sql date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(rad.getDatum()); 
                    Date vreme = new Timestamp(cal.getTime().getTime());
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(vreme.getTime());
                    ps.setTimestamp(3, timestamp);


                    
                    //String upit2 = "SELECT * FROM recezent_oblast_ocena WHERE"
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("UPDATE OCENE failed, no rows affected.");
                    }
                }
            }
            
            
            String upit2 = "UPDATE konferencija SET statusK = ? where konfID = ?;";
            PreparedStatement ps2 = con.prepareStatement(upit2);
            
            ps2.setString(1, "OBJAVLJENA");
            ps2.setInt(2, Integer.parseInt(konfID));
            
            int affectedRows = ps2.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE KONF_Status failed, no rows affected.");
            }
       
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno objavljen raspored Konferencije!", ""));
        //poziv konstruktora
        return "pocetnaAdmin";
    }
    
}
