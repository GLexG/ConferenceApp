/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Korisnik;
import db.Oblast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class PopuniOblastBean {
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    
    private String konfID;
    private int oblastID;
    private String naziv;
    private String knaziv;
    private int ocena;
    
    private List<Oblast> sveOblasti;

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }
    
    public String getKonfID() {
        return konfID;
    }

    public void setKonfID(String konfID) {
        this.konfID = konfID;
    }

    public int getOblastID() {
        return oblastID;
    }

    public void setOblastID(int oblastID) {
        this.oblastID = oblastID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKnaziv() {
        return knaziv;
    }

    public void setKnaziv(String knaziv) {
        this.knaziv = knaziv;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public List<Oblast> getSveOblasti() {
        return sveOblasti;
    }

    public void setSveOblasti(List<Oblast> sveOblasti) {
        this.sveOblasti = sveOblasti;
    }
    
    @PostConstruct 
    public void ucitajOblasti() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        konfID = request.getParameter("konfID");
        
        sveOblasti = new ArrayList<Oblast>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * FROM konf_oblast " +
                            "LEFT JOIN oblast ON oblast.oblastID = konf_oblast.oblastID " +
                            "LEFT JOIN konferencija ON konferencija.konfID = konf_oblast.konfID " +
                            //"WHERE konf_oblast.konfID = 31";
                            "WHERE konf_oblast.konfID = "+konfID+";";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                naziv = rs.getString("naziv");
                knaziv = rs.getString("knaziv");
                oblastID = rs.getInt("oblastID");
                
                ocena = 1;
                
                Oblast oblast = new Oblast(oblastID,naziv,knaziv,ocena);
                sveOblasti.add(oblast);
            }
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public String oceniOblast() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            
            
//            String upit = "UPDATE rad SET naslov = ?, kljucnereci = ?, apstrakt = ?, oblastID = ?, fileurl = ?, status = ?, listaautora = ? WHERE radID= ?;";
//            PreparedStatement ps = con.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
//            
//            String upit2 = "SELECT oblastID FROM oblast WHERE naziv = '" + selektovanaOblast + "';";
//            ResultSet rs1 = stmt1.executeQuery(upit2);
//            if (rs1.next()) { oblastID = rs1.getInt("oblastID");}
//            
//            String upit4 = "SELECT konfID FROM konferencija WHERE knaziv = '" + selektovanaKonferencija + "';";
//            ResultSet rs3 = stmt2.executeQuery(upit4);
//            if (rs3.next()) { konfID = rs3.getInt("konfID");}
            
            String upit = "INSERT into recezent_oblast_ocena (korisnikID, konfID, oblastID, ocena,status) values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(upit);
            
            Oblast oblast;

            for(Iterator<Oblast> iterator = sveOblasti.iterator(); iterator.hasNext();){
                oblast = iterator.next();
                if(oblast != null){
                    ocena = oblast.getOcena();
                    oblastID = oblast.getOblastID();
                    
                    ps.setInt(1, korisnikID);
                    ps.setInt(2, Integer.parseInt(konfID));
                    ps.setInt(3, oblastID);
                    ps.setInt(4, ocena);
                    
                    ps.setString(5,"OCENJEN");
                    
                    //String upit2 = "SELECT * FROM recezent_oblast_ocena WHERE"
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("UPDATE OCENE failed, no rows affected.");
                    }
                }
            }


            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesan Update", "Uspesan Update"));
        return "pregledKRecezent";
        
    }
    
}
