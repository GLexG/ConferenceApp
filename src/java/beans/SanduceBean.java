package beans;

import db.Poruka;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class SanduceBean {
    @ManagedProperty(value = "#{indexBean.korisnikID}")
    private int korisnikID;
    private List<Poruka> poruke;

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }


    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(List<Poruka> poruke) {
        this.poruke = poruke;
    }
    
    @PostConstruct
    public void ucitajPoruke() {
        poruke = new ArrayList<Poruka>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
            Statement stmt = con.createStatement();
            String upit = "SELECT * from poruka WHERE korisnikID = " + korisnikID + ";";
            ResultSet rs = stmt.executeQuery(upit);
            while (rs.next()) {
                String naslov = rs.getString("naslov");
                String tekst = rs.getString("tekst");
                //int radID = rs.getInt("radID");
                
                
                Poruka poruka = new Poruka(korisnikID, tekst, naslov);
                poruke.add(poruka);
            }
            Collections.reverse(poruke);    //Da bi najnovija poruka bila na vrhu
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
