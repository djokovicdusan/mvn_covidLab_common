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
import java.util.Objects;

/**
 * Class that represents a model for Covid 19 Labaratory system Administrator.
 * 
 * @author Dule Djo
 */
public class Administrator implements OpstiDomenskiObjekat {
	/**
	 * Administrator's username.
	 */
	String korisnickoIme;
	/**
	 * Administrator's password.
	 */
	String lozinka;
	/**
	 * Administrator's first name.
	 */
	String ime;
	/**
	 * Administrator's last name.
	 */
	String prezime;

	/**
	 * Non-parameterized constructor used for initialization of Administrator type
	 * objects.
	 */
	public Administrator() {
	}

	/**
	 * Parameterized constructor used for initialization of Administrator type
	 * objects and setting them to given values.
	 * 
	 * @param korisnickoIme Administrator's username as a String.
	 * @param lozinka       Administrator's password as a String.
	 * @param ime           Administrator's first name as a String.
	 * @param prezime       Administrator's last name as a String.
	 */
	public Administrator(String korisnickoIme, String lozinka, String ime, String prezime) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
	}

	/**
	 * Returns Administrator's username.
	 * 
	 * @return korisnickoIme Username as a String.
	 */
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	/**
	 * Sets the Administrator's username.
	 * 
	 * @param korisnickoIme Username as a String.
	 */
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	/**
	 * Returns Administrator's password.
	 * 
	 * @return lozinka Password as a String.
	 */
	public String getLozinka() {
		return lozinka;
	}

	/**
	 * Returns Administrator's first name.
	 * 
	 * @return ime First name as a String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Returns Administrator's last name.
	 * 
	 * @return prezime Last name as a String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Sets the Administrator's first name.
	 * 
	 * @param ime First name as a String.
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * Sets the Administrator's last name.
	 * 
	 * @param prezime Last name as a String.
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Sets the Administrator's password.
	 * 
	 * @param lozinka Password as a String.
	 */
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public int hashCode() {
		int hash = 7;
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
		final Administrator other = (Administrator) obj;
		if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
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

	@Override
	public String getTableName() {
		return "admin";
	}

	@Override
	public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
		List<OpstiDomenskiObjekat> list = new LinkedList<>();
		while (resultSet.next()) {
			String korisnickoIme = resultSet.getString("korisnickoime");
			String lozinka = resultSet.getString("lozinka");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			Administrator a = new Administrator(korisnickoIme, lozinka, ime, prezime);
			list.add(a);

		}
		return list;
	}

	@Override
	public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
		OpstiDomenskiObjekat odo = null;
		if (resultSet.next()) {
			String korisnickoIme = resultSet.getString("korisnickoime");
			String lozinka = resultSet.getString("lozinka");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			odo = new Administrator(korisnickoIme, lozinka, ime, prezime);
			return odo;

		}
		return null;
	}

	@Override
	public String getAttributeNames() {
		return "korisnickoIme,lozinka,ime,prezime";
	}

	@Override
	public String getUnknownValues() {
		return "?,?,?,?";
	}

	@Override
	public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
		Administrator a = (Administrator) odo;
		ps.setString(1, a.getKorisnickoIme());
		ps.setString(2, a.getLozinka());
		ps.setString(3, a.getIme());
		ps.setString(4, a.getPrezime());
	}

	@Override
	public String getUpdateQuery() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getID(OpstiDomenskiObjekat odo) {
		Administrator a = (Administrator) odo;
		return a.getKorisnickoIme();
	}

	@Override
	public String getCondition(OpstiDomenskiObjekat entity) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getOrderCondition() {
		return "username";
	}

}
