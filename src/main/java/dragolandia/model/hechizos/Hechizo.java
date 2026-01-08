package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import jakarta.persistence.*;

@Entity
@Table(name = "hechizo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_hechizo", discriminatorType = DiscriminatorType.STRING)
public abstract class Hechizo{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int damage;
    private int debuff;

    protected Hechizo() {}

    protected Hechizo(String nombre, int damage, int debuff){
        this.nombre = nombre;
        this.damage = damage;
        this.debuff = debuff;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getDamage() {
        return damage;
    }

    public int getDebuff() {
        return debuff;
    }

    public abstract void efecto();

    public abstract void efecto(Mago mago, List<Monstruo> objetivos);
}