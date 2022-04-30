/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interface that is implemented by all domain classes. It contains methods
 * which ease the execution of system operations and cuts off some sufficient
 * code.
 * 
 * @author Dule Djo
 */
public interface OpstiDomenskiObjekat extends Serializable {
	/**
	 * Returns a String value that represents the database table name for specific
	 * object.
	 * 
	 * @return Database table name as a String.
	 */
	public String getTableName();

	/**
	 * Returns a list of objects read from the given result set.
	 * 
	 * @param resultSet Result set which represents a SQL query result.
	 * @return List of objects read from the given result set.
	 * @throws java.lang.Exception if an unmatched key value is provided.
	 */
	public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception;

	/**
	 * Returns a list of class objects, if the class has a List type attribute. If
	 * not, it returns null.
	 * @param resultSet Result set which represents a SQL query result.
	 * @return List of objects equal to the class attribute.
	 * @throws java.lang.Exception if an unmatched key value is provided.
	 */
	public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception;

	/**
	 * Returns a String value which represents a list of column names from the
	 * belonging database table.
	 * 
	 * @return Database column names as a String.
	 */
	public String getAttributeNames();

	/**
	 * Returns a String value which represents a list of as many question marks
	 * (divided by commas) as the number of column names from the belonging database
	 * table.
	 * 
	 * @return List of question marks as a String.
	 */
	public String getUnknownValues();

	/**
	 * Prepares an SQL statement on the basis of the given entity object.
	 * 
	 * @param ps     Prepared statement which represents precompiled empty SQL
	 *               statement.
	 * @param entity OpstiDomenskiObjekat which represents a single instance of
	 *               domain class.
	 * @throws java.lang.Exception if an entity is null.
	 */
	public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception;

	/**
	 * Returns a String value which represents a list of as many question marks
	 * (divided by commas) as the number of column names from the belonging database
	 * table in a format "columnName=?"
	 * 
	 * @return List of column names and question marks as a String.
	 */
	public String getUpdateQuery();

	/**
	 * Returns a String value that represents the primary key column name from the
	 * database table.
	 * 
	 * @param entity OpstiDomenskiObjekat which represents an instance of domain
	 *               class
	 * @return Primary key column name as a String.
	 */
	public String getID(OpstiDomenskiObjekat entity);

	/**
	 * Returns a String value that represents a custom SQL statement, unique for
	 * every domain class.
	 * 
	 * @param entity OpstiDomenskiObjekat which represents an instance of domain
	 *               class
	 * @return SQL statement as a String.
	 */
	public String getCondition(OpstiDomenskiObjekat entity);

	/**
	 * Returns a String value that represents a custom SQL order condition, unique
	 * for every domain class.
	 * 
	 * @return Order condition as a String.
	 */
	public String getOrderCondition();

}
