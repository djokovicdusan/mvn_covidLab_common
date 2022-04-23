/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Dule Djo
 */
public class ServerskiOdgovor implements Serializable{
    private int operacija;
    private Object parametar;
    private boolean uspesno;
    private Exception greska;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(int operacija, Object parametar, boolean uspesno, Exception greska) {
        this.operacija = operacija;
        this.parametar = parametar;
        this.uspesno = uspesno;
        this.greska = greska;
    }

    /**
     * @return the parametar
     */
    public Object getParametar() {
        return parametar;
    }

    /**
     * @param parametar the parametar to set
     */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    /**
     * @return the uspesno
     */
    public boolean isUspesno() {
        return uspesno;
    }

    /**
     * @param uspesno the uspesno to set
     */
    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    /**
     * @return the greska
     */
    public Exception getGreska() {
        return greska;
    }

    /**
     * @param greska the greska to set
     */
    public void setGreska(Exception greska) {
        this.greska = greska;
    }

    /**
     * @return the operacija
     */
    public int getOperacija() {
        return operacija;
    }

    /**
     * @param operacija the operacija to set
     */
    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }
    
}
