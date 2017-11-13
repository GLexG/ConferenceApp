/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Leon
 */
import db.Konferencija;
import db.Korisnik;
import db.Rad;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {
    
    private int korisnikID;
    private Korisnik korisnik;
    private String username;
    private String password;
    private boolean registrovan;
    private boolean admin;
    private boolean autor;
    private boolean recezent;
    
    //private List<Konferencija> aktivniDogadjaji;
    private Konferencija konferencija;
    
    private List<Konferencija> aktivneKonferencije;
    private List<Rad> aktivniRadovi;
    private Rad rad;
    
   // private List<Rad> listaRadova;
    private List<Konferencija> rezultatPretrage;
    private List<Rad> rezultatPretrage2;
    
    private String pretragaNaziv;
    private String pretragaOblast;
    private Date pretragaVremeOd;
    private Date pretragaVremeDo;
    private int idMesta;
    
    public int getKorisnikID() {
        return korisnikID;
    }

    ////////////////////zzzzzzzzzzzzzzzzzzzzzzzzzzzzz
    public void setKorisnikID(int korisnikID) {    
        this.korisnikID = korisnikID;
    }

    public List<Rad> getAktivniRadovi() {
        return aktivniRadovi;
    }

    public void setAktivniRadovi(List<Rad> aktivniRadovi) {
        this.aktivniRadovi = aktivniRadovi;
    }

    public Rad getRad() {
        return rad;
    }

    public void setRad(Rad rad) {
        this.rad = rad;
    }

    public List<Rad> getRezultatPretrage2() {
        return rezultatPretrage2;
    }

    public void setRezultatPretrage2(List<Rad> rezultatPretrage2) {
        this.rezultatPretrage2 = rezultatPretrage2;
    }

    public String getPretragaOblast() {
        return pretragaOblast;
    }

    public void setPretragaOblast(String pretragaOblast) {
        this.pretragaOblast = pretragaOblast;
    }
    
    
    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    
    
    public boolean isAutor() {
        return autor;
    }

    public void setAutor(boolean autor) {
        this.autor = autor;
    }

    public boolean isRecezent() {
        return recezent;
    }

    public void setRecezent(boolean recezent) {
        this.recezent = recezent;
    }

    public List<Konferencija> getAktivneKonferencije() {
        return aktivneKonferencije;
    }

    public void setAktivneKonferencije(List<Konferencija> aktivneKonferencije) {
        this.aktivneKonferencije = aktivneKonferencije;
    }

    public List<Konferencija> getRezultatPretrage() {
        return rezultatPretrage;
    }

    public void setRezultatPretrage(List<Konferencija> rezultatPretrage) {
        this.rezultatPretrage = rezultatPretrage;
    }

    public String getPretragaNaziv() {
        return pretragaNaziv;
    }

    public void setPretragaNaziv(String pretragaNaziv) {
        this.pretragaNaziv = pretragaNaziv;
    }

    public String getpretragaOblast() {
        return pretragaOblast;
    }

    public void setpretragaOblast(String pretragaOblast) {
        this.pretragaOblast = pretragaOblast;
    }

    public Date getPretragaVremeOd() {
        return pretragaVremeOd;
    }

    public void setPretragaVremeOd(Date pretragaVremeOd) {
        this.pretragaVremeOd = pretragaVremeOd;
    }

    public Date getPretragaVremeDo() {
        return pretragaVremeDo;
    }

    public void setPretragaVremeDo(Date pretragaVremeDo) {
        this.pretragaVremeDo = pretragaVremeDo;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }
    
    ////////////////////zzzzzzzzzzzzzzzzzzzzzzzzzzzzz
    
    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistrovan() {
        return registrovan;
    }

    public void setRegistrovan(boolean registrovan) {
        this.registrovan = registrovan;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    
    public String logIn() {
        String pom = "index";
       
        if (username.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unesite korisnicko ime.", ""));
        } else if (password.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unesite lozinku.", ""));
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
                Statement stmt = con.createStatement();
                String upit = "SELECT * FROM korisnik WHERE username = ? and password = ?;";    //Koristim PreparedStatement da bih se zastitio od SQL injection napada
              
                PreparedStatement ps = con.prepareStatement(upit);

                ps.setString(1, username);
                ps.setString(2, password);
                 
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    korisnikID = rs.getInt("korisnikID");
                    
                    String ime = rs.getString("ime");
                    String prezime = rs.getString("prezime");
                    String telefon = rs.getString("telefon");
                    String email = rs.getString("email");
                    String datum = rs.getString("datum");
                    String slika = rs.getString("slika");
                    //boolean prihvacen = rs.getBoolean("prihvacen");
                    //registrovan = rs.getBoolean("is_registrovan");
                    admin = rs.getBoolean("is_admin");
                    autor = rs.getBoolean("is_autor");
                    recezent = rs.getBoolean("is_recezent");
                    
                    
                    
                    System.out.print(admin); System.out.print(recezent); System.out.print(autor);
                    //boolean blokiran = rs.getBoolean("is_blokiran");

                    korisnik = new Korisnik(username, password, ime, prezime, telefon, email, datum, slika);                
                                                
                        if(autor) pom = "pocetnaAutor";
                        if(recezent) pom = "pocetnaRecezent";
                        if(admin) pom = "pocetnaAdmin";
                        

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravno korisnicko ime i/ili lozinka.", ""));
                }
                ps.close();
                stmt.close(); 
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return pom;
    }
    
    public String logOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "index";
    }
   // pretrazi radove po imenu
    public void pretrazi() {
        Rad rad;
        boolean loseVreme=false;
        boolean izbaci=false;
        rezultatPretrage2 = new ArrayList<Rad>(aktivniRadovi);
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
            
            if (izbaci) iterator.remove();
            izbaci = false;
        }
        if (loseVreme) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Neispravan vremenski period!", ""));
    }
    
    public List<Rad> prikaziRadove() {
        aktivniRadovi = new ArrayList<Rad>();
         Rad rad;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();    //Jer ne moze isti statement za dva executeQuery-ja.

            //String upit = "SELECT * FROM konferencija WHERE vreme_do >= '" + dateFormat.format(date) + "';";
            String upit =   "SELECT * FROM rad " +
                            "LEFT JOIN oblast " +
                            "ON rad.oblastID=oblast.oblastID";
            
//            String upit2 = "SELECT ime FROM korisnik "
//                         + "LEFT JOIN konf_rad_autor"
//                         + "ON konf_rad_autor.korisnikID = korisnik.korisnikID "
//                         + "LEFT JOIN rad"                         
//                         + "ON konf_rad_autor.radID =rad.radID     ";
            List<String> autori = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int radID = rs.getInt("radID");
                String naslov = rs.getString("naslov");
                String kljucnereci = rs.getString("kljucnereci");
                String apstrakt = rs.getString("apstrakt");
                String oblast = rs.getString("naziv");
                String fileurl = rs.getString("fileurl");
                String status = rs.getString("status");
                
                

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
               rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status);
                
                aktivniRadovi.add(rad);
            }
           
            stmt1.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aktivniRadovi;
    }
    
    public List<Konferencija> prikaziKonferencije() {
        aktivneKonferencije = new ArrayList<Konferencija>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();    //Jer ne moze isti statement za dva executeQuery-ja.
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            //String upit = "SELECT * FROM konferencija WHERE vreme_do >= '" + dateFormat.format(date) + "';";
            
            String upit = "SELECT * FROM konferencija WHERE vreme_do >= '2016-05-13 15:02:31' ";
            
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int konfID = rs.getInt("konfID");
                String naziv = rs.getString("knaziv");
                String mesto = rs.getString("mesto");
                Date vreme_odrzavanja = rs.getTimestamp("vreme_odrzavanja");
                Date vreme_od = rs.getTimestamp("vreme_od");
                //boolean otkazan = rs.getBoolean("otkazan");
                Date vreme_do = rs.getTimestamp("vreme_do");
                int maxrad = rs.getInt("maxrad");
                
                String status_konf = rs.getString("statusK");
               
                
                konferencija = new Konferencija(konfID, naziv, mesto, vreme_odrzavanja,vreme_od, vreme_do, maxrad,status_konf);
                aktivneKonferencije.add(konferencija);
            }
            stmt1.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aktivneKonferencije;
    }
    
    public List<String> dohvatiSveKonferencije() { //Za prikaz
        List<String> svaMesta = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "select * from konferencija;";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naziv = rs.getString("naziv");
                svaMesta.add(naziv);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return svaMesta;
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
    
    
    public void saveFile(String fileName, InputStream in) { //Sacuvaj sliku
        try {
            OutputStream out = new FileOutputStream(new File("..\\PIA_JUN_2016\\web\\resources\\images\\" + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
