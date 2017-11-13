
import db.Konferencija;
import db.Korisnik;
import db.Rad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped
public class DetaljiRadaBean {
    
    
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    private List<Rad> mojiAktivniRadovi;
    
    private Rad selektovaniRad;

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

    public Rad getSelektovaniRad() {
        return selektovaniRad;
    }

    public void setSelektovaniRad(Rad selektovaniRad) {
        this.selektovaniRad = selektovaniRad;
    }
    
    
    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public List<Rad> getMojiAktivniRadovi() {
        return mojiAktivniRadovi;
    }

    public void setMojiAktivniRadovi(List<Rad> mojiAktivniRadovi) {
        this.mojiAktivniRadovi = mojiAktivniRadovi;
    }

    public int getGlavniAutorID() {
        return glavniAutorID;
    }

    public void setGlavniAutorID(int glavniAutorID) {
        this.glavniAutorID = glavniAutorID;
    }

    public int getIdKonferencije() {
        return idKonferencije;
    }

    public void setIdKonferencije(int idKonferencije) {
        this.idKonferencije = idKonferencije;
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

    public String getPretragaKonferencija() {
        return pretragaKonferencija;
    }

    public void setPretragaKonferencija(String pretragaKonferencija) {
        this.pretragaKonferencija = pretragaKonferencija;
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

    public List<String> getSveOblasti() {
        return sveOblasti;
    }

    public void setSveOblasti(List<String> sveOblasti) {
        this.sveOblasti = sveOblasti;
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

    public int getNewKonfID() {
        return newKonfID;
    }

    public void setNewKonfID(int newKonfID) {
        this.newKonfID = newKonfID;
    }

    public int getKonfMaxRad() {
        return konfMaxRad;
    }

    public void setKonfMaxRad(int konfMaxRad) {
        this.konfMaxRad = konfMaxRad;
    }

    public String getRadID() {
        return radID;
    }

    public void setRadID(String radID) {
        this.radID = radID;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }
    
    
    
    
    
    
    
    @PostConstruct
    public void dohvatiRad(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        radID = request.getParameter("radID");

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
                            //"WHERE konf_rad_autor.korisnikID = "+ korisnikID +" "+
                            //"WHERE konferencija.statusK = 'OBJAVLJENA' " +
                            "WHERE rad.status = 'PRIHVACEN' AND rad.radID = "+ radID +" " +
                            "ORDER BY rad.radID ASC ;";

            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int radID = rs.getInt("radID");
                String naslov = rs.getString("naslov");
                String kljucnereci = rs.getString("kljucnereci");
                String apstrakt = rs.getString("apstrakt");
                String oblast = rs.getString("naziv");
                String fileurl = rs.getString("fileurl");
                String status = rs.getString("status");
                
                String listaautora = rs.getString("listaautora");
                Date datum = rs.getTimestamp("datum");
                
                int konfID = rs.getInt("konfID");
                String knaziv = rs.getString("knaziv");
               
                
                
               //rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status,autori);
               //rad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status);
               selektovaniRad = new Rad(radID, naslov, kljucnereci, apstrakt, oblast, fileurl,status,listaautora,datum,konfID,korisnikID,knaziv);
            }
           
            stmt1.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
    
    
}
