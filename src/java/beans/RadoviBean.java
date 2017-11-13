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
import javax.servlet.http.HttpServletRequest;
import javax.faces.event.ActionEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.UploadedFile;

import org.primefaces.event.CloseEvent;
/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped

public class RadoviBean {
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    private List<Rad> mojiAktivniRadovi;

    private int glavniAutorID;
    //private int registrovan;
    private int idKonferencije;
    
    private List<Rad> rezultatPretrage2;
    private String pretragaNaziv;
    private String pretragaOblast;
    private String pretragaKonferencija;
    
    private String selektovanaKonferencija;
    private String selektovanaOblast;
    
    private List<Korisnik> autori;
    private List<String> sveOblasti;
    private List<Korisnik> selektovaniAutori;
    private Korisnik selektovaniAutor;
    private String listaAutora;
    
    private String newNaslov;
    private String newKljucneReci;
    private String newApstrakt;
    
    private int newOblastID;
    private int newRadID;
    private int newKonfID;
    
    private int konfMaxRad;
    private String radID;
    
    private Konferencija konferencija;
    
    
    
    public RadoviBean(){}
     
    public RadoviBean(List<Rad> mojiRadovi, List<Korisnik> autori, int glavniAutorID) {
        this.mojiAktivniRadovi = mojiRadovi;
        this.autori = autori;
        this.glavniAutorID = glavniAutorID;
    }

    public int getKonfMaxRad() {
        return konfMaxRad;
    }

    public void setKonfMaxRad(int konfMaxRad) {
        this.konfMaxRad = konfMaxRad;
    }
    
    
    public List<String> getSveOblasti() {
        return sveOblasti;
    }

    public void setSveOblasti(List<String> sveOblasti) {
        this.sveOblasti = sveOblasti;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    
    
    
    public String getRadID() {
        return radID;
    }

    public void setRadID(String radID) {
        this.radID = radID;
    }
    
    
    public String getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(String listaAutora) {
        this.listaAutora = listaAutora;
    }

    public int getNewKonfID() {
        return newKonfID;
    }

    public void setNewKonfID(int newKonfID) {
        this.newKonfID = newKonfID;
    }

    public int getNewOblastID() {
        return newOblastID;
    }

    public void setNewOblastID(int newOblastID) {
        this.newOblastID = newOblastID;
    }
    
    
    
    public int getNewRadID() {
        return newRadID;
    }

    public void setNewRadID(int newRadID) {
        this.newRadID = newRadID;
    }
    
    
    public String getNewNaslov() {
        return newNaslov;
    }

    public void setNewNaslov(String newNaslov) {
        this.newNaslov = newNaslov;
    }

    public String getNewKljucneReci() {
        return newKljucneReci;
    }

    public void setNewKljucneReci(String newKljucneReci) {
        this.newKljucneReci = newKljucneReci;
    }

    public String getNewApstrakt() {
        return newApstrakt;
    }

    public void setNewApstrakt(String newApstrakt) {
        this.newApstrakt = newApstrakt;
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
    
//    public String f_prikaziMojeRadove(int konferencijaid){
//        setIdKonferencije(konferencijaid);
//        return "mojiRadovi";
//    }
    public String prikaziMojeRadoveView(int id){
        idKonferencije = id;
        return "mojiRadovi";
    }
    @PostConstruct
    public void prikaziMojeRadove() {
    //public List<Rad> prikaziMojeRadove() {
        //idKonferencije=konferencijaid;
        //int id2 = id;
        mojiAktivniRadovi = new ArrayList<Rad>();
         Rad rad;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();    //Jer ne moze isti statement za dva executeQuery-ja.

            //String upit = "SELECT * FROM konferencija WHERE vreme_do >= '" + dateFormat.format(date) + "';";
            String upit =   "SELECT * FROM rad " +
                            "LEFT JOIN oblast " +
                            "ON rad.oblastID=oblast.oblastID " +
                            "LEFT JOIN konf_rad_autor ON rad.radID=konf_rad_autor.radID " +
                            "LEFT JOIN konferencija ON konferencija.konfID = konf_rad_autor.konfID " +
                            "WHERE konf_rad_autor.korisnikID = "+ korisnikID +" "+
                            "ORDER BY rad.radID ASC ;";
            
//                        String upit =   "SELECT * FROM rad " +
//                            "LEFT JOIN oblast " +
//                            "ON rad.oblastID=oblast.oblastID " +
//                            "LEFT JOIN konf_rad_autor ON rad.radID=konf_rad_autor.radID " +
//                            "WHERE konf_rad_autor.korisnikID = '1'";
            
//                        String upit =   "SELECT * FROM rad " +
//                            "LEFT JOIN oblast " +
//                            "ON rad.oblastID=oblast.oblastID";
            
//            String upit2 = "SELECT ime FROM korisnik "
//                         + "LEFT JOIN konf_rad_autor"
//                         + "ON konf_rad_autor.korisnikID = korisnik.korisnikID "
//                         + "LEFT JOIN rad"                         
//                         + "ON konf_rad_autor.radID =rad.radID     ";
            //List<String> autori = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int radID = rs.getInt("radID");
                String naslov = rs.getString("naslov");
                String kljucnereci = rs.getString("kljucnereci");
                String apstrakt = rs.getString("apstrakt");
                String oblast = rs.getString("naziv");
                String fileurl = rs.getString("fileurl");
                String status = rs.getString("status");
                
                int konfID = rs.getInt("konfID");
                String knaziv = rs.getString("knaziv");
                
                
                

//                SELECT korisnik.ime
//                FROM konf_rad_autor
//                LEFT JOIN korisnik
//                ON konf_rad_autor.korisnikID = korisnik.korisnikID
//                WHERE konf_rad_autor.radID = '1'
                
                
                //String upit2 = "SELECT korisnikID from konf_rad_autor WHERE radID = ?";
//                String upit2 = "SELECT korisnik.ime" +
//                "FROM konf_rad_autor" +
//                "LEFT JOIN korisnik" +
//                "ON konf_rad_autor.korisnikID = korisnik.korisnikID" +
//                "WHERE konf_rad_autor.radID = '" + radID + "'";
//                ResultSet rs2 = stmt1.executeQuery(upit2);
//                while(rs2.next()) {
//                    String ime  = rs2.getString("ime");
//                    autori.add(ime);  
//                }
                
                
               //rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status,autori);
               //rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status);
               rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status,konfID,korisnikID,knaziv);
               mojiAktivniRadovi.add(rad);
            }
           
            stmt1.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return mojiAktivniRadovi;
    }
    
    public void pretrazi() {
        Rad rad;
        boolean loseVreme=false;
        boolean izbaci=false;
        rezultatPretrage2 = new ArrayList<Rad>(mojiAktivniRadovi);
        for (Iterator<Rad> iterator = rezultatPretrage2.iterator(); iterator.hasNext(); ) {
            rad = iterator.next();
            if (pretragaNaziv != null) {
                if (!pretragaNaziv.isEmpty()) {
                    if (!rad.getNaslov().toLowerCase().contains(pretragaNaziv.toLowerCase()))
                        izbaci = true;
                }
            }
            if (pretragaOblast != null) {
                if (!pretragaOblast.equals("Sva mesta")) {
                    if (!rad.getOblast().equals(pretragaOblast))
                        izbaci = true;
                }
            }
            
            if (pretragaKonferencija != null) {
                if (!pretragaKonferencija.equals("Sve Konferencije")) {
                    if (!rad.getKnaziv().equals(pretragaKonferencija))
                        izbaci = true;
                }
            }
            
            if (izbaci) iterator.remove();
            izbaci = false;
        }
        if (loseVreme) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Neispravan vremenski period!", ""));
    }
    
    public void pretraziKonferencije() {
        Rad rad;
        boolean loseVreme=false;
        boolean izbaci=false;
        rezultatPretrage2 = new ArrayList<Rad>(mojiAktivniRadovi);
        for (Iterator<Rad> iterator = rezultatPretrage2.iterator(); iterator.hasNext(); ) {
            rad = iterator.next();
            if (pretragaNaziv != null) {
                if (!pretragaNaziv.isEmpty()) {
                    if (!rad.getNaslov().toLowerCase().contains(pretragaNaziv.toLowerCase()))
                        izbaci = true;
                }
            }
            if (pretragaOblast != null) {
                if (!pretragaOblast.equals("Sva mesta")) {
                    if (!rad.getOblast().equals(pretragaOblast))
                        izbaci = true;
                }
            }
            
            if (pretragaKonferencija != null) {
                if (!pretragaKonferencija.equals("Sve Konferencije")) {
                    if (!rad.getOblast().equals(pretragaKonferencija))
                        izbaci = true;
                }
            }
            
            if (izbaci) iterator.remove();
            izbaci = false;
        }
        if (loseVreme) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Neispravan vremenski period!", ""));
    }
    
    
    public void onCountryChange() {
    
        if(selektovanaKonferencija !=null && !selektovanaKonferencija.equals("")){
                sveOblasti = new ArrayList<String>();
                try {
                   Class.forName("com.mysql.jdbc.Driver");
                   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
                   Statement stmt = con.createStatement();
                   String upit =    "SELECT * FROM konf_oblast " +
                                    "LEFT JOIN konferencija ON konf_oblast.konfID = konferencija.konfID " +
                                    "LEFT JOIN oblast ON konf_oblast.oblastID = oblast.oblastID " +
                                    "WHERE knaziv = '" + selektovanaKonferencija + "';";
                                    //"WHERE radID = " + radID + ";";
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
        }
        
            
    }
    
        public List<String> dohvatiSveOblasti() { //Za prikaz
        sveOblasti = new ArrayList<String>();
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
    
    public String napraviRad() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            
//            String upit = "INSERT into RAD (naslov, kljucnereci, apstrakt, oblastID, fileurl, status) values (";
//            upit += "'" + newNaslov + "', ";
//            upit += "'" + newKljucneReci + "', ";
//            upit += "'" + newApstrakt + "', ";
//            upit += newOblastID + ", ";
//            upit += newOblastID + ", ";
            
            String upit = "INSERT into RAD(naslov, kljucnereci, apstrakt, oblastID, fileurl, status, listaautora) values (?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            
            String upit2 = "SELECT oblastID FROM oblast WHERE naziv = '" + selektovanaOblast + "';";
            ResultSet rs1 = stmt1.executeQuery(upit2);
            if (rs1.next()) { newOblastID = rs1.getInt("oblastID");}
            
            String upit4 = "SELECT * FROM konferencija WHERE knaziv = '" + selektovanaKonferencija + "';";
            ResultSet rs3 = stmt2.executeQuery(upit4);
            if (rs3.next()) { 
                newKonfID = rs3.getInt("konfID");
                konfMaxRad = rs3.getInt("maxrad");
            }
            
            //MAKSIMALAN BROJ RADOVA PO AUTORU
            
            String upit5 = "SELECT COUNT(*) AS broj FROM konf_rad_autor " +
                           "WHERE konfID = " + newKonfID + " AND korisnikID = " + korisnikID + "";
            ResultSet rs5 = stmt3.executeQuery(upit5);
            if(rs5.next()){
                int broj = rs5.getInt("broj");
                
                if (broj>=konfMaxRad){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "Prekoracen broj predatih radova!", ""));        
                      return "pocetnaAutor";
                }
            }
                    
                    
                    
            //SASTAVALJANJE LISTE AUTORA
            Korisnik kori;
            listaAutora="";
            for(Iterator<Korisnik> iterator = selektovaniAutori.iterator(); iterator.hasNext();){
                kori = iterator.next();
                if(kori != null){
                    listaAutora += kori.getIme() +",";
                }
            }
                
            ps.setString(1, newNaslov);
            ps.setString(2, newKljucneReci);
            ps.setString(3, newApstrakt);
            ps.setInt(4, newOblastID);
            ps.setString(5, newNaslov);
            ps.setString(6, "KREIRAN");
            ps.setString(7, listaAutora);
                 
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) { newRadID = rs.getInt(1); }
            
            
            
            //upis u konf_rad_autor
            String upit3 = "INSERT INTO konf_rad_autor values (?,?,?)";
            PreparedStatement ps2 = con.prepareStatement(upit3);
            
            ps2.setInt(1, newKonfID);
            ps2.setInt(2, newRadID);
            ps2.setInt(3, korisnikID);
            
            affectedRows = ps2.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            
            
            
            stmt.close();stmt1.close();stmt2.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno kreiran rad!", ""));
        return "pocetnaAutor";
    }
    
    public String posaljiRad(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        radID = request.getParameter("radID");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "UPDATE rad SET status = ? where radID = ?;";
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setString(1, "PREDAT");
            ps.setInt(2, Integer.parseInt(radID));
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD_Status failed, no rows affected.");
            }
       
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad poslat.", ""));
        //poziv konstruktora
        return "pocetnaAutor";
    }    
    
    public String izbrisiRad(int delRadID){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            
            String upit = "DELETE FROM rad WHERE radID = ?";
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setInt(1, delRadID);

            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("DELETE RAD_ failed, no rows affected.");
            }
            
            String upit2 = "DELETE FROM konf_rad_autor WHERE radID = ?";
            PreparedStatement ps2 = con.prepareStatement(upit2);
            ps2.setInt(1, delRadID);
            
            affectedRows = ps2.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("DELETE RAD_ failed, no rows affected.");
            }
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rad uspesno izbrisan.", ""));
        return "pocetnaAutor";
    }
    
}
