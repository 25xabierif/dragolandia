package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBILITAR")
public class Debilitar extends Hechizo{

    public Debilitar(){
        super("Debilitar",0,60);
    }

    @Override
    public void efecto(Mago mago, List<Monstruo> objetivos) {
        objetivos.forEach(monstruo -> {
            int fuerzaFinal = (int)monstruo.getFuerza()*(super.getDebuff())/100;
            monstruo.setFuerza(fuerzaFinal);
        });
    }

    @Override
    public void efecto() {}
    
}
