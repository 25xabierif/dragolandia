package dragolandia.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "bosque")
public class Bosque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int nivelPeligro;

    @OneToOne
    @JoinColumn(name = "monstruo_jefe_id")
    private Monstruo monstruoJefe;

    @OneToOne
    @JoinColumn(name = "dragon_jefe_id")
    private Dragon dragon;

    @OneToMany (
        mappedBy = "bosque",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Monstruo> monstruos = new ArrayList<Monstruo>();

    public Bosque(){}

    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, List<Monstruo> monstruos, Dragon dragon) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
        this.monstruos = monstruos;
        this.dragon = dragon;
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

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public Dragon getDragon(){
        return dragon;
    }

    public void setDragon(Dragon dragon){
        this.dragon = dragon;
    }

    public List<Monstruo> getListaMonstruos(){
        return monstruos;
    }

    public void addMonstruo(Monstruo monstruo){
        this.monstruos.add(monstruo);
        monstruo.setBosque(this);
    }

    public void removeMonstruo(Monstruo monstruo){
        this.monstruos.remove(monstruo);
        monstruo.setBosque(null);
    }

    @Override
    public String toString() {
        return "Bosque [id=" + id + ", nombre=" + nombre + ", nivelPeligro=" + nivelPeligro + ", monstruoJefe="
                + monstruoJefe.getNombre() + "]";
    }

    @Transient
    public void mostrarJefe(){
        System.out.println("Tipo monstruo: "+monstruoJefe.getTipo()+"  \nNombre: "+monstruoJefe.getNombre()+ "\nVida: "+monstruoJefe.getVida()+"\nFuerza: "+monstruoJefe.getFuerza());
    }

}
