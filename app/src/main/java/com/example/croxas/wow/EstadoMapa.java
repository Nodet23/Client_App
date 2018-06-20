package com.example.croxas.wow;
public class EstadoMapa {
    private int x;
    private int y;
    private boolean exist;

    public EstadoMapa(int x, int y, boolean exist) {
        this.x = x;
        this.y = y;
        this.exist = exist;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}


