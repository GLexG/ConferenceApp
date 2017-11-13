/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Formular;
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
public class RecenzijaRadaBean {
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
    private String status;
    
    private String statusF;
    
    //formular
    //radID,korisnikID
    private int relevantnost;
    private int originalnost;
    private int primenljivost;
    private int doprinost;
    private int publikovao;        
    private int konciznost;
    private int format;
    private int kvalitet;
    private int naslovodgovara;
    private int opstaocena;
    private int ukupnaocena;
    private int preporuka1;
    private int preporuka2;
    private int preporuka3;
    private int preporuka4;
    private int preporuka5;
    private String komentar1;
    private String komentar2;
    private String komentar3;
    private String komentar4;

    public String getStatusF() {
        return statusF;
    }

    public void setStatusF(String statusF) {
        this.statusF = statusF;
    }
    
    
    public int getRelevantnost() {
        return relevantnost;
    }

    public void setRelevantnost(int relevantnost) {
        this.relevantnost = relevantnost;
    }

    public int getOriginalnost() {
        return originalnost;
    }

    public void setOriginalnost(int originalnost) {
        this.originalnost = originalnost;
    }

    public int getPrimenljivost() {
        return primenljivost;
    }

    public void setPrimenljivost(int primenljivost) {
        this.primenljivost = primenljivost;
    }

    public int getDoprinost() {
        return doprinost;
    }

    public void setDoprinost(int doprinost) {
        this.doprinost = doprinost;
    }

    public int getPublikovao() {
        return publikovao;
    }

    public void setPublikovao(int publikovao) {
        this.publikovao = publikovao;
    }

    public int getKonciznost() {
        return konciznost;
    }

    public void setKonciznost(int konciznost) {
        this.konciznost = konciznost;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getKvalitet() {
        return kvalitet;
    }

    public void setKvalitet(int kvalitet) {
        this.kvalitet = kvalitet;
    }

    public int getNaslovodgovara() {
        return naslovodgovara;
    }

    public void setNaslovodgovara(int naslovodgovara) {
        this.naslovodgovara = naslovodgovara;
    }

    public int getOpstaocena() {
        return opstaocena;
    }

    public void setOpstaocena(int opstaocena) {
        this.opstaocena = opstaocena;
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

    public String getKomentar1() {
        return komentar1;
    }

    public void setKomentar1(String komentar1) {
        this.komentar1 = komentar1;
    }

    public String getKomentar2() {
        return komentar2;
    }

    public void setKomentar2(String komentar2) {
        this.komentar2 = komentar2;
    }

    public String getKomentar3() {
        return komentar3;
    }

    public void setKomentar3(String komentar3) {
        this.komentar3 = komentar3;
    }

    public String getKomentar4() {
        return komentar4;
    }

    public void setKomentar4(String komentar4) {
        this.komentar4 = komentar4;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @PostConstruct
    public void ucitajRecenziju() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        radID = request.getParameter("radID");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            String upit =   "SELECT * FROM rad " +
                            "LEFT JOIN oblast " +
                            "ON rad.oblastID=oblast.oblastID " +
                            "LEFT JOIN formular " +
                            "ON formular.radID = rad.radID " +
                            "WHERE rad.radID= "+ radID + ";";
                            //"WHERE rad.radID= "+ radID + ";";
             
            String upit4 =  "SELECT * FROM formular " +
                            "WHERE radID = " + radID + " AND korisnikID = " + korisnikID +";";
            
            ResultSet rs = stmt.executeQuery(upit);
            if(rs.next()){
                naslov = rs.getString("naslov");
                oblast = rs.getString("naziv");
            }
            
            String upit2 = "SELECT * FROM formular WHERE radID = " + radID + " AND korisnikID = "+ korisnikID +" ";
            ResultSet rs2 = stmt1.executeQuery(upit2);
            
        if(!rs2.next()){

            
            //inicijalizacija
            int pomRadID = Integer.parseInt(radID);
            relevantnost = 0;
            originalnost = 0;
            primenljivost = 0;
            doprinost = 0;
            publikovao = 0;
            konciznost = 0;
            format = 0;
            kvalitet = 0;
            naslovodgovara = 0;
            opstaocena = 0;
            ukupnaocena = 0;
            preporuka1 = 0;
            preporuka2 = 0;
            preporuka3 = 0;
            preporuka4 = 0;
            preporuka5 = 0;
            komentar1 = "Nema komentara";
            komentar2 = "Nema komentara";
            komentar3 = "Nema komentara";
            komentar4 = "Nema komentara";            
            
            //prvo popunim podacima random i posle selektujem
            
            String upit3 = "INSERT INTO formular(radID,korisnikID,relevantnost,originalnost,primenljivost,doprinost,publikovao,konciznost,format,kvalitet,naslovodgovara,opstaocena,ukupnaocena,preporuka1,preporuka2,preporuka3,preporuka4,preporuka5,komentar1,komentar2,komentar3,komentar4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            
            PreparedStatement ps3 = con.prepareStatement(upit3);
            ps3.setInt(1, pomRadID);ps3.setInt(2, korisnikID);ps3.setInt(3, relevantnost);ps3.setInt(4, originalnost);
            ps3.setInt(5, primenljivost);ps3.setInt(6, doprinost);ps3.setInt(7, publikovao);
            ps3.setInt(8, konciznost);ps3.setInt(9, format);ps3.setInt(10, kvalitet);
            ps3.setInt(11, naslovodgovara);ps3.setInt(12, opstaocena);ps3.setInt(13, ukupnaocena);
            ps3.setInt(14, preporuka1);ps3.setInt(15, preporuka2);ps3.setInt(16, preporuka3);
            ps3.setInt(17, preporuka4);ps3.setInt(18, preporuka5);ps3.setString(19, komentar1);
            ps3.setString(20, komentar2);ps3.setString(21, komentar3);ps3.setString(22, komentar4);

                    
            int affectedRows = ps3.executeUpdate();
            if (affectedRows == 0) {
                 throw new SQLException("Update failed, no rows affected.");
            }
            
            
        }else{
            ResultSet rs4 = stmt2.executeQuery(upit4);
            if(rs4.next()){
                
                relevantnost = rs4.getInt("relevantnost");

                originalnost = rs4.getInt("originalnost");
                primenljivost = rs4.getInt("primenljivost");
                doprinost = rs4.getInt("doprinost");
                publikovao = rs4.getInt("publikovao");
                konciznost = rs4.getInt("konciznost");
                format = rs4.getInt("format");
                kvalitet = rs4.getInt("kvalitet");
                naslovodgovara = rs4.getInt("naslovodgovara");
                opstaocena = rs4.getInt("opstaocena");
                ukupnaocena = rs4.getInt("ukupnaocena");
                preporuka1 = rs4.getInt("preporuka1");
                preporuka2 = rs4.getInt("preporuka2");
                preporuka3 = rs4.getInt("preporuka3");
                preporuka4 = rs4.getInt("preporuka4");
                preporuka5 = rs4.getInt("preporuka5");
                komentar1 = rs4.getString("komentar1");
                komentar2 = rs4.getString("komentar2");
                komentar3 = rs4.getString("komentar3");
                komentar4 = rs4.getString("komentar4");   
            
            int pomRadID = Integer.parseInt(radID);
            Formular formular = new Formular(pomRadID,korisnikID,relevantnost,originalnost,primenljivost,doprinost,publikovao,konciznost,format,kvalitet,naslovodgovara,opstaocena,ukupnaocena,preporuka1,preporuka2,preporuka3,preporuka4,preporuka5,komentar1,komentar2,komentar3,komentar4);
            }
        }

            
                    
                
            
            

            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void izmeniRecenziju() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            
           // String upit = "INSERT into RAD (naslov, kljucnereci, apstrakt, oblastID, fileurl, status) values (";
//            upit += "'" + newNaslov + "', ";
//            upit += "'" + newKljucneReci + "', ";
//            upit += "'" + newApstrakt + "', ";
//            upit += newOblastID + ", ";
//            upit += newOblastID + ", ";
            
            String upit = "UPDATE formular SET relevantnost = ?, originalnost = ?, primenljivost = ?, doprinost = ?, "
                        + "publikovao = ?, konciznost = ?, format = ?, kvalitet = ?, naslovodgovara = ?, opstaocena = ?, ukupnaocena = ?, "
                        + "preporuka1 = ?, preporuka2 = ?, preporuka3 = ?, preporuka4 = ?, preporuka5 = ?, komentar1 = ?, komentar2 = ?, komentar3 = ?, komentar4 = ? ,statusF = ?"
                        + "WHERE radID= "+ radID + " AND korisnikID =" + korisnikID +";";
            PreparedStatement ps = con.prepareStatement(upit);

            ukupnaocena = relevantnost+originalnost+primenljivost+doprinost+publikovao+konciznost+format+kvalitet+naslovodgovara+opstaocena;
            ps.setInt(1, relevantnost);
            ps.setInt(2, originalnost);
            ps.setInt(3, primenljivost);
            ps.setInt(4, doprinost);
            ps.setInt(5, publikovao);
            ps.setInt(6, konciznost);
            ps.setInt(7, format);
            ps.setInt(8, kvalitet);
            ps.setInt(9, naslovodgovara);
            ps.setInt(10, opstaocena);
            ps.setInt(11, ukupnaocena);
            ps.setInt(12, preporuka1);
            ps.setInt(13, preporuka2);
            ps.setInt(14, preporuka3);
            ps.setInt(15, preporuka4);
            ps.setInt(16, preporuka5);
            ps.setString(17, komentar1);
            ps.setString(18, komentar2);
            ps.setString(19, komentar3);
            ps.setString(20, komentar4);
            ps.setString(21, "ZAPOCET");
            
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD failed, no rows affected.");
            }
            
            String upit2 = "UPDATE rad SET status = ? WHERE radID = "+ radID + ";";
            PreparedStatement ps2 = con.prepareStatement(upit2);
            ps2.setString(1, "NA RECENZIJI");
            affectedRows = ps2.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE STATUS failed, no rows affected.");
            }
            

            stmt.close();stmt1.close();stmt2.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesan Update", "Uspesan Update"));
        //return "pregledRRecezent";
    }
    
    public String posaljiRecenziju() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();


            
            String upit = "UPDATE formular SET statusF = ? "
                        + "WHERE radID= "+ radID + " AND korisnikID =" + korisnikID +";";
            PreparedStatement ps = con.prepareStatement(upit);

            ps.setString(1, "POSLAT");
            
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE RAD failed, no rows affected.");
            }
            
            //PROMENITI STATUS RADA!!!!!!!!!!!!! U VRACEN SA RECENZIJE
            //I U FORMULARAUADAS
            
            String upit7 = "UPDATE rad SET status = ? WHERE radID = "+ radID + ";";
            PreparedStatement ps7 = con.prepareStatement(upit7);
            ps7.setString(1, "VRACEN SA RECENZIJE");
            affectedRows = ps7.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("UPDATE STATUS failed, no rows affected.");
            }

            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesana Recenzija Rada", "Uspesana Recenzija Rada"));
        return "pregledRRecezent";
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
    

    
}
