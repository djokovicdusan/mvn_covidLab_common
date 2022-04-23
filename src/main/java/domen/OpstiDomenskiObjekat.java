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
 *
 * @author Dule Djo
 */
public interface OpstiDomenskiObjekat extends Serializable{
    
    public String getTableName();
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception;
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception;
    public String getAttributeNames();
    public String getUnknownValues();
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat entity) throws Exception;
    public String getUpdateQuery();
    public String getID(OpstiDomenskiObjekat entity);
    public String getCondition(OpstiDomenskiObjekat entity);
    public String getOrderCondition();
    
}
