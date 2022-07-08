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
 * Class that represents a model for a laboratory patient.
 * 
 * @author Dule Djo
 */
public class Pacijent implements OpstiDomenskiObjekat {
	/**
	 * Patient's id.
	 */
	Long pacijentId;
	/**
	 * Patient's first name.
	 */
	String ime;
	/**
	 * Patient's last name.
	 */
	String prezime;
	/**
	 * Patient's date of birth.
	 */
	Date datumRodjenja;
	/**
	 * Patient's phone number.
	 */
	String telefon;
	/**
	 * Patient's email.
	 */
	String email;
	/**
	 * Patient's choosen laborant.
	 */
	Laborant laborant;

	/**
	 * Parameterized constructor used for initialization of Patient type objects and
	 * setting them to given values.
	 * 
	 * @param pacijentId    Patient's id as a Long.
	 * @param ime           Patient's first name as a String.
	 * @param prezime       Patient's last name as a String.
	 * @param datumRodjenja Patient's office number as a Date.
	 * @param telefon       Patient's phone number as a String.
	 * @param email         Patient's email as a String.
	 * @param Laborant      Patient's choosen laborant as domen.Laborant.
	 */
	public Pacijent(Long pacijentId, String ime, String prezime, Date datumRodjenja, String telefon, String email,
			Laborant laborant) {
		this.pacijentId = pacijentId;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.email = email;
		this.laborant = laborant;
	}

	/**
	 * Non-parameterized constructor used for initialization of Pacient type
	 * objects.
	 */
	public Pacijent() {
	}

	/**
	 * Returns Patient's id.
	 * 
	 * @return pacijentId id as a Long.
	 */
	public Long getPacijentId() {
		return pacijentId;
	}

	/**
	 * Sets Patient's id.
	 * 
	 * @param pacijentId id as a Long.
	 */
	public void setPacijentId(Long pacijentId) {
		this.pacijentId = pacijentId;
	}

	/**
	 * Returns Patient's first name.
	 * 
	 * @return ime First name as a String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Sets Patient's first name.
	 * 
	 * @param ime First name as a String.
	 */
	public void setIme(String ime) {
//		if (ime.length() < 2)
//			throw new IllegalArgumentException("Ime mora sadrzati bar 2 znaka");
		this.ime = ime;
	}


	/**
	 * Returns Patient's last name.
	 * 
	 * @return prezime Last name as a String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Sets Patient's last name.
	 * 
	 * @param prezime Last name as a String.
	 */
	public void setPrezime(String prezime) {
//		if (prezime.length() < 2)
//			throw new IllegalArgumentException("Prezime mora sadrzati bar 2 znaka");
		this.prezime = prezime;
	}

	/**
	 * Returns Patient's date of birth.
	 * 
	 * @return datumRodjenja Date of birth as a Date.
	 */
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	/**
	 * Sets Patient's date of birth.
	 * 
	 * @param datumRodjenja Date of birth as a Date.
	 */
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	/**
	 * Returns Patient's phone number.
	 * 
	 * @return telefon Phone number as a String.
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Sets Patient's phone number.
	 * 
	 * @param telefon Phone number as a String.
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * Returns Patient's email.
	 * 
	 * @return email as a String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets Patient's email.
	 * 
	 * @param email as a String.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns Patient's choose laborant.
	 * 
	 * @return laborant as a Laborant.
	 */
	public Laborant getLaborant() {
		return laborant;
	}

	/**
	 * Sets Patient's choose laborant.
	 * 
	 * @param laborant as a Laborant.
	 */
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
