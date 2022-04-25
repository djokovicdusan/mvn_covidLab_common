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
 * Class that represents a model for Covid 19 laboratory test.
 * 
 * @author Dule Djo
 */
public class Test implements OpstiDomenskiObjekat {
	/**
	 * Test's id.
	 */
	Long testId;
	/**
	 * Test's name.
	 */
	String naziv;
	/**
	 * Test's description.
	 */
	String opis;
	/**
	 * Test's parent category.
	 */
	String vrsta;
	/**
	 * Test's manual and precaution.
	 */
	String uputstvoZaUpotrebu;

	/**
	 * Parameterized constructor used for initialization of Test type objects and
	 * setting them to given values.
	 * 
	 * @param testId             Test's id as a Long.
	 * @param naziv              Test's name as a String.
	 * @param opis               Test's description name as a String.
	 * @param vrsta              Test's category as a Date.
	 * @param uputstvoZaUpotrebu Test's manual and precaution as a String.
	 */
	public Test(Long testId, String naziv, String opis, String vrsta, String uputstvoZaUpotrebu) {
		this.testId = testId;
		this.naziv = naziv;
		this.opis = opis;
		this.vrsta = vrsta;
		this.uputstvoZaUpotrebu = uputstvoZaUpotrebu;
	}

	/**
	 * Non-parameterized constructor used for initialization of Test type
	 * objects.
	 */
	public Test() {
	}

	/**
	 * Returns Test's id.
	 * 
	 * @return testId id as a Long.
	 */
	public Long getTestId() {
		return testId;
	}

	/**
	 * Sets Test's id.
	 * 
	 * @param testId id as a Long.
	 */
	public void setTestId(Long testId) {
		this.testId = testId;
	}

	/**
	 * Returns Test's name.
	 * 
	 * @return naziv name as a String.
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Sets Test's name.
	 * 
	 * @param naziv name as a String.
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Returns Test's description.
	 * 
	 * @return opis Description as a String.
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * Sets Test's description.
	 * 
	 * @param opis Description as a String.
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}

	/**
	 * Returns Test's category.
	 * 
	 * @return vrsta Category as a String.
	 */
	public String getVrsta() {
		return vrsta;
	}

	/**
	 * Sets Test's category.
	 * 
	 * @param vrsta Category as a String.
	 */
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	/**
	 * Returns Test's manual.
	 * 
	 * @return uputstvoZaUpotrebu Manual as a String.
	 */
	public String getUputstvoZaUpotrebu() {
		return uputstvoZaUpotrebu;
	}

	/**
	 * Sets Test's manual.
	 * 
	 * @param uputstvoZaUpotrebu Manual as a String.
	 */
	public void setUputstvoZaUpotrebu(String uputstvoZaUpotrebu) {
		this.uputstvoZaUpotrebu = uputstvoZaUpotrebu;
	}

	@Override
	public int hashCode() {
		int hash = 5;
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
		final Test other = (Test) obj;
		if (this.testId != other.testId) {
			return false;
		}
		return true;
	}

	/**
	 * Returns Test's name.
	 * 
	 * @return naziv Name as a String.
	 */
	@Override
	public String toString() {
		return naziv;
	}

	@Override
	public String getTableName() {
		return "test";
	}

	@Override
	public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
		List<OpstiDomenskiObjekat> list = new LinkedList<>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String naziv = resultSet.getString("naziv");
			String opis = resultSet.getString("opis");
			String vrsta = resultSet.getString("vrsta");
			String uputstvoZaUpotrebu = resultSet.getString("uputstvoZaUpotrebu");

			Test t = new Test(id, naziv, opis, vrsta, uputstvoZaUpotrebu);
			list.add(t);

		}
		return list;
	}

	@Override
	public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
		OpstiDomenskiObjekat generalEntity = null;
		if (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String naziv = resultSet.getString("naziv");
			String opis = resultSet.getString("opis");
			String vrsta = resultSet.getString("vrsta");
			String uputstvoZaUpotrebu = resultSet.getString("uputstvoZaUpotrebu");

			generalEntity = new Test(id, naziv, opis, vrsta, uputstvoZaUpotrebu);
			return generalEntity;
		}
		return null;
	}

	@Override
	public String getAttributeNames() {
		return "naziv,opis,vrsta,uputstvoZaUpotrebu";
	}

	@Override
	public String getUnknownValues() {
		return "?,?,?,?";
	}

	@Override
	public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
		Test t = (Test) odo;
		ps.setString(1, t.getNaziv());
		ps.setString(2, t.getOpis());
		ps.setString(3, t.getVrsta());
		ps.setString(4, t.getUputstvoZaUpotrebu());
	}

	@Override
	public String getUpdateQuery() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getID(OpstiDomenskiObjekat odo) {
		Test t = (Test) odo;
		return String.valueOf(t.getTestId());
	}

	@Override
	public String getCondition(OpstiDomenskiObjekat entity) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public String getOrderCondition() {
		return "naziv";
	}

}
