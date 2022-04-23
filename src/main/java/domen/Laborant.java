/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dule Djo
 */
public class Laborant implements OpstiDomenskiObjekat {

    Long laborantId;
    String ime;
    String prezime;
    int brojOrdinacije;

    public Laborant(Long laborantId, String ime, String prezime, int brojOrdinacije) {
        this.laborantId = laborantId;
        this.ime = ime;
        this.prezime = prezime;
        this.brojOrdinacije = brojOrdinacije;
    }

    public Laborant() {
    }
    

    public Long getLaborantId() {
        return laborantId;
    }

    public void setLaborantId(Long laborantId) {
        this.laborantId = laborantId;
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

    public int getBrojOrdinacije() {
        return brojOrdinacije;
    }

    public void setBrojOrdinacije(int brojOrdinacije) {
        this.brojOrdinacije = brojOrdinacije;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Laborant other = (Laborant) obj;
        if (this.laborantId != other.laborantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    public String toStringTest(){
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "laborant";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String ime = resultSet.getString("ime");
            String prezime = resultSet.getString("prezime");
            int brojOrdinacije = resultSet.getInt("brojOrdinacije");


            Laborant laborant = new Laborant(id, ime, prezime, brojOrdinacije);

            list.add(laborant);

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
            int brojOrdinacije = resultSet.getInt("brojOrdinacije");


            odo = new Laborant(id, ime, prezime, brojOrdinacije);
            return odo;
        }
        return null;
    }

    @Override
    public String getAttributeNames() {
        return "ime,prezime,brojOrdinacije";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
        Laborant laborant = (Laborant) odo;
        ps.setString(1, laborant.getIme());
        ps.setString(2, laborant.getPrezime());
        ps.setDouble(3, laborant.getBrojOrdinacije());
    }

    @Override
    public String getUpdateQuery() {
        return "ime=?,prezime=?,brojOrdinacije=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat odo) {
        Laborant laborant = (Laborant) odo;
        return String.valueOf(laborant.getLaborantId());
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat odo) {
        Laborant laborant = (Laborant) odo;
        String prezime = "";
        if (laborant.getPrezime() != null) {
            prezime = laborant.getPrezime();
        }
        return "prezime LIKE '" + prezime + "%'";
    }

    @Override
    public String getOrderCondition() {
        return "prezime";
    }

}
