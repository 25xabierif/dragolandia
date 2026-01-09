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

    /**
     * Método al que entregamos un id y un nombre y modificamos el nombre del bosque asociado a ese id
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateNombre(int id, String nombre){
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Bosque bosque = em.find(Bosque.class, id);

                if(bosque != null){

                    bosque.setNombre(nombre);
                    em.merge(bosque);
                    em.getTransaction().commit();

                    actualizado = true;
                    System.out.println("El nombre del bosque se ha actualizado con éxito. Nuevo nombre: "+bosque.getNombre());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el nombre del bosque: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error en acceso a la sesión: "+e.getMessage());
        }

        return actualizado;
    }

    /**
     * Método para actualizar el nivel de peligro del bosque según un id entregado.
     * @param id
     * @param nivelPeligro
     * @return
     */
    public boolean updateNivelPeligro(int id, int nivelPeligro){
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Bosque bosque = em.find(Bosque.class, id);

                if(bosque != null){

                    bosque.setNivelPeligro(nivelPeligro);
                    em.merge(bosque);
                    em.getTransaction().commit();
                    
                    actualizado = true;
                    System.out.println("El nivel de pelirgro del bosque se ha actualizado con éxito. Nuevo nivel de peligro: "+bosque.getNivelPeligro());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el nivel de peligro del bosque: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error en acceso a la sesión: "+e.getMessage());
        }

        return actualizado;
    }

    /**
     * Método que actualiza el monstruo jefe del bosque, previamente comprobando que el monstruo
     * sea uno de los monstruos de la lista de monstruos del bosque.
     * @param id
     * @param monstruo
     * @return
     */
    public boolean updateMonstruoJefe(int id, Monstruo monstruo){
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Bosque bosque = em.find(Bosque.class, id);

                if(bosque != null && bosque.getListaMonstruos().contains(monstruo)){

                    bosque.setMonstruoJefe(monstruo);
                    em.merge(bosque);
                    em.getTransaction().commit();
                    
                    actualizado = true;
                    System.out.println("El monstruo jefe del bosque se ha actualizado con éxito. Nuevo monstruo jefe: "+bosque.getMonstruoJefe().getNombre());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el monstruo jefe del bosque: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error en acceso a la sesión: "+e.getMessage());
        }

        return actualizado;
    }

    /**
     * Método que nos permite actualizar el dragón del bosque
     * @param id
     * @param dragon
     * @return
     */
    public boolean updateDragon(int id, Dragon dragon){
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();

                Bosque bosque = em.find(Bosque.class, id);

                if(bosque != null){

                    bosque.setDragon(dragon);
                    em.merge(bosque);
                    em.getTransaction().commit();
                    
                    actualizado = true;
                    System.out.println("El dragon del bosque se ha actualizado con éxito. Nuevo dragon: "+bosque.getDragon());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el dragon del bosque: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error en acceso a la sesión: "+e.getMessage());
        }

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

