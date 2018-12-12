package inc.alfaleon.pruebarv;


import java.io.Serializable;

public class Monstruo implements Serializable {

    private String nombre;
    private String descripcion;
    private String descripcionLarga;
    private int foto;


    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }



    public Monstruo(){

    }

    public Monstruo(String nombre, String descripcion, int foto){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.foto=foto;
    }

    public Monstruo(String nombre, String descripcion, String descripcionLarga){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this. descripcionLarga = descripcionLarga;
    }

    public Monstruo(String nombre, String descripcion, String descripcionLarga, int foto){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.descripcionLarga=descripcionLarga;
        this.foto=foto;

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
