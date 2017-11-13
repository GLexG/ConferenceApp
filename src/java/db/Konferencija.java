/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;

/**
 *
 * @author Leon
 */
public class Konferencija {
    private int konfID;
    private String naziv;
    private String mesto;
    private Date vreme_odrzavanja;
    private Date vreme_od;
    private Date vreme_do;
    private int maxrad; 
    
    private Date rok;
    private String ocena_status;
    
    private String statusK;
   

    public Konferencija(int konfID, String naziv, String mesto, Date vreme_odrzavanja, Date vreme_od, Date vreme_do, int maxrad, String statusK) {
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_odrzavanja = vreme_odrzavanja;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
        this.statusK = statusK;
    }
    
    
    
    public Konferencija(int konfID, String naziv, String mesto, Date vreme_odrzavanja, Date vreme_od, Date vreme_do, int maxrad, Date rok, String ocena_status) {
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_odrzavanja = vreme_odrzavanja;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
        this.rok = rok;
        this.ocena_status = ocena_status;
    }

    
    

    public Konferencija(int konfID, String naziv, String mesto, Date vreme_odrzavanja, Date vreme_od, Date vreme_do, int maxrad, Date rok) {
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_odrzavanja = vreme_odrzavanja;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
        this.rok = rok;
    }
    
    
    public Konferencija(int konfID, String naziv, String mesto, Date vreme_od, Date vreme_do, int maxrad) {
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
    }

    public Konferencija(String naziv, String mesto, Date vreme_od, Date vreme_do, int maxrad) {
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
    }

    public Konferencija(int konfID, String naziv, String mesto, Date vreme_odrzavanja, Date vreme_od, Date vreme_do, int maxrad) {
        this.konfID = konfID;
        this.naziv = naziv;
        this.mesto = mesto;
        this.vreme_odrzavanja = vreme_odrzavanja;
        this.vreme_od = vreme_od;
        this.vreme_do = vreme_do;
        this.maxrad = maxrad;
    }

    public String getStatusK() {
        return statusK;
    }

    public void setStatusK(String statusK) {
        this.statusK = statusK;
    }
    
    
    
    public Date getVreme_odrzavanja() {
        return vreme_odrzavanja;
    }

    public void setVreme_odrzavanja(Date vreme_odrzavanja) {
        this.vreme_odrzavanja = vreme_odrzavanja;
    }

    public Date getRok() {
        return rok;
    }

    public void setRok(Date rok) {
        this.rok = rok;
    }

    public String getOcena_status() {
        return ocena_status;
    }

    public void setOcena_status(String ocena_status) {
        this.ocena_status = ocena_status;
    }
    
    
    

    public int getKonfID() {
        return konfID;
    }

    public void setKonfID(int konfID) {
        this.konfID = konfID;
    }
    
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getVreme_od() {
        return vreme_od;
    }

    public void setVreme_od(Date vreme_od) {
        this.vreme_od = vreme_od;
    }

    public Date getVreme_do() {
        return vreme_do;
    }

    public void setVreme_do(Date vreme_do) {
        this.vreme_do = vreme_do;
    }

    public int getMaxrad() {
        return maxrad;
    }

    public void setMaxrad(int maxrad) {
        this.maxrad = maxrad;
    }
    
    
}
