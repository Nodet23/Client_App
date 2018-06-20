package com.example.croxas.wow;

import java.io.Serializable;

/**
 * Created by Croxas on 17/12/17.
 */

public class Objeto implements Serializable{
    private String Nombre;
    private String Tipo;
    private String Descripcion;
    private int Valor;
    private int Coste;
    private int Cantidad;

    //Añadido para la parte de android
    private String urlIcon;

    public Objeto(){

    }

    public Objeto(String nombre, String t, String d, int v, int c, int cantidad, String urlIcon)
    {
        this.Nombre = nombre;
        this.Tipo = t;
        this.Descripcion = d;
        this.Valor = v;
        this.Coste = c;
        this.Cantidad = cantidad;

        this.urlIcon = urlIcon;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }

    public int getCoste() {
        return Coste;
    }

    public void setCoste(int coste) {
        Coste = coste;
    }

    public String getUrlIcon() {return urlIcon;}

    public void setUrlIcon(String urlIcon) {this.urlIcon = urlIcon;}

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
}