package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Leon
 */
@ManagedBean
@RequestScoped
public class PromenaLozinkeBean {
    private String korisnickoIme;
    private String staraLozinka;
    private String novaLozinka;
    private String potvrdaLozinke;

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getNovaLozinka() {
        return novaLozinka;
    }

    public void setNovaLozinka(String novaLozinka) {
        this.novaLozinka = novaLozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }
    
    private boolean confirmPassword() {
        if (novaLozinka.equals(potvrdaLozinke)) return true;
        return false;
    }
    
    public String promeniLoz() {
        if (novaLozinka.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinka mora imati najmanje 3 znaka.", ""));
        } else {
            if (confirmPassword()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
                    String upit = "SELECT * FROM korisnik WHERE username = ? and password = ?";    //Koristim PreparedStatement da bih se zastitio od SQL injection napada
                    PreparedStatement ps = con.prepareStatement(upit);
                    ps.setString(1, korisnickoIme);
                    ps.setString(2, staraLozinka);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        if(novaLozinka.equals(staraLozinka)){
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinka je ista kao stara lozinka", ""));
                            return "index";
                        }
                            
                        upit = "UPDATE korisnik SET password = ? WHERE username = ?;";  //Koristim PreparedStatement da bih se zastitio od SQL injection napada
                        PreparedStatement ps1 = con.prepareStatement(upit);
                        ps1.setString(1, novaLozinka);
                        ps1.setString(2, korisnickoIme);
                        ps1.executeUpdate();
                        ps1.close();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspesno ste promenili lozinku."));
                        RequestContext.getCurrentInstance().execute("PF('prom').hide()");
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravno korisnicko ime i/ili lozinka.", ""));
                    }
                    ps.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinke nisu identicne.", ""));
            }
        }
        return "index";
    }
    
}
