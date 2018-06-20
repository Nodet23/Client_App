package com.example.croxas.wow;

/**
 * Created by Croxas on 17/12/17.
 */

public class DAO_InterfaceUserImp {
    public int id = 0;
    protected boolean idAutogen = true;
    protected boolean hasId = true;


    public boolean isIdAutogen() {
        return idAutogen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAutogen(boolean idAutogen) {
        this.idAutogen = idAutogen;
    }

    public boolean isHasId() {
        return hasId;
    }

    public void setHasId(boolean hasId) {
        this.hasId = hasId;
    }

}
