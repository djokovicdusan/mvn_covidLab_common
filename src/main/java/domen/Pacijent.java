/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dule Djo
 */
public class Pacijent implements OpstiDomenskiObjekat {

    Long pacijentId;
    String ime;
    String prezime;
    Date datumRodjenja;
    String telefon;
    String email;
    Laborant laborant;

    public Pacijent(Long pacijentId, String ime, String prezime, Date datumRodjenja, String telefon, String email, Laborant laborant) {
        this.pacijentId = pacijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.email = email;
        this.laborant = laborant;
    }

    public Pacijent() {
    }

    public Long getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(Long pacijentId) {
        this.pacijentId = pacijentId;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
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

    public Laborant getLaborant() {
        return laborant;
    }

    public void setLaborant(Laborant laborant) {
        this.laborant = laborant;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pacijent other = (Pacijent) obj;
        if (this.pacijentId != other.pacijentId) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "pacijent";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            Date datumRodjenja = new Date(resultSet.getDate("datumRodjenja").getTime());
            String telefon = resultSet.getString("telefon");
            String email = resultSet.getString("email");

            Long laborantId = resultSet.getLong("laborantId");
            Laborant laborant = new Laborant();
            laborant.setLaborantId(laborantId);

            Pacijent p = new Pacijent(id, ime, prezime, datumRodjenja, telefon, email, laborant);
            list.add(p);

        }
        return list;
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat odo = null;
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            Date datumRodjenja = new Date(resultSet.getDate("datumRodjenja").getTime());
            String telefon = resultSet.getString("telefon");
            String email = resultSet.getString("email");

            Long laborantId = resultSet.getLong("laborantId");
            Laborant laborant = new Laborant();
            laborant.setLaborantId(laborantId);

            odo = new Pacijent(id, ime, prezime, datumRodjenja, telefon, email, laborant);
            return odo;

        }
        return null;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,datumRodjenja,telefon,email,laborantId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
        Pacijent p = (Pacijent) odo;
        ps.setString(1, p.getIme());
        ps.setString(2, p.getPrezime());
        ps.setDate(3, new java.sql.Date(p.getDatumRodjenja().getTime()));
        ps.setString(4, p.getTelefon());
        ps.setString(5, p.getEmail());
        ps.setLong(6, p.getLaborant().getLaborantId());
    }

    @Override
    public String getUpdateQuery() {
        return "ime=?,prezime=?,datumRodjenja=?,telefon=?,email=?,laborantId=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat odo) {
        Pacijent p = (Pacijent) odo;
        return String.valueOf(p.getPacijentId());
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat odo) {
        Pacijent pacijent = (Pacijent) odo;
        String ime = "";
        String prezime = "";
        if (pacijent.getIme() != null) {
            ime = pacijent.getIme();
        }
        if (pacijent.getPrezime() != null) {
            prezime = pacijent.getPrezime();
        }
        if (pacijent.getLaborant() == null) {
            return "ime LIKE '" + ime + "%' AND prezime LIKE '" + prezime + "%'";
        } else {
            return "laborantId=" + pacijent.getLaborant().getLaborantId();
        }
    }

    @Override
    public String getOrderCondition() {
        return "ime";
    }

}
