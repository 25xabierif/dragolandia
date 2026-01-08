package dragolandia.controller;

import java.util.List;
import dragolandia.model.Bosque;
import dragolandia.model.Dragon;
import dragolandia.model.Monstruo;
import jakarta.persistence.EntityManager;

public class GestorBosque {
    
    public boolean addBosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, Dragon dragon, List<Monstruo> monstruos){
        
        boolean added = false;

        if(validarBosque(nombre, nivelPeligro)){

            Bosque bosque = new Bosque(nombre, nivelPeligro, monstruoJefe);

            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    em.getTransaction().begin();
                    em.persist(bosque);
                    em.getTransaction().commit();

                    added = true;
                    System.out.println("El bosque '"+bosque.getNombre()+"' se ha registrado en la BD con éxito.");
                } catch (Exception e) {
                    System.err.println("No se ha podido registrar el bosque: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("Error en acceso a la sesión: "+e.getMessage());
            }
        }
        return added;
    }

    public boolean validarBosque(String nombre, int nivelPeligro){
        boolean bosqueValido = true;
        if(nombre == null || nombre.trim().isEmpty()){
            System.err.println("El nombre no puede estar vacío.");
            bosqueValido = false;
        }
        if(nivelPeligro <= 0){
            System.err.println("El nivel de peligro debe ser mayor que 0");
            bosqueValido = false;
        }
        return bosqueValido;
    }
}

