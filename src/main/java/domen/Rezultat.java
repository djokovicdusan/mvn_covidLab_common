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
 *
 * @author Dule Djo
 */
public class Rezultat implements OpstiDomenskiObjekat {

    Test test;
    TerminTestiranja terminTestiranja;
    boolean vrednost;

    public Rezultat(Test test, TerminTestiranja terminTestiranja, boolean vrednost) {
        this.test = test;
        this.terminTestiranja = terminTestiranja;
        this.vrednost = vrednost;
    }

    public Rezultat() {
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public TerminTestiranja getTerminTestiranja() {
        return terminTestiranja;
    }

    public void setTerminTestiranja(TerminTestiranja terminTestiranja) {
        this.terminTestiranja = terminTestiranja;
    }

    public boolean isVrednost() {
        return vrednost;
    }

    public void setVrednost(boolean vrednost) {
        this.vrednost = vrednost;
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
        final Rezultat other = (Rezultat) obj;
        if (!Objects.equals(this.test, other.test)) {
            return false;
        }
        if (!Objects.equals(this.terminTestiranja, other.terminTestiranja)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rezultat{" + "test=" + test + ", terminTestiranja=" + terminTestiranja + ", vrednost=" + vrednost + '}';
    }
    

    @Override
    public String getTableName() {
        return "rezultat";
    }

    @Override
    public List<OpstiDomenskiObjekat> getList(ResultSet resultSet) throws Exception {
        List<OpstiDomenskiObjekat> list = new LinkedList<>();
        while (resultSet.next()) {
            Long testId = resultSet.getLong("testId");
            Test test = new Test();
            test.setTestId(testId);
            Long terminTestId = resultSet.getLong("terminTestiranjaId");
            TerminTestiranja tt = new TerminTestiranja();
            tt.setTerminTestiranjaId(terminTestId);
            // nadji pacijenta koji je pod tim terminom ???
            
            boolean vrednost = resultSet.getBoolean("vrednost");
//            System.out.println("vrednost je " + vrednost);

            Rezultat rezultat = new Rezultat(test, tt, vrednost);

            list.add(rezultat);

        }
        return list;
    }

    @Override
    public OpstiDomenskiObjekat getResult(ResultSet resultSet) throws Exception {
        OpstiDomenskiObjekat odo = null;
        if (resultSet.next()) {
            Long testId = resultSet.getLong("testId");
            Test test = new Test();
            test.setTestId(testId);
            Long terminTestId = resultSet.getLong("terminTestiranjaId");
            TerminTestiranja tt = new TerminTestiranja();
            tt.setTerminTestiranjaId(terminTestId);
            boolean vrednost = resultSet.getBoolean("vrednost");

            odo = new Rezultat(test, terminTestiranja, vrednost);

            return odo;
        }
        return null;
    }

    @Override
    public String getAttributeNames() {
        return "testId,terminTestiranjaId,vrednost";
    }

    @Override
    public String getUnknownValues() {
        return "?,?,?";
    }

    @Override
    public void prepareStatement(PreparedStatement ps, OpstiDomenskiObjekat odo) throws Exception {
        Rezultat r = (Rezultat) odo;
        ps.setLong(1, r.getTest().getTestId());
        ps.setLong(2, r.getTerminTestiranja().getTerminTestiranjaId());
        ps.setBoolean(3, r.isVrednost());
    }

    @Override
    public String getUpdateQuery() {
        // proveri sme li ovako sa booleanom
        return "vrednost=?";
    }

    @Override
    public String getID(OpstiDomenskiObjekat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCondition(OpstiDomenskiObjekat entity) {
        return "terminTestiranjaId=" + terminTestiranja.getTerminTestiranjaId();
    }

    @Override
    public String getOrderCondition() {
        return "vrednost";
    }

}
