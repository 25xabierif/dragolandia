package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RAYO")
public class Rayo extends Hechizo{
    
    public Rayo (){
        super("Rayo",100,0);
    }

    @Override
    public void efecto(Mago mago, List<Monstruo> objetivos) {
        objetivos.forEach(monstruo -> monstruo.setVida(monstruo.getVida()-(super.getDamage()+mago.getNivelMagia())));
    }

    @Override
    public void efecto() {}
    
}
