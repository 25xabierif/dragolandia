package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOLA_FUEGO")
public class BolaFuego extends Hechizo{

    public BolaFuego(){
        super("Bola de Fuego",50,0);
    }

    @Override
    public void efecto(Mago mago, List<Monstruo> objetivos) {
       objetivos.forEach(monstruo -> monstruo.setVida(monstruo.getVida()-(super.getDamage()+mago.getNivelMagia())/2)); 
    }

    @Override
    public void efecto() {}
    
}