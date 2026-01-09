package dragolandia.controller;

import java.util.ArrayList;
import java.util.List;
import dragolandia.model.Bosque;
import dragolandia.model.Dragon;
import dragolandia.model.Monstruo;
import jakarta.persistence.EntityManager;

public class GestorBosque {
    
    /**
     * Método para registrar el bosque en la base de datos. A su vez también almacenará los monstruos que 
     * le hayan sido facilitados en la lista de monstruos correspondiente.
     * @param nombre
     * @param nivelPeligro
     * @param monstruoJefe
     * @param monstruos
     * @param dragon
     * @return
     */
    public boolean addBosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, List<Monstruo> monstruos, Dragon dragon){
        
        boolean added = false;

        if(validarBosque(nombre, nivelPeligro)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {
                
                try {
                    
                    em.getTransaction().begin();

                    List <Monstruo> monstruosMerged = new ArrayList<>();

                    for (Monstruo monstruo : monstruos) {
                        Monstruo monstruoMerged = em.merge(monstruo);
                        monstruosMerged.add(monstruoMerged);
                    }

                    Bosque bosque = new Bosque(nombre, nivelPeligro, monstruoJefe, monstruosMerged, dragon);

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

    public boolean updateNombre(int id, String nombre){
        boolean actualizado = false;

        

        return actualizado;
    }

    /**
     * Método que nos permite asegurarnos de que los atributos del bosque cumplen unos requisitos concretos.
     * @param nombre
     * @param nivelPeligro
     * @return
     */
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

