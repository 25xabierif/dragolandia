package dragolandia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "monstruo")
public class Monstruo {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private int fuerza;

    @ManyToOne
    @JoinColumn(name = "bosque_id")
    private Bosque bosque;

    public Monstruo(){}

    public Monstruo(String nombre, int vida, Tipo tipo, int fuerza){
        this.nombre = nombre;
        this.vida = vida;
        this.tipo = tipo;
        this.fuerza = fuerza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public Bosque getBosque(){
        return this.bosque;
    }

    public void setBosque(Bosque bosque){
        this.bosque = bosque;
    }

    @Override
    public String toString() {
        return "Monstruo [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", tipo=" + tipo + ", fuerza=" + fuerza
                + "]";
    }

    @Transient
    public void atacar(Mago mago){
        int vidaActualizada = mago.getVida() - fuerza;
        mago.setVida(vidaActualizada);
        System.out.println(mago.getNombre()+" ha recibido "+fuerza+" de daño. Vida restante: "+mago.getVida()+".");
    }

}
