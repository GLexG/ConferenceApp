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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class DodajOblastBean {
      
    private String selektovanaOblast;
    private String novaOblast;

    public String getNovaOblast() {
        return novaOblast;
    }

    public void setNovaOblast(String novaOblast) {
        this.novaOblast = novaOblast;
    }
    
    
    
    
    public String getSelektovanaOblast() {
        return selektovanaOblast;
    }

    public void setSelektovanaOblast(String selektovanaOblast) {
        this.selektovanaOblast = selektovanaOblast;
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
    
    public String dodajOblast(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "INSERT INTO oblast(naziv) values (?);";
            
            PreparedStatement ps = con.prepareStatement(upit);
            
            ps.setString(1, novaOblast);
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodata Oblast.", ""));
        return "dodajOblast";
        
    }
    
    
    
}
