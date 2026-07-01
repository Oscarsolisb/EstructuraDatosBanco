/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sc.pkg304_jn_proyecto;

import java.time.LocalDateTime;

/**
 *
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class Cliente {
    
    private String nombre;
    private int id;
    private int edad;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaAtendido = null;
    private Tramite tramite;
    private Tipo tipo;

    public Cliente(String nombre, int id, int edad, Tramite tramite, Tipo tipo) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tramite = tramite;
        this.tipo = tipo;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", id=" + id + ", edad=" + edad + ", fechaCreacion=" + fechaCreacion + ", fechaAtendido=" + fechaAtendido + ", tramite=" + tramite + ", tipo=" + tipo + '}';
    }

    
    
    
    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaAtendido() {
        return fechaAtendido;
    }

    public void setFechaAtendido(LocalDateTime fechaAtendido) {
        this.fechaAtendido = fechaAtendido;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    
    
    
    
}
