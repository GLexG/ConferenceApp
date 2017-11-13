package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;




/**
 *
 * @author Leon
 */
@ManagedBean
@ViewScoped
public class RegistracijaBean implements Serializable {
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String potvrdaLozinke;
    private String telefon;
    private String email;
    private String datum;
    private UploadedFile slika;

    
    


    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }


    public UploadedFile getSlika() {
        return slika;
    }

    public void setSlika(UploadedFile slika) {
        this.slika = slika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    private boolean potvrdi() {
        if (lozinka.equals(potvrdaLozinke)) return true;
        return false;
    }
    
    
    public String registrujSe() {
        if (korisnickoIme.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnicko ime mora imati najmanje 3 znaka.", ""));
        } else if (lozinka.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinka mora imati najmanje 3 znaka.", ""));
//        } else if (slika.getFileName().isEmpty()) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste dodali sliku.", ""));
        } else {
            boolean greska = false;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pia_projekat_jun2016","root","");
                String upit = "select * from korisnik where username = ?;"; //Koristim PreparedStatement da bih se zastitio od SQL injection napada
                PreparedStatement ps = con.prepareStatement(upit);
                ps.setString(1, korisnickoIme);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {    //Username vec postoji
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnicko ime vec postoji.", ""));
                    greska = true;
                }
                ps.close();
                if (!potvrdi()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinke nisu identicne.", ""));
                    greska = true;
                }
                if (!greska) {
                    upit = "insert into korisnik (username, password, ime, prezime, telefon, email, datum, slika,is_autor,is_recezent) values (?,?,?,?,?,?,?,? ,?,?);";
                    PreparedStatement ps1 = con.prepareStatement(upit);
                    ps1.setString(1, korisnickoIme);
                    ps1.setString(2, lozinka);
                    ps1.setString(3, ime);
                    ps1.setString(4, prezime);
                    ps1.setString(5, telefon);
                    ps1.setString(6, email);
                    ps1.setString(7, datum);
                    //ps1.setString(8, slika.getFileName());
                    ps1.setString(8, "default.jpg");
                    ps1.setInt(9, 1);
                    ps1.setInt(10, 1);
//                    try {
//                         saveFile(slika.getFileName(), slika.getInputstream());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    
                    //image upload ----------------------
//        InputStream input = null;
//        OutputStream output = null;
//        
//        if (slika.getSize() > 0) {
//        
//            try {
//                input = slika.getInputstream();
//            } catch (IOException ex) {
//
//            }
//            //ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
//            //File dest = new File(extContext.getRealPath("//resources//uploads//" + newUser.getUsername()));
//            String path = "C:\\Users\\Leon\\Documents\\NetBeansProjects\\WebApplication1\\web\\recourses\\uploads\\" + korisnickoIme + ".jpg";
//            File dest = new File(path);
//            
//            try {
//                output = new FileOutputStream(dest);
//                IOUtils.copy(input, output);
//            } catch (Exception ex) {
//                hasError = true;
//                message = "Error with uploading image.";
//                return "registration.xhtml?faces-redirect=true";
//            } finally {
//                IOUtils.closeQuietly(input);
//            }
//
//           // String path = extContext.getRealPath("//resources//uploads//" + newUser.getUsername());
//            newUser.setPhoto(path);
//        }
                    
                    
                    
                    ps1.executeUpdate();
                    ps1.close();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspesno ste registrovani!"));
                    RequestContext.getCurrentInstance().execute("PF('reg').hide()");
                }
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "index";
    }
    
    public void upload(FileUploadEvent event) {
            UploadedFile uploadedFile = event.getFile();
            String fileName = uploadedFile.getFileName();
            String contentType = uploadedFile.getContentType();
            byte[] contents = uploadedFile.getContents(); // Or getInputStream()
            
            try {
                    saveFile(slika.getFileName(), slika.getInputstream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
    public void saveFile(String fileName, InputStream in) { //Sacuvaj sliku
        try {
            OutputStream out = new FileOutputStream(new File("../PIA_JUN_2016/web/resources/images/" + fileName));
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
