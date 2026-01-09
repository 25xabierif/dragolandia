package dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import dragolandia.model.hechizos.Hechizo;
import jakarta.persistence.*;

@Entity
@Table(name = "mago")
public class Mago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hechizo> hechizos = new ArrayList<>();

    public Mago(){}

    public Mago(String nombre, int vida, int nivelMagia, List<Hechizo> hechizos){
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
        this.hechizos = hechizos;
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

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public List<Hechizo> getHechizos() {
        return hechizos;
    }

    public void setHechizos(List<Hechizo> hechizos) {
        this.hechizos = hechizos;
    }

    public void addHechizo(Hechizo hechizo){
        hechizos.add(hechizo);
    }

    public void popHechizo(Hechizo hechizo){
        hechizos.remove(hechizo);
    }

    @Override
    public String toString() {
        return "Mago id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + ".";
    }

    /**
     * Método que permite elegir un monstruo y lanzarle un hechizo
     * @param monstruo
     */
    @Transient
    public void lanzarHechizo(Monstruo monstruo){
        int vidaActualizada = monstruo.getVida() - nivelMagia;
        monstruo.setVida(vidaActualizada);
        System.out.println(monstruo.getNombre()+" ha recibido "+nivelMagia+ " puntos de daño. Vida restante: "+monstruo.getVida()+".");
    }

    /**
     * Método que permite elegir un hechizo y un monstruo objetivo 
     * @param hechizo
     * @param monstruo
     */
    @Transient
    public void lanzarHechizo(Hechizo hechizo, Monstruo monstruo){
        if(this.hechizos.contains(hechizo)){
            
        }else{
            this.setVida(vida-1);
        }
    }
}
