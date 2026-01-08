package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Mago;
import dragolandia.model.Monstruo;

public class Escudo extends Hechizo{

    public Escudo(){
        super("Escudo",0,0);
    }

    @Override
    public void efecto() {
    }

    @Override
    public void efecto(Mago mago, List<Monstruo> objetivos) {}
    
}
