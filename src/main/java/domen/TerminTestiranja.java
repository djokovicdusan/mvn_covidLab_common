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
 * Class that represents a model for a Covid 19 laboratory testing scheduled
 * appointment.
 * 
 * @author Dule Djo
 */
public class TerminTestiranja implements OpstiDomenskiObjekat {
	/**
	 * TerminTestiranja id.
	 */
	Long terminTestiranjaId;
	/**
	 * TerminTestiranja appointment date.
	 */
	Date datum;
	/**
	 * TerminTestiranja footnote.
	 */
	String napomena;
	/**
	 * TerminTestiranja patient that scheduled an appointement.
	 */
	Pacijent pacijent;
	/**
	 * TerminTestiranja laborant that will take care of the procedure.
	 */
	Laborant laborant;

	/**
	 * Parameterized constructor used for initialization of TerminTestiranja type
	 * objects and setting them to given values.
	 * 
	 * @param terminTestiranjaId TerminTestiranja id as a Long.
	 * @param datum              TerminTestiranja office number as a Date.
	 * @param napomena           TerminTestiranja footnote as a String.
	 * @param pacijent           TerminTestiranja choosen patient as domen.Pacijent.
	 * @param laborant           TerminTestiranja choosen laborant as
	 *                           domen.Laborant.
	 */
	public TerminTestiranja(Long terminTestiranjaId, Date datum, String napomena, Pacijent pacijent,
			Laborant laborant) {
		this.terminTestiranjaId = terminTestiranjaId;
		this.datum = datum;
		this.napomena = napomena;
		this.pacijent = pacijent;
		this.laborant = laborant;
	}

	/**
	 * Non-parameterized constructor used for initialization of TerminTestiranja
	 * type objects.
	 */
	public TerminTestiranja() {
	}

	/**
	 * Returns TerminTestiranja id.
	 * 
	 * @return terminTestiranjaId id as a Long.
	 */
	public Long getTerminTestiranjaId() {
		return terminTestiranjaId;
	}

	/**
	 * Sets TerminTestiranja id.
	 * 
	 * @param terminTestiranjaId id as a Long.
	 */
	public void setTerminTestiranjaId(Long terminTestiranjaId) {
		this.terminTestiranjaId = terminTestiranjaId;
	}

	/**
	 * Returns TerminTestiranja date.
	 * 
	 * @return datum Date as Date.
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * Sets TerminTestiranja date.
	 * 
	 * @param datum Date as Date.
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * Returns TerminTestiranja footnote.
	 * 
	 * @return napomena Footnote as a String.
	 */
	public String getNapomena() {
		return napomena;
	}

	/**
	 * Sets TerminTestiranja footnote.
	 * 
	 * @param napomena Footnote as a String.
	 */
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	/**
	 * Returns TerminTestiranja patient.
	 * 
	 * @return pacijent Patient as Pacijent.
	 */
	public Pacijent getPacijent() {
		return pacijent;
	}

	/**
	 * Sets TerminTestiranja patient.
	 * 
	 * @param pacijent Patient as Pacijent.
	 */
	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	/**
	 * Returns TerminTestiranja laborant.
	 * 
	 * @return laborant as Laborant.
	 */
	public Laborant getLaborant() {
		return laborant;
	}

	/**
	 * Sets TerminTestiranja laborant.
	 * 
	 * @param laborant as Laborant.
	 */
	public void setLaborant(Laborant laborant) {
		this.laborant = laborant;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}
	/**
	 * Returns TerminTestiranja info as a custom string.
	 * 
	 * @return String.
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getID(OpstiDomenskiObjekat odo) {
		TerminTestiranja tt = (TerminTestiranja) odo;
		return String.valueOf(tt.getTerminTestiranjaId());
	}

	@Override
	public String getCondition(OpstiDomenskiObjekat entity) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getOrderCondition() {
		return "id";
	}

}
