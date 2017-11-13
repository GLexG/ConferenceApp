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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class IzmeniRadBean {
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    
    private String radID;
    private int newRadID;//za editovanje novog ubacivanja
    private String naslov;
    private String kljucnereci;
    private String apstrakt;
    
    private int oblastID;
    private int konfID;
    
    private String oblast;
    
    private String fileurl;
    private String status;
    
    private String selektovanaKonferencija;
    private String selektovanaOblast;
    
    private List<Korisnik> autori; 
    private List<Korisnik> selektovaniAutori;
    private Korisnik selektovaniAutor;
    private String listaAutora;
    
    private UploadedFile file;
    
    private String stariStatus;

    public String getStariStatus() {
        return stariStatus;
    }

    public void setStariStatus(String stariStatus) {
        this.stariStatus = stariStatus;
    }
    
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
    
    public int getNewRadID() {
        return newRadID;
    }

    public void setNewRadID(int newRadID) {
        this.newRadID = newRadID;
    }
    
    public int getKonfID() {
        return konfID;
    }

    public void setKonfID(int konfID) {
        this.konfID = konfID;
    }
    
    
    public String getSelektovanaKonferencija() {
        return selektovanaKonferencija;
    }

    public void setSelektovanaKonferencija(String selektovanaKonferencija) {
        this.selektovanaKonferencija = selektovanaKonferencija;
    }

    public String getSelektovanaOblast() {
        return selektovanaOblast;
    }

    public void setSelektovanaOblast(String selektovanaOblast) {
        this.selektovanaOblast = selektovanaOblast;
    }
    
    public List<Korisnik> getAutori() {
        return autori;
    }

    public void setAutori(List<Korisnik> autori) {
        this.autori = autori;
    }

    public List<Korisnik> getSelektovaniAutori() {
        return selektovaniAutori;
    }

    public void setSelektovaniAutori(List<Korisnik> selektovaniAutori) {
        this.selektovaniAutori = selektovaniAutori;
    }

    public Korisnik getSelektovaniAutor() {
        return selektovaniAutor;
    }

    public void setSelektovaniAutor(Korisnik selektovaniAutor) {
        this.selektovaniAutor = selektovaniAutor;
    }

    public String getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(String listaAutora) {
        this.listaAutora = listaAutora;
    }
    
    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }
    
    public String getRadID() {
        return radID;
    }

    public void setRadID(String radID) {
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

    public int getOblastID() {
        return oblastID;
    }

    public void setOblastID(int oblastID) {
        this.oblastID = oblastID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
    
    
    @PostConstruct
    public void ucitajRad() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        radID = request.getParameter("radID");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            String upit =   "SELECT * FROM rad " +
                            "LEFT JOIN oblast " +
                            "ON rad.oblastID=oblast.oblastID " +
                            "WHERE radID = " + radID + ";";
            
            //String upit = "SELECT * FROM RAD WHERE radID = " + radID + ";";
            
            ResultSet rs = stmt.executeQuery(upit);
            if (rs.next()) {
                naslov = rs.getString("naslov");
                kljucnereci = rs.getString("kljucnereci");
                oblast = rs.getString("naziv");
                apstrakt = rs.getString("apstrakt");
            }
            

            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String izmeniRad() {
        
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
            
            String upit = "UPDATE rad SET naslov = ?, kljucnereci = ?, apstrakt = ?, oblastID = ?, fileurl = ?, status = ?, listaautora = ? WHERE radID= ?;";
            PreparedStatement ps = con.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            
            String upit2 = "SELECT oblastID FROM oblast WHERE naziv = '" + selektovanaOblast + "';";
            ResultSet rs1 = stmt1.executeQuery(upit2);
            if (rs1.next()) { oblastID = rs1.getInt("oblastID");}
            
            String upit4 = "SELECT konfID FROM konferencija WHERE knaziv = '" + selektovanaKonferencija + "';";
            ResultSet rs3 = stmt2.executeQuery(upit4);
            if (rs3.next()) { konfID = rs3.getInt("konfID");}
            
            
            //SASTAVALJANJE LISTE AUTORA
            Korisnik kori;
            listaAutora="";
            for(Iterator<Korisnik> iterator = selektovaniAutori.iterator(); iterator.hasNext();){
                kori = iterator.next();
                if(kori != null){
                    listaAutora += kori.getIme() +",";
                }
            }
            
            // AKO PONOVO EDITUJEMO AKO JE ADMIN ZATRAZIO ISPRAVKE
            
            String upit7 = "SELECT * FROM rad WHERE radID = '" + radID + "'";
            ResultSet rs7 = stmt2.executeQuery(upit7);
            if(rs7.next()){
                stariStatus = rs7.getString("status");
            }
            
            
            
            ps.setString(1, naslov);
            ps.setString(2, kljucnereci);
            ps.setString(3, apstrakt);
            ps.setInt(4, oblastID);
            ps.setString(5, naslov);
            
            if(stariStatus.equals("NA_ISPRAVCI")){
                ps.setString(6, "ISPRAVLJEN");
            }
            else{
                ps.setString(6, "KREIRAN");
            }
            ps.setString(7, listaAutora);
            ps.setInt(8, Integer.parseInt(radID));
                 
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD failed, no rows affected.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) { newRadID = rs.getInt(1); }
             
            
//            //brisanje prvo
//            String upit5 = "DELETE FROM konf_rad_autor WHERE radID = ?";
//            PreparedStatement ps5 = con.prepareStatement(upit5);   
//            ps5.setInt(1, Integer.parseInt(radID));
//            affectedRows = ps5.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("DELETING  failed, no rows affected.");
//            }
            
            //upis u konf_rad_autor
//            String upit3 = "INSERT INTO konf_rad_autor values (?,?,?)";
//            PreparedStatement ps2 = con.prepareStatement(upit3);
//            
//            ps2.setInt(1, konfID);
//            ps2.setInt(2, newRadID);
//            ps2.setInt(3, korisnikID);
//            
//            affectedRows = ps2.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Updateing konf_rad_autor failed, no rows affected.");
//            }

            stmt.close();stmt1.close();stmt2.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesan Update", "Uspesan Update"));
        return "pocetnaAutor";
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
        List<String> sveKonferencije = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM konferencija;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString("knaziv");
                sveKonferencije.add(naziv);
            }
            stmt.close();
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
                 
                Korisnik korisnik = new Korisnik(kID,kIme,kPrezime);
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
