package dragolandia;

import dragolandia.controller.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        
        /* GestorMago gestorMago = new GestorMago();

        BolaFuego bola = new BolaFuego();
        Debilitar debil = new Debilitar();

        gestorMago.addMago("Gandalf", 1500, 200, List.of(bola, debil));
        gestorMago.addMago("Saruman", 1300, 180, List.of(bola, debil));
        gestorMago.addMago("Harry Potter", 700, 100, List.of(bola, debil));

        Monstruo reyBrujo = new Monstruo("Rey Brujo de Angmar", 1200, Tipo.ESPECTRO, 450);
        Monstruo urukhai = new Monstruo("Morzag Hojanegra", 450, Tipo.OGRO, 150);
        Monstruo orco = new Monstruo("Sniz", 330, Tipo.OGRO, 120);
        Monstruo orco1 = new Monstruo("Grak el Flaco", 330, Tipo.OGRO, 120);
        Monstruo orco2 = new Monstruo("Ushrat", 330, Tipo.OGRO, 120);
        Monstruo orco3 = new Monstruo("Bogdûl", 330, Tipo.OGRO, 120);
        List<Monstruo> monstruosFangorn = new ArrayList<>();
        monstruosFangorn.add(reyBrujo);
        monstruosFangorn.add(urukhai);
        monstruosFangorn.add(orco);
        monstruosFangorn.add(orco1);
        monstruosFangorn.add(orco2);
        monstruosFangorn.add(orco3);

        Dragon dragon = new Dragon("Urus", 300, 8000);

        gestorBosque.addBosque("Fangorn", 10, reyBrujo, monstruosFangorn, dragon); */

        HibernateUtil.shutdown();
    }
}