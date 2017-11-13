/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static com.sun.faces.el.FacesCompositeELResolver.ELResolverChainType.Faces;
import db.Korisnik;
import db.Oblast;
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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leon
 */
//@ViewScoped
@ManagedBean
@ViewScoped
public class PregledKoordinatorBean {
    private String konfID;
    
    private String paramRadID;
    private String paramKonfID;
    
    private int radID;
    private String naslov;
    private String kljucnereci;
    private String apstrakt;
    private String oblast;
    private String status;
    private String listaautora;
    private String knaziv;
    private String fileurl;
    
    private List<Rad> radovi;
    
    public String pretragaNaziv;
    public String pretragaOblast;
    
    private List<Rad> rezultatPretrage2;
    
    private List<Recezent> recezentiK;
    
    private List<Recezent> selektovaniRecezenti;
    
    private Date rok_recenzije;

    public PregledKoordinatorBean(String konfID, int radID, String naslov, String kljucnereci, String apstrakt, String oblast, String status, String listaautora, String knaziv, String fileurl, List<Rad> radovi, String pretragaNaziv, String pretragaOblast, List<Rad> rezultatPretrage2) {
        this.konfID = konfID;
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.status = status;
        this.listaautora = listaautora;
        this.knaziv = knaziv;
        this.fileurl = fileurl;
        this.radovi = radovi;
        this.pretragaNaziv = pretragaNaziv;
        this.pretragaOblast = pretragaOblast;
        this.rezultatPretrage2 = rezultatPretrage2;
    }

    public PregledKoordinatorBean(String konfID, String paramRadID, String paramKonfID, int radID, String naslov, String kljucnereci, String apstrakt, String oblast, String status, String listaautora, String knaziv, String fileurl, List<Rad> radovi, String pretragaNaziv, String pretragaOblast, List<Rad> rezultatPretrage2, List<Recezent> recezentiK, List<Recezent> selektovaniRecezenti) {
        this.konfID = konfID;
        this.paramRadID = paramRadID;
        this.paramKonfID = paramKonfID;
        this.radID = radID;
        this.naslov = naslov;
        this.kljucnereci = kljucnereci;
        this.apstrakt = apstrakt;
        this.oblast = oblast;
        this.status = status;
        this.listaautora = listaautora;
        this.knaziv = knaziv;
        this.fileurl = fileurl;
        this.radovi = radovi;
        this.pretragaNaziv = pretragaNaziv;
        this.pretragaOblast = pretragaOblast;
        this.rezultatPretrage2 = rezultatPretrage2;
        this.recezentiK = recezentiK;
        this.selektovaniRecezenti = selektovaniRecezenti;
    }

    public Date getRok_recenzije() {
        return rok_recenzije;
    }

    public void setRok_recenzije(Date rok_recenzije) {
        this.rok_recenzije = rok_recenzije;
    }


    public String getParamKonfID() {
        return paramKonfID;
    }

    public void setParamKonfID(String paramKonfID) {
        this.paramKonfID = paramKonfID;
    }
    
    

    public String getParamRadID() {
        return paramRadID;
    }

    public void setParamRadID(String paramRadID) {
        this.paramRadID = paramRadID;
    }
    
    
    
    public List<Recezent> getSelektovaniRecezenti() {
        return selektovaniRecezenti;
    }

    public void setSelektovaniRecezenti(List<Recezent> selektovaniRecezenti) {
        this.selektovaniRecezenti = selektovaniRecezenti;
    }

    
    
    
     public PregledKoordinatorBean(){}

    public List<Recezent> getRecezentiK() {
        return recezentiK;
    }

    public void setRecezentiK(List<Recezent> recezentiK) {
        this.recezentiK = recezentiK;
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

    public List<Rad> getRezultatPretrage2() {
        return rezultatPretrage2;
    }

    public void setRezultatPretrage2(List<Rad> rezultatPretrage2) {
        this.rezultatPretrage2 = rezultatPretrage2;
    }
 
    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
    
    
    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }
    
    public String getKonfID() {
        return konfID;
    }

    public void setKonfID(String konfID) {
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

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getListaautora() {
        return listaautora;
    }

    public void setListaautora(String listaautora) {
        this.listaautora = listaautora;
    }

    public List<Rad> getRadovi() {
        return radovi;
    }

    public void setRadovi(List<Rad> radovi) {
        this.radovi = radovi;
    }
    


    //@PostConstruct        
    //public void ucitajRadove() {
    //public List<Rad> ucitajRadove() {
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
                            //"WHERE konferencija.konfID = " + radnom + ";";
                            "WHERE konferencija.konfID = " + konfID + ";";
            
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
                
                Rad rad;
                rad = new Rad(radID,naslov,kljucnereci,apstrakt,oblast,fileurl,status,listaautora);
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
    
    //////////////////////////////////////CTRL+V
    //NE RADI PRETRAGA OBLASTI FOR FK SAKE
    
    public void pretrazi() {
        Rad rad;
        boolean loseVreme=false;
        boolean izbaci=false;
        rezultatPretrage2 = new ArrayList<Rad>(radovi);
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

//    public void uzmiReq(){
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        paramRadID = request.getParameter("radID");
//        paramKonfID = request.getParameter("konfID");
//    }
    
    public List<Recezent> prikaziRecezenteK() { //Za prikaz
        //nije dobro da se prosledjuje samo radID ,treba i konfID jer moze isti rad na vise konferencija...ali recimo da ne moze
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        paramRadID = request.getParameter("radID");
        paramKonfID = request.getParameter("konfID");

        int autor = 0;int oblast = 0;
        
        recezentiK = new ArrayList<Recezent>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();Statement stmt2 = con.createStatement();Statement stmt3 = con.createStatement();
//            String upit = "SELECT * FROM korisnik WHERE is_recezent = 1;";
            
            //PROVERA DA LI SMO IZABRALI AUTORA DA BUDE RECEZENT SEBI!!!
            String upit3 = "SELECT korisnikID FROM konf_rad_autor WHERE radID = "+ paramRadID +";";
            ResultSet rs3 = stmt.executeQuery(upit3);
            if(rs3.next()){
                autor =  rs3.getInt("korisnikID");
            }
            
            //ODABIR ODREDJENE OBLASTI IZABRANOG RADA
            String upit4 = "SELECT oblastID FROM rad WHERE rad.radID = "+ paramRadID + ";"; 
            ResultSet rs4 = stmt.executeQuery(upit4);
            if(rs4.next()){
                oblast =  rs4.getInt("oblastID");
            }
            
            
            //sAutor = Integer.toString(autor);
            //String rrrr =""+autor;
            String upit =   //"SELECT DISTINCT * FROM konf_rad_recezent " +
                          "SELECT DISTINCT * FROM konf_rad_recezent " +
                          "LEFT JOIN korisnik ON korisnik.korisnikID = konf_rad_recezent.korisnikID " +
                          "LEFT JOIN recezent_oblast_ocena ON recezent_oblast_ocena.korisnikID = konf_rad_recezent.korisnikID " +
                          "LEFT JOIN oblast ON recezent_oblast_ocena.oblastID = oblast.oblastID " +
                          "WHERE konf_rad_recezent.konfID = "+ paramKonfID +" AND konf_rad_recezent.korisnikID != "+ autor +" AND oblast.oblastID = "+ oblast +" " +
                          "GROUP BY korisnik.korisnikID";
                          //"WHERE konf_rad_recezent.konfID = "+ paramKonfID +" AND konf_rad_recezent.korisnikID != "+ autor +";";
                          //"WHERE konf_rad_recezent.konfID = 29";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                int kID = rs.getInt("korisnikID");
                String kIme = rs.getString("ime");
                String kPrezime = rs.getString("prezime");
                //TREBA DA DOHVATIM OBLAST datog RADA i ocenu iz recezent_oblast_ocena
//                String upit2 = "SELECT * konf_rad_autor WHERE radID =";
                int kocena = rs.getInt("ocena");
                String knaziv = rs.getString("naziv");
                
                //TREBA OVDE !=0
                String upit2 = "SELECT COUNT(*) AS broj FROM konf_rad_recezent "
                             + "WHERE konf_rad_recezent.radID != 0 "
                             + "AND konf_rad_recezent.korisnikID = "+ kID +";";
                
                int kbroj = 0;
                ResultSet rs2 = stmt2.executeQuery(upit2);
                if(rs2.next()){
                    kbroj = rs2.getInt("broj");
                }    
                //Recezent rec = new Recezent(kID,kIme,kPrezime);
                Recezent rec = new Recezent(kID,kIme,kPrezime,kocena,knaziv,kbroj);
                //Recezent rec = new Recezent(kID,kIme,kPrezime,kocena,knaziv);
                
                //TREBA DA NE PRIKAZUJEM DUPLE REZULTATE IZ KONF_RAD_RECEZENT
                //GROUP BY!

                recezentiK.add(rec);

            }
            stmt.close();stmt2.close();stmt3.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recezentiK;
    }
    public String dodajRecezente() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            

            
            Recezent koris;
            for(Iterator<Recezent> iterator = selektovaniRecezenti.iterator(); iterator.hasNext();){
                koris = iterator.next();
                
                if(koris.getBr_radova()>5){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnik ima prijavljen vise od 5radova!", ""));
                }
                if(koris != null){
                    String upit3 = "INSERT INTO konf_rad_recezent(konfID,korisnikID,radID,rok) values(?,?,?,?);";
                    PreparedStatement ps3 = con.prepareStatement(upit3);
                    ps3.setInt(1, Integer.parseInt(paramKonfID));
                    ps3.setInt(2, koris.getKorisnikID());
                    ps3.setInt(3,Integer.parseInt(paramRadID)); //stavljam ID Rada
                    
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(rok_recenzije);
                    Date rok_recenzije = new Timestamp(cal.getTime().getTime());
                    
                    java.util.Date utilDate = new java.util.Date();
                    utilDate = rok_recenzije;
                    java.sql.Date sqlDate_rok = new java.sql.Date(utilDate.getTime());
                    
                    ps3.setDate(4, sqlDate_rok);
                    
                    int affectedRows = ps3.executeUpdate();
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
         //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno dodati recezenti!", ""));
         return "pocetnaAdmin";
    }
    

}
