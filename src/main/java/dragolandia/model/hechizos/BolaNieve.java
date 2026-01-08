package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOLA_NIEVE")
public class BolaNieve extends Hechizo{

    public BolaNieve(){
        super("Bola de nieve",25,20);
    }

    @Override
    public void efecto(Mago mago, List<Monstruo> objetivos) {
        objetivos.forEach(monstruo -> {
            monstruo.setVida(monstruo.getVida()-(super.getDamage()+mago.getNivelMagia())/4);
            monstruo.setFuerza(monstruo.getFuerza()-monstruo.getFuerza()*(super.getDebuff())/100);
        });
    }

    @Override
    public void efecto() {}
}
