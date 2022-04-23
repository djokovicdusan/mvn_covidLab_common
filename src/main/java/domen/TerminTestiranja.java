/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dule Djo
 */
public class TerminTestiranja implements OpstiDomenskiObjekat {

    Long terminTestiranjaId;
    Date datum;
    String napomena;
    Pacijent pacijent;
    Laborant laborant;

    public TerminTestiranja(Long terminTestiranjaId, Date datum, String napomena, Pacijent pacijent, Laborant laborant) {
        this.terminTestiranjaId = terminTestiranjaId;
        this.datum = datum;
        this.napomena = napomena;
        this.pacijent = pacijent;
        this.laborant = laborant;
    }

    public TerminTestiranja() {
    }

    public Long getTerminTestiranjaId() {
        return terminTestiranjaId;
    }

    public void setTerminTestiranjaId(Long terminTestiranjaId) {
        this.terminTestiranjaId = terminTestiranjaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Laborant getLaborant() {
        return laborant;
    }

    public void setLaborant(Laborant laborant) {
        this.laborant = laborant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd.MM.yyyy");
        String noviDatum = sdf.format(datum);

        return noviDatum + ":" + pacijent + "; laborant " + laborant;
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
        final TerminTestiranja other = (TerminTestiranja) obj;
        if (this.terminTestiranjaId != other.terminTestiranjaId) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "termintestiranja";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Date datum = new Date(resultSet.getDate("datum").getTime());
            String napomena = resultSet.getString("napomena");

            Long pacijentId = resultSet.getLong("pacijentId");
            Pacijent pacijent = new Pacijent();
            pacijent.setPacijentId(pacijentId);

            Long laborantId = resultSet.getLong("laborantId");
            Laborant laborant = new Laborant();
            laborant.setLaborantId(laborantId);

            TerminTestiranja terminTestiranja = new TerminTestiranja(id, datum, napomena, pacijent, laborant);
            list.add(terminTestiranja);

        }
        return list;
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat odo = null;
        if (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Date datum = new Date(resultSet.getDate("datum").getTime());
            String napomena = resultSet.getString("napomena");

            Long pacijentId = resultSet.getLong("pacijentId");
            Pacijent pacijent = new Pacijent();
            pacijent.setPacijentId(pacijentId);

            Long laborantId = resultSet.getLong("laborantId");
            Laborant laborant = new Laborant();
            laborant.setLaborantId(laborantId);

            odo = new TerminTestiranja(id, datum, napomena, pacijent, laborant);
            return odo;
        }
        return null;
    }

    @Override
    public String getAttributeNames() {
        return "datum,napomena,pacijentId,laborantId";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
        TerminTestiranja terminTestiranja = (TerminTestiranja) odo;
        ps.setDate(1, new java.sql.Date(terminTestiranja.getDatum().getTime()));
        ps.setString(2, terminTestiranja.getNapomena());
        ps.setLong(3, terminTestiranja.getPacijent().getPacijentId());
        ps.setLong(4, terminTestiranja.getLaborant().getLaborantId());

    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID(OpstiDomenskiObjekat odo) {
        TerminTestiranja tt = (TerminTestiranja) odo;
        return String.valueOf(tt.getTerminTestiranjaId());
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOrderCondition() {
        return "id";
    }

}
