package dragolandia;

import java.util.List;

import dragolandia.controller.GestorMago;
import dragolandia.controller.HibernateUtil;
import dragolandia.model.hechizos.BolaFuego;
import dragolandia.model.hechizos.Debilitar;

public class Main {
    public static void main(String[] args) {
        
        GestorMago gestorMago = new GestorMago();

        BolaFuego bola = new BolaFuego();
        Debilitar debil = new Debilitar();

        gestorMago.addMago("Gandalf", 1500, 200, List.of(bola, debil));
        gestorMago.addMago("Saruman", 1300, 180, List.of(bola, debil));
        gestorMago.addMago("Harry Potter", 700, 100, List.of(bola, debil));

        HibernateUtil.shutdown();
    }
}