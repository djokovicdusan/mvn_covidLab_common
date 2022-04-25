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
 * Class that represents a model for a Laborant;
 * 
 * @author Dule Djo
 */
public class Laborant implements OpstiDomenskiObjekat {
	/**
	 * Laborant's id.
	 */
	Long laborantId;
	/**
	 * Laborant's first name.
	 */
	String ime;
	/**
	 * Laborant's last name.
	 */
	String prezime;
	/**
	 * Laborant's office number.
	 */
	int brojOrdinacije;

	/**
	 * Parameterized constructor used for initialization of Laborant type objects
	 * and setting them to given values.
	 * 
	 * @param laborantId     Laborant's id as a Long.
	 * @param ime            Laborant's first name as a String.
	 * @param prezime        Laborant's last name as a String.
	 * @param brojOrdinacije Laborant's office number as a int.
	 */
	public Laborant(Long laborantId, String ime, String prezime, int brojOrdinacije) {
		this.laborantId = laborantId;
		this.ime = ime;
		this.prezime = prezime;
		this.brojOrdinacije = brojOrdinacije;
	}

	/**
	 * Non-parameterized constructor used for initialization of Laborant type
	 * objects.
	 */
	public Laborant() {
	}

	/**
	 * Returns Laborant's id.
	 * 
	 * @return laborantId id as a Long.
	 */
	public Long getLaborantId() {
		return laborantId;
	}

	/**
	 * Sets the Laborant's id.
	 * 
	 * @param laborantId id as a Long.
	 */
	public void setLaborantId(Long laborantId) {
		this.laborantId = laborantId;
	}

	/**
	 * Returns Laborant's name.
	 * 
	 * @return ime Laborant's first name as a String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Sets the Laborant's first name.
	 * 
	 * @param ime First name as a String.
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * Returns Laborant's last name.
	 * 
	 * @return prezime Last name as a String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Sets the Laborant's last name.
	 * 
	 * @param prezime Last name as a String.
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Returns Laborant's office number.
	 * 
	 * @return brojOrdinacije Laborant's office number as a int.
	 */
	public int getBrojOrdinacije() {
		return brojOrdinacije;
	}

	/**
	 * Sets the Laborant's office number.
	 * 
	 * @param brojOrdinacije Laborant's office number as a int.
	 */
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

	/**
	 * Returns the Administrator's full name.
	 * 
	 * @return ime + prezime Full name as a String.
	 */
	@Override
	public String toString() {
		return ime + " " + prezime;
	}

	/**
	 * Returns the Administrator's full name.
	 * 
	 * @return ime + prezime Full name as a String.
	 */
	public String toStringTest() {
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
