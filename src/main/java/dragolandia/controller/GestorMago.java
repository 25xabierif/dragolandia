package dragolandia.controller;

import java.util.ArrayList;
import java.util.List;
import dragolandia.model.Mago;
import dragolandia.model.hechizos.Hechizo;
import jakarta.persistence.EntityManager;

public class GestorMago {
    
    /**
     * Método para añadir mago a la BD que devuelve true en caso de haberlo conseguido
     * @param mago
     * @return
     */
    public boolean addMago(String nombre, int vida, int nivelMagia, List<Hechizo> hechizos){

        boolean added = false;

        if(validarMago(nombre, vida, nivelMagia)){

            try (EntityManager em = HibernateUtil.getEntityManager()) {

                try {

                    em.getTransaction().begin();

                    List<Hechizo> hechizosMerged = new ArrayList<>();

                    for (Hechizo hechizo : hechizos) {
                        Hechizo hechizoMerged = em.merge(hechizo);
                        hechizosMerged.add(hechizoMerged);
                    }

                    Mago mago = new Mago(nombre, vida, nivelMagia, hechizosMerged);

                    em.persist(mago);
                    em.getTransaction().commit();

                    added = true;
                    System.out.println("Mago registrado con éxito en la BD con id: "+mago.getId()+"."); 
                } catch (Exception e) {
                    System.err.println("No se ha podido registrar el mago en la BD: "+e.getMessage());
                }

            } catch (Exception e) {
                System.err.println("Error en la sesión Hibernate: "+e.getMessage());
            }
        }
        return added;
    }

    /**
     * Método que devuelve true en caso de que se haya actualizado la vida del mago después de un ataque
     * @param mago
     * @return
     */
    public boolean updateHP(int id, int hp){
        
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {

            try {

                em.getTransaction().begin();
                
                Mago mago = em.find(Mago.class, id);

                if(mago != null){
                    mago.setVida(hp);
                    em.merge(mago);
                    em.getTransaction().commit();

                    actualizado = true;
                    System.out.println("HP actual: "+mago.getVida());
                }

            } catch (Exception e) {
                System.err.println("No se ha podido actualizar la vida del mago: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }
        return actualizado;
    }

    /**
     * Metodo que selecciona un mago por su id y le actualiza el nombre
     * Devuelve true/false en función de si lo ha conseguido o no
     * @param id
     * @param nombre
     * @return
     */
    public boolean updateName(int id, String nombre){
        
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            

            try {

                em.getTransaction().begin();

                Mago mago = em.find(Mago.class, id);
                if(mago != null){
                    mago.setNombre(nombre);
                    em.merge(mago);
                    em.getTransaction().commit();

                    actualizado = true;
                    System.out.println("Nuevo nombre: "+mago.getNombre()+".");
                }
            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el nombre del mago: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }
        return actualizado;
    }

    /**
     * Método que selecciona un mago por su id y le actualiza el nivel de magia
     * Devuelve true/false en caso de que la operación haya sido realizada con éxito
     * @param id
     * @param nivelMagia
     * @return
     */
    public boolean updateMagicLevel(int id, int nivelMagia){
        
        boolean actualizado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {

            try {
                
                em.getTransaction().begin();
                
                Mago mago = em.find(Mago.class, id);

                if(mago != null){
                    mago.setNivelMagia(nivelMagia);
                    em.merge(mago);
                    em.getTransaction().commit();

                    actualizado = true;
                    System.out.println("Nuevo nivel de magia: "+mago.getNivelMagia()+".");
                }
            } catch (Exception e) {
                System.err.println("No se ha podido actualizar el nivel de magia del mago: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }
        return actualizado;
    }

    /**
     * Método que nos permite añadir hechizos al mago accediendo a este a través de un id
     * @param id
     * @param hechizo
     * @return
     */
    public boolean addHechizo(int id, Hechizo hechizo){
        boolean added = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();
                Mago mago = em.find(Mago.class, id);
                mago.addHechizo(hechizo);
                em.merge(mago);
                em.getTransaction().commit();

                added = true;
                System.out.println("¡El mago "+mago.getNombre()+" ha aprendido el hechizo "+hechizo.getNombre()+"!");

            } catch (Exception e) {
                System.err.println("No se ha podido añadir el hechizo "+hechizo.getNombre()+"al mago: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }

        return added;
    }


    /**
     * Método que nos permite eliminar hechizos del mago accediendo a este a través de un id
     * @param id
     * @param hechizo
     * @return
     */
    public boolean popHechizo(int id, Hechizo hechizo){
        boolean eliminado = false;

        try (EntityManager em = HibernateUtil.getEntityManager()) {
            
            try {
                
                em.getTransaction().begin();
                Mago mago = em.find(Mago.class, id);
                mago.popHechizo(hechizo);
                em.merge(mago);
                em.getTransaction().commit();

                eliminado = true;
                System.out.println("El mago "+mago.getNombre()+" ha olvidado el hechizo "+hechizo.getNombre()+".");

            } catch (Exception e) {
                System.err.println("No se ha podido eliminar el hechizo "+hechizo.getNombre()+"al mago: "+e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("No se ha podido acceder a la sesión: "+e.getMessage());
        }

        return eliminado;
    }

    /**
     * Método que comprueba que los parámetros del mago introducido cumplan unos estándares mínimos
     * @param nombre
     * @param vida
     * @param nivelMagia
     * @return
     */
    private boolean validarMago(String nombre, int vida, int nivelMagia) {
        boolean magoValido = true;
        if (nombre == null || nombre.trim().isEmpty()) {
            System.err.println("El nombre no puede estar vacío.");
            magoValido = false;
        }
        if (vida <= 0) {
            System.err.println("La vida debe ser mayor que 0.");
            magoValido = false;
        }
        if (nivelMagia <= 0) {
            System.err.println("El nivel de magia debe ser mayor que 0.");
            magoValido = false;
        }
        return magoValido;
    }
}
